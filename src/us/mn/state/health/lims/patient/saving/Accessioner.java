/*
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

/**
 * C�te d'Ivoire
 * @author pahill
 * @since 2010-06-15
 **/
package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.sample.util.CI.ProjectForm.EID;
import static us.mn.state.health.lims.sample.util.CI.ProjectForm.SPECIAL_REQUEST;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Transaction;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.StatusSet;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.form.ProjectData;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper.TypeOfSampleTests;
import us.mn.state.health.lims.sample.util.CI.IProjectFormMapper;
import us.mn.state.health.lims.sample.util.CI.ProjectForm;
import us.mn.state.health.lims.sample.util.CI.ProjectFormMapperFactory;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.daoimpl.SampleProjectDAOImpl;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * Update/Creates, as needed, a Sample and Patient and all associated parts in
 * order to enter that patient in the system. To use one of these to accession,
 * you should:
 * <nl>
 * <li>Provide a constructor (which probably includes whatever structure comes
 * from your UI form, so it can be used below).
 * <li>set the expected new RecordStatus for the patient and sample (maybe in
 * the constructor), but you can change that later.
 * <li>implement canAccession() to decide if this is the right time to try this
 * accession algorithm.
 * <li>implement validate* and match* methods to make sure all the entities
 * created and found hang together.
 * <li>implement the missing populate* methods (including providing any empty
 * implementation).
 * <li>possibly override the persist* methods, if there is more to do than
 * simply saving what has been built (delete any old ones? Update instead?).
 * </nl>
 * Use:<br/>
 * if (myAccessioner1.canAccession()) { if (!myAccession1.accession(...)) {
 * errors = myAccession1.getMessages(); saveErrors(request, errors);
 * request.setAttribute(Globals.ERROR_KEY, errors); return
 * mapping.findForward(FWD_FAIL); } else { return
 * mapping.findForward(FWD_SUCCESS); } } else (myAccession2.canAccession() { ...
 * }
 * 
 * PAH 07/2010 This object is still a work in progress. For example, we have a
 * member for projectFormMapper, but all its use in the subclasses at this time
 * . Maybe the projectFormMapper should just be injected after creation? It is
 * also the case that there are form field property names listed in these
 * various acccesioning classes when the formFieldMapper class is really the
 * class that should know the right place to find values on the submitted form.
 * 
 * @author pahill
 */

public abstract class Accessioner {

	/**
	 * a set of possible analysis status that means an analysis is done
	 */
	private Set<String> analysisDone = new HashSet<String>();
	{
		analysisDone.add(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Finalized));
		analysisDone.add(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.NonConforming_depricated));
		analysisDone.add(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Canceled));
	}

	/**
	 * Sample or Patient Entry always mark the type of an analysis as a MANUAL
	 * type.
	 */
	private static final String DEFAULT_ANALYSIS_TYPE = IActionConstants.ANALYSIS_TYPE_MANUAL;
	// private static String OBSERVATION_HISTORY_YES_ID = null;
	private static String SAMPLE_TABLE_ID = null;

	static {
		SAMPLE_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("sample").getId();
//		OBSERVATION_HISTORY_YES_ID = new DictionaryDAOImpl().getDictionaryByDictEntry("Demographic Response Yes (in Yes or No)").getId();
	}

	/**
	 * Find the projectFormName where ever we normally store it. This is for
	 * that could which needs this information before creating a
	 * projectFormMapper
	 * 
	 * @param form
	 * @return the current projectFormName
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String findProjectFormName(DynaBean form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return ((ObservationData) (PropertyUtils.getProperty(form, "observations"))).getProjectFormName();
	}

	/**
	 * @param dynaBean
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws LIMSRuntimeException
	 */
	public static IProjectFormMapper getProjectFormMapper(DynaBean dynaBean) throws LIMSRuntimeException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return new ProjectFormMapperFactory().getProjectInitializer(findProjectFormName(dynaBean), (BaseActionForm) dynaBean);
	}

	/**
	 * @param dynaBean
	 * @return
	 */
	public static IProjectFormMapper getProjectFormMapper(String projectFormName, DynaBean dynaBean) {
		return new ProjectFormMapperFactory().getProjectInitializer(projectFormName, (BaseActionForm) dynaBean);
	}

	protected String accessionNumber;
	protected String patientIdentifier;
	protected String patientSiteSubjectNo;
	protected StatusSet statusSet;
	ActionMessages messages = new ActionMessages();

	protected java.sql.Date today;
	protected String todayAsText;
	protected String sysUserId;
	protected ProjectForm projectForm;

	protected Patient patientInDB;
	private List<PatientIdentity> patientIdentities = new ArrayList<PatientIdentity>();
	protected List<ObservationHistory> observationHistories = new ArrayList<ObservationHistory>();

	Map<String, List<ObservationHistory>> observationHistoryLists = null;

	protected Sample sample;
	protected List<SampleItemAnalysisCollection> sampleItemsAnalysis = new ArrayList<SampleItemAnalysisCollection>();
	protected List<SampleOrganization> sampleOrganizations = new ArrayList<SampleOrganization>();

	protected SampleHuman sampleHuman;
	protected SampleProject sampleProject;

	protected RecordStatus newPatientStatus;
	protected RecordStatus newSampleStatus;

	protected PatientDAO patientDAO = new PatientDAOImpl();
	protected PersonDAO personDAO = new PersonDAOImpl();
	protected PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
	protected SampleDAO sampleDAO = new SampleDAOImpl();
	protected SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	protected SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl();
	protected ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();
	protected ProjectDAO projectDAO = new ProjectDAOImpl();
	protected OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	protected SampleOrganizationDAO sampleOrganizationDAO = new SampleOrganizationDAOImpl();
	protected SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	protected TestDAO testDAO = new TestDAOImpl();
	protected AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private NoteDAO noteDAO = new NoteDAOImpl();

	protected boolean existingSample;
	protected boolean existingPatient;
	protected IProjectFormMapper projectFormMapper;

	protected Patient patientToDelete;
	protected List<Analysis> analysisToUpdate = new ArrayList<Analysis>();
	protected List<Analysis> analysisToDelete = new ArrayList<Analysis>();
	protected List<SampleItem> sampleItemsToDelete = new ArrayList<SampleItem>();
	private boolean aDifferentPatientRecord = false;
	private ProjectData projectData;

	protected Accessioner(String sampleIdentifier, String patientIdentifier, String siteSubjectNo, String sysUserId) {
		this();
		this.accessionNumber = sampleIdentifier;
		this.patientIdentifier = patientIdentifier;
		this.patientSiteSubjectNo = siteSubjectNo;
		this.sysUserId = sysUserId;
	}

	public Accessioner() {
		today = new java.sql.Date(System.currentTimeMillis());
		todayAsText = DateUtil.formatDateAsText(today);
	}

	/**
	 * Primary entry point for processing a patient/sample combination Check the
	 * messages, on true there may have been errors
	 * 
	 * @return TRUE => errors FALSE => did not do it, had a problem doing it.
	 * @throws Exception
	 * @throws Exception
	 */
	public String save() throws Exception {
		Transaction tx = null;
		try {
			if (!canAccession()) {
				return null;
			}
			existingPatient = findPatient();
			if (existingPatient && !validateFoundPatient()) {
				return IActionConstants.FWD_FAIL;
			}
			existingSample = findSample();
			if (existingSample && !validateFoundSample()) {
				return IActionConstants.FWD_FAIL;
			}
			if (!matchPatientAndSample()) {
				return IActionConstants.FWD_FAIL;
			}

			populatePatientData();
			populateSampleData();
			populateSampleHuman();
			populateObservationHistory();

			tx = HibernateUtil.getSession().beginTransaction();
			// all of the following methods are assumed to only write when
			// necessary
			persistPatient();
			persistIdentityTypes();

			persistSampleData();

			persistSampleHuman();

			persistObservationHistory();
	
		//	persistObservationHistoryLists();// no more running name has been changed to persistObservationHistoryLists2
			persistObservationHistoryLists2();
			persistRecordStatus();
			deleteOldPatient();
			populateAndPersistUnderInvestigationNote();
			tx.commit();
			return IActionConstants.FWD_SUCCESS;
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			logAndAddMessage("save()", "errors.InsertException", e);
			return IActionConstants.FWD_FAIL;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	private void populateAndPersistUnderInvestigationNote() {
		// N.B. The notes is being attached to the Sample table rather than the
		// observation history because the observation history table
		// is being updated by inserting new observation histories and then
		// deleting the old ones. Any references to the old observation history
		// under investigation row are being lost. Until we fix that the notes
		// will be attached to the sample with note type of "UnderInvestigation"
		if (//OBSERVATION_HISTORY_YES_ID.equals(observationData.getUnderInvestigation()) && <-- not sure of the business rules around this
		!GenericValidator.isBlankOrNull(projectData.getUnderInvestigationNote())) {

			Note note = new Note();
			note.setNoteType(Note.EXTERNAL);
			note.setReferenceId(sample.getId());
			note.setReferenceTableId(SAMPLE_TABLE_ID);

			List<Note> noteList = noteDAO.getNotesByNoteTypeRefIdRefTable(note);

			if (noteList != null && !noteList.isEmpty()) {
				note = noteList.get(0);
				note.setText(note.getText() + "<br/>" + getActionLabel() + ": " + projectData.getUnderInvestigationNote());
			} else {
				note.setText(getActionLabel() + ": " + projectData.getUnderInvestigationNote());
				note.setSubject("UnderInvestigation");
			}

			note.setSysUserId(sysUserId);
			note.setSystemUser( NoteService.createSystemUser( sysUserId ));

			if (note.getId() == null) {
				noteDAO.insertData(note);
			} else {
				noteDAO.updateData(note);
			}
		}
	}

	protected void persistSampleData() throws Exception {
		persistSample();
		persistSampleProject();
		persistSampleOrganization();
		persisteSampleItemsChanged();
		persistSampleItemsAndAnalysis();
	}

	/**
	 * This is probably the result of Sample(Second)Entry, so nothing to due by
	 * default
	 */
	private void persisteSampleItemsChanged() {
		analysisDAO.deleteData(analysisToDelete);
		sampleItemDAO.deleteData(sampleItemsToDelete);
		for (Analysis analysis : this.analysisToUpdate) {
			analysisDAO.updateData(analysis);
		}
	}

	/**
	 * This method is called when this object contains a patient from the
	 * database found by ID. Sometimes we need to validate some of the fields
	 * with the values from the input. Place an error in the messages list to
	 * return an error.
	 * 
	 * @return TRUE => all is well with the patient we have found in the
	 *         database vs. the data coming from the input.
	 */
	protected boolean validateFoundPatient() {
		return true;
	}

	/**
	 * This method is called when this object contains a sample from the
	 * database found by ID. Sometimes we need to validate some of the fields
	 * with the values from the input. Place an error in the messages list to
	 * return an error.
	 * 
	 * @return TRUE => all is well with the SAMPLE we have found in the database
	 *         vs. the data coming from the input.
	 */
	protected boolean validateFoundSample() {
		return true;
	}

	/**
	 * This method is called when there is something to check to make sure the
	 * patient and sample go together correctly.
	 * 
	 * @return TRUE => all looks good.
	 */
	protected boolean matchPatientAndSample() {
		return true;
	}

	/**
	 * Use appropriate means to come up with a patient and its person record.
	 * 
	 * @return TRUE => patient was found in the database, FALSE => patient not
	 *         found, simply created.
	 */
	protected boolean findPatient() {
		String patientId = this.statusSet.getPatientId();
		String sampleId = this.statusSet.getSampleId();
		String unknownPatient = PatientUtil.getUnknownPatient().getId();
		if (sampleId == null) {
			// no sample => nothing to leverage so build up from what we have
			return createPatientByIdentifiers();
		}
		if (patientId.equals(unknownPatient)) {
			// the UNKNOWN patient, so we'll create a new one
			setADifferentPatient(true);
			return createNewPatient();
		}
		// the patient is already associated with the existing sample and its
		// not the unknown one
		patientInDB = patientDAO.readPatient(patientId);
		if (patientIdentifiersDoNotMatch()) {
			Patient otherPatient = findPatientByIndentifiers();
			if (otherPatient == null) { // unknown
				int otherSamplesOnCurrentPatient = sampleHumanDAO.getSamplesForPatient(patientId).size() - 1;
				if (otherSamplesOnCurrentPatient == 0) {
					// stick with the existing patient of the sample, update it
					return true;
				} else {
					// it's a new patient, so we might have to move some old
					// pointers around.
					setADifferentPatient(true);
					return createNewPatient();
				}
			} else {
				// it's another existing patient, so use that and don't forget
				// we're doing that.
				patientInDB = otherPatient;
				setADifferentPatient(false);
				return true;
			}
		}
		return true;
	}

	private boolean patientIdentifiersDoNotMatch() {

		String existingSubjectNo = StringUtil.replaceNullWithEmptyString(patientInDB.getNationalId()).trim();

		if (!GenericValidator.isBlankOrNull(this.patientIdentifier) && this.patientIdentifier.equals(existingSubjectNo)) {
			return false;
		}

		String existingSiteSubjectNo = StringUtil.replaceNullWithEmptyString(patientInDB.getExternalId()).trim();

		return !(!GenericValidator.isBlankOrNull(this.patientSiteSubjectNo) && this.patientSiteSubjectNo.equals(existingSiteSubjectNo));
	}

	/**
	 * Either find it by the primary or secondary identifier or return a new one
	 */
	private boolean createPatientByIdentifiers() {
		patientInDB = findPatientByIndentifiers();
		return patientInDB != null || createNewPatient();
	}

	private Patient findPatientByIndentifiers() {
		Patient aPatient;
		if (this.patientIdentifier != "") {
			aPatient = patientDAO.getPatientByNationalId(this.patientIdentifier);
		} else {
			String externalId = this.projectFormMapper.getSiteSubjectNumber();
			aPatient = patientDAO.getPatientByExternalId(externalId);
		}
		return aPatient;
	}

	/**
	 * @return FALSE since it doesn't already exist
	 */
	protected boolean createNewPatient() {
		patientInDB = new Patient();
		patientInDB.setPerson(new Person());
		return false;
	}

	/**
	 * 
	 * @return true = existing sample
	 * @throws LIMSRuntimeException
	 * @throws LIMSInvalidConfigurationException
	 */
	protected boolean findSample() throws LIMSRuntimeException, LIMSInvalidConfigurationException {
		sample = sampleDAO.getSampleByAccessionNumber(accessionNumber);
		String sampleId = this.statusSet.getSampleId();
		// if there is sample for the given acc. number is an existing sample it
		// had better be same one we found when we loading the sampleSet
		if (sample != null && !isNewSample()) {
			if (!sample.getId().equals(sampleId)) {
				messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.may_not_reuse_accession_number", accessionNumber));
				throw new RuntimeException("You can not re-use an existing accessionNumber " + accessionNumber);
			}
			return true;
		} else {
			// the accession number is unknown, so load/build the sample using
			// the sampleStatus ID
			sample = new Sample();
			if (sampleId == null) {
				return false; // it brand new, no existing accessionNumber in
								// use, not known sampleId provided
			} else {
				sample = knownSampleTemplate();
				sampleDAO.getData(sample);
				sample.setAccessionNumber(accessionNumber);
				return true; // it existed previously, but we have a new
								// (edited) accesionNumber
			}
		}
	}

	private Sample knownSampleTemplate() {
		Sample sample = new Sample();
		sample.setId(this.statusSet.getSampleId());
		return sample;
	}

	/**
	 * @return if the sample we are working with is NOT in the database this
	 *         object is about to create it.
	 */
	protected boolean isNewSample() {
		return sample.getId() == null;
	}

	/**
	 * @return TRUE if the patient is not the known patient already associated
	 *         with the known sample.
	 */
	protected boolean isADifferentPatient() {
		return this.aDifferentPatientRecord;
	}

	/**
	 * Call this to record that when we started, the patient record associated
	 * with the original sample record is NOT the record we are now working
	 * with. We record this because, once we updating records to write, we can
	 * longer compare IDs to come up with the answer.
	 */
	protected void setADifferentPatient(boolean force) {
		//TODO 'force' was added as a safety because aDifferentPatientRecourd was not being set to true when it should
		// have.  The original test for aDifferentPatientRecord may still be needed, in the two places force is set
		// to true
		aDifferentPatientRecord = (force || patientInDB == null || statusSet.getPatientId() != patientInDB.getId());
	}

	/**
	 * Test this object to see if we should even begin. This is intended for
	 * checking existing patient/sample record status.
	 * 
	 * @return TRUE => all is well; FALSE (Default) => this particular version
	 *         of the accession process is not appropriate for the status
	 *         combination.
	 */
	abstract public boolean canAccession();

	/**
	 * load up this object with any new observation history records, including
	 * lists
	 */
	protected void populateObservationHistory() {
		projectFormMapper.getDynaBean();
		ObservationData observationData = (ObservationData) (projectFormMapper.getDynaBean().get("observations"));
		this.observationHistories = projectFormMapper.readObservationHistories(observationData);
		this.observationHistoryLists = projectFormMapper.readObservationHistoryLists(observationData);
	}

	public StatusSet findStatusSet() {
		if (statusSet == null) {
			String sampleId = projectFormMapper.getSampleId();
			if (GenericValidator.isBlankOrNull(sampleId)) {
				statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);
			} else {
				statusSet = StatusService.getInstance().getStatusSetForSampleId(sampleId);
			}
		}
		return statusSet;
	}

	/**
	 * Move data from the form to the sample and sample related objects, include
	 * SampleOrganization but not to any of the entities which tie a patient to
	 * a sample; don't include ObservationHistory and SampleHuman
	 * 
	 * @throws Exception
	 *             if things go wrong.
	 */
	abstract protected void populateSampleData() throws Exception;

	/**
	 * Create any appropriate sample human entity
	 * 
	 * @throws Exception
	 */
	protected void populateSampleHuman() throws Exception {
		if (isNewSample()) {
			sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Entered));
			sampleHuman = new SampleHuman();
		} else {
			if (isNewPatient() || isADifferentPatient()) {
				sampleHuman = new SampleHuman();
				sampleHuman.setSampleId(sample.getId());
				sampleHumanDAO.getDataBySample(sampleHuman);
			}
		}
		if (isADifferentPatient()) {
			int size = sampleHumanDAO.getSamplesForPatient(statusSet.getPatientId()).size();
			if (size == 1) {
				// if there is only one sample on the OLD patient of this sample
				// we can delete it.
				patientToDelete = knownPatientTemplate();
				patientDAO.getData(patientToDelete);
			}
		}
	}

	private boolean isNewPatient() {
		return patientInDB.getId() == null;
	}

	/**
	 * Build an example Patient record with the ID only for the current patient
	 * of the sample
	 * 
	 * @return
	 */
	private Patient knownPatientTemplate() {
		Patient patient = new Patient();
		patient.setId(statusSet.getPatientId());
		return patient;
	}

	protected void populateSample(Timestamp receivedDateForDisplay, Timestamp collectionDateForDisplay) throws Exception {
		sample.setAccessionNumber(accessionNumber);		
		sample.setReceivedTimestamp(receivedDateForDisplay);		
		sample.setCollectionDate(collectionDateForDisplay);

		// and all the administration fields.
		if (isNewSample()) {
			sample.setEnteredDateForDisplay(todayAsText);
			sample.setEnteredDate(today);
		}
		sample.setDomain(SystemConfiguration.getInstance().getHumanDomain());
	}

	protected void populateSampleProject() {
		if (!isSampleInProject()) {
			Project project = projectForm.getProject();
			sampleProject = new SampleProject();
			sampleProject.setProject(project);
		}
	}

	/**
	 * @return TRUE only if the sample is already associated with a project.
	 */
	private boolean isSampleInProject() {
		if (isNewSample()) {
			return false;
		}
		SampleProject oldSampleProject = sampleProjectDAO.getSampleProjectBySampleId(this.sample.getId());
		return oldSampleProject != null;
	}

	/**
	 * create a sampleOrganization record, finding any old one to delete. While
	 * there can be more than one organization per sample, this code only
	 * supports one.
	 * 
	 * @param organizationId
	 *            if none null, use it; otherwise do nothing
	 */
	protected void populateSampleOrganization(String organizationId) {
		if (GenericValidator.isBlankOrNull(organizationId) || organizationId.equals(BaseProjectFormMapper.ORGANIZATION_ID_NONE)) {
			return;
		}
	
		Organization org = new Organization();
		org.setId(organizationId);
		organizationDAO.getData(org);

		if (null == org.getId()) {
			throw new LIMSRuntimeException("Undefined organization name = " + organizationId
					+ ". Unable to create sample to organization mapping.");
		}

		SampleOrganization oldSampleOrg;
		if (!isNewSample()) {
			oldSampleOrg = new SampleOrganization();
			oldSampleOrg.setSample(sample);
			sampleOrganizationDAO.getDataBySample(oldSampleOrg);
			if (oldSampleOrg.getOrganization() != null) { // there may not be an
															// organiztion
															// attached yet
				String oldOrganizationId = oldSampleOrg.getOrganization().getId();
				if (oldOrganizationId.equals(organizationId)) {
					return; // we have an existing sample with the right
							// organization already, so don't bother with delete
							// or insert
				}
				oldSampleOrg.setSysUserId(sysUserId);
				oldSampleOrg.setOrganization(org);
				sampleOrganizations.add(oldSampleOrg);
			}
		}
		SampleOrganization so = new SampleOrganization();
		so.setOrganization(org);
		// sampleOrganization.setSampleOrganizationType("?"); // nothing
		// important
		so.setSysUserId(sysUserId);
		sampleOrganizations.add(so);
	}

	/**
	 * Add to the list of patient identities which will be saved.
	 * 
	 * @param patientIdentityTypeName
	 * @param paramValue
	 *            a value for a particular patient identity; if null, do
	 *            nothing.
	 */
	protected void addPatientIdentity(String patientIdentityTypeName, String paramValue) {
		if (paramValue == null) {
			return;
		}
		String typeID = PatientIdentityTypeMap.getInstance().getIDForType(patientIdentityTypeName);
		PatientIdentity identity = new PatientIdentity();
		identity.setPatientId(patientInDB.getId());
		identity.setIdentityTypeId(typeID);
		identity.setIdentityData(paramValue);
		patientIdentities.add(identity);
	}

	/**
	 * Assume all the fields on the dynaForm have the right names (see code) and
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected void populatePatientData() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DynaBean dynaForm = projectFormMapper.getDynaBean();
		Timestamp lastupdated1 = patientInDB.getLastupdated();
		PropertyUtils.copyProperties(patientInDB, dynaForm);
		patientInDB.setLastupdated(lastupdated1);
		Person person = patientInDB.getPerson();
		PropertyUtils.copyProperties(person, dynaForm);

		patientInDB.setNationalId(convertEmptyToNull((String) dynaForm.get("subjectNumber")));
		patientInDB.setExternalId(convertEmptyToNull((String) dynaForm.get("siteSubjectNumber")));
		populatePatientBirthDate((String) dynaForm.get("birthDateForDisplay"));

		projectData = (ProjectData) dynaForm.get("ProjectData");
		if (projectData != null) {
			person.setHomePhone(convertEmptyToNull(projectData.getPhoneNumber()));
			person.setFax(convertEmptyToNull(projectData.getFaxNumber()));
			person.setEmail(convertEmptyToNull(projectData.getEmail()));
			person.setStreetAddress(convertEmptyToNull(projectData.getAddress()));
		}
	}

	/**
	 * @param value
	 *            - string to test
	 * @return if the string is null or empty "", then return null
	 */
	private String convertEmptyToNull(String value) {
		return (GenericValidator.isBlankOrNull(value)) ? null : value;
	}

	/**
	 * Given a possibly ambiguous birthday date string, get the right values
	 * into both (1) the patient birthdate and (2) make sure we create a
	 * patient_identity for any ambiguous value.
	 */
	protected void populatePatientBirthDate(String birthDateForDisplay) {
		patientInDB.setBirthDateForDisplay(birthDateForDisplay);
	}

	protected void populateSampleItems(List<TypeOfSampleTests> typeofSampleTestList, Timestamp collectionDate) throws Exception {
		sampleItemsAnalysis = new ArrayList<SampleItemAnalysisCollection>();

		if (typeofSampleTestList.size() == 0) {
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.no.tests"));
			throw new Exception("No tests selected.");
		}

		for (TypeOfSampleTests typeofSampleTest : typeofSampleTestList) {
			TypeOfSample toSample = typeofSampleTest.typeOfSample;
			List<Test> tests = typeofSampleTest.tests;
			SampleItem item = buildSampleItem(sample, toSample, collectionDate);

			sampleItemsAnalysis.add(new SampleItemAnalysisCollection(item, tests));
		}
	}

	protected SampleItem buildSampleItem(Sample sample, TypeOfSample typeofsample, Timestamp collectionDate) {
		SampleItem item = new SampleItem();
		item.setTypeOfSample(typeofsample);
		item.setSortOrder(Integer.toString(0));
		item.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));
		item.setCollectionDate(collectionDate);
		return item;
	}

	protected final class SampleItemAnalysisCollection {
		public SampleItem item;
		public List<Test> tests;
		public String collectionDate;

		public SampleItemAnalysisCollection(SampleItem item, List<Test> tests) throws Exception {
			// Currently we allow a sampleItem w/o any tests requested,
			// elsewhere we check that at least one test is ordered somewhere.
			// if (tests.size() == 0) {
			// messages.add(ActionErrors.GLOBAL_MESSAGE, new
			// ActionError("errors.samples.with.no.tests"));
			// throw new
			// Exception("A least one sample was defined without any corresponding test selected.");
			// }
			this.item = item;
			this.tests = tests;
		}
	}

	/**
	 * Delete any new ones and remove any old ones.
	 */
	protected void persistSampleOrganization() {
		for (SampleOrganization so : sampleOrganizations) {
			sampleDAO.getData(sample);
			so.setSample(sample);
			if( so.getId() == null){
				sampleOrganizationDAO.insertData(so);
			}else{
				sampleOrganizationDAO.updateData(so);
			}
		}
	}

	protected void persistSampleItemsAndAnalysis() throws Exception {
		if (0 == sampleItemsAnalysis.size()) {
			return;
		}

		// Find all the already created sample items for this sample
		Map<String, SampleItem> itemsByType = findExistingSampleTypeItems();
		int nextSortOrder = calcLastSortOrder(itemsByType) + 1;

		String analysisRevision = SystemConfiguration.getInstance().getAnalysisDefaultRevision();
		boolean newAnalysis = false;
		for (SampleItemAnalysisCollection sampleTestPair : sampleItemsAnalysis) {

			// create new or find existing sample item for testing.
			SampleItem item = sampleTestPair.item;
			SampleItem existingSampleItem = itemsByType.get(item.getTypeOfSampleId());
			if (existingSampleItem == null) {
				item.setSample(sample);
				item.setSortOrder(Integer.toString(nextSortOrder++));
				item.setSysUserId(sysUserId);

				sampleItemDAO.insertData(item);
			} else {
				sampleTestPair.item = item = existingSampleItem;
			}

			List<String> existingTests = findDefinedTestsForItem(item);
			// insert any missing analysis
			for (Test newTest : sampleTestPair.tests) {
				testDAO.getData(newTest);
				if (!existingTests.contains(newTest.getId())) {
					Analysis analysis = buildAnalysis(analysisRevision, sampleTestPair, newTest);
					analysisDAO.insertData(analysis, false);
					newAnalysis = true;
				}
			}
		}
		// if we didn't create any analysis, we need to check if the sample is
		// completely done.
		if (!newAnalysis) {
			completeSample();
		}
	}

	/**
	 * @param itemsByType
	 * @return the maximum value which has already been used in the set of
	 *         SampleItems
	 */
	private int calcLastSortOrder(Map<String, SampleItem> itemsByType) {
		int max = 0;
		for (Entry<String, SampleItem> entry : itemsByType.entrySet()) {
			SampleItem sampleItem = entry.getValue();
			int curr = Integer.valueOf(sampleItem.getSortOrder());
			if (max < curr) {
				max = curr;
			}
		}
		return max;
	}

	/**
	 * Find all tests of the item which have had analysis already ordered
	 * (defined).
	 * 
	 * @param item
	 * @return a list of test IDs
	 */
	private List<String> findDefinedTestsForItem(SampleItem item) {
		List<Analysis> analysesForItem = analysisDAO.getAnalysesBySampleItem(item);
		List<String> testIds = new ArrayList<String>();
		for (Analysis analysis : analysesForItem) {
			Test test = analysis.getTest();
			testIds.add(test.getId());
		}
		return testIds;
	}

	private Map<String, SampleItem> findExistingSampleTypeItems() {
		List<SampleItem> itemsForSample = sampleItemDAO.getSampleItemsBySampleId(sample.getId());
		Map<String, SampleItem> itemsByType = new HashMap<String, SampleItem>();
		for (SampleItem sampleItem : itemsForSample) {
			String id = sampleItem.getTypeOfSampleId();
			itemsByType.put(id, sampleItem);
		}
		return itemsByType;
	}

	/**
	 * If during entry we find (1) all the analysis are finished, and (2) the
	 * sample is not already been declared bad, then we're ready to mark the
	 * sample as done.
	 * 
	 * @throws Exception
	 */
	public void completeSample() throws Exception {
		if (isAllAnalysisDone() && !StatusService.getInstance().getStatusID(OrderStatus.NonConforming_depricated).equals(sample.getStatus())) {
			sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Finished));
			sample.setSysUserId(sysUserId);
			sampleDAO.updateData(sample);
		}
	}

	/**
	 * The question is whether we are ready to update the sample status. TODO
	 * Pahill maybe we could move this to StatusService.getInstance()?
	 */
	private boolean isAllAnalysisDone() {
		List<Analysis> analyses = analysisDAO.getAnalysesBySampleId(sample.getId());
		for (Analysis analysis : analyses) {
			if (!analysisDone.contains(analysis.getStatusId())) {
				return false;
			}
		}
		return true;
	}

	private Analysis buildAnalysis(String analysisRevision, SampleItemAnalysisCollection sampleTestCollection, Test test) {
		java.sql.Date collectionDateTime = DateUtil.convertStringDateToSqlDate(sampleTestCollection.collectionDate);

		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setSampleItem(sampleTestCollection.item);
		analysis.setRevision(analysisRevision);
		analysis.setStartedDate(collectionDateTime);
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
		analysis.setTestSection(test.getTestSection());
		analysis.setSysUserId(sysUserId);
		return analysis;
	}

	/**
	 * save patient
	 */
	protected void persistPatient() {
		if (patientInDB != null) {
			Person person = patientInDB.getPerson();
			person.setSysUserId(sysUserId);
			patientInDB.setSysUserId(sysUserId);
			if (patientInDB.getId() == null) {
				personDAO.insertData(person);
				patientDAO.insertData(patientInDB);
			} else {
				// The reason for the explicit person update is to capture the
				// history.
				personDAO.updateData(person);
				patientDAO.updateData(patientInDB);
			}
		}
	}

	/**
	 * Save whatever patient identities have been created.
	 * 
	 * @throws LIMSRuntimeException
	 */
	public void persistIdentityTypes() throws LIMSRuntimeException {

		if (0 == patientIdentities.size()) {
			return;
		}
		for (PatientIdentity identity : patientIdentities) {
			identity.setPatientId(patientInDB.getId());
			identity.setSysUserId(sysUserId);
			identityDAO.insertData(identity);
		}
	}

	protected void persistSample() throws LIMSRuntimeException, Exception {
		if (null != sample) {
			sample.setSysUserId(sysUserId);
			if (sample.getId() != null) {
				sampleDAO.updateData(sample);
				HibernateUtil.getSession().evict(sample);
			} else {
				sampleDAO.insertDataWithAccessionNumber(sample);
			}
		}
	}

	protected void persistSampleProject() throws LIMSRuntimeException {
		if (null != sampleProject) {
			sampleProject.setSample(sample);
			sampleProject.setSysUserId(sysUserId);
			sampleProjectDAO.insertData(sampleProject);
		}
	}

	/**
	 * Persist any old or new sampleHuman we're currently holding.
	 */
	protected void persistSampleHuman() {
		if (sampleHuman != null) {
			sampleHuman.setPatientId(patientInDB.getId());
			sampleHuman.setSampleId(sample.getId());
			// we do not store any doctor name as a provider in SampleHuman
			sampleHuman.setSysUserId(sysUserId);
			if (null == sampleHuman.getId()) {
				sampleHumanDAO.insertData(sampleHuman);
			} else {
				sampleHumanDAO.updateData(sampleHuman);
			}
		}
	}

	/**
	 * Persist any simple observations histories in this accession object.
	 */
	protected void persistObservationHistory() {
		if (isADifferentPatient()) {
			List<ObservationHistory> oldOHes = observationHistoryDAO.getAll(knownPatientTemplate(), knownSampleTemplate());
			for (ObservationHistory oldOh : oldOHes) {
				oldOh.setSampleId(sample.getId());
				oldOh.setPatientId(patientInDB.getId());
				oldOh.setSysUserId(sysUserId);
				observationHistoryDAO.updateData(oldOh);
			}
		}

		for (ObservationHistory newOh : observationHistories) {
			List<ObservationHistory> existingTypeOHes = observationHistoryDAO.getAll(patientInDB, sample, newOh.getObservationHistoryTypeId());
			List<ObservationHistory> deleteTypeOHes = new ArrayList<ObservationHistory>();
			// delete any matching old ones, but only when there are both same
			// Ob. History type AND the same value type. Why because the
			// database allows more than one of the same type,
			// and we store e.g. nationality twice as two types, D=dictionary
			// for dropdown value, L=value used when there the menu value is
			// other, and there is a literal with text for other.
			String newValueType = newOh.getValueType();
			for (ObservationHistory oh : existingTypeOHes) {
				if (oh.getValueType().equals(newValueType)) {
					oh.setSysUserId(sysUserId);
					deleteTypeOHes.add(oh);
				}
			}
			if (deleteTypeOHes.size() > 0) {
				observationHistoryDAO.delete(deleteTypeOHes);
			}

			newOh.setSampleId(sample.getId());
			newOh.setPatientId(patientInDB.getId());
			newOh.setSysUserId(sysUserId);
			observationHistoryDAO.insertData(newOh);
		}
	}

	//Note -- this version does not do the delete insert model of updates but we are putting off
	// rolling it out because of the cost of testing
