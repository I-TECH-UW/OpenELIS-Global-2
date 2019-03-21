package spring.mine.result.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.internationalization.MessageUtil;
import spring.mine.result.form.LogbookResultsForm;
import spring.mine.result.validator.LogbookResultsFormValidator;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.NoteService.NoteType;
import us.mn.state.health.lims.common.services.ResultSaveService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.services.beanAdapters.ResultSaveBeanAdapter;
import us.mn.state.health.lims.common.services.registration.ResultUpdateRegister;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.services.serviceBeans.ResultSaveBean;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.dao.ReferralTypeDAO;
import us.mn.state.health.lims.referral.daoimpl.ReferralDAOImpl;
import us.mn.state.health.lims.referral.daoimpl.ReferralResultDAOImpl;
import us.mn.state.health.lims.referral.daoimpl.ReferralTypeDAOImpl;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.referral.valueholder.ReferralType;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.result.action.util.ResultsUpdateDataSet;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultInventoryDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultSignatureDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;
import us.mn.state.health.lims.result.valueholder.ResultSignature;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.statusofsample.util.StatusRules;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testreflex.action.util.TestReflexBean;
import us.mn.state.health.lims.testreflex.action.util.TestReflexUtil;

@Controller
public class LogbookResultsController extends LogbookResultsBaseController {

	@Autowired
	LogbookResultsFormValidator formValidator;

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

