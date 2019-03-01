package spring.mine.barcode.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
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
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

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

	@RequestMapping(value = "/BarcodeConfigurationSave", method = RequestMethod.POST)
	public ModelAndView showBarcodeConfigurationSave(HttpServletRequest request,
			@ModelAttribute("form") BarcodeConfigurationForm form) {
		String forward = FWD_SUCCESS_INSERT;
		if (form == null) {
			form = new BarcodeConfigurationForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			updateSiteInfo(siteInformationDAO, "heightOrderLabels", form.getHeightOrderLabels(), "text");
			updateSiteInfo(siteInformationDAO, "widthOrderLabels", form.getWidthOrderLabels(), "text");
			updateSiteInfo(siteInformationDAO, "heightSpecimenLabels", form.getHeightSpecimenLabels(), "text");
			updateSiteInfo(siteInformationDAO, "widthSpecimenLabels", form.getWidthSpecimenLabels(), "text");

			updateSiteInfo(siteInformationDAO, "numOrderLabels", form.getNumOrderLabels(), "text");
			updateSiteInfo(siteInformationDAO, "numSpecimenLabels", form.getNumSpecimenLabels(), "text");

			updateSiteInfo(siteInformationDAO, "collectionDateCheck", form.getCollectionDateCheck(), "boolean");
			updateSiteInfo(siteInformationDAO, "patientSexCheck", form.getPatientSexCheck(), "boolean");
			updateSiteInfo(siteInformationDAO, "testsCheck", form.getTestsCheck(), "boolean");

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			errors.reject("barcode.config.error.insert");
		} finally {
			HibernateUtil.closeSession();
			ConfigurationProperties.forceReload();
		}

		if (errors.hasErrors()) {
			saveErrors(errors);
			forward = FWD_FAIL_INSERT;
		}

		return findForward(forward, form);
	}

	/**
	 * Persist a bar code configuration value in the database under site_information
	 *
	 * @param errors    For error tracking on inserts
	 * @param name      The name in the database
	 * @param value     The new value to save
	 * @param valueType The type of the value to save
	 */
	private void updateSiteInfo(SiteInformationDAO siteInformationDAO, String name, String value, String valueType) {
		if ("boolean".equals(valueType)) {
			value = "true".equalsIgnoreCase(value) ? "true" : "false";
		}
		SiteInformation siteInformation = siteInformationDAO.getSiteInformationByName(name);
		if (siteInformation == null) {
			siteInformation = new SiteInformation();
			siteInformation.setName(name);
			siteInformation.setValue(value);
			siteInformation.setValueType(valueType);
			siteInformation.setSysUserId(getSysUserId(request));
			siteInformationDAO.insertData(siteInformation);
		} else {
			siteInformation.setValue(value);
			siteInformation.setSysUserId(getSysUserId(request));
			siteInformationDAO.updateData(siteInformation);
		}

	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("BarcodeConfigurationDefinition", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return new ModelAndView("redirect:/BarcodeConfiguration.do?forward=success", "form", form);
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return new ModelAndView("redirect:/BarcodeConfiguration.do?forward=fail", "form", form);
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
