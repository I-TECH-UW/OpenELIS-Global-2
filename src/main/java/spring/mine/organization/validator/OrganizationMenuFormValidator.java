package spring.mine.organization.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.organization.form.OrganizationMenuForm;

@Component
public class OrganizationMenuFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationMenuForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationMenuForm form = (OrganizationMenuForm) target;

		for (String id : form.getSelectedIDs()) {
			ValidationHelper.validateIdField(id, "selectedIDs", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}

		// search string should not need validation
	}

}
