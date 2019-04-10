package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.SampleTypeRenameEntryForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class SampleTypeRenameEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleTypeRenameEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleTypeRenameEntryForm form = (SampleTypeRenameEntryForm) target;

		// sampleTypeList;

		ValidationHelper.validateFieldAndCharset(form.getNameEnglish(), "nameEnglish", errors, true, 255,
				" a-zA-Z%0-9-");

		ValidationHelper.validateFieldAndCharset(form.getNameFrench(), "nameFrench", errors, true, 255,
				" a-zA-ZàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ%0-9-");

		ValidationHelper.validateIdField(form.getSampleTypeId(), "sampleTypeId", errors, true);
	}

}
