package spring.mine.qaevent.service;

import org.apache.commons.validator.GenericValidator;

import spring.service.note.NoteService;
import spring.util.SpringContext;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class NonConformityHelper {

	private static final String QA_NOTE_SUBJECT = "QaEvent Note";

	public static String getNoteForSample(Sample sample) {
		NoteService noteSampleService = SpringContext.getBean(NoteService.class);
		noteSampleService.setSample(sample);
		Note note = noteSampleService.getMostRecentNoteFilteredBySubject(QA_NOTE_SUBJECT);
		return note != null ? note.getText() : null;
	}

	public static String getNoteForSampleQaEvent(SampleQaEvent sampleQaEvent) {
		if (sampleQaEvent == null || GenericValidator.isBlankOrNull(sampleQaEvent.getId())) {
			return null;
		} else {
			NoteService noteSampleQaEventService = SpringContext.getBean(NoteService.class);
			noteSampleQaEventService.setSampleQaEvent(sampleQaEvent);
			Note note = noteSampleQaEventService.getMostRecentNoteFilteredBySubject(null);
			return note != null ? note.getText() : null;
		}
	}
}
