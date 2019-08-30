package org.openelisglobal.datasubmission.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.validator.ActionError;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.datasubmission.DataSubmitter;
import org.openelisglobal.datasubmission.dao.DataIndicatorDAO;
import org.openelisglobal.datasubmission.daoimpl.DataIndicatorDAOImpl;
import org.openelisglobal.datasubmission.valueholder.DataIndicator;
import org.openelisglobal.siteinformation.dao.SiteInformationDAO;
import org.openelisglobal.siteinformation.daoimpl.SiteInformationDAOImpl;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;

public class DataSubmissionSaveAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success";
		DynaActionForm dynaForm = (DynaActionForm) form;
		int month = GenericValidator.isBlankOrNull(request.getParameter("month")) ? 
				DateUtil.getCurrentMonth() : Integer.parseInt(request.getParameter("month"));
		int year = GenericValidator.isBlankOrNull(request.getParameter("year")) ? 
				DateUtil.getCurrentYear() : Integer.parseInt(request.getParameter("year"));
		@SuppressWarnings("unchecked")
		List<DataIndicator> indicators = (List<DataIndicator>) dynaForm.get("indicators");
		boolean submit = "true".equalsIgnoreCase(request.getParameter("submit"));
		SiteInformation dataSubUrl = (SiteInformation) dynaForm.get("dataSubUrl");
		dataSubUrl.setSysUserId(currentUserId);
		SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();
		siteInfoDAO.updateData(dataSubUrl);
		DataIndicatorDAO indicatorDAO = new DataIndicatorDAOImpl();
		boolean allSuccess = true;
		ActionMessages errors = new ActionMessages();
		for (DataIndicator indicator : indicators) {
			if (submit && indicator.isSendIndicator()) {
				boolean success;
				success = DataSubmitter.sendDataIndicator(indicator);
				indicator.setStatus(DataIndicator.SENT);
				if (success) {
					indicator.setStatus(DataIndicator.RECEIVED);
				} else {
					allSuccess = false;
					indicator.setStatus(DataIndicator.FAILED);
					ActionError error = new ActionError("errors.IndicatorCommunicationException", StringUtil.getMessageForKey(indicator.getTypeOfIndicator().getNameKey()), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				}
			}
			
			DataIndicator databaseIndicator = indicatorDAO.getIndicatorByTypeYearMonth(indicator.getTypeOfIndicator(), year, month);
			if (databaseIndicator == null) {
				indicatorDAO.insertData(indicator);
			} else {
				indicator.setId(databaseIndicator.getId());
				indicatorDAO.updateData(indicator);
			}
		}

		if (!allSuccess) {
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
		}
		request.setAttribute(IActionConstants.FWD_SUCCESS, allSuccess);
		
		return mapping.findForward(forward);
	}

	@Override
	protected String getPageTitleKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
