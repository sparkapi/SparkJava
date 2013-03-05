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

public class SubResourceEntity extends ResourceEntity {
	
	private static final long serialVersionUID = 13L;

	private String parentEntity;
	
	public SubResourceEntity(String parentEntity) {
		super();
		this.parentEntity = parentEntity;
	}

	public String getParentEntity() {
		return parentEntity;
	}

	protected void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}
}
