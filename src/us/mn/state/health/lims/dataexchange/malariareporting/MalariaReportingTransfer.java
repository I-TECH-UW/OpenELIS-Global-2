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
package us.mn.state.health.lims.dataexchange.malariareporting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ResultReportXmit;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;
import us.mn.state.health.lims.result.valueholder.Result;

public class MalariaReportingTransfer {

	private static String DOCUMENT_TYPE_ID;
	private static String QUEUE_TYPE_ID;
	private static String RESULT_REFERRANCE_TABLE_ID;
	
	static{
		DocumentType malairaDocumentType = new DocumentTypeDAOImpl().getDocumentTypeByName("malariaCase"); 
		if( malairaDocumentType != null){
			DOCUMENT_TYPE_ID = malairaDocumentType.getId();
		}
		
		
		ReferenceTables  referenceTable = new ReferenceTablesDAOImpl().getReferenceTableByName("RESULT");
		if( referenceTable != null){
			RESULT_REFERRANCE_TABLE_ID = referenceTable.getId();
		}
		
		ReportQueueType queueType = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("malariaCase");
		if( queueType != null){
			QUEUE_TYPE_ID = queueType.getId();
		}
		
		
	}
	public void sendResults(ResultReportXmit resultReport, List<Result> reportingResult, String url) {

		if (resultReport.getTestResults() == null || resultReport.getTestResults().isEmpty()) {
			return;
		}

		String castorPropertyName = "ResultReportingMapping";
		boolean sendAsychronously = true;
		ITransmissionResponseHandler responseHandler = new ResultFailHandler(reportingResult);

		new ReportTransmission().sendReport(resultReport, castorPropertyName, url, sendAsychronously, responseHandler);

	}

	class ResultFailHandler implements ITransmissionResponseHandler {

		private List<Result> reportingResults;
		
		public ResultFailHandler(List<Result> reportingResults) {
			this.reportingResults = reportingResults;
		}

		@Override
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {

			if (httpReturnStatus == HttpServletResponse.SC_OK) {
				markResultsAsSent();
			} else {
				bufferResults(msg);
			}
		}

		private void bufferResults(String msg) {
			ReportExternalExport report = new ReportExternalExport();
			report.setData(msg);
			report.setSysUserId("1");
			report.setEventDate(DateUtil.getNowAsTimestamp());
			report.setCollectionDate(DateUtil.getNowAsTimestamp());
			report.setTypeId(QUEUE_TYPE_ID);
			report.setBookkeepingData(getResultIdListString());
			report.setSend(true);
			
			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				new ReportExternalExportDAOImpl().insertReportExternalExport(report);

				tx.commit();

			} catch (LIMSRuntimeException lre) {
				tx.rollback();
			}
		}

		private String getResultIdListString() {
			String comma = "";
			
			StringBuilder builder = new StringBuilder();
			
			for(Result result : reportingResults){
				builder.append(comma);  //empty first time through
				builder.append(result.getId());
				
				comma = ",";
			}
			
			return builder.toString();
		}

		private void markResultsAsSent() {
			Timestamp now = DateUtil.getNowAsTimestamp();

			List<DocumentTrack> documents = new ArrayList<DocumentTrack>();
			
			for (Result result : reportingResults) {
				DocumentTrack document = new DocumentTrack();
				document.setDocumentTypeId(DOCUMENT_TYPE_ID);
				document.setRecordId(result.getId());
				document.setReportTime(now);
				document.setTableId(RESULT_REFERRANCE_TABLE_ID);
				document.setSysUserId("1");
				documents.add(document);
			}

			DocumentTrackDAO trackDAO = new DocumentTrackDAOImpl();

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {

				for (DocumentTrack document : documents) {
					trackDAO.insertData(document);
				}

				tx.commit();
			} catch (LIMSRuntimeException lre) {
				tx.rollback();
			}
		}
	}
}
