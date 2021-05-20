package org.openelisglobal.dataexchange.service.order;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder.SortOrder;

public interface ElectronicOrderService extends BaseObjectService<ElectronicOrder, String> {

    List<ElectronicOrder> getAllElectronicOrdersOrderedBy(ElectronicOrder.SortOrder order);

    List<ElectronicOrder> getElectronicOrdersByExternalId(String id);

    List<ElectronicOrder> getAllElectronicOrdersContainingValueOrderedBy(String searchValue, SortOrder sortOrder);

    List<ElectronicOrder> getAllElectronicOrdersContainingValuesOrderedBy(String accessionNumber,
            String patientLastName, String patientFirstName, String gender, SortOrder order);

    List<ElectronicOrder> getElectronicOrdersContainingValueExludedByOrderedBy(String searchValue,
            List<ExternalOrderStatus> excludedStatuses, SortOrder sortOrder);

}
