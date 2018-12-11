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
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

/**
 */
public class UnitOfMeasureService implements LocaleChangeListener{


    private static final UnitOfMeasureService INSTANCE = new UnitOfMeasureService( new UnitOfMeasure() );
    private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
    private UnitOfMeasure unitOfMeasure;

    private static Map<String, String> unitOfMeasureIdToNameMap = new HashMap<String, String>();


    static{
        createTestIdToNameMap();


        SystemConfiguration.getInstance().addLocalChangeListener( INSTANCE );
    }

    public UnitOfMeasureService(UnitOfMeasure unitOfMeasure){
        this.unitOfMeasure = unitOfMeasure;
    }

    public UnitOfMeasureService(String unitOfMeasureId){
        this.unitOfMeasure = new UnitOfMeasureDAOImpl().getUnitOfMeasureById(unitOfMeasureId);
    }

    public UnitOfMeasure getUnitOfMeasure(){
        return unitOfMeasure;
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
        return unitOfMeasure == null ? "0" :unitOfMeasure.getSortOrder();
    }


    public static String getUserLocalizedUnitOfMeasureName(UnitOfMeasure unitOfMeasure){
        if( unitOfMeasure == null){
            return "";
        }

        return getUserLocalizedUnitOfMeasureName(unitOfMeasure.getId());
    }

    public static String getUserLocalizedUnitOfMeasureName( String unitOfMeasureId ){
        String name = unitOfMeasureIdToNameMap.get(unitOfMeasureId);
        return name == null ? "" : name;
    }




    private static void createTestIdToNameMap() {
    	unitOfMeasureIdToNameMap = new HashMap<String, String>();

        List<UnitOfMeasure> UnitOfMeasures = new UnitOfMeasureDAOImpl().getAllUnitOfMeasures();

        for (UnitOfMeasure unitOfMeasure : UnitOfMeasures) {
            unitOfMeasureIdToNameMap.put(unitOfMeasure.getId(), buildUnitOfMeasureName( unitOfMeasure ).replace("\n", " "));
        }
    }

    private static String buildUnitOfMeasureName( UnitOfMeasure unitOfMeasure ){
//        Localization localization = unitOfMeasure.getLocalization();
//
//        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
//            return localization.getFrench();
//        }else{
//            return localization.getEnglish();
//        }
//  }

//    public static List<Test> getTestsInSection(String id) {
//        return TestService.getTestsInTestSectionById(id);
    	return ""; // just for compile
    }

    public static List<UnitOfMeasure> getAllUnitOfMeasures() {
        return new UnitOfMeasureDAOImpl().getAllUnitOfMeasures();
    }
}
