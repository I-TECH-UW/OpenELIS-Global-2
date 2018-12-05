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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.SectionPerformanceData;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@SuppressWarnings("unchecked")
public class IndicatorSectionPerformanceReport extends RetroCIReport implements  IReportCreator{

	private List<SectionPerformanceData> reportItems;
	private static String BIOCHEMISTRY_SECTION_ID;
	private static String SEROLOGY_SECTION_ID;
	private static String HEMATOLOGY_SECTION_ID;
	private static String IMMUNOLOGY_SECTION_ID;
	private static String VEROLOGY_SECTION_ID;
	private SampleDAO sampleDAO = new SampleDAOImpl();
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();

	static{
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<TestSection> testSectionList = testSectionDAO.getAllTestSections();

		for(TestSection section : testSectionList){
			String sectionName = section.getTestSectionName();

			if("Biochemistry".equals(sectionName)){
				BIOCHEMISTRY_SECTION_ID = section.getId();
			}else if("Serology".equals(sectionName)){
				SEROLOGY_SECTION_ID = section.getId();
			}else if("Hematology".equals(sectionName)){
				HEMATOLOGY_SECTION_ID = section.getId();
			}else if("Immunology".equals(sectionName)){
				IMMUNOLOGY_SECTION_ID = section.getId();
			}else if("Virology".equals(sectionName)){
				VEROLOGY_SECTION_ID = section.getId();
			}
		}
	}

	@Override
	protected String reportFileName(){
		return "RetroCI_backlog";
	}

	public JRDataSource getReportDataSource() throws IllegalStateException{
		if(!initialized){
			throw new IllegalStateException("initializeReport not called first");
		}

		return new JRBeanCollectionDataSource(reportItems);
	}

	public HashMap<String, ?> getReportParameters() throws IllegalStateException{
		return new HashMap<String, String>();
	}

	public void initializeReport(BaseActionForm dynaForm){
		super.initializeReport();

		try{
			createReportItems();
		}catch(LIMSInvalidConfigurationException e){
			e.printStackTrace();
		}
	}

	private void createReportItems() throws LIMSInvalidConfigurationException{
		reportItems = new ArrayList<SectionPerformanceData>();

		addTestItems();
		addValidationItems();
		addIntakeItems();

		sortReportItems();
	}

	private void sortReportItems(){
		Collections.sort(reportItems, new Comparator<SectionPerformanceData>(){
			public int compare(SectionPerformanceData o1, SectionPerformanceData o2){
				return o2.getCategoryValue().compareTo(o1.getCategoryValue());
			}
		});

	}

	private void addTestItems(){
		List<Integer> includedStatusList = new ArrayList<Integer>();
		includedStatusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted)));

		List<Analysis> bioAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(BIOCHEMISTRY_SECTION_ID, includedStatusList, true);
		List<Analysis> serologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(SEROLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> hematologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(HEMATOLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> immunologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(IMMUNOLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> verologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(VEROLOGY_SECTION_ID, includedStatusList, true);

		fillDataForAnalysis(bioAnalysisList, "Biochimie test");
		fillDataForAnalysis(serologyAnalysisList, "Serologie test");
		fillDataForAnalysis(hematologyAnalysisList, "Hematologie test");
		fillDataForAnalysis(immunologyAnalysisList, "Immuno test");
		fillDataForAnalysis(verologyAnalysisList, "Verologie test");

	}

	private void addValidationItems(){
		List<Integer> includedStatusList = new ArrayList<Integer>();
		includedStatusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));

		List<Analysis> bioAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(BIOCHEMISTRY_SECTION_ID, includedStatusList, true);
		List<Analysis> serologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(SEROLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> hematologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(HEMATOLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> immunologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(IMMUNOLOGY_SECTION_ID, includedStatusList, true);
		List<Analysis> verologyAnalysisList = analysisDAO.getAllAnalysisByTestSectionAndStatus(VEROLOGY_SECTION_ID, includedStatusList, true);

		fillDataForValidation(bioAnalysisList, "Biochimie Validation");
		fillDataForValidation(serologyAnalysisList, "Serologie Validation");
		fillDataForValidation(hematologyAnalysisList, "Hematologie Validation");
		fillDataForValidation(immunologyAnalysisList, "Immuno Validation");
		fillDataForValidation(verologyAnalysisList, "Verologie Validation");

	}

	private void fillDataForValidation(List<Analysis> analysisList, String category){
		if(!analysisList.isEmpty()){

			int maxDays = 0;
			Analysis maxAnalysis = null;

			for(Analysis analysis : analysisList){
				int days = DateUtil.getDaysInPastForDate(analysis.getCompletedDate());
				if(days > maxDays){
					maxDays = days;
					maxAnalysis = analysis;
				}
			}

			if(maxAnalysis != null){
				Sample sample = getSampleForAnalysis(maxAnalysis);
				addDataFromSample(category, sample);
			}
		}
	}

	private void fillDataForAnalysis(List<Analysis> analysisList, String category){
		if(!analysisList.isEmpty()){
			Sample sample = getOldestSample(analysisList);
			addDataFromSample(category, sample);
		}
	}

	private Sample getOldestSample(List<Analysis> analysisList){
		// this is a bit simplistic to assume that the first is the oldest.
		Analysis analysis = analysisList.get(0);

		return getSampleForAnalysis(analysis);
	}

	private Sample getSampleForAnalysis(Analysis analysis){
		Sample sample = analysis.getSampleItem().getSample();
		return sample;
	}

	private void addIntakeItems() throws LIMSInvalidConfigurationException{
		String notRegisteredID = StatusService.getInstance().getDictionaryID(RecordStatus.NotRegistered);
		String initialRegisteredID = StatusService.getInstance().getDictionaryID(RecordStatus.InitialRegistration);

		ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();
		List<ObservationHistory> notRegisteredList = observationHistoryDAO.getObservationHistoryByDictonaryValues(notRegisteredID);
		List<ObservationHistory> initialRegisteredList = observationHistoryDAO.getObservationHistoryByDictonaryValues(initialRegisteredID);

		fillDataForRecords(notRegisteredList, "Pas d'inscription initiale");
		fillDataForRecords(initialRegisteredList, "Entrée non validé");
	}

	private void fillDataForRecords(List<ObservationHistory> observationList, String category) throws LIMSInvalidConfigurationException{
		if(!observationList.isEmpty()){
			String lowestSampleId = getLowestSampleId(observationList);
			Sample sample = new Sample();
			sample.setId(lowestSampleId);
			sampleDAO.getData(sample);

			addDataFromSample(category, sample);
		}
	}

	private void addDataFromSample(String category, Sample sample){
		SectionPerformanceData data = new SectionPerformanceData();
		int days = DateUtil.getDaysInPastForDate(sample.getEnteredDate());
		data.setCategoryValue(days);
		String label = category + " " + sample.getEnteredDateForDisplay() + " " + sample.getAccessionNumber();
		data.setCategoryLabel(label);

		reportItems.add(data);
	}

	private String getLowestSampleId(List<ObservationHistory> observationList){
		// redo so it actually looks, although this should be the lowest
		return observationList.get(0).getSampleId();
	}

}
