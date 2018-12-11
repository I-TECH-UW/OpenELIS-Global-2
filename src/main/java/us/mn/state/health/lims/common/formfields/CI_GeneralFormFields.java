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
package us.mn.state.health.lims.common.formfields;

import java.util.HashMap;

import us.mn.state.health.lims.common.formfields.FormFields.Field;


public class CI_GeneralFormFields implements IFormFieldsForImplementation {

	public HashMap<FormFields.Field, Boolean> getImplementationAttributes() {
		HashMap<FormFields.Field, Boolean> settings = new HashMap<FormFields.Field, Boolean>();
		settings.put(Field.StNumber,Boolean.FALSE);
		settings.put(Field.AKA, Boolean.FALSE);
		settings.put(Field.MothersName, Boolean.FALSE);
		settings.put(Field.PatientType, Boolean.FALSE);
		settings.put(Field.InsuranceNumber, Boolean.FALSE);
		settings.put(Field.CollectionDate, Boolean.TRUE);
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.InlineOrganizationTypes, Boolean.TRUE);
		settings.put(Field.Occupation, Boolean.FALSE);
		settings.put(Field.ADDRESS_DEPARTMENT, Boolean.FALSE);
		settings.put(Field.MotherInitial, Boolean.FALSE);
		settings.put(Field.ResultsReferral, Boolean.TRUE);
		settings.put(Field.ValueHozSpaceOnResults, Boolean.TRUE);
		settings.put(Field.InitialSampleCondition, Boolean.TRUE);
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.OrganizationMultiUnit, Boolean.FALSE);
		settings.put(Field.OrganizationOrgId, Boolean.FALSE);
		settings.put(Field.RequesterSiteList, Boolean.TRUE);
		settings.put(Field.ADDRESS_CITY, Boolean.FALSE);
		settings.put(Field.PatientRequired, Boolean.FALSE);
		settings.put(Field.SampleCondition, Boolean.TRUE);
		settings.put(Field.NON_CONFORMITY_SITE_LIST, Boolean.TRUE);
		settings.put(Field.NON_CONFORMITY_SITE_LIST_USER_ADDABLE, Boolean.FALSE);
		settings.put(Field.QaEventsBySection, Boolean.TRUE);
		settings.put(Field.PatientNameRequired, Boolean.FALSE);
		settings.put(Field.SubjectNumber, Boolean.TRUE);
		settings.put(Field.NationalID,Boolean.TRUE);
		settings.put(Field.SampleEntryUseReceptionHour, Boolean.TRUE);
		settings.put(Field.SampleEntryUseRequestDate, Boolean.TRUE);
		settings.put(Field.SampleEntryNextVisitDate, Boolean.TRUE);
		settings.put(Field.SampleEntryRequestingSiteSampleId, Boolean.FALSE);
		settings.put(Field.SampleEntryReferralSiteNameRequired, Boolean.TRUE);
		settings.put(Field.SampleEntryReferralSiteNameCapitialized, Boolean.TRUE);
		settings.put(Field.SampleEntryProviderFax, Boolean.TRUE);
		settings.put(Field.SampleEntryProviderEmail, Boolean.TRUE);
		settings.put(Field.SampleEntryHealthFacilityAddress, Boolean.FALSE);
		settings.put(Field.SampleEntrySampleCollector, Boolean.TRUE);
		settings.put(Field.PatientPhone, Boolean.TRUE);
		settings.put(Field.PatientHealthRegion, Boolean.TRUE);
		settings.put(Field.PatientHealthDistrict, Boolean.TRUE);
		settings.put(Field.PatientMarriageStatus, Boolean.TRUE);
		settings.put(Field.PatientEducation, Boolean.TRUE);
		settings.put(Field.SampleEntryPatientClinical, Boolean.FALSE);
		settings.put(Field.SampleEntryRequesterLastNameRequired, Boolean.TRUE);
		//settings.put(Field.QASubjectNumber, Boolean.TRUE);
		settings.put(Field.QATimeWithDate, Boolean.TRUE);
		settings.put(Field.SampleEntryReferralSiteCode, Boolean.FALSE);							
		settings.put(Field.QA_DOCUMENT_NUMBER, Boolean.FALSE);							
		settings.put(Field.CollectionTime, Boolean.TRUE);					
        settings.put(Field.TEST_LOCATION_CODE,Boolean.TRUE);							
        settings.put(Field.ProviderInfo, Boolean.TRUE);					
        settings.put(Field.SearchSampleStatus,  Boolean.TRUE);					
        settings.put(Field.OrganizationAddressInfo, Boolean.FALSE);						
        settings.put(Field.DepersonalizedResults, Boolean.FALSE);						
		settings.put(Field.Project, Boolean.TRUE);
		settings.put(Field.NON_CONFORMITY_PROVIDER_ADDRESS, Boolean.FALSE);		
		settings.put(Field.QA_FULL_PROVIDER_INFO, Boolean.TRUE);					
		settings.put(Field.QASubjectNumber, Boolean.FALSE);					
        settings.put(Field.ADDRESS_COMMUNE, Boolean.TRUE);							
        settings.put(Field.ADDRESS_VILLAGE, Boolean.TRUE);							
        settings.put(Field.SEARCH_PATIENT_WITH_LAB_NO, Boolean.TRUE);

		
		return settings;
	}

}
