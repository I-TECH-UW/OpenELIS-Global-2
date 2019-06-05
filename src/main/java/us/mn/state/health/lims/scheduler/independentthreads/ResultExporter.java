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
package us.mn.state.health.lims.scheduler.independentthreads;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.dataexchange.aggregatereporting.ReportExternalExportService;
import spring.service.dataexchange.aggregatereporting.ReportQueueTypeService;
import spring.service.dataexchange.orderresult.HL7MessageOutService;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.reports.DocumentTrackService;
import spring.service.reports.DocumentTypeService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission.HTTP_TYPE;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

@Service
public class ResultExporter extends Thread {

	private long sleepTime;
	private boolean running = true;
	@Autowired
	private ReportQueueTypeService reportQueueTypeService;
	@Autowired
	private ReferenceTablesService referenceTablesService;
	@Autowired
	private DocumentTypeService documentTypeService;
	@Autowired
	private DocumentTrackService trackService;
	@Autowired
	private HL7MessageOutService hl7MessageService;
	@Autowired
	private ReportExternalExportService reportExternalExportService;

	private String resultReportTypeId;

	@PostConstruct
	public void setupGlobalVariables() {
		resultReportTypeId = reportQueueTypeService.getReportQueueTypeByName("Results").getId();
	}

	public void setSleepInMin(long sleepInMin) {
		sleepTime = sleepInMin * 1000L * 60L;
	}

	@Override
	public void run() {

		while (running) {
			exportResults();

			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				running = false;
			}
		}

	}

	public void stopExports() {
		running = false;
	}

	private void exportResults() {
		if (shouldReportResults()) {
			List<ReportExternalExport> reportList = reportExternalExportService
					.getUnsentReportExports(resultReportTypeId);

			ReportTransmission transmitter = new ReportTransmission();
			String url = ConfigurationProperties.getInstance().getPropertyValue(Property.resultReportingURL);
			boolean sendAsychronously = false;

			for (ReportExternalExport report : reportList) {
				transmitter.sendRawReport(report.getData(), url, sendAsychronously,
						new SuccessReportHandler(report.getId()), HTTP_TYPE.POST);
			}
		}
	}

	private boolean shouldReportResults() {
		String reportResults = ConfigurationProperties.getInstance().getPropertyValueLowerCase(Property.reportResults);
		return ("true".equals(reportResults) || "enable".equals(reportResults));
	}

	class SuccessReportHandler implements ITransmissionResponseHandler {
		String externalExportRowId;

		public SuccessReportHandler(String rowId) {
			externalExportRowId = rowId;
		}

		@Override
		@Transactional
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {

			if (httpReturnStatus == HttpServletResponse.SC_OK) {
				ReportExternalExport report = reportExternalExportService.readReportExternalExport(externalExportRowId);
				List<DocumentTrack> documents = getSentDocuments(report.getBookkeepingData());

//				Transaction tx = HibernateUtil.getSession().beginTransaction();

				try {
					HL7MessageOut hl7Message = hl7MessageService.getByData(msg);
					if (hl7Message != null) {
						hl7Message.setStatus(HL7MessageOut.SUCCESS);
						hl7MessageService.update(hl7Message);
					}
					for (DocumentTrack document : documents) {
						trackService.insertData(document);
					}
					reportExternalExportService.delete(report);

//					tx.commit();

				} catch (LIMSRuntimeException lre) {
//					tx.rollback();
					LogEvent.logErrorStack(this.getClass().getSimpleName(), "handleResponse", lre);
					throw lre;
				}

			}

		}

		private List<DocumentTrack> getSentDocuments(String bookkeepingData) {
			List<DocumentTrack> documentList = new ArrayList<>();
			String resultTableId = getResultTableId();
			DocumentType type = getResultType();
			Timestamp now = DateUtil.getNowAsTimestamp();

			if (!GenericValidator.isBlankOrNull(bookkeepingData) && !"null".equals(bookkeepingData)) {
				String[] resultIdList = bookkeepingData.split(",");

				for (int i = 0; i < resultIdList.length; i++) {
					DocumentTrack document = new DocumentTrack();
					document.setDocumentTypeId(type.getId());
					document.setRecordId(resultIdList[i]);
					document.setReportTime(now);
					document.setTableId(resultTableId);
					document.setSysUserId("1");
					documentList.add(document);
				}
			}
			return documentList;
		}

		private DocumentType getResultType() {
			return documentTypeService.getDocumentTypeByName("resultExport");
		}

		private String getResultTableId() {
			return referenceTablesService.getReferenceTableByName("RESULT").getId();
		}

	}
}
