package org.openelisglobal.note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.valueholder.Note;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class NoteServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    NoteService noteService;

    @Before
    public void init() throws Exception {
        assertNotNull(noteService);
        noteService.deleteAll(noteService.getAll());
    }

    @After
    public void tearDown() {
        noteService.deleteAll(noteService.getAll());

    }

    @Test
    public void deleteNote_shouldDeleteNote() throws Exception {
        String subject = "Test Note Subject";
        String text = "This is a test note text.";

        Note note = new Note();
        note.setSubject(subject);
        note.setText(text);

        String noteId = noteService.insert(note);

        Note savedNote = noteService.get(noteId);
        noteService.delete(savedNote);
        assertEquals(0, noteService.getAll().size());
    }

    @Test
    public void getNote_shouldReturnNullForNonExistentNote() throws Exception {
        String nonExistentNoteId = "nonExistentNoteId";

        Note note = noteService.get(nonExistentNoteId);
        assertNull(note);
    }

    @Test
    public void deleteAllNotes_shouldClearAllNotes() throws Exception {
        String subject1 = "Note 1";
        String text1 = "First test note.";

        Note note1 = new Note();
        note1.setSubject(subject1);
        note1.setText(text1);
        noteService.insert(note1);

        String subject2 = "Note 2";
        String text2 = "Second test note.";

        Note note2 = new Note();
        note2.setSubject(subject2);
        note2.setText(text2);
        noteService.insert(note2);

        noteService.deleteAll(noteService.getAll());

        assertEquals(0, noteService.getAll().size());
    }
}
