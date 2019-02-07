package spring.mine.siteinformation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.MenuStatementConfigForm;
import spring.generated.forms.NonConformityConfigurationForm;
import spring.generated.forms.PatientConfigurationForm;
import spring.generated.forms.PrintedReportsConfigurationForm;
import spring.generated.forms.ResultConfigurationForm;
import spring.generated.forms.SampleEntryConfigForm;
import spring.generated.forms.SiteInformationForm;
import spring.generated.forms.WorkplanConfigurationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.internationalization.MessageUtil;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.PhoneNumberService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationSideEffects;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDomainDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformationDomain;

@Controller
@SessionAttributes("form")
public class SiteInformationController extends BaseController {

	@ModelAttribute("form")
	public BaseForm form(HttpServletRequest request) {
		return findForm(request);
	}

	private static final String ACCESSION_NUMBER_PREFIX = "Accession number prefix";
	private static final SiteInformationDomain SITE_IDENTITY_DOMAIN;
	private static final SiteInformationDomain RESULT_CONFIG_DOMAIN;

	static {
		SITE_IDENTITY_DOMAIN = new SiteInformationDomainDAOImpl().getByName("siteIdentity");
		RESULT_CONFIG_DOMAIN = new SiteInformationDomainDAOImpl().getByName("resultConfiguration");
	}

	@RequestMapping(value = { "/NonConformityConfiguration", "/WorkplanConfiguration", "/PrintedReportsConfiguration",
			"/SampleEntryConfig", "/ResultConfiguration", "/MenuStatementConfig", "/PatientConfiguration",
			"/SiteInformation" }, method = RequestMethod.POST)
	public ModelAndView showSiteInformation(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		BaseErrors errors = new BaseErrors();
		form.setCancelAction("Cancel" + form.getFormAction() + ".do");
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		String id = request.getParameter(ID);

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);

		boolean isNew = id == null || "0".equals(id);

		if (!isNew) {
			SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();

			SiteInformation siteInformation = new SiteInformation();
			siteInformation.setId(id);
			siteInformationDAO.getData(siteInformation);
			request.setAttribute(ID, siteInformation.getId());

			PropertyUtils.setProperty(form, "paramName", siteInformation.getName());
			PropertyUtils.setProperty(form, "description", getInstruction(siteInformation));
			PropertyUtils.setProperty(form, "value", siteInformation.getValue());
			setLocalizationValues(form, siteInformation);
			PropertyUtils.setProperty(form, "encrypted", siteInformation.isEncrypted());
			PropertyUtils.setProperty(form, "valueType", siteInformation.getValueType());
			PropertyUtils.setProperty(form, "editable", isEditable(siteInformation));
			PropertyUtils.setProperty(form, "tag", siteInformation.getTag());

			if ("dictionary".equals(siteInformation.getValueType())) {
				List<String> dictionaryValues = new ArrayList<>();

				List<Dictionary> dictionaries = new DictionaryDAOImpl()
						.getDictionaryEntriesByCategoryId(siteInformation.getDictionaryCategoryId());

				for (Dictionary dictionary : dictionaries) {
					dictionaryValues.add(dictionary.getDictEntry());
				}

				PropertyUtils.setProperty(form, "dictionaryValues", dictionaryValues);
			}

		}

		String domainName = form.getString("siteInfoDomainName");
		if ("SiteInformation".equals(domainName)) {
			if (isNew) {
				request.setAttribute("key", "siteInformation.add.title");
			} else {
				request.setAttribute("key", "siteInformation.edit.title");
			}
		} else if ("ResultConfiguration".equals(domainName)) {
			if (isNew) {
				request.setAttribute("key", "resultConfiguration.add.title");
			} else {
				request.setAttribute("key", "resultConfiguration.edit.title");
			}
		}

