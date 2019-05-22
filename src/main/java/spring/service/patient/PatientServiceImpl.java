package spring.service.patient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.person.PersonServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.order.action.MessagePatient;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;

@Service
@DependsOn({ "springContext" })
public class PatientServiceImpl extends BaseObjectServiceImpl<Patient> implements PatientService, IPatientService {

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
	private static final PatientDAO patientDAO = SpringContext.getBean(PatientDAO.class);

	@Autowired
	private static final PatientIdentityDAO patientIdentityDAO = SpringContext.getBean(PatientIdentityDAO.class);
	@Autowired
	private static final SampleHumanDAO sampleHumanDAO = SpringContext.getBean(SampleHumanDAO.class);
	@Autowired
	private static final GenderDAO genderDAO = SpringContext.getBean(GenderDAO.class);
	@Autowired
	PatientIdentityTypeDAO identityTypeDAO = SpringContext.getBean(PatientIdentityTypeDAO.class);
	@Autowired
	AddressPartDAO addressPartDAO = SpringContext.getBean(AddressPartDAO.class);

	private Patient patient;
	private PersonServiceImpl personService;

	public synchronized void initializeGlobalVariables() {

		PatientIdentityType patientType = identityTypeDAO.getNamedIdentityType("GUID");
		if (patientType != null) {
			PATIENT_GUID_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("SUBJECT");
		if (patientType != null) {
			PATIENT_SUBJECT_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("NATIONAL");
		if (patientType != null) {
			PATIENT_NATIONAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("ST");
		if (patientType != null) {
			PATIENT_ST_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("AKA");
		if (patientType != null) {
			PATIENT_AKA_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("MOTHER");
		if (patientType != null) {
			PATIENT_MOTHER_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("INSURANCE");
		if (patientType != null) {
			PATIENT_INSURANCE_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("OCCUPATION");
		if (patientType != null) {
			PATIENT_OCCUPATION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("ORG_SITE");
		if (patientType != null) {
			PATIENT_ORG_SITE_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("MOTHERS_INITIAL");
		if (patientType != null) {
			PATIENT_MOTHERS_INITIAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("EDUCATION");
		if (patientType != null) {
			PATIENT_EDUCATION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("MARITIAL");
		if (patientType != null) {
			PATIENT_MARITAL_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("HEALTH_DISTRICT");
		if (patientType != null) {
			PATIENT_HEALTH_DISTRICT_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("HEALTH_REGION");
		if (patientType != null) {
			PATIENT_HEALTH_REGION_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("OB_NUMBER");
		if (patientType != null) {
			PATIENT_OB_NUMBER_IDENTITY = patientType.getId();
		}

		patientType = identityTypeDAO.getNamedIdentityType("PC_NUMBER");
		if (patientType != null) {
			PATIENT_PC_NUMBER_IDENTITY = patientType.getId();
		}

		List<AddressPart> parts = addressPartDAO.getAll();

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
			patientDAO.getData(this.patient);
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
		this(sampleHumanDAO.getPatientForSample(sample));
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
		this(patientDAO.getPatientByExternalId(mPatient.getExternalId()));
	}

	@Override
	protected PatientDAO getBaseObjectDAO() {
		return patientDAO;
	}

	private static Patient getPatientForGuid(String guid) {
		List<PatientIdentity> identites = patientIdentityDAO.getPatientIdentitiesByValueAndType(guid,
				PATIENT_GUID_IDENTITY);
		if (identites.isEmpty()) {
			return null;
		}

		return patientDAO.getData(identites.get(0).getPatientId());
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

		PatientIdentity identity = patientIdentityDAO.getPatitentIdentityForPatientAndType(patient.getId(), identityId);

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
			Gender gender = genderDAO.getGenderByType(genderType);
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
}
