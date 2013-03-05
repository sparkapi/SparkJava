package com.sparkplatform.api.services;

import java.util.HashMap;

import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.Response;
import com.sparkplatform.api.services.BaseService;
import com.sparkplatform.api.models.ExampleModel;

public class ExampleService extends BaseService<ExampleModel> {
	static final String PATH = "/test";
	
	public ExampleService(Client c) {
		super(c);
	}

	public ExampleModel get(String id) throws SparkAPIClientException{
		Client c = getClient();
		Response r = c.get(PATH + "/" + id, new HashMap<ApiParameter, String>());
		return r.getResults(ExampleModel.class).get(0);
	}

	@Override
	public String getPath() {
		return PATH;
	}

}
