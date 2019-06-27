package spring.service.note;

import spring.service.note.NoteServiceImpl.BoundTo;

public interface NoteObject {

	String getTableId();

	String getObjectId();

	BoundTo getBoundTo();

}
