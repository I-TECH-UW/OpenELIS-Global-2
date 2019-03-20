package spring.mine.referral.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
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

import spring.mine.common.controller.BaseController;
import spring.mine.referral.form.ReferredOutTestsForm;
import spring.mine.referral.validator.ReferredOutTestsFormValidator;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.referral.action.beanitems.IReferralResultTest;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;
import us.mn.state.health.lims.referral.action.beanitems.ReferredTest;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.daoimpl.ReferralDAOImpl;
import us.mn.state.health.lims.referral.daoimpl.ReferralResultDAOImpl;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class ReferredOutTestsController extends BaseController {

	@Autowired
	ReferredOutTestsFormValidator formValidator;

	private final ReferralDAO referralDAO = new ReferralDAOImpl();
	private final ReferralResultDAO referralResultDAO = new ReferralResultDAOImpl();
	private final OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	private final ResultDAO resultDAO = new ResultDAOImpl();
	private final SampleDAO sampleDAO = new SampleDAOImpl();
	private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private final NoteDAO noteDAO = new NoteDAOImpl();
	private final TestResultDAO testResultDAO = new TestResultDAOImpl();

	private static final String RESULT_SUBJECT = "Result Note";
	private TestDAO testDAO = new TestDAOImpl();
	private SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

	@RequestMapping(value = "/ReferredOutTests", method = RequestMethod.GET)
	public ModelAndView showReferredOutTests(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		ReferredOutTestsForm form = new ReferredOutTestsForm();
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		List<ReferralItem> referralItems = getReferralItems();
		PropertyUtils.setProperty(form, "referralItems", referralItems);
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "referralOrganizations",
				DisplayListService.getListWithLeadingBlank(DisplayListService.ListType.REFERRAL_ORGANIZATIONS));

		fillInDictionaryValuesForReferralItems(referralItems);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private void fillInDictionaryValuesForReferralItems(List<ReferralItem> referralItems) {
		List<NonNumericTests> nonNumericTests = getNonNumericTests(referralItems);
		for (ReferralItem referralItem : referralItems) {
			String referredResultType = referralItem.getReferredResultType();
			if (TypeOfTestResultService.ResultType.isDictionaryVariant(referredResultType)) {
				referralItem.setDictionaryResults(
						getDictionaryValuesForTest(referralItem.getReferredTestId(), nonNumericTests));
			}

			if (referralItem.getAdditionalTests() != null) {
				for (ReferredTest test : referralItem.getAdditionalTests()) {
					if (TypeOfTestResultService.ResultType.isDictionaryVariant(test.getReferredResultType())) {
						test.setDictionaryResults(
								getDictionaryValuesForTest(test.getReferredTestId(), nonNumericTests));
					}
				}
			}
		}

	}

	private List<ReferralItem> getReferralItems() {
		List<ReferralItem> referralItems = new ArrayList<>();
		ReferralDAO referralDAO = new ReferralDAOImpl();

		List<Referral> referralList = referralDAO.getAllUncanceledOpenReferrals();

		for (Referral referral : referralList) {
			ReferralItem referralItem = getReferralItem(referral);
			if (referralItem != null) {
				referralItems.add(referralItem);
			}
		}

		Collections.sort(referralItems, new ReferralComparator());

		return referralItems;
	}

	private final static class ReferralComparator implements Comparator<ReferralItem> {
		@Override
		public int compare(ReferralItem left, ReferralItem right) {
			int result = left.getAccessionNumber().compareTo(right.getAccessionNumber());
			if (result != 0) {
				return result;
			}
			result = left.getSampleType().compareTo(right.getSampleType());
			if (result != 0) {
				return result;
			}
			return left.getReferringTestName().compareTo(right.getReferringTestName());
		}
	}

	private ReferralItem getReferralItem(Referral referral) {
		boolean allReferralResultsHaveResults = true;
		List<ReferralResult> referralResults = referralResultDAO.getReferralResultsForReferral(referral.getId());
		for (ReferralResult referralResult : referralResults) {
			if (referralResult.getResult() == null
					|| GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
				allReferralResultsHaveResults = false;
				break;
			}
		}

		if (allReferralResultsHaveResults) {
			return null;
		}

		ReferralItem referralItem = new ReferralItem();

		AnalysisService analysisService = new AnalysisService(referral.getAnalysis());

		referralItem.setCanceled(false);
		referralItem.setReferredResultType("N");
		referralItem.setAccessionNumber(analysisService.getOrderAccessionNumber());

		TypeOfSample typeOfSample = analysisService.getTypeOfSample();
		referralItem.setSampleType(typeOfSample.getLocalizedName());

		referralItem
				.setReferringTestName(TestService.getUserLocalizedTestName(analysisService.getAnalysis().getTest()));
		List<Result> resultList = analysisService.getResults();
		String resultString = "";

		if (!resultList.isEmpty()) {
			Result result = resultList.get(0);
			resultString = getAppropriateResultValue(resultList);
			referralItem.setInLabResultId(result.getId());
		}

		referralItem.setReferralId(referral.getId());
		if (!referralResults.isEmpty()) {
			referralResults = setReferralItemForNextTest(referralItem, referralResults);
			if (!referralResults.isEmpty()) {
				referralItem.setAdditionalTests(getAdditionalReferralTests(referralResults));
			}
		}
		referralItem.setReferralResults(resultString);
		referralItem.setReferralDate(DateUtil.convertTimestampToStringDate(referral.getRequestDate()));
		referralItem.setReferredSendDate(getSendDateOrDefault(referral));
		referralItem.setReferrer(referral.getRequesterName());
		referralItem.setReferralReasonId(referral.getReferralReasonId());
		referralItem.setTestSelectionList(getTestsForTypeOfSample(typeOfSample));
		referralItem.setReferralId(referral.getId());
		if (referral.getOrganization() != null) {
			referralItem.setReferredInstituteId(referral.getOrganization().getId());
		}
		String notes = analysisService.getNotesAsString(true, true, "<br/>", false);
		if (notes != null) {
			referralItem.setPastNotes(notes);
		}

		return referralItem;
	}

	private String getSendDateOrDefault(Referral referral) {
		if (referral.getSentDate() == null) {
			return DateUtil.getCurrentDateAsText();
		} else {
			return DateUtil.convertTimestampToStringDate(referral.getSentDate());
		}
	}

	private List<ReferredTest> getAdditionalReferralTests(List<ReferralResult> referralResults) {
		List<ReferredTest> additionalTestList = new ArrayList<>();

		while (!referralResults.isEmpty()) {
			ReferralResult referralResult = referralResults.get(0); // use the top one to load various bits of
																	// information.
			ReferredTest referralTest = new ReferredTest();
			referralTest.setReferralId(referralResult.getReferralId());
			referralResults = setReferralItemForNextTest(referralTest, referralResults); // remove one or more
																							// referralResults from the
																							// list as needed (for
																							// multiResults).
			referralTest.setReferredReportDate(
					DateUtil.convertTimestampToStringDate(referralResult.getReferralReportDate()));
			referralTest.setReferralResultId(referralResult.getId());
			additionalTestList.add(referralTest);
		}
		return additionalTestList;
	}

	/**
	 * Move everything appropriate to the referralItem including one or more of the
	 * referralResults from the given list. Note: This method removes an item from
	 * the referralResults list.
	 *
	 * @param referralItem    The source item
	 * @param referralResults The created list
	 */
	private List<ReferralResult> setReferralItemForNextTest(IReferralResultTest referralItem,
			List<ReferralResult> referralResults) {

		ReferralResult nextTestFirstResult = referralResults.remove(0);
		List<ReferralResult> resultsForOtherTests = new ArrayList<>(referralResults);

		referralItem.setReferredTestId(nextTestFirstResult.getTestId());
		referralItem.setReferredTestIdShadow(referralItem.getReferredTestId());
		referralItem.setReferredReportDate(
				DateUtil.convertTimestampToStringDate(nextTestFirstResult.getReferralReportDate()));
		// We can not use ResultService because that assumes the result is for an
		// analysis, not a referral
		Result result = nextTestFirstResult.getResult();

		String resultType = (result != null) ? result.getResultType() : "N";
		referralItem.setReferredResultType(resultType);
		if (!TypeOfTestResultService.ResultType.isMultiSelectVariant(resultType)) {
			if (result != null) {
				String resultValue = GenericValidator.isBlankOrNull(result.getValue()) ? "" : result.getValue();
				referralItem.setReferredResult(resultValue);
				referralItem.setReferredDictionaryResult(resultValue);
			}
		} else {
			ArrayList<Result> resultList = new ArrayList<>();
			resultList.add(nextTestFirstResult.getResult());

			for (ReferralResult referralResult : referralResults) {
				if (nextTestFirstResult.getTestId().equals(referralResult.getTestId())
						&& !GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
					resultList.add(referralResult.getResult());
					resultsForOtherTests.remove(referralResult);
				}
			}

			referralItem.setMultiSelectResultValues(ResultService.getJSONStringForMultiSelect(resultList));
		}

		return resultsForOtherTests;
	}

	private List<IdValuePair> getDictionaryValuesForTest(String testId, List<NonNumericTests> nonNumericTests) {
		if (!GenericValidator.isBlankOrNull(testId)) {
			for (NonNumericTests test : nonNumericTests) {
				if (testId.equals(test.testId)) {
					return test.dictionaryValues;
				}
			}
		}
		return new ArrayList<>();
	}

	private String getAppropriateResultValue(List<Result> results) {
		Result result = results.get(0);
		if (TypeOfTestResultService.ResultType.DICTIONARY.matches(result.getResultType())) {
			Dictionary dictionary = dictionaryDAO.getDictionaryById(result.getValue());
			if (dictionary != null) {
				return dictionary.getLocalizedName();
			}
		} else if (TypeOfTestResultService.ResultType.isMultiSelectVariant(result.getResultType())) {
			Dictionary dictionary = new Dictionary();
			StringBuilder multiResult = new StringBuilder();

			for (Result subResult : results) {
				dictionary.setId(subResult.getValue());
				dictionaryDAO.getData(dictionary);

				if (dictionary.getId() != null) {
					multiResult.append(dictionary.getLocalizedName());
					multiResult.append(", ");
				}
			}

			if (multiResult.length() > 0) {
				multiResult.setLength(multiResult.length() - 2); // remove last ", "
			}

			return multiResult.toString();
		} else {
			String resultValue = GenericValidator.isBlankOrNull(result.getValue()) ? "" : result.getValue();

			if (!GenericValidator.isBlankOrNull(resultValue)
					&& result.getAnalysis().getTest().getUnitOfMeasure() != null) {
				resultValue += " " + result.getAnalysis().getTest().getUnitOfMeasure().getName();
			}

			return resultValue;
		}

		return "";
	}

	private List<IdValuePair> getTestsForTypeOfSample(TypeOfSample typeOfSample) {
		List<Test> testList = TypeOfSampleService.getActiveTestsBySampleTypeId(typeOfSample.getId(), false);

		List<IdValuePair> valueList = new ArrayList<>();

		for (Test test : testList) {
			if (test.getOrderable()) {
				valueList.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
			}
		}

		return valueList;
	}

	private List<NonNumericTests> getNonNumericTests(List<ReferralItem> referralItems) {
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Set<String> testIdSet = new HashSet<>();

		for (ReferralItem item : referralItems) {
			for (IdValuePair pair : item.getTestSelectionList()) {
				testIdSet.add(pair.getId());
			}
		}

		List<NonNumericTests> nonNumericTestList = new ArrayList<>();
		TestResultDAO testResultDAO = new TestResultDAOImpl();
		for (String testId : testIdSet) {
			List<TestResult> testResultList = testResultDAO.getActiveTestResultsByTest(testId);

			if (!(testResultList == null || testResultList.isEmpty())) {
				NonNumericTests nonNumericTests = new NonNumericTests();

				nonNumericTests.testId = testId;
				nonNumericTests.testType = testResultList.get(0).getTestResultType();
				boolean isSelectList = TypeOfTestResultService.ResultType.isDictionaryVariant(nonNumericTests.testType);

				if (isSelectList) {
					List<IdValuePair> dictionaryValues = new ArrayList<>();
					for (TestResult testResult : testResultList) {
						if (TypeOfTestResultService.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
							String resultName = dictionaryDAO.getDictionaryById(testResult.getValue())
									.getLocalizedName();
							dictionaryValues.add(new IdValuePair(testResult.getValue(), resultName));
						}
					}

					nonNumericTests.dictionaryValues = dictionaryValues;
				}

				if (nonNumericTests.testType != null) {
					nonNumericTestList.add(nonNumericTests);
				}
			}

		}

		return nonNumericTestList;
	}

	@RequestMapping(value = "/ReferredOutTests", method = RequestMethod.POST)
	public ModelAndView showReferredOutTestsUpdate(HttpServletRequest request,
			@ModelAttribute("form") ReferredOutTestsForm form, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			findForward(FWD_FAIL_INSERT, form);
		}

		List<ReferralSet> referralSetList = new ArrayList<>();
		List<ReferralResult> removableReferralResults = new ArrayList<>();
		List<ReferralItem> modifiedItems = new ArrayList<>();
		List<ReferralItem> canceledItems = new ArrayList<>();
		Set<Sample> parentSamples = new HashSet<>();
		List<Sample> modifiedSamples = new ArrayList<>();

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		List<ReferralItem> referralItems = (List<ReferralItem>) PropertyUtils.getProperty(form, "referralItems");
		selectModifiedAndCanceledItems(referralItems, modifiedItems, canceledItems);
		validateModifedItems(result, modifiedItems);

		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		try {
			createReferralSets(referralSetList, removableReferralResults, modifiedItems, canceledItems, parentSamples);
		} catch (LIMSRuntimeException e) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (ReferralSet referralSet : referralSetList) {
				referralDAO.updateData(referralSet.referral);

				for (ReferralResult referralResult : referralSet.updatableReferralResults) {
					Result rResult = referralResult.getResult();
					if (rResult != null) {
						if (rResult.getId() == null) {
							resultDAO.insertData(rResult);
						} else {
							rResult.setSysUserId(getSysUserId(request));
							resultDAO.updateData(rResult);
						}
					}

					if (referralResult.getId() == null) {
						referralResultDAO.insertData(referralResult);
					} else {
						referralResultDAO.updateData(referralResult);
					}
				}

				if (referralSet.note != null) {
					if (referralSet.note.getId() == null) {
						noteDAO.insertData(referralSet.note);
					} else {
						noteDAO.updateData(referralSet.note);
					}
				}
			}

			for (ReferralResult referralResult : removableReferralResults) {

				referralResult.setSysUserId(getSysUserId(request));
				referralResultDAO.deleteData(referralResult);

				if (referralResult.getResult() != null && referralResult.getResult().getId() != null) {
					referralResult.getResult().setSysUserId(getSysUserId(request));
					resultDAO.deleteData(referralResult.getResult());
				}
			}

			setStatusOfParentSamples(modifiedSamples, parentSamples);

			for (Sample sample : modifiedSamples) {
				sampleDAO.updateData(sample);
			}

			tx.commit();

		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			String errorMsg;
			if (lre.getException() instanceof StaleObjectStateException) {
				errorMsg = "errors.OptimisticLockException";
			} else {
				lre.printStackTrace();
				errorMsg = "error.system";
			}

			result.reject(errorMsg);
			saveErrors(result);
			request.setAttribute(ALLOW_EDITS_KEY, "false");
			return findForward(FWD_FAIL_INSERT, form);

		} finally {
			HibernateUtil.closeSession();
		}
		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void selectModifiedAndCanceledItems(List<ReferralItem> referralItems, List<ReferralItem> modifiedItems,
			List<ReferralItem> canceledItems) {
		for (ReferralItem item : referralItems) {
			if (item.isCanceled()) {
				canceledItems.add(item);
			} else if (item.isModified()) {
				modifiedItems.add(item);
			}
		}
	}

	private void validateModifedItems(Errors errors, List<ReferralItem> modifiedItems) {
		for (ReferralItem referralItem : modifiedItems) {
			validateModifedItem(referralItem, errors);
		}
	}

	private void validateModifedItem(ReferralItem referralItem, Errors errors) {
		// if an institution has not been entered then there may not be a test
		if (!institutionEntered(referralItem) && testEntered(referralItem)) {
			errors.reject("error.referral.missingInstitution", new String[] { referralItem.getAccessionNumber() },
					"error.referral.missingInstitution");
		}

		// if a test has not been entered then there can not be a result or
		// report date
		if (!testEntered(referralItem) && (reportDateEntered(referralItem) || resultEntered(referralItem))) {
			errors.reject("error.referral.missingTest", new String[] { referralItem.getAccessionNumber() },
					"error.referral.missingTest");
		}
		try {
			DateUtil.convertStringDateToTruncatedTimestamp(referralItem.getReferredReportDate());
		} catch (LIMSRuntimeException e) {
			errors.reject("errors.date", new String[] { referralItem.getReferredReportDate() }, "errors.date");

		}
	}

	private boolean institutionEntered(ReferralItem referralItem) {
		return !"0".equals(referralItem.getReferredInstituteId());
	}

	private boolean testEntered(IReferralResultTest resultTest) {
		return !"0".equals(resultTest.getReferredTestId());
	}

	private boolean reportDateEntered(IReferralResultTest resultTest) {
		return !GenericValidator.isBlankOrNull(resultTest.getReferredReportDate());
	}

	private boolean resultEntered(IReferralResultTest resultTest) {
		return !(GenericValidator.isBlankOrNull(resultTest.getReferredResult())
				&& "0".equals(resultTest.getReferredDictionaryResult()));
	}

	private void createReferralSets(List<ReferralSet> referralSetList, List<ReferralResult> removableReferralResults,
			List<ReferralItem> modifiedItems, List<ReferralItem> canceledItems, Set<Sample> parentSamples)
			throws LIMSRuntimeException {

		for (ReferralItem item : canceledItems) {
			referralSetList.add(createCanceledReferralSet(item, parentSamples));
		}

		for (ReferralItem item : modifiedItems) {
			referralSetList.add(createModifiedSet(item, removableReferralResults));
		}
	}

	private ReferralSet createCanceledReferralSet(ReferralItem item, Set<Sample> parentSamples) {
		ReferralSet referralSet = new ReferralSet();

		Referral referral = referralDAO.getReferralById(item.getReferralId());

		referralSet.referral = referral;
		referral.setSysUserId(getSysUserId(request));
		referral.setCanceled(true);

		setStatusForCanceledReferrals(referral, parentSamples);

		return referralSet;
	}

	@SuppressWarnings("unchecked")
	private void setStatusForCanceledReferrals(Referral referral, Set<Sample> parentSamples) {

		Analysis analysis = referral.getAnalysis();
		analysis.setReferredOut(false);
		Sample sample = analysis.getSampleItem().getSample();
		parentSamples.add(sample);

	}

	private ReferralSet createModifiedSet(ReferralItem referralItem, List<ReferralResult> removableReferralResults)
			throws LIMSRuntimeException {
		// place all existing referral results in list
		ReferralSet referralSet = new ReferralSet();
		referralSet.setExistingReferralResults(
				referralResultDAO.getReferralResultsForReferral(referralItem.getReferralId()));

		Referral referral = referralDAO.getReferralById(referralItem.getReferralId());
		referral.setCanceled(false);
		referral.setSysUserId(getSysUserId(request));
		referral.setOrganization(organizationDAO.getOrganizationById(referralItem.getReferredInstituteId()));
		referral.setSentDate(DateUtil.convertStringDateToTruncatedTimestamp(referralItem.getReferredSendDate()));
		referral.setRequesterName(referralItem.getReferrer());
		referral.setReferralReasonId(referralItem.getReferralReasonId());
		referralSet.referral = referral;

		referralSet.note = new NoteService(referral.getAnalysis()).createSavableNote(NoteService.NoteType.INTERNAL,
				referralItem.getNote(), RESULT_SUBJECT, getSysUserId(request));

		createReferralResults(referralItem, referralSet);

		if (referralItem.getAdditionalTests() != null) {
			for (ReferredTest existingAdditionalTest : referralItem.getAdditionalTests()) {
				if (!existingAdditionalTest.isRemove()) {
					// nothing to do if isRemove, because on insert we reused what we could
					// then deleted all old referralResults (see below).
					// removableReferralResults.add(getRemovableReferralableResults(existingAdditionalTest));
					createReferralResults(existingAdditionalTest, referralSet);
				}
			}
		}

		List<ReferredTest> newAdditionalTests = getNewTests(referralItem.getAdditionalTestsXMLWad());

		for (ReferredTest newReferralTest : newAdditionalTests) {
			newReferralTest.setReferralId(referralItem.getReferralId());
			createReferralResults(newReferralTest, referralSet);
		}

		// any leftovers get deleted
		removableReferralResults.addAll(referralSet.getExistingReferralResults());

		return referralSet;
	}

	/**
	 * Reuse any existing referrableResults, placing the submitted results in them.
	 * Then any remaining referral results are removable.
	 ***/
	private void createReferralResults(IReferralResultTest referralItem, ReferralSet referralSet) {
		if (referralItem.getReferredTestIdShadow() != null
				&& !referralItem.getReferredTestId().equals(referralItem.getReferredTestIdShadow())) {
			referralSet.updateTest(referralItem.getReferredTestIdShadow(), referralItem.getReferredTestId(),
					getSysUserId(request));
		} else {
			String referredResultType = getReferredResultType(referralItem, null);
			if (TypeOfTestResultService.ResultType.isMultiSelectVariant(referredResultType)) {
				if (!GenericValidator.isBlankOrNull(referralItem.getMultiSelectResultValues())
						&& !"{}".equals(referralItem.getMultiSelectResultValues())) {
					JSONParser parser = new JSONParser();
					try {
						JSONObject jsonResult = (JSONObject) parser.parse(referralItem.getMultiSelectResultValues());
						for (Object key : jsonResult.keySet()) {
							String[] ids = ((String) jsonResult.get(key)).trim().split(",");
							// This will populate a result for each item in the multiselect referral result
							for (String id : ids) {
								ReferralResult referralResult = referralSet.getNextReferralResult();
								referralItem.setReferredDictionaryResult(id); // move particular multi result into
																				// (single) dictionary result.
								fillReferralResultResult(referralItem, referralResult, Integer.parseInt((String) key));
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					ReferralResult referralResult = referralSet.getNextReferralResult();
					fillReferralResultResult(referralItem, referralResult, 0);
				}
			} else {
				ReferralResult referralResult = referralSet.getNextReferralResult();
				fillReferralResultResult(referralItem, referralResult, 0);
			}
		}
	}

	private void fillReferralResultResult(IReferralResultTest referralItem, ReferralResult referralResult,
			int grouping) {
		referralResult.setSysUserId(getSysUserId(request));

		setReferredResultReportDate(referralItem, referralResult);
		setReferredResultTestId(referralItem, referralResult);
		referralResult.setReferralId(referralItem.getReferralId());
		Result result = referralResult.getResult();

		if (result == null && !GenericValidator.isBlankOrNull(referralItem.getReferredResultType())) {
			result = new Result();
		}

		if (result != null) {
			setResultValuesForReferralResult(referralItem, result, grouping);
			referralResult.setResult(result);
		}

	}

	/**
	 * If the referredTest.referredResultType is "M" the particular value to
	 * translate into the result should already be loaded in
	 * referredTest.referredDictionaryResult
	 *
	 */
	private void setResultValuesForReferralResult(IReferralResultTest referredTest, Result result, int grouping) {
		result.setSysUserId(getSysUserId(request));
		result.setSortOrder("0");

		Test test = testDAO.getTestById(referredTest.getReferredTestId());
		Sample sample = referralDAO.getReferralById(referredTest.getReferralId()).getAnalysis().getSampleItem()
				.getSample();
		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		ResultLimit limit = new ResultLimitService().getResultLimitForTestAndPatient(test, patient);
		result.setMinNormal(limit != null ? limit.getLowNormal() : 0.0);
		result.setMaxNormal(limit != null ? limit.getHighNormal() : 0.0);
		result.setGrouping(grouping);

		String referredResultType = getReferredResultType(referredTest, test);
		result.setResultType(referredResultType);
		if (TypeOfTestResultService.ResultType.isDictionaryVariant(referredResultType)) {
			String dicResult = referredTest.getReferredDictionaryResult();
			if (!(GenericValidator.isBlankOrNull(dicResult) || "0".equals(dicResult))) {
				result.setValue(dicResult);
			}
		} else {
			result.setValue(referredTest.getReferredResult());
		}
	}

	private String getReferredResultType(IReferralResultTest referredTest, Test test) {
		// N.B. this should not be corrected here. It should be done on load
		/*
		 * referredTest.getReferredResultType() is not always accurate alpha-numeric and
		 * numeric are not differentiated
		 */

		String referredResultType = referredTest.getReferredResultType();

		if (!TypeOfTestResultService.ResultType.isDictionaryVariant(referredResultType) && test != null) {
			@SuppressWarnings("unchecked")
			List<TestResult> testResults = testResultDAO.getAllActiveTestResultsPerTest(test);

			if (!testResults.isEmpty()) {
				referredResultType = testResults.get(0).getTestResultType();
			}
		}

		return referredResultType;
	}

	private void setReferredResultTestId(IReferralResultTest referralTest, ReferralResult referralResult) {
		if (!"0".equals(referralTest.getReferredTestId())) {
			referralResult.setTestId(referralTest.getReferredTestId());
		}
	}

	private void setReferredResultReportDate(IReferralResultTest referralTest, ReferralResult referralResult)
			throws LIMSRuntimeException {

		if (!GenericValidator.isBlankOrNull(referralTest.getReferredReportDate())) {
			try {
				referralResult.setReferralReportDate(
						DateUtil.convertStringDateToTruncatedTimestamp(referralTest.getReferredReportDate()));
			} catch (LIMSRuntimeException e) {
				/*
				 * ActionError error = new ActionError("errors.date",
				 * referralTest.getReferredReportDate(), null);
				 * errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				 */
				throw e;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<ReferredTest> getNewTests(String xml) {
		List<ReferredTest> newTestList = new ArrayList<>();

		if (GenericValidator.isBlankOrNull(xml)) {
			return newTestList;
		}

		try {
			Document testsDom = DocumentHelper.parseText(xml);

			for (Iterator<Element> i = testsDom.getRootElement().elementIterator("test"); i.hasNext();) {
				Element testItem = i.next();

				String testId = testItem.attribute("testId").getValue();

				ReferredTest referralTest = new ReferredTest();
				referralTest.setReferredTestId(testId);
				referralTest.setReferredResultType(new TestService(testId).getResultType());
				referralTest.setReferredResult("");
				referralTest.setReferredDictionaryResult("");
				referralTest.setReferredMultiDictionaryResult("");
				referralTest.setReferredReportDate("");

				newTestList.add(referralTest);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new LIMSRuntimeException(e);
		}

		return newTestList;
	}

	private void setStatusOfParentSamples(List<Sample> modifiedSamples, Set<Sample> parentSamples) {
		for (Sample sample : parentSamples) {
			List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

			String finalizedId = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
			boolean allAnalysisFinished = true;

			if (analysisList != null) {
				for (Analysis childAnalysis : analysisList) {
					Referral referral = referralDAO.getReferralByAnalysisId(childAnalysis.getId());
					List<ReferralResult> referralResultList;

					if (referral == null || referral.getId() == null) {
						referralResultList = new ArrayList<>();
					} else {
						referralResultList = referralResultDAO.getReferralResultsForReferral(referral.getId());
					}

					if (referralResultList.isEmpty()) {
						if (!finalizedId.equals(childAnalysis.getStatusId())) {
							allAnalysisFinished = false;
							break;
						}
					} else {
						for (ReferralResult referralResult : referralResultList) {
							if (referralResult.getResult() == null
									|| GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
								if (!(referral.isCanceled() && finalizedId.equals(childAnalysis.getStatusId()))) {
									allAnalysisFinished = false;
									break;
								}
							}
						}
					}
				}
			}

			if (allAnalysisFinished) {
				sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Finished));
				sample.setSysUserId(getSysUserId(request));
				modifiedSamples.add(sample);
			}
		}
	}

	static class ReferralSet {
		Referral referral;
		Note note;
		List<ReferralResult> updatableReferralResults = new ArrayList<>();
		private List<ReferralResult> existingReferralResults = new ArrayList<>();

		public List<ReferralResult> getExistingReferralResults() {
			return existingReferralResults;
		}

		public void setExistingReferralResults(List<ReferralResult> existingReferralResults) {
			this.existingReferralResults = existingReferralResults;
		}

		ReferralResult getNextReferralResult() {
			ReferralResult referralResult = existingReferralResults.isEmpty() ? new ReferralResult()
					: existingReferralResults.remove(0);
			updatableReferralResults.add(referralResult);

			return referralResult;
		}

		public void updateTest(String oldTestId, String newTestId, String currentUserId) {
			ReferralResult updatedReferralResult = null;
			for (ReferralResult referralResult : existingReferralResults) {
				if (referralResult.getTestId().equals(oldTestId)) {
					Result result = referralResult.getResult();
					result.setSysUserId(currentUserId);
					if (updatedReferralResult == null) {
						referralResult.setTestId(newTestId);
						referralResult.setSysUserId(currentUserId);
						result.setResultType(new TestService(newTestId).getResultType());
						result.setValue("");
						updatedReferralResult = referralResult;
						updatableReferralResults.add(referralResult);
					}
				}
			}
			existingReferralResults.remove(updatedReferralResult);
		}
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "referredOutDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/ReferredOutTests.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "referredOutDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageSubtitleKey() {
		return "referral.out.manage";
	}

	@Override
	protected String getPageTitleKey() {
		return "referral.out.manage";
	}

	public class NonNumericTests {
		public String testId;
		public String testType;
		public List<IdValuePair> dictionaryValues;
	}
}
