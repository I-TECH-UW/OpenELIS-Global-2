package spring.service.address;

import java.lang.String;
import java.util.List;
import java.util.Optional;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.common.valueholder.BaseObject;

public interface OrganizationAddressService extends BaseObjectService<OrganizationAddress> {

	String insert(OrganizationAddress organizationAddress);

	List<OrganizationAddress> getAddressPartsByOrganizationId(String organizationId);
}
