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
*/
package us.mn.state.health.lims.samplepdf.daoimpl;

import java.util.List;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.samplepdf.dao.SamplePdfDAO;
import us.mn.state.health.lims.samplepdf.valueholder.SamplePdf;

/**
 * @author Hung Nguyen
 */
public class SamplePdfDAOImpl extends BaseDAOImpl implements SamplePdfDAO {

	public boolean isAccessionNumberFound(int accessionNumber) throws LIMSRuntimeException {	
		Boolean isFound = false;
		try {			
			String sql = "from SamplePdf s where s.accessionNumber = :param and s.allowView='Y'";
    		org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
    		query.setParameter("param", accessionNumber);
    		List list = query.list();
			if ((list != null) && !list.isEmpty()) {
				isFound = true;
			}
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("SamplePdfDAOImpl","isAccessionNumberFound()",e.toString());	
			throw new LIMSRuntimeException("Error in SamplePdf isAccessionNumberFound()", e);
		}
		
		return isFound;
	}
	
	//bugzilla 2529,2530,2531
	public SamplePdf getSamplePdfByAccessionNumber(SamplePdf samplePdf) throws LIMSRuntimeException {	
		try {
			String sql = "from SamplePdf s where s.accessionNumber = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", samplePdf.getAccessionNumber());
		
			List list = query.list();
			if ((list != null) && !list.isEmpty()) {
				samplePdf = (SamplePdf)list.get(0);
			}

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("SamplePdfDAOImpl","getSamplePdfByAccessionNumber()",e.toString());
			throw new LIMSRuntimeException("Error in SamplePdf getSamplePdfByAccessionNumber()", e);		
		}	

		return samplePdf;
	}
}