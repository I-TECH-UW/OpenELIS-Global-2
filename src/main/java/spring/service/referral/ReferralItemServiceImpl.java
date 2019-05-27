package spring.service.referral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analysis.AnalysisService;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.service.result.ResultServiceImpl;
import spring.service.test.TestServiceImpl;
import spring.service.typeofsample.TypeOfSampleServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.referral.action.beanitems.IReferralResultTest;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;
import us.mn.state.health.lims.referral.action.beanitems.ReferredTest;
import us.mn.state.health.lims.referral.valueholder.Referral;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
public class ReferralItemServiceImpl implements ReferralItemService {

	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	ReferralService referralService;
	@Autowired
	ReferralResultService referralResultService;
	@Autowired
	AnalysisService analysisService;

	@Override
	@Transactional
	public List<ReferralItem> getReferralItems() {
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

	@Transactional
	public ReferralItem getReferralItem(Referral referral) {
		boolean allReferralResultsHaveResults = true;
		List<ReferralResult> referralResults = referralResultService.getReferralResultsForReferral(referral.getId());
		for (ReferralResult referralResult : referralResults) {
			if (referralResult.getResult() == null || GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
				allReferralResultsHaveResults = false;
				break;
			}
		}

		if (allReferralResultsHaveResults) {
			return null;
		}

		ReferralItem referralItem = new ReferralItem();

		Analysis analysis = referral.getAnalysis();
		AnalysisServiceImpl analysisServiceImpl = new AnalysisServiceImpl(analysis);

		referralItem.setCanceled(false);
		referralItem.setReferredResultType("N");
		referralItem.setAccessionNumber(analysisServiceImpl.getOrderAccessionNumber());

		TypeOfSample typeOfSample = analysisServiceImpl.getTypeOfSample();
		referralItem.setSampleType(typeOfSample.getLocalizedName());

		referralItem.setReferringTestName(TestServiceImpl.getUserLocalizedTestName(analysis.getTest()));
		List<Result> resultList = analysisServiceImpl.getResults();
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
		String notes = analysisServiceImpl.getNotesAsString(true, true, "<br/>", false);
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
			referralTest.setReferredReportDate(DateUtil.convertTimestampToStringDate(referralResult.getReferralReportDate()));
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
	private List<ReferralResult> setReferralItemForNextTest(IReferralResultTest referralItem, List<ReferralResult> referralResults) {

		ReferralResult nextTestFirstResult = referralResults.remove(0);
		List<ReferralResult> resultsForOtherTests = new ArrayList<>(referralResults);

		referralItem.setReferredTestId(nextTestFirstResult.getTestId());
		referralItem.setReferredTestIdShadow(referralItem.getReferredTestId());
		referralItem.setReferredReportDate(DateUtil.convertTimestampToStringDate(nextTestFirstResult.getReferralReportDate()));
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
				if (nextTestFirstResult.getTestId().equals(referralResult.getTestId()) && !GenericValidator.isBlankOrNull(referralResult.getResult().getValue())) {
					resultList.add(referralResult.getResult());
					resultsForOtherTests.remove(referralResult);
				}
			}

			referralItem.setMultiSelectResultValues(ResultServiceImpl.getJSONStringForMultiSelect(resultList));
		}

		return resultsForOtherTests;
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

			if (!GenericValidator.isBlankOrNull(resultValue) && result.getAnalysis().getTest().getUnitOfMeasure() != null) {
				resultValue += " " + result.getAnalysis().getTest().getUnitOfMeasure().getName();
			}

			return resultValue;
		}

		return "";
	}

	private List<IdValuePair> getTestsForTypeOfSample(TypeOfSample typeOfSample) {
		List<Test> testList = TypeOfSampleServiceImpl.getActiveTestsBySampleTypeId(typeOfSample.getId(), false);

		List<IdValuePair> valueList = new ArrayList<>();

		for (Test test : testList) {
			if (test.getOrderable()) {
				valueList.add(new IdValuePair(test.getId(), TestServiceImpl.getUserLocalizedTestName(test)));
			}
		}

		return valueList;
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
}
