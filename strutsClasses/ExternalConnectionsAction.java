package org.openelisglobal.externalconnections.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseMenuAction;
import org.openelisglobal.common.formfields.AdminFormFields;
import org.openelisglobal.common.formfields.AdminFormFields.Field;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.display.URLForDisplay;

public class ExternalConnectionsAction extends BaseMenuAction {

	@Override
	protected String getPageTitleKey() {
		return StringUtil.getMessageForKey("externalconnect.browse.title");
	}

	@Override
	protected String getPageSubtitleKey() {
		return StringUtil.getMessageForKey("externalconnect.browse.title");
	}

	@Override
	protected List createMenuList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdminFormFields adminFields = AdminFormFields.getInstance();
		List<URLForDisplay> menuList = new ArrayList<URLForDisplay>();
		URLForDisplay url = new URLForDisplay();
		if (adminFields.useField(Field.RESULT_REPORTING_CONFIGURATION)) {
			url.setDisplayKey("resultreporting.browse.title");
			url.setUrlAddress(request.getContextPath() + "/ResultReportingConfiguration.do" );
			menuList.add(url);
		}
		return menuList;
	}

	@Override
	protected String getDeactivateDisabled() {
		// TODO Auto-generated method stub
		return null;
	}

}
