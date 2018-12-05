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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.daoimpl.PanelItemDAOImpl;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem.ResultDisplayType;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

/**
 */
public class TestService implements LocaleChangeListener{

    public static final String HIV_TYPE = "HIV_TEST_KIT";
    public static final String SYPHILIS_TYPE = "SYPHILIS_TEST_KIT";
    private static final TestService INSTANCE = new TestService( new Test() );
    private static final TypeOfSampleTestDAOImpl TYPE_OF_SAMPLE_TEST_DAO = new TypeOfSampleTestDAOImpl();
    private static String VARIABLE_TYPE_OF_SAMPLE_ID;
    private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
    private static final TestResultDAO TEST_RESULT_DAO = new TestResultDAOImpl();
    private static final TestDAO TEST_DAO = new TestDAOImpl();
    private static final TypeOfSampleDAO TYPE_OF_SAMPLE_DAO = new TypeOfSampleDAOImpl();

    private final Test test;

    private static Map<Entity, Map<String, String>> entityToMap;

    public static List<Test> getTestsInTestSectionById(String testSectionId) {
        return new TestDAOImpl().getTestsByTestSectionId(testSectionId);
    }

    public enum Entity{
        TEST_NAME,
        TEST_AUGMENTED_NAME,
        TEST_REPORTING_NAME
    }

    static{
        TypeOfSample variableTypeOfSample = new TypeOfSampleDAOImpl().getTypeOfSampleByLocalAbbrevAndDomain( "Variable", "H" );
        VARIABLE_TYPE_OF_SAMPLE_ID = variableTypeOfSample == null ? "-1" : variableTypeOfSample.getId();

        entityToMap = new HashMap<Entity, Map<String, String>>();
        entityToMap.put( Entity.TEST_NAME, createTestIdToNameMap() );
        entityToMap.put(Entity.TEST_AUGMENTED_NAME, createTestIdToAugmentedNameMap());
        entityToMap.put(Entity.TEST_REPORTING_NAME, createTestIdToReportingNameMap());

        SystemConfiguration.getInstance().addLocalChangeListener( INSTANCE );
    }

    @Override
    public void localeChanged( String locale ){
        LANGUAGE_LOCALE = locale;
        refreshTestNames();
    }

    public static void refreshTestNames(){
        entityToMap.put( Entity.TEST_NAME, createTestIdToNameMap() );
        entityToMap.put(Entity.TEST_AUGMENTED_NAME, createTestIdToAugmentedNameMap());
        entityToMap.put(Entity.TEST_REPORTING_NAME, createTestIdToReportingNameMap());
    }

    public TestService(Test test){
        this.test = test;
    }

    public TestService(String testId){
        this.test = TEST_DAO.getTestById( testId );
    }

    public Test getTest(){
        return test;
    }

    public String getTestMethodName(){
        return (test != null && test.getMethod() != null) ? test.getMethod().getMethodName() : null;
    }

    public boolean isActive(){
        return test == null ? false : test.isActive();
    }
    @SuppressWarnings("unchecked")
    public List<TestResult> getPossibleTestResults( ) {
        return TEST_RESULT_DAO.getAllActiveTestResultsPerTest( test );
    }

    public String getUOM(boolean isCD4Conclusion){
        if (!isCD4Conclusion) {
            if (test != null && test.getUnitOfMeasure() != null) {
                return test.getUnitOfMeasure().getName();
            }
        }

        return "";
    }

    public boolean isReportable(){
        return test != null && "Y".equals( test.getIsReportable() );
    }

