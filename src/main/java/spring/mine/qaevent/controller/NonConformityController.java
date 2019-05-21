package spring.mine.qaevent.controller;

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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.controller.BaseController;
import spring.mine.qaevent.form.NonConformityForm;
import spring.mine.qaevent.service.NonConformityHelper;
import spring.mine.qaevent.validator.NonConformityFormValidator;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.organization.OrganizationService;
import spring.service.project.ProjectService;
import spring.service.provider.ProviderService;
import spring.service.requester.SampleRequesterService;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleitem.SampleItemService;
import spring.service.sampleproject.SampleProjectService;
import spring.service.sampleqaevent.SampleQaEventService;
import spring.service.test.TestSectionService;
import spring.service.typeofsample.TypeOfSampleService;
import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.PersonService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateData;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateWorker;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class NonConformityController extends BaseController {

	@Autowired
	NonConformityFormValidator formValidator;

	@Autowired
	SampleService sampleService;
	@Autowired
	SampleItemService sampleItemService;
	@Autowired
	TypeOfSampleService typeOfSampleService;
	@Autowired
	SampleHumanService sampleHumanService;
	@Autowired
	spring.service.person.PersonService personService;
	@Autowired
	ProviderService providerService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	ObservationHistoryService observationHistoryService;
	@Autowired
	SampleProjectService sampleProjectService;
	@Autowired
	TestSectionService testSectionService;
	@Autowired
	SampleRequesterService sampleRequesterService;
	@Autowired
	ProjectService projectService;
	@Autowired
	SampleQaEventService sampleQaEventService;

	@RequestMapping(value = "/NonConformity", method = RequestMethod.GET)
	public ModelAndView showNonConformity(HttpServletRequest request) throws LIMSInvalidConfigurationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		NonConformityForm form = new NonConformityForm();
		String labNumber = request.getParameter("labNo");
		if (!GenericValidator.isBlankOrNull(labNumber)) {
			setupFormForLabNumber(labNumber, form);
		}
		addFlashMsgsToRequest(request);
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupFormForLabNumber(String labNumber, NonConformityForm form)
			throws LIMSInvalidConfigurationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Sample sample = getSampleForLabNumber(labNumber);

		boolean sampleFound = !(sample == null || GenericValidator.isBlankOrNull(sample.getId()));

		PropertyUtils.setProperty(form, "labNo", labNumber);
		Date today = Calendar.getInstance().getTime();
		PropertyUtils.setProperty(form, "date", DateUtil.formatDateAsText(today));
		if (FormFields.getInstance().useField(Field.QATimeWithDate)) {
			PropertyUtils.setProperty(form, "time", DateUtil.nowTimeAsText());
		}

		if (sampleFound) {
			createForExistingSample(form, sample);
		}

		setProjectList(form);

		PropertyUtils.setProperty(form, "sampleItemsTypeOfSampleIds", getSampleTypeOfSamplesString(sample));
		PropertyUtils.setProperty(form, "sections", createSectionList());
		PropertyUtils.setProperty(form, "qaEventTypes", DisplayListService.getList(ListType.QA_EVENTS));
		PropertyUtils.setProperty(form, "qaEvents", getSampleQaEventItems(sample));

		PropertyUtils.setProperty(form, "typeOfSamples", DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));

		PropertyUtils.setProperty(form, "readOnly", false);
		PropertyUtils.setProperty(form, "siteList",
				DisplayListService.getFreshList(ListType.SAMPLE_PATIENT_REFERRING_CLINIC));
		Provider provider = getProvider(sample);
		if (provider != null) {
			PropertyUtils.setProperty(form, "providerNew", Boolean.FALSE.toString());
			Person providerPerson = getProviderPerson(provider);
			if (providerPerson != null && !providerPerson.getId().equals(PatientUtil.getUnknownPerson().getId())) {
				PersonService personService = new PersonService(providerPerson);
				PropertyUtils.setProperty(form, "providerFirstName", personService.getFirstName());
				PropertyUtils.setProperty(form, "providerLastName", personService.getLastName());
				PropertyUtils.setProperty(form, "providerWorkPhone", personService.getPhone());
				Map<String, String> addressComponents = personService.getAddressComponents();

				PropertyUtils.setProperty(form, "providerStreetAddress", addressComponents.get("Street"));
				PropertyUtils.setProperty(form, "providerCity", addressComponents.get("village"));
				PropertyUtils.setProperty(form, "providerCommune", addressComponents.get("commune"));
				PropertyUtils.setProperty(form, "providerDepartment", addressComponents.get("department"));
			}
		} else {
			PropertyUtils.setProperty(form, "providerNew", Boolean.TRUE.toString());
			PropertyUtils.setProperty(form, "requesterSampleID", "");
			PropertyUtils.setProperty(form, "providerFirstName", "");
			PropertyUtils.setProperty(form, "providerLastName", "");
			PropertyUtils.setProperty(form, "providerWorkPhone", "");
		}

		PropertyUtils.setProperty(form, "departments", DisplayListService.getList(ListType.HAITI_DEPARTMENTS));

	}

	private void createForExistingSample(NonConformityForm form, Sample sample)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PatientService patientService = getPatientService(sample);
		List<ObservationHistory> observationHistoryList = getObservationHistory(sample, patientService);
		PropertyUtils.setProperty(form, "sampleId", sample.getId());
		PropertyUtils.setProperty(form, "patientId", patientService.getPatientId());

		Project project = getProjectForSample(sample);
		if (project != null) {
			PropertyUtils.setProperty(form, "projectId", project.getId());
			PropertyUtils.setProperty(form, "project", project.getLocalizedName());
		}

		String subjectNo = patientService.getSubjectNumber();
		if (!GenericValidator.isBlankOrNull(subjectNo)) {
			PropertyUtils.setProperty(form, "subjectNew", Boolean.FALSE);
			PropertyUtils.setProperty(form, "subjectNo", subjectNo);
		}

		String STNo = patientService.getSTNumber();
		if (!GenericValidator.isBlankOrNull(STNo)) {
			PropertyUtils.setProperty(form, "newSTNumber", Boolean.FALSE);
			PropertyUtils.setProperty(form, "STNumber", STNo);
		}

		String nationalId = patientService.getNationalId();
		if (!GenericValidator.isBlankOrNull(nationalId)) {
			PropertyUtils.setProperty(form, "nationalIdNew", Boolean.FALSE);
			PropertyUtils.setProperty(form, "nationalId", nationalId);
		}

		ObservationHistory doctorObservation = getRefererObservation(observationHistoryList);
		if (doctorObservation != null) {
			PropertyUtils.setProperty(form, "doctorNew", Boolean.FALSE);
			PropertyUtils.setProperty(form, "doctor", doctorObservation.getValue());
		}

		if (FormFields.getInstance().useField(Field.NON_CONFORMITY_SITE_LIST)) {
			PropertyUtils.setProperty(form, "serviceNew", Boolean.FALSE);
			PropertyUtils.setProperty(form, "service", getSampleRequesterOrganizationName(sample));
		} else {
			ObservationHistory serviceObservation = getServiceObservation(observationHistoryList);
			if (serviceObservation != null) {
				PropertyUtils.setProperty(form, "serviceNew", Boolean.FALSE);
				PropertyUtils.setProperty(form, "service", serviceObservation.getValue());
			}
		}

		PropertyUtils.setProperty(form, "comment", NonConformityHelper.getNoteForSample(sample));

		PropertyUtils.setProperty(form, "requesterSampleID", sample.getReferringId());
	}

	/**
	 * @return
	 */
	private String getSampleRequesterOrganizationName(Sample sample) {
		List<SampleRequester> sampleRequestors = sampleRequesterService.getRequestersForSampleId(sample.getId());
		if (sampleRequestors.isEmpty()) {
			return null;
		}
		long typeID = TableIdService.ORGANIZATION_REQUESTER_TYPE_ID;
		for (SampleRequester sampleRequester : sampleRequestors) {
			if (sampleRequester.getRequesterTypeId() == typeID) {
				String orgId = String.valueOf(sampleRequester.getRequesterId());
				Organization org = organizationService.get(orgId);

				if (org != null) {
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
	private Person getProviderPerson(Provider provider) {
		if (provider == null) {
			return null;
		}
		return personService.get(provider.getPerson().getId());
	}

	private Provider getProvider(Sample sample) {
		if (sample == null) {
			return null;
		}
		SampleHuman sampleHuman = getSampleHuman(sample);
		String id = sampleHuman.getProviderId();
		if (id == null) {
			return null;
		}
		return providerService.get(id);
	}

	/**
	 * @return
	 */
	private SampleHuman getSampleHuman(Sample sample) {
		SampleHuman sampleHuman = new SampleHuman();
		sampleHuman.setSampleId(sample.getId());
		return sampleHumanService.getDataBySample(sampleHuman);
	}

	/**
	 * @param sample
	 * @return
	 */
	private List<QaEventItem> getSampleQaEventItems(Sample sample) {
		List<QaEventItem> qaEventItems = new ArrayList<>();
		if (sample != null) {
			List<SampleQaEvent> sampleQAEventList = getSampleQaEvents(sample);
			for (SampleQaEvent event : sampleQAEventList) {
				QAService qa = new QAService(event);
				QaEventItem item = new QaEventItem();
				item.setId(qa.getEventId());
				item.setQaEvent(qa.getQAEvent().getId());
				SampleItem sampleItem = qa.getSampleItem();
				// -1 is the index for "all samples"
				item.setSampleType((sampleItem == null) ? "-1" : sampleItem.getTypeOfSampleId());
				item.setSection(qa.getObservationValue(QAObservationType.SECTION));
				item.setAuthorizer(qa.getObservationValue(QAObservationType.AUTHORIZER));
				item.setRecordNumber(qa.getObservationValue(QAObservationType.DOC_NUMBER));
				item.setRemove(false);
				item.setNote(NonConformityHelper.getNoteForSampleQaEvent(event));

				qaEventItems.add(item);
			}
		}

		int oldQaEvents = qaEventItems.size();
		for (int i = oldQaEvents; i < 10; i++) {
			qaEventItems.add(new QaEventItem());
		}
		return qaEventItems;
	}

	private Set<TypeOfSample> getSampleTypeOfSamples(Sample sample) {
		Set<TypeOfSample> typeOfSamples = new HashSet<>();
		List<SampleItem> sampleItems = sampleItemService.getSampleItemsBySampleId(sample.getId());
		for (SampleItem sampleItem : sampleItems) {
			TypeOfSample typeOfSample = typeOfSampleService.get(sampleItem.getTypeOfSampleId());
			if (!typeOfSamples.contains(typeOfSample)) {
				typeOfSamples.add(typeOfSample);
			}
		}
		return typeOfSamples;
	}

	private String getSampleTypeOfSamplesString(Sample sample) {
		if (sample == null) {
			return "";
		}
		Set<TypeOfSample> sampleTypeOfSamples = getSampleTypeOfSamples(sample);
		StringBuilder str = new StringBuilder(",");
		for (TypeOfSample typeOfSample : sampleTypeOfSamples) {
			str.append(typeOfSample.getId()).append(",");
		}
		return str.toString();
	}

	private List<SampleQaEvent> getSampleQaEvents(Sample sample) {
		return sampleQaEventService.getSampleQaEventsBySample(sample);
	}

	private void setProjectList(NonConformityForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<Project> projects = projectService.getAll();
		PropertyUtils.setProperty(form, "projects", projects);
	}

	private Sample getSampleForLabNumber(String labNumber) throws LIMSInvalidConfigurationException {
		return sampleService.getSampleByAccessionNumber(labNumber);
	}

	private PatientService getPatientService(Sample sample) {
		Patient patient = sampleHumanService.getPatientForSample(sample);
		return new PatientService(patient);
	}

	private List<ObservationHistory> getObservationHistory(Sample sample, PatientService patientService) {
		return observationHistoryService.getAll(patientService.getPatient(), sample);
	}

	private Project getProjectForSample(Sample sample) {
		SampleProject sampleProject = sampleProjectService.getSampleProjectBySampleId(sample.getId());

		return sampleProject == null ? null : sampleProject.getProject();
	}

	private ObservationHistory getRefererObservation(List<ObservationHistory> observationHistoryList) {
		for (ObservationHistory observation : observationHistoryList) {
			if (observation.getObservationHistoryTypeId().equals(TableIdService.DOCTOR_OBSERVATION_TYPE_ID)) {
				return observation;
			}
		}

		return null;
	}

	private ObservationHistory getServiceObservation(List<ObservationHistory> observationHistoryList) {
		for (ObservationHistory observation : observationHistoryList) {
			if (observation.getObservationHistoryTypeId().equals(TableIdService.SERVICE_OBSERVATION_TYPE_ID)) {
				return observation;
			}
		}

		return null;
	}

	private void sortSections(List<TestSection> list) {
		Collections.sort(list, new Comparator<TestSection>() {
			@Override
			public int compare(TestSection o1, TestSection o2) {
				return o1.getSortOrderInt() - o2.getSortOrderInt();
			}
		});
	}

	private List<TestSection> createSectionList() {

		List<TestSection> sections = testSectionService.getAllActiveTestSections();
		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NONCONFORMITY_RECEPTION_AS_UNIT,
				"true")) {
			TestSection extra = new TestSection();
			extra.setTestSectionName("Reception");
			extra.setSortOrder("0");
			extra.setNameKey("testSection.Reception");
			sections.add(extra);
		}

		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT,
				"true")) {
			TestSection extra = new TestSection();
			extra.setTestSectionName("Sample Collection");
			extra.setSortOrder("1");
			extra.setNameKey("testSection.SampleCollection");
			sections.add(extra);
		}

		sortSections(sections);
		return sections;
	}

	@RequestMapping(value = "/NonConformity", method = RequestMethod.POST)
	public ModelAndView showNonConformityUpdate(HttpServletRequest request,
			@ModelAttribute("form") @Validated(NonConformityForm.NonConformity.class) NonConformityForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		NonConformityUpdateData data = new NonConformityUpdateData(form, getSysUserId(request));
		NonConformityUpdateWorker worker = new NonConformityUpdateWorker(data);
		String forward = worker.update();

		if (FWD_FAIL_INSERT.equals(forward)) {
			saveErrors(worker.getErrors());
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		}
		return findForward(forward, form);
	}

	@Override
	protected String getPageSubtitleKey() {
		return "qaevent.add.title";
	}

	@Override
	protected String getPageTitleKey() {
		return "qaevent.add.title";
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "nonConformityDefiniton";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/NonConformity.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "nonConformityDefiniton";
		} else {
			return "PageNotFound";
		}
	}
}
