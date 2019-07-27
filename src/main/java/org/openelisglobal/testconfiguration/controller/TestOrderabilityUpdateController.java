package org.openelisglobal.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.testconfiguration.form.TestOrderabilityForm;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;

@Controller
public class TestOrderabilityUpdateController extends BaseController {
  @RequestMapping(
      value = "/TestOrderabilityUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showTestOrderabilityUpdate(HttpServletRequest request,
      @ModelAttribute("form") TestOrderabilityForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new TestOrderabilityForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "/TestOrderability.do";
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
