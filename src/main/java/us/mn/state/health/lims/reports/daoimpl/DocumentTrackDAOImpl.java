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

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.GenericDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;

public class DocumentTrackDAOImpl extends GenericDAOImpl<String, DocumentTrack > implements DocumentTrackDAO {

	public DocumentTrackDAOImpl() {
		super(DocumentTrack.class, "document_track");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentTrack> getByTypeRecordAndTable(String typeId, String tableId, String recordId) throws LIMSRuntimeException {
		String sql = "From DocumentTrack dt where dt.documentTypeId = :typeId and dt.tableId = :tableId and dt.recordId = :recordId order by dt.reportTime";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("typeId", Integer.parseInt(typeId));
			query.setInteger("tableId", Integer.parseInt(tableId));
			query.setInteger("recordId", Integer.parseInt(recordId));
			List<DocumentTrack> documents = query.list();
			closeSession();
			return documents;
			
		} catch (HibernateException e) {
			handleException(e, "getByTypeRecordAndTable");
		}
		
		return null;
	}

	@Override
	public List<DocumentTrack> getByTypeRecordAndTableAndName(String reportTypeId, String tableId, String recordId, String name) throws LIMSRuntimeException {
		String sql = "From DocumentTrack dt where dt.documentTypeId = :typeId and dt.tableId = :tableId and dt.recordId = :recordId and dt.documentName = :name order by dt.reportTime";

		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("typeId", Integer.parseInt(reportTypeId));
			query.setInteger("tableId", Integer.parseInt(tableId));
			query.setInteger("recordId", Integer.parseInt(recordId));
			query.setString("name", name);
			List<DocumentTrack> documents = query.list();
			closeSession();
			return documents;

		} catch (HibernateException e) {
			handleException(e, "getByTypeRecordAndTableAndName");
		}

		return null;
	}

}
