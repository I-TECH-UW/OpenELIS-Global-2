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
package us.mn.state.health.lims.reports.action.implementation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import spring.mine.common.form.BaseForm;
import spring.service.analysis.AnalysisService;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.note.NoteServiceImpl;
import spring.service.organization.OrganizationService;
import spring.service.patient.PatientServiceImpl;
import spring.service.requester.RequesterTypeService;
import spring.service.requester.SampleRequesterService;
import spring.service.result.ResultService;
import spring.service.result.ResultServiceImpl;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleitem.SampleItemService;
import spring.service.test.TestServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.TestSegmentedExportBean;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

public class HaitiLNSPExportReport extends CSVExportReport {

	private DateRange dateRange;
	private String lowDateStr;
	private String highDateStr;
	private final SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	private final SampleItemService sampleItemService = SpringContext.getBean(SampleItemService.class);
	private final AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
	private final ResultService resultService = SpringContext.getBean(ResultService.class);
	private final SampleService sampleService = SpringContext.getBean(SampleService.class);
	private final SampleRequesterService sampleRequesterService = SpringContext.getBean(SampleRequesterService.class);
	private final OrganizationService organizationService = SpringContext.getBean(OrganizationService.class);
	private final RequesterTypeService requesterTypeService = SpringContext.getBean(RequesterTypeService.class);

	private final long ORGANIZTION_REFERRAL_TYPE_ID;
	protected List<TestSegmentedExportBean> testExportList;

	public HaitiLNSPExportReport() {
		String orgTypeId = requesterTypeService.getRequesterTypeByName("organization").getId();
		ORGANIZTION_REFERRAL_TYPE_ID = orgTypeId == null ? -1L : Long.parseLong(orgTypeId);
	}

	@Override
	public void initializeReport(BaseForm form) {
		super.initializeReport();

		errorFound = false;

		lowDateStr = form.getString("lowerDateRange");
		highDateStr = form.getString("upperDateRange");
		dateRange = new DateRange(lowDateStr, highDateStr);

		createReportParameters();

		errorFound = !validateSubmitParameters();
		if (errorFound) {
			return;
		}

		createReportItems();
	}

	private void createReportItems() {
		testExportList = new ArrayList<>();
		List<Sample> orderList = sampleService.getSamplesReceivedInDateRange(lowDateStr, highDateStr);

		for (Sample order : orderList) {
			getResultsForOrder(order);
		}
	}

	private void getResultsForOrder(Sample order) {
		Patient patient = sampleHumanService.getPatientForSample(order);
		List<SampleRequester> requesterList = sampleRequesterService.getRequestersForSampleId(order.getId());
		Organization requesterOrganization = null;

		for (SampleRequester requester : requesterList) {
			if (requester.getRequesterTypeId() == ORGANIZTION_REFERRAL_TYPE_ID) {
				requesterOrganization = organizationService
						.getOrganizationById(String.valueOf(requester.getRequesterId()));
				break;
			}
		}

		PatientServiceImpl patientService = new PatientServiceImpl(patient);

		List<SampleItem> sampleItemList = sampleItemService.getSampleItemsBySampleId(order.getId());

		for (SampleItem sampleItem : sampleItemList) {
			getResultsForSampleItem(requesterOrganization, patientService, sampleItem, order);
		}
	}

	private void getResultsForSampleItem(Organization requesterOrganization, PatientServiceImpl patientService,
			SampleItem sampleItem, Sample order) {
		List<Analysis> analysisList = analysisService.getAnalysesBySampleItem(sampleItem);

		for (Analysis analysis : analysisList) {
			getResultForAnalysis(requesterOrganization, patientService, order, sampleItem, analysis);
		}

	}

