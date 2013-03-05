//
//  Copyright (c) 2013 Financial Business Systems, Inc. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

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
	
	@Test
	public void testSparkProperties() {
		Configuration c = new Configuration();
		Configuration.loadFromProperties(c, new File("src/test/resources/test_sparkapi.properties"));
		assertEquals("sparkapi.com", c.getEndpoint());
		assertEquals("<YOUR OAUTH2 CLIENT KEY>", c.getApiKey());
		assertEquals("<YOUR OAUTH2 CLIENT SECRET>",c.getApiSecret());
		assertEquals("SparkJava 1.0",c.getUserAgent());
		assertTrue(c.isSsl());
		
	}
}