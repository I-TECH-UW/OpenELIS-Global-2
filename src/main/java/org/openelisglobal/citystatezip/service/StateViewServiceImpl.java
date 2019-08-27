package org.openelisglobal.citystatezip.service;

import org.openelisglobal.citystatezip.dao.StateViewDAO;
import org.openelisglobal.citystatezip.valueholder.StateView;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateViewServiceImpl extends BaseObjectServiceImpl<StateView, String> implements StateViewService {
    @Autowired
    protected StateViewDAO baseObjectDAO;

    StateViewServiceImpl() {
        super(StateView.class);
    }

    @Override
    protected StateViewDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
