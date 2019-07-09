package us.mn.state.health.lims.analysisqaeventaction.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.analysisqaeventaction.valueholder.AnalysisQaEventAction;
import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class AnalysisQaEventActionDAOImpl extends BaseDAOImpl<AnalysisQaEventAction, String> implements AnalysisQaEventActionDAO {
  AnalysisQaEventActionDAOImpl() {
    super(AnalysisQaEventAction.class);
  }
}
