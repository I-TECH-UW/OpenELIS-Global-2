package spring.service.sample;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.mine.sample.form.SampleEditForm;
import spring.service.analysis.AnalysisService;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.organization.OrganizationService;
import spring.service.person.PersonService;
import spring.service.requester.SampleRequesterService;
import spring.service.result.ResultService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleitem.SampleItemService;
import spring.service.test.TestSectionService;
import spring.service.test.TestService;
import spring.service.typeofsample.TypeOfSampleTestService;
import spring.service.userrole.UserRoleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.RequesterService;
import us.mn.state.health.lims.common.services.SampleAddService;
import us.mn.state.health.lims.common.services.SampleAddService.SampleTestCollection;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.registration.ResultUpdateRegister;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.action.util.ResultsUpdateDataSet;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.bean.SampleEditItem;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
public class SampleEditServiceImpl implements SampleEditService {

	private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";
	private static final String CANCELED_TEST_STATUS_ID;
	private static final String CANCELED_SAMPLE_STATUS_ID;

	static {
		CANCELED_TEST_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Canceled);
		CANCELED_SAMPLE_STATUS_ID = StatusService.getInstance().getStatusID(SampleStatus.Canceled);
	}

	@Autowired
	private SampleItemService sampleItemService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private TestService testService;
	@Autowired
	private ObservationHistoryService observationService;
	@Autowired
	private TestSectionService testSectionService;
	@Autowired
	private PersonService personService;
	@Autowired
	private SampleRequesterService sampleRequesterService;
	@Autowired
	private OrganizationService organizationService;
