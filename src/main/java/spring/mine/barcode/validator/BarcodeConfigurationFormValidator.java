package spring.mine.barcode.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.barcode.form.BarcodeConfigurationForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class BarcodeConfigurationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BarcodeConfigurationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BarcodeConfigurationForm form = (BarcodeConfigurationForm) target;

		// check dimensions
		ValidationHelper.validateFieldMinMax(form.getHeightOrderLabels(), "heightOrderLabels", errors, 0, 100);

		ValidationHelper.validateFieldMinMax(form.getWidthOrderLabels(), "widthOrderLabels", errors, 0, 100);

		ValidationHelper.validateFieldMinMax(form.getHeightSpecimenLabels(), "heightSpecimenLabels", errors, 0, 100);

		ValidationHelper.validateFieldMinMax(form.getWidthSpecimenLabels(), "widthSpecimenLabels", errors, 0, 100);

		// check number of labels
		ValidationHelper.validateFieldMinMax(form.getNumOrderLabels(), "numOrderLabels", errors, 0, 2000);

		ValidationHelper.validateFieldMinMax(form.getNumSpecimenLabels(), "numSpecimenLabels", errors, 0, 2000);

		// check optional fields
		ValidationHelper.validateTFField((Boolean) form.get("collectionDateCheck"), "collectionDateCheck", errors,
				false);

		ValidationHelper.validateTFField((Boolean) form.get("patientSexCheck"), "patientSexCheck", errors, false);

		ValidationHelper.validateTFField((Boolean) form.get("testsCheck"), "testsCheck", errors, false);

	}

}
