package spring.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.PersonAddressDAO;
import us.mn.state.health.lims.address.valueholder.PersonAddress;

@Service
public class PersonAddressServiceImpl extends BaseObjectServiceImpl<PersonAddress> implements PersonAddressService {
  @Autowired
  protected PersonAddressDAO baseObjectDAO;

  PersonAddressServiceImpl() {
    super(PersonAddress.class);
  }

  @Override
  protected PersonAddressDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
