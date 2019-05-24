package spring.service.note;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import spring.mine.internationalization.MessageUtil;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.common.BaseObjectServiceImpl;
import spring.service.sample.SampleServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.StringUtil.EncodeContext;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

@Service
@DependsOn({ "springContext" })
public class NoteServiceImpl extends BaseObjectServiceImpl<Note> implements NoteService {

	public enum NoteType {
		EXTERNAL(Note.EXTERNAL), INTERNAL(Note.INTERNAL), REJECTION_REASON(Note.REJECT_REASON), NON_CONFORMITY(Note.NON_CONFORMITY);

		String DBCode;

		NoteType(String dbCode) {
			DBCode = dbCode;
		}

		public String getDBCode() {
			return DBCode;
		}
	}

	public enum BoundTo {
		ANALYSIS, QA_EVENT, SAMPLE, SAMPLE_ITEM
	}

	private static boolean SUPPORT_INTERNAL_EXTERNAL = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NOTE_EXTERNAL_ONLY_FOR_VALIDATION, "true");
	private static String SAMPLE_ITEM_TABLE_REFERENCE_ID;
	public static String TABLE_REFERENCE_ID;

	@Autowired
	private static NoteDAO noteDAO = SpringContext.getBean(NoteDAO.class);

	@Autowired
	private static SampleQaEventDAO sampleQADAO = SpringContext.getBean(SampleQaEventDAO.class);
	@Autowired
	private ReferenceTablesDAO refTableDAO = SpringContext.getBean(ReferenceTablesDAO.class);
	@Autowired
	static SystemUserDAO systemUserDAO = SpringContext.getBean(SystemUserDAO.class);

	private BoundTo binding;
	private String tableId;
	private String objectId;
	private Object object;

	public synchronized void initializeGlobalVariables() {
		TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName("NOTE").getId();
		SAMPLE_ITEM_TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName("SAMPLE_ITEM").getId();
	}

	NoteServiceImpl() {
		super(Note.class);
		initializeGlobalVariables();
	}

	public NoteServiceImpl(Analysis analysis) {
		this();
		tableId = AnalysisServiceImpl.TABLE_REFERENCE_ID;
		objectId = analysis.getId();
		binding = BoundTo.ANALYSIS;
		object = analysis;
	}

	public NoteServiceImpl(Sample sample) {
		this();
		tableId = SampleServiceImpl.TABLE_REFERENCE_ID;
		objectId = sample.getId();
		binding = BoundTo.SAMPLE;
		object = sample;
	}

	public NoteServiceImpl(SampleQaEvent sampleQaEvent) {
		this();
		tableId = QAService.TABLE_REFERENCE_ID;
		objectId = sampleQaEvent.getId();
		binding = BoundTo.QA_EVENT;
		object = sampleQaEvent;
	}

	public NoteServiceImpl(SampleItem sampleItem) {
		this();
		tableId = SAMPLE_ITEM_TABLE_REFERENCE_ID;
		objectId = sampleItem.getId();
		binding = BoundTo.SAMPLE_ITEM;
		object = sampleItem;
	}

	@Override
	protected NoteDAO getBaseObjectDAO() {
		return noteDAO;
	}

	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, NoteType[] filter, boolean excludeExternPrefix) {
		return getNotesAsString(prefixType, prefixTimestamp, noteSeparator, filter, excludeExternPrefix, EncodeContext.HTML);
	}

	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, NoteType[] filter, boolean excludeExternPrefix, EncodeContext context) {
		boolean includeNoneConformity = false;
		List<String> dbFilter = new ArrayList<>(filter.length);
		for (NoteType type : filter) {
			if (type == NoteType.NON_CONFORMITY) {
				includeNoneConformity = true;
			}

			dbFilter.add(type.getDBCode());

		}

		List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType(objectId, tableId, dbFilter);

		if (includeNoneConformity) {
			List<Note> nonConformityNoteList = getNonConformityReasons();
			if (!nonConformityNoteList.isEmpty()) {
				noteList.addAll(nonConformityNoteList);
				Collections.sort(noteList, new Comparator<Note>() {
					@Override
					public int compare(Note o1, Note o2) {
						return o1.getLastupdated().compareTo(o2.getLastupdated());
					}
				});
			}
		}

		return notesToString(prefixType, prefixTimestamp, noteSeparator, noteList, excludeExternPrefix, context);
	}

	private List<Note> getNonConformityReasons() {
		ArrayList<Note> notes = new ArrayList<>();

		if (binding == BoundTo.QA_EVENT) {
			return notes;
		}

		ArrayList<String> filter = new ArrayList<>(1);
		filter.add(NoteType.NON_CONFORMITY.getDBCode());
		Sample sample = null;
		SampleItem sampleItem = null;

		// get parent objects and the qa notes
		if (binding == BoundTo.ANALYSIS) {
			sampleItem = ((Analysis) object).getSampleItem();
			notes.addAll(noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType(sampleItem.getId(), SAMPLE_ITEM_TABLE_REFERENCE_ID, filter));

			sample = sampleItem.getSample();
			notes.addAll(noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType(sample.getId(), SampleServiceImpl.TABLE_REFERENCE_ID, filter));
		} else if (binding == BoundTo.SAMPLE_ITEM) {
			sampleItem = (SampleItem) object;
			sample = sampleItem.getSample();
			notes.addAll(noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType(sample.getId(), SampleServiceImpl.TABLE_REFERENCE_ID, filter));
		}

		if (sample != null) {
			List<SampleQaEvent> sampleQAList = sampleQADAO.getSampleQaEventsBySample(sample);
			for (SampleQaEvent event : sampleQAList) {
				if (sampleItem == null || event.getSampleItem() == null || (sampleItem.getId().equals(event.getSampleItem().getId()))) {
					notes.addAll(noteDAO.getNotesChronologicallyByRefIdAndRefTableAndType(event.getId(), QAService.TABLE_REFERENCE_ID, filter));
					Note proxyNote = new Note();
					proxyNote.setNoteType(Note.NON_CONFORMITY);
					proxyNote.setText(event.getQaEvent().getLocalizedName());
					proxyNote.setLastupdated(event.getLastupdated());
					notes.add(proxyNote);
				}
			}
		}

		return notes;
	}

	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, boolean excludeExternPrefix) {
		return getNotesAsString(prefixType, prefixTimestamp, noteSeparator, excludeExternPrefix, EncodeContext.HTML);
	}

	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, boolean excludeExternPrefix, EncodeContext context) {
		List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable(objectId, tableId);

		return notesToString(prefixType, prefixTimestamp, noteSeparator, noteList, excludeExternPrefix, context);
	}

	private String notesToString(boolean prefixType, boolean prefixTimestamp, String noteSeparator, List<Note> noteList, boolean excludeExternPrefix, EncodeContext context) {
		if (noteList.isEmpty()) {
			return null;
		}

		StringBuilder builder = new StringBuilder();

		for (Note note : noteList) {
			if (prefixType) {
				builder.append(StringUtil.encodeForContext(getNotePrefix(note, excludeExternPrefix), context));
				builder.append(" ");
			}

			if (prefixTimestamp) {
				builder.append(StringUtil.encodeForContext(getNoteTimestamp(note), context));
				builder.append(" ");
			}

			if (prefixType || prefixTimestamp) {
				builder.append(": ");
			}
			builder.append(StringUtil.encodeForContext(note.getText(), context));

			builder.append(StringUtil.blankIfNull(noteSeparator));
		}

		if (!GenericValidator.isBlankOrNull(noteSeparator)) {
			builder.setLength(builder.lastIndexOf(noteSeparator));
		}

		return builder.toString();
	}

	public String getNotesAsString(String prefix, String noteSeparator) {
		List<Note> noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable(objectId, tableId);

		if (noteList.isEmpty()) {
			return null;
		}

		StringBuilder builder = new StringBuilder();

		for (Note note : noteList) {
			builder.append(StringUtil.blankIfNull(prefix));
			builder.append(note.getText());
			builder.append(StringUtil.blankIfNull(noteSeparator));
		}

		if (!GenericValidator.isBlankOrNull(noteSeparator)) {
			builder.setLength(builder.lastIndexOf(noteSeparator));
		}

		return builder.toString();
	}

	public Note getMostRecentNoteFilteredBySubject(String filter) {
		List<Note> noteList;
		if (GenericValidator.isBlankOrNull(filter)) {
			noteList = noteDAO.getNotesChronologicallyByRefIdAndRefTable(objectId, tableId);
			if (!noteList.isEmpty()) {
				return noteList.get(noteList.size() - 1);
			}
		} else {
			noteList = noteDAO.getNoteByRefIAndRefTableAndSubject(objectId, tableId, filter);
			if (!noteList.isEmpty()) {
				return noteList.get(0);
			}
		}

		return null;
	}

	private String getNoteTimestamp(Note note) {
		return DateUtil.convertTimestampToStringDateAndTime(note.getLastupdated());
	}

	public Note createSavableNote(NoteType type, String text, String subject, String currentUserId) {
		if (GenericValidator.isBlankOrNull(text)) {
			return null;
		}

		Note note = new Note();
		note.setReferenceId(objectId);
		note.setReferenceTableId(tableId);
		note.setNoteType(type.getDBCode());
		note.setSubject(subject);
		note.setText(text);
		note.setSysUserId(currentUserId);
		note.setSystemUser(createSystemUser(currentUserId));

		return note;
	}

	public static SystemUser createSystemUser(String currentUserId) {
		SystemUser systemUser = new SystemUser();
		systemUser.setId(currentUserId);
		systemUserDAO.getData(systemUser);
		return systemUser;
	}

	public static String getReferenceTableIdForNoteBinding(BoundTo binding) {
		switch (binding) {
		case ANALYSIS: {
			return AnalysisServiceImpl.TABLE_REFERENCE_ID;
		}
		case QA_EVENT: {
			return QAService.TABLE_REFERENCE_ID;
		}
		case SAMPLE: {
			return SampleServiceImpl.TABLE_REFERENCE_ID;
		}
		case SAMPLE_ITEM: {
			return SAMPLE_ITEM_TABLE_REFERENCE_ID;
		}
		default: {
			return null;
		}
		}
	}

	public static List<Note> getTestNotesInDateRangeByType(Date lowDate, Date highDate, NoteType noteType) {
		return noteDAO.getNotesInDateRangeAndType(lowDate, DateUtil.addDaysToSQLDate(highDate, 1), noteType.DBCode, AnalysisServiceImpl.TABLE_REFERENCE_ID);
	}

	private String getNotePrefix(Note note, boolean excludeExternPrefix) {
		if (SUPPORT_INTERNAL_EXTERNAL) {
			if (Note.INTERNAL.equals(note.getNoteType())) {
				return MessageUtil.getMessage("note.type.internal");
			} else if (Note.EXTERNAL.equals(note.getNoteType())) {
				return excludeExternPrefix ? "" : MessageUtil.getMessage("note.type.external");
			} else if (Note.REJECT_REASON.equals(note.getNoteType())) {
				return MessageUtil.getMessage("note.type.rejectReason");
			} else if (Note.NON_CONFORMITY.equals(note.getNoteType())) {
				return MessageUtil.getMessage("note.type.nonConformity");
			}
		}

		return "";
	}

	@Override
	public void getData(Note note) {
        getBaseObjectDAO().getData(note);

	}

	@Override
	public Note getData(String noteId) {
        return getBaseObjectDAO().getData(noteId);
	}

	@Override
	public void deleteData(List notes) {
        getBaseObjectDAO().deleteData(notes);

	}

	@Override
	public void updateData(Note note) {
        getBaseObjectDAO().updateData(note);

	}

	@Override
	public boolean insertData(Note note) {
        return getBaseObjectDAO().insertData(note);
	}

	@Override
	public List<Note> getNotesByNoteTypeRefIdRefTable(Note note) {
        return getBaseObjectDAO().getNotesByNoteTypeRefIdRefTable(note);
	}

	@Override
	public List<Note> getNotesInDateRangeAndType(Date lowDate, Date highDate, String noteType, String referenceTableId) {
        return getBaseObjectDAO().getNotesInDateRangeAndType(lowDate,highDate,noteType,referenceTableId);
	}

	@Override
	public List getPreviousNoteRecord(String id) {
        return getBaseObjectDAO().getPreviousNoteRecord(id);
	}

	@Override
	public List getNextNoteRecord(String id) {
        return getBaseObjectDAO().getNextNoteRecord(id);
	}

	@Override
	public Integer getTotalNoteCount() {
        return getBaseObjectDAO().getTotalNoteCount();
	}

	@Override
	public List getAllNotesByRefIdRefTable(Note note) {
        return getBaseObjectDAO().getAllNotesByRefIdRefTable(note);
	}

	@Override
	public List<Note> getNotesChronologicallyByRefIdAndRefTable(String refId, String table_id) {
        return getBaseObjectDAO().getNotesChronologicallyByRefIdAndRefTable(refId,table_id);
	}

	@Override
	public List<Note> getNotesChronologicallyByRefIdAndRefTableAndType(String objectId, String tableId, List<String> filter) {
        return getBaseObjectDAO().getNotesChronologicallyByRefIdAndRefTableAndType(objectId,tableId,filter);
	}

	@Override
	public List<Note> getNoteByRefIAndRefTableAndSubject(String refId, String table_id, String subject) {
        return getBaseObjectDAO().getNoteByRefIAndRefTableAndSubject(refId,table_id,subject);
	}

	@Override
	public List getPageOfNotes(int startingRecNo) {
        return getBaseObjectDAO().getPageOfNotes(startingRecNo);
	}

	@Override
	public List<Note> getAllNotes() {
        return getBaseObjectDAO().getAllNotes();
	}
}
