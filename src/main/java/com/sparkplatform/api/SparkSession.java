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

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sparkplatform.api.core.Session;

public class SparkSession extends Session {
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	private String openIDToken;

	@JsonIgnore
	private Date startTime;

	@JsonProperty("expires_in")
	private int expiresIn;
	@JsonIgnore
	private int refreshTimeout = 43200;
	@JsonProperty("error")
	private String error;
	@JsonProperty("error_description")
	private String errorDescription;
	
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
		return accessToken == null || refreshToken == null || expiresIn == 0
				|| startTime == null || startTime.getTime() + expiresIn - refreshTimeout < System.currentTimeMillis();
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
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getExpiresIn()
	{
		return expiresIn;
	}
	
	public void setExpiresIn(int expiresIn)
	{
		this.expiresIn = expiresIn;
	}

	public int getRefreshTimeout() {
		return refreshTimeout;
	}

	public void setRefreshTimeout(int refreshTimeout) {
		this.refreshTimeout = refreshTimeout;
	}	

	public boolean hasError()
	{
		return getError() != null;
	}
	
	public String getError()
	{
		return error;
	}
	
	public void setError(String error)
	{
		this.error = error;
	}
	
	public String getErrorDescription()
	{
		return errorDescription;
	}
	
	public void setErrorDescription(String errorDescription)
	{
		this.errorDescription = errorDescription;
	}
}
