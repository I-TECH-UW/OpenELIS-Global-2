package us.mn.state.health.lims.action.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.action.valueholder.Action;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
public class ActionDAOImpl extends BaseDAOImpl<Action> implements ActionDAO {
  ActionDAOImpl() {
    super(Action.class);
  }
}
