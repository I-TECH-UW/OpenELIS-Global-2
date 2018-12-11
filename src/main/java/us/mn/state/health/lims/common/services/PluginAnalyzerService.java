/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerLineReader;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.plugin.AnalyzerImporterPlugin;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class PluginAnalyzerService {
    private List<AnalyzerTestMapping> existingMappings;
    private static AnalyzerTestMappingDAO analyzerMappingDAO = new AnalyzerTestMappingDAOImpl();

    private PluginAnalyzerService() {
    }

    public static PluginAnalyzerService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void registerAnalyzer( AnalyzerImporterPlugin analyzer){
        AnalyzerLineReader.registerAnalyzerPlugin(analyzer);
    }
    public String addAnalyzerDatabaseParts(String name, String description, List<TestMapping> nameMappings) {
        AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
        Analyzer analyzer = analyzerDAO.getAnalyzerByName(name);
        if ( analyzer != null && analyzer.getId() != null) {
            analyzer.setActive(true);
            registerAanlyzerInCache(name, analyzer.getId());
        } else {
            if( analyzer == null){
                analyzer = new Analyzer();
                analyzer.setActive(true);
                analyzer.setName(name);
            }
            analyzer.setDescription(description);
        }

        List<AnalyzerTestMapping> testMappings = createTestMappings( nameMappings);
        if( !testMappings.isEmpty() && existingMappings == null){
            existingMappings = analyzerMappingDAO.getAllAnalyzerTestMappings();
        }

        analyzer.setSysUserId("1");

        Transaction tx = HibernateUtil.getSession().beginTransaction();

        try {
            if (analyzer.getId() == null) {
                analyzerDAO.insertData(analyzer);
            } else {
                analyzerDAO.updateData(analyzer);
            }

            for( AnalyzerTestMapping mapping : testMappings){
                mapping.setAnalyzerId(analyzer.getId());
                if( newMapping(mapping)){
                    analyzerMappingDAO.insertData(mapping, "1");
                    existingMappings.add(mapping);
                }
            }
            tx.commit();

        } catch (LIMSRuntimeException lre) {
            tx.rollback();
        }

        registerAanlyzerInCache(name, analyzer.getId());
        return analyzer.getId();
    }

    private boolean newMapping(AnalyzerTestMapping mapping) {
        for( AnalyzerTestMapping existingMap: existingMappings){
            if( existingMap.getAnalyzerId().equals(mapping.getAnalyzerId()) &&
                    existingMap.getAnalyzerTestName().equals(mapping.getAnalyzerTestName())){
                return false;
            }
        }
        return true;
    }

    private List<AnalyzerTestMapping> createTestMappings(List<TestMapping> nameMappings) {
        ArrayList<AnalyzerTestMapping> testMappings = new ArrayList<AnalyzerTestMapping>();
        for(TestMapping names : nameMappings){
            String testId = getIdForTestName( names.getDbbTestName());

            AnalyzerTestMapping analyzerMapping = new AnalyzerTestMapping();
            analyzerMapping.setAnalyzerTestName(names.getAnalyzerTestName());
            analyzerMapping.setTestId(testId);
            testMappings.add(analyzerMapping);
        }
        return testMappings;
    }

    private String getIdForTestName(String dbbTestName) {
        Test test = new TestDAOImpl().getTestByName(dbbTestName);
        if( test != null){
            return test.getId();
        }
        LogEvent.logError("PluginAnalyzerService", "createTestMappings", "Unable to find test " + dbbTestName + " in test catalog");
        return null;
    }

    private void registerAanlyzerInCache(String name, String id) {
        AnalyzerTestNameCache.instance().registerPluginAnalyzer(name, id);
    }

    public static class TestMapping{
        private final String  analyzerTestName;
        private final String dbbTestName;

        public TestMapping(String analyzerTestName, String dbbTestName){
            this.analyzerTestName = analyzerTestName;
            this.dbbTestName = dbbTestName;
        }

        public String getAnalyzerTestName(){
            return analyzerTestName;
        }

        public String getDbbTestName(){
            return dbbTestName;
        }
    }
    static class SingletonHolder {
        static final PluginAnalyzerService INSTANCE = new PluginAnalyzerService();
    }
}
