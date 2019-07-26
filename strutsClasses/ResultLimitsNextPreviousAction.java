/**
 * Project : LIS<br>
 * File name : PatientTypeNextPreviousAction.java<br>
 * Description : 
 * @author TienDH
 * @date Aug 20, 2007
 */
package org.openelisglobal.resultlimits.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.resultlimits.dao.ResultLimitDAO;
import org.openelisglobal.resultlimits.daoimpl.ResultLimitDAOImpl;
import org.openelisglobal.resultlimits.valueholder.ResultLimit;

public class ResultLimitsNextPreviousAction extends BaseAction {

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, TRUE);
		request.setAttribute(PREVIOUS_DISABLED, FALSE);
		request.setAttribute(NEXT_DISABLED, FALSE);
		
		String id = request.getParameter(ID);
		
		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");

		if( id != null && !id.equals("0")){
			ResultLimitsUpdateAction updateAction = new ResultLimitsUpdateAction();
			String updateResponse = updateAction.validateAndUpdateResultLimits(mapping, request, (BaseActionForm)form, false);
			
			if( updateResponse == FWD_FAIL){
				return getForward(mapping.findForward(FWD_FAIL), id, start);
			}
		}
		
		ResultLimit resultLimit = new ResultLimit();
		resultLimit.setId(id);
		try {

			ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();

			resultLimitDAO.getData(resultLimit);

			if (FWD_NEXT.equals(direction)) {

				List resultLimits = resultLimitDAO.getNextResultLimitRecord(resultLimit.getId());
				
				if (resultLimits != null && resultLimits.size() > 0) {
					resultLimit = (ResultLimit) resultLimits.get(0);
					resultLimitDAO.getData(resultLimit);
					if (resultLimits.size() < 2) {
						// disable next button
						request.setAttribute(NEXT_DISABLED, TRUE);
					}
					id = resultLimit.getId();
				} else {
					// just disable next button
					request.setAttribute(NEXT_DISABLED, TRUE);
				}
			}

			if (FWD_PREVIOUS.equals(direction)) {

				List resultLimits = resultLimitDAO.getPreviousResultLimitRecord(resultLimit.getId());
				
				if (resultLimits != null && resultLimits.size() > 0) {
					resultLimit = (ResultLimit) resultLimits.get(0);
					resultLimitDAO.getData(resultLimit);
					if (resultLimits.size() < 2) {
						// disable previous button
						request.setAttribute(PREVIOUS_DISABLED, TRUE);
					}
					id = resultLimit.getId().toString();
				} else {
					// just disable next button
					request.setAttribute(PREVIOUS_DISABLED, TRUE);
				}
			}

		} catch (LIMSRuntimeException lre) {
			request.setAttribute(ALLOW_EDITS_KEY, FALSE);
			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL;
		}
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);

		if (resultLimit.getId() != null && !resultLimit.getId().equals("0")) {
			request.setAttribute(ID, resultLimit.getId());

		}

		return getForward(mapping.findForward(forward), id, start);

	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}

}