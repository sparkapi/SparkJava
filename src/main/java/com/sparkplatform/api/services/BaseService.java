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

package com.sparkplatform.api.services;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.models.Base;

/**
 * Base service class that abstracts the HTTP rest requests using some handy methods.
 * @param <T> Resource entity that this service end point provides.
 */
public abstract class BaseService<T extends Base> {
	protected static final Map<ApiParameter, String> EMPTY = new HashMap<ApiParameter, String>();
	private Class<T> klass;
	private Client c = null;

	public BaseService(Client c) {
		super();
		this.c = c;
	}

	public Client getClient() {
		return c;
	}

	public T get(String id) throws SparkAPIClientException {
		return get(id, EMPTY);
	}

	public T get(String id, Map<ApiParameter, String> options)
			throws SparkAPIClientException {
		return c.get(getPath(id), options).getResults(model()).get(0);
	}

	public List<T> find() throws SparkAPIClientException {
		return find(EMPTY);
	}

	public List<T> find(Map<ApiParameter, String> options)
			throws SparkAPIClientException {
		return c.get(getPath(), options).getResults(model());
	}

	public abstract String getPath();

	protected String getPath(String resourceId) {
		return new StringBuffer(getPath()).append("/").append(resourceId)
				.toString();
	}

	/**
	 * This is a kludgy workaround to get the generic class sent in for the resource. 
	 * It may not work in all instances, in which case the actual \<Model\>.class value 
	 * should be explicitly returned.
	 * 
	 * It is not perfect, but it is WAY better than having to pass it in to all service 
	 * implementations.
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> model() {
		if(klass != null){
			return klass;
		}
		ParameterizedType superclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		klass = (Class<T>) superclass.getActualTypeArguments()[0];
		if(klass == null) {
			throw new IllegalArgumentException("Unable to determine the service model class, it will need to be explicitly passed.");
		}
		return klass;
	}

}
