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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

/**
 */
public class TestSectionService implements LocaleChangeListener{


    private static final TestSectionService INSTANCE = new TestSectionService( new TestSection() );
    private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
    private TestSection testSection;

    private static Map<String, String> testUnitIdToNameMap = new HashMap<String, String>();


    static{
        createTestIdToNameMap();


        SystemConfiguration.getInstance().addLocalChangeListener( INSTANCE );
    }

    public TestSectionService(TestSection testSection){
        this.testSection = testSection;
    }

    public TestSectionService(String testSectionId){
        this.testSection = new TestSectionDAOImpl().getTestSectionById(testSectionId);
    }

    public TestSection getTestSection(){
        return testSection;
    }
    @Override
    public void localeChanged( String locale ){
        LANGUAGE_LOCALE = locale;
        testNamesChanged();
    }

    public static void refreshNames(){
        testNamesChanged();
    }

    public static void testNamesChanged( ){
        createTestIdToNameMap();
    }

    public String getSortOrder(){
        return testSection == null ? "0" :testSection.getSortOrder();
    }



    public static String getUserLocalizedTesSectionName(TestSection testSection){
        if( testSection == null){
            return "";
        }

        return getUserLocalizedTestSectionName(testSection.getId());
    }

    public static String getUserLocalizedTestSectionName( String testSectionId ){
        String name = testUnitIdToNameMap.get(testSectionId);
        return name == null ? "" : name;
    }



    private static void createTestIdToNameMap() {
        testUnitIdToNameMap = new HashMap<String, String>();

        List<TestSection> testSections = new TestSectionDAOImpl().getAllTestSections();

        for (TestSection testSection : testSections) {
            testUnitIdToNameMap.put(testSection.getId(), buildTestSectionName( testSection ).replace("\n", " "));
        }
    }

    private static String buildTestSectionName( TestSection testSection ){
        Localization localization = testSection.getLocalization();

        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
            return localization.getFrench();
        }else{
            return localization.getEnglish();
        }
    }

    public static List<Test> getTestsInSection(String id) {
        return TestService.getTestsInTestSectionById(id);
    }

    public static List<TestSection> getAllTestSections() {
        return new TestSectionDAOImpl().getAllTestSections();
    }
}