	private boolean useTechnicianName = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.resultTechnicianName, "true");
	private boolean alwaysValidate = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.ALWAYS_VALIDATE_RESULTS, "true");
	private boolean supportReferrals = FormFields.getInstance().useField(Field.ResultsReferral);
	private String statusRuleSet = ConfigurationProperties.getInstance()
			.getPropertyValueUpperCase(Property.StatusRules);

	static {
		ReferralTypeDAO referralTypeDAO = new ReferralTypeDAOImpl();
		ReferralType referralType = referralTypeDAO.getReferralTypeByName("Confirmation");
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
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "rejectReasons",
				DisplayListService.getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		// load testSections for drop down
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<IdValuePair> testSections = DisplayListService.getList(ListType.TEST_SECTION);
		PropertyUtils.setProperty(form, "testSections", testSections);
		PropertyUtils.setProperty(form, "testSectionsByName",
				DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionDAO.getTestSectionById(testSectionId);
			PropertyUtils.setProperty(form, "testSectionId", "0");
		}

		setRequestType(ts == null ? MessageUtil.getMessage("workplan.unit.types") : ts.getLocalizedName());

		List<TestResultItem> tests;

		ResultsPaging paging = new ResultsPaging();
		List<InventoryKitItem> inventoryList = new ArrayList<>();
		ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility(getSysUserId(request));

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
				InventoryUtility inventoryUtility = new InventoryUtility();
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
			@ModelAttribute("form") LogbookResultsForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		formValidator.validate(form, result);
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

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (Note note : actionDataSet.getNoteList()) {
				noteDAO.insertData(note);
			}

			for (ResultSet resultSet : actionDataSet.getNewResults()) {
				resultSet.result.setResultEvent(Event.PRELIMINARY_RESULT);
				resultDAO.insertData(resultSet.result);
				if (resultSet.signature != null) {
					resultSet.signature.setResultId(resultSet.result.getId());
					resultSigDAO.insertData(resultSet.signature);
				}

				if (resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null) {
					resultSet.testKit.setResultId(resultSet.result.getId());
					resultInventoryDAO.insertData(resultSet.testKit);
				}
			}

			for (Referral referral : actionDataSet.getSavableReferrals()) {
				if (referral != null) {
					saveReferralsWithRequiredObjects(referral);
				}
			}

			for (ResultSet resultSet : actionDataSet.getModifiedResults()) {
				resultSet.result.setResultEvent(Event.RESULT);
				resultDAO.updateData(resultSet.result);

				if (resultSet.signature != null) {
					resultSet.signature.setResultId(resultSet.result.getId());
					if (resultSet.alwaysInsertSignature) {
						resultSigDAO.insertData(resultSet.signature);
					} else {
						resultSigDAO.updateData(resultSet.signature);
					}
				}

				if (resultSet.testKit != null && resultSet.testKit.getInventoryLocationId() != null) {
					resultSet.testKit.setResultId(resultSet.result.getId());
					if (resultSet.testKit.getId() == null) {
						resultInventoryDAO.insertData(resultSet.testKit);
					} else {
						resultInventoryDAO.updateData(resultSet.testKit);
					}
				}
			}

			for (Analysis analysis : actionDataSet.getModifiedAnalysis()) {
				analysisDAO.updateData(analysis);
			}

			ResultSaveService.removeDeletedResultsInTransaction(actionDataSet.getDeletableResults(),
					getSysUserId(request));

			setTestReflexes(actionDataSet);

			setSampleStatus(actionDataSet);

			for (IResultUpdate updater : updaters) {
				updater.transactionalUpdate(actionDataSet);
			}

			tx.commit();

		} catch (LIMSRuntimeException lre) {
			tx.rollback();

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

	private void saveReferralsWithRequiredObjects(Referral referral) {
		if (referral.getId() != null) {
			referralDAO.updateData(referral);
		} else {
			referralDAO.insertData(referral);
			ReferralResult referralResult = new ReferralResult();
			referralResult.setReferralId(referral.getId());
			referralResult.setSysUserId(getSysUserId(request));
			referralResultDAO.insertData(referralResult);
		}
	}

	protected void setTestReflexes(ResultsUpdateDataSet actionDataSet) {
		TestReflexUtil testReflexUtil = new TestReflexUtil();
		testReflexUtil.setCurrentUserId(getSysUserId(request));
		testReflexUtil.addNewTestsToDBForReflexTests(convertToTestReflexBeanList(actionDataSet.getNewResults()));
		testReflexUtil.updateModifiedReflexes(convertToTestReflexBeanList(actionDataSet.getModifiedResults()));
	}

	private List<TestReflexBean> convertToTestReflexBeanList(List<ResultSet> resultSetList) {
		List<TestReflexBean> reflexBeanList = new ArrayList<>();

		for (ResultSet resultSet : resultSetList) {
			TestReflexBean reflex = new TestReflexBean();
			reflex.setPatient(resultSet.patient);

			if (resultSet.triggersToSelectedReflexesMap.size() > 0 && resultSet.multipleResultsForAnalysis) {
				for (String trigger : resultSet.triggersToSelectedReflexesMap.keySet()) {
					if (trigger.equals(resultSet.result.getValue())) {
						HashMap<String, List<String>> reducedMap = new HashMap<>(1);
						reducedMap.put(trigger, resultSet.triggersToSelectedReflexesMap.get(trigger));
						reflex.setTriggersToSelectedReflexesMap(reducedMap);
					}
				}
				if (reflex.getTriggersToSelectedReflexesMap() == null) {
					reflex.setTriggersToSelectedReflexesMap(new HashMap<String, List<String>>());
				}
			} else {
				reflex.setTriggersToSelectedReflexesMap(resultSet.triggersToSelectedReflexesMap);
			}

			reflex.setResult(resultSet.result);
			reflex.setSample(resultSet.sample);
			reflexBeanList.add(reflex);
		}

		return reflexBeanList;
	}

	private void setSampleStatus(ResultsUpdateDataSet actionDataSet) {
		Set<Sample> sampleSet = new HashSet<>();

		for (ResultSet resultSet : actionDataSet.getNewResults()) {
			sampleSet.add(resultSet.sample);
		}

		String sampleTestingStartedId = StatusService.getInstance().getStatusID(OrderStatus.Started);
		String sampleNonConformingId = StatusService.getInstance().getStatusID(OrderStatus.NonConforming_depricated);

		for (Sample sample : sampleSet) {
			if (!(sample.getStatusId().equals(sampleNonConformingId)
					|| sample.getStatusId().equals(sampleTestingStartedId))) {
				Sample newSample = new Sample();
				newSample.setId(sample.getId());
				sampleDAO.getData(newSample);

				newSample.setStatusId(sampleTestingStartedId);
				newSample.setSysUserId(getSysUserId(request));
				sampleDAO.updateData(newSample);
			}
		}
	}

	private void createAnalysisOnlyUpdates(ResultsUpdateDataSet actionDataSet) {
		for (TestResultItem testResultItem : actionDataSet.getAnalysisOnlyChangeResults()) {
			AnalysisService analysisService = new AnalysisService(testResultItem.getAnalysisId());
			analysisService.getAnalysis().setSysUserId(getSysUserId(request));
			analysisService.getAnalysis()
					.setCompletedDate(DateUtil.convertStringDateToSqlDate(testResultItem.getTestDate()));
			if (testResultItem.getAnalysisMethod() != null) {
				analysisService.getAnalysis().setAnalysisType(testResultItem.getAnalysisMethod());
			}
			actionDataSet.getModifiedAnalysis().add(analysisService.getAnalysis());
		}
	}

	private void createResultsFromItems(ResultsUpdateDataSet actionDataSet) {

		for (TestResultItem testResultItem : actionDataSet.getModifiedItems()) {
			AnalysisService analysisService = new AnalysisService(testResultItem.getAnalysisId());
			analysisService.getAnalysis().setStatusId(getStatusForTestResult(testResultItem));
			analysisService.getAnalysis().setSysUserId(getSysUserId(request));
			handleReferrals(testResultItem, analysisService.getAnalysis(), actionDataSet);
			actionDataSet.getModifiedAnalysis().add(analysisService.getAnalysis());

			NoteService noteService = new NoteService(analysisService.getAnalysis());
			actionDataSet.addToNoteList(noteService.createSavableNote(NoteType.INTERNAL, testResultItem.getNote(),
					RESULT_SUBJECT, getSysUserId(request)));

			if (testResultItem.isShadowRejected()) {
				String rejectedReasonId = testResultItem.getRejectReasonId();
				for (IdValuePair rejectReason : DisplayListService.getList(ListType.REJECTION_REASONS)) {
					if (rejectedReasonId.equals(rejectReason.getId())) {
						actionDataSet.addToNoteList(noteService.createSavableNote(NoteType.REJECTION_REASON,
								rejectReason.getValue(), RESULT_SUBJECT, getSysUserId(request)));
						break;
					}
				}
			}

			ResultSaveBean bean = ResultSaveBeanAdapter.fromTestResultItem(testResultItem);
			ResultSaveService resultSaveService = new ResultSaveService(analysisService.getAnalysis(),
					getSysUserId(request));
			// deletable Results will be written to, not read
			List<Result> results = resultSaveService.createResultsFromTestResultItem(bean,
					actionDataSet.getDeletableResults());

			analysisService.getAnalysis().setCorrectedSincePatientReport(
					resultSaveService.isUpdatedResult() && analysisService.patientReportHasBeenDone());

			if (analysisService.hasBeenCorrectedSinceLastPatientReport()) {
				actionDataSet.addToNoteList(noteService.createSavableNote(NoteType.EXTERNAL,
						StringUtil.getMessageForKey("note.corrected.result"), RESULT_SUBJECT, getSysUserId(request)));
			}

			// If there is more than one result then each user selected reflex gets mapped
			// to that result
			for (Result result : results) {
				addResult(result, testResultItem, analysisService.getAnalysis(), results.size() > 1, actionDataSet);

				if (analysisShouldBeUpdated(testResultItem, result)) {
					updateAnalysis(testResultItem, testResultItem.getTestDate(), analysisService.getAnalysis());
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
					referral = referralDAO.getReferralById(testResultItem.getReferralId());
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

		SampleService sampleService = new SampleService(testResultItem.getAccessionNumber());
		Patient patient = sampleService.getPatient();

		Map<String, List<String>> triggersToReflexesMap = new HashMap<>();

		getSelectedReflexes(testResultItem.getReflexJSONResult(), triggersToReflexesMap);

		if (newResult) {
			actionDataSet.getNewResults().add(new ResultSet(result, technicianResultSignature, testKit, patient,
					sampleService.getSample(), triggersToReflexesMap, multipleResultsForAnalysis));
		} else {
			actionDataSet.getModifiedResults().add(new ResultSet(result, technicianResultSignature, testKit, patient,
					sampleService.getSample(), triggersToReflexesMap, multipleResultsForAnalysis));
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
			ResultLimit resultLimit = resultLimitDAO.getResultLimitById(testResult.getResultLimitId());
			if (resultLimit != null && resultLimit.isAlwaysValidate()) {
				return StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
			}

			return StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
		}
	}

	private boolean noResults(String value, String multiSelectValue, String type) {

		return (GenericValidator.isBlankOrNull(value) && GenericValidator.isBlankOrNull(multiSelectValue))
				|| (TypeOfTestResultService.ResultType.DICTIONARY.matches(type) && "0".equals(value));
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
			resultInventoryDAO.getData(testKit);
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
				sig.setId(testResult.getTechnicianSignatureId());
				resultSigDAO.getData(sig);
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
