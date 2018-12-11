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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.qaevent.action.retroCI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateData;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateWorker;

public class NonConformityUpdateAction extends BaseAction {
    /*
     * (non-Javadoc)
     * 
     * @see
     * us.mn.state.health.lims.common.action.BaseAction#performAction(org.apache
     * .struts.action.ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */


	@Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
    	BaseActionForm dynaForm = (BaseActionForm) form;
    	NonConformityUpdateData data = new NonConformityUpdateData(dynaForm, currentUserId);
    	NonConformityUpdateWorker worker = new NonConformityUpdateWorker(data );
    	String result = worker.update();
    	
    	if( IActionConstants.FWD_FAIL.equals(result)){
    		ActionMessages errors = worker.getErrors();
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
    	}
    	
    	return mapping.findForward(result);
    }


 
    @Override
    protected String getPageSubtitleKey() {
        return "qaevent.add.title";
    }

    @Override
    protected String getPageTitleKey() {
        return "qaevent.add.title";
    }
}
