package spring.mine.resultvalidation.controller;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.validator.BaseErrors;
import spring.mine.internationalization.MessageUtil;
import spring.mine.resultvalidation.form.ResultValidationForm;
import spring.mine.resultvalidation.util.ResultValidationSaveService;
import spring.mine.resultvalidation.validator.ResultValidationFormValidator;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
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
import us.mn.state.health.lims.common.util.ConfigurationProperties;
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
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationUtility;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@Controller
public class ResultValidationController extends BaseResultValidationController {

	@Autowired
	ResultValidationFormValidator formValidator;

	// DAOs
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final TestResultDAO testResultDAO = new TestResultDAOImpl();
	private static final ResultDAO resultDAO = new ResultDAOImpl();
	private static final NoteDAO noteDAO = new NoteDAOImpl();
	private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static final DocumentTrackDAO documentTrackDAO = new DocumentTrackDAOImpl();

	private static final String RESULT_SUBJECT = "Result Note";
	private static final String RESULT_TABLE_ID;
	private static final String RESULT_REPORT_ID;

	static {
		RESULT_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("RESULT").getId();
		RESULT_REPORT_ID = new DocumentTypeDAOImpl().getDocumentTypeByName("resultExport").getId();
	}

	@RequestMapping(value = "/ResultValidation", method = RequestMethod.GET)
	public ModelAndView showResultValidation(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ResultValidationForm form = new ResultValidationForm();

		request.getSession().setAttribute(SAVE_DISABLED, "true");
		String testSectionId = (request.getParameter("testSectionId"));

		ResultValidationPaging paging = new ResultValidationPaging();
		String newPage = request.getParameter("page");

		TestSection ts = null;

		if (GenericValidator.isBlankOrNull(newPage)) {

			// load testSections for drop down
			TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
			PropertyUtils.setProperty(form, "testSections", DisplayListService.getList(ListType.TEST_SECTION));
			PropertyUtils.setProperty(form, "testSectionsByName",
					DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));

			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				ts = testSectionDAO.getTestSectionById(testSectionId);
				PropertyUtils.setProperty(form, "testSectionId", "0");
			}

