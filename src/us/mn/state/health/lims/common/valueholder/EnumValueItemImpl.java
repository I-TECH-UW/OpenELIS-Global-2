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
package us.mn.state.health.lims.common.valueholder;

import us.mn.state.health.lims.common.action.IActionConstants;

public class EnumValueItemImpl extends BaseObject implements EnumValueItem {

	protected String name;

	protected String enumName;

	protected String sortOrder;

	protected String key;

	protected String isActive = IActionConstants.YES;

	// enum name
	public String getEnumName() {
		return this.enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	// Each enumValueItem can be retrieved using a string key
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	// enumValueItem name
	public String getName() {
		return this.name;
	}

	public void setName(String pName) {
		this.name = name;
	}

	// enumValueItem sortorder
	public String getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(String pSortorder) {
		this.sortOrder = sortOrder;
	}

	
	// is enum active
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String pActive) {
		this.isActive = pActive;
	}



}