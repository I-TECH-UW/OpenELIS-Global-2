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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.IndeterminateReportData;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

public abstract class PatientIndeterminateReport extends RetroCIPatientReport {

    protected List<IndeterminateReportData> reportItems;


	protected String getReportNameForReport() {
		return StringUtil.getMessageForKey("reports.label.patient.indeterminate");
	}

	public JRDataSource getReportDataSource() throws IllegalStateException {
		if (!initialized) {
			throw new IllegalStateException("initializeReport not called first");
		}

		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}

	protected void createReportItems() {
		IndeterminateReportData data = new IndeterminateReportData();

		setPatientInfo(data);
		setTestInfo(data);

		reportItems.add(data);

	}

	protected void setPatientInfo(IndeterminateReportData data) {

		SampleOrganizationDAO orgDAO = new SampleOrganizationDAOImpl();

		String subjectNumber = reportPatient.getNationalId();
		if (GenericValidator.isBlankOrNull(subjectNumber)) {
			subjectNumber = reportPatient.getExternalId();
		}
		data.setSubjectNumber(subjectNumber);
		data.setBirth_date(reportPatient.getBirthDateForDisplay());
		data.setAge(DateUtil.getCurrentAgeForDate(reportPatient.getBirthDate(), reportSample.getCollectionDate()));
		data.setGender(reportPatient.getGender());
		data.setCollectiondate(reportSample.getCollectionDateForDisplay() + " " + reportSample.getCollectionTimeForDisplay());
		data.setReceivedDate(reportSample.getReceivedDateForDisplay() + " " + reportSample.getReceivedTimeForDisplay( ));

		SampleOrganization sampleOrg = new SampleOrganization();
		sampleOrg.setSample(reportSample);
		orgDAO.getDataBySample(sampleOrg);
		data.setOrgname(sampleOrg.getId() == null ? "" : sampleOrg.getOrganization().getOrganizationName());

		data.setDoctor(getObservationValues(OBSERVATION_DOCTOR_ID));
		data.setLabNo(reportSample.getAccessionNumber());
	}

	protected void setTestInfo(IndeterminateReportData data) {
	    boolean atLeastOneAnalysisNotValidated = false;
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
        List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(reportSample.getId());
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		String invalidValue = StringUtil.getMessageForKey("report.test.status.inProgress");
		ResultDAO resultDAO = new ResultDAOImpl();

		for (Analysis analysis : analysisList) {
			String testName = TestService.getUserLocalizedTestName( analysis.getTest() );

			List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
			String resultValue = null;

			boolean valid = ANALYSIS_FINALIZED_STATUS_ID.equals(analysis.getStatusId());

			if (!valid) {
				atLeastOneAnalysisNotValidated = true;
				data.setFinalResult(invalidValue);
			}
			// there may be more than one result for an analysis if one of
			// them
			// is a conclusion
			if (resultList.size() > 1) {
				for (Result result : resultList) {

					Dictionary dictionary = new Dictionary();
					dictionary.setId(result.getValue());
					dictionaryDAO.getData(dictionary);

					if (result.getAnalyte() != null && result.getAnalyte().getId().equals(CONCLUSION_ID)) {
						data.setFinalResult(valid ? dictionary.getDictEntry() : invalidValue);
					} else {
						resultValue = valid ? dictionary.getDictEntry() : invalidValue;
					}
				}
			} else if ( valid && resultList.size() > 0) {
				Dictionary dictionary = new Dictionary();
				dictionary.setId(resultList.get(0).getValue());
				dictionaryDAO.getData(dictionary);
				resultValue = dictionary.getDictEntry();
			}

			setIndeterminateData(data, testName, valid ? resultValue : invalidValue);

		}

		data.setStatus(atLeastOneAnalysisNotValidated ? StringUtil.getMessageForKey("report.status.partial") : StringUtil
				.getMessageForKey("report.status.complete"));

	}

	private void setIndeterminateData(IndeterminateReportData data, String testName, String resultValue) {
		if (testName.equals("Integral")) {
			data.setIntegral(resultValue);
		} else if (testName.equals("Murex")) {
			data.setMurex(resultValue);
		} else if (testName.equals("Vironostika")) {
			data.setVironstika(resultValue);
		} else if (testName.equals("Genie II")) {
			data.setGenie_hiv1_hiv2(resultValue);
		} else if (testName.equals("Western Blot 1")) {
			data.setWb1(resultValue);
		} else if (testName.equals("Western Blot 2")) {
			data.setWb2(resultValue);
		} else if (testName.equals("P24 Ag")) {
			data.setP24(resultValue);
		} else if (testName.equals("DNA PCR")) {
			data.setPcr(resultValue);
		} else if (testName.equals("Genie II 10")) {
			data.setGenie10(resultValue);
		} else if (testName.equals("Genie II 100")) {
			data.setGenie100(resultValue);
		} else if (testName.equals("Bioline")) {
			data.setBioline(resultValue);
		}
	}

	@Override
	protected void initializeReportItems() {
		reportItems = new ArrayList<IndeterminateReportData>();
	}

	@Override
	protected String getProjectId() {
		return INDETERMINATE_STUDY_ID;
	}
}
