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
package us.mn.state.health.lims.referral.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.GenericDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referral.dao.ReferralReasonDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralReason;

/*
 */
public class ReferralReasonDAOImpl extends GenericDAOImpl<String, ReferralReason> implements ReferralReasonDAO {
    public ReferralReasonDAOImpl() {
        super(ReferralReason.class, "referral_reason");
    }

    @SuppressWarnings("unchecked")
	public List<ReferralReason> getAllReferralReasons() throws LIMSRuntimeException {
		String sql = "from ReferralReason";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			List<ReferralReason> reasons = query.list();
			closeSession();
			return reasons;
		}catch(HibernateException e){
			handleException(e, "getAllReferralReasons");
		}

		return null;
	}
}