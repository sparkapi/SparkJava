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

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyType extends Base {
	
	private static final long serialVersionUID = 10L;
	
	@JsonProperty("MlsName")
	private String mlsName;
	@JsonProperty("MlsCode")
	private String mlsCode;
	public String getMlsName() {
		return mlsName;
	}
	public void setMlsName(String mlsName) {
		this.mlsName = mlsName;
	}
	public String getMlsCode() {
		return mlsCode;
	}
	public void setMlsCode(String mlsCode) {
		this.mlsCode = mlsCode;
	}
	
}
