package org.openelisglobal.dataexchange.service.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.order.dao.ElectronicOrderDAO;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElectronicOrderServiceImpl extends BaseObjectServiceImpl<ElectronicOrder, String>
        implements ElectronicOrderService {
    @Autowired
    protected ElectronicOrderDAO baseObjectDAO;
    @Autowired
    protected IStatusService statusService;

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

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersContainingValueOrderedBy(String searchValue, SortOrder order) {
        return getBaseObjectDAO().getAllElectronicOrdersContainingValueOrderedBy(searchValue, order);
    }

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersContainingValuesOrderedBy(String accessionNumber,
            String patientLastName, String patientFirstName, String gender, SortOrder order) {
        return getBaseObjectDAO().getAllElectronicOrdersContainingValuesOrderedBy(accessionNumber, patientLastName,
                patientFirstName, gender, order);
    }

    @Override
    public List<ElectronicOrder> getElectronicOrdersContainingValueExludedByOrderedBy(String searchValue,
            List<ExternalOrderStatus> excludedStatuses, SortOrder sortOrder) {
        List<Integer> exludedStatusIds = new ArrayList<>();
        for (ExternalOrderStatus status : excludedStatuses) {
            String statusId = statusService.getStatusID(status);
            if (!GenericValidator.isBlankOrNull(statusId)) {
                exludedStatusIds.add(Integer.parseInt(statusId));
            }
        }

        return getBaseObjectDAO().getElectronicOrdersContainingValueExludedByOrderedBy(searchValue, exludedStatusIds,
                sortOrder);
    }

}
