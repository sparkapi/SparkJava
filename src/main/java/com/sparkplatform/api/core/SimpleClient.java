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

import java.util.HashMap;
import java.util.Map;

/**
 * Unrestricted client for doing more experimental queries.  This client is unsupported by the 
 * service classes provided by the library, but can be used for hitting the services directly in a 
 * more scripted manner.
 */
public class SimpleClient extends BaseClient<String> {
	public SimpleClient(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}
	public SimpleClient(Configuration config) {
		super(config);
	}	
	@Override
	Map<String, String> stringifyParameterKeys(Map<String, String> parms) {
		return new HashMap<String, String>(parms);
	}
	
}
