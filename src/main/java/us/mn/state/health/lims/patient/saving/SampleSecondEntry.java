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

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jul 6, 2010
 */
public class SampleSecondEntry extends SampleEntry implements IActionConstants {

    /**
     * @param dynaBean
     * @param sysUserId
     */
    public SampleSecondEntry(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws Exception {
        super(dynaBean, sysUserId, request);
        this.newPatientStatus = null;   // no change
        this.newSampleStatus = ValidationRegistration;
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
		return StringUtil.getMessageForKey("banner.menu.createSample.Verify");
	}
}
