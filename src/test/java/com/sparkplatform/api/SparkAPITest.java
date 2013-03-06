package com.sparkplatform.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockConnection;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.models.Listing;

public class SparkAPITest {
	private SparkAPI sparkAPI;
	private Configuration c;
	private MockConnection connection;
	
	@Before
	public void setup(){
		try {
			c = new Configuration();
			c.setApiKey("ApiKey");
			c.setApiSecret("ApiSecret");
			c.setUserAgent("SparkAPITest");
			connection = new MockConnection();
			sparkAPI = new SparkAPI(c,connection,connection);
			SparkSession session = new SparkSession();
			session.setAccessToken("accessToken");
			session.setRefreshToken("refreshToken");
			sparkAPI.setSession(session);
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}
	}
	
	@Test
	public void testGetMyAccount() {
		try {
			connection.stubGet(
					"/" + c.getVersion() + "/my/account",
					"spark_myAccount.json", 
					200);
			Response r = sparkAPI.get("/my/account",null);
			assertTrue(r.isSuccess());
			JsonNode account = r.getFirstResult();
			assertNotNull(account);
			assertEquals(account.get("Mls").getTextValue(),"Humboldt Association of REALTORS");
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}
	}

	@Test
	public void testGetListings() {
		try {
			connection.stubGet(
					"/" + c.getVersion() + "/listings?_limit=1&_filter=PropertyType+Eq+%27A%27",
					"spark_listing.json", 
					200);
			Map<ApiParameter,String> parameters = new HashMap<ApiParameter,String>();
			parameters.put(ApiParameter._limit, "1");
			parameters.put(ApiParameter._filter, "PropertyType Eq 'A'");
			Response r = sparkAPI.get("/listings",parameters);
			assertTrue(r.isSuccess());
   		 	assertNotNull(r.getResults(Listing.class));
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}

	}
	
}