    public String getSortOrder(){
        return test == null ? "0" :test.getSortOrder();
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

    public String getResultType(){
        String testResultType = TypeOfTestResultService.ResultType.NUMERIC.getCharacterValue();
        List<TestResult> testResults = getPossibleTestResults();

        if (testResults != null && !testResults.isEmpty()) {
            testResultType = testResults.get(0).getTestResultType();
        }

        return testResultType;
    }

    public TypeOfSample getTypeOfSample(){
        if( test == null){
            return null;
        }

        TypeOfSampleTest typeOfSampleTest = TYPE_OF_SAMPLE_TEST_DAO.getTypeOfSampleTestForTest(test.getId());

        if( typeOfSampleTest == null){
            return null;
        }

       String typeOfSampleId = typeOfSampleTest.getTypeOfSampleId();

        return TYPE_OF_SAMPLE_DAO.getTypeOfSampleById( typeOfSampleId );
    }

    public List<Panel> getPanels(){
        List<Panel> panelList = new ArrayList<Panel>();
        if( test != null){
            List<PanelItem> panelItemList = new PanelItemDAOImpl().getPanelItemByTestId(test.getId());
            for( PanelItem panelItem : panelItemList){
                panelList.add(panelItem.getPanel());
            }
        }

        return panelList;
    }
    
    public List<Panel> getAllPanels(){
        return new PanelDAOImpl().getAllPanels();
    }    
    
    public TestSection getTestSection(){
        return test == null ? null : test.getTestSection();
    }

    public String getTestSectionName(){
        return TestSectionService.getUserLocalizedTesSectionName(getTestSection());
    }

    public static List<Test> getAllActiveTests(){
        return TEST_DAO.getAllActiveTests(false);
    }

    public static Map<String, String> getMap( Entity entiy){
        return entityToMap.get(entiy);
    }

    public static String getUserLocalizedTestName( Test test ){
        if( test == null){
            return "";
        }

        return getUserLocalizedTestName( test.getId() );
    }

    public static String getUserLocalizedReportingTestName( String testId ){
        String name = entityToMap.get(Entity.TEST_REPORTING_NAME).get( testId );
        return name == null ? "" : name;
    }

    public static String getUserLocalizedReportingTestName( Test test ){
        if( test == null){
            return "";
        }

        return getUserLocalizedReportingTestName( test.getId() );
    }

    public static String getUserLocalizedTestName( String testId ){
        String name = entityToMap.get(Entity.TEST_NAME).get( testId );
        return name == null ? "" : name;
    }
    /**
     * Returns the test name augmented with the sample type IF  ConfigurationProperties.Property.TEST_NAME_AUGMENTED
     * is true. If it is not true just the test name will be returned.  The test name will be correct for the current locale
     * @param test The test for which we want the name
     * @return The test name or the augmented test name
     */
    public static String getLocalizedTestNameWithType( Test test ){
        if( test == null){
            return "";
        }

        return getLocalizedTestNameWithType( test.getId() );
    }

    /**
     * Returns the test name augmented with the sample type IF  ConfigurationProperties.Property.TEST_NAME_AUGMENTED
     * is true. If it is not true just the test name will be returned.  The test name will be correct for the current locale
     * @param testId The test id of the test for which we want the name
     * @return The test name or the augmented test name
     */
    public static String getLocalizedTestNameWithType( String testId ){
        String description = entityToMap.get(Entity.TEST_AUGMENTED_NAME ).get( testId );
        return description == null ? "" : description;
    }
    private static Map<String, String> createTestIdToNameMap() {
        Map<String, String> testIdToNameMap = new HashMap<String, String>();

        List<Test> tests = new TestDAOImpl().getAllTests(false);

        for (Test test : tests) {
            testIdToNameMap.put(test.getId(), buildTestName( test ).replace("\n", " "));
        }

        return testIdToNameMap;
    }

    private static String buildTestName( Test test ){
        Localization localization = test.getLocalizedTestName();

        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
            return localization.getFrench();
        }else{
            return localization.getEnglish();
        }
    }
    private static Map<String, String> createTestIdToAugmentedNameMap() {
        Map<String, String> testIdToNameMap = new HashMap<String, String>();

        List<Test> tests = new TestDAOImpl().getAllTests(false);

        for (Test test : tests) {
            testIdToNameMap.put(test.getId(), buildAugmentedTestName( test ).replace( "\n", " " ));
        }

        return testIdToNameMap;
    }

    private static Map<String, String> createTestIdToReportingNameMap(){
        Map<String, String> testIdToNameMap = new HashMap<String, String>();

        List<Test> tests = new TestDAOImpl().getAllActiveTests(false);

        for (Test test : tests) {
            testIdToNameMap.put(test.getId(), buildReportingTestName( test ) );
        }

        return testIdToNameMap;
    }

    private static String buildReportingTestName( Test test ){
        Localization localization = test.getLocalizedReportingName();

        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
            return localization.getFrench();
        }else{
            return localization.getEnglish();
        }
    }
    private static String buildAugmentedTestName( Test test ){
        Localization localization = test.getLocalizedTestName();

        String sampleName = "";

        if( ConfigurationProperties.getInstance().isPropertyValueEqual( ConfigurationProperties.Property.TEST_NAME_AUGMENTED, "true" )){
            TypeOfSample typeOfSample = new TestService( test ).getTypeOfSample();
            if( typeOfSample != null && !typeOfSample.getId().equals( VARIABLE_TYPE_OF_SAMPLE_ID ) ){
                sampleName = "(" + typeOfSample.getLocalizedName() + ")";
            }
        }

        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
            return localization.getFrench() + sampleName;
        }else{
            return localization.getEnglish()  + sampleName;
        }
    }

}
