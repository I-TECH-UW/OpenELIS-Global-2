package spring.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem.ResultDisplayType;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
@DependsOn({ "springContext" })
public class TestServiceImpl extends BaseObjectServiceImpl<Test> implements TestService, LocaleChangeListener {

	public enum Entity {
		TEST_NAME, TEST_AUGMENTED_NAME, TEST_REPORTING_NAME
	}

	public static final String HIV_TYPE = "HIV_TEST_KIT";
	public static final String SYPHILIS_TYPE = "SYPHILIS_TEST_KIT";
	private static String VARIABLE_TYPE_OF_SAMPLE_ID;
	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
	private static Map<Entity, Map<String, String>> entityToMap;

	@Autowired
	protected static TestDAO testDAO = SpringContext.getBean(TestDAO.class);

	@Autowired
	private static TestResultDAO TEST_RESULT_DAO = SpringContext.getBean(TestResultDAO.class);
	@Autowired
	private static TypeOfSampleDAO TYPE_OF_SAMPLE_DAO = SpringContext.getBean(TypeOfSampleDAO.class);
	@Autowired
	private static TypeOfSampleTestDAO TYPE_OF_SAMPLE_testDAO = SpringContext.getBean(TypeOfSampleTestDAO.class);
	@Autowired
	private PanelItemDAO panelItemDAO = SpringContext.getBean(PanelItemDAO.class);
	@Autowired
	private PanelDAO panelDAO = SpringContext.getBean(PanelDAO.class);

	private Test test;

	@PostConstruct
	public void initialize() {
		SystemConfiguration.getInstance().addLocalChangeListener(this);
	}

	public synchronized void initializeGlobalVariables() {
		TypeOfSample variableTypeOfSample = TYPE_OF_SAMPLE_DAO.getTypeOfSampleByLocalAbbrevAndDomain("Variable", "H");
		VARIABLE_TYPE_OF_SAMPLE_ID = variableTypeOfSample == null ? "-1" : variableTypeOfSample.getId();

		if (entityToMap == null) {
			createEntityMap();
		}
	}

	private synchronized void createEntityMap() {
		entityToMap = new HashMap<>();
		entityToMap.put(Entity.TEST_NAME, createTestIdToNameMap());
		entityToMap.put(Entity.TEST_AUGMENTED_NAME, createTestIdToAugmentedNameMap());
		entityToMap.put(Entity.TEST_REPORTING_NAME, createTestIdToReportingNameMap());

	}

	public TestServiceImpl() {
		super(Test.class);
		initializeGlobalVariables();
	}

	public TestServiceImpl(Test test) {
		this();
		this.test = test;
	}

	public TestServiceImpl(String testId) {
		this();
		test = get(testId);
	}

	@Override
	protected TestDAO getBaseObjectDAO() {
		return testDAO;
	}

	@Transactional
	public static List<Test> getTestsInTestSectionById(String testSectionId) {
		return testDAO.getTestsByTestSectionId(testSectionId);
	}

	@Override
	public void localeChanged(String locale) {
		LANGUAGE_LOCALE = locale;
		refreshTestNames();
	}

	public static void refreshTestNames() {
		entityToMap.put(Entity.TEST_NAME, createTestIdToNameMap());
		entityToMap.put(Entity.TEST_AUGMENTED_NAME, createTestIdToAugmentedNameMap());
		entityToMap.put(Entity.TEST_REPORTING_NAME, createTestIdToReportingNameMap());
	}

	public Test getTest() {
		return test;
	}

	public String getTestMethodName() {
		return (test != null && test.getMethod() != null) ? test.getMethod().getMethodName() : null;
	}

