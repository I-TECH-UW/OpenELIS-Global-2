package org.openelisglobal.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.StaleObjectStateException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.result.form.LogbookResultsForm;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.note.service.NoteService;
import org.openelisglobal.note.service.NoteServiceImpl.NoteType;
import org.openelisglobal.referral.service.ReferralService;
import org.openelisglobal.referral.service.ReferralTypeService;
import org.openelisglobal.result.service.LogbookResultsPersistService;
import org.openelisglobal.result.service.ResultInventoryService;
import org.openelisglobal.result.service.ResultSignatureService;
import org.openelisglobal.resultlimit.service.ResultLimitService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.ResultSaveService;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.beanAdapters.ResultSaveBeanAdapter;
import org.openelisglobal.common.services.registration.ResultUpdateRegister;
import org.openelisglobal.common.services.registration.interfaces.IResultUpdate;
import org.openelisglobal.common.services.serviceBeans.ResultSaveBean;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralType;
import org.openelisglobal.result.action.util.ResultSet;
import org.openelisglobal.result.action.util.ResultUtil;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.result.valueholder.ResultInventory;
import org.openelisglobal.result.valueholder.ResultSignature;
import org.openelisglobal.resultlimits.valueholder.ResultLimit;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.statusofsample.util.StatusRules;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.test.valueholder.TestSection;

@Controller
public class LogbookResultsController extends LogbookResultsBaseController {

	@Autowired
	private ResultSignatureService resultSigService;
	@Autowired
	private ResultInventoryService resultInventoryService;
	@Autowired
	private ReferralService referralService;
	@Autowired
	private ResultLimitService resultLimitService;
	@Autowired
	private TestSectionService testSectionService;
	@Autowired
	private ReferralTypeService referralTypeService;
	@Autowired
	private LogbookResultsPersistService logbookPersistService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private NoteService noteService;

	private static final String RESULT_SUBJECT = "Result Note";
	private static String REFERRAL_CONFORMATION_ID;

