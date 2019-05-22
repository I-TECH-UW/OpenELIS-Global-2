package spring.service.organization;

import java.util.List;

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
	@Transactional 
	public Organization getOrganizationByName(Organization organization, boolean ignoreCase) {
		return baseObjectDAO.getOrganizationByName(organization, ignoreCase);
	}

	@Override
	@Transactional 
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
	@Transactional 
	public List<Organization> getPagesOfSearchedOrganizations(int startingRecNo, String searchString) {
		return baseObjectDAO.getLikePage("organizationName", searchString, startingRecNo);
	}

	@Override
	@Transactional 
	public int getTotalSearchedOrganizationCount(String searchString) {
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
}
