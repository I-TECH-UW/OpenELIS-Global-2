package spring.service.address;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.PersonAddress;

public interface PersonAddressService extends BaseObjectService<PersonAddress> {

	List<PersonAddress> getAddressPartsByPersonId(String id);
}
