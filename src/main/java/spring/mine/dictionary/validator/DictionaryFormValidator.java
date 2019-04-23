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
		return DictionaryForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DictionaryForm form = (DictionaryForm) target;

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
