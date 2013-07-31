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

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparkplatform.api.SparkAPIClientException;

/**
 * JSON client parser for the HTTP response.
 */
public class JsonResponseHandler implements ResponseHandler<Response> {
	private ObjectMapper mapper = new ObjectMapper();
	
	public JsonResponseHandler() {
		super();
//		mapper.configure(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS, true);
	}

	@Override
	public Response handleResponse(HttpResponse response) {
		Response r;
		try {
			InputStream content = response.getEntity().getContent();
			int statusCode = response.getStatusLine().getStatusCode();
			r = parseResponse(content, statusCode);
		} catch (IOException e) {
			r = new Response(new SparkAPIClientException("Failure parsing JSON resonse.  The server response may be invalid", e));
		}
		return r;
	}

	/**
	 * Parse the response JSON into the standard API response object.
	 * @param content response content
	 * @param statusCode response HTTP status
	 * @return A response object
	 * @throws IOException
	 */
	public Response parseResponse(InputStream content, int statusCode) throws IOException { 
		JsonNode root;
		Response r = null;
		root = mapper.readValue(content, JsonNode.class);
		r = parse(root, statusCode);
		return r;
	}
	
	private Response parse(JsonNode root, int status){
		 // can reuse, share globally
		Response r = null;
		JsonNode rootNode = root.get("D");
		// SparkAPI response
		if(rootNode != null)
		{
			r = new Response(mapper, rootNode);
			r.setSuccess(rootNode.get("Success").booleanValue());
			r.setStatus(status);
			if(!r.isSuccess())
			{
				JsonNode codeNode = rootNode.get("Code");
				if(codeNode != null && codeNode.isInt())
					r.setCode(codeNode.intValue());
				JsonNode messageNode = rootNode.get("Message");
				if(messageNode != null)
					r.setMessage(messageNode.textValue());
			}
		}
		// OAuth response
		else
		{
			r = new Response(mapper,root);
			r.setSuccess(root.get("error") == null);
		}
			
		return r;		
	}
}
