package spring.mine.barcode.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;
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
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class BarcodeConfigurationSaveController extends BaseController {
	@RequestMapping(value = "/BarcodeConfigurationSave", method = RequestMethod.POST)
	public ModelAndView showBarcodeConfigurationSave(HttpServletRequest request,
			@ModelAttribute("form") BarcodeConfigurationForm form) {
		String forward = FWD_SUCCESS;
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
			forward = FWD_FAIL;
		}

		return findForward(forward, form);
	}

	private ActionMessages validate(HttpServletRequest request) {
		ActionMessages errors = new ActionMessages();

		// check dimensions
		if (!GenericValidator.isFloat(request.getParameter("heightOrderLabels"))) {
			ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Order Height", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isFloat(request.getParameter("widthOrderLabels"))) {
			ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Order Width", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isFloat(request.getParameter("heightSpecimenLabels"))) {
			ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Specimen Height", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isFloat(request.getParameter("widthSpecimenLabels"))) {
			ActionError error = new ActionError("barcode.config.error.dimension.invalid", "Specimen Width", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		// check number of labels
		if (!GenericValidator.isInt(request.getParameter("numOrderLabels"))) {
			ActionError error = new ActionError("barcode.config.error.number.invalid", "Order", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isInt(request.getParameter("numSpecimenLabels"))) {
			ActionError error = new ActionError("barcode.config.error.number.invalid", "Specimen", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		// check optional fields
		if (!GenericValidator.isBool(request.getParameter("collectionDateCheck"))
				&& !GenericValidator.isBlankOrNull(request.getParameter("collectionDateCheck"))) {
			ActionError error = new ActionError("barcode.config.error.field.invalid", "Collection Date", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isBool(request.getParameter("patientSexCheck"))
				&& !GenericValidator.isBlankOrNull(request.getParameter("patientSexCheck"))) {
			ActionError error = new ActionError("barcode.config.error.field.invalid", "Patient Sex", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}
		if (!GenericValidator.isBool(request.getParameter("testsCheck"))
				&& !GenericValidator.isBlankOrNull(request.getParameter("testsCheck"))) {
			ActionError error = new ActionError("barcode.config.error.field.invalid", "Tests", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		return errors;
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
		if ("success".equals(forward)) {
			return new ModelAndView("redirect:/BarcodeConfiguration.do?forward=success", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("redirect:/BarcodeConfiguration.do?forward=fail", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
