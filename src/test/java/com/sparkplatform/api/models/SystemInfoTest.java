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
import com.sparkplatform.api.services.SystemInfoService;


public class SystemInfoTest {

	private static final String JSON = "systeminfo.json";
	
	MockClient c;
	SystemInfoService s;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new SystemInfoService(c);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new SystemInfo());
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet(s.getPath(), JSON, 200);
		Response r = c.get(s.getPath(), new HashMap<ApiParameter, String>());
		assertNotNull(r);
		SystemInfo m = r.getResults(SystemInfo.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet(s.getPath(), JSON, 200);
		SystemInfo m = s.find().get(0);
		validate(m);
	}
	
	private void validate(SystemInfo m){
		assertEquals("20000426143505724628000000", m.getMlsId());
		assertEquals("20000426173054342350000000", m.getId());
		assertEquals("https://dev.fbsdata.com/r/oauth2", m.getConfiguration().getOauth2ServiceEndpointPrivate());
		assertEquals(0, m.getConfiguration().getAttributes().size());
		assertEquals(0, m.getAttributes().size());
	}
}
