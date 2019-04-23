package spring.mine.siteinformation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.constants.Constants;
import spring.mine.common.controller.BaseMenuController;
import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.siteinformation.form.SiteInformationMenuForm;
import spring.mine.siteinformation.validator.SiteInformationMenuFormValidator;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class SiteInformationMenuController extends BaseMenuController {

	@Autowired
	SiteInformationMenuFormValidator formValidator;

	private String titleKey = null;

	@RequestMapping(value = { "/NonConformityConfigurationMenu", "/WorkplanConfigurationMenu",
			"/PrintedReportsConfigurationMenu", "/SampleEntryConfigMenu", "/ResultConfigurationMenu",
			"/MenuStatementConfigMenu", "/PatientConfigurationMenu",
			"/SiteInformationMenu" }, method = RequestMethod.GET)
	public ModelAndView showSiteInformationMenu(HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		SiteInformationMenuForm form = new SiteInformationMenuForm();
		setupFormForRequest(form, request);

		String forward = performMenuAction(form, request);
		if (FWD_FAIL.equals(forward)) {
			Errors errors = new BaseErrors();
			errors.reject("error.generic");
			redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, errors);
			return findForward(FWD_FAIL, form);
		} else {
			addFlashMsgsToRequest(request);
			return findForward(forward, form);
		}
	}

	private void setupFormForRequest(SiteInformationMenuForm form, HttpServletRequest request) {
		String path = request.getServletPath();
		if (path.contains("NonConformityConfiguration")) {
			form.setSiteInfoDomainName("non_conformityConfiguration");
			form.setFormName("NonConformityConfigurationMenuForm");
		} else if (path.contains("WorkplanConfiguration")) {
			form.setSiteInfoDomainName("WorkplanConfiguration");
			form.setFormName("WorkplanConfigurationMenuForm");
		} else if (path.contains("PrintedReportsConfiguration")) {
			form.setSiteInfoDomainName("PrintedReportsConfiguration");
			form.setFormName("PrintedReportsConfigurationMenuForm");
		} else if (path.contains("SampleEntryConfig")) {
			form.setSiteInfoDomainName("sampleEntryConfig");
			form.setFormName("sampleEntryConfigMenuForm");
		} else if (path.contains("ResultConfiguration")) {
			form.setSiteInfoDomainName("ResultConfiguration");
			form.setFormName("resultConfigurationMenuForm");
		} else if (path.contains("MenuStatementConfig")) {
			form.setSiteInfoDomainName("MenuStatementConfig");
			form.setFormName("MenuStatementConfigMenuForm");
		} else if (path.contains("PatientConfiguration")) {
			form.setSiteInfoDomainName("PatientConfiguration");
			form.setFormName("PatientConfigurationMenuForm");
		} else {
			form.setSiteInfoDomainName("SiteInformation");
			form.setFormName("siteInformationMenuForm");
		}

		form.setFormMethod(RequestMethod.POST);
	}

	@Override
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {
		List<SiteInformation> configurationList;

		String domainName = form.getString("siteInfoDomainName");
		String dbDomainName = null;
		if ("SiteInformation".equals(domainName)) {
			dbDomainName = "siteIdentity";
			titleKey = "siteInformation.browse.title";
		} else if ("ResultConfiguration".equals(domainName)) {
			dbDomainName = "resultConfiguration";
			titleKey = "resultConfiguration.browse.title";
		} else if ("sampleEntryConfig".equals(domainName)) {
			dbDomainName = "sampleEntryConfig";
			titleKey = "sample.entry.browse.title";
		} else if ("PrintedReportsConfiguration".equals(domainName)) {
			dbDomainName = "printedReportsConfig";
			titleKey = "printedReportsConfiguration.browse.title";
		} else if ("WorkplanConfiguration".equals(domainName)) {
			dbDomainName = "workplanConfig";
			titleKey = "workplanConfiguration.browse.title";
		} else if ("non_conformityConfiguration".equals(domainName)) {
			dbDomainName = "non_conformityConfig";
			titleKey = "nonConformityConfiguration.browse.title";
		} else if ("PatientConfiguration".equals(domainName)) {
			dbDomainName = "patientEntryConfig";
			titleKey = "patientEntryConfiguration.browse.title";
		} else if ("MenuStatementConfig".equals(domainName)) {
			dbDomainName = "MenuStatementConfig";
			titleKey = "MenuStatementConfig.browse.title";
		}

		int startingRecNo = Integer.parseInt((String) request.getAttribute("startingRecNo"));

		request.setAttribute("menuDefinition", "SiteInformationMenuDefinition");

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

		configurationList = siteInformationDAO.getPageOfSiteInformationByDomainName(startingRecNo, dbDomainName);

		hideEncryptedFields(configurationList);

		setDisplayPageBounds(request, configurationList == null ? 0 : configurationList.size(), startingRecNo,
				siteInformationDAO.getCountForDomainName(dbDomainName));

		return configurationList;
	}

	private void hideEncryptedFields(List<SiteInformation> siteInformationList) {
		for (SiteInformation siteInformation : siteInformationList) {
			if (siteInformation.isEncrypted() && !GenericValidator.isBlankOrNull(siteInformation.getValue())) {
				siteInformation.setValue(siteInformation.getValue().replaceAll(".", "*"));
			}
		}

	}

	@Override
	protected String getDeactivateDisabled() {
		return "true";
	}

	@Override
	protected String getAddDisabled() {
		return "true";
	}

	@RequestMapping(value = { "/DeleteMenuStatementConfig", "/DeleteWorkplanConfiguration",
			"/DeletePatientConfiguration", "/DeleteNonConformityConfiguration", "/DeleteResultConfiguration",
			"/DeletePrintedReportsConfiguration", "/DeleteSiteInformation" }, method = RequestMethod.GET)
	public ModelAndView showDeleteSiteInformation(HttpServletRequest request,
			@ModelAttribute("form") @Valid SiteInformationMenuForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, result);
			return findForward(FWD_FAIL_DELETE, form);
		}

		List<String> selectedIDs = (List<String>) form.get("selectedIDs");

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {

			for (String siteInformationId : selectedIDs) {
				siteInformationDAO.deleteData(siteInformationId, getSysUserId(request));
			}

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			String errorMsg;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errorMsg = "errors.OptimisticLockException";
			} else {
				errorMsg = "errors.DeleteException";
			}
			result.reject(errorMsg);
			redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, result);
			return findForward(FWD_FAIL_DELETE, form);

		} finally {
			HibernateUtil.closeSession();
		}

		ConfigurationProperties.forceReload();

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_DELETE, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "haitiMasterListsPageDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_SUCCESS_DELETE.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_FAIL_DELETE.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return titleKey;
	}

	@Override
	protected String getPageSubtitleKey() {
		return titleKey;
	}
}
