package spring.generated.typeofsample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TypeOfSamplePanelMenuForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class DeleteTypeOfSamplePanelController extends BaseController {
  @RequestMapping(
      value = "/DeleteTypeOfSamplePanel",
      method = RequestMethod.GET
  )
  public ModelAndView showDeleteTypeOfSamplePanel(HttpServletRequest request,
      @ModelAttribute("form") TypeOfSamplePanelMenuForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new TypeOfSamplePanelMenuForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/TypeOfSamplePanelMenu.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("/TypeOfSamplePanelMenu.do", "form", form);
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
