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
package us.mn.state.health.lims.common.services.historyservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class NoteHistoryService extends HistoryService {

	private static NoteDAO noteDAO = new NoteDAOImpl();
	private Map<String, String> noteIdToIndicatorMap;	

	public NoteHistoryService(Sample sample) {
		setUpForNotes( sample );
	}
	
	@SuppressWarnings("unchecked")
	private void setUpForNotes(Sample sample) {
		noteIdToIndicatorMap = new HashMap<String, String>();

		historyList = new ArrayList<History>();

        addAnalysisNotes( sample );
        addOrderNotes( sample );
        addQANotes( sample );
		newValueMap = new HashMap<String, String>();
	}

    private void addAnalysisNotes( Sample sample ){
        History searchHistory = new History();
        searchHistory.setReferenceTable( NoteService.TABLE_REFERENCE_ID);
        List<Analysis> analysisList = new SampleService( sample).getAnalysis();
        Note searchNote = new Note();
        searchNote.setReferenceTableId( AnalysisService.TABLE_REFERENCE_ID );
        for( Analysis analysis : analysisList){
            searchNote.setReferenceId(analysis.getId());

            List<Note> notes = noteDAO.getAllNotesByRefIdRefTable(searchNote);

            for(Note note : notes){
                searchHistory.setReferenceId(note.getId());
                noteIdToIndicatorMap.put(note.getId(), analysis.getTest().getTestName() );
                historyList.addAll(auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory));
            }
        }
    }

    private void addOrderNotes( Sample sample ){
        History searchHistory = new History();
        searchHistory.setReferenceTable( NoteService.TABLE_REFERENCE_ID);

        Note searchNote = new Note();
        searchNote.setReferenceTableId( SampleService.TABLE_REFERENCE_ID );
        searchNote.setReferenceId( sample.getId() );

        List<Note> notes = noteDAO.getAllNotesByRefIdRefTable(searchNote);

        for(Note note : notes){
            searchHistory.setReferenceId(note.getId());
            noteIdToIndicatorMap.put(note.getId(), StringUtil.getMessageForKey( "auditTrail.order" ) );
            historyList.addAll(auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory));
        }
    }

    private void addQANotes( Sample sample ){
        History searchHistory = new History();
        searchHistory.setReferenceTable( NoteService.TABLE_REFERENCE_ID);

        Note searchNote = new Note();
        searchNote.setReferenceTableId( QAService.TABLE_REFERENCE_ID );

        List<SampleQaEvent> qaEventList =  new SampleService( sample ).getSampleQAEventList();

        for( SampleQaEvent qaEvent : qaEventList){
            searchNote.setReferenceId(qaEvent.getId());
            List<Note> notes = noteDAO.getAllNotesByRefIdRefTable(searchNote);

            for(Note note : notes){
                searchHistory.setReferenceId(note.getId());
                noteIdToIndicatorMap.put(note.getId(), qaEvent.getQaEvent().getLocalizedName() );
                historyList.addAll(auditTrailDAO.getHistoryByRefIdAndRefTableId(searchHistory));
            }
        }
    }


    @Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		Note note = noteDAO.getData(history.getReferenceId());
		identifier = noteIdToIndicatorMap.get(history.getReferenceId());
		AuditTrailItem audit = getCoreTrail(history);
		audit.setNewValue( note.getText());
		items.add(audit);
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {
		
	}

	@Override
	protected String getObjectName() {
		return StringUtil.getMessageForKey("note.note");
	}
}
