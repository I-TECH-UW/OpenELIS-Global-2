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
package us.mn.state.health.lims.resultvalidation.action;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.NoteService.NoteType;
import us.mn.state.health.lims.common.services.ResultSaveService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.services.beanAdapters.ResultSaveBeanAdapter;
import us.mn.state.health.lims.common.services.registration.ValidationUpdateRegister;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.services.serviceBeans.ResultSaveBean;
//import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.StringUtil;
//import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.dao.DocumentTrackDAO;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationPaging;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class ResultValidationSaveAction extends BaseResultValidationAction implements IResultSaveService  {

	// DAOs
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final TestResultDAO testResultDAO = new TestResultDAOImpl();
	private static final ResultDAO resultDAO = new ResultDAOImpl();
	private static final NoteDAO noteDAO = new NoteDAOImpl();
	private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static final DocumentTrackDAO documentTrackDAO =  new DocumentTrackDAOImpl();
	
	// Update Lists
	private List<Analysis> analysisUpdateList;
	private ArrayList<Sample> sampleUpdateList;
	private ArrayList<Note> noteUpdateList;
	private ArrayList<Result> resultUpdateList;
    private List<Result> deletableList;

	private SystemUser systemUser;
	private ArrayList<Integer> sampleFinishedStatus = new ArrayList<Integer>();
	private List<ResultSet> modifiedResultSet;
	private List<ResultSet> newResultSet;

	private static final String RESULT_SUBJECT = "Result Note";
	private static final String RESULT_TABLE_ID;
	private static final String RESULT_REPORT_ID;
	
	
	static{
		RESULT_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("RESULT").getId();		
		RESULT_REPORT_ID = new DocumentTypeDAOImpl().getDocumentTypeByName("resultExport").getId();
	}
	
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		
		String forward = FWD_SUCCESS;

		List<IResultUpdate> updaters = ValidationUpdateRegister.getRegisteredUpdaters();
		boolean areListeners = updaters != null && !updaters.isEmpty(); 
		
		request.getSession().setAttribute(SAVE_DISABLED, "true");

		BaseActionForm dynaForm = (BaseActionForm)form;

		ResultValidationPaging paging = new ResultValidationPaging();
		paging.updatePagedResults(request, dynaForm);
		List<AnalysisItem> resultItemList = paging.getResults(request);

		String testSectionName = (String)dynaForm.get("testSection");
		String testName = (String)dynaForm.get("testName");
		setRequestType(testSectionName);
		//----------------------
		String url = request.getRequestURL().toString();
		
		ActionMessages errors = validateModifiedItems(resultItemList);

		if(errors.size() > 0){
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			return mapping.findForward(FWD_VALIDATION_ERROR);
		}
		
		createSystemUser();
		setSampleFinishedStatuses();

		noteUpdateList = new ArrayList<Note>();
		resultUpdateList = new ArrayList<Result>();
		analysisUpdateList = new ArrayList<Analysis>();
		modifiedResultSet = new ArrayList<ResultSet>();
		newResultSet = new ArrayList<ResultSet>();
        deletableList = new ArrayList<Result>();
		
		if(testSectionName.equals("serology")){
			createUpdateElisaList(resultItemList);
		}else{
			createUpdateList(resultItemList, areListeners);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try{
            ResultSaveService.removeDeletedResultsInTransaction( deletableList,currentUserId);

            // update analysis
			for(Analysis analysis : analysisUpdateList){
				analysisDAO.updateData(analysis);
			}

			for(Result result : resultUpdateList){
				if( result.getId() != null){
					resultDAO.updateData(result);
				}else{
					resultDAO.insertData(result);
				}
			}

			checkIfSamplesFinished(resultItemList);

			// update finished samples
			for(Sample sample : sampleUpdateList){
				sampleDAO.updateData(sample);
			}

			// create or update notes
			for(Note note : noteUpdateList){
				if(note != null){
					if(note.getId() == null){
						noteDAO.insertData(note);
					}else{
						noteDAO.updateData(note);
					}
				}
			}

			for(IResultUpdate updater : updaters){
				updater.transactionalUpdate(this);
			}
			
			tx.commit();

		}catch(LIMSRuntimeException lre){
			tx.rollback();
		}
		
		for(IResultUpdate updater : updaters){
			updater.postTransactionalCommitUpdate(this);
		}
		
		// route save back to RetroC specific ResultValidationRetroCAction
	//	if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "CI RetroCI"))
		if (url.contains("RetroC"))
			forward = "successRetroC";
			

		if( isBlankOrNull( testSectionName )){
			return mapping.findForward(forward);
		}else{
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", testSectionName);
			params.put("test", testName);
			params.put("forward", forward);

			return getForwardWithParameters(mapping.findForward(forward), params);
		}

	}

	private ActionMessages validateModifiedItems(List<AnalysisItem> resultItemList){
		ActionErrors errors = new ActionErrors();
		
		
		for (AnalysisItem item : resultItemList) {
			List<ActionError> errorList = new ArrayList<ActionError>();
			validateQuantifiableItems(item, errorList);
			

			if (errorList.size() > 0) {
				StringBuilder augmentedAccession = new StringBuilder(item.getAccessionNumber());
				augmentedAccession.append(" : ");
				augmentedAccession.append(item.getTestName());
				ActionError accessionError = new ActionError("errors.followingAccession", augmentedAccession);
				errors.add(ActionErrors.GLOBAL_MESSAGE, accessionError);

				for (ActionError error : errorList) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				}

			}
		}

		return errors;
	}

	public void validateQuantifiableItems(AnalysisItem analysisItem, List<ActionError> errors){
		if( analysisItem.isHasQualifiedResult() &&
				isBlankOrNull( analysisItem.getQualifiedResultValue() ) &&
				analysisItemWillBeUpdated(analysisItem)){
			errors.add(new ActionError("errors.missing.result.details", new StringBuilder("Result")));
		}
		// verify that qualifiedResultValue has been entered if required
		if (!isBlankOrNull( analysisItem.getQualifiedDictionaryId() )) {
		    String[] qualifiedDictionaryIds = analysisItem.getQualifiedDictionaryId().replace("[", "").replace("]", "").split(",");
		    Set<String> qualifiedDictIdsSet = new HashSet<String>(Arrays.asList(qualifiedDictionaryIds));
		    
		    
		    if (qualifiedDictIdsSet.contains(analysisItem.getResult()) &&
		            isBlankOrNull( analysisItem.getQualifiedResultValue() )) {
		        errors.add(new ActionError("errors.missing.result.details", new StringBuilder("Result")));
		      
		    }

		}
				
		
	}


	
	private void createUpdateList(List<AnalysisItem> analysisItems, boolean areListeners){

		List<String> analysisIdList = new ArrayList<String>();

		for(AnalysisItem analysisItem : analysisItems){
			if(!analysisItem.isReadOnly() && analysisItemWillBeUpdated(analysisItem)){

                AnalysisService analysisService = new AnalysisService( analysisItem.getAnalysisId() );
                Analysis analysis = analysisService.getAnalysis();
                NoteService noteService = new NoteService( analysis );

				analysis.setSysUserId(currentUserId);

				if(!analysisIdList.contains(analysis.getId())){

					if(analysisItem.getIsAccepted()){
						analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized));
						analysis.setReleasedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
						analysisIdList.add(analysis.getId());
						analysisUpdateList.add(analysis);
					}

					if(analysisItem.getIsRejected()){
						analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected));
						analysisIdList.add(analysis.getId());
						analysisUpdateList.add(analysis);
					}
				}

                createNeededNotes( analysisItem, noteService );

                if (areResults(analysisItem)) {
                    List<Result> results = createResultFromAnalysisItem(analysisItem, analysisService, noteService);
                    for (Result result : results) {
                        resultUpdateList.add(result);

                        if (areListeners) {
                            addResultSets(analysis, result);
                        }
                    }
                }
            }
		}
	}

    private void createNeededNotes( AnalysisItem analysisItem, NoteService noteService ){
        if( analysisItem.getIsRejected()){
            Note note = noteService.createSavableNote( NoteType.INTERNAL, StringUtil.getMessageForKey( "validation.note.retest" ), RESULT_SUBJECT, currentUserId );
            noteUpdateList.add( note );
        }

        if( !GenericValidator.isBlankOrNull( analysisItem.getNote() )){
            NoteType noteType = analysisItem.getIsAccepted() ? NoteType.EXTERNAL : NoteType.INTERNAL;
            Note note = noteService.createSavableNote( noteType, analysisItem.getNote(), RESULT_SUBJECT, currentUserId );
            noteUpdateList.add( note );
        }
    }

    private void addResultSets(Analysis analysis, Result result){
		Sample sample = analysis.getSampleItem().getSample();
		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		if (finalResultAlreadySent(result)) {
        	result.setResultEvent(Event.CORRECTION);
			modifiedResultSet.add(new ResultSet(result, null,null, patient, sample, null, false));
		} else {
        	result.setResultEvent(Event.FINAL_RESULT);
			newResultSet.add(new ResultSet(result, null,null, patient, sample, null, false));
		}
	}

    // TO DO bug falsely triggered when preliminary result is sent, fails, retries and succeeds
	private boolean finalResultAlreadySent(Result result) {
		List<DocumentTrack> documents =  documentTrackDAO.getByTypeRecordAndTable(RESULT_REPORT_ID, RESULT_TABLE_ID, result.getId());
		return documents.size() > 0;
	}

	private boolean analysisItemWillBeUpdated(AnalysisItem analysisItem){
		return analysisItem.getIsAccepted() || analysisItem.getIsRejected();
	}

	private void createUpdateElisaList(List<AnalysisItem> resultItems){

		for(AnalysisItem resultItem : resultItems){

			if(resultItem.getIsAccepted()){

				List<Analysis> acceptedAnalysisList = createAnalysisFromElisaAnalysisItem(resultItem);

				for(Analysis analysis : acceptedAnalysisList){
					analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized));
					analysisUpdateList.add(analysis);
				}
			}

			if(resultItem.getIsRejected()){
				List<Analysis> rejectedAnalysisList = createAnalysisFromElisaAnalysisItem(resultItem);

				for(Analysis analysis : rejectedAnalysisList){
					analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected));
					analysisUpdateList.add(analysis);
				}

			}
		}
	}

	private List<Analysis> createAnalysisFromElisaAnalysisItem(AnalysisItem analysisItem){

		List<Analysis> analysisList = new ArrayList<Analysis>();

		Analysis analysis = new Analysis();

		if(!isBlankOrNull( analysisItem.getMurexResult() )){
			analysis = getAnalysisFromId(analysisItem.getMurexAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getBiolineResult() )){
			analysis = getAnalysisFromId(analysisItem.getBiolineAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getIntegralResult() )){
			analysis = getAnalysisFromId(analysisItem.getIntegralAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getVironostikaResult() )){
			analysis = getAnalysisFromId(analysisItem.getVironostikaAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getGenieIIResult() )){
			analysis = getAnalysisFromId(analysisItem.getGenieIIAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getGenieII10Result() )){
			analysis = getAnalysisFromId(analysisItem.getGenieII10AnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getGenieII100Result() )){
			analysis = getAnalysisFromId(analysisItem.getGenieII100AnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getWesternBlot1Result() )){
			analysis = getAnalysisFromId(analysisItem.getWesternBlot1AnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getWesternBlot2Result() )){
			analysis = getAnalysisFromId(analysisItem.getWesternBlot2AnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getP24AgResult() )){
			analysis = getAnalysisFromId(analysisItem.getP24AgAnalysisId());
			analysisList.add(analysis);
		}
		if(!isBlankOrNull( analysisItem.getInnoliaResult() )){
			analysis = getAnalysisFromId(analysisItem.getInnoliaAnalysisId());
			analysisList.add(analysis);
		}

		analysisList.add(analysis);

		return analysisList;
	}

	private void checkIfSamplesFinished(List<AnalysisItem> resultItemList){
		sampleUpdateList = new ArrayList<Sample>();

		String currentSampleId = "";
		boolean sampleFinished = true;

		for(AnalysisItem analysisItem : resultItemList){

			String analysisSampleId = sampleDAO.getSampleByAccessionNumber(analysisItem.getAccessionNumber()).getId();
			if(!analysisSampleId.equals(currentSampleId)){

				currentSampleId = analysisSampleId;

                List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(currentSampleId);

				for(Analysis analysis : analysisList){
					if(!sampleFinishedStatus.contains(Integer.parseInt(analysis.getStatusId()))){
						sampleFinished = false;
						break;
					}
				}

				if(sampleFinished){
					Sample sample = new Sample();
					sample.setId(currentSampleId);
					sampleDAO.getData(sample);
					sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Finished));
					sampleUpdateList.add(sample);
				}

				sampleFinished = true;

			}

		}
	}

	private Analysis getAnalysisFromId(String id){
		Analysis analysis = new Analysis();
		analysis.setId(id);
		analysisDAO.getData(analysis);
		analysis.setSysUserId(currentUserId);

		return analysis;
	}

	private List<Result> createResultFromAnalysisItem(AnalysisItem analysisItem, AnalysisService analysisService, NoteService noteService){

        ResultSaveBean bean =  ResultSaveBeanAdapter.fromAnalysisItem(analysisItem);
        ResultSaveService resultSaveService = new ResultSaveService(analysisService.getAnalysis(), currentUserId );
        List<Result> results = resultSaveService.createResultsFromTestResultItem(bean,deletableList);
        if( analysisService.patientReportHasBeenDone() && resultSaveService.isUpdatedResult()){
            analysisService.getAnalysis().setCorrectedSincePatientReport( true );
            noteUpdateList.add( noteService.createSavableNote( NoteType.EXTERNAL, StringUtil.getMessageForKey( "note.corrected.result" ), RESULT_SUBJECT, currentUserId ));
        }
        return results;
	}


	protected TestResult getTestResult(AnalysisItem analysisItem){
		TestResult testResult = null;
		if(TypeOfTestResultService.ResultType.DICTIONARY.matches( analysisItem.getResultType() )){
			testResult = testResultDAO.getTestResultsByTestAndDictonaryResult(analysisItem.getTestId(), analysisItem.getResult());
		}else{
			List<TestResult> testResultList = testResultDAO.getActiveTestResultsByTest( analysisItem.getTestId() );
			// we are assuming there is only one testResult for a numeric type
			// result
			if(!testResultList.isEmpty()){
				testResult = testResultList.get(0);
			}
		}
		return testResult;
	}

	private boolean areResults(AnalysisItem item){
		return !( isBlankOrNull( item.getResult() ) ||
                (TypeOfTestResultService.ResultType.DICTIONARY.matches( item.getResultType() ) && "0".equals(item.getResult()))) ||
                (TypeOfTestResultService.ResultType.isMultiSelectVariant( item.getResultType() ) && !isBlankOrNull( item.getMultiSelectResultValues() ));
	}

	private void createSystemUser(){
		systemUser = new SystemUser();
		systemUser.setId(currentUserId);
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUserDAO.getData(systemUser);
	}

	private void setSampleFinishedStatuses(){
		sampleFinishedStatus = new ArrayList<Integer>();
		sampleFinishedStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));
		sampleFinishedStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
		sampleFinishedStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated)));
	}

	@Override
	public String getCurrentUserId(){
		return currentUserId;
	}

	@Override
	public List<ResultSet> getNewResults(){
		return newResultSet;
	}

	@Override
	public List<ResultSet> getModifiedResults(){
		return modifiedResultSet;
	}

}
