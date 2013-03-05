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
import java.util.List;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.models.PropertyType;
import com.sparkplatform.api.models.StandardField;

public class StandardFieldService extends BaseService<StandardField> {

	@Override
	public String getPath() {
		return "/standardfields";
	}

	public StandardFieldService(Client c) {
		super(c);
	}
	public List<StandardField> nearby(String latititude, String longitued, String expand, PropertyType first, PropertyType ... types) throws SparkAPIClientException{
		StringBuffer buffer = new StringBuffer(getPath()).append("/nearby/").append(first.getMlsCode());
		for (PropertyType propertyType : types) {
			buffer.append(",").append(propertyType.getMlsCode());
		}
		return getClient().get(buffer.toString(), new HashMap<ApiParameter, String>()).getResults(StandardField.class);
	}
	
}