	public boolean isActive() {
		return test == null ? false : test.isActive();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TestResult> getPossibleTestResults() {
		return TEST_RESULT_DAO.getAllActiveTestResultsPerTest(test);
	}

	public String getUOM(boolean isCD4Conclusion) {
		if (!isCD4Conclusion) {
			if (test != null && test.getUnitOfMeasure() != null) {
				return test.getUnitOfMeasure().getName();
			}
		}

		return "";
	}

	public boolean isReportable() {
		return test != null && "Y".equals(test.getIsReportable());
	}

	public String getSortOrder() {
		return test == null ? "0" : test.getSortOrder();
	}

	public ResultDisplayType getDisplayTypeForTestMethod() {
		String methodName = getTestMethodName();

		if (HIV_TYPE.equals(methodName)) {
			return ResultDisplayType.HIV;
		} else if (SYPHILIS_TYPE.equals(methodName)) {
			return ResultDisplayType.SYPHILIS;
		}

		return TestResultItem.ResultDisplayType.TEXT;
	}

	public String getResultType() {
		String testResultType = TypeOfTestResultServiceImpl.ResultType.NUMERIC.getCharacterValue();
		List<TestResult> testResults = getPossibleTestResults();

		if (testResults != null && !testResults.isEmpty()) {
			testResultType = testResults.get(0).getTestResultType();
		}

		return testResultType;
	}

	@Transactional
	public TypeOfSample getTypeOfSample() {
		if (test == null) {
			return null;
		}

		TypeOfSampleTest typeOfSampleTest = TYPE_OF_SAMPLE_testDAO.getTypeOfSampleTestForTest(test.getId());

		if (typeOfSampleTest == null) {
			return null;
		}

		String typeOfSampleId = typeOfSampleTest.getTypeOfSampleId();

		return TYPE_OF_SAMPLE_DAO.getTypeOfSampleById(typeOfSampleId);
	}

	@Transactional
	public List<Panel> getPanels() {
		List<Panel> panelList = new ArrayList<>();
		if (test != null) {
			List<PanelItem> panelItemList = panelItemDAO.getPanelItemByTestId(test.getId());
			for (PanelItem panelItem : panelItemList) {
				panelList.add(panelItem.getPanel());
			}
		}

		return panelList;
	}

	@Transactional
	public List<Panel> getAllPanels() {
		return panelDAO.getAllPanels();
	}

	public TestSection getTestSection() {
		return test == null ? null : test.getTestSection();
	}

	public String getTestSectionName() {
		return TestSectionServiceImpl.getUserLocalizedTesSectionName(getTestSection());
	}

	@Transactional
	public static List<Test> getAllActiveTests() {
		return testDAO.getAllActiveTests(false);
	}

	public static Map<String, String> getMap(Entity entiy) {
		return entityToMap.get(entiy);
	}

	public static String getUserLocalizedTestName(Test test) {
		if (test == null) {
			return "";
		}

		return getUserLocalizedTestName(test.getId());
	}

	public static String getUserLocalizedReportingTestName(String testId) {
		String name = entityToMap.get(Entity.TEST_REPORTING_NAME).get(testId);
		return name == null ? "" : name;
	}

	public static String getUserLocalizedReportingTestName(Test test) {
		if (test == null) {
			return "";
		}

		return getUserLocalizedReportingTestName(test.getId());
	}

	public static String getUserLocalizedTestName(String testId) {
		String name = entityToMap.get(Entity.TEST_NAME).get(testId);
		return name == null ? "" : name;
	}

	/**
	 * Returns the test name augmented with the sample type IF
	 * ConfigurationProperties.Property.TEST_NAME_AUGMENTED is true. If it is not
	 * true just the test name will be returned. The test name will be correct for
	 * the current locale
	 *
	 * @param test The test for which we want the name
	 * @return The test name or the augmented test name
	 */
	public static String getLocalizedTestNameWithType(Test test) {
		if (test == null) {
			return "";
		}

		return getLocalizedTestNameWithType(test.getId());
	}

	/**
	 * Returns the test name augmented with the sample type IF
	 * ConfigurationProperties.Property.TEST_NAME_AUGMENTED is true. If it is not
	 * true just the test name will be returned. The test name will be correct for
	 * the current locale
	 *
	 * @param testId The test id of the test for which we want the name
	 * @return The test name or the augmented test name
	 */
	public static String getLocalizedTestNameWithType(String testId) {
		String description = entityToMap.get(Entity.TEST_AUGMENTED_NAME).get(testId);
		return description == null ? "" : description;
	}

	@Transactional
	private static Map<String, String> createTestIdToNameMap() {
		Map<String, String> testIdToNameMap = new HashMap<>();

		List<Test> tests = testDAO.getAllTests(false);

		for (Test test : tests) {
			testIdToNameMap.put(test.getId(), buildTestName(test).replace("\n", " "));
		}

		return testIdToNameMap;
	}

	private static String buildTestName(Test test) {
		Localization localization = test.getLocalizedTestName();

		try {
			if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
				return localization.getFrench();
			} else {
				return localization.getEnglish();
			}
		} catch (Exception ex) {
			System.out.println("buildTestName caught LAZY");
			return "ts:btn:284:name";
		}
	}

	@Transactional
	private static Map<String, String> createTestIdToAugmentedNameMap() {
		Map<String, String> testIdToNameMap = new HashMap<>();

		List<Test> tests = testDAO.getAllTests(false);

		for (Test test : tests) {
			testIdToNameMap.put(test.getId(), buildAugmentedTestName(test).replace("\n", " "));
		}

		return testIdToNameMap;
	}

	@Transactional
	private static Map<String, String> createTestIdToReportingNameMap() {
		Map<String, String> testIdToNameMap = new HashMap<>();

		List<Test> tests = testDAO.getAllActiveTests(false);

		for (Test test : tests) {
			testIdToNameMap.put(test.getId(), buildReportingTestName(test));
		}

		return testIdToNameMap;
	}

	private static String buildReportingTestName(Test test) {
		Localization localization = test.getLocalizedReportingName();

		try {
			if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
				return localization.getFrench();
			} else {
				return localization.getEnglish();
			}
		} catch (Exception ex) {
			System.out.println("reporting caught LAZY");
			return "ts:brtn:322:name";
		}
	}

	private static String buildAugmentedTestName(Test test) {
		Localization localization = test.getLocalizedTestName();

		String sampleName = "";

		if (ConfigurationProperties.getInstance().isPropertyValueEqual(ConfigurationProperties.Property.TEST_NAME_AUGMENTED, "true")) {
			TypeOfSample typeOfSample = new TestServiceImpl(test).getTypeOfSample();
			if (typeOfSample != null && !typeOfSample.getId().equals(VARIABLE_TYPE_OF_SAMPLE_ID)) {
				sampleName = "(" + typeOfSample.getLocalizedName() + ")";
			}
		}

		try {
			if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
				return localization.getFrench() + sampleName;
			} else {
				return localization.getEnglish() + sampleName;
				// return "ts:batn:342:name:" + test.getDescription();
			}
		} catch (Exception ex) {
			System.out.println("augmented caught LAZY");
			return "ts:batn:345:name:" + test.getDescription();
		}
	}

	@Override
	public void getData(Test test) {
        getBaseObjectDAO().getData(test);

	}

	@Override
	public void deleteData(List tests) {
        getBaseObjectDAO().deleteData(tests);

	}

	@Override
	public void updateData(Test test) {
        getBaseObjectDAO().updateData(test);

	}

	@Override
	public boolean insertData(Test test) {
        return getBaseObjectDAO().insertData(test);
	}

	@Override
	public Test getActiveTestById(Integer id) {
        return getBaseObjectDAO().getActiveTestById(id);
	}

	@Override
	public Test getTestByUserLocalizedName(String testName) {
        return getBaseObjectDAO().getTestByUserLocalizedName(testName);
	}

	@Override
	public Integer getTotalTestCount() {
        return getBaseObjectDAO().getTotalTestCount();
	}

	@Override
	public List getNextTestRecord(String id) {
        return getBaseObjectDAO().getNextTestRecord(id);
	}

	@Override
	public List<Test> getAllActiveTests(boolean onlyTestsFullySetup) {
        return getBaseObjectDAO().getAllActiveTests(onlyTestsFullySetup);
	}

	@Override
	public List getTestsByTestSectionAndMethod(String filter, String filter2) {
        return getBaseObjectDAO().getTestsByTestSectionAndMethod(filter,filter2);
	}

	@Override
	public List<Test> getTestsByTestSectionId(String id) {
        return getBaseObjectDAO().getTestsByTestSectionId(id);
	}

	@Override
	public List getPageOfTestsBySysUserId(int startingRecNo, int sysUserId) {
        return getBaseObjectDAO().getPageOfTestsBySysUserId(startingRecNo,sysUserId);
	}

	@Override
	public Integer getTotalSearchedTestCount(String searchString) {
        return getBaseObjectDAO().getTotalSearchedTestCount(searchString);
	}

	@Override
	public Integer getAllSearchedTotalTestCount(HttpServletRequest request, String searchString) {
        return getBaseObjectDAO().getAllSearchedTotalTestCount(request,searchString);
	}

	@Override
	public List<Test> getActiveTestByName(String testName) {
        return getBaseObjectDAO().getActiveTestByName(testName);
	}

	@Override
	public List getPreviousTestRecord(String id) {
        return getBaseObjectDAO().getPreviousTestRecord(id);
	}

	@Override
	public List getTestsByTestSection(String filter) {
        return getBaseObjectDAO().getTestsByTestSection(filter);
	}

	@Override
	public List getPageOfSearchedTests(int startingRecNo, String searchString) {
        return getBaseObjectDAO().getPageOfSearchedTests(startingRecNo,searchString);
	}

	@Override
	public List getAllTestsBySysUserId(int sysUserId, boolean onlyTestsFullySetup) {
        return getBaseObjectDAO().getAllTestsBySysUserId(sysUserId,onlyTestsFullySetup);
	}

	@Override
	public List getMethodsByTestSection(String filter) {
        return getBaseObjectDAO().getMethodsByTestSection(filter);
	}

	@Override
	public List<Test> getActiveTestsByLoinc(String loincCode) {
        return getBaseObjectDAO().getActiveTestsByLoinc(loincCode);
	}

	@Override
	public List<Test> getAllActiveOrderableTests() {
        return getBaseObjectDAO().getAllActiveOrderableTests();
	}

	@Override
	public Test getTestByDescription(String description) {
        return getBaseObjectDAO().getTestByDescription(description);
	}

	@Override
	public List<Test> getTestsByLoincCode(String loincCode) {
        return getBaseObjectDAO().getTestsByLoincCode(loincCode);
	}

	@Override
	public List<Test> getAllOrderBy(String columnName) {
        return getBaseObjectDAO().getAllOrderBy(columnName);
	}

	@Override
	public boolean isTestFullySetup(Test test) {
        return getBaseObjectDAO().isTestFullySetup(test);
	}

	@Override
	public Test getTestById(Test test) {
        return getBaseObjectDAO().getTestById(test);
	}

	@Override
	public Test getTestById(String testId) {
        return getBaseObjectDAO().getTestById(testId);
	}

	@Override
	public List getTestsByMethod(String filter) {
        return getBaseObjectDAO().getTestsByMethod(filter);
	}

	@Override
	public List getPageOfTests(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTests(startingRecNo);
	}

	@Override
	public List getTests(String filter, boolean onlyTestsFullySetup) {
        return getBaseObjectDAO().getTests(filter,onlyTestsFullySetup);
	}

	@Override
	public List<Test> getAllTests(boolean onlyTestsFullySetup) {
        return getBaseObjectDAO().getAllTests(onlyTestsFullySetup);
	}

	@Override
	public Test getTestByName(Test test) {
        return getBaseObjectDAO().getTestByName(test);
	}

	@Override
	public Test getTestByName(String testName) {
        return getBaseObjectDAO().getTestByName(testName);
	}

	@Override
	public Test getTestByGUID(String guid) {
        return getBaseObjectDAO().getTestByGUID(guid);
	}

	@Override
	public Integer getTotalSearchedTestCountBySysUserId(int sysUserId, String searchString) {
        return getBaseObjectDAO().getTotalSearchedTestCountBySysUserId(sysUserId,searchString);
	}

	@Override
	public Integer getNextAvailableSortOrderByTestSection(Test test) {
        return getBaseObjectDAO().getNextAvailableSortOrderByTestSection(test);
	}

	@Override
	public List<Test> getPageOfSearchedTestsBySysUserId(int startingRecNo, int sysUserId, String searchString) {
        return getBaseObjectDAO().getPageOfSearchedTestsBySysUserId(startingRecNo,sysUserId,searchString);
	}
}
