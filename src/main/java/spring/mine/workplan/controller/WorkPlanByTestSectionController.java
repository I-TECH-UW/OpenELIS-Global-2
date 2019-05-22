package spring.mine.workplan.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import spring.service.sampleqaevent.SampleQaEventService;
import spring.service.test.TestSectionService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import spring.service.analysis.AnalysisServiceImpl;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl.ObservationType;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class WorkPlanByTestSectionController extends BaseWorkplanController {

	@Autowired
	private spring.service.analysis.AnalysisService analysisService;
	@Autowired
	private SampleQaEventService sampleQaEventService;
	@Autowired
	private TestSectionService testSectionService;

	@RequestMapping(value = "/WorkPlanByTestSection", method = RequestMethod.GET)
	public ModelAndView showWorkPlanByTestSection(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkplanForm form = new WorkplanForm();

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		String testSectionId = (request.getParameter("testSectionId"));

		String workplan = request.getParameter("type");

		// load testSections for drop down
		PropertyUtils.setProperty(form, "testSections", DisplayListService.getList(ListType.TEST_SECTION));
		PropertyUtils.setProperty(form, "testSectionsByName",
				DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));

		TestSection ts = null;

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionService.get(testSectionId);
			PropertyUtils.setProperty(form, "testSectionId", "0");
		}

		List<TestResultItem> workplanTests = new ArrayList<>();

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			// get tests based on test section
			workplanTests = getWorkplanByTestSection(testSectionId);
			PropertyUtils.setProperty(form, "workplanTests", workplanTests);
			PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);
			PropertyUtils.setProperty(form, "testName", ts.getLocalizedName());

		} else {
			// set workplanTests as empty
			PropertyUtils.setProperty(form, "workplanTests", new ArrayList<TestResultItem>());
		}
		ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility();
		resultsLoadUtility.sortByAccessionAndSequence(workplanTests);
		// add Patient Name to test table
		if (isPatientNameAdded()) {
			addPatientNamesToList(workplanTests);
		}
		PropertyUtils.setProperty(form, "workplanType", workplan);
		PropertyUtils.setProperty(form, "searchLabel", MessageUtil.getMessage("workplan.unit.types"));

		return findForward(FWD_SUCCESS, form);
	}

	@SuppressWarnings("unchecked")
	private List<TestResultItem> getWorkplanByTestSection(String testSectionId) {

		List<Analysis> testList = new ArrayList<>();
		List<TestResultItem> workplanTestList = new ArrayList<>();
		String currentAccessionNumber = new String();
		String subjectNumber = new String();
		String patientName = new String();
		String nextVisit = new String();
		int sampleGroupingNumber = 0;
		List<String> testIdList = new ArrayList<>();
		List<TestResultItem> nfsTestItemList = new ArrayList<>();
		boolean isNFSTest = false;
		TestResultItem testResultItem = new TestResultItem();

		if (!(GenericValidator.isBlankOrNull(testSectionId))) {

			String sectionId = testSectionId;
			testList = analysisService.getAllAnalysisByTestSectionAndStatus(sectionId, statusList, true);

			if (testList.isEmpty()) {
				return new ArrayList<>();
			}

			for (Analysis analysis : testList) {
				Sample sample = analysis.getSampleItem().getSample();
				String analysisAccessionNumber = sample.getAccessionNumber();

				if (!analysisAccessionNumber.equals(currentAccessionNumber)) {

					if (isNFSTest) {
						if (!allNFSTestsRequested(testIdList)) {
							// add nfs subtests
							for (TestResultItem nfsTestItem : nfsTestItemList) {
								workplanTestList.add(nfsTestItem);
							}
						}
					}

					sampleGroupingNumber++;

					currentAccessionNumber = analysisAccessionNumber;
					testIdList = new ArrayList<>();
					nfsTestItemList = new ArrayList<>();
					isNFSTest = false;

					subjectNumber = getSubjectNumber(analysis);
					patientName = getPatientName(analysis);
					nextVisit = ObservationHistoryServiceImpl.getValueForSample(ObservationType.NEXT_VISIT_DATE,
							sample.getId());
				}

				AnalysisServiceImpl analysisServiceOld = new AnalysisServiceImpl(analysis);
				testResultItem = new TestResultItem();
				testResultItem.setTestName(analysisServiceOld.getTestDisplayName());
				testResultItem.setAccessionNumber(currentAccessionNumber);
				testResultItem.setReceivedDate(getReceivedDateDisplay(sample));
				testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
				testResultItem.setTestId(analysis.getTest().getId());
				testResultItem.setNonconforming(QAService.isAnalysisParentNonConforming(analysis));

				if (FormFields.getInstance().useField(Field.QaEventsBySection)) {
					testResultItem.setNonconforming(getQaEventByTestSection(analysis));
				}

				testResultItem.setPatientInfo(subjectNumber);
				testResultItem.setNextVisitDate(nextVisit);
				if (isPatientNameAdded()) {
					testResultItem.setPatientName(patientName);
				}
				testIdList.add(testResultItem.getTestId());

				if (nfsTestIdList.contains(testResultItem.getTestId())) {
					isNFSTest = true;
					nfsTestItemList.add(testResultItem);
				}
				if (isNFSTest) {

					if (allNFSTestsRequested(testIdList)) {
						testResultItem.setTestName("NFS");
						workplanTestList.add(testResultItem);
					} else if (!nfsTestItemList.isEmpty() && (testList.size() - 1) == testList.indexOf(analysis)) {
						// add nfs subtests
						for (TestResultItem nfsTestItem : nfsTestItemList) {
							workplanTestList.add(nfsTestItem);
						}
					}
				} else {
					workplanTestList.add(testResultItem);
				}
			}

		}

		return workplanTestList;
	}

	private void addPatientNamesToList(List<TestResultItem> workplanTestList) {
		String currentAccessionNumber = new String();
		int sampleGroupingNumber = 0;

		int newIndex = 0;
		int newElementsAdded = 0;
		int workplanTestListOrigSize = workplanTestList.size();

		for (int i = 0; newIndex < (workplanTestListOrigSize + newElementsAdded); i++) {

			TestResultItem testResultItem = workplanTestList.get(newIndex);

			if (!testResultItem.getAccessionNumber().equals(currentAccessionNumber)) {
				sampleGroupingNumber++;
				if (isPatientNameAdded()) {
					addPatientNameToList(testResultItem, workplanTestList, newIndex, sampleGroupingNumber);
					newIndex++;
					newElementsAdded++;
				}

				currentAccessionNumber = testResultItem.getAccessionNumber();
			}
			testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
			newIndex++;
		}

	}

	private void addPatientNameToList(TestResultItem firstTestResultItem, List<TestResultItem> workplanTestList,
			int insertPosition, int sampleGroupingNumber) {
		TestResultItem testResultItem = new TestResultItem();
		testResultItem.setAccessionNumber(firstTestResultItem.getAccessionNumber());
		testResultItem.setPatientInfo(firstTestResultItem.getPatientInfo());
		testResultItem.setReceivedDate(firstTestResultItem.getReceivedDate());
		// Add Patient Name to top of test list
		testResultItem.setTestName(firstTestResultItem.getPatientName());
		testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
		testResultItem.setServingAsTestGroupIdentifier(true);
		workplanTestList.add(insertPosition, testResultItem);

	}

	private boolean isPatientNameAdded() {
		return ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP");
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
	protected String getMessageForKey(HttpServletRequest request, String messageKey) throws Exception {
		if (GenericValidator.isBlankOrNull(request.getParameter("type"))) {
			return MessageUtil.getMessage(messageKey, MessageUtil.getMessage("workplan.unit.types"));
		}
		return MessageUtil.getMessage(messageKey, request.getParameter("type"));
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "workplanByTestSectionDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "workplan.page.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "workplan.page.title";
	}
}
