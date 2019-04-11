package spring.mine.datasubmission.validator;

import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.datasubmission.form.DataSubmissionForm;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;

@Component
public class DataSubmissionFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DataSubmissionForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DataSubmissionForm form = (DataSubmissionForm) target;

		ValidationHelper.validateFieldMinMax(form.getYear(), "year", errors,
				Calendar.getInstance().get(Calendar.YEAR) - 25, Calendar.getInstance().get(Calendar.YEAR));

		ValidationHelper.validateOptionField(form.getSiteId(), "siteId", errors,
				new String[] { ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode) });
	}

}
