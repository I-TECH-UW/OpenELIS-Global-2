package spring.mine.qaevent.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.qaevent.form.NonConformityForm;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;

@Component
public class NonConformityFormValidator implements Validator {

	@Autowired
	QaEventItemValidator qaEventItemValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return NonConformityForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NonConformityForm form = (NonConformityForm) target;
		for (QaEventItem qaEventItem : form.getQaEvents()) {
			qaEventItemValidator.validate(qaEventItem, errors);
		}

	}

}
