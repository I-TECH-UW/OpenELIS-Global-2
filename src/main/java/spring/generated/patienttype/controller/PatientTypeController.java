package spring.generated.patienttype.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.PatientTypeForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class PatientTypeController extends BaseController {
  @RequestMapping(
      value = "/PatientType",
      method = RequestMethod.GET
  )
  public ModelAndView showPatientType(HttpServletRequest request,
      @ModelAttribute("form") PatientTypeForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new PatientTypeForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("patientTypeDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("haitiMasterListsPageDefinition", "form", form);
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
