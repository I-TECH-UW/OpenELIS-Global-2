package spring.service.sample;

import java.sql.Date;
import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface SampleService extends BaseObjectService<Sample, String> {
	void getData(Sample sample);

	List<Sample> getConfirmationSamplesReceivedInDateRange(Date receivedDateStart, Date receivedDateEnd);

	List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(List<Integer> inclusiveProjectIdList,
			List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession);

	List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(String projectId, List<Integer> inclusiveStatusIdList,
			String minAccession, String maxAccession);

	String getLargestAccessionNumberWithPrefix(String prefix);

	String getLargestAccessionNumberMatchingPattern(String startingWith, int size);

	List<Sample> getSamplesWithPendingQaEventsByService(String serviceId);

	List getSamplesByStatusAndDomain(List statuses, String domain);

	List getPreviousSampleRecord(String id);

	List<Sample> getSamplesReceivedOn(String recievedDate);

	List<Sample> getSamplesCollectedOn(String collectionDate);

	String getLargestAccessionNumber();

	List<Sample> getSamplesWithPendingQaEvents(Sample sample, boolean filterByCategory, String qaEventCategoryId,
			boolean filterByDomain);

	List getNextSampleRecord(String id);

	Sample getSampleByReferringId(String referringId);

	List<Sample> getSamplesReceivedInDateRange(String receivedDateStart, String receivedDateEnd);

	List<Sample> getSamplesByAccessionRange(String minAccession, String maxAccession);

	boolean insertDataWithAccessionNumber(Sample sample);

	void getSampleByAccessionNumber(Sample sample);

	Sample getSampleByAccessionNumber(String accessionNumber);

	List getPageOfSamples(int startingRecNo);

	List<Sample> getSamplesForPatient(String patientID);

	String generateAccessionNumberAndInsert(Sample sample);
}
