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
package org.openelisglobal.patient.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.patient.saving.Accessioner;
import org.openelisglobal.patient.saving.PatientEditUpdate;
import org.openelisglobal.patient.saving.PatientEntry;
import org.openelisglobal.patient.saving.PatientEntryAfterAnalyzer;
import org.openelisglobal.patient.saving.PatientEntryAfterSampleEntry;
import org.openelisglobal.patient.saving.PatientSecondEntry;

/***
 * Action controller for C�te d'Ivoire 
 * @author pahill
 * @since 2010-04-12
 */
public class PatientEntryByProjectUpdateAction extends BasePatientEntryByProject {
	private String sysUserId;

    protected String getPageTitleKey() {
		return "patient.project.title";
	}

	protected String getPageSubtitleKey() {
		return "patient.project.title";
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
        sysUserId = String.valueOf(usd.getSystemUserId());
        String forward = null;
        Accessioner accessioner;
        addAllPatientFormLists((BaseActionForm)form);


		// commented out to allow maven compilation - CSL
        /*accessioner = new PatientEditUpdate((DynaBean) form, sysUserId, request);
        if ( accessioner.canAccession()) {
            forward = handleSave(request, accessioner);
            return mapping.findForward(forward);
        }
        
        accessioner = new PatientSecondEntry((DynaBean) form, sysUserId, request);
        if ( accessioner.canAccession()) {
            forward = handleSave(request, accessioner);
            return mapping.findForward(forward);
        }
        
        accessioner = new PatientEntry((DynaBean) form, sysUserId, request);
        if (accessioner.canAccession()) {
            forward = handleSave(request, accessioner);
            if (forward != null) {
                return mapping.findForward(forward);
            }
        }
        accessioner = new PatientEntryAfterSampleEntry((DynaBean)form, sysUserId, request);
        if (accessioner.canAccession()) {
            forward = handleSave(request, accessioner);
            if (forward != null) {
                return mapping.findForward(forward);
            }
	    } 
        accessioner = new PatientEntryAfterAnalyzer((DynaBean)form, sysUserId, request);
        if (accessioner.canAccession()) {
            forward = handleSave(request, accessioner);
            if (forward != null) {
                return mapping.findForward(forward);
            }
        }         */
        logAndAddMessage(request, "performAction", "errors.UpdateException");
        return mapping.findForward(FWD_FAIL);
	}	
}
