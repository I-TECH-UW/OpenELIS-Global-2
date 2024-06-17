package org.openelisglobal.analysisqaeventaction.dao;

import org.openelisglobal.analysisqaeventaction.valueholder.AnalysisQaEventAction;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AnalysisQaEventActionDAOImpl extends BaseDAOImpl<AnalysisQaEventAction, String>
    implements AnalysisQaEventActionDAO {
  AnalysisQaEventActionDAOImpl() {
    super(AnalysisQaEventAction.class);
  }
}
