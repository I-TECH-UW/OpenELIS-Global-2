package spring.service.typeofsample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.panel.PanelService;
import spring.service.test.TestService;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestComparator;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO.SampleDomain;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
@DependsOn({ "springContext" })
public class TypeOfSampleServiceImpl extends BaseObjectServiceImpl<TypeOfSample, String>
		implements TypeOfSampleService {

	private static TypeOfSampleService INSTANCE;

	private Map<String, List<Test>> sampleIdTestMap = new HashMap<>();
	private Map<String, String> typeOfSampleIdToNameMap;
	private Map<String, String> typeOfSampleWellKnownNameToIdMap;
	private Map<String, TypeOfSample> testIdToTypeOfSampleMap = null;
	private Map<String, List<TypeOfSample>> panelIdToTypeOfSampleMap = null;
	// The purpose of this map is to make sure all the references refer to the same
	// instances of the TypeOfSample objects
	// Without this comparisons may fail
	private Map<String, TypeOfSample> typeOfSampleIdtoTypeOfSampleMap = null;

	@Autowired
	protected TypeOfSampleDAO baseObjectDAO;

	@Autowired
	private TypeOfSamplePanelService typeOfSamplePanelService;
	@Autowired
	private TestService testService;
	@Autowired
	private TypeOfSampleTestService typeOfSampleTestService;
	@Autowired
	private PanelService panelService;

	@PostConstruct
	private void registerInstance() {
		INSTANCE = this;
	}

	@PostConstruct
	private void initializeGlobalVariables() {
		if (typeOfSampleIdtoTypeOfSampleMap == null) {
			createTypeOfSampleIdentityMap();
		}
	}

	TypeOfSampleServiceImpl() {
		super(TypeOfSample.class);
	}

	public static TypeOfSampleService getInstance() {
		return INSTANCE;
	}

	@Override
	protected TypeOfSampleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample typeOfSample, boolean ignoreCase) {
		return baseObjectDAO.getTypeOfSampleByDescriptionAndDomain(typeOfSample, ignoreCase);
	}

	@Override
	public List<Test> getActiveTestsBySampleTypeId(String sampleTypeId, boolean orderableOnly) {

		List<Test> testList = sampleIdTestMap.get(sampleTypeId);

		if (testList == null) {
			testList = createSampleIdTestMap(sampleTypeId);
		}

		if (orderableOnly) {
			return filterByOrderable(testList);
		} else {
			return testList;
		}
	}

	@Override
	public List<Test> getAllTestsBySampleTypeId(String sampleTypeId) {
		List<Test> testList = new ArrayList<>();

		List<TypeOfSampleTest> testLinks = typeOfSampleTestService.getTypeOfSampleTestsForSampleType(sampleTypeId);

		for (TypeOfSampleTest link : testLinks) {
			testList.add(testService.getTestById(link.getTestId()));
		}

		Collections.sort(testList, TestComparator.NAME_COMPARATOR);
		return testList;
	}

	@Override
	public TypeOfSample getTransientTypeOfSampleById(String id) {
		return baseObjectDAO.getTypeOfSampleById(id);
	}

	private List<Test> filterByOrderable(List<Test> testList) {
		List<Test> filteredList = new ArrayList<>();

		for (Test test : testList) {
			if (test.getOrderable()) {
				filteredList.add(test);
			}
		}

		return filteredList;
	}

	@Override
	public TypeOfSample getTypeOfSampleForTest(String testId) {
		if (testIdToTypeOfSampleMap == null) {
			createTestIdToTypeOfSampleMap();
		}

		return testIdToTypeOfSampleMap.get(testId);
	}

	private void createTestIdToTypeOfSampleMap() {
		testIdToTypeOfSampleMap = new HashMap<>();

		List<TypeOfSampleTest> typeOfSampleTestList = typeOfSampleTestService.getAllTypeOfSampleTests();

		for (TypeOfSampleTest typeTest : typeOfSampleTestList) {
			String testId = typeTest.getTestId();
			TypeOfSample typeOfSample = typeOfSampleIdtoTypeOfSampleMap
					.get(baseObjectDAO.getTypeOfSampleById(typeTest.getTypeOfSampleId()).getId());
			testIdToTypeOfSampleMap.put(testId, typeOfSample);
		}
	}

	private List<Test> createSampleIdTestMap(String sampleTypeId) {
		List<Test> testList;
		List<TypeOfSampleTest> tests = typeOfSampleTestService.getTypeOfSampleTestsForSampleType(sampleTypeId);

		testList = new ArrayList<>();

		for (TypeOfSampleTest link : tests) {
			Test test = testService.getActiveTestById(Integer.valueOf(link.getTestId()));
			if (test != null) {
				testList.add(test);
			}
		}

		Collections.sort(testList, TestComparator.NAME_COMPARATOR);

		sampleIdTestMap.put(sampleTypeId, testList);
		return testList;
	}

	/**
	 * This class keeps lists of tests for each type of sample. If the DB of tests
	 * changes, we need to invalidate such lists.
	 */
	@Override
	public void clearCache() {
		sampleIdTestMap.clear();
		createTypeOfSampleIdentityMap();
		typeOfSampleIdToNameMap = null;
		typeOfSampleWellKnownNameToIdMap = null;
		testIdToTypeOfSampleMap = null;
	}

	private void createTypeOfSampleIdentityMap() {
		typeOfSampleIdtoTypeOfSampleMap = new HashMap<>();

		@SuppressWarnings("unchecked")
		List<TypeOfSample> typeOfSampleList = baseObjectDAO.getAllTypeOfSamples();

		for (TypeOfSample typeOfSample : typeOfSampleList) {
			typeOfSampleIdtoTypeOfSampleMap.put(typeOfSample.getId(), typeOfSample);
		}
	}

	@Override
	public String getTypeOfSampleNameForId(String id) {
		if (typeOfSampleIdToNameMap == null) {
			createSampleNameIDMaps();
		}

		return typeOfSampleIdToNameMap.get(id);
	}

	@Override
	public String getTypeOfSampleIdForLocalAbbreviation(String name) {
		if (typeOfSampleWellKnownNameToIdMap == null) {
			createSampleNameIDMaps();
		}

		return typeOfSampleWellKnownNameToIdMap.get(name);
	}

	@SuppressWarnings("unchecked")
	private void createSampleNameIDMaps() {
		typeOfSampleIdToNameMap = new HashMap<>();
		typeOfSampleWellKnownNameToIdMap = new HashMap<>();

		List<TypeOfSample> allTypes = baseObjectDAO.getAllTypeOfSamples();
		for (TypeOfSample typeOfSample : allTypes) {
			typeOfSampleIdToNameMap.put(typeOfSample.getId(), typeOfSample.getLocalizedName());
			typeOfSampleWellKnownNameToIdMap.put(typeOfSample.getLocalAbbreviation(), typeOfSample.getId());
		}
	}

	@Override
	public List<TypeOfSample> getTypeOfSampleForPanelId(String id) {
		if (panelIdToTypeOfSampleMap == null) {
			panelIdToTypeOfSampleMap = new HashMap<>();

			List<Panel> panels = panelService.getAllActivePanels();

			for (Panel panel : panels) {
				List<TypeOfSamplePanel> typeOfSamplePanels = typeOfSamplePanelService
						.getTypeOfSamplePanelsForPanel(panel.getId());
				List<TypeOfSample> typeOfSampleList = new ArrayList<>();
				for (TypeOfSamplePanel typeOfSamplePanel : typeOfSamplePanels) {
					typeOfSampleList.add(typeOfSampleIdtoTypeOfSampleMap
							.get(baseObjectDAO.getTypeOfSampleById(typeOfSamplePanel.getTypeOfSampleId()).getId()));
				}
				panelIdToTypeOfSampleMap.put(panel.getId(), typeOfSampleList);
			}
		}

		return panelIdToTypeOfSampleMap.get(id);
	}

	@Override
	public void getData(TypeOfSample typeOfSample) {
		getBaseObjectDAO().getData(typeOfSample);

	}

	@Override
	public String getNameForTypeOfSampleId(String id) {
		return getBaseObjectDAO().getNameForTypeOfSampleId(id);
	}

	@Override
	public List getAllTypeOfSamples() {
		return baseObjectDAO.getAllTypeOfSamples();
	}

	@Override
	public List<TypeOfSample> getAllTypeOfSamplesSortOrdered() {
		return getBaseObjectDAO().getAllTypeOfSamplesSortOrdered();
	}

	@Override
	public List getTypesForDomain(SampleDomain domain) {
		return getBaseObjectDAO().getTypesForDomain(domain);
	}

	@Override
	public List getPreviousTypeOfSampleRecord(String id) {
		return getBaseObjectDAO().getPreviousTypeOfSampleRecord(id);
	}

	@Override
	public Integer getTotalTypeOfSampleCount() {
		return getBaseObjectDAO().getTotalTypeOfSampleCount();
	}

	@Override
	public List getNextTypeOfSampleRecord(String id) {
		return getBaseObjectDAO().getNextTypeOfSampleRecord(id);
	}

	@Override
	public TypeOfSample getTypeOfSampleById(String typeOfSampleId) {
		return getBaseObjectDAO().getTypeOfSampleById(typeOfSampleId);
	}

	@Override
	public TypeOfSample getSampleTypeFromTest(Test test) {
		return getBaseObjectDAO().getSampleTypeFromTest(test);
	}

	@Override
	public List<TypeOfSample> getTypesForDomainBySortOrder(SampleDomain human) {
		return getBaseObjectDAO().getTypesForDomainBySortOrder(human);
	}

	@Override
	public List getPageOfTypeOfSamples(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTypeOfSamples(startingRecNo);
	}

	@Override
	public List getTypes(String filter, String domain) {
		return getBaseObjectDAO().getTypes(filter, domain);
	}

	@Override
	public TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain(String localAbbrev, String domain) {
		return getBaseObjectDAO().getTypeOfSampleByLocalAbbrevAndDomain(localAbbrev, domain);
	}

	@Override
	public void delete(TypeOfSample typeOfSample) {
		super.delete(typeOfSample);
		getBaseObjectDAO().clearMap();
	}

	@Override
	public String insert(TypeOfSample typeOfSample) {
		if (baseObjectDAO.duplicateTypeOfSampleExists(typeOfSample)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + typeOfSample.getDescription());
		}
		baseObjectDAO.clearMap();
		return super.insert(typeOfSample);
	}

	@Override
	public TypeOfSample save(TypeOfSample typeOfSample) {
		if (baseObjectDAO.duplicateTypeOfSampleExists(typeOfSample)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + typeOfSample.getDescription());
		}
		baseObjectDAO.clearMap();
		return super.save(typeOfSample);
	}

	@Override
	public TypeOfSample update(TypeOfSample typeOfSample) {
		if (baseObjectDAO.duplicateTypeOfSampleExists(typeOfSample)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + typeOfSample.getDescription());
		}
		baseObjectDAO.clearMap();
		return super.update(typeOfSample);
	}
}
