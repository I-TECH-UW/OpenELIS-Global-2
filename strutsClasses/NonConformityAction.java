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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package org.openelisglobal.qaevent.action.retroCI;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.NoteService;
import org.openelisglobal.common.services.PatientService;
import org.openelisglobal.person.service.PersonServiceImpl;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.common.services.QAService.QAObservationType;
import org.openelisglobal.common.services.TableIdService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.observationhistory.dao.ObservationHistoryDAO;
import org.openelisglobal.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.organization.dao.OrganizationDAO;
import org.openelisglobal.organization.daoimpl.OrganizationDAOImpl;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.util.PatientUtil;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.dao.PersonDAO;
import org.openelisglobal.person.daoimpl.PersonDAOImpl;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.project.dao.ProjectDAO;
import org.openelisglobal.project.daoimpl.ProjectDAOImpl;
import org.openelisglobal.project.valueholder.Project;
import org.openelisglobal.provider.dao.ProviderDAO;
import org.openelisglobal.provider.daoimpl.ProviderDAOImpl;
import org.openelisglobal.provider.valueholder.Provider;
import org.openelisglobal.qaevent.valueholder.retroCI.QaEventItem;
import org.openelisglobal.requester.dao.SampleRequesterDAO;
import org.openelisglobal.requester.daoimpl.SampleRequesterDAOImpl;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.sample.dao.SampleDAO;
import org.openelisglobal.sample.daoimpl.SampleDAOImpl;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.dao.SampleHumanDAO;
import org.openelisglobal.samplehuman.daoimpl.SampleHumanDAOImpl;
import org.openelisglobal.samplehuman.valueholder.SampleHuman;
import org.openelisglobal.sampleitem.dao.SampleItemDAO;
import org.openelisglobal.sampleitem.daoimpl.SampleItemDAOImpl;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.sampleproject.dao.SampleProjectDAO;
import org.openelisglobal.sampleproject.daoimpl.SampleProjectDAOImpl;
import org.openelisglobal.sampleproject.valueholder.SampleProject;
import org.openelisglobal.sampleqaevent.dao.SampleQaEventDAO;
import org.openelisglobal.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.test.daoimpl.TestSectionDAOImpl;
import org.openelisglobal.test.valueholder.TestSection;
import org.openelisglobal.typeofsample.dao.TypeOfSampleDAO;
import org.openelisglobal.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

public class NonConformityAction extends BaseAction{

	private PatientService patientService;
	private List<ObservationHistory> observationHistoryList;
	private List<SampleQaEvent> sampleQAEventList;

	private static final String QA_NOTE_SUBJECT = "QaEvent Note";

	private static SampleDAO sampleDAO = new SampleDAOImpl();
	private static SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	private static TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
	private static SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static PersonDAO personDAO = new PersonDAOImpl();
	private static ProviderDAO providerDAO = new ProviderDAOImpl();
	private static OrganizationDAO orgDAO = new OrganizationDAOImpl();

	private Boolean readOnly = Boolean.FALSE;
	private boolean sampleFound;
	private Sample sample;
	private boolean useSiteList;

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		useSiteList = FormFields.getInstance().useField(Field.NON_CONFORMITY_SITE_LIST);

		BaseActionForm dynaForm = (BaseActionForm)form;

		dynaForm.initialize(mapping);

		readOnly = Boolean.FALSE;

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		String labNumber = request.getParameter("labNo");

