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
package us.mn.state.health.lims.reports.action.implementation;

import java.util.List;

import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;

public class PatientARVInitialVersion2Report extends PatientARVReport implements IReportCreator {
	@Override
    protected String reportFileName(){
    	return "Patient_ARV_Version2";
    }


	protected String getReportNameForReport(){
		return StringUtil.getMessageForKey("sample.entry.project.initialARV.title");
	}

	protected boolean allowSample(){
		List<ObservationHistory> historyList = observationHistoryDAO.getAll(reportPatient, reportSample, OBSERVATION_PROJECT_ID);

		for( ObservationHistory history : historyList){
			if( "InitialARV_Id".equals(history.getValue())){
				return true;
			}
		}

		return false;
	}

	protected String getProjectId() {
		return ANTIRETROVIRAL_STUDY_ID;
	}
	
	@Override
	protected void createReportParameters() {
	    super.createReportParameters();
		reportParameters.put("contact", "CHU de Treichville, 01 BP 1712 Tel : 21-21-42-50/21-25-4189 Fax : 21-24-29-69/ 21-25-10-63");
	}
}
