package us.mn.state.health.lims.reports.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportLabProject;

@Component
public class ResultsReportLabProjectDAOImpl extends BaseDAOImpl<ResultsReportLabProject> implements ResultsReportLabProjectDAO {
  ResultsReportLabProjectDAOImpl() {
    super(ResultsReportLabProject.class);
  }
}
