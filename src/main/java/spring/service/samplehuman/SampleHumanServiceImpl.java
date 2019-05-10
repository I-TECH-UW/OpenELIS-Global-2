package spring.service.samplehuman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

@Service
public class SampleHumanServiceImpl extends BaseObjectServiceImpl<SampleHuman> implements SampleHumanService {
  @Autowired
  protected SampleHumanDAO baseObjectDAO;

  SampleHumanServiceImpl() {
    super(SampleHuman.class);
  }

  @Override
  protected SampleHumanDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
