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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public class NoteService{
    private static NoteDAO noteDAO = new NoteDAOImpl();
    private static SampleQaEventDAO sampleQADAO = new SampleQaEventDAOImpl();
    private static boolean SUPPORT_INTERNAL_EXTERNAL = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NOTE_EXTERNAL_ONLY_FOR_VALIDATION,"true");

    public enum NoteType{
        EXTERNAL(Note.EXTERNAL),
        INTERNAL(Note.INTERNAL),
        REJECTION_REASON( Note.REJECT_REASON),
        NON_CONFORMITY(Note.NON_CONFORMITY);

        String DBCode;

        NoteType(String dbCode){
            DBCode = dbCode;
        }

        public String getDBCode(){
            return DBCode;
        }
    }

    public enum BoundTo{
        ANALYSIS, QA_EVENT, SAMPLE, SAMPLE_ITEM
    }

    private BoundTo binding;
    private final String tableId;
    private final String objectId;
    private final Object object;

    private static final String SAMPLE_ITEM_TABLE_REFERENCE_ID;
    public static final String TABLE_REFERENCE_ID;

    static{
        ReferenceTablesDAO refTableDAO = new ReferenceTablesDAOImpl();

        TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName("NOTE").getId();
        SAMPLE_ITEM_TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName( "SAMPLE_ITEM" ).getId();
    }

    public NoteService(Analysis analysis){
        tableId = AnalysisService.TABLE_REFERENCE_ID;
        objectId = analysis.getId();
        binding = BoundTo.ANALYSIS;
        object = analysis;
    }

    public NoteService(Sample sample){
        tableId = SampleService.TABLE_REFERENCE_ID;
        objectId = sample.getId();
        binding = BoundTo.SAMPLE;
        object = sample;
    }

    public NoteService(SampleQaEvent sampleQaEvent){
        tableId = QAService.TABLE_REFERENCE_ID;
        objectId = sampleQaEvent.getId();
        binding = BoundTo.QA_EVENT;
        object = sampleQaEvent;
    }

    public NoteService( SampleItem sampleItem){
        tableId = SAMPLE_ITEM_TABLE_REFERENCE_ID;
        objectId = sampleItem.getId();
        binding = BoundTo.SAMPLE_ITEM;
        object = sampleItem;
    }

    public String getNotesAsString( boolean prefixType, boolean prefixTimestamp, String noteSeparator, NoteType[] filter, boolean excludeExternPrefix  ){
        boolean includeNoneConformity = false;
        List<String> dbFilter = new ArrayList<String>( filter.length );
        for( NoteType type : filter){
            if( type == NoteType.NON_CONFORMITY){
                includeNoneConformity = true;
            }

            dbFilter.add( type.getDBCode() );

        }

        List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType( objectId, tableId, dbFilter );

        if( includeNoneConformity){
            List<Note> nonConformityNoteList = getNonConformityReasons();
            if( !nonConformityNoteList.isEmpty()){
                noteList.addAll( nonConformityNoteList );
                Collections.sort( noteList, new Comparator<Note>(){
                    @Override
                    public int compare( Note o1, Note o2 ){
                        return o1.getLastupdated().compareTo( o2.getLastupdated() );
                    }
                } );
            }
        }

        return notesToString( prefixType, prefixTimestamp, noteSeparator, noteList, excludeExternPrefix );
    }

    private List<Note> getNonConformityReasons(){
        ArrayList<Note> notes = new ArrayList<Note>(  );

        if( binding == BoundTo.QA_EVENT){
            return notes;
        }

        ArrayList<String> filter = new ArrayList<String>( 1 );
        filter.add( NoteType.NON_CONFORMITY.getDBCode() );
        Sample sample = null;
        SampleItem sampleItem = null;

        //get parent objects and the qa notes
        if( binding == BoundTo.ANALYSIS){
            sampleItem = ((Analysis)object).getSampleItem();
            notes.addAll( noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType( sampleItem.getId(), SAMPLE_ITEM_TABLE_REFERENCE_ID, filter) );

            sample = sampleItem.getSample();
            notes.addAll( noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType( sample.getId(), SampleService.TABLE_REFERENCE_ID, filter ) );
        }else if( binding == BoundTo.SAMPLE_ITEM ){
            sampleItem = (SampleItem)object;
            sample = sampleItem.getSample();
            notes.addAll( noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType( sample.getId(), SampleService.TABLE_REFERENCE_ID, filter ) );
        }


        if( sample != null){
            List<SampleQaEvent> sampleQAList = sampleQADAO.getSampleQaEventsBySample(sample);
            for( SampleQaEvent event : sampleQAList){
                if( sampleItem == null || event.getSampleItem() == null || ( sampleItem.getId().equals( event.getSampleItem().getId() ) ) ){
                    notes.addAll( noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType( event.getId(), QAService.TABLE_REFERENCE_ID, filter ) );
                    Note proxyNote = new Note();
                    proxyNote.setNoteType( Note.NON_CONFORMITY );
                    proxyNote.setText( event.getQaEvent().getLocalizedName() );
                    proxyNote.setLastupdated( event.getLastupdated() );
                    notes.add( proxyNote );
                }
            }
        }

        return notes;
    }

    public String getNotesAsString( boolean prefixType, boolean prefixTimestamp, String noteSeparator, boolean excludeExternPrefix  ){
            List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable( objectId, tableId );

            return notesToString( prefixType, prefixTimestamp, noteSeparator, noteList, excludeExternPrefix );
        }

    private String notesToString( boolean prefixType, boolean prefixTimestamp, String noteSeparator, List<Note> noteList, boolean excludeExternPrefix ){
        if(noteList.isEmpty()){
            return null;
        }

        StringBuilder builder = new StringBuilder();

        for( Note note : noteList ){
            if( prefixType ){
                builder.append( getNotePrefix( note, excludeExternPrefix ) );
                builder.append( " " );
            }

            if( prefixTimestamp ){
                builder.append( getNoteTimestamp( note ) );
                builder.append( " " );
            }

            if( prefixType || prefixTimestamp){
                builder.append( ": " );
            }
            builder.append( note.getText() );

            builder.append( StringUtil.blankIfNull( noteSeparator ) );
        }

        if(!GenericValidator.isBlankOrNull( noteSeparator )){
            builder.setLength(builder.lastIndexOf( noteSeparator ));
        }

        return builder.toString();
    }

    public String getNotesAsString( String prefix, String noteSeparator ){
        List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable( objectId, tableId );

        if(noteList.isEmpty()){
            return null;
        }

        StringBuilder builder = new StringBuilder();

        for( Note note : noteList ){
            builder.append( StringUtil.blankIfNull( prefix ) );
            builder.append( note.getText() );
            builder.append( StringUtil.blankIfNull( noteSeparator ) );
        }

        if( !GenericValidator.isBlankOrNull( noteSeparator )){
            builder.setLength(builder.lastIndexOf( noteSeparator ));
        }

        return builder.toString();
    }


    public Note getMostRecentNoteFilteredBySubject(String filter){
        List<Note> noteList;
        if( GenericValidator.isBlankOrNull( filter )){
            noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable( objectId, tableId );
            if(!noteList.isEmpty()){
                return noteList.get(noteList.size() - 1);
            }
        }else{
            noteList = noteDAO.getNoteByRefIAndRefTableAndSubject(objectId, tableId, filter );
            if(!noteList.isEmpty()){
                return noteList.get(0);
            }
        }

        return null;
    }
    private String getNoteTimestamp( Note note ){
        return DateUtil.convertTimestampToStringDateAndTime( note.getLastupdated() );
    }

    public Note createSavableNote( NoteType type, String text, String subject, String currentUserId){
        if( GenericValidator.isBlankOrNull( text )){
            return null;
        }

        Note note = new Note();
        note.setReferenceId( objectId );
        note.setReferenceTableId( tableId );
        note.setNoteType( type.getDBCode() );
        note.setSubject( subject );
        note.setText( text );
        note.setSysUserId( currentUserId );
        note.setSystemUser( createSystemUser(currentUserId) );

        return note;
    }

    public static SystemUser createSystemUser(String currentUserId) {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(currentUserId);
        SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
        systemUserDAO.getData(systemUser);
        return systemUser;
    }

    public static String getReferenceTableIdForNoteBinding( BoundTo binding){
        switch( binding ){
            case ANALYSIS:{
                return AnalysisService.TABLE_REFERENCE_ID;
            }
            case QA_EVENT:{
                return QAService.TABLE_REFERENCE_ID;
            }
            case SAMPLE:{
                return SampleService.TABLE_REFERENCE_ID;
            }
            case SAMPLE_ITEM:{
                return SAMPLE_ITEM_TABLE_REFERENCE_ID;
            }
            default:{
                return null;
            }
        }
    }

    public static List<Note> getTestNotesInDateRangeByType( Date lowDate, Date highDate, NoteType noteType ){
        return noteDAO.getNotesInDateRangeAndType( lowDate, DateUtil.addDaysToSQLDate(highDate, 1), noteType.DBCode, AnalysisService.TABLE_REFERENCE_ID);
    }
    private String getNotePrefix( Note note, boolean excludeExternPrefix ) {
        if(SUPPORT_INTERNAL_EXTERNAL){
            if( Note.INTERNAL.equals(note.getNoteType())){
                return StringUtil.getMessageForKey("note.type.internal");
            }else if( Note.EXTERNAL.equals(note.getNoteType())){
                return excludeExternPrefix ? "" : StringUtil.getMessageForKey("note.type.external");
            }else if( Note.REJECT_REASON.equals( note.getNoteType() )){
                return StringUtil.getMessageForKey( "note.type.rejectReason" );
            }else if(Note.NON_CONFORMITY.equals( note.getNoteType() )){
                return StringUtil.getMessageForKey( "note.type.nonConformity" );
            }
        }

        return "";
    }
}
