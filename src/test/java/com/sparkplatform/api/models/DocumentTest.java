package com.sparkplatform.api.models;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.services.ListingService;


public class DocumentTest {

	MockClient c;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Document());
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_documents.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Document d = l.getStandardFields().getDocuments().get(0);
		assertEquals("20060725224801143085000000", d.getId());
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet("/listings/20060725224713296297000000", "listing_with_documents.json", 200);
		c.stubGet("/listings/20060725224713296297000000/documents", "listing_document_index.json", 200);
		ListingService s = new ListingService(c);
		Listing l = s.get("20060725224713296297000000");
		Document d = s.getDocumentService(l).find().get(0);
		assertEquals("20060725224801143085000000", d.getId());
	}

}
