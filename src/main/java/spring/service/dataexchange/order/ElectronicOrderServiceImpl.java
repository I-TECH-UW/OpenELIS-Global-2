package spring.service.dataexchange.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder.SortOrder;

@Service
public class ElectronicOrderServiceImpl extends BaseObjectServiceImpl<ElectronicOrder> implements ElectronicOrderService {
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
	public void updateData(ElectronicOrder eOrder) {
        getBaseObjectDAO().updateData(eOrder);

	}

	@Override
	public void insertData(ElectronicOrder eOrder) {
        getBaseObjectDAO().insertData(eOrder);

	}

	@Override
	public List<ElectronicOrder> getAllElectronicOrdersOrderedBy(SortOrder order) {
        return getBaseObjectDAO().getAllElectronicOrdersOrderedBy(order);
	}

	@Override
	public List<ElectronicOrder> getAllElectronicOrders() {
        return getBaseObjectDAO().getAllElectronicOrders();
	}

	@Override
	public List<ElectronicOrder> getElectronicOrdersByExternalId(String id) {
        return getBaseObjectDAO().getElectronicOrdersByExternalId(id);
	}

	@Override
	public List<ElectronicOrder> getElectronicOrdersByPatientId(String id) {
        return getBaseObjectDAO().getElectronicOrdersByPatientId(id);
	}
}
