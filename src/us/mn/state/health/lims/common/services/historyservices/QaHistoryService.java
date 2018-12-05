package us.mn.state.health.lims.common.services.historyservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class QaHistoryService extends HistoryService {

	private SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();
	
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
			historyList.addAll(auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory));
		}
		
		newValueMap = new HashMap<String, String>();
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		identifier =  sampleQaEventDAO.getData(history.getReferenceId()).getQaEvent().getLocalizedName();
		items.add(getCoreTrail(history));
	}

	@Override
	protected String getObjectName() {
		return StringUtil.getMessageForKey("qaevent.browse.title");
	}

	@Override
	protected void getObservableChanges(History history,	Map<String, String> changeMap, String changes) {
			changeMap.put(STATUS_ATTRIBUTE, "Gail");

	}

}
