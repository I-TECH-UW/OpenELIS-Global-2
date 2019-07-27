package org.openelisglobal.testconfiguration.validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.openelisglobal.testconfiguration.form.TestActivationForm;
import org.openelisglobal.common.JSONUtils;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.common.log.LogEvent;

@Component
public class TestActivationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestActivationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestActivationForm form = (TestActivationForm) target;

		try {
			JSONObject changeList = JSONUtils.getAsObject(form.getJsonChangeList());

			JSONArray activateTests = JSONUtils.getAsArray(changeList.get("activateTest"));
			for (int i = 0; i < activateTests.size(); ++i) {
				JSONObject activateTest = JSONUtils.getAsObject(activateTests.get(i));

				ValidationHelper.validateIdField(String.valueOf(activateTest.get("id")), "JsonChangeList", "id", errors,
						true);

				ValidationHelper.validateFieldAndCharset(String.valueOf(activateTest.get("sortOrder")),
						"JsonChangeList", "sort order[" + i + "]", errors, true, 3, "0-9");

				ValidationHelper.validateFieldAndCharset(String.valueOf(activateTest.get("activated")),
						"JsonChangeList", "activated[" + i + "]", errors, true, 3, "^$|^true$|^false$");
			}

			JSONArray deactivateTests = JSONUtils.getAsArray(changeList.get("deactivateTest"));
			for (int i = 0; i < deactivateTests.size(); ++i) {
				JSONObject deactivateTest = JSONUtils.getAsObject(deactivateTests.get(i));

				ValidationHelper.validateIdField(String.valueOf(deactivateTest.get("id")), "JsonChangeList",
						"id[" + i + "]", errors, true);
			}
		} catch (ParseException e) {
			LogEvent.logError("TestActivationFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonChangeList", "error.field.format.json");
		}

	}

}
