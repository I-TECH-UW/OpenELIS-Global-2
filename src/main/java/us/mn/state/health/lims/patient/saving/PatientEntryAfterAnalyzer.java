
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
 * @since 2010-06-25
 */
package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.InitialRegistration;
import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.NotRegistered;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;


public class PatientEntryAfterAnalyzer extends PatientEntry {

    public PatientEntryAfterAnalyzer(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws Exception {
        super(dynaBean, sysUserId, request);
        this.newPatientStatus = InitialRegistration;
        this.newSampleStatus  = NotRegistered;
    }
    
    @Override
    public boolean canAccession() {
        return (NotRegistered == statusSet.getPatientRecordStatus()
             && NotRegistered == statusSet.getSampleRecordStatus());
    }
        
    /**
     * Find existing sampleHuman, so we can update it with our new patient when we fill in all IDs when we persist.
     * @see us.mn.state.health.lims.patient.saving.PatientEntry#populateSampleHuman()
     */
    @Override
    protected void populateSampleHuman() throws Exception {
        sampleHuman = new SampleHuman();
        sampleHuman.setSampleId(statusSet.getSampleId());
        sampleHumanDAO.getDataBySample(sampleHuman);
    }
    
    /**
     * Get rid of the links to the UNKNOWN_PATIENT, then insert a new one.
     * @see us.mn.state.health.lims.patient.saving.Accessioner#persistRecordStatus()
     */
    @Override
    protected void persistRecordStatus() {
        StatusService.getInstance().deleteRecordStatus(sample, PatientUtil.getUnknownPatient(), sysUserId);
        super.persistRecordStatus();
    }
}
