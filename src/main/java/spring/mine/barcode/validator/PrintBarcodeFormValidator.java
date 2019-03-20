package spring.mine.barcode.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import spring.mine.barcode.form.PrintBarcodeForm;
import spring.mine.common.validator.BaseFormValidator;

@Component
public class PrintBarcodeFormValidator extends BaseFormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PrintBarcodeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// no validation needed no preservation occurs
	}
}
