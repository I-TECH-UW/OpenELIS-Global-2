package spring.mine.common.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.management.form.TestSectionManagementForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class TestSectionManagementController extends BaseController {
  @RequestMapping(
      value = "/TestSectionManagement",
      method = { RequestMethod.GET, RequestMethod.POST }
  )
  public ModelAndView showTestSectionManagement(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    TestSectionManagementForm form = new TestSectionManagementForm();
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "testSectionManagementDefinition";
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
