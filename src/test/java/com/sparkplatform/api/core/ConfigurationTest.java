package com.sparkplatform.api.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;


public class ConfigurationTest {
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new Configuration());
	}
	
	@Test
	public void testLoad(){
		Configuration c = Configuration.load();
		assertEquals("v1", c.getVersion());
		assertNull(c.getApiSecret());
		assertNull(c.getApiUser());
	}
	
	@Test
	public void testLoadFromProperties(){
		Configuration c = new Configuration();
		Configuration.loadFromProperties(c, new File("src/test/resources/test_flexmls_api.properties"));
		assertEquals("v1", c.getVersion());
		assertEquals("PASSWORDZ",c.getApiSecret());
		assertEquals("10000000000000000000000000",c.getApiUser());
		assertEquals("sample_key", c.getApiKey());
		assertEquals("test.flexmls.com", c.getEndpoint());
		assertTrue(c.isSsl());

		Configuration badconfig = new Configuration();
		Configuration.loadFromProperties(badconfig, new File("not_a_file"));
		assertFalse(badconfig.isSsl());
		assertNull(badconfig.getApiSecret());
	}
}