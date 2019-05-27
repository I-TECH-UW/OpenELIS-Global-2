package spring.service.typeofsample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestComparator;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO.SampleDomain;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSamplePanelDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
@DependsOn({ "springContext" })
public class TypeOfSampleServiceImpl extends BaseObjectServiceImpl<TypeOfSample> implements TypeOfSampleService {

	private static Map<String, List<Test>> sampleIdTestMap = new HashMap<>();
	private static Map<String, String> typeOfSampleIdToNameMap;
	private static Map<String, String> typeOfSampleWellKnownNameToIdMap;
	private static Map<String, TypeOfSample> testIdToTypeOfSampleMap = null;
	private static Map<String, List<TypeOfSample>> panelIdToTypeOfSampleMap = null;
	// The purpose of this map is to make sure all the references refer to the same
	// instances of the TypeOfSample objects
	// Without this comparisons may fail
	private static Map<String, TypeOfSample> typeOfSampleIdtoTypeOfSampleMap = null;

	@Autowired
	protected TypeOfSampleDAO baseObjectDAO;

	@Autowired
	private static TypeOfSampleDAO typeOfSampleDAO = SpringContext.getBean(TypeOfSampleDAO.class);
	@Autowired
	static TypeOfSamplePanelDAO typeOfSamplePanelDAO = SpringContext.getBean(TypeOfSamplePanelDAO.class);
	@Autowired
	static TestDAO testDAO = SpringContext.getBean(TestDAO.class);
	@Autowired
	static TypeOfSampleTestDAO typeOfSampleTestDAO = SpringContext.getBean(TypeOfSampleTestDAO.class);
	@Autowired
	static PanelDAO panelDAO = SpringContext.getBean(PanelDAO.class);

	public synchronized void initializeGlobalVariables() {
		if (typeOfSampleIdtoTypeOfSampleMap == null) {
			createTypeOfSampleIdentityMap();
		}
	}

