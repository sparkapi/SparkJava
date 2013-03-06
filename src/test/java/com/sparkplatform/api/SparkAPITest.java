package com.sparkplatform.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.sparkplatform.api.core.Configuration;

public class SparkAPITest {
	
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
	
}
