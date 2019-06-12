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
import spring.service.referencetables.ReferenceTablesService;
import spring.service.sampleitem.SampleItemService;
import spring.util.SpringContext;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

public class SampleHistoryService extends AbstractHistoryService {
	
	protected ReferenceTablesService referenceTablesService = SpringContext.getBean(ReferenceTablesService.class);
	protected SampleItemService sampleItemService = SpringContext.getBean(SampleItemService.class);
	protected HistoryService historyService = SpringContext.getBean(HistoryService.class);
	
	private static String SAMPLE_ITEM_TABLE_ID;
		
	public SampleHistoryService(Sample sample) {
		SAMPLE_ITEM_TABLE_ID = referenceTablesService.getReferenceTableByName("SAMPLE_ITEM").getId();
		setUpForSample( sample );
	}
	
	@SuppressWarnings("unchecked")
	private void setUpForSample(Sample sample) {
		List<SampleItem> sampleItems = sampleItemService.getSampleItemsBySampleId(sample.getId()); 
		
		History searchHistory = new History();
		searchHistory.setReferenceTable(SAMPLE_ITEM_TABLE_ID);
		historyList = new ArrayList<History>();
		
		for( SampleItem item : sampleItems){
			searchHistory.setReferenceId(item.getId());
			historyList.addAll(historyService.getHistoryByRefIdAndRefTableId(searchHistory));
		}
		
		newValueMap = new HashMap<String, String>();
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		identifier = sampleItemService.getData(history.getReferenceId()).getTypeOfSample().getDescription();
		items.add(getCoreTrail(history));
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {
		SampleItem item = sampleItemService.getData(history.getReferenceId());
		String statusId = item.getStatusId();
		if( statusId != null){
			identifier = item.getTypeOfSample().getDescription();
			newValueMap.put(STATUS_ATTRIBUTE, StatusService.getInstance().getStatusNameFromId(statusId));
			changeMap.put(STATUS_ATTRIBUTE, "");
		}
	}

	@Override
	protected String getObjectName() {
		return MessageUtil.getMessage("sample.entry.sampleList.label");
	}
}
