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

import java.util.List;

import org.apache.commons.validator.GenericValidator;

import spring.service.dictionary.DictionaryService;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.util.SpringContext;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ARVFollowupInitalHIVStrategy implements ICSVColumnCustomStrategy {

	private SampleService sampleService = SpringContext.getBean(SampleService.class);
	private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	private ObservationHistoryService ohService = SpringContext.getBean(ObservationHistoryService.class);
	private DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);

	@Override
	public String translate(String value, String accessionNumber, String csvName, String dbName) {
		Sample sample = sampleService.getSampleByAccessionNumber(accessionNumber);

		if (sample == null) {
			return "";
		}

		Patient patient = sampleHumanService.getPatientForSample(sample);

		if (patient == null) {
			return "";
		}

		List<Sample> samples = sampleHumanService.getSamplesForPatient(patient.getId());

		if (samples == null || samples.size() < 2) {
			return "";
		}

		Sample firstSample = samples.get(0);

		String typeId = ObservationHistoryTypeMap.getInstance().getIDForType("projectFormName");
		ObservationHistory oh = ohService.getObservationHistoriesBySampleIdAndType(firstSample.getId(), typeId);

		if (oh == null) {
			return "";
		}

		if ("InitialARV_Id".equals(oh.getValue())) {
			typeId = ObservationHistoryTypeMap.getInstance().getIDForType("hivStatus");
			oh = ohService.getObservationHistoriesBySampleIdAndType(firstSample.getId(), typeId);

			if (oh != null && !GenericValidator.isBlankOrNull(oh.getValue())) {
				return dictionaryService.getDataForId(oh.getValue()).getLocalizedName();
			}
		}

		return "";
	}

}
