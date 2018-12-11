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
public class RejectionReportByTestSection extends RejectionReport implements IReportCreator, IReportParameterSetter {
    private String unitName;

    @Override
    public void setRequestParameters( BaseActionForm dynaForm ){
        new ReportSpecificationParameters( ReportSpecificationParameters.Parameter.DATE_RANGE,
                StringUtil.getMessageForKey( "report.rejection.report.base" ) + " " + StringUtil.getMessageForKey( "report.by.unit" ),
                StringUtil.getMessageForKey( "report.instruction.all.fields" ) ).setRequestParameters( dynaForm );
        new ReportSpecificationList( DisplayListService.getList( DisplayListService.ListType.TEST_SECTION ),
                                     StringUtil.getMessageForKey( "workplan.unit.types" ) ).setRequestParameters( dynaForm );
    }

    @Override
    protected String getActivityLabel(){
        return StringUtil.getMessageForKey( "report.unit" ) + ": "+ unitName;
    }

    @Override
    protected void buildReportContent( ReportSpecificationList panelSelection ){

        unitName = getNameForId( panelSelection );
        createReportParameters();

        rejections = new ArrayList<RejectionReportBean>();
        ArrayList<RejectionReportBean> rawResults = new ArrayList<RejectionReportBean>(  );

        List<Note> testRejectionNotes = NoteService.getTestNotesInDateRangeByType( dateRange.getLowDate(), dateRange.getHighDate(), NoteService.NoteType.REJECTION_REASON );

        Collections.sort( testRejectionNotes, new Comparator<Note>(){
            @Override
            public int compare( Note o1, Note o2 ){
                return o1.getReferenceId().compareTo( o2.getReferenceId() );
            }
        } );


        Analysis currentAnalysis = new Analysis();
        String noteText = null;
        for( Note note : testRejectionNotes ){
            Analysis analysis = new AnalysisService( note.getReferenceId() ).getAnalysis();
            if( analysis != null && analysis.getTestSection() != null && panelSelection.getSelection().equals( analysis.getTestSection().getId() ) ){
                if( analysis.getId().equals( currentAnalysis.getId() ) ){
                    noteText += (noteText != null ? "<br/>" : "") + note.getText();
                }else{
                    if( noteText != null ){
                        rawResults.add( createRejectionReportBean( noteText, currentAnalysis, true ) );
                    }
                    noteText = note.getText();
                }
                currentAnalysis = analysis;
            }
        }

        //pick up last rejection note
        if( noteText != null ){
            rawResults.add( createRejectionReportBean( noteText, currentAnalysis, true ) );
        }

        injectPatientLineAndCopyToFinalList( rawResults );
    }
}
