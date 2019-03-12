package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.PanelTestAssignForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.daoimpl.PanelItemDAOImpl;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestComparator;
import us.mn.state.health.lims.testconfiguration.action.PanelTests;

@Controller
public class PanelTestAssignController extends BaseController {
  @RequestMapping(
      value = "/PanelTestAssign",
      method = RequestMethod.GET
  )
  public ModelAndView showPanelTestAssign(HttpServletRequest request,
      @ModelAttribute("form") PanelTestAssignForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new PanelTestAssignForm();
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
    
	String panelId = form.getString("panelId");
	if (panelId == null) {
		panelId = "";
	}
    List<IdValuePair> panels = DisplayListService.getList(DisplayListService.ListType.PANELS);
    
    if (!GenericValidator.isBlankOrNull(panelId)) {
        PanelDAO panelDAO = new PanelDAOImpl();
        Panel panel = panelDAO.getPanelById(panelId);
    	IdValuePair panelPair = new IdValuePair(panelId, panel.getLocalizedName());

    	List<IdValuePair> tests = new ArrayList<IdValuePair>();

        List<Test> testList = getAllTestsByPanelId(panelId);
        
        PanelTests panelTests = new PanelTests(panelPair);
        HashSet<String> testIdSet = new HashSet<String>();
        
        for( Test test : testList){
            if( test.isActive()) {
                tests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
                testIdSet.add(test.getId());
            }
        }
        
        panelTests.setTests(tests, testIdSet);
        try {
			PropertyUtils.setProperty(form, "selectedPanel", panelTests);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  else {
    	try {
			PropertyUtils.setProperty(form, "selectedPanel", new PanelTests());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    try {
		PropertyUtils.setProperty(form, "panelList", panels);
	} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    return findForward(forward, form);
  }
  
  public static List<Test> getAllTestsByPanelId(String panelId){
      List<Test> testList = new ArrayList<Test>();
      PanelItemDAO panelItemDAO = new PanelItemDAOImpl();

      @SuppressWarnings("unchecked")
		List<PanelItem> testLinks = panelItemDAO.getPanelItemsForPanel(panelId);

      for (PanelItem link : testLinks) {
          testList.add(link.getTest());
      }

      Collections.sort(testList, TestComparator.NAME_COMPARATOR);
      return testList;
  }
  
  @RequestMapping(
	      value = "/PanelTestAssign",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postPanelTestAssign(HttpServletRequest request,
	      @ModelAttribute("form") PanelTestAssignForm form) throws Exception {
	  
	    String forward = FWD_SUCCESS_INSERT;
	    
	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }
	    
        String panelId = removeComma(form.getString("panelId"));
        String currentUser = getSysUserId(request);
        boolean updatepanel = false;
        
        Panel panel = new PanelDAOImpl().getPanelById(panelId);
                                
        if (!GenericValidator.isBlankOrNull(panelId)) {
        	PanelItemDAO panelItemDAO = new PanelItemDAOImpl();
        	@SuppressWarnings("unchecked")
			List<PanelItem> panelItems = panelItemDAO.getPanelItemsForPanel(panelId);
        	
        	String[] newTests = (String[]) form.get("currentTests");
            
        	Transaction tx = HibernateUtil.getSession().beginTransaction();
            try {
            	for (PanelItem oldPanelItem : panelItems) {
            		oldPanelItem.setSysUserId(currentUser);
            	}
        		panelItemDAO.deleteData(panelItems);
                
        		for (String testId : newTests) {
        			PanelItem panelItem = new PanelItem();
        			panelItem.setPanel(panel);
        			TestDAO testDAO = new TestDAOImpl();
        			panelItem.setTest(testDAO.getTestById(testId));
        			panelItem.setLastupdatedFields();
        			panelItem.setSysUserId(currentUserId);
        			new PanelItemDAOImpl().insertData(panelItem);
        		}
        		
        		if( "N".equals(panel.getIsActive())){
        			panel.setIsActive("Y");
        			panel.setSysUserId(currentUser);
        			updatepanel=true;
            	                	    
        		}
        		
        		 if(updatepanel==true){
                     new PanelDAOImpl().updateData(panel);
                 }    
        		
        		tx.commit();
            } catch (LIMSRuntimeException lre) {
                tx.rollback();
                lre.printStackTrace();
            } finally {
                HibernateUtil.closeSession();
            }
        }
        
        if(updatepanel==false){
        	panel.setIsActive("N");
        	panel.setSysUserId(currentUser);
        	new PanelDAOImpl().updateData(panel);
        }        

        DisplayListService.refreshList(DisplayListService.ListType.PANELS);
        DisplayListService.refreshList(DisplayListService.ListType.PANELS_INACTIVE);

		setSuccessFlag(request);
	    
        return findForward(forward, form);
  }

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if (FWD_SUCCESS.equals(forward)) {
      return new ModelAndView("panelAssignDefinition", "form", form);
    } else if (FWD_SUCCESS_INSERT.equals(forward)) {
	  return new ModelAndView("redirect:/PanelTestAssign.do", "form", form);
    } else {
      return new ModelAndView("PageNotFound");
    }
  }
  
  protected String removeComma(String str) {
	  if (str.charAt(str.length()-1)==','){
	      str = str.replace(str.substring(str.length()-1), "");
	      return str;
	  } else{
	      return str;
	  }  
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
