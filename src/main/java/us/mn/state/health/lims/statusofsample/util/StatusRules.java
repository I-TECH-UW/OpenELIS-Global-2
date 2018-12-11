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
package us.mn.state.health.lims.statusofsample.util;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;

public class StatusRules{
	
	
	public boolean hasFailedValidation(String analysisStatusId) {
		return analysisStatusId.equals(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected));
	}

	public void setAllowableStatusForLoadingResults(ResultsLoadUtility resultsLoadUtility) {
		resultsLoadUtility.addIncludedAnalysisStatus(AnalysisStatus.BiologistRejected);
		resultsLoadUtility.addIncludedAnalysisStatus(AnalysisStatus.NotStarted);
		resultsLoadUtility.addIncludedAnalysisStatus(AnalysisStatus.NonConforming_depricated);
		resultsLoadUtility.addIncludedSampleStatus(OrderStatus.Entered);
		resultsLoadUtility.addIncludedSampleStatus(OrderStatus.Started);
		resultsLoadUtility.addIncludedSampleStatus(OrderStatus.NonConforming_depricated);
	}

	public String getStartingAnalysisStatus() {
		return StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted);
	}

	public static boolean useRecordStatusForValidation(){
		String statusRules = ConfigurationProperties.getInstance().getPropertyValueUpperCase(Property.StatusRules);
		return statusRules.equals(	IActionConstants.STATUS_RULES_RETROCI);

	}
}
