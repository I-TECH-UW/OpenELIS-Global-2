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
package us.mn.state.health.lims.common.services;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Transaction;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.dao.DocumentTypeDAO;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ReportTrackingService {
	private static DocumentTrackDAO docTrackDAO = new DocumentTrackDAOImpl();
    private static final String PATIENT_DOCUMENT_TYPE_ID;
    private static final String NON_CONFORMITY_DOCUMENT_TYPE_ID;
    private static final String RESULT_EXPORT_DOCUMENT_TYPE_ID;
    private static final String MALARIA_CASE_DOCUMENT_TYPE_ID;
	public enum ReportType {
		PATIENT,
        NON_CONFORMITY_NOTIFICATION,
        RESULT_EXPORT,
        MALARIA_CASE
	}

	static{
        DocumentTypeDAO documentTypeDAO = new DocumentTypeDAOImpl();
		PATIENT_DOCUMENT_TYPE_ID  = documentTypeDAO.getDocumentTypeByName( "patientReport" ).getId();
        NON_CONFORMITY_DOCUMENT_TYPE_ID  = documentTypeDAO.getDocumentTypeByName( "nonConformityNotification" ).getId();
        RESULT_EXPORT_DOCUMENT_TYPE_ID = documentTypeDAO.getDocumentTypeByName( "resultExport" ).getId();
        MALARIA_CASE_DOCUMENT_TYPE_ID = documentTypeDAO.getDocumentTypeByName( "malariaCase" ).getId();
	}	
	public void addReports(List<String> refIds, ReportType type, String name, String currentSystemUserId) {
	

		Transaction trx = HibernateUtil.getSession().beginTransaction();
		try {

			String refTableId = getReferenceTable(type);
			DocumentTrackDAO rtDAO = new DocumentTrackDAOImpl();
			
			for (String id : refIds) {
				DocumentTrack docTrack = new DocumentTrack();
				docTrack.setDocumentTypeId(getReportTypeId(type));
				docTrack.setRecordId(id);
				docTrack.setReportTime(DateUtil.getNowAsTimestamp());
				docTrack.setDocumentName(name);
				docTrack.setTableId(refTableId);
				docTrack.setSysUserId(currentSystemUserId);
				DocumentTrack parent = getParent(id, refTableId, docTrack.getDocumentTypeId());
				if( parent != null){
					docTrack.setParent(parent);
				}
				rtDAO.insertData(docTrack);
				
			}
			
			trx.commit();
		} catch (Exception e) {
			trx.rollback();
		}
	}

	private DocumentTrack getParent(String id, String refTableId, String documentTypeId) {
		List<DocumentTrack> docs = docTrackDAO.getByTypeRecordAndTable(documentTypeId, refTableId, id);
		return docs.isEmpty() ? null : docs.get(docs.size() - 1);
	}

	private String getReferenceTable(ReportType type) {
		switch (type) {
		case PATIENT: {
			return new ReferenceTablesDAOImpl().getReferenceTableByName("SAMPLE").getId();
		}
        case NON_CONFORMITY_NOTIFICATION:{
            return new ReferenceTablesDAOImpl().getReferenceTableByName( "SAMPLE_QAEVENT" ).getId();
        }
        case MALARIA_CASE:{
            return new ReferenceTablesDAOImpl().getReferenceTableByName( "ANALYSIS" ).getId();
        }
        case RESULT_EXPORT:{
            return new ReferenceTablesDAOImpl().getReferenceTableByName( "ANALYSIS" ).getId();
        }

		}

		return null;
	}

	private String getReportTypeId(ReportType type) {
		switch (type) {
		case PATIENT: {
			return PATIENT_DOCUMENT_TYPE_ID;
		}
        case NON_CONFORMITY_NOTIFICATION:{
            return NON_CONFORMITY_DOCUMENT_TYPE_ID;
        }
        case RESULT_EXPORT:{
            return RESULT_EXPORT_DOCUMENT_TYPE_ID;
        }
        case MALARIA_CASE:{
            return MALARIA_CASE_DOCUMENT_TYPE_ID;
        }

		}

		return null;
	}

	public List<DocumentTrack> getReportsForSample(Sample sample,  ReportType type) {
		return docTrackDAO.getByTypeRecordAndTable(getReportTypeId(type), getReferenceTable(type), sample.getId());
	}

    public List<DocumentTrack> getReportsForSampleAndReportName(Sample sample,  ReportType type, String name) {
        return docTrackDAO.getByTypeRecordAndTableAndName(getReportTypeId(type), getReferenceTable(type), sample.getId(), name);
    }

    public DocumentTrack getLastReportForSample( Sample sample, ReportType type){
        List<DocumentTrack> reports = getReportsForSample( sample, type );
        return reports.isEmpty() ? null : reports.get( reports.size() - 1 );
    }

	public DocumentTrack getLastNamedReportForSample( Sample sample, ReportType type, String name){
		if( sample == null || type == null || GenericValidator.isBlankOrNull(name)){
			return null;
		}

        List<DocumentTrack> reports = getReportsForSampleAndReportName( sample, type, name );
        return reports.isEmpty() ? null : reports.get( reports.size() - 1 );
	}

	public Timestamp getTimeOfLastReport( Sample sample, ReportType type){
        DocumentTrack report = getLastReportForSample(sample, type );
        return report == null ? null : report.getReportTime();
    }

	public Timestamp getTimeOfLastNamedReport( Sample sample, ReportType type, String name){
		DocumentTrack report = getLastNamedReportForSample( sample, type, name );
		return report == null ? null : report.getReportTime();
	}

	public DocumentTrack getDocumentForId(String id){
		return docTrackDAO.readEntity(id);
	}
}
