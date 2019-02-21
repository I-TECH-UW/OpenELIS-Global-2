package spring.mine.barcode.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.barcode.form.BarcodeConfigurationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;

@Controller
public class BarcodeConfigurationController extends BaseController {
	@RequestMapping(value = "/BarcodeConfiguration", method = RequestMethod.GET)
	public ModelAndView showBarcodeConfiguration(HttpServletRequest request,
			@ModelAttribute("form") BarcodeConfigurationForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new BarcodeConfigurationForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		setFields(form);

		request.getSession().setAttribute(SAVE_DISABLED, "false");

		return findForward(forward, form);
	}

	/**
	 * Set the form fields with those values stored in the database
	 *
	 * @param form The form to populate
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void setFields(BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		// get the dimension values
		String heightOrderLabels = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.ORDER_BARCODE_HEIGHT);
		String widthOrderLabels = ConfigurationProperties.getInstance().getPropertyValue(Property.ORDER_BARCODE_WIDTH);
		String heightSpecimenLabels = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.SPECIMEN_BARCODE_HEIGHT);
		String widthSpecimenLabels = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.SPECIMEN_BARCODE_WIDTH);
		// set the dimension values
		PropertyUtils.setProperty(form, "heightOrderLabels", heightOrderLabels);
		PropertyUtils.setProperty(form, "widthOrderLabels", widthOrderLabels);
		PropertyUtils.setProperty(form, "heightSpecimenLabels", heightSpecimenLabels);
		PropertyUtils.setProperty(form, "widthSpecimenLabels", widthSpecimenLabels);

		// get the maximum print values
		String numOrderLabels = ConfigurationProperties.getInstance().getPropertyValue(Property.MAX_ORDER_PRINTED);
		String numSpecimenLabels = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.MAX_SPECIMEN_PRINTED);
		String numAliquotLabels = ConfigurationProperties.getInstance().getPropertyValue(Property.MAX_ALIQUOT_PRINTED);
		// set the maximum print values
		PropertyUtils.setProperty(form, "numOrderLabels", numOrderLabels);
		PropertyUtils.setProperty(form, "numSpecimenLabels", numSpecimenLabels);
		PropertyUtils.setProperty(form, "numAliquotLabels", numAliquotLabels);

		// get the optional specimen values
		String collectionDateCheck = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.SPECIMEN_FIELD_DATE);
		String testsCheck = ConfigurationProperties.getInstance().getPropertyValue(Property.SPECIMEN_FIELD_TESTS);
		String patientSexCheck = ConfigurationProperties.getInstance().getPropertyValue(Property.SPECIMEN_FIELD_SEX);
		// set the optional specimen values
		PropertyUtils.setProperty(form, "collectionDateCheck", collectionDateCheck);
		PropertyUtils.setProperty(form, "testsCheck", testsCheck);
		PropertyUtils.setProperty(form, "patientSexCheck", patientSexCheck);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("BarcodeConfigurationDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "barcodeconfiguration.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "barcodeconfiguration.browse.title";
	}
}