		if(!GenericValidator.isBlankOrNull(labNumber)){

			sample = getSampleForLabNumber(labNumber);

			sampleFound = !(sample == null || GenericValidator.isBlankOrNull(sample.getId()));

			PropertyUtils.setProperty(dynaForm, "labNo", labNumber);
			Date today = Calendar.getInstance().getTime();
			PropertyUtils.setProperty(dynaForm, "date", DateUtil.formatDateAsText(today));
			if(FormFields.getInstance().useField(Field.QATimeWithDate)){
				PropertyUtils.setProperty(dynaForm, "time", DateUtil.nowTimeAsText());
			}

			if(sampleFound){
				createForExistingSample(dynaForm);
			}

			setProjectList(dynaForm);

			PropertyUtils.setProperty(dynaForm, "sampleItemsTypeOfSampleIds", getSampleTypeOfSamplesString());
			PropertyUtils.setProperty(dynaForm, "sections", createSectionList());
			PropertyUtils.setProperty(dynaForm, "qaEventTypes", DisplayListService.getInstance().getList(ListType.QA_EVENTS));
			PropertyUtils.setProperty(dynaForm, "qaEvents", getSampleQaEventItems(sample));

            PropertyUtils.setProperty( dynaForm, "typeOfSamples", DisplayListService.getInstance().getList( ListType.SAMPLE_TYPE_ACTIVE) );

			PropertyUtils.setProperty(dynaForm, "readOnly", readOnly);
			PropertyUtils.setProperty(dynaForm, "siteList", DisplayListService.getInstance().getFreshList(ListType.SAMPLE_PATIENT_REFERRING_CLINIC));
			Provider provider = getProvider();
			if(provider != null){
				PropertyUtils.setProperty(dynaForm, "providerNew", Boolean.FALSE.toString());
				Person providerPerson = getProviderPerson(provider);
				if(providerPerson != null && !providerPerson.getId().equals(PatientUtil.getUnknownPerson().getId())){
					PersonService personService = new PersonService(providerPerson);
					PropertyUtils.setProperty(dynaForm, "providerFirstName", personService.getFirstName());
					PropertyUtils.setProperty(dynaForm, "providerLastName", personService.getLastName());
					PropertyUtils.setProperty(dynaForm, "providerWorkPhone", personService.getPhone());
					Map<String, String> addressComponents = personService.getAddressComponents();
					
					PropertyUtils.setProperty(dynaForm, "providerStreetAddress", addressComponents.get("Street"));
					PropertyUtils.setProperty(dynaForm, "providerCity", addressComponents.get("village"));
					PropertyUtils.setProperty(dynaForm, "providerCommune", addressComponents.get("commune"));
					PropertyUtils.setProperty(dynaForm, "providerDepartment", addressComponents.get("department"));
				}
			}else{
				PropertyUtils.setProperty(dynaForm, "providerNew", Boolean.TRUE.toString());
				PropertyUtils.setProperty(dynaForm, "requesterSampleID", "");
				PropertyUtils.setProperty(dynaForm, "providerFirstName", "");
				PropertyUtils.setProperty(dynaForm, "providerLastName", "");
				PropertyUtils.setProperty(dynaForm, "providerWorkPhone", "");
			}
			
			PropertyUtils.setProperty(dynaForm, "departments", DisplayListService.getInstance().getList(ListType.HAITI_DEPARTMENTS));
		}

