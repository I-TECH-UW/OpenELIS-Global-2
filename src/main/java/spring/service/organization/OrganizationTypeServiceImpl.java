package spring.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

@Service
public class OrganizationTypeServiceImpl extends BaseObjectServiceImpl<OrganizationType> implements OrganizationTypeService {
  @Autowired
  protected OrganizationTypeDAO baseObjectDAO;

  OrganizationTypeServiceImpl() {
    super(OrganizationType.class);
  }

  @Override
  protected OrganizationTypeDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
