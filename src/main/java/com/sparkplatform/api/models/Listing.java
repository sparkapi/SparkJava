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
		
		@JsonProperty("ListingKey")
		private String listingKey;
		@JsonProperty("ListingId")
		private String listingId;
		@JsonProperty("PropertyType")
		private String propertyType;
		@JsonProperty("PropertySubType")
		private String propertySubType;
		@JsonProperty("ListPrice")
		private DoubleField listPrice;

		@JsonProperty("BathsFull")
		private IntegerField bathsFull;
		@JsonProperty("BathsHalf")
		private IntegerField bathsHalf;
		@JsonProperty("BathsThreeQuarter")
		private IntegerField bathsThreeQuarter;
		@JsonProperty("BathsTotal")
		private DoubleField bathsTotal;
		@JsonProperty("BedsTotal")
		private IntegerField bedsTotal;
		@JsonProperty("BuildingAreaTotal")
		private DoubleField buildingAreaTotal;

		@JsonProperty("StreetNumber")
		private String streetNumber;
		@JsonProperty("StreetDirPrefix")
		private String streetDirPrefix;
		@JsonProperty("StreetName")
		private String streetName;
		@JsonProperty("StreetSuffix")
		private String streetSuffix;
		@JsonProperty("StreetDirSuffix")
		private String streetDirSuffix;
		@JsonProperty("StreetAdditionalInfo")
		private String streetAdditionalInfo;
		@JsonProperty("City")
		private String city;
		@JsonProperty("StateOrProvince")
		private String stateOrProvince;
		@JsonProperty("PostalCode")
		private String postalCode;
		@JsonProperty("YearBuilt")
		private IntegerField yearBuilt;
		@JsonProperty("Longitude")
		private DoubleField longitude;
		@JsonProperty("Latitude")
		private DoubleField latitude;
		@JsonProperty("SubdivisionName")
		private String subdivisionName;
		@JsonProperty("MLSAreaMinor")
		private String mLSAreaMinor;
		@JsonProperty("CountyOrParish")
		private String countyOrParish;
		@JsonProperty("PublicRemarks")
		private String publicRemarks;
		@JsonProperty("privateRemarks")
		private String privateRemarks;
		@JsonProperty("privateOfficeRemarks")
		private String privateOfficeRemarks;
		@JsonProperty("PendingDate")
		private DateField pendingDate;
		@JsonProperty("CloseDate")
		private DateField closeDate;
		@JsonProperty("CancelDate")
		private DateField cancelDate;
		@JsonProperty("WithdrawDate")
		private DateField withdrawDate;
		@JsonProperty("ListAgentFirstName")
		private String listAgentFirstName;
		@JsonProperty("ListAgentMiddleName")
		private String listAgentMiddleName;
		@JsonProperty("ListAgentLastName")
		private String listAgentLastName;
		@JsonProperty("ListAgentPreferredPhone")
		private String listAgentPreferredPhone;
		@JsonProperty("ListAgentPreferredPhoneExt")
		private String listAgentPreferredPhoneExt;
		@JsonProperty("ListAgentOfficePhone")
		private String listAgentOfficePhone;
		@JsonProperty("ListAgentOfficePhoneExt")
		private String listAgentOfficePhoneExt;
		@JsonProperty("ListAgentCellPhone")
		private String listAgentCellPhone;
		@JsonProperty("ListAgentDirectPhone")
		private String listAgentDirectPhone;
		@JsonProperty("ListAgentTollFreePhone")
		private String listAgentTollFreePhone;
		@JsonProperty("ListAgentFax")
		private String listAgentFax;
		@JsonProperty("ListAgentPager")
		private String listAgentPager;
		@JsonProperty("ListAgentVoiceMail")
		private String listAgentVoiceMail;
		@JsonProperty("ListAgentVoiceMailExt")
		private String listAgentVoiceMailExt;
		@JsonProperty("ListAgentEmail")
		private String listAgentEmail;
		@JsonProperty("ListAgentURL")
		private String listAgentURL;
		@JsonProperty("ListAgentStateLicense")
		private String listAgentStateLicense;
		@JsonProperty("ListAgentDesignation")
		private String listAgentDesignation;
		@JsonProperty("ListOfficeName")
		private String listOfficeName;
		@JsonProperty("ListOfficePhone")
		private String listOfficePhone;
		@JsonProperty("ListOfficePhoneExt")
		private String listOfficePhoneExt;
		@JsonProperty("ListOfficeFax")
		private String listOfficeFax;
		@JsonProperty("ListOfficeEmail")
		private String listOfficeEmail;
		@JsonProperty("ListOfficeURL")
		private String listOfficeURL;
		@JsonProperty("CoListAgentFirstName")
		private String coListAgentFirstName;
		@JsonProperty("CoListAgentMiddleName")
		private String coListAgentMiddleName;
		@JsonProperty("CoListAgentLastName")
		private String coListAgentLastName;
		@JsonProperty("CoListAgentPreferredPhone")
		private String coListAgentPreferredPhone;
		@JsonProperty("CoListAgentPreferredPhoneExt")
		private String coListAgentPreferredPhoneExt;
		@JsonProperty("CoListAgentOfficePhone")
		private String coListAgentOfficePhone;
		@JsonProperty("CoListAgentOfficePhoneExt")
		private String coListAgentOfficePhoneExt;
		@JsonProperty("CoListAgentCellPhone")
		private String coListAgentCellPhone;
		@JsonProperty("CoListAgentDirectPhone")
		private String coListAgentDirectPhone;
		@JsonProperty("CoListAgentTollFreePhone")
		private String coListAgentTollFreePhone;
		@JsonProperty("CoListAgentFax")
		private String coListAgentFax;
		@JsonProperty("CoListAgentPager")
		private String coListAgentPager;
		@JsonProperty("CoListAgentVoiceMail")
		private String coListAgentVoiceMail;
		@JsonProperty("CoListAgentVoiceMailExt")
		private String coListAgentVoiceMailExt;
		@JsonProperty("CoListAgentEmail")
		private String coListAgentEmail;
		@JsonProperty("CoListAgentURL")
		private String coListAgentURL;
		@JsonProperty("CoListAgentStateLicense")
		private String coListAgentStateLicense;
		@JsonProperty("CoListAgentDesignation")
		private String coListAgentDesignation;
		@JsonProperty("CoListOfficeName")
		private String coListOfficeName;
		@JsonProperty("CoListOfficePhone")
		private String coListOfficePhone;
		@JsonProperty("CoListOfficePhoneExt")
		private String coListOfficePhoneExt;
		@JsonProperty("CoListOfficeFax")
		private String coListOfficeFax;
		@JsonProperty("CoListOfficeEmail")
		private String coListOfficeEmail;
		@JsonProperty("CoListOfficeURL")
		private String coListOfficeURL;
		@JsonProperty("VirtualTourURLBranded")
		private String virtualTourURLBranded;
		@JsonProperty("VirtualTourURLUnbranded")
		private String virtualTourURLUnbranded;
		@JsonProperty("Supplement")
		private String supplement;
		@JsonProperty("ModificationTimestamp")
		private DateField modificationTimestamp;
		
		public String getListingKey() {
			return listingKey;
		}
		public void setListingKey( String listingKey ) {
			this.listingKey = listingKey;
		}
		public String getListingId() {
			return listingId;
		}
		public void setListingId( String listingId ) {
			this.listingId = listingId;
		}
		public String getPropertyType() {
			return propertyType;
		}
		public void setPropertyType( String propertyType ) {
			this.propertyType = propertyType;
		}
		public String getPropertySubType() {
			return propertySubType;
		}
		public void setPropertySubType( String propertySubType ) {
			this.propertySubType = propertySubType;
		}
		public DoubleField getListPrice() {
			return listPrice;
		}
		public void setListPrice( DoubleField listPrice ) {
			this.listPrice = listPrice;
		}
		public String getStreetNumber() {
			return streetNumber;
		}
		public void setStreetNumber( String streetNumber ) {
			this.streetNumber = streetNumber;
		}
		public String getStreetDirPrefix() {
			return streetDirPrefix;
		}
		public void setStreetDirPrefix( String streetDirPrefix ) {
			this.streetDirPrefix = streetDirPrefix;
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName( String streetName ) {
			this.streetName = streetName;
		}
		public String getStreetSuffix() {
			return streetSuffix;
		}
		public void setStreetSuffix( String streetSuffix ) {
			this.streetSuffix = streetSuffix;
		}
		public String getStreetDirSuffix() {
			return streetDirSuffix;
		}
		public void setStreetDirSuffix( String streetDirSuffix ) {
			this.streetDirSuffix = streetDirSuffix;
		}
		public String getStreetAdditionalInfo() {
			return streetAdditionalInfo;
		}
		public void setStreetAdditionalInfo( String streetAdditionalInfo ) {
			this.streetAdditionalInfo = streetAdditionalInfo;
		}
		public String getCity() {
			return city;
		}
		public void setCity( String city ) {
			this.city = city;
		}
		public String getStateOrProvince() {
			return stateOrProvince;
		}
		public void setStateOrProvince( String stateOrProvince ) {
			this.stateOrProvince = stateOrProvince;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode( String postalCode ) {
			this.postalCode = postalCode;
		}
		public IntegerField getYearBuilt() {
			return yearBuilt;
		}
		public void setYearBuilt( IntegerField yearBuilt ) {
			this.yearBuilt = yearBuilt;
		}
		public DoubleField getBuildingAreaTotal() {
			return buildingAreaTotal;
		}
		public void setBuildingAreaTotal( DoubleField buildingAreaTotal ) {
			this.buildingAreaTotal = buildingAreaTotal;
		}
		public IntegerField getBathsThreeQuarter() {
			return bathsThreeQuarter;
		}
		public void setBathsThreeQuarter( IntegerField bathsThreeQuarter ) {
			this.bathsThreeQuarter = bathsThreeQuarter;
		}
		public DoubleField getBathsTotal() {
			return bathsTotal;
		}
		public void setBathsTotal( DoubleField bathsTotal ) {
			this.bathsTotal = bathsTotal;
		}
		public IntegerField getBathsFull() {
			return bathsFull;
		}
		public void setBathsFull( IntegerField bathsFull ) {
			this.bathsFull = bathsFull;
		}
		public IntegerField getBedsTotal() {
			return bedsTotal;
		}
		public void setBedsTotal( IntegerField bedsTotal ) {
			this.bedsTotal = bedsTotal;
		}
		public IntegerField getBathsHalf() {
			return bathsHalf;
		}
		public void setBathsHalf( IntegerField bathsHalf ) {
			this.bathsHalf = bathsHalf;
		}
		public DoubleField getLongitude() {
			return longitude;
		}
		public void setLongitude( DoubleField longitude ) {
			this.longitude = longitude;
		}
		public DoubleField getLatitude() {
			return latitude;
		}
		public void setLatitude( DoubleField latitude ) {
			this.latitude = latitude;
		}
		public String getSubdivisionName() {
			return subdivisionName;
		}
		public void setSubdivisionName( String subdivisionName ) {
			this.subdivisionName = subdivisionName;
		}
		public String getMLSAreaMinor() {
			return mLSAreaMinor;
		}
		public void setMLSAreaMinor( String mLSAreaMinor ) {
			this.mLSAreaMinor = mLSAreaMinor;
		}
		public String getCountyOrParish() {
			return countyOrParish;
		}
		public void setCountyOrParish( String countyOrParish ) {
			this.countyOrParish = countyOrParish;
		}
		public String getPublicRemarks() {
			return publicRemarks;
		}
		public void setPublicRemarks( String publicRemarks ) {
			this.publicRemarks = publicRemarks;
		}
		public String getPrivateRemarks() {
			return privateRemarks;
		}
		public void setPrivateRemarks( String privateRemarks ) {
			this.privateRemarks = privateRemarks;
		}
		public String getPrivateOfficeRemarks() {
			return privateOfficeRemarks;
		}
		public void setPrivateOfficeRemarks( String privateOfficeRemarks ) {
			this.privateOfficeRemarks = privateOfficeRemarks;
		}
		public DateField getPendingDate() {
			return pendingDate;
		}
		public void setPendingDate( DateField pendingDate ) {
			this.pendingDate = pendingDate;
		}
		public DateField getCloseDate() {
			return closeDate;
		}
		public void setCloseDate( DateField closeDate ) {
			this.closeDate = closeDate;
		}
		public DateField getCancelDate() {
			return cancelDate;
		}
		public void setCancelDate( DateField cancelDate ) {
			this.cancelDate = cancelDate;
		}
		public DateField getWithdrawDate() {
			return withdrawDate;
		}
		public void setWithdrawDate( DateField withdrawDate ) {
			this.withdrawDate = withdrawDate;
		}
		public String getListAgentFirstName() {
			return listAgentFirstName;
		}
		public void setListAgentFirstName( String listAgentFirstName ) {
			this.listAgentFirstName = listAgentFirstName;
		}
		public String getListAgentMiddleName() {
			return listAgentMiddleName;
		}
		public void setListAgentMiddleName( String listAgentMiddleName ) {
			this.listAgentMiddleName = listAgentMiddleName;
		}
		public String getListAgentLastName() {
			return listAgentLastName;
		}
		public void setListAgentLastName( String listAgentLastName ) {
			this.listAgentLastName = listAgentLastName;
		}
		public String getListAgentPreferredPhone() {
			return listAgentPreferredPhone;
		}
		public void setListAgentPreferredPhone( String listAgentPreferredPhone ) {
			this.listAgentPreferredPhone = listAgentPreferredPhone;
		}
		public String getListAgentPreferredPhoneExt() {
			return listAgentPreferredPhoneExt;
		}
		public void setListAgentPreferredPhoneExt( String listAgentPreferredPhoneExt ) {
			this.listAgentPreferredPhoneExt = listAgentPreferredPhoneExt;
		}
		public String getListAgentOfficePhone() {
			return listAgentOfficePhone;
		}
		public void setListAgentOfficePhone( String listAgentOfficePhone ) {
			this.listAgentOfficePhone = listAgentOfficePhone;
		}
		public String getListAgentOfficePhoneExt() {
			return listAgentOfficePhoneExt;
		}
		public void setListAgentOfficePhoneExt( String listAgentOfficePhoneExt ) {
			this.listAgentOfficePhoneExt = listAgentOfficePhoneExt;
		}
		public String getListAgentCellPhone() {
			return listAgentCellPhone;
		}
		public void setListAgentCellPhone( String listAgentCellPhone ) {
			this.listAgentCellPhone = listAgentCellPhone;
		}
		public String getListAgentDirectPhone() {
			return listAgentDirectPhone;
		}
		public void setListAgentDirectPhone( String listAgentDirectPhone ) {
			this.listAgentDirectPhone = listAgentDirectPhone;
		}
		public String getListAgentTollFreePhone() {
			return listAgentTollFreePhone;
		}
		public void setListAgentTollFreePhone( String listAgentTollFreePhone ) {
			this.listAgentTollFreePhone = listAgentTollFreePhone;
		}
		public String getListAgentFax() {
			return listAgentFax;
		}
		public void setListAgentFax( String listAgentFax ) {
			this.listAgentFax = listAgentFax;
		}
		public String getListAgentPager() {
			return listAgentPager;
		}
		public void setListAgentPager( String listAgentPager ) {
			this.listAgentPager = listAgentPager;
		}
		public String getListAgentVoiceMail() {
			return listAgentVoiceMail;
		}
		public void setListAgentVoiceMail( String listAgentVoiceMail ) {
			this.listAgentVoiceMail = listAgentVoiceMail;
		}
		public String getListAgentVoiceMailExt() {
			return listAgentVoiceMailExt;
		}
		public void setListAgentVoiceMailExt( String listAgentVoiceMailExt ) {
			this.listAgentVoiceMailExt = listAgentVoiceMailExt;
		}
		public String getListAgentEmail() {
			return listAgentEmail;
		}
		public void setListAgentEmail( String listAgentEmail ) {
			this.listAgentEmail = listAgentEmail;
		}
		public String getListAgentURL() {
			return listAgentURL;
		}
		public void setListAgentURL( String listAgentURL ) {
			this.listAgentURL = listAgentURL;
		}
		public String getListAgentStateLicense() {
			return listAgentStateLicense;
		}
		public void setListAgentStateLicense( String listAgentStateLicense ) {
			this.listAgentStateLicense = listAgentStateLicense;
		}
		public String getListAgentDesignation() {
			return listAgentDesignation;
		}
		public void setListAgentDesignation( String listAgentDesignation ) {
			this.listAgentDesignation = listAgentDesignation;
		}
		public String getListOfficeName() {
			return listOfficeName;
		}
		public void setListOfficeName( String listOfficeName ) {
			this.listOfficeName = listOfficeName;
		}
		public String getListOfficePhone() {
			return listOfficePhone;
		}
		public void setListOfficePhone( String listOfficePhone ) {
			this.listOfficePhone = listOfficePhone;
		}
		public String getListOfficePhoneExt() {
			return listOfficePhoneExt;
		}
		public void setListOfficePhoneExt( String listOfficePhoneExt ) {
			this.listOfficePhoneExt = listOfficePhoneExt;
		}
		public String getListOfficeFax() {
			return listOfficeFax;
		}
		public void setListOfficeFax( String listOfficeFax ) {
			this.listOfficeFax = listOfficeFax;
		}
		public String getListOfficeEmail() {
			return listOfficeEmail;
		}
		public void setListOfficeEmail( String listOfficeEmail ) {
			this.listOfficeEmail = listOfficeEmail;
		}
		public String getListOfficeURL() {
			return listOfficeURL;
		}
		public void setListOfficeURL( String listOfficeURL ) {
			this.listOfficeURL = listOfficeURL;
		}
		public String getCoListAgentFirstName() {
			return coListAgentFirstName;
		}
		public void setCoListAgentFirstName( String coListAgentFirstName ) {
			this.coListAgentFirstName = coListAgentFirstName;
		}
		public String getCoListAgentMiddleName() {
			return coListAgentMiddleName;
		}
		public void setCoListAgentMiddleName( String coListAgentMiddleName ) {
			this.coListAgentMiddleName = coListAgentMiddleName;
		}
		public String getCoListAgentLastName() {
			return coListAgentLastName;
		}
		public void setCoListAgentLastName( String coListAgentLastName ) {
			this.coListAgentLastName = coListAgentLastName;
		}
		public String getCoListAgentPreferredPhone() {
			return coListAgentPreferredPhone;
		}
		public void setCoListAgentPreferredPhone( String coListAgentPreferredPhone ) {
			this.coListAgentPreferredPhone = coListAgentPreferredPhone;
		}
		public String getCoListAgentPreferredPhoneExt() {
			return coListAgentPreferredPhoneExt;
		}
		public void setCoListAgentPreferredPhoneExt( String coListAgentPreferredPhoneExt ) {
			this.coListAgentPreferredPhoneExt = coListAgentPreferredPhoneExt;
		}
		public String getCoListAgentOfficePhone() {
			return coListAgentOfficePhone;
		}
		public void setCoListAgentOfficePhone( String coListAgentOfficePhone ) {
			this.coListAgentOfficePhone = coListAgentOfficePhone;
		}
		public String getCoListAgentOfficePhoneExt() {
			return coListAgentOfficePhoneExt;
		}
		public void setCoListAgentOfficePhoneExt( String coListAgentOfficePhoneExt ) {
			this.coListAgentOfficePhoneExt = coListAgentOfficePhoneExt;
		}
		public String getCoListAgentCellPhone() {
			return coListAgentCellPhone;
		}
		public void setCoListAgentCellPhone( String coListAgentCellPhone ) {
			this.coListAgentCellPhone = coListAgentCellPhone;
		}
		public String getCoListAgentDirectPhone() {
			return coListAgentDirectPhone;
		}
		public void setCoListAgentDirectPhone( String coListAgentDirectPhone ) {
			this.coListAgentDirectPhone = coListAgentDirectPhone;
		}
		public String getCoListAgentTollFreePhone() {
			return coListAgentTollFreePhone;
		}
		public void setCoListAgentTollFreePhone( String coListAgentTollFreePhone ) {
			this.coListAgentTollFreePhone = coListAgentTollFreePhone;
		}
		public String getCoListAgentFax() {
			return coListAgentFax;
		}
		public void setCoListAgentFax( String coListAgentFax ) {
			this.coListAgentFax = coListAgentFax;
		}
		public String getCoListAgentPager() {
			return coListAgentPager;
		}
		public void setCoListAgentPager( String coListAgentPager ) {
			this.coListAgentPager = coListAgentPager;
		}
		public String getCoListAgentVoiceMail() {
			return coListAgentVoiceMail;
		}
		public void setCoListAgentVoiceMail( String coListAgentVoiceMail ) {
			this.coListAgentVoiceMail = coListAgentVoiceMail;
		}
		public String getCoListAgentVoiceMailExt() {
			return coListAgentVoiceMailExt;
		}
		public void setCoListAgentVoiceMailExt( String coListAgentVoiceMailExt ) {
			this.coListAgentVoiceMailExt = coListAgentVoiceMailExt;
		}
		public String getCoListAgentEmail() {
			return coListAgentEmail;
		}
		public void setCoListAgentEmail( String coListAgentEmail ) {
			this.coListAgentEmail = coListAgentEmail;
		}
		public String getCoListAgentURL() {
			return coListAgentURL;
		}
		public void setCoListAgentURL( String coListAgentURL ) {
			this.coListAgentURL = coListAgentURL;
		}
		public String getCoListAgentStateLicense() {
			return coListAgentStateLicense;
		}
		public void setCoListAgentStateLicense( String coListAgentStateLicense ) {
			this.coListAgentStateLicense = coListAgentStateLicense;
		}
		public String getCoListAgentDesignation() {
			return coListAgentDesignation;
		}
		public void setCoListAgentDesignation( String coListAgentDesignation ) {
			this.coListAgentDesignation = coListAgentDesignation;
		}
		public String getCoListOfficeName() {
			return coListOfficeName;
		}
		public void setCoListOfficeName( String coListOfficeName ) {
			this.coListOfficeName = coListOfficeName;
		}
		public String getCoListOfficePhone() {
			return coListOfficePhone;
		}
		public void setCoListOfficePhone( String coListOfficePhone ) {
			this.coListOfficePhone = coListOfficePhone;
		}
		public String getCoListOfficePhoneExt() {
			return coListOfficePhoneExt;
		}
		public void setCoListOfficePhoneExt( String coListOfficePhoneExt ) {
			this.coListOfficePhoneExt = coListOfficePhoneExt;
		}
		public String getCoListOfficeFax() {
			return coListOfficeFax;
		}
		public void setCoListOfficeFax( String coListOfficeFax ) {
			this.coListOfficeFax = coListOfficeFax;
		}
		public String getCoListOfficeEmail() {
			return coListOfficeEmail;
		}
		public void setCoListOfficeEmail( String coListOfficeEmail ) {
			this.coListOfficeEmail = coListOfficeEmail;
		}
		public String getCoListOfficeURL() {
			return coListOfficeURL;
		}
		public void setCoListOfficeURL( String coListOfficeURL ) {
			this.coListOfficeURL = coListOfficeURL;
		}
		public String getVirtualTourURLBranded() {
			return virtualTourURLBranded;
		}
		public void setVirtualTourURLBranded( String virtualTourURLBranded ) {
			this.virtualTourURLBranded = virtualTourURLBranded;
		}
		public String getVirtualTourURLUnbranded() {
			return virtualTourURLUnbranded;
		}
		public void setVirtualTourURLUnbranded( String virtualTourURLUnbranded ) {
			this.virtualTourURLUnbranded = virtualTourURLUnbranded;
		}
		public String getSupplement() {
			return supplement;
		}
		public void setSupplement( String supplement ) {
			this.supplement = supplement;
		}
		public DateField getModificationTimestamp() {
			return modificationTimestamp;
		}
		public void setModificationTimestamp( DateField modificationTimestamp ) {
			this.modificationTimestamp = modificationTimestamp;
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
