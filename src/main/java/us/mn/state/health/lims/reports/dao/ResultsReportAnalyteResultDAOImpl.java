package us.mn.state.health.lims.reports.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportAnalyteResult;

@Component
public class ResultsReportAnalyteResultDAOImpl extends BaseDAOImpl<ResultsReportAnalyteResult> implements ResultsReportAnalyteResultDAO {
  ResultsReportAnalyteResultDAOImpl() {
    super(ResultsReportAnalyteResult.class);
  }
}
