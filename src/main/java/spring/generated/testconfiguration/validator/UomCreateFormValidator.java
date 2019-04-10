package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.UomCreateForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class UomCreateFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UomCreateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UomCreateForm form = (UomCreateForm) target;

		// display only existingUomList

		// display only inactiveUomList

		// display only existingEnglishNames

		// display only existingFrenchNames

		ValidationHelper.validateFieldAndCharset(form.getUomEnglishName(), "uomEnglishName", errors, true, 255,
				"a-zA-Z0-9#µ^/()%$_\\-");

		ValidationHelper.validateFieldAndCharset(form.getUomFrenchName(), "uomFrenchName", errors, false, 255,
				"a-zA-Z0-9#µ^/()%$_\\-");
	}

}
