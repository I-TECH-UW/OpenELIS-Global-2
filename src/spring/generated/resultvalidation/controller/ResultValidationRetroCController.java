package spring.generated.resultvalidation.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.ResultValidationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class ResultValidationRetroCController extends BaseController {
  @RequestMapping(
      value = "/ResultValidationRetroC",
      method = RequestMethod.GET
  )
  public ModelAndView showResultValidationRetroC(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    ResultValidationForm form = new ResultValidationForm();
    form.setFormName("ResultValidationForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("resultValidationDefinition", "form", form);
    } else if ("elisaSuccess".equals(forward)) {
      return new ModelAndView("elisaAlgorithmResultValidationDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("homePageDefinition", "form", form);
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
