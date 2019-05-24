package spring.service.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

@Service
public class OrganizationTypeServiceImpl extends BaseObjectServiceImpl<OrganizationType> implements OrganizationTypeService {
	@Autowired
	protected OrganizationTypeDAO baseObjectDAO;

	OrganizationTypeServiceImpl() {
		super(OrganizationType.class);
	}

	@Override
	protected OrganizationTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List<Organization> getOrganizationsByTypeName(String orderByCol, String[] names) {
        return getBaseObjectDAO().getOrganizationsByTypeName(orderByCol,names);
	}

	@Override
	public List<OrganizationType> getAllOrganizationTypes() {
        return getBaseObjectDAO().getAllOrganizationTypes();
	}

	@Override
	public OrganizationType getOrganizationTypeByName(String name) {
        return getBaseObjectDAO().getOrganizationTypeByName(name);
	}
}
