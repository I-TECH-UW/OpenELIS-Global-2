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

import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import spring.service.result.ResultServiceImpl;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ActivityReportBean;
import us.mn.state.health.lims.result.valueholder.Result;

/**
 */
public class ActivityReportByTest extends ActivityReport implements IReportCreator, IReportParameterSetter {
    private String testName = "";

    @Override
    protected boolean isReportByTest(){
        return true;
    }

    @Override
    public void setRequestParameters( BaseForm form ){
        new ReportSpecificationParameters( ReportSpecificationParameters.Parameter.DATE_RANGE,
                MessageUtil.getMessage( "report.activity.report.base" ) + " " + MessageUtil.getMessage( "report.by.test" ),
                MessageUtil.getMessage( "report.instruction.all.fields" ) ).setRequestParameters( form );
        new ReportSpecificationList( DisplayListService.getList( DisplayListService.ListType.ALL_TESTS ),
                                     MessageUtil.getMessage( "workplan.test.types" ) ).setRequestParameters( form );
    }

    @Override
    protected void buildReportContent( ReportSpecificationList testSelection ){

        testName = testSelection.getSelectionAsName();
        createReportParameters();
        
        // do not print the separator bar between name/Id and tests
        reportParameters.put( "underlineResults", false );
        
        List<Result> resultList = ResultServiceImpl.getResultsInTimePeriodWithTest( dateRange.getLowDate(), dateRange.getHighDate(), testSelection.getSelection() );
        testsResults = new ArrayList<ActivityReportBean>( resultList.size() );

        String currentAnalysisId = "-1";
        for( Result result : resultList){
            if( result.getAnalysis() != null){
                if( !currentAnalysisId.equals( result.getAnalysis().getId())){
                    testsResults.add( createActivityReportBean( result, false ) );
                    currentAnalysisId = result.getAnalysis().getId();
                }
            }
        }
    }


    @Override
    protected String getActivityLabel(){
        return "Test: " + testName;
    }
}
