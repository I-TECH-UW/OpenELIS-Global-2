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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ClinicalPatientData;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;

public class PatientClinicalReport extends PatientReport implements IReportCreator, IReportParameterSetter{

	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static Set<Integer> analysisStatusIds;
	private boolean isLNSP = false;
	protected List<ClinicalPatientData> clinicalReportItems;

	static{
		analysisStatusIds = new HashSet<Integer>();
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected)));
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated)));
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted)));
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
		analysisStatusIds.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
        analysisStatusIds.add(Integer.parseInt( StatusService.getInstance().getStatusID( AnalysisStatus.TechnicalRejected ) ) );

	}

	public PatientClinicalReport(){
		super();
	}

	public PatientClinicalReport( boolean isLNSP ){
		super();
		this.isLNSP = isLNSP;
	}

	@Override
	protected String reportFileName(){
		return "PatientClinicalReport";
	}

	@Override
	protected void createReportItems(){
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleIdAndStatusId(currentSampleService.getId(), analysisStatusIds);

		currentConclusion = null;
        Set<SampleItem> sampleSet = new HashSet<SampleItem>(  );
        List<ClinicalPatientData> currentSampleReportItems = new ArrayList<ClinicalPatientData>( analysisList.size() );
		for(Analysis analysis : analysisList){
            sampleSet.add( analysis.getSampleItem() );
            boolean hasParentResult = analysis.getParentResult() != null;

			if(analysis.getTest() != null ){
                currentAnalysisService = new AnalysisService( analysis );
				ClinicalPatientData resultsData = buildClinicalPatientData( hasParentResult );

				if(currentAnalysisService.getAnalysis().isReferredOut()){
					Referral referral = referralDao.getReferralByAnalysisId( currentAnalysisService.getAnalysis().getId() );
					if(referral != null){
						List<ClinicalPatientData> referredData = addReferredTests(referral, resultsData);
                        currentSampleReportItems.addAll( referredData );
					}
				}else{
                    currentSampleReportItems.add( resultsData );
                }
			}
		}

        reportItems.addAll( currentSampleReportItems );
        setCollectionTime( sampleSet, currentSampleReportItems, false );
	}

    private List<ClinicalPatientData> addReferredTests(Referral referral, ClinicalPatientData parentData){

        List<ClinicalPatientData> currentSampleReportItems = new ArrayList<ClinicalPatientData>(  );
        List<ReferralResult> referralResults = referralResultDAO.getReferralResultsForReferral(referral.getId());
        String note = new NoteService( currentAnalysisService.getAnalysis() ).getNotesAsString( false, true, "<br/>", FILTER, true );

		if( !referralResults.isEmpty()){
		
			boolean referralTestAssigned = false;
			for( ReferralResult referralResult : referralResults){
				if( referralResult.getTestId() != null){
					referralTestAssigned = true;
				}
			}
			
			if( !referralTestAssigned){
                currentSampleReportItems.add( parentData );
			}
		}else{
            currentSampleReportItems.add( parentData );
		}
		
		
		for(int i = 0; i < referralResults.size(); i++){
			if( referralResults.get(i).getResult() == null ){
				sampleCompleteMap.put(currentSampleService.getAccessionNumber(), Boolean.FALSE);
			}else{

				i = reportReferralResultValue(referralResults, i);
				ReferralResult referralResult = referralResults.get(i);

				ClinicalPatientData data = new ClinicalPatientData();
				copyParentData(data, parentData);

				data.setResult(reportReferralResultValue);
				data.setNote(note);
				String testId = referralResult.getTestId();
				if(!GenericValidator.isBlankOrNull(testId)){
					Test test = new Test();
					test.setId(testId);
					testDAO.getData(test);
					data.setTestName( TestService.getUserLocalizedReportingTestName( test ) );

					String uom = getUnitOfMeasure( test);
					if(reportReferralResultValue != null){
						data.setReferralResult(addIfNotEmpty(reportReferralResultValue, uom));
					}
					data.setTestRefRange(addIfNotEmpty(getRange(referralResult.getResult()), uom));
					data.setTestSortOrder(GenericValidator.isBlankOrNull(test.getSortOrder()) ? Integer.MAX_VALUE : Integer.parseInt(test
							.getSortOrder()));
					data.setSectionSortOrder( currentAnalysisService.getTestSection().getSortOrderInt());
					data.setTestSection( currentAnalysisService.getTestSection().getLocalizedName() );
				}

				if(GenericValidator.isBlankOrNull(reportReferralResultValue)){
					sampleCompleteMap.put(currentSampleService.getAccessionNumber(), Boolean.FALSE);
					data.setResult(StringUtil.getMessageForKey("report.test.status.inProgress"));
				}else{
					data.setResult( reportReferralResultValue );
				}

				data.setAlerts(getResultFlag(referralResult.getResult(), null));
				data.setHasRangeAndUOM(referralResult.getResult() != null && "N".equals(referralResult.getResult().getResultType()));

                currentSampleReportItems.add( data );
			}
		}

        return currentSampleReportItems;
	}

	private void copyParentData(ClinicalPatientData data, ClinicalPatientData parentData){
		data.setContactInfo(parentData.getContactInfo());
		data.setSiteInfo(parentData.getSiteInfo());
		data.setReceivedDate(parentData.getReceivedDate());
		data.setDob(parentData.getDob());
		data.setAge(parentData.getAge());
		data.setGender(parentData.getGender());
		data.setNationalId(parentData.getNationalId());
		data.setPatientName(parentData.getPatientName());
		data.setFirstName(parentData.getFirstName());
		data.setLastName(parentData.getLastName());
		data.setDept(parentData.getDept());
		data.setCommune(parentData.getCommune());
		data.setStNumber(parentData.getStNumber());
		data.setAccessionNumber(parentData.getAccessionNumber());
	}

	@Override
	protected void postSampleBuild(){
		if(reportItems.isEmpty()){
			ClinicalPatientData reportItem = buildClinicalPatientData( false );
			reportItem.setTestSection(StringUtil.getMessageForKey("report.no.results"));
			clinicalReportItems.add(reportItem);
		}else{
			buildReport();
		}

	}

	private void buildReport(){
		Collections.sort(reportItems, new Comparator<ClinicalPatientData>(){
			@Override
			public int compare(ClinicalPatientData o1, ClinicalPatientData o2){

				String o1AccessionNumber = AccessionNumberUtil.getAccessionNumberFromSampleItemAccessionNumber(o1.getAccessionNumber());
				String o2AccessionNumber = AccessionNumberUtil.getAccessionNumberFromSampleItemAccessionNumber(o2.getAccessionNumber());
				int accessionSort = o1AccessionNumber.compareTo(o2AccessionNumber);

				if(accessionSort != 0){
					return accessionSort;
				}

				if(o1.getSectionSortOrder() > o2.getSectionSortOrder()){
					return 1;
				}else if(o1.getSectionSortOrder() < o2.getSectionSortOrder()){
					return -1;
				}

				int o1Panel = Integer.MAX_VALUE;
				int o2Panel = Integer.MAX_VALUE;
				if(o1.getPanel() != null){
					o1Panel = o1.getPanel().getSortOrderInt();
				}
				if(o2.getPanel() != null){
					o2Panel = o2.getPanel().getSortOrderInt();
				}

				int panelSort = o1Panel - o2Panel;

				if(panelSort != 0){
					return panelSort;
				}

                if( o1.getParentResult() != null && o2.getParentResult() != null){
                    int parentSort = Integer.parseInt( o1.getParentResult().getId()) -
                            Integer.parseInt( o2.getParentResult().getId());

                    if( parentSort != 0){
                        return parentSort;
                    }
                }

				return o1.getTestSortOrder() - o2.getTestSortOrder();
			}
		});

        ArrayList<ClinicalPatientData> augmentedList = new ArrayList<ClinicalPatientData>( reportItems.size() );
        HashSet<String> parentResults = new HashSet<String>(  );
        for(ClinicalPatientData data : reportItems){
            if( data.getParentResult() != null && !parentResults.contains( data.getParentResult().getId() )){
                parentResults.add( data.getParentResult().getId() );
                ClinicalPatientData marker = new ClinicalPatientData(data);
                marker.setTestName( new ResultService(data.getParentResult()).getSimpleResultValue() );
                marker.setResult( null );
                marker.setTestRefRange( null );
                marker.setParentMarker( true );
                augmentedList.add( marker );
            }

            augmentedList.add( data );
        }

        reportItems = augmentedList;

		String currentPanelId = null;
		for(ClinicalPatientData reportItem : reportItems){
			if(reportItem.getPanel() != null && !reportItem.getPanel().getId().equals(currentPanelId)){
				currentPanelId = reportItem.getPanel().getId();
				reportItem.setSeparator(true);
			}else if(reportItem.getPanel() == null && currentPanelId != null){
				currentPanelId = null;
				reportItem.setSeparator(true);
			}

			reportItem.setAccessionNumber(reportItem.getAccessionNumber().split("-")[0]);
			reportItem.setCompleteFlag(StringUtil.getMessageForKey(sampleCompleteMap.get(reportItem.getAccessionNumber()) ? "report.status.complete" : "report.status.partial"));
            if( reportItem.isCorrectedResult()){
                //The report is French only
                if( reportItem.getNote() != null && reportItem.getNote().length() > 0 ){
                    reportItem.setNote( "Résultat corrigé<br/>" + reportItem.getNote() );
                }else{
                    reportItem.setNote( "Résultat corrigé" );
                }
            }

            reportItem.setCorrectedResult( sampleCorrectedMap.get(reportItem.getAccessionNumber().split( "_" )[0]) != null );
		}
	}

	@Override
	protected String getReportNameForParameterPage(){
		return StringUtil.getMessageForKey("openreports.patientTestStatus");
	}

	public JRDataSource getReportDataSource() throws IllegalStateException{
		if(!initialized){
			throw new IllegalStateException("initializeReport not called first");
		}

		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}

	@Override
	protected void initializeReportItems(){
		super.initializeReportItems();
		clinicalReportItems = new ArrayList<ClinicalPatientData>();
	}

	@Override
	protected void setReferredResult(ClinicalPatientData data, Result result){
		data.setResult(data.getResult() );
		data.setAlerts(getResultFlag(result, null));
	}

	@Override
	protected boolean appendUOMToRange(){
		return false;
	}

	@Override
	protected boolean augmentResultWithFlag(){
		return false;
	}

	@Override
	protected boolean useReportingDescription(){
		return true;
	}

    @Override
    protected String getHeaderName(){
        return "GeneralHeader.jasper";
    }

}
