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

public class Photo extends ResourceEntity {
	
	private static final long serialVersionUID = 9L;
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Caption")
	private String caption;	
	@JsonProperty("UriThumb")
	private String uriThumb;
	@JsonProperty("Uri300")
	private String uri300;
	@JsonProperty("Uri640")
	private String uri640;
	@JsonProperty("Uri800")
	private String uri800;
	@JsonProperty("Uri1024")
	private String uri1024;
	@JsonProperty("Uri1280")
	private String uri1280;
	@JsonProperty("UriLarge")
	private String uriLarge;
	@JsonProperty("Primary")
	private boolean primary;
	
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
	public String getUriThumb() {
		return uriThumb;
	}
	public void setUriThumb(String uriThumb) {
		this.uriThumb = uriThumb;
	}
	public String getUri300() {
		return uri300;
	}
	public void setUri300(String uri300) {
		this.uri300 = uri300;
	}
	public String getUri640() {
		return uri640;
	}
	public void setUri640(String uri640) {
		this.uri640 = uri640;
	}
	public String getUri800() {
		return uri800;
	}
	public void setUri800(String uri800) {
		this.uri800 = uri800;
	}
	public String getUri1024() {
		return uri1024;
	}
	public void setUri1024(String uri1024) {
		this.uri1024 = uri1024;
	}
	public String getUri1280() {
		return uri1280;
	}
	public void setUri1280(String uri1280) {
		this.uri1280 = uri1280;
	}
	public String getUriLarge() {
		return uriLarge;
	}
	public void setUriLarge(String uriLarge) {
		this.uriLarge = uriLarge;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
}
