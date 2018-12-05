/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */
package us.mn.state.health.lims.referral.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referral.dao.ReferringTestResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferringTestResult;

/*
 */
public class ReferringTestResultDAOImpl extends BaseDAOImpl implements ReferringTestResultDAO {

	private AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

	public boolean insertData(ReferringTestResult referringTestResult) throws LIMSRuntimeException {
		try {
			String id = (String) HibernateUtil.getSession().save(referringTestResult);
			referringTestResult.setId(id);

			auditDAO.saveNewHistory(referringTestResult, referringTestResult.getSysUserId(), "REFERRING_TEST_RESULT");
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insertData");
		}

		return true;
	}

    @Override
    @SuppressWarnings("unchecked")
    public List<ReferringTestResult> getReferringTestResultsForSampleItem(String sampleItemId) throws LIMSRuntimeException {
        String sql = "from ReferringTestResult rtr where rtr.sampleItemId = :sampleItemId";
        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("sampleItemId", Integer.parseInt(sampleItemId));
            List<ReferringTestResult> list = query.list();
            closeSession();
            return list;
        }catch (HibernateException e){
            handleException(e, "getReferringTestResultsForSampleItem");
        }
        return null;
    }

    @Override
    public List<ReferringTestResult> getResultsInDateRange(Date lowDate, Date highDate) throws LIMSRuntimeException {
        String sql = "from ReferringTestResult rtr where rtr.lastupdated BETWEEN :lowDate AND :highDate";
        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setDate("lowDate", lowDate);
            query.setDate("highDate", highDate);

            List<ReferringTestResult> list = query.list();
            closeSession();
            return list;
        }catch (HibernateException e){
            handleException(e, "getResultsInDateRange");
        }

        return null;
    }


}