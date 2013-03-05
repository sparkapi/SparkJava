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

package com.sparkplatform.api.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.services.ExampleService;

public class ModelTest {
	MockClient c;
	
	@Test
	public void testProperties(){
		ExampleModel m = new ExampleModel();
		PropertyAsserter.assertBasicGetterSetterBehavior(m);
		m.setAttribute("FOO", "BAR");
		assertEquals("BAR", m.getAttribute("FOO"));
	}
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
	}
	
	@Test
	public void testGet() throws SparkAPIClientException {
		c.stubGet("/test", "test.json", 200);
		Response r = c.get("/test", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		List<ExampleModel> results = r.getResults(ExampleModel.class);
		ExampleModel m = results.get(0);
		assertEquals(1, m.getBar());
		ExampleModel m2 = results.get(1);
		assertEquals(2, m2.getBar());
		ExampleModel m3 = results.get(2);
		assertEquals(3, m3.getBar());
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet("/test", "test.json", 200);
		ExampleService s = new ExampleService(c);
		ExampleModel m = s.find().get(0);
		assertEquals(1, m.getBar());
	}
}
