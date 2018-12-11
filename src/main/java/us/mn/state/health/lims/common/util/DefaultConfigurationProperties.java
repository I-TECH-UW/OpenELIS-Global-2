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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class DefaultConfigurationProperties extends ConfigurationProperties {

	private static String propertyFile = "/SystemConfiguration.properties";
	private java.util.Properties properties = null;
	protected static Map<ConfigurationProperties.Property, KeyDefaultPair> propertiesFileMap;
	protected static Map<String, ConfigurationProperties.Property> dbNamePropertiesMap;
	private boolean databaseLoaded = false;

	{
		//config from SystemConfiguration.properties
		propertiesFileMap  = new HashMap<ConfigurationProperties.Property, KeyDefaultPair>();
		propertiesFileMap.put(Property.AmbiguousDateValue, new KeyDefaultPair("date.ambiguous.date.value", "01") );
		propertiesFileMap.put(Property.AmbiguousDateHolder , new KeyDefaultPair("date.ambiguous.date.holder", "X") );
		propertiesFileMap.put(Property.ReferingLabParentOrg , new KeyDefaultPair("organization.reference.lab.parent", null) );
		propertiesFileMap.put(Property.resultsResendTime , new KeyDefaultPair("results.send.retry.time", "30") );
//		propertiesFileMap.put(Property. , new KeyDefaultPair() );


		//config from site_information table
		dbNamePropertiesMap  = new HashMap<String, ConfigurationProperties.Property>();
		setDBPropertyMappingAndDefault(Property.SiteCode, "siteNumber", "" );
		setDBPropertyMappingAndDefault(Property.TrainingInstallation, "TrainingInstallation", "false");
		setDBPropertyMappingAndDefault(Property.PatientSearchURL, "patientSearchURL" , "");
		setDBPropertyMappingAndDefault(Property.PatientSearchUserName, "patientSearchLogOnUser" , "" );
		setDBPropertyMappingAndDefault(Property.PatientSearchPassword, "patientSearchPassword", "" );
		setDBPropertyMappingAndDefault(Property.UseExternalPatientInfo, "useExternalPatientSource" , "false");
		setDBPropertyMappingAndDefault(Property.labDirectorName, "lab director" , "");
		setDBPropertyMappingAndDefault(Property.languageSwitch, "allowLanguageChange", "true" );
		setDBPropertyMappingAndDefault(Property.resultReportingURL, "resultReportingURL", "");
		setDBPropertyMappingAndDefault(Property.reportResults, "resultReporting", "false");
		setDBPropertyMappingAndDefault(Property.malariaSurveillanceReportURL, "malariaSurURL", "");
		setDBPropertyMappingAndDefault(Property.malariaSurveillanceReport, "malariaSurReport", "false");
		setDBPropertyMappingAndDefault(Property.malariaCaseReport, "malariaCaseReport", "false");
		setDBPropertyMappingAndDefault(Property.malariaCaseReportURL, "malariaCaseURL", "");
		setDBPropertyMappingAndDefault(Property.testUsageReportingURL, "testUsageAggregationUrl", "");
		setDBPropertyMappingAndDefault(Property.testUsageReporting, "testUsageReporting", "false");
		setDBPropertyMappingAndDefault(Property.roleRequiredForModifyResults, "modify results role" , "false");
		setDBPropertyMappingAndDefault(Property.notesRequiredForModifyResults, "modify results note required", "false" );
		setDBPropertyMappingAndDefault(Property.resultTechnicianName, "ResultTechnicianName", "false");
		setDBPropertyMappingAndDefault(Property.allowResultRejection, "allowResultRejection", "false");
		setDBPropertyMappingAndDefault(Property.restrictFreeTextRefSiteEntry, "restrictFreeTextRefSiteEntry", "false");
		setDBPropertyMappingAndDefault(Property.autoFillTechNameBox, "autoFillTechNameBox", "false");
		setDBPropertyMappingAndDefault(Property.autoFillTechNameUser, "autoFillTechNameUser", "false");
		setDBPropertyMappingAndDefault(Property.failedValidationMarker, "showValidationFailureIcon", "true");
		setDBPropertyMappingAndDefault(Property.SiteName, "SiteName", "");
		setDBPropertyMappingAndDefault(Property.PasswordRequirments , "passwordRequirements", "MINN");
		setDBPropertyMappingAndDefault(Property.FormFieldSet , "setFieldForm", IActionConstants.FORM_FIELD_SET_CI_GENERAL);
		setDBPropertyMappingAndDefault(Property.StringContext , "stringContext","");
		setDBPropertyMappingAndDefault(Property.StatusRules , "statusRules", "CI");
		setDBPropertyMappingAndDefault(Property.ReflexAction , "reflexAction", "Haiti");
		setDBPropertyMappingAndDefault(Property.AccessionFormat , "acessionFormat", "SITEYEARNUM"); //spelled wrong in DB
		setDBPropertyMappingAndDefault(Property.TRACK_PATIENT_PAYMENT, "trackPayment", "false");
		setDBPropertyMappingAndDefault(Property.ALERT_FOR_INVALID_RESULTS, "alertWhenInvalidResult", "false");
		setDBPropertyMappingAndDefault(Property.DEFAULT_DATE_LOCALE, "default date locale", "fr-FR");
		setDBPropertyMappingAndDefault(Property.DEFAULT_LANG_LOCALE, "default language locale", "fr-FR");
		setDBPropertyMappingAndDefault(Property.configurationName, "configuration name", "not set");
		setDBPropertyMappingAndDefault(Property.CONDENSE_NFS_PANEL, "condenseNFS", "false");
		setDBPropertyMappingAndDefault(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE, "roleForPatientOnResults", "false");
		setDBPropertyMappingAndDefault(Property.USE_PAGE_NUMBERS_ON_REPORTS, "reportPageNumbers", "true");
		setDBPropertyMappingAndDefault(Property.QA_SORT_EVENT_LIST, "sortQaEvents", "true");
		setDBPropertyMappingAndDefault(Property.ALWAYS_VALIDATE_RESULTS, "validate all results", "true");
		setDBPropertyMappingAndDefault(Property.ADDITIONAL_SITE_INFO, "additional site info", "");
		setDBPropertyMappingAndDefault(Property.SUBJECT_ON_WORKPLAN, "subject on workplan", "false");
		setDBPropertyMappingAndDefault(Property.NEXT_VISIT_DATE_ON_WORKPLAN, "next visit on workplan", "false");
		setDBPropertyMappingAndDefault(Property.ACCEPT_EXTERNAL_ORDERS, "external orders", "false");
		setDBPropertyMappingAndDefault(Property.SIGNATURES_ON_NONCONFORMITY_REPORTS, "non-conformity signature", "false");
		setDBPropertyMappingAndDefault(Property.AUTOFILL_COLLECTION_DATE, "auto-fill collection date/time", "true");
		setDBPropertyMappingAndDefault(Property.RESULTS_ON_WORKPLAN, "results on workplan", "false");
		setDBPropertyMappingAndDefault(Property.NONCONFORMITY_RECEPTION_AS_UNIT, "Reception as unit", "true");
		setDBPropertyMappingAndDefault(Property.NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT, "Collection as unit", "false");
        setDBPropertyMappingAndDefault(Property.ACCESSION_NUMBER_PREFIX, "Accession number prefix", "");
        setDBPropertyMappingAndDefault(Property.NOTE_EXTERNAL_ONLY_FOR_VALIDATION, "validationOnlyNotesAreExternal", "false");
        setDBPropertyMappingAndDefault(Property.PHONE_FORMAT, "phone format", "(ddd) dddd-dddd");
        setDBPropertyMappingAndDefault(Property.VALIDATE_PHONE_FORMAT, "validate phone format", "true");
        setDBPropertyMappingAndDefault( Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS, "Allow duplicate subject number", "true" );
        setDBPropertyMappingAndDefault( Property.VALIDATE_REJECTED_TESTS, "validateTechnicalRejection", "false" );
        setDBPropertyMappingAndDefault( Property.TEST_NAME_AUGMENTED, "augmentTestNameWithType", "true" );
        setDBPropertyMappingAndDefault( Property.USE_BILLING_REFERENCE_NUMBER, "billingRefNumber", "false" );
        setDBPropertyMappingAndDefault( Property.BILLING_REFERENCE_NUMBER_LABEL, "billingRefNumberLocalization", "-1" );
        setDBPropertyMappingAndDefault( Property.ORDER_PROGRAM, "Program", "true" );
        setDBPropertyMappingAndDefault( Property.BANNER_TEXT, "bannerHeading", "-1" );
        setDBPropertyMappingAndDefault( Property.CLOCK_24, "24 hour clock", "true" );
		setDBPropertyMappingAndDefault( Property.PATIENT_NATIONALITY, "supportPatientNationality", "false");
		setDBPropertyMappingAndDefault( Property.PATIENT_ID_REQUIRED, "Patient ID required", "true");
		setDBPropertyMappingAndDefault( Property.PATIENT_SUBJECT_NUMBER_REQUIRED, "Subject number required", "true");
		setDBPropertyMappingAndDefault( Property.QA_SAMPLE_ID_REQUIRED, "sample id required", "false");
		setDBPropertyMappingAndDefault( Property.MAX_ORDER_PRINTED, "numOrderLabels", "10");
		setDBPropertyMappingAndDefault( Property.MAX_SPECIMEN_PRINTED, "numSpecimenLabels", "1");
		setDBPropertyMappingAndDefault( Property.MAX_ALIQUOT_PRINTED, "numAliquotLabels", "1");
		setDBPropertyMappingAndDefault( Property.ORDER_BARCODE_HEIGHT, "heightOrderLabels", "25.4");
		setDBPropertyMappingAndDefault( Property.ORDER_BARCODE_WIDTH, "widthOrderLabels", "76.2");
		setDBPropertyMappingAndDefault( Property.SPECIMEN_BARCODE_HEIGHT, "heightSpecimenLabels", "25.4");
		setDBPropertyMappingAndDefault( Property.SPECIMEN_BARCODE_WIDTH, "widthSpecimenLabels", "76.2");
		setDBPropertyMappingAndDefault( Property.SPECIMEN_FIELD_DATE, "collectionDateCheck", "true");
		setDBPropertyMappingAndDefault( Property.SPECIMEN_FIELD_SEX, "patientSexCheck", "true");
		setDBPropertyMappingAndDefault( Property.SPECIMEN_FIELD_TESTS, "testsCheck", "true");
	}

	private void setDBPropertyMappingAndDefault(Property property, String dbName, String defaultValue) {
		dbNamePropertiesMap.put(dbName, property);
		propertiesValueMap.put(property, defaultValue);
	}

	protected DefaultConfigurationProperties(){
		loadFromPropertiesFile();
		loadSpecial();
	}


	protected void loadIfPropertyValueNeeded(Property property){
		if( !databaseLoaded && dbNamePropertiesMap.containsValue(property)){
			loadFromDatabase();
		}
	}

	protected void loadFromDatabase() {
		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		List<SiteInformation> siteInformationList = siteInformationDAO.getAllSiteInformation();

		for( SiteInformation siteInformation : siteInformationList){
			Property property = dbNamePropertiesMap.get(siteInformation.getName());
			if( property != null){
				propertiesValueMap.put(property, siteInformation.getValue());
			}
		}

		databaseLoaded = true;
	}

	protected void loadFromPropertiesFile() {
		InputStream propertyStream = null;

		try {
			propertyStream = this.getClass().getResourceAsStream(propertyFile);

			// Now load a java.util.Properties object with the properties
			properties = new java.util.Properties();

			properties.load(propertyStream);

		} catch (Exception e) {
			LogEvent.logError("DefaultConfigurationProperties","",e.toString());
		} finally {
			if (null != propertyStream) {
				try {
					propertyStream.close();
					propertyStream = null;
				} catch (Exception e) {
			        LogEvent.logError("DefaultConfigurationProperties","",e.toString());
				}
			}

		}

		for( Property property : propertiesFileMap.keySet()){
			KeyDefaultPair pair = propertiesFileMap.get(property);
			String value = properties.getProperty( pair.key, pair.defaultValue);
			propertiesValueMap.put(property, value);
		}
	}
	
	private void loadSpecial() {
		propertiesValueMap.put(Property.releaseNumber, Versioning.getReleaseNumber());
		propertiesValueMap.put(Property.buildNumber, Versioning.getBuildNumber());
	}

	protected class KeyDefaultPair{
		public final String key;
		public final String defaultValue;

		public KeyDefaultPair( String key, String defaultValue){
			this.key = key;
			this.defaultValue = defaultValue;
		}
	}
}
