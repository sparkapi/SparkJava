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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sparkplatform.api.SparkAPIClientException;


public class MockClient extends Client {
	
	private MockConnection con;
	
	private MockClient(Configuration config,
			Connection<Response> defaultConnection,
			Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}

	public static void mock(){
		
	}

	public static MockClient mock(Configuration cf) throws SparkAPIClientException {
		MockConnection cn = new MockConnection();
		MockClient c = new MockClient(cf, cn, cn);
		c.con = cn;
		c.reauth();
		return c;
	}
	
	public List<String> getRoles(){
		List<String> l = new ArrayList<String>();
		l.add("idx");
		return l;
	}
	
	@Override
	protected Session authenticate() throws SparkAPIClientException {
		Session s = new Session("FAKE_TOKEN", getRoles(), new Date(new Date().getTime() + 300000));
		setSession(s);
		return s;
	}
	
	@Override
	protected String signToken(String path, Map<String, String> options,
			String body) {
		return "FAKE_SIG";
	}

	public void stubGet(String path, String fixture, int status) throws SparkAPIClientException {
		String s = setupRequest(path, "", new HashMap<String, String>());
		con.stubGet(s, fixture, status);
	}
	
	public void stubPost(String path, String body, String fixture, int status) throws SparkAPIClientException {
		
	}

	public void stubDelete(String path, String fixture, int status) throws SparkAPIClientException {
		
	}
	public void stubPut(String path, String body, String fixture, int status) throws SparkAPIClientException {
		
	}

}
