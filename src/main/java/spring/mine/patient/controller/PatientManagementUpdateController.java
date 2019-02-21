package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.dao.PersonAddressDAO;
import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.daoimpl.PersonAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.daoimpl.PatientPatientTypeDAOImpl;
import us.mn.state.health.lims.patienttype.util.PatientTypeMap;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.dao.SearchResultsDAO;
import us.mn.state.health.lims.sample.daoimpl.SearchResultsDAOImp;

@Controller
public class PatientManagementUpdateController extends PatientManagementBaseController {

	private static PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
	private static PatientDAO patientDAO = new PatientDAOImpl();
	private static PersonAddressDAO personAddressDAO = new PersonAddressDAOImpl();
	private static final String AMBIGUOUS_DATE_CHAR = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.AmbiguousDateHolder);
	private static final String AMBIGUOUS_DATE_HOLDER = AMBIGUOUS_DATE_CHAR + AMBIGUOUS_DATE_CHAR;

	private static String ADDRESS_PART_VILLAGE_ID;
	private static String ADDRESS_PART_COMMUNE_ID;
	private static String ADDRESS_PART_DEPT_ID;

	public enum PatientUpdateStatus {
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

	@RequestMapping(value = "/PatientManagementUpdate", method = RequestMethod.POST)
	public ModelAndView showPatientManagementUpdate(@ModelAttribute("form") SamplePatientEntryForm form,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		Errors errors = new BaseErrors();
		

		form.setPatientSearch(new PatientSearch());
		PatientManagementInfo patientInfo = form.getPatientProperties();

		Patient patient = new Patient();
		setPatientUpdateStatus(patientInfo);

		if (patientInfo.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION) {

			preparePatientData(result, request, patientInfo, patient);

			if (result.hasErrors()) {
				saveErrors(errors);
				return findForward(FWD_FAIL, form);
			}

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				persistPatientData(patientInfo, patient);
				tx.commit();
			} catch (LIMSRuntimeException lre) {
				tx.rollback();

				if (lre.getException() instanceof StaleObjectStateException) {
					result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
				} else {
					lre.printStackTrace();
					result.reject("errors.UpdateException", "errors.UpdateException");
				}
				saveErrors(result);
				request.setAttribute(ALLOW_EDITS_KEY, "false");
				if (result.hasErrors()) {
					saveErrors(result);
					return findForward(FWD_FAIL, form);
				}

			} finally {
				HibernateUtil.closeSession();
			}

		}

		setSuccessFlag(request, forward);

		return findForward(forward, form);
	}

	public void preparePatientData(Errors errors, HttpServletRequest request, PatientManagementInfo patientInfo,
			Patient patient) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		validatePatientInfo(errors, patientInfo);
		if (errors.hasErrors()) {
			return;
		}

		initMembers(patient);
		patientInfo.setPatientIdentities(new ArrayList<PatientIdentity>());

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			patient = loadForUpdate(patientInfo);
		}

		copyFormBeanToValueHolders(patientInfo, patient);

		setSystemUserID(patientInfo, patient);

		setLastUpdatedTimeStamps(patientInfo, patient);

	}

	private void validatePatientInfo(Errors errors, PatientManagementInfo patientInfo) {
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
							errors.reject("error.duplicate.STNumber", "error.duplicate.STNumber");
						}
						if (newSubjectNumber != null && newSubjectNumber.equals(result.getSubjectNumber())) {
							errors.reject("error.duplicate.subjectNumber", "error.duplicate.subjectNumber");
						}
						if (newNationalId != null && newNationalId.equals(result.getNationalId())) {
							errors.reject("error.duplicate.nationalId", "error.duplicate.nationalId");
						}
					}
				}
			}
		}

		validateBirthdateFormat(patientInfo, errors);

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
				errors.reject("error.birthdate.format", "error.birthdate.format");
			}
		}
	}

	private void setLastUpdatedTimeStamps(PatientManagementInfo patientInfo, Patient patient) {
		String patientUpdate = patientInfo.getPatientLastUpdated();
		if (!GenericValidator.isBlankOrNull(patientUpdate)) {
			Timestamp timeStamp = Timestamp.valueOf(patientUpdate);
			patient.setLastupdated(timeStamp);
		}

		String personUpdate = patientInfo.getPersonLastUpdated();
		if (!GenericValidator.isBlankOrNull(personUpdate)) {
			Timestamp timeStamp = Timestamp.valueOf(personUpdate);
			patient.getPerson().setLastupdated(timeStamp);
		}
	}

	private void initMembers(Patient patient) {
		patient.setPerson(new Person());
	}

	private Patient loadForUpdate(PatientManagementInfo patientInfo) {
		Patient patient = patientDAO.readPatient(patientInfo.getPatientPK());
		patientInfo.setPatientIdentities(identityDAO.getPatientIdentitiesForPatient(patient.getId()));
		return patient;
	}

	public void setPatientUpdateStatus(PatientManagementInfo patientInfo) {

		String status = patientInfo.getPatientProcessingStatus();

		if ("noAction".equals(status)) {
			patientInfo.setPatientUpdateStatus(PatientUpdateStatus.NO_ACTION);
		} else if ("update".equals(status)) {
			patientInfo.setPatientUpdateStatus(PatientUpdateStatus.UPDATE);
		} else {
			patientInfo.setPatientUpdateStatus(PatientUpdateStatus.ADD);
		}
	}

	private void copyFormBeanToValueHolders(PatientManagementInfo patientInfo, Patient patient)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		PropertyUtils.copyProperties(patient, patientInfo);
		PropertyUtils.copyProperties(patient.getPerson(), patientInfo);
	}

	public void persistPatientData(PatientManagementInfo patientInfo, Patient patient) throws LIMSRuntimeException {
		PersonDAO personDAO = new PersonDAOImpl();

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			personDAO.insertData(patient.getPerson());
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			personDAO.updateData(patient.getPerson());
		}
		patient.setPerson(patient.getPerson());

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			patientDAO.insertData(patient);
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			patientDAO.updateData(patient);
		}

		persistPatientRelatedInformation(patientInfo, patient);
	}

	protected void persistPatientRelatedInformation(PatientManagementInfo patientInfo, Patient patient) {
		persistIdentityTypes(patientInfo, patient);
		persistExtraPatientAddressInfo(patientInfo, patient);
		persistPatientType(patientInfo, patient);
	}

	protected void persistIdentityTypes(PatientManagementInfo patientInfo, Patient patient) {

		persistIdentityType(patientInfo.getSTnumber(), "ST", patientInfo, patient);
		persistIdentityType(patientInfo.getMothersName(), "MOTHER", patientInfo, patient);
		persistIdentityType(patientInfo.getAka(), "AKA", patientInfo, patient);
		persistIdentityType(patientInfo.getInsuranceNumber(), "INSURANCE", patientInfo, patient);
		persistIdentityType(patientInfo.getOccupation(), "OCCUPATION", patientInfo, patient);
		persistIdentityType(patientInfo.getSubjectNumber(), "SUBJECT", patientInfo, patient);
		persistIdentityType(patientInfo.getMothersInitial(), "MOTHERS_INITIAL", patientInfo, patient);
		persistIdentityType(patientInfo.getEducation(), "EDUCATION", patientInfo, patient);
		persistIdentityType(patientInfo.getMaritialStatus(), "MARITIAL", patientInfo, patient);
		persistIdentityType(patientInfo.getNationality(), "NATIONALITY", patientInfo, patient);
		persistIdentityType(patientInfo.getHealthDistrict(), "HEALTH DISTRICT", patientInfo, patient);
		persistIdentityType(patientInfo.getHealthRegion(), "HEALTH REGION", patientInfo, patient);
		persistIdentityType(patientInfo.getOtherNationality(), "OTHER NATIONALITY", patientInfo, patient);
	}

	private void persistExtraPatientAddressInfo(PatientManagementInfo patientInfo, Patient patient) {
		PersonAddress village = null;
		PersonAddress commune = null;
		PersonAddress dept = null;
		List<PersonAddress> personAddressList = personAddressDAO.getAddressPartsByPersonId(patient.getPerson().getId());

		for (PersonAddress address : personAddressList) {
			if (address.getAddressPartId().equals(ADDRESS_PART_COMMUNE_ID)) {
				commune = address;
				commune.setValue(patientInfo.getCommune());
				commune.setSysUserId(getSysUserId(request));
				personAddressDAO.update(commune);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_VILLAGE_ID)) {
				village = address;
				village.setValue(patientInfo.getCity());
				village.setSysUserId(getSysUserId(request));
				personAddressDAO.update(village);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_DEPT_ID)) {
				dept = address;
				if (!GenericValidator.isBlankOrNull(patientInfo.getAddressDepartment())
						&& !patientInfo.getAddressDepartment().equals("0")) {
					dept.setValue(patientInfo.getAddressDepartment());
					dept.setType("D");
					dept.setSysUserId(getSysUserId(request));
					personAddressDAO.update(dept);
				}
			}
		}

		if (commune == null) {
			insertNewPatientInfo(ADDRESS_PART_COMMUNE_ID, patientInfo.getCommune(), "T", patient);
		}

		if (village == null) {
			insertNewPatientInfo(ADDRESS_PART_VILLAGE_ID, patientInfo.getCity(), "T", patient);
		}

		if (dept == null && patientInfo.getAddressDepartment() != null
				&& !patientInfo.getAddressDepartment().equals("0")) {
			insertNewPatientInfo(ADDRESS_PART_DEPT_ID, patientInfo.getAddressDepartment(), "D", patient);
		}

	}

	private void setSystemUserID(PatientManagementInfo patientInfo, Patient patient) {
		patient.setSysUserId(getSysUserId(request));
		patient.getPerson().setSysUserId(getSysUserId(request));

		for (PatientIdentity identity : patientInfo.getPatientIdentities()) {
			identity.setSysUserId(getSysUserId(request));
		}
	}

	private void insertNewPatientInfo(String partId, String value, String type, Patient patient) {
		PersonAddress address;
		address = new PersonAddress();
		address.setPersonId(patient.getPerson().getId());
		address.setAddressPartId(partId);
		address.setType(type);
		address.setValue(value);
		address.setSysUserId(getSysUserId(request));
		personAddressDAO.insert(address);
	}

	public void persistIdentityType(String paramValue, String type, PatientManagementInfo patientInfo, Patient patient)
			throws LIMSRuntimeException {

		Boolean newIdentityNeeded = true;
		String typeID = PatientIdentityTypeMap.getInstance().getIDForType(type);

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {

			for (PatientIdentity listIdentity : patientInfo.getPatientIdentities()) {
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
			identity.setSysUserId(getSysUserId(request));
			identity.setIdentityData(paramValue);
			identity.setLastupdatedFields();
			identityDAO.insertData(identity);
		}
	}

	protected void persistPatientType(PatientManagementInfo patientInfo, Patient patient) {

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
				patientPatientType.setSysUserId(getSysUserId(request));
				patientPatientType.setPatientId(patient.getId());
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeDAO.insertData(patientPatientType);
			} else {
				patientPatientType.setSysUserId(getSysUserId(request));
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
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("patientManagementDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("patientManagementDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}
}
