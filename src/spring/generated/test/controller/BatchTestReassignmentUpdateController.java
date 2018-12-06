package spring.generated.test.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.BatchTestReassignment;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class BatchTestReassignmentUpdateController extends BaseController {
  @RequestMapping(
      value = "/BatchTestReassignmentUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showBatchTestReassignmentUpdate(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    BatchTestReassignment form = new BatchTestReassignment();
    form.setFormName("BatchTestReassignment");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/BatchTestReassignment.do?forward=success", "form", form);
    } else if ("resubmit".equals(forward)) {
      return new ModelAndView("BatchTestReassignmentDefinition", "form", form);
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
