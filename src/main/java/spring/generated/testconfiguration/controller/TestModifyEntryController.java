package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.TestModifyEntryForm;
import spring.generated.testconfiguration.validator.TestModifyEntryFormValidator;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.TestSectionService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.daoimpl.PanelItemDAOImpl;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testconfiguration.beans.ResultLimitBean;
import us.mn.state.health.lims.testconfiguration.beans.TestCatalogBean;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Controller
public class TestModifyEntryController extends BaseController {

	@Autowired
	TestModifyEntryFormValidator formValidator;

	private DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	private TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

	@RequestMapping(value = "/TestModifyEntry", method = RequestMethod.GET)
	public ModelAndView showTestModifyEntry(HttpServletRequest request) {

		TestModifyEntryForm form = new TestModifyEntryForm();
		setupDisaplyItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisaplyItems(TestModifyEntryForm form) {

		List<IdValuePair> allSampleTypesList = new ArrayList<>();
		allSampleTypesList.addAll(DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
		allSampleTypesList.addAll(DisplayListService.getList(ListType.SAMPLE_TYPE_INACTIVE));

		try {
			PropertyUtils.setProperty(form, "sampleTypeList", allSampleTypesList);
			PropertyUtils.setProperty(form, "panelList", DisplayListService.getList(ListType.PANELS));
			PropertyUtils.setProperty(form, "resultTypeList",
					DisplayListService.getList(ListType.RESULT_TYPE_LOCALIZED));
			PropertyUtils.setProperty(form, "uomList", DisplayListService.getList(ListType.UNIT_OF_MEASURE));
			PropertyUtils.setProperty(form, "labUnitList", DisplayListService.getList(ListType.TEST_SECTION));
			PropertyUtils.setProperty(form, "ageRangeList", ResultLimitService.getPredefinedAgeRanges());
			PropertyUtils.setProperty(form, "dictionaryList",
					DisplayListService.getList(ListType.DICTIONARY_TEST_RESULTS));
			PropertyUtils.setProperty(form, "groupedDictionaryList", createGroupedDictionaryList());
			PropertyUtils.setProperty(form, "testList",
					DisplayListService.getFreshList(DisplayListService.ListType.ALL_TESTS));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// gnr: ALL_TESTS calls getActiveTests, this could be a way to enable
		// maintenance of inactive tests
		// PropertyUtils.setProperty( form, "testListInactive",
		// DisplayListService.getList( DisplayListService.ListType.ALL_TESTS_INACTIVE )
		// );

		List<TestCatalogBean> testCatBeanList = createTestCatBeanList();
		try {
			PropertyUtils.setProperty(form, "testCatBeanList", testCatBeanList);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<TestCatalogBean> createTestCatBeanList() {
		List<TestCatalogBean> beanList = new ArrayList<>();

		List<Test> testList = new TestDAOImpl().getAllTests(false);

		for (Test test : testList) {

			TestCatalogBean bean = new TestCatalogBean();
			TestService testService = new TestService(test);
			String resultType = testService.getResultType();
			bean.setId(test.getId());
			bean.setEnglishName(test.getLocalizedTestName().getEnglish());
			bean.setFrenchName(test.getLocalizedTestName().getFrench());
			bean.setEnglishReportName(test.getLocalizedReportingName().getEnglish());
			bean.setFrenchReportName(test.getLocalizedReportingName().getFrench());
			bean.setTestSortOrder(Integer.parseInt(test.getSortOrder()));
			bean.setTestUnit(testService.getTestSectionName());
			bean.setPanel(createPanelList(testService));
			bean.setResultType(resultType);
			TypeOfSample typeOfSample = testService.getTypeOfSample();
			bean.setSampleType(typeOfSample != null ? typeOfSample.getLocalizedName() : "n/a");
			bean.setOrderable(test.getOrderable() ? "Orderable" : "Not orderable");
			bean.setLoinc(test.getLoinc());
			bean.setActive(test.isActive() ? "Active" : "Not active");
			bean.setUom(testService.getUOM(false));
			if (TypeOfTestResultService.ResultType.NUMERIC.matches(resultType)) {
				bean.setSignificantDigits(testService.getPossibleTestResults().get(0).getSignificantDigits());
				bean.setHasLimitValues(true);
				bean.setResultLimits(getResultLimits(test, bean.getSignificantDigits()));
			}
			bean.setHasDictionaryValues(TypeOfTestResultService.ResultType.isDictionaryVariant(bean.getResultType()));
			if (bean.isHasDictionaryValues()) {
				bean.setDictionaryValues(createDictionaryValues(testService));
				bean.setReferenceValue(createReferenceValueForDictionaryType(test));
				bean.setDictionaryIds(createDictionaryIds(testService));
				bean.setReferenceId(createReferenceIdForDictionaryType(test));
				bean.setReferenceId(getDictionaryIdByDictEntry(bean.getReferenceValue(), bean.getDictionaryIds(),
						bean.getDictionaryValues()));
			}
			beanList.add(bean);
		}

		Collections.sort(beanList, new Comparator<TestCatalogBean>() {
			@Override
			public int compare(TestCatalogBean o1, TestCatalogBean o2) {
				// sort by test section, sample type, panel, sort order
				int comparison = o1.getTestUnit().compareTo(o2.getTestUnit());
				if (comparison != 0) {
					return comparison;
				}

				comparison = o1.getSampleType().compareTo(o2.getSampleType());
				if (comparison != 0) {
					return comparison;
				}

				comparison = o1.getPanel().compareTo(o2.getPanel());
				if (comparison != 0) {
					return comparison;
				}

				return o1.getTestSortOrder() - o2.getTestSortOrder();
			}
		});

		return beanList;
	}

	private List<ResultLimitBean> getResultLimits(Test test, String significantDigits) {
		List<ResultLimitBean> limitBeans = new ArrayList<>();

		List<ResultLimit> resultLimitList = ResultLimitService.getResultLimits(test);

		Collections.sort(resultLimitList, new Comparator<ResultLimit>() {
			@Override
			public int compare(ResultLimit o1, ResultLimit o2) {
				return (int) (o1.getMinAge() - o2.getMinAge());
			}
		});

		for (ResultLimit limit : resultLimitList) {
			ResultLimitBean bean = new ResultLimitBean();
			bean.setNormalRange(ResultLimitService.getDisplayReferenceRange(limit, significantDigits, "-"));
			bean.setValidRange(ResultLimitService.getDisplayValidRange(limit, significantDigits, "-"));
			bean.setGender(limit.getGender());
			bean.setAgeRange(ResultLimitService.getDisplayAgeRange(limit, "-"));
			limitBeans.add(bean);
		}
		return limitBeans;
	}

	private String createReferenceValueForDictionaryType(Test test) {
		List<ResultLimit> resultLimits = ResultLimitService.getResultLimits(test);

		if (resultLimits.isEmpty()) {
			return "n/a";
		}

		return ResultLimitService.getDisplayReferenceRange(resultLimits.get(0), null, null);

	}

	private List<String> createDictionaryValues(TestService testService) {
		List<String> dictionaryList = new ArrayList<>();
		List<TestResult> testResultList = testService.getPossibleTestResults();
		for (TestResult testResult : testResultList) {
			CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryValue(testResult));
		}

		return dictionaryList;
	}

	private String getDictionaryValue(TestResult testResult) {

		if (TypeOfTestResultService.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
			Dictionary dictionary = dictionaryDAO.getDataForId(testResult.getValue());
			String displayValue = dictionary.getLocalizedName();

			if ("unknown".equals(displayValue)) {
				displayValue = !GenericValidator.isBlankOrNull(dictionary.getDictEntry()) ? dictionary.getDictEntry()
						: dictionary.getLocalAbbreviation();
			}

			if (testResult.getIsQuantifiable()) {
				displayValue += " Qualifiable";
			}
			return displayValue;
		}

		return null;
	}

	private String createReferenceIdForDictionaryType(Test test) {
		List<ResultLimit> resultLimits = ResultLimitService.getResultLimits(test);

		if (resultLimits.isEmpty()) {
			return "n/a";
		}

		return ResultLimitService.getDisplayReferenceRange(resultLimits.get(0), null, null);
	}

	private List<String> createDictionaryIds(TestService testService) {
		List<String> dictionaryList = new ArrayList<>();
		List<TestResult> testResultList = testService.getPossibleTestResults();
		for (TestResult testResult : testResultList) {
			CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryId(testResult));
		}

		return dictionaryList;
	}

	private String getDictionaryIdByDictEntry(String dict_entry, List<String> ids, List<String> values) {

		if ("n/a".equals(dict_entry)) {
			return null;
		}

		for (int i = 0; i < ids.size(); i++) {
			if (values.get(i).equals(dict_entry)) {
				return ids.get(i);
			}
		}

		return null;
	}

	private String getDictionaryId(TestResult testResult) {

		if (TypeOfTestResultService.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
			Dictionary dictionary = dictionaryDAO.getDataForId(testResult.getValue());
			String displayId = dictionary.getId();

			if ("unknown".equals(displayId)) {
				displayId = !GenericValidator.isBlankOrNull(dictionary.getDictEntry()) ? dictionary.getDictEntry()
						: dictionary.getLocalAbbreviation();
			}

			if (testResult.getIsQuantifiable()) {
				displayId += " Qualifiable";
			}
			return displayId;
		}

		return null;
	}

	private String createPanelList(TestService testService) {
		StringBuilder builder = new StringBuilder();

		List<Panel> panelList = testService.getPanels();
		for (Panel panel : panelList) {
			builder.append(LocalizationService.getLocalizedValueById(panel.getLocalization().getId()));
			builder.append(", ");
		}

		String panelString = builder.toString();
		if (panelString.isEmpty()) {
			panelString = "None";
		} else {
			panelString = panelString.substring(0, panelString.length() - 2);
		}

		return panelString;
	}

	private List<List<IdValuePair>> createGroupedDictionaryList() {
		List<TestResult> testResults = getSortedTestResults();

		HashSet<String> dictionaryIdGroups = getDictionaryIdGroups(testResults);

		return getGroupedDictionaryPairs(dictionaryIdGroups);
	}

	@SuppressWarnings("unchecked")
	private List<TestResult> getSortedTestResults() {
		List<TestResult> testResults = new TestResultDAOImpl().getAllTestResults();

		Collections.sort(testResults, new Comparator<TestResult>() {
			@Override
			public int compare(TestResult o1, TestResult o2) {
				int result = o1.getTest().getId().compareTo(o2.getTest().getId());

				if (result != 0) {
					return result;
				}

				return GenericValidator.isBlankOrNull(o1.getSortOrder()) ? 0
						: Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
			}
		});
		return testResults;
	}

	private HashSet<String> getDictionaryIdGroups(List<TestResult> testResults) {
		HashSet<String> dictionaryIdGroups = new HashSet<>();
		String currentTestId = null;
		String dictionaryIdGroup = null;
		for (TestResult testResult : testResults) {
			if (TypeOfTestResultService.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
				if (testResult.getTest().getId().equals(currentTestId)) {
					dictionaryIdGroup += "," + testResult.getValue();
				} else {
					currentTestId = testResult.getTest().getId();
					if (dictionaryIdGroup != null) {
						dictionaryIdGroups.add(dictionaryIdGroup);
					}

					dictionaryIdGroup = testResult.getValue();
				}

			}
		}

		if (dictionaryIdGroup != null) {
			dictionaryIdGroups.add(dictionaryIdGroup);
		}

		return dictionaryIdGroups;
	}

	private List<List<IdValuePair>> getGroupedDictionaryPairs(HashSet<String> dictionaryIdGroups) {
		List<List<IdValuePair>> groups = new ArrayList<>();
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		for (String group : dictionaryIdGroups) {
			List<IdValuePair> dictionaryPairs = new ArrayList<>();
			for (String id : group.split(",")) {
				Dictionary dictionary = dictionaryDAO.getDictionaryById(id);
				if (dictionary != null) {
					dictionaryPairs.add(new IdValuePair(id, dictionary.getLocalizedName()));
				}
			}
			groups.add(dictionaryPairs);
		}

		Collections.sort(groups, new Comparator<List<IdValuePair>>() {
			@Override
			public int compare(List<IdValuePair> o1, List<IdValuePair> o2) {
				return o1.size() - o2.size();
			}
		});
		return groups;
	}

	@RequestMapping(value = "/TestModifyEntry", method = RequestMethod.POST)
	public ModelAndView postTestModifyEntry(HttpServletRequest request,
			@ModelAttribute("form") @Valid TestModifyEntryForm form, BindingResult result) {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisaplyItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}
		String currentUserId = getSysUserId(request);
		String changeList = form.getString("jsonWad");

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(changeList);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		TestAddParams testAddParams = extractTestAddParms(obj, parser);

		Localization nameLocalization = createNameLocalization(testAddParams);
		Localization reportingNameLocalization = createReportingNameLocalization(testAddParams);

		List<TestSet> testSets = createTestSets(testAddParams);
		TestDAO testDAO = new TestDAOImpl();

		TypeOfSampleTestDAO typeOfSampleTestDAO = new TypeOfSampleTestDAOImpl();
		PanelItemDAO panelItemDAO = new PanelItemDAOImpl();

		TestResultDAO testResultDAO = new TestResultDAOImpl();
		ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			List<TypeOfSampleTest> typeOfSampleTest = typeOfSampleTestDAO
					.getTypeOfSampleTestsForTest(testAddParams.testId);
			String[] typeOfSamplesTestIDs = new String[typeOfSampleTest.size()];
			for (int i = 0; i < typeOfSampleTest.size(); i++) {
				typeOfSamplesTestIDs[i] = typeOfSampleTest.get(i).getId();
			}
			typeOfSampleTestDAO.deleteData(typeOfSamplesTestIDs, currentUserId);

			List<PanelItem> panelItems = panelItemDAO.getPanelItemByTestId(testAddParams.testId);
			for (PanelItem item : panelItems) {
				item.setSysUserId(currentUserId);
			}
			panelItemDAO.deleteData(panelItems);

			List<ResultLimit> resultLimitItems = resultLimitDAO.getAllResultLimitsForTest(testAddParams.testId);
			for (ResultLimit item : resultLimitItems) {
				item.setSysUserId(currentUserId);
			}
			resultLimitDAO.deleteData(resultLimitItems);

			for (TestSet set : testSets) {
				set.test.setSysUserId(currentUserId);
				set.test.setLocalizedTestName(nameLocalization);
				set.test.setLocalizedReportingName(reportingNameLocalization);

//	gnr: based on testAddUpdate,
//  added existing testId to process in createTestSets using testAddParams.testId, delete then insert to modify for most elements

				for (Test test : set.sortedTests) {
					test.setSysUserId(currentUserId);
					// if (!test.getId().equals( set.test.getId() )) {
					testDAO.updateData(test);
					// }
				}

				updateTestNames(testAddParams.testId, nameLocalization.getEnglish(), nameLocalization.getFrench(),
						reportingNameLocalization.getEnglish(), reportingNameLocalization.getFrench(), currentUserId);
				updateTestEntities(testAddParams.testId, testAddParams.loinc, currentUserId);

				set.sampleTypeTest.setSysUserId(currentUserId);
				set.sampleTypeTest.setTestId(set.test.getId());
				typeOfSampleTestDAO.insertData(set.sampleTypeTest);

				for (PanelItem item : set.panelItems) {
					item.setSysUserId(currentUserId);
					Test nonTransiantTest = testDAO.getTestById(set.test.getId());
					item.setTest(nonTransiantTest);
					panelItemDAO.insertData(item);
				}

				for (TestResult testResult : set.testResults) {
					testResult.setSysUserId(currentUserId);
					Test nonTransiantTest = testDAO.getTestById(set.test.getId());
					testResult.setTest(nonTransiantTest);
					testResultDAO.insertData(testResult);
				}

				for (ResultLimit resultLimit : set.resultLimits) {
					resultLimit.setSysUserId(currentUserId);
					resultLimit.setTestId(set.test.getId());
					resultLimitDAO.insertData(resultLimit);
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			result.reject("error.hibernate.exception");
			setupDisaplyItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		} finally {
			HibernateUtil.closeSession();
		}

		TestService.refreshTestNames();
		TypeOfSampleService.clearCache();

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void updateTestEntities(String testId, String loinc, String userId) {
		Test test = new TestService(testId).getTest();

		if (test != null) {
			test.setSysUserId(userId);
			test.setLoinc(loinc);
			new TestDAOImpl().updateData(test);
		}
	}

	private void updateTestNames(String testId, String nameEnglish, String nameFrench, String reportNameEnglish,
			String reportNameFrench, String userId) {
		Test test = new TestService(testId).getTest();

		if (test != null) {
			Localization name = test.getLocalizedTestName();
			Localization reportingName = test.getLocalizedReportingName();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);
			reportingName.setEnglish(reportNameEnglish.trim());
			reportingName.setFrench(reportNameFrench.trim());
			reportingName.setSysUserId(userId);

			new LocalizationDAOImpl().updateData(name);
			new LocalizationDAOImpl().updateData(reportingName);

		}

		// Refresh test names
		DisplayListService.getFreshList(DisplayListService.ListType.ALL_TESTS);
		DisplayListService.getFreshList(DisplayListService.ListType.ORDERABLE_TESTS);
	}

	private void createPanelItems(ArrayList<PanelItem> panelItems, TestAddParams testAddParams) {
		PanelDAOImpl panelDAO = new PanelDAOImpl();
		for (String panelId : testAddParams.panelList) {
			PanelItem panelItem = new PanelItem();
			panelItem.setPanel(panelDAO.getPanelById(panelId));
			panelItems.add(panelItem);
		}
	}

	private void createTestResults(ArrayList<TestResult> testResults, String significantDigits,
			TestAddParams testAddParams) {
		TypeOfTestResultService.ResultType type = TypeOfTestResultService.getResultTypeById(testAddParams.resultTypeId);

		if (TypeOfTestResultService.ResultType.isTextOnlyVariant(type)
				|| TypeOfTestResultService.ResultType.isNumeric(type)) {
			TestResult testResult = new TestResult();
			testResult.setTestResultType(type.getCharacterValue());
			testResult.setSortOrder("1");
			testResult.setIsActive(true);
			testResult.setSignificantDigits(significantDigits);
			testResults.add(testResult);
		} else if (TypeOfTestResultService.ResultType.isDictionaryVariant(type.getCharacterValue())) {
			int sortOrder = 10;
			for (DictionaryParams params : testAddParams.dictionaryParamList) {
				TestResult testResult = new TestResult();
				testResult.setTestResultType(type.getCharacterValue());
				testResult.setSortOrder(String.valueOf(sortOrder));
				sortOrder += 10;
				testResult.setIsActive(true);
				testResult.setValue(params.dictionaryId);
				testResult.setIsQuantifiable(params.isQuantifiable);
				testResults.add(testResult);
			}
		}
	}

	private Localization createNameLocalization(TestAddParams testAddParams) {
		return LocalizationService.createNewLocalization(testAddParams.testNameEnglish, testAddParams.testNameFrench,
				LocalizationService.LocalizationType.TEST_NAME);
	}

	private Localization createReportingNameLocalization(TestAddParams testAddParams) {
		return LocalizationService.createNewLocalization(testAddParams.testReportNameEnglish,
				testAddParams.testReportNameFrench, LocalizationService.LocalizationType.REPORTING_TEST_NAME);
	}

	private List<TestSet> createTestSets(TestAddParams testAddParams) {
		Double lowValid = null;
		Double highValid = null;
		String significantDigits = testAddParams.significantDigits;
		boolean numericResults = TypeOfTestResultService.ResultType.isNumericById(testAddParams.resultTypeId);
		boolean dictionaryResults = TypeOfTestResultService.ResultType
				.isDictionaryVarientById(testAddParams.resultTypeId);
		List<TestSet> testSets = new ArrayList<>();
		UnitOfMeasure uom = null;
		if (!GenericValidator.isBlankOrNull(testAddParams.uomId) || "0".equals(testAddParams.uomId)) {
			uom = new UnitOfMeasureDAOImpl().getUnitOfMeasureById(testAddParams.uomId);
		}
		TestSection testSection = new TestSectionService(testAddParams.testSectionId).getTestSection();

		if (numericResults) {
			lowValid = StringUtil.doubleWithInfinity(testAddParams.lowValid);
			highValid = StringUtil.doubleWithInfinity(testAddParams.highValid);
		}
		// The number of test sets depend on the number of sampleTypes
		for (int i = 0; i < testAddParams.sampleList.size(); i++) {
			TypeOfSample typeOfSample = typeOfSampleDAO
					.getTypeOfSampleById(testAddParams.sampleList.get(i).sampleTypeId);
			if (typeOfSample == null) {
				continue;
			}
			TestSet testSet = new TestSet();
			Test test = new Test();

			test.setId(testAddParams.testId);

			test.setUnitOfMeasure(uom);
			test.setDescription(testAddParams.testNameEnglish + "(" + typeOfSample.getDescription() + ")");
			test.setTestName(testAddParams.testNameEnglish);
			test.setLocalCode(testAddParams.testNameEnglish);
			test.setIsActive(testAddParams.active);
			test.setOrderable("Y".equals(testAddParams.orderable));
			test.setIsReportable("N");
			test.setTestSection(testSection);
			test.setGuid(String.valueOf(UUID.randomUUID()));
			ArrayList<String> orderedTests = testAddParams.sampleList.get(i).orderedTests;
			for (int j = 0; j < orderedTests.size(); j++) {
				if ("0".equals(orderedTests.get(j))) {
					test.setSortOrder(String.valueOf(j));
				} else {
					Test orderedTest = new TestService(orderedTests.get(j)).getTest();
					orderedTest.setSortOrder(String.valueOf(j));
					testSet.sortedTests.add(orderedTest);
				}
			}

			testSet.test = test;

			TypeOfSampleTest typeOfSampleTest = new TypeOfSampleTest();
			typeOfSampleTest.setTypeOfSampleId(typeOfSample.getId());
			testSet.sampleTypeTest = typeOfSampleTest;

			createPanelItems(testSet.panelItems, testAddParams);
			createTestResults(testSet.testResults, significantDigits, testAddParams);
			if (numericResults) {
				testSet.resultLimits = createResultLimits(lowValid, highValid, testAddParams);
			} else if (dictionaryResults) {
				testSet.resultLimits = createDictionaryResultLimit(testAddParams);
			}

			testSets.add(testSet);
		}

		return testSets;
	}

	private ArrayList<ResultLimit> createDictionaryResultLimit(TestAddParams testAddParams) {
		ArrayList<ResultLimit> resultLimits = new ArrayList<>();
		if (!GenericValidator.isBlankOrNull(testAddParams.dictionaryReferenceId)) {
			ResultLimit limit = new ResultLimit();
			limit.setResultTypeId(testAddParams.resultTypeId);
			limit.setDictionaryNormalId(testAddParams.dictionaryReferenceId);
			resultLimits.add(limit);
		}

		return resultLimits;
	}

	private ArrayList<ResultLimit> createResultLimits(Double lowValid, Double highValid, TestAddParams testAddParams) {
		ArrayList<ResultLimit> resultLimits = new ArrayList<>();
		for (ResultLimitParams params : testAddParams.limits) {
			ResultLimit limit = new ResultLimit();
			limit.setResultTypeId(testAddParams.resultTypeId);
			limit.setGender(params.gender);
			limit.setMinAge(StringUtil.doubleWithInfinity(params.lowAge));
			limit.setMaxAge(StringUtil.doubleWithInfinity(params.highAge));
			limit.setLowNormal(StringUtil.doubleWithInfinity(params.lowLimit));
			limit.setHighNormal(StringUtil.doubleWithInfinity(params.highLimit));
			limit.setLowValid(lowValid);
			limit.setHighValid(highValid);
			resultLimits.add(limit);
		}

		return resultLimits;
	}

	private TestAddParams extractTestAddParms(JSONObject obj, JSONParser parser) {
		TestAddParams testAddParams = new TestAddParams();
		try {

			testAddParams.testId = (String) obj.get("testId");
			testAddParams.testNameEnglish = (String) obj.get("testNameEnglish");
			testAddParams.testNameFrench = (String) obj.get("testNameFrench");
			testAddParams.testReportNameEnglish = (String) obj.get("testReportNameEnglish");
			testAddParams.testReportNameFrench = (String) obj.get("testReportNameFrench");
			testAddParams.testSectionId = (String) obj.get("testSection");
			testAddParams.dictionaryReferenceId = (String) obj.get("dictionaryReference");
			extractPanels(obj, parser, testAddParams);
			testAddParams.uomId = (String) obj.get("uom");
			testAddParams.loinc = (String) obj.get("loinc");
			testAddParams.resultTypeId = (String) obj.get("resultType");
			extractSampleTypes(obj, parser, testAddParams);
			testAddParams.active = (String) obj.get("active");
			testAddParams.orderable = (String) obj.get("orderable");
			if (TypeOfTestResultService.ResultType.isNumericById(testAddParams.resultTypeId)) {
				testAddParams.lowValid = (String) obj.get("lowValid");
				testAddParams.highValid = (String) obj.get("highValid");
				testAddParams.significantDigits = (String) obj.get("significantDigits");
				extractLimits(obj, parser, testAddParams);
			} else if (TypeOfTestResultService.ResultType.isDictionaryVarientById(testAddParams.resultTypeId)) {
				String dictionary = (String) obj.get("dictionary");
				JSONArray dictionaryArray = (JSONArray) parser.parse(dictionary);
				for (int i = 0; i < dictionaryArray.size(); i++) {
					DictionaryParams params = new DictionaryParams();
					params.dictionaryId = (String) ((JSONObject) dictionaryArray.get(i)).get("value");
					params.isQuantifiable = "Y".equals(((JSONObject) dictionaryArray.get(i)).get("qualified"));
					testAddParams.dictionaryParamList.add(params);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return testAddParams;
	}

	private void extractLimits(JSONObject obj, JSONParser parser, TestAddParams testAddParams) throws ParseException {
		String lowAge = "0";
		String limits = (String) obj.get("resultLimits");
		JSONArray limitArray = (JSONArray) parser.parse(limits);
		for (int i = 0; i < limitArray.size(); i++) {
			ResultLimitParams params = new ResultLimitParams();
			Boolean gender = (Boolean) ((JSONObject) limitArray.get(i)).get("gender");
			if (gender) {
				params.gender = "M";
			}
			String highAge = (String) (((JSONObject) limitArray.get(i)).get("highAgeRange"));
			params.displayRange = (String) (((JSONObject) limitArray.get(i)).get("reportingRange"));
			params.lowLimit = (String) (((JSONObject) limitArray.get(i)).get("lowNormal"));
			params.highLimit = (String) (((JSONObject) limitArray.get(i)).get("highNormal"));
			params.lowAge = lowAge;
			params.highAge = highAge;
			testAddParams.limits.add(params);

			if (gender) {
				params = new ResultLimitParams();
				params.gender = "F";
				params.displayRange = (String) (((JSONObject) limitArray.get(i)).get("reportingRangeFemale"));
				params.lowLimit = (String) (((JSONObject) limitArray.get(i)).get("lowNormalFemale"));
				params.highLimit = (String) (((JSONObject) limitArray.get(i)).get("highNormalFemale"));
				params.lowAge = lowAge;
				params.highAge = highAge;
				testAddParams.limits.add(params);
			}

			lowAge = highAge;
		}
	}

	private void extractPanels(JSONObject obj, JSONParser parser, TestAddParams testAddParams) throws ParseException {
		String panels = (String) obj.get("panels");
		JSONArray panelArray = (JSONArray) parser.parse(panels);

		for (int i = 0; i < panelArray.size(); i++) {
			testAddParams.panelList.add((String) (((JSONObject) panelArray.get(i)).get("id")));
		}

	}

	private void extractSampleTypes(JSONObject obj, JSONParser parser, TestAddParams testAddParams)
			throws ParseException {
		String sampleTypes = (String) obj.get("sampleTypes");
		JSONArray sampleTypeArray = (JSONArray) parser.parse(sampleTypes);

		for (int i = 0; i < sampleTypeArray.size(); i++) {
			SampleTypeListAndTestOrder sampleTypeTests = new SampleTypeListAndTestOrder();
			sampleTypeTests.sampleTypeId = (String) (((JSONObject) sampleTypeArray.get(i)).get("typeId"));

			JSONArray testArray = (JSONArray) (((JSONObject) sampleTypeArray.get(i)).get("tests"));
			for (int j = 0; j < testArray.size(); j++) {
				sampleTypeTests.orderedTests.add(String.valueOf(((JSONObject) testArray.get(j)).get("id")));
			}
			testAddParams.sampleList.add(sampleTypeTests);
		}
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testModifyDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "testModifyDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/TestModifyEntry.do";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}

	public class TestAddParams {
		String testId;
		public String testNameEnglish;
		public String testNameFrench;
		public String testReportNameEnglish;
		public String testReportNameFrench;
		String testSectionId;
		ArrayList<String> panelList = new ArrayList<>();
		String uomId;
		String loinc;
		String resultTypeId;
		ArrayList<SampleTypeListAndTestOrder> sampleList = new ArrayList<>();
		String active;
		String orderable;
		String lowValid;
		String highValid;
		public String significantDigits;
		String dictionaryReferenceId;
		ArrayList<ResultLimitParams> limits = new ArrayList<>();
		public ArrayList<DictionaryParams> dictionaryParamList = new ArrayList<>();
	}

	public class SampleTypeListAndTestOrder {
		String sampleTypeId;
		ArrayList<String> orderedTests = new ArrayList<>();
	}

	public class ResultLimitParams {
		String gender;
		String lowAge;
		String highAge;
		String lowLimit;
		String highLimit;
		String displayRange;
	}

	public class TestSet {
		Test test;
		TypeOfSampleTest sampleTypeTest;
		ArrayList<Test> sortedTests = new ArrayList<>();
		ArrayList<PanelItem> panelItems = new ArrayList<>();
		ArrayList<TestResult> testResults = new ArrayList<>();
		ArrayList<ResultLimit> resultLimits = new ArrayList<>();
	}

	public class DictionaryParams {
		public String dictionaryId;
		public boolean isQuantifiable = false;
	}
}
