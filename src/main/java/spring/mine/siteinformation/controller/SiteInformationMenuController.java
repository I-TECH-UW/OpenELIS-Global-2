package spring.mine.siteinformation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseMenuController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.siteinformation.form.MenuStatementConfigMenuForm;
import spring.mine.siteinformation.form.NonConformityConfigurationMenuForm;
import spring.mine.siteinformation.form.PatientConfigurationMenuForm;
import spring.mine.siteinformation.form.PrintedReportsConfigurationMenuForm;
import spring.mine.siteinformation.form.ResultConfigurationMenuForm;
import spring.mine.siteinformation.form.SampleEntryConfigMenuForm;
import spring.mine.siteinformation.form.SiteInformationMenuForm;
import spring.mine.siteinformation.form.WorkplanConfigurationMenuForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class SiteInformationMenuController extends BaseMenuController {
	private String titleKey = null;
	static private String FWD_CLOSE = "close";

	@RequestMapping(value = { "/NonConformityConfigurationMenu", "/WorkplanConfigurationMenu",
			"/PrintedReportsConfigurationMenu", "/SampleEntryConfigMenu", "/ResultConfigurationMenu",
			"/MenuStatementConfigMenu", "/PatientConfigurationMenu",
			"/SiteInformationMenu" }, method = RequestMethod.GET)
	public ModelAndView showSiteInformationMenu(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		MenuForm form = findForm(request);
		form.setFormAction("");
		form.setFormMethod(RequestMethod.POST);
		Errors errors = new BaseErrors();
		

		return performMenuAction(form, request);
	}

	private MenuForm findForm(HttpServletRequest request) {
		MenuForm form;
		String path = request.getServletPath();
		if (path.contains("NonConformityConfiguration")) {
			form = new NonConformityConfigurationMenuForm();
		} else if (path.contains("WorkplanConfiguration")) {
			form = new WorkplanConfigurationMenuForm();
		} else if (path.contains("PrintedReportsConfiguration")) {
			form = new PrintedReportsConfigurationMenuForm();
		} else if (path.contains("SampleEntryConfig")) {
			form = new SampleEntryConfigMenuForm();
		} else if (path.contains("ResultConfiguration")) {
			form = new ResultConfigurationMenuForm();
		} else if (path.contains("MenuStatementConfig")) {
			form = new MenuStatementConfigMenuForm();
		} else if (path.contains("PatientConfiguration")) {
			form = new PatientConfigurationMenuForm();
		} else {
			form = new SiteInformationMenuForm();
		}
		return form;
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
	public ModelAndView showDeleteSiteInformation(HttpServletRequest request, @ModelAttribute("form") MenuForm form) {
		String forward = FWD_SUCCESS;
		form.setFormAction("");
		Errors errors = new BaseErrors();
		
		String[] selectedIDs = (String[]) form.get("selectedIDs");
		String currentUserId = getSysUserId(request);

		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
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
			errors.reject(errorMsg);
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			forward = FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}

		if (forward.equals(FWD_FAIL)) {
			return findForward(forward, form);
		}

		if (TRUE.equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		ConfigurationProperties.forceReload();

		request.setAttribute("menuDefinition", "SiteInformationMenuDefinition");

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "haitiMasterListsPageDefinition";
		} else if (FWD_FAIL.equals(forward)) {
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
