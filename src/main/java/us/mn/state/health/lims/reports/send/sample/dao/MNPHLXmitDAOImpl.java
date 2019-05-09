package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.MNPHLXmit;

@Component
public class MNPHLXmitDAOImpl extends BaseDAOImpl<MNPHLXmit> implements MNPHLXmitDAO {
  MNPHLXmitDAOImpl() {
    super(MNPHLXmit.class);
  }
}
