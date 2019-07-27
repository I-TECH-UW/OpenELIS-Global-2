package org.openelisglobal.sample.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.requester.service.SampleRequesterService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.SampleAddService.SampleTestCollection;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.TableIdService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.valueholder.TestSection;

@Service
public class SamplePatientEntryServiceImpl implements SamplePatientEntryService {

	private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";

	@Autowired
	private OrganizationAddressService organizationAddressService;
	@Autowired
	private TestSectionService testSectionService;
	@Autowired
	private ElectronicOrderService electronicOrderService;
	@Autowired
	private ObservationHistoryService observationHistoryService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private SampleHumanService sampleHumanService;
	@Autowired
	private SampleItemService sampleItemService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private TestService testService;
	@Autowired
	private SampleRequesterService sampleRequesterService;
	@Autowired
	private OrganizationService organizationService;

	@Transactional
	@Override
	public void persistData(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
			PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request) {
		boolean useInitialSampleCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);

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

		request.getSession().setAttribute("lastAccessionNumber", updateData.getAccessionNumber());
		request.getSession().setAttribute("lastPatientId", updateData.getPatientId());
	}

	private void persistObservations(SamplePatientUpdateData updateData) {

		for (ObservationHistory observation : updateData.getObservations()) {
			observation.setSampleId(updateData.getSample().getId());
			observation.setPatientId(updateData.getPatientId());
			observationHistoryService.insert(observation);
		}
	}

	private void persistOrganizationData(SamplePatientUpdateData updateData) {
		Organization newOrganization = updateData.getNewOrganization();
		if (newOrganization != null) {
			organizationService.insert(newOrganization);
			organizationService.linkOrganizationAndType(newOrganization,
					TableIdService.getInstance().REFERRING_ORG_TYPE_ID);
			if (updateData.getRequesterSite() != null) {
				updateData.getRequesterSite().setRequesterId(newOrganization.getId());
			}

			for (OrganizationAddress address : updateData.getOrgAddressExtra()) {
				address.setOrganizationId(newOrganization.getId());
				organizationAddressService.insert(address);
			}
		}

		if (updateData.getCurrentOrganization() != null) {
			organizationService.update(updateData.getCurrentOrganization());
		}

	}

	private void persistProviderData(SamplePatientUpdateData updateData) {
		if (updateData.getProviderPerson() != null && updateData.getProvider() != null) {

			personService.insert(updateData.getProviderPerson());
			updateData.getProvider().setPerson(updateData.getProviderPerson());

			providerService.insert(updateData.getProvider());
		}
	}

	private void persistSampleData(SamplePatientUpdateData updateData) {
		String analysisRevision = SystemConfiguration.getInstance().getAnalysisDefaultRevision();

		sampleService.insertDataWithAccessionNumber(updateData.getSample());

		// if (!GenericValidator.isBlankOrNull(projectId)) {
		// persistSampleProject();
		// }

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {

			sampleItemService.insert(sampleTestCollection.item);

			for (Test test : sampleTestCollection.tests) {
				test = testService.get(test.getId());

				Analysis analysis = populateAnalysis(analysisRevision, sampleTestCollection, test,
						sampleTestCollection.testIdToUserSectionMap.get(test.getId()),
						sampleTestCollection.testIdToUserSampleTypeMap.get(test.getId()), updateData);
				analysisService.insert(analysis);
			}

		}

		updateData.buildSampleHuman();

		sampleHumanService.insert(updateData.getSampleHuman());

		if (updateData.getElectronicOrder() != null) {
			electronicOrderService.update(updateData.getElectronicOrder());
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
		if (updateData.getProviderPerson() != null
				&& !org.apache.commons.validator.GenericValidator.isBlankOrNull(updateData.getProviderPerson().getId())) {
			SampleRequester sampleRequester = new SampleRequester();
			sampleRequester.setRequesterId(updateData.getProviderPerson().getId());
			sampleRequester.setRequesterTypeId(TableIdService.getInstance().PROVIDER_REQUESTER_TYPE_ID);
			sampleRequester.setSampleId(Long.parseLong(updateData.getSample().getId()));
			sampleRequester.setSysUserId(updateData.getCurrentUserId());
			sampleRequesterService.insert(sampleRequester);
		}

		if (updateData.getRequesterSite() != null) {
			updateData.getRequesterSite().setSampleId(Long.parseLong(updateData.getSample().getId()));
			if (updateData.getNewOrganization() != null) {
				updateData.getRequesterSite().setRequesterId(updateData.getNewOrganization().getId());
			}
			sampleRequesterService.insert(updateData.getRequesterSite());
		}
	}

	private void persistInitialSampleConditions(SamplePatientUpdateData updateData) {

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
			List<ObservationHistory> initialConditions = sampleTestCollection.initialSampleConditionIdList;

			if (initialConditions != null) {
				for (ObservationHistory observation : initialConditions) {
					observation.setSampleId(sampleTestCollection.item.getSample().getId());
					observation.setSampleItemId(sampleTestCollection.item.getId());
					observation.setPatientId(updateData.getPatientId());
					observation.setSysUserId(updateData.getCurrentUserId());
					observationHistoryService.insert(observation);
				}
			}
		}
	}

	private Analysis populateAnalysis(String analysisRevision, SampleTestCollection sampleTestCollection, Test test,
			String userSelectedTestSection, String sampleTypeName, SamplePatientUpdateData updateData) {
		java.sql.Date collectionDateTime = DateUtil.convertStringDateTimeToSqlDate(sampleTestCollection.collectionDate);
		TestSection testSection = test.getTestSection();
		if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(userSelectedTestSection)) {
			testSection = testSectionService.get(userSelectedTestSection);
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
		if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(sampleTypeName)) {
			analysis.setSampleTypeName(sampleTypeName);
		}
		analysis.setTestSection(testSection);
		return analysis;
	}
}
