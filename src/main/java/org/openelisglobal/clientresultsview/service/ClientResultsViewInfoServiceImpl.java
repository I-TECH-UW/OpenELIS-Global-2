package org.openelisglobal.clientresultsview.service;

import org.openelisglobal.clientresultsview.dao.ClientResultsViewInfoDAO;
import org.openelisglobal.clientresultsview.valueholder.ClientResultsViewBean;
import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientResultsViewInfoServiceImpl extends BaseObjectServiceImpl<ClientResultsViewBean, Integer>
        implements ClientResultsViewInfoService {

    @Autowired
    private ClientResultsViewInfoDAO baseObjectDAO;

    public ClientResultsViewInfoServiceImpl() {
        super(ClientResultsViewBean.class);
        this.auditTrailLog = false;
    }

    @Override
    protected BaseDAO<ClientResultsViewBean, Integer> getBaseObjectDAO() {
        return baseObjectDAO;
    }

}
