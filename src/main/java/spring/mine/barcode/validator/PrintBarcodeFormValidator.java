package spring.mine.barcode.validator;

import org.springframework.validation.Errors;

import spring.mine.barcode.form.PrintBarcodeForm;
import spring.mine.common.validator.BaseFormValidator;

public class PrintBarcodeFormValidator extends BaseFormValidator {


	@Override
	public boolean supports(Class<?> clazz) {
		return PrintBarcodeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	    PrintBarcodeForm form = (PrintBarcodeForm) target;
		
		
	}
}
