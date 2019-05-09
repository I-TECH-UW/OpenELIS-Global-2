package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.ObservationXmit;

@Component
public class ObservationXmitDAOImpl extends BaseDAOImpl<ObservationXmit> implements ObservationXmitDAO {
  ObservationXmitDAOImpl() {
    super(ObservationXmit.class);
  }
}
