package spring.service.analysisqaeventaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analysisqaeventaction.dao.AnalysisQaEventActionDAO;
import us.mn.state.health.lims.analysisqaeventaction.valueholder.AnalysisQaEventAction;

@Service
public class AnalysisQaEventActionServiceImpl extends BaseObjectServiceImpl<AnalysisQaEventAction> implements AnalysisQaEventActionService {
	@Autowired
	protected AnalysisQaEventActionDAO baseObjectDAO;

	AnalysisQaEventActionServiceImpl() {
		super(AnalysisQaEventAction.class);
	}

	@Override
	protected AnalysisQaEventActionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
