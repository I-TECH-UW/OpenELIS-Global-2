package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.ObservationXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.ObservationXmit;

@Service
public class ObservationXmitServiceImpl extends BaseObjectServiceImpl<ObservationXmit> implements ObservationXmitService {
  @Autowired
  protected ObservationXmitDAO baseObjectDAO;

  ObservationXmitServiceImpl() {
    super(ObservationXmit.class);
  }

  @Override
  protected ObservationXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
