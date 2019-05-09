package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.Test_QaEvent_ActionsDAO;
import us.mn.state.health.lims.qaevent.valueholder.Test_QaEvent_Actions;

@Service
public class Test_QaEvent_ActionsServiceImpl extends BaseObjectServiceImpl<Test_QaEvent_Actions> implements Test_QaEvent_ActionsService {
  @Autowired
  protected Test_QaEvent_ActionsDAO baseObjectDAO;

  Test_QaEvent_ActionsServiceImpl() {
    super(Test_QaEvent_Actions.class);
  }

  @Override
  protected Test_QaEvent_ActionsDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
