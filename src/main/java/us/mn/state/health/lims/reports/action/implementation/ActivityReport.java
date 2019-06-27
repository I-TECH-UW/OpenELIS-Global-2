/*
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
 */

package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl.ObservationType;
import spring.service.patient.PatientService;
import spring.service.result.ResultService;
import spring.service.sample.SampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ActivityReportBean;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;

public abstract class ActivityReport extends Report implements IReportCreator {
	private int PREFIX_LENGTH = AccessionNumberUtil.getAccessionNumberValidator().getInvarientLength();
	protected List<ActivityReportBean> testsResults;
	protected String reportPath = "";
	protected DateRange dateRange;

	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(testsResults);
	}

	@Override
	protected void createReportParameters() {
		reportParameters.put("activityLabel", getActivityLabel());
		reportParameters.put("accessionPrefix", AccessionNumberUtil.getAccessionNumberValidator().getPrefix());
		reportParameters.put("labNumberTitle", MessageUtil.getContextualMessage("quick.entry.accession.number"));
		reportParameters.put("labName", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
		reportParameters.put("SUBREPORT_DIR", reportPath);
		reportParameters.put("startDate", dateRange.getLowDateStr());
		reportParameters.put("endDate", dateRange.getHighDateStr());
		reportParameters.put("isReportByTest", isReportByTest());

	}

	protected boolean isReportByTest() {
		return false;
	}

	protected abstract String getActivityLabel();

	protected abstract void buildReportContent(ReportSpecificationList testSelection);

	@Override
	public void initializeReport(BaseForm form) {
		initialized = true;
		ReportSpecificationList selection = (ReportSpecificationList) form.get("selectList");
		String lowDateStr = form.getString("lowerDateRange");
		String highDateStr = form.getString("upperDateRange");
		dateRange = new DateRange(lowDateStr, highDateStr);

		super.createReportParameters();
		errorFound = !validateSubmitParameters(selection);
		if (errorFound) {
			return;
		}

		buildReportContent(selection);
		if (testsResults.size() == 0) {
			add1LineErrorMessage("report.error.message.noPrintableItems");
		}
	}

	private boolean validateSubmitParameters(ReportSpecificationList selectList) {

		return (dateRange.validateHighLowDate("report.error.message.date.received.missing")
				&& validateSelection(selectList));
	}

	private boolean validateSelection(ReportSpecificationList selectList) {
		boolean complete = !GenericValidator.isBlankOrNull(selectList.getSelection())
				&& !"0".equals(selectList.getSelection());

		if (!complete) {
			add1LineErrorMessage("report.error.message.activity.missing");
		}

		return complete;
	}

	protected ActivityReportBean createActivityReportBean(Result result, boolean useTestName) {
		ActivityReportBean item = new ActivityReportBean();

		ResultService resultResultService = SpringContext.getBean(ResultService.class);
		resultResultService.setResult(result);
		SampleService sampleSampleService = SpringContext.getBean(SampleService.class);
		sampleSampleService.setSample(result.getAnalysis().getSampleItem().getSample());
		PatientService patientSampleService = SpringContext.getBean(PatientService.class);
		patientSampleService.setPatientBySample(sampleSampleService.getSample());
		item.setResultValue(resultResultService.getResultValue("\n", true, true));
		item.setTechnician(resultResultService.getSignature());
		item.setAccessionNumber(sampleSampleService.getAccessionNumber().substring(PREFIX_LENGTH));
		item.setReceivedDate(sampleSampleService.getReceivedDateWithTwoYearDisplay());
		item.setResultDate(DateUtil.convertTimestampToTwoYearStringDate(result.getLastupdated()));
		item.setCollectionDate(
				DateUtil.convertTimestampToTwoYearStringDate(result.getAnalysis().getSampleItem().getCollectionDate()));

		List<String> values = new ArrayList<>();
		values.add(patientSampleService.getLastName() == null ? "" : patientSampleService.getLastName().toUpperCase());
		values.add(patientSampleService.getNationalId());

		String referringPatientId = ObservationHistoryServiceImpl.getInstance()
				.getValueForSample(ObservationType.REFERRERS_PATIENT_ID, sampleSampleService.getSample().getId());
		values.add(referringPatientId == null ? "" : referringPatientId);

		String name = StringUtil.buildDelimitedStringFromList(values, " / ", true);

		if (useTestName) {
			item.setPatientOrTestName(resultResultService.getReportingTestName());
			item.setNonPrintingPatient(name);
		} else {
			item.setPatientOrTestName(name);
		}

		return item;
	}

	@Override
	protected String reportFileName() {
		return "ActivityReport";
	}

	protected ActivityReportBean createIdentityActivityBean(ActivityReportBean item, boolean blankCollectionDate) {
		ActivityReportBean filler = new ActivityReportBean();

		filler.setAccessionNumber(item.getAccessionNumber());
		filler.setReceivedDate(item.getReceivedDate());
		filler.setCollectionDate(blankCollectionDate ? " " : item.getCollectionDate());
		filler.setPatientOrTestName(item.getNonPrintingPatient());

		return filler;
	}
}
