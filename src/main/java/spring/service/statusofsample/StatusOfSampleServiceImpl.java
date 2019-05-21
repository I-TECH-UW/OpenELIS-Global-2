package spring.service.statusofsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

@Service
public class StatusOfSampleServiceImpl extends BaseObjectServiceImpl<StatusOfSample> implements StatusOfSampleService {
  @Autowired
  protected StatusOfSampleDAO baseObjectDAO;

  StatusOfSampleServiceImpl() {
    super(StatusOfSample.class);
  }

  @Override
  protected StatusOfSampleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
