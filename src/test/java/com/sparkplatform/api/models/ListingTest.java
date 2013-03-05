package com.sparkplatform.api.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.services.ListingService;


public class ListingTest {

	MockClient c;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Listing());
		PropertyAsserter.assertBasicGetterSetterBehavior(new Listing.StandardFields());
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet("/listings", "listing.json", 200);
		Response r = c.get("/listings", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		Listing m = r.getResults(Listing.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet("/listings", "listing.json", 200);
		ListingService s = new ListingService(c);
		Listing m = s.find().get(0);
		validate(m);
	}
	@Test
	public void testServiceGet() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing.json", 200);
		ListingService s = new ListingService(c);
		validate(s.get("20060725224713296297000000"));
	}
	@Test
	public void testMy() throws SparkAPIClientException {
		c.stubGet("/my/listings", "listing.json", 200);
		ListingService s = new ListingService(c);
		validate(s.my().get(0));
	}

	private void validate(Listing m){
		assertEquals("20060725224713296297000000", m.getId());
		assertEquals("Bonners Ferry", m.getStandardFields().getCity());
		assertEquals(m.getId(), m.getStandardFields().getListingKey());
		assertEquals(0, m.getAttributes().size());
		assertEquals(0, m.getStandardFields().getAttributes().size());
	}
}
