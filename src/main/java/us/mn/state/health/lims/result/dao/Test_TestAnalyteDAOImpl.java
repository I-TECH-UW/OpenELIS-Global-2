package us.mn.state.health.lims.result.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.result.valueholder.Test_TestAnalyte;

@Component
public class Test_TestAnalyteDAOImpl extends BaseDAOImpl<Test_TestAnalyte> implements Test_TestAnalyteDAO {
  Test_TestAnalyteDAOImpl() {
    super(Test_TestAnalyte.class);
  }
}
