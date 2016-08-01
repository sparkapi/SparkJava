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
import com.sparkplatform.api.models.CustomField.Field;
import com.sparkplatform.api.services.CustomFieldService;


public class CustomFieldTest {

	private static final String JSON = "customfields.json";
	private static final String JSON_TAXES = "customfields_taxes.json";
	
	MockClient c;
	CustomFieldService s;
	
	@Before
	public void setup() throws SparkAPIClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new CustomFieldService(c);
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
		CustomFieldResults map = r.getResults(CustomFieldResults.class).get(0);
		CustomField m = map.getCustomFieldMap().values().iterator().next();
		validate(m);
	}

	@Test
	public void testGetByName() throws SparkAPIClientException {
		c.stubGet(s.getPath()+"/Taxes", JSON_TAXES, 200);
		Response r = c.get(s.getPath()+"/Taxes", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		CustomField m = r.getResults(CustomField.class).get(0);
		validateTaxes(m);
	}

	@Test
	public void testService() throws SparkAPIClientException {
		c.stubGet(s.getPath(), JSON, 200);
		CustomField m = s.find().get(0);
		validate(m);
	}
	
	private void validate(CustomField m){
		Field floodPlan = m.getField("Flood Plain");
		assertEquals("/v1/customfields/Flood Plain", floodPlan.getResourceUri());
		assertEquals(CustomField.Type.Character, floodPlan.getType());
		assertTrue("Flood Plain is searchable", floodPlan.isSearchable());
		assertTrue("Flood Plain has a list", floodPlan.isHasList());
		Field taxes = m.getField("Taxes");
		assertEquals("/v1/customfields/Taxes", taxes.getResourceUri());
		assertEquals(CustomField.Type.Decimal, taxes.getType());
		assertTrue("ListPrice is searchable", taxes.isSearchable());
		assertFalse("ListPrice has no list", taxes.isHasList());
	}

	private void validateTaxes(CustomField m){
		Field taxes = m.getField("Taxes");
		assertEquals("/v1/customfields/Taxes", taxes.getResourceUri());
		assertEquals(CustomField.Type.Decimal, taxes.getType());
		assertTrue("ListPrice is searchable", taxes.isSearchable());
		assertFalse("ListPrice has no list", taxes.isHasList());
	}
}
