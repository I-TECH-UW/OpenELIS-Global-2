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

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.RejectionReportBean;

/**
 */
public class RejectionReportByTest extends RejectionReport implements IReportCreator, IReportParameterSetter {
    private String testName = "";

    @Override
    public void setRequestParameters( BaseActionForm dynaForm ){
        new ReportSpecificationParameters( ReportSpecificationParameters.Parameter.DATE_RANGE,
                StringUtil.getMessageForKey( "report.rejection.report.base" ) + " " + StringUtil.getMessageForKey( "report.by.test" ),
                StringUtil.getMessageForKey( "report.instruction.all.fields" ) ).setRequestParameters( dynaForm );
        new ReportSpecificationList( DisplayListService.getList( DisplayListService.ListType.ALL_TESTS ),
                                     StringUtil.getMessageForKey( "workplan.test.types" ) ).setRequestParameters( dynaForm );
    }

    @Override
    protected void buildReportContent( ReportSpecificationList testSelection ){

        testName = getNameForId( testSelection );
        createReportParameters();

        rejections = new ArrayList<RejectionReportBean>();
        List<Note> testRejectionNotes = NoteService.getTestNotesInDateRangeByType( dateRange.getLowDate(), dateRange.getHighDate(), NoteService.NoteType.REJECTION_REASON );

        Analysis currentAnalysis = new Analysis();
        String noteText = null;
        for( Note note : testRejectionNotes ){
            Analysis analysis = new AnalysisService( note.getReferenceId() ).getAnalysis();
            if( analysis != null && testSelection.getSelection().equals( analysis.getTest().getId() ) ){
                if( analysis.getId().equals( currentAnalysis.getId() ) ){
                    noteText += (noteText != null ? "<br/>" : "") + note.getText();
                }else{
                    if( noteText != null ){
                        rejections.add( createRejectionReportBean( noteText, currentAnalysis, false ) );
                    }
                    noteText = note.getText();
                }
                currentAnalysis = analysis;
            }
        }

        //pick up last rejection note
        if( noteText != null ){
            rejections.add( createRejectionReportBean( noteText, currentAnalysis, false ) );
        }

        Collections.sort( rejections, new Comparator<RejectionReportBean>(){
            @Override
            public int compare( RejectionReportBean o1, RejectionReportBean o2 ){
                return o1.getAccessionNumber().compareTo( o2.getAccessionNumber() );
            }
        } );
    }

    @Override
    protected boolean isReportByTest(){
        return Boolean.TRUE;
    }

    @Override
    protected String getActivityLabel(){
        return "Test: " + testName;
    }
}
