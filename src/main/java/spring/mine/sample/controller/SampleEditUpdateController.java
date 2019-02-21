package spring.mine.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SampleEditForm;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.services.RequesterService;
import us.mn.state.health.lims.common.services.SampleAddService;
import us.mn.state.health.lims.common.services.SampleAddService.SampleTestCollection;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.registration.ResultUpdateRegister;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.action.util.ResultsUpdateDataSet;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.bean.SampleEditItem;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class SampleEditUpdateController extends BaseController {

	private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static final SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final TestDAO testDAO = new TestDAOImpl();
	private static final String CANCELED_TEST_STATUS_ID;
	private static final String CANCELED_SAMPLE_STATUS_ID;
	// private ObservationHistory paymentObservation = null;
	private static final ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
	private static final TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
	private static final PersonDAO personDAO = new PersonDAOImpl();
	private static final SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
	private static final OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	private static final OrganizationOrganizationTypeDAO orgOrgTypeDAO = new OrganizationOrganizationTypeDAOImpl();

	static {
		CANCELED_TEST_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Canceled);
		CANCELED_SAMPLE_STATUS_ID = StatusService.getInstance().getStatusID(SampleStatus.Canceled);
	}

	@RequestMapping(value = "/SampleEditUpdate", method = RequestMethod.POST)
	public ModelAndView showSampleEditUpdate(HttpServletRequest request, @ModelAttribute("form") SampleEditForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleEditForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		boolean sampleChanged = accessionNumberChanged(form);
		Sample updatedSample = null;

		if (sampleChanged) {
			errors = (BaseErrors) validateNewAccessionNumber(form.getNewAccessionNumber(), errors);
			if (errors.hasErrors()) {
				saveErrors(errors);
				request.setAttribute(Globals.ERROR_KEY, errors);
				return findForward(FWD_FAIL, form);
			} else {
				updatedSample = updateAccessionNumberInSample(form);
			}
		}

		List<SampleEditItem> existingTests = form.getExistingTests();
		List<Analysis> cancelAnalysisList = createRemoveList(existingTests);
		List<SampleItem> updateSampleItemList = createSampleItemUpdateList(existingTests);
		List<SampleItem> cancelSampleItemList = createCancelSampleList(existingTests, cancelAnalysisList);
		List<Analysis> addAnalysisList = createAddAanlysisList(form.getPossibleTests());

		List<IResultUpdate> updaters = ResultUpdateRegister.getRegisteredUpdaters();
		ResultsUpdateDataSet actionDataSet = new ResultsUpdateDataSet(getSysUserId(request));

		if (updatedSample == null) {
			updatedSample = sampleDAO.getSampleByAccessionNumber(form.getAccessionNumber());
		}

		String receivedDateForDisplay = updatedSample.getReceivedDateForDisplay();
		String collectionDateFromRecieveDate = null;
		boolean useReceiveDateForCollectionDate = !FormFields.getInstance().useField(Field.CollectionDate);

		if (useReceiveDateForCollectionDate) {
			collectionDateFromRecieveDate = receivedDateForDisplay + " 00:00:00";
		}

		SampleAddService sampleAddService = new SampleAddService(form.getSampleXML(), getSysUserId(request), updatedSample,
				collectionDateFromRecieveDate);
		List<SampleTestCollection> addedSamples = createAddSampleList(form, sampleAddService);

		SampleOrderService sampleOrderService = new SampleOrderService(form.getSampleOrderItems());
		SampleOrderService.SampleOrderPersistenceArtifacts orderArtifacts = sampleOrderService
				.getPersistenceArtifacts(updatedSample, getSysUserId(request));

		if (orderArtifacts.getSample() != null) {
			sampleChanged = true;
			updatedSample = orderArtifacts.getSample();
		}

		Person referringPerson = orderArtifacts.getProviderPerson();
		Patient patient = new SampleService(updatedSample).getPatient();

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {

			for (SampleItem sampleItem : updateSampleItemList) {
				sampleItemDAO.updateData(sampleItem);
			}

			for (Analysis analysis : cancelAnalysisList) {
				analysisDAO.updateData(analysis);
				addExternalResultsToDeleteList(analysis, patient, updatedSample, actionDataSet);
			}

			for (IResultUpdate updater : updaters) {
				updater.postTransactionalCommitUpdate(actionDataSet);
			}

			for (Analysis analysis : addAnalysisList) {
				if (analysis.getId() == null) {
					analysisDAO.insertData(analysis, false); // don't check for duplicates
				} else {
					analysisDAO.updateData(analysis);
				}
			}

			for (SampleItem sampleItem : cancelSampleItemList) {
				sampleItemDAO.updateData(sampleItem);
			}

			if (sampleChanged) {
				sampleDAO.updateData(updatedSample);
			}

			// seems like this is unused
			/*
			 * if (paymentObservation != null) {
			 * paymentObservation.setPatientId(patient.getId());
			 * observationDAO.insertOrUpdateData(paymentObservation); }
			 */

			for (SampleTestCollection sampleTestCollection : addedSamples) {
				sampleItemDAO.insertData(sampleTestCollection.item);

				for (Test test : sampleTestCollection.tests) {
					testDAO.getData(test);

					Analysis analysis = populateAnalysis(sampleTestCollection, test,
							sampleTestCollection.testIdToUserSectionMap.get(test.getId()), sampleAddService);
					analysisDAO.insertData(analysis, false); // false--do not check for duplicates
				}

				if (sampleTestCollection.initialSampleConditionIdList != null) {
					for (ObservationHistory observation : sampleTestCollection.initialSampleConditionIdList) {
						observation.setPatientId(patient.getId());
						observation.setSampleItemId(sampleTestCollection.item.getId());
						observation.setSampleId(sampleTestCollection.item.getSample().getId());
						observation.setSysUserId(getSysUserId(request));
						observationDAO.insertData(observation);
					}
				}
			}

			if (referringPerson != null) {
				if (referringPerson.getId() == null) {
					personDAO.insertData(referringPerson);
				} else {
					personDAO.updateData(referringPerson);
				}
			}

			for (ObservationHistory observation : orderArtifacts.getObservations()) {
				observationDAO.insertOrUpdateData(observation);
			}

			if (orderArtifacts.getSamplePersonRequester() != null) {
				SampleRequester samplePersonRequester = orderArtifacts.getSamplePersonRequester();
				samplePersonRequester.setRequesterId(orderArtifacts.getProviderPerson().getId());
				sampleRequesterDAO.insertOrUpdateData(samplePersonRequester);
			}

			if (orderArtifacts.getProviderOrganization() != null) {
				boolean link = orderArtifacts.getProviderOrganization().getId() == null;
				organizationDAO.insertOrUpdateData(orderArtifacts.getProviderOrganization());
				if (link) {
					orgOrgTypeDAO.linkOrganizationAndType(orderArtifacts.getProviderOrganization(),
							RequesterService.REFERRAL_ORG_TYPE_ID);
				}
			}

			if (orderArtifacts.getSampleOrganizationRequester() != null) {
				if (orderArtifacts.getProviderOrganization() != null) {
					orderArtifacts.getSampleOrganizationRequester()
							.setRequesterId(orderArtifacts.getProviderOrganization().getId());
				}
				sampleRequesterDAO.insertOrUpdateData(orderArtifacts.getSampleOrganizationRequester());
			}

			if (orderArtifacts.getDeletableSampleOrganizationRequester() != null) {
				sampleRequesterDAO.delete(orderArtifacts.getDeletableSampleOrganizationRequester());
			}

			tx.commit();

			request.getSession().setAttribute("lastAccessionNumber", updatedSample.getAccessionNumber());
			request.getSession().setAttribute("lastPatientId", patient.getId());
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			if (lre.getException() instanceof StaleObjectStateException) {
				errors.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			} else {
				lre.printStackTrace();
				errors.reject("errors.UpdateException", "errors.UpdateException");
			}

			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			return findForward(FWD_FAIL, form);

		} finally {
			HibernateUtil.closeSession();
		}

		String sampleEditWritable = (String) request.getSession().getAttribute(IActionConstants.SAMPLE_EDIT_WRITABLE);
		request.setAttribute(IActionConstants.FWD_SUCCESS, true);
		if (GenericValidator.isBlankOrNull(sampleEditWritable)) {
			return findForward(FWD_SUCCESS, form);
		} else {
			Map<String, String> params = new HashMap<>();
			params.put("type", sampleEditWritable);
			return getForwardWithParameters(findForward(FWD_SUCCESS, form), params);
		}
	}

	private void addExternalResultsToDeleteList(Analysis analysis, Patient patient, Sample updatedSample,
			ResultsUpdateDataSet actionDataSet) {
		List<ResultSet> deletedResults = new ArrayList<>();
		if (!GenericValidator.isBlankOrNull(analysis.getSampleItem().getSample().getReferringId())) {
			ResultDAO resultDAO = new ResultDAOImpl();
			List<Result> results = resultDAO.getResultsByAnalysis(analysis);
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

	private List<SampleItem> createSampleItemUpdateList(List<SampleEditItem> existingTests) {
		List<SampleItem> modifyList = new ArrayList<>();

		for (SampleEditItem editItem : existingTests) {
			if (editItem.isSampleItemChanged()) {
				SampleItem sampleItem = sampleItemDAO.getData(editItem.getSampleItemId());
				if (sampleItem != null) {
					String collectionTime = editItem.getCollectionDate();
					if (GenericValidator.isBlankOrNull(collectionTime)) {
						sampleItem.setCollectionDate(null);
					} else {
						collectionTime += " " + (GenericValidator.isBlankOrNull(editItem.getCollectionTime()) ? "00:00"
								: editItem.getCollectionTime());
						sampleItem.setCollectionDate(DateUtil.convertStringDateToTimestamp(collectionTime));
					}
					sampleItem.setSysUserId(getSysUserId(request));
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
			testSection = testSectionDAO.getTestSectionById(userSelectedTestSection); // change
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

	private List<SampleItem> createCancelSampleList(List<SampleEditItem> list, List<Analysis> cancelAnalysisList) {
		List<SampleItem> cancelList = new ArrayList<>();

		boolean cancelTest = false;

		for (SampleEditItem editItem : list) {
			if (editItem.getAccessionNumber() != null) {
				cancelTest = false;
			}
			if (cancelTest && !cancelAnalysisListContainsId(editItem.getAnalysisId(), cancelAnalysisList)) {
				Analysis analysis = getCancelableAnalysis(editItem);
				cancelAnalysisList.add(analysis);
			}

			if (editItem.isRemoveSample()) {
				cancelTest = true;
				SampleItem sampleItem = getCancelableSampleItem(editItem);
				if (sampleItem != null) {
					cancelList.add(sampleItem);
				}
				if (!cancelAnalysisListContainsId(editItem.getAnalysisId(), cancelAnalysisList)) {
					Analysis analysis = getCancelableAnalysis(editItem);
					cancelAnalysisList.add(analysis);
				}
			}
		}

		return cancelList;
	}

	private SampleItem getCancelableSampleItem(SampleEditItem editItem) {
		String sampleItemId = editItem.getSampleItemId();
		SampleItem item = new SampleItem();
		item.setId(sampleItemId);
		sampleItemDAO.getData(item);

		if (item.getId() != null) {
			item.setStatusId(CANCELED_SAMPLE_STATUS_ID);
			item.setSysUserId(getSysUserId(request));
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

	private Errors validateNewAccessionNumber(String accessionNumber, Errors errors) {
		ValidationResults results = AccessionNumberUtil.correctFormat(accessionNumber, false);

		if (results != ValidationResults.SUCCESS) {
			errors.reject("sample.entry.invalid.accession.number.format",
					"sample.entry.invalid.accession.number.format");
		} else if (AccessionNumberUtil.isUsed(accessionNumber)) {
			errors.reject("sample.entry.invalid.accession.number.used", "sample.entry.invalid.accession.number.used");
		}

		return errors;
	}

	private Sample updateAccessionNumberInSample(SampleEditForm form) {
		Sample sample = sampleDAO.getSampleByAccessionNumber(form.getAccessionNumber());

		if (sample != null) {
			sample.setAccessionNumber(form.getNewAccessionNumber());
			sample.setSysUserId(getSysUserId(request));
		}

		return sample;
	}

	private boolean accessionNumberChanged(SampleEditForm form) {
		String newAccessionNumber = form.getNewAccessionNumber();

		return !GenericValidator.isBlankOrNull(newAccessionNumber)
				&& !newAccessionNumber.equals(form.getAccessionNumber());

	}

	private List<Analysis> createRemoveList(List<SampleEditItem> tests) {
		List<Analysis> removeAnalysisList = new ArrayList<>();

		for (SampleEditItem sampleEditItem : tests) {
			if (sampleEditItem.isCanceled()) {
				Analysis analysis = getCancelableAnalysis(sampleEditItem);
				removeAnalysisList.add(analysis);
			}
		}

		return removeAnalysisList;
	}

	private Analysis getCancelableAnalysis(SampleEditItem sampleEditItem) {
		Analysis analysis = new Analysis();
		analysis.setId(sampleEditItem.getAnalysisId());
		analysisDAO.getData(analysis);
		analysis.setSysUserId(getSysUserId(request));
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled));
		return analysis;
	}

	private List<Analysis> createAddAanlysisList(List<SampleEditItem> tests) {
		List<Analysis> addAnalysisList = new ArrayList<>();

		for (SampleEditItem sampleEditItem : tests) {
			if (sampleEditItem.isAdd()) {

				Analysis analysis = newOrExistingCanceledAnalysis(sampleEditItem);

				if (analysis.getId() == null) {
					SampleItem sampleItem = new SampleItem();
					sampleItem.setId(sampleEditItem.getSampleItemId());
					sampleItemDAO.getData(sampleItem);
					analysis.setSampleItem(sampleItem);

					Test test = new Test();
					test.setId(sampleEditItem.getTestId());
					testDAO.getData(test);

					analysis.setTest(test);
					analysis.setRevision("0");
					analysis.setTestSection(test.getTestSection());
					analysis.setEnteredDate(DateUtil.getNowAsTimestamp());
					analysis.setIsReportable(test.getIsReportable());
					analysis.setAnalysisType("MANUAL");
					analysis.setStartedDate(DateUtil.getNowAsSqlDate());
				}

				analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
				analysis.setSysUserId(getSysUserId(request));

				addAnalysisList.add(analysis);
			}
		}

		return addAnalysisList;
	}

	private Analysis newOrExistingCanceledAnalysis(SampleEditItem sampleEditItem) {
		List<Analysis> canceledAnalysis = analysisDAO
				.getAnalysesBySampleItemIdAndStatusId(sampleEditItem.getSampleItemId(), CANCELED_TEST_STATUS_ID);

		for (Analysis analysis : canceledAnalysis) {
			if (sampleEditItem.getTestId().equals(analysis.getTest().getId())) {
				return analysis;
			}
		}

		return new Analysis();
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("redirect:/SampleEdit.do?forward=success", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("sampleEditDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return StringUtil.getContextualKeyForKey("sample.edit.title");
	}

	@Override
	protected String getPageSubtitleKey() {
		return StringUtil.getContextualKeyForKey("sample.edit.subtitle");
	}
}
