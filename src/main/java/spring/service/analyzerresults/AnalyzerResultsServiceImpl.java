package spring.service.analyzerresults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;

@Service
public class AnalyzerResultsServiceImpl extends BaseObjectServiceImpl<AnalyzerResults> implements AnalyzerResultsService {
  @Autowired
  protected AnalyzerResultsDAO baseObjectDAO;

  AnalyzerResultsServiceImpl() {
    super(AnalyzerResults.class);
  }

  @Override
  protected AnalyzerResultsDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
