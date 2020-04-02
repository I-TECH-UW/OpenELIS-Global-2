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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.note.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.note.dao.NoteDAO;
import org.openelisglobal.note.valueholder.Note;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class NoteDAOImpl extends BaseDAOImpl<Note, String> implements NoteDAO {

    public NoteDAOImpl() {
        super(Note.class);
    }

//	@Override
//	public void deleteData(List notes) throws LIMSRuntimeException {
//		try {
//
//			for (Object note : notes) {
//				Note data = (Note) note;
//
//				Note oldData = readNote(data.getId());
//				Note newData = new Note();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "NOTE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Note AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (Object note : notes) {
//				Note data = (Note) note;
//				data = readNote(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Note deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Note note) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(note);
//			note.setId(id);
//
//			String sysUserId = note.getSysUserId();
//			String tableName = "NOTE";
//			auditDAO.saveNewHistory(note, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Note insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void getData(Note note) throws LIMSRuntimeException {
//		try {
//			Note nt = entityManager.unwrap(Session.class).get(Note.class, note.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (nt != null) {
//				PropertyUtils.copyProperties(note, nt);
//
//			} else {
//				note.setId(null);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Note getData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public Note getData(String noteId) throws LIMSRuntimeException {
        try {
            Note note = entityManager.unwrap(Session.class).get(Note.class, noteId);
            // closeSession(); // CSL remove old
            return note;
        } catch (RuntimeException e) {
            handleException(e, "getData");
        }

        return null;
    }

//	@Override
//	public List getPageOfNotes(int startingRecNo) throws LIMSRuntimeException {
//		List list;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//			// bugzilla 2571 go through ReferenceTablesDAO to get reference tables info
//			String sql = "from Note nt order by nt.referenceTableId, nt.referenceId,nt.noteType";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "getPageOfNotes()", e.toString());
//			throw new LIMSRuntimeException("Error in Note getPageOfNotes()", e);
//		}
//
//		return list;
//	}

//	public Note readNote(String idString) {
//		Note note;
//		try {
//			note = entityManager.unwrap(Session.class).get(Note.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "readNote()", e.toString());
//			throw new LIMSRuntimeException("Error in Note readNote()", e);
//		}
//
//		return note;
//	}

//	@Override
//	public List getNextNoteRecord(String id) throws LIMSRuntimeException {
//
//		return null;// getNextRecord(id, "Note", Note.class);
//
//	}
//
//	@Override
//	public List getPreviousNoteRecord(String id) throws LIMSRuntimeException {
//
//		return null;// getPreviousRecord(id, "Note", Note.class);
//	}

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAllNotesByRefIdRefTable(Note note) throws LIMSRuntimeException {
        try {

            String sql = "from Note n where n.referenceId = :refId and n.referenceTableId = :refTableId order by n.noteType desc, n.lastupdated desc";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("refId", Integer.parseInt(note.getReferenceId()));

            query.setInteger("refTableId", Integer.parseInt(note.getReferenceTableId()));

            List<Note> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Note getAllNotesByRefIdRefTable()", e);
        }
    }

//	@Override
//
//	public List<Note> getNotesByNoteTypeRefIdRefTable(Note note) throws LIMSRuntimeException {
//		try {
//			String sql = "from Note n where n.referenceId = :refId and n.referenceTableId = :refTableId and n.noteType = :noteType order by n.lastupdated";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setInteger("refId", Integer.parseInt(note.getReferenceId()));
//
//			query.setInteger("refTableId", Integer.parseInt(note.getReferenceTableId()));
//			query.setParameter("noteType", note.getNoteType());
//
//			List<Note> list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return list;
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("NoteDAOImpl", "getNotesByNoteTypeRefIdRefTable()", e.toString());
//			throw new LIMSRuntimeException("Error in Note getNotesByNoteTypeRefIdRefTable()", e);
//		}
//	}

