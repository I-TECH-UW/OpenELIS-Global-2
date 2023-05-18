package org.openelisglobal.workplan.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.QAService.QAObservationType;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
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
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleqaevent.service.SampleQaEventService;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.search.service.SearchResultsService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.systemuser.service.UserService;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.workplan.controller.WorkplanRestController.ValueComparator;
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
public class WorkplanByTestRestController extends WorkplanRestController {

	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private SampleQaEventService sampleQaEventService;
	@Autowired
	private UserService userService;

	private static boolean HAS_NFS_PANEL = false;

	static {
		HAS_NFS_PANEL = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.CONDENSE_NFS_PANEL, "true");
	}

	@GetMapping(value = "test-list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private List<IdValuePair> getTestDropdownList(HttpServletRequest request) {
		List<IdValuePair> testList = userService.getAllDisplayUserTestsByLabUnit(getSysUserId(request),
				Constants.ROLE_RESULTS);

		if (HAS_NFS_PANEL) {
			testList = adjustNFSTests(testList);
		}
		Collections.sort(testList, new ValueComparator());
		return testList;
	}

	@GetMapping(value = "/workplan-by-test", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> showWorkPlanByPanel(HttpServletRequest request,
			@RequestParam(name = "test_id", defaultValue = "0") String testType)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Map<String, Object> response = new HashMap<String, Object>();
		List<TestResultItem> workplanTests = new ArrayList<TestResultItem>();
		List<TestResultItem> filteredTests = new ArrayList<TestResultItem>();

		if (!GenericValidator.isBlankOrNull(testType)) {
			if (testType.equals("NFS")) {
				workplanTests = getWorkplanForNFSTest(testType);
				filteredTests = userService.filterResultsByLabUnitRoles(getSysUserId(request), workplanTests,
						Constants.ROLE_RESULTS);
			} else {
				workplanTests = getWorkplanByTest(testType);
				filteredTests = userService.filterResultsByLabUnitRoles(getSysUserId(request), workplanTests,
						Constants.ROLE_RESULTS);
			}
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

	private List<TestResultItem> getWorkplanByTest(String testType) {

		List<Analysis> testList;
		List<TestResultItem> workplanTestList = new ArrayList<>();
		String currentAccessionNumber = null;
		String subjectNumber = null;
		String patientName = null;
		String nextVisit = null;
		int sampleGroupingNumber = 0;

		if (!(GenericValidator.isBlankOrNull(testType) || testType.equals("0"))) {

			testList = analysisService.getAllAnalysisByTestAndStatus(testType, statusList);

			if (testList.isEmpty()) {
				return new ArrayList<>();
			}

			for (Analysis analysis : testList) {
				TestResultItem testResultItem = new TestResultItem();
				testResultItem.setTestId(testType);
				Sample sample = analysis.getSampleItem().getSample();
				testResultItem.setAccessionNumber(sample.getAccessionNumber());
				testResultItem.setReceivedDate(getReceivedDateDisplay(sample));
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

	private List<TestResultItem> getWorkplanForNFSTest(String testType) {

		List<Analysis> testList;
		List<TestResultItem> workplanTestList = new ArrayList<>();
		String currentAccessionNumber = null;
		int sampleGroupingNumber = 0;

		TestResultItem testResultItem;

		List<String> testIdList = new ArrayList<>();

		if (!(GenericValidator.isBlankOrNull(testType) || testType.equals("0"))) {

			testList = analysisService.getAllAnalysisByTestsAndStatus(nfsTestIdList, statusList);

			if (testList.isEmpty()) {
				return new ArrayList<>();
			}

			for (Analysis analysis : testList) {

				Sample sample = analysis.getSampleItem().getSample();
				String analysisAccessionNumber = sample.getAccessionNumber();

				if (!analysisAccessionNumber.equals(currentAccessionNumber)) {
					sampleGroupingNumber++;
					currentAccessionNumber = analysisAccessionNumber;
					testIdList = new ArrayList<>();

				}
				testResultItem = new TestResultItem();
				testResultItem.setTestId(testType);
				testResultItem.setAccessionNumber(currentAccessionNumber);
				testResultItem.setReceivedDate(sample.getReceivedDateForDisplay());
				testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
				testResultItem.setNonconforming(QAService.isAnalysisParentNonConforming(analysis));
				testIdList.add(analysis.getTest().getId());

				if (allNFSTestsRequested(testIdList)) {
					workplanTestList.add(testResultItem);
				}
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
