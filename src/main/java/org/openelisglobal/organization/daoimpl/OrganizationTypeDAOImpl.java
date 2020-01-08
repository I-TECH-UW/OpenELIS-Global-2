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
package org.openelisglobal.organization.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.organization.dao.OrganizationTypeDAO;
import org.openelisglobal.organization.valueholder.OrganizationType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationTypeDAOImpl extends BaseDAOImpl<OrganizationType, String> implements OrganizationTypeDAO {

    public OrganizationTypeDAOImpl() {
        super(OrganizationType.class);
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<OrganizationType> getAllOrganizationTypes() throws LIMSRuntimeException {
        List<OrganizationType> list = null;
        try {
            String sql = "from OrganizationType";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Organization getAllOrganizationTypes()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationType getOrganizationTypeByName(String name) throws LIMSRuntimeException {
        String sql = null;
        try {
            sql = "from OrganizationType o where o.name = :name";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setString("name", name);

            
            List<OrganizationType> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0 ? list.get(0) : null;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in OrganizationType getOrganizationTypeByName()", e);
        }
    }
}