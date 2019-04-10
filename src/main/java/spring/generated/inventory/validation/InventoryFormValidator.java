package spring.generated.inventory.validation;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import spring.generated.inventory.form.InventoryForm;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

public class InventoryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return InventoryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InventoryForm form = (InventoryForm) target;

		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, DateRelation.TODAY);

		// TODO inventoryItems

		try {
			DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(form.getNewKitsXML())));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			errors.rejectValue("newKitsXMl", "errors.field.format.xml");
			e.printStackTrace();
		}
	}

}
