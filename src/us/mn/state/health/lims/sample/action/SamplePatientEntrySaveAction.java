/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.sample.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.address.dao.OrganizationAddressDAO;
import us.mn.state.health.lims.address.daoimpl.OrganizationAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.SampleAddService.SampleTestCollection;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.services.registration.ResultUpdateRegister;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
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
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.action.util.ResultsUpdateDataSet;
import us.mn.state.health.lims.result.valueholder.Result;
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

public class SamplePatientEntrySaveAction extends BaseAction {

	private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";
	private OrganizationDAO orgDAO = new OrganizationDAOImpl();
	private OrganizationAddressDAO orgAddressDAO = new OrganizationAddressDAOImpl();
	private OrganizationOrganizationTypeDAO orgOrgTypeDAO = new OrganizationOrganizationTypeDAOImpl();
	private TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
	private ElectronicOrderDAO electronicOrderDAO = new ElectronicOrderDAOImpl();
	private PatientDAO patientDAO = new PatientDAOImpl();

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = FWD_SUCCESS;

        SamplePatientUpdateData updateData = new SamplePatientUpdateData( getSysUserId(request)) ;

		boolean useInitialSampleCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);
		BaseActionForm dynaForm = (BaseActionForm) form;
		PatientManagementInfo patientInfo = (PatientManagementInfo ) dynaForm.get("patientProperties");
        SampleOrderItem sampleOrder = (SampleOrderItem)dynaForm.get("sampleOrderItems");

        ActionMessages errors = new ActionMessages();

        boolean trackPayments = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.TRACK_PATIENT_PAYMENT, "true");

		String receivedDateForDisplay =  sampleOrder.getReceivedDateForDisplay();

		if (!GenericValidator.isBlankOrNull(sampleOrder.getReceivedTime())) {
			receivedDateForDisplay += " " + sampleOrder.getReceivedTime();
		}else{
			receivedDateForDisplay += " 00:00";
		}

        updateData.setCollectionDateFromRecieveDateIfNeeded( receivedDateForDisplay );
        updateData.initializeRequester( sampleOrder );

		IPatientUpdate patientUpdate = new PatientManagementUpdateAction();
		testAndInitializePatientForSaving(mapping, request, patientInfo, patientUpdate, updateData);

        updateData.setAccessionNumber( sampleOrder.getLabNo() );
		updateData.initProvider( sampleOrder );
		updateData.initSampleData( dynaForm.getString( "sampleXML" ), receivedDateForDisplay, trackPayments, sampleOrder );
		updateData.validateSample( errors );

		if (errors.size() > 0) {
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			return mapping.findForward(FWD_FAIL);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			persistOrganizationData(updateData);
			
			if ( updateData.isSavePatient()) {
				patientUpdate.persistPatientData(patientInfo);
			}

            updateData.setPatientId( patientUpdate.getPatientId(dynaForm) );

			persistProviderData( updateData );
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

			ActionError error;
			if (lre.getException() instanceof StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null, null);
			} else {
				lre.printStackTrace();
				error = new ActionError("errors.UpdateException", null, null);
			}

			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			request.setAttribute(ALLOW_EDITS_KEY, "false");
			return mapping.findForward(FWD_FAIL);

		} finally {
			HibernateUtil.closeSession();
		}

		setSuccessFlag(request, forward);

		return mapping.findForward(forward);
	}

    private void persistObservations(SamplePatientUpdateData updateData) {

        ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
		for (ObservationHistory observation : updateData.getObservations()) {
			observation.setSampleId(updateData.getSample().getId());
			observation.setPatientId(updateData.getPatientId());
			observationDAO.insertData( observation );
		}
	}

	private void testAndInitializePatientForSaving( ActionMapping mapping, HttpServletRequest request, PatientManagementInfo patientInfo,
                                                    IPatientUpdate patientUpdate, SamplePatientUpdateData updateData ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		patientUpdate.setPatientUpdateStatus(patientInfo);
		updateData.setSavePatient(  patientUpdate.getPatientUpdateStatus() != PatientManagementUpdateAction.PatientUpdateStatus.NO_ACTION );

		if (updateData.isSavePatient()) {
           updateData.setPatientErrors( patientUpdate.preparePatientData(mapping, request, patientInfo) );
		} else {
            updateData.setPatientErrors(new ActionMessages());
		}
	}

	private void persistOrganizationData( SamplePatientUpdateData updateData ) {
        Organization newOrganization = updateData.getNewOrganization();
		if (newOrganization != null) {
			orgDAO.insertData(newOrganization);
			orgOrgTypeDAO.linkOrganizationAndType(newOrganization, TableIdService.REFERRING_ORG_TYPE_ID);
			if (updateData.getRequesterSite() != null) {
                updateData.getRequesterSite().setRequesterId( newOrganization.getId() );
			}

			for (OrganizationAddress address : updateData.getOrgAddressExtra()) {
				address.setOrganizationId(newOrganization.getId());
				orgAddressDAO.insert(address);
			}
		}
		
		if( updateData.getCurrentOrganization() != null){
			orgDAO.updateData(updateData.getCurrentOrganization());
		}

	}

	private void persistProviderData( SamplePatientUpdateData updateData ) {
		if (updateData.getProviderPerson() != null && updateData.getProvider()!= null) {
			PersonDAO personDAO = new PersonDAOImpl();
			ProviderDAO providerDAO = new ProviderDAOImpl();

			personDAO.insertData(updateData.getProviderPerson());
            updateData.getProvider().setPerson( updateData.getProviderPerson() );

			providerDAO.insertData(updateData.getProvider());
		}
	}

	private void persistSampleData( SamplePatientUpdateData updateData ) {
		SampleDAO sampleDAO = new SampleDAOImpl();
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		TestDAO testDAO = new TestDAOImpl();
		String analysisRevision = SystemConfiguration.getInstance().getAnalysisDefaultRevision();

		sampleDAO.insertDataWithAccessionNumber(updateData.getSample());

	//	if (!GenericValidator.isBlankOrNull(projectId)) {
	//		persistSampleProject();
	//	}

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {

			sampleItemDAO.insertData(sampleTestCollection.item);

			for (Test test : sampleTestCollection.tests) {
				testDAO.getData(test);

				Analysis analysis = populateAnalysis(analysisRevision,
                                                     sampleTestCollection,
                                                     test,
                                                     sampleTestCollection.testIdToUserSectionMap.get(test.getId()),
                                                     sampleTestCollection.testIdToUserSampleTypeMap.get(test.getId()),
                                                     updateData);
				analysisDAO.insertData(analysis, false); // false--do not check for duplicates
				if(updateData.getElectronicOrder() != null){
					List<IResultUpdate> updaters = ResultUpdateRegister.getRegisteredUpdaters();
					ResultsUpdateDataSet actionDataSet = new ResultsUpdateDataSet( currentUserId );
		            
					List<ResultSet> blankResults = new ArrayList<ResultSet>();
					Result result = new Result();
					result.setResultEvent(Event.SPEC_RECEIVED);
			    	result.setAnalysis(analysis);
			    	result.setMinNormal((double) 0);
			    	result.setMaxNormal((double) 0);
			    	result.setValue("empty");
					blankResults.add(new ResultSet(result, null, null, patientDAO.getData(updateData.getPatientId()), updateData.getSample(), null, false));
					actionDataSet.setModifiedResults(blankResults);
		    		for(IResultUpdate updater : updaters){
		    			updater.postTransactionalCommitUpdate(actionDataSet);
		    		}
				}
			}

		}

        updateData.buildSampleHuman();

		sampleHumanDAO.insertData(updateData.getSampleHuman());

		if(updateData.getElectronicOrder() != null){
			electronicOrderDAO.updateData(updateData.getElectronicOrder());			
		}
	}

/*	private void persistSampleProject() throws LIMSRuntimeException {
		SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		Project project = new Project();
	//	project.setId(projectId);
		projectDAO.getData(project);

		SampleProject sampleProject = new SampleProject();
		sampleProject.setProject(project);
		sampleProject.setSample(sample);
		sampleProject.setSysUserId(currentUserId);
		sampleProjectDAO.insertData(sampleProject);
	}*/

	private void persistRequesterData( SamplePatientUpdateData updateData ) {
		SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
		if (updateData.getProviderPerson() != null && !GenericValidator.isBlankOrNull(updateData.getProviderPerson().getId())) {
			SampleRequester sampleRequester = new SampleRequester();
			sampleRequester.setRequesterId(updateData.getProviderPerson().getId());
			sampleRequester.setRequesterTypeId(TableIdService.PROVIDER_REQUESTER_TYPE_ID);
			sampleRequester.setSampleId(Long.parseLong( updateData.getSample().getId()));
			sampleRequester.setSysUserId(updateData.getCurrentUserId());
			sampleRequesterDAO.insertData(sampleRequester);
		}

		if (updateData.getRequesterSite() != null) {
            updateData.getRequesterSite().setSampleId( Long.parseLong( updateData.getSample().getId() ) );
			if( updateData.getNewOrganization() != null){
                updateData.getRequesterSite().setRequesterId( updateData.getNewOrganization().getId() );
			}
			sampleRequesterDAO.insertData(updateData.getRequesterSite());
		}
	}

	private void persistInitialSampleConditions( SamplePatientUpdateData updateData ) {
		ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();

		for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
			List<ObservationHistory> initialConditions = sampleTestCollection.initialSampleConditionIdList;

			if (initialConditions != null) {
				for (ObservationHistory observation : initialConditions) {
					observation.setSampleId(sampleTestCollection.item.getSample().getId());
					observation.setSampleItemId(sampleTestCollection.item.getId());
					observation.setPatientId(updateData.getPatientId());
					observation.setSysUserId( updateData.getCurrentUserId());
					ohDAO.insertData(observation);
				}
			}
		}
	}

	private Analysis populateAnalysis( String analysisRevision, SampleTestCollection sampleTestCollection, Test test, String userSelectedTestSection, String sampleTypeName, SamplePatientUpdateData updateData ) {
	    java.sql.Date collectionDateTime = DateUtil.convertStringDateTimeToSqlDate(sampleTestCollection.collectionDate);
	    TestSection testSection = test.getTestSection();
		if( !GenericValidator.isBlankOrNull(userSelectedTestSection)){
			testSection = testSectionDAO.getTestSectionById( userSelectedTestSection);
		}
		
		Panel panel = updateData.getSampleAddService().getPanelForTest( test );
		
		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setPanel(panel);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setSampleItem(sampleTestCollection.item);
		analysis.setSysUserId(sampleTestCollection.item.getSysUserId());
		analysis.setRevision(analysisRevision);
		analysis.setStartedDate(collectionDateTime == null ? DateUtil.getNowAsSqlDate() : collectionDateTime );
		analysis.setStatusId(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted));
        if( !GenericValidator.isBlankOrNull( sampleTypeName )){
            analysis.setSampleTypeName( sampleTypeName );
        }
		analysis.setTestSection(testSection);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		return analysis;
	}

	@Override
	protected String getPageTitleKey() {
		return "sample.entry.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "sample.entry.title";
	}
}
