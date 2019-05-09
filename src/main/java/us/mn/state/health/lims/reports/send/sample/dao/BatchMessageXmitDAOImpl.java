package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.BatchMessageXmit;

@Component
public class BatchMessageXmitDAOImpl extends BaseDAOImpl<BatchMessageXmit> implements BatchMessageXmitDAO {
  BatchMessageXmitDAOImpl() {
    super(BatchMessageXmit.class);
  }
}
