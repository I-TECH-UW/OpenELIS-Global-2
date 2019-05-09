package us.mn.state.health.lims.reports.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportSample;

@Component
public class ResultsReportSampleDAOImpl extends BaseDAOImpl<ResultsReportSample> implements ResultsReportSampleDAO {
  ResultsReportSampleDAOImpl() {
    super(ResultsReportSample.class);
  }
}
