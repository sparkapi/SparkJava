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

import java.util.List;
import java.util.Map;

import com.sparkplatform.api.SparkAPIClientException;
import com.sparkplatform.api.core.ApiParameter;
import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.models.Listing;

public class ListingService extends BaseService<Listing> {

	@Override
	public String getPath() {
		return "/listings";
	}

	public ListingService(Client c) {
		super(c);
	}
	
	public List<Listing> my() throws SparkAPIClientException {
		return my(EMPTY);
	}
	public List<Listing> my(Map<ApiParameter, String> opts) throws SparkAPIClientException {
		return getClient().get("/my" + getPath(), opts).getResults(model());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	// SubResources
	//
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public DocumentService getDocumentService(Listing l){
		return new DocumentService(getClient(), getPath() + "/" + l.getId());
	}

	public PhotoService getPhotoService(Listing l){
		return new PhotoService(getClient(), getPath() + "/" + l.getId());
	}

	public VideoService getVideoService(Listing l){
		return new VideoService(getClient(), getPath() + "/" + l.getId());
	}
	public VirtualTourService getVirtualTourService(Listing l){
		return new VirtualTourService(getClient(), getPath() + "/" + l.getId());
	}

	public OpenHouseService getOpenHouseService(Listing l){
		return new OpenHouseService(getClient(), getPath() + "/" + l.getId());
	}

}
