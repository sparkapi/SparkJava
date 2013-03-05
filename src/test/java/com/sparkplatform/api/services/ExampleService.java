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

package com.sparkplatform.api.services;

import java.util.HashMap;

import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.services.BaseService;
import com.sparkplatform.api.models.ExampleModel;

public class ExampleService extends BaseService<ExampleModel> {
	static final String PATH = "/test";
	
	public ExampleService(Client c) {
		super(c);
	}

	public ExampleModel get(String id) throws SparkAPIClientException{
		Client c = getClient();
		Response r = c.get(PATH + "/" + id, new HashMap<ApiParameter, String>());
		return r.getResults(ExampleModel.class).get(0);
	}

	@Override
	public String getPath() {
		return PATH;
	}

}
