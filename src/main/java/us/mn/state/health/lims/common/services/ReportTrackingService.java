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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.referencetables.ReferenceTablesService;
import spring.service.reports.DocumentTrackService;
import spring.service.reports.DocumentTypeService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
public class ReportTrackingService {
	public enum ReportType {
		PATIENT, NON_CONFORMITY_NOTIFICATION, RESULT_EXPORT, MALARIA_CASE
	}

	private static ReportTrackingService INSTANCE;

	@Autowired
	private DocumentTrackService documentTrackService;
	@Autowired
	private DocumentTypeService documentTypeService;
	@Autowired
	private ReferenceTablesService referenceTablesService;

	private String PATIENT_DOCUMENT_TYPE_ID;
	private String NON_CONFORMITY_DOCUMENT_TYPE_ID;
	private String RESULT_EXPORT_DOCUMENT_TYPE_ID;
	private String MALARIA_CASE_DOCUMENT_TYPE_ID;

	@PostConstruct
	private void registerInstance() {
		INSTANCE = this;
	}

	@PostConstruct
	private void initialize() {
		PATIENT_DOCUMENT_TYPE_ID = documentTypeService.getDocumentTypeByName("patientReport").getId();
		NON_CONFORMITY_DOCUMENT_TYPE_ID = documentTypeService.getDocumentTypeByName("nonConformityNotification")
				.getId();
		RESULT_EXPORT_DOCUMENT_TYPE_ID = documentTypeService.getDocumentTypeByName("resultExport").getId();
		MALARIA_CASE_DOCUMENT_TYPE_ID = documentTypeService.getDocumentTypeByName("malariaCase").getId();
	}

	public static ReportTrackingService getInstance() {
		return INSTANCE;
	}

	@Transactional
	public void addReports(List<String> refIds, ReportType type, String name, String currentSystemUserId) {

		String refTableId = getReferenceTable(type);

		for (String id : refIds) {
			DocumentTrack docTrack = new DocumentTrack();
			docTrack.setDocumentTypeId(getReportTypeId(type));
			docTrack.setRecordId(id);
			docTrack.setReportTime(DateUtil.getNowAsTimestamp());
			docTrack.setDocumentName(name);
			docTrack.setTableId(refTableId);
			docTrack.setSysUserId(currentSystemUserId);
			DocumentTrack parent = getParent(id, refTableId, docTrack.getDocumentTypeId());
			if (parent != null) {
				docTrack.setParent(parent);
			}
			documentTrackService.insertData(docTrack);

		}

	}

	private DocumentTrack getParent(String id, String refTableId, String documentTypeId) {
		List<DocumentTrack> docs = documentTrackService.getByTypeRecordAndTable(documentTypeId, refTableId, id);
		return docs.isEmpty() ? null : docs.get(docs.size() - 1);
	}

	private String getReferenceTable(ReportType type) {
		switch (type) {
		case PATIENT: {
			return referenceTablesService.getReferenceTableByName("SAMPLE").getId();
		}
		case NON_CONFORMITY_NOTIFICATION: {
			return referenceTablesService.getReferenceTableByName("SAMPLE_QAEVENT").getId();
		}
		case MALARIA_CASE: {
			return referenceTablesService.getReferenceTableByName("ANALYSIS").getId();
		}
		case RESULT_EXPORT: {
			return referenceTablesService.getReferenceTableByName("ANALYSIS").getId();
		}

		}

		return null;
	}

	private String getReportTypeId(ReportType type) {
		switch (type) {
		case PATIENT: {
			return PATIENT_DOCUMENT_TYPE_ID;
		}
		case NON_CONFORMITY_NOTIFICATION: {
			return NON_CONFORMITY_DOCUMENT_TYPE_ID;
		}
		case RESULT_EXPORT: {
			return RESULT_EXPORT_DOCUMENT_TYPE_ID;
		}
		case MALARIA_CASE: {
			return MALARIA_CASE_DOCUMENT_TYPE_ID;
		}

		}

		return null;
	}

	public List<DocumentTrack> getReportsForSample(Sample sample, ReportType type) {
		return documentTrackService.getByTypeRecordAndTable(getReportTypeId(type), getReferenceTable(type),
				sample.getId());
	}

	public List<DocumentTrack> getReportsForSampleAndReportName(Sample sample, ReportType type, String name) {
		return documentTrackService.getByTypeRecordAndTableAndName(getReportTypeId(type), getReferenceTable(type),
				sample.getId(), name);
	}

	public DocumentTrack getLastReportForSample(Sample sample, ReportType type) {
		List<DocumentTrack> reports = getReportsForSample(sample, type);
		return reports.isEmpty() ? null : reports.get(reports.size() - 1);
	}

	public DocumentTrack getLastNamedReportForSample(Sample sample, ReportType type, String name) {
		if (sample == null || type == null || GenericValidator.isBlankOrNull(name)) {
			return null;
		}

		List<DocumentTrack> reports = getReportsForSampleAndReportName(sample, type, name);
		return reports.isEmpty() ? null : reports.get(reports.size() - 1);
	}

	public Timestamp getTimeOfLastReport(Sample sample, ReportType type) {
		DocumentTrack report = getLastReportForSample(sample, type);
		return report == null ? null : report.getReportTime();
	}

	public Timestamp getTimeOfLastNamedReport(Sample sample, ReportType type, String name) {
		DocumentTrack report = getLastNamedReportForSample(sample, type, name);
		return report == null ? null : report.getReportTime();
	}

	public DocumentTrack getDocumentForId(String id) {
		return documentTrackService.readEntity(id);
	}
}
