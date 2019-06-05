package spring.service.patient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.address.AddressPartService;
import spring.service.address.PersonAddressService;
import spring.service.common.BaseObjectServiceImpl;
import spring.service.gender.GenderService;
import spring.service.patientidentity.PatientIdentityService;
import spring.service.patientidentitytype.PatientIdentityTypeService;
import spring.service.patienttype.PatientPatientTypeService;
import spring.service.person.PersonService;
import spring.service.person.PersonServiceImpl;
import spring.service.samplehuman.SampleHumanService;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.order.action.MessagePatient;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.patient.action.IPatientUpdate.PatientUpdateStatus;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.patienttype.util.PatientTypeMap;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class PatientServiceImpl extends BaseObjectServiceImpl<Patient, String> implements PatientService, IPatientService {

	public static final String ADDRESS_STREET = "Street";
	public static final String ADDRESS_STATE = "State";
	public static final String ADDRESS_VILLAGE = "village";
	public static final String ADDRESS_DEPT = "department";
	public static final String ADDRESS_COMMUNE = "commune";
	public static final String ADDRESS_ZIP = "zip";
	public static final String ADDRESS_COUNTRY = "Country";
	public static final String ADDRESS_CITY = "City";

	public static String PATIENT_GUID_IDENTITY;
	public static String PATIENT_NATIONAL_IDENTITY;
	public static String PATIENT_ST_IDENTITY;
	public static String PATIENT_SUBJECT_IDENTITY;
	public static String PATIENT_AKA_IDENTITY;
	public static String PATIENT_MOTHER_IDENTITY;
	public static String PATIENT_INSURANCE_IDENTITY;
	public static String PATIENT_OCCUPATION_IDENTITY;
	public static String PATIENT_ORG_SITE_IDENTITY;
	public static String PATIENT_MOTHERS_INITIAL_IDENTITY;
	public static String PATIENT_EDUCATION_IDENTITY;
	public static String PATIENT_MARITAL_IDENTITY;
	public static String PATIENT_HEALTH_DISTRICT_IDENTITY;
	public static String PATIENT_HEALTH_REGION_IDENTITY;
	public static String PATIENT_OB_NUMBER_IDENTITY;
	public static String PATIENT_PC_NUMBER_IDENTITY;

	private static Map<String, String> addressPartIdToNameMap = new HashMap<>();

	@Autowired
	private static final PatientDAO baseObjectDAO = SpringContext.getBean(PatientDAO.class);

	@Autowired
	private static final PatientIdentityService patientIdentityService = SpringContext
			.getBean(PatientIdentityService.class);
	@Autowired
	private static final SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	@Autowired
	private static final GenderService genderService = SpringContext.getBean(GenderService.class);
	@Autowired
	private static PatientIdentityTypeService identityTypeService = SpringContext
			.getBean(PatientIdentityTypeService.class);
	@Autowired
	private static AddressPartService addressPartService = SpringContext.getBean(AddressPartService.class);
	@Autowired
	private static PersonAddressService personAddressService = SpringContext.getBean(PersonAddressService.class);
	@Autowired
	private static PatientPatientTypeService patientPatientTypeService = SpringContext
			.getBean(PatientPatientTypeService.class);
	private PersonService personService = SpringContext.getBean(PersonService.class);

	private Patient patient;

	public synchronized void initializeGlobalVariables() {

		PatientIdentityType patientType = identityTypeService.getNamedIdentityType("GUID");
		if (patientType != null) {
			PATIENT_GUID_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("SUBJECT");
		if (patientType != null) {
			PATIENT_SUBJECT_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("NATIONAL");
		if (patientType != null) {
			PATIENT_NATIONAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("ST");
		if (patientType != null) {
			PATIENT_ST_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("AKA");
		if (patientType != null) {
			PATIENT_AKA_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("MOTHER");
		if (patientType != null) {
			PATIENT_MOTHER_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("INSURANCE");
		if (patientType != null) {
			PATIENT_INSURANCE_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("OCCUPATION");
		if (patientType != null) {
			PATIENT_OCCUPATION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("ORG_SITE");
		if (patientType != null) {
			PATIENT_ORG_SITE_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("MOTHERS_INITIAL");
		if (patientType != null) {
			PATIENT_MOTHERS_INITIAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("EDUCATION");
		if (patientType != null) {
			PATIENT_EDUCATION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("MARITIAL");
		if (patientType != null) {
			PATIENT_MARITAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("HEALTH_DISTRICT");
		if (patientType != null) {
			PATIENT_HEALTH_DISTRICT_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("HEALTH_REGION");
		if (patientType != null) {
			PATIENT_HEALTH_REGION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("OB_NUMBER");
		if (patientType != null) {
			PATIENT_OB_NUMBER_IDENTITY = patientType.getId();
		}

		patientType = identityTypeService.getNamedIdentityType("PC_NUMBER");
		if (patientType != null) {
			PATIENT_PC_NUMBER_IDENTITY = patientType.getId();
		}

		List<AddressPart> parts = addressPartService.getAll();

		for (AddressPart part : parts) {
			addressPartIdToNameMap.put(part.getId(), part.getPartName());
		}
	}

	PatientServiceImpl() {
		super(Patient.class);
		initializeGlobalVariables();
	}

	public PatientServiceImpl(Patient patient) {
		this();
		this.patient = patient;

		if (patient == null) {
			personService = new PersonServiceImpl(null);
			return;
		}

		if (patient.getPerson() == null) {
			baseObjectDAO.getData(this.patient);
		}
		personService = new PersonServiceImpl(patient.getPerson());

	}

	/**
	 * Gets the patient for the sample and then calls the constructor with patient
	 * argument
	 *
	 * @param sample
	 */
	public PatientServiceImpl(Sample sample) {
		this(sampleHumanService.getPatientForSample(sample));
	}

	/**
	 * Gets the patient with this guid
	 *
	 * @param guid
	 */
	public PatientServiceImpl(String guid) {
		this(getPatientForGuid(guid));
	}

	public PatientServiceImpl(MessagePatient mPatient) {
		this(baseObjectDAO.getPatientByExternalId(mPatient.getExternalId()));
	}

	@Override
	protected PatientDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	private static Patient getPatientForGuid(String guid) {
		List<PatientIdentity> identites = patientIdentityService.getPatientIdentitiesByValueAndType(guid,
				PATIENT_GUID_IDENTITY);
		if (identites.isEmpty()) {
			return null;
		}

		return baseObjectDAO.getData(identites.get(0).getPatientId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getGUID()
	 */
	@Override
	public String getGUID() {
		return getIdentityInfo(PATIENT_GUID_IDENTITY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getNationalId()
	 */
	@Override
	public String getNationalId() {
		if (patient == null) {
			return "";
		}

		if (!GenericValidator.isBlankOrNull(patient.getNationalId())) {
			return patient.getNationalId();
		} else {
			return getIdentityInfo(PATIENT_NATIONAL_IDENTITY);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getSTNumber()
	 */
	@Override
	public String getSTNumber() {
		return getIdentityInfo(PATIENT_ST_IDENTITY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.common.services.IPatientService#getSubjectNumber()
	 */
	@Override
	public String getSubjectNumber() {
		return getIdentityInfo(PATIENT_SUBJECT_IDENTITY);
	}

	private String getIdentityInfo(String identityId) {
		if (patient == null || GenericValidator.isBlankOrNull(identityId)) {
			return "";
		}

		PatientIdentity identity = patientIdentityService.getPatitentIdentityForPatientAndType(patient.getId(),
				identityId);

		if (identity != null) {
			return identity.getIdentityData();
		} else {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return personService.getFirstName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getLastName()
	 */
	@Override
	public String getLastName() {
		return personService.getLastName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.common.services.IPatientService#getLastFirstName()
	 */
	@Override
	public String getLastFirstName() {
		return personService.getLastFirstName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getGender()
	 */
	@Override
	public String getGender() {
		return patient != null ? patient.getGender() : "";
	}

	@Override
	public String getLocalizedGender() {
		String genderType = getGender();

		if (genderType.length() > 0) {
			Gender gender = genderService.getGenderByType(genderType);
			if (gender != null) {
				return gender.getLocalizedName();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.common.services.IPatientService#getAddressComponents(
	 * )
	 */
	@Override
	public Map<String, String> getAddressComponents() {
		return personService.getAddressComponents();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getEnteredDOB()
	 */
	@Override
	public String getEnteredDOB() {
		return patient != null ? patient.getBirthDateForDisplay() : "";
	}

	@Override
	public Timestamp getDOB() {
		return patient != null ? patient.getBirthDate() : null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPhone()
	 */
	@Override
	public String getPhone() {
		return personService.getPhone();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPerson()
	 */
	@Override
	public Person getPerson() {
		return personService.getPerson();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPatientId()
	 */
	@Override
	public String getPatientId() {
		return patient != null ? patient.getId() : null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.common.services.IPatientService#getBirthdayForDisplay
	 * ()
	 */
	@Override
	public String getBirthdayForDisplay() {
		return patient != null ? DateUtil.convertTimestampToStringDate(patient.getBirthDate()) : "";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * us.mn.state.health.lims.common.services.IPatientService#getIdentityList()
	 */
	@Override
	public List<PatientIdentity> getIdentityList() {
		return patient != null ? PatientUtil.getIdentityListForPatient(patient) : new ArrayList<>();
	}

	public String getExternalId() {
		return patient == null ? "" : patient.getExternalId();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPatient()
	 */
	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public String getAKA() {
		return getIdentityInfo(PATIENT_AKA_IDENTITY);
	}

	@Override
	public String getMother() {
		return getIdentityInfo(PATIENT_MOTHER_IDENTITY);
	}

	@Override
	public String getInsurance() {
		return getIdentityInfo(PATIENT_INSURANCE_IDENTITY);
	}

	@Override
	public String getOccupation() {
		return getIdentityInfo(PATIENT_OCCUPATION_IDENTITY);
	}

	@Override
	public String getOrgSite() {
		return getIdentityInfo(PATIENT_ORG_SITE_IDENTITY);
	}

	@Override
	public String getMothersInitial() {
		return getIdentityInfo(PATIENT_MOTHERS_INITIAL_IDENTITY);
	}

	@Override
	public String getEducation() {
		return getIdentityInfo(PATIENT_EDUCATION_IDENTITY);
	}

	@Override
	public String getMaritalStatus() {
		return getIdentityInfo(PATIENT_MARITAL_IDENTITY);
	}

	@Override
	public String getHealthDistrict() {
		return getIdentityInfo(PATIENT_HEALTH_DISTRICT_IDENTITY);
	}

	@Override
	public String getHealthRegion() {
		return getIdentityInfo(PATIENT_HEALTH_REGION_IDENTITY);
	}

	@Override
	public String getObNumber() {
		return getIdentityInfo(PATIENT_OB_NUMBER_IDENTITY);
	}

	@Override
	public String getPCNumber() {
		return getIdentityInfo(PATIENT_PC_NUMBER_IDENTITY);
	}

	@Override
	public void getData(Patient patient) {
		getBaseObjectDAO().getData(patient);

	}

	@Override
	public Patient getData(String patientId) {
		return getBaseObjectDAO().getData(patientId);
	}

	@Override
	public void deleteData(List patients) {
		getBaseObjectDAO().deleteData(patients);

	}

	@Override
	public void updateData(Patient patient) {
		getBaseObjectDAO().updateData(patient);

	}

	@Override
	public boolean insertData(Patient patient) {
		return getBaseObjectDAO().insertData(patient);
	}

	@Override
	public Patient getPatientByNationalId(String subjectNumber) {
		return getBaseObjectDAO().getPatientByNationalId(subjectNumber);
	}

	@Override
	public List<Patient> getPatientsByNationalId(String nationalId) {
		return getBaseObjectDAO().getPatientsByNationalId(nationalId);
	}

	@Override
	public List getPreviousPatientRecord(String id) {
		return getBaseObjectDAO().getPreviousPatientRecord(id);
	}

	@Override
	public Patient getPatientByPerson(Person person) {
		return getBaseObjectDAO().getPatientByPerson(person);
	}

	@Override
	public List getPageOfPatients(int startingRecNo) {
		return getBaseObjectDAO().getPageOfPatients(startingRecNo);
	}

	@Override
	public List getNextPatientRecord(String id) {
		return getBaseObjectDAO().getNextPatientRecord(id);
	}

	@Override
	public Patient getPatientByExternalId(String externalId) {
		return getBaseObjectDAO().getPatientByExternalId(externalId);
	}

	@Override
	public List getAllPatients() {
		return getBaseObjectDAO().getAllPatients();
	}

	@Override
	public boolean externalIDExists(String patientExternalID) {
		return getBaseObjectDAO().externalIDExists(patientExternalID);
	}

	@Override
	public Patient readPatient(String idString) {
		return getBaseObjectDAO().readPatient(idString);
	}

	@Override
	public List<String> getPatientIdentityBySampleStatusIdAndProject(List<Integer> inclusiveStatusIdList,
			String study) {
		return getBaseObjectDAO().getPatientIdentityBySampleStatusIdAndProject(inclusiveStatusIdList, study);
	}

	@Override
	@Transactional
	public void persistPatientData(PatientManagementInfo patientInfo, Patient patient, String sysUserId)
			throws LIMSRuntimeException {
		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			personService.insert(patient.getPerson());
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			personService.update(patient.getPerson());
		}
		patient.setPerson(patient.getPerson());

		if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.ADD) {
			insert(patient);
		} else if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
			update(patient);
		}

		persistPatientRelatedInformation(patientInfo, patient, sysUserId);
	}

	protected void persistPatientRelatedInformation(PatientManagementInfo patientInfo, Patient patient,
			String sysUserId) {
		persistIdentityTypes(patientInfo, patient, sysUserId);
		persistExtraPatientAddressInfo(patientInfo, patient, sysUserId);
		persistPatientType(patientInfo, patient, sysUserId);
	}

	protected void persistIdentityTypes(PatientManagementInfo patientInfo, Patient patient, String sysUserId) {

		persistIdentityType(patientInfo.getSTnumber(), "ST", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getMothersName(), "MOTHER", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getAka(), "AKA", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getInsuranceNumber(), "INSURANCE", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getOccupation(), "OCCUPATION", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getSubjectNumber(), "SUBJECT", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getMothersInitial(), "MOTHERS_INITIAL", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getEducation(), "EDUCATION", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getMaritialStatus(), "MARITIAL", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getNationality(), "NATIONALITY", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getHealthDistrict(), "HEALTH DISTRICT", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getHealthRegion(), "HEALTH REGION", patientInfo, patient, sysUserId);
		persistIdentityType(patientInfo.getOtherNationality(), "OTHER NATIONALITY", patientInfo, patient, sysUserId);
	}

	private void persistExtraPatientAddressInfo(PatientManagementInfo patientInfo, Patient patient, String sysUserId) {
		String ADDRESS_PART_DEPT_ID = "";
		String ADDRESS_PART_COMMUNE_ID = "";
		String ADDRESS_PART_VILLAGE_ID = "";

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

		PersonAddress village = null;
		PersonAddress commune = null;
		PersonAddress dept = null;
		List<PersonAddress> personAddressList = personAddressService
				.getAddressPartsByPersonId(patient.getPerson().getId());

		for (PersonAddress address : personAddressList) {
			if (address.getAddressPartId().equals(ADDRESS_PART_COMMUNE_ID)) {
				commune = address;
				commune.setValue(patientInfo.getCommune());
				commune.setSysUserId(sysUserId);
				personAddressService.update(commune);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_VILLAGE_ID)) {
				village = address;
				village.setValue(patientInfo.getCity());
				village.setSysUserId(sysUserId);
				personAddressService.update(village);
			} else if (address.getAddressPartId().equals(ADDRESS_PART_DEPT_ID)) {
				dept = address;
				if (!GenericValidator.isBlankOrNull(patientInfo.getAddressDepartment())
						&& !patientInfo.getAddressDepartment().equals("0")) {
					dept.setValue(patientInfo.getAddressDepartment());
					dept.setType("D");
					dept.setSysUserId(sysUserId);
					personAddressService.update(dept);
				}
			}
		}

		if (commune == null) {
			insertNewPatientInfo(ADDRESS_PART_COMMUNE_ID, patientInfo.getCommune(), "T", patient, sysUserId);
		}

		if (village == null) {
			insertNewPatientInfo(ADDRESS_PART_VILLAGE_ID, patientInfo.getCity(), "T", patient, sysUserId);
		}

		if (dept == null && patientInfo.getAddressDepartment() != null
				&& !patientInfo.getAddressDepartment().equals("0")) {
			insertNewPatientInfo(ADDRESS_PART_DEPT_ID, patientInfo.getAddressDepartment(), "D", patient, sysUserId);
		}

	}

	private void insertNewPatientInfo(String partId, String value, String type, Patient patient, String sysUserId) {
		PersonAddress address;
		address = new PersonAddress();
		address.setPersonId(patient.getPerson().getId());
		address.setAddressPartId(partId);
		address.setType(type);
		address.setValue(value);
		address.setSysUserId(sysUserId);
		personAddressService.insert(address);
	}

	public void persistIdentityType(String paramValue, String type, PatientManagementInfo patientInfo, Patient patient,
			String sysUserId) throws LIMSRuntimeException {

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
			identity.setSysUserId(sysUserId);
			identity.setIdentityData(paramValue);
			identity.setLastupdatedFields();
			patientIdentityService.insert(identity);
		}
	}

	protected void persistPatientType(PatientManagementInfo patientInfo, Patient patient, String sysUserId) {
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
				patientPatientType.setSysUserId(sysUserId);
				patientPatientType.setPatientId(patient.getId());
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeService.insert(patientPatientType);
			} else {
				patientPatientType.setSysUserId(sysUserId);
				patientPatientType.setPatientTypeId(typeID);
				patientPatientTypeService.update(patientPatientType);
			}
		}
	}

}
