package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.Test_TestAnalyteDAO;
import us.mn.state.health.lims.result.valueholder.Test_TestAnalyte;

@Service
public class Test_TestAnalyteServiceImpl extends BaseObjectServiceImpl<Test_TestAnalyte> implements Test_TestAnalyteService {
  @Autowired
  protected Test_TestAnalyteDAO baseObjectDAO;

  Test_TestAnalyteServiceImpl() {
    super(Test_TestAnalyte.class);
  }

  @Override
  protected Test_TestAnalyteDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
