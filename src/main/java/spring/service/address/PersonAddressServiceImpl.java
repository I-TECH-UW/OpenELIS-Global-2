package spring.service.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.PersonAddressDAO;
import us.mn.state.health.lims.address.valueholder.AddressPK;
import us.mn.state.health.lims.address.valueholder.PersonAddress;

@Service
public class PersonAddressServiceImpl extends BaseObjectServiceImpl<PersonAddress, AddressPK>
		implements PersonAddressService {
	@Autowired
	protected PersonAddressDAO baseObjectDAO;

	PersonAddressServiceImpl() {
		super(PersonAddress.class);
	}

	@Override
	protected PersonAddressDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonAddress> getAddressPartsByPersonId(String personId) {
		return baseObjectDAO.getAllMatching("compoundId.targetId", personId);
	}

	@Override
	@Transactional(readOnly = true)
	public PersonAddress getByPersonIdAndPartId(String personId, String addressPartId) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("compoundId.targetId", personId);
		properties.put("compoundId.addressPartId", addressPartId);
		return getMatch(properties).orElse(null);
	}

	@Override
	public AddressPK insert(PersonAddress personAddress) {
		return super.insert(personAddress);
	}
}
