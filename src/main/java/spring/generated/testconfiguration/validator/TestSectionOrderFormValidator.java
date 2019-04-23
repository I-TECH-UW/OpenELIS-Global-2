package spring.generated.testconfiguration.validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestSectionOrderForm;
import spring.mine.common.JSONUtils;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class TestSectionOrderFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestSectionOrderForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestSectionOrderForm form = (TestSectionOrderForm) target;

		try {
			JSONObject changeList = JSONUtils.getAsObject(form.getJsonChangeList());

			JSONArray testSections = JSONUtils.getAsArray(changeList.get("testSections"));
			for (int i = 0; i < testSections.size(); ++i) {
				JSONObject testSection = JSONUtils.getAsObject(testSections.get(i));

				ValidationHelper.validateIdField(String.valueOf(testSection.get("id")), "JsonChangeList",
						"testSection[" + i + "] id", errors, true);

				ValidationHelper.validateFieldAndCharset(String.valueOf(testSection.get("sortOrder")), "JsonChangeList",
						"test section[" + i + "] sort order", errors, true, 3, "0-9");
			}
		} catch (ParseException e) {
			LogEvent.logError("TestSectionOrderFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonChangeList", "error.field.format.json");
		}
	}

}
