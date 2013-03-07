package com.sparkplatform.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockConnection;
import com.sparkplatform.api.core.Response;

public class SparkAPIAuthTest {
	
	private static final String authorizationURLBase = 
			"https://sparkplatform.com/oauth2/callback?";
	private static final String hybridAuthorizationURLPass = 
			authorizationURLBase + "openid.mode=id_res&openid.spark.code=foobar";
	private static final String authorizationURLFail = 
			"https://sparkplatform.com/ticket";
	private static final String openIdSparkCode = "openIdSparkCode";

	
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

			((MockConnection)sparkAPI.getConnection()).stubPost(
					"/" + sparkAPI.getConfig().getVersion() + "/oauth2/grant",
					sparkAPI.getOAuthRequestJSON("authorization_code",openIdSparkCode,null),
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
					sparkAPI.getOAuthRequestJSON("authorization_code",openIdSparkCode,null),
					"spark_OAuthFailure.json", 
					200);

			try {
				sparkAPI.hybridAuthenticate(openIdSparkCode);
			} catch (SparkAPIClientException e) {
				// success
			}
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
	public void testSessionRenew() {
		try {
			SparkAPI sparkAPI = getSparkAPI();
			SparkSession session = new SparkSession();
			session.setAccessToken("accessToken");
			session.setRefreshToken("refreshToken");
			sparkAPI.setSession(session);
			
			MockConnection mockConnection = ((MockConnection)sparkAPI.getConnection());
			List<MockConnection.Stub> stubs = new ArrayList<MockConnection.Stub>();
			stubs.add(new MockConnection.Stub("failure_sessionRenew.json", 300)); // first call fails
			stubs.add(new MockConnection.Stub("spark_myAccount.json", 200)); // second call passes
			mockConnection.stubGet(
					"/" + sparkAPI.getConfig().getVersion() + "/my/account",
					stubs);
			mockConnection.stubPost(
					"/" + sparkAPI.getConfig().getVersion() + "/oauth2/grant",
					sparkAPI.getOAuthRequestJSON("refresh_token",null,session.getRefreshToken()),
					"spark_OAuthSuccess.json", 
					200);
			
			Response r = sparkAPI.get("/my/account",null);
			assertNotNull(r);
			assertTrue(r.isSuccess());
			JsonNode account = r.getFirstResult();
			assertNotNull(account);
			assertEquals(account.get("Mls").getTextValue(),"Humboldt Association of REALTORS");
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	
	@Test
	public void testOpenIdAuthenticate() {
		try {
			Configuration c = new Configuration();
			SparkAPI.setConfiguration(c);
			c.setUserAgent("SparkAPITest");

			SparkAPI sparkAPI = SparkAPI.getInstance();
			assertNotNull(sparkAPI.openIdAuthenticate(openIdAuthorizationURLPass));
			assertNull(sparkAPI.openIdAuthenticate(authorizationURLFail));
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}
	}
	
}
