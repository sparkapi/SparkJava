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

package com.sparkplatform.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
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
			assertNotNull(r);
			assertTrue(r.isSuccess());
			JsonNode account = r.getFirstResult();
			assertNotNull(account);
			assertEquals(account.get("Mls").textValue(),"Humboldt Association of REALTORS");
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
			assertNotNull(r);
			assertTrue(r.isSuccess());
   		 	assertNotNull(r.getResults(Listing.class));
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}

	}
	
	@Test
	public void testPutMyAccount() {
		try
		{
			String body = getBodyFixture("spark_putMyAccount.json");
			connection.stubPut(
					"/" + c.getVersion() + "/my/account",
					body,
					"success.json", 
					200);
			Response r = sparkAPI.put("/my/account",body,null);
			assertNotNull(r);
			assertTrue(r.isSuccess());
		}
		catch(Exception e)
		{
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testPostListingCart() {
		try
		{
			String body = getBodyFixture("spark_postListingCarts.json");
			connection.stubPost(
					"/" + c.getVersion() + "/listingcarts",
					body,
					"successPost.json", 
					200);
			Response r = sparkAPI.post("/listingcarts",body,null);
			assertNotNull(r);
			assertTrue(r.isSuccess());
			JsonNode result = r.getFirstResult();
			assertNotNull(result.get("ResourceUri"));			
		}
		catch(Exception e)
		{
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testDeleteListingCart() {
		try {
			connection.stubDelete(
					"/" + c.getVersion() + "/listingcarts/1",
					"success.json", 
					200);
			Response r = sparkAPI.delete("/listingcarts/1",null);
			assertNotNull(r);
			assertTrue(r.isSuccess());
		} catch (SparkAPIClientException e) {
			fail("SparkAPIClientException thrown");
		}
	}
	
	private String getBodyFixture(String bodyFile) throws IOException
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("src/test/fixtures/" + bodyFile));
			String line = null;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line);
				builder.append("\r\n");
			}
			return builder.toString();
		}
		finally
		{
			br.close();			
		}
	}
}
