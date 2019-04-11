package spring.mine.qaevent.service;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class NonConformityHelper {

	private static final String QA_NOTE_SUBJECT = "QaEvent Note";

	public static String getNoteForSample(Sample sample) {
		Note note = new NoteService(sample).getMostRecentNoteFilteredBySubject(QA_NOTE_SUBJECT);
		return note != null ? note.getText() : null;
	}

	public static String getNoteForSampleQaEvent(SampleQaEvent sampleQaEvent) {
		if (sampleQaEvent == null || GenericValidator.isBlankOrNull(sampleQaEvent.getId())) {
			return null;
		} else {
			Note note = new NoteService(sampleQaEvent).getMostRecentNoteFilteredBySubject(null);
			return note != null ? note.getText() : null;
		}
	}
}