		return mapping.findForward(FWD_SUCCESS);
	}

	private void createForExistingSample(BaseActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		getPatient(sample);
		getObservationHistory(sample);
		getSampleQaEvents(sample);
		PropertyUtils.setProperty(dynaForm, "sampleId", sample.getId());
		PropertyUtils.setProperty(dynaForm, "patientId", patientService.getPatientId());
		
		Project project = getProjectForSample(sample);
		if(project != null){
			PropertyUtils.setProperty(dynaForm, "projectId", project.getId());
			PropertyUtils.setProperty(dynaForm, "project", project.getLocalizedName());
		}

		String subjectNo = patientService.getSubjectNumber();
		if(!GenericValidator.isBlankOrNull(subjectNo)){
			PropertyUtils.setProperty(dynaForm, "subjectNew", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "subjectNo", subjectNo);
		}

		String STNo = patientService.getSTNumber();
		if(!GenericValidator.isBlankOrNull(STNo)){
			PropertyUtils.setProperty(dynaForm, "newSTNumber", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "STNumber", STNo);
		}

		
		String nationalId = patientService.getNationalId();
		if(!GenericValidator.isBlankOrNull(nationalId)){
			PropertyUtils.setProperty(dynaForm, "nationalIdNew", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "nationalId", nationalId);
		}
		
		ObservationHistory doctorObservation = getRefererObservation(sample);
		if(doctorObservation != null){
			PropertyUtils.setProperty(dynaForm, "doctorNew", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "doctor", doctorObservation.getValue());
		}

		if(useSiteList){
			PropertyUtils.setProperty(dynaForm, "serviceNew", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "service", getSampleRequesterOrganizationName());
		}else{
			ObservationHistory serviceObservation = getServiceObservation(sample);
			if(serviceObservation != null){
				PropertyUtils.setProperty(dynaForm, "serviceNew", Boolean.FALSE);
				PropertyUtils.setProperty(dynaForm, "service", serviceObservation.getValue());
			}
		}

		PropertyUtils.setProperty(dynaForm, "comment", getNoteForSample(sample));

		PropertyUtils.setProperty(dynaForm, "requesterSampleID", sample.getReferringId());
	}

	/**
	 * @return
	 */
	private String getSampleRequesterOrganizationName(){
		SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
		List<SampleRequester> sampleRequestors = sampleRequesterDAO.getRequestersForSampleId(sample.getId());
		if(sampleRequestors.size() == 0){
			return null;
		}
		long typeID = TableIdService.getInstance().ORGANIZATION_REQUESTER_TYPE_ID;
		for(SampleRequester sampleRequester : sampleRequestors){
			if(sampleRequester.getRequesterTypeId() == typeID){
				String orgId = String.valueOf(sampleRequester.getRequesterId());
				Organization org = orgDAO.getOrganizationById(orgId);

				if(org != null){
					String orgName = org.getOrganizationName();
					orgName += GenericValidator.isBlankOrNull(org.getShortName()) ? "" : ("-" + org.getShortName());
					return orgName;
				}
			}
		}
		return null;
	}

	/**
	 * @param provider
	 * @return
	 */
	private Person getProviderPerson(Provider provider){
		if(provider == null){
			return null;
		}
		Person providerPerson = provider.getPerson();
		personDAO.getData(providerPerson);
		return providerPerson;
	}

	private Provider getProvider(){
		if(sample == null){
			return null;
		}
		SampleHuman sampleHuman = getSampleHuman();
		Provider provider = new Provider();
		String id = sampleHuman.getProviderId();
		if(id == null){
			return null;
		}
		provider.setId(id);
		providerDAO.getData(provider);
		return provider;

	}

	/**
	 * @return
	 */
	private SampleHuman getSampleHuman(){
		SampleHuman sampleHuman = new SampleHuman();
		sampleHuman.setSampleId(sample.getId());
		sampleHumanDAO.getDataBySample(sampleHuman);
		return sampleHuman;
	}

	/**
	 * @param sample
	 * @return
	 */
	private List<QaEventItem> getSampleQaEventItems(Sample sample){
		List<QaEventItem> qaEventItems = new ArrayList<QaEventItem>();
		if(sample != null){
			getSampleQaEvents(sample);
			for(SampleQaEvent event : sampleQAEventList){
				QAService qa = new QAService(event);
				QaEventItem item = new QaEventItem();
				item.setId(qa.getEventId());
				item.setQaEvent(qa.getQAEvent().getId());
				SampleItem sampleItem = qa.getSampleItem();
                // -1 is the index for "all samples"
				item.setSampleType((sampleItem == null) ? "-1" : sampleItem.getTypeOfSampleId());
				item.setSection(qa.getObservationValue( QAObservationType.SECTION ));
				item.setAuthorizer(qa.getObservationValue( QAObservationType.AUTHORIZER ));
				item.setRecordNumber(qa.getObservationValue( QAObservationType.DOC_NUMBER ));
				item.setRemove(false);
				item.setNote(getNoteForSampleQaEvent(event));

				qaEventItems.add(item);
			}
		}

		int oldQaEvents = qaEventItems.size();
		for(int i = oldQaEvents; i < 10; i++){
			qaEventItems.add(new QaEventItem());
		}
		return qaEventItems;
	}

	private Set<TypeOfSample> getSampleTypeOfSamples(){
		Set<TypeOfSample> typeOfSamples = new HashSet<TypeOfSample>();
		List<SampleItem> sampleItems = sampleItemDAO.getSampleItemsBySampleId(sample.getId());
		for(SampleItem sampleItem : sampleItems){
			TypeOfSample typeOfSample = typeOfSampleDAO.getTypeOfSampleById(sampleItem.getTypeOfSampleId());
			if(!typeOfSamples.contains(typeOfSample)){
				typeOfSamples.add(typeOfSample);
			}
		}
		return typeOfSamples;
	}

	private String getSampleTypeOfSamplesString(){
		if(sample == null){
			return "";
		}
		Set<TypeOfSample> sampleTypeOfSamples = getSampleTypeOfSamples();
		StringBuilder str = new StringBuilder(",");
		for(TypeOfSample typeOfSample : sampleTypeOfSamples){
			str.append(typeOfSample.getId()).append(",");
		}
		return str.toString();
	}

	public static String getNoteForSample(Sample sample){
		Note note = new NoteService( sample ).getMostRecentNoteFilteredBySubject( QA_NOTE_SUBJECT );
		return note != null ? note.getText() : null;
	}

	public static String getNoteForSampleQaEvent(SampleQaEvent sampleQaEvent){
		if(sampleQaEvent == null || GenericValidator.isBlankOrNull(sampleQaEvent.getId())){
			return null;
		}else{
            Note note = new NoteService( sampleQaEvent ).getMostRecentNoteFilteredBySubject( null );
			return note != null ? note.getText() : null;
		}
	}

	private void getSampleQaEvents(Sample sample){
		SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();
		sampleQAEventList = sampleQaEventDAO.getSampleQaEventsBySample(sample);
	}

	private void setProjectList(BaseActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		ProjectDAO projectDAO = new ProjectDAOImpl();
		List<Project> projects = projectDAO.getAllProjects();
		PropertyUtils.setProperty(dynaForm, "projects", projects);
	}

	private Sample getSampleForLabNumber(String labNumber) throws LIMSInvalidConfigurationException{
		return sampleDAO.getSampleByAccessionNumber(labNumber);
	}

	private void getPatient(Sample sample){
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		Patient patient = sampleHumanDAO.getPatientForSample(sample);
		patientService = new PatientService(patient);
	}

	private void getObservationHistory(Sample sample){
		ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
		observationHistoryList = observationDAO.getAll(patientService.getPatient(), sample);
	}

	private Project getProjectForSample(Sample sample){
		SampleProjectDAO samplePorjectDAO = new SampleProjectDAOImpl();
		SampleProject sampleProject = samplePorjectDAO.getSampleProjectBySampleId(sample.getId());

		return sampleProject == null ? null : sampleProject.getProject();
	}
	
	private ObservationHistory getRefererObservation(Sample sample){
		for(ObservationHistory observation : observationHistoryList){
			if(observation.getObservationHistoryTypeId().equals(TableIdService.getInstance().DOCTOR_OBSERVATION_TYPE_ID)){
				return observation;
			}
		}

		return null;
	}

	private ObservationHistory getServiceObservation(Sample sample){
		for(ObservationHistory observation : observationHistoryList){
			if(observation.getObservationHistoryTypeId().equals(TableIdService.getInstance().SERVICE_OBSERVATION_TYPE_ID)){
				return observation;
			}
		}

		return null;
	}

	private void sortSections(List<TestSection> list){
		Collections.sort(list, new Comparator<TestSection>(){
			@Override
			public int compare(TestSection o1, TestSection o2){
				return o1.getSortOrderInt() - o2.getSortOrderInt();
			}
		});
	}

	private List<TestSection> createSectionList(){

		List<TestSection> sections = new TestSectionDAOImpl().getAllActiveTestSections();
		if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NONCONFORMITY_RECEPTION_AS_UNIT, "true")){
			TestSection extra = new TestSection();
			extra.setTestSectionName("Reception");
			extra.setSortOrder("0");
			extra.setNameKey("testSection.Reception");
			sections.add(extra);
		}
		
		if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT, "true")){
			TestSection extra = new TestSection();
			extra.setTestSectionName("Sample Collection");
			extra.setSortOrder("1");
			extra.setNameKey("testSection.SampleCollection");
			sections.add(extra);
		}
		
		sortSections(sections);
		return sections;
	}

	@Override
	protected String getPageSubtitleKey(){
		return "qaevent.add.title";
	}

	@Override
	protected String getPageTitleKey(){
		return "qaevent.add.title";
	}
}
