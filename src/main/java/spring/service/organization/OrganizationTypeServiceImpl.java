package spring.service.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

@Service
public class OrganizationTypeServiceImpl extends BaseObjectServiceImpl<OrganizationType, String> implements OrganizationTypeService {
	@Autowired
	protected OrganizationTypeDAO baseObjectDAO;

	public OrganizationTypeServiceImpl() {
		super(OrganizationType.class);
	}

	@Override
	protected OrganizationTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizationsByTypeName(String orderByCol, String[] names) {
		return getBaseObjectDAO().getOrganizationsByTypeName(orderByCol, names);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrganizationType> getAllOrganizationTypes() {
		return getBaseObjectDAO().getAllOrganizationTypes();
	}

	@Override
	@Transactional(readOnly = true)
	public OrganizationType getOrganizationTypeByName(String name) {
		return getBaseObjectDAO().getOrganizationTypeByName(name);
	}
}
