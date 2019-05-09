package spring.service.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.action.dao.ActionDAO;
import us.mn.state.health.lims.action.valueholder.Action;

@Service
public class ActionServiceImpl extends BaseObjectServiceImpl<Action> implements ActionService {
  @Autowired
  protected ActionDAO baseObjectDAO;

  ActionServiceImpl() {
    super(Action.class);
  }

  @Override
  protected ActionDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