		return findForward(forward, form);
	}

	private BaseForm findForm(HttpServletRequest request) {
		BaseForm form;
		String path = request.getServletPath();
		if (path.contains("NonConformityConfiguration")) {
			form = new NonConformityConfigurationForm();
			form.setFormAction("NonConformityConfiguration");

		} else if (path.contains("WorkplanConfiguration")) {
			form = new WorkplanConfigurationForm();
			form.setFormAction("WorkplanConfiguration");

		} else if (path.contains("PrintedReportsConfiguration")) {
			form = new PrintedReportsConfigurationForm();
			form.setFormAction("PrintedReportsConfiguration");

		} else if (path.contains("SampleEntryConfig")) {
			form = new SampleEntryConfigForm();
			form.setFormAction("SampleEntryConfig");

		} else if (path.contains("ResultConfiguration")) {
			form = new ResultConfigurationForm();
			form.setFormAction("ResultConfiguration");

		} else if (path.contains("MenuStatementConfig")) {
			form = new MenuStatementConfigForm();
			form.setFormAction("MenuStatementConfig");

		} else if (path.contains("PatientConfiguration")) {
			form = new PatientConfigurationForm();
			form.setFormAction("PatientConfiguration");

		} else {
			form = new SiteInformationForm();
			form.setFormAction("SiteInformation");
		}
		return form;
	}

	private String getInstruction(SiteInformation siteInformation) {
		String instruction = MessageUtil.getMessage(siteInformation.getInstructionKey());
		if (GenericValidator.isBlankOrNull(instruction)) {
			instruction = MessageUtil.getMessage(siteInformation.getDescriptionKey());
		}

		return GenericValidator.isBlankOrNull(instruction) ? siteInformation.getDescription() : instruction;
	}

	private void setLocalizationValues(BaseForm form, SiteInformation siteInformation)
			throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		if ("localization".equals(siteInformation.getTag())) {
			LocalizationService localizationService = new LocalizationService(siteInformation.getValue());
			Localization localization = localizationService.getLocalization();
			PropertyUtils.setProperty(form, "englishValue", localization.getEnglish());
			PropertyUtils.setProperty(form, "frenchValue", localization.getFrench());

		}
	}

	private Boolean isEditable(SiteInformation siteInformation) {
		if (ACCESSION_NUMBER_PREFIX.endsWith(siteInformation.getName())) {
			return new SampleDAOImpl().getTotalCount("Sample", Sample.class) == 0;
		}
		return Boolean.TRUE;
	}

	@RequestMapping(value = { "/UpdateNonConformityConfiguration", "/UpdateWorkplanConfiguration",
			"/UpdatePrintedReportsConfiguration", "/UpdateSampleEntryConfig", "/UpdateResultConfiguration",
			"/UpdateMenuStatementConfig", "/UpdatePatientConfiguration",
			"/UpdateSiteInformation" }, method = RequestMethod.POST)
	public ModelAndView showUpdateSiteInformation(HttpServletRequest request, @ModelAttribute("form") BaseForm form,
			SessionStatus status) {
		String forward = FWD_SUCCESS;
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);
		boolean isNew = id == null || id.equals("0");

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		String tag = form.getString("tag");

		// N.B. The reason for this branch is that localization does not actually update
		// site information, it updates the
		// localization table
		if ("localization".equals(tag)) {
			String localizationId = form.getString("value");
			forward = validateAndUpdateLocalization(request, localizationId, form.getString("englishValue"),
					form.getString("frenchValue"));
		} else {
			forward = validateAndUpdateSiteInformation(request, form, isNew);
		}
		// makes the changes take effect immediately
		ConfigurationProperties.forceReload();
		// signal to remove from from session
		status.setComplete();
		return FWD_FAIL.equals(forward) || FWD_FAIL_INSERT.equals(forward) ? findForward(forward, form)
				: getForward(findForward(forward, form), id, start, direction);
	}

	private String validateAndUpdateLocalization(HttpServletRequest request, String localizationId, String english,
			String french) {
		LocalizationService localizationService = new LocalizationService(localizationId);
		localizationService.setCurrentUserId(currentUserId);

		String forward = FWD_SUCCESS_INSERT;
		if (localizationService.updateLocalizationIfNeeded(english, french)) {

			Errors errors;
			Transaction tx = HibernateUtil.getSession().beginTransaction();
			try {
				new LocalizationDAOImpl().updateData(localizationService.getLocalization());
				tx.commit();
			} catch (LIMSRuntimeException lre) {
				tx.rollback();
				errors = new BaseErrors();
				errors.reject("errors.UpdateException");
				saveErrors(errors);
				request.setAttribute(Globals.ERROR_KEY, errors);

				forward = FWD_FAIL_INSERT;

			} finally {
				HibernateUtil.closeSession();
			}

		}

		return forward;
	}

	public String validateAndUpdateSiteInformation(HttpServletRequest request, BaseForm form,
			boolean newSiteInformation) {

		String name = form.getString("paramName");
		String value = form.getString("value");
		Errors errors = new BaseErrors();

		if (!isValid(request, name, value, errors)) {
			return FWD_FAIL_INSERT;
		}

		String forward = FWD_SUCCESS_INSERT;
		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		SiteInformation siteInformation = new SiteInformation();

		if (newSiteInformation) {
			siteInformation.setName(name);
			siteInformation.setDescription(form.getString("description"));
			siteInformation.setValueType("text");
			siteInformation.setEncrypted((Boolean) form.get("encrypted"));
			siteInformation.setDomain(SITE_IDENTITY_DOMAIN);
		} else {
			siteInformation.setId(request.getParameter(ID));
			siteInformationDAO.getData(siteInformation);
		}

		siteInformation.setValue(value);
		siteInformation.setSysUserId(currentUserId);

		String domainName = form.getString("siteInfoDomainName");

		if ("SiteInformation".equals(domainName)) {
			siteInformation.setDomain(SITE_IDENTITY_DOMAIN);
		} else if ("ResultConfiguration".equals(domainName)) {
			siteInformation.setDomain(RESULT_CONFIG_DOMAIN);
		}
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {

			if (newSiteInformation) {
				siteInformationDAO.insertData(siteInformation);
			} else {
				siteInformationDAO.updateData(siteInformation);
			}

			new ConfigurationSideEffects().siteInformationChanged(siteInformation);

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			String errorMsg;
			if (lre.getException() instanceof StaleObjectStateException) {

				errorMsg = "errors.OptimisticLockException";

			} else {
				errorMsg = "errors.UpdateException";
			}

			errors.reject(errorMsg);
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL_INSERT;

		} finally {
			HibernateUtil.closeSession();
		}

		return forward;
	}

	private boolean isValid(HttpServletRequest request, String name, String value, Errors errors) {
		if (GenericValidator.isBlankOrNull(name)) {
			errors.reject("error.SiteInformation.name.required");
			request.setAttribute(Globals.ERROR_KEY, errors);
			saveErrors(errors);

			return false;
		}

		if ("phone format".equals(name) && !PhoneNumberService.validatePhoneFormat(value)) {
			errors.reject("error.SiteInformation.phone.format");
			request.setAttribute(Globals.ERROR_KEY, errors);
			saveErrors(errors);

			return false;
		}

		return true;
	}

	@RequestMapping(value = { "/NextPreviousNonConformityConfiguration", "/NextPreviousWorkplanConfiguration",
			"/NextPreviousPrintedReportsConfiguration", "/NextPreviousSampleEntryConfig",
			"/NextPreviousResultConfiguration", "/NextPreviousMenuStatementConfig", "/NextPreviousPatientConfiguration",
			"/NextPreviousSiteInformation", "/UpdateNextPreviousNonConformityConfiguration",
			"/UpdateNextPreviousWorkplanConfiguration", "/UpdateNextPreviousPrintedReportsConfiguration",
			"/UpdateNextPreviousSampleEntryConfig", "/UpdateNextPreviousResultConfiguration",
			"/UpdateNextPreviousMenuStatementConfig", "/UpdateNextPreviousPatientConfiguration",
			"/UpdateNextPreviousSiteInformation" }, method = RequestMethod.POST)
	public ModelAndView showNextPreviousSiteInformation(HttpServletRequest request,
			@ModelAttribute("form") BaseForm form) {
		String forward = FWD_SUCCESS;
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		request.setAttribute(ALLOW_EDITS_KEY, TRUE);
		request.setAttribute(PREVIOUS_DISABLED, FALSE);
		request.setAttribute(NEXT_DISABLED, FALSE);

		String id = request.getParameter(ID);

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		if (id != null && !id.equals("0")) {
			String updateResponse = validateAndUpdateSiteInformation(request, form, false);

			if (updateResponse == FWD_FAIL_INSERT) {
				return getForward(findForward(FWD_FAIL_INSERT, form), id, start, null);
			}
		}

		SiteInformation siteInformation = new SiteInformation();
		siteInformation.setId(id);

		try {

			SiteInformationDAO SiteInformationDAO = new SiteInformationDAOImpl();

			SiteInformationDAO.getData(siteInformation);

			if (FWD_NEXT.equals(direction)) {

				List<SiteInformation> SiteInformations = SiteInformationDAO
						.getNextSiteInformationRecord(siteInformation.getId());

				if (SiteInformations != null && SiteInformations.size() > 0) {
					siteInformation = SiteInformations.get(0);
					SiteInformationDAO.getData(siteInformation);
					if (SiteInformations.size() < 2) {
						// disable next button
						request.setAttribute(NEXT_DISABLED, TRUE);
					}
					id = siteInformation.getId();
				} else {
					// just disable next button
					request.setAttribute(NEXT_DISABLED, TRUE);
				}
			}

			if (FWD_PREVIOUS.equals(direction)) {

				List<SiteInformation> SiteInformations = SiteInformationDAO
						.getPreviousSiteInformationRecord(siteInformation.getId());

				if (SiteInformations != null && SiteInformations.size() > 0) {
					siteInformation = SiteInformations.get(0);
					SiteInformationDAO.getData(siteInformation);
					if (SiteInformations.size() < 2) {
						// disable previous button
						request.setAttribute(PREVIOUS_DISABLED, TRUE);
					}
					id = siteInformation.getId();
				} else {
					// just disable next button
					request.setAttribute(PREVIOUS_DISABLED, TRUE);
				}
			}

		} catch (LIMSRuntimeException lre) {
			request.setAttribute(ALLOW_EDITS_KEY, FALSE);
			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL_INSERT;
		}
		if (forward.equals(FWD_FAIL)) {
			return findForward(forward, form);
		}

		if (siteInformation.getId() != null && !siteInformation.getId().equals("0")) {
			request.setAttribute(ID, siteInformation.getId());
		}

		return getForward(findForward(forward, form), id, start, null);

	}

	@RequestMapping(value = { "/CancelNonConformityConfiguration", "/CancelWorkplanConfiguration",
			"/CancelPrintedReportsConfiguration", "/CancelSampleEntryConfig", "/CancelResultConfiguration",
			"/CancelMenuStatementConfig", "/CancelPatientConfiguration",
			"/CancelSiteInformation" }, method = RequestMethod.GET)
	public ModelAndView cancelSiteInformation(HttpServletRequest request, @ModelAttribute("form") BaseForm form,
			SessionStatus status) {
		status.setComplete();
		return findForward(FWD_CANCEL, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("siteInformationDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("masterListsPageDefinition", "form", form);
		} else if ("insertSuccess".equals(forward)) {
			String url = form.getFormAction() + "Menu.do";
			return new ModelAndView("redirect:/" + url, "form", form);
		} else if ("insertFailure".equals(forward)) {
			String url = form.getFormAction() + ".do";
			return new ModelAndView("redirect:/" + url, "form", form);
		} else if (FWD_CANCEL.equals(forward)) {
			String url = form.getFormAction() + "Menu.do";
			return new ModelAndView("redirect:/" + url, "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return (String) request.getAttribute("key");
	}

	@Override
	protected String getPageSubtitleKey() {
		return (String) request.getAttribute("key");
	}

}
