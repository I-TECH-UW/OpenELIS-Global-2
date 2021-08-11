package org.openelisglobal.dataexchange.service.order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.order.dao.ElectronicOrderDAO;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder.SortOrder;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.test.service.TestService;
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
    @Autowired
    protected OrganizationService organizationService;
    @Autowired
    protected TestService testService;
    @Autowired
    protected FhirUtil fhirUtil;

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

        List<ElectronicOrder> searchResult = getBaseObjectDAO()
                .getAllElectronicOrdersContainingValueOrderedBy(searchValue, order);

        if (searchResult != null && searchResult.size() > 0) {
            return searchResult;
        }
        // this is done in case sample lab number was used to search instead of the
        // order lab number
        if (searchValue != null && searchValue.contains(".")) {
            searchValue = searchValue.substring(0, searchValue.indexOf('.'));
        }
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

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersByDateAndStatus(Date startDate, Date endDate, String statusId,
            SortOrder sortOrder) {
        return getBaseObjectDAO().getAllElectronicOrdersByDateAndStatus(startDate, endDate, statusId, sortOrder);
    }

    @Override
    public List<ElectronicOrder> getAllElectronicOrdersByTimestampAndStatus(Timestamp startTimestamp,
            Timestamp endTimestamp, String statusId, SortOrder sortOrder) {
        return getBaseObjectDAO().getAllElectronicOrdersByTimestampAndStatus(startTimestamp, endTimestamp, statusId,
                sortOrder);
    }

}
