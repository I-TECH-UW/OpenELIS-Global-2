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
*/
package us.mn.state.health.lims.note.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.note.valueholder.Note;

/**
* @author diane benz
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates. To enable and disable the creation of 
type
* comments go to Window>Preferences>Java>Code Generation.
*/
public interface NoteDAO extends BaseDAO {

	public boolean insertData(Note note) throws LIMSRuntimeException;
	public void deleteData(List notes) throws LIMSRuntimeException;
	public List<Note> getAllNotes() throws LIMSRuntimeException;
	public List getPageOfNotes(int startingRecNo) throws LIMSRuntimeException;
	public void getData(Note note) throws LIMSRuntimeException;
	public void updateData(Note note) throws LIMSRuntimeException;
	public List getNextNoteRecord(String id) throws LIMSRuntimeException;
	public List getPreviousNoteRecord(String id) throws LIMSRuntimeException;
	public Integer getTotalNoteCount() throws LIMSRuntimeException;
	public List getAllNotesByRefIdRefTable(Note note) throws LIMSRuntimeException;
	public List<Note> getNotesByNoteTypeRefIdRefTable(Note note) throws LIMSRuntimeException;
    public  List<Note> getNotesChronologicallyByRefIdAndRefTableAndType( String objectId, String tableId, List<String> filter ) throws  LIMSRuntimeException;
	public List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject) throws LIMSRuntimeException;
	public Note getData(String noteId) throws LIMSRuntimeException;
    public List<Note> getNotesChronologicallyByRefIdAndRefTable( String refId, String table_id ) throws LIMSRuntimeException;
    public List<Note> getNotesInDateRangeAndType( Date lowDate, Date highDate, String noteType, String referenceTableId ) throws LIMSRuntimeException;

}
