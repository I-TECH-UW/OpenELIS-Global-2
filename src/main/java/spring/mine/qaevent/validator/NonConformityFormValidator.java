package spring.mine.qaevent.validator;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.qaevent.form.NonConformityForm;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;

@Component
public class NonConformityFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NonConformityForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NonConformityForm form = (NonConformityForm) target;

		// sampleItemsTypeOfSampleIds
		String[] sampleItemsTypeOfSampleIds = form.getSampleItemsTypeOfSampleIds().split(",", -1);
		for (String sampleItemsTypeOfSampleId : sampleItemsTypeOfSampleIds) {
			ValidationHelper.validateIdField(sampleItemsTypeOfSampleId, "sampleItemsTypeOfSampleIds", errors, false);
			if (errors.hasErrors()) {
				break;
			}
		}

		for (int i = 0; i < form.getQaEvents().size(); ++i) {
			QaEventItem qaEvent = form.getQaEvents().get(i);
			if (!GenericValidator.isBlankOrNull(qaEvent.getId())) {
				ValidationHelper.validateFieldRequired(qaEvent.getQaEvent(), "qaEvents[" + i + "].qaEvent", errors);
			}
		}
	}

}
