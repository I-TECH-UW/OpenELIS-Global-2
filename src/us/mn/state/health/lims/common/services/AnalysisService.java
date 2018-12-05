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

package us.mn.state.health.lims.common.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 */
public class AnalysisService{
    private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
    private static final DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
    private static final ResultDAO resultDAO = new ResultDAOImpl();
    private static final TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
    private final Analysis analysis;
    public static final String TABLE_REFERENCE_ID;
    private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";

    static{
        TABLE_REFERENCE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("ANALYSIS").getId();
    }

    public AnalysisService(Analysis analysis){
        this.analysis = analysis;
    }

    public AnalysisService(String analysisId){
        analysis = analysisDAO.getAnalysisById( analysisId );
    }

    public Analysis getAnalysis(){
        return analysis;
    }

    public String getTestDisplayName( ){
        if( analysis == null){return ""; }
        Test test = getTest();
        String name = TestService.getLocalizedTestNameWithType( test );

        TypeOfSample typeOfSample = TypeOfSampleService.getTypeOfSampleForTest(test.getId());

        if( typeOfSample != null && typeOfSample.getId().equals( TypeOfSampleService.getTypeOfSampleIdForLocalAbbreviation("Variable"))){
            name += "(" + analysis.getSampleTypeName() + ")";
        }

        String parentResultType = analysis.getParentResult() != null ? analysis.getParentResult().getResultType() : "";
        if(  TypeOfTestResultService.ResultType.isMultiSelectVariant( parentResultType ) ){
            Dictionary dictionary = dictionaryDAO.getDictionaryById( analysis.getParentResult().getValue() );
            if( dictionary != null){
                String parentResult = dictionary.getLocalAbbreviation();
                if( GenericValidator.isBlankOrNull( parentResult )){
                    parentResult = dictionary.getDictEntry();
                }
                name = parentResult + " &rarr; " + name;
            }
        }

        return name;
    }

    public String getCSVMultiselectResults(){
        if( analysis == null){return ""; }
        List<Result> existingResults = resultDAO.getResultsByAnalysis( analysis );
        StringBuilder multiSelectBuffer = new StringBuilder();
        for( Result existingResult : existingResults ){
            if( TypeOfTestResultService.ResultType.isMultiSelectVariant( existingResult.getResultType() )){
                multiSelectBuffer.append( existingResult.getValue() );
                multiSelectBuffer.append( ',' );
            }
        }

        // remove last ','
        multiSelectBuffer.setLength( multiSelectBuffer.length() - 1 );

        return multiSelectBuffer.toString();
    }

    public String getJSONMultiSelectResults(){
        return analysis == null ? "" : ResultService.getJSONStringForMultiSelect(resultDAO.getResultsByAnalysis( analysis ));
    }

    public Result getQuantifiedResult(){
        if( analysis == null){return null; }
        List<Result> existingResults = resultDAO.getResultsByAnalysis( analysis );
        List<String> quantifiableResultsIds = new ArrayList<String>(  );
        for( Result existingResult : existingResults ){
            if( TypeOfTestResultService.ResultType.isDictionaryVariant( existingResult.getResultType() ) ){
                quantifiableResultsIds.add( existingResult.getId() );
            }
        }

        for( Result existingResult : existingResults ){
            if( !TypeOfTestResultService.ResultType.isDictionaryVariant( existingResult.getResultType() ) &&
                    existingResult.getParentResult() != null &&
                    quantifiableResultsIds.contains( existingResult.getParentResult().getId()) &&
                    !GenericValidator.isBlankOrNull(existingResult.getValue())){
            return existingResult;
            }
        }

        return null;
    }
    public String getCompletedDateForDisplay(){
        return analysis == null ? "" : analysis.getCompletedDateForDisplay();
    }

    public String getAnalysisType(){
        return analysis == null ? "" :analysis.getAnalysisType();
    }

    public String getStatusId(){
        return analysis == null ? "" :analysis.getStatusId();
    }

    public Boolean getTriggeredReflex(){
        return analysis == null ? false :analysis.getTriggeredReflex();
    }

    public boolean resultIsConclusion(Result currentResult){
        if( analysis == null || currentResult == null){return false; }
        List<Result> results = resultDAO.getResultsByAnalysis(analysis);
        if (results.size() == 1) {
            return false;
        }

        Long testResultId = Long.parseLong(currentResult.getId());
        // This based on the fact that the conclusion is always added
        // after the shared result so if there is a result with a larger id
        // then this is not a conclusion
        for (Result result : results) {
            if (Long.parseLong(result.getId()) > testResultId) {
                return false;
            }
        }

        return true;
    }

    public boolean isParentNonConforming(){
        return analysis == null ? false :QAService.isAnalysisParentNonConforming(analysis);
    }

    public Test getTest(){
        return analysis == null ? null :analysis.getTest();
    }

    public static List<Analysis> getAnalysisStartedOrCompletedInDateRange(Date lowDate, Date highDate){
        return analysisDAO.getAnalysisStartedOrCompletedInDateRange(lowDate, highDate);
    }

    public List<Result> getResults(){
        return analysis == null ? new ArrayList<Result>(  ) : resultDAO.getResultsByAnalysis( analysis );
    }

    public boolean hasBeenCorrectedSinceLastPatientReport(){
        return analysis == null ? false : analysis.isCorrectedSincePatientReport();
    }

    public boolean patientReportHasBeenDone(){
        return analysis == null ? false : new ReportTrackingService().getLastReportForSample( analysis.getSampleItem().getSample(), ReportTrackingService.ReportType.PATIENT ) != null;
    }

    public String getNotesAsString( boolean prefixType, boolean prefixTimestamp, String noteSeparator, boolean excludeExternPrefix  ){
        return analysis == null ? "" : new NoteService( analysis ).getNotesAsString( prefixType, prefixTimestamp, noteSeparator, excludeExternPrefix );
    }

    public String getOrderAccessionNumber(){
        return analysis == null ? "" : analysis.getSampleItem().getSample().getAccessionNumber();
    }

    public TypeOfSample getTypeOfSample(){
       return analysis == null ? null : typeOfSampleDAO.getTypeOfSampleById( analysis.getSampleItem().getTypeOfSampleId() );
    }

    public Panel getPanel(){
        return analysis == null ? null : analysis.getPanel();
    }

    public TestSection getTestSection(){
        return analysis == null ? null : analysis.getTestSection();
    }

    public static Analysis buildAnalysis(Test test, SampleItem sampleItem){

            Analysis analysis = new Analysis();
            analysis.setTest(test);
            analysis.setIsReportable(test.getIsReportable());
            analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
            analysis.setRevision("0");
            analysis.setStartedDate(DateUtil.getNowAsSqlDate());
            analysis.setStatusId(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.NotStarted));
            analysis.setSampleItem(sampleItem);
            analysis.setTestSection(test.getTestSection());
            analysis.setSampleTypeName(sampleItem.getTypeOfSample().getLocalizedName());

        return analysis;
    }
}
