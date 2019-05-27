package spring.service.analysisqaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analysisqaevent.dao.AnalysisQaEventDAO;
import us.mn.state.health.lims.analysisqaevent.valueholder.AnalysisQaEvent;

@Service
public class AnalysisQaEventServiceImpl extends BaseObjectServiceImpl<AnalysisQaEvent> implements AnalysisQaEventService {
	@Autowired
	protected AnalysisQaEventDAO baseObjectDAO;

	AnalysisQaEventServiceImpl() {
		super(AnalysisQaEvent.class);
	}

	@Override
	protected AnalysisQaEventDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
