package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.SampleTypeCreateForm;
import spring.mine.common.controller.BaseController;
import spring.service.localization.LocalizationService;
import spring.service.role.RoleService;
import spring.service.testconfiguration.SampleTypeCreateService;
import spring.service.typeofsample.TypeOfSampleService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeCreateController extends BaseController {

	public static final String NAME_SEPARATOR = "$";

	@Autowired
	private TypeOfSampleService typeOfSampleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SampleTypeCreateService sampleTypeCreateService;
	@Autowired
	private LocalizationService localizationService;

	@RequestMapping(value = "/SampleTypeCreate", method = RequestMethod.GET)
	public ModelAndView showSampleTypeCreate(HttpServletRequest request) {

		SampleTypeCreateForm form = new SampleTypeCreateForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(SampleTypeCreateForm form) {
		try {
			PropertyUtils.setProperty(form, "existingSampleTypeList",
					DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
			PropertyUtils.setProperty(form, "inactiveSampleTypeList",
					DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE));
			List<TypeOfSample> typeOfSamples = typeOfSampleService.getAllTypeOfSamples();
			PropertyUtils.setProperty(form, "existingEnglishNames",
					getExistingTestNames(typeOfSamples, Locale.ENGLISH));
			PropertyUtils.setProperty(form, "existingFrenchNames",
					getExistingTestNames(typeOfSamples, Locale.FRENCH));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private String getExistingTestNames(List<TypeOfSample> typeOfSamples, Locale locale) {
		StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

		for (TypeOfSample typeOfSample : typeOfSamples) {
			builder.append(typeOfSample.getLocalization().getLocalizedValue(locale));
			builder.append(NAME_SEPARATOR);
		}

		return builder.toString();
	}

	@RequestMapping(value = "/SampleTypeCreate", method = RequestMethod.POST)
	public ModelAndView postSampleTypeCreate(HttpServletRequest request,
			@ModelAttribute("form") @Valid SampleTypeCreateForm form, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}
		String identifyingName = form.getString("sampleTypeEnglishName");
		String userId = getSysUserId(request);

		Localization localization = createLocalization(form.getString("sampleTypeFrenchName"), identifyingName, userId);

		TypeOfSample typeOfSample = createTypeOfSample(identifyingName, userId);

		SystemModule workplanModule = createSystemModule("Workplan", identifyingName, userId);
		SystemModule resultModule = createSystemModule("LogbookResults", identifyingName, userId);
		SystemModule validationModule = createSystemModule("ResultValidation", identifyingName, userId);

		Role resultsEntryRole = roleService.getRoleByName("Results entry");
		Role validationRole = roleService.getRoleByName("Validator");

		RoleModule workplanResultModule = createRoleModule(userId, workplanModule, resultsEntryRole);
		RoleModule resultResultModule = createRoleModule(userId, resultModule, resultsEntryRole);
		RoleModule validationValidationModule = createRoleModule(userId, validationModule, validationRole);

		try {
			sampleTypeCreateService.createAndInsertSampleType(localization, typeOfSample, workplanModule, resultModule,
					validationModule, workplanResultModule, resultResultModule, validationValidationModule);
		} catch (LIMSRuntimeException lre) {
			lre.printStackTrace();
		}
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.SAMPLE_TYPE);
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
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
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "sampleTypeCreateDefinition";
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
