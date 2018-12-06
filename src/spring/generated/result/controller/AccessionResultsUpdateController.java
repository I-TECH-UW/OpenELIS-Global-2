package spring.generated.result.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.AccessionResultsForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class AccessionResultsUpdateController extends BaseController {
  @RequestMapping(
      value = "/AccessionResultsUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showAccessionResultsUpdate(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    AccessionResultsForm form = new AccessionResultsForm();
    form.setFormName("AccessionResultsForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/AccessionResults.do", "form", form);
    } else if ("error".equals(forward)) {
      return new ModelAndView("accessionResultDefinition", "form", form);
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
