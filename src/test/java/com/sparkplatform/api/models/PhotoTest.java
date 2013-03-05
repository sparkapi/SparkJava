package com.sparkplatform.api.models;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.services.ListingService;


public class PhotoTest {

	MockClient c;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Photo());
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_photos.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Photo p = l.getStandardFields().getPhotos().get(0);
		assertEquals("20060725225010201442000000", p.getId());
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_photos.json", 200);
		c.stubGet("/listings/20060725224713296297000000/photos", "listing_photo_index.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Photo d = s.getPhotoService(l).find().get(0);
		assertEquals("20060725225010201442000000", d.getId());
	}

}
