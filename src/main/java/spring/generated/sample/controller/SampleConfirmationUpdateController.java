package spring.generated.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.SampleConfirmationEntryForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;

@Controller
public class SampleConfirmationUpdateController extends BaseController {
  @RequestMapping(
      value = "/SampleConfirmationUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleConfirmationUpdate(HttpServletRequest request,
      @ModelAttribute("form") SampleConfirmationEntryForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleConfirmationEntryForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "/SampleConfirmationEntry.do?forward=success";
    } else if (FWD_FAIL.equals(forward)) {
      return "/SampleConfirmationEntry.do?forward=fail";
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
