package org.openelisglobal.barcode.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.barcode.form.PrintBarcodeForm;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.SampleStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.bean.SampleEditItem;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

@Controller
public class PrintBarcodeController extends BaseController {

    private static final SampleEditItemComparator testComparator = new SampleEditItemComparator();
    private static final Set<Integer> excludedAnalysisStatusList = new HashSet<>();
    private static final Set<Integer> ENTERED_STATUS_SAMPLE_LIST = new HashSet<>();
    private static final Collection<String> ABLE_TO_CANCEL_ROLE_NAMES = new ArrayList<>();

    @Autowired
    IStatusService statusService;
    @Autowired
    SampleService sampleService;
    @Autowired
    SampleItemService sampleItemService;
    @Autowired
    AnalysisService analysisService;
    @Autowired
    TypeOfSampleService typeOfSampleService;
    @Autowired
    SampleHumanService sampleHumanService;

    @PostConstruct
    private void initialize() {
        excludedAnalysisStatusList.add(Integer.parseInt(statusService.getStatusID(AnalysisStatus.Canceled)));
        ENTERED_STATUS_SAMPLE_LIST.add(Integer.parseInt(statusService.getStatusID(SampleStatus.Entered)));
        ABLE_TO_CANCEL_ROLE_NAMES.add("Validator");
        ABLE_TO_CANCEL_ROLE_NAMES.add("Validation");
        ABLE_TO_CANCEL_ROLE_NAMES.add("Biologist");
    }

    @RequestMapping(value = "/PrintBarcode", method = RequestMethod.GET)
    public ModelAndView setupPrintBarcode(HttpServletRequest request,
            @Valid @ModelAttribute("form") PrintBarcodeForm form, BindingResult result)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        form.setFormAction("PrintBarcode");
        form.setFormMethod(RequestMethod.GET);
        Map<String, Object> displayObjects = new HashMap<>();
        addPatientSearch(displayObjects);

        if (GenericValidator.isBlankOrNull(request.getParameter("accessionNumber"))) {
            return findForward(FWD_SUCCESS, displayObjects, form);
        }

