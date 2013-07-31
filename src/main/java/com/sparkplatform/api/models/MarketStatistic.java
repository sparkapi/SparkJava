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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;


public class MarketStatistic extends Base {
	
	private static final long serialVersionUID = 7L;
	
	private static Logger logger = Logger.getLogger(MarketStatistic.class);
	
	public static class MDY_DateDeserializer extends DateDeserializer {
        private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException {
	        String date = jsonParser.getText();
	        try {
	            return FORMAT.parse(date);
	        } catch (ParseException e) {
	        	logger.debug("Failed parsing expected date format.  Trying again with standard formats.", e);
	        }
			return super.deserialize(jsonParser, ctxt);
		}
		
	}

	private Map<String, List<Float>> attributes = new HashMap<String, List<Float>>();
	
	public static enum MarketStatFields {
		// Volume
		ActiveListVolume,
		NewListVolume,
		PendedListVolume,
		SoldListVolume,
		SoldSaleVolume,
		//DOM
		AverageDom,
		AverageCdom,
		// Ratio
		SaleToOriginalListPriceRatio,
		SaleToListPriceRatio,
		// Price
		ActiveAverageListPrice,
		NewAverageListPrice,
		PendedAverageListPrice,
		SoldAverageListPrice,
		SoldAverageSoldPrice,
		ActiveMedianListPrice,
		NewMedianListPrice,
		PendedMedianListPrice,
		SoldMedianListPrice,
		SoldMedianSoldPrice,
		// Inventory
		ActiveListings,
		NewListings,
		PendedListings,
		SoldListings,
		// Absortion
		AbsorptionRate;

	}

	@JsonProperty("Dates")
	@JsonDeserialize(contentUsing=MDY_DateDeserializer.class)
	private List<Date> dates;

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	
	@JsonAnySetter
	public void setAttribute(String key, Object obj){
		if (MarketStatFields.valueOf(key) != null) {
			@SuppressWarnings("unchecked")
			List<Float> floats = (List<Float>)obj;
			if(floats != null){
				setAttribute(key, floats);
				return;
			}
			logger.warn("Key value is not the expected json type: "  + key);
		}
		super.setAttribute(key, obj);
	}
	
	public void setAttribute(String key, List<Float> value){
		if(logger.isDebugEnabled()){
			logger.debug("Added attribute: "  + key);
		}
		attributes.put(key, value);
	}

	public Map<String, List<Float>> getMarketAttributes(){
		return attributes;
	}	

}
