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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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


@JsonDeserialize(using=StandardField.MapperDeserializer.class)
public class StandardField extends Base {
	
	private static final long serialVersionUID = 11L;
	
	@JsonProperty
	private Map<String, Field> fieldMap = new HashMap<String, StandardField.Field>();
	
	/**
	 * The design of the base class didn't anticipate results that are a loose map of results.  In
	 * this deserializer I work around jackson to explicitly create the mapping and bind it to a 
	 * class instance.
	 */
	public static class MapperDeserializer extends JsonDeserializer<StandardField> {
		@SuppressWarnings("deprecation")
		@Override
		public StandardField deserialize(JsonParser jp,
				DeserializationContext ctxt) throws IOException,
				JsonProcessingException {
			JavaType jt = TypeFactory.mapType(Map.class, String.class, Field.class);
			DeserializerProvider deserializerProvider = ctxt.getDeserializerProvider();
			JsonDeserializer<Object> z = deserializerProvider.findTypedValueDeserializer(ctxt.getConfig(), jt, null);
			@SuppressWarnings("unchecked")
			Map<String, Field>o = (Map<String, Field>)z.deserialize(jp, ctxt);
			if(o == null) {
				throw new JsonMappingException("StandardField entity doesn't support the format presented", jp.getTokenLocation());
			}
			return new StandardField(o);
		}
	}
	
	public enum Type {
		Integer(Integer.class),
		Decimal(Double.class),
		Boolean(Boolean.class),
		Character(String.class),
		Date(Date.class),
		Datetime(Date.class);
		
		private Class<?> klass;
		private Type(Class<?> c){
			this.klass = c;
		}
		public Class<?> getKlass() {
			return klass;
		}
	}
	
	public static class Field extends Base {
		
		private static final long serialVersionUID = 12L;
		
		@JsonProperty("Label")
		private String label;
		@JsonProperty("Searchable")
		private boolean searchable;
		@JsonProperty("Type")
		private Type type;
		@JsonProperty("ResourceUri")
		private String resourceUri;
		@JsonProperty("HasList")
		private boolean hasList;
		@JsonProperty("MlsVisible")
		private List<String> mlsVisible;
		

		public String getLabel() {
			return label;
		}
		
		public void setLabel(String label) {
			this.label = label;
		}
		
		public boolean isSearchable() {
			return searchable;
		}

		public void setSearchable(boolean searchable) {
			this.searchable = searchable;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getResourceUri() {
			return resourceUri;
		}

		public void setResourceUri(String resourceUri) {
			this.resourceUri = resourceUri;
		}

		public boolean isHasList() {
			return hasList;
		}

		public void setHasList(boolean hasList) {
			this.hasList = hasList;
		}
		
		public List<String>getMlsVisible() {
			return mlsVisible;
		}
		
		public boolean isMlsVisible(String propertyType) {
			for(String s : mlsVisible)
				if(s.equals(propertyType))
					return true;
			
			return false;
		}
		
		public void setMlsVisible(List<String> mlsVisible) {
			this.mlsVisible = mlsVisible;
		}

		@Override
		public String toString() {
			return "Field [label=" + label + ", searchable=" + searchable
					+ ", type=" + type + ", resourceUri=" + resourceUri
					+ ", hasList=" + hasList + ", mlsVisible=" + mlsVisible
					+ "]";
		}
	}
	
	public StandardField(Map<String, Field> fieldMap) {
		super();
		this.fieldMap = fieldMap;
	}

	public StandardField() {
		super();
		// Default
	}

	public void setField(String key, Field value){
		fieldMap.put(key, value);
	}
	
	public StandardField.Field getField(String key){
		return fieldMap.get(key);
	}

	@Override
	@JsonIgnore
	public void setAttribute(String key, Object value) {
		super.setAttribute(key, value);
	}
	
	@Override
	public String toString()
	{
		return fieldMap.toString();
	}
	
	public Map<String, Field> getFieldMap()
	{
		return fieldMap;
	}
}
