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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Configuration;
import com.sparkplatform.api.core.MockClient;
import com.sparkplatform.api.core.PropertyAsserter;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.models.StandardField.Field;
import com.sparkplatform.api.services.StandardFieldService;


public class StandardFieldTest {

	private static final String JSON = "standardfields.json";
	
	MockClient c;
	StandardFieldService s;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new StandardFieldService(c);
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
		StandardField m = r.getResults(StandardField.class).get(0);
		validate(m);
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet(s.getPath(), JSON, 200);
		StandardField m = s.find().get(0);
		validate(m);
	}
	
	private void validate(StandardField m){
		Field city = m.getField("City");
		assertEquals("/v1/standardfields/City", city.getResourceUri());
		assertEquals(StandardField.Type.Character, city.getType());
		assertTrue("City is searchable", city.isSearchable());
		assertTrue("City has a list", city.isHasList());
		Field listPrice = m.getField("ListPrice");
		assertEquals("/v1/standardfields/ListPrice", listPrice.getResourceUri());
		assertEquals(StandardField.Type.Decimal, listPrice.getType());
		assertTrue("ListPrice is searchable", listPrice.isSearchable());
		assertFalse("ListPrice has no list", listPrice.isHasList());
	}
}
