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
package us.mn.state.health.lims.reports.action.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.daoimpl.SampleProjectDAOImpl;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;

public class ReportUtil {

	private static String SAMPLE_QAEVENT_TABLE_ID;;
	private static String DOCTOR_OBSERVATION_ID;
	private static DocumentType NON_CONFORMITY_NOTIFICATION_TYPE;
	private static SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl();
	private static TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
	private static ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();

	private static DocumentTrackDAO documentTrackDAO = new DocumentTrackDAOImpl();

	static {
		ObservationHistoryType oht = new ObservationHistoryTypeDAOImpl().getByName("nameOfDoctor");
		if( oht != null){
			DOCTOR_OBSERVATION_ID = oht.getId();
		}
		
		NON_CONFORMITY_NOTIFICATION_TYPE = new DocumentTypeDAOImpl().getByName("nonConformityNotification");
		SAMPLE_QAEVENT_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("SAMPLE_QAEVENT").getId();
	}

	public static enum DocumentTypes {
		NON_CONFORMITY_NOTIFCATION
	}

	public static Patient findPatient(Sample sample) {
		return sampleHumanDAO.getPatientForSample(sample);
	}

	public static Project findProject(Sample sample) {
		SampleProject sampleProject = sampleProjectDAO.getSampleProjectBySampleId(sample.getId());
		if (sampleProject == null) {
			return null;
		}
		return sampleProject.getProject();
	}

	public static String getSampleType(SampleQaEvent event) {
		SampleItem sampleItem = event.getSampleItem();
		return (sampleItem == null) ? StringUtil.getContextualMessageForKey("nonConformant.allSampleTypesText")
				: findTypeOfSample(sampleItem.getTypeOfSampleId());

	}

	private static String findTypeOfSample(String typeOfSampleId) {
		return typeOfSampleDAO.getTypeOfSampleById(typeOfSampleId).getLocalizedName();
	}

	public static String findDoctorForSample(Sample sample) {
		ObservationHistory oh = observationDAO.getObservationHistoriesBySampleIdAndType(sample.getId(), DOCTOR_OBSERVATION_ID);
		return oh == null ? "" : oh.getValue();

	}

	public static void markDocumentsAsPrinted(DocumentTypes docType, List<String> recordIds, String currentUserId, Set<String> checkPriorPrintList) {
			
		DocumentType documentType = null;
		String tableId = null;
		Timestamp reportTime = DateUtil.getNowAsTimestamp();

		switch (docType) {
		case NON_CONFORMITY_NOTIFCATION:
			documentType = NON_CONFORMITY_NOTIFICATION_TYPE;
			tableId = SAMPLE_QAEVENT_TABLE_ID;
			break;
		default:
			break;
		}

		List<DocumentTrack> documents = new ArrayList<DocumentTrack>();

		for (String recordId : recordIds) {
			DocumentTrack document = new DocumentTrack();
			document.setDocumentTypeId(documentType.getId());
			document.setRecordId(recordId);
			document.setTableId(tableId);
			document.setReportTime(reportTime);
			document.setSysUserId(currentUserId);
			document.setLastupdated(reportTime);

			if( checkPriorPrintList.contains(recordId)){
				List<DocumentTrack> priorDocuments = documentTrackDAO.getByTypeRecordAndTable(documentType.getId(), tableId, recordId);
				if( !priorDocuments.isEmpty()){
					document.setParent(priorDocuments.get(priorDocuments.size() - 1));
				}
			}
			
			documents.add(document);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		
		try{
			for( DocumentTrack document : documents){
				documentTrackDAO.insertData(document);
			}
			
			tx.commit();
		}catch( HibernateException e){
			tx.rollback();
		}
	}

	public static boolean documentHasBeenPrinted(DocumentTypes docType, String recordId) {
		DocumentType documentType = null;
		String tableId = null;

		switch (docType) {
		case NON_CONFORMITY_NOTIFCATION:
			documentType = NON_CONFORMITY_NOTIFICATION_TYPE;
			tableId = SAMPLE_QAEVENT_TABLE_ID;
			break;
		default:
			break;
		}

		
		return !documentTrackDAO.getByTypeRecordAndTable( documentType.getId(), tableId, recordId).isEmpty();
	}
}
