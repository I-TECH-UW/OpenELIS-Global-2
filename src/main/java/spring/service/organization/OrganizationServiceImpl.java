package spring.service.organization;

import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;

@Service
public class OrganizationServiceImpl extends BaseObjectServiceImpl<Organization> implements OrganizationService {
	@Autowired
	protected OrganizationDAO baseObjectDAO;
	@Autowired
	OrganizationOrganizationTypeDAO organizationOrganizationTypeDAO;

	OrganizationServiceImpl() {
		super(Organization.class);
	}

	@Override
	protected OrganizationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public Organization getOrganizationByName(Organization organization, boolean ignoreCase) {
		return baseObjectDAO.getOrganizationByName(organization, ignoreCase);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getTypeIdsForOrganizationId(String id) {
		return organizationOrganizationTypeDAO.getTypeIdsForOrganizationId(id);
	}

	@Override
	@Transactional
	public void deleteAllLinksForOrganization(String id) {
		organizationOrganizationTypeDAO.deleteAllLinksForOrganization(id);
	}

	@Override
	@Transactional
	public void linkOrganizationAndType(Organization organization, String typeId) {
		organizationOrganizationTypeDAO.linkOrganizationAndType(organization, typeId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getPagesOfSearchedOrganizations(int startingRecNo, String searchString) {
		return baseObjectDAO.getLikePage("organizationName", searchString, startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalSearchedOrganizationCount(String searchString) {
		return getCountLike("organizationName", searchString);
	}

	@Override
	@Transactional
	public void delete(Organization organization) {
		Organization oldObject = getBaseObjectDAO().get(organization.getId())
				.orElseThrow(() -> new ObjectNotFoundException(organization.getId(), Organization.class.getName()));
		oldObject.setIsActive(IActionConstants.NO);
		oldObject.setSysUserId(organization.getSysUserId());
		update(oldObject);
	}

	@Override
	@Transactional
	public void delete(String id, String sysUserId) {
		Organization oldObject = getBaseObjectDAO().get(id)
				.orElseThrow(() -> new ObjectNotFoundException(id, Organization.class.getName()));
		oldObject.setIsActive(IActionConstants.NO);
		oldObject.setSysUserId(sysUserId);
		update(oldObject);
	}

	@Override
	@Transactional
	public void deleteAll(List<Organization> baseObjects) {
		for (Organization organization : baseObjects) {
			delete(organization);
		}
	}

	@Override
	@Transactional
	public void deleteAll(List<String> ids, String sysUserId) {
		for (String id : ids) {
			delete(id, sysUserId);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public void getData(Organization organization) {
		getBaseObjectDAO().getData(organization);

	}

	@Override
	public void deleteData(List organizations) {
		getBaseObjectDAO().deleteData(organizations);

	}

	@Override
	public void updateData(Organization organization) {
		getBaseObjectDAO().updateData(organization);

	}

	@Override
	public boolean insertData(Organization organization) {
		return getBaseObjectDAO().insertData(organization);
	}

	@Override
	public void insertOrUpdateData(Organization organization) {
		getBaseObjectDAO().insertOrUpdateData(organization);

	}

	@Override
	@Transactional(readOnly = true)
	public List getNextOrganizationRecord(String id) {
		return getBaseObjectDAO().getNextOrganizationRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizationsByParentId(String parentId) {
		return getBaseObjectDAO().getOrganizationsByParentId(parentId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizationsByTypeName(String orderByProperty, String[] typeName) {
		return getBaseObjectDAO().getOrganizationsByTypeName(orderByProperty, typeName);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Organization> getOrganizationsByProjectName(String projectName) {
		return getBaseObjectDAO().getOrganizationsByProjectName(projectName);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalOrganizationCount() {
		return getBaseObjectDAO().getTotalOrganizationCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllOrganizations() {
		return getBaseObjectDAO().getAllOrganizations();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousOrganizationRecord(String id) {
		return getBaseObjectDAO().getPreviousOrganizationRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Organization getOrganizationById(String organizationId) {
		return getBaseObjectDAO().getOrganizationById(organizationId);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfOrganizations(int startingRecNo) {
		return getBaseObjectDAO().getPageOfOrganizations(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getOrganizations(String filter) {
		return getBaseObjectDAO().getOrganizations(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizationsByTypeNameAndLeadingChars(String partialName, String typeName) {
		return getBaseObjectDAO().getOrganizationsByTypeNameAndLeadingChars(partialName, typeName);
	}

	@Override
	@Transactional(readOnly = true)
	public Organization getOrganizationByLocalAbbreviation(Organization organization, boolean ignoreCase) {
		return getBaseObjectDAO().getOrganizationByLocalAbbreviation(organization, ignoreCase);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizationsByTypeName(String orderByProperty, String referralOrgType) {
		return getBaseObjectDAO().getOrganizationsByTypeName(orderByProperty, referralOrgType);
	}

}
