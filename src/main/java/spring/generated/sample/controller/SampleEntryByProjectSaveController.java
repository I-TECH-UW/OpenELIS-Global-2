package spring.generated.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.controller.BaseSampleEntryController;

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
    Errors errors = new BaseErrors();
    
    

    
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
