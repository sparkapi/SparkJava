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

package com.sparkplatform.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SparkSessionTest {

	@Test
	public void testNotExpired() {
		SparkSession session = getHybridSession();
		assertFalse(session.isExpired());
	}

	@Test
	public void testExpired() {
		SparkSession session = new SparkSession();
		assertTrue(session.isExpired());
	}
	
	@Test
	public void testIsHybridSession() {
		SparkSession session = getHybridSession();
		assertTrue(session.isHybridSession());
	}
	
	@Test
	public void testIsOpenIDSession() {
		SparkSession session1 = getHybridSession();
		assertFalse(session1.isOpenIDSession());
		
		SparkSession session2 = new SparkSession();
		session2.setOpenIdToken("openIdToken");
		assertTrue(session2.isOpenIDSession());
	}
	
	private SparkSession getHybridSession() {
		SparkSession session = new SparkSession();
		session.setAccessToken("accessToken");
		session.setRefreshToken("refreshToken");
		return session;
	}
	
}
