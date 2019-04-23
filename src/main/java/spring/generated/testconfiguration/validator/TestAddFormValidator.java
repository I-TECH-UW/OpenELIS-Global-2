package spring.generated.testconfiguration.validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestAddForm;
import spring.mine.common.JSONUtils;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class TestAddFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestAddForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestAddForm form = (TestAddForm) target;

		try {
			JSONObject newTest = JSONUtils.getAsObject(form.getJsonWad());

			ValidationHelper.validateField(String.valueOf(newTest.get("testNameEnglish")), "JsonWad", "testNameEnglish",
					errors, true, 255);

			ValidationHelper.validateField(String.valueOf(newTest.get("testNameFrench")), "JsonWad", "testNameFrench",
					errors, true, 255);

			ValidationHelper.validateField(String.valueOf(newTest.get("testReportNameEnglish")), "JsonWad",
					"testReportNameEnglish", errors, true, 255);

			ValidationHelper.validateField(String.valueOf(newTest.get("testReportNameFrench")), "JsonWad",
					"testReportNameFrench", errors, true, 255);

			ValidationHelper.validateIdField(String.valueOf(newTest.get("testSection")), "JsonWad", "testSection",
					errors, true);

			JSONArray panels = JSONUtils.getAsArray(newTest.get("panels"));
			for (int i = 0; i < panels.size(); ++i) {
				JSONObject panel = JSONUtils.getAsObject(panels.get(i));
				ValidationHelper.validateIdField(String.valueOf(panel.get("id")), "JsonWad", "panels[" + i + "]",
						errors, false);
			}

			ValidationHelper.validateIdField(String.valueOf(newTest.get("uom")), "JsonWad", "uom", errors, false);

			ValidationHelper.validateIdField(String.valueOf(newTest.get("resultType")), "JsonWad", "resultType", errors,
					true);

			ValidationHelper.validateYNField(String.valueOf(newTest.get("orderable")), "JsonWad", "orderable", errors);

			ValidationHelper.validateYNField(String.valueOf(newTest.get("active")), "JsonWad", "active", errors);

			JSONArray sampleTypes = JSONUtils.getAsArray(newTest.get("sampleTypes"));
			for (int i = 0; i < sampleTypes.size(); ++i) {
				JSONObject sampleType = JSONUtils.getAsObject(sampleTypes.get(i));
				ValidationHelper.validateIdField(String.valueOf(sampleType.get("typeId")), "JsonWad", errors, false);

				JSONArray tests = JSONUtils.getAsArray(sampleType.get("tests"));
				for (int j = 0; j < tests.size(); ++j) {
					JSONObject test = JSONUtils.getAsObject(tests.get(i));
					ValidationHelper.validateIdField(String.valueOf(test.get("id")), "JsonWad", errors, false);
				}
			}

			if (newTest.containsKey("dictionary")) {
				JSONArray dictionaries = JSONUtils.getAsArray(newTest.get("dictionary"));

				for (int i = 0; i < dictionaries.size(); ++i) {
					JSONObject dictionary = JSONUtils.getAsObject(dictionaries.get(i));

					ValidationHelper.validateIdField(String.valueOf(dictionary.get("value")), "JsonWad",
							"dictionary value", errors, true);

					ValidationHelper.validateYNField(String.valueOf(dictionary.get("qualified")), "JsonWad",
							"dictionary qualified", errors);
				}
			}

			if (newTest.containsKey("lowValid")) {
				ValidationHelper.validateField(String.valueOf(newTest.get("lowValid")), "JsonWad", "lowValid", errors,
						false, 255, ValidationHelper.FLOAT_REGEX);

				ValidationHelper.validateField(String.valueOf(newTest.get("highValid")), "JsonWad", "highValid", errors,
						false, 255, ValidationHelper.FLOAT_REGEX);

				ValidationHelper.validateFieldAndCharset(String.valueOf(newTest.get("significantDigits")), "JsonWad",
						errors, false, 2, "0-9");

				JSONArray resultLimits = JSONUtils.getAsArray(newTest.get("resultLimits"));
				for (int i = 0; i < resultLimits.size(); ++i) {
					JSONObject resultLimit = JSONUtils.getAsObject(resultLimits.get(i));
					ValidationHelper.validateField(String.valueOf(resultLimit.get("highAgeRange")), "JsonWad",
							"result limit [" + i + "] highAgeRange", errors, false, 255, "^Infinity$|^\\d*$");

					ValidationHelper.validateTFField((Boolean) resultLimit.get("gender"), "JsonWad",
							"result limit [" + i + "] gender", errors, true);

					ValidationHelper.validateField(String.valueOf(resultLimit.get("lowNormal")), "JsonWad",
							"result limit [" + i + "] lowNormal", errors, false, 255, ValidationHelper.FLOAT_REGEX);

					ValidationHelper.validateField(String.valueOf(resultLimit.get("highNormal")), "JsonWad",
							"result limit [" + i + "] highNormal", errors, false, 255, ValidationHelper.FLOAT_REGEX);

					ValidationHelper.validateField(String.valueOf(resultLimit.get("reportingRange")), "JsonWad",
							"result limit [" + i + "] reportingRange", errors, false, 255,
							ValidationHelper.FLOAT_REGEX);

					if ((Boolean) resultLimit.get("gender")) {
						ValidationHelper.validateField(String.valueOf(resultLimit.get("lowNormalFemale")), "JsonWad",
								"result limit [" + i + "] lowNormalFemale", errors, false, 255,
								ValidationHelper.FLOAT_REGEX);

						ValidationHelper.validateField(String.valueOf(resultLimit.get("highNormalFemale")), "JsonWad",
								"result limit [" + i + "] highNormalFemale", errors, false, 255,
								ValidationHelper.FLOAT_REGEX);

						ValidationHelper.validateField(String.valueOf(resultLimit.get("reportingRangeFemale")),
								"JsonWad", "result limit [" + i + "] reportingRangeFemale", errors, false, 255,
								ValidationHelper.FLOAT_REGEX);
					}

				}
			}

		} catch (ParseException e) {
			LogEvent.logError("TestAddFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonWad", "error.field.format.json");
		}
	}

}
