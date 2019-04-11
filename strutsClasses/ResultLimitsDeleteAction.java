package us.mn.state.health.lims.resultlimits.action;

import java.util.ArrayList;
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
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;

/**
 * Project : LIS<br>
 * File name : PatientTypeDeleteAction.java<br>
 * Description : 
 * @author TienDH
 * @date Aug 20, 2007
 */
public class ResultLimitsDeleteAction extends BaseAction {
	static private String FWD_CLOSE = "close";

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "success";

		DynaActionForm dynaForm = (DynaActionForm) form;

		String[] selectedIDs = (String[]) dynaForm.get("selectedIDs");

		String sysUserId =   getSysUserId(request);

		List<ResultLimit> resultLimits = new ArrayList<ResultLimit>();		
		for (int i = 0; i < selectedIDs.length; i++) {
			ResultLimit resultLimit = new ResultLimit();
			resultLimit.setId(selectedIDs[i]);
			resultLimit.setSysUserId(sysUserId);
			resultLimits.add(resultLimit);
		}

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();	
		ActionMessages errors = null;
		try {
			
			ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();
			resultLimitDAO.deleteData(resultLimits);
			
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			
			errors = new ActionMessages();
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null,	null);
			} else {
				error = new ActionError("errors.DeleteException", null, null);
			}
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			forward = FWD_FAIL;
			
		}  finally {
            HibernateUtil.closeSession();
        }							
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);
		
		if (TRUE.equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}
		
		request.setAttribute("menuDefinition", "ResultLimitMenuDefinition");

		return mapping.findForward(forward);
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}