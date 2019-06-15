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
package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.InitialRegistration;
import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.ValidationRegistration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import us.mn.state.health.lims.common.action.IActionConstants;

/**
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jul 6, 2010
 */
@Service
@Scope("prototype")
public class SampleSecondEntry extends SampleEntry implements ISampleSecondEntry, IActionConstants {

	/**
	 * @param dynaBean
	 * @param sysUserId
	 */
	public SampleSecondEntry(BaseForm form, String sysUserId, HttpServletRequest request) throws Exception {
		this();
		super.setFieldsFromForm(form);
		super.setSysUserId(sysUserId);
		super.setRequest(request);
	}

	public SampleSecondEntry() {
		super();
		newPatientStatus = null; // no change
		newSampleStatus = ValidationRegistration;
	}

	/**
	 * @see us.mn.state.health.lims.patient.saving.PatientEntry#canAccession()
	 */
	@Override
	public boolean canAccession() {
		return projectFormMapper.isSecondEntry(request) && InitialRegistration == statusSet.getSampleRecordStatus();
	}

	@Override
	protected String getActionLabel() {
		return MessageUtil.getMessage("banner.menu.createSample.Verify");
	}
}
