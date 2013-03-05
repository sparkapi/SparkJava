package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;


public class JsonResponseHandlerTest {
	private static final String RESPONSE = "{\"D\":{\"Success\": true,\"Results\":[]}}";
	private static final String FAIL_RESPONSE = "{\"D\":{\"Success\": false,\"Message\":\"Things r broke\",\"Code\":1000}}";
	private static final String ERROR = "THIS IS GARBAGE";
	JsonResponseHandler handler = new JsonResponseHandler();

	@Before 
	public void setup(){
	}
	
	@Test
	public void testResponse() throws Exception {
		Response r = handler.handleResponse(mockResponse(200, RESPONSE));
		assertTrue(r.isSuccess());
	}

	@Test
	public void testBadResponse() throws Exception {
		JsonResponseHandler handler = new JsonResponseHandler();
		Response r = handler.handleResponse(mockResponse(500, FAIL_RESPONSE));
		assertFalse(r.isSuccess());
		assertEquals("Things r broke",r.getMessage());
		assertEquals(1000,r.getCode());
	}
	@Test(expected=SparkAPIClientException.class)
	public void testError() throws Exception {
		JsonResponseHandler handler = new JsonResponseHandler();
		Response r = handler.handleResponse(mockResponse(500, ERROR));
		r.checkFailures();
	}

	public HttpResponse mockResponse(int status, String json) throws Exception{
		InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
		return mockResponse(status, is);
	}
	public HttpResponse mockResponse(int status, File json) throws Exception{
		InputStream is = new FileInputStream(json);
		return mockResponse(status, is);
	}
	public HttpResponse mockResponse(int status, InputStream is) throws Exception{
		HttpResponse r = mock(HttpResponse.class);
		HttpEntity mockEntity = mockEntity(is);
		when(r.getEntity()).thenReturn(mockEntity);
		StatusLine mockStatusLine = mockStatusLine(status);
		when(r.getStatusLine()).thenReturn(mockStatusLine);
		return r;
	}
	
	public StatusLine mockStatusLine(int status) throws Exception {
		StatusLine e = mock(StatusLine.class);
		when(e.getStatusCode()).thenReturn(status);
		return e;
	}
	public HttpEntity mockEntity(InputStream is) throws Exception {
		HttpEntity e = mock(HttpEntity.class);
		when(e.getContent()).thenReturn(is);
		return e;
	}
}
