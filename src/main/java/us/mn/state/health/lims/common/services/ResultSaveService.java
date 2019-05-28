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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.service.referral.ReferralResultService;
import spring.service.result.ResultService;
import spring.service.result.ResultSignatureService;
import spring.service.testresult.TestResultService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.serviceBeans.ResultSaveBean;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultSignature;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@Service
@Scope("prototype")
@DependsOn({ "springContext" })
public class ResultSaveService {
	private static ResultService resultService = SpringContext.getBean(ResultService.class);
	private static TestResultService testResultService = SpringContext.getBean(TestResultService.class);
	private static ResultSignatureService resultSigService = SpringContext.getBean(ResultSignatureService.class);
	private static ReferralResultService referralResultService = SpringContext.getBean(ReferralResultService.class);

	private Analysis analysis;
	private String currentUserId;
	private boolean updatedResult = false;

	public ResultSaveService() {
	}

	public ResultSaveService(Analysis analysis, String currentUserId) {
		setAnalysis(analysis);
		setCurrentUserId(currentUserId);
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public List<Result> createResultsFromTestResultItem(ResultSaveBean serviceBean, List<Result> deletableResults) {
		List<Result> results = new ArrayList<>();
		boolean isQualifiedResult = serviceBean.isHasQualifiedResult();

		if (TypeOfTestResultService.ResultType.MULTISELECT.matches(serviceBean.getResultType())
				|| TypeOfTestResultService.ResultType.CASCADING_MULTISELECT.matches(serviceBean.getResultType())) {

			if (!GenericValidator.isBlankOrNull(serviceBean.getMultiSelectResultValues())) {
				JSONParser parser = new JSONParser();
				try {
					JSONObject jsonResult = (JSONObject) parser.parse(serviceBean.getMultiSelectResultValues());

					List<Result> existingResults = resultService.getResultsByAnalysis(analysis);
					for (Object key : jsonResult.keySet()) {
						getResultsForMultiSelect(results, existingResults, serviceBean, (String) key,
								(String) jsonResult.get(key), isQualifiedResult);
					}
					deletableResults.addAll(existingResults);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} else {
			Result result = new Result();
			Result qualifiedResult = null;

			boolean newResult = GenericValidator.isBlankOrNull(serviceBean.getResultId());

			if (!newResult) {
				result.setId(serviceBean.getResultId());
				resultService.getData(result);

				if (!GenericValidator.isBlankOrNull(serviceBean.getQualifiedResultId())) {
					qualifiedResult = new Result();
					qualifiedResult.setId(serviceBean.getQualifiedResultId());
					resultService.getData(qualifiedResult);
				} else if (isQualifiedResult) {
					qualifiedResult = getQuantifiedResult(serviceBean, result);
				}
			}

			if (TypeOfTestResultService.ResultType.DICTIONARY.matches(serviceBean.getResultType())
					|| isQualifiedResult) {
				setTestResultsForDictionaryResult(serviceBean.getTestId(), serviceBean.getResultValue(), result); // support
																													// qualified
																													// result
			} else {
				List<TestResult> testResultList = testResultService.getActiveTestResultsByTest(serviceBean.getTestId());
				// we are assuming there is only one testResult for a numeric
				// type result
				if (!testResultList.isEmpty()) {
					result.setTestResult(testResultList.get(0));
				}
			}

			if (newResult) {
				setNewResultValues(serviceBean, result);
				if (isQualifiedResult) {
					qualifiedResult = getQuantifiedResult(serviceBean, result);
				}
			}

			setAnalyteForResult(result);
			setStandardResultValues(serviceBean.getResultValue(), result);
			results.add(result);

			if (isQualifiedResult) {
				setStandardResultValues(serviceBean.getQualifiedResultValue(), qualifiedResult);
				results.add(qualifiedResult);
			} else if (qualifiedResult != null) { // covers the case where user
				// made change from qualified to
				// non-qualified
				setStandardResultValues("", qualifiedResult);
				results.add(qualifiedResult);
			}
		}

		Collections.sort(deletableResults, new Comparator<Result>() {
			@Override
			public int compare(Result o1, Result o2) {
				return (o1.getParentResult() != null && o2.getId().equals(o1.getParentResult().getId())) ? -1 : 0;
			}
		});

		if (!deletableResults.isEmpty()) {
			updatedResult = true;
		}
		return results;
	}

	private void getResultsForMultiSelect(List<Result> results, List<Result> existingResults,
			ResultSaveBean serviceBean, String key, String value, boolean isQualifiedResult) {
		int groupingKey = Integer.parseInt(key);
		String[] multiResults = value.split(",");

		/*
		 * We will go through all of selections made by the user and compare them to the
		 * selections already in the DB. If a match is found then it will be removed
		 * from the DB list and we will go on to the next selection made by the user. If
		 * a match is not found then a new result will be created.
		 */
		for (String resultAsString : multiResults) {
			Result existingResultFromDB = null;
			for (Result existingResult : existingResults) {
				if (resultAsString.equals(existingResult.getValue()) && existingResult.getGrouping() == groupingKey) {
					existingResultFromDB = existingResult;
					break;
				}
			}

			if (existingResultFromDB != null) {
				existingResults.remove(existingResultFromDB);
				continue;
			}

			Result result = new Result();

			setTestResultsForDictionaryResult(serviceBean.getTestId(), resultAsString, result);
			setNewResultValues(serviceBean, result);
			setAnalyteForResult(result);
			setStandardResultValues(resultAsString, result);
			result.setSortOrder(getResultSortOrder(result.getValue()));
			result.setGrouping(groupingKey);

			results.add(result);
		}

		/*
		 * A quantifiable result may or may not be in the DB
		 */
		if (isQualifiedResult) {
			// cases that it is in DB
			if (!existingResults.isEmpty() && serviceBean.getQualifiedResultId() != null) {
				List<Result> removableResults = new ArrayList<>();
				for (Result existingResult : existingResults) {
					if (serviceBean.getQualifiedResultId().equals(existingResult.getId())) {
						removableResults.add(existingResult);
						setStandardResultValues(serviceBean.getQualifiedResultValue(), existingResult);
						results.add(existingResult);
					}
				}
				existingResults.removeAll(removableResults);
				// case this is a new quantified result
			} else {
				String[] quantifiableResults = serviceBean.getQualifiedDictionaryId()
						.substring(1, serviceBean.getQualifiedDictionaryId().length() - 1).split(",");
				for (String quantifiableResultId : quantifiableResults) {
					for (Result selectedResult : results) {
						if (selectedResult.getValue().equals(quantifiableResultId)) {
							Result quantifiedResult = getQuantifiedResult(serviceBean, selectedResult);
							setStandardResultValues(serviceBean.getQualifiedResultValue(), quantifiedResult);
							results.add(quantifiedResult);
							break;
						}
					}
				}

			}
		}

		for (Result result : existingResults) {
			result.setSysUserId(currentUserId);
		}
	}

	private TestResult setTestResultsForDictionaryResult(String testId, String dictValue, Result result) {
		TestResult testResult;
		testResult = testResultService.getTestResultsByTestAndDictonaryResult(testId, dictValue);

		if (testResult != null) {
			result.setTestResult(testResult);
		}

		return testResult;
	}

	private void setNewResultValues(ResultSaveBean serviceBean, Result result) {
		result.setAnalysis(analysis);
		result.setIsReportable(serviceBean.getReportable());
		result.setResultType(serviceBean.getResultType());
		result.setMinNormal(serviceBean.getLowerNormalRange());
		result.setMaxNormal(serviceBean.getUpperNormalRange());
		result.setSignificantDigits(serviceBean.getSignificantDigits());
	}

	private void setAnalyteForResult(Result result) {
		TestAnalyte testAnalyte = ResultUtil.getTestAnalyteForResult(result);

		if (testAnalyte != null) {
			result.setAnalyte(testAnalyte.getAnalyte());
		}
	}

	private void setStandardResultValues(String value, Result result) {
		if (!(GenericValidator.isBlankOrNull(value) || GenericValidator.isBlankOrNull(result.getValue()))
				&& !StringUtil.blankIfNull(value).equals(result.getValue())) {
			updatedResult = true;
		}
		result.setValue(value);
		result.setSysUserId(currentUserId);
		result.setSortOrder("0");
	}

	private String getResultSortOrder(String resultValue) {
		TestResult testResult = testResultService.getTestResultsByTestAndDictonaryResult(analysis.getTest().getId(),
				resultValue);
		return testResult == null ? "0" : testResult.getSortOrder();
	}

	private Result getQuantifiedResult(ResultSaveBean serviceBean, Result parentResult) {
		Result qualifiedResult = new Result();
		setNewResultValues(serviceBean, qualifiedResult);
		setAnalyteForResult(parentResult);
		qualifiedResult.setResultType("A");
		qualifiedResult.setParentResult(parentResult);
		return qualifiedResult;
	}

	public boolean isUpdatedResult() {
		return updatedResult;
	}

	public static void removeDeletedResultsInTransaction(List<Result> deletableResults, String currentUserId) {
		for (Result result : deletableResults) {
			List<ResultSignature> signatures = resultSigService.getResultSignaturesByResult(result);
			List<ReferralResult> referrals = referralResultService.getReferralsByResultId(result.getId());

			for (ResultSignature signature : signatures) {
				signature.setSysUserId(currentUserId);
			}

			resultSigService.deleteData(signatures);

			for (ReferralResult referral : referrals) {
				referral.setSysUserId(currentUserId);
				referralResultService.deleteData(referral);
			}

			result.setSysUserId(currentUserId);
			resultService.deleteData(result);
		}

	}
}
