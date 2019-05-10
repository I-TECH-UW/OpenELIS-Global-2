package spring.service.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.valueholder.Note;

@Service
public class NoteServiceImpl extends BaseObjectServiceImpl<Note> implements NoteService {
  @Autowired
  protected NoteDAO baseObjectDAO;

  NoteServiceImpl() {
    super(Note.class);
  }

  @Override
  protected NoteDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
