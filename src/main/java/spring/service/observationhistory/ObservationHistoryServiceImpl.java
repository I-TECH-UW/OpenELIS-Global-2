package spring.service.observationhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;

@Service
public class ObservationHistoryServiceImpl extends BaseObjectServiceImpl<ObservationHistory> implements ObservationHistoryService {
  @Autowired
  protected ObservationHistoryDAO baseObjectDAO;

  ObservationHistoryServiceImpl() {
    super(ObservationHistory.class);
  }

  @Override
  protected ObservationHistoryDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
