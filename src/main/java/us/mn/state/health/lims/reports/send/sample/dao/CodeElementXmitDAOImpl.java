package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.CodeElementXmit;

@Component
public class CodeElementXmitDAOImpl extends BaseDAOImpl<CodeElementXmit> implements CodeElementXmitDAO {
  CodeElementXmitDAOImpl() {
    super(CodeElementXmit.class);
  }
}
