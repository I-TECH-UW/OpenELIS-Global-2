package spring.service.organization;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;

public interface OrganizationContactService extends BaseObjectService<OrganizationContact> {
	String insert(OrganizationContact contact);

	List<OrganizationContact> getListForOrganizationId(String orgId);
}
