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
