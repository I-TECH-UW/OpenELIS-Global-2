package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.ResultsReportAnalyteResultDAO;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportAnalyteResult;

@Service
public class ResultsReportAnalyteResultServiceImpl extends BaseObjectServiceImpl<ResultsReportAnalyteResult> implements ResultsReportAnalyteResultService {
  @Autowired
  protected ResultsReportAnalyteResultDAO baseObjectDAO;

  ResultsReportAnalyteResultServiceImpl() {
    super(ResultsReportAnalyteResult.class);
  }

  @Override
  protected ResultsReportAnalyteResultDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
