package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.QaEventDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;

@Service
public class QaEventServiceImpl extends BaseObjectServiceImpl<QaEvent> implements QaEventService {
  @Autowired
  protected QaEventDAO baseObjectDAO;

  QaEventServiceImpl() {
    super(QaEvent.class);
  }

  @Override
  protected QaEventDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
