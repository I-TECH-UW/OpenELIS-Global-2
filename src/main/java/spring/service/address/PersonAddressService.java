package spring.service.address;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.AddressPK;
import us.mn.state.health.lims.address.valueholder.PersonAddress;

public interface PersonAddressService extends BaseObjectService<PersonAddress, AddressPK> {

	@Override
	AddressPK insert(PersonAddress personAddress);

	List<PersonAddress> getAddressPartsByPersonId(String personId);

	PersonAddress getByPersonIdAndPartId(String personId, String addressPartId);
}
