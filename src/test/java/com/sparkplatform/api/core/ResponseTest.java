package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.SparkAPIException;

public class ResponseTest {
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Response(new SparkAPIClientException("")));
	}

	@Test
	public void testCheckFailures() throws SparkAPIClientException {
		// Sky is falling response 
		SparkAPIClientException exception = new SparkAPIClientException("This is an error!");
		Response r = new Response(exception);
		try {
			r.checkFailures();
			fail("Failures were expected");
		} catch (SparkAPIClientException e) {
			assertEquals(exception, e);
		}
		// API ERROR Response
		Response y = new Response(null, null);
		y.setCode(1000);
		y.setMessage("API ERROR");
		y.setStatus(500);
		try {
			y.checkFailures();
			fail("Failures were expected");
		} catch (SparkAPIException e) {
			assertEquals(ApiCode.INVALID_KEY, e.getCode());
			assertEquals(500, e.getStatus());
			assertEquals("API ERROR", e.getMessage());
		}
		// Success
		Response z = new Response(null, null);
		z.setSuccess(true);
		z.checkFailures();
	}

}
