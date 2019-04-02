package spring.mine.dictionary.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.dictionary.form.DictionaryForm;
import us.mn.state.health.lims.common.util.SystemConfiguration;

@Component
public class DictionaryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DictionaryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DictionaryForm form = (DictionaryForm) target;

		ValidationHelper.validateIdField(form.getId(), "id", errors, false);

		ValidationHelper.validateIdField(form.getSelectedDictionaryCategoryId(), "id", errors, true);

		// dictionaryCategory doesn't need to be validated as it is used for display

		// categories doesn't need to be validated as it is used for display

		ValidationHelper.validateYNField(form.getIsActive(), "isActive", errors);

		// right now all chars are permitted UNSECURE VARIABLE
		ValidationHelper.validateField(form.getDictEntry(), "dictEntry", errors, true, 4000);

		// right now all chars are permitted UNSECURE VARIABLE
		ValidationHelper.validateField(form.getLocalAbbreviation(), "localAbbreviation", errors, false, 60);

		String[] dirtyFields = form.getDirtyFormFields()
				.split(SystemConfiguration.getInstance().getDefaultIdSeparator(), -1);
		for (String dirtyField : dirtyFields) {
			ValidationHelper.validateFieldAndCharset(dirtyField, "dirtyFormField", errors, false, 255, "a-zA-Z0-9_");
			if (errors.hasErrors()) {
				break;
			}
		}

	}

}