	private boolean useTechnicianName = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.resultTechnicianName, "true");
	private boolean alwaysValidate = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.ALWAYS_VALIDATE_RESULTS, "true");
	private boolean supportReferrals = FormFields.getInstance().useField(Field.ResultsReferral);
	private String statusRuleSet = ConfigurationProperties.getInstance()
			.getPropertyValueUpperCase(Property.StatusRules);

	@PostConstruct
	private void initialize() {
		ReferralType referralType = referralTypeService.getReferralTypeByName("Confirmation");
		if (referralType != null) {
			REFERRAL_CONFORMATION_ID = referralType.getId();
		}
	}

	@RequestMapping(value = "/LogbookResults", method = RequestMethod.GET)
	public ModelAndView showLogbookResults(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		LogbookResultsForm form = new LogbookResultsForm();

		String requestedPage = request.getParameter("page");
		String testSectionId = request.getParameter("testSectionId");

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		TestSection ts = null;

		String currentDate = getCurrentDate();
		PropertyUtils.setProperty(form, "currentDate", currentDate);
		PropertyUtils.setProperty(form, "logbookType", request.getParameter("type"));
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons", DisplayListService.getInstance()
				.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		// load testSections for drop down
		List<IdValuePair> testSections = DisplayListService.getInstance().getList(ListType.TEST_SECTION);
		PropertyUtils.setProperty(form, "testSections", testSections);
		PropertyUtils.setProperty(form, "testSectionsByName",
				DisplayListService.getInstance().getList(ListType.TEST_SECTION_BY_NAME));

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionService.get(testSectionId);
			PropertyUtils.setProperty(form, "testSectionId", "0");
		}

		setRequestType(ts == null ? MessageUtil.getMessage("workplan.unit.types") : ts.getLocalizedName());

		List<TestResultItem> tests;

		ResultsPaging paging = new ResultsPaging();
		List<InventoryKitItem> inventoryList = new ArrayList<>();
		ResultsLoadUtility resultsLoadUtility = SpringContext.getBean(ResultsLoadUtility.class);
		resultsLoadUtility.setSysUser(getSysUserId(request));

		if (GenericValidator.isBlankOrNull(requestedPage)) {

			new StatusRules().setAllowableStatusForLoadingResults(resultsLoadUtility);

			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				tests = resultsLoadUtility.getUnfinishedTestResultItemsInTestSection(testSectionId);
			} else {
				tests = new ArrayList<>();
			}

			if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE,
					"true") && !userHasPermissionForModule(request, "PatientResults")) {
				for (TestResultItem resultItem : tests) {
					resultItem.setPatientInfo("---");
				}

			}

			paging.setDatabaseResults(request, form, tests);

		} else {
			paging.page(request, form, requestedPage);
		}
		PropertyUtils.setProperty(form, "displayTestKit", false);
		if (ts != null) {
			// this does not look right what happens after a new page!!!
			boolean isHaitiClinical = ConfigurationProperties.getInstance()
					.isPropertyValueEqual(Property.configurationName, "Haiti Clinical");
			if (resultsLoadUtility.inventoryNeeded() || (isHaitiClinical && ("VCT").equals(ts.getTestSectionName()))) {
				InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
				inventoryList = inventoryUtility.getExistingActiveInventory();

				PropertyUtils.setProperty(form, "displayTestKit", true);
			}
		}
		List<String> hivKits = new ArrayList<>();
		List<String> syphilisKits = new ArrayList<>();

		for (InventoryKitItem item : inventoryList) {
			if (item.getType().equals("HIV")) {
				hivKits.add(item.getInventoryLocationId());
			} else {
				syphilisKits.add(item.getInventoryLocationId());
			}
		}
		PropertyUtils.setProperty(form, "hivKits", hivKits);
		PropertyUtils.setProperty(form, "syphilisKits", syphilisKits);
		PropertyUtils.setProperty(form, "inventoryItems", inventoryList);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private String getCurrentDate() {
		Date today = Calendar.getInstance().getTime();
		return DateUtil.formatDateAsText(today);

	}

	@RequestMapping(value = { "/LogbookResults", "/PatientResults", "/AccessionResults",
			"/StatusResults" }, method = RequestMethod.POST)
	public ModelAndView showLogbookResultsUpdate(HttpServletRequest request,
			@ModelAttribute("form") @Validated(LogbookResultsForm.LogbookResults.class) LogbookResultsForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			saveErrors(result);
			findForward(FWD_FAIL_INSERT, form);
		}

		List<IResultUpdate> updaters = ResultUpdateRegister.getRegisteredUpdaters();

		ResultsPaging paging = new ResultsPaging();
		paging.updatePagedResults(request, form);
		List<TestResultItem> tests = paging.getResults(request);

		ResultsUpdateDataSet actionDataSet = new ResultsUpdateDataSet(getSysUserId(request));
		actionDataSet.filterModifiedItems(tests);

		Errors errors = actionDataSet.validateModifiedItems();

		if (errors.hasErrors()) {
			saveErrors(errors);
			return findForward(FWD_VALIDATION_ERROR, form);
		}

		createResultsFromItems(actionDataSet);
		createAnalysisOnlyUpdates(actionDataSet);

		try {
			logbookPersistService.persistDataSet(actionDataSet, updaters, getSysUserId(request));
		} catch (LIMSRuntimeException lre) {
			String errorMsg;
			if (lre.getException() instanceof StaleObjectStateException) {
				errorMsg = "errors.OptimisticLockException";
			} else {
				lre.printStackTrace();
				errorMsg = "errors.UpdateException";
			}

			errors.reject(errorMsg, errorMsg);
			saveErrors(errors);
			return findForward(FWD_FAIL_INSERT, form);

		}

		for (IResultUpdate updater : updaters) {
			updater.postTransactionalCommitUpdate(actionDataSet);
		}

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		if (GenericValidator.isBlankOrNull(form.getString("logbookType"))) {
			return findForward(FWD_SUCCESS_INSERT, form);
		} else {
			Map<String, String> params = new HashMap<>();
			params.put("type", form.getString("logbookType"));
			return getForwardWithParameters(findForward(FWD_SUCCESS_INSERT, form), params);
		}
	}

	private void createAnalysisOnlyUpdates(ResultsUpdateDataSet actionDataSet) {
		for (TestResultItem testResultItem : actionDataSet.getAnalysisOnlyChangeResults()) {

			Analysis analysis = analysisService.get(testResultItem.getAnalysisId());
			analysis.setSysUserId(getSysUserId(request));
			analysis.setCompletedDate(DateUtil.convertStringDateToSqlDate(testResultItem.getTestDate()));
			if (testResultItem.getAnalysisMethod() != null) {
				analysis.setAnalysisType(testResultItem.getAnalysisMethod());
			}
			actionDataSet.getModifiedAnalysis().add(analysis);
		}
	}

	private void createResultsFromItems(ResultsUpdateDataSet actionDataSet) {

		for (TestResultItem testResultItem : actionDataSet.getModifiedItems()) {

			Analysis analysis = analysisService.get(testResultItem.getAnalysisId());
			analysis.setStatusId(getStatusForTestResult(testResultItem));
			analysis.setSysUserId(getSysUserId(request));
			handleReferrals(testResultItem, analysis, actionDataSet);
			actionDataSet.getModifiedAnalysis().add(analysis);

			actionDataSet.addToNoteList(noteService.createSavableNote(analysis, NoteType.INTERNAL,
					testResultItem.getNote(), RESULT_SUBJECT, getSysUserId(request)));

			if (testResultItem.isShadowRejected()) {
				String rejectedReasonId = testResultItem.getRejectReasonId();
				for (IdValuePair rejectReason : DisplayListService.getInstance().getList(ListType.REJECTION_REASONS)) {
					if (rejectedReasonId.equals(rejectReason.getId())) {
						actionDataSet.addToNoteList(noteService.createSavableNote(analysis, NoteType.REJECTION_REASON,
								rejectReason.getValue(), RESULT_SUBJECT, getSysUserId(request)));
						break;
					}
				}
			}

			ResultSaveBean bean = ResultSaveBeanAdapter.fromTestResultItem(testResultItem);
			ResultSaveService resultSaveService = new ResultSaveService(analysis, getSysUserId(request));
			// deletable Results will be written to, not read
			List<Result> results = resultSaveService.createResultsFromTestResultItem(bean,
					actionDataSet.getDeletableResults());

			analysis.setCorrectedSincePatientReport(
					resultSaveService.isUpdatedResult() && analysisService.patientReportHasBeenDone(analysis));

			if (analysisService.hasBeenCorrectedSinceLastPatientReport(analysis)) {
				actionDataSet.addToNoteList(noteService.createSavableNote(analysis, NoteType.EXTERNAL,
						MessageUtil.getMessage("note.corrected.result"), RESULT_SUBJECT, getSysUserId(request)));
			}

			// If there is more than one result then each user selected reflex gets mapped
			// to that result
			for (Result result : results) {
				addResult(result, testResultItem, analysis, results.size() > 1, actionDataSet);

				if (analysisShouldBeUpdated(testResultItem, result)) {
					updateAnalysis(testResultItem, testResultItem.getTestDate(), analysis);
				}
			}
		}
	}

	private void handleReferrals(TestResultItem testResultItem, Analysis analysis, ResultsUpdateDataSet actionDataSet) {

		if (supportReferrals) {

			Referral referral = null;
			// referredOut means the referral checkbox was checked, repeating
			// analysis means that we have multi-select results, so only do one.
			if (testResultItem.isShadowReferredOut()
					&& !actionDataSet.getReferredAnalysisIds().contains(analysis.getId())) {
				actionDataSet.getReferredAnalysisIds().add(analysis.getId());
				// If it is a new result or there is no referral ID that means
				// that a new referral has to be created if it was checked and
				// it was canceled then we are un-canceling a canceled referral
				if (testResultItem.getResultId() == null
						|| GenericValidator.isBlankOrNull(testResultItem.getReferralId())) {
					referral = new Referral();
					referral.setReferralTypeId(REFERRAL_CONFORMATION_ID);
					referral.setSysUserId(getSysUserId(request));
					referral.setRequestDate(new Timestamp(new Date().getTime()));
					referral.setRequesterName(testResultItem.getTechnician());
					referral.setAnalysis(analysis);
					referral.setReferralReasonId(testResultItem.getReferralReasonId());
				} else if (testResultItem.isReferralCanceled()) {
					referral = referralService.get(testResultItem.getReferralId());
					referral.setCanceled(false);
					referral.setSysUserId(getSysUserId(request));
					referral.setRequesterName(testResultItem.getTechnician());
					referral.setReferralReasonId(testResultItem.getReferralReasonId());
				}

				actionDataSet.getSavableReferrals().add(referral);

			}
		}
	}

	protected boolean analysisShouldBeUpdated(TestResultItem testResultItem, Result result) {
		return result != null && !GenericValidator.isBlankOrNull(result.getValue())
				|| (supportReferrals && ResultUtil.isReferred(testResultItem))
				|| ResultUtil.isForcedToAcceptance(testResultItem) || testResultItem.isShadowRejected();
	}

	private void addResult(Result result, TestResultItem testResultItem, Analysis analysis,
			boolean multipleResultsForAnalysis, ResultsUpdateDataSet actionDataSet) {
		boolean newResult = result.getId() == null;
		boolean newAnalysisInLoop = analysis != actionDataSet.getPreviousAnalysis();

		ResultSignature technicianResultSignature = null;

		if (useTechnicianName && newAnalysisInLoop) {
			technicianResultSignature = createTechnicianSignatureFromResultItem(testResultItem);
		}

		ResultInventory testKit = createTestKitLinkIfNeeded(testResultItem, ResultsLoadUtility.TESTKIT);

		analysis.setReferredOut(testResultItem.isReferredOut());
		analysis.setEnteredDate(DateUtil.getNowAsTimestamp());

		if (newResult) {
			analysis.setEnteredDate(DateUtil.getNowAsTimestamp());
			analysis.setRevision("1");
		} else if (newAnalysisInLoop) {
			analysis.setRevision(String.valueOf(Integer.parseInt(analysis.getRevision()) + 1));
		}

		SampleService sampleService = SpringContext.getBean(SampleService.class);
		Sample sample = sampleService.getSampleByAccessionNumber(testResultItem.getAccessionNumber());
		Patient patient = sampleService.getPatient(sample);

		Map<String, List<String>> triggersToReflexesMap = new HashMap<>();

		getSelectedReflexes(testResultItem.getReflexJSONResult(), triggersToReflexesMap);

		if (newResult) {
			actionDataSet.getNewResults().add(new ResultSet(result, technicianResultSignature, testKit, patient, sample,
					triggersToReflexesMap, multipleResultsForAnalysis));
		} else {
			actionDataSet.getModifiedResults().add(new ResultSet(result, technicianResultSignature, testKit, patient,
					sample, triggersToReflexesMap, multipleResultsForAnalysis));
		}

		actionDataSet.setPreviousAnalysis(analysis);
	}

	private void getSelectedReflexes(String reflexJSONResult, Map<String, List<String>> triggersToReflexesMap) {
		if (!GenericValidator.isBlankOrNull(reflexJSONResult)) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject jsonResult = (JSONObject) parser.parse(reflexJSONResult.replaceAll("'", "\""));

				for (Object compoundReflexes : jsonResult.values()) {
					if (compoundReflexes != null) {
						String triggerIds = (String) ((JSONObject) compoundReflexes).get("triggerIds");
						List<String> selectedReflexIds = new ArrayList<>();
						JSONArray selectedReflexes = (JSONArray) ((JSONObject) compoundReflexes).get("selected");
						for (Object selectedReflex : selectedReflexes) {
							selectedReflexIds.add(((String) selectedReflex));
						}
						triggersToReflexesMap.put(triggerIds.trim(), selectedReflexIds);
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private String getStatusForTestResult(TestResultItem testResult) {
		if (testResult.isShadowRejected()) {
			return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected);
		} else if (alwaysValidate || !testResult.isValid() || ResultUtil.isForcedToAcceptance(testResult)) {
			return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
		} else if (noResults(testResult.getShadowResultValue(), testResult.getMultiSelectResultValues(),
				testResult.getResultType())) {
			return StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted);
		} else {
			ResultLimit resultLimit = resultLimitService.get(testResult.getResultLimitId());
			if (resultLimit != null && resultLimit.isAlwaysValidate()) {
				return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
			}

			return StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
		}
	}

	private boolean noResults(String value, String multiSelectValue, String type) {

		return (GenericValidator.isBlankOrNull(value) && GenericValidator.isBlankOrNull(multiSelectValue))
				|| (TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(type) && "0".equals(value));
	}

	private ResultInventory createTestKitLinkIfNeeded(TestResultItem testResult, String testKitName) {
		ResultInventory testKit = null;

		if ((TestResultItem.ResultDisplayType.SYPHILIS == testResult.getRawResultDisplayType()
				|| TestResultItem.ResultDisplayType.HIV == testResult.getRawResultDisplayType())
				&& ResultsLoadUtility.TESTKIT.equals(testKitName)) {

			testKit = createTestKit(testResult, testKitName, testResult.getTestKitId());
		}

		return testKit;
	}

	private ResultInventory createTestKit(TestResultItem testResult, String testKitName, String testKitId)
			throws LIMSRuntimeException {
		ResultInventory testKit;
		testKit = new ResultInventory();

		if (!GenericValidator.isBlankOrNull(testKitId)) {
			testKit.setId(testKitId);
			testKit = resultInventoryService.get(testKitId);
		}

		testKit.setInventoryLocationId(testResult.getTestKitInventoryId());
		testKit.setDescription(testKitName);
		testKit.setSysUserId(getSysUserId(request));
		return testKit;
	}

	private void updateAnalysis(TestResultItem testResultItem, String testDate, Analysis analysis) {
		if (testResultItem.getAnalysisMethod() != null) {
			analysis.setAnalysisType(testResultItem.getAnalysisMethod());
		}
		analysis.setStartedDateForDisplay(testDate);

		// This needs to be refactored -- part of the logic is in
		// getStatusForTestResult. RetroCI over rides to whatever was set before
		if (statusRuleSet.equals(STATUS_RULES_RETROCI)) {
			if (!StatusService.getInstance().getStatusID(AnalysisStatus.Canceled).equals(analysis.getStatusId())) {
				analysis.setCompletedDate(DateUtil.convertStringDateToSqlDate(testDate));
				analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance));
			}
		} else if (StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.Finalized)
				|| StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.TechnicalAcceptance)
				|| (analysis.isReferredOut()
						&& !GenericValidator.isBlankOrNull(testResultItem.getShadowResultValue()))) {
			analysis.setCompletedDate(DateUtil.convertStringDateToSqlDate(testDate));
		}

	}

	private ResultSignature createTechnicianSignatureFromResultItem(TestResultItem testResult) {
		ResultSignature sig = null;

		// The technician signature may be blank if the user changed a
		// conclusion and then changed it back. It will be dirty
		// but will not need a signature
		if (!GenericValidator.isBlankOrNull(testResult.getTechnician())) {
			sig = new ResultSignature();

			if (!GenericValidator.isBlankOrNull(testResult.getTechnicianSignatureId())) {
				sig = resultSigService.get(testResult.getTechnicianSignatureId());
			}

			sig.setIsSupervisor(false);
			sig.setNonUserName(testResult.getTechnician());

			sig.setSysUserId(getSysUserId(request));
		}
		return sig;
	}

	private String findLogBookForward(String forward) {
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/LogbookResults.do";
		} else if (FWD_VALIDATION_ERROR.equals(forward)) {
			return "resultsLogbookDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "resultsLogbookDefinition";
		} else {
			return "PageNotFound";
		}
	}

	private String findAccessionForward(String forward) {
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/AccessionResults.do";
		} else if (FWD_VALIDATION_ERROR.equals(forward)) {
			return "accessionResultDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "accessionResultDefinition";
		} else {
			return "PageNotFound";
		}
	}

	private String findPatientForward(String forward) {
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PatientResults.do";
		} else if (FWD_VALIDATION_ERROR.equals(forward)) {
			return "patientResultDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "patientResultDefinition";
		} else {
			return "PageNotFound";
		}
	}

	private String findStatusForward(String forward) {
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/StatusResults.do?blank=true";
		} else if (FWD_VALIDATION_ERROR.equals(forward)) {
			return "statusResultDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "statusResultDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "resultsLogbookDefinition";
		} else if (request.getRequestURL().indexOf("LogbookResults") >= 0) {
			return findLogBookForward(forward);
		} else if (request.getRequestURL().indexOf("AccessionResults") >= 0) {
			return findAccessionForward(forward);
		} else if (request.getRequestURL().indexOf("PatientResults") >= 0) {
			return findPatientForward(forward);
		} else if (request.getRequestURL().indexOf("StatusResults") >= 0) {
			return findStatusForward(forward);
		} else {
			return "PageNotFound";
		}
	}
}
