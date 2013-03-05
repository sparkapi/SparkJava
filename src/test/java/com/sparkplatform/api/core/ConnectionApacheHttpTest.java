package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;

public class ConnectionApacheHttpTest {
	ConnectionApacheHttp c;
	
	@Before
	public void setup(){
		c = new ConnectionApacheHttp(new Configuration());
	}	
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
	
	@Test
	public void get() throws IOException, SparkAPIClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpGet.class);
		Response rs2 = c.get("TESTGET");
		assertEquals(rs2, rs);
	}

	@Test
	public void delete() throws IOException, SparkAPIClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpDelete.class);
		Response rs2 = c.delete("TESTDELETE");
		assertEquals(rs2, rs);
	}

	@Test
	public void post() throws IOException, SparkAPIClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpPost.class);
		Response rs2 = c.post("TESTPOST", "data");
		assertEquals(rs2, rs);
	}

	@Test
	public void put() throws IOException, SparkAPIClientException{
		Response rs = new Response(null);
		rs.setSuccess(true);
		mockupHttpClient(rs, HttpPut.class);
		Response rs2 = c.put("TESTPUT", "data");
		assertEquals(rs2, rs);
	}

	@Test
	public void failure() throws IOException, SparkAPIClientException{
		SparkAPIClientException ex = new SparkAPIClientException("Something blew up");
		Response rs = new Response(ex);
		rs.setSuccess(false);
		mockupHttpClient(rs, HttpPut.class);
		try {
			c.get("TEST");
			fail("exception expected");
		} catch (SparkAPIClientException e) {
			assertEquals(ex, e);
		}
	}
	
	@Test
	public void reset() throws IOException, SparkAPIClientException{
		c.setClient(null);
		c.reset();
		assertNotNull(c.getClient());
	}

	private <T extends HttpRequest> void mockupHttpClient(Response rs, Class<T> klass) throws IOException {
		HttpClient hc = mock(HttpClient.class);
		HttpHost h = new HttpHost("MY.TEST.SERVER.FBSDATA.COM");
		c.setClient(hc);
		c.setHost(h);
		when(hc.execute(eq(h), any(klass), any(JsonResponseHandler.class))).thenReturn(rs);
	}

}
