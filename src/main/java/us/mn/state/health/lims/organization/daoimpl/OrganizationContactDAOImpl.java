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
package us.mn.state.health.lims.organization.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationContactDAO;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;

public class OrganizationContactDAOImpl extends BaseDAOImpl implements OrganizationContactDAO {

	private static AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationContact> getListForOrganizationId(String orgId) throws LIMSRuntimeException {
		String sql = "From OrganizationContact oc where oc.organizationId = :orgId";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("orgId", Integer.parseInt(orgId));
			List<OrganizationContact> contactList = query.list();
			closeSession();
			return contactList;
		}catch(HibernateException e){
			handleException(e, "getListForOrganizationId");
		}

		return null;
	}

	@Override
	public void insert(OrganizationContact contact) throws LIMSRuntimeException {
		try {
			String id = (String)HibernateUtil.getSession().save(contact);
			contact.setId(id);
			auditDAO.saveNewHistory(contact,contact.getSysUserId(),"organization_contact");
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insert");
		}
	}

}
