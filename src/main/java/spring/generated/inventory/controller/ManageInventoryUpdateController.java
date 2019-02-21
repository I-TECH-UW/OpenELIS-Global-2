package spring.generated.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.InventoryForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class ManageInventoryUpdateController extends BaseController {
  @RequestMapping(
      value = "/ManageInventoryUpdate",
      method = RequestMethod.GET
  )
  public ModelAndView showManageInventoryUpdate(HttpServletRequest request,
      @ModelAttribute("form") InventoryForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new InventoryForm();
    }
        form.setFormAction("");
    Errors errors = new BaseErrors();
    

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("/ManageInventory.do?forward=success", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("manageInventoryDefinition", "form", form);
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
