package spring.service.testtrailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testtrailer.dao.TestTrailerDAO;
import us.mn.state.health.lims.testtrailer.valueholder.TestTrailer;

@Service
public class TestTrailerServiceImpl extends BaseObjectServiceImpl<TestTrailer> implements TestTrailerService {
  @Autowired
  protected TestTrailerDAO baseObjectDAO;

  TestTrailerServiceImpl() {
    super(TestTrailer.class);
  }

  @Override
  protected TestTrailerDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
