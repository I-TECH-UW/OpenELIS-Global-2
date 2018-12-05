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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ARVReportData;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

public abstract class PatientARVReport extends RetroCIPatientReport{
	private List<ARVReportData> reportItems;
	private String invalidValue = StringUtil.getMessageForKey("report.test.status.inProgress");
	protected void initializeReportItems(){
		reportItems = new ArrayList<ARVReportData>();
	}

	protected void setPatientInfo(ARVReportData data){

		SampleOrganizationDAO orgDAO = new SampleOrganizationDAOImpl();

		String subjectNumber = reportPatient.getNationalId();
		if(GenericValidator.isBlankOrNull(subjectNumber)){
			subjectNumber = reportPatient.getExternalId();
		}

		data.setSubjectNumber(subjectNumber);
		data.setBirth_date(reportPatient.getBirthDateForDisplay());
		data.setAge(DateUtil.getCurrentAgeForDate(reportPatient.getBirthDate(), reportSample.getCollectionDate()));
		data.setGender(reportPatient.getGender());
		data.setCollectiondate(reportSample.getCollectionDateForDisplay() + " " + reportSample.getCollectionTimeForDisplay());
		data.setReceptiondate(DateUtil.convertTimestampToStringDate(reportSample.getReceivedTimestamp()));

		SampleOrganization sampleOrg = new SampleOrganization();
		sampleOrg.setSample(reportSample);
		orgDAO.getDataBySample(sampleOrg);
		data.setOrgname(sampleOrg.getId() == null ? "" : sampleOrg.getOrganization().getOrganizationName());

		data.setDoctor(getObservationValues(OBSERVATION_DOCTOR_ID));
		data.setLabNo(reportSample.getAccessionNumber());
		
		data.getSampleQaEventItems(reportSample);

	}

	public JRDataSource getReportDataSource() throws IllegalStateException{
		if(!initialized){
			throw new IllegalStateException("initializeReport not called first");
		}

		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}

	protected void createReportItems(){
		ARVReportData data = new ARVReportData();
		
		setPatientInfo(data);
		setTestInfo(data);
		reportItems.add(data);

	}
	protected void setTestInfo(ARVReportData data){
		boolean atLeastOneAnalysisNotValidated = false;
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(reportSample.getId());
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Timestamp lastReport = new ReportTrackingService().getTimeOfLastNamedReport(reportSample, ReportTrackingService.ReportType.PATIENT, requestedReport);
		Boolean mayBeDuplicate = lastReport != null;
		ResultDAO resultDAO = new ResultDAOImpl();
		Date maxCompleationDate = null;
		long maxCompleationTime = 0L;

		for(Analysis analysis : analysisList){
			if (analysis.getCompletedDate() != null) {
				if (analysis.getCompletedDate().getTime() > maxCompleationTime) {
					maxCompleationDate = analysis.getCompletedDate();
					maxCompleationTime = maxCompleationDate.getTime();
				}
				
			}
					
			if(!analysis.getStatusId().equals(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled))){
				String testName = TestService.getUserLocalizedTestName( analysis.getTest() );
				List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
				String resultValue = null;

				boolean valid = ANALYSIS_FINALIZED_STATUS_ID.equals(analysis.getStatusId());
				if(!valid){
					atLeastOneAnalysisNotValidated = true;
				}
				// there may be more than one result for an analysis if one of
				// them
				// is a conclusion
				if(resultList.size() > 1){
					for(Result result : resultList){
						if(result.getAnalyte() != null && result.getAnalyte().getId().equals(CONCLUSION_ID)){
							Dictionary dictionary = new Dictionary();
							dictionary.setId(result.getValue());
							dictionaryDAO.getData(dictionary);
							data.setVih(valid ? dictionary.getDictEntry() : invalidValue);
							data.setShowSerologie(Boolean.TRUE);
						}else if(result.getAnalyte() != null && result.getAnalyte().getId().equals(CD4_CNT_CONCLUSION)){
							data.setCd4(valid ? result.getValue() : invalidValue);
						}else{
							resultValue = result.getValue();
						}
					}
				}

				if(resultList.size() > 0){
					if(resultValue == null){
						resultValue = resultList.get(resultList.size() - 1).getValue();
					}
				}

				if(resultValue != null || !valid){
					assignResultsToAVRReportData(data, testName, valid ? resultValue : invalidValue);
				}
			}

			if( mayBeDuplicate &&
					StatusService.getInstance().matches( analysis.getStatusId(), AnalysisStatus.Finalized) &&
					lastReport.before(analysis.getLastupdated())){
				mayBeDuplicate = false;
			}

		}
	
