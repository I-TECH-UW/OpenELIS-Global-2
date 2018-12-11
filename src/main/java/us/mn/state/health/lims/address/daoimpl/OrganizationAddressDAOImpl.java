/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.address.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class OrganizationAddressDAOImpl extends BaseDAOImpl implements OrganizationAddressDAO {

	private static AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationAddress> getAddressPartsByOrganizationId(String organizationId) throws LIMSRuntimeException {
		String sql = "from OrganizationAddress pa where pa.compoundId.targetId = :organizationId";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("organizationId", Integer.parseInt(organizationId));
			List<OrganizationAddress> addressPartList = query.list();
			closeSession();
			return addressPartList;
		}catch(HibernateException e){
			handleException(e, "getAddressPartsByOrganizationId");
		}

		return null;
	}

	@Override
	public void insert(OrganizationAddress organizationAddress) throws LIMSRuntimeException {
	try {
			HibernateUtil.getSession().save(organizationAddress);
			auditDAO.saveNewHistory(organizationAddress,organizationAddress.getSysUserId(),"organization_address");
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insert");
		}
	}

	@Override
	public void update(OrganizationAddress organizationAddress) throws LIMSRuntimeException {

		OrganizationAddress oldData = readOrganizationAddress(organizationAddress);

		try {
			auditDAO.saveHistory(organizationAddress, oldData, organizationAddress.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "organization_address");

			HibernateUtil.getSession().merge(organizationAddress);
			closeSession();
			HibernateUtil.getSession().evict(organizationAddress);
			HibernateUtil.getSession().refresh(organizationAddress);
		} catch (HibernateException e) {
			handleException(e, "update");
		}
	}

	public OrganizationAddress readOrganizationAddress(OrganizationAddress organizationAddress) {
		try {
			OrganizationAddress oldOrganizationAddress = (OrganizationAddress) HibernateUtil.getSession().get(OrganizationAddress.class, organizationAddress.getCompoundId());
			closeSession();

			return oldOrganizationAddress;
		} catch (HibernateException e) {
			handleException(e,"readOrganizationAddress");
		}

		return null;
	}
}
