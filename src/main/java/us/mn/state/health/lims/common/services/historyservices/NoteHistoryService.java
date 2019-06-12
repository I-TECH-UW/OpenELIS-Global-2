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

import spring.mine.internationalization.MessageUtil;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.history.HistoryService;
import spring.service.note.NoteService;
import spring.service.note.NoteServiceImpl;
import spring.service.sample.SampleServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class NoteHistoryService extends AbstractHistoryService {

	protected NoteService noteService = SpringContext.getBean(NoteService.class);
	protected HistoryService historyService = SpringContext.getBean(HistoryService.class);

	private Map<String, String> noteIdToIndicatorMap;

	public NoteHistoryService(Sample sample) {
		setUpForNotes(sample);
	}

	@SuppressWarnings("unchecked")
	private void setUpForNotes(Sample sample) {
		noteIdToIndicatorMap = new HashMap<>();

		historyList = new ArrayList<>();

		addAnalysisNotes(sample);
		addOrderNotes(sample);
		addQANotes(sample);
		newValueMap = new HashMap<>();
	}

	private void addAnalysisNotes(Sample sample) {
		History searchHistory = new History();
		searchHistory.setReferenceTable(NoteServiceImpl.TABLE_REFERENCE_ID);
		List<Analysis> analysisList = new SampleServiceImpl(sample).getAnalysis();
		Note searchNote = new Note();
		searchNote.setReferenceTableId(AnalysisServiceImpl.TABLE_REFERENCE_ID);
		for (Analysis analysis : analysisList) {
			searchNote.setReferenceId(analysis.getId());

			List<Note> notes = noteService.getAllNotesByRefIdRefTable(searchNote);

			for (Note note : notes) {
				searchHistory.setReferenceId(note.getId());
				noteIdToIndicatorMap.put(note.getId(), analysis.getTest().getTestName());
				historyList.addAll(historyService.getHistoryByRefIdAndRefTableId(searchHistory));
			}
		}
	}

	private void addOrderNotes(Sample sample) {
		History searchHistory = new History();
		searchHistory.setReferenceTable(NoteServiceImpl.TABLE_REFERENCE_ID);

		Note searchNote = new Note();
		searchNote.setReferenceTableId(SampleServiceImpl.TABLE_REFERENCE_ID);
		searchNote.setReferenceId(sample.getId());

		List<Note> notes = noteService.getAllNotesByRefIdRefTable(searchNote);

		for (Note note : notes) {
			searchHistory.setReferenceId(note.getId());
			noteIdToIndicatorMap.put(note.getId(), MessageUtil.getMessage("auditTrail.order"));
			historyList.addAll(historyService.getHistoryByRefIdAndRefTableId(searchHistory));
		}
	}

	private void addQANotes(Sample sample) {
		History searchHistory = new History();
		searchHistory.setReferenceTable(NoteServiceImpl.TABLE_REFERENCE_ID);

		Note searchNote = new Note();
		searchNote.setReferenceTableId(QAService.TABLE_REFERENCE_ID);

		List<SampleQaEvent> qaEventList = new SampleServiceImpl(sample).getSampleQAEventList();

		for (SampleQaEvent qaEvent : qaEventList) {
			searchNote.setReferenceId(qaEvent.getId());
			List<Note> notes = noteService.getAllNotesByRefIdRefTable(searchNote);

			for (Note note : notes) {
				searchHistory.setReferenceId(note.getId());
				noteIdToIndicatorMap.put(note.getId(), qaEvent.getQaEvent().getLocalizedName());
				historyList.addAll(historyService.getHistoryByRefIdAndRefTableId(searchHistory));
			}
		}
	}

	@Override
	protected void addInsertion(History history, List<AuditTrailItem> items) {
		Note note = noteService.getData(history.getReferenceId());
		identifier = noteIdToIndicatorMap.get(history.getReferenceId());
		AuditTrailItem audit = getCoreTrail(history);
		audit.setNewValue(note.getText());
		items.add(audit);
	}

	@Override
	protected void getObservableChanges(History history, Map<String, String> changeMap, String changes) {

	}

	@Override
	protected String getObjectName() {
		return MessageUtil.getMessage("note.note");
	}
}
