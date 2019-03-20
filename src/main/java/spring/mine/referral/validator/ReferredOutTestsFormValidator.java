package spring.mine.referral.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.referral.form.ReferredOutTestsForm;

@Component
public class ReferredOutTestsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ReferredOutTestsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReferredOutTestsForm form = (ReferredOutTestsForm) target;

		/// TODO validate referralItems

		/// TODO validate referralOrganizations

		/// TODO validate referralReasons

	}

}
