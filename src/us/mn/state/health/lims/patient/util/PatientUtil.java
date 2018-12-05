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
package us.mn.state.health.lims.patient.util;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;

public class PatientUtil {

	private static Patient UNKNOWN_PATIENT;
	private static Person UNKNOWN_PERSON;
	private static Provider UNKNOWN_PROVIDER;
	private static PatientDAO patientDAO = new PatientDAOImpl();
	
	private static void initializeUnknowns() {
		PersonDAO personDAO = new PersonDAOImpl();
		UNKNOWN_PERSON = personDAO.getPersonByLastName("UNKNOWN_");
		if (UNKNOWN_PERSON == null) {
			UNKNOWN_PERSON = new Person();
			UNKNOWN_PERSON.setSysUserId("1");
			UNKNOWN_PERSON.setLastName("UNKNOWN_");
			personDAO.insertData(UNKNOWN_PERSON);
		}

		ProviderDAO providerDAO = new ProviderDAOImpl();
		UNKNOWN_PROVIDER = providerDAO.getProviderByPerson(UNKNOWN_PERSON);

		if (UNKNOWN_PROVIDER == null) {
			UNKNOWN_PROVIDER = new Provider();
			UNKNOWN_PROVIDER.setSysUserId("1");
			UNKNOWN_PROVIDER.setPerson(UNKNOWN_PERSON);
			providerDAO.insertData(UNKNOWN_PROVIDER);
		}


		UNKNOWN_PATIENT = patientDAO.getPatientByPerson(UNKNOWN_PERSON);

		if (UNKNOWN_PATIENT == null) {
			UNKNOWN_PATIENT = new Patient();
			UNKNOWN_PATIENT.setSysUserId("1");
			UNKNOWN_PATIENT.setPerson(UNKNOWN_PERSON);
			patientDAO.insertData(UNKNOWN_PATIENT);
		}
	}

	public static String getDisplayDOBForPatient(String patientId, String defaultValue) {
		Patient patient = patientDAO.getData(patientId);
		if( patient != null){
			return patient.getBirthDateForDisplay();
		}
		
		return defaultValue;
	}

	public static List<PatientIdentity> getIdentityListForPatient(String patientId) {
		PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
		return identityDAO.getPatientIdentitiesForPatient(patientId);
	}

	@SuppressWarnings("unchecked")
	public static List<Gender> findGenders() {
		return (List<Gender>) new GenderDAOImpl().getAllGenders();
	}

	public static List<PatientIdentity> getIdentityListForPatient(Patient patient) {
        if( patient != null){
            PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
            return identityDAO.getPatientIdentitiesForPatient( patient.getId() );
        }else{
            return new ArrayList<PatientIdentity>(  );
        }
	}

	public static void invalidateUnknownPatients() {
		UNKNOWN_PATIENT = null;
		UNKNOWN_PERSON = null;
		UNKNOWN_PROVIDER = null;
	}

	public static Patient getUnknownPatient() {
		if( UNKNOWN_PATIENT == null){
			initializeUnknowns();
		}
		return UNKNOWN_PATIENT;
	}

	public static Person getUnknownPerson() {
		if( UNKNOWN_PERSON == null){
			initializeUnknowns();
		}
		return UNKNOWN_PERSON;
	}

	public static Provider getUnownProvider() {
		if( UNKNOWN_PROVIDER == null){
			initializeUnknowns();
		}
		return UNKNOWN_PROVIDER;
	}
	
	public static Patient getPatientByIdentificationNumber( String id){
		Patient patient = patientDAO.getPatientByNationalId(id);
		
		if( patient == null){
			patient = patientDAO.getPatientByExternalId(id);
		}
		
		return patient;
	}
	
	public static Patient getPatientForSample(Sample sample){
		return new SampleHumanDAOImpl().getPatientForSample(sample);
	}
}
