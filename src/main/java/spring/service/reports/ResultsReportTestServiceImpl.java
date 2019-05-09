package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.ResultsReportTestDAO;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportTest;

@Service
public class ResultsReportTestServiceImpl extends BaseObjectServiceImpl<ResultsReportTest> implements ResultsReportTestService {
  @Autowired
  protected ResultsReportTestDAO baseObjectDAO;

  ResultsReportTestServiceImpl() {
    super(ResultsReportTest.class);
  }

  @Override
  protected ResultsReportTestDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
