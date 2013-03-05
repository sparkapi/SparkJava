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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Essentially read only, strictly validated JSON entity for the API session.
 */
public class Session {
	@JsonProperty("AuthToken")
	private String token;
	@JsonProperty("Roles")
	private List<String> roles = new ArrayList<String>();
	@JsonProperty("Expires")
	private Date expiration;
	
	public Session() { }
	
	public Session(String token, List<String> roles, Date expiration) {
		super();
		this.token = token;
		this.roles = Collections.unmodifiableList(roles);
		this.expiration = expiration;
	}
	public String getToken() {
		return token;
	}
	public List<String> getRoles() {
		return roles;
	}
	public Date getExpiration() {
		return expiration;
	}
	
	public boolean isExpired(){
		return expiration.before(new Date());
	}
}
