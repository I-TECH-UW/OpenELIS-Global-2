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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.audittrail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailViewWorker;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;

public class AuditTrailViewAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm)form;
		String accessionNumber = dynaForm.getString("accessionNumberSearch");
		dynaForm.initialize(mapping);
		
		if( !GenericValidator.isBlankOrNull(accessionNumber)){
			AuditTrailViewWorker worker = new AuditTrailViewWorker( accessionNumber);
			List<AuditTrailItem> items = worker.getAuditTrail();
			PropertyUtils.setProperty(dynaForm, "log", items);
			PropertyUtils.setProperty(dynaForm, "accessionNumber", accessionNumber);
            PropertyUtils.setProperty( dynaForm, "sampleOrderItems", worker.getSampleOrderSnapshot() );
            PropertyUtils.setProperty( dynaForm, "patientProperties", worker.getPatientSnapshot() );
		}
		
		return mapping.findForward(IActionConstants.FWD_SUCCESS);
	}

	@Override
	protected String getPageTitleKey() {
		return "reports.auditTrail";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "reports.auditTrail";
	}

}
