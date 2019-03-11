package spring.mine.dictionary.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.dictionary.form.DictionaryForm;

@Component
public class DictionaryValidator implements Validator {

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

		// categories doesn't need to be validated as it is used for select options
		// display

		ValidationHelper.validateYNField(form.getIsActive(), "isActive", errors);

		// TODO make a charset that is tight to this input
		// right now all chars are permitted
		ValidationHelper.validateFieldAndCharset(form.getDictEntry(), "dictEntry", errors, true, 4000, ".");

		// TODO validate localAbbreviation

		// TODO validate dirtyFormFields

		// newDictionary doesn't need to be validated as it is not saved

	}

	public void preInsertValidate(DictionaryForm form, Errors errors) {

	}

	public void preUpdateValidate(DictionaryForm form, Errors errors) {

	}

}
