package spring.service.dataexchange.order;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public interface ElectronicOrderService extends BaseObjectService<ElectronicOrder> {
	void updateData(ElectronicOrder eOrder);

	void insertData(ElectronicOrder eOrder);

	List<ElectronicOrder> getAllElectronicOrdersOrderedBy(ElectronicOrder.SortOrder order);

	List<ElectronicOrder> getAllElectronicOrders();

	List<ElectronicOrder> getElectronicOrdersByExternalId(String id);

	List<ElectronicOrder> getElectronicOrdersByPatientId(String id);
}
