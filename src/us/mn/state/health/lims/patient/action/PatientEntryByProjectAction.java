/*
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
package us.mn.state.health.lims.patient.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.saving.Accessioner;

/**
 * Action for Côte d'Ivoire study based patient entry.
 * @author pahill
 * @since 2010-04-16
 */
public class PatientEntryByProjectAction extends BasePatientEntryByProject {

	private String todayAsText;

	protected String getPageTitleKey() {
		return "patient.project.title";
	}

	protected String getPageSubtitleKey() {
		String key = null;

		switch (requestType) {
			case INITIAL: {
				key = "banner.menu.createPatient.Initial";
				break;
			}
			case VERIFY: {
				key = "banner.menu.createPatient.Verify";
				break;
			}

			default: {
				key = "banner.menu.createPatient.Initial";
			}
		}

		return key;
	}




	@Override
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		todayAsText = DateUtil.formatDateAsText(new Date());

		String forward = "success";

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);

		// retrieve the current project, before clearing, so that we can set it on later.
		String projectFormName = Accessioner.findProjectFormName(dynaForm);
		// Initialize the form.
		dynaForm.initialize(mapping);
		updateRequestType(request);

		addAllPatientFormLists(dynaForm);

		PropertyUtils.setProperty(form, "currentDate", todayAsText);	// TODO Needed?
		PropertyUtils.setProperty(form, "receivedDateForDisplay", todayAsText);
		PropertyUtils.setProperty(form, "interviewDate", todayAsText);
		// put the projectFormName back in.
        setProjectFormName(form, projectFormName);
		return mapping.findForward(forward);
	}
}
