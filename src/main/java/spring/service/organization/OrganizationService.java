package spring.service.organization;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.organization.valueholder.Organization;

public interface OrganizationService extends BaseObjectService<Organization> {

	Organization getOrganizationByName(Organization o, boolean b);

	List<String> getTypeIdsForOrganizationId(String id);

	void deleteAllLinksForOrganization(String id);

	void linkOrganizationAndType(Organization organization, String typeId);

	List<Organization> getPagesOfSearchedOrganizations(int startingRecNo, String searchString);

	int getTotalSearchedOrganizationCount(String searchString);

}
