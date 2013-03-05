package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;

public class ConnectionApacheHttpsTest {
	
	ConnectionApacheHttps c = new ConnectionApacheHttps(new Configuration());

	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(c);
	}
	
	@Test
	public void reset() throws IOException, SparkAPIClientException{
		c.setClient(null);
		c.setHost(null);
		c.reset();
		assertNotNull(c.getClient());
		assertNotNull(c.getHost());
		assertEquals(443, c.getHost().getPort());
	}

}
