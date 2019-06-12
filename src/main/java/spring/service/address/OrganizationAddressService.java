package spring.service.address;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.AddressPK;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;

public interface OrganizationAddressService extends BaseObjectService<OrganizationAddress, AddressPK> {

	List<OrganizationAddress> getAddressPartsByOrganizationId(String organizationId);
}
