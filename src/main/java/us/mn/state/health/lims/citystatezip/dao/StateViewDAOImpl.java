package us.mn.state.health.lims.citystatezip.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.citystatezip.valueholder.StateView;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class StateViewDAOImpl extends BaseDAOImpl<StateView, String> implements StateViewDAO {
  StateViewDAOImpl() {
    super(StateView.class);
  }
}
