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
package org.openelisglobal.sample.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.SampleOrderService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.action.bean.PatientSearch;

/**
 * The SampleEntryAction class represents the initial Action for the SampleEntry
 * form of the application
 *
 */
public class SamplePatientEntryAction extends BaseSampleEntryAction {

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "success";

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);

		BaseActionForm dynaForm = (BaseActionForm) form;

		dynaForm.initialize(mapping);

        SampleOrderService sampleOrderService = new SampleOrderService();
        PropertyUtils.setProperty( dynaForm, "sampleOrderItems", sampleOrderService.getSampleOrderItem() );
		PropertyUtils.setProperty(dynaForm, "patientProperties", new PatientManagementInfo());
        PropertyUtils.setProperty( dynaForm, "patientSearch", new PatientSearch() );
		PropertyUtils.setProperty(dynaForm, "sampleTypes", DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
		PropertyUtils.setProperty(dynaForm, "testSectionList", DisplayListService.getInstance().getList(ListType.TEST_SECTION));
        PropertyUtils.setProperty( dynaForm, "currentDate", DateUtil.getCurrentDateAsText());

		addProjectList( dynaForm );

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(dynaForm, "initialSampleConditionList", DisplayListService.getInstance().getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		return mapping.findForward(forward);
	}

}
