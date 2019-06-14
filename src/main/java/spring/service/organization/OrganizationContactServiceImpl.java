package spring.service.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.organization.dao.OrganizationContactDAO;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;

@Service
public class OrganizationContactServiceImpl extends BaseObjectServiceImpl<OrganizationContact, String>
		implements OrganizationContactService {
	@Autowired
	protected OrganizationContactDAO baseObjectDAO;

	OrganizationContactServiceImpl() {
		super(OrganizationContact.class);
	}

	@Override
	protected OrganizationContactDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrganizationContact> getListForOrganizationId(String orgId) {
		return getBaseObjectDAO().getListForOrganizationId(orgId);
	}

}
