package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.Sample_TestAnalyteDAO;
import us.mn.state.health.lims.result.valueholder.Sample_TestAnalyte;

@Service
public class Sample_TestAnalyteServiceImpl extends BaseObjectServiceImpl<Sample_TestAnalyte> implements Sample_TestAnalyteService {
  @Autowired
  protected Sample_TestAnalyteDAO baseObjectDAO;

  Sample_TestAnalyteServiceImpl() {
    super(Sample_TestAnalyte.class);
  }

  @Override
  protected Sample_TestAnalyteDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