	private void getResultForAnalysis(Organization requesterOrganization, PatientServiceImpl patientService,
			Sample order, SampleItem sampleItem, Analysis analysis) {
		TestSegmentedExportBean ts = new TestSegmentedExportBean();

		ts.setAccessionNumber(order.getAccessionNumber());
		ts.setReceptionDate(order.getReceivedDateForDisplay());
		ts.setReceptionTime(DateUtil.convertTimestampToStringConfiguredHourTime(order.getReceivedTimestamp()));
		ts.setCollectionDate(DateUtil.convertTimestampToStringDate(sampleItem.getCollectionDate()));
		ts.setCollectionTime(DateUtil.convertTimestampToStringConfiguredHourTime(sampleItem.getCollectionDate()));
		ts.setAge(createReadableAge(patientService.getDOB()));
		ts.setDOB(patientService.getEnteredDOB());
		ts.setFirstName(patientService.getFirstName());
		ts.setLastName(patientService.getLastName());
		ts.setGender(patientService.getGender());
		ts.setNationalId(patientService.getNationalId());
		ts.setStatus(StatusService.getInstance()
				.getStatusName(StatusService.getInstance().getAnalysisStatusForID(analysis.getStatusId())));
		ts.setSampleType(sampleItem.getTypeOfSample().getLocalizedName());
		ts.setTestBench(analysis.getTestSection() == null ? "" : analysis.getTestSection().getTestSectionName());
		ts.setTestName(TestServiceImpl.getUserLocalizedTestName(analysis.getTest()));
		ts.setDepartment(
				StringUtil.blankIfNull(patientService.getAddressComponents().get(PatientServiceImpl.ADDRESS_DEPT)));
		String notes = new NoteServiceImpl(analysis).getNotesAsString(false, false, "|", false);
		if (notes != null) {
			ts.setNotes(notes);
		}

		if (requesterOrganization != null) {
			ts.setSiteCode(requesterOrganization.getShortName());
			ts.setReferringSiteName(requesterOrganization.getOrganizationName());
		}

		if (StatusService.getInstance().getStatusID(AnalysisStatus.Finalized).equals(analysis.getStatusId())) {
			ts.setResultDate(DateUtil.convertSqlDateToStringDate(analysis.getCompletedDate()));

			List<Result> resultList = resultService.getResultsByAnalysis(analysis);
			if (!resultList.isEmpty()) {
				setAppropriateResults(resultList, analysis, ts);
			}
		}
		testExportList.add(ts);
	}

	@Override
	protected String reportFileName() {
		return "haitiLNSPExport";
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.Report#getContentType()
	 */
	@Override
	public String getContentType() {
		if (errorFound) {
			return super.getContentType();
		} else {
			return "application/pdf; charset=UTF-8";
		}
	}

	@Override
	public byte[] runReport() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(TestSegmentedExportBean.getHeader());
		builder.append("\n");

		for (TestSegmentedExportBean testLine : testExportList) {
			builder.append(testLine.getAsCSVString());
			builder.append("\n");
		}

		return builder.toString().getBytes();
	}

	@Override
	public String getResponseHeaderName() {
		return "Content-Disposition";
	}

	@Override
	public String getResponseHeaderContent() {
		return "attachment;filename=" + getReportFileName() + ".csv";
	}

	/**
	 * check everything
	 */
	private boolean validateSubmitParameters() {
		return dateRange.validateHighLowDate("report.error.message.date.received.missing");
	}

	private String createReadableAge(Timestamp dob) {
		if (dob == null) {
			return "";
		}

		Date dobDate = DateUtil.convertTimestampToSqlDate(dob);
		int months = DateUtil.getAgeInMonths(dobDate, DateUtil.getNowAsSqlDate());
		if (months > 35) {
			return (months / 12) + " Ans";
		} else if (months > 0) {
			return months + " M";
		} else {
			int days = DateUtil.getAgeInDays(dobDate, DateUtil.getNowAsSqlDate());
			return days + " J";
		}

	}

	private void setAppropriateResults(List<Result> resultList, Analysis analysis, TestSegmentedExportBean data) {
		Result result = resultList.get(0);
		ResultServiceImpl resultService = new ResultServiceImpl(result);
		String reportResult = resultService.getResultValue(true);
		Result quantifiableResult = new AnalysisServiceImpl(analysis).getQuantifiedResult();
		if (quantifiableResult != null) {
			reportResult += ":" + quantifiableResult.getValue();
		}

		data.setResult(reportResult.replace(",", ";"));

	}

}
