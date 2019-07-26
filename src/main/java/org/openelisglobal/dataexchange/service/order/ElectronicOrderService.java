package org.openelisglobal.dataexchange.service.order;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;

public interface ElectronicOrderService extends BaseObjectService<ElectronicOrder, String> {

	List<ElectronicOrder> getAllElectronicOrdersOrderedBy(ElectronicOrder.SortOrder order);

	List<ElectronicOrder> getElectronicOrdersByExternalId(String id);

}
