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
package org.openelisglobal.result.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openelisglobal.analysis.dao.AnalysisDAO;
import org.openelisglobal.analysis.daoimpl.AnalysisDAOImpl;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.AnalysisService;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.NoteService;
import org.openelisglobal.common.services.NoteService.NoteType;
import org.openelisglobal.common.services.ResultSaveService;
import org.openelisglobal.common.services.SampleService;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.OrderStatus;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.openelisglobal.common.services.beanAdapters.ResultSaveBeanAdapter;
import org.openelisglobal.common.services.registration.ResultUpdateRegister;
import org.openelisglobal.common.services.registration.interfaces.IResultUpdate;
import org.openelisglobal.common.services.serviceBeans.ResultSaveBean;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.validator.ActionError;
import org.openelisglobal.dataexchange.orderresult.OrderResponseWorker.Event;
import org.openelisglobal.hibernate.HibernateUtil;
import org.openelisglobal.note.dao.NoteDAO;
import org.openelisglobal.note.daoimpl.NoteDAOImpl;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.referral.dao.ReferralDAO;
import org.openelisglobal.referral.dao.ReferralResultDAO;
import org.openelisglobal.referral.dao.ReferralTypeDAO;
import org.openelisglobal.referral.daoimpl.ReferralDAOImpl;
import org.openelisglobal.referral.daoimpl.ReferralResultDAOImpl;
import org.openelisglobal.referral.daoimpl.ReferralTypeDAOImpl;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralResult;
import org.openelisglobal.referral.valueholder.ReferralType;
import org.openelisglobal.result.action.util.ResultSet;
import org.openelisglobal.result.action.util.ResultUtil;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;
import org.openelisglobal.result.dao.ResultDAO;
import org.openelisglobal.result.dao.ResultInventoryDAO;
import org.openelisglobal.result.dao.ResultSignatureDAO;
import org.openelisglobal.result.daoimpl.ResultDAOImpl;
import org.openelisglobal.result.daoimpl.ResultInventoryDAOImpl;
import org.openelisglobal.result.daoimpl.ResultSignatureDAOImpl;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.result.valueholder.ResultInventory;
import org.openelisglobal.result.valueholder.ResultSignature;
import org.openelisglobal.resultlimits.dao.ResultLimitDAO;
import org.openelisglobal.resultlimits.daoimpl.ResultLimitDAOImpl;
import org.openelisglobal.resultlimits.valueholder.ResultLimit;
import org.openelisglobal.sample.dao.SampleDAO;
import org.openelisglobal.sample.daoimpl.SampleDAOImpl;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.testreflex.action.util.TestReflexBean;
import org.openelisglobal.testreflex.action.util.TestReflexUtil;

public class ResultsLogbookUpdateAction extends BaseAction {

	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private ResultDAO resultDAO = new ResultDAOImpl();
	private ResultSignatureDAO resultSigDAO = new ResultSignatureDAOImpl();
	private ResultInventoryDAO resultInventoryDAO = new ResultInventoryDAOImpl();
	private NoteDAO noteDAO = new NoteDAOImpl();
	private SampleDAO sampleDAO = new SampleDAOImpl();
	private ReferralDAO referralDAO = new ReferralDAOImpl();
	private ReferralResultDAO referralResultDAO = new ReferralResultDAOImpl();
	private ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();

	private static final String RESULT_SUBJECT = "Result Note";
	private static String REFERRAL_CONFORMATION_ID;

