package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.TestSectionCreateForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.TestSectionService;
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
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class TestSectionCreateController extends BaseController {
  public static final String NAME_SEPARATOR = "$";
  @RequestMapping(
      value = "/TestSectionCreate",
      method = RequestMethod.GET
  )
  
  public ModelAndView showTestSectionCreate(HttpServletRequest request,
      @ModelAttribute("form") TestSectionCreateForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new TestSectionCreateForm();
    }
        form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    if (form.getErrors() != null) {
    	errors = (BaseErrors) form.getErrors();
    }
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }
    
    try {
		PropertyUtils.setProperty(form, "existingTestUnitList", DisplayListService.getList(DisplayListService.ListType.TEST_SECTION));
		PropertyUtils.setProperty(form, "inactiveTestUnitList", DisplayListService.getList(DisplayListService.ListType.TEST_SECTION_INACTIVE));
	    List<TestSection> testSections = TestSectionService.getAllTestSections();
	    PropertyUtils.setProperty(form, "existingEnglishNames", getExistingTestNames(testSections, ConfigurationProperties.LOCALE.ENGLISH));
	    PropertyUtils.setProperty(form, "existingFrenchNames", getExistingTestNames(testSections, ConfigurationProperties.LOCALE.FRENCH));
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	}
    
    return findForward(forward, form);
  }
  
  private String getExistingTestNames(List<TestSection> testSections, ConfigurationProperties.LOCALE locale) {
      StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

      for( TestSection testSection : testSections){
          builder.append(LocalizationService.getLocalizationValueByLocal(locale, testSection.getLocalization()));
          builder.append(NAME_SEPARATOR);
      }

      return builder.toString();
  }
  
  @RequestMapping(
	      value = "/TestSectionCreate",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postTestSectionCreate(HttpServletRequest request,
	      @ModelAttribute("form") TestSectionCreateForm form) throws Exception {
	  
	    String forward = FWD_SUCCESS_INSERT;
	    
	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }
	    
        RoleDAO roleDAO = new RoleDAOImpl();
        RoleModuleDAOImpl roleModuleDAO = new RoleModuleDAOImpl();
        SystemModuleDAO systemModuleDAO = new SystemModuleDAOImpl();
        
        
        String identifyingName = form.getString("testUnitEnglishName");
        String userId = getSysUserId(request);

        Localization localization = createLocalization(form.getString("testUnitFrenchName"), identifyingName, userId);

        TestSection testSection = createTestSection(identifyingName, userId);

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
            testSection.setLocalization(localization);
            new TestSectionDAOImpl().insertData(testSection);
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

        DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION);
        DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION_INACTIVE);

	    return findForward(forward, form);
  }
  
  private Localization createLocalization( String french, String english, String currentUserId) {
      Localization localization = new Localization();
      localization.setEnglish(english);
      localization.setFrench(french);
      localization.setDescription("test unit name");
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

  private TestSection createTestSection(String identifyingName, String userId) {
      TestSection testSection = new TestSection();
      testSection.setDescription(identifyingName);
      testSection.setTestSectionName(identifyingName);
      testSection.setIsActive("N");
      String identifyingNameKey=identifyingName.replaceAll(" ","_");
      testSection.setNameKey("testSection."+identifyingNameKey );
      
            
      testSection.setSortOrderInt(Integer.MAX_VALUE);
      testSection.setSysUserId(userId);
      return testSection;
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

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
	  if (FWD_SUCCESS.equals(forward)) {
	      return new ModelAndView("testSectionCreateDefinition", "form", form);
	    } else if (FWD_SUCCESS_INSERT.equals(forward)) {
	      return new ModelAndView("redirect:/TestSectionCreate.do", "form", form);
	    } else {
	    	return new ModelAndView("PageNotFound");
	    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
