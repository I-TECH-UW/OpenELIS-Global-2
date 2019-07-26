package org.openelisglobal.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import org.openelisglobal.common.form.MenuForm;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;

public abstract class BaseMenuController extends BaseController {

	protected static final int PREVIOUS = 1;

	protected static final int NEXT = 2;

	protected static final int NONE = -1;

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String performMenuAction(MenuForm form, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String forward = FWD_SUCCESS;

		int action = -1;
		if (!StringUtil.isNullorNill(request.getParameter("paging"))) {
			action = Integer.parseInt(request.getParameter("paging"));
		}
		List menuList = null;

		try {
			switch (action) {
			case PREVIOUS:
				menuList = doPreviousPage(form, request);
				break;
			case NEXT:
				menuList = doNextPage(form, request);
				break;
			default:
				menuList = doNone(form, request);
			}
		} catch (Exception e) {
			LogEvent.logError("BaseMenuController", "performMenuAction()", e.toString());
			forward = FWD_FAIL;
		}

		PropertyUtils.setProperty(form, "menuList", menuList);

		request.setAttribute(DEACTIVATE_DISABLED, getDeactivateDisabled());
		request.setAttribute(ADD_DISABLED, getAddDisabled());
		request.setAttribute(EDIT_DISABLED, getEditDisabled());

		List<String> selectedIDs = new ArrayList<>();

		PropertyUtils.setProperty(form, "selectedIDs", selectedIDs);

		return forward;
	}

	protected List doNextPage(MenuForm form, HttpServletRequest request) throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		LogEvent.logDebug("BaseMenuAction", "performAction()", "current start " + startingRecNo);
		int nextStartingRecNo = startingRecNo + getPageSize();

		LogEvent.logDebug("BaseMenuAction", "performAction()", "next start " + nextStartingRecNo);
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List nextPageList = createMenuList(form, request);

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

	protected List doPreviousPage(MenuForm form, HttpServletRequest request) throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		int nextStartingRecNo = startingRecNo - getPageSize();
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List previousPageList = createMenuList(form, request);

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

	protected List doNone(MenuForm form, HttpServletRequest request) throws Exception {

		int startingRecNo = getCurrentStartingRecNo(request);

		int nextStartingRecNo = startingRecNo;
		String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
		request.setAttribute("startingRecNo", stringNextStartingRecNo);

		List samePageList = createMenuList(form, request);

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

	protected abstract List createMenuList(MenuForm form, HttpServletRequest request) throws Exception;

	protected abstract String getDeactivateDisabled();

	protected String getAddDisabled() {
		return "false";
	}

	protected String getEditDisabled() {
		return "false";
	}

	protected void setDisplayPageBounds(HttpServletRequest request, int listSize, int startingRecNo,
			BaseObjectService baseObjectService) throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(baseObjectService.getCount()));
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
