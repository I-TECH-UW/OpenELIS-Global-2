package spring.mine.qaevent.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;

@Component
public class QaEventItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return QaEventItemValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QaEventItem qaEvent = (QaEventItem) target;

		if (GenericValidator.isBlankOrNull(qaEvent.getQaEvent()) || !GenericValidator.isInt(qaEvent.getQaEvent())) {
			errors.rejectValue("qaEvent", "error.qaEvent");
		}
		if (GenericValidator.isBlankOrNull(qaEvent.getSampleType())
				|| !GenericValidator.isInt(qaEvent.getSampleType())) {
			errors.rejectValue("qaEvent", "error.sampleType");
		}
	}

}
