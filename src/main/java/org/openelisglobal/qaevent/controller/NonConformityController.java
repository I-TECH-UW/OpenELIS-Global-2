package org.openelisglobal.qaevent.controller;

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

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.qaevent.form.NonConformityForm;
import org.openelisglobal.qaevent.service.NonConformityHelper;
import org.openelisglobal.qaevent.validator.NonConformityFormValidator;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.project.service.ProjectService;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.requester.service.SampleRequesterService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.sampleproject.service.SampleProjectService;
import org.openelisglobal.sampleqaevent.service.SampleQaEventService;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.common.services.QAService.QAObservationType;
import org.openelisglobal.common.services.TableIdService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.util.PatientUtil;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.project.valueholder.Project;
import org.openelisglobal.provider.valueholder.Provider;
import org.openelisglobal.qaevent.valueholder.retroCI.QaEventItem;
import org.openelisglobal.qaevent.worker.INonConformityUpdateWorker;
import org.openelisglobal.qaevent.worker.NonConformityUpdateData;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.valueholder.SampleHuman;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.sampleproject.valueholder.SampleProject;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.test.valueholder.TestSection;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

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
    org.openelisglobal.person.service.PersonService personService;
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
        PropertyUtils.setProperty(form, "qaEventTypes", DisplayListService.getInstance().getList(ListType.QA_EVENTS));
        PropertyUtils.setProperty(form, "qaEvents", getSampleQaEventItems(sample));

        PropertyUtils.setProperty(form, "typeOfSamples",
                DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));

        PropertyUtils.setProperty(form, "readOnly", false);
        PropertyUtils.setProperty(form, "siteList",
                DisplayListService.getInstance().getFreshList(ListType.SAMPLE_PATIENT_REFERRING_CLINIC));
        Provider provider = getProvider(sample);
        if (provider != null) {
            PropertyUtils.setProperty(form, "providerNew", Boolean.FALSE.toString());
            Person providerPerson = getProviderPerson(provider);
            if (providerPerson != null && !providerPerson.getId().equals(PatientUtil.getUnknownPerson().getId())) {
                PropertyUtils.setProperty(form, "providerFirstName", personService.getFirstName(providerPerson));
                PropertyUtils.setProperty(form, "providerLastName", personService.getLastName(providerPerson));
                PropertyUtils.setProperty(form, "providerWorkPhone", personService.getPhone(providerPerson));
                Map<String, String> addressComponents = personService.getAddressComponents(providerPerson);

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

        PropertyUtils.setProperty(form, "departments",
                DisplayListService.getInstance().getList(ListType.HAITI_DEPARTMENTS));

    }

    private void createForExistingSample(NonConformityForm form, Sample sample)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Patient patient = sampleHumanService.getPatientForSample(sample);
        PatientService patientService = SpringContext.getBean(PatientService.class);
        PersonService personService = SpringContext.getBean(PersonService.class);
        personService.getData(patient.getPerson());
        List<ObservationHistory> observationHistoryList = getObservationHistory(sample, patient);
        PropertyUtils.setProperty(form, "sampleId", sample.getId());
        PropertyUtils.setProperty(form, "patientId", patientService.getPatientId(patient));

        Project project = getProjectForSample(sample);
        if (project != null) {
            PropertyUtils.setProperty(form, "projectId", project.getId());
            PropertyUtils.setProperty(form, "project", project.getLocalizedName());
        }

        String subjectNo = patientService.getSubjectNumber(patient);
        if (!GenericValidator.isBlankOrNull(subjectNo)) {
            PropertyUtils.setProperty(form, "subjectNew", Boolean.FALSE);
            PropertyUtils.setProperty(form, "subjectNo", subjectNo);
        }

        String STNo = patientService.getSTNumber(patient);
        if (!GenericValidator.isBlankOrNull(STNo)) {
            PropertyUtils.setProperty(form, "newSTNumber", Boolean.FALSE);
            PropertyUtils.setProperty(form, "STNumber", STNo);
        }

        String nationalId = patientService.getNationalId(patient);
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
        long typeID = TableIdService.getInstance().ORGANIZATION_REQUESTER_TYPE_ID;
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

    private List<ObservationHistory> getObservationHistory(Sample sample, Patient patient) {
        return observationHistoryService.getAll(patient, sample);
    }

    private Project getProjectForSample(Sample sample) {
        SampleProject sampleProject = sampleProjectService.getSampleProjectBySampleId(sample.getId());

        return sampleProject == null ? null : sampleProject.getProject();
    }

    private ObservationHistory getRefererObservation(List<ObservationHistory> observationHistoryList) {
        for (ObservationHistory observation : observationHistoryList) {
            if (observation.getObservationHistoryTypeId()
                    .equals(TableIdService.getInstance().DOCTOR_OBSERVATION_TYPE_ID)) {
                return observation;
            }
        }

        return null;
    }

    private ObservationHistory getServiceObservation(List<ObservationHistory> observationHistoryList) {
        for (ObservationHistory observation : observationHistoryList) {
            if (observation.getObservationHistoryTypeId()
                    .equals(TableIdService.getInstance().SERVICE_OBSERVATION_TYPE_ID)) {
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
//		NonConformityUpdateWorker worker = new NonConformityUpdateWorker(data);
        INonConformityUpdateWorker worker = SpringContext.getBean(INonConformityUpdateWorker.class);
        worker.setWebData(data);
        try {
            worker.update();
        } catch (LIMSRuntimeException lre) {
            saveErrors(worker.getErrors());
            return findForward(FWD_FAIL_INSERT, form);
        }
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);

        return findForward(FWD_SUCCESS_INSERT, form);
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
