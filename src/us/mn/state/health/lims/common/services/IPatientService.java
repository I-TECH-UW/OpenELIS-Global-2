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
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.valueholder.Person;

public interface IPatientService{

	String getGUID();

	String getNationalId();

	String getSTNumber();

	String getSubjectNumber();

	String getFirstName();

	String getLastName();

	String getLastFirstName();

	String getGender();

    String getLocalizedGender();

	Map<String, String> getAddressComponents();

	String getEnteredDOB();

	Timestamp getDOB();

	String getPhone();

	Person getPerson();

	String getPatientId();

	String getBirthdayForDisplay();

	List<PatientIdentity> getIdentityList();

	Patient getPatient();

    String getAKA();

    String getMother();

    String getInsurance();

    String getOccupation();

    String getOrgSite();

    String getMothersInitial();

    String getEducation();

    String getMaritalStatus();

    String getHealthDistrict();

    String getHealthRegion();

    String getObNumber();

    String getPCNumber();
}