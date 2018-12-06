package spring.generated.typeofsample.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.TypeOfSampleTestMenuForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class DeleteTypeOfSampleTestController extends BaseController {
  @RequestMapping(
      value = "/DeleteTypeOfSampleTest",
      method = RequestMethod.GET
  )
  public ModelAndView showDeleteTypeOfSampleTest(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    TypeOfSampleTestMenuForm form = new TypeOfSampleTestMenuForm();
    form.setFormName("typeOfSampleTestMenuForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/TypeOfSampleTestMenu.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("/TypeOfSampleTestMenu.do", "form", form);
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
