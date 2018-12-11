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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.RejectionReportBean;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;

public abstract class RejectionReport extends Report implements IReportCreator{
    private int PREFIX_LENGTH = AccessionNumberUtil.getAccessionNumberValidator().getInvarientLength();
    protected List<RejectionReportBean> rejections;
    protected String reportPath = "";
    protected DateRange dateRange;

    @Override
    public JRDataSource getReportDataSource() throws IllegalStateException{
        return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource( rejections );
    }

    @Override
    protected void createReportParameters() {
        super.createReportParameters();
        reportParameters.put( "activityLabel", getActivityLabel() );
        reportParameters.put( "accessionPrefix", AccessionNumberUtil.getAccessionNumberValidator().getPrefix() );
        reportParameters.put( "labNumberTitle", StringUtil.getContextualMessageForKey( "quick.entry.accession.number" ) );
        reportParameters.put( "labName", ConfigurationProperties.getInstance().getPropertyValue( Property.SiteName ) );
        reportParameters.put( "SUBREPORT_DIR", reportPath );
        reportParameters.put( "startDate", dateRange.getLowDateStr() );
        reportParameters.put( "endDate", dateRange.getHighDateStr() );
        reportParameters.put( "isReportByTest", isReportByTest() );
    }

    protected boolean isReportByTest(){
        return Boolean.FALSE;
    }

    protected abstract String getActivityLabel();

    protected abstract void buildReportContent( ReportSpecificationList testSelection );

    @Override
    public void initializeReport( BaseActionForm dynaForm ){
        initialized = true;
        ReportSpecificationList selection = ( ReportSpecificationList ) dynaForm.get( "selectList" );
        String lowDateStr = dynaForm.getString( "lowerDateRange" );
        String highDateStr = dynaForm.getString( "upperDateRange" );
        dateRange = new DateRange( lowDateStr, highDateStr );

        errorFound = !validateSubmitParameters(selection);
        if ( errorFound ) {
            return;
        }

        buildReportContent( selection );
        if ( rejections.size() == 0 ) {
            add1LineErrorMessage("report.error.message.noPrintableItems");
        }
    }


    private boolean validateSubmitParameters(ReportSpecificationList selectList) {

        return (dateRange.validateHighLowDate("report.error.message.date.received.missing") &&
                validateSelection(selectList));
    }

    private boolean validateSelection( ReportSpecificationList selectList ){
        boolean complete = !GenericValidator.isBlankOrNull( selectList.getSelection() ) && !"0".equals( selectList.getSelection() );

        if( !complete){
            add1LineErrorMessage("report.error.message.activity.missing");
        }

        return complete;
    }


    protected RejectionReportBean createRejectionReportBean( String noteText, Analysis analysis, boolean useTestName  ){
        RejectionReportBean item = new RejectionReportBean();

        AnalysisService analysisService = new AnalysisService( analysis );
        SampleService sampleService = new SampleService(  analysisService.getAnalysis().getSampleItem().getSample() );
        PatientService patientService = new PatientService( sampleService.getSample() );

        List<Result> results = analysisService.getResults();
        for( Result result : results){
            String signature = new ResultService( result ).getSignature();
            if( !GenericValidator.isBlankOrNull( signature )){
                item.setTechnician( signature);
                break;
            }
        }

        item.setAccessionNumber( sampleService.getAccessionNumber().substring( PREFIX_LENGTH ) );
        item.setReceivedDate( sampleService.getTwoYearReceivedDateForDisplay() );
        item.setCollectionDate( DateUtil.convertTimestampToTwoYearStringDate( analysisService.getAnalysis().getSampleItem().getCollectionDate() ) );
        item.setRejectionReason( noteText );

        StringBuilder nameBuilder = new StringBuilder( patientService.getLastName().toUpperCase() );
        if( !GenericValidator.isBlankOrNull( patientService.getNationalId() ) ){
            if( nameBuilder.length() > 0 ){
                nameBuilder.append( " / " );
            }
            nameBuilder.append( patientService.getNationalId() );
        }


        if( useTestName ){
            item.setPatientOrTestName( TestService.getUserLocalizedTestName( analysisService.getTest() ) );
            item.setNonPrintingPatient( nameBuilder.toString() );
        }else{
            item.setPatientOrTestName( nameBuilder.toString() );
        }

        return item;
    }

    @Override
    protected String reportFileName(){
        return  "RejectionReport";
    }

    protected RejectionReportBean createIdentityRejectionBean( RejectionReportBean item, boolean blankCollectionDate ){
        RejectionReportBean filler = new RejectionReportBean();

        filler.setAccessionNumber( item.getAccessionNumber() );
        filler.setReceivedDate( item.getReceivedDate() );
        filler.setCollectionDate( blankCollectionDate ? " " : item.getCollectionDate() );
        filler.setPatientOrTestName( item.getNonPrintingPatient() );

        return filler;
    }

    protected String getNameForId( ReportSpecificationList list ){

        String selection = list.getSelection();

        for( IdValuePair pair : list.getList()){
            if( selection.equals( pair.getId() )){
                return pair.getValue();
            }
        }

        return "";
    }

    protected void injectPatientLineAndCopyToFinalList( ArrayList<RejectionReportBean> rawResults ){
        Collections.sort( rawResults, new Comparator<RejectionReportBean>(){
            @Override
            public int compare( RejectionReportBean o1, RejectionReportBean o2 ){
                int sortResult = o1.getAccessionNumber().compareTo( o2.getAccessionNumber() );
                return sortResult == 0 ? o1.getPatientOrTestName().compareTo( o2.getPatientOrTestName() ) : sortResult;
            }
        } );

        String currentAccessionNumber = "";
        for( RejectionReportBean item : rawResults){
            if( !currentAccessionNumber.equals( item.getAccessionNumber() )){
                rejections.add( createIdentityRejectionBean( item, false ) );
                currentAccessionNumber = item.getAccessionNumber();
            }
            item.setCollectionDate( null );
            rejections.add( item );
        }
    }
}
