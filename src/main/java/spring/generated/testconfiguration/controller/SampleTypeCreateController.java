package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.SampleTypeCreateForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleDAO;
import us.mn.state.health.lims.systemmodule.daoimpl.SystemModuleDAOImpl;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.daoimpl.RoleModuleDAOImpl;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeCreateController extends BaseController {
	public static final String NAME_SEPARATOR = "$";

	@RequestMapping(value = "/SampleTypeCreate", method = RequestMethod.GET)
	public ModelAndView showSampleTypeCreate(HttpServletRequest request,
			@ModelAttribute("form") SampleTypeCreateForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleTypeCreateForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		try {
			PropertyUtils.setProperty(form, "existingSampleTypeList",
					DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
			PropertyUtils.setProperty(form, "inactiveSampleTypeList",
					DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE));
			List<TypeOfSample> typeOfSamples = TypeOfSampleService.getAllTypeOfSamples();
			PropertyUtils.setProperty(form, "existingEnglishNames",
					getExistingTestNames(typeOfSamples, ConfigurationProperties.LOCALE.ENGLISH));
			PropertyUtils.setProperty(form, "existingFrenchNames",
					getExistingTestNames(typeOfSamples, ConfigurationProperties.LOCALE.FRENCH));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return findForward(forward, form);
	}

	private String getExistingTestNames(List<TypeOfSample> typeOfSamples, ConfigurationProperties.LOCALE locale) {
		StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

		for (TypeOfSample typeOfSample : typeOfSamples) {
			builder.append(LocalizationService.getLocalizationValueByLocal(locale, typeOfSample.getLocalization()));
			builder.append(NAME_SEPARATOR);
		}

		return builder.toString();
	}

	@RequestMapping(value = "/SampleTypeCreate", method = RequestMethod.POST)
	public ModelAndView postSampleTypeCreate(HttpServletRequest request,
			@ModelAttribute("form") SampleTypeCreateForm form) throws Exception {

		String forward = FWD_SUCCESS_INSERT;

		BaseErrors errors = new BaseErrors();

		RoleDAO roleDAO = new RoleDAOImpl();
		RoleModuleDAOImpl roleModuleDAO = new RoleModuleDAOImpl();
		SystemModuleDAO systemModuleDAO = new SystemModuleDAOImpl();

		String identifyingName = form.getString("sampleTypeEnglishName");
		String userId = getSysUserId(request);

		Localization localization = createLocalization(form.getString("sampleTypeFrenchName"), identifyingName, userId);

		TypeOfSample typeOfSample = createTypeOfSample(identifyingName, userId);

		SystemModule workplanModule = createSystemModule("Workplan", identifyingName, userId);
		SystemModule resultModule = createSystemModule("LogbookResults", identifyingName, userId);
		SystemModule validationModule = createSystemModule("ResultValidation", identifyingName, userId);

		Role resultsEntryRole = roleDAO.getRoleByName("Results entry");
		Role validationRole = roleDAO.getRoleByName("Validator");

		RoleModule workplanResultModule = createRoleModule(userId, workplanModule, resultsEntryRole);
		RoleModule resultResultModule = createRoleModule(userId, resultModule, resultsEntryRole);
		RoleModule validationValidationModule = createRoleModule(userId, validationModule, validationRole);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			new LocalizationDAOImpl().insert(localization);
			typeOfSample.setLocalization(localization);
			new TypeOfSampleDAOImpl().insertData(typeOfSample);
			systemModuleDAO.insertData(workplanModule);
			systemModuleDAO.insertData(resultModule);
			systemModuleDAO.insertData(validationModule);
			roleModuleDAO.insertData(workplanResultModule);
			roleModuleDAO.insertData(resultResultModule);
			roleModuleDAO.insertData(validationValidationModule);

			tx.commit();

		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			lre.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE);
		DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

		return findForward(forward, form);
	}

	private Localization createLocalization(String french, String english, String currentUserId) {
		Localization localization = new Localization();
		localization.setEnglish(english);
		localization.setFrench(french);
		localization.setDescription("type of sample name");
		localization.setSysUserId(currentUserId);
		return localization;
	}

	private RoleModule createRoleModule(String userId, SystemModule workplanModule, Role role) {
		RoleModule roleModule = new RoleModule();
		roleModule.setRole(role);
		roleModule.setSystemModule(workplanModule);
		roleModule.setSysUserId(userId);
		roleModule.setHasAdd("Y");
		roleModule.setHasDelete("Y");
		roleModule.setHasSelect("Y");
		roleModule.setHasUpdate("Y");
		return roleModule;
	}

	private TypeOfSample createTypeOfSample(String identifyingName, String userId) {
		TypeOfSample typeOfSample = new TypeOfSample();
		typeOfSample.setDescription(identifyingName);
		typeOfSample.setDomain("H");
		typeOfSample.setLocalAbbreviation(
				identifyingName.length() > 10 ? identifyingName.substring(0, 10) : identifyingName);
		typeOfSample.setIsActive(false);
		typeOfSample.setSortOrder(Integer.MAX_VALUE);
		typeOfSample.setSysUserId(userId);
		String identifyingNameKey = identifyingName.replaceAll(" ", "_");
		typeOfSample.setNameKey("Sample.type." + identifyingNameKey);
		return typeOfSample;
	}

	private SystemModule createSystemModule(String menuItem, String identifyingName, String userId) {
		SystemModule module = new SystemModule();
		module.setSystemModuleName(menuItem + ":" + identifyingName);
		module.setDescription(menuItem + "=>" + identifyingName);
		module.setSysUserId(userId);
		module.setHasAddFlag("Y");
		module.setHasDeleteFlag("Y");
		module.setHasSelectFlag("Y");
		module.setHasUpdateFlag("Y");
		return module;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "sampleTypeCreateDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SampleTypeCreate.do";
		} else {
			return "PageNotFound";
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
