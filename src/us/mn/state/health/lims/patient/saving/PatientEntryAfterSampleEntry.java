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

/**
 * Côte d'Ivoire
 * @author pahill
 * @since 2010-06-15
 **/

package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.NotRegistered;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.common.services.StatusService.RecordStatus;

public class PatientEntryAfterSampleEntry extends PatientEntry {

    public PatientEntryAfterSampleEntry(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws Exception {
        super(dynaBean, sysUserId, request);
        this.newPatientStatus = RecordStatus.InitialRegistration;
        this.newSampleStatus  = null; // leave it be
    }
    
    /**
     * An existing not registered patient with the sample already somewhere else  
     */
    @Override
    public boolean canAccession() {
        return (NotRegistered == statusSet.getPatientRecordStatus() &&
                NotRegistered != statusSet.getSampleRecordStatus());
    }        
}
