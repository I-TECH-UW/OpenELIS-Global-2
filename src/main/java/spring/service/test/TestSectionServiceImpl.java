package spring.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
@DependsOn({ "springContext" })
public class TestSectionServiceImpl extends BaseObjectServiceImpl<TestSection> implements TestSectionService, LocaleChangeListener {

	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
	private static Map<String, String> testUnitIdToNameMap;

	@Autowired
	protected static TestSectionDAO testSectionDAO = SpringContext.getBean(TestSectionDAO.class);

	private TestSection testSection;

	public synchronized void initializeGlobalVariables() {
		if (testUnitIdToNameMap == null) {
			createTestIdToNameMap();
		}
	}

	@PostConstruct
	public void initialize() {
		SystemConfiguration.getInstance().addLocalChangeListener(this);
	}

	public TestSectionServiceImpl() {
		super(TestSection.class);
		initializeGlobalVariables();
	}

	public TestSectionServiceImpl(TestSection testSection) {
		this();
		this.testSection = testSection;
	}

	public TestSectionServiceImpl(String testSectionId) {
		this();
		testSection = testSectionDAO.getTestSectionById(testSectionId);
	}

	@Override
	protected TestSectionDAO getBaseObjectDAO() {
		return testSectionDAO;
	}

	@Override
	@Transactional
	public List<TestSection> getAllActiveTestSections() {
		return testSectionDAO.getAllMatchingOrdered("isActive", "Y", "sortOrderInt", false);
	}

	public TestSection getTestSection() {
		return testSection;
	}

	@Override
	public void localeChanged(String locale) {
		LANGUAGE_LOCALE = locale;
		testNamesChanged();
	}

	public static void refreshNames() {
		testNamesChanged();
	}

	public static void testNamesChanged() {
		createTestIdToNameMap();
	}

	public String getSortOrder() {
		return testSection == null ? "0" : testSection.getSortOrder();
	}

	public static String getUserLocalizedTesSectionName(TestSection testSection) {
		if (testSection == null) {
			return "";
		}

		return getUserLocalizedTestSectionName(testSection.getId());
	}

	public static String getUserLocalizedTestSectionName(String testSectionId) {
		String name = testUnitIdToNameMap.get(testSectionId);
		return name == null ? "" : name;
	}

	private static void createTestIdToNameMap() {
		testUnitIdToNameMap = new HashMap<>();

		List<TestSection> testSections = testSectionDAO.getAllTestSections();

		for (TestSection testSection : testSections) {
			testUnitIdToNameMap.put(testSection.getId(), buildTestSectionName(testSection).replace("\n", " "));
		}
	}

	private static String buildTestSectionName(TestSection testSection) {
		Localization localization = testSection.getLocalization();

		if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
			return localization.getFrench();
		} else {
			return localization.getEnglish();
		}
	}

	public static List<Test> getTestsInSection(String id) {
		return TestServiceImpl.getTestsInTestSectionById(id);
	}

	@Override
	public void getData(TestSection testSection) {
        getBaseObjectDAO().getData(testSection);

	}

	@Override
	public void deleteData(List testSections) {
        getBaseObjectDAO().deleteData(testSections);

	}

	@Override
	public void updateData(TestSection testSection) {
        getBaseObjectDAO().updateData(testSection);

	}

	@Override
	public boolean insertData(TestSection testSection) {
        return getBaseObjectDAO().insertData(testSection);
	}

	@Override
	public List getTestSections(String filter) {
        return getBaseObjectDAO().getTestSections(filter);
	}

	@Override
	public TestSection getTestSectionByName(String testSection) {
        return getBaseObjectDAO().getTestSectionByName(testSection);
	}

	@Override
	public TestSection getTestSectionByName(TestSection testSection) {
        return getBaseObjectDAO().getTestSectionByName(testSection);
	}

	@Override
	public List getNextTestSectionRecord(String id) {
        return getBaseObjectDAO().getNextTestSectionRecord(id);
	}

	@Override
	public List getPageOfTestSections(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTestSections(startingRecNo);
	}

	@Override
	public Integer getTotalTestSectionCount() {
        return getBaseObjectDAO().getTotalTestSectionCount();
	}

	@Override
	public List getPreviousTestSectionRecord(String id) {
        return getBaseObjectDAO().getPreviousTestSectionRecord(id);
	}

	@Override
	public List<TestSection> getAllTestSections() {
		return testSectionDAO.getAllTestSections();
	}

	@Override
	public List getTestSectionsBySysUserId(String filter, int sysUserId) {
        return getBaseObjectDAO().getTestSectionsBySysUserId(filter,sysUserId);
	}

	@Override
	public List getAllTestSectionsBySysUserId(int sysUserId) {
        return getBaseObjectDAO().getAllTestSectionsBySysUserId(sysUserId);
	}

	@Override
	public TestSection getTestSectionById(String testSectionId) {
        return getBaseObjectDAO().getTestSectionById(testSectionId);
	}

	@Override
	public List<TestSection> getAllInActiveTestSections() {
        return getBaseObjectDAO().getAllInActiveTestSections();
	}
}
