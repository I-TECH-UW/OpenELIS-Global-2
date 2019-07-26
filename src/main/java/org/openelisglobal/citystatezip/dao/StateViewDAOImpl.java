package org.openelisglobal.citystatezip.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.citystatezip.valueholder.StateView;
import  org.openelisglobal.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class StateViewDAOImpl extends BaseDAOImpl<StateView, String> implements StateViewDAO {
  StateViewDAOImpl() {
    super(StateView.class);
  }
}
