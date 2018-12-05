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

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.dao.LocalizationDAO;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;

/**
 */
public class LocalizationService implements LocaleChangeListener{

    private static final LocalizationService INSTANCE = new LocalizationService(null);
    private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
    private static LocalizationDAO localizationDAO = new LocalizationDAOImpl();
    private Localization localization;

    static{
        SystemConfiguration.getInstance().addLocalChangeListener( INSTANCE );
    }

    public static String getCurrentLocale() {
        return LANGUAGE_LOCALE;
    }

    public enum LocalizationType{
        TEST_NAME("test name"),
        REPORTING_TEST_NAME("test report name"),
        BANNER_LABEL("Site information banner test"),
        TEST_UNIT_NAME("test unit name"),
        PANEL_NAME("panel name"),
        BILL_REF_LABEL("Billing reference_label");

        String dbLabel;

        LocalizationType( String dbLabel){
            this.dbLabel = dbLabel;
        }

        public String getDBDescription( ){ return dbLabel;}
    }

    public LocalizationService( String id){
        if( !GenericValidator.isBlankOrNull( id )){
            localization = localizationDAO.getLocalizationById( id );
        }
    }

    public static String getLocalizationValueByLocal(ConfigurationProperties.LOCALE locale, Localization localization){
        if( locale == ConfigurationProperties.LOCALE.FRENCH ){
            return localization.getFrench();
        }else{
            return localization.getEnglish();
        }
    }
    @Override
    public void localeChanged( String locale ){
        LANGUAGE_LOCALE = locale;
    }

    public static String getLocalizedValueById( String id){
        return getLocalizedValue(localizationDAO.getLocalizationById(id));
    }

    public static String getLocalizedValue(Localization localization){
        if( localization == null){
            return "";
        }
        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
            return localization.getFrench();
        }else{
            return localization.getEnglish();
        }
    }

    /**
     * Checks to see if localization is needed, if so it does the update.
     * @param french The french localization
     * @param english The english localization
     * @return true if the object can be found and an update is needed.  False if the id the service was created with is
     * not valid or the french or english is empty or null or the french and english match what is already in the object
     */
    public boolean updateLocalizationIfNeeded( String english, String french){
        if( localization == null || GenericValidator.isBlankOrNull( french ) || GenericValidator.isBlankOrNull( english )){
            return false;
        }

        if( localization == null){
            return false;
        }

        if( english.equals( localization.getEnglish() ) && french.equals( localization.getFrench() )){
            return false;
        }

        localization.setEnglish( english );
        localization.setFrench( french );
        return true;
    }

    public Localization getLocalization(){
        return localization;
    }

    public void setCurrentUserId( String currentUserId ){
        if( localization != null ){
            localization.setSysUserId( currentUserId );
        }
    }

    public static Localization createNewLocalization( String english, String french, LocalizationType type){
        Localization localization = new Localization();
        localization.setDescription(type.getDBDescription());
        localization.setEnglish(english);
        localization.setFrench(french);
        return localization;
    }
}
