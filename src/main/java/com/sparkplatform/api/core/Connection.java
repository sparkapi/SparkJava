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

import com.sparkplatform.api.SparkAPIClientException;

/**
 * HTTP Abstraction layer for the API.  Implementations are responsible for performing the HTTP 
 * request, and parsing the resulting JSON response into some thing the client can make use of.
 * The implementation should also raise API exceptions when the API response is unsuccessful.
 *
 * @param <R> The response object
 */
public abstract class Connection<R> implements HttpActions<R, String> {

	private static final Map<String, String> EMPT_MAP = new HashMap<String, String>(); 
	
	/**
	 * Helper get with no parameters
	 * @param path request path
	 * @return Response object
	 * @throws SparkAPIClientException
	 */
	public R get(String path) throws SparkAPIClientException {
		return get(path, EMPT_MAP);
	}
	/**
	 * Helper get with no parameters
	 * @param path request path
	 * @param body post data
	 * @return Response object
	 * @throws SparkAPIClientException
	 */
	public R post(String path, String body) throws SparkAPIClientException {
		return post(path, body, EMPT_MAP);
	}
	/**
	 * Helper get with no parameters
	 * @param path request path
	 * @param body post data
	 * @return Response object
	 * @throws SparkAPIClientException
	 */
	public R put(String path, String body) throws SparkAPIClientException {
		return put(path, body, EMPT_MAP);
	}
	/**
	 * Helper delete with no parameters
	 * @param path request path
	 * @return Response object
	 * @throws SparkAPIClientException
	 */
	public R delete(String path) throws SparkAPIClientException {
		return delete(path, EMPT_MAP);
	}
}
