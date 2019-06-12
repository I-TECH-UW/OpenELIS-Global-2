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

import spring.mine.internationalization.MessageUtil;
import spring.service.history.HistoryService;
import spring.service.result.ResultService;
import spring.service.result.ResultServiceImpl;
import spring.service.test.TestServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.result.valueholder.Result;

public class ResultHistoryService extends AbstractHistoryService {

	protected ResultService resultService = SpringContext.getBean(ResultService.class);
	protected HistoryService historyService = SpringContext.getBean(HistoryService.class);

	public ResultHistoryService(Result result, Analysis analysis) {
		setUpForResult(result, analysis);
	}

	@SuppressWarnings("unchecked")
	private void setUpForResult(Result result, Analysis analysis) {
		if (analysis.getTest() != null) {
			History searchHistory = new History();
			searchHistory.setReferenceId(result.getId());
			searchHistory.setReferenceTable(ResultServiceImpl.TABLE_REFERENCE_ID);
			historyList = historyService.getHistoryByRefIdAndRefTableId(searchHistory);

			newValueMap = new HashMap<>();
			newValueMap.put(VALUE_ATTRIBUTE, getViewableValue(result.getValue(), result));

			identifier = TestServiceImpl.getLocalizedTestNameWithType(analysis.getTest()) + " - "
					+ analysis.getAnalysisType();
		} else {
			historyList = new ArrayList<>();
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
			Result result = resultService.getResultById(history.getReferenceId());
			value = getViewableValue(value, result);

			if (value != null) {
				changeMap.put(VALUE_ATTRIBUTE, value);
			}
		}

	}

	@Override
	protected String getObjectName() {
		return MessageUtil.getMessage("sample.entry.project.result");
	}

	@Override
	protected boolean showAttribute() {
		return true;
	}

}
