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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Client configuration settings.  Values are pulled from an optional flexmls_api.properties file 
 * or the system environment when creating from the {@link Configuration#load()} factory.
 */
public class Configuration {
	// default configuration file
	private static final String PROPERTIES = "sparkapi.properties";

	/**
	 * Supported environment and property keys
	 */
	public static enum KEYS {
		API_KEY,
		API_SECRET,
		API_USER,
		CALLBACK_URL,
		ENDPOINT,
		SSL,
		VERSION,
		USER_AGENT;
	}
	
	private String apiSecret;
	private String apiKey;
	private String apiUser;
	private String callbackUrl = "https://sparkplatform.com/oauth2/callback";
	private String endpoint = "sparkapi.com";
	private String version = "v1";
	private boolean ssl;
	private String userAgent;
	
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiUser() {
		return apiUser;
	}
	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl( String callbackUrl ) {
		this.callbackUrl = callbackUrl;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isSsl() {
		return ssl;
	}
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	private static Logger logger = Logger.getLogger(Configuration.class);
	
	/**
	 * Load configuration from the system environment.
	 * @return Client configuration settings
	 */
	public static void loadFromEnvironment(Configuration c){
		setup(System.getenv(), c);
	}
	
	/**
	 * Load configuration from the flexmls_api.properties file.
	 * @return Client configuration settings
	 */
	public static void loadFromProperties(Configuration c){
		loadFromProperties(c, new File(PROPERTIES));
	}

	public static void loadFromProperties(Configuration c, File f){
		Properties properties = new Properties();
		try {
			if(f.exists() && f.isFile()){
				logger.debug("Loading configuration from properties file... " + f.getPath());
			    properties.load(new FileInputStream(f));
				logger.debug("Found properties: " + properties.toString());
				loadFromProperties(c,properties);
			}
		} catch (IOException e) {
			logger.warn("Unable to read properties file '" + f.getPath() + "', skipping.", e);
		}
	}
	
	/**
	 * Load configuration from properties.
	 * @return Client configuration settings
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadFromProperties(Configuration c, Properties properties){
		setup((Map)properties, c);
	}
	
	private static void setup(Map<String,String> map, Configuration c){
		if(map.containsKey(KEYS.API_KEY.toString())){
			c.setApiKey(map.get(KEYS.API_KEY.toString()));
		}
		if(map.containsKey(KEYS.API_USER.toString())){
			c.setApiUser(map.get(KEYS.API_USER.toString()));
		}
		if(map.containsKey(KEYS.API_SECRET.toString())){
			c.setApiSecret(map.get(KEYS.API_SECRET.toString()));
		}
		if(map.containsKey(KEYS.CALLBACK_URL.toString())){
			c.setCallbackUrl(map.get(KEYS.CALLBACK_URL.toString()));
		}
		if(map.containsKey(KEYS.ENDPOINT.toString())){
			c.setEndpoint(map.get(KEYS.ENDPOINT.toString()));
		}
		if(map.containsKey(KEYS.SSL.toString())){
			c.setSsl("true".equalsIgnoreCase(map.get(KEYS.SSL.toString())));
		}
		if(map.containsKey(KEYS.VERSION.toString())){
			c.setVersion(map.get(KEYS.VERSION.toString()));
		}
		if(map.containsKey(KEYS.USER_AGENT.toString())){
			c.setUserAgent(map.get(KEYS.USER_AGENT.toString()));
		}
		
	}
	
	/**
	 * Load configuration from the flexmls_api.properties file, and the system environment.
	 * @return Client configuration settings
	 */
	public static Configuration load(){
		Configuration c = new Configuration();
		loadFromProperties(c);
		loadFromEnvironment(c);
		return c;
	}
	
}
