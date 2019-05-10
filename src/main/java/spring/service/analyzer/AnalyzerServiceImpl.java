package spring.service.analyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;

@Service
public class AnalyzerServiceImpl extends BaseObjectServiceImpl<Analyzer> implements AnalyzerService {
  @Autowired
  protected AnalyzerDAO baseObjectDAO;

  AnalyzerServiceImpl() {
    super(Analyzer.class);
  }

  @Override
  protected AnalyzerDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
