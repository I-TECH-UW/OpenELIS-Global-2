package spring.service.organization;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;

public interface OrganizationContactService extends BaseObjectService<OrganizationContact, String> {
	String insert(OrganizationContact contact);

	List<OrganizationContact> getListForOrganizationId(String orgId);
}
