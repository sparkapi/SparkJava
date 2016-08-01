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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.models.CustomField;
import com.sparkplatform.api.models.CustomFieldResults;

public class CustomFieldService extends BaseService<CustomField> {

	@Override
	public String getPath() {
		return "/customfields";
	}

	public CustomFieldService(Client c) {
		super(c);
	}

	@Override
	public List<CustomField> find( Map<ApiParameter, String> options ) throws SparkAPIClientException {
		List<CustomField> list = new ArrayList<CustomField>();
		List<CustomFieldResults> res = getClient().get(getPath(), options).getResults(CustomFieldResults.class);
		if (!res.isEmpty()) {
			CustomFieldResults cfr = res.get( 0 );
			for (CustomField field: cfr.getCustomFieldMap().values()) {
				list.add( field );
			}
		}
		return list;
	}
}
