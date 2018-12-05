package spring.mine.common.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.StringUtil;

public abstract class BaseFormValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return BaseForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		BaseForm form = (BaseForm) target;
		validateBaseForm(form, errors);
	}
	
	public void validateBaseForm(BaseForm form, Errors errors) {
		if ( StringUtil.isNullorNill(form.getFormName()) ) {
			errors.rejectValue("formName", "formName.bad", "formName invalid");
		}
		if ( StringUtil.isNullorNill(form.getFormAction()) ) {
			errors.rejectValue("formAction", "formName.bad", "formAction invalid");
		}
	}

}
