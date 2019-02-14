package spring.generated.sample.controller;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.sample.controller.BaseSampleEntryController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.SampleEntry;
import us.mn.state.health.lims.patient.saving.SampleEntryAfterPatientEntry;
import us.mn.state.health.lims.patient.saving.SampleSecondEntry;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleEntryByProjectSaveController extends BaseSampleEntryController {
  @RequestMapping(
      value = "/SampleEntryByProjectSave",
      method = RequestMethod.POST
  )
  public ModelAndView showSampleEntryByProjectSave(HttpServletRequest request,
      @ModelAttribute("form") SampleEntryByProjectForm form) throws Exception {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleEntryByProjectForm();
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
    

    
    return findLocalForward(forward, form);
  }


  protected ModelAndView findLocalForward(String forward, BaseForm form) {
	  
    if ("success".equals(forward)) {
      return new ModelAndView("forward:/SampleEntryByProject.do?forward=success", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("sampleEntryByProjectDefinition", "form", form);
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
