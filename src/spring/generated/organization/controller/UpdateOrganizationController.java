package spring.generated.organization.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.OrganizationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class UpdateOrganizationController extends BaseController {
  @RequestMapping(
      value = "/UpdateOrganization",
      method = RequestMethod.GET
  )
  public ModelAndView showUpdateOrganization(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    OrganizationForm form = new OrganizationForm();
    form.setFormName("organizationForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/OrganizationMenu.do", "form", form);
    } else if ("insertSuccess".equals(forward)) {
      return new ModelAndView("/Organization.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("organizationDefinition", "form", form);
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
