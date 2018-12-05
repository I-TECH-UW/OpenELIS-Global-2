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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.qaevent.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.qaevent.dao.QaObservationTypeDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaObservationType;

public class QaObservationTypeDAOImpl extends BaseDAOImpl implements QaObservationTypeDAO {

	@Override
	public QaObservationType getQaObservationTypeByName(String typeName) throws LIMSRuntimeException {
		String sql = "FROM QaObservationType where name = :name";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", typeName);
			QaObservationType type = (QaObservationType)query.uniqueResult();
			closeSession();
			return type;
		} catch (HibernateException e) {
			handleException(e, "getQaObservationTypeByName");
		}
		return null;
	}

	
}
