package spring.mine.siteinformation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.siteinformation.form.SiteInformationForm;

@Component
public class SiteInformationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteInformationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SiteInformationForm form = (SiteInformationForm) target;

	}

}