//	@Override
//	public Integer getTotalNoteCount() throws LIMSRuntimeException {
//		return getCount();
//	}
//
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = Integer.valueOf(id);
//		String tablePrefix = getTablePrefix(table);
//
//		List list;
//		int rrn;
//		try {
//			String sql = "select n.id from Note n " + " order by n.referenceTableId, n.referenceId, n.noteType";
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}
//
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = Integer.valueOf(id);
//		String tablePrefix = getTablePrefix(table);
//
//		List list;
//		int rrn;
//		try {
//			String sql = "select n.id from Note n "
//					+ " order by n.referenceTableId desc, n.referenceId desc, n.noteType desc";
//
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("NoteDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    @Override

    public boolean duplicateNoteExists(Note note) {
        try {

            List<Note> list;

            String sql = "from Note t where trim(lower(t.noteType)) = :noteType and t.referenceId = :referenceId and t.referenceTableId = :referenceTableId and trim(lower(t.text)) = :param4 and trim(lower(t.subject)) = :param5 and t.id != :noteId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("noteType", note.getNoteType().toLowerCase().trim());
            query.setInteger("referenceId", Integer.parseInt(note.getReferenceId()));

            query.setInteger("referenceTableId", Integer.parseInt(note.getReferenceTableId()));
            query.setParameter("param4", note.getText().toLowerCase().trim());
            query.setParameter("param5", note.getSubject().toLowerCase().trim());

            int noteId = !StringUtil.isNullorNill(note.getId()) ? Integer.parseInt(note.getId()) : 0;

            query.setInteger("noteId", noteId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateNoteExists()", e);
        }
    }

//
//	@Override
//	public List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject)
//			throws LIMSRuntimeException {
//		String sql = "FROM Note n where n.referenceId = :refId and n.referenceTableId = :tableId and  n.subject = :subject";
//
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setInteger("refId", Integer.parseInt(refId));
//			query.setInteger("tableId", Integer.parseInt(table_id));
//			query.setString("subject", subject);
//
//			List<Note> noteList = query.list();
//
//			// closeSession(); // CSL remove old
//
//			return noteList;
//		} catch (HibernateException e) {
//			handleException(e, "getNoteByRefIAndRefTableAndSubject");
//		}
//		return null;
//	}

//	@Override
//
//	public List<Note> getNotesChronologicallyByRefIdAndRefTable(String refId, String table_id)
//			throws LIMSRuntimeException {
//		String sql = "FROM Note n where n.referenceId = :refId and n.referenceTableId = :tableId order by n.lastupdated asc";
//
//		try {
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setInteger("refId", Integer.parseInt(refId));
//			query.setInteger("tableId", Integer.parseInt(table_id));
//
//			List<Note> noteList = query.list();
//
//			// closeSession(); // CSL remove old
//
//			return noteList;
//		} catch (HibernateException e) {
//			handleException(e, "getNotesChronologicallyByRefIdAndRefTable");
//		}
//		return null;
//	}

    @Override

    @Transactional(readOnly = true)
    public List<Note> getNotesChronologicallyByRefIdAndRefTableAndType(String objectId, String tableId,
            List<String> filter) throws LIMSRuntimeException {
        String sql = "FROM Note n where n.referenceId = :refId and n.referenceTableId = :tableId and n.noteType in ( :filter ) order by n.lastupdated asc";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("refId", Integer.parseInt(objectId));
            query.setInteger("tableId", Integer.parseInt(tableId));
            query.setParameterList("filter", filter);

            List<Note> noteList = query.list();

            // closeSession(); // CSL remove old

            return noteList;
        } catch (HibernateException e) {
            handleException(e, "getNotesChronologicallyByRefIdAndRefTable");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getNotesInDateRangeAndType(Date lowDate, Date highDate, String noteType, String referenceTableId)
            throws LIMSRuntimeException {
        String sql = "FROM Note n where n.noteType = :type and n.referenceTableId = :referenceTableId and n.lastupdated between :lowDate and :highDate";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("type", noteType);
            query.setInteger("referenceTableId", Integer.parseInt(referenceTableId));
            query.setDate("lowDate", lowDate);
            query.setDate("highDate", highDate);

            List<Note> noteList = query.list();

            // closeSession(); // CSL remove old

            return noteList;
        } catch (HibernateException e) {
            handleException(e, "getNotesInDateRangeAndType");
        }
        return null;
    }
}
