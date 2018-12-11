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
package us.mn.state.health.lims.dataexchange.aggregatereporting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class AggregateReportJob implements Job {
	private static String LAB_INDICATOR_REPORT_ID;
	private static ReportExternalExportDAO reportExternalExportDAO = new ReportExternalExportDAOImpl();
	private static CronSchedulerDAO cronSchedulerDAO = new CronSchedulerDAOImpl();
	private static SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();

	static {
		ReportQueueType reportType = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("labIndicator");

		if (reportType != null) {
			LAB_INDICATOR_REPORT_ID = reportType.getId();
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Reporting triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));
		LogEvent.logInfo("AggregateReportJob", "execute()", "Reporting triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));

		updateRunTimestamp();

		List<ReportExternalExport> sendableReports = reportExternalExportDAO.getUnsentReportExports(LAB_INDICATOR_REPORT_ID);

		if (!sendableReports.isEmpty()) {
			SendingAggregateReportWrapper wrapper = new SendingAggregateReportWrapper();
			wrapper.setSiteId(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));

			SiteInformation authInfo = siteInfoDAO.getSiteInformationByName("testUsageAggregationUserName");
			wrapper.setUser(authInfo == null ? " " : authInfo.getValue());
			authInfo = siteInfoDAO.getSiteInformationByName("testUsageAggregationPassword");
			wrapper.setPassword(authInfo == null ? " " : authInfo.getValue());

			List<AggregateReportXmit> reportXmit = new ArrayList<AggregateReportXmit>();

		
			for (ReportExternalExport report : sendableReports) {	
				reportXmit.add(new AggregateReportXmit(report));
			}

			wrapper.setReports(reportXmit);

			String castorPropertyName = "AggregateReportingMapping";

			String url = ConfigurationProperties.getInstance().getPropertyValue(Property.testUsageReportingURL) + "/IndicatorAggregation";//

			boolean sendAsychronously = false;
			ResponseHandler responseHandler = new ResponseHandler();
			responseHandler.setReports(sendableReports);
			responseHandler.setReAttemptTry(wrapper, castorPropertyName, url);

			//String xml = createXML(wrapper);
			new ReportTransmission().sendReport(wrapper, castorPropertyName, url, sendAsychronously, responseHandler);
		    //new ReportTransmission().sendRawReport(xml, url, sendAsychronously, responseHandler);

		}
	}

	/*
	 * This is an alternative if castor is not behaving well.  It was adding newline chars to the data which was messing it up when it's displayed
	 */