	TypeOfSampleServiceImpl() {
		super(TypeOfSample.class);
		initializeGlobalVariables();
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

	public static List<Test> getActiveTestsBySampleTypeId(String sampleTypeId, boolean orderableOnly) {

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

	public static List<Test> getAllTestsBySampleTypeId(String sampleTypeId) {
		List<Test> testList = new ArrayList<>();

		List<TypeOfSampleTest> testLinks = typeOfSampleTestDAO.getTypeOfSampleTestsForSampleType(sampleTypeId);

		for (TypeOfSampleTest link : testLinks) {
			testList.add(testDAO.getTestById(link.getTestId()));
		}

		Collections.sort(testList, TestComparator.NAME_COMPARATOR);
		return testList;
	}

	public static TypeOfSample getTransientTypeOfSampleById(String id) {
		return typeOfSampleDAO.getTypeOfSampleById(id);
	}

	private static List<Test> filterByOrderable(List<Test> testList) {
		List<Test> filteredList = new ArrayList<>();

		for (Test test : testList) {
			if (test.getOrderable()) {
				filteredList.add(test);
			}
		}

		return filteredList;
	}

	public static TypeOfSample getTypeOfSampleForTest(String testId) {
		if (testIdToTypeOfSampleMap == null) {
			createTestIdToTypeOfSampleMap();
		}

		return testIdToTypeOfSampleMap.get(testId);
	}

	private static void createTestIdToTypeOfSampleMap() {
		testIdToTypeOfSampleMap = new HashMap<>();

		List<TypeOfSampleTest> typeOfSampleTestList = typeOfSampleTestDAO.getAllTypeOfSampleTests();

		for (TypeOfSampleTest typeTest : typeOfSampleTestList) {
			String testId = typeTest.getTestId();
			TypeOfSample typeOfSample = typeOfSampleIdtoTypeOfSampleMap.get(typeOfSampleDAO.getTypeOfSampleById(typeTest.getTypeOfSampleId()).getId());
			testIdToTypeOfSampleMap.put(testId, typeOfSample);
		}
	}

	private static List<Test> createSampleIdTestMap(String sampleTypeId) {
		List<Test> testList;
		List<TypeOfSampleTest> tests = typeOfSampleTestDAO.getTypeOfSampleTestsForSampleType(sampleTypeId);

		testList = new ArrayList<>();

		for (TypeOfSampleTest link : tests) {
			Test test = testDAO.getActiveTestById(Integer.valueOf(link.getTestId()));
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
	public static void clearCache() {
		sampleIdTestMap.clear();
		createTypeOfSampleIdentityMap();
		typeOfSampleIdToNameMap = null;
		typeOfSampleWellKnownNameToIdMap = null;
		testIdToTypeOfSampleMap = null;
	}

	private static void createTypeOfSampleIdentityMap() {
		typeOfSampleIdtoTypeOfSampleMap = new HashMap<>();

		@SuppressWarnings("unchecked")
		List<TypeOfSample> typeOfSampleList = typeOfSampleDAO.getAllTypeOfSamples();

		for (TypeOfSample typeOfSample : typeOfSampleList) {
			typeOfSampleIdtoTypeOfSampleMap.put(typeOfSample.getId(), typeOfSample);
		}
	}

	public static String getTypeOfSampleNameForId(String id) {
		if (typeOfSampleIdToNameMap == null) {
			createSampleNameIDMaps();
		}

		return typeOfSampleIdToNameMap.get(id);
	}

	public static String getTypeOfSampleIdForLocalAbbreviation(String name) {
		if (typeOfSampleWellKnownNameToIdMap == null) {
			createSampleNameIDMaps();
		}

		return typeOfSampleWellKnownNameToIdMap.get(name);
	}

	@SuppressWarnings("unchecked")
	private static void createSampleNameIDMaps() {
		typeOfSampleIdToNameMap = new HashMap<>();
		typeOfSampleWellKnownNameToIdMap = new HashMap<>();

		List<TypeOfSample> allTypes = typeOfSampleDAO.getAllTypeOfSamples();
		for (TypeOfSample typeOfSample : allTypes) {
			typeOfSampleIdToNameMap.put(typeOfSample.getId(), typeOfSample.getLocalizedName());
			typeOfSampleWellKnownNameToIdMap.put(typeOfSample.getLocalAbbreviation(), typeOfSample.getId());
		}
	}

	public static List<TypeOfSample> getTypeOfSampleForPanelId(String id) {
		if (panelIdToTypeOfSampleMap == null) {
			panelIdToTypeOfSampleMap = new HashMap<>();

			List<Panel> panels = panelDAO.getAllActivePanels();

			for (Panel panel : panels) {
				List<TypeOfSamplePanel> typeOfSamplePanels = typeOfSamplePanelDAO.getTypeOfSamplePanelsForPanel(panel.getId());
				List<TypeOfSample> typeOfSampleList = new ArrayList<>();
				for (TypeOfSamplePanel typeOfSamplePanel : typeOfSamplePanels) {
					typeOfSampleList.add(typeOfSampleIdtoTypeOfSampleMap.get(typeOfSampleDAO.getTypeOfSampleById(typeOfSamplePanel.getTypeOfSampleId()).getId()));
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
	public void deleteData(List typeOfSamples) {
        getBaseObjectDAO().deleteData(typeOfSamples);

	}

	@Override
	public void updateData(TypeOfSample typeOfSample) {
        getBaseObjectDAO().updateData(typeOfSample);

	}

	@Override
	public boolean insertData(TypeOfSample typeOfSample) {
        return getBaseObjectDAO().insertData(typeOfSample);
	}

	@Override
	public String getNameForTypeOfSampleId(String id) {
        return getBaseObjectDAO().getNameForTypeOfSampleId(id);
	}

	@Override
	public List getAllTypeOfSamples() {
		return typeOfSampleDAO.getAllTypeOfSamples();
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
        return getBaseObjectDAO().getTypes(filter,domain);
	}

	@Override
	public TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain(String localAbbrev, String domain) {
        return getBaseObjectDAO().getTypeOfSampleByLocalAbbrevAndDomain(localAbbrev,domain);
	}
}
