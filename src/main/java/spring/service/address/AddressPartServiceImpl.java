package spring.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.valueholder.AddressPart;

@Service
public class AddressPartServiceImpl extends BaseObjectServiceImpl<AddressPart> implements AddressPartService {
  @Autowired
  protected AddressPartDAO baseObjectDAO;

  AddressPartServiceImpl() {
    super(AddressPart.class);
  }

  @Override
  protected AddressPartDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
