package us.mn.state.health.lims.result.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.result.valueholder.Sample_TestAnalyte;

@Component
public class Sample_TestAnalyteDAOImpl extends BaseDAOImpl<Sample_TestAnalyte> implements Sample_TestAnalyteDAO {
  Sample_TestAnalyteDAOImpl() {
    super(Sample_TestAnalyte.class);
  }
}
