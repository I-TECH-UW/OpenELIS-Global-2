package spring.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.organization.dao.OrganizationContactDAO;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;

@Service
public class OrganizationContactServiceImpl extends BaseObjectServiceImpl<OrganizationContact> implements OrganizationContactService {
  @Autowired
  protected OrganizationContactDAO baseObjectDAO;

  OrganizationContactServiceImpl() {
    super(OrganizationContact.class);
  }

  @Override
  protected OrganizationContactDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
