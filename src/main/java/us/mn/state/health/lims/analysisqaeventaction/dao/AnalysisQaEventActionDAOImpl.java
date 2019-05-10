package us.mn.state.health.lims.analysisqaeventaction.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.analysisqaeventaction.valueholder.AnalysisQaEventAction;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
public class AnalysisQaEventActionDAOImpl extends BaseDAOImpl<AnalysisQaEventAction> implements AnalysisQaEventActionDAO {
  AnalysisQaEventActionDAOImpl() {
    super(AnalysisQaEventAction.class);
  }
}
