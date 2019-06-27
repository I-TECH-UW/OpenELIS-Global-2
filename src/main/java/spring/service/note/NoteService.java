package spring.service.note;

import java.sql.Date;
import java.util.List;

import spring.service.common.BaseObjectService;
import spring.service.note.NoteServiceImpl.NoteType;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.util.StringUtil.EncodeContext;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public interface NoteService extends BaseObjectService<Note, String> {

	Note getData(String noteId);

	List<Note> getNotesByNoteTypeRefIdRefTable(Note note);

	List<Note> getNotesInDateRangeAndType(Date lowDate, Date highDate, String noteType, String referenceTableId);

	List getAllNotesByRefIdRefTable(Note note);

	List<Note> getNotesChronologicallyByRefIdAndRefTable(String refId, String table_id);

	List<Note> getNotesChronologicallyByRefIdAndRefTableAndType(String objectId, String tableId, List<String> filter);

	List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject);

	void setSample(Sample sample);

	void setSampleQaEvent(SampleQaEvent sampleQaEvent);

	void setSampleItem(SampleItem sampleItem);

	String getNotesAsString(String prefix, String noteSeparator);

	Note createSavableNote(NoteType type, String text, String subject, String currentUserId);

	Note getMostRecentNoteFilteredBySubject(String filter);

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix, EncodeContext context);

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix);

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, NoteType[] filter,
			boolean excludeExternPrefix, EncodeContext context);

	String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, NoteType[] filter,
			boolean excludeExternPrefix);

	void setAnalysis(Analysis analysis);

}
