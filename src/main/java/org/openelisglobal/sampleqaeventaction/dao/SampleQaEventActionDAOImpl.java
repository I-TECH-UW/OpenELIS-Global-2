package org.openelisglobal.sampleqaeventaction.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.sampleqaeventaction.valueholder.SampleQaEventAction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SampleQaEventActionDAOImpl extends BaseDAOImpl<SampleQaEventAction, String>
    implements SampleQaEventActionDAO {
  SampleQaEventActionDAOImpl() {
    super(SampleQaEventAction.class);
  }
}
