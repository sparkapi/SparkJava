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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class SystemInfo extends Base {
	
	private static final long serialVersionUID = 14L;
	
	@JsonProperty("Id")
	private String id;
	@JsonProperty("OfficeId")
	private String officeId;
	@JsonProperty("MlsId")
	private String mlsId;
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Office")
	private String office;
	@JsonProperty("Mls")
	private String mls;

	@JsonProperty("Configuration")
	private List<SystemInfo.Configuration> configurationList = new ArrayList<SystemInfo.Configuration>();

	public static class Configuration extends Base {
		
		private static final long serialVersionUID = 15L;
		
		@JsonProperty("MlsLogos")
		private List<String> mlsLogos;
		
		@JsonProperty("IdxDisclaimer")
		private String idxDisclaimer;
		@JsonProperty("IdxLogoSmall")
		private String idxLogoSmall;
		@JsonProperty("IdxLogo")
		private String idxLogo;
		
		@JsonProperty("OAuth2ServiceEndpointPrivate")
		private String oauth2ServiceEndpointPrivate;
		
		public List<String> getMlsLogos() {
			return mlsLogos;
		}

		public void setMlsLogos(List<String> mlsLogos) {
			this.mlsLogos = mlsLogos;
		}

		public String getIdxDisclaimer() {
			return idxDisclaimer;
		}
		public void setIdxDisclaimer(String idxDisclaimer) {
			this.idxDisclaimer = idxDisclaimer;
		}

		public String getIdxLogoSmall() {
			return idxLogoSmall;
		}

		public void setIdxLogoSmall(String idxLogoSmall) {
			this.idxLogoSmall = idxLogoSmall;
		}

		public String getIdxLogo() {
			return idxLogo;
		}

		public void setIdxLogo(String idxLogo) {
			this.idxLogo = idxLogo;
		}

		public String getOauth2ServiceEndpointPrivate() {
			return oauth2ServiceEndpointPrivate;
		}

		public void setOauth2ServiceEndpointPrivate(String oauth2ServiceEndpointPrivate) {
			this.oauth2ServiceEndpointPrivate = oauth2ServiceEndpointPrivate;
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getMlsId() {
		return mlsId;
	}

	public void setMlsId(String mlsId) {
		this.mlsId = mlsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getMls() {
		return mls;
	}

	public void setMls(String mls) {
		this.mls = mls;
	}

	public Configuration getConfiguration() {
		return configurationList.get(0);
	}

	public void setConfiguration(Configuration configuration) {
		this.configurationList.clear();
		this.configurationList.add(configuration);
	}
	
}
