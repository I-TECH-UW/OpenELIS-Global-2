package spring.mine.sample.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;
import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.daoimpl.OrganizationAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.SampleAddService.SampleTestCollection;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.patient.action.IPatientUpdate;
import us.mn.state.health.lims.patient.action.PatientManagementUpdateAction;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.action.util.SamplePatientUpdateData;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class SamplePatientEntryController extends BaseSampleEntryController {

	private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";
	private OrganizationDAO orgDAO = new OrganizationDAOImpl();
	private OrganizationAddressDAO orgAddressDAO = new OrganizationAddressDAOImpl();
	private OrganizationOrganizationTypeDAO orgOrgTypeDAO = new OrganizationOrganizationTypeDAOImpl();
	private TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
	private ElectronicOrderDAO electronicOrderDAO = new ElectronicOrderDAOImpl();

	@RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.GET)
	public ModelAndView showSamplePatientEntry(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		SamplePatientEntryForm form = new SamplePatientEntryForm();
		form.setFormAction("");
		Errors errors = new BaseErrors();

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);
		SampleOrderService sampleOrderService = new SampleOrderService();
		PropertyUtils.setProperty(form, "sampleOrderItems", sampleOrderService.getSampleOrderItem());
		PropertyUtils.setProperty(form, "patientProperties", new PatientManagementInfo());
		PropertyUtils.setProperty(form, "patientSearch", new PatientSearch());
		PropertyUtils.setProperty(form, "sampleTypes", DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
		PropertyUtils.setProperty(form, "testSectionList", DisplayListService.getList(ListType.TEST_SECTION));
		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText());

		for (Object program : form.getSampleOrderItems().getProgramList()) {
			// System.out.println(((IdValuePair) program).getValue());
		}

		addProjectList(form);

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(form, "initialSampleConditionList",
					DisplayListService.getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		return findForward(forward, form);
	}

	@RequestMapping(value = "/SamplePatientEntrySave", method = RequestMethod.POST)
	public @ResponseBody ModelAndView showSamplePatientEntrySave(@ModelAttribute("form") SamplePatientEntryForm form,
			BindingResult result, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS_INSERT;
		Errors errors = new BaseErrors();

		SamplePatientUpdateData updateData = new SamplePatientUpdateData(getSysUserId(request));

		boolean useInitialSampleCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);
		PatientManagementInfo patientInfo = (PatientManagementInfo) PropertyUtils.getProperty(form,
				"patientProperties");
		SampleOrderItem sampleOrder = (SampleOrderItem) PropertyUtils.getProperty(form, "sampleOrderItems");

		boolean trackPayments = ConfigurationProperties.getInstance()
				.isPropertyValueEqual(Property.TRACK_PATIENT_PAYMENT, "true");

		String receivedDateForDisplay = sampleOrder.getReceivedDateForDisplay();

		if (!GenericValidator.isBlankOrNull(sampleOrder.getReceivedTime())) {
			receivedDateForDisplay += " " + sampleOrder.getReceivedTime();
		} else {
			receivedDateForDisplay += " 00:00";
		}

		updateData.setCollectionDateFromRecieveDateIfNeeded(receivedDateForDisplay);
		updateData.initializeRequester(sampleOrder);

		IPatientUpdate patientUpdate = new PatientManagementUpdateAction();
		testAndInitializePatientForSaving(request, patientInfo, patientUpdate, updateData);

		updateData.setAccessionNumber(sampleOrder.getLabNo());
		updateData.initProvider(sampleOrder);
		updateData.initSampleData(form.getSampleXML(), receivedDateForDisplay, trackPayments, sampleOrder);
		updateData.validateSample(result);

		if (result.hasErrors()) {
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, result);
			setSuccessFlag(request, "success");
			return findForward(FWD_FAIL_INSERT, form);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			persistOrganizationData(updateData);

			if (updateData.isSavePatient()) {
				patientUpdate.persistPatientData(patientInfo);
			}

			updateData.setPatientId(patientUpdate.getPatientId(form));

			persistProviderData(updateData);
			persistSampleData(updateData);
			persistRequesterData(updateData);
			if (useInitialSampleCondition) {
				persistInitialSampleConditions(updateData);
			}

			persistObservations(updateData);

			tx.commit();

			request.getSession().setAttribute("lastAccessionNumber", updateData.getAccessionNumber());
			request.getSession().setAttribute("lastPatientId", updateData.getPatientId());

		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			// ActionError error;
			if (lre.getException() instanceof StaleObjectStateException) {
				// error = new ActionError("errors.OptimisticLockException", null, null);
				result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			} else {
				lre.printStackTrace();
				// error = new ActionError("errors.UpdateException", null, null);
				result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			}
			System.out.println(result);

			// errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(result);
			request.setAttribute(Globals.ERROR_KEY, result);
			request.setAttribute(ALLOW_EDITS_KEY, "false");
			setSuccessFlag(request, "success");
			return findForward(FWD_FAIL_INSERT, form);

		} finally {
			HibernateUtil.closeSession();
		}

		setSuccessFlag(request, "success");

		return findForward(forward, form);
	}

	private void persistObservations(SamplePatientUpdateData updateData) {

		ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
		for (ObservationHistory observation : updateData.getObservations()) {
			observation.setSampleId(updateData.getSample().getId());
			observation.setPatientId(updateData.getPatientId());
			observationDAO.insertData(observation);
		}
	}

	private void testAndInitializePatientForSaving(HttpServletRequest request, PatientManagementInfo patientInfo,
			IPatientUpdate patientUpdate, SamplePatientUpdateData updateData)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		patientUpdate.setPatientUpdateStatus(patientInfo);
		updateData.setSavePatient(
				patientUpdate.getPatientUpdateStatus() != PatientManagementUpdateAction.PatientUpdateStatus.NO_ACTION);

		if (updateData.isSavePatient()) {
			updateData.setPatientErrors(patientUpdate.preparePatientData(request, patientInfo));
		} else {
			updateData.setPatientErrors(new BaseErrors());
		}
	}

	private void persistOrganizationData(SamplePatientUpdateData updateData) {
		Organization newOrganization = updateData.getNewOrganization();
		if (newOrganization != null) {
			orgDAO.insertData(newOrganization);
			orgOrgTypeDAO.linkOrganizationAndType(newOrganization, TableIdService.REFERRING_ORG_TYPE_ID);
			if (updateData.getRequesterSite() != null) {
				updateData.getRequesterSite().setRequesterId(newOrganization.getId());
			}

			for (OrganizationAddress address : updateData.getOrgAddressExtra()) {
				address.setOrganizationId(newOrganization.getId());
				orgAddressDAO.insert(address);
			}
		}

		if (updateData.getCurrentOrganization() != null) {
			orgDAO.updateData(updateData.getCurrentOrganization());
		}

	}

	private void persistProviderData(SamplePatientUpdateData updateData) {
		if (updateData.getProviderPerson() != null && updateData.getProvider() != null) {
			PersonDAO personDAO = new PersonDAOImpl();
			ProviderDAO providerDAO = new ProviderDAOImpl();

			personDAO.insertData(updateData.getProviderPerson());
			updateData.getProvider().setPerson(updateData.getProviderPerson());

			providerDAO.insertData(updateData.getProvider());
		}
	}

	private void persistSampleData(SamplePatientUpdateData updateData) {
		SampleDAO sampleDAO = new SampleDAOImpl();
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		TestDAO testDAO = new TestDAOImpl();
		String analysisRevision = SystemConfiguration.getInstance().getAnalysisDefaultRevision();

		sampleDAO.insertDataWithAccessionNumber(updateData.getSample());

		// if (!GenericValidator.isBlankOrNull(projectId)) {
		// persistSampleProject();
		// }

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {

			sampleItemDAO.insertData(sampleTestCollection.item);

			for (Test test : sampleTestCollection.tests) {
				testDAO.getData(test);

				Analysis analysis = populateAnalysis(analysisRevision, sampleTestCollection, test,
						sampleTestCollection.testIdToUserSectionMap.get(test.getId()),
						sampleTestCollection.testIdToUserSampleTypeMap.get(test.getId()), updateData);
				analysisDAO.insertData(analysis, false); // false--do not check for duplicates
			}

		}

		updateData.buildSampleHuman();

		sampleHumanDAO.insertData(updateData.getSampleHuman());

		if (updateData.getElectronicOrder() != null) {
			electronicOrderDAO.updateData(updateData.getElectronicOrder());
		}
	}

	/*
	 * private void persistSampleProject() throws LIMSRuntimeException {
	 * SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl(); ProjectDAO
	 * projectDAO = new ProjectDAOImpl(); Project project = new Project(); //
	 * project.setId(projectId); projectDAO.getData(project);
	 *
	 * SampleProject sampleProject = new SampleProject();
	 * sampleProject.setProject(project); sampleProject.setSample(sample);
	 * sampleProject.setSysUserId(getSysUserId(request));
	 * sampleProjectDAO.insertData(sampleProject); }
	 */

	private void persistRequesterData(SamplePatientUpdateData updateData) {
		SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
		if (updateData.getProviderPerson() != null
				&& !GenericValidator.isBlankOrNull(updateData.getProviderPerson().getId())) {
			SampleRequester sampleRequester = new SampleRequester();
			sampleRequester.setRequesterId(updateData.getProviderPerson().getId());
			sampleRequester.setRequesterTypeId(TableIdService.PROVIDER_REQUESTER_TYPE_ID);
			sampleRequester.setSampleId(Long.parseLong(updateData.getSample().getId()));
			sampleRequester.setSysUserId(updateData.getCurrentUserId());
			sampleRequesterDAO.insertData(sampleRequester);
		}

		if (updateData.getRequesterSite() != null) {
			updateData.getRequesterSite().setSampleId(Long.parseLong(updateData.getSample().getId()));
			if (updateData.getNewOrganization() != null) {
				updateData.getRequesterSite().setRequesterId(updateData.getNewOrganization().getId());
			}
			sampleRequesterDAO.insertData(updateData.getRequesterSite());
		}
	}

	private void persistInitialSampleConditions(SamplePatientUpdateData updateData) {
		ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
			List<ObservationHistory> initialConditions = sampleTestCollection.initialSampleConditionIdList;

			if (initialConditions != null) {
				for (ObservationHistory observation : initialConditions) {
					observation.setSampleId(sampleTestCollection.item.getSample().getId());
					observation.setSampleItemId(sampleTestCollection.item.getId());
					observation.setPatientId(updateData.getPatientId());
					observation.setSysUserId(updateData.getCurrentUserId());
					ohDAO.insertData(observation);
				}
			}
		}
	}

	private Analysis populateAnalysis(String analysisRevision, SampleTestCollection sampleTestCollection, Test test,
			String userSelectedTestSection, String sampleTypeName, SamplePatientUpdateData updateData) {
		java.sql.Date collectionDateTime = DateUtil.convertStringDateTimeToSqlDate(sampleTestCollection.collectionDate);
		TestSection testSection = test.getTestSection();
		if (!GenericValidator.isBlankOrNull(userSelectedTestSection)) {
			testSection = testSectionDAO.getTestSectionById(userSelectedTestSection);
		}

		Panel panel = updateData.getSampleAddService().getPanelForTest(test);

		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setPanel(panel);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setSampleItem(sampleTestCollection.item);
		analysis.setSysUserId(sampleTestCollection.item.getSysUserId());
		analysis.setRevision(analysisRevision);
		analysis.setStartedDate(collectionDateTime == null ? DateUtil.getNowAsSqlDate() : collectionDateTime);
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
		if (!GenericValidator.isBlankOrNull(sampleTypeName)) {
			analysis.setSampleTypeName(sampleTypeName);
		}
		analysis.setTestSection(testSection);
		return analysis;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "samplePatientEntryDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SamplePatientEntry.do?forward=success";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "samplePatientEntryDefinition";
		} else {
			return "PageNotFound";
		}
	}
}
