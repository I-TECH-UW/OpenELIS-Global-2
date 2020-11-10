package org.openelisglobal.clientresultsview.dao;

import org.openelisglobal.clientresultsview.valueholder.ClientResultsViewBean;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;

@Component
public class ClientResultsViewInfoDAOImpl extends BaseDAOImpl<ClientResultsViewBean, Integer>
        implements ClientResultsViewInfoDAO {

    public ClientResultsViewInfoDAOImpl() {
        super(ClientResultsViewBean.class);
    }

}
