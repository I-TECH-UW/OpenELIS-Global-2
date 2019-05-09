package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.ResultsReportSampleDAO;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportSample;

@Service
public class ResultsReportSampleServiceImpl extends BaseObjectServiceImpl<ResultsReportSample> implements ResultsReportSampleService {
  @Autowired
  protected ResultsReportSampleDAO baseObjectDAO;

  ResultsReportSampleServiceImpl() {
    super(ResultsReportSample.class);
  }

  @Override
  protected ResultsReportSampleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
