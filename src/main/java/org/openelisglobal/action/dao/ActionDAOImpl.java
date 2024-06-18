package org.openelisglobal.action.dao;

import org.openelisglobal.action.valueholder.Action;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ActionDAOImpl extends BaseDAOImpl<Action, String> implements ActionDAO {
  ActionDAOImpl() {
    super(Action.class);
  }
}
