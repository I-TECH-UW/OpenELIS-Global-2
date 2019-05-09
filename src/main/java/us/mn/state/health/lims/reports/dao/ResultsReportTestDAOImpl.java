package us.mn.state.health.lims.reports.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportTest;

@Component
public class ResultsReportTestDAOImpl extends BaseDAOImpl<ResultsReportTest> implements ResultsReportTestDAO {
  ResultsReportTestDAOImpl() {
    super(ResultsReportTest.class);
  }
}
