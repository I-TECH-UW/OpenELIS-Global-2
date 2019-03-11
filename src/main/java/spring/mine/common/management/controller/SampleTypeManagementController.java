package spring.mine.common.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.management.form.SampleTypeManagementForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class SampleTypeManagementController extends BaseController {
  @RequestMapping(
      value = "/SampleTypeManagement",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleTypeManagement(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    SampleTypeManagementForm form = new SampleTypeManagementForm();
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "sampleTypeManagementDefinition";
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
