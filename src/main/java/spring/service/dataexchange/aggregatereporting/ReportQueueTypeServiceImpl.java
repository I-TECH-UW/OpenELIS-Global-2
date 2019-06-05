package spring.service.dataexchange.aggregatereporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportQueueTypeDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;

@Service
public class ReportQueueTypeServiceImpl extends BaseObjectServiceImpl<ReportQueueType, String> implements ReportQueueTypeService {
	@Autowired
	protected ReportQueueTypeDAO baseObjectDAO;

	ReportQueueTypeServiceImpl() {
		super(ReportQueueType.class);
	}

	@Override
	protected ReportQueueTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public ReportQueueType getReportQueueTypeByName(String name) {
        return getBaseObjectDAO().getReportQueueTypeByName(name);
	}
}
