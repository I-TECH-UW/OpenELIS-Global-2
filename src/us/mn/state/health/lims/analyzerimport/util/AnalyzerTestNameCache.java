/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.analyzerimport.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class AnalyzerTestNameCache {
    public static final String SYSMEX_XT2000_NAME = "Sysmex XT 2000";
    public static final String COBAS_INTEGRA400_NAME = "Cobas Integra";
    public static final String FACSCALIBUR = "Facscalibur";
    public static final String EVOLIS = "Evolis";
    public static final String COBAS_TAQMAN = "Cobas Taqman";
    public static final String FACSCANTO = "FacsCanto";
    public static final String COBAS_DBS = "CobasDBS";
    public static final String COBAS_C311 = "Cobas C311";
    private static final Object lock = new Object();
    private static final HashMap<String, Map<String, MappedTestName>> analyzerNameToTestNameMap = new HashMap<String, Map<String, MappedTestName>>();
    private static AnalyzerTestNameCache instance;
    private static Map<String, String> analyzerNameToIdMap;
    private static Map<String, String> requestTODBName = new HashMap<String, String>();
    private static boolean isMapped = false;

    static{
        requestTODBName.put("sysmex", SYSMEX_XT2000_NAME);
        requestTODBName.put("cobas_integra", COBAS_INTEGRA400_NAME );
        requestTODBName.put("facscalibur", FACSCALIBUR );
        requestTODBName.put("evolis", EVOLIS );
        requestTODBName.put("cobas_taqman", COBAS_TAQMAN );
        requestTODBName.put("facscanto", FACSCANTO );
        requestTODBName.put("cobasDBS", COBAS_DBS );
        requestTODBName.put("cobasc311", COBAS_C311 );
    }
    public static AnalyzerTestNameCache instance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new AnalyzerTestNameCache();
            }
        }
        return instance;
    }

    public static void setTestInstance(AnalyzerTestNameCache cache) {
        instance = cache;
    }

    public String getDBNameForActionName(String actionName){
        return requestTODBName.get(actionName);
    }

    public List<String> getAnalyzerNames(){
        insureMapsLoaded();
        List<String> nameList = new ArrayList<String>();
        nameList.addAll(analyzerNameToIdMap.keySet());
        return nameList;
    }
    public MappedTestName getMappedTest(String analyzerName, String analyzerTestName) {
        //This will look for a mapping for the analyzer and if it is found will then
        //look for a mapping for the test name
        Map<String, MappedTestName> testMap = getMappedTestsForAnalyzer(analyzerName);

        if (testMap != null) {
            return testMap.get(analyzerTestName);
        }

        return null;
    }

    public void registerPluginAnalyzer( String analyzerName, String analyzerId){
        requestTODBName.put(analyzerName, analyzerName);
        if(isMapped){
            analyzerNameToIdMap.put(analyzerName,analyzerId);
        }
    }

    private synchronized void insureMapsLoaded() {
        if (!isMapped) {
            loadMaps();
            isMapped = true;
        }
    }

    public Map<String, MappedTestName> getMappedTestsForAnalyzer(String analyzerName) {
        insureMapsLoaded();
        return analyzerNameToTestNameMap.get(analyzerName);
    }

    public synchronized void reloadCache() {
        isMapped = false;
    }

    private void loadMaps() {
        AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
        List<Analyzer> analyzerList = analyzerDAO.getAllAnalyzers();
        analyzerNameToTestNameMap.clear();

        analyzerNameToIdMap = new HashMap<String, String>();

        for (Analyzer analyzer : analyzerList) {
            analyzerNameToIdMap.put(analyzer.getName(), analyzer.getId());
            analyzerNameToTestNameMap.put(analyzer.getName(), new HashMap<String, MappedTestName>());
        }

        AnalyzerTestMappingDAO analyzerTestMappingDAO = new AnalyzerTestMappingDAOImpl();
        List<AnalyzerTestMapping> mappingList = analyzerTestMappingDAO.getAllAnalyzerTestMappings();

        TestDAO testDAO = new TestDAOImpl();
        for (AnalyzerTestMapping mapping : mappingList) {
            MappedTestName mappedTestName = createMappedTestName(testDAO, mapping);

            Analyzer analyzer = new Analyzer();
            analyzer.setId(mapping.getAnalyzerId());
            analyzer = new AnalyzerDAOImpl().getAnalyzerById(analyzer);

            Map<String, MappedTestName> testMap = analyzerNameToTestNameMap.get(analyzer.getName());
            if (testMap != null) {
                testMap.put(mapping.getAnalyzerTestName(), mappedTestName);
            }
        }

    }

    private MappedTestName createMappedTestName(TestDAO testDAO, AnalyzerTestMapping mapping) {

        MappedTestName mappedTest = new MappedTestName();
        mappedTest.setAnalyzerTestName(mapping.getAnalyzerTestName());
        mappedTest.setTestId(mapping.getTestId());
        mappedTest.setAnalyzerId(mapping.getAnalyzerId());
        if( mapping.getTestId() != null){
            Test test = new Test();
            test.setId(mapping.getTestId());
            testDAO.getData(test);
            mappedTest.setOpenElisTestName( TestService.getUserLocalizedTestName( test ));
        }else{
            mappedTest.setTestId("-1");
            mappedTest.setOpenElisTestName(StringUtil.getMessageForKey("warning.configuration.needed"));
        }


        return mappedTest;
    }

    public MappedTestName getEmptyMappedTestName(String analyzerName, String analyzerTestName) {
        insureMapsLoaded();
        MappedTestName mappedTest = new MappedTestName();
        mappedTest.setAnalyzerTestName(analyzerTestName);
        mappedTest.setTestId(null);
        mappedTest.setOpenElisTestName(analyzerTestName);
        mappedTest.setAnalyzerId(analyzerNameToIdMap.get(analyzerName));

        return mappedTest;
    }

    public String getAnalyzerIdForName(String analyzerName) {
        insureMapsLoaded();

        return analyzerNameToIdMap.get(analyzerName);
    }
}
