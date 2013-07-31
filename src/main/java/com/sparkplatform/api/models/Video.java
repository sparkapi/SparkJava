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

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video extends ResourceEntity {
	
	private static final long serialVersionUID = 16L;
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Caption")
	private String caption;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("ObjectHtml")
	private String objectHtml;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObjectHtml() {
		return objectHtml;
	}
	public void setObjectHtml(String objectHtml) {
		this.objectHtml = objectHtml;
	}
}
