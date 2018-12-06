package spring.generated.sample.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class SampleEntryByProjectController extends BaseController {
  @RequestMapping(
      value = "/SampleEntryByProject",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleEntryByProject(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    SampleEntryByProjectForm form = new SampleEntryByProjectForm();
    form.setFormName("sampleEntryByProjectForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("sampleEntryByProjectDefinition", "form", form);
    } else if ("eid_entry".equals(forward)) {
      return new ModelAndView("sampleEntryEIDDefinition", "form", form);
    } else if ("vl_entry".equals(forward)) {
      return new ModelAndView("sampleEntryVLDefinition", "form", form);
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
