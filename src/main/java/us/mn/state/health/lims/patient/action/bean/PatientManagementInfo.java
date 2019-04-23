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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.patient.action.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.sample.form.SamplePatientEntryForm;
import spring.mine.validation.annotations.OptionalNotBlank;
import spring.mine.validation.annotations.ValidDate;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.patient.action.IPatientUpdate.PatientUpdateStatus;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.daoimpl.PatientTypeDAOImpl;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public class PatientManagementInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ValidDate(relative = DateRelation.TODAY, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String currentDate;

	// TODO removable?
	private String patientLastUpdated;
	// TODO removable?
	private String personLastUpdated;

	private PatientUpdateStatus patientUpdateStatus;

	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String patientPK;
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String STnumber;
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String subjectNumber;
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String nationalId;
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String guid;

	@OptionalNotBlank(formFields = { Field.PatientNameRequired }, groups = {
			SamplePatientEntryForm.SamplePatientEntry.class })
	@Pattern(regexp = ValidationHelper.NAME_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String lastName;
	@OptionalNotBlank(formFields = { Field.PatientNameRequired }, groups = {
			SamplePatientEntryForm.SamplePatientEntry.class })
	@Pattern(regexp = ValidationHelper.NAME_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String firstName;
	@Pattern(regexp = ValidationHelper.NAME_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String aka;

	@Pattern(regexp = ValidationHelper.NAME_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String mothersName;
	@Size(max = 1)
	private String mothersInitial;

	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String streetAddress;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String city;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String commune;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String addressDepartment;

	@NotBlank(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	@Pattern(regexp = ValidationHelper.GENDER_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String gender;
	@NotBlank(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	@Size(max = 3, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	@Pattern(regexp = "^[0-9]*$", groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String age;
	@ValidDate(relative = DateRelation.PAST, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String birthDateForDisplay = "";

	@Pattern(regexp = ValidationHelper.ID_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String patientType = "";

	// for display
	private static List<PatientType> patientTypes;

	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String insuranceNumber;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String occupation;
	@Pattern(regexp = ValidationHelper.PHONE_REGEX, groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String phone;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String healthRegion;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String education;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String maritialStatus;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String nationality;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String healthDistrict;
	@SafeHtml(groups = { SamplePatientEntryForm.SamplePatientEntry.class })
	private String otherNationality;

	// for display
	private static List<IdValuePair> genders;
	private static List<Dictionary> addressDepartments;
	private static List<IdValuePair> healthRegions;
	private static List<IdValuePair> educationList;
	private static List<IdValuePair> maritialList;
	private static List<IdValuePair> nationalityList;

	private boolean readOnly = false;

	private List<PatientIdentity> patientIdentities;

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getPatientLastUpdated() {
		return patientLastUpdated;
	}

	public void setPatientLastUpdated(String patientLastUpdated) {
		this.patientLastUpdated = patientLastUpdated;
	}

	public String getPersonLastUpdated() {
		return personLastUpdated;
	}

	public void setPersonLastUpdated(String personLastUpdated) {
		this.personLastUpdated = personLastUpdated;
	}

	public String getPatientPK() {
		return patientPK;
	}

	public void setPatientPK(String patientPK) {
		this.patientPK = patientPK;
	}

	public String getSTnumber() {
		return STnumber;
	}

	public void setSTnumber(String sTnumber) {
		STnumber = sTnumber;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getMothersInitial() {
		return mothersInitial;
	}

	public void setMothersInitial(String mothersInitial) {
		this.mothersInitial = mothersInitial;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getBirthDateForDisplay() {
		return birthDateForDisplay;
	}

	public void setBirthDateForDisplay(String birthDateForDisplay) {
		this.birthDateForDisplay = birthDateForDisplay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setGenders(List<IdValuePair> genderList) {
		genders = genderList;
	}

	public List<IdValuePair> getGenders() {
		if (genders == null) {
			genders = DisplayListService.getList(ListType.GENDERS);
		}

		return genders;
	}

	public void setPatientTypes(List<PatientType> patientTypes) {
		PatientManagementInfo.patientTypes = patientTypes;
	}

	@SuppressWarnings("unchecked")
	public List<PatientType> getPatientTypes() {
		if (patientTypes == null) {
			PatientTypeDAO patientTypeDAOs = new PatientTypeDAOImpl();
			patientTypes = patientTypeDAOs.getAllPatientTypes();
		}
		return patientTypes;
	}

	public void setAddressDepartment(String addressDepartment) {
		this.addressDepartment = addressDepartment;
	}

	public String getAddressDepartment() {
		return addressDepartment;
	}

	public List<Dictionary> getAddressDepartments() {
		if (addressDepartments == null) {
			addressDepartments = new DictionaryDAOImpl().getDictionaryEntrysByCategoryAbbreviation("description",
					"haitiDepartment", true);
		}

		return addressDepartments;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<IdValuePair> getHealthRegions() {
		if (healthRegions == null) {
			healthRegions = DisplayListService.getList(ListType.PATIENT_HEALTH_REGIONS);
		}
		return healthRegions;
	}

	public void setHealthRegions(List<IdValuePair> healthRegions) {
		PatientManagementInfo.healthRegions = healthRegions;
	}

	public String getHealthRegion() {
		return healthRegion;
	}

	public void setHealthRegion(String healthRegion) {
		this.healthRegion = healthRegion;
	}

	public String getHealthDistrict() {
		return healthDistrict;
	}

	public void setHealthDistrict(String healthDistrict) {
		this.healthDistrict = healthDistrict;
	}

	public List<IdValuePair> getEducationList() {
		if (educationList == null) {
			educationList = DisplayListService.getList(ListType.PATIENT_EDUCATION);
		}
		return educationList;
	}

	public void setEducationList(List<IdValuePair> educationList) {
		PatientManagementInfo.educationList = educationList;
	}

	public List<IdValuePair> getMaritialList() {
		if (maritialList == null) {
			maritialList = DisplayListService.getList(ListType.PATIENT_MARITAL_STATUS);
		}
		return maritialList;
	}

	public void setMaritialList(List<IdValuePair> maritialList) {
		PatientManagementInfo.maritialList = maritialList;
	}

	public List<IdValuePair> getNationalityList() {
		if (nationalityList == null) {
			nationalityList = DisplayListService.getList(ListType.PATIENT_NATIONALITY);
		}
		return nationalityList;
	}

	public void setNationalityList(List<IdValuePair> nationalityList) {
		PatientManagementInfo.nationalityList = nationalityList;
	}

	public String getOtherNationality() {
		return otherNationality;
	}

	public void setOtherNationality(String otherNationality) {
		this.otherNationality = otherNationality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String naionality) {
		nationality = naionality;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public PatientUpdateStatus getPatientUpdateStatus() {
		return patientUpdateStatus;
	}

	public void setPatientUpdateStatus(PatientUpdateStatus patientUpdateStatus) {
		this.patientUpdateStatus = patientUpdateStatus;
	}

	public List<PatientIdentity> getPatientIdentities() {
		return patientIdentities;
	}

	public void setPatientIdentities(List<PatientIdentity> patientIdentities) {
		this.patientIdentities = patientIdentities;
	}
}
