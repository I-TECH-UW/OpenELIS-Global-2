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

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.TestSegmentedExportBean;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.RequesterTypeDAOImpl;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

public class HaitiLNSPExportReport extends CSVExportReport{

	private DateRange dateRange;
	private String lowDateStr;
	private String highDateStr;
	private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static final SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static final ResultDAO resultDAO = new ResultDAOImpl();
	private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
	private static final OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	private static final long ORGANIZTION_REFERRAL_TYPE_ID;
	protected List<TestSegmentedExportBean> testExportList;

	static{
		String orgTypeId = new RequesterTypeDAOImpl().getRequesterTypeByName("organization").getId();
		ORGANIZTION_REFERRAL_TYPE_ID = orgTypeId == null ? -1L : Long.parseLong(orgTypeId);
	}

	@Override
	public void initializeReport(BaseActionForm dynaForm){
		super.initializeReport();

		errorFound = false;

		lowDateStr = dynaForm.getString("lowerDateRange");
		highDateStr = dynaForm.getString("upperDateRange");
		dateRange = new DateRange(lowDateStr, highDateStr);

		createReportParameters();

		errorFound = !validateSubmitParameters();
		if(errorFound){
			return;
		}

		createReportItems();
	}

	private void createReportItems(){
		testExportList = new ArrayList<TestSegmentedExportBean>();
		List<Sample> orderList = sampleDAO.getSamplesReceivedInDateRange(lowDateStr, highDateStr);

		for(Sample order : orderList){
			getResultsForOrder(order);
		}
	}

	private void getResultsForOrder(Sample order){
		Patient patient = sampleHumanDAO.getPatientForSample(order);
		List<SampleRequester> requesterList = sampleRequesterDAO.getRequestersForSampleId(order.getId());
		Organization requesterOrganization = null;
		
		for(SampleRequester requester : requesterList){
			if(requester.getRequesterTypeId() == ORGANIZTION_REFERRAL_TYPE_ID){
				requesterOrganization = organizationDAO.getOrganizationById(String.valueOf(requester.getRequesterId()));
				break;
			}
		}

		PatientService patientService = new PatientService(patient);

		List<SampleItem> sampleItemList = sampleItemDAO.getSampleItemsBySampleId(order.getId());

		for(SampleItem sampleItem : sampleItemList){
			getResultsForSampleItem(requesterOrganization, patientService, sampleItem, order);
		}
	}

	private void getResultsForSampleItem(Organization requesterOrganization, PatientService patientService, SampleItem sampleItem, Sample order){
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleItem(sampleItem);

		for(Analysis analysis : analysisList){
			getResultForAnalysis(requesterOrganization, patientService, order, sampleItem, analysis);
		}

	}

	private void getResultForAnalysis(Organization requesterOrganization, PatientService patientService, Sample order, SampleItem sampleItem,
			Analysis analysis){
		TestSegmentedExportBean ts = new TestSegmentedExportBean();

		ts.setAccessionNumber(order.getAccessionNumber());
		ts.setReceptionDate(order.getReceivedDateForDisplay());
		ts.setReceptionTime(DateUtil.convertTimestampToStringConfiguredHourTime( order.getReceivedTimestamp() ));
		ts.setCollectionDate(DateUtil.convertTimestampToStringDate( sampleItem.getCollectionDate() ));		
		ts.setCollectionTime(DateUtil.convertTimestampToStringConfiguredHourTime( sampleItem.getCollectionDate() ));
		ts.setAge(createReadableAge(patientService.getDOB()));
		ts.setDOB(patientService.getEnteredDOB());
		ts.setFirstName(patientService.getFirstName());
		ts.setLastName(patientService.getLastName());
		ts.setGender(patientService.getGender());
		ts.setNationalId(patientService.getNationalId());
		ts.setStatus(StatusService.getInstance().getStatusName(StatusService.getInstance().getAnalysisStatusForID(analysis.getStatusId())));
		ts.setSampleType(sampleItem.getTypeOfSample().getLocalizedName());
		ts.setTestBench(analysis.getTestSection() == null ? "" : analysis.getTestSection().getTestSectionName());
        ts.setTestName( TestService.getUserLocalizedTestName( analysis.getTest() ) );
        ts.setDepartment( StringUtil.blankIfNull(patientService.getAddressComponents().get(PatientService.ADDRESS_DEPT) ) );
        String notes = new NoteService( analysis ).getNotesAsString( false, false, "|", false );
        if( notes != null){
            ts.setNotes(notes);
        }


		if(requesterOrganization != null){
			ts.setSiteCode(requesterOrganization.getShortName());
			ts.setReferringSiteName(requesterOrganization.getOrganizationName());
		}
		
		if(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized).equals(analysis.getStatusId())){
			ts.setResultDate(DateUtil.convertSqlDateToStringDate(analysis.getCompletedDate()));

			List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
			if(!resultList.isEmpty()){
				setAppropriateResults(resultList, analysis, ts);
			}
		}
		testExportList.add(ts);
	}

	@Override
	protected String reportFileName(){
		return "haitiLNSPExport";
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.Report#getContentType()
	 */
	@Override
	public String getContentType(){
		if(errorFound){
			return super.getContentType();
		}else{
			return "application/pdf; charset=UTF-8";
		}
	}

	@Override
	public byte[] runReport() throws Exception{
		StringBuilder builder = new StringBuilder();
		builder.append(TestSegmentedExportBean.getHeader());
		builder.append("\n");

		for(TestSegmentedExportBean testLine : testExportList){
			builder.append(testLine.getAsCSVString());
			builder.append("\n");
		}

		return builder.toString().getBytes();
	}

	@Override
	public String getResponseHeaderName(){
		return "Content-Disposition";
	}

	@Override
	public String getResponseHeaderContent(){
		return "attachment;filename=" + getReportFileName() + ".csv";
	}

	/**
	 * check everything
	 */
	private boolean validateSubmitParameters(){
		return dateRange.validateHighLowDate("report.error.message.date.received.missing");
	}

	private String createReadableAge(Timestamp dob){
		if(dob == null){
			return "";
		}

		Date dobDate = DateUtil.convertTimestampToSqlDate(dob);
		int months = DateUtil.getAgeInMonths(dobDate, DateUtil.getNowAsSqlDate());
		if(months > 35){
			return (months / 12) + " Ans";
		}else if(months > 0){
			return months + " M";
		}else{
			int days = DateUtil.getAgeInDays(dobDate, DateUtil.getNowAsSqlDate());
			return days + " J";
		}

	}

	private void setAppropriateResults(List<Result> resultList, Analysis analysis, TestSegmentedExportBean data){
		Result result = resultList.get(0);
        ResultService resultService = new ResultService( result );
		String reportResult = resultService.getResultValue( true );
        Result quantifiableResult = new AnalysisService(analysis).getQuantifiedResult();
        if( quantifiableResult != null){
            reportResult += ":" + quantifiableResult.getValue();
        }

		data.setResult(reportResult.replace(",", ";"));

	}

}
