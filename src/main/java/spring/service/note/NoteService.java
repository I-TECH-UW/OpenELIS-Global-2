package spring.service.note;

import java.sql.Date;
import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.note.valueholder.Note;

public interface NoteService extends BaseObjectService<Note, String> {

	Note getData(String noteId);

	List<Note> getNotesByNoteTypeRefIdRefTable(Note note);

	List<Note> getNotesInDateRangeAndType(Date lowDate, Date highDate, String noteType, String referenceTableId);

	List getAllNotesByRefIdRefTable(Note note);

	List<Note> getNotesChronologicallyByRefIdAndRefTable(String refId, String table_id);

	List<Note> getNotesChronologicallyByRefIdAndRefTableAndType(String objectId, String tableId, List<String> filter);

	List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject);

}
