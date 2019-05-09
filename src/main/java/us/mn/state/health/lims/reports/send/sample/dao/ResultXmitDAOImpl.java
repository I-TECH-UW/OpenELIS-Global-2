package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.ResultXmit;

@Component
public class ResultXmitDAOImpl extends BaseDAOImpl<ResultXmit> implements ResultXmitDAO {
  ResultXmitDAOImpl() {
    super(ResultXmit.class);
  }
}