/*	protected void persistObservationHistory() {
		if (isADifferentPatient()) {
			List<ObservationHistory> oldOHes = observationHistoryDAO.getAll(knownPatientTemplate(), knownSampleTemplate());
			for (ObservationHistory oldOh : oldOHes) {
				oldOh.setSampleId(sample.getId());
				oldOh.setPatientId(patientInDB.getId());
				oldOh.setSysUserId(sysUserId);
				observationHistoryDAO.updateData(oldOh);
			}
		}

		for (ObservationHistory newOh : observationHistories) {
			boolean machedInDB = false;
			List<ObservationHistory> existingTypeOHes = observationHistoryDAO.getAll(patientInDB, sample,
					newOh.getObservationHistoryTypeId());
			List<ObservationHistory> deleteTypeOHes = new ArrayList<ObservationHistory>();
			// update any matching old ones, but only when there are both same
			// Ob. History type AND the same value type. Why because the
			// database allows more than one of the same type,
			// and we store e.g. nationality twice as two types, D=dictionary
			// for dropdown value, L=value used when there the menu value is
			// other, and there is a literal with text for other.
			String newValueType = newOh.getValueType();
			for (ObservationHistory oh : existingTypeOHes) {
				if (oh.getValueType().equals(newValueType)) {
					machedInDB = true;
					if (oh.getValue() != null && !oh.getValue().equals(newOh.getValue())) {
						oh.setSysUserId(sysUserId);
						oh.setValue(newOh.getValue());
						observationHistoryDAO.updateData(oh);

					}
				}
			}
			// if (deleteTypeOHes.size() > 0) {
			// observationHistoryDAO.delete(deleteTypeOHes);
			// }

			if (!machedInDB) {
				newOh.setSampleId(sample.getId());
				newOh.setPatientId(patientInDB.getId());
				newOh.setSysUserId(sysUserId);
				observationHistoryDAO.insertData(newOh);
			}
		}
	}
*/
	/**
     *
     */
	protected void persistObservationHistoryLists() {
		System.out.println("FUNCTION NAME PROHIBITED !");
	}
	protected void persistObservationHistoryLists2() {
		if (observationHistoryLists == null) {
			return;
		}
		
		for (String listType : this.observationHistoryLists.keySet()) {
			// throw away the old list
			Map<String, ObservationHistory> oldOHes = findExistingObservationHistories(listType);
			// System.out.println();
			// System.out.println(listType + " oldOHes.size = "
			// +oldOHes.size());
			for (ObservationHistory oh : oldOHes.values()) {
				oh.setSysUserId(sysUserId);
			}
			observationHistoryDAO.delete(new ArrayList<ObservationHistory>(oldOHes.values()));

			// insert the new
			List<ObservationHistory> newOHes = observationHistoryLists.get(listType);
			// System.out.println(listType + " newOHes.size = "
			// +newOHes.size());
			for (ObservationHistory newOH : newOHes) {
				newOH.setSysUserId(sysUserId);
				newOH.setPatientId(patientInDB.getId());
				newOH.setSampleId(sample.getId());
	               
				observationHistoryDAO.insertData(newOH);
			}
		}
	}

	private Map<String, ObservationHistory> findExistingObservationHistories(String nameKey) {
		Map<String, ObservationHistory> existing = new HashMap<String, ObservationHistory>();

		String ohTypeId = ObservationHistoryTypeMap.getInstance().getIDForType(nameKey);
		List<ObservationHistory> existingOHes = observationHistoryDAO.getAll(patientInDB, sample, ohTypeId);
		for (ObservationHistory oh : existingOHes) {
			existing.put(oh.getValue(), oh);
		}
		return existing;
	}
	protected void persistRecordStatus() {
		// Special Request and EID don't have a patient entry form, so we move
		// the patient record status when we move the sample record status.
		if (projectForm == SPECIAL_REQUEST || projectForm == EID) {
			newPatientStatus = newSampleStatus;
		}
		StatusService.getInstance().persistRecordStatusForSample(sample, newSampleStatus, patientInDB, newPatientStatus, sysUserId);
	}

	protected void deleteOldPatient() {
		if (patientToDelete != null) {
			List<PatientIdentity> oldIdentities = identityDAO.getPatientIdentitiesForPatient(patientToDelete.getId());
			for (PatientIdentity listIdentity : oldIdentities) {
				identityDAO.delete(listIdentity.getId(), sysUserId);
			}
			Person personToDelete = patientToDelete.getPerson();
			patientToDelete.setSysUserId(sysUserId);
			patientDAO.deleteData(Arrays.asList(patientToDelete));
			personToDelete.setSysUserId(sysUserId);
			personDAO.deleteData(Arrays.asList(personToDelete));
		}
	}

	protected List<ObservationHistory> getObservationHistories() {
		return observationHistories;
	}

	protected SampleDAO getSimpleSampleDAO() {
		return new SampleDAOImpl();
	}

	public ActionMessages getMessages() {
		return messages;
	}

	public void setMessages(ActionMessages messages) {
		this.messages = messages;
	}

	/****
	 * Record a thrown exception
	 * 
	 * @param methodName
	 *            where it happened.
	 * @param messageKey
	 *            default message if there is not already a error recorded.
	 * @param e
	 *            the thrown exception of which to print the stack trace.
	 */
	public void logAndAddMessage(String methodName, String messageKey, Exception e) {
		e.printStackTrace();
		LogEvent.logError(this.getClass().getSimpleName(), methodName, e.toString());
		if (messages.size() == 0) {
			ActionError error = new ActionError(messageKey, null, null);
			messages.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
	}

	protected abstract String getActionLabel();

	protected void persistInitialSampleConditions(){
	if(!FormFields.getInstance().useField(Field.InitialSampleCondition)) return;
		
	try {
		String xml=(String)projectFormMapper.getDynaBean().get( "sampleXML" );
		//System.out.println("AMANI:"+xml);
		Document sampleDom = DocumentHelper.parseText(xml);
		for (Iterator i = sampleDom.getRootElement().elementIterator("sample"); i.hasNext();) {
		Element sampleItem = (Element) i.next();
		String initialSampleConditionIdString = sampleItem.attributeValue("initialConditionIds");
		String sampleItemId = sampleItem.attributeValue("sampleID");
		
		ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();
		ObservationHistory observation=new ObservationHistory();			
		
		if (!GenericValidator.isBlankOrNull(initialSampleConditionIdString)) {
				String[] initialSampleConditionIds = initialSampleConditionIdString.split(",");
				for(int j=0;j<initialSampleConditionIds.length;j++){
					 observation=new ObservationHistory();
					 observation.setValue(initialSampleConditionIds[j]);
					 observation.setValueType(ObservationHistory.ValueType.DICTIONARY);
					 observation.setObservationHistoryTypeId(getObservationHistoryTypeId(new ObservationHistoryTypeDAOImpl(), "initialSampleCondition"));
				     observation.setSampleId(sample.getId());
					 observation.setSampleItemId(sampleItemId);
					 observation.setPatientId(patientInDB.getId());
					 observation.setSysUserId(sysUserId);
					 ohDAO.insertData(observation);
				}
			}
		}
		
	  } catch (DocumentException e) {
		e.printStackTrace();
	  }
	//  dynaForm.set("orbservations", observations);
	
		
	}

	private static String getObservationHistoryTypeId(ObservationHistoryTypeDAO ohtDAO, String name) {
		ObservationHistoryType oht;
		oht = ohtDAO.getByName(name);
		if (oht != null) {
			return oht.getId();
		}
	
		return null;
	}
}
