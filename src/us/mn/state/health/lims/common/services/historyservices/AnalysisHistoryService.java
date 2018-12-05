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
package us.mn.state.health.lims.common.services.historyservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;

public class AnalysisHistoryService extends HistoryService {
	private static String ANALYSIS_TABLE_ID;

	static {
		ReferenceTablesDAO tableDAO = new ReferenceTablesDAOImpl();
		ANALYSIS_TABLE_ID = tableDAO.getReferenceTableByName("ANALYSIS").getId();
	}

	public AnalysisHistoryService(Analysis analysis) {
		setUpForAnalysis(analysis);
	}

	@SuppressWarnings("unchecked")
	private void setUpForAnalysis(Analysis analysis) {
		if (  analysis.getTest() != null) {
			History searchHistory = new History();
			searchHistory.setReferenceId(analysis.getId());
			searchHistory.setReferenceTable(ANALYSIS_TABLE_ID);
			historyList = auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory);

			newValueMap = new HashMap<String, String>();
			newValueMap.put(STATUS_ATTRIBUTE, StatusService.getInstance().getStatusNameFromId(analysis.getStatusId()));

			identifier = TestService.getLocalizedTestNameWithType( analysis.getTest() ) + " - " + analysis.getAnalysisType();
		}else{
			historyList = new ArrayList<History>();
		}
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		items.add(getCoreTrail(history));
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {
		String status = extractStatus(changes);
		if (status != null) {
			changeMap.put(STATUS_ATTRIBUTE, status);
		}
	}

	@Override
	protected String getObjectName() {
		return StringUtil.getContextualMessageForKey("sample.entry.test");
	}

	@Override
	protected boolean showAttribute() {
		return true;
	}
}
