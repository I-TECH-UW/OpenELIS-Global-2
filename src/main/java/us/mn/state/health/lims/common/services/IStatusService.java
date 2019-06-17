package us.mn.state.health.lims.common.services;

import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.ExternalOrderStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface IStatusService {
	public boolean matches(String id, SampleStatus sampleStatus);

	public boolean matches(String id, AnalysisStatus analysisStatus);

	public boolean matches(String id, OrderStatus orderStatus);

	public boolean matches(String id, ExternalOrderStatus externalOrderStatus);

	public String getStatusID(OrderStatus statusType);

	public String getStatusID(SampleStatus statusType);

	public String getStatusID(AnalysisStatus statusType);

	public String getStatusID(ExternalOrderStatus statusType);

	public String getStatusName(RecordStatus statusType);

	public String getStatusName(OrderStatus statusType);

	public String getStatusName(SampleStatus statusType);

	public String getStatusName(AnalysisStatus statusType);

	public String getStatusName(ExternalOrderStatus statusType);

	public String getDictionaryID(RecordStatus statusType);

	public OrderStatus getOrderStatusForID(String id);

	public SampleStatus getSampleStatusForID(String id);

	public AnalysisStatus getAnalysisStatusForID(String id);

	public ExternalOrderStatus getExternalOrderStatusForID(String id);

	public RecordStatus getRecordStatusForID(String id);

	public StatusSet getStatusSetForSampleId(String sampleId);

	public StatusSet getStatusSetForAccessionNumber(String accessionNumber);

	/*
	 * Preconditions: It is called within a transaction Both the patient and sample
	 * ids are valid
	 *
	 * For now it will fail silently Either sampleStatus or patient status may be
	 * null
	 */
	@Transactional(readOnly = true)
	public void persistRecordStatusForSample(Sample sample, RecordStatus recordStatus, Patient patient,
			RecordStatus patientStatus, String sysUserId);

	@Transactional
	public void deleteRecordStatus(Sample sample, Patient patient, String sysUserId);

	public String getStatusNameFromId(String id);

}
