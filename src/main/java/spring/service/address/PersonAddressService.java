package spring.service.address;

import java.lang.String;
import java.util.List;
import java.util.Optional;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.valueholder.BaseObject;

public interface PersonAddressService extends BaseObjectService<PersonAddress> {

	String insert(PersonAddress personAddress);

	List<PersonAddress> getAddressPartsByPersonId(String personId);

	PersonAddress getByPersonIdAndPartId(String personId, String addressPartId);
}
