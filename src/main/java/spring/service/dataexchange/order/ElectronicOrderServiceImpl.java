package spring.service.dataexchange.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder.SortOrder;

@Service
public class ElectronicOrderServiceImpl extends BaseObjectServiceImpl<ElectronicOrder, String>
		implements ElectronicOrderService {
	@Autowired
	protected ElectronicOrderDAO baseObjectDAO;

	ElectronicOrderServiceImpl() {
		super(ElectronicOrder.class);
	}

	@Override
	protected ElectronicOrderDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElectronicOrder> getAllElectronicOrdersOrderedBy(SortOrder order) {
		return getBaseObjectDAO().getAllElectronicOrdersOrderedBy(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElectronicOrder> getElectronicOrdersByExternalId(String id) {
		return getBaseObjectDAO().getElectronicOrdersByExternalId(id);
	}

}
