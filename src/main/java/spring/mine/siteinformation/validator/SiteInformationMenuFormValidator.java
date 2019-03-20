package spring.mine.siteinformation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.siteinformation.form.SiteInformationMenuForm;

@Component
public class SiteInformationMenuFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteInformationMenuForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SiteInformationMenuForm form = (SiteInformationMenuForm) target;
	}

}
