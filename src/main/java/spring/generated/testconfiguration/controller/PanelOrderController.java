package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.PanelOrderForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panel.valueholder.PanelSortOrderComparator;
import us.mn.state.health.lims.testconfiguration.action.PanelTestConfigurationUtil;
import us.mn.state.health.lims.testconfiguration.action.SampleTypePanel;

@Controller
public class PanelOrderController extends BaseController {
  @RequestMapping(
      value = "/PanelOrder",
      method = RequestMethod.GET
  )
  public ModelAndView showPanelOrder(HttpServletRequest request,
      @ModelAttribute("form") PanelOrderForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new PanelOrderForm();
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
		PropertyUtils.setProperty(form, "panelList", DisplayListService.getList(DisplayListService.ListType.PANELS));
	} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    HashMap<String, List<Panel>> existingSampleTypePanelMap = PanelTestConfigurationUtil.createTypeOfSamplePanelMap(true);
    HashMap<String, List<Panel>> inactiveSampleTypePanelMap = PanelTestConfigurationUtil.createTypeOfSamplePanelMap(false);
    try {
		PropertyUtils.setProperty(form, "existingSampleTypeList", DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
	} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //List<Panel> panels = new PanelDAOImpl().getAllPanels();
    
    List<SampleTypePanel> sampleTypePanelsExists = new ArrayList<SampleTypePanel>();
    List<SampleTypePanel> sampleTypePanelsInactive = new ArrayList<SampleTypePanel>();

    for (IdValuePair typeOfSample : DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE)) {
    	SampleTypePanel sampleTypePanel = new SampleTypePanel(typeOfSample.getValue());
    	sampleTypePanel.setPanels(existingSampleTypePanelMap.get(typeOfSample.getValue()));
    	if (sampleTypePanel.getPanels() != null && sampleTypePanel.getPanels().size() > 0) {
    		Collections.sort(sampleTypePanel.getPanels(), PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
    	}

    	sampleTypePanelsExists.add(sampleTypePanel);
    	SampleTypePanel sampleTypePanelInactive = new SampleTypePanel(typeOfSample.getValue());
    	sampleTypePanelInactive.setPanels(inactiveSampleTypePanelMap.get(typeOfSample.getValue()));
    	if (sampleTypePanelInactive.getPanels() != null && sampleTypePanelInactive.getPanels().size() > 0) {
    		Collections.sort(sampleTypePanelInactive.getPanels(), PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
    	}
    	
    	sampleTypePanelsInactive.add(sampleTypePanelInactive);
    }
    
    try {
    	PropertyUtils.setProperty(form, "existingPanelList", sampleTypePanelsExists);
		PropertyUtils.setProperty(form, "inactivePanelList", sampleTypePanelsInactive);
	} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    return findForward(forward, form);
  }
  
  @RequestMapping(
	      value = "/PanelOrder",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postPanelOrder(HttpServletRequest request,
	      @ModelAttribute("form") PanelOrderForm form) throws Exception {
	  
	    String forward = FWD_SUCCESS_INSERT;
	    
	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }
	    
        String changeList = form.getString("jsonChangeList");

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(changeList);
        List<ActivateSet> orderSet = getActivateSetForActions("panels", obj, parser);
        List<Panel> panels = new ArrayList<Panel>();

        String currentUserId = getSysUserId(request);
        PanelDAO panelDAO = new PanelDAOImpl();
        for (ActivateSet sets : orderSet) {
            Panel panel = panelDAO.getPanelById(sets.id);
            panel.setSortOrderInt(sets.sortOrder); 
            panel.setSysUserId(currentUserId);
            panels.add(panel);
        }


        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
            for (Panel panel : panels) {
            	panelDAO.updateData(panel);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.PANELS);
        DisplayListService.refreshList(DisplayListService.ListType.PANELS_INACTIVE);
        DisplayListService.refreshList(DisplayListService.ListType.PANELS_ACTIVE);
  
	    return findForward(forward, form);
  }
  
  private class ActivateSet {
      public String id;
      public Integer sortOrder;
  }
  
  private List<ActivateSet> getActivateSetForActions(String key, JSONObject root, JSONParser parser) {
      List<ActivateSet> list = new ArrayList<ActivateSet>();

      String action = (String) root.get(key);

      try {
          JSONArray actionArray = (JSONArray) parser.parse(action);

          for (int i = 0; i < actionArray.size(); i++) {
              ActivateSet set = new ActivateSet();
              set.id = String.valueOf(((JSONObject) actionArray.get(i)).get("id"));
              Long longSort = (Long) ((JSONObject) actionArray.get(i)).get("sortOrder");
              set.sortOrder = longSort.intValue();
              list.add(set);
          }
      } catch (ParseException e) {
          e.printStackTrace();
      }

      return list;
  }

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("panelOrderDefinition", "form", form);
    } else if (FWD_SUCCESS_INSERT.equals(forward)) {
	      return new ModelAndView("redirect:/PanelOrder.do", "form", form);
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