        String accessionNumber = form.getAccessionNumber();
        Sample sample = getSample(accessionNumber);
        if (sample != null && !GenericValidator.isBlankOrNull(sample.getId())) {
            List<SampleItem> sampleItemList = getSampleItems(sample);
            setPatientInfo(displayObjects, sample);
            List<SampleEditItem> currentTestList = getCurrentTestInfo(sampleItemList, accessionNumber, false);
            displayObjects.put("existingTests", currentTestList);
        }
        addPatientSearch(displayObjects);
        return findForward(FWD_SUCCESS, displayObjects, form);
    }

    private void addPatientSearch(Map<String, Object> displayObjects) {
        PatientSearch patientSearch = new PatientSearch();
        patientSearch.setLoadFromServerWithPatient(true);
        patientSearch.setSelectedPatientActionButtonText(MessageUtil.getMessage("label.patient.search.select"));
        displayObjects.put("patientSearch", patientSearch);

    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "PrintBarcodeDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "PrintBarcodeDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "barcode.print.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "barcode.print.title";
    }

    /**
     * Get sample by accession number
     *
     * @param accessionNumber That corresponds to the sample
     * @return The sample belonging to accession number
     */
    private Sample getSample(String accessionNumber) {
        return sampleService.getSampleByAccessionNumber(accessionNumber);
    }

    /**
     * Get list of SampleItems belonging to a sample
     *
     * @param sample Containing all the individual sample items
     * @return The list of sample items belonging to sample
     */
    private List<SampleItem> getSampleItems(Sample sample) {
        return sampleItemService.getSampleItemsBySampleIdAndStatus(sample.getId(), ENTERED_STATUS_SAMPLE_LIST);
    }

    /**
     * Get list of tests corresponding to sample items
     *
     * @param sampleItemList     The list of sample items to fetch corresponding
     *                           tests
     * @param accessionNumber    The accession number corresponding to the sample
     * @param allowedToCancelAll
     * @return list of corresponding tests
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private List<SampleEditItem> getCurrentTestInfo(List<SampleItem> sampleItemList, String accessionNumber,
            boolean allowedToCancelAll)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        List<SampleEditItem> currentTestList = new ArrayList<>();
        for (SampleItem sampleItem : sampleItemList) {
            addCurrentTestsToList(sampleItem, currentTestList, accessionNumber, allowedToCancelAll);
        }

        return currentTestList;
    }

    private void addCurrentTestsToList(SampleItem sampleItem, List<SampleEditItem> currentTestList,
            String accessionNumber, boolean allowedToCancelAll) {

        TypeOfSample typeOfSample = new TypeOfSample();
        typeOfSample.setId(sampleItem.getTypeOfSampleId());
        typeOfSampleService.get(typeOfSample.getDescription());
        List<Analysis> analysisList = analysisService.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem,
                excludedAnalysisStatusList);
        List<SampleEditItem> analysisSampleItemList = new ArrayList<>();

        String collectionDate = DateUtil.convertTimestampToStringDate(sampleItem.getCollectionDate());
        String collectionTime = DateUtil.convertTimestampToStringTime(sampleItem.getCollectionDate());
        boolean canRemove = true;
        for (Analysis analysis : analysisList) {
            SampleEditItem sampleEditItem = new SampleEditItem();
            sampleEditItem.setTestId(analysis.getTest().getId());
            sampleEditItem.setTestName(TestServiceImpl.getUserLocalizedTestName(analysis.getTest()));
            sampleEditItem.setSampleItemId(sampleItem.getId());

            boolean canCancel = allowedToCancelAll
                    || (!StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.Canceled)
                            && StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.NotStarted));
            if (!canCancel) {
                canRemove = false;
            }
            sampleEditItem.setCanCancel(canCancel);
            sampleEditItem.setAnalysisId(analysis.getId());
            sampleEditItem.setStatus(StatusService.getInstance().getStatusNameFromId(analysis.getStatusId()));
            sampleEditItem.setSortOrder(analysis.getTest().getSortOrder());
            sampleEditItem.setHasResults(
                    !StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.NotStarted));

            analysisSampleItemList.add(sampleEditItem);
            break;
        }

        if (!analysisSampleItemList.isEmpty()) {
            Collections.sort(analysisSampleItemList, testComparator);
            SampleEditItem firstItem = analysisSampleItemList.get(0);
            firstItem.setAccessionNumber(accessionNumber + "-" + sampleItem.getSortOrder());
            firstItem.setSampleType(typeOfSample.getLocalizedName());
            firstItem.setCanRemoveSample(canRemove);
            firstItem.setCollectionDate(collectionDate == null ? "" : collectionDate);
            firstItem.setCollectionTime(collectionTime);
            currentTestList.addAll(analysisSampleItemList);
        }
    }

    private void setPatientInfo(Map<String, Object> displayObjects, Sample sample)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Patient patient = sampleHumanService.getPatientForSample(sample);
        PatientService patientPatientService = SpringContext.getBean(PatientService.class);
        PersonService personService = SpringContext.getBean(PersonService.class);
        personService.getData(patient.getPerson());

        displayObjects.put("patientName", patientPatientService.getLastFirstName(patient));
        displayObjects.put("dob", patientPatientService.getEnteredDOB(patient));
        displayObjects.put("gender", patientPatientService.getGender(patient));
        displayObjects.put("nationalId", patientPatientService.getNationalId(patient));
    }

    private static class SampleEditItemComparator implements Comparator<SampleEditItem> {

        @Override
        public int compare(SampleEditItem o1, SampleEditItem o2) {
            if (GenericValidator.isBlankOrNull(o1.getSortOrder())
                    || GenericValidator.isBlankOrNull(o2.getSortOrder())) {
                return o1.getTestName().compareTo(o2.getTestName());
            }

            try {
                return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
            } catch (NumberFormatException e) {
                return o1.getTestName().compareTo(o2.getTestName());
            }
        }

    }
}
