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

package us.mn.state.health.lims.result.action.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

/**
 */
public class ResultsUpdateDataSet implements IResultSaveService{
    private List<TestResultItem> modifiedItems = new ArrayList<TestResultItem>(  );
    private List<ResultSet> modifiedResults = new ArrayList<ResultSet>(  );
    private List<TestResultItem> analysisOnlyChangeResults = new ArrayList<TestResultItem>(  );
    private List<ResultSet> newResults = new ArrayList<ResultSet>(  );
    private List<Analysis> modifiedAnalysis = new ArrayList<Analysis>(  );
    private List<Result> deletableResults = new ArrayList<Result>(  );
    private List<Referral> savableReferrals = new ArrayList<Referral>(  );
    private List<String> referredAnalysisIds = new ArrayList<String>(  );
    private Analysis previousAnalysis = new Analysis();
    private ResultsValidation resultValidation = new ResultsValidation();
    private List<Note> noteList = new ArrayList<Note>(  );

    private final String currentUserId;

    public ResultsUpdateDataSet( String currentUserId){
        this.currentUserId  = currentUserId;
    }

    public List<TestResultItem> getModifiedItems(){
        return modifiedItems;
    }

    @Override
    public List<ResultSet> getModifiedResults(){
        return modifiedResults;
    }
    
    public void setModifiedResults(List<ResultSet> modifiedResults) {
    	this.modifiedResults = modifiedResults;
    }

    @Override
    public String getCurrentUserId(){
        return currentUserId;
    }


    @Override
    public List<ResultSet> getNewResults(){
        return newResults;
    }

    public List<TestResultItem> getAnalysisOnlyChangeResults(){
        return analysisOnlyChangeResults;
    }

    public List<Analysis> getModifiedAnalysis(){
        return modifiedAnalysis;
    }

    public List<Result> getDeletableResults(){
        return deletableResults;
    }

    public List<Referral> getSavableReferrals(){
        return savableReferrals;
    }

    public List<String> getReferredAnalysisIds(){
        return referredAnalysisIds;
    }

    public Analysis getPreviousAnalysis(){
        return previousAnalysis;
    }

    public List<Note> getNoteList(){
        return noteList;
    }

    public void addToNoteList( Note note){
        if( note != null){
            noteList.add( note );
        }
    }
    public void filterModifiedItems( List<TestResultItem> allItems ){
        for(TestResultItem item : allItems){
            if( isUpdated( item )){
                modifiedItems.add(item);
            }else if( item.getIsModified()){
                //this covers cases such as test date change or test method change w/o data update
               analysisOnlyChangeResults.add( item );
            }
        }
    }

    public ActionMessages validateModifiedItems(){
        return resultValidation.validateModifiedItems(getModifiedItems());
    }




    private boolean isUpdated( TestResultItem item ){
        return item.getIsModified()
                && (ResultUtil.areResults(item) || ResultUtil.areNotes(item) || ResultUtil.isReferred(item) || ResultUtil.isForcedToAcceptance(item) || ResultUtil.isRejected(item));
    }


    public void setPreviousAnalysis( Analysis previousAnalysis ){
        this.previousAnalysis = previousAnalysis;
    }
}
