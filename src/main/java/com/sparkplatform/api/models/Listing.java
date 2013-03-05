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

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Listing extends ResourceEntity {
	
	private static final long serialVersionUID = 5L;
	
	@JsonProperty("StandardFields")
	private StandardFields standardFields;
	
	public StandardFields getStandardFields() {
		return standardFields;
	}
	
	public void setStandardFields(StandardFields standardFields) {
		this.standardFields = standardFields;
	}
	
	public static class StandardFields extends Base {
		
		private static final long serialVersionUID = 6L;
		
	    @JsonProperty("StreetNumber")
	    private String streetNumber;
	    @JsonProperty("Longitude")
	    private String longitude;
	    @JsonProperty("City")
	    private String city;
	    @JsonProperty("ListingId")
	    private String listingId;
	    @JsonProperty("PublicRemarks")
	    private String publicRemarks;
	    @JsonProperty("BuildingAreaTotal")
	    private String buildingAreaTotal;
	    @JsonProperty("YearBuilt")
	    private int yearBuilt;
	    @JsonProperty("StreetName")
	    private String streetName;
	    @JsonProperty("ListPrice")
	    private Double listPrice;
	    @JsonProperty("PostalCode")
	    private String postalCode;
	    @JsonProperty("Latitude")
	    private String latitude;
	    @JsonProperty("BathsThreeQuarter")
	    private String bathsThreeQuarter;
	    @JsonProperty("BathsFull")
	    private String bathsFull;
	    @JsonProperty("BathsTotal")
	    private Double bathsTotal;
	    @JsonProperty("StateOrProvince")
	    private String stateOrProvince;
	    @JsonProperty("PropertyType")
	    private String propertyType;
	    @JsonProperty("StreetAdditionalInfo")
	    private String streetAdditionalInfo;
	    @JsonProperty("StreetDirPrefix")
	    private String streetDirPrefix;
	    @JsonProperty("BedsTotal")
	    private String bedsTotal;
	    @JsonProperty("StreetDirSuffix")
	    private String streetDirSuffix;
	    @JsonProperty("ListingKey")
	    private String listingKey;
	    @JsonProperty("ListOfficeName")
	    private String listOfficeName;
	    @JsonProperty("BathsHalf")
	    private String bathsHalf;
	    @JsonProperty("ModificationTimestamp")
	    private Date modificationTimestamp;
	    @JsonProperty("CountyOrParish")
	    private String countyOrParish;
	    @JsonProperty("StreetSuffix")
	    private String streetSuffix;
	    
		public String getStreetNumber() {
			return streetNumber;
		}
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getListingId() {
			return listingId;
		}
		public void setListingId(String listingId) {
			this.listingId = listingId;
		}
		public String getPublicRemarks() {
			return publicRemarks;
		}
		public void setPublicRemarks(String publicRemarks) {
			this.publicRemarks = publicRemarks;
		}
		public String getBuildingAreaTotal() {
			return buildingAreaTotal;
		}
		public void setBuildingAreaTotal(String buildingAreaTotal) {
			this.buildingAreaTotal = buildingAreaTotal;
		}
		public int getYearBuilt() {
			return yearBuilt;
		}
		public void setYearBuilt(int yearBuilt) {
			this.yearBuilt = yearBuilt;
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		public Double getListPrice() {
			return listPrice;
		}
		public void setListPrice(Double listPrice) {
			this.listPrice = listPrice;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getBathsThreeQuarter() {
			return bathsThreeQuarter;
		}
		public void setBathsThreeQuarter(String bathsThreeQuarter) {
			this.bathsThreeQuarter = bathsThreeQuarter;
		}
		public String getBathsFull() {
			return bathsFull;
		}
		public void setBathsFull(String bathsFull) {
			this.bathsFull = bathsFull;
		}
		public Double getBathsTotal() {
			return bathsTotal;
		}
		public void setBathsTotal(Double bathsTotal) {
			this.bathsTotal = bathsTotal;
		}
		public String getStateOrProvince() {
			return stateOrProvince;
		}
		public void setStateOrProvince(String stateOrProvince) {
			this.stateOrProvince = stateOrProvince;
		}
		public String getPropertyType() {
			return propertyType;
		}
		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}
		public String getStreetAdditionalInfo() {
			return streetAdditionalInfo;
		}
		public void setStreetAdditionalInfo(String streetAdditionalInfo) {
			this.streetAdditionalInfo = streetAdditionalInfo;
		}
		public String getStreetDirPrefix() {
			return streetDirPrefix;
		}
		public void setStreetDirPrefix(String streetDirPrefix) {
			this.streetDirPrefix = streetDirPrefix;
		}
		public String getBedsTotal() {
			return bedsTotal;
		}
		public void setBedsTotal(String bedsTotal) {
			this.bedsTotal = bedsTotal;
		}
		public String getStreetDirSuffix() {
			return streetDirSuffix;
		}
		public void setStreetDirSuffix(String streetDirSuffix) {
			this.streetDirSuffix = streetDirSuffix;
		}
		public String getListingKey() {
			return listingKey;
		}
		public void setListingKey(String listingKey) {
			this.listingKey = listingKey;
		}
		public String getListOfficeName() {
			return listOfficeName;
		}
		public void setListOfficeName(String listOfficeName) {
			this.listOfficeName = listOfficeName;
		}
		public String getBathsHalf() {
			return bathsHalf;
		}
		public void setBathsHalf(String bathsHalf) {
			this.bathsHalf = bathsHalf;
		}
		public Date getModificationTimestamp() {
			return modificationTimestamp;
		}
		public void setModificationTimestamp(Date modificationTimestamp) {
			this.modificationTimestamp = modificationTimestamp;
		}
		public String getCountyOrParish() {
			return countyOrParish;
		}
		public void setCountyOrParish(String countyOrParish) {
			this.countyOrParish = countyOrParish;
		}
		
		public String getStreetSuffix() {
			return streetSuffix;
		}
		public void setStreetSuffix(String streetSuffix) {
			this.streetSuffix = streetSuffix;
		}
		
	    @JsonProperty("Documents")
	    private List<Document> documents;
	    @JsonProperty("Photos")
	    private List<Photo> photos;
	    @JsonProperty("Videos")
	    private List<Video> videos;
	    @JsonProperty("VirtualTours")
	    private List<VirtualTour> virtualTours;
	    @JsonProperty("OpenHouses")
	    private List<OpenHouse> openHouses;

		public List<Document> getDocuments() {
			return documents;
		}
		public void setDocuments(List<Document> documents) {
			this.documents = documents;
		}
		public List<Photo> getPhotos() {
			return photos;
		}
		public void setPhotos(List<Photo> photos) {
			this.photos = photos;
		}
		public List<Video> getVideos() {
			return videos;
		}
		public void setVideos(List<Video> videos) {
			this.videos = videos;
		}
		public List<VirtualTour> getVirtualTours() {
			return virtualTours;
		}
		public void setVirtualTours(List<VirtualTour> virtualTours) {
			this.virtualTours = virtualTours;
		}
		public List<OpenHouse> getOpenHouses() {
			return openHouses;
		}
		public void setOpenHouses(List<OpenHouse> openHouses) {
			this.openHouses = openHouses;
		}
		
	}
}
