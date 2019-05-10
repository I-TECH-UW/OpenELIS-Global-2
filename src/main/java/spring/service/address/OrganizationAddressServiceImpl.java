package spring.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;

@Service
public class OrganizationAddressServiceImpl extends BaseObjectServiceImpl<OrganizationAddress> implements OrganizationAddressService {
  @Autowired
  protected OrganizationAddressDAO baseObjectDAO;

  OrganizationAddressServiceImpl() {
    super(OrganizationAddress.class);
  }

  @Override
  protected OrganizationAddressDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
