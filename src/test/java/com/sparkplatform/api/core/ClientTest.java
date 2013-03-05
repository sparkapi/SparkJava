package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.models.ExampleModel;

public class ClientTest {
	SimpleClient c = null;
	Configuration config = new Configuration();
	Map<String, String> sample = new HashMap<String, String>();
	MockConnection conn;

	@Before
	public void setup(){
		config.setApiKey("MyKey");
		config.setApiSecret("password");
		config.setEndpoint("http://testapi.flexlms.com");
		conn = new MockConnection();
		c = new SimpleClient(config, conn, conn);
		sample = new HashMap<String, String>();
		sample.put("Optimus",   "semi");
		sample.put("Soundwave", "walkman");
		sample.put("Starscream", "F-15 Eagle");
		sample.put("Megatron",  "Walther P-38");
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}

	@Test
	public void testConstruction(){
		// for petty coverage sake.
		new Client(config);
		new SimpleClient(config);
	}

	@Test
	public void testGet() throws SparkAPIClientException {
		mockAuth();
		conn.stubGet(
			"/v1/test?ApiSig=1fcc1c5f58cc504fbffd79784cb1fad8&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15+Eagle&Megatron=Walther+P-38&Optimus=semi",
			"test.json", 
			200);
		Response r = c.get("/test", sample);
		assertTrue(r.isSuccess());
		List<ExampleModel> models = r.getResults(ExampleModel.class);
		assertEquals(3, models.size());
		ExampleModel myFoo = models.get(0);
		assertEquals("MyFoo", myFoo.getFoo());
		assertEquals(1, myFoo.getBar());
	}

	@Test
	public void testPost() throws SparkAPIClientException {
		mockAuth();
		conn.stubPost(
			"/v1/test?ApiSig=820f36fdd9a59154007b39f3dc3bc912&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15+Eagle&Megatron=Walther+P-38&Optimus=semi",
			"foo=Test&bar=10",
			"success.json",
			201);
		Response r = c.post("/test", "foo=Test&bar=10", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testPut() throws SparkAPIClientException {
		mockAuth();
		conn.stubPut(
				"/v1/test/1234?ApiSig=2649baff1e76ae996fa1b76a6cbff86b&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15+Eagle&Megatron=Walther+P-38&Optimus=semi",
				"foo=Test&bar=10",
				"success.json",
				201);
		Response r = c.put("/test/1234", "foo=Test&bar=10", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testDelete() throws SparkAPIClientException {
		mockAuth();
		conn.stubDelete(
				"/v1/test/1234?ApiSig=bd358131c3f975ef9f137a3b17cdfa35&AuthToken=c729d695fc1613af58de764fa44881cb&Soundwave=walkman&Starscream=F-15+Eagle&Megatron=Walther+P-38&Optimus=semi",
				"success.json", 
				200);
		Response r = c.delete("/test/1234", sample);
		assertTrue(r.isSuccess());
	}

	@Test
	public void testAuthenticate() throws SparkAPIClientException {
		mockAuth();
		Session s = c.authenticate();
		assertNotNull("Session", s);
		assertEquals("c729d695fc1613af58de764fa44881cb", s.getToken());
		assertEquals("private_full", s.getRoles().get(0));
	}

	@Test
	public void testSign() {
		assertEquals("b6799a05f2da802bbac47e790cc42183", c.sign("SignMePlz"));
	}

	@Test
	public void testSignToken() {
		assertEquals("f7c26177fbc4eaf63560eecebdbb34a4", c.signToken("/path", sample, ""));
	}

	@Test
	public void testBuildParamString() {
		assertEquals("MegatronWalther+P-38OptimussemiSoundwavewalkmanStarscreamF-15+Eagle", c.buildParamString(sample));
	}
	
	private void mockAuth() throws SparkAPIClientException{
		conn.stubPost("/v1/session?ApiKey=MyKey&ApiSig=9526c9b45b579ffb67facafa52351ec9","", "session.json", 200);
	}

	@Test
	public void testStringifyApiParams() throws SparkAPIClientException {
		mockAuth();
		conn.stubGet(
			"/v1/test?ApiSig=e8a5b0cb217d8d5a6437944250ee904f&AuthToken=c729d695fc1613af58de764fa44881cb&_pagination=1",
			"test.json", 
			200);
		Client c = new Client(config, conn, conn);
		Map<ApiParameter,String> args = new HashMap<ApiParameter, String>();
		args.put(ApiParameter._pagination, "1");
		Response r = c.get("/test", args);
		assertTrue(r.isSuccess());
		List<ExampleModel> models = r.getResults(ExampleModel.class);
		assertEquals(3, models.size());
		ExampleModel myFoo = models.get(0);
		assertEquals("MyFoo", myFoo.getFoo());
		assertEquals(1, myFoo.getBar());
	}

}