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

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;
import us.mn.state.health.lims.common.valueholder.SimpleBaseEntity;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;

public class Organization extends EnumValueItemImpl implements SimpleBaseEntity<String> {
	private static final long serialVersionUID = 1L;

	@SafeHtml
	private String city;
	@SafeHtml
	private String cliaNum;
	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String id;
	@URL
	private String internetAddress;
	@Pattern(regexp = ValidationHelper.YES_NO_REGEX)
	private String isActive;
	@SafeHtml
	private String mlsLabFlag;
	@Pattern(regexp = ValidationHelper.YES_NO_REGEX)
	private String mlsSentinelLabFlag;
	@SafeHtml
	private String multipleUnit;
	private ValueHolderInterface organization;
	@SafeHtml
	private String organizationName;
	@SafeHtml
	private String orgMltOrgMltId;
	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String pwsId;
	@SafeHtml
	private String shortName;
	@SafeHtml
	private String state;
	@SafeHtml
	private String streetAddress;
	@SafeHtml
	private String zipCode;
	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String selectedOrgId;
	@SafeHtml
	private String organizationLocalAbbreviation;
	@SafeHtml
	private String code;
	private Set<OrganizationType> organizationTypes;

	public Organization() {
		super();
		organization = new ValueHolder();
	}

	public String getCity() {
		return city;
	}

	public String getCliaNum() {
		return cliaNum;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getConcatOrganizationLocalAbbreviationName() {
		return organizationLocalAbbreviation + "-" + organizationName;
	}

	public String getInternetAddress() {
		return internetAddress;
	}

	@Override
	public String getIsActive() {
		return isActive;
	}

	public String getMlsLabFlag() {
		return mlsLabFlag;
	}

	public String getMlsSentinelLabFlag() {
		return mlsSentinelLabFlag;
	}

	public String getMultipleUnit() {
		return multipleUnit;
	}

	public String getOrgMltOrgMltId() {
		return orgMltOrgMltId;
	}

	public Organization getOrganization() {
		return (Organization) organization.getValue();
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getPwsId() {
		return pwsId;
	}

	public String getShortName() {
		return shortName;
	}

	public String getState() {
		return state;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCliaNum(String cliaNum) {
		this.cliaNum = cliaNum;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	@Override
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
		return selectedOrgId;
	}

	private Set testSections = new HashSet(0);

	public Set getTestSections() {
		return testSections;
	}

	public void setTestSections(Set testSections) {
		this.testSections = testSections;
	}

	public String getOrganizationLocalAbbreviation() {
		return organizationLocalAbbreviation;
	}

	public void setOrganizationLocalAbbreviation(String organizationLocalAbbreviation) {
		this.organizationLocalAbbreviation = organizationLocalAbbreviation;
	}

	public String getDoubleName() {
		return shortName + " = " + organizationName;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", isActive=" + isActive + ", organizationName=" + organizationName
				+ ", organizationLocalAbbreviation=" + organizationLocalAbbreviation + ", shortName=" + shortName + "]";
	}

	/**
	 *
	 * @param organizationTypes
	 *
	 * @deprecated this seem to be bogus and will cause a null pointer exception in
	 *             session flush. Use the Organization_organization_type link method
	 */
	@Deprecated
	public void setOrganizationTypes(Set<OrganizationType> organizationTypes) {
		this.organizationTypes = organizationTypes;
	}

	public Set<OrganizationType> getOrganizationTypes() {
		return organizationTypes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
