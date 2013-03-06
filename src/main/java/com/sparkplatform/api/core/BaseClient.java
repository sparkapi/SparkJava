//
//  Copyright (c) 2013 Financial Business Systems, Inc. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

package com.sparkplatform.api.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.SparkAPIException;

/**
 * Client class for communicating with the flexmls restful interface.  Abstracts the HTTP,
 * authentication, and request signing layers to ease communication with the service.
 *
 * @param <U> The parameter type accepted by the client implementation.  The value can be anything 
 * so long as the <pre>stringifyParameterKeys()</pre> method converts it to a String for consumption.
 */
public abstract class BaseClient<U> implements HttpActions<Response, U>{

	private static Logger logger = Logger.getLogger(BaseClient.class);
	private Configuration config;
	private Connection<Response> connection;
	private Connection<Response> secure;
	private Session session;

	/**
	 * Configure the client with general settings, and connection implementations
	 * @param config
	 * @param defaultConnection HTTP connection
	 * @param secureConnection SSL based HTTP connection (for authentication at least)
	 */
	public BaseClient(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super();
		this.config = config;
		this.secure = secureConnection;
		this.connection = defaultConnection;
	}
	
	/**
	 * Configure the client with general settings, and default connection settings
	 * @param config
	 */
	public BaseClient(Configuration config) {
		super();
		this.config = config;
		this.secure = new ConnectionApacheHttps(config);
		this.connection = config.isSsl() ? secure : new ConnectionApacheHttp(config);
	}

	@Override
	public Response get(String path, Map<U, String> options)
			throws SparkAPIClientException {
				return new ReAuthable("GET", path, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws SparkAPIClientException {
						return connection.get(path);
					}
				}.execute();
			}

	@Override
	public Response post(String path, String body, Map<U, String> options)
			throws SparkAPIClientException {
				return new ReAuthable("POST", path, body, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws SparkAPIClientException {
						return connection.post(path,body);
					}
				}.execute();
			}

	@Override
	public Response put(String path, String body, Map<U, String> options)
			throws SparkAPIClientException {
				return new ReAuthable("PUT", path, body, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws SparkAPIClientException {
						return connection.put(path,body);
					}
				}.execute();
			}

	@Override
	public Response delete(String path, Map<U, String> options)
			throws SparkAPIClientException {
				return new ReAuthable("DELETE", path, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws SparkAPIClientException {
						return connection.delete(path);
					}
				}.execute();
			}

	abstract Map<String, String> stringifyParameterKeys(Map<U, String> parms);

	protected void log(String action, String path) {
		if(logger.isDebugEnabled()){
			logger.debug("Request: [" + action + "] - " + path);
		}
	}

	protected void reauth() throws SparkAPIClientException {
		if(session == null || session.isExpired()){
			authenticate();
		}
	}

	protected Session authenticate() throws SparkAPIClientException {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		String signature = sign(b.toString());
		String path = authPath(signature);
		log("AUTH-POST", path);
		Response response = secure.post(path,"");
		List<Session> sessions = response.getResults(Session.class);
		if(sessions.isEmpty()){
			throw new SparkAPIClientException("Service error.  No session returned for service authentication.");
		}
		Session s = sessions.get(0);
		setSession(s);
		return s;
	}

	private String authPath(String sig) {
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append("/session?");
		b.append("ApiKey=").append(config.getApiKey());
		b.append("&ApiSig=").append(sig);
		return b.toString();
	}

	protected Map<String,String> sessionParams() {
		Map<String, String> params = new HashMap<String, String>();
		if(config.getApiUser() != null){
			params.put("ApiUser", config.getApiUser());
		}
		params.put("AuthToken", session.getToken());
		return params;
	}

	protected String requestPath(String path, String signature, Map<String, String> params) {
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append(path).append("?");
		b.append("ApiSig").append("=").append(signature);
		for (String key : params.keySet()) {
			b.append("&").append(key).append("=").append(encode(params.get(key)));
		}
		return b.toString();
	}
	
	protected String encode(String s){
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Unlikely to happen, but notify at least and resume without encoding.
			logger.error("Unable to encode url parameters as utf-8.", e);
		}
		return s;
	}

	protected String sign(String s) {
		return DigestUtils.md5Hex(s);
	}

	protected String signToken(String path, Map<String, String> options, String body) {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		b.append("ServicePath/").append(config.getVersion()).append(path);
		b.append(buildParamString(options));
		b.append(body);
		return sign(b.toString());
	}

	protected String buildParamString(Map<String, String> params) {
		List<String> list = new ArrayList<String>(params.keySet());
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (String key : list) {
			buffer.append(key).append(encode(params.get(key)));
		}
		return buffer.toString();
	}

	protected String setupRequest(String path, String body, Map<String, String> options) {
		Map<String, String> params = sessionParams();
		if(options != null)
			params.putAll(options);
		String sig = signToken(path, params, body);
		return requestPath(path, sig, params);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) throws SparkAPIClientException {
		this.session = session;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public Connection<Response> getConnection()
	{
		return config.isSsl() ? secure : connection;
	}
	
	abstract class ReAuthable {
		private static final int MAX_RETRIES = 1;
		private String command;
		private String path;
		private String body = "";
		private Map<String, String> options;
		
		public ReAuthable(String command, String path, String body,
				Map<String, String> options) {
			super();
			this.command = command;
			this.path = path;
			this.body = body;
			this.options = options;
		}
		public ReAuthable(String command, String path, Map<String, String> options) {
			this(command, path, "", options);
		}
		public Response execute() throws SparkAPIClientException {
			reauth();
			String apiPath = setupRequest(path, body, options);
			log(command, apiPath);
			int retries = 0;
			while(retries <= MAX_RETRIES){
				try {
					return run(apiPath, body);
				} catch (SparkAPIException e) {
					if(retries <= MAX_RETRIES && ApiCode.SESSION_EXPIRED.equals(e.getCode())){
						authenticate();
					}
					else {
						throw e;
					}
				}
				retries++;
			}
			throw new SparkAPIClientException("Session expired and maximum number of authentication attempts reached.");
		}
		protected abstract Response run(String path, String body) throws SparkAPIClientException;
	}

}