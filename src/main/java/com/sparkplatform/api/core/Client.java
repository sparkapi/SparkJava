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

import java.util.HashMap;
import java.util.Map;

import com.sparkplatform.api.services.ContactService;
import com.sparkplatform.api.services.CustomFieldService;
import com.sparkplatform.api.services.ListingService;
import com.sparkplatform.api.services.MarketStatisticsService;
import com.sparkplatform.api.services.PropertyTypeService;
import com.sparkplatform.api.services.StandardFieldService;
import com.sparkplatform.api.services.SystemInfoService;

/**
 * Main API client interface.  This client is strictly set to use only parameters and feature set 
 * specified in the documentation.  An instance of this class is provided to all service 
 * implementations.
 * 
 * @see BaseClient
 */
public class Client extends BaseClient<ApiParameter> {
	public Client(Configuration config, Connection<Response> defaultConnection, Connection<Response> secureConnection) {
		super(config, defaultConnection, secureConnection);
	}
	public Client(Configuration config) {
		super(config);
	}
	
	@Override
	Map<String, String> stringifyParameterKeys(Map<ApiParameter, String> parms) {
		if(parms == null)
			return null;
		
		Map<String, String> strings = new HashMap<String, String>();
		for (ApiParameter parm : parms.keySet()) {
			strings.put(parm.toString(), parms.get(parm));
		}
		return strings;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Service registry
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ContactService contactService; 
	private ListingService listingService;
	private PropertyTypeService propertyTypeService;
	private MarketStatisticsService marketStatisticsService;
	private SystemInfoService systemInfoService;
	private StandardFieldService standardFieldService;
	private CustomFieldService customFieldService;
	
	public ContactService getContactService() {
		if(contactService == null){
			contactService = new ContactService(this);
		}
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	public ListingService getListingService() {
		if(listingService == null){
			listingService = new ListingService(this);
		}
		return listingService;
	}

	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}

	public PropertyTypeService getPropertyTypeService() {
		if(propertyTypeService == null){
			propertyTypeService = new PropertyTypeService(this);
		}
		return propertyTypeService;
	}

	public void setPropertyTypeService(PropertyTypeService propertyTypeService) {
		this.propertyTypeService = propertyTypeService;
	}
	
	public MarketStatisticsService getMarketStatisticsService() {
		if(marketStatisticsService == null){
			marketStatisticsService = new MarketStatisticsService(this);
		}
		return marketStatisticsService;
	}

	public void setMarketStatisticsService(
			MarketStatisticsService marketStatisticsService) {
		this.marketStatisticsService = marketStatisticsService;
	}

	public SystemInfoService getSystemInfoService() {
		if(systemInfoService == null){
			systemInfoService = new SystemInfoService(this);
		}
		return systemInfoService;
	}

	public void setSystemInfoService(SystemInfoService systemInfoService) {
		this.systemInfoService = systemInfoService;
	}

	public StandardFieldService getStandardFieldService() {
		if(standardFieldService == null){
			standardFieldService = new StandardFieldService(this);
		}
		return standardFieldService;
	}

	public void setStandardFieldService(StandardFieldService standardFieldService) {
		this.standardFieldService = standardFieldService;
	}
	
	public CustomFieldService getCustomFieldService() {
		if(customFieldService == null){
			customFieldService = new CustomFieldService(this);
		}
		return customFieldService;
	}

	public void setCustomFieldService(CustomFieldService customFieldService) {
		this.customFieldService = customFieldService;
	}
	
}
