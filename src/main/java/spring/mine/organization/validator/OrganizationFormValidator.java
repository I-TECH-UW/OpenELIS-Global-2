package spring.mine.organization.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.organization.form.OrganizationForm;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;

@Component
public class OrganizationFormValidator implements Validator {

	private boolean useOrgLocalAbbrev = FormFields.getInstance().useField(Field.OrgLocalAbrev);
	private boolean useState = FormFields.getInstance().useField(Field.OrgState);
	private boolean useZip = FormFields.getInstance().useField(Field.ZipCode);
	private boolean useMLS = FormFields.getInstance().useField(Field.MLS);
	private boolean useInlineOrganizationTypes = FormFields.getInstance().useField(Field.InlineOrganizationTypes);
	private boolean useAddressInfo = FormFields.getInstance().useField(Field.OrganizationAddressInfo);
	private boolean useCLIA = FormFields.getInstance().useField(Field.OrganizationCLIA);
	private boolean useParent = FormFields.getInstance().useField(Field.OrganizationParent);
	private boolean useShortName = FormFields.getInstance().useField(Field.OrganizationShortName);
	private boolean useMultiUnit = FormFields.getInstance().useField(Field.OrganizationMultiUnit);
	private boolean showCity = FormFields.getInstance().useField(Field.ADDRESS_CITY);
	private boolean showDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT);
	private boolean showCommune = FormFields.getInstance().useField(Field.ADDRESS_COMMUNE);
	private boolean showVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE);

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationForm form = (OrganizationForm) target;

		ValidationHelper.validateField("organizationName", form.getOrganizationName(), errors, true, 80);

		ValidationHelper.validateYNField(form.getIsActive(), "isActive", errors);

		// TO DO validate internetaddress here

		validateOptionalFields(form, errors);

	}

	// TODO validate fields that are optionally configured to be shown
	// only some are done here
	private void validateOptionalFields(OrganizationForm form, Errors errors) {

		ValidationHelper.validateIdField("id", form.getId(), errors, false);

		if (useParent) {

		}
		if (useOrgLocalAbbrev) {

		}
		if (useShortName) {
			// TODO tighten regex
			ValidationHelper.validateFieldAndCharset(form.getShortName(), "shortName", errors, true, 15, ".");
		}
		if (useAddressInfo) {
			validateAddressInfo(form, errors);
		}
		if (useMLS) {

		}
		if (useCLIA) {

		}
		if (useInlineOrganizationTypes) {
			validateInlineOrganizationTypes(form, errors);
		}

	}

	// TODO
	private void validateAddressInfo(OrganizationForm form, Errors errors) {
		if (useMultiUnit) {

		}
		if (showCity) {

		}
		if (showDepartment) {

		}
		if (showCommune) {

		}
		if (showVillage) {

		}
		if (useState) {

		}
		if (useZip) {

		}
	}

	private void validateInlineOrganizationTypes(OrganizationForm form, Errors errors) {
		ValidationHelper.validateFieldRequired(form.getSelectedTypes(), "selectedTypes", errors);
		if (!errors.hasErrors()) {
			for (String selectedType : form.getSelectedTypes()) {
				ValidationHelper.validateIdField(selectedType, "selectedTypes", errors, true);
				if (errors.hasErrors()) {
					break;
				}
			}
		}
	}

}
