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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission.HTTP_TYPE;
import us.mn.state.health.lims.dataexchange.orderresult.DAO.HL7MessageOutDAOImpl;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;

public class ResultExporter extends Thread {

	private long sleepTime;
	private boolean running = true;
	private ReportExternalExportDAO reportExportDAO = new ReportExternalExportDAOImpl();
	private String resultReportTypeId;

	public ResultExporter(long sleepInMin) {
		sleepTime = sleepInMin * 1000L * 60L;
		resultReportTypeId = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("Results").getId();
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
			List<ReportExternalExport> reportList = reportExportDAO.getUnsentReportExports(resultReportTypeId);

			ReportTransmission transmitter = new ReportTransmission();
			String url = ConfigurationProperties.getInstance().getPropertyValue(Property.resultReportingURL);
			boolean sendAsychronously = false;

			for (ReportExternalExport report : reportList) {
				transmitter.sendRawReport(report.getData(), url, sendAsychronously, new SuccessReportHandler(report.getId()), HTTP_TYPE.POST);
			}
		}
	}

	private boolean shouldReportResults() {
		String reportResults = ConfigurationProperties.getInstance().getPropertyValueLowerCase(Property.reportResults);
		return ("true".equals(reportResults) || "enable".equals(reportResults) );
	}

	class SuccessReportHandler implements ITransmissionResponseHandler {
		String externalExportRowId;

		public SuccessReportHandler(String rowId) {
			externalExportRowId = rowId;
		}

		@Override
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {

			if (httpReturnStatus == HttpServletResponse.SC_OK) {
				ReportExternalExport report = reportExportDAO.readReportExternalExport(externalExportRowId);
				List<DocumentTrack> documents = getSentDocuments(report.getBookkeepingData());

				DocumentTrackDAO trackDAO = new DocumentTrackDAOImpl();
				Transaction tx = HibernateUtil.getSession().beginTransaction();

				try {
					HL7MessageOutDAOImpl hl7MessageDAO = new HL7MessageOutDAOImpl();
					HL7MessageOut hl7Message = hl7MessageDAO.getByData(msg); 
					if (hl7Message != null) {
						hl7Message.setStatus(HL7MessageOut.SUCCESS);
						hl7MessageDAO.update(hl7Message);
					}
					for (DocumentTrack document : documents) {
						trackDAO.insertData(document);
					}
					new ReportExternalExportDAOImpl().delete(report);

					tx.commit();

				} catch (LIMSRuntimeException lre) {
					tx.rollback();
				}

			}

		}

		private List<DocumentTrack> getSentDocuments(String bookkeepingData) {
			List<DocumentTrack> documentList = new ArrayList<DocumentTrack>();
			String resultTableId = getResultTableId();
			DocumentType type = getResultType();
			Timestamp now = DateUtil.getNowAsTimestamp();
			
			if (!GenericValidator.isBlankOrNull(bookkeepingData) && !"null".equals(bookkeepingData)) {
				String[] resultIdList = bookkeepingData.split(",");
				
				for( int i = 0; i < resultIdList.length; i++){
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
			return new DocumentTypeDAOImpl().getDocumentTypeByName("resultExport");
		}

		private String getResultTableId() {
			return new ReferenceTablesDAOImpl().getReferenceTableByName("RESULT").getId();
		}


	}
}
