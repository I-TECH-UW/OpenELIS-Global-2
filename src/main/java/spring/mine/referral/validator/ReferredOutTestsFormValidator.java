package spring.mine.referral.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.referral.form.ReferredOutTestsForm;
import us.mn.state.health.lims.referral.action.beanitems.ReferralItem;

@Component
public class ReferredOutTestsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ReferredOutTestsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReferredOutTestsForm form = (ReferredOutTestsForm) target;

		// TODO validate referralItem
		for (ReferralItem refItem : form.getReferralItems()) {

		}

	}

}