/*	private String createXML(SendingAggregateReportWrapper wrapper) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append("<aggregate-reports>\n");
		buffer.append("<version>1</version>\n");
		buffer.append("<drowssap>");
		buffer.append(wrapper.getPassword());
		buffer.append("</drowssap>\n");
		buffer.append(" <user>");
		buffer.append(wrapper.getUser());
		buffer.append("</user>\n");
		buffer.append("<site-id>");
		buffer.append(wrapper.getSiteId());
		buffer.append("</site-id>\n");
		for (AggregateReportXmit report : wrapper.getReports()) {
			buffer.append(" <reports xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"report\">\n");
			buffer.append("<event-date>");
			buffer.append(report.getEventDate());
			buffer.append("</event-date>\n");
			buffer.append("<data>");
			buffer.append(report.getData().replace("\"", "&quot;").replace("'", "&apos;"));
			buffer.append("</data>\n");
			buffer.append("</reports>\n");
		}
		buffer.append("</aggregate-reports>");

		return buffer.toString();
	}
*/
	private void updateRunTimestamp() {
		CronScheduler reportScheduler = cronSchedulerDAO.getCronScheduleByJobName("sendSiteIndicators");
		reportScheduler.setLastRun(DateUtil.getNowAsTimestamp());
		reportScheduler.setSysUserId("1");

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			cronSchedulerDAO.update(reportScheduler);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
	}

	class ResponseHandler implements ITransmissionResponseHandler {

		private static final long MAX_DELAY = 256; // Anything past this will be
													// a cumulative time of over
													// 8 hours
		private static final long MILLI_SEC_PER_MIN = 1000L * 60L;
		private List<ReportExternalExport> sendableReports;
		private ReportExternalExportDAO reportExternalExportDAO = new ReportExternalExportDAOImpl();
		private long delayInMin = 5L;
		private SendingAggregateReportWrapper wrapper;
		private String castorPropertyName;
		private String url;
		private ResponseHandler instance = this;

		public void setReports(List<ReportExternalExport> sendableReports) {
			this.sendableReports = sendableReports;
		}

		public void setReAttemptTry(SendingAggregateReportWrapper wrapper, String castorPropertyName, String url) {
			this.wrapper = wrapper;
			this.castorPropertyName = castorPropertyName;
			this.url = url;
		}

		@Override
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {

			switch (httpReturnStatus) {
			case HttpServletResponse.SC_OK:
				handleSuccess();
				break;
			case HttpServletResponse.SC_UNAUTHORIZED:
				handleUnauthorized();
				retry();
				break;
			case HttpServletResponse.SC_NOT_FOUND:
				handleNotFound(errors);
				retry();
				break;
			case HttpServletResponse.SC_BAD_REQUEST:
				handleBadRequest(errors);
				break;
			default:
				handleUnknownFailure(errors);
				retry();
			}
		}

		private void handleSuccess() {

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				for (ReportExternalExport report : sendableReports) {
					report = reportExternalExportDAO.loadReport(report);
					report.setSend(false);
					report.setSentDate(DateUtil.getNowAsTimestamp());
					report.setSysUserId("1");
					reportExternalExportDAO.updateReportExternalExport(report);
				}

				SiteInformation sendInfo = siteInfoDAO.getSiteInformationByName("testUsageSendStatus");
				if (sendInfo != null) {
					sendInfo.setValue(StringUtil.getMessageForKey("http.success"));
					sendInfo.setSysUserId("1");
					siteInfoDAO.updateData(sendInfo);
				}

				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			}

		}

		private void handleUnknownFailure(List<String> errors) {
			for (String error : errors) {
				if (error.startsWith("Unable to connect")) {
					writeSendStatus(StringUtil.getMessageForKey("http.site.unreachable"));
					return;
				}
			}

			writeSendStatus(StringUtil.getMessageForKey("http.unknown.failure"));
		}

		private void handleUnauthorized() {
			writeSendStatus(StringUtil.getMessageForKey("http.unauthorized"));
		}

		private void handleNotFound(List<String> errors) {
			String error = errors.isEmpty() ? "" : errors.get(0);
			writeSendStatus(StringUtil.getContextualMessageForKey("http.notfound") + ": " + error);
		}

		private void handleBadRequest(List<String> errors) {
			String error = errors.isEmpty() ? "" : errors.get(0);
			writeSendStatus(StringUtil.getContextualMessageForKey("http.badrequest") + ": " + error);
		}

		private void retry() {
			delayInMin = delayInMin * 2L;
			if (delayInMin < MAX_DELAY) {
				new Thread() {
					public void run() {
						System.out.println("Aggregate Report: Will attempt to resend report in " + delayInMin + " minutes.");
						LogEvent.logInfo("AggregateReportJob", "retry()", "Will attempt to resend report in " + delayInMin + " minutes.");
						try {
							sleep(delayInMin * MILLI_SEC_PER_MIN);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						new ReportTransmission().sendReport(wrapper, castorPropertyName, url, false, instance);
					}
				}.start();
			} else {
				System.out.println("Aggregate report: Giving up trying to connect");
				LogEvent.logInfo("AggregateReportJob", "retry()", "Giving up trying to connect");
			}
		}

		private void writeSendStatus(String status) {
			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				SiteInformation sendInfo = siteInfoDAO.getSiteInformationByName("testUsageSendStatus");
				if (sendInfo != null) {
					sendInfo.setValue(status);
					sendInfo.setSysUserId("1");
					siteInfoDAO.updateData(sendInfo);
				}
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			}
		}

	}

}
