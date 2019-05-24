package spring.service.address;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.common.action.IActionConstants;

@Service
public class OrganizationAddressServiceImpl extends BaseObjectServiceImpl<OrganizationAddress> implements OrganizationAddressService {
	@Autowired
	protected OrganizationAddressDAO baseObjectDAO;

	OrganizationAddressServiceImpl() {
		super(OrganizationAddress.class);
	}

	@Override
	protected OrganizationAddressDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public OrganizationAddress update(OrganizationAddress organizationAddress) {
		OrganizationAddress oldObject = getBaseObjectDAO().get(organizationAddress.getCompoundId()).orElseThrow(() -> new ObjectNotFoundException(organizationAddress.getId(), OrganizationAddress.class.getName()));
		if (auditTrailLog) {
			auditTrailDAO.saveHistory(organizationAddress, oldObject, organizationAddress.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, getBaseObjectDAO().getTableName());
		}
		return getBaseObjectDAO().save(organizationAddress);
	}

	@Override
	@Transactional
	public List<OrganizationAddress> getAddressPartsByOrganizationId(String id) {
		return baseObjectDAO.getAddressPartsByOrganizationId(id);
	}
}
