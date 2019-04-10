package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestAddForm;

@Component
public class TestAddFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestAddForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestAddForm form = (TestAddForm) target;

		// String jsonWad = "";

		// List sampleTypeList;

		// List panelList;

		// List uomList;

		// List resultTypeList;

		// List ageRangeList;

		// List labUnitList;

		// List dictionaryList;

		// List groupedDictionaryList;

	}

}
