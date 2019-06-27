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
import spring.service.dictionary.DictionaryService;
import spring.service.localization.LocalizationService;
import spring.service.localization.LocalizationServiceImpl;
import spring.service.panel.PanelService;
import spring.service.resultlimit.ResultLimitService;
import spring.service.test.TestSectionService;
import spring.service.test.TestService;
import spring.service.test.TestServiceImpl;
import spring.service.testconfiguration.TestModifyService;
import spring.service.testresult.TestResultService;
import spring.service.typeofsample.TypeOfSampleService;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.service.unitofmeasure.UnitOfMeasureService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testconfiguration.beans.ResultLimitBean;
import us.mn.state.health.lims.testconfiguration.beans.TestCatalogBean;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Controller
public class TestModifyEntryController extends BaseController {

	@Autowired
	private TestModifyEntryFormValidator formValidator;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private PanelService panelService;
	@Autowired
	private TypeOfSampleService typeOfSampleService;
	@Autowired
	private TestService testService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private UnitOfMeasureService unitOfMeasureService;
	@Autowired
	private TestModifyService testModifyService;
	@Autowired
	private LocalizationService localizationService;
	@Autowired
	private TestSectionService testSectionService;

	@RequestMapping(value = "/TestModifyEntry", method = RequestMethod.GET)
	public ModelAndView showTestModifyEntry(HttpServletRequest request) {

		TestModifyEntryForm form = new TestModifyEntryForm();
		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(TestModifyEntryForm form) {

		List<IdValuePair> allSampleTypesList = new ArrayList<>();
		allSampleTypesList.addAll(DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
		allSampleTypesList.addAll(DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_INACTIVE));

		try {
			PropertyUtils.setProperty(form, "sampleTypeList", allSampleTypesList);
			PropertyUtils.setProperty(form, "panelList", DisplayListService.getInstance().getList(ListType.PANELS));
			PropertyUtils.setProperty(form, "resultTypeList",
					DisplayListService.getInstance().getList(ListType.RESULT_TYPE_LOCALIZED));
			PropertyUtils.setProperty(form, "uomList",
					DisplayListService.getInstance().getList(ListType.UNIT_OF_MEASURE));
			PropertyUtils.setProperty(form, "labUnitList",
					DisplayListService.getInstance().getList(ListType.TEST_SECTION));
			PropertyUtils.setProperty(form, "ageRangeList",
					SpringContext.getBean(ResultLimitService.class).getPredefinedAgeRanges());
			PropertyUtils.setProperty(form, "dictionaryList",
					DisplayListService.getInstance().getList(ListType.DICTIONARY_TEST_RESULTS));
			PropertyUtils.setProperty(form, "groupedDictionaryList", createGroupedDictionaryList());
			PropertyUtils.setProperty(form, "testList",
					DisplayListService.getInstance().getFreshList(DisplayListService.ListType.ALL_TESTS));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// gnr: ALL_TESTS calls getActiveTests, this could be a way to enable
		// maintenance of inactive tests
		// PropertyUtils.setProperty( form, "testListInactive",
		// DisplayListService.getInstance().getList(
		// DisplayListService.ListType.ALL_TESTS_INACTIVE )
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

		List<Test> testList = testService.getAllTests(false);

		for (Test test : testList) {

			TestCatalogBean bean = new TestCatalogBean();
			TestService testTestService = SpringContext.getBean(TestService.class);
			testTestService.setTest(test);
			String resultType = testTestService.getResultType();
			bean.setId(test.getId());
			bean.setEnglishName(test.getLocalizedTestName().getEnglish());
			bean.setFrenchName(test.getLocalizedTestName().getFrench());
			bean.setEnglishReportName(test.getLocalizedReportingName().getEnglish());
			bean.setFrenchReportName(test.getLocalizedReportingName().getFrench());
			bean.setTestSortOrder(Integer.parseInt(test.getSortOrder()));
			bean.setTestUnit(testTestService.getTestSectionName());
			bean.setPanel(createPanelList(testTestService));
			bean.setResultType(resultType);
			TypeOfSample typeOfSample = testTestService.getTypeOfSample();
			bean.setSampleType(typeOfSample != null ? typeOfSample.getLocalizedName() : "n/a");
			bean.setOrderable(test.getOrderable() ? "Orderable" : "Not orderable");
			bean.setLoinc(test.getLoinc());
			bean.setActive(test.isActive() ? "Active" : "Not active");
			bean.setUom(testTestService.getUOM(false));
			if (TypeOfTestResultServiceImpl.ResultType.NUMERIC.matches(resultType)) {
				bean.setSignificantDigits(testTestService.getPossibleTestResults().get(0).getSignificantDigits());
				bean.setHasLimitValues(true);
				bean.setResultLimits(getResultLimits(test, bean.getSignificantDigits()));
			}
			bean.setHasDictionaryValues(
					TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(bean.getResultType()));
			if (bean.isHasDictionaryValues()) {
				bean.setDictionaryValues(createDictionaryValues(testTestService));
				bean.setReferenceValue(createReferenceValueForDictionaryType(test));
				bean.setDictionaryIds(createDictionaryIds(testTestService));
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

		List<ResultLimit> resultLimitList = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

		Collections.sort(resultLimitList, new Comparator<ResultLimit>() {
			@Override
			public int compare(ResultLimit o1, ResultLimit o2) {
				return (int) (o1.getMinAge() - o2.getMinAge());
			}
		});

		for (ResultLimit limit : resultLimitList) {
			ResultLimitBean bean = new ResultLimitBean();
			bean.setNormalRange(SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(limit,
					significantDigits, "-"));
			bean.setValidRange(SpringContext.getBean(ResultLimitService.class).getDisplayValidRange(limit,
					significantDigits, "-"));
			bean.setGender(limit.getGender());
			bean.setAgeRange(SpringContext.getBean(ResultLimitService.class).getDisplayAgeRange(limit, "-"));
			limitBeans.add(bean);
		}
		return limitBeans;
	}

	private String createReferenceValueForDictionaryType(Test test) {
		List<ResultLimit> resultLimits = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

		if (resultLimits.isEmpty()) {
			return "n/a";
		}

		return SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(resultLimits.get(0), null,
				null);

	}

	private List<String> createDictionaryValues(TestService testTestService) {
		List<String> dictionaryList = new ArrayList<>();
		List<TestResult> testResultList = testTestService.getPossibleTestResults();
		for (TestResult testResult : testResultList) {
			CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryValue(testResult));
		}

		return dictionaryList;
	}

	private String getDictionaryValue(TestResult testResult) {

		if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
			Dictionary dictionary = dictionaryService.getDataForId(testResult.getValue());
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
		List<ResultLimit> resultLimits = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

		if (resultLimits.isEmpty()) {
			return "n/a";
		}

		return SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(resultLimits.get(0), null,
				null);
	}

	private List<String> createDictionaryIds(TestService testTestService) {
		List<String> dictionaryList = new ArrayList<>();
		List<TestResult> testResultList = testTestService.getPossibleTestResults();
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

		if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
			Dictionary dictionary = dictionaryService.getDataForId(testResult.getValue());
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

	private String createPanelList(TestService testTestService) {
		StringBuilder builder = new StringBuilder();

		List<Panel> panelList = testTestService.getPanels();
		for (Panel panel : panelList) {
			builder.append(localizationService.getLocalizedValueById(panel.getLocalization().getId()));
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
		List<TestResult> testResults = testResultService.getAllTestResults();

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
			if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
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
		for (String group : dictionaryIdGroups) {
			List<IdValuePair> dictionaryPairs = new ArrayList<>();
			for (String id : group.split(",")) {
				Dictionary dictionary = dictionaryService.getDictionaryById(id);
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
			setupDisplayItems(form);
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

		try {
			testModifyService.updateTestSets(testSets, testAddParams, nameLocalization, reportingNameLocalization,
					currentUserId);
		} catch (HibernateException lre) {
			lre.printStackTrace();
			result.reject("error.hibernate.exception");
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		TestServiceImpl.refreshTestNames();
		SpringContext.getBean(TypeOfSampleService.class).clearCache();

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void createPanelItems(ArrayList<PanelItem> panelItems, TestAddParams testAddParams) {
		for (String panelId : testAddParams.panelList) {
			PanelItem panelItem = new PanelItem();
			panelItem.setPanel(panelService.getPanelById(panelId));
			panelItems.add(panelItem);
		}
	}

	private void createTestResults(ArrayList<TestResult> testResults, String significantDigits,
			TestAddParams testAddParams) {
		TypeOfTestResultServiceImpl.ResultType type = TypeOfTestResultServiceImpl.getInstance()
				.getResultTypeById(testAddParams.resultTypeId);

		if (TypeOfTestResultServiceImpl.ResultType.isTextOnlyVariant(type)
				|| TypeOfTestResultServiceImpl.ResultType.isNumeric(type)) {
			TestResult testResult = new TestResult();
			testResult.setTestResultType(type.getCharacterValue());
			testResult.setSortOrder("1");
			testResult.setIsActive(true);
			testResult.setSignificantDigits(significantDigits);
			testResults.add(testResult);
		} else if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(type.getCharacterValue())) {
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
		return LocalizationServiceImpl.createNewLocalization(testAddParams.testNameEnglish,
				testAddParams.testNameFrench, LocalizationServiceImpl.LocalizationType.TEST_NAME);
	}

	private Localization createReportingNameLocalization(TestAddParams testAddParams) {
		return LocalizationServiceImpl.createNewLocalization(testAddParams.testReportNameEnglish,
				testAddParams.testReportNameFrench, LocalizationServiceImpl.LocalizationType.REPORTING_TEST_NAME);
	}

	private List<TestSet> createTestSets(TestAddParams testAddParams) {
		Double lowValid = null;
		Double highValid = null;
		String significantDigits = testAddParams.significantDigits;
		boolean numericResults = TypeOfTestResultServiceImpl.ResultType.isNumericById(testAddParams.resultTypeId);
		boolean dictionaryResults = TypeOfTestResultServiceImpl.ResultType
				.isDictionaryVarientById(testAddParams.resultTypeId);
		List<TestSet> testSets = new ArrayList<>();
		UnitOfMeasure uom = null;
		if (!GenericValidator.isBlankOrNull(testAddParams.uomId) || "0".equals(testAddParams.uomId)) {
			uom = unitOfMeasureService.getUnitOfMeasureById(testAddParams.uomId);
		}
		TestSection testSection = testSectionService.get(testAddParams.testSectionId);

		if (numericResults) {
			lowValid = StringUtil.doubleWithInfinity(testAddParams.lowValid);
			highValid = StringUtil.doubleWithInfinity(testAddParams.highValid);
		}
		// The number of test sets depend on the number of sampleTypes
		for (int i = 0; i < testAddParams.sampleList.size(); i++) {
			TypeOfSample typeOfSample = typeOfSampleService
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
					Test orderedTest = SpringContext.getBean(TestService.class).get(orderedTests.get(j));
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
			if (TypeOfTestResultServiceImpl.ResultType.isNumericById(testAddParams.resultTypeId)) {
				testAddParams.lowValid = (String) obj.get("lowValid");
				testAddParams.highValid = (String) obj.get("highValid");
				testAddParams.significantDigits = (String) obj.get("significantDigits");
				extractLimits(obj, parser, testAddParams);
			} else if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVarientById(testAddParams.resultTypeId)) {
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
		public String testId;
		public String testNameEnglish;
		public String testNameFrench;
		public String testReportNameEnglish;
		public String testReportNameFrench;
		String testSectionId;
		ArrayList<String> panelList = new ArrayList<>();
		String uomId;
		public String loinc;
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
		public Test test;
		public TypeOfSampleTest sampleTypeTest;
		public ArrayList<Test> sortedTests = new ArrayList<>();
		public ArrayList<PanelItem> panelItems = new ArrayList<>();
		public ArrayList<TestResult> testResults = new ArrayList<>();
		public ArrayList<ResultLimit> resultLimits = new ArrayList<>();
	}

	public class DictionaryParams {
		public String dictionaryId;
		public boolean isQuantifiable = false;
	}
}
