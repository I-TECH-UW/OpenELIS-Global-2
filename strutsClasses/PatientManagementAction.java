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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.patient.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;

public class PatientManagementAction extends BaseAction {

	protected String getPageTitleKey() {
		return "patient.management.title";
	}

	protected String getPageSubtitleKey() {
		return "patient.management.title";
	}

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "success";

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);

		BaseActionForm dynaForm = (BaseActionForm) form;

		dynaForm.initialize(mapping);
		PropertyUtils.setProperty(dynaForm, "patientProperties", new PatientManagementInfo());

		return mapping.findForward(forward);
	}
}
