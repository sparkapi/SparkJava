package com.sparkplatform.api.models;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.services.ListingService;


public class VideoTest {

	MockClient c;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Video());
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_videos.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Video d = l.getStandardFields().getVideos().get(0);
		assertEquals("20110103201412297033000000", d.getId());
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_videos.json", 200);
		c.stubGet("/listings/20060725224713296297000000/videos", "listing_video_index.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Video d = s.getVideoService(l).find().get(0);
		assertEquals("20110103201412297033000000", d.getId());
	}

}
