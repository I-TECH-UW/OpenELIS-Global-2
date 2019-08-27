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
package org.openelisglobal.dataexchange.aggregatereporting.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.dataexchange.aggregatereporting.dao.ReportQueueTypeDAO;
import org.openelisglobal.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ReportQueueTypeDAOImpl extends BaseDAOImpl<ReportQueueType, String> implements ReportQueueTypeDAO {

    public ReportQueueTypeDAOImpl() {
        super(ReportQueueType.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ReportQueueType getReportQueueTypeByName(String name) throws LIMSRuntimeException {
        String sql = "from ReportQueueType rqt where rqt.name = :name";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", name);
            ReportQueueType type = (ReportQueueType) query.uniqueResult();
            // closeSession(); // CSL remove old
            return type;
        } catch (HibernateException e) {
            handleException(e, "getReportQueueTypeByName");
        }
        return null;
    }
}
