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
package org.openelisglobal.common.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.dao.DatabaseChangeLogDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.valueholder.DatabaseChangeLog;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
//public class DatabaseChangeLogDAOImpl extends BaseDAOImpl<DatabaseChangeLog, String> implements DatabaseChangeLogDAO {
public class DatabaseChangeLogDAOImpl implements DatabaseChangeLogDAO {

    // public DatabaseChangeLogDAOImpl() {
    // super(DatabaseChangeLog.class);
    // }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    
    @Transactional(readOnly = true)
    public DatabaseChangeLog getLastExecutedChange() throws LIMSRuntimeException {
        List<DatabaseChangeLog> results;

        try {
            String sql = "from DatabaseChangeLog dcl order by dcl.executed desc";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            results = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (results != null && results.get(0) != null) {
                return results.get(0);
            }

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in DatabaseChangeLogDAOImpl getLastExecutedChange()", e);
        }

        return null;
    }

}
