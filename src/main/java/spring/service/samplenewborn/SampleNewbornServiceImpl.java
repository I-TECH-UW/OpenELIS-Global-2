package spring.service.samplenewborn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.samplenewborn.dao.SampleNewbornDAO;
import us.mn.state.health.lims.samplenewborn.valueholder.SampleNewborn;

@Service
public class SampleNewbornServiceImpl extends BaseObjectServiceImpl<SampleNewborn> implements SampleNewbornService {
  @Autowired
  protected SampleNewbornDAO baseObjectDAO;

  SampleNewbornServiceImpl() {
    super(SampleNewborn.class);
  }

  @Override
  protected SampleNewbornDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
