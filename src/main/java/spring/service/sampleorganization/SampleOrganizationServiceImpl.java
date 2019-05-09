package spring.service.sampleorganization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

@Service
public class SampleOrganizationServiceImpl extends BaseObjectServiceImpl<SampleOrganization> implements SampleOrganizationService {
  @Autowired
  protected SampleOrganizationDAO baseObjectDAO;

  SampleOrganizationServiceImpl() {
    super(SampleOrganization.class);
  }

  @Override
  protected SampleOrganizationDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
