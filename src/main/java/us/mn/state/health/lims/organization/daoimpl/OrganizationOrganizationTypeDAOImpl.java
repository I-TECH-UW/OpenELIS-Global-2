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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;

//TODO move to service layer, or other DAOs
@Component
@Transactional
public class OrganizationOrganizationTypeDAOImpl implements OrganizationOrganizationTypeDAO {


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void deleteAllLinksForOrganization(String id) throws LIMSRuntimeException {

		try {
			String sql = "delete from organization_organization_type where org_id = :id";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
			query.setInteger("id", Integer.parseInt(id));
			query.executeUpdate();
		} catch (Exception e) {
			LogEvent.logError("OrganizationOrganizationTypeDAOImpl", "deleteAllLinksForOrganization()", e.toString());
			throw new LIMSRuntimeException("Error in OrganizationOrganizationType deleteAllLinksForOrganization()", e);
		}
	}

	@Override
	public void linkOrganizationAndType(Organization org, String typeId) throws LIMSRuntimeException {

		try {
			String sql = "INSERT INTO organization_organization_type(org_id, org_type_id)VALUES (:org_id, :type_id);";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
			query.setInteger("org_id", Integer.parseInt(org.getId()));
			query.setInteger("type_id", Integer.parseInt(typeId));
			query.executeUpdate();

		} catch (Exception e) {
			LogEvent.logError("OrganizationOrganizationTypeDAOImpl", "linkOrganizationAndType()", e.toString());
			throw new LIMSRuntimeException("Error in OrganizationOrganizationType linkOrganizationAndType()", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<String> getOrganizationIdsForType(String typeId) throws LIMSRuntimeException {
		List<String> orgIdList = null;
		String sql = "select cast(org_id AS varchar) from organization_organization_type where org_type_id = :orgTypeId";

		try {
			Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
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
	@Transactional(readOnly = true)
	public List<String> getTypeIdsForOrganizationId(String organizationId) throws LIMSRuntimeException {
		List<String> orgIdList = null;
		String sql = "select cast(org_type_id AS varchar) from organization_organization_type where org_id = :orgId";

		try {
			Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
			query.setInteger("orgId", Integer.parseInt(organizationId));
			orgIdList = query.list();

		} catch (Exception e) {
			handleException(e, "getTypeIdsForOrganizationId");
		}
		return orgIdList;
	}

	private void handleException(Exception e, String string) {
		// TODO Auto-generated method stub
		e.printStackTrace();

	}
}