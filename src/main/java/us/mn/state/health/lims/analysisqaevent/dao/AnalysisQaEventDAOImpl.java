package us.mn.state.health.lims.analysisqaevent.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.analysisqaevent.valueholder.AnalysisQaEvent;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
public class AnalysisQaEventDAOImpl extends BaseDAOImpl<AnalysisQaEvent> implements AnalysisQaEventDAO {
  AnalysisQaEventDAOImpl() {
    super(AnalysisQaEvent.class);
  }
}
