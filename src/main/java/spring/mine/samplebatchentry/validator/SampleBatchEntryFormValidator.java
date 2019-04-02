package spring.mine.samplebatchentry.validator;

import java.util.Iterator;

import org.apache.commons.validator.GenericValidator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.internationalization.MessageUtil;
import spring.mine.samplebatchentry.form.SampleBatchEntryForm;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;

@Component
public class SampleBatchEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleBatchEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleBatchEntryForm form = (SampleBatchEntryForm) target;

		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, CustomDateValidator.PAST,
				true);

		ValidationHelper.validateTimeField(form.getCurrentTime(), "currentTime", errors, false);

		// project does not appear to need validation

		// projects does not appear to need validation

		// sampleTypes does not need validation

		validateSampleXML(form.getSampleXML(), errors);

		// TODO sampleOrderItems

		// initialSampleConditionList does not require validation

		// testSectionList does not require validation

		// patientInfoCheck does not require validation

		// facilityIDCheck does not need validation check

		ValidationHelper.validateIdField(form.getFacilityID(), "facilityID", errors, false);

		ValidationHelper.validateOptionFieldIgnoreCase(form.getMethod(), "method", errors,
				new String[] { "On Demand", "Pre-Printed" });

		ValidationHelper.validateOptionFieldIgnoreCase(form.getStudy(), "study", errors,
				new String[] { "routine", "viralLoad", "EID" });

		if (!GenericValidator.isBlankOrNull(form.getLabNo())) {
			if (!ValidationResults.SUCCESS.equals(AccessionNumberUtil.correctFormat(form.getLabNo(), false))) {
				errors.rejectValue("labNo", "error.field.accession.format");
			}
		}

		// TODO patientProperties

		// patientSearch does not need validation

		ValidationHelper.validateOptionField(form.getProgramCode(), "programCode", errors,
				new String[] { MessageUtil.getMessage("sample.entry.project.LDBS"),
						MessageUtil.getMessage("sample.entry.project.LART"), "", null });

		if (form.getStudy().equals("viralLoad")) {
			// TODO projectDataVL
		}

		if (form.getStudy().equals("EID")) {
			// TODO projectDataEID
		}

		if (form.getStudy().equals("routine")) {
			// TODO projectData
		}

		// TODO observations

		// organizationTypeLists does not require validation

		// localDBOnly does not require validation

	}

	@SuppressWarnings("unchecked")
	private void validateSampleXML(String sampleXML, Errors errors) {
		try {
			Document sampleDom = DocumentHelper.parseText(sampleXML);
			for (Iterator<Element> iter = sampleDom.getRootElement().elementIterator("sample"); iter.hasNext();) {
				Element sampleItem = iter.next();
				validateSampleItem(sampleItem, errors);
				if (errors.hasErrors()) {
					return;
				}
			}
		} catch (DocumentException e) {
			errors.reject("batchentry.error.sampleXML.invalid");
		}
	}

	private void validateSampleItem(Element sampleItem, Errors errors) {
		// validate test ids
		String[] testIDs = sampleItem.attributeValue("tests").split(",");
		for (int j = 0; j < testIDs.length; ++j) {
			ValidationHelper.validateIdField(testIDs[j], "sampleXML", "sampleXML tests", errors, false);
			if (errors.hasErrors()) {
				return;
			}

		} // validate panel ids
		String[] panelIDs = sampleItem.attributeValue("panels").split(",");
		for (int j = 0; j < panelIDs.length; ++j) {
			ValidationHelper.validateIdField(panelIDs[j], "sampleXML", "sampleXML panels", errors, false);
			if (errors.hasErrors()) {
				return;
			}
		}
		// validate date not required
		String collectionDate = sampleItem.attributeValue("date").trim();
		ValidationHelper.validateDateField(collectionDate, "sampleXML", "sampleXML date", errors,
				CustomDateValidator.PAST, false);
		if (errors.hasErrors()) {
			return;
		}

		// validate time
		String collectionTime = sampleItem.attributeValue("time").trim();
		ValidationHelper.validateTimeField(collectionTime, "sampleXML", "sampleXML time", errors, false);
		if (errors.hasErrors()) {
			return;
		}

		// validate sample id
		String sampleId = sampleItem.attributeValue("sampleID");
		ValidationHelper.validateIdField(sampleId, "sampleXML", "sampleXML sampleID", errors, true);

	}

}
