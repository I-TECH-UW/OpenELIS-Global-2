package spring.generated.menu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.MainForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;

@Controller
public class HomePageController extends BaseController {
  @RequestMapping(
      value = "/HomePage",
      method = RequestMethod.GET
  )
  public ModelAndView showHomePage(HttpServletRequest request,
      @ModelAttribute("form") MainForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new MainForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected String findLocalForward(String forward) {
    if (FWD_SUCCESS.equals(forward)) {
      return "homePageDefinition";
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
