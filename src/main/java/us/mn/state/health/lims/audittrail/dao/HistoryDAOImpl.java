package us.mn.state.health.lims.audittrail.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
public class HistoryDAOImpl extends BaseDAOImpl<History> implements HistoryDAO {
  HistoryDAOImpl() {
    super(History.class);
  }
}
