package spring.mine.siteinformation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.siteinformation.form.SiteInformationForm;

@Component
public class SiteInformationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteInformationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SiteInformationForm form = (SiteInformationForm) target;

		ValidationHelper.validateFieldAndCharset(form.getParamName(), "paramName", errors, true, 32,
				"a-zA-Z0-9_\\-/ ()");

		// UNSECURE VARIABLE
		ValidationHelper.validateField(form.getDescription(), "description", errors, true, 120);

		// UNSECURE VARIABLE
		ValidationHelper.validateField(form.getValue(), "value", errors, true, 200);

		// check what encrypted means in this context (is it just masked from view
		// like in the current method
		// or should it be completely unaccessible client side)
		// encrypted doesn't need validation

		ValidationHelper.validateOptionField(form.getValueType(), "valueType", errors,
				new String[] { "boolean", "logoUpload", "text", "freeText", "dictionary", "complex" });

		ValidationHelper.validateOptionField(form.getSiteInfoDomainName(), "siteInfoDomainName", errors,
				new String[] { "non_conformityConfiguration", "WorkplanConfiguration", "PrintedReportsConfiguration",
						"sampleEntryConfig", "ResultConfiguration", "MenuStatementConfig", "PaitientConfiguration",
						"SiteInformation" });

		// dictionaryValues doesn't require validation

		// editable doesn't need validation

		ValidationHelper.validateOptionField(form.getTag(), "tag", errors,
				new String[] { "enable", "url", "numericOnly", "programConfiguration", "localization", "", null });

		ValidationHelper.validateField(form.getDescriptionKey(), "descriptionKey", errors, false, 255,
				ValidationHelper.MESSAGE_KEY_REGEX);

		if ("localization".equals(form.getTag())) {
			// UNSECURE VARIABLE
			ValidationHelper.validateField(form.getEnglishValue(), "englishValue", errors, true, 255);

			// UNSECURE VARIABLE
			ValidationHelper.validateField(form.getFrenchValue(), "frenchValue", errors, true, 255);
		}
	}

}
