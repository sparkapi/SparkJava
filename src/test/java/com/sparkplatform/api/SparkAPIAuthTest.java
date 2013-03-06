package com.sparkplatform.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

import com.sparkplatform.api.core.Configuration;

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
	public void testIsHybridAuthorized() {
		assertNotNull(SparkAPI.isHybridAuthorized(hybridAuthorizationURLPass));
		assertNull(SparkAPI.isHybridAuthorized(authorizationURLFail));
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
	
}
