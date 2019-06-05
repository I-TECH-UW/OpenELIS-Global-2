package spring.service.organization;

import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.organization.valueholder.Organization;

public interface OrganizationService extends BaseObjectService<Organization, String> {
	void getData(Organization organization);

	void deleteData(List organizations);

	void updateData(Organization organization);

	boolean insertData(Organization organization);

	void insertOrUpdateData(Organization organization);

	List getNextOrganizationRecord(String id);

	Organization getOrganizationByName(Organization organization, boolean ignoreCase);

	List<Organization> getOrganizationsByParentId(String parentId);

	List<Organization> getOrganizationsByTypeName(String orderByProperty, String[] typeName);

	Set<Organization> getOrganizationsByProjectName(String projectName);

	Integer getTotalOrganizationCount();

	List getAllOrganizations();

	List getPreviousOrganizationRecord(String id);

	List getPagesOfSearchedOrganizations(int startRecNo, String searchString);

	Organization getOrganizationById(String organizationId);

	List getPageOfOrganizations(int startingRecNo);

	List getOrganizations(String filter);

	List<Organization> getOrganizationsByTypeNameAndLeadingChars(String partialName, String typeName);

	Organization getOrganizationByLocalAbbreviation(Organization organization, boolean ignoreCase);

	Integer getTotalSearchedOrganizationCount(String searchString);

	void linkOrganizationAndType(Organization organization, String typeId);

	List<String> getTypeIdsForOrganizationId(String id);

	void deleteAllLinksForOrganization(String id);

	List<Organization> getOrganizationsByTypeName(String orderByProperty, String referralOrgType);
}
