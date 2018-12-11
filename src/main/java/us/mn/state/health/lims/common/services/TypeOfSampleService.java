package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestComparator;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSamplePanelDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSamplePanelDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public class TypeOfSampleService {

	private static Map<String, List<Test>> sampleIdTestMap = new HashMap<String, List<Test>>();
    private static Map<String, String> typeOfSampleIdToNameMap;
    private static Map<String, String> typeOfSampleWellKnownNameToIdMap;
	private static Map<String, TypeOfSample> testIdToTypeOfSampleMap = null;
	private static Map<String, List<TypeOfSample>> panelIdToTypeOfSampleMap = null;
	//The purpose of this map is to make sure all the references refer to the same instances of the TypeOfSample objects
	//Without this comparisons may fail
	private static Map<String, TypeOfSample> typeOfSampleIdtoTypeOfSampleMap = null;
	private static final TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

	static{
		createTypeOfSampleIdentityMap();
	}

    public static List<Test> getActiveTestsBySampleTypeId(String sampleTypeId, boolean orderableOnly) {

        List<Test> testList = sampleIdTestMap.get(sampleTypeId);

        if (testList == null) {
            testList = createSampleIdTestMap(sampleTypeId);
        }

        if( orderableOnly){
            return filterByOrderable( testList);
        }else{
            return testList;
        }
    }

    public static List<Test> getAllTestsBySampleTypeId(String sampleTypeId){
        List<Test> testList = new ArrayList<Test>();
        TypeOfSampleTestDAO sampleTestsDAO = new TypeOfSampleTestDAOImpl();

        List<TypeOfSampleTest> testLinks = sampleTestsDAO.getTypeOfSampleTestsForSampleType(sampleTypeId);

        TestDAO testDAO = new TestDAOImpl();

        for (TypeOfSampleTest link : testLinks) {
            testList.add(testDAO.getTestById(link.getTestId()));
        }

        Collections.sort(testList, TestComparator.NAME_COMPARATOR);
        return testList;
    }
	public static TypeOfSample getTransientTypeOfSampleById(String id){
			return typeOfSampleDAO.getTypeOfSampleById(id);
	}
	private static List<Test> filterByOrderable(List<Test> testList) {
		List<Test> filteredList = new ArrayList<Test>();
		
		for( Test test : testList){
			if( test.getOrderable()){
				filteredList.add(test);
			}
		}
		
		return filteredList;
	}

	public static TypeOfSample getTypeOfSampleForTest(String testId){
		if( testIdToTypeOfSampleMap == null){
			createTestIdToTypeOfSampleMap();
		}
		
		return testIdToTypeOfSampleMap.get(testId);
	}
	
	
	private static void createTestIdToTypeOfSampleMap() {
		testIdToTypeOfSampleMap = new HashMap<String, TypeOfSample>( );
		
		List<TypeOfSampleTest> typeOfSampleTestList = new TypeOfSampleTestDAOImpl().getAllTypeOfSampleTests();

		TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

		for( TypeOfSampleTest typeTest : typeOfSampleTestList){
			String testId = typeTest.getTestId();
			TypeOfSample typeOfSample = typeOfSampleIdtoTypeOfSampleMap.get(typeOfSampleDAO.getTypeOfSampleById(typeTest.getTypeOfSampleId()).getId());
			testIdToTypeOfSampleMap.put(testId, typeOfSample);
		}
	}

	private static List<Test> createSampleIdTestMap(String sampleTypeId) {
		List<Test> testList;
		TypeOfSampleTestDAO sampleTestsDAO = new TypeOfSampleTestDAOImpl();
		List<TypeOfSampleTest> tests = sampleTestsDAO.getTypeOfSampleTestsForSampleType(sampleTypeId);

		TestDAO testDAO = new TestDAOImpl();

		testList = new ArrayList<Test>();

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
	 * This class keeps lists of tests for each type of sample.  If the DB of tests changes, we need to invalidate such lists.
	 */
	public static void clearCache() {
	    sampleIdTestMap.clear();
	    createTypeOfSampleIdentityMap();
	    typeOfSampleIdToNameMap = null;
        typeOfSampleWellKnownNameToIdMap = null;
	    testIdToTypeOfSampleMap = null;
	}

	private static void createTypeOfSampleIdentityMap(){
		typeOfSampleIdtoTypeOfSampleMap = new HashMap<String, TypeOfSample>();
		
		@SuppressWarnings("unchecked")
		List<TypeOfSample> typeOfSampleList = new TypeOfSampleDAOImpl().getAllTypeOfSamples();
		
		for(TypeOfSample typeOfSample : typeOfSampleList){
			typeOfSampleIdtoTypeOfSampleMap.put(typeOfSample.getId(), typeOfSample);
		}
	}

	public static String getTypeOfSampleNameForId( String id){
		if( typeOfSampleIdToNameMap == null){
            createSampleNameIDMaps();
		}

		return typeOfSampleIdToNameMap.get(id);
	}

    public static String getTypeOfSampleIdForLocalAbbreviation( String name ){
        if( typeOfSampleWellKnownNameToIdMap == null){
            createSampleNameIDMaps();
        }

        return typeOfSampleWellKnownNameToIdMap.get( name );
    }

    @SuppressWarnings( "unchecked" )
    private static void createSampleNameIDMaps(){
        typeOfSampleIdToNameMap = new HashMap<String, String>();
        typeOfSampleWellKnownNameToIdMap = new HashMap<String, String>( );

        TypeOfSampleDAO tosDAO = new TypeOfSampleDAOImpl();
        List<TypeOfSample> allTypes = tosDAO.getAllTypeOfSamples();
        for( TypeOfSample typeOfSample : allTypes){
            typeOfSampleIdToNameMap.put(typeOfSample.getId(), typeOfSample.getLocalizedName());
            typeOfSampleWellKnownNameToIdMap.put( typeOfSample.getLocalAbbreviation(), typeOfSample.getId() );
        }
    }

    public static List<TypeOfSample> getTypeOfSampleForPanelId(String id){
		if( panelIdToTypeOfSampleMap == null){
			panelIdToTypeOfSampleMap = new HashMap<String, List<TypeOfSample>>();
			
			List<Panel> panels = new PanelDAOImpl().getAllActivePanels();
			
			TypeOfSamplePanelDAO typeOfSamplePanelDAO = new TypeOfSamplePanelDAOImpl(); 
			TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
			for(Panel panel : panels){
				List<TypeOfSamplePanel> typeOfSamplePanels = typeOfSamplePanelDAO.getTypeOfSamplePanelsForPanel(panel.getId());
				List<TypeOfSample> typeOfSampleList = new ArrayList<TypeOfSample>();
				for( TypeOfSamplePanel typeOfSamplePanel : typeOfSamplePanels){
					typeOfSampleList.add(typeOfSampleIdtoTypeOfSampleMap.get(typeOfSampleDAO.getTypeOfSampleById(typeOfSamplePanel.getTypeOfSampleId()).getId()));
				}
				panelIdToTypeOfSampleMap.put(panel.getId(), typeOfSampleList);
			}
		}
		
		return panelIdToTypeOfSampleMap.get(id);
	}
    
    @SuppressWarnings("unchecked")
	public static List<TypeOfSample> getAllTypeOfSamples() {
        return new TypeOfSampleDAOImpl().getAllTypeOfSamples();
    }

}
