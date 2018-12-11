package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.organization.valueholder.Organization;

public class OrganizationForm extends BaseForm {
  private String id = "";

  private String organizationLocalAbbreviation = "";

  private String organizationName = "";

  private String city = "";

  private String zipCode = "";

  private String mlsSentinelLabFlag = "";

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

  private String internetAddress = "";

  private String mlsLabFlag = "";

  private String cliaNum = "";

  private String pwsId = "";

  private Collection orgTypes;

  private String[] selectedTypes;

  private Timestamp lastupdated;

  private String commune = "";

  private String village = "";

  private String department = "";

  private Collection departmentList;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrganizationLocalAbbreviation() {
    return this.organizationLocalAbbreviation;
  }

  public void setOrganizationLocalAbbreviation(String organizationLocalAbbreviation) {
    this.organizationLocalAbbreviation = organizationLocalAbbreviation;
  }

  public String getOrganizationName() {
    return this.organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getMlsSentinelLabFlag() {
    return this.mlsSentinelLabFlag;
  }

  public void setMlsSentinelLabFlag(String mlsSentinelLabFlag) {
    this.mlsSentinelLabFlag = mlsSentinelLabFlag;
  }

  public String getIsActive() {
    return this.isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  public String getOrgMltOrgMltId() {
    return this.orgMltOrgMltId;
  }

  public void setOrgMltOrgMltId(String orgMltOrgMltId) {
    this.orgMltOrgMltId = orgMltOrgMltId;
  }

  public String getSelectedOrgId() {
    return this.selectedOrgId;
  }

  public void setSelectedOrgId(String selectedOrgId) {
    this.selectedOrgId = selectedOrgId;
  }

  public String getParentOrgName() {
    return this.parentOrgName;
  }

  public void setParentOrgName(String parentOrgName) {
    this.parentOrgName = parentOrgName;
  }

  public Organization getOrganization() {
    return this.organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public String getShortName() {
    return this.shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getMultipleUnit() {
    return this.multipleUnit;
  }

  public void setMultipleUnit(String multipleUnit) {
    this.multipleUnit = multipleUnit;
  }

  public String getStreetAddress() {
    return this.streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Collection getStates() {
    return this.states;
  }

  public void setStates(Collection states) {
    this.states = states;
  }

  public String getInternetAddress() {
    return this.internetAddress;
  }

  public void setInternetAddress(String internetAddress) {
    this.internetAddress = internetAddress;
  }

  public String getMlsLabFlag() {
    return this.mlsLabFlag;
  }

  public void setMlsLabFlag(String mlsLabFlag) {
    this.mlsLabFlag = mlsLabFlag;
  }

  public String getCliaNum() {
    return this.cliaNum;
  }

  public void setCliaNum(String cliaNum) {
    this.cliaNum = cliaNum;
  }

  public String getPwsId() {
    return this.pwsId;
  }

  public void setPwsId(String pwsId) {
    this.pwsId = pwsId;
  }

  public Collection getOrgTypes() {
    return this.orgTypes;
  }

  public void setOrgTypes(Collection orgTypes) {
    this.orgTypes = orgTypes;
  }

  public String[] getSelectedTypes() {
    return this.selectedTypes;
  }

  public void setSelectedTypes(String[] selectedTypes) {
    this.selectedTypes = selectedTypes;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getCommune() {
    return this.commune;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public String getVillage() {
    return this.village;
  }

  public void setVillage(String village) {
    this.village = village;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Collection getDepartmentList() {
    return this.departmentList;
  }

  public void setDepartmentList(Collection departmentList) {
    this.departmentList = departmentList;
  }
}
