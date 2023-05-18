package org.openelisglobal.workplan.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.QAService.QAObservationType;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.service.ObservationHistoryServiceImpl.ObservationType;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.OrderPriority;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleqaevent.service.SampleQaEventService;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.search.service.SearchResultsService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.systemuser.service.UserService;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.workplan.form.WorkplanForm;
import org.openelisglobal.workplan.form.WorkplanForm.PrintWorkplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/rest/")
public class WorkplanByPriorityRestController extends WorkplanRestController {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private SampleQaEventService sampleQaEventService;
    @Autowired
    private UserService userService;	

	@GetMapping(value = "priorities", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private List<IdValuePair> createPriorityList() {
		return DisplayListService.getInstance().getList(ListType.ORDER_PRIORITY);
	}
	
	@RequestMapping(value = "/workplan-by-priority", method = RequestMethod.GET)
    public Map<String, Object> showWorkPlanByPriority(HttpServletRequest request,
			@RequestParam(name = "priority", defaultValue = "") OrderPriority priority)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        
		Map<String, Object> response = new HashMap<String, Object>();
        List<TestResultItem> workplanTests = new ArrayList<TestResultItem>();
        List<TestResultItem> filteredTests =  new ArrayList<TestResultItem>();

        if (priority != null) {
            workplanTests = getWorkplanByPriority(priority);
            filteredTests = userService.filterResultsByLabUnitRoles(getSysUserId(request), workplanTests,
                    Constants.ROLE_RESULTS);

            ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility();
            resultsLoadUtility.sortByAccessionAndSequence(filteredTests);

        } 
        
		response.put("tests", filteredTests);
		response.put("SUBJECT_ON_WORKPLAN",
				ConfigurationProperties.getInstance().getPropertyValue(Property.SUBJECT_ON_WORKPLAN));
		response.put("NEXT_VISIT_DATE_ON_WORKPLAN",
				ConfigurationProperties.getInstance().getPropertyValue(Property.NEXT_VISIT_DATE_ON_WORKPLAN));
		response.put("configurationName",
				ConfigurationProperties.getInstance().getPropertyValue(Property.configurationName));

		return response;

    }

    private List<TestResultItem> getWorkplanByPriority(OrderPriority priority) {

        List<TestResultItem> workplanTestList = new ArrayList<>();
        String currentAccessionNumber = null;
        String subjectNumber = null;
        String patientName = null;
        String nextVisit = null;
        int sampleGroupingNumber = 0;

        if (priority != null) {
            List<Analysis> analysisList = analysisService.getAnalysesByPriorityAndStatusId(priority, statusList);
            for (Analysis analysis : analysisList) {
                TestResultItem testResultItem = new TestResultItem();
                testResultItem.setTestId(analysis.getTest().getId());
                Sample sample = analysis.getSampleItem().getSample();
                testResultItem.setAccessionNumber(sample.getAccessionNumber());
                testResultItem.setReceivedDate(getReceivedDateDisplay(sample));
                testResultItem.setTestName(TestServiceImpl.getUserLocalizedTestName(analysis.getTest()));
                boolean nonConforming = QAService.isAnalysisParentNonConforming(analysis);
                if (FormFields.getInstance().useField(Field.QaEventsBySection)) {
                    nonConforming = nonConforming || getQaEventByTestSection(analysis);
                }
                testResultItem.setNonconforming(nonConforming);

                if (!testResultItem.getAccessionNumber().equals(currentAccessionNumber)) {
                    sampleGroupingNumber++;
                    currentAccessionNumber = testResultItem.getAccessionNumber();
                    subjectNumber = getSubjectNumber(analysis);
                    patientName = getPatientName(analysis);
                    nextVisit = SpringContext.getBean(ObservationHistoryService.class)
                            .getValueForSample(ObservationType.NEXT_VISIT_DATE, sample.getId());
                }
                testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
                testResultItem.setPatientInfo(subjectNumber);
                testResultItem.setPatientName(patientName);
                testResultItem.setNextVisitDate(nextVisit);

                workplanTestList.add(testResultItem);
            }
        }
        return workplanTestList;
    }

    private boolean getQaEventByTestSection(Analysis analysis) {

        if (analysis.getTestSection() != null && analysis.getSampleItem().getSample() != null) {
            Sample sample = analysis.getSampleItem().getSample();
            List<SampleQaEvent> sampleQaEventsList = getSampleQaEvents(sample);
            for (SampleQaEvent event : sampleQaEventsList) {
                QAService qa = new QAService(event);
                if (!GenericValidator.isBlankOrNull(qa.getObservationValue(QAObservationType.SECTION))
                        && qa.getObservationValue(QAObservationType.SECTION)
                                .equals(analysis.getTestSection().getNameKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<SampleQaEvent> getSampleQaEvents(Sample sample) {
        return sampleQaEventService.getSampleQaEventsBySample(sample);
    }

}