		if (maxCompleationDate != null) {
			data.setCompleationdate(DateUtil.convertSqlDateToStringDate(maxCompleationDate));
		}
		data.setDuplicateReport(mayBeDuplicate);
		data.setStatus(atLeastOneAnalysisNotValidated ? StringUtil.getMessageForKey("report.status.partial") : StringUtil
				.getMessageForKey("report.status.complete"));
	}

	private void assignResultsToAVRReportData(ARVReportData data, String testName, String resultValue){

		if(testName.equals("Glycémie")){
			data.setGlyc(resultValue);
		}else if(testName.equals("Créatininémie")){
			data.setCreatininemie(resultValue);
		}else if(testName.equals("Transaminases ALTL")){
			data.setSgpt(resultValue);
		}else if(testName.equals("Transaminases ASTL")){
			data.setSgot(resultValue);
		}else if(testName.equals("GB")){
			data.setGb(resultValue);
		}else if(testName.equals("GR")){
			data.setGr(resultValue);
		}else if(testName.equals("Hb")){
			data.setHb(resultValue);
		}else if(testName.equals("HCT")){
			data.setHct(resultValue);
		}else if(testName.equals("VGM")){
			data.setVgm(resultValue);
		}else if(testName.equals("PLQ")){
			data.setPlq(resultValue);
		}else if(testName.equals("Neut %")){
			data.setNper(resultValue);
		}else if(testName.equals("Lymph %")){
			data.setLper(resultValue);
		}else if(testName.equals("Mono %")){
			data.setMper(resultValue);
		}else if(testName.equals("Eo %")){
			data.setEoper(resultValue);
		}else if(testName.equals("Baso %")){
			data.setBper(resultValue);
		}else if(testName.equals("CD4 absolute count")){
			data.setCd4(resultValue);
		}else if(testName.equals("CD4 percentage count")){
			data.setCd4per(resultValue);
		}else if(testName.equals("TCMH")){
			data.setTcmh(resultValue);
		}else if(testName.equals("CCMH")){
			data.setCcmh(resultValue);
		}else if(testName.equals("DNA PCR")){
			data.setPcr(resultValue);
		}else if(testName.equals("Viral Load")){
			data.setShowVirologie(Boolean.TRUE);
			// Results entered via analyzer have log value, results entered
			// manually may not
			String baseValue = resultValue;
			if(!GenericValidator.isBlankOrNull(resultValue) && resultValue.contains("(")){
				String[] splitValue = resultValue.split("\\(");
				data.setAmpli2(splitValue[0]);
				baseValue = splitValue[0];
			}else{
				data.setAmpli2(resultValue);
			}
			if(!GenericValidator.isBlankOrNull(baseValue) && !"0".equals(baseValue)){
				try{
					double viralLoad = Double.parseDouble(baseValue);
					data.setAmpli2lo(String.format("%.3g%n", Math.log10(viralLoad)));
				}catch(NumberFormatException nfe){
					data.setAmpli2lo("");
				}
			}

		}else if(testName.equals("Murex") || testName.equals("Intgral")){ //Serology must have one of these but not necessarily both
			data.setShowSerologie(Boolean.TRUE);
			if(GenericValidator.isBlankOrNull(data.getVih())){
				data.setVih(invalidValue);
			}
		}
	}
}
