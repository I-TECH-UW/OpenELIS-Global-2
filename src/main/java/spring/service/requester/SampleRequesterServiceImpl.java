package spring.service.requester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

@Service
public class SampleRequesterServiceImpl extends BaseObjectServiceImpl<SampleRequester> implements SampleRequesterService {
  @Autowired
  protected SampleRequesterDAO baseObjectDAO;

  SampleRequesterServiceImpl() {
    super(SampleRequester.class);
  }

  @Override
  protected SampleRequesterDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
