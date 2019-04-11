package spring.mine.qaevent.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.qaevent.form.NonConformityForm;

@Component
public class NonConformityFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NonConformityForm.class.equals(clazz);
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

	}

}
