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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * Top level model for managing JSON entities in the library.
 */
public class Base implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(Base.class);
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	/**
	 * This method gets called for all fields that are not mapped to a specific java class field in
	 * inheriting models.  It's a "catch-all" for future fields, or anything that the client 
	 * missed. 
	 * @param key
	 * @param value
	 */
	@JsonAnySetter
	public void setAttribute(String key, Object value){
		if(logger.isDebugEnabled()){
			logger.debug("Added attribute: "  + key);
		}
		attributes.put(key, value);
	}

	@JsonAnyGetter
	public Object getAttribute(String key){
		return attributes.get(key);
	}

	public Map<String, Object> getAttributes(){
		return attributes;
	}
	
}
