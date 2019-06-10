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

import spring.mine.internationalization.MessageUtil;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.observationhistorytype.ObservationHistoryTypeService;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.reports.DocumentTrackService;
import spring.service.reports.DocumentTypeService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleproject.SampleProjectService;
import spring.service.typeofsample.TypeOfSampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class ReportUtil {

	private static String SAMPLE_QAEVENT_TABLE_ID;
	private static String DOCTOR_OBSERVATION_ID;
	private static DocumentType NON_CONFORMITY_NOTIFICATION_TYPE;

	private static SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	private static SampleProjectService sampleProjectService = SpringContext.getBean(SampleProjectService.class);
	private static TypeOfSampleService typeOfSampleService = SpringContext.getBean(TypeOfSampleService.class);
	private static ObservationHistoryService observationService = SpringContext
			.getBean(ObservationHistoryService.class);
	private static DocumentTrackService documentTrackService = SpringContext.getBean(DocumentTrackService.class);

	static {
		ObservationHistoryType oht = SpringContext.getBean(ObservationHistoryTypeService.class)
				.getByName("nameOfDoctor");
		if (oht != null) {
			DOCTOR_OBSERVATION_ID = oht.getId();
		}

		NON_CONFORMITY_NOTIFICATION_TYPE = SpringContext.getBean(DocumentTypeService.class)
				.getDocumentTypeByName("nonConformityNotification");
		SAMPLE_QAEVENT_TABLE_ID = SpringContext.getBean(ReferenceTablesService.class)
				.getReferenceTableByName("SAMPLE_QAEVENT").getId();
	}

	public static enum DocumentTypes {
		NON_CONFORMITY_NOTIFCATION
	}

	public static Patient findPatient(Sample sample) {
		return sampleHumanService.getPatientForSample(sample);
	}

	public static Project findProject(Sample sample) {
		SampleProject sampleProject = sampleProjectService.getSampleProjectBySampleId(sample.getId());
		if (sampleProject == null) {
			return null;
		}
		return sampleProject.getProject();
	}

	public static String getSampleType(SampleQaEvent event) {
		SampleItem sampleItem = event.getSampleItem();
		return (sampleItem == null) ? MessageUtil.getContextualMessage("nonConformant.allSampleTypesText")
				: findTypeOfSample(sampleItem.getTypeOfSampleId());

	}

	private static String findTypeOfSample(String typeOfSampleId) {
		return typeOfSampleService.getTypeOfSampleById(typeOfSampleId).getLocalizedName();
	}

	public static String findDoctorForSample(Sample sample) {
		ObservationHistory oh = observationService.getObservationHistoriesBySampleIdAndType(sample.getId(),
				DOCTOR_OBSERVATION_ID);
		return oh == null ? "" : oh.getValue();

	}

	public static void markDocumentsAsPrinted(DocumentTypes docType, List<String> recordIds, String currentUserId,
			Set<String> checkPriorPrintList) {

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

		List<DocumentTrack> documents = new ArrayList<>();

		for (String recordId : recordIds) {
			DocumentTrack document = new DocumentTrack();
			document.setDocumentTypeId(documentType.getId());
			document.setRecordId(recordId);
			document.setTableId(tableId);
			document.setReportTime(reportTime);
			document.setSysUserId(currentUserId);
			document.setLastupdated(reportTime);

			if (checkPriorPrintList.contains(recordId)) {
				List<DocumentTrack> priorDocuments = documentTrackService.getByTypeRecordAndTable(documentType.getId(),
						tableId, recordId);
				if (!priorDocuments.isEmpty()) {
					document.setParent(priorDocuments.get(priorDocuments.size() - 1));
				}
			}

			documents.add(document);
		}

		try {
			documentTrackService.insertAll(documents);
//			for (DocumentTrack document : documents) {
//				documentTrackService.insertData(document);
//			}

		} catch (HibernateException e) {
			LogEvent.logErrorStack("ReportUtil", "markDocumentsAsPrinted()", e);
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

		return !documentTrackService.getByTypeRecordAndTable(documentType.getId(), tableId, recordId).isEmpty();
	}
}
