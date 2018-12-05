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
package us.mn.state.health.lims.reports.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.reports.dao.DocumentTypeDAO;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

public class DocumentTypeDAOImpl extends BaseDAOImpl implements DocumentTypeDAO {

	@Override
	public DocumentType getDocumentTypeByName(String name) throws LIMSRuntimeException {
		String sql = "Select from DocumentType dt where name = :name";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			DocumentType docType = (DocumentType)query.uniqueResult();
			closeSession();
			return docType;
		}catch( HibernateException e){
			handleException(e, "getDocumentTypeByName");
		}
		return null;
	}

	public DocumentType getByName(String name) {
		String sql = "From DocumentType dt where dt.name = :name";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			DocumentType document = (DocumentType)query.setMaxResults(1).uniqueResult();
			closeSession();
			return document;
		} catch (HibernateException e) {
			handleException(e, "getByName");
		}
		
		return null;
	}

}
