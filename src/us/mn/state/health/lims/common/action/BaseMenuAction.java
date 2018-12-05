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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 */
package us.mn.state.health.lims.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;

public abstract class BaseMenuAction extends BaseAction implements IActionConstants {

	protected static final int PREVIOUS = 1;

	protected static final int NEXT = 2;

	protected static final int NONE = -1;

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = FWD_SUCCESS;

		DynaActionForm dynaForm = (DynaActionForm) form;

		int action = -1;
		if (!StringUtil.isNullorNill(request.getParameter("paging"))) {
			action = Integer.parseInt(request.getParameter("paging"));
		}
		List menuList = null;

		try {
			switch (action) {
			case PREVIOUS:
				menuList = doPreviousPage(mapping, form, request, response);
				break;
			case NEXT:
				menuList = doNextPage(mapping, form, request, response);
				break;
			default:
				menuList = doNone(mapping, form, request, response);
			}
		} catch (Exception e) {
			LogEvent.logError("BaseMenuAction", "performAction()", e.toString());
			forward = FWD_FAIL;
		}

		dynaForm.initialize(mapping);

		PropertyUtils.setProperty(dynaForm, "menuList", menuList);

		request.setAttribute(DEACTIVATE_DISABLED, getDeactivateDisabled());
		request.setAttribute(ADD_DISABLED, getAddDisabled());
		request.setAttribute(EDIT_DISABLED, getEditDisabled());

		String[] selectedIDs = new String[5];

		PropertyUtils.setProperty(dynaForm, "selectedIDs", selectedIDs);

		return mapping.findForward(forward);
	}

	protected List doNextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		LogEvent.logDebug("BaseMenuAction", "performAction()", "current start " + startingRecNo);
		int nextStartingRecNo = startingRecNo + getPageSize();

		LogEvent.logDebug("BaseMenuAction", "performAction()", "next start " + nextStartingRecNo);
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List nextPageList = createMenuList(mapping, form, request, response);

		request.setAttribute(PREVIOUS_DISABLED, "false");

		if (nextPageList.size() > getPageSize()) {
			request.setAttribute(NEXT_DISABLED, "false");
			// chop off last record (this was only to indicate that there are
			// more records
			nextPageList = nextPageList.subList(0, getPageSize());
		} else {
			request.setAttribute(NEXT_DISABLED, "true");
		}

		return nextPageList;
	}

	protected List doPreviousPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		int nextStartingRecNo = startingRecNo - getPageSize();
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List previousPageList = createMenuList(mapping, form, request, response);

		request.setAttribute(NEXT_DISABLED, "false");

		if (previousPageList.size() > getPageSize()) {
			request.setAttribute(PREVIOUS_DISABLED, "false");
			// chop off last record (this was only to indicate that there are
			// more records
			previousPageList = previousPageList.subList(0, getPageSize());
		} else {
			request.setAttribute(PREVIOUS_DISABLED, "true");
		}

		if (nextStartingRecNo <= 1) {
			request.setAttribute(PREVIOUS_DISABLED, "true");
		}

		return previousPageList;
	}

	protected List doNone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		int nextStartingRecNo = startingRecNo;
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List samePageList = createMenuList(mapping, form, request, response);

		// this is first page: don't enable previous button

		if (nextStartingRecNo <= 1) {
			request.setAttribute(PREVIOUS_DISABLED, "true");
		}

		if (samePageList.size() > getPageSize()) {
			request.setAttribute(NEXT_DISABLED, "false");
			// chop off last record (this was only to indicate that there are
			// more records
			samePageList = samePageList.subList(0, getPageSize());
		} else {
			request.setAttribute(NEXT_DISABLED, "true");
		}
		return samePageList;
	}

	protected int getCurrentStartingRecNo(HttpServletRequest request) {

		String stringStartingRecNo = "1";

		if (!StringUtil.isNullorNill((String) request.getAttribute("startingRecNo"))) {
			stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		} else if (!StringUtil.isNullorNill(request.getParameter("startingRecNo"))) {
			stringStartingRecNo = request.getParameter("startingRecNo");
		}

		int startingRecNo = Integer.parseInt(stringStartingRecNo);
		return startingRecNo;
	}

	protected abstract List createMenuList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	protected abstract String getDeactivateDisabled();

	
	protected String getAddDisabled() {
		return "false";
	}

	protected String getEditDisabled() {
		return "false";
	}

	protected void setDisplayPageBounds(HttpServletRequest request, int listSize, int startingRecNo, BaseDAO DAO, Class valueClass)
			throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(DAO.getTotalCount(valueClass.getName(), valueClass)));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

		int numOfRecs = 0;
		if (listSize != 0) {
			numOfRecs = Math.min(listSize, getPageSize());

			numOfRecs--;
		}

		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
	}

	protected void setDisplayPageBounds(HttpServletRequest request, int listSize, int startingRecNo, int totalRecords)
			throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(totalRecords));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

		int numOfRecs = 0;
		if (listSize != 0) {
			numOfRecs = Math.min(listSize, getPageSize());

			numOfRecs--;
		}

		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
	}
}