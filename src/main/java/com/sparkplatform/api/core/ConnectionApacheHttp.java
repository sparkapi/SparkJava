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

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.sparkplatform.api.SparkAPIClientException;


/**
 * Connection wrapper for the Apache HTTPClient library.
 */
public class ConnectionApacheHttp extends Connection<Response> {
	
	private static Logger logger = Logger.getLogger(ConnectionApacheHttp.class);
	
	private HttpClient client;
	private Configuration config;
	private HttpHost host;
	private ResponseHandler<Response> handler;
	
	private Map<String,String> headers;
	
	public ConnectionApacheHttp(Configuration config) {
		this.config = config;
		init();
	}
	
	private void setHeaders(HttpRequest r)
	{
		if(headers != null)
		{
			for(String key : headers.keySet())
				r.addHeader(key, headers.get(key));
		}
	}
	
	@Override
	public Response get(String path, Map<String, String> options) throws SparkAPIClientException {
		HttpRequest r = new HttpGet(path);
		setHeaders(r);
		return request(r);
	}
	@Override
	public Response delete(String path, Map<String, String> options)
			throws SparkAPIClientException {
		HttpRequest r = new HttpDelete(path);
		setHeaders(r);
		return request(r);
	}

	@Override
	public Response post(String path, String body, Map<String, String> options)
			throws SparkAPIClientException {
		HttpPost r = new HttpPost(path);
		setHeaders(r);
		setData(r, body);
		return request(r);
	}
	
	@Override
	public Response put(String path, String body, Map<String, String> options)
			throws SparkAPIClientException {
		HttpPut r = new HttpPut(path);
		setHeaders(r);
		setData(r, body);
		return request(r);
	}
	
	private static void setData(HttpEntityEnclosingRequestBase r, String body) throws SparkAPIClientException{
		HttpEntity data;
		try {
			data = new StringEntity(body);
			((StringEntity)data).setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			r.setEntity(data);
		} catch (UnsupportedEncodingException e) {
			throw new SparkAPIClientException("Message cannot be sent as the body is encoded in an unsupported format.", e);
		} 
	}

	protected Response request(HttpRequest r) throws SparkAPIClientException {
		Response rs = null;
		try {
			rs = client.execute(host, r, handler);
		} catch (Exception e) {
			throw new SparkAPIClientException("Connection failure with HTTP.  Check your url settings.", e);
		}
		rs.checkFailures();
		return rs;
	}
	
	/**
	 * Reset the connection configuration
	 */
	public final void reset() {
		init();
		resetChild();
	}
	
	private void init() {
		client = new DefaultHttpClient();
		host = new HttpHost(config.getEndpoint());
		handler = new JsonResponseHandler();
	}
	
	/**
	 * Allow inheriting classes to reset themselves
	 */
	protected void resetChild() { }
	
	protected HttpClient getClient() {
		return client;
	}

	protected void setClient(HttpClient client) {
		this.client = client;
	}

	protected Configuration getConfig() {
		return config;
	}

	protected void setConfig(Configuration config) {
		this.config = config;
	}

	protected HttpHost getHost() {
		return host;
	}

	protected void setHost(HttpHost host) {
		this.host = host;
	}
	
	public Map<String,String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(Map<String,String> headers) {
		this.headers = headers;
	}
	
}
