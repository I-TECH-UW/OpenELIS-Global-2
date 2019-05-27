package spring.mine.workplan.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.internationalization.MessageUtil;
import spring.mine.workplan.form.WorkplanForm;
import spring.service.analysis.AnalysisService;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl.ObservationType;
import spring.service.sampleqaevent.SampleQaEventService;
import spring.service.test.TestServiceImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

@Controller
public class WorkPlanByTestController extends BaseWorkplanController {

	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private SampleQaEventService sampleQaEventService;
	private static boolean HAS_NFS_PANEL = false;

	static {
		HAS_NFS_PANEL = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.CONDENSE_NFS_PANEL, "true");
	}

	@RequestMapping(value = "/WorkPlanByTest", method = RequestMethod.GET)
	public ModelAndView showWorkPlanByPanel(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkplanForm form = new WorkplanForm();

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		List<TestResultItem> workplanTests;

		String testType = request.getParameter("selectedSearchID");
		String testName;

		if (!GenericValidator.isBlankOrNull(testType)) {

			if (testType.equals("NFS")) {
				testName = "NFS";
				workplanTests = getWorkplanForNFSTest(testType);
			} else {
				testName = getTestName(testType);
				workplanTests = getWorkplanByTest(testType);
			}
			ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility();
			resultsLoadUtility.sortByAccessionAndSequence(workplanTests);
			PropertyUtils.setProperty(form, "testTypeID", testType);
			PropertyUtils.setProperty(form, "testName", testName);
			PropertyUtils.setProperty(form, "workplanTests", workplanTests);
			PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);

		} else {
			// no search done, set workplanTests as empty
			PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
			PropertyUtils.setProperty(form, "testName", null);
			PropertyUtils.setProperty(form, "workplanTests", new ArrayList<TestResultItem>());
		}

		PropertyUtils.setProperty(form, "searchTypes", getTestDropdownList());
		PropertyUtils.setProperty(form, "workplanType", request.getParameter("type"));
		PropertyUtils.setProperty(form, "searchLabel", MessageUtil.getMessage("workplan.test.types"));
		PropertyUtils.setProperty(form, "searchAction", "WorkPlanByTest.do");

		return findForward(FWD_SUCCESS, form);
	}

	private List<IdValuePair> getTestDropdownList() {
		List<IdValuePair> testList = DisplayListService.getInstance().getList(DisplayListService.ListType.ALL_TESTS);

		if (HAS_NFS_PANEL) {
			testList = adjustNFSTests(testList);
		}
		Collections.sort(testList, new ValueComparator());
		return testList;
	}

	private List<IdValuePair> adjustNFSTests(List<IdValuePair> allTestsList) {
		List<IdValuePair> adjustedList = new ArrayList<>(allTestsList.size());
		for (IdValuePair idValuePair : allTestsList) {
			if (!nfsTestIdList.contains(idValuePair.getId())) {
				adjustedList.add(idValuePair);
			}
		}
		// add NFS to the list
		adjustedList.add(new IdValuePair("NFS", "NFS"));
		return adjustedList;
	}

	@SuppressWarnings("unchecked")
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
				Sample sample = analysis.getSampleItem().getSample();
				testResultItem.setAccessionNumber(sample.getAccessionNumber());
				testResultItem.setReceivedDate(getReceivedDateDisplay(sample));
				testResultItem.setNonconforming(QAService.isAnalysisParentNonConforming(analysis));

				if (FormFields.getInstance().useField(Field.QaEventsBySection)) {
					testResultItem.setNonconforming(getQaEventByTestSection(analysis));
				}

				if (!testResultItem.getAccessionNumber().equals(currentAccessionNumber)) {
					sampleGroupingNumber++;
					currentAccessionNumber = testResultItem.getAccessionNumber();
					subjectNumber = getSubjectNumber(analysis);
					patientName = getPatientName(analysis);
					nextVisit = ObservationHistoryServiceImpl.getInstance()
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

	@SuppressWarnings("unchecked")
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

	private String getTestName(String testId) {
		return TestServiceImpl.getUserLocalizedTestName(testId);
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

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "workplanResultDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else {
			return "PageNotFound";
		}
	}

	class ValueComparator implements Comparator<IdValuePair> {

		@Override
		public int compare(IdValuePair p1, IdValuePair p2) {
			return p1.getValue().toUpperCase().compareTo(p2.getValue().toUpperCase());
		}

	}

	@Override
	protected String getPageTitleKey() {
		return "workplan.test.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "workplan.test.title";
	}
}
