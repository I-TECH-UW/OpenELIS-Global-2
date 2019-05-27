package spring.mine.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.controller.BaseController;
import spring.mine.sample.form.SamplePatientEntryForm;
import spring.mine.sample.validator.SamplePatientEntryFormValidator;
import spring.service.address.AddressPartService;
import spring.service.address.PersonAddressService;
import spring.service.patient.PatientService;
import spring.service.patientidentity.PatientIdentityService;
import spring.service.patienttype.PatientPatientTypeService;
import spring.service.person.PersonService;
import spring.service.search.SearchResultsService;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.patient.action.IPatientUpdate.PatientUpdateStatus;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.patienttype.util.PatientTypeMap;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.person.valueholder.Person;

@Controller
public class PatientManagementController extends BaseController {

	@Autowired
	SamplePatientEntryFormValidator formValidator;

	private static final String AMBIGUOUS_DATE_CHAR = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.AmbiguousDateHolder);
	private static final String AMBIGUOUS_DATE_HOLDER = AMBIGUOUS_DATE_CHAR + AMBIGUOUS_DATE_CHAR;

	private static String ADDRESS_PART_VILLAGE_ID;
	private static String ADDRESS_PART_COMMUNE_ID;
	private static String ADDRESS_PART_DEPT_ID;

	@Autowired
	AddressPartService addressPartService;
	@Autowired
	PatientPatientTypeService patientPatientTypeService;
	@Autowired
	PatientIdentityService patientIdentityService;
	@Autowired
	PatientService patientService;
	@Autowired
	PersonService personService;
	@Autowired
	PersonAddressService personAddressService;
	@Autowired
	SearchResultsService searchService;

	@PostConstruct
	private void initialize() {
		List<AddressPart> partList = addressPartService.getAll();
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

	@RequestMapping(value = "/PatientManagement", method = RequestMethod.GET)
	public ModelAndView showPatientManagement(HttpServletRequest request) {
		SamplePatientEntryForm form = new SamplePatientEntryForm();

		cleanAndSetupRequestForm(form, request);
		addFlashMsgsToRequest(request);

		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/PatientManagement", method = RequestMethod.POST)
	public ModelAndView showPatientManagementUpdate(HttpServletRequest request,
			@ModelAttribute("form") @Valid SamplePatientEntryForm form, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		form.setPatientSearch(new PatientSearch());
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		PatientManagementInfo patientInfo = form.getPatientProperties();

		Patient patient = new Patient();
		setPatientUpdateStatus(patientInfo);

		if (patientInfo.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION) {

			preparePatientData(result, request, patientInfo, patient);
			if (result.hasErrors()) {
				saveErrors(result);
				return findForward(FWD_FAIL_INSERT, form);
			}
			try {
				persistPatientData(patientInfo, patient);
			} catch (LIMSRuntimeException lre) {

				if (lre.getException() instanceof StaleObjectStateException) {
					result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
				} else {
					lre.printStackTrace();
					result.reject("errors.UpdateException", "errors.UpdateException");
				}
				request.setAttribute(ALLOW_EDITS_KEY, "false");
				if (result.hasErrors()) {
					saveErrors(result);
					return findForward(FWD_FAIL_INSERT, form);
				}

			}

		}
		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	public void cleanAndSetupRequestForm(SamplePatientEntryForm form, HttpServletRequest request) {
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);
		form.setPatientProperties(new PatientManagementInfo());
		form.setPatientSearch(new PatientSearch());
		form.getPatientProperties().setPatientUpdateStatus(PatientUpdateStatus.ADD);
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
			Patient dbPatient = loadForUpdate(patientInfo);
			PropertyUtils.copyProperties(patient, dbPatient);
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

			List<PatientSearchResults> results = searchService.getSearchResults(null, null, newSTNumber,
					newSubjectNumber, newNationalId, null, null, null);

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
		Patient patient = patientService.get(patientInfo.getPatientPK());
		patientInfo.setPatientIdentities(patientIdentityService.getPatientIdentitiesForPatient(patient.getId()));
		return patient;
	}

	public void setPatientUpdateStatus(PatientManagementInfo patientInfo) {

		/*
		 * String status = patientInfo.getPatientProcessingStatus();
		 *
		 * if ("noAction".equals(status)) {
		 * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.NO_ACTION); } else if
		 * ("update".equals(status)) {
		 * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.UPDATE); } else {
		 * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.ADD); }
		 */
	}

	private void copyFormBeanToValueHolders(PatientManagementInfo patientInfo, Patient patient)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		PropertyUtils.copyProperties(patient, patientInfo);
		PropertyUtils.copyProperties(patient.getPerson(), patientInfo);
	}

	@Transactional
	public void persistPatientData(PatientManagementInfo patientInfo, Patient patient) throws LIMSRuntimeException {
		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			personService.insert(patient.getPerson());
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			personService.update(patient.getPerson());
		}
		patient.setPerson(patient.getPerson());

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			patientService.insert(patient);
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			patientService.update(patient);
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
		List<PersonAddress> personAddressList = personAddressService
				.getAddressPartsByPersonId(patient.getPerson().getId());

		for (PersonAddress address : personAddressList) {
			if (address.getAddressPartId().equals(ADDRESS_PART_COMMUNE_ID)) {
				commune = address;
				commune.setValue(patientInfo.getCommune());
				commune.setSysUserId(getSysUserId(request));
				personAddressService.update(commune);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_VILLAGE_ID)) {
				village = address;
				village.setValue(patientInfo.getCity());
				village.setSysUserId(getSysUserId(request));
				personAddressService.update(village);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_DEPT_ID)) {
				dept = address;
				if (!GenericValidator.isBlankOrNull(patientInfo.getAddressDepartment())
						&& !patientInfo.getAddressDepartment().equals("0")) {
					dept.setValue(patientInfo.getAddressDepartment());
					dept.setType("D");
					dept.setSysUserId(getSysUserId(request));
					personAddressService.update(dept);
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
		personAddressService.insert(address);
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
						patientIdentityService.update(listIdentity);
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
			patientIdentityService.insert(identity);
		}
	}

	protected void persistPatientType(PatientManagementInfo patientInfo, Patient patient) {
		String typeName = null;

		try {
			typeName = patientInfo.getPatientType();
		} catch (Exception ignored) {
		}

		if (!GenericValidator.isBlankOrNull(typeName) && !"0".equals(typeName)) {
			String typeID = PatientTypeMap.getInstance().getIDForType(typeName);

			PatientPatientType patientPatientType = patientPatientTypeService
					.getPatientPatientTypeForPatient(patient.getId());

			if (patientPatientType == null) {
				patientPatientType = new PatientPatientType();
				patientPatientType.setSysUserId(getSysUserId(request));
				patientPatientType.setPatientId(patient.getId());
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeService.insert(patientPatientType);
			} else {
				patientPatientType.setSysUserId(getSysUserId(request));
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeService.update(patientPatientType);
			}
		}
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "patientManagementDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/Dashboard.do";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PatientManagement.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "patientManagementDefinition";
		} else {
			return "PageNotFound";
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
}
