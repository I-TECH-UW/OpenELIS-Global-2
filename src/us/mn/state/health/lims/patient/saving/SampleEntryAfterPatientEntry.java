package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.NotRegistered;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

public class SampleEntryAfterPatientEntry extends SampleEntry {

    public SampleEntryAfterPatientEntry(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws Exception {
        super(dynaBean, sysUserId, request);        
        this.newPatientStatus = null;  // leave it be
        this.newSampleStatus  = RecordStatus.InitialRegistration;
    }
    
    @Override
    public boolean canAccession() {
        return (NotRegistered       == statusSet.getSampleRecordStatus());
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
}
