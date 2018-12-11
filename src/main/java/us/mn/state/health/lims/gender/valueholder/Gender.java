/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.gender.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class Gender extends BaseObject {
	private static final long serialVersionUID = 1L;

	private String id;

	private String description;

	private String genderType;
	
	public Gender() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	
	@Override
	public String getDefaultLocalizedName() {
		return this.description;
	}
	
	public String toString() {
		return "Gender { Id = " + id + ", description=" + description  + " }";
	}
}