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
package us.mn.state.health.lims.organization.valueholder;

import java.util.HashSet;
import java.util.Set;

import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;
import us.mn.state.health.lims.common.valueholder.SimpleBaseEntity;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;

public class Organization extends EnumValueItemImpl implements SimpleBaseEntity<String>{
    private static final long serialVersionUID = 1L;

    private String city;
	private String cliaNum;
	private String id;
	private String internetAddress;
	private String isActive;
	private String mlsLabFlag;
	private String mlsSentinelLabFlag;
	private String multipleUnit;
	private ValueHolderInterface organization;
	private String organizationName;
	private String orgMltOrgMltId;
	private String pwsId;
	private String shortName;
	private String state;
	private String streetAddress;
	private String zipCode;
	private String selectedOrgId;
	private String organizationLocalAbbreviation;
	private String code;
	private Set<OrganizationType> organizationTypes;
	
	public Organization() {
		super();
		this.organization = new ValueHolder();
	}

	public String getCity() {
		return this.city;
	}

	public String getCliaNum() {
		return this.cliaNum;
	}

	public String getId() {
		return this.id;
	}

	public String getConcatOrganizationLocalAbbreviationName() {		
		return this.organizationLocalAbbreviation +"-" +this.organizationName;
	}	

	public String getInternetAddress() {
		return this.internetAddress;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public String getMlsLabFlag() {
		return this.mlsLabFlag;
	}

	public String getMlsSentinelLabFlag() {
		return this.mlsSentinelLabFlag;
	}

	public String getMultipleUnit() {
		return this.multipleUnit;
	}

	public String getOrgMltOrgMltId() {
		return this.orgMltOrgMltId;
	}

	public Organization getOrganization() {
		return (Organization) this.organization.getValue();
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public String getPwsId() {
		return this.pwsId;
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getState() {
		return this.state;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCliaNum(String cliaNum) {
		this.cliaNum = cliaNum;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setMlsLabFlag(String mlsLabFlag) {
		this.mlsLabFlag = mlsLabFlag;
	}

	public void setMlsSentinelLabFlag(String mlsSentinelLabFlag) {
		this.mlsSentinelLabFlag = mlsSentinelLabFlag;
	}

	public void setMultipleUnit(String multipleUnit) {
		this.multipleUnit = multipleUnit;
	}

	public void setOrgMltOrgMltId(String orgMltOrgMltId) {
		this.orgMltOrgMltId = orgMltOrgMltId;
	}

	public void setOrganization(Organization organization) {
		this.organization.setValue(organization);
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public void setPwsId(String pwsId) {
		this.pwsId = pwsId;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setSelectedOrgId(String selectedOrgId) {
		this.selectedOrgId = selectedOrgId;
	}

	public String getSelectedOrgId() {
		return this.selectedOrgId;
	}

	private Set testSections = new HashSet(0);

    public Set getTestSections() {
        return this.testSections;
    }
    
    public void setTestSections(Set testSections) {
        this.testSections = testSections;
    }

	public String getOrganizationLocalAbbreviation() {
		return organizationLocalAbbreviation;
	}

	public void setOrganizationLocalAbbreviation(
			String organizationLocalAbbreviation) {
		this.organizationLocalAbbreviation = organizationLocalAbbreviation;
	}
	
	public String getDoubleName() {
	    return this.shortName + " = " + this.organizationName;
	}

    @Override
    public String toString() {
        return "Organization [id=" + id + ", isActive=" + isActive + ", organizationName=" + organizationName
                        + ", organizationLocalAbbreviation=" + organizationLocalAbbreviation + ", shortName="
                        + shortName + "]";
    }

    /**
     *
     * @param organizationTypes
     *
     * @deprecated this seem to be bogus and will cause a null pointer exception in session flush.  Use the Organization_organization_type link method
     */
    @Deprecated
    public void setOrganizationTypes(Set<OrganizationType> organizationTypes) {
        this.organizationTypes = organizationTypes;
    }

    public Set<OrganizationType> getOrganizationTypes() {
        return organizationTypes;
    }

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}
}
