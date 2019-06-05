package us.mn.state.health.lims.analysisqaevent.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.analysisqaevent.valueholder.AnalysisQaEvent;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
@Transactional 
public class AnalysisQaEventDAOImpl extends BaseDAOImpl<AnalysisQaEvent, String> implements AnalysisQaEventDAO {
  AnalysisQaEventDAOImpl() {
    super(AnalysisQaEvent.class);
  }
}
