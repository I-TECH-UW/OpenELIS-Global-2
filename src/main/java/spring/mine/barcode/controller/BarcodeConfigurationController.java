package spring.mine.barcode.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.barcode.form.BarcodeConfigurationForm;
import spring.mine.barcode.validator.BarcodeConfigurationFormValidator;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class BarcodeConfigurationController extends BaseController {

	@Autowired
	BarcodeConfigurationFormValidator validator;

	@RequestMapping(value = "/BarcodeConfiguration", method = RequestMethod.GET)
	public ModelAndView showBarcodeConfiguration(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		BarcodeConfigurationForm form = new BarcodeConfigurationForm();

		addFlashMsgsToRequest(request);
		form.setCancelAction("MasterListsPage.do");

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
		PropertyUtils.setProperty(form, "heightOrderLabels", Float.parseFloat(heightOrderLabels));
		PropertyUtils.setProperty(form, "widthOrderLabels", Float.parseFloat(widthOrderLabels));
		PropertyUtils.setProperty(form, "heightSpecimenLabels", Float.parseFloat(heightSpecimenLabels));
		PropertyUtils.setProperty(form, "widthSpecimenLabels", Float.parseFloat(widthSpecimenLabels));

		// get the maximum print values
		String numOrderLabels = ConfigurationProperties.getInstance().getPropertyValue(Property.MAX_ORDER_PRINTED);
		String numSpecimenLabels = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.MAX_SPECIMEN_PRINTED);
		String numAliquotLabels = ConfigurationProperties.getInstance().getPropertyValue(Property.MAX_ALIQUOT_PRINTED);
		// set the maximum print values
		PropertyUtils.setProperty(form, "numOrderLabels", Integer.parseInt(numOrderLabels));
		PropertyUtils.setProperty(form, "numSpecimenLabels", Integer.parseInt(numSpecimenLabels));
		PropertyUtils.setProperty(form, "numAliquotLabels", Integer.parseInt(numAliquotLabels));

		// get the optional specimen values
		String collectionDateCheck = ConfigurationProperties.getInstance()
				.getPropertyValue(Property.SPECIMEN_FIELD_DATE);
		String testsCheck = ConfigurationProperties.getInstance().getPropertyValue(Property.SPECIMEN_FIELD_TESTS);
		String patientSexCheck = ConfigurationProperties.getInstance().getPropertyValue(Property.SPECIMEN_FIELD_SEX);
		// set the optional specimen values
		PropertyUtils.setProperty(form, "collectionDateCheck", Boolean.valueOf(collectionDateCheck));
		PropertyUtils.setProperty(form, "testsCheck", Boolean.valueOf(testsCheck));
		PropertyUtils.setProperty(form, "patientSexCheck", Boolean.valueOf(patientSexCheck));
	}

	@RequestMapping(value = "/BarcodeConfiguration", method = RequestMethod.POST)
	public ModelAndView barcodeConfigurationSave(HttpServletRequest request,
			@ModelAttribute("form") BarcodeConfigurationForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		validator.validate(form, result);
		form.setCancelAction("MasterListsPage.do");
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			updateSiteInfo(siteInformationDAO, "heightOrderLabels", Float.toString(form.getHeightOrderLabels()),
					"text");
			updateSiteInfo(siteInformationDAO, "widthOrderLabels", Float.toString(form.getWidthOrderLabels()), "text");
			updateSiteInfo(siteInformationDAO, "heightSpecimenLabels", Float.toString(form.getHeightSpecimenLabels()),
					"text");
			updateSiteInfo(siteInformationDAO, "widthSpecimenLabels", Float.toString(form.getWidthSpecimenLabels()),
					"text");

			updateSiteInfo(siteInformationDAO, "numOrderLabels", Integer.toString(form.getNumOrderLabels()), "text");
			updateSiteInfo(siteInformationDAO, "numSpecimenLabels", Integer.toString(form.getNumSpecimenLabels()),
					"text");

			updateSiteInfo(siteInformationDAO, "collectionDateCheck", Boolean.toString(form.getCollectionDateCheck()),
					"boolean");
			updateSiteInfo(siteInformationDAO, "patientSexCheck", Boolean.toString(form.getPatientSexCheck()),
					"boolean");
			updateSiteInfo(siteInformationDAO, "testsCheck", Boolean.toString(form.getTestsCheck()), "boolean");

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			result.reject("barcode.config.error.insert");
		} finally {
			HibernateUtil.closeSession();
			ConfigurationProperties.forceReload();
		}

		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
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
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "BarcodeConfigurationDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/BarcodeConfiguration.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "BarcodeConfigurationDefinition";
		} else {
			return "PageNotFound";
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
