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
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;

public class ResultHistoryService extends HistoryService {
	private static ResultDAO resultDAO = new ResultDAOImpl();

	public ResultHistoryService(Result result, Analysis analysis) {
		setUpForResult(result, analysis);
	}

	@SuppressWarnings("unchecked")
	private void setUpForResult(Result result, Analysis analysis) {
		if ( analysis.getTest() != null) {
			History searchHistory = new History();
			searchHistory.setReferenceId(result.getId());
			searchHistory.setReferenceTable( ResultService.TABLE_REFERENCE_ID);
			historyList = auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory);

			newValueMap = new HashMap<String, String>();
			newValueMap.put(VALUE_ATTRIBUTE, getViewableValue(result.getValue(), result));

			identifier = TestService.getLocalizedTestNameWithType( analysis.getTest() ) + " - " + analysis.getAnalysisType();
		} else {
			historyList = new ArrayList<History>();
		}
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		AuditTrailItem item = getCoreTrail(history);
		item.setNewValue(newValueMap.get(VALUE_ATTRIBUTE));
		items.add(item);
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {
		String value = extractSimple(changes, "value");
		if (value != null) {
			Result result = resultDAO.getResultById(history.getReferenceId());
			value = getViewableValue(value, result);

			if (value != null) {
				changeMap.put(VALUE_ATTRIBUTE, value);
			}
		}

	}

	@Override
	protected String getObjectName() {
		return StringUtil.getMessageForKey("sample.entry.project.result");
	}

	@Override
	protected boolean showAttribute() {
		return true;
	}

}
