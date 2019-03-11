package spring.generated.externalconnections.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.ExternalConnectionsConfigForm;
import spring.mine.common.controller.BaseMenuController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.formfields.AdminFormFields;
import us.mn.state.health.lims.common.formfields.AdminFormFields.Field;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.display.URLForDisplay;

@Controller
public class ExternalConnectionsConfigMenuController extends BaseMenuController {
	// result reporting config directly in menu as it was originally
	// @RequestMapping(value = "/ExternalConnectionsConfigMenu", method =
	// RequestMethod.GET)
	public ModelAndView showExternalConnectionsConfigMenu(HttpServletRequest request,
			@ModelAttribute("form") ExternalConnectionsConfigForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ExternalConnectionsConfigForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		return performMenuAction(form, request);

	}

	@Override
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {
		AdminFormFields adminFields = AdminFormFields.getInstance();
		List<URLForDisplay> menuList = new ArrayList<>();
		URLForDisplay url = new URLForDisplay();
		if (adminFields.useField(Field.RESULT_REPORTING_CONFIGURATION)) {
			url.setDisplayKey("resultreporting.browse.title");
			url.setUrlAddress(request.getContextPath() + "/ResultReportingConfiguration.do");
			menuList.add(url);
		}
		return menuList;
	}

	@Override
	protected String getPageTitleKey() {
		return StringUtil.getMessageForKey("externalconnect.browse.title");
	}

	@Override
	protected String getPageSubtitleKey() {
		return StringUtil.getMessageForKey("externalconnect.browse.title");
	}

	@Override
	protected String getDeactivateDisabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "externalConnectionsConfigMenuDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "/MasterListsPage.do";
		} else {
			return "PageNotFound";
		}
	}
}
