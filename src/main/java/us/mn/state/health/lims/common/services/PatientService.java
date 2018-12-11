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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.order.action.MessagePatient;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.daoimpl.PatientIdentityTypeDAOImpl;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;

public class PatientService implements IPatientService {

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



    private static Map<String, String> addressPartIdToNameMap = new HashMap<String, String>();
	private static final PatientIdentityDAO patientIdentityDAO = new PatientIdentityDAOImpl();
	private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static final PatientDAO patientDAO = new PatientDAOImpl();
    private static final GenderDAO genderDAO = new GenderDAOImpl();
	private Patient patient;
	private PersonService personService;
	
	static{
        PatientIdentityTypeDAOImpl identityTypeDAOImpl = new PatientIdentityTypeDAOImpl();

		PatientIdentityType patientType = identityTypeDAOImpl.getNamedIdentityType("GUID");
		if( patientType != null){
			PATIENT_GUID_IDENTITY = patientType.getId();
		}

        patientType = identityTypeDAOImpl.getNamedIdentityType("SUBJECT");
        if( patientType != null){
            PATIENT_SUBJECT_IDENTITY = patientType.getId();
        }

		patientType = identityTypeDAOImpl.getNamedIdentityType("NATIONAL");
		if( patientType != null){
			PATIENT_NATIONAL_IDENTITY = patientType.getId();
		}
		
		patientType = identityTypeDAOImpl.getNamedIdentityType("ST");
		if( patientType != null){
			PATIENT_ST_IDENTITY = patientType.getId();
		}

        patientType = identityTypeDAOImpl.getNamedIdentityType("AKA");
        if( patientType != null){
            PATIENT_AKA_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("MOTHER");
        if( patientType != null){
            PATIENT_MOTHER_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("INSURANCE");
        if( patientType != null){
            PATIENT_INSURANCE_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("OCCUPATION");
        if( patientType != null){
            PATIENT_OCCUPATION_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("ORG_SITE");
        if( patientType != null){
            PATIENT_ORG_SITE_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("MOTHERS_INITIAL");
        if( patientType != null){
            PATIENT_MOTHERS_INITIAL_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("EDUCATION");
        if( patientType != null){
            PATIENT_EDUCATION_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("MARITIAL");
        if( patientType != null){
            PATIENT_MARITAL_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("HEALTH_DISTRICT");
        if( patientType != null){
            PATIENT_HEALTH_DISTRICT_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("HEALTH_REGION");
        if( patientType != null){
            PATIENT_HEALTH_REGION_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("OB_NUMBER");
        if( patientType != null){
            PATIENT_OB_NUMBER_IDENTITY = patientType.getId();
        }

        patientType = identityTypeDAOImpl.getNamedIdentityType("PC_NUMBER");
        if( patientType != null){
            PATIENT_PC_NUMBER_IDENTITY = patientType.getId();
        }

        List<AddressPart> parts = new AddressPartDAOImpl().getAll();
		
		for( AddressPart part : parts){
			addressPartIdToNameMap.put(part.getId(), part.getPartName());
		}
	}
	
	public PatientService(Patient patient){
		this.patient = patient;
		
		if( patient == null){
			personService = new PersonService( null );
			return;
		} 
			
		if( patient.getPerson() == null){
			new PatientDAOImpl().getData(this.patient);
		}
		personService = new PersonService(patient.getPerson());

	}
	
	/**
	 * Gets the patient for the sample and then calls the constructor with patient argument
	 * @param sample
	 */
	public PatientService(Sample sample){
		this(sampleHumanDAO.getPatientForSample(sample));
	}
	
	/**
	 * Gets the patient with this guid
	 * @param guid
	 */
	public PatientService(String guid){
		this(getPatientForGuid( guid));
	}
	
	public PatientService(MessagePatient mPatient) {
		this(patientDAO.getPatientByExternalId(mPatient.getExternalId()));
	}

	private static Patient getPatientForGuid(String guid){
		List<PatientIdentity> identites = patientIdentityDAO.getPatientIdentitiesByValueAndType(guid, PATIENT_GUID_IDENTITY);
		if( identites.isEmpty()){
			return null;
		}
		
		return patientDAO.getData( identites.get(0).getPatientId() );
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getGUID()
	 */
	@Override
	public String getGUID(){
        return getIdentityInfo(PATIENT_GUID_IDENTITY);
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getNationalId()
	 */
	@Override
	public String getNationalId(){
		if( patient == null){
			return "";
		}
		
		if( !GenericValidator.isBlankOrNull(patient.getNationalId())){
			return patient.getNationalId();
		}else{
			return getIdentityInfo(PATIENT_NATIONAL_IDENTITY);
		}
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getSTNumber()
	 */
	@Override
	public String getSTNumber(){
		return getIdentityInfo(PATIENT_ST_IDENTITY);
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getSubjectNumber()
	 */
	@Override
	public String getSubjectNumber(){
		return getIdentityInfo(PATIENT_SUBJECT_IDENTITY);
	}
	
	private String getIdentityInfo(String identityId) {
		if( patient == null || GenericValidator.isBlankOrNull( identityId )){
			return "";
		}
		
		PatientIdentity identity = patientIdentityDAO.getPatitentIdentityForPatientAndType(patient.getId(), identityId);
		
		if( identity != null){
			return identity.getIdentityData();
		}else{
			return "";
		}
	}
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getFirstName()
	 */
	@Override
	public String getFirstName(){
		return personService.getFirstName();
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getLastName()
	 */
	@Override
	public String getLastName(){
		return personService.getLastName();
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getLastFirstName()
	 */
	@Override
	public String getLastFirstName(){
		return personService.getLastFirstName();
	}
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getGender()
	 */
	@Override
	public String getGender(){
		return patient != null ? patient.getGender() : "";
	}

    @Override
    public String getLocalizedGender(){
        String genderType = getGender();

        if( genderType.length() > 0){
            Gender gender = genderDAO.getGenderByType( genderType );
            if( gender != null){
                return gender.getLocalizedName();
            }
        }
         return null;
    }

    /* (non-Javadoc)
         * @see us.mn.state.health.lims.common.services.IPatientService#getAddressComponents()
         */
	@Override
	public Map<String, String> getAddressComponents(){
		return personService.getAddressComponents();
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getEnteredDOB()
	 */
	@Override
	public String getEnteredDOB(){
			return patient != null ? patient.getBirthDateForDisplay() : "";
	}

	@Override
	public Timestamp getDOB() {
		return patient != null ? patient.getBirthDate() : null;
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPhone()
	 */
	@Override
	public String getPhone(){
		return personService.getPhone();
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPerson()
	 */
	@Override
	public Person getPerson(){
		return personService.getPerson();
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPatientId()
	 */
	@Override
	public String getPatientId(){
		return patient != null ? patient.getId() : null;
	}
	
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getBirthdayForDisplay()
	 */
	@Override
	public String getBirthdayForDisplay(){
		return patient != null ? DateUtil.convertTimestampToStringDate(patient.getBirthDate()) : "";
	}

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getIdentityList()
	 */
	@Override
	public List<PatientIdentity> getIdentityList(){
		return patient != null ? PatientUtil.getIdentityListForPatient(patient) : new ArrayList<PatientIdentity>();
	}

    public String getExternalId(){
        return patient == null ? "" : patient.getExternalId();
    }
	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.services.IPatientService#getPatient()
	 */
	@Override
	public Patient getPatient(){
		return patient;
	}

    @Override
    public String getAKA(){
        return getIdentityInfo(PATIENT_AKA_IDENTITY);
    }

    @Override
    public String getMother(){
        return getIdentityInfo(PATIENT_MOTHER_IDENTITY);
    }

    @Override
    public String getInsurance(){
        return getIdentityInfo(PATIENT_INSURANCE_IDENTITY);
    }

    @Override
    public String getOccupation(){
        return getIdentityInfo(PATIENT_OCCUPATION_IDENTITY);
    }

    @Override
    public String getOrgSite(){
        return getIdentityInfo(PATIENT_ORG_SITE_IDENTITY);
    }

    @Override
    public String getMothersInitial(){
        return getIdentityInfo(PATIENT_MOTHERS_INITIAL_IDENTITY);
    }

    @Override
    public String getEducation(){
        return getIdentityInfo(PATIENT_EDUCATION_IDENTITY);
    }

    @Override
    public String getMaritalStatus(){
        return getIdentityInfo(PATIENT_MARITAL_IDENTITY);
    }

    @Override
    public String getHealthDistrict(){
        return getIdentityInfo(PATIENT_HEALTH_DISTRICT_IDENTITY);
    }

    @Override
    public String getHealthRegion(){
        return getIdentityInfo(PATIENT_HEALTH_REGION_IDENTITY);
    }

    @Override
    public String getObNumber(){
        return getIdentityInfo(PATIENT_OB_NUMBER_IDENTITY);
    }

    @Override
    public String getPCNumber(){
        return getIdentityInfo(PATIENT_PC_NUMBER_IDENTITY);
    }
}
