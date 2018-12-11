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

import org.hibernate.Session;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationTypeDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;

public class OrganizationTypeDAOImpl extends BaseDAOImpl implements OrganizationTypeDAO {

    @SuppressWarnings("unchecked")
    public List<OrganizationType> getAllOrganizationTypes() throws LIMSRuntimeException {
        List<OrganizationType> list = null;
        try {
            String sql = "from OrganizationType";
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            LogEvent.logError("OrganizationTypeDAOImpl", "getAllOrganizationTypess()", e.toString());
            throw new LIMSRuntimeException("Error in Organization getAllOrganizationTypes()", e);
        }

        return list;
    }

    public OrganizationType getOrganizationTypeByName(String name) throws LIMSRuntimeException {
        String sql = null;
        try {
            sql = "from OrganizationType o where o.name = :name";
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

            query.setString("name", name);

            @SuppressWarnings("unchecked")
            List<OrganizationType> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            return list.size() > 0 ? list.get(0) : null;

        } catch (Exception e) {
            LogEvent.logError("OrganizationTypeDAOImpl", "getOrganizationTypeByName()", e.toString());
            throw new LIMSRuntimeException("Error in OrganizationType getOrganizationTypeByName()", e);
        }
    }

    public List<Organization> getOrganizationsByTypeName(String orderByCol, String... names) throws LIMSRuntimeException {
        String sql = null;
        try {
            sql = "from OrganizationType ot WHERE ot.name IN (:names) ";
            Session session = HibernateUtil.getSession();
            org.hibernate.Query query = session.createQuery(sql).setParameterList("names", names);
            @SuppressWarnings("unchecked")
            OrganizationType ot = ((List<OrganizationType>) query.list()).get(0);
            sql = "where this.isActive = 'Y' ";
            if (null != orderByCol) {
                sql += " order by " + orderByCol;
            }
            @SuppressWarnings("unchecked")
            List<Organization> orgs2 = session.createFilter(ot.getOrganizations(), sql).list();

            session.flush();
            session.clear();

            return orgs2;

        } catch (Exception e) {
            LogEvent.logError("OrganizationTypeDAOImpl", "getOrganizationsByTypeName()", e.toString());
            throw new LIMSRuntimeException("Error in OrganizationType getOrganizationTypeByName()", e);
        }
    }
}