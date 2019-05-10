package us.mn.state.health.lims.citystatezip.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.citystatezip.valueholder.StateView;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
public class StateViewDAOImpl extends BaseDAOImpl<StateView> implements StateViewDAO {
  StateViewDAOImpl() {
    super(StateView.class);
  }
}
