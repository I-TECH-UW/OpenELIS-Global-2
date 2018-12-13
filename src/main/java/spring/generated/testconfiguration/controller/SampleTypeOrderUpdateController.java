package spring.generated.testconfiguration.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.SampleTypeOrderForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class SampleTypeOrderUpdateController extends BaseController {
  @RequestMapping(
      value = "/SampleTypeOrderUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleTypeOrderUpdate(HttpServletRequest request,
      @ModelAttribute("form") SampleTypeOrderForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleTypeOrderForm();
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

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/SampleTypeOrder.do", "form", form);
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
