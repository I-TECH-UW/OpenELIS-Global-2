package spring.service.dataexchange.order;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public interface ElectronicOrderService extends BaseObjectService<ElectronicOrder, String> {

	List<ElectronicOrder> getAllElectronicOrdersOrderedBy(ElectronicOrder.SortOrder order);

	List<ElectronicOrder> getElectronicOrdersByExternalId(String id);

}
