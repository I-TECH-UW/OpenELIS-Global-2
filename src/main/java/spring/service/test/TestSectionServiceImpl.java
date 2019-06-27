package spring.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.systemusersection.SystemUserSectionService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
@DependsOn({ "springContext" })
public class TestSectionServiceImpl extends BaseObjectServiceImpl<TestSection, String>
		implements TestSectionService, LocaleChangeListener {

	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
	private Map<String, String> testUnitIdToNameMap;

	protected TestSectionDAO baseObjectDAO = SpringContext.getBean(TestSectionDAO.class);
	private SystemUserSectionService systemUserSectionService = SpringContext.getBean(SystemUserSectionService.class);

	@PostConstruct
	private void initializeGlobalVariables() {
		createTestIdToNameMap();
	}

	@PostConstruct
	private void initialize() {
		SystemConfiguration.getInstance().addLocalChangeListener(this);
	}

	public TestSectionServiceImpl() {
		super(TestSection.class);
	}

	@Override
	protected TestSectionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestSection> getAllActiveTestSections() {
		return baseObjectDAO.getAllMatchingOrdered("isActive", "Y", "sortOrderInt", false);
	}

	@Override
	public void localeChanged(String locale) {
		LANGUAGE_LOCALE = locale;
		testNamesChanged();
	}

	public void refreshNames() {
		testNamesChanged();
	}

	public void testNamesChanged() {
		createTestIdToNameMap();
	}

	@Transactional(readOnly = true)
	public String getSortOrder(TestSection testSection) {
		return testSection == null ? "0" : testSection.getSortOrder();
	}

	public String getUserLocalizedTesSectionName(TestSection testSection) {
		if (testSection == null) {
			return "";
		}

		return getUserLocalizedTestSectionName(testSection.getId());
	}

	public String getUserLocalizedTestSectionName(String testSectionId) {
		String name = testUnitIdToNameMap.get(testSectionId);
		return name == null ? "" : name;
	}

	private void createTestIdToNameMap() {
		testUnitIdToNameMap = new HashMap<>();

		List<TestSection> testSections = baseObjectDAO.getAllTestSections();

		for (TestSection testSection : testSections) {
			testUnitIdToNameMap.put(testSection.getId(), buildTestSectionName(testSection).replace("\n", " "));
		}
	}

	private String buildTestSectionName(TestSection testSection) {
		Localization localization = testSection.getLocalization();

		if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
			return localization.getFrench();
		} else {
			return localization.getEnglish();
		}
	}

	@Override
	public List<Test> getTestsInSection(String id) {
		return TestServiceImpl.getTestsInTestSectionById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(TestSection testSection) {
		getBaseObjectDAO().getData(testSection);

	}

	@Override
	@Transactional(readOnly = true)
	public List getTestSections(String filter) {
		return getBaseObjectDAO().getTestSections(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public TestSection getTestSectionByName(String testSection) {
		return getBaseObjectDAO().getTestSectionByName(testSection);
	}

	@Override
	@Transactional(readOnly = true)
	public TestSection getTestSectionByName(TestSection testSection) {
		return getBaseObjectDAO().getTestSectionByName(testSection);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTestSectionRecord(String id) {
		return getBaseObjectDAO().getNextTestSectionRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTestSections(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTestSections(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTestSectionCount() {
		return getBaseObjectDAO().getTotalTestSectionCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTestSectionRecord(String id) {
		return getBaseObjectDAO().getPreviousTestSectionRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestSection> getAllTestSections() {
		return baseObjectDAO.getAllTestSections();
	}

	@Override
	@Transactional(readOnly = true)
	public List getTestSectionsBySysUserId(String filter, int sysUserId) {
		String sectionIdList = "";

		List userTestSectionList = systemUserSectionService.getAllSystemUserSectionsBySystemUserId(sysUserId);
		for (int i = 0; i < userTestSectionList.size(); i++) {
			SystemUserSection sus = (SystemUserSection) userTestSectionList.get(i);
		}
		return getBaseObjectDAO().getTestSectionsBySysUserId(filter, sysUserId, sectionIdList);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTestSectionsBySysUserId(int sysUserId) {
		String sectionIdList = "";

		List userTestSectionList = systemUserSectionService.getAllSystemUserSectionsBySystemUserId(sysUserId);
		for (int i = 0; i < userTestSectionList.size(); i++) {
			SystemUserSection sus = (SystemUserSection) userTestSectionList.get(i);
			sectionIdList += sus.getTestSection().getId() + ",";
		}
		return getBaseObjectDAO().getAllTestSectionsBySysUserId(sysUserId, sectionIdList);
	}

	@Override
	@Transactional(readOnly = true)
	public TestSection getTestSectionById(String testSectionId) {
		return getBaseObjectDAO().getTestSectionById(testSectionId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestSection> getAllInActiveTestSections() {
		return getBaseObjectDAO().getAllInActiveTestSections();
	}

	@Override
	public String insert(TestSection testSection) {
		if (duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.insert(testSection);
	}

	@Override
	public TestSection save(TestSection testSection) {
		if (duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.save(testSection);
	}

	@Override
	public TestSection update(TestSection testSection) {
		if (duplicateTestSectionExists(testSection)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testSection.getTestSectionName());
		}
		return super.update(testSection);
	}

	private boolean duplicateTestSectionExists(TestSection testSection) {
		return baseObjectDAO.duplicateTestSectionExists(testSection);
	}
}
