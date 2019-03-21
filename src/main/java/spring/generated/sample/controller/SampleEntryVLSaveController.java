package spring.generated.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;

@Controller
public class SampleEntryVLSaveController extends BaseController {
  @RequestMapping(
      value = "/SampleEntryVLSave",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleEntryVLSave(HttpServletRequest request,
      @ModelAttribute("form") SampleEntryByProjectForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleEntryByProjectForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "/SampleEntryVL.do?forward=success";
    } else if (FWD_FAIL.equals(forward)) {
      return "sampleEntryVLDefinition";
    } else {
      return "PageNotFound";
    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
