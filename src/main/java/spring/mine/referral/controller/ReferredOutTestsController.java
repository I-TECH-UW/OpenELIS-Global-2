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
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.StaleObjectStateException;
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
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.service.note.NoteServiceImpl;
import spring.service.organization.OrganizationService;
import spring.service.referral.ReferralResultService;
import spring.service.referral.ReferralService;
import spring.service.referral.ReferralSetService;
import spring.service.result.ResultServiceImpl;
import spring.service.resultlimit.ResultLimitServiceImpl;
import spring.service.samplehuman.SampleHumanService;
import spring.service.test.TestService;
import spring.service.test.TestServiceImpl;
import spring.service.testresult.TestResultService;
import spring.service.typeofsample.TypeOfSampleServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.referral.action.beanitems.IReferralResultTest;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;
import us.mn.state.health.lims.referral.action.beanitems.ReferredTest;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.referral.valueholder.ReferralSet;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class ReferredOutTestsController extends BaseController {

	private static final String RESULT_SUBJECT = "Result Note";

	@Autowired
	private ReferralService referralService;
	@Autowired
	private ReferralResultService referralResultService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private TestService testService;
	@Autowired
	private SampleHumanService sampleHumanService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private ReferralSetService referralSetService;

	@RequestMapping(value = "/ReferredOutTests", method = RequestMethod.GET)
	public ModelAndView showReferredOutTests(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		ReferredOutTestsForm form = new ReferredOutTestsForm();
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		List<ReferralItem> referralItems = getReferralItems();
		PropertyUtils.setProperty(form, "referralItems", referralItems);
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "referralOrganizations", DisplayListService.getInstance()
				.getListWithLeadingBlank(DisplayListService.ListType.REFERRAL_ORGANIZATIONS));

		fillInDictionaryValuesForReferralItems(referralItems);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private void fillInDictionaryValuesForReferralItems(List<ReferralItem> referralItems) {
		List<NonNumericTests> nonNumericTests = getNonNumericTests(referralItems);
		for (ReferralItem referralItem : referralItems) {
			String referredResultType = referralItem.getReferredResultType();
			if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(referredResultType)) {
				referralItem.setDictionaryResults(
						getDictionaryValuesForTest(referralItem.getReferredTestId(), nonNumericTests));
			}

			if (referralItem.getAdditionalTests() != null) {
				for (ReferredTest test : referralItem.getAdditionalTests()) {
					if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(test.getReferredResultType())) {
						test.setDictionaryResults(
								getDictionaryValuesForTest(test.getReferredTestId(), nonNumericTests));
					}
				}
			}
		}

	}

	private List<ReferralItem> getReferralItems() {
		List<ReferralItem> referralItems = new ArrayList<>();

		List<Referral> referralList = referralService.getAllUncanceledOpenReferrals();

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
		List<ReferralResult> referralResults = referralResultService.getReferralResultsForReferral(referral.getId());
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

		AnalysisServiceImpl analysisService = new AnalysisServiceImpl(referral.getAnalysis());

		referralItem.setCanceled(false);
		referralItem.setReferredResultType("N");
		referralItem.setAccessionNumber(analysisService.getOrderAccessionNumber());

		TypeOfSample typeOfSample = analysisService.getTypeOfSample();
		referralItem.setSampleType(typeOfSample.getLocalizedName());

		referralItem.setReferringTestName(
				TestServiceImpl.getUserLocalizedTestName(analysisService.getAnalysis().getTest()));
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
		if (!TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(resultType)) {
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

			referralItem.setMultiSelectResultValues(ResultServiceImpl.getJSONStringForMultiSelect(resultList));
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
		if (TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(result.getResultType())) {
			Dictionary dictionary = dictionaryService.get(result.getValue());
			if (dictionary != null) {
				return dictionary.getLocalizedName();
			}
		} else if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(result.getResultType())) {
			StringBuilder multiResult = new StringBuilder();

			for (Result subResult : results) {
				Dictionary dictionary = dictionaryService.get(subResult.getValue());

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
		List<Test> testList = SpringContext.getBean(TypeOfSampleServiceImpl.class).getActiveTestsBySampleTypeId(typeOfSample.getId(),
				false);

		List<IdValuePair> valueList = new ArrayList<>();

		for (Test test : testList) {
			if (test.getOrderable()) {
				valueList.add(new IdValuePair(test.getId(), TestServiceImpl.getUserLocalizedTestName(test)));
			}
		}

		return valueList;
	}

	private List<NonNumericTests> getNonNumericTests(List<ReferralItem> referralItems) {
		Set<String> testIdSet = new HashSet<>();

		for (ReferralItem item : referralItems) {
			for (IdValuePair pair : item.getTestSelectionList()) {
				testIdSet.add(pair.getId());
			}
		}

		List<NonNumericTests> nonNumericTestList = new ArrayList<>();
		for (String testId : testIdSet) {
			List<TestResult> testResultList = testResultService.getActiveTestResultsByTest(testId);

			if (!(testResultList == null || testResultList.isEmpty())) {
				NonNumericTests nonNumericTests = new NonNumericTests();

				nonNumericTests.testId = testId;
				nonNumericTests.testType = testResultList.get(0).getTestResultType();
				boolean isSelectList = TypeOfTestResultServiceImpl.ResultType
						.isDictionaryVariant(nonNumericTests.testType);

				if (isSelectList) {
					List<IdValuePair> dictionaryValues = new ArrayList<>();
					for (TestResult testResult : testResultList) {
						if (TypeOfTestResultServiceImpl.ResultType
								.isDictionaryVariant(testResult.getTestResultType())) {
							String resultName = dictionaryService.get(testResult.getValue()).getLocalizedName();
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
			@ModelAttribute("form") @Valid ReferredOutTestsForm form, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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

		try {
			referralSetService.updateRefreralSets(referralSetList, modifiedSamples, parentSamples,
					removableReferralResults, getSysUserId(request));
		} catch (LIMSRuntimeException lre) {
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

		Referral referral = referralService.get(item.getReferralId());

		referralSet.setReferral(referral);
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
				referralResultService.getReferralResultsForReferral(referralItem.getReferralId()));

		Referral referral = referralService.get(referralItem.getReferralId());
		referral.setCanceled(false);
		referral.setSysUserId(getSysUserId(request));
		referral.setOrganization(organizationService.get(referralItem.getReferredInstituteId()));
		referral.setSentDate(DateUtil.convertStringDateToTruncatedTimestamp(referralItem.getReferredSendDate()));
		referral.setRequesterName(referralItem.getReferrer());
		referral.setReferralReasonId(referralItem.getReferralReasonId());
		referralSet.setReferral(referral);

		referralSet.setNote(new NoteServiceImpl(referral.getAnalysis()).createSavableNote(
				NoteServiceImpl.NoteType.INTERNAL, referralItem.getNote(), RESULT_SUBJECT, getSysUserId(request)));

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
			if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(referredResultType)) {
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

		Test test = testService.get(referredTest.getReferredTestId());
		Sample sample = referralService.get(referredTest.getReferralId()).getAnalysis().getSampleItem().getSample();
		Patient patient = sampleHumanService.getPatientForSample(sample);
		ResultLimit limit = new ResultLimitServiceImpl().getResultLimitForTestAndPatient(test, patient);
		result.setMinNormal(limit != null ? limit.getLowNormal() : 0.0);
		result.setMaxNormal(limit != null ? limit.getHighNormal() : 0.0);
		result.setGrouping(grouping);

		String referredResultType = getReferredResultType(referredTest, test);
		result.setResultType(referredResultType);
		if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(referredResultType)) {
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

		if (!TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(referredResultType) && test != null) {
			@SuppressWarnings("unchecked")
			List<TestResult> testResults = testResultService.getAllActiveTestResultsPerTest(test);

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
				referralTest.setReferredResultType(new TestServiceImpl(testId).getResultType());
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
