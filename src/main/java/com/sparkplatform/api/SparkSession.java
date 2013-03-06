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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sparkplatform.api.core.Session;


@JsonIgnoreProperties({"expires_in"})

public class SparkSession extends Session {
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	private String openIDToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getOpenIdToken() {
		return openIDToken;
	}
	
	public void setOpenIdToken(String openIDToken) {
		this.openIDToken = openIDToken;
	}
	
	public boolean isExpired(){
		return accessToken == null || refreshToken == null;
	}
	
	public boolean isHybridSession() {
		return accessToken != null && refreshToken != null;
	}
	
	public boolean isOpenIDSession() {
		return openIDToken != null;
	}
	
	Session authenticate() throws SparkAPIClientException {
		throw new SparkAPIClientException("Spark authentication required");
	}
	
}
