package com.sparkplatform.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class SparkAPITest {
	
	private static final String hybridAuthorizationURLBase = 
			"https://sparkplatform.com/oauth2/callback?";
	private static final String hybridAuthorizationURLPass = 
			hybridAuthorizationURLBase + "openid.mode=id_res&openid.spark.code=foobar";
	private static final String hybridAuthorizationURLFail = 
			"https://sparkplatform.com/ticket";
	
	@Test
	public void testIsHybridAuthorized() {
		assertNotNull(SparkAPI.isHybridAuthorized(hybridAuthorizationURLPass));
		assertNull(SparkAPI.isHybridAuthorized(hybridAuthorizationURLFail));
	}
	
}
