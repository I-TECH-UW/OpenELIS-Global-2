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

import spring.service.dictionary.DictionaryService;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.observationhistorytype.ObservationHistoryTypeService;
import spring.service.sample.SampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class EIDTypeOfClinicStrategy implements ICSVColumnCustomStrategy {

	private String CLINIC_OTHER_OBS_HISTORY_TYPE_ID;
	private String CLINIC_OTHER_DICT_ID;

	public EIDTypeOfClinicStrategy() {
		Dictionary clinicOtherDictionary = SpringContext.getBean(DictionaryService.class)
				.getDictionaryEntrysByNameAndCategoryDescription("4 = Other(Specify)", "EID Type of Clinic");

		if (clinicOtherDictionary != null) {
			CLINIC_OTHER_DICT_ID = clinicOtherDictionary.getId();
		}

		ObservationHistoryType observationType = SpringContext.getBean(ObservationHistoryTypeService.class)
				.getByName("eidTypeOfClinicOther");

		if (observationType != null) {
			CLINIC_OTHER_OBS_HISTORY_TYPE_ID = observationType.getId();
		}
	}

	@Override
	public String translate(String value, String accessionNumber, String csvName, String dbName) {
		if (CLINIC_OTHER_DICT_ID.equals(value)) { // clinic "other" was entered
			Sample sample = SpringContext.getBean(SampleService.class).getSampleByAccessionNumber(accessionNumber);

			if (sample != null) {
				ObservationHistory observation = SpringContext.getBean(ObservationHistoryService.class)
						.getObservationHistoriesBySampleIdAndType(sample.getId(), CLINIC_OTHER_OBS_HISTORY_TYPE_ID);

				if (observation != null) {
					return observation.getValue();
				}
			}
		}
		return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translate(value);

	}

}
