package org.openelisglobal.dataexchange.service.order;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;

public interface PortableOrderService extends BaseObjectService<PortableOrder, String> {

    List<PortableOrder> getAllPortableOrdersOrderedBy(PortableOrder.SortOrder order);

    List<PortableOrder> getPortableOrdersByExternalId(String id);

}
