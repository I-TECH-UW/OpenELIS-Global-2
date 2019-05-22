package us.mn.state.health.lims.result.action.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.validation.Errors;

import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class ResultsValidation {

	private static final String SPECIAL_CASE = "XXXX";
	private boolean supportReferrals = FormFields.getInstance().useField(Field.ResultsReferral);
	private boolean useTechnicianName = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.resultTechnicianName, "true");
	private boolean noteRequiredForChangedResults = false;
	private boolean useRejected = ConfigurationProperties.getInstance()
			.isPropertyValueEqual(Property.allowResultRejection, "true");

	private static ResultDAO resultDAO = new ResultDAOImpl();

	public Errors validateItem(TestResultItem item) {
		Errors errors = new BaseErrors();

		validateTestDate(item, errors);

		if (!item.isRejected()) {
			validateResult(item, errors);
		}

		if (noteRequiredForChangedResults && !item.isRejected()) {
			validateRequiredNote(item, errors);
		}

		if (supportReferrals) {
			validateReferral(item, errors);
		}
		if (useTechnicianName) {
			validateTesterSignature(item, errors);
		}
		if (useRejected) {
			validateRejection(item, errors);
		}

		return errors;
	}

	public Errors validateModifiedItems(List<TestResultItem> modifiedItems) {
		noteRequiredForChangedResults = "true"
				.equals(ConfigurationProperties.getInstance().getPropertyValue(Property.notesRequiredForModifyResults));

		Errors errors = new BaseErrors();

		for (TestResultItem item : modifiedItems) {

			Errors itemErrors = validateItem(item);

			if (itemErrors.hasErrors()) {
				StringBuilder augmentedAccession = new StringBuilder(item.getSequenceAccessionNumber());
				augmentedAccession.append(" : ");
				augmentedAccession.append(item.getTestName());
				// ActionError accessionError = new ActionError("errors.followingAccession",
				// augmentedAccession);

				errors.reject("errors.followingAccession");
				errors.addAllErrors(itemErrors);
			}
		}

		return errors;
	}

	private void validateTestDate(TestResultItem item, Errors errors) {

		Date date = CustomDateValidator.getInstance().getDate(item.getTestDate());

		if (date == null) {
			// errors.add(new ActionError("errors.date", new
			// StringBuilder(item.getTestDate())));
			errors.reject("errors.date");
		} else if (!IActionConstants.VALID
				.equals(CustomDateValidator.getInstance().validateDate(date, DateRelation.PAST))) {
			errors.reject("error.date.inFuture");
		}
	}

	private void validateResult(TestResultItem testResultItem, Errors errors) {
		String resultValue = testResultItem.getShadowResultValue();
		if (GenericValidator.isBlankOrNull(resultValue) && testResultItem.isRejected()) {
			return;
		}

		if (!(ResultUtil.areNotes(testResultItem) || (supportReferrals && ResultUtil.isReferred(testResultItem))
				|| ResultUtil.areResults(testResultItem) || ResultUtil.isForcedToAcceptance(testResultItem))) {
			errors.reject("errors.result.required");
		}

		if (!GenericValidator.isBlankOrNull(resultValue) && "N".equals(testResultItem.getResultType())) {
			if (resultValue.equals(SPECIAL_CASE)) {
				return;
			}
			try {
				Double.parseDouble(resultValue);
			} catch (NumberFormatException e) {
				// errors.add(new ActionError("errors.number.format", new
				// StringBuilder("Result")));
				errors.reject("errors.number.format");
			}
		}

		if (testResultItem.isHasQualifiedResult()
				&& GenericValidator.isBlankOrNull(testResultItem.getQualifiedResultValue())) {
			// errors.add(new ActionError("errors.missing.result.details", new
			// StringBuilder("Result")));
			errors.reject("errors.missing.result.details");
		}
	}

	private void validateReferral(TestResultItem item, Errors errors) {
		if (item.isShadowReferredOut() && "0".equals(item.getReferralReasonId())) {
			errors.reject("error.referral.noReason");
		}
	}

	private void validateRequiredNote(TestResultItem item, Errors errors) {
		if (GenericValidator.isBlankOrNull(item.getNote()) && !GenericValidator.isBlankOrNull(item.getResultId())) {

			if (resultHasChanged(item)) {
				errors.reject("error.requiredNote.missing");
			}

		}

	}

	private boolean resultHasChanged(TestResultItem item) {

		if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(item.getResultType())) {
			List<Result> resultList = new AnalysisServiceImpl(item.getAnalysisId()).getResults();
			ArrayList<String> dictionaryIds = new ArrayList<>(resultList.size());
			for (Result result : resultList) {
				dictionaryIds.add(result.getValue());
			}
			if (!GenericValidator.isBlankOrNull(item.getMultiSelectResultValues())
					&& !"{}".equals(item.getMultiSelectResultValues())) {
				JSONParser parser = new JSONParser();
				try {
					int matchCount = 0;
					JSONObject jsonResult = (JSONObject) parser.parse(item.getMultiSelectResultValues());
					for (Object key : jsonResult.keySet()) {
						String[] ids = ((String) jsonResult.get(key)).trim().split(",");

						for (String id : ids) {
							if (dictionaryIds.contains(id)) {
								matchCount++;
							} else {
								return false;
							}
						}
					}
					return matchCount != dictionaryIds.size();
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
			}

		} else {
			Result dbResult = resultDAO.getResultById(item.getResultId());
			return !item.getShadowResultValue().equals(dbResult.getValue())
					&& !GenericValidator.isBlankOrNull(dbResult.getValue());
		}

		return false;
	}

	private void validateTesterSignature(TestResultItem item, Errors errors) {
		// Conclusions may not need a signature. If the user changed the value
		// and then changed it back it will be
		// marked as dirty but the signature may still be blank.
		if ("0".equals(item.getResultId())) {
			return;
		}

		Result result = resultDAO.getResultById(item.getResultId());

		if (result != null && result.getAnalyte() != null
				&& "Conclusion".equals(result.getAnalyte().getAnalyteName())) {
			if (result.getValue().equals(item.getShadowResultValue())) {
				return;
			}
		}

		if (GenericValidator.isBlankOrNull(item.getTechnician())) {
			errors.reject("errors.signature.required");
		}
	}

	private void validateRejection(TestResultItem item, Errors errors) {
		if (item.isRejected() && "0".equals(item.getRejectReasonId())) {
			errors.reject("error.reject.noReason");
		}
	}
}
