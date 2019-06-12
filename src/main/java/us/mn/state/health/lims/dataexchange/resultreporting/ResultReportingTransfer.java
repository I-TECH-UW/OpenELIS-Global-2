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
package us.mn.state.health.lims.dataexchange.resultreporting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import spring.service.dataexchange.aggregatereporting.ReportExternalExportService;
import spring.service.dataexchange.aggregatereporting.ReportQueueTypeService;
import spring.service.dataexchange.orderresult.HL7MessageOutService;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.reports.DocumentTrackService;
import spring.service.reports.DocumentTypeService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ResultReportXmit;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;
import us.mn.state.health.lims.result.valueholder.Result;

public class ResultReportingTransfer {

	private static DocumentType DOCUMENT_TYPE;
	private static String QUEUE_TYPE_ID;
	private static String RESULT_REFERRANCE_TABLE_ID;

	static {
		DOCUMENT_TYPE = SpringContext.getBean(DocumentTypeService.class).getDocumentTypeByName("resultExport");
		ReferenceTables referenceTable = SpringContext.getBean(ReferenceTablesService.class)
				.getReferenceTableByName("RESULT");
		if (referenceTable != null) {
			RESULT_REFERRANCE_TABLE_ID = referenceTable.getId();
		}
		ReportQueueType queueType = SpringContext.getBean(ReportQueueTypeService.class)
				.getReportQueueTypeByName("Results");
		if (queueType != null) {
			QUEUE_TYPE_ID = queueType.getId();
		}
	}

	public void sendResults(ResultReportXmit resultReport, List<Result> reportingResult, String url) {

		if (resultReport.getTestResults() == null || resultReport.getTestResults().isEmpty()) {
			return;
		}

		ITransmissionResponseHandler responseHandler = new ResultFailHandler(reportingResult);
		new ReportTransmission().sendHL7Report(resultReport, url, responseHandler);
	}

	class ResultFailHandler implements ITransmissionResponseHandler {

		private List<Result> reportingResults;

		public ResultFailHandler(List<Result> reportingResults) {
			this.reportingResults = reportingResults;
		}

		@Override
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {
			if (httpReturnStatus == HttpServletResponse.SC_OK) {
				markFinalResultsAsSent();
				persistMessage(msg, true);
			} else {
				bufferResults(msg);
				persistMessage(msg, false);
			}
		}

		private void persistMessage(String msg, boolean success) {
			HL7MessageOutService messageOutService = SpringContext.getBean(HL7MessageOutService.class);
			HL7MessageOut messageOut = new HL7MessageOut();
			messageOut.setData(msg);
			if (success) {
				messageOut.setStatus(HL7MessageOut.SUCCESS);
			} else {
				messageOut.setStatus(HL7MessageOut.FAIL);
			}
			messageOutService.insert(messageOut);
		}

		private void bufferResults(String msg) {
			ReportExternalExport report = new ReportExternalExport();
			report.setData(msg);
			report.setSysUserId("1");
			report.setEventDate(DateUtil.getNowAsTimestamp());
			report.setCollectionDate(DateUtil.getNowAsTimestamp());
			report.setTypeId(QUEUE_TYPE_ID);
			report.setBookkeepingData(getResultIdListString() == null ? "" : getResultIdListString());
			report.setSend(true);

			try {
				SpringContext.getBean(ReportExternalExportService.class).insert(report);
			} catch (LIMSRuntimeException lre) {
				LogEvent.logErrorStack(this.getClass().getSimpleName(), "bufferResults()", lre);
			}
		}

		private String getResultIdListString() {
			String comma = "";

			StringBuilder builder = new StringBuilder();

			for (Result result : reportingResults) {
				builder.append(comma); // empty first time through
				builder.append(result.getId());

				comma = ",";
			}

			return builder.toString();
		}

		private void markFinalResultsAsSent() {
			Timestamp now = DateUtil.getNowAsTimestamp();

			List<DocumentTrack> documents = new ArrayList<>();

			for (Result result : reportingResults) {
				if (result.getResultEvent() == Event.FINAL_RESULT || result.getResultEvent() == Event.CORRECTION) {
					DocumentTrack document = new DocumentTrack();
					document.setDocumentTypeId(DOCUMENT_TYPE.getId());
					document.setRecordId(result.getId());
					document.setReportTime(now);
					document.setTableId(RESULT_REFERRANCE_TABLE_ID);
					document.setSysUserId("1");
					documents.add(document);
				}
			}

			DocumentTrackService trackService = SpringContext.getBean(DocumentTrackService.class);

			try {
				trackService.insertAll(documents);
//				for (DocumentTrack document : documents) {
//					trackService.insert(document);
//				}
			} catch (LIMSRuntimeException lre) {
				LogEvent.logErrorStack(this.getClass().getSimpleName(), "markFinalResultsAsSent()", lre);
			}
		}
	}
}
