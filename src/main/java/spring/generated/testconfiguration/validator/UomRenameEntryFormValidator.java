package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.UomRenameEntryForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class UomRenameEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UomRenameEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UomRenameEntryForm form = (UomRenameEntryForm) target;

		// dsplay only uomList;

		ValidationHelper.validateFieldAndCharset(form.getNameEnglish(), "nameEnglish", errors, true, 255,
				"a-zA-Z0-9#µ^/()%$_\\-");

		ValidationHelper.validateFieldAndCharset(form.getNameFrench(), "nameFrench", errors, false, 255,
				"a-zA-Z0-9#µ^/()%$_\\-");

		ValidationHelper.validateIdField(form.getUomId(), "uomId", errors, true);

	}

}
