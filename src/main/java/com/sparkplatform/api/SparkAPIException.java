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

import com.sparkplatform.api.core.ApiCode;

/**
 * Errors Specifically returned from the API
 */
public class SparkAPIException extends SparkAPIClientException {
	private static final long serialVersionUID = -8156427208964545915L;
	private String message;
	private ApiCode code;
	private int status;
	public SparkAPIException(String message, ApiCode code, int status) {
		super(message);
		this.message = message;
		this.code = code;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public ApiCode getCode() {
		return code;
	}
	public int getStatus() {
		return status;
	}
}
