package spring.service.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.valueholder.Analysis;

@Service
public class AnalysisServiceImpl extends BaseObjectServiceImpl<Analysis> implements AnalysisService {
  @Autowired
  protected AnalysisDAO baseObjectDAO;

  AnalysisServiceImpl() {
    super(Analysis.class);
  }

  @Override
  protected AnalysisDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
