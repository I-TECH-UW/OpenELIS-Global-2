package spring.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.valueholder.AddressPart;

@Service
public class AddressPartServiceImpl extends BaseObjectServiceImpl<AddressPart, String> implements AddressPartService {
	@Autowired
	protected AddressPartDAO baseObjectDAO;

	public AddressPartServiceImpl() {
		super(AddressPart.class);
	}

	@Override
	protected AddressPartDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public AddressPart getAddresPartByName(String name) {
		return getBaseObjectDAO().getAddresPartByName(name);
	}
}
