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


public class HT_LNSPFormFields implements IFormFieldsForImplementation {

	public HashMap<FormFields.Field, Boolean> getImplementationAttributes() {
		HashMap<FormFields.Field, Boolean> settings = new HashMap<FormFields.Field, Boolean>();
		settings.put(Field.StNumber, Boolean.FALSE);
		settings.put(Field.AKA, Boolean.FALSE);
		settings.put(Field.MothersName, Boolean.FALSE);
		settings.put(Field.PatientType, Boolean.FALSE);
		settings.put(Field.InsuranceNumber, Boolean.FALSE);		
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.InlineOrganizationTypes, Boolean.TRUE);
		settings.put(Field.Occupation, Boolean.FALSE);
		settings.put(Field.ADDRESS_DEPARTMENT, Boolean.TRUE);
		settings.put(Field.MotherInitial, Boolean.TRUE);
		settings.put(Field.ResultsReferral, Boolean.TRUE);
		settings.put(Field.ValueHozSpaceOnResults, Boolean.TRUE);
		settings.put(Field.InitialSampleCondition, Boolean.TRUE);
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.OrganizationMultiUnit, Boolean.FALSE);
		settings.put(Field.OrganizationOrgId, Boolean.FALSE);
		settings.put(Field.RequesterSiteList, Boolean.TRUE);
		settings.put(Field.ADDRESS_CITY, Boolean.FALSE);
		settings.put(Field.PatientRequired, Boolean.TRUE);
		settings.put(Field.SampleCondition, Boolean.FALSE);
		settings.put(Field.NON_CONFORMITY_SITE_LIST, Boolean.TRUE);
		settings.put(Field.PatientNameRequired, Boolean.FALSE);
		settings.put(Field.SampleEntryUseReceptionHour, Boolean.TRUE);
		settings.put(Field.CollectionDate, Boolean.TRUE);
		settings.put(Field.CollectionTime, Boolean.TRUE);
		settings.put(Field.SAMPLE_ENTRY_USE_REFFERING_PATIENT_NUMBER, Boolean.TRUE);
		settings.put(Field.SampleEntryRequestingSiteSampleId, Boolean.FALSE);
        settings.put(Field.PatientIDRequired_SampleConfirmation, Boolean.FALSE );
		return settings;
	}

}
