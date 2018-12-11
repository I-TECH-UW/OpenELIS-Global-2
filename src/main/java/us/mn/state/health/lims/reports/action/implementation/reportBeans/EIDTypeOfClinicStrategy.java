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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.reports.action.implementation.reportBeans;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class EIDTypeOfClinicStrategy implements ICSVColumnCustomStrategy {

	private static String CLINIC_OTHER_OBS_HISTORY_TYPE_ID;
	private static String CLINIC_OTHER_DICT_ID;

	static {
		Dictionary clinicOtherDictionary = new DictionaryDAOImpl().getDictionaryEntrysByNameAndCategoryDescription("4 = Other(Specify)",
				"EID Type of Clinic");

		if (clinicOtherDictionary != null) {
			CLINIC_OTHER_DICT_ID = clinicOtherDictionary.getId();
		}

		ObservationHistoryType observationType = new ObservationHistoryTypeDAOImpl().getByName("eidTypeOfClinicOther");

		if (observationType != null) {
			CLINIC_OTHER_OBS_HISTORY_TYPE_ID = observationType.getId();
		}
	}

	@Override
	public String translate(String value, String accessionNumber, String csvName, String dbName) {
		if (CLINIC_OTHER_DICT_ID.equals(value)) { // clinic "other" was entered
			Sample sample = new SampleDAOImpl().getSampleByAccessionNumber(accessionNumber);

			if (sample != null) {
				ObservationHistory observation = new ObservationHistoryDAOImpl().getObservationHistoriesBySampleIdAndType(sample.getId(),
						CLINIC_OTHER_OBS_HISTORY_TYPE_ID);

				if (observation != null) {
					return observation.getValue();
				}
			}
		}
		return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translate(value);

	}

}
