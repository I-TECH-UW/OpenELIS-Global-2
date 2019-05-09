package us.mn.state.health.lims.qaevent.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.Test_QaEvents;

@Component
public class Test_QaEventsDAOImpl extends BaseDAOImpl<Test_QaEvents> implements Test_QaEventsDAO {
  Test_QaEventsDAOImpl() {
    super(Test_QaEvents.class);
  }
}
