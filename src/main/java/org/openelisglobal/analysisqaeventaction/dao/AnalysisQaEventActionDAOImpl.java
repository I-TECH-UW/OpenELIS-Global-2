package org.openelisglobal.analysisqaeventaction.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.analysisqaeventaction.valueholder.AnalysisQaEventAction;
import  org.openelisglobal.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class AnalysisQaEventActionDAOImpl extends BaseDAOImpl<AnalysisQaEventAction, String> implements AnalysisQaEventActionDAO {
  AnalysisQaEventActionDAOImpl() {
    super(AnalysisQaEventAction.class);
  }
}
