package us.mn.state.health.lims.qaevent.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.Test_QaEvent_Actions;

@Component
public class Test_QaEvent_ActionsDAOImpl extends BaseDAOImpl<Test_QaEvent_Actions> implements Test_QaEvent_ActionsDAO {
  Test_QaEvent_ActionsDAOImpl() {
    super(Test_QaEvent_Actions.class);
  }
}