    private boolean useTechnicianName = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.resultTechnicianName, "true");
	private boolean alwaysValidate = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.ALWAYS_VALIDATE_RESULTS, "true");
    private boolean supportReferrals = FormFields.getInstance().useField( Field.ResultsReferral );
	private String statusRuleSet = ConfigurationProperties.getInstance().getPropertyValueUpperCase( Property.StatusRules );

	static{
		ReferralTypeDAO referralTypeDAO = new ReferralTypeDAOImpl();
		ReferralType referralType = referralTypeDAO.getReferralTypeByName("Confirmation");
		if(referralType != null){
			REFERRAL_CONFORMATION_ID = referralType.getId();
		}
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		String forward = FWD_SUCCESS;
		List<IResultUpdate> updaters = ResultUpdateRegister.getRegisteredUpdaters();

		BaseActionForm dynaForm = (BaseActionForm)form;

		ResultsPaging paging = new ResultsPaging();

		// commented out to allow maven compilation - CSLpaging.updatePagedResults(request, dynaForm);
		List<TestResultItem> tests = paging.getResults(request);

        ResultsUpdateDataSet actionDataSet = new ResultsUpdateDataSet( currentUserId );
		actionDataSet.filterModifiedItems( tests );


		// commented out to allow maven compilation - CSL
		/*ActionMessages errors =  actionDataSet.validateModifiedItems();

		if(errors.size() > 0){
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return mapping.findForward(FWD_VALIDATION_ERROR);
		}
*/

		createResultsFromItems(actionDataSet);
        createAnalysisOnlyUpdates(actionDataSet);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try{
            for(Note note: actionDataSet.getNoteList()){
                noteDAO.insertData( note );
            }

			for(ResultSet resultSet : actionDataSet.getNewResults()){
				resultSet.result.setResultEvent(Event.PRELIMINARY_RESULT);
				resultDAO.insertData(resultSet.result);
				if(resultSet.signature != null){
					resultSet.signature.setResultId(resultSet.result.getId());
					resultSigDAO.insertData(resultSet.signature);
				}

				if(resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null){
					resultSet.testKit.setResultId(resultSet.result.getId());
					resultInventoryDAO.insertData(resultSet.testKit);
				}
			}

            for( Referral referral : actionDataSet.getSavableReferrals()){
                if( referral != null){
                    saveReferralsWithRequiredObjects( referral );
                }
            }

			for(ResultSet resultSet : actionDataSet.getModifiedResults()){
				resultSet.result.setResultEvent(Event.RESULT);
				resultDAO.updateData(resultSet.result);

				if(resultSet.signature != null){
					resultSet.signature.setResultId(resultSet.result.getId());
					if(resultSet.alwaysInsertSignature){
						resultSigDAO.insertData(resultSet.signature);
					}else{
						resultSigDAO.updateData(resultSet.signature);
					}
				}

				if(resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null){
					resultSet.testKit.setResultId(resultSet.result.getId());
					if(resultSet.testKit.getId() == null){
						resultInventoryDAO.insertData(resultSet.testKit);
					}else{
						resultInventoryDAO.updateData(resultSet.testKit);
					}
				}
			}

			for(Analysis analysis : actionDataSet.getModifiedAnalysis()){
				analysisDAO.updateData(analysis);
			}

            ResultSaveService.removeDeletedResultsInTransaction( actionDataSet.getDeletableResults(),currentUserId);

			setTestReflexes( actionDataSet );

			setSampleStatus( actionDataSet );

			for(IResultUpdate updater : updaters){
				updater.transactionalUpdate(actionDataSet);
			}

			tx.commit();

		}catch(LIMSRuntimeException lre){
			tx.rollback();

			ActionError error;
			if(lre.getException() instanceof StaleObjectStateException){
				error = new ActionError("errors.OptimisticLockException", null, null);
			}else{
				lre.printStackTrace();
				error = new ActionError("errors.UpdateException", null, null);
			}


			// commented out to allow maven compilation - CSL
			/*errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
*/
			return mapping.findForward(FWD_FAIL);

		}

		for(IResultUpdate updater : updaters){
			updater.postTransactionalCommitUpdate(actionDataSet);
		}

		setSuccessFlag(request, forward);

		if(GenericValidator.isBlankOrNull(dynaForm.getString("logbookType"))){
			return mapping.findForward(forward);
		}else{
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", dynaForm.getString("logbookType"));
			params.put("forward", forward);
			return getForwardWithParameters(mapping.findForward(forward), params);
		}
	}

    private void saveReferralsWithRequiredObjects( Referral referral ){
        if( referral.getId() != null){
            referralDAO.updateData( referral );
        }else{
            referralDAO.insertData( referral );
            ReferralResult referralResult = new ReferralResult();
            referralResult.setReferralId( referral.getId() );
            referralResult.setSysUserId( currentUserId );
            referralResultDAO.insertData( referralResult );
        }
	}



	protected void setTestReflexes(ResultsUpdateDataSet actionDataSet){
		TestReflexUtil testReflexUtil = new TestReflexUtil();
		testReflexUtil.setCurrentUserId( currentUserId );
		testReflexUtil.addNewTestsToDBForReflexTests( convertToTestReflexBeanList( actionDataSet.getNewResults()) );
		testReflexUtil.updateModifiedReflexes( convertToTestReflexBeanList( actionDataSet.getModifiedResults()) );
	}

	private List<TestReflexBean> convertToTestReflexBeanList(List<ResultSet> resultSetList){
		List<TestReflexBean> reflexBeanList = new ArrayList<TestReflexBean>();

		for(ResultSet resultSet : resultSetList){
			TestReflexBean reflex = new TestReflexBean();
			reflex.setPatient(resultSet.patient);

            if( resultSet.triggersToSelectedReflexesMap.size() > 0 && resultSet.multipleResultsForAnalysis){
                for( String trigger : resultSet.triggersToSelectedReflexesMap.keySet()){
                    if( trigger.equals( resultSet.result.getValue() )){
                        HashMap<String, List<String>> reducedMap = new HashMap<String, List<String>>( 1 );
                        reducedMap.put( trigger, resultSet.triggersToSelectedReflexesMap.get( trigger ) );
                        reflex.setTriggersToSelectedReflexesMap( reducedMap );
                    }
                }
                if( reflex.getTriggersToSelectedReflexesMap() == null){
                    reflex.setTriggersToSelectedReflexesMap( new HashMap<String, List<String>>(  ) );
                }
            }else{
                reflex.setTriggersToSelectedReflexesMap( resultSet.triggersToSelectedReflexesMap );
            }

			reflex.setResult(resultSet.result);
			reflex.setSample(resultSet.sample);
			reflexBeanList.add(reflex);
		}

		return reflexBeanList;
	}

	private void setSampleStatus(ResultsUpdateDataSet actionDataSet){
		Set<Sample> sampleSet = new HashSet<Sample>();

		for(ResultSet resultSet : actionDataSet.getNewResults()){
			sampleSet.add(resultSet.sample);
		}

		String sampleTestingStartedId = StatusService.getInstance().getStatusID(OrderStatus.Started);
		String sampleNonConformingId = StatusService.getInstance().getStatusID(OrderStatus.NonConforming_depricated);

		for(Sample sample : sampleSet){
			if(!(sample.getStatusId().equals(sampleNonConformingId) || sample.getStatusId().equals(sampleTestingStartedId))){
				Sample newSample = new Sample();
				newSample.setId(sample.getId());
				sampleDAO.getData(newSample);

				newSample.setStatusId(sampleTestingStartedId);
				newSample.setSysUserId(currentUserId);
				sampleDAO.updateData(newSample);
			}
		}
	}

    private void createAnalysisOnlyUpdates( ResultsUpdateDataSet actionDataSet ){
        for(TestResultItem testResultItem : actionDataSet.getAnalysisOnlyChangeResults()){
            AnalysisService analysisService = new AnalysisService( testResultItem.getAnalysisId() );
            analysisService.getAnalysis().setSysUserId( currentUserId );
            analysisService.getAnalysis().setCompletedDate( DateUtil.convertStringDateToSqlDate( testResultItem.getTestDate() ) );
            analysisService.getAnalysis().setAnalysisType( testResultItem.getAnalysisMethod() );
            actionDataSet.getModifiedAnalysis().add( analysisService.getAnalysis() );
        }
    }

	private void createResultsFromItems( ResultsUpdateDataSet actionDataSet ){

		for(TestResultItem testResultItem : actionDataSet.getModifiedItems()){
			AnalysisService analysisService = new AnalysisService( testResultItem.getAnalysisId() );
            analysisService.getAnalysis().setStatusId( getStatusForTestResult( testResultItem ) );
            analysisService.getAnalysis().setSysUserId( currentUserId );
            handleReferrals(testResultItem, analysisService.getAnalysis(), actionDataSet);
            actionDataSet.getModifiedAnalysis().add( analysisService.getAnalysis() );

            NoteService noteService = new NoteService( analysisService.getAnalysis() );
            actionDataSet.addToNoteList( noteService.createSavableNote( NoteType.INTERNAL, testResultItem.getNote(), RESULT_SUBJECT, currentUserId ) );

            if (testResultItem.isShadowRejected()) {
                String rejectedReasonId = testResultItem.getRejectReasonId();
                for (IdValuePair rejectReason : DisplayListService.getInstance().getList(ListType.REJECTION_REASONS)) {
                    if (rejectedReasonId.equals(rejectReason.getId())) {
                        actionDataSet.addToNoteList( noteService.createSavableNote( NoteType.REJECTION_REASON, rejectReason.getValue(), RESULT_SUBJECT, currentUserId) );
                        break;
                    }
                }
            }

            ResultSaveBean bean = ResultSaveBeanAdapter.fromTestResultItem(testResultItem);
            ResultSaveService resultSaveService = new ResultSaveService(analysisService.getAnalysis(),currentUserId);
            //deletable Results will be written to, not read
			List<Result> results = resultSaveService.createResultsFromTestResultItem( bean, actionDataSet.getDeletableResults());

            analysisService.getAnalysis().setCorrectedSincePatientReport( resultSaveService.isUpdatedResult() &&
                                                                          analysisService.patientReportHasBeenDone()  );

            if( analysisService.hasBeenCorrectedSinceLastPatientReport()){
                actionDataSet.addToNoteList( noteService.createSavableNote( NoteType.EXTERNAL, StringUtil.getMessageForKey( "note.corrected.result" ), RESULT_SUBJECT, currentUserId ) );
            }

            //If there is more than one result then each user selected reflex gets mapped to that result
			for(Result result : results){
				addResult(result, testResultItem, analysisService.getAnalysis(), results.size() > 1, actionDataSet);

				if(analysisShouldBeUpdated(testResultItem, result)){
					updateAnalysis( testResultItem, testResultItem.getTestDate(), analysisService.getAnalysis() );
				}
			}
		}
	}

    private void handleReferrals( TestResultItem testResultItem, Analysis analysis, ResultsUpdateDataSet actionDataSet ){

        if(supportReferrals){

            Referral referral = null;
            // referredOut means the referral checkbox was checked, repeating
            // analysis means that we have multi-select results, so only do one.
            if(testResultItem.isShadowReferredOut() && !actionDataSet.getReferredAnalysisIds().contains( analysis.getId() )){
                actionDataSet.getReferredAnalysisIds().add( analysis.getId() );
                // If it is a new result or there is no referral ID that means
                // that a new referral has to be created if it was checked and
                // it was canceled then we are un-canceling a canceled referral
                if(testResultItem.getResultId() == null || GenericValidator.isBlankOrNull(testResultItem.getReferralId())){
                    referral = new Referral();
                    referral.setReferralTypeId(REFERRAL_CONFORMATION_ID);
                    referral.setSysUserId(currentUserId);
                    referral.setRequestDate( new Timestamp( new Date().getTime() ) );
                    referral.setRequesterName( testResultItem.getTechnician() );
                    referral.setAnalysis( analysis );
                    referral.setReferralReasonId(testResultItem.getReferralReasonId());
                }else if(testResultItem.isReferralCanceled()){
                    referral = referralDAO.getReferralById(testResultItem.getReferralId());
                    referral.setCanceled(false);
                    referral.setSysUserId(currentUserId);
                    referral.setRequesterName(testResultItem.getTechnician());
                    referral.setReferralReasonId(testResultItem.getReferralReasonId());
                }

                actionDataSet.getSavableReferrals().add( referral );

            }
        }
    }



	protected boolean analysisShouldBeUpdated(TestResultItem testResultItem, Result result){
		return result != null && !GenericValidator.isBlankOrNull(result.getValue())
				|| (supportReferrals && ResultUtil.isReferred(testResultItem))
				|| ResultUtil.isForcedToAcceptance(testResultItem) || testResultItem.isShadowRejected();
	}

	private void addResult(Result result, TestResultItem testResultItem, Analysis analysis, boolean multipleResultsForAnalysis, ResultsUpdateDataSet actionDataSet){
		boolean newResult = result.getId() == null;
		boolean newAnalysisInLoop = analysis != actionDataSet.getPreviousAnalysis();

		ResultSignature technicianResultSignature = null;

		if(useTechnicianName && newAnalysisInLoop){
			technicianResultSignature = createTechnicianSignatureFromResultItem(testResultItem);
		}

		ResultInventory testKit = createTestKitLinkIfNeeded(testResultItem, ResultsLoadUtility.TESTKIT);

		analysis.setReferredOut(testResultItem.isReferredOut());
		analysis.setEnteredDate(DateUtil.getNowAsTimestamp());

		if(newResult){
			analysis.setEnteredDate(DateUtil.getNowAsTimestamp());
			analysis.setRevision("1");
		}else if(newAnalysisInLoop){
			analysis.setRevision(String.valueOf(Integer.parseInt(analysis.getRevision()) + 1));
		}

        SampleService sampleService = new SampleService( testResultItem.getAccessionNumber() );
        Patient patient = sampleService.getPatient();

        Map<String,List<String>> triggersToReflexesMap = new HashMap<String, List<String>>(  );

        getSelectedReflexes( testResultItem.getReflexJSONResult(), triggersToReflexesMap );

        if(newResult){
			actionDataSet.getNewResults().add( new ResultSet( result, technicianResultSignature, testKit, patient, sampleService.getSample(), triggersToReflexesMap,
                    multipleResultsForAnalysis ) );
		}else{
			actionDataSet.getModifiedResults().add( new ResultSet( result, technicianResultSignature, testKit, patient, sampleService.getSample(), triggersToReflexesMap,
                    multipleResultsForAnalysis ) );
		}

		actionDataSet.setPreviousAnalysis(analysis);
	}

    private void getSelectedReflexes( String reflexJSONResult, Map<String, List<String>> triggersToReflexesMap ){
        if( !GenericValidator.isBlankOrNull( reflexJSONResult )){
            JSONParser parser=new JSONParser();
            try{
                JSONObject jsonResult = ( JSONObject ) parser.parse( reflexJSONResult.replaceAll( "'", "\"" ) );

                for(Object compoundReflexes : jsonResult.values()){
                    if( compoundReflexes != null){
                        String triggerIds = ( String ) ( ( JSONObject ) compoundReflexes ).get( "triggerIds" );
                        List<String> selectedReflexIds = new ArrayList<String>();
                        JSONArray selectedReflexes = ( JSONArray ) ( ( JSONObject ) compoundReflexes ).get( "selected" );
                        for( Object selectedReflex : selectedReflexes ){
                            selectedReflexIds.add( ( ( String ) selectedReflex ) );
                        }
                        triggersToReflexesMap.put( triggerIds.trim(), selectedReflexIds );
                    }
                }
            }catch( ParseException e ){
                e.printStackTrace();
            }
        }
    }

    private String getStatusForTestResult(TestResultItem testResult){
        if (testResult.isShadowRejected()) {
            return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected);
        }else if(alwaysValidate || !testResult.isValid() || ResultUtil.isForcedToAcceptance(testResult)){
			return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
		}else if(noResults(testResult.getShadowResultValue(), testResult.getMultiSelectResultValues(), testResult.getResultType())){
			return StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted);
		}else{
			ResultLimit resultLimit = resultLimitDAO.getResultLimitById(testResult.getResultLimitId());
			if(resultLimit != null && resultLimit.isAlwaysValidate()){
				return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
			}

			return StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
		}
	}

	private boolean noResults(String value, String multiSelectValue, String type){

		return (GenericValidator.isBlankOrNull(value) && GenericValidator.isBlankOrNull(multiSelectValue)) ||
				( TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(type) && "0".equals(value));
	}

	private ResultInventory createTestKitLinkIfNeeded(TestResultItem testResult, String testKitName){
		ResultInventory testKit = null;

		if((TestResultItem.ResultDisplayType.SYPHILIS == testResult.getRawResultDisplayType() || TestResultItem.ResultDisplayType.HIV == testResult
				.getRawResultDisplayType()) && ResultsLoadUtility.TESTKIT.equals(testKitName)){

			testKit = createTestKit( testResult, testKitName, testResult.getTestKitId() );
		}

		return testKit;
	}

	private ResultInventory createTestKit( TestResultItem testResult, String testKitName, String testKitId ) throws LIMSRuntimeException{
		ResultInventory testKit;
		testKit = new ResultInventory();

		if(!GenericValidator.isBlankOrNull(testKitId)){
			testKit.setId(testKitId);
			resultInventoryDAO.getData(testKit);
		}

		testKit.setInventoryLocationId(testResult.getTestKitInventoryId());
		testKit.setDescription(testKitName);
		testKit.setSysUserId(currentUserId);
		return testKit;
	}

	private void updateAnalysis( TestResultItem testResultItem, String testDate, Analysis analysis ){
		analysis.setAnalysisType(testResultItem.getAnalysisMethod());
		analysis.setStartedDateForDisplay(testDate);

		// This needs to be refactored -- part of the logic is in
		// getStatusForTestResult. RetroCI over rides to whatever was set before
		if(statusRuleSet.equals(IActionConstants.STATUS_RULES_RETROCI)){
			if( !StatusService.getInstance().getStatusID(AnalysisStatus.Canceled).equals(analysis.getStatusId() )){
				analysis.setCompletedDate(DateUtil.convertStringDateToSqlDate(testDate));
				analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance));
			}
		}else if(StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.Finalized) ||
				StatusService.getInstance().matches( analysis.getStatusId(), AnalysisStatus.TechnicalAcceptance ) ||
				(analysis.isReferredOut() && !GenericValidator.isBlankOrNull(testResultItem.getShadowResultValue()))){
			analysis.setCompletedDate(DateUtil.convertStringDateToSqlDate(testDate));
		}

	}


	private ResultSignature createTechnicianSignatureFromResultItem(TestResultItem testResult){
		ResultSignature sig = null;

		// The technician signature may be blank if the user changed a
		// conclusion and then changed it back. It will be dirty
		// but will not need a signature
		if(!GenericValidator.isBlankOrNull(testResult.getTechnician())){
			sig = new ResultSignature();

			if(!GenericValidator.isBlankOrNull(testResult.getTechnicianSignatureId())){
				sig.setId(testResult.getTechnicianSignatureId());
				resultSigDAO.getData(sig);
			}

			sig.setIsSupervisor(false);
			sig.setNonUserName(testResult.getTechnician());

			sig.setSysUserId(currentUserId);
		}
		return sig;
	}

	protected ActionForward getForward(ActionForward forward, String accessionNumber){
		ActionRedirect redirect = new ActionRedirect(forward);
		if(!StringUtil.isNullorNill(accessionNumber))
			redirect.addParameter(ACCESSION_NUMBER, accessionNumber);

		return redirect;

	}

	@Override
	protected ActionForward getForward(ActionForward forward, String accessionNumber, String analysisId){
		ActionRedirect redirect = new ActionRedirect(forward);
		if(!StringUtil.isNullorNill(accessionNumber))
			redirect.addParameter(ACCESSION_NUMBER, accessionNumber);

		if(!StringUtil.isNullorNill(analysisId))
			redirect.addParameter(ANALYSIS_ID, analysisId);

		return redirect;

	}

	@Override
	protected String getPageSubtitleKey(){
		return "banner.menu.results";
	}

	@Override
	protected String getPageTitleKey(){
		return "banner.menu.results";
	}

}
