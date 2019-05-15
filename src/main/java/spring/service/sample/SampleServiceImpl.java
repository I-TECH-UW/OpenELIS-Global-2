package spring.service.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
public class SampleServiceImpl extends BaseObjectServiceImpl<Sample> implements SampleService {
  @Autowired
  protected SampleDAO baseObjectDAO;

  SampleServiceImpl() {
    super(Sample.class);
  }

  @Override
  protected SampleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
