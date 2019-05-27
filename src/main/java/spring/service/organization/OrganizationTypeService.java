package spring.service.organization;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

public interface OrganizationTypeService extends BaseObjectService<OrganizationType> {
	List<Organization> getOrganizationsByTypeName(String orderByCol, String[] names);

	List<OrganizationType> getAllOrganizationTypes();

	OrganizationType getOrganizationTypeByName(String name);
}
