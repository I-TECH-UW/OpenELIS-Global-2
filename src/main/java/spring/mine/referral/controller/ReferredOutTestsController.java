package spring.mine.referral.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.referral.form.ReferredOutTestsForm;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.referral.action.beanitems.IReferralResultTest;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;
import us.mn.state.health.lims.referral.action.beanitems.ReferredTest;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.daoimpl.ReferralDAOImpl;
import us.mn.state.health.lims.referral.daoimpl.ReferralResultDAOImpl;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class ReferredOutTestsController extends BaseController {

	private static ReferralResultDAO referralResultDAO = new ReferralResultDAOImpl();
	private static DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

	@RequestMapping(value = "/ReferredOutTests", method = RequestMethod.GET)
	public ModelAndView showReferredOutTests(HttpServletRequest request,
			@ModelAttribute("form") ReferredOutTestsForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ReferredOutTestsForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		List<ReferralItem> referralItems = getReferralItems();
		PropertyUtils.setProperty(form, "referralItems", referralItems);
		PropertyUtils.setProperty(form, "referralReasons",
				DisplayListService.getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(form, "referralOrganizations",
				DisplayListService.getListWithLeadingBlank(DisplayListService.ListType.REFERRAL_ORGANIZATIONS));

		fillInDictionaryValuesForReferralItems(referralItems);

		return findForward(forward, form);
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

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("referredOutDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
