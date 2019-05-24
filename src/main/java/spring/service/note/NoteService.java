package spring.service.note;

import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.note.valueholder.Note;

public interface NoteService extends BaseObjectService<Note> {
	void getData(Note note);

	Note getData(String noteId);

	void deleteData(List notes);

	void updateData(Note note);

	boolean insertData(Note note);

	List<Note> getNotesByNoteTypeRefIdRefTable(Note note);

	List<Note> getNotesInDateRangeAndType(Date lowDate, Date highDate, String noteType, String referenceTableId);

	List getPreviousNoteRecord(String id);

	List getNextNoteRecord(String id);

	Integer getTotalNoteCount();

	List getAllNotesByRefIdRefTable(Note note);

	List<Note> getNotesChronologicallyByRefIdAndRefTable(String refId, String table_id);

	List<Note> getNotesChronologicallyByRefIdAndRefTableAndType(String objectId, String tableId, List<String> filter);

	List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject);

	List getPageOfNotes(int startingRecNo);

	List<Note> getAllNotes();
}
