package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.UHLXmit;

@Component
public class UHLXmitDAOImpl extends BaseDAOImpl<UHLXmit> implements UHLXmitDAO {
  UHLXmitDAOImpl() {
    super(UHLXmit.class);
  }
}
