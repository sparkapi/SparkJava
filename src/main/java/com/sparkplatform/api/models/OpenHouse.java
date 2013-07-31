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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenHouse extends ResourceEntity {
	
	private static final long serialVersionUID = 8L;
	
    private static final SimpleDateFormat MDY_FORMAT = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
	private static Logger logger = Logger.getLogger(OpenHouse.class);
	
	@JsonProperty("Date")
	private String dateString;
	@JsonIgnore
	private Date date;
	
	@JsonProperty("StartTime")
	private String startString;
	@JsonIgnore
	private Date startTime;
	
	@JsonProperty("EndTime")
	private String endString;
	@JsonIgnore
	private Date endTime;

	public Date getDate() {
		if(date == null){
	        try {
	            date = MDY_FORMAT.parse(dateString);
	        } catch (ParseException e) {
	        	logger.debug("Failed parsing expected date format.  Trying again with standard formats.", e);
	        }
		}
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		if(startTime == null){
	        try {
	        	startTime = FORMAT.parse(dateString + " " + startString);
	        } catch (ParseException e) {
	        	logger.debug("Failed parsing expected date format.  Trying again with standard formats.", e);
	        }
		}
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		if(endTime == null){
	        try {
	        	endTime = FORMAT.parse(dateString + " " + endString);
	        } catch (ParseException e) {
	        	logger.debug("Failed parsing expected date format.  Trying again with standard formats.", e);
	        }
		}
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
