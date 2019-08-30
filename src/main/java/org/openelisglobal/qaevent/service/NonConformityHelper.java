package org.openelisglobal.qaevent.service;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.spring.util.SpringContext;

public class NonConformityHelper {

    private static final String QA_NOTE_SUBJECT = "QaEvent Note";

    public static String getNoteForSample(Sample sample) {
        NoteService noteService = SpringContext.getBean(NoteService.class);
        Note note = noteService.getMostRecentNoteFilteredBySubject(sample, QA_NOTE_SUBJECT);
        return note != null ? note.getText() : null;
    }

    public static String getNoteForSampleQaEvent(SampleQaEvent sampleQaEvent) {
        if (sampleQaEvent == null || GenericValidator.isBlankOrNull(sampleQaEvent.getId())) {
            return null;
        } else {
            NoteService noteService = SpringContext.getBean(NoteService.class);
            Note note = noteService.getMostRecentNoteFilteredBySubject(sampleQaEvent, null);
            return note != null ? note.getText() : null;
        }
    }
}
