package spring.mine.common.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.management.form.TestManagementConfigForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class TestManagementConfigMenuController extends BaseController {
  @RequestMapping(
      value = "/TestManagementConfigMenu",
      method = { RequestMethod.GET, RequestMethod.POST }
  )
  public ModelAndView showTestManagementConfigMenu(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    TestManagementConfigForm form = new TestManagementConfigForm();
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if (FWD_SUCCESS.equals(forward)) {
      return new ModelAndView("testManagementConfigDefinition", "form", form);
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
