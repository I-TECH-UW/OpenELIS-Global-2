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
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.ReportTrackingService.ReportType;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ReportHistoryService extends HistoryService {
	private static String REPORT_TABLE_ID;
	
	static {
		ReferenceTablesDAO tableDAO = new ReferenceTablesDAOImpl();
		REPORT_TABLE_ID = tableDAO.getReferenceTableByName("document_track").getId();
	}
	
	public ReportHistoryService(Sample sample) {
		setUpForReport(sample);
	}
	
	@SuppressWarnings("unchecked")
	private void setUpForReport(Sample sample) {
		List<DocumentTrack> documentList = new ReportTrackingService().getReportsForSample(sample, ReportType.PATIENT);

		historyList = new ArrayList<History>();
		for (DocumentTrack docTrack : documentList) {
			History searchHistory = new History();
			searchHistory.setReferenceId(docTrack.getId());
			searchHistory.setReferenceTable(REPORT_TABLE_ID);
			historyList.addAll(auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory));
		}
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		identifier = new ReportTrackingService().getDocumentForId(history.getReferenceId()).getDocumentName();
		AuditTrailItem item = getCoreTrail(history);
		items.add(item);
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {
		String status = extractStatus(changes);
		if (status != null) {
			changeMap.put(STATUS_ATTRIBUTE, status);
		}
		String value = extractSimple(changes, "value");
		if (value != null) {
			changeMap.put(VALUE_ATTRIBUTE, value);
		}
	}
	
	@Override
	protected String getObjectName() {
		return StringUtil.getMessageForKey("banner.menu.reports");
	}

}
