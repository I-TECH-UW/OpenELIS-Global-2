package spring.service.address;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.AddressPart;

public interface AddressPartService extends BaseObjectService<AddressPart, String> {
	List<AddressPart> getAll();

	AddressPart getAddresPartByName(String name);
}
