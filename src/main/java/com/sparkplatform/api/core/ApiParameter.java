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

/**
 * All the documented API response codes.
 */
public enum ApiParameter {
	// Search
	_filter,
	_expand,
	_select,
	// Pagination
	_pagination,
	_limit,
	_page,
	_orderby,
	// Market Stats
	LocationField,
	LocationValue,
	Options,
	PropertyTypeCode,
	// Hotsheet
	HotSheet,
	OpenHouses,
	// Nearby
	Lat,
	Lon,
	// Api client fundamentals
	ApiKey,
	ApiSig,
	AuthToken,
	ApiUser;

}
