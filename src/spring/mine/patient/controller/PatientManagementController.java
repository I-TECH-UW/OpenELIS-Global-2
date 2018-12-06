package spring.mine.patient.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;

@Controller
public class PatientManagementController extends BaseController {
  @RequestMapping(
      value = "/PatientManagement",
      method = RequestMethod.GET
  )
  public ModelAndView showPatientManagement(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    SamplePatientEntryForm form = new SamplePatientEntryForm();
    form.setFormName("samplePatientEntryForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);
	form.setPatientProperties(new PatientManagementInfo());
	form.setPatientSearch(new PatientSearch());
	form.getPatientProperties().setPatientProcessingStatus("add");

	
    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("patientManagementDefinition", "form", form);
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
