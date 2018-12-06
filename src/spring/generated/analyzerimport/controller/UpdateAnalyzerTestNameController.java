package spring.generated.analyzerimport.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.AnalyzerTestNameForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class UpdateAnalyzerTestNameController extends BaseController {
  @RequestMapping(
      value = "/UpdateAnalyzerTestName",
      method = RequestMethod.GET
  )
  public ModelAndView showUpdateAnalyzerTestName(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    AnalyzerTestNameForm form = new AnalyzerTestNameForm();
    form.setFormName("analyzerTestNameForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("insertSuccess".equals(forward)) {
      return new ModelAndView("/AnalyzerTestName.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("analyzerTestNameDefinition", "form", form);
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
