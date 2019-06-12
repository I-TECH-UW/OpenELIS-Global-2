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
package us.mn.state.health.lims.reports.action.implementation;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import spring.mine.internationalization.MessageUtil;
import spring.service.image.ImageService;
import spring.service.organization.OrganizationService;
import spring.service.siteinformation.SiteInformationService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ErrorMessages;

public abstract class Report implements IReportCreator {

	public static ImageService imageService = SpringContext.getBean(ImageService.class);
	public static SiteInformationService siteInformationService = SpringContext.getBean(SiteInformationService.class);
	private OrganizationService organizationService = SpringContext.getBean(OrganizationService.class);
	public static final String ERROR_REPORT = "NoticeOfReportError";

	protected static final String CSV = "csv";

	protected boolean initialized = false;
	protected boolean errorFound = false;
	protected List<ErrorMessages> errorMsgs = new ArrayList<>();
	protected HashMap<String, Object> reportParameters = null;
	protected String requestedReport;
	private String fullReportFilename;

	@Override
	public void setRequestedReport(String report) {
		requestedReport = report;
	}

	protected void initializeReport() {
		initialized = true;
	}

	@Override
	public String getResponseHeaderName() {
		return null;
	}

	@Override
	public String getResponseHeaderContent() {
		return null;
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.IReportCreator#getContentType()
	 */
	@Override
	public String getContentType() {
		return "application/pdf; charset=UTF-8";
	}

	/**
	 * Make sure we have a reportParameters map and make sure there is lab director
	 * in that map (for any possible error report). All reports need a director name
	 * either in their header including or on their error report page."
	 */
	protected void createReportParameters() {
		reportParameters = (reportParameters != null) ? reportParameters : new HashMap<>();
		reportParameters.put("directorName",
				ConfigurationProperties.getInstance().getPropertyValue(Property.labDirectorName));
		reportParameters.put("siteName", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
		reportParameters.put("additionalSiteInfo",
				ConfigurationProperties.getInstance().getPropertyValue(Property.ADDITIONAL_SITE_INFO));
		reportParameters.put("usePageNumbers",
				ConfigurationProperties.getInstance().getPropertyValue(Property.USE_PAGE_NUMBERS_ON_REPORTS));
		reportParameters.put("localization", createLocalizationMap());
		// reportParameters.put("leftHeaderImage", getImage("headerLeftImage"));
		// reportParameters.put("rightHeaderImage", getImage("headerRightImage"));
		reportParameters.put("REPORT_LOCALE", SystemConfiguration.getInstance().getDefaultLocale());
	}

//	@Deprecated
//	private Object getImage(String siteName) {
//		SiteInformation siteInformation = siteInformationService.getSiteInformationByName(siteName);
//		return GenericValidator.isBlankOrNull(siteInformation.getValue()) ? null
//				: imageService.retrieveImageInputStream(siteInformation.getValue());
//	}

	/**
	 *
	 * @return map
	 * @deprecated The correct way to localize JasperReports is to us $R{key}. This
	 *             was put in before the correct way was understood. Do not add to
	 *             this list. It will eventually be moved to the correct way.
	 */
	@Deprecated
	protected Map<String, String> createLocalizationMap() {
		HashMap<String, String> localizationMap = new HashMap<>();
		localizationMap.put("requestOrderNumber", MessageUtil.getMessage("report.requestOrderNumber"));
		localizationMap.put("confirmationOrderNumber", MessageUtil.getMessage("report.confirmationOrderNumber"));
		localizationMap.put("sampleType", MessageUtil.getMessage("report.sampleType"));
		localizationMap.put("reception", MessageUtil.getMessage("report.reception"));
		localizationMap.put("initialResults", MessageUtil.getMessage("report.initialResults"));
		localizationMap.put("confirmationResults", MessageUtil.getMessage("report.confirmationResult"));
		localizationMap.put("requesterContact", MessageUtil.getMessage("report.requesterContact"));
		localizationMap.put("telephoneAbv", MessageUtil.getMessage("report.telephoneAbv"));
		localizationMap.put("completionDate", MessageUtil.getMessage("report.completionDate"));
		localizationMap.put("site", MessageUtil.getMessage("report.site"));
		localizationMap.put("fax", MessageUtil.getMessage("report.fax"));
		localizationMap.put("email", MessageUtil.getMessage("report.email"));
		localizationMap.put("test", MessageUtil.getMessage("report.test"));
		localizationMap.put("result", MessageUtil.getMessage("report.result"));
		localizationMap.put("note", MessageUtil.getMessage("report.note"));
		localizationMap.put("pageNumberOf", MessageUtil.getMessage("report.pageNumberOf"));
		localizationMap.put("collectionDate", MessageUtil.getMessage("report.collectionDate"));
		/* For patient report CDI */
		localizationMap.put("patientCode", MessageUtil.getMessage("report.patientCode"));
		localizationMap.put("prescriber", MessageUtil.getMessage("report.prescriber"));
		localizationMap.put("districtFacility", MessageUtil.getMessage("report.districtFacility"));
		localizationMap.put("regionFacility", MessageUtil.getMessage("report.regionFacility"));
		localizationMap.put("referringSite", MessageUtil.getMessage("report.referringSite"));
		localizationMap.put("ordinanceNo", MessageUtil.getMessage("report.ordinanceNo"));
		localizationMap.put("orderDate", MessageUtil.getMessage("report.orderDate"));
		localizationMap.put("receiptDate", MessageUtil.getMessage("report.receiptDate"));
		localizationMap.put("specimenAndNo", MessageUtil.getMessage("report.specimenAndNo"));
		localizationMap.put("collectionDate", MessageUtil.getMessage("report.collectionDate"));
		localizationMap.put("outcome", MessageUtil.getMessage("report.outcome"));
		localizationMap.put("referenceValue", MessageUtil.getMessage("report.referenceValue"));
		localizationMap.put("unit", MessageUtil.getMessage("report.unit"));
		localizationMap.put("labInfomation", MessageUtil.getMessage("report.labInfomation"));
		localizationMap.put("serviceHead", MessageUtil.getMessage("report.serviceHead"));
		localizationMap.put("associateProfessor", MessageUtil.getMessage("report.associateProfessor"));
		localizationMap.put("assHeadOfBioclinicque", MessageUtil.getMessage("report.assHeadOfBioclinicque"));
		localizationMap.put("reportDate", MessageUtil.getMessage("report.reportDate"));
		localizationMap.put("about", MessageUtil.getMessage("report.about"));
		localizationMap.put("idNational", MessageUtil.getMessage("report.idNational"));
		localizationMap.put("program", MessageUtil.getMessage("report.program"));
		localizationMap.put("status", MessageUtil.getMessage("report.status"));
		localizationMap.put("alert", MessageUtil.getMessage("report.alert"));
		localizationMap.put("correctedReport", MessageUtil.getMessage("report.correctedReport"));
		localizationMap.put("signValidation", MessageUtil.getMessage("report.signValidation"));
		localizationMap.put("date", MessageUtil.getMessage("report.date"));
		localizationMap.put("analysisReport", MessageUtil.getMessage("report.analysisReport"));
		localizationMap.put("specimen", MessageUtil.getMessage("report.specimen"));
		localizationMap.put("specimenCollectTimes", MessageUtil.getMessage("report.specimenCollectTimes"));

		/* HIV summary */
		localizationMap.put("total", MessageUtil.getMessage("report.total"));
		localizationMap.put("children", MessageUtil.getMessage("report.children"));
		localizationMap.put("women", MessageUtil.getMessage("report.women"));
		localizationMap.put("men", MessageUtil.getMessage("report.men"));
		localizationMap.put("population", MessageUtil.getMessage("report.population"));
		localizationMap.put("account", MessageUtil.getMessage("report.total"));
		localizationMap.put("accounTestsByAgeAndSex", MessageUtil.getMessage("report.accounTestsByAgeAndSex"));
		localizationMap.put("positive", MessageUtil.getMessage("report.positive"));
		localizationMap.put("accountHivTypeTest", MessageUtil.getMessage("report.accountHivTypeTest"));
		localizationMap.put("negative", MessageUtil.getMessage("report.negative"));
		localizationMap.put("undetermined", MessageUtil.getMessage("report.undetermined"));
		localizationMap.put("percentage", MessageUtil.getMessage("report.percentage"));
		localizationMap.put("waiting", MessageUtil.getMessage("report.percentage"));

		localizationMap.put("reception", MessageUtil.getMessage("report.reception"));
		/* activity report */
		localizationMap.put("activity", MessageUtil.getMessage("report.activity"));
		localizationMap.put("from", MessageUtil.getMessage("report.from"));
		localizationMap.put("to", MessageUtil.getMessage("report.to"));
		localizationMap.put("printed", MessageUtil.getMessage("report.printed"));
		localizationMap.put("techId", MessageUtil.getMessage("report.techId"));
		localizationMap.put("collection", MessageUtil.getMessage("report.collection"));
		localizationMap.put("patientNameCode", MessageUtil.getMessage("report.patientNameCode"));
		localizationMap.put("status", MessageUtil.getMessage("report.status"));
		localizationMap.put("testName", MessageUtil.getMessage("report.testName"));
		localizationMap.put("dateFormat", MessageUtil.getMessage("report.dateFormat"));
		localizationMap.put("dateReviewedReceived", MessageUtil.getMessage("report.dateReviewedReceived"));
		/* Non Conformity by group/date */
		localizationMap.put("supervisorSign", MessageUtil.getMessage("report.supervisorSign"));
		localizationMap.put("for", MessageUtil.getMessage("report.for"));
		localizationMap.put("comments", MessageUtil.getMessage("report.comments"));
		localizationMap.put("biologist", MessageUtil.getMessage("report.biologist"));
		localizationMap.put("typeOfSample", MessageUtil.getMessage("report.typeOfSample"));
		localizationMap.put("reasonForRejection", MessageUtil.getMessage("report.reasonForRejection"));
		localizationMap.put("section", MessageUtil.getMessage("report.section"));
		localizationMap.put("service", MessageUtil.getMessage("report.service"));
		localizationMap.put("study", MessageUtil.getMessage("report.study"));
		localizationMap.put("siteSubjectNo", MessageUtil.getMessage("report.siteSubjectNo"));
		localizationMap.put("subjectNo", MessageUtil.getMessage("report.subjectNo"));
		/* Validation Report */
		localizationMap.put("validationReport", MessageUtil.getMessage("report.validationReport"));
		localizationMap.put("testSection", MessageUtil.getMessage("report.testSection"));
		/* No Report report */
		localizationMap.put("noReportMessage", MessageUtil.getMessage("report.noReportMessage"));

		return localizationMap;
	}

	@Override
	public byte[] runReport() throws Exception {
		return JasperRunManager.runReportToPdf(fullReportFilename, getReportParameters(), getReportDataSource());
	}

	public abstract JRDataSource getReportDataSource() throws IllegalStateException;

	@Override
	public HashMap<String, ?> getReportParameters() throws IllegalStateException {
		if (!initialized) {
			throw new IllegalStateException("initializeReport not called first");
		}
		return reportParameters != null ? reportParameters : new HashMap<>();
	}

	/**
	 * Utility routine for a sequence done in many places. Adds a message to the
	 * errorMsgs
	 *
	 * @param messageId - name of resource
	 */
	protected void add1LineErrorMessage(String messageId) {
		errorFound = true;
		ErrorMessages msgs = new ErrorMessages();
		msgs.setMsgLine1(MessageUtil.getMessage(messageId));
		errorMsgs.add(msgs);
	}

	/**
	 * Utility routine for a sequence done in many places. Adds a message to the
	 * errorMsgs
	 *
	 * @param messageId - name of resource
	 */
	protected void add1LineErrorMessage(String messageId, String more) {
		errorFound = true;
		ErrorMessages msgs = new ErrorMessages();
		msgs.setMsgLine1(MessageUtil.getMessage(messageId) + more);
		errorMsgs.add(msgs);
	}

	/**
	 * Checks a given date to make sure it is ok, filling in with a default if not
	 * found, logging a message, if there is a problem.
	 *
	 * @param checkDateStr   - date to check
	 * @param defaultDateStr - will use this date if the 1st one is null or blank.
	 * @param badDateMessage - message to report if the date is bad (blank or not
	 *                       valid form).
	 * @return Date
	 */
	protected Date validateDate(String checkDateStr, String defaultDateStr, String badDateMessage) {
		checkDateStr = isBlankOrNull(checkDateStr) ? defaultDateStr : checkDateStr;
		Date checkDate;
		if (isBlankOrNull(checkDateStr)) {
			add1LineErrorMessage(badDateMessage);
			return null;
		}

		try {
			checkDate = DateUtil.convertStringDateToSqlDate(checkDateStr);
		} catch (LIMSRuntimeException re) {
			add1LineErrorMessage("report.error.message.date.format", " " + checkDateStr);
			return null;
		}
		return checkDate;
	}

	/**
	 * @return true, if location is not blank or "0" is is found in the DB; false
	 *         otherwise
	 */
	protected Organization getValidOrganization(String locationStr) {
		if (isBlankOrNull(locationStr) || "0".equals(locationStr)) {
			add1LineErrorMessage("report.error.message.location.missing");
			return null;
		}
		Organization org = organizationService.getOrganizationById(locationStr);
		if (org == null) {
			add1LineErrorMessage("report.error.message.location.missing");
			return null;
		}
		return org;
	}

	public String getReportFileName() {
		return errorFound ? ERROR_REPORT : reportFileName();
	}

	public class DateRange {
		private String lowDateStr;
		private String highDateStr;
		private Date lowDate;
		private Date highDate;

		public Date getLowDate() {
			return lowDate;
		}

		public Date getHighDate() {
			return highDate;
		}

		/**
		 * If you need to compare a Date which started as a date string to a bunch of
		 * timestamps, you should move it from 00:00 at the beginning of the day to the
		 * end of the day at 23:59:59.999.
		 *
		 * @return the high date with time set to the end of the day.
		 */
		public Date getHighDateAtEndOfDay() {
			// not perfect in areas with Daylight Savings Time. Will over shoot
			// on the spring forward day and undershoot on the fall back day.
			return new Date(highDate.getTime() + 24 * 60 * 60 * 1000);
		}

		public DateRange(String lowDateStr, String highDateStr) {
			this.lowDateStr = lowDateStr;
			this.highDateStr = highDateStr;
		}

		/**
		 * <ol>
		 * <li>High date picks up low date if it ain't filled in,
		 * <li>they can't both be empty
		 * <li>they have to be well formed.
		 *
		 * @return true if valid, false otherwise
		 */
		public boolean validateHighLowDate(String missingDateMessage) {
			lowDate = validateDate(lowDateStr, null, missingDateMessage);
			highDate = validateDate(highDateStr, lowDateStr, missingDateMessage);
			if (lowDate == null || highDate == null) {
				return false;
			}
			if (highDate.getTime() < lowDate.getTime()) {
				Date tmpDate = highDate;
				highDate = lowDate;
				lowDate = tmpDate;

				String tmpString = highDateStr;
				highDateStr = lowDateStr;
				lowDateStr = tmpString;
			}
			return true;
		}

		@Override
		public String toString() {
			String range = lowDateStr;
			try {
				if (!GenericValidator.isBlankOrNull(highDateStr)) {
					range += "  -  " + highDateStr;
				}
			} catch (Exception ignored) {
			}
			return range;
		}

		public String getLowDateStr() {
			return lowDateStr;
		}

		public String getHighDateStr() {
			if (isBlankOrNull(highDateStr) && highDate != null) {
				highDateStr = DateUtil.convertSqlDateToStringDate(highDate);
			}
			return highDateStr;
		}
	}

	@Override
	public void setReportPath(String path) {
		fullReportFilename = path + getReportFileName() + ".jasper";
	}

	@Override
	public List<String> getReportedOrders() {
		return new ArrayList<>();
	}

	protected abstract String reportFileName();
}
