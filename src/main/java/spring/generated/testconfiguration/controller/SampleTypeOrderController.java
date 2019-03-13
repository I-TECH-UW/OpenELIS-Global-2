package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import spring.generated.forms.SampleTypeOrderForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeOrderController extends BaseController {
  @RequestMapping(
      value = "/SampleTypeOrder",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleTypeOrder(HttpServletRequest request,
      @ModelAttribute("form") SampleTypeOrderForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleTypeOrderForm();
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
		PropertyUtils.setProperty(form, "sampleTypeList", DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE));
	} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		e.printStackTrace();
	}


    return findForward(forward, form);
  }
  
  private class ActivateSet {
      public String id;
      public Integer sortOrder;
  }
  
  @RequestMapping(
	      value = "/SampleTypeOrder",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postSampleTypeOrder(HttpServletRequest request,
	      @ModelAttribute("form") SampleTypeOrderForm form) throws Exception {
	  
	    String forward = FWD_SUCCESS_INSERT;
	    
	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }
  
	    String changeList = form.getJsonChangeList();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(changeList);
        List<ActivateSet> orderSet = getActivateSetForActions("sampleTypes", obj, parser);
        List<TypeOfSample> typeOfSamples = new ArrayList<TypeOfSample>();

        String currentUserId = getSysUserId(request);
        TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
        for (ActivateSet sets : orderSet) {
            TypeOfSample typeOfSample = typeOfSampleDAO.getTypeOfSampleById(sets.id);
            typeOfSample.setSortOrder(sets.sortOrder);
            typeOfSample.setSysUserId(currentUserId);
            typeOfSamples.add(typeOfSample);
        }


        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
            for (TypeOfSample typeOfSample : typeOfSamples) {
            	typeOfSampleDAO.updateData(typeOfSample);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE);
        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);
        
        return findForward(forward, form);
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
      return new ModelAndView("sampleTypeOrderDefinition", "form", form);
    } else if (FWD_SUCCESS_INSERT.equals(forward)) {
        return new ModelAndView("redirect:/SampleTypeOrder.do", "form", form);
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
