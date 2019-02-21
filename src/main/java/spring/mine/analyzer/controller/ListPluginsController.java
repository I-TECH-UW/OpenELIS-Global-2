package spring.mine.analyzer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.analyzer.form.ListPluginForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.plugin.PluginLoader;

@Controller
public class ListPluginsController extends BaseController {
	@RequestMapping(value = "/ListPlugins", method = RequestMethod.GET)
	public ModelAndView showListPlugins(HttpServletRequest request) {
		String forward = FWD_SUCCESS;
		ListPluginForm form = new ListPluginForm();
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		List<String> pluginNames = PluginLoader.getCurrentPlugins();

		if (pluginNames.size() == 0) {
			pluginNames.add(StringUtil.getContextualMessageForKey("plugin.no.plugins"));
		}
		form.setPluginList(pluginNames);

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("ListPluginsPageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "plugin.installed.plugins";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "plugin.installed.plugins";
	}
}
