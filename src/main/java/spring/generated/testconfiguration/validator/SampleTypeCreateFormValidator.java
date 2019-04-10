package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.SampleTypeCreateForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class SampleTypeCreateFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleTypeCreateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleTypeCreateForm form = (SampleTypeCreateForm) target;

		// display only existingSampleTypeList;

		// display only inactiveSampleTypeList;

		// display only existingEnglishNames;

		// display only existingFrenchNames;

		ValidationHelper.validateFieldAndCharset(form.getSampleTypeEnglishName(), "sampleTypeEnglishName", errors, true,
				255, " a-zA-Z%0-9-");

		ValidationHelper.validateFieldAndCharset(form.getSampleTypeFrenchName(), "sampleTypeFrenchhName", errors, true,
				255, " a-zA-ZàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ%0-9-");

	}

}
