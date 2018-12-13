package spring.generated.siteinformation.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.ResultConfigurationMenuForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class DeleteResultConfigurationController extends BaseController {
  @RequestMapping(
      value = "/DeleteResultConfiguration",
      method = RequestMethod.GET
  )
  public ModelAndView showDeleteResultConfiguration(HttpServletRequest request,
      @ModelAttribute("form") ResultConfigurationMenuForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new ResultConfigurationMenuForm();
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
      return new ModelAndView("/ResultConfigurationMenu.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("/ResultConfigurationMenu.do", "form", form);
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
