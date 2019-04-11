package us.mn.state.health.lims.datasubmission.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.datasubmission.DataSubmitter;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.daoimpl.DataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

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