			List<AnalysisItem> resultList;
			ResultsValidationUtility resultsValidationUtility = new ResultsValidationUtility();
			setRequestType(ts == null ? MessageUtil.getMessage("workplan.unit.types") : ts.getLocalizedName());
			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				resultList = resultsValidationUtility.getResultValidationList(getValidationStatus(), testSectionId);

			} else {
				resultList = new ArrayList<>();
			}
			paging.setDatabaseResults(request, form, resultList);

		} else {
			paging.page(request, form, newPage);
		}

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	public List<Integer> getValidationStatus() {
		List<Integer> validationStatus = new ArrayList<>();
		validationStatus
				.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
		if (ConfigurationProperties.getInstance()
				.isPropertyValueEqual(ConfigurationProperties.Property.VALIDATE_REJECTED_TESTS, "true")) {
			validationStatus
					.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
		}

		return validationStatus;
	}

	@RequestMapping(value = "/ResultValidation", method = RequestMethod.POST)
	public ModelAndView showResultValidationSave(HttpServletRequest request,
			@ModelAttribute("form") ResultValidationForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}
		String forward = FWD_SUCCESS_INSERT;
		List<IResultUpdate> updaters = ValidationUpdateRegister.getRegisteredUpdaters();
		boolean areListeners = updaters != null && !updaters.isEmpty();

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		ResultValidationPaging paging = new ResultValidationPaging();
		paging.updatePagedResults(request, form);
		List<AnalysisItem> resultItemList = paging.getResults(request);

		String testSectionName = (String) form.get("testSection");
		String testName = (String) form.get("testName");
		setRequestType(testSectionName);
		// ----------------------
		String url = request.getRequestURL().toString();

		Errors errors = validateModifiedItems(resultItemList);

		if (errors.hasErrors()) {
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			return findForward(FWD_VALIDATION_ERROR, form);
		}

		createSystemUser();

		// Update Lists
		List<Analysis> analysisUpdateList = new ArrayList<>();
		ArrayList<Sample> sampleUpdateList = new ArrayList<>();
		ArrayList<Note> noteUpdateList = new ArrayList<>();
		ArrayList<Result> resultUpdateList = new ArrayList<>();
		List<Result> deletableList = new ArrayList<>();

		// wrapper object for holding modifedResultSet and newResultSet
		IResultSaveService resultSaveService = new ResultValidationSaveService();

		if (testSectionName.equals("serology")) {
			createUpdateElisaList(resultItemList, analysisUpdateList);
		} else {
			createUpdateList(resultItemList, analysisUpdateList, deletableList, noteUpdateList, deletableList,
					resultSaveService, areListeners);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			ResultSaveService.removeDeletedResultsInTransaction(deletableList, getSysUserId(request));

			// update analysis
			for (Analysis analysis : analysisUpdateList) {
				analysisDAO.updateData(analysis);
			}

			for (Result resultUpdate : resultUpdateList) {
				if (resultUpdate.getId() != null) {
					resultDAO.updateData(resultUpdate);
				} else {
					resultDAO.insertData(resultUpdate);
				}
			}

			checkIfSamplesFinished(resultItemList, sampleUpdateList);

			// update finished samples
			for (Sample sample : sampleUpdateList) {
				sampleDAO.updateData(sample);
			}

			// create or update notes
			for (Note note : noteUpdateList) {
				if (note != null) {
					if (note.getId() == null) {
						noteDAO.insertData(note);
					} else {
						noteDAO.updateData(note);
					}
				}
			}

			for (IResultUpdate updater : updaters) {
				updater.transactionalUpdate(resultSaveService);
			}

			tx.commit();

		} catch (LIMSRuntimeException lre) {
			tx.rollback();
		}

		for (IResultUpdate updater : updaters) {

			updater.postTransactionalCommitUpdate(resultSaveService);
		}

		// route save back to RetroC specific ResultValidationRetroCAction
		// if
		// (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName,
		// "CI RetroCI"))
		if (url.contains("RetroC")) {
			forward = "successRetroC";
		}

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		if (isBlankOrNull(testSectionName)) {
			return findForward(forward, form);
		} else {
			Map<String, String> params = new HashMap<>();
			params.put("type", testSectionName);
			params.put("test", testName);
			params.put("forward", forward);

			return getForwardWithParameters(findForward(forward, form), params);
		}
	}

	private Errors validateModifiedItems(List<AnalysisItem> resultItemList) {
		Errors errors = new BaseErrors();

		for (AnalysisItem item : resultItemList) {
			Errors errorList = new BaseErrors();
			validateQuantifiableItems(item, errorList);

			if (errorList.hasErrors()) {
				StringBuilder augmentedAccession = new StringBuilder(item.getAccessionNumber());
				augmentedAccession.append(" : ");
				augmentedAccession.append(item.getTestName());
				String errorMsg = "errors.followingAccession";
				errors.reject(errorMsg, new String[] { augmentedAccession.toString() }, errorMsg);
				errors.addAllErrors(errorList);

			}
		}

		return errors;
	}

	public void validateQuantifiableItems(AnalysisItem analysisItem, Errors errorList) {
		if (analysisItem.isHasQualifiedResult() && isBlankOrNull(analysisItem.getQualifiedResultValue())
				&& analysisItemWillBeUpdated(analysisItem)) {
			errorList.reject("errors.missing.result.details", new String[] { "Result" },
					"errors.missing.result.details");
		}
		// verify that qualifiedResultValue has been entered if required
		if (!isBlankOrNull(analysisItem.getQualifiedDictionaryId())) {
			String[] qualifiedDictionaryIds = analysisItem.getQualifiedDictionaryId().replace("[", "").replace("]", "")
					.split(",");
			Set<String> qualifiedDictIdsSet = new HashSet<>(Arrays.asList(qualifiedDictionaryIds));

			if (qualifiedDictIdsSet.contains(analysisItem.getResult())
					&& isBlankOrNull(analysisItem.getQualifiedResultValue())) {
				errorList.reject("errors.missing.result.details", new String[] { "Result" },
						"errors.missing.result.details");

			}

		}

	}

	private void createUpdateList(List<AnalysisItem> analysisItems, List<Analysis> analysisUpdateList,
			List<Result> resultUpdateList, List<Note> noteUpdateList, List<Result> deletableList,
			IResultSaveService resultValidationSave, boolean areListeners) {

		List<String> analysisIdList = new ArrayList<>();

		for (AnalysisItem analysisItem : analysisItems) {
			if (!analysisItem.isReadOnly() && analysisItemWillBeUpdated(analysisItem)) {

				AnalysisService analysisService = new AnalysisService(analysisItem.getAnalysisId());
				Analysis analysis = analysisService.getAnalysis();
				NoteService noteService = new NoteService(analysis);

				analysis.setSysUserId(getSysUserId(request));

				if (!analysisIdList.contains(analysis.getId())) {

					if (analysisItem.getIsAccepted()) {
						analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized));
						analysis.setReleasedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
						analysisIdList.add(analysis.getId());
						analysisUpdateList.add(analysis);
					}

					if (analysisItem.getIsRejected()) {
						analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected));
						analysisIdList.add(analysis.getId());
						analysisUpdateList.add(analysis);
					}
				}

				createNeededNotes(analysisItem, noteService, noteUpdateList);

				if (areResults(analysisItem)) {
					List<Result> results = createResultFromAnalysisItem(analysisItem, analysisService, noteService,
							noteUpdateList, deletableList);
					for (Result result : results) {
						resultUpdateList.add(result);

						if (areListeners) {
							addResultSets(analysis, result, resultValidationSave);
						}
					}
				}
			}
		}
	}

	private void createNeededNotes(AnalysisItem analysisItem, NoteService noteService, List<Note> noteUpdateList) {
		if (analysisItem.getIsRejected()) {
			Note note = noteService.createSavableNote(NoteType.INTERNAL,
					MessageUtil.getMessage("validation.note.retest"), RESULT_SUBJECT, getSysUserId(request));
			noteUpdateList.add(note);
		}

		if (!GenericValidator.isBlankOrNull(analysisItem.getNote())) {
			NoteType noteType = analysisItem.getIsAccepted() ? NoteType.EXTERNAL : NoteType.INTERNAL;
			Note note = noteService.createSavableNote(noteType, analysisItem.getNote(), RESULT_SUBJECT,
					getSysUserId(request));
			noteUpdateList.add(note);
		}
	}

	private void addResultSets(Analysis analysis, Result result, IResultSaveService resultValidationSave) {
		Sample sample = analysis.getSampleItem().getSample();
		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		if (finalResultAlreadySent(result)) {
			result.setResultEvent(Event.CORRECTION);
			resultValidationSave.getModifiedResults()
					.add(new ResultSet(result, null, null, patient, sample, null, false));
		} else {
			result.setResultEvent(Event.FINAL_RESULT);
			resultValidationSave.getNewResults().add(new ResultSet(result, null, null, patient, sample, null, false));
		}
	}

	// TO DO bug falsely triggered when preliminary result is sent, fails, retries
	// and succeeds
	private boolean finalResultAlreadySent(Result result) {
		List<DocumentTrack> documents = documentTrackDAO.getByTypeRecordAndTable(RESULT_REPORT_ID, RESULT_TABLE_ID,
				result.getId());
		return documents.size() > 0;
	}

	private boolean analysisItemWillBeUpdated(AnalysisItem analysisItem) {
		return analysisItem.getIsAccepted() || analysisItem.getIsRejected();
	}

	private void createUpdateElisaList(List<AnalysisItem> resultItems, List<Analysis> analysisUpdateList) {

		for (AnalysisItem resultItem : resultItems) {

			if (resultItem.getIsAccepted()) {

				List<Analysis> acceptedAnalysisList = createAnalysisFromElisaAnalysisItem(resultItem);

				for (Analysis analysis : acceptedAnalysisList) {
					analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized));
					analysisUpdateList.add(analysis);
				}
			}

			if (resultItem.getIsRejected()) {
				List<Analysis> rejectedAnalysisList = createAnalysisFromElisaAnalysisItem(resultItem);

				for (Analysis analysis : rejectedAnalysisList) {
					analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected));
					analysisUpdateList.add(analysis);
				}

			}
		}
	}

	private List<Analysis> createAnalysisFromElisaAnalysisItem(AnalysisItem analysisItem) {

		List<Analysis> analysisList = new ArrayList<>();

		Analysis analysis = new Analysis();

		if (!isBlankOrNull(analysisItem.getMurexResult())) {
			analysis = getAnalysisFromId(analysisItem.getMurexAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getBiolineResult())) {
			analysis = getAnalysisFromId(analysisItem.getBiolineAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getIntegralResult())) {
			analysis = getAnalysisFromId(analysisItem.getIntegralAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getVironostikaResult())) {
			analysis = getAnalysisFromId(analysisItem.getVironostikaAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getGenieIIResult())) {
			analysis = getAnalysisFromId(analysisItem.getGenieIIAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getGenieII10Result())) {
			analysis = getAnalysisFromId(analysisItem.getGenieII10AnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getGenieII100Result())) {
			analysis = getAnalysisFromId(analysisItem.getGenieII100AnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getWesternBlot1Result())) {
			analysis = getAnalysisFromId(analysisItem.getWesternBlot1AnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getWesternBlot2Result())) {
			analysis = getAnalysisFromId(analysisItem.getWesternBlot2AnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getP24AgResult())) {
			analysis = getAnalysisFromId(analysisItem.getP24AgAnalysisId());
			analysisList.add(analysis);
		}
		if (!isBlankOrNull(analysisItem.getInnoliaResult())) {
			analysis = getAnalysisFromId(analysisItem.getInnoliaAnalysisId());
			analysisList.add(analysis);
		}

		analysisList.add(analysis);

		return analysisList;
	}

	private void checkIfSamplesFinished(List<AnalysisItem> resultItemList, List<Sample> sampleUpdateList) {
		String currentSampleId = "";
		boolean sampleFinished = true;
		List<Integer> sampleFinishedStatus = getSampleFinishedStatuses();

		for (AnalysisItem analysisItem : resultItemList) {

			String analysisSampleId = sampleDAO.getSampleByAccessionNumber(analysisItem.getAccessionNumber()).getId();
			if (!analysisSampleId.equals(currentSampleId)) {

				currentSampleId = analysisSampleId;

				List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(currentSampleId);

				for (Analysis analysis : analysisList) {
					if (!sampleFinishedStatus.contains(Integer.parseInt(analysis.getStatusId()))) {
						sampleFinished = false;
						break;
					}
				}

				if (sampleFinished) {
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

	private Analysis getAnalysisFromId(String id) {
		Analysis analysis = new Analysis();
		analysis.setId(id);
		analysisDAO.getData(analysis);
		analysis.setSysUserId(getSysUserId(request));

		return analysis;
	}

	private List<Result> createResultFromAnalysisItem(AnalysisItem analysisItem, AnalysisService analysisService,
			NoteService noteService, List<Note> noteUpdateList, List<Result> deletableList) {

		ResultSaveBean bean = ResultSaveBeanAdapter.fromAnalysisItem(analysisItem);
		ResultSaveService resultSaveService = new ResultSaveService(analysisService.getAnalysis(),
				getSysUserId(request));
		List<Result> results = resultSaveService.createResultsFromTestResultItem(bean, deletableList);
		if (analysisService.patientReportHasBeenDone() && resultSaveService.isUpdatedResult()) {
			analysisService.getAnalysis().setCorrectedSincePatientReport(true);
			noteUpdateList.add(noteService.createSavableNote(NoteType.EXTERNAL,
					MessageUtil.getMessage("note.corrected.result"), RESULT_SUBJECT, getSysUserId(request)));
		}
		return results;
	}

	protected TestResult getTestResult(AnalysisItem analysisItem) {
		TestResult testResult = null;
		if (TypeOfTestResultService.ResultType.DICTIONARY.matches(analysisItem.getResultType())) {
			testResult = testResultDAO.getTestResultsByTestAndDictonaryResult(analysisItem.getTestId(),
					analysisItem.getResult());
		} else {
			List<TestResult> testResultList = testResultDAO.getActiveTestResultsByTest(analysisItem.getTestId());
			// we are assuming there is only one testResult for a numeric type
			// result
			if (!testResultList.isEmpty()) {
				testResult = testResultList.get(0);
			}
		}
		return testResult;
	}

	private boolean areResults(AnalysisItem item) {
		return !(isBlankOrNull(item.getResult())
				|| (TypeOfTestResultService.ResultType.DICTIONARY.matches(item.getResultType())
						&& "0".equals(item.getResult())))
				|| (TypeOfTestResultService.ResultType.isMultiSelectVariant(item.getResultType())
						&& !isBlankOrNull(item.getMultiSelectResultValues()));
	}

	private SystemUser createSystemUser() {
		SystemUser systemUser = new SystemUser();
		systemUser.setId(getSysUserId(request));
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUserDAO.getData(systemUser);
		return systemUser;
	}

	private List<Integer> getSampleFinishedStatuses() {
		ArrayList<Integer> sampleFinishedStatus = new ArrayList<>();
		sampleFinishedStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));
		sampleFinishedStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
		sampleFinishedStatus.add(
				Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated)));
		return sampleFinishedStatus;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "resultValidationDefinition";
		} else if ("elisaSuccess".equals(forward)) {
			return "elisaAlgorithmResultValidationDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/ResultValidation.do?forward=success";
		} else if ("successRetroC".equals(forward)) {
			return "redirect:/ResultValidationRetroC.do?forward=success";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_VALIDATION_ERROR.equals(forward)) {
			return "resultValidationDefinition";
		} else {
			return "PageNotFound";
		}
	}
}
