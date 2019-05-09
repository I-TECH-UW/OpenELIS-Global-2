package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.Test_QaEventsDAO;
import us.mn.state.health.lims.qaevent.valueholder.Test_QaEvents;

@Service
public class Test_QaEventsServiceImpl extends BaseObjectServiceImpl<Test_QaEvents> implements Test_QaEventsService {
  @Autowired
  protected Test_QaEventsDAO baseObjectDAO;

  Test_QaEventsServiceImpl() {
    super(Test_QaEvents.class);
  }

  @Override
  protected Test_QaEventsDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
