package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestModifyEntryForm;

@Component
public class TestModifyEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestModifyEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestModifyEntryForm form = (TestModifyEntryForm) target;

		// List testList;

		// String nameEnglish = "";

		// String nameFrench = "";

		// String reportNameEnglish = "";

		// String reportNameFrench = "";

		// String testId = "";

		// String loinc = "";

		// String jsonWad = "";

		// List sampleTypeList;

		// List panelList;

		// List uomList;

		// List resultTypeList;

		// List ageRangeList;

		// List labUnitList;

		// List dictionaryList;

		// List groupedDictionaryList;

		// List testCatBeanList;
	}

}
