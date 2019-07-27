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
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.patient.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.validation.Errors;

import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.address.dao.AddressPartDAO;
import org.openelisglobal.address.dao.PersonAddressDAO;
import org.openelisglobal.address.daoimpl.AddressPartDAOImpl;
import org.openelisglobal.address.daoimpl.PersonAddressDAOImpl;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.PersonAddress;
import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.hibernate.HibernateUtil;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.dao.PatientDAO;
import org.openelisglobal.patient.daoimpl.PatientDAOImpl;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.dao.PatientIdentityDAO;
import org.openelisglobal.patientidentity.daoimpl.PatientIdentityDAOImpl;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.patientidentitytype.util.PatientIdentityTypeMap;
import org.openelisglobal.patienttype.dao.PatientPatientTypeDAO;
import org.openelisglobal.patienttype.daoimpl.PatientPatientTypeDAOImpl;
import org.openelisglobal.patienttype.util.PatientTypeMap;
import org.openelisglobal.patienttype.valueholder.PatientPatientType;
import org.openelisglobal.person.dao.PersonDAO;
import org.openelisglobal.person.daoimpl.PersonDAOImpl;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.sample.dao.SearchResultsDAO;
import org.openelisglobal.sample.daoimpl.SearchResultsDAOImp;

public class PatientManagementUpdateAction extends BaseAction implements IPatientUpdate {

