package spring.service.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.dao.ResultsReportLabProjectDAO;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportLabProject;

@Service
public class ResultsReportLabProjectServiceImpl extends BaseObjectServiceImpl<ResultsReportLabProject> implements ResultsReportLabProjectService {
  @Autowired
  protected ResultsReportLabProjectDAO baseObjectDAO;

  ResultsReportLabProjectServiceImpl() {
    super(ResultsReportLabProject.class);
  }

  @Override
  protected ResultsReportLabProjectDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
