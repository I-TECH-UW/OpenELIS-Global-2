package us.mn.state.health.lims.common.services.historyservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spring.mine.internationalization.MessageUtil;
import spring.service.history.HistoryService;
import spring.service.sampleqaevent.SampleQaEventService;
import spring.util.SpringContext;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class QaHistoryService extends AbstractHistoryService {
	
	protected SampleQaEventService sampleQaEventService = SpringContext.getBean(SampleQaEventService.class);
	protected HistoryService historyService = SpringContext.getBean(HistoryService.class);

	public QaHistoryService(Sample sample) {
		setUpForSample( sample );
	}

	@SuppressWarnings("unchecked")
	private void setUpForSample(Sample sample) {
		List<SampleQaEvent> qaEventList =  new SampleService( sample ).getSampleQAEventList();
		
		History searchHistory = new History();
		searchHistory.setReferenceTable( QAService.TABLE_REFERENCE_ID);
		historyList = new ArrayList<History>();
		
		for( SampleQaEvent event : qaEventList){
			searchHistory.setReferenceId(event.getId());
			historyList.addAll(historyService.getHistoryByRefIdAndRefTableId(searchHistory));
		}
		
		newValueMap = new HashMap<String, String>();
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		identifier =  sampleQaEventService.getData(history.getReferenceId()).getQaEvent().getLocalizedName();
		items.add(getCoreTrail(history));
	}

	@Override
	protected String getObjectName() {
		return MessageUtil.getMessage("qaevent.browse.title");
	}

	@Override
	protected void getObservableChanges(History history,	Map<String, String> changeMap, String changes) {
			changeMap.put(STATUS_ATTRIBUTE, "Gail");

	}

}
