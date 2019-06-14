package spring.service.sample;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analysis.AnalysisService;
import spring.service.common.BaseObjectServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.organization.OrganizationService;
import spring.service.person.PersonService;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.requester.RequesterTypeService;
import spring.service.requester.SampleRequesterService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleqaevent.SampleQaEventService;
import spring.service.test.TestService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class SampleServiceImpl extends BaseObjectServiceImpl<Sample, String> implements SampleService {

	public static String TABLE_REFERENCE_ID;
	private static Long PERSON_REQUESTER_TYPE_ID;
	private static Long ORGANIZATION_REQUESTER_TYPE_ID;

	protected SampleDAO sampleDAO = SpringContext.getBean(SampleDAO.class);
	private static final AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
	private static final SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	private static final SampleQaEventService sampleQaEventService = SpringContext.getBean(SampleQaEventService.class);
	private static final SampleRequesterService sampleRequesterService = SpringContext.getBean(SampleRequesterService.class);
	private static final PersonService personService = SpringContext.getBean(PersonService.class);
	private static ReferenceTablesService refTableService = SpringContext.getBean(ReferenceTablesService.class);
	private static RequesterTypeService requesterTypeService = SpringContext.getBean(RequesterTypeService.class);
	private static OrganizationService organizationService = SpringContext.getBean(OrganizationService.class);
	private static TestService testService = SpringContext.getBean(TestService.class);

	private Sample sample;

	public synchronized void initializeGlobalVariables() {
		TABLE_REFERENCE_ID = refTableService.getReferenceTableByName("SAMPLE").getId();
		RequesterType type = requesterTypeService.getRequesterTypeByName("provider");
		PERSON_REQUESTER_TYPE_ID = type != null ? Long.parseLong(type.getId()) : Long.MIN_VALUE;
		type = requesterTypeService.getRequesterTypeByName("organization");
		ORGANIZATION_REQUESTER_TYPE_ID = type != null ? Long.parseLong(type.getId()) : Long.MIN_VALUE;
	}

	public SampleServiceImpl() {
		super(Sample.class);
		initializeGlobalVariables();
	}

	public SampleServiceImpl(Sample sample) {
		this();
		this.sample = sample;
	}

	public SampleServiceImpl(String accessionNumber) {
		this();
		sample = sampleDAO.getSampleByAccessionNumber(accessionNumber);
	}

	@Override
	protected SampleDAO getBaseObjectDAO() {
		return sampleDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public Sample getSampleByAccessionNumber(String labNumber) {
		return getMatch("accessionNumber", labNumber).orElse(null);
	}

	@Override
	@Transactional
	public boolean insertDataWithAccessionNumber(Sample sample) {
		insert(sample);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesReceivedOn(String recievedDate) {
		return sampleDAO.getSamplesReceivedOn(recievedDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesForPatient(String patientID) {
		return sampleHumanService.getSamplesForPatient(patientID);
	}

	/**
	 * Gets the date of when the order was completed
	 *
	 * @return The date of when it was completed, null if it was not yet completed
	 */
	@Transactional(readOnly = true)
	public Date getCompletedDate() {
		Date date = null;
		List<Analysis> analysisList = analysisService.getAnalysesBySampleId(sample.getId());

		for (Analysis analysis : analysisList) {
			if (!isCanceled(analysis)) {
				if (analysis.getCompletedDate() == null) {
					return null;
				} else if (date == null) {
					date = analysis.getCompletedDate();
				} else if (analysis.getCompletedDate().after(date)) {
					date = analysis.getCompletedDate();
				}
			}
		}
		return date;
	}

	private boolean isCanceled(Analysis analysis) {
		return StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Canceled)
				.equals(analysis.getStatusId());
	}

	@Transactional(readOnly = true)
	public Timestamp getOrderedDate() {
		if (sample == null) {
			return null;
		}
		ObservationHistory observation = ObservationHistoryServiceImpl.getInstance()
				.getObservationForSample(ObservationHistoryServiceImpl.ObservationType.REQUEST_DATE, sample.getId());
		if (observation != null && observation.getValue() != null) {
			return DateUtil.convertStringDateToTruncatedTimestamp(observation.getValue());
		} else { // If ordered date is not given then use received date
			return sample.getReceivedTimestamp();
		}
	}

	@Transactional(readOnly = true)
	public String getAccessionNumber() {
		return sample.getAccessionNumber();
	}

	@Transactional(readOnly = true)
	public String getReceivedDateForDisplay() {
		return sample.getReceivedDateForDisplay();
	}

	@Transactional(readOnly = true)
	public String getTwoYearReceivedDateForDisplay() {
		String fourYearDate = getReceivedDateForDisplay();
		int lastSlash = fourYearDate.lastIndexOf("/");
		return fourYearDate.substring(0, lastSlash + 1) + fourYearDate.substring(lastSlash + 3);
	}

	@Transactional(readOnly = true)
	public String getReceivedDateWithTwoYearDisplay() {
		return DateUtil.convertTimestampToTwoYearStringDate(sample.getReceivedTimestamp());
	}

	@Transactional(readOnly = true)
	public String getReceivedTimeForDisplay() {
		return sample.getReceivedTimeForDisplay();
	}

	@Transactional(readOnly = true)
	public String getReceived24HourTimeForDisplay() {
		return sample.getReceived24HourTimeForDisplay();
	}

	public boolean isConfirmationSample() {
		return sample != null && sample.getIsConfirmation();
	}

	@Transactional(readOnly = true)
	public Sample getSample() {
		return sample;
	}

	@Transactional(readOnly = true)
	public String getId() {
		return sample.getId();
	}

	@Transactional(readOnly = true)
	public Patient getPatient() {
		return sampleHumanService.getPatientForSample(sample);
	}

	@Transactional(readOnly = true)
	public List<Analysis> getAnalysis() {
		return sample == null ? new ArrayList<>() : analysisService.getAnalysesBySampleId(sample.getId());
	}

	@Transactional(readOnly = true)
	public List<SampleQaEvent> getSampleQAEventList() {
		return sample == null ? new ArrayList<>() : sampleQaEventService.getSampleQaEventsBySample(sample);
	}

	@Transactional(readOnly = true)
	public Person getPersonRequester() {
		if (sample == null) {
			return null;
		}

		List<SampleRequester> requesters = sampleRequesterService.getRequestersForSampleId(sample.getId());

		for (SampleRequester requester : requesters) {
			if (PERSON_REQUESTER_TYPE_ID == requester.getRequesterTypeId()) {
				Person person = new Person();
				person.setId(String.valueOf(requester.getRequesterId()));
				personService.getData(person);
				return person.getId() != null ? person : null;
			}
		}

		return null;
	}

	@Transactional(readOnly = true)
	public Organization getOrganizationRequester() {
		if (sample == null) {
			return null;
		}

		List<SampleRequester> requesters = sampleRequesterService.getRequestersForSampleId(sample.getId());

		for (SampleRequester requester : requesters) {
			if (ORGANIZATION_REQUESTER_TYPE_ID == requester.getRequesterTypeId()) {
				Organization org = organizationService.getOrganizationById(String.valueOf(requester.getRequesterId()));
				return org != null ? org : null;
			}
		}

		return null;
	}

	@Transactional(readOnly = true)
	public Sample getPatientPreviousSampleForTestName(Patient patient, String testName) {
		List<Sample> sampList = sampleHumanService.getSamplesForPatient(patient.getId());
		Sample previousSample = null;
		List<Integer> sampIDList = new ArrayList<>();
		List<Integer> testIDList = new ArrayList<>();

		testIDList.add(Integer.parseInt(testService.getTestByName(testName).getId()));

		if (sampList.isEmpty()) {
			return previousSample;
		}

		for (Sample sample : sampList) {
			sampIDList.add(Integer.parseInt(sample.getId()));
		}

		List<Integer> statusList = new ArrayList<>();
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));

		List<Analysis> analysisList = analysisService.getAnalysesBySampleIdTestIdAndStatusId(sampIDList, testIDList,
				statusList);

		if (analysisList.isEmpty()) {
			return previousSample;
		}

		for (int j = 0; j < analysisList.size(); j++) {
			if (j < analysisList.size() && sample.getAccessionNumber()
					.equals(analysisList.get(j).getSampleItem().getSample().getAccessionNumber())) {
				previousSample = analysisList.get(j + 1).getSampleItem().getSample();
			}

		}

		/*
		 * for(int j=0;j<analysisList.size();j++){
		 *
		 * if(j<analysisList.size() &&
		 * sample.getAccessionNumber().equals(analysisList.get(j).getSampleItem().
		 * getSample().getAccessionNumber())) return
		 * analysisList.get(j+1).getSampleItem().getSample();
		 *
		 * }
		 */
		return previousSample;

	}

	@Override
	@Transactional(readOnly = true)
	public void getData(Sample sample) {
		getBaseObjectDAO().getData(sample);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getConfirmationSamplesReceivedInDateRange(Date receivedDateStart, Date receivedDateEnd) {
		return getBaseObjectDAO().getConfirmationSamplesReceivedInDateRange(receivedDateStart, receivedDateEnd);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(List<Integer> inclusiveProjectIdList,
			List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) {
		return getBaseObjectDAO().getSamplesByProjectAndStatusIDAndAccessionRange(inclusiveProjectIdList,
				inclusiveStatusIdList, minAccession, maxAccession);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(String projectId,
			List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) {
		return getBaseObjectDAO().getSamplesByProjectAndStatusIDAndAccessionRange(projectId, inclusiveStatusIdList,
				minAccession, maxAccession);
	}

	@Override
	@Transactional(readOnly = true)
	public String getLargestAccessionNumberWithPrefix(String prefix) {
		return getBaseObjectDAO().getLargestAccessionNumberWithPrefix(prefix);
	}

	@Override
	@Transactional(readOnly = true)
	public String getLargestAccessionNumberMatchingPattern(String startingWith, int size) {
		return getBaseObjectDAO().getLargestAccessionNumberMatchingPattern(startingWith, size);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesWithPendingQaEventsByService(String serviceId) {
		return getBaseObjectDAO().getSamplesWithPendingQaEventsByService(serviceId);
	}

	@Override
	@Transactional(readOnly = true)
	public List getSamplesByStatusAndDomain(List statuses, String domain) {
		return getBaseObjectDAO().getSamplesByStatusAndDomain(statuses, domain);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousSampleRecord(String id) {
		return getBaseObjectDAO().getPreviousSampleRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesCollectedOn(String collectionDate) {
		return getBaseObjectDAO().getSamplesCollectedOn(collectionDate);
	}

	@Override
	@Transactional(readOnly = true)
	public String getLargestAccessionNumber() {
		return getBaseObjectDAO().getLargestAccessionNumber();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesWithPendingQaEvents(Sample sample, boolean filterByCategory, String qaEventCategoryId,
			boolean filterByDomain) {
		return getBaseObjectDAO().getSamplesWithPendingQaEvents(sample, filterByCategory, qaEventCategoryId,
				filterByDomain);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextSampleRecord(String id) {
		return getBaseObjectDAO().getNextSampleRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Sample getSampleByReferringId(String referringId) {
		return getBaseObjectDAO().getSampleByReferringId(referringId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesReceivedInDateRange(String receivedDateStart, String receivedDateEnd) {
		return getBaseObjectDAO().getSamplesReceivedInDateRange(receivedDateStart, receivedDateEnd);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sample> getSamplesByAccessionRange(String minAccession, String maxAccession) {
		return getBaseObjectDAO().getSamplesByAccessionRange(minAccession, maxAccession);
	}

	@Override
	@Transactional(readOnly = true)
	public void getSampleByAccessionNumber(Sample sample) {
		getBaseObjectDAO().getSampleByAccessionNumber(sample);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfSamples(int startingRecNo) {
		return getBaseObjectDAO().getPageOfSamples(startingRecNo);
	}

	@Override
	public String generateAccessionNumberAndInsert(Sample sample) {
		sample.setAccessionNumber(getBaseObjectDAO().getNextAccessionNumber());
		return insert(sample);
	}
}
