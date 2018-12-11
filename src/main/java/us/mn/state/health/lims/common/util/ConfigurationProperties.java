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
package us.mn.state.health.lims.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

/*
 * This is an abstract class which represents the configuration properties of the application.  The derived
 * classes will determine how the propertiesValueMap is populated
 */
public abstract class ConfigurationProperties {

    public enum LOCALE{
        ENGLISH("en_US"),
        FRENCH("fr-FR");
        private String locale;
        private LOCALE(String locale){
            this.locale = locale;
        }
        public String getRepresentation(){
            return locale;
        }
    }
	private static final Object lockObj = new Object();
	private static ConfigurationProperties activeConcreteInstance= null;
	protected Map<ConfigurationProperties.Property, String> propertiesValueMap = new HashMap<ConfigurationProperties.Property, String>();
    //These should all be upper case.  As you touch them change them
    public enum Property{
        AmbiguousDateValue,         //Are ambiguous dates allowed for DOB.  i.e. patient knows age but not actuall DOB
        AmbiguousDateHolder,        //What character should be used as a placeholder when displaying the date.  i.e. if 'X' then XX/XX/2000
        ReferingLabParentOrg,       //Should the parent organization of a lab be entered.  i.e. The hospital in which the lab is run
        FormFieldSet,               //internal only
        PasswordRequirments,        //Indicator for what the password complexity requirements are.  Should be standardized
        StringContext,              //Determines which equivalent string should be used.  i.e. label for accession number can be 'lab number' or 'accession number'
        StatusRules,                //Only used to separate RetroCI rules from others.  May be obsolete
        SiteCode,                   //Code referring to site
        SiteName,                   //Name for site
        AccessionFormat,            //Format of accession number can be one of SITEYEARNUM, YEARNUM OR PROGRAMNUM see AccessionNumberValidatorFactory
        ReflexAction,               //In combination with flags in reflex table determines actual action, should be standardize
        TrainingInstallation,       //Flag to indicate if this is a training instance
        UseExternalPatientInfo,     //If true patient information will be searched for externally
        PatientSearchURL,           //URL of where external patient information will be searched
        PatientSearchUserName,      //User name for accesses to external patient search
        PatientSearchPassword,      //User password for accesses to external patient search
        labDirectorName,            //The name of the lab director
        languageSwitch,             //If true a user can switch between English and French (changes it for everybody)
        reportResults,              //If true results will be reported electronically
        resultReportingURL,         //URL for electronic result reporting
        malariaSurveillanceReport,  //If true malaria surveillance will be reported electronically
        malariaSurveillanceReportURL,//URL for malaria surveillance reporting
        malariaCaseReport,          //If true send malaria case reports
        malariaCaseReportURL,       //URL for malaria case reports
        releaseNumber,              //The release number
        buildNumber,                //Repository identifier
        configurationName,          //Identifies the configuration i.e. HaitiLNSP or CI IPCI
        testUsageReportingURL,      //URL for test summary reporting
        testUsageReporting,         //If true send test summary report electronically
        roleRequiredForModifyResults,//If true a separate role is needed to modify results
        notesRequiredForModifyResults,//If true a note is required when a result is modified
        resultTechnicianName,        //If true the technicians name is needed for results
        allowResultRejection,        //If true then a technician has the ability to reject an individual test and select a reason for rejection
        restrictFreeTextRefSiteEntry, //If true then a user cannot enter new referring sites during sample entry and must choose from list provided
        autoFillTechNameBox,         //If true a box will be provided to auto-fill technicians name for all results on page
        autoFillTechNameUser,        //If true the technicians name will be auto-filled with the name of the logged in user
        AUTOFILL_COLLECTION_DATE,    //If true the collection date will be auto-filled with current date
        failedValidationMarker,      //If true results that failed validation will have icon next to them
        resultsResendTime,           //How much time between trying to resend results that failed to reach their destination
        TRACK_PATIENT_PAYMENT,       //If true then patient payment status can be entered
        ALERT_FOR_INVALID_RESULTS,   //If true then technician will get an alert for results outside of the valid range
        DEFAULT_LANG_LOCALE,         //Default language locale
        DEFAULT_DATE_LOCALE,         //Date local
        CONDENSE_NFS_PANEL,          //Flag used for RetroCI to condense NFS panel tests
        PATIENT_DATA_ON_RESULTS_BY_ROLE,//If true patient data will show on results page only if user has correct permissions
        USE_PAGE_NUMBERS_ON_REPORTS, //If true page numbers will be used on reports
        QA_SORT_EVENT_LIST,          //If true QA events will be sorted by name
        ALWAYS_VALIDATE_RESULTS,     //If true all results will be validated, otherwise just those outside of valid range and those flagged
        ADDITIONAL_SITE_INFO,        //Extra site info for report header
        SUBJECT_ON_WORKPLAN,         //If true the subject will be on the workplan
        NEXT_VISIT_DATE_ON_WORKPLAN, //If true the next visit date will be on workplan
        RESULTS_ON_WORKPLAN,         //If true a space will be left for results on the workplan
        ACCEPT_EXTERNAL_ORDERS,      //If true EMR's can make electronic orders to lab
        SIGNATURES_ON_NONCONFORMITY_REPORTS,   //If true a space should be left for signatures on non-conformity reports
        NONCONFORMITY_RECEPTION_AS_UNIT, //If true then reception will be an option for where a non-conformity was identified
        NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT, //If true then sample collection will be an option for where a non-conformity was identified
        ACCESSION_NUMBER_PREFIX,     //If SITEYEARNUM is the format then this is the prefix
        NOTE_EXTERNAL_ONLY_FOR_VALIDATION, //If true then only validation notes will be on patient report
        PHONE_FORMAT,                //Format of phone number
        VALIDATE_PHONE_FORMAT,       //If true then entered phone numbers will be validated against format
        ALLOW_DUPLICATE_SUBJECT_NUMBERS, //If true then duplicate subject numbers are allowed
        VALIDATE_REJECTED_TESTS, //If true then if the technician rejects a test the next step is validation
        TEST_NAME_AUGMENTED,   //If true then in some places the test name will be suffixed with the sample type
        USE_BILLING_REFERENCE_NUMBER,       //If true then the user can enter billing codes for latter reporting
        BILLING_REFERENCE_NUMBER_LABEL,   //The label being used for the billing reference number
        ORDER_PROGRAM,   //Should program be part of an order
        BANNER_TEXT, //Text on Banner
        CLOCK_24,    //True for 24 hour clock, false for 12 hour clock
        PATIENT_NATIONALITY, //True if patient nationality should be collected with patient information
        PATIENT_ID_REQUIRED, //True if patient id is required for new patient
        PATIENT_SUBJECT_NUMBER_REQUIRED, //True if patient subject number is required for new patient
        QA_SAMPLE_ID_REQUIRED,  //True if sample id required from referring lab
        MAX_ORDER_PRINTED,	//Max number of order labels that can be printed
        MAX_SPECIMEN_PRINTED,	//Max number of specimen labels that can be printed
        MAX_ALIQUOT_PRINTED,	//Max number of aliquots that can be printed
        ORDER_BARCODE_HEIGHT,	//Height of the order barcode
        ORDER_BARCODE_WIDTH,	//Width of the order barcode
        SPECIMEN_BARCODE_HEIGHT,	//Height of the specimen barcode
        SPECIMEN_BARCODE_WIDTH,	//Width of the specimen barcode
        SPECIMEN_FIELD_DATE,
        SPECIMEN_FIELD_SEX,
        SPECIMEN_FIELD_TESTS
    }

	

