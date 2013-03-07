package com.sparkplatform.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockConnection;

public class SparkAPIAuthTest {
	
	private static final String authorizationURLBase = 
			"https://sparkplatform.com/oauth2/callback?";
	private static final String hybridAuthorizationURLPass = 
			authorizationURLBase + "openid.mode=id_res&openid.spark.code=foobar";
	private static final String authorizationURLFail = 
			"https://sparkplatform.com/ticket";
	
	private static final String openIdAuthorizationURLPass = 
			authorizationURLBase + "openid.mode=id_res&openid.ax.value.id=foobar";
	
	@Test
	public void testSparkHeaders()
	{
		SparkAPI sparkAPI = new SparkAPI(new Configuration());
		try {
			sparkAPI.getHeaders();
			fail("SparkAPIClientExeption not thrown");
		} catch (SparkAPIClientException e) {
			// success
		}
		
		// default headers
		try {
			Configuration c = new Configuration();
			c.setUserAgent("SparkAPITest");
			sparkAPI.setConfig(c);
			Map<String, String> headers = sparkAPI.getHeaders();
			assertNotNull(headers.get(SparkAPI.userAgentHeader));
			assertNotNull(headers.get(SparkAPI.apiUserAgentHeader));
			assertNull(headers.get(SparkAPI.authorizationHeader));
		} catch (SparkAPIClientException e) {
			fail("exception thrown");
		}
		
		// session headers
		try {
			SparkSession session = new SparkSession();
			session.setAccessToken("accessToken");
			sparkAPI.setSession(session);
			Map<String, String> headers = sparkAPI.getHeaders();
			assertNotNull(headers.get(SparkAPI.authorizationHeader));
		} catch (SparkAPIClientException e) {
			fail("exception thrown");
		}
	}
	
	
	@Test
	public void testIsHybridAuthorized() {
		assertNotNull(SparkAPI.isHybridAuthorized(hybridAuthorizationURLPass));
		assertNull(SparkAPI.isHybridAuthorized(authorizationURLFail));
	}
	
	@Test
	public void testHybridAuthenticateSuccess() {
		try {

			SparkAPI sparkAPI = getSparkAPI();
			String openIdSparkCode = "openIdSparkCode";

			((MockConnection)sparkAPI.getConnection()).stubPost(
					"/" + sparkAPI.getConfig().getVersion() + "/oauth2/grant",
					sparkAPI.getOAuthRequestJSON(openIdSparkCode),
					"spark_OAuthSuccess.json", 
					200);

			SparkSession session = sparkAPI.hybridAuthenticate(openIdSparkCode);
			assertNotNull(session);
			assertEquals(session.getAccessToken(),"accessToken");
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testHybridAuthenticateFailure() {
		try {
			SparkAPI sparkAPI = getSparkAPI();
			String openIdSparkCode = "openIdSparkCode";
			
			((MockConnection)sparkAPI.getConnection()).stubPost(
					"/" + sparkAPI.getConfig().getVersion() + "/oauth2/grant",
					sparkAPI.getOAuthRequestJSON(openIdSparkCode),
					"spark_OAuthFailure.json", 
					200);

			SparkSession session = sparkAPI.hybridAuthenticate(openIdSparkCode);
			assertTrue(session.hasError());
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	private SparkAPI getSparkAPI()
	{
		Configuration c = new Configuration();
		c.setApiKey("ApiKey");
		c.setApiSecret("ApiSecret");
		c.setUserAgent("SparkAPITest");
		MockConnection connection = new MockConnection();
		return new SparkAPI(c,connection,connection);
	}
	
	@Test
	public void testOpenIdAuthenticate() throws SparkAPIClientException {
		Configuration c = new Configuration();
		SparkAPI.setConfiguration(c);
		c.setUserAgent("SparkAPITest");

		SparkAPI sparkAPI = SparkAPI.getInstance();
		assertNotNull(sparkAPI.openIdAuthenticate(openIdAuthorizationURLPass));
		assertNull(sparkAPI.openIdAuthenticate(authorizationURLFail));
	}
	
}
