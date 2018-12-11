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
package us.mn.state.health.lims.sampletracking.valueholder;

import java.io.Serializable;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 * @author AIS view sampletracking is mapped to this valueholder
 * bug 1920 - standards
 *
 */
public class SampleTracking extends BaseObject implements Serializable {
	
	private SampleTracking id;
	
	private String accNum;
	
	private String patientId;
	
	private String cliRef;
	
	private String patientLastName;
	
	private String patientFirstName;
	
	//bugzilla 2069
	private String organizationLocalAbbreviation;
	
	private String orgName;
	
	private String recdDate;
	
	private String tosId;	
	
	private String tosDesc;
	
	private String sosId;
	
	private String sosDesc;
	
	private String collDate;
	
	private String dateofBirth;

	//bugzilla 2455
	private String specOrIsolate;
		
	public SampleTracking() {
		super();
	}

	//bugzilla 2455
	public String getSpecOrIsolate() {
		return specOrIsolate;
	}
	public void setSpecOrIsolate(String specOrIsolate) {
		this.specOrIsolate = specOrIsolate;
	}
	
	
	public void setId(SampleTracking id) {
		this.id = id;
	}
	
	public SampleTracking getId() {
		return this.id;
	}	
	
	public String getAccNum() {
		return this.accNum;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public String getCliRef() {
		return this.cliRef;
	}

	public String getPatientLastName() {
		return this.patientLastName;
	}

	public String getPatientFirstName() {
		return this.patientFirstName;
	}

	public String getOrgName() {
		return this.orgName;
	}
	
	//bugzilla 2069
	public String getOrganizationLocalAbbreviation() {
		return this.organizationLocalAbbreviation;
	}

	public String getRecdDate() {
		return this.recdDate;
	}
	
	public String getTosId() {
		return this.tosId;
	}

	public String getTosDesc() {
		return this.tosDesc;
	}
	
	public String getSosId() {
		return this.sosId;
	}

	public String getSosDesc() {
		return this.sosDesc;
	}

	public String getCollDate() {
		return this.collDate;
	}	
	
	public String getDateofBirth() {
		return this.dateofBirth;
	}	
	
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setCliRef(String cliRef) {
		this.cliRef = cliRef;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	//bugzilla 2069
	public void setOrganizationLocalAbbreviation(String organizationLocalAbbreviation) {
		this.organizationLocalAbbreviation = organizationLocalAbbreviation;
	}

	public void setRecdDate(String recdDate) {
		this.recdDate = recdDate;
	}
	
	public void setTosId(String tosId) {
		this.tosId = tosId;
	}

	public void setTosDesc(String tosDesc) {
		this.tosDesc = tosDesc;
	}
	
	public void setSosId(String sosId) {
		this.sosId = sosId;
	}

	public void setSosDesc(String sosDesc) {
		this.sosDesc = sosDesc;
	}

	public void setCollDate(String collDate) {
		this.collDate = collDate;
	}	
	
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
}
