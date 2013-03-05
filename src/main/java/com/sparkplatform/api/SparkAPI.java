package com.sparkplatform.api;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.Connection;
import com.sparkplatform.api.core.ConnectionApacheHttp;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.core.Session;

public class SparkAPI extends Client {

	// configuration **********************************************************
	
	public static final String sparkCallbackURL = "https://sparkplatform.com/oauth2/callback";
	
	// constants **************************************************************
	
	public static final String sparkOpenIdLogoutURL = "https://sparkplatform.com/openid/logout";
	
	public static final String sparkAPILibrary = "Spark Android API 1.0";
	public static final String sparkOAuth2Grant = "/oauth2/grant";
	
	// class vars *************************************************************
	
	private static Logger logger = Logger.getLogger(SparkAPI.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private static SparkAPI instance;
	private static Configuration configuration;
	
	// class interface ********************************************************

	public static SparkAPI getInstance()
	{
		if(instance == null)
		{
			if(configuration == null)
				configuration = Configuration.load();
		    instance = new SparkAPI(configuration);
		}
		return instance;
	}
	
	public static void setConfiguration(Configuration c)
	{
		configuration = c;
	}
	
	public static Configuration getConfiguration()
	{
		return configuration;
	}
	
	private SparkAPI(Configuration config) {
		super(config);
	}
	
	private SparkAPI(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}

	// authentication *********************************************************
	
	public boolean isHybridSession()
	{
		SparkSession session = (SparkSession)getSession();
		return session != null && session.getAccessToken() != null && session.getRefreshToken() != null;
	}
	
	public boolean isOpenIDSession()
	{
		SparkSession session = (SparkSession)getSession();
		return session != null && session.getOpenIdToken() != null;
	}
	
	public String getSparkOpenIdURLString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("https://sparkplatform.com/openid");
		builder.append("?openid.mode=checkid_setup");
		builder.append("&openid.spark.client_id=");
		encodeParam(builder, getConfig().getApiKey());
		builder.append("&openid.return_to=");
		encodeParam(builder, sparkCallbackURL);
		return builder.toString();
	}
	
	public String getSparkOpenIdAttributeExchangeURLString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(getSparkOpenIdURLString());
		builder.append("&openid.ax.mode=fetch_request");
		builder.append("&openid.ax.type.first_name=");
		encodeParam(builder,"http://openid.net/schema/namePerson/first");
		builder.append("&openid.ax.type.last_name=");
		encodeParam(builder,"http://openid.net/schema/namePerson/last");
		builder.append("&openid.ax.type.middle_name=");
		encodeParam(builder,"http://openid.net/schema/namePerson/middle");
		builder.append("&openid.ax.type.friendly=");
		encodeParam(builder,"http://openid.net/schema/namePerson/friendly");
		builder.append("&openid.ax.type.id=");
		encodeParam(builder,"http://openid.net/schema/person/guid");		
		builder.append("&openid.ax.type.email=");
		encodeParam(builder,"http://openid.net/schema/contact/internet/email");
		builder.append("&openid.ax.required=");
		encodeParam(builder,"first_name,last_name,middle_name,friendly,id,email");
		return builder.toString();
	}
	
	public String getSparkHybridOpenIdURLString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(getSparkOpenIdURLString());
		builder.append("&openid.spark.combined_flow=true");
		return builder.toString();
	}

	public String getSparkOAuth2GrantString()
	{
		Configuration c = getConfig();
		return "https://" + c.getEndpoint()  + "/" + c.getVersion() + sparkOAuth2Grant;
	}
	
	public static String isHybridAuthorized(String url)
	{
		List<NameValuePair> params = getURLParams(url);
		if(params == null)
			return null;
		
		String openIdMode = null;
		String openIdSparkCode = null;
		
	    return ((openIdMode = getParameter(params,"openid.mode")) != null &&
	       openIdMode.equals("id_res") &&
	       (openIdSparkCode = getParameter(params,"openid.spark.code")) != null) ? openIdSparkCode : null;
	}
	
	public SparkSession hybridAuthenticate(String openIdSparkCode) throws SparkAPIClientException
	{
		Configuration c = getConfig();
		Map<String,String> map = new HashMap<String,String>();
		map.put("client_id", c.getApiKey());
		map.put("client_secret", c.getApiSecret());
		map.put("grant_type", "authorization_code");
		map.put("code", openIdSparkCode);
		map.put("redirect_uri", SparkAPI.sparkCallbackURL);

		SparkSession sparkSession = null;
		try
		{
			HttpPost post = new HttpPost(getSparkOAuth2GrantString());
			initSparkHeader(post);
			StringEntity stringEntity = new StringEntity(objectMapper.writeValueAsString(map));
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			post.setEntity(stringEntity);
			HttpClient httpclient = new DefaultHttpClient(); 
			HttpResponse response = httpclient.execute(post);
			String responseBody = EntityUtils.toString(response.getEntity());
			logger.debug("OAuth2 response>" + responseBody);
			sparkSession = objectMapper.readValue(responseBody, SparkSession.class);
			setSession(sparkSession);
		} 
		catch (Exception e)
		{
			logger.error("exception>", e);
		}

		return sparkSession;
	}

	public SparkSession openIdAuthenticate(String url) throws SparkAPIClientException
	{
		List<NameValuePair> params = getURLParams(url);
		if(params == null)
			return null;
		
		String openIdMode = null;
		if((openIdMode = getParameter(params,"openid.mode")) != null &&
		   "id_res".equals(openIdMode))
		{
			String openIdSparkId = getParameter(params,"openid.ax.value.id");
			logger.debug("openIdToken>" + openIdSparkId);
			SparkSession session = new SparkSession();
			session.setOpenIdToken(openIdSparkId);
			setSession(session);
			return session;
		}
		
		return null;
	}
	
	
	protected Session authenticate() throws SparkAPIClientException 
	{
		Configuration c = getConfig();
		Map<String,String> map = new HashMap<String,String>();
		map.put("client_id", c.getApiKey());
		map.put("client_secret", c.getApiSecret());
		map.put("grant_type", "refresh_token");
		map.put("refresh_token", getSparkSession().getRefreshToken());
		map.put("redirect_uri", SparkAPI.sparkCallbackURL);

		Response response = null;
		try {
			response = getConnection().post(getSparkOAuth2GrantString(),objectMapper.writeValueAsString(map));
		} catch (Exception e) {
			throw new SparkAPIClientException("Session mapping error",e);
		}
		List<SparkSession> sessions = response.getResults(SparkSession.class);
		if(sessions.isEmpty()){
			throw new SparkAPIClientException("Service error.  No session returned for service authentication.");
		}
		Session s = sessions.get(0);
		setSession(s);
		return s;
	}
	
	// helper methods *********************************************************
	
	public static List<NameValuePair> getURLParams(String url)
	{
		List<NameValuePair> params = null;
		try {
			params = URLEncodedUtils.parse(new URI(url), "UTF-8");
		} catch (URISyntaxException e) {
			logger.error("malformed URL", e);
		}
		return params;
	}
	
	public static String getParameter(List<NameValuePair> params, String name)
	{
		for(NameValuePair nameValuePair : params)
			if(nameValuePair.getName().equals(name))
				return nameValuePair.getValue();
		
		return null;
	}
	
	public void initSparkHeader(HttpUriRequest httpRequest) throws SparkAPIClientException
	{		
		Map<String, String> headers = getDefaultHeaders();
		for(String key : headers.keySet())
			httpRequest.setHeader(key,headers.get(key));
	}
	
	public Map<String,String> getDefaultHeaders() throws SparkAPIClientException
	{
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("User-Agent", sparkAPILibrary);
		String sparkAPIUserAgent = getConfig().getUserAgent();
		if(sparkAPIUserAgent == null || sparkAPIUserAgent.trim().length() == 0)
			throw new SparkAPIClientException("Please set the sparkAPIUserAgent for your application!");
		else
			headers.put("X-SparkApi-User-Agent", sparkAPIUserAgent);
		return headers;
	}
	
	public Map<String,String> getHeaders() throws SparkAPIClientException
	{
		Map<String,String> headers = getDefaultHeaders();
		SparkSession session = (SparkSession)getSession();
		if(session != null && session.getAccessToken() != null)
			headers.put("Authorization", "OAuth " + session.getAccessToken());
		return headers;
	}
	
	private void encodeParam(StringBuilder builder, String value)
	{
		try {
			builder.append(URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	protected String requestPath(String path, Map<String, String> params) {
		StringBuilder b = new StringBuilder();
		b.append("/").append(getConfig().getVersion()).append(path).append("?");
		for (String key : params.keySet()) {
			b.append("&").append(key).append("=").append(encode(params.get(key)));
		}
		return b.toString();
	}
	
	protected String setupRequest(String path, String body, Map<String, String> options) {
		Map<String, String> params = new HashMap<String,String>();
		if(options != null)
			params.putAll(options);
		return requestPath(path, params);
	}
	
	public void setSession(Session session) throws SparkAPIClientException
	{
		super.setSession(session);
		Connection<Response> connection = getConnection();
		((ConnectionApacheHttp)connection).setHeaders(getHeaders());
	}
	
	public SparkSession getSparkSession()
	{
		return (SparkSession)getSession();
	}
}
