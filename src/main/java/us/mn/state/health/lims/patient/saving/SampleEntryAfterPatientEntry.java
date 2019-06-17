package us.mn.state.health.lims.patient.saving;

import static us.mn.state.health.lims.common.services.StatusService.RecordStatus.NotRegistered;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

@Service
@Scope("prototype")
public class SampleEntryAfterPatientEntry extends SampleEntry {

	public SampleEntryAfterPatientEntry(BaseForm form, String sysUserId, HttpServletRequest request) throws Exception {
		this();
		super.setFieldsFromForm(form);
		super.setSysUserId(sysUserId);
		super.setRequest(request);

		newPatientStatus = null; // leave it be
		newSampleStatus = RecordStatus.InitialRegistration;
	}

	public SampleEntryAfterPatientEntry() {
		super();
		newPatientStatus = null; // leave it be
		newSampleStatus = RecordStatus.InitialRegistration;
	}

	@Override
	public boolean canAccession() {
		return (NotRegistered == statusSet.getSampleRecordStatus());
	}

	/**
	 * Find existing sampleHuman, so we can update it with our new patient when we
	 * fill in all IDs when we persist.
	 *
	 * @see us.mn.state.health.lims.patient.saving.PatientEntry#populateSampleHuman()
	 */
	@Override
	protected void populateSampleHuman() throws Exception {
		sampleHuman = new SampleHuman();
		sampleHuman.setSampleId(statusSet.getSampleId());
		sampleHumanService.getDataBySample(sampleHuman);
	}
}
