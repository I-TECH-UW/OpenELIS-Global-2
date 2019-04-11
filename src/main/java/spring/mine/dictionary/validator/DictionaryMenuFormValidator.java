package spring.mine.dictionary.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.dictionary.form.DictionaryMenuForm;

@Component
public class DictionaryMenuFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DictionaryMenuForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DictionaryMenuForm form = (DictionaryMenuForm) target;

		for (String id : form.getSelectedIDs()) {
			ValidationHelper.validateIdField(id, "selectedIDs", errors, true);
		}

		// searchString does not require validation, not preserved

	}

}
