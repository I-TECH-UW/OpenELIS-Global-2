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
package us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportQueueTypeDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class ReportQueueTypeDAOImpl extends BaseDAOImpl implements ReportQueueTypeDAO {

	@Override
	public ReportQueueType getReportQueueTypeByName(String name) throws LIMSRuntimeException {
		String sql = "from ReportQueueType rqt where rqt.name = :name";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			ReportQueueType type = (ReportQueueType)query.uniqueResult();
			closeSession();
			return type;
		}catch(HibernateException e){
			handleException(e, "getReportQueueTypeByName");
		}
		return null;
	}
}
