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

public class CI_RETROFormFields implements IFormFieldsForImplementation {

	public HashMap<FormFields.Field, Boolean> getImplementationAttributes() {
		HashMap<FormFields.Field, Boolean> settings = new HashMap<FormFields.Field, Boolean>();

		settings.put(Field.StNumber,  Boolean.FALSE);
		settings.put(Field.AKA, Boolean.FALSE);
		settings.put(Field.MothersName, Boolean.FALSE);
		settings.put(Field.PatientType, Boolean.TRUE);
		settings.put(Field.InsuranceNumber, Boolean.FALSE);
		settings.put(Field.CollectionDate, Boolean.TRUE);
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.InlineOrganizationTypes, Boolean.TRUE);
		settings.put(Field.ProviderInfo, Boolean.FALSE);
		settings.put(Field.NationalID,  Boolean.TRUE);
		settings.put(Field.SearchSampleStatus,  Boolean.TRUE);
		settings.put(Field.OrgLocalAbrev, Boolean.FALSE);
		settings.put(Field.OrganizationAddressInfo, Boolean.FALSE);
		settings.put(Field.DepersonalizedResults, Boolean.TRUE);
		settings.put(Field.SEARCH_PATIENT_WITH_LAB_NO, Boolean.TRUE);
		settings.put(Field.Project, Boolean.TRUE);
		settings.put(Field.QA_FULL_PROVIDER_INFO, Boolean.FALSE);
		settings.put(Field.QASubjectNumber, Boolean.FALSE);
        settings.put(Field.ADDRESS_COMMUNE, Boolean.FALSE);
        settings.put(Field.ADDRESS_VILLAGE, Boolean.FALSE);



        return settings;
	}

}
