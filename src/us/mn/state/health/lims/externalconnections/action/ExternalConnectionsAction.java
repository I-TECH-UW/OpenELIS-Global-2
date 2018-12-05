package us.mn.state.health.lims.externalconnections.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.formfields.AdminFormFields;
import us.mn.state.health.lims.common.formfields.AdminFormFields.Field;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.display.URLForDisplay;

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