	protected Patient patient;
	protected Person person;
	private List<PatientIdentity> patientIdentities;
	private String patientID = "";
	private static PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
	private static PatientDAO patientDAO = new PatientDAOImpl();
	private static PersonAddressDAO personAddressDAO = new PersonAddressDAOImpl();
	private static final String AMBIGUOUS_DATE_CHAR = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.AmbiguousDateHolder);
	private static final String AMBIGUOUS_DATE_HOLDER = AMBIGUOUS_DATE_CHAR + AMBIGUOUS_DATE_CHAR;
	protected PatientUpdateStatus patientUpdateStatus = PatientUpdateStatus.NO_ACTION;

	private static String ADDRESS_PART_VILLAGE_ID;
	private static String ADDRESS_PART_COMMUNE_ID;
	private static String ADDRESS_PART_DEPT_ID;

	public static enum PatientUpdateStatus {
		NO_ACTION, UPDATE, ADD
	}

	static {
		AddressPartDAO addressPartDAO = new AddressPartDAOImpl();
		List<AddressPart> partList = addressPartDAO.getAll();

		for (AddressPart addressPart : partList) {
			if ("department".equals(addressPart.getPartName())) {
				ADDRESS_PART_DEPT_ID = addressPart.getId();
			} else if ("commune".equals(addressPart.getPartName())) {
				ADDRESS_PART_COMMUNE_ID = addressPart.getId();
			} else if ("village".equals(addressPart.getPartName())) {
				ADDRESS_PART_VILLAGE_ID = addressPart.getId();
			}
		}
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;
		PatientManagementInfo patientInfo = (PatientManagementInfo) dynaForm.get("patientProperties");
		setPatientUpdateStatus(patientInfo);

		if (patientUpdateStatus != PatientUpdateStatus.NO_ACTION) {

			Errors errors;

			errors = preparePatientData(mapping, request, patientInfo);

			if (errors.hasErrors()) {
				request.setAttribute(Globals.ERROR_KEY, errors);
				return mapping.findForward(FWD_FAIL);
			}

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {

				persistPatientData(patientInfo);

				tx.commit();

			} catch (LIMSRuntimeException lre) {
				tx.rollback();
				errors = new BaseErrors();

				if (lre.getException() instanceof StaleObjectStateException) {
					errors.reject("errors.OptimisticLockException", null, null);
				} else {
					lre.printStackTrace();
					errors.reject("errors.UpdateException", null, null);
				}

				// saveErrors(request, errors);
				request.setAttribute(Globals.ERROR_KEY, errors);
				request.setAttribute(ALLOW_EDITS_KEY, "false");
				return mapping.findForward(FWD_FAIL);

			} finally {
				HibernateUtil.closeSession();
			}

			dynaForm.initialize(mapping);

		}

		setSuccessFlag(request, forward);

		return mapping.findForward(forward);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.openelisglobal.patient.action.IPatientUpdate#preparePatientData
	 * (org.apache.struts.action.ActionMapping,
	 * javax.servlet.http.HttpServletRequest,
	 * org.openelisglobal.common.action.BaseActionForm)
	 */
	public Errors preparePatientData(ActionMapping mapping, HttpServletRequest request,
			PatientManagementInfo patientInfo)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (currentUserId == null) {
			currentUserId = getSysUserId(request);
		}

		Errors errors = validatePatientInfo(patientInfo);
		if (errors.hasErrors()) {
			return errors;
		}

		initMembers();

		if (patientUpdateStatus == PatientUpdateStatus.UPDATE) {
			loadForUpdate(patientInfo);
		}

		copyFormBeanToValueHolders(patientInfo);

		setSystemUserID();

		setLastUpdatedTimeStamps(patientInfo);

		return errors;
	}

	private Errors validatePatientInfo(PatientManagementInfo patientInfo) {
		Errors errors = new BaseErrors();
		if (ConfigurationProperties.getInstance()
				.isPropertyValueEqual(ConfigurationProperties.Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS, "false")) {
			String newSTNumber = GenericValidator.isBlankOrNull(patientInfo.getSTnumber()) ? null
					: patientInfo.getSTnumber();
			String newSubjectNumber = GenericValidator.isBlankOrNull(patientInfo.getSubjectNumber()) ? null
					: patientInfo.getSubjectNumber();
			String newNationalId = GenericValidator.isBlankOrNull(patientInfo.getNationalId()) ? null
					: patientInfo.getNationalId();

			SearchResultsDAO search = new SearchResultsDAOImp();
			List<PatientSearchResults> results = search.getSearchResults(null, null, newSTNumber, newSubjectNumber,
					newNationalId, null, null, null);

			if (!results.isEmpty()) {

				for (PatientSearchResults result : results) {
					if (!result.getPatientID().equals(patientInfo.getPatientPK())) {
						if (newSTNumber != null && newSTNumber.equals(result.getSTNumber())) {
							errors.reject("error.duplicate.STNumber", null, null);
						}
						if (newSubjectNumber != null && newSubjectNumber.equals(result.getSubjectNumber())) {
							errors.reject("error.duplicate.subjectNumber", null, null);
						}
						if (newNationalId != null && newNationalId.equals(result.getNationalId())) {
							errors.reject("error.duplicate.nationalId", null, null);
						}
					}
				}
			}
		}

		validateBirthdateFormat(patientInfo, errors);

		return errors;
	}

	private void validateBirthdateFormat(PatientManagementInfo patientInfo, Errors errors) {
		String birthDate = patientInfo.getBirthDateForDisplay();
		boolean validBirthDateFormat = true;

		if (!GenericValidator.isBlankOrNull(birthDate)) {
			validBirthDateFormat = birthDate.length() == 10;
			// the regex matches ambiguous day and month or ambiguous day or completely
			// formed date
			if (validBirthDateFormat) {
				validBirthDateFormat = birthDate.matches("(((" + AMBIGUOUS_DATE_HOLDER + "|\\d{2})/\\d{2})|"
						+ AMBIGUOUS_DATE_HOLDER + "/(" + AMBIGUOUS_DATE_HOLDER + "|\\d{2}))/\\d{4}");
			}

			if (!validBirthDateFormat) {
				errors.reject("error.birthdate.format", null, null);
			}
		}
	}

	private void setLastUpdatedTimeStamps(PatientManagementInfo patientInfo) {
		String patientUpdate = patientInfo.getPatientLastUpdated();
		if (!GenericValidator.isBlankOrNull(patientUpdate)) {
			Timestamp timeStamp = Timestamp.valueOf(patientUpdate);
			patient.setLastupdated(timeStamp);
		}

		String personUpdate = patientInfo.getPersonLastUpdated();
		if (!GenericValidator.isBlankOrNull(personUpdate)) {
			Timestamp timeStamp = Timestamp.valueOf(personUpdate);
			person.setLastupdated(timeStamp);
		}
	}

	private void initMembers() {
		patient = new Patient();
		person = new Person();
		patientIdentities = new ArrayList<>();
	}

	private void loadForUpdate(PatientManagementInfo patientInfo) {

		patientID = patientInfo.getPatientPK();
		patient = patientDAO.readPatient(patientID);
		person = patient.getPerson();

		patientIdentities = identityDAO.getPatientIdentitiesForPatient(patient.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.openelisglobal.patient.action.IPatientUpdate#setPatientUpdateStatus
	 * (org.openelisglobal.common.action.BaseActionForm)
	 */
	@Override
	public void setPatientUpdateStatus(PatientManagementInfo patientInfo) {

		String status = patientInfo.getPatientProcessingStatus();

		if ("noAction".equals(status)) {
			patientUpdateStatus = PatientUpdateStatus.NO_ACTION;
		} else if ("update".equals(status)) {
			patientUpdateStatus = PatientUpdateStatus.UPDATE;
		} else {
			patientUpdateStatus = PatientUpdateStatus.ADD;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.openelisglobal.patient.action.IPatientUpdate#getPatientUpdateStatus
	 * ()
	 */
	@Override
	public PatientUpdateStatus getPatientUpdateStatus() {
		return patientUpdateStatus;
	}

	private void copyFormBeanToValueHolders(PatientManagementInfo patientInfo)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		PropertyUtils.copyProperties(patient, patientInfo);
		PropertyUtils.copyProperties(person, patientInfo);
	}

	private void setSystemUserID() {
		patient.setSysUserId(currentUserId);
		person.setSysUserId(currentUserId);

		for (PatientIdentity identity : patientIdentities) {
			identity.setSysUserId(currentUserId);
		}
	}

	@Override
	public void persistPatientData(PatientManagementInfo patientInfo) throws LIMSRuntimeException {
		PersonDAO personDAO = new PersonDAOImpl();

		if (patientUpdateStatus == PatientUpdateStatus.ADD) {
			personDAO.insertData(person);
		} else if (patientUpdateStatus == PatientUpdateStatus.UPDATE) {
			personDAO.updateData(person);
		}
		patient.setPerson(person);

		if (patientUpdateStatus == PatientUpdateStatus.ADD) {
			patientDAO.insertData(patient);
		} else if (patientUpdateStatus == PatientUpdateStatus.UPDATE) {
			patientDAO.updateData(patient);
		}

		persistPatientRelatedInformation(patientInfo);
		patientID = patient.getId();
	}

	protected void persistPatientRelatedInformation(PatientManagementInfo patientInfo) {
		persistIdentityTypes(patientInfo);
		persistExtraPatientAddressInfo(patientInfo);
		persistPatientType(patientInfo);
	}

	protected void persistIdentityTypes(PatientManagementInfo patientInfo) {

		persistIdentityType(patientInfo.getSTnumber(), "ST");
		persistIdentityType(patientInfo.getMothersName(), "MOTHER");
		persistIdentityType(patientInfo.getAka(), "AKA");
		persistIdentityType(patientInfo.getInsuranceNumber(), "INSURANCE");
		persistIdentityType(patientInfo.getOccupation(), "OCCUPATION");
		persistIdentityType(patientInfo.getSubjectNumber(), "SUBJECT");
		persistIdentityType(patientInfo.getMothersInitial(), "MOTHERS_INITIAL");
		persistIdentityType(patientInfo.getEducation(), "EDUCATION");
		persistIdentityType(patientInfo.getMaritialStatus(), "MARITIAL");
		persistIdentityType(patientInfo.getNationality(), "NATIONALITY");
		persistIdentityType(patientInfo.getHealthDistrict(), "HEALTH DISTRICT");
		persistIdentityType(patientInfo.getHealthRegion(), "HEALTH REGION");
		persistIdentityType(patientInfo.getOtherNationality(), "OTHER NATIONALITY");
	}

	private void persistExtraPatientAddressInfo(PatientManagementInfo patientInfo) {
		PersonAddress village = null;
		PersonAddress commune = null;
		PersonAddress dept = null;
		List<PersonAddress> personAddressList = personAddressDAO.getAddressPartsByPersonId(person.getId());

		for (PersonAddress address : personAddressList) {
			if (address.getAddressPartId().equals(ADDRESS_PART_COMMUNE_ID)) {
				commune = address;
				commune.setValue(patientInfo.getCommune());
				commune.setSysUserId(currentUserId);
				personAddressDAO.update(commune);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_VILLAGE_ID)) {
				village = address;
				village.setValue(patientInfo.getCity());
				village.setSysUserId(currentUserId);
				personAddressDAO.update(village);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_DEPT_ID)) {
				dept = address;
				if (!GenericValidator.isBlankOrNull(patientInfo.getAddressDepartment())
						&& !patientInfo.getAddressDepartment().equals("0")) {
					dept.setValue(patientInfo.getAddressDepartment());
					dept.setType("D");
					dept.setSysUserId(currentUserId);
					personAddressDAO.update(dept);
				}
			}
		}

		if (commune == null) {
			insertNewPatientInfo(ADDRESS_PART_COMMUNE_ID, patientInfo.getCommune(), "T");
		}

		if (village == null) {
			insertNewPatientInfo(ADDRESS_PART_VILLAGE_ID, patientInfo.getCity(), "T");
		}

		if (dept == null && patientInfo.getAddressDepartment() != null
				&& !patientInfo.getAddressDepartment().equals("0")) {
			insertNewPatientInfo(ADDRESS_PART_DEPT_ID, patientInfo.getAddressDepartment(), "D");
		}

	}

	private void insertNewPatientInfo(String partId, String value, String type) {
		PersonAddress address;
		address = new PersonAddress();
		address.setPersonId(person.getId());
		address.setAddressPartId(partId);
		address.setType(type);
		address.setValue(value);
		address.setSysUserId(currentUserId);
		personAddressDAO.insert(address);
	}

	public void persistIdentityType(String paramValue, String type) throws LIMSRuntimeException {

		Boolean newIdentityNeeded = true;
		String typeID = PatientIdentityTypeMap.getInstance().getIDForType(type);

		if (patientUpdateStatus == PatientUpdateStatus.UPDATE) {

			for (PatientIdentity listIdentity : patientIdentities) {
				if (listIdentity.getIdentityTypeId().equals(typeID)) {

					newIdentityNeeded = false;

					if ((listIdentity.getIdentityData() == null && !GenericValidator.isBlankOrNull(paramValue))
							|| (listIdentity.getIdentityData() != null
									&& !listIdentity.getIdentityData().equals(paramValue))) {
						listIdentity.setIdentityData(paramValue);
						identityDAO.updateData(listIdentity);
					}

					break;
				}
			}
		}

		if (newIdentityNeeded && !GenericValidator.isBlankOrNull(paramValue)) {
			// either a new patient or a new identity item
			PatientIdentity identity = new PatientIdentity();
			identity.setPatientId(patient.getId());
			identity.setIdentityTypeId(typeID);
			identity.setSysUserId(currentUserId);
			identity.setIdentityData(paramValue);
			identity.setLastupdatedFields();
			identityDAO.insertData(identity);
		}
	}

	protected void persistPatientType(PatientManagementInfo patientInfo) {

		PatientPatientTypeDAO patientPatientTypeDAO = new PatientPatientTypeDAOImpl();

		String typeName = null;

		try {
			typeName = patientInfo.getPatientType();
		} catch (Exception ignored) {
		}

		if (!GenericValidator.isBlankOrNull(typeName) && !"0".equals(typeName)) {
			String typeID = PatientTypeMap.getInstance().getIDForType(typeName);

			PatientPatientType patientPatientType = patientPatientTypeDAO
					.getPatientPatientTypeForPatient(patient.getId());

			if (patientPatientType == null) {
				patientPatientType = new PatientPatientType();
				patientPatientType.setSysUserId(currentUserId);
				patientPatientType.setPatientId(patient.getId());
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeDAO.insertData(patientPatientType);
			} else {
				patientPatientType.setSysUserId(currentUserId);
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeDAO.updateData(patientPatientType);
			}
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "patient.management.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "patient.management.title";
	}

	@Override
	public String getPatientId(SamplePatientEntryForm form) {
		return GenericValidator.isBlankOrNull(patientID) ? form.getPatientProperties().getPatientPK() : patientID;
	}

	@Override
	public Errors preparePatientData(HttpServletRequest request, PatientManagementInfo patientInfo)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (currentUserId == null) {
			currentUserId = getSysUserId(request);
		}

		Errors errors = validatePatientInfo(patientInfo);
		if (errors.hasErrors()) {
			return errors;
		}

		initMembers();

		if (patientUpdateStatus == PatientUpdateStatus.UPDATE) {
			loadForUpdate(patientInfo);
		}

		copyFormBeanToValueHolders(patientInfo);

		setSystemUserID();

		setLastUpdatedTimeStamps(patientInfo);

		return errors;
	}
}