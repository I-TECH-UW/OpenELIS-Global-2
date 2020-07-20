package org.openelisglobal.dataexchange.service.order;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.dataexchange.order.dao.PortableOrderDAO;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortableOrderServiceImpl extends BaseObjectServiceImpl<PortableOrder, String>
        implements PortableOrderService {
    @Autowired
    protected PortableOrderDAO baseObjectDAO;

    PortableOrderServiceImpl() {
        super(PortableOrder.class);
    }

    @Override
    protected PortableOrderDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortableOrder> getAllPortableOrdersOrderedBy(SortOrder order) {
        return getBaseObjectDAO().getAllPortableOrdersOrderedBy(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortableOrder> getPortableOrdersByExternalId(String id) {
        return getBaseObjectDAO().getPortableOrdersByExternalId(id);
    }

}
