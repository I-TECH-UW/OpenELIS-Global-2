/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) I-Tech, University of Washington, Seattle WA.  All Rights Reserved.
*
*/

package us.mn.state.health.lims.analyzer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.plugin.PluginLoader;

public class ListPluginAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm)form;
		dynaForm.initialize(mapping);
		
		List<String> pluginNames = PluginLoader.getCurrentPlugins();
		
		if (pluginNames.size() == 0) {
			pluginNames.add(StringUtil.getContextualMessageForKey( "plugin.no.plugins" ));
		}
		PropertyUtils.setProperty(dynaForm, "pluginList", pluginNames);

		return mapping.findForward(IActionConstants.FWD_SUCCESS);
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