//	@Autowired
//	private OrganizationOrganizationTypeService orgOrgTypeService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	TypeOfSampleTestService typeOfSampleTestService;
	@Autowired
	ResultService resultService;
	@Autowired
	SampleHumanService sampleHumanService;
	@Autowired
	UserRoleService userRoleService;

	@Transactional
	@Override
	public void editSample(SampleEditForm form, HttpServletRequest request, Sample updatedSample, boolean sampleChanged,
			String sysUserId) {

		List<SampleEditItem> existingTests = form.getExistingTests();
		List<Analysis> cancelAnalysisList = createRemoveList(existingTests, sysUserId);
		List<SampleItem> updateSampleItemList = createSampleItemUpdateList(existingTests, sysUserId);
		List<SampleItem> cancelSampleItemList = createCancelSampleList(existingTests, cancelAnalysisList, sysUserId);
		List<Analysis> addAnalysisList = createAddAanlysisList(form.getPossibleTests(), sysUserId);

		List<IResultUpdate> updaters = ResultUpdateRegister.getRegisteredUpdaters();
		ResultsUpdateDataSet actionDataSet = new ResultsUpdateDataSet(sysUserId);

		if (updatedSample == null) {
			updatedSample = sampleService.getSampleByAccessionNumber(form.getAccessionNumber());
		}

		String receivedDateForDisplay = updatedSample.getReceivedDateForDisplay();
		String collectionDateFromRecieveDate = null;
		boolean useReceiveDateForCollectionDate = !FormFields.getInstance().useField(Field.CollectionDate);

		if (useReceiveDateForCollectionDate) {
			collectionDateFromRecieveDate = receivedDateForDisplay + " 00:00:00";
		}

		SampleAddService sampleAddService = new SampleAddService(form.getSampleXML(), sysUserId, updatedSample,
				collectionDateFromRecieveDate);
		List<SampleTestCollection> addedSamples = createAddSampleList(form, sampleAddService);

		SampleOrderService sampleOrderService = new SampleOrderService(form.getSampleOrderItems());
		SampleOrderService.SampleOrderPersistenceArtifacts orderArtifacts = sampleOrderService
				.getPersistenceArtifacts(updatedSample, sysUserId);

		if (orderArtifacts.getSample() != null) {
			sampleChanged = true;
			updatedSample = orderArtifacts.getSample();
		}

		Person referringPerson = orderArtifacts.getProviderPerson();
		SampleService sampleSampleService = SpringContext.getBean(SampleService.class);
		sampleSampleService.setSample(updatedSample);
		Patient patient = sampleSampleService.getPatient();

		for (SampleItem sampleItem : updateSampleItemList) {
			sampleItemService.update(sampleItem);
		}

		for (Analysis analysis : cancelAnalysisList) {
			analysisService.update(analysis);
			addExternalResultsToDeleteList(analysis, patient, updatedSample, actionDataSet);
		}

		for (IResultUpdate updater : updaters) {
			updater.postTransactionalCommitUpdate(actionDataSet);
		}

		for (Analysis analysis : addAnalysisList) {
			if (analysis.getId() == null) {
				analysisService.insert(analysis);
			} else {
				analysisService.update(analysis);
			}
		}

		for (SampleItem sampleItem : cancelSampleItemList) {
			sampleItemService.update(sampleItem);
		}

		if (sampleChanged) {
			sampleService.update(updatedSample);
		}

		// seems like this is unused
		/*
		 * if (paymentObservation != null) {
		 * paymentObservation.setPatientId(patient.getId());
		 * observationDAO.insertOrUpdateData(paymentObservation); }
		 */

		for (SampleTestCollection sampleTestCollection : addedSamples) {
			sampleItemService.insert(sampleTestCollection.item);

			for (Test test : sampleTestCollection.tests) {
				test = testService.get(test.getId());

				Analysis analysis = populateAnalysis(sampleTestCollection, test,
						sampleTestCollection.testIdToUserSectionMap.get(test.getId()), sampleAddService);
				analysisService.insert(analysis);
			}

			if (sampleTestCollection.initialSampleConditionIdList != null) {
				for (ObservationHistory observation : sampleTestCollection.initialSampleConditionIdList) {
					observation.setPatientId(patient.getId());
					observation.setSampleItemId(sampleTestCollection.item.getId());
					observation.setSampleId(sampleTestCollection.item.getSample().getId());
					observation.setSysUserId(sysUserId);
					observationService.insert(observation);
				}
			}
		}

		if (referringPerson != null) {
			if (referringPerson.getId() == null) {
				personService.insert(referringPerson);
			} else {
				personService.update(referringPerson);
			}
		}

		for (ObservationHistory observation : orderArtifacts.getObservations()) {
			observationService.save(observation);
		}

		if (orderArtifacts.getSamplePersonRequester() != null) {
			SampleRequester samplePersonRequester = orderArtifacts.getSamplePersonRequester();
			samplePersonRequester.setRequesterId(orderArtifacts.getProviderPerson().getId());
			sampleRequesterService.save(samplePersonRequester);
		}

		if (orderArtifacts.getProviderOrganization() != null) {
			boolean link = orderArtifacts.getProviderOrganization().getId() == null;
			organizationService.save(orderArtifacts.getProviderOrganization());
			if (link) {
				organizationService.linkOrganizationAndType(orderArtifacts.getProviderOrganization(),
						RequesterService.REFERRAL_ORG_TYPE_ID);
			}
		}

		if (orderArtifacts.getSampleOrganizationRequester() != null) {
			if (orderArtifacts.getProviderOrganization() != null) {
				orderArtifacts.getSampleOrganizationRequester()
						.setRequesterId(orderArtifacts.getProviderOrganization().getId());
			}
			sampleRequesterService.save(orderArtifacts.getSampleOrganizationRequester());
		}

		if (orderArtifacts.getDeletableSampleOrganizationRequester() != null) {
			sampleRequesterService.delete(orderArtifacts.getDeletableSampleOrganizationRequester());
		}

		request.getSession().setAttribute("lastAccessionNumber", updatedSample.getAccessionNumber());
		request.getSession().setAttribute("lastPatientId", patient.getId());

	}

	private void addExternalResultsToDeleteList(Analysis analysis, Patient patient, Sample updatedSample,
			ResultsUpdateDataSet actionDataSet) {
		List<ResultSet> deletedResults = new ArrayList<>();
		if (!GenericValidator.isBlankOrNull(analysis.getSampleItem().getSample().getReferringId())) {
			List<Result> results = resultService.getResultsByAnalysis(analysis);
			if (results.size() == 0) {
				Result result = createCancelResult(analysis);
				results.add(result);
			}
			for (Result result : results) {
				result.setResultEvent(Event.TESTING_NOT_DONE);

				deletedResults.add(new ResultSet(result, null, null, patient, updatedSample, null, false));
			}
		}
		actionDataSet.setModifiedResults(deletedResults);

	}

	private Result createCancelResult(Analysis analysis) {
		Result result = new Result();
		result.setAnalysis(analysis);
		result.setMinNormal((double) 0);
		result.setMaxNormal((double) 0);
		result.setValue("cancel");
		return result;
	}

	private List<SampleItem> createSampleItemUpdateList(List<SampleEditItem> existingTests, String sysUserId) {
		List<SampleItem> modifyList = new ArrayList<>();

		for (SampleEditItem editItem : existingTests) {
			if (editItem.isSampleItemChanged()) {
				SampleItem sampleItem = sampleItemService.get(editItem.getSampleItemId());
				if (sampleItem != null) {
					String collectionTime = editItem.getCollectionDate();
					if (GenericValidator.isBlankOrNull(collectionTime)) {
						sampleItem.setCollectionDate(null);
					} else {
						collectionTime += " " + (GenericValidator.isBlankOrNull(editItem.getCollectionTime()) ? "00:00"
								: editItem.getCollectionTime());
						sampleItem.setCollectionDate(DateUtil.convertStringDateToTimestamp(collectionTime));
					}
					sampleItem.setSysUserId(sysUserId);
					modifyList.add(sampleItem);
				}
			}
		}

		return modifyList;
	}

	private Analysis populateAnalysis(SampleTestCollection sampleTestCollection, Test test,
			String userSelectedTestSection, SampleAddService sampleAddService) {
		java.sql.Date collectionDateTime = DateUtil.convertStringDateTimeToSqlDate(sampleTestCollection.collectionDate);
		TestSection testSection = test.getTestSection();
		if (!GenericValidator.isBlankOrNull(userSelectedTestSection)) {
			testSection = testSectionService.get(userSelectedTestSection); // change
		}

		Panel panel = sampleAddService.getPanelForTest(test);

		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setSampleItem(sampleTestCollection.item);
		analysis.setSysUserId(sampleTestCollection.item.getSysUserId());
		analysis.setRevision("0");
		analysis.setStartedDate(collectionDateTime == null ? DateUtil.getNowAsSqlDate() : collectionDateTime);
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
		analysis.setTestSection(testSection);
		analysis.setPanel(panel);
		return analysis;
	}

	private List<SampleTestCollection> createAddSampleList(SampleEditForm form, SampleAddService sampleAddService) {

		String maxAccessionNumber = form.getMaxAccessionNumber();
		if (!GenericValidator.isBlankOrNull(maxAccessionNumber)) {
			sampleAddService.setInitialSampleItemOrderValue(Integer.parseInt(maxAccessionNumber.split("-")[1]));
		}

		return sampleAddService.createSampleTestCollection();
	}

	private List<SampleItem> createCancelSampleList(List<SampleEditItem> list, List<Analysis> cancelAnalysisList,
			String sysUserId) {
		List<SampleItem> cancelList = new ArrayList<>();

		boolean cancelTest = false;

		for (SampleEditItem editItem : list) {
			if (editItem.getAccessionNumber() != null) {
				cancelTest = false;
			}
			if (cancelTest && !cancelAnalysisListContainsId(editItem.getAnalysisId(), cancelAnalysisList)) {
				Analysis analysis = getCancelableAnalysis(editItem, sysUserId);
				cancelAnalysisList.add(analysis);
			}

			if (editItem.isRemoveSample()) {
				cancelTest = true;
				SampleItem sampleItem = getCancelableSampleItem(editItem, sysUserId);
				if (sampleItem != null) {
					cancelList.add(sampleItem);
				}
				if (!cancelAnalysisListContainsId(editItem.getAnalysisId(), cancelAnalysisList)) {
					Analysis analysis = getCancelableAnalysis(editItem, sysUserId);
					cancelAnalysisList.add(analysis);
				}
			}
		}

		return cancelList;
	}

	private List<Analysis> createAddAanlysisList(List<SampleEditItem> tests, String sysUserId) {
		List<Analysis> addAnalysisList = new ArrayList<>();

		for (SampleEditItem sampleEditItem : tests) {
			if (sampleEditItem.isAdd()) {

				Analysis analysis = newOrExistingCanceledAnalysis(sampleEditItem);

				if (analysis.getId() == null) {
					SampleItem sampleItem = sampleItemService.get(sampleEditItem.getSampleItemId());
					analysis.setSampleItem(sampleItem);

					Test test = testService.get(sampleEditItem.getTestId());

					analysis.setTest(test);
					analysis.setRevision("0");
					analysis.setTestSection(test.getTestSection());
					analysis.setEnteredDate(DateUtil.getNowAsTimestamp());
					analysis.setIsReportable(test.getIsReportable());
					analysis.setAnalysisType("MANUAL");
					analysis.setStartedDate(DateUtil.getNowAsSqlDate());
				}

				analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
				analysis.setSysUserId(sysUserId);

				addAnalysisList.add(analysis);
			}
		}

		return addAnalysisList;
	}

	private Analysis newOrExistingCanceledAnalysis(SampleEditItem sampleEditItem) {
		List<Analysis> canceledAnalysis = analysisService
				.getAnalysesBySampleItemIdAndStatusId(sampleEditItem.getSampleItemId(), CANCELED_TEST_STATUS_ID);

		for (Analysis analysis : canceledAnalysis) {
			if (sampleEditItem.getTestId().equals(analysis.getTest().getId())) {
				return analysis;
			}
		}

		return new Analysis();
	}

	private List<Analysis> createRemoveList(List<SampleEditItem> tests, String sysUserId) {
		List<Analysis> removeAnalysisList = new ArrayList<>();

		for (SampleEditItem sampleEditItem : tests) {
			if (sampleEditItem.isCanceled()) {
				Analysis analysis = getCancelableAnalysis(sampleEditItem, sysUserId);
				removeAnalysisList.add(analysis);
			}
		}

		return removeAnalysisList;
	}

	private Analysis getCancelableAnalysis(SampleEditItem sampleEditItem, String sysUserId) {
		Analysis analysis = analysisService.get(sampleEditItem.getAnalysisId());
		analysis.setSysUserId(sysUserId);
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled));
		return analysis;
	}

	private SampleItem getCancelableSampleItem(SampleEditItem editItem, String sysUserId) {
		String sampleItemId = editItem.getSampleItemId();
		SampleItem item = sampleItemService.get(sampleItemId);

		if (item.getId() != null) {
			item.setStatusId(CANCELED_SAMPLE_STATUS_ID);
			item.setSysUserId(sysUserId);
			return item;
		}

		return null;
	}

	private boolean cancelAnalysisListContainsId(String analysisId, List<Analysis> cancelAnalysisList) {

		for (Analysis analysis : cancelAnalysisList) {
			if (analysisId.equals(analysis.getId())) {
				return true;
			}
		}

		return false;
	}
}
