package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.PanelCreateForm;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import spring.service.localization.LocalizationService;
import spring.service.localization.LocalizationServiceImpl;
import spring.service.role.RoleService;
import spring.service.rolemodule.RoleModuleService;
import spring.service.panel.PanelService;
import spring.service.systemmodule.SystemModuleService;
import spring.service.systemusermodule.PermissionModuleService;
import spring.service.typeofsample.TypeOfSamplePanelService;
import spring.service.typeofsample.TypeOfSampleService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.daoimpl.RoleModuleDAOImpl;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.testconfiguration.action.PanelTestConfigurationUtil;
import us.mn.state.health.lims.testconfiguration.action.SampleTypePanel;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSamplePanelDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

@Controller
public class PanelCreateController extends BaseController {
	
	@Autowired
	PanelService panelService;
	@Autowired
	RoleService roleService;
	@Autowired
	RoleModuleService roleModuleService;
	@Autowired
	SystemModuleService systemModuleService;
	@Autowired
	TypeOfSampleService typeOfSampleService;
	@Autowired
	TypeOfSamplePanelService typeOfSamplePanelService;
	@Autowired
	LocalizationService localizationService;

	public static final String NAME_SEPARATOR = "$";

	@RequestMapping(value = "/PanelCreate", method = RequestMethod.GET)
	public ModelAndView showPanelCreate(HttpServletRequest request) {
		PanelCreateForm form = new PanelCreateForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(PanelCreateForm form) {
		HashMap<String, List<Panel>> existingSampleTypePanelMap = PanelTestConfigurationUtil
				.createTypeOfSamplePanelMap(true);
		HashMap<String, List<Panel>> inactiveSampleTypePanelMap = PanelTestConfigurationUtil
				.createTypeOfSamplePanelMap(false);
		try {
			PropertyUtils.setProperty(form, "existingSampleTypeList",
					DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Panel> panels = panelService.getAllPanels();
		try {
			PropertyUtils.setProperty(form, "existingEnglishNames",
					getExistingTestNames(panels, ConfigurationProperties.LOCALE.ENGLISH));
			PropertyUtils.setProperty(form, "existingFrenchNames",
					getExistingTestNames(panels, ConfigurationProperties.LOCALE.FRENCH));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<SampleTypePanel> sampleTypePanelsExists = new ArrayList<>();
		List<SampleTypePanel> sampleTypePanelsInactive = new ArrayList<>();

		for (IdValuePair typeOfSample : DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE)) {
			SampleTypePanel sampleTypePanel = new SampleTypePanel(typeOfSample.getValue());
			sampleTypePanel.setPanels(existingSampleTypePanelMap.get(typeOfSample.getValue()));
			sampleTypePanelsExists.add(sampleTypePanel);
			SampleTypePanel sampleTypePanelInactive = new SampleTypePanel(typeOfSample.getValue());
			sampleTypePanelInactive.setPanels(inactiveSampleTypePanelMap.get(typeOfSample.getValue()));
			sampleTypePanelsInactive.add(sampleTypePanelInactive);
		}
		try {
			PropertyUtils.setProperty(form, "existingPanelList", sampleTypePanelsExists);
			PropertyUtils.setProperty(form, "inactivePanelList", sampleTypePanelsInactive);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getExistingTestNames(List<Panel> panels, ConfigurationProperties.LOCALE locale) {
		StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

		for (Panel panel : panels) {
			builder.append(LocalizationServiceImpl.getLocalizationValueByLocal(locale, panel.getLocalization()));
			builder.append(NAME_SEPARATOR);
		}

		return builder.toString();
	}

	@RequestMapping(value = "/PanelCreate", method = RequestMethod.POST)
	public ModelAndView postPanelCreate(HttpServletRequest request, @ModelAttribute("form") @Valid PanelCreateForm form,
			BindingResult result) throws Exception {
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

//		RoleDAO roleDAO = new RoleDAOImpl();
//		RoleModuleDAOImpl roleModuleDAO = new RoleModuleDAOImpl();
//		SystemModuleDAO systemModuleDAO = new SystemModuleDAOImpl();
		String identifyingName = form.getString("panelEnglishName");
		String sampleTypeId = form.getString("sampleTypeId");
		String userId = getSysUserId(request);

		Localization localization = createLocalization(form.getString("panelFrenchName"), identifyingName, userId);

		Panel panel = createPanel(identifyingName, userId);
		SystemModule workplanModule = createSystemModule("Workplan", identifyingName, userId);
		SystemModule resultModule = createSystemModule("LogbookResults", identifyingName, userId);
		SystemModule validationModule = createSystemModule("ResultValidation", identifyingName, userId);

		Role resultsEntryRole = roleService.getRoleByName("Results entry");
		Role validationRole = roleService.getRoleByName("Validator");

		RoleModule workplanResultModule = createRoleModule(userId, workplanModule, resultsEntryRole);
		RoleModule resultResultModule = createRoleModule(userId, resultModule, resultsEntryRole);
		RoleModule validationValidationModule = createRoleModule(userId, validationModule, validationRole);

//		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			localizationService.insert(localization);
			panel.setLocalization(localization);
			panelService.insert(panel);

			TypeOfSamplePanel typeOfSamplePanel = createTypeOfSamplePanel(sampleTypeId, panel, userId);
			typeOfSamplePanelService.insert(typeOfSamplePanel);

			systemModuleService.insert(workplanModule);
			systemModuleService.insert(resultModule);
			systemModuleService.insert(validationModule);
			roleModuleService.insert(workplanResultModule);
			roleModuleService.insert(resultResultModule);
			roleModuleService.insert(validationValidationModule);

//			tx.commit();

		} catch (LIMSRuntimeException lre) {
//			tx.rollback();
			lre.printStackTrace();
		} 
//			finally {
//			HibernateUtil.closeSession();
//		}

		DisplayListService.refreshList(DisplayListService.ListType.PANELS);
		DisplayListService.refreshList(DisplayListService.ListType.PANELS_INACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private Localization createLocalization(String french, String english, String currentUserId) {
		Localization localization = new Localization();
		localization.setEnglish(english);
		localization.setFrench(french);
		localization.setDescription("panel name");
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

	private Panel createPanel(String identifyingName, String userId) {
		Panel panel = new Panel();
		panel.setDescription(identifyingName);
		panel.setPanelName(identifyingName);
		panel.setIsActive("N");
		panel.setSortOrderInt(Integer.MAX_VALUE);
		panel.setSysUserId(userId);
		return panel;
	}

	private TypeOfSamplePanel createTypeOfSamplePanel(String sampleTypeId, Panel panel, String userId) {
		TypeOfSamplePanel sampleTypePanel = new TypeOfSamplePanel();
		sampleTypePanel.setPanelId(panel.getId());
		sampleTypePanel.setTypeOfSampleId(sampleTypeId);
		sampleTypePanel.setSysUserId(userId);
		return sampleTypePanel;
	}

	private SystemModule createSystemModule(String menuItem, String identifyingName, String userId) {
		SystemModule module = new SystemModule();
		module.setSystemModuleName(menuItem + ":" + identifyingName);
		module.setDescription(menuItem + "=>panel=>" + identifyingName);
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
			return "panelCreateDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PanelCreate.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "panelCreateDefinition";
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
