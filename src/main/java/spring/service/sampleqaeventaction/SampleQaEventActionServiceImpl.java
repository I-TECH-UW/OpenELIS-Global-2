package spring.service.sampleqaeventaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleqaeventaction.dao.SampleQaEventActionDAO;
import us.mn.state.health.lims.sampleqaeventaction.valueholder.SampleQaEventAction;

@Service
public class SampleQaEventActionServiceImpl extends BaseObjectServiceImpl<SampleQaEventAction> implements SampleQaEventActionService {
	@Autowired
	protected SampleQaEventActionDAO baseObjectDAO;

	SampleQaEventActionServiceImpl() {
		super(SampleQaEventAction.class);
	}

	@Override
	protected SampleQaEventActionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
