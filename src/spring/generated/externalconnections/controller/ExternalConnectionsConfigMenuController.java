package spring.generated.externalconnections.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.ExternalConnectionsConfigForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class ExternalConnectionsConfigMenuController extends BaseController {
  @RequestMapping(
      value = "/ExternalConnectionsConfigMenu",
      method = RequestMethod.GET
  )
  public ModelAndView showExternalConnectionsConfigMenu(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    ExternalConnectionsConfigForm form = new ExternalConnectionsConfigForm();
    form.setFormName("ExternalConnectionsConfigForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("externalConnectionsConfigMenuDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("/MasterListsPage.do", "form", form);
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