	public static ConfigurationProperties getInstance(){
		synchronized (lockObj) {
			if (activeConcreteInstance == null) {
				activeConcreteInstance = new DefaultConfigurationProperties();
			}
		}
		return activeConcreteInstance;
	}

	public String getPropertyValue( Property property ){
		loadIfPropertyValueNeeded(property);

		return GenericValidator.isBlankOrNull(propertiesValueMap.get(property)) ? null : propertiesValueMap.get(property).trim();
	}

	public String getPropertyValueUpperCase( Property property ){
		String value = getPropertyValue(property);
		return value == null ? null : value.toUpperCase();
	}

	public String getPropertyValueLowerCase( Property property ){
		String value = getPropertyValue(property);
		return value == null ? null : value.toLowerCase();
	}

	public static void forceReload(){
		activeConcreteInstance = null;
	}

	/*
	 * Allowing for lazy loading.
	 */
	abstract protected void loadIfPropertyValueNeeded(Property property);

	public boolean isPropertyValueEqual(Property property, String target) {
		
		if( target == null){
			return getPropertyValue(property) == null;
		}else{
			return target.equals(getPropertyValue(property));
		}
	}
	
	public boolean isCaseInsensitivePropertyValueEqual(Property property, String target) {
		if( target == null){
			return getPropertyValue(property) == null;
		}else{
			return target.toLowerCase().equals(getPropertyValueLowerCase(property));
		}
	}
	
	public void setPropertyValue( Property property, String value){
		propertiesValueMap.put(property, value);
	}

	/**
	 * For testing only to set a controllable singleton
	 * @param activeConcreteInstance
	 */
	public static void setActiveConcreteInstance( ConfigurationProperties activeConcreteInstance) {
		ConfigurationProperties.activeConcreteInstance = activeConcreteInstance;
	}
}
