package spring.mine.organization.form;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

public class OrganizationForm extends BaseForm {
	private String id = "";

	private String organizationLocalAbbreviation = "";

	@NotBlank
	@SafeHtml
	private String organizationName = "";

	private String city = "";

	private String zipCode = "";

	private String mlsSentinelLabFlag = "";

	@NotBlank
	@Pattern(regexp = ValidationHelper.YES_NO_REGEX)
	private String isActive = "";

	private String orgMltOrgMltId = "";

	private String selectedOrgId = "";

	private String parentOrgName = "";

	private Organization organization;

	private String shortName = "";

	private String multipleUnit = "";

	private String streetAddress = "";

	private String state = "";

	private Collection states;

	@URL
	private String internetAddress = "";

	private String mlsLabFlag = "";

	private String cliaNum = "";

	private String pwsId = "";

	private List<OrganizationType> orgTypes;

	private String[] selectedTypes;

	private Timestamp lastupdated;

	private String commune = "";

	private String village = "";

	private String department = "";

	private List<Dictionary> departmentList;

	public OrganizationForm() {
		setFormName("organizationForm");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationLocalAbbreviation() {
		return organizationLocalAbbreviation;
	}

	public void setOrganizationLocalAbbreviation(String organizationLocalAbbreviation) {
		this.organizationLocalAbbreviation = organizationLocalAbbreviation;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMlsSentinelLabFlag() {
		return mlsSentinelLabFlag;
	}

	public void setMlsSentinelLabFlag(String mlsSentinelLabFlag) {
		this.mlsSentinelLabFlag = mlsSentinelLabFlag;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getOrgMltOrgMltId() {
		return orgMltOrgMltId;
	}

	public void setOrgMltOrgMltId(String orgMltOrgMltId) {
		this.orgMltOrgMltId = orgMltOrgMltId;
	}

	public String getSelectedOrgId() {
		return selectedOrgId;
	}

	public void setSelectedOrgId(String selectedOrgId) {
		this.selectedOrgId = selectedOrgId;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMultipleUnit() {
		return multipleUnit;
	}

	public void setMultipleUnit(String multipleUnit) {
		this.multipleUnit = multipleUnit;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection getStates() {
		return states;
	}

	public void setStates(Collection states) {
		this.states = states;
	}

	public String getInternetAddress() {
		return internetAddress;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	public String getMlsLabFlag() {
		return mlsLabFlag;
	}

	public void setMlsLabFlag(String mlsLabFlag) {
		this.mlsLabFlag = mlsLabFlag;
	}

	public String getCliaNum() {
		return cliaNum;
	}

	public void setCliaNum(String cliaNum) {
		this.cliaNum = cliaNum;
	}

	public String getPwsId() {
		return pwsId;
	}

	public void setPwsId(String pwsId) {
		this.pwsId = pwsId;
	}

	public List<OrganizationType> getOrgTypes() {
		return orgTypes;
	}

	public void setOrgTypes(List<OrganizationType> orgTypes) {
		this.orgTypes = orgTypes;
	}

	public String[] getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(String[] selectedTypes) {
		this.selectedTypes = selectedTypes;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Dictionary> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Dictionary> departmentList) {
		this.departmentList = departmentList;
	}
}
