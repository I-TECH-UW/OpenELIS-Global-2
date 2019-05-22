package us.mn.state.health.lims.audittrail.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class HistoryDAOImpl extends BaseDAOImpl<History> implements HistoryDAO {
  HistoryDAOImpl() {
    super(History.class);
  }
}
