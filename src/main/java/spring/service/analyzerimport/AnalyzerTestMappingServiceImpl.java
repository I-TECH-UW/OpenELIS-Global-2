package spring.service.analyzerimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;

@Service
public class AnalyzerTestMappingServiceImpl extends BaseObjectServiceImpl<AnalyzerTestMapping> implements AnalyzerTestMappingService {
  @Autowired
  protected AnalyzerTestMappingDAO baseObjectDAO;

  AnalyzerTestMappingServiceImpl() {
    super(AnalyzerTestMapping.class);
  }

  @Override
  protected AnalyzerTestMappingDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
