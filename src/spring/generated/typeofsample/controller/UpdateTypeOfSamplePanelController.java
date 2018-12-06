package spring.generated.typeofsample.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.TypeOfSamplePanelForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class UpdateTypeOfSamplePanelController extends BaseController {
  @RequestMapping(
      value = "/UpdateTypeOfSamplePanel",
      method = RequestMethod.GET
  )
  public ModelAndView showUpdateTypeOfSamplePanel(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    TypeOfSamplePanelForm form = new TypeOfSamplePanelForm();
    form.setFormName("typeOfSamplePanelForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("insertSuccess".equals(forward)) {
      return new ModelAndView("/TypeOfSamplePanel.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("typeOfSamplePanelDefinition", "form", form);
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
