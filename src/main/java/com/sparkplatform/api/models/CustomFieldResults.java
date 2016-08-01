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

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;


@JsonDeserialize(using=CustomFieldResults.MapperDeserializer.class)
public class CustomFieldResults extends Base {
	
	private static final long serialVersionUID = 19L;

	@JsonIgnore
	private Map<String, CustomField> customFieldsMap = new HashMap<String, CustomField>();
	
	/**
	 * The design of the base class didn't anticipate results that are a loose map of results.  In
	 * this deserializer I work around jackson to explicitly create the mapping and bind it to a 
	 * class instance.
	 */
	public static class MapperDeserializer extends JsonDeserializer<CustomFieldResults> {
		@SuppressWarnings("deprecation")
		@Override
		public CustomFieldResults deserialize(JsonParser jp,
				DeserializationContext ctxt) throws IOException,
				JsonProcessingException {
			JavaType jt = TypeFactory.mapType(Map.class, String.class, Result.class);
			DeserializerProvider deserializerProvider = ctxt.getDeserializerProvider();
			JsonDeserializer<Object> z = deserializerProvider.findTypedValueDeserializer(ctxt.getConfig(), jt);
			@SuppressWarnings("unchecked")
			Map<String, Result>o = (Map<String, Result>)z.deserialize(jp, ctxt);
			if(o == null) {
				throw new JsonMappingException("CustomField entity doesn't support the format presented", jp.getTokenLocation());
			}
			Map<String, CustomField> map = new LinkedHashMap<String, CustomField>();
			for (Map.Entry<String, Result> entry: o.entrySet()) {
				map.put(entry.getKey(), entry.getValue().getFields());
			}
			return new CustomFieldResults(map);
		}
	}

	/**
	 * it's just a work around for extra map.
	 */
	public static class Result extends Base {

		private static final long serialVersionUID = 20L;

		@JsonProperty("Fields")
		private CustomField fields;
		@JsonProperty("Label")
		private String label;
		public String getLabel() {
			return label;
		}
		public void setLabel( String label ) {
			this.label = label;
		}
		public CustomField getFields() {
			return fields;
		}
		public void setFields( CustomField fields ) {
			this.fields = fields;
		}
	}
	
	public CustomFieldResults(Map<String, CustomField> resultMap) {
		super();
		this.customFieldsMap = resultMap;
	}

	public CustomFieldResults() {
		super();
		// Default
	}

	public void setCustomField(String key, CustomField value){
		customFieldsMap.put(key, value);
	}
	
	public CustomField getCustomField(String key){
		return customFieldsMap.get(key);
	}

	@Override
	@JsonIgnore
	public void setAttribute(String key, Object value) {
		super.setAttribute(key, value);
	}
	
	@Override
	public String toString()
	{
		return customFieldsMap.toString();
	}
	
	public Map<String, CustomField> getCustomFieldMap()
	{
		return customFieldsMap;
	}
}
