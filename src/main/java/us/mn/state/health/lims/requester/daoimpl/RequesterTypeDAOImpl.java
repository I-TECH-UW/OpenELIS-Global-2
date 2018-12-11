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
package us.mn.state.health.lims.requester.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.valueholder.RequesterType;

/*
 */
public class RequesterTypeDAOImpl extends BaseDAOImpl implements RequesterTypeDAO {

	@Override
	public RequesterType getRequesterTypeByName(String typeName) throws LIMSRuntimeException {
		String sql = "from RequesterType rt where rt.requesterType = :typeName";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("typeName", typeName);
			RequesterType type = (RequesterType) query.uniqueResult();
			closeSession();
			return type;
		}catch(HibernateException e){
			handleException(e, "getRequesterTypeByName");
		}


		return null;
	}


}