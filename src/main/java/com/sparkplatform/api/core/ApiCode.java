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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All the documented API response codes.  
 * 
 * @see http://www.flexmls.com/developers/idx-api/supporting-documentation/error-codes/
 */
public enum ApiCode {
	NOT_FOUND(404, "Not found"), 
	NOT_ALLOWED(405, "Method not allowed"), 

	INVALID_KEY(1000, "Invalid API key and/or request signed improperly"), 
	DISABLED_KEY(1010, "API key is disabled"), 
	AUTH_NOT_PERMITTED(1013, "The auth mechanism used for this request is not permitted for the provided key"), 
	API_USER_REQUIRED(1015, "ApiUser must be supplied, or the provided key does not have access to the supplied user"), 
	X_SPARK_API_USER_AGENT_REQUIRED(1016, "The X-SparkApi-User-Agent request header is required. No request to the API will be successful without it. Please see the overview for additional information."), 
	READ_ONLY_ACCESS(1017, "You are restricted to read-only access. POST, PUT, and DELETE requests are forbidden."), 
	FORBIDDEN(1018, "You are forbidden from accessing this resource with the method attempted"), 
	SESSION_EXPIRED(1020, "Session token has expired"), 
	USER_ACCOUNT_NOT_AVAILABLE(1023, "The user's account is not yet available in Spark."), 
	PORTAL_GROUP_INVALID(1025, "The user has a Portal Group selected that is no longer valid. Set a new portal group before accessing this service."), 
	SSL_REQUIRED(1030, "SSL required for this type of request"), 
	INVALID_JSON(1035, "POST data not supplied as valid JSON. Issued if the \"Content-Type\" header is not \"application/json\" and/or if the POST data is not in valid JSON format"), 
	INVALID_FILTER(1040, "The _filter syntax was invalid or a specified field to search on does not exist. Response will include the SparkQLErrors attribute."), 
	FILTER_EXCEEDS_RESTRICTIONS(1041, "(Message varies) The _filter syntax is valid but exceeds restrictions on filter or argument length Response will include the SparkQLErrors attribute."), 
	MISSING_PARAMETER(1050, "(Message varies) A required parameter for the request was not provided"), 
	INVALID_PARAMETER(1053, "(Message varies) A parameter was provided but does not adhere to constraints"), 
	INVALID_WRITE(1055, "(Message varies) Issued when a write is requested that will conflict with existing data. For instance, adding a new contact with an e-mail that already exists"), 
	DATA_VALIDATION_FAILED(1060, "(Message varies) A Validation field did not match existing data"), 
	PREVIOUS_DATA_PROHIBITED(1061, "(Message varies) Attempt to reuse previous data when prohibited"), 
	
	PORTAL_REQUIRED(1100, "This resource cannot be accessed until a Portal account is created for the specified contact"), 
	PORTAL_ALREADY_EXISTS(1110, "A portal already exists for this contact"), 
	RESOURCE_UNAVAILABLE(1500, "The resource is not available at the current API key's service level. For example, this error applies if a user attempts to access the IDX Links API via a non-IDX API key."), 
	RESOURCE_DISABLED(1505, "(Message varies) This resource, or a component of this resource, is disabled for the current user's MLS. This is usually temporary, such as during a gradual rollout of a feature."), 
	TEMPORARY_UNAVAILABLE(1510, "The API is temporarily unavailable."), 
	LONG_REQUEST_CANCELLED(1515, "Your request took too long to process and has therefore been cancelled."), 
	LONG_REQUEST_FILTER_OR_ORDERBY_CANCELLED(1516, "Your request took too long to process and was cancelled due to the _filter or _orderby parameter you provided."), 
	RATE_LIMIT_EXCEEDED(1550, "Over rate limit"),

	RESERVED(2000, "Codes 2000-2999 are reserved for value-specific errors in the _filter parameter. These are not syntax errors, but instead errors specific to the valid field present in the parameter. Responses will include the SparkQLErrors attribute."),
	SAVED_SEARCH_UNAVAILBLE(2001, "The SavedSearch specified does not exist or is not available to the current user."),
	EMAIL_LINK_UNAVAILBLE(2002, "The EmailLink specified does not exist or is not available to the current user."),
	SHARED_LISTINGS_ID_UNAVAILABLE(2003, "The SharedListings id specified does not exist or does not contain listings."),
	SEARCH_CRITERIA_RESTRICTIONS(2500, "The search you are attempting to subscribe to does not meet the search criteria restrictions."),
	USER_ALREADY_SUBSCRIBED_TO_SEARCH(2505, "The current user is already subscribed to this saved search."),
	RESO_VERSION_NOT_SUPPORTED(3000, "The RESO data dictionary version you requested is not supported."),
	
	// For missing codes that the library doesn't know about yet.
	UNKNOWN_API_CODE(0, "");

	private static final Map<Integer, ApiCode> MAP = new HashMap<Integer, ApiCode>();

	static {
		for (ApiCode s : EnumSet.allOf(ApiCode.class)) {
			MAP.put(s.getCode(), s);
		}
	}

	private int code;
	private String message;

	private ApiCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * ApiCode int value
	 * @return the int value
	 */
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * Reverse lookup for ApiCode
	 * @param code int code value
	 * @return the Matching enum, or UNKNOWN_API_CODE
	 */
	public static ApiCode get(int code) {
		return MAP.containsKey(code) ? MAP.get(code) : UNKNOWN_API_CODE;
	}
}
