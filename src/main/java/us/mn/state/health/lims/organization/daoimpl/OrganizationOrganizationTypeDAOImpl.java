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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.organization.daoimpl;

import java.util.List;

import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;

public class OrganizationOrganizationTypeDAOImpl extends BaseDAOImpl implements OrganizationOrganizationTypeDAO {

	public void deleteAllLinksForOrganization(String id) throws LIMSRuntimeException {

		try {
			String sql = "delete from organization_organization_type where org_id = '" + Integer.parseInt(id) + "';";
			HibernateUtil.getSession().connection().prepareStatement(sql).executeUpdate();
		} catch (Exception e) {
			LogEvent.logError("OrganizationOrganizationTypeDAOImpl", "deleteAllLinksForOrganization()", e.toString());
			throw new LIMSRuntimeException("Error in OrganizationOrganizationType deleteAllLinksForOrganization()", e);
		}
	}

	public void linkOrganizationAndType(Organization org, String typeId) throws LIMSRuntimeException {

		try {
			StringBuffer buffer = new StringBuffer("INSERT INTO organization_organization_type(org_id, org_type_id)VALUES (");
			buffer.append(Integer.parseInt(org.getId()));
			buffer.append(",");
			buffer.append(Integer.parseInt(typeId));
			buffer.append(");");

			HibernateUtil.getSession().connection().prepareStatement(buffer.toString()).executeUpdate();
		} catch (Exception e) {
			LogEvent.logError("OrganizationOrganizationTypeDAOImpl", "linkOrganizationAndType()", e.toString());
			throw new LIMSRuntimeException("Error in OrganizationOrganizationType linkOrganizationAndType()", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getOrganizationIdsForType(String typeId) throws LIMSRuntimeException {
		List<String> orgIdList = null;
		String sql = "select cast(org_id AS varchar) from organization_organization_type where org_type_id = :orgTypeId";

		try{
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setInteger("orgTypeId", Integer.parseInt(typeId));
			orgIdList = query.list();

		} catch (Exception e) {
			LogEvent.logError("OrganizationOrganizationTypeDAOImpl", "getOrganizationForType()", e.toString());
			throw new LIMSRuntimeException("Error in OrganizationOrganizationType getOrganizationForType()", e);
		}
		return orgIdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTypeIdsForOrganizationId(String organizationId) throws LIMSRuntimeException {
		List<String> orgIdList = null;
		String sql = "select cast(org_type_id AS varchar) from organization_organization_type where org_id = :orgId";

		try{
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setInteger("orgId", Integer.parseInt(organizationId));
			orgIdList = query.list();

		} catch (Exception e) {
			handleException(e, "getTypeIdsForOrganizationId");
		}
		return orgIdList;
	}
}