package org.openelisglobal.citystatezip.dao;

import org.openelisglobal.citystatezip.valueholder.StateView;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StateViewDAOImpl extends BaseDAOImpl<StateView, String> implements StateViewDAO {
    StateViewDAOImpl() {
        super(StateView.class);
    }
}
