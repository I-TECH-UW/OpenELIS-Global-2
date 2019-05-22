package spring.service.sample;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.dao.TestDAO;

@Service
@DependsOn({ "springContext" })
public class SampleServiceImpl extends BaseObjectServiceImpl<Sample> implements SampleService {

	public static String TABLE_REFERENCE_ID;
	private static Long PERSON_REQUESTER_TYPE_ID;
	private static Long ORGANIZATION_REQUESTER_TYPE_ID;

	@Autowired
	protected SampleDAO sampleDAO;
	@Autowired
	private static final AnalysisDAO analysisDAO = SpringContext.getBean(AnalysisDAO.class);
	@Autowired
	private static final SampleHumanDAO sampleHumanDAO = SpringContext.getBean(SampleHumanDAO.class);
	@Autowired
	private static final SampleQaEventDAO sampleQaEventDAO = SpringContext.getBean(SampleQaEventDAO.class);
	@Autowired
	private static final SampleRequesterDAO sampleRequesterDAO = SpringContext.getBean(SampleRequesterDAO.class);
	@Autowired
	private static final PersonDAO personDAO = SpringContext.getBean(PersonDAO.class);
	@Autowired
	ReferenceTablesDAO refTableDAO = SpringContext.getBean(ReferenceTablesDAO.class);
	@Autowired
	RequesterTypeDAO requesterTypeDAO = SpringContext.getBean(RequesterTypeDAO.class);
	@Autowired
	OrganizationDAO organizationDAO = SpringContext.getBean(OrganizationDAO.class);
	@Autowired
	TestDAO testDAO = SpringContext.getBean(TestDAO.class);

	private Sample sample;

	public synchronized void initializeGlobalVariables() {
		TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName("SAMPLE").getId();
		RequesterType type = requesterTypeDAO.getRequesterTypeByName("provider");
		PERSON_REQUESTER_TYPE_ID = type != null ? Long.parseLong(type.getId()) : Long.MIN_VALUE;
		type = requesterTypeDAO.getRequesterTypeByName("organization");
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
	public Sample getSampleByAccessionNumber(String labNumber) {
		return getMatch("accessionNumber", labNumber).get();
	}

	@Override
	@Transactional 
	public void insertDataWithAccessionNumber(Sample sample) {
		insert(sample);
	}

	@Override
	@Transactional 
	public List<Sample> getSamplesReceivedOn(String recievedDate) {
		return sampleDAO.getSamplesReceivedOn(recievedDate);
	}

	@Override
	@Transactional 
	public List<Sample> getSamplesForPatient(String patientID) {
		return sampleHumanDAO.getSamplesForPatient(patientID);
	}

	/**
	 * Gets the date of when the order was completed
	 *
	 * @return The date of when it was completed, null if it was not yet completed
	 */
	public Date getCompletedDate() {
		Date date = null;
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

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

	public Timestamp getOrderedDate() {
		if (sample == null) {
			return null;
		}
		ObservationHistory observation = ObservationHistoryServiceImpl
				.getObservationForSample(ObservationHistoryServiceImpl.ObservationType.REQUEST_DATE, sample.getId());
		if (observation != null && observation.getValue() != null) {
			return DateUtil.convertStringDateToTruncatedTimestamp(observation.getValue());
		} else { // If ordered date is not given then use received date
			return sample.getReceivedTimestamp();
		}
	}

	public String getAccessionNumber() {
		return sample.getAccessionNumber();
	}

	public String getReceivedDateForDisplay() {
		return sample.getReceivedDateForDisplay();
	}

	public String getTwoYearReceivedDateForDisplay() {
		String fourYearDate = getReceivedDateForDisplay();
		int lastSlash = fourYearDate.lastIndexOf("/");
		return fourYearDate.substring(0, lastSlash + 1) + fourYearDate.substring(lastSlash + 3);
	}

	public String getReceivedDateWithTwoYearDisplay() {
		return DateUtil.convertTimestampToTwoYearStringDate(sample.getReceivedTimestamp());
	}

	public String getReceivedTimeForDisplay() {
		return sample.getReceivedTimeForDisplay();
	}

	public String getReceived24HourTimeForDisplay() {
		return sample.getReceived24HourTimeForDisplay();
	}

	public boolean isConfirmationSample() {
		return sample != null && sample.getIsConfirmation();
	}

	public Sample getSample() {
		return sample;
	}

	public String getId() {
		return sample.getId();
	}

	public Patient getPatient() {
		return sampleHumanDAO.getPatientForSample(sample);
	}

	public List<Analysis> getAnalysis() {
		return sample == null ? new ArrayList<>() : analysisDAO.getAnalysesBySampleId(sample.getId());
	}

	public List<SampleQaEvent> getSampleQAEventList() {
		return sample == null ? new ArrayList<>() : sampleQaEventDAO.getSampleQaEventsBySample(sample);
	}

	public Person getPersonRequester() {
		if (sample == null) {
			return null;
		}

		List<SampleRequester> requesters = sampleRequesterDAO.getRequestersForSampleId(sample.getId());

		for (SampleRequester requester : requesters) {
			if (PERSON_REQUESTER_TYPE_ID == requester.getRequesterTypeId()) {
				Person person = new Person();
				person.setId(String.valueOf(requester.getRequesterId()));
				personDAO.getData(person);
				return person.getId() != null ? person : null;
			}
		}

		return null;
	}

	public Organization getOrganizationRequester() {
		if (sample == null) {
			return null;
		}

		List<SampleRequester> requesters = sampleRequesterDAO.getRequestersForSampleId(sample.getId());

		for (SampleRequester requester : requesters) {
			if (ORGANIZATION_REQUESTER_TYPE_ID == requester.getRequesterTypeId()) {
				Organization org = organizationDAO.getOrganizationById(String.valueOf(requester.getRequesterId()));
				return org != null ? org : null;
			}
		}

		return null;
	}

	public Sample getPatientPreviousSampleForTestName(Patient patient, String testName) {
		List<Sample> sampList = sampleHumanDAO.getSamplesForPatient(patient.getId());
		Sample previousSample = null;
		List<Integer> sampIDList = new ArrayList<>();
		List<Integer> testIDList = new ArrayList<>();

		testIDList.add(Integer.parseInt(testDAO.getTestByName(testName).getId()));

		if (sampList.isEmpty()) {
			return previousSample;
		}

		for (Sample sample : sampList) {
			sampIDList.add(Integer.parseInt(sample.getId()));
		}

		List<Integer> statusList = new ArrayList<>();
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));

		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleIdTestIdAndStatusId(sampIDList, testIDList,
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
}
