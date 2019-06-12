package spring.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class TestSectionServiceImpl extends BaseObjectServiceImpl<TestSection, String>
		implements TestSectionService, LocaleChangeListener {

	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
	private static Map<String, String> testUnitIdToNameMap;

	@Autowired
	protected static TestSectionDAO baseObjectDAO = SpringContext.getBean(TestSectionDAO.class);

	private TestSection testSection;

	public synchronized void initializeGlobalVariables() {
		if (testUnitIdToNameMap == null) {
			createTestIdToNameMap();
		}
	}

	@PostConstruct
	private void initialize() {
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
		testSection = baseObjectDAO.getTestSectionById(testSectionId);
	}

	@Override
	protected TestSectionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<TestSection> getAllActiveTestSections() {
		return baseObjectDAO.getAllMatchingOrdered("isActive", "Y", "sortOrderInt", false);
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

		List<TestSection> testSections = baseObjectDAO.getAllTestSections();

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
		return baseObjectDAO.getAllTestSections();
	}

	@Override
	public List getTestSectionsBySysUserId(String filter, int sysUserId) {
		return getBaseObjectDAO().getTestSectionsBySysUserId(filter, sysUserId);
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

	@Override
	public String insert(TestSection testSection) {
		if (baseObjectDAO.duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.insert(testSection);
	}

	@Override
	public TestSection save(TestSection testSection) {
		if (baseObjectDAO.duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.save(testSection);
	}

	@Override
	public TestSection update(TestSection testSection) {
		if (baseObjectDAO.duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.update(testSection);
	}
}
