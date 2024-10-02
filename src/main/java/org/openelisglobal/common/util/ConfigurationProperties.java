/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.mozilla.org/MPL/
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
 * ANY KIND, either express or implied. See the License for the specific language governing rights
 * and limitations under the License.
 *
 * <p>The Original Code is OpenELIS code.
 *
 * <p>Copyright (C) The Minnesota Department of Health. All Rights Reserved.
 *
 * <p>Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.common.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.spring.util.SpringContext;

/*
 * This is an abstract class which represents the configuration properties of the application.  The derived
 * classes will determine how the propertiesValueMap is populated
 */
public abstract class ConfigurationProperties {

    private static final Object lockObj = new Object();
    private static ConfigurationProperties activeConcreteInstance = null;
    protected Map<ConfigurationProperties.Property, String> propertiesValueMap = new HashMap<>();

    // These should all be upper case. As you touch them change them
    public enum Property {
        AmbiguousDateValue("date.ambiguous.date.value"), // Are ambiguous dates allowed for DOB. i.e. patient knows age
        // but not actuall DOB
        AmbiguousDateHolder("date.ambiguous.date.holder"), // What character should be used as a placeholder when
        // displaying the date. i.e. if 'X' then XX/XX/2000
        ReferingLabParentOrg("organization.reference.lab.parent"), // Should the parent organization of a lab be
        // entered. i.e. The hospital in which the lab is run
        FormFieldSet("setFieldForm"), // internal only
        PasswordRequirments("passwordRequirements"), // Indicator for what the password complexity requirements are.
        // Should be standardized
        StringContext("stringContext"), // Determines which equivalent string should be used. i.e. label for
        // accession
        // number can be 'lab number' or 'accession number'
        StatusRules("statusRules"), // Only used to separate RetroCI rules from others. May be obsolete
        SiteCode("siteNumber"), // Code referring to site
        SiteName("SiteName"), // Name for site
        Addressline1label("Address line 1 label"), // The address lines 1 can be used to specify anything
        Addressline2label("Address line 2 label"), // The address lines 2 can be used to specify anything
        Addressline3label("Address line 3 label"), // The address lines 3 can be used to specify anything
        GeographicUnit1Label("Geographic Unit 1 Label"), // The Geographical units for a particular region
        GeographicUnit2Label("Geographic Unit 2 Label"), // The Geographical units for a particular district
        AccessionFormat("acessionFormat"), // Format of accession number can be one of SITEYEARNUM, YEARNUM OR
        // PROGRAMNUM, ALPHANUM see AccessionNumberValidatorFactory
        ReflexAction("reflexAction"), // In combination with flags in reflex table determines actual action,
        // should be
        // standardize
        TrainingInstallation("TrainingInstallation"), // Flag to indicate if this is a training instance
        UseExternalPatientInfo("useExternalPatientSource"), // If true patient information will be searched for
        // externally
        PatientSearchURL("patientSearchURL"), // URL of where external patient information will be searched
        PatientSearchUserName("patientSearchLogOnUser"), // User name for accesses to external patient search
        PatientSearchPassword("PatientSearchPassword"), // User password for accesses to external patient search
        PatientSearchEnabled("PatientSearchEnabled"), labDirectorName("lab director"), // The name of the lab director
        languageSwitch("allowLanguageChange"), // If true a user can switch between English and French (changes it
        // for
        // everybody)
        reportResults("resultReporting"), // If true results will be reported electronically
        resultReportingURL("resultReportingURL"), // URL for electronic result reporting
        malariaSurveillanceReport("malariaSurReport"), // If true malaria surveillance will be reported electronically
        malariaSurveillanceReportURL("malariaSurURL"), // URL for malaria surveillance reporting
        malariaCaseReport("malariaCaseReport"), // If true send malaria case reports
        malariaCaseReportURL("malariaCaseURL"), // URL for malaria case reports
        releaseNumber("releaseNumber"), // The release number
        // buildNumber("buildNumber"), //Repository identifier
        configurationName("configuration name"), // Identifies the configuration i.e. HaitiLNSP or CI IPCI
        testUsageReportingURL("testUsageAggregationUrl"), // URL for test summary reporting
        testUsageReporting("testUsageReporting"), // If true send test summary report electronically
        roleRequiredForModifyResults("modify results role"), // If true a separate role is needed to modify results
        notesRequiredForModifyResults("modify results note required"), // If true a note is required when a result is
        // modified
        resultTechnicianName("ResultTechnicianName"), // If true the technicians name is needed for results
        customCriticalMessage("customCriticalMessage"), // Override the default message for a critical result
        allowResultRejection("allowResultRejection"), // If true then a technician has the ability to reject an
        // individual test and select a reason for rejection
        restrictFreeTextRefSiteEntry("restrictFreeTextRefSiteEntry"), // If true then a user cannot enter new referring
        // sites during sample entry and must choose from
        // list provided
        restrictFreeTextMethodEntry("restrictFreeTextMethodEntry"), // If true the user must choose an existing method
        // and will not be able to create a new method
        // through result entry
        restrictFreeTextProviderEntry("restrictFreeTextProviderEntry"), // If true then a user cannot enter new
        // providers during sample entry and must choose
        // from list provided
        autoFillTechNameBox("autoFillTechNameBox"), // If true a box will be provided to auto-fill technicians name for
        // all results on page
        autoFillTechNameUser("autoFillTechNameUser"), // If true the technicians name will be auto-filled with the name
        // of the logged in user
        AUTOFILL_COLLECTION_DATE("auto-fill collection date/time"), // If true the collection date will be auto-filled
        // with current date
        failedValidationMarker("showValidationFailureIcon"), // If true results that failed validation will have icon
        // next to them
        resultsResendTime("results.send.retry.time"), // How much time between trying to resend results that failed to
        // reach their destination
        TRACK_PATIENT_PAYMENT("trackPayment"), // If true then patient payment status can be entered
        ACCESSION_NUMBER_VALIDATE("validateAccessionNumber"), // If true then validate the accession number
        ALERT_FOR_INVALID_RESULTS("alertWhenInvalidResult"), // If true then technician will get an alert for results
        // outside of the valid range
        DEFAULT_LANG_LOCALE("default language locale"), // Default language locale
        DEFAULT_DATE_LOCALE("default date locale"), // Date local
        CONDENSE_NFS_PANEL("condenseNFS"), // Flag used for RetroCI to condense NFS panel tests
        PATIENT_DATA_ON_RESULTS_BY_ROLE("roleForPatientOnResults"), // If true patient data will show on results page
        // only if user has correct permissions
        USE_PAGE_NUMBERS_ON_REPORTS("reportPageNumbers"), // If true page numbers will be used on reports
        QA_SORT_EVENT_LIST("sortQaEvents"), // If true QA events will be sorted by name
        ALWAYS_VALIDATE_RESULTS("validate all results"), // If true all results will be validated, otherwise just those
        // outside of valid range and those flagged
        ADDITIONAL_SITE_INFO("additional site info"), // Extra site info for report header
        SUBJECT_ON_WORKPLAN("subject on workplan"), // If true the subject will be on the workplan
        NEXT_VISIT_DATE_ON_WORKPLAN("next visit on workplan"), // If true the next visit date will be on workplan
        RESULTS_ON_WORKPLAN("results on workplan"), // If true a space will be left for results on the workplan
        ACCEPT_EXTERNAL_ORDERS("external orders"), // If true EMR's can make electronic orders to lab
        SIGNATURES_ON_NONCONFORMITY_REPORTS("non-conformity signature"), // If true a space should be left for
        // signatures on non-conformity reports
        NONCONFORMITY_RECEPTION_AS_UNIT("Reception as unit"), // If true then reception will be an option for where a
        // non-conformity was identified
        NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT("Collection as unit"), // If true then sample collection will be an
        // option for where a non-conformity was
        // identified
        ACCESSION_NUMBER_PREFIX("Accession number prefix"), // If SITEYEARNUM is the format then this is the prefix
        NOTE_EXTERNAL_ONLY_FOR_VALIDATION("validationOnlyNotesAreExternal"), // If true then only validation notes will
        // be on patient report
        PHONE_FORMAT("phone format"), // Format of phone number
        VALIDATE_PHONE_FORMAT("validate phone format"), // If true then entered phone numbers will be validated against
        // format
        ALLOW_DUPLICATE_SUBJECT_NUMBERS("Allow duplicate subject number"), // If true then duplicate subject numbers are
        // allowed
        ALLOW_DUPLICATE_NATIONAL_IDS("Allow duplicate national ids"), // If true then duplicate national ids are allowed
        VALIDATE_REJECTED_TESTS("validateTechnicalRejection"), // If true then if the technician rejects a test the next
        // step is validation
        TEST_NAME_AUGMENTED("augmentTestNameWithType"), // If true then in some places the test name will be suffixed
        // with the sample type
        USE_BILLING_REFERENCE_NUMBER("billingRefNumber"), // If true then the user can enter billing codes for latter
        // reporting
        BILLING_REFERENCE_NUMBER_LABEL("billingRefNumberLocalization"), // The label being used for the billing
        // reference number
        ORDER_PROGRAM("Program"), // Should program be part of an order
        BANNER_TEXT("bannerHeading"), // Text on Banner
        CLOCK_24("24 hour clock"), // True for 24 hour clock, false for 12 hour clock
        PATIENT_NATIONALITY("supportPatientNationality"), // True if patient nationality should be collected with
        // patient information
        PATIENT_ID_REQUIRED("Patient ID required"), // True if patient id is required for new patient
        PATIENT_SUBJECT_NUMBER_REQUIRED("Subject number required"), // True if patient subject number is required for
        // new patient
        PATIENT_NATIONAL_ID_REQUIRED("National ID required"), // True if patient national id is required for new patient
        QA_SAMPLE_ID_REQUIRED("sample id required"), // True if sample id required from referring lab
        MAX_ORDER_PRINTED("numMaxOrderLabels"), // Max number of order labels that can be printed
        MAX_SPECIMEN_PRINTED("numMaxSpecimenLabels"), // Max number of specimen labels that can be printed
        MAX_ALIQUOT_PRINTED("numMaxAliquotLabels"), // Max number of aliquots that can be printed
        DEFAULT_ORDER_PRINTED("numDefaultOrderLabels"), // Max number of order labels that can be printed
        DEFAULT_SPECIMEN_PRINTED("numDefaultSpecimenLabels"), // Max number of specimen labels that can be printed
        DEFAULT_ALIQUOT_PRINTED("numDefaultAliquotLabels"), // Max number of aliquots that can be printed
        ORDER_BARCODE_HEIGHT("heightOrderLabels"), // Height of the order barcode
        ORDER_BARCODE_WIDTH("widthOrderLabels"), // Width of the order barcode
        SPECIMEN_BARCODE_HEIGHT("heightSpecimenLabels"), // Height of the specimen barcode
        SPECIMEN_BARCODE_WIDTH("widthSpecimenLabels"), // Width of the specimen barcode
        SPECIMEN_FIELD_DATE("collectionDateCheck"), //
        SPECIMEN_FIELD_SEX("patientSexCheck"), //
        SPECIMEN_FIELD_COLLECTED_BY("collectedByCheck"), SPECIMEN_FIELD_TESTS("testsCheck"), //
        BLOCK_BARCODE_HEIGHT("heightBlockLabels"), //
        BLOCK_BARCODE_WIDTH("widthBlockLabels"), //
        SLIDE_BARCODE_HEIGHT("heightSlideLabels"), //
        SLIDE_BARCODE_WIDTH("widthSlideLabels"), //
        ALT_ACCESSION_PREFIX("prePrintAltAccessionPrefix"), //
        USE_ALT_ACCESSION_PREFIX("prePrintUseAltAccession"), //
        USE_ALPHANUM_ACCESSION_PREFIX("useAlphanumAccessionPrefix"), //
        ALPHANUM_ACCESSION_PREFIX("alphanumAccessionPrefix"), //
        LAB_DIRECTOR_NAME("labDirectorName"), //
        LAB_DIRECTOR_TITLE("labDirectorTitle"), //
        INFO_HIGHWAY_USERNAME("infoHighway.username"), //
        INFO_HIGHWAY_PASSWORD("infoHighway.password"), //
        INFO_HIGHWAY_ADDRESS("infoHighway.uri"), //
        INFO_HIGHWAY_ENABLED("infoHighway.enabled"), //
        PATIENT_RESULTS_BMP_SMS_USERNAME("patientresultsbmpsms.username"), //
        PATIENT_RESULTS_BMP_SMS_PASSWORD("patientresultsbmpsms.password"), //
        PATIENT_RESULTS_BMP_SMS_ADDRESS("patientresultsbmpsms.uri"), //
        PATIENT_RESULTS_BMP_SMS_ENABLED("patientresultsbmpsms.enabled"), //
        PATIENT_RESULTS_SMPP_SMS_USERNAME("patientresultssmpp.username"), //
        PATIENT_RESULTS_SMPP_SMS_PASSWORD("patientresultssmpp.password"), //
        PATIENT_RESULTS_SMPP_SMS_ADDRESS("patientresultssmpp.uri"), //
        PATIENT_RESULTS_SMPP_SMS_ENABLED("patientresultssmpp.enabled"), //
        PATIENT_RESULTS_SMTP_USERNAME("patientresultssmtp.username"), //
        PATIENT_RESULTS_SMTP_PASSWORD("patientresultssmtp.password"), //
        PATIENT_RESULTS_SMTP_ADDRESS("patientresultssmtp.uri"), //
        PATIENT_RESULTS_SMTP_ENABLED("patientresultssmtp.enabled"), //
        CONTACT_TRACING("contactTracingEnabled"), //
        REQUIRE_LAB_UNIT_AT_LOGIN("requireLabUnitAtLogin"), //
        ENABLE_CLIENT_REGISTRY("enableClientRegistry"); // if true, then client registry search option is visible on
                                                        // the ui

        private String name;

        private Property(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static ConfigurationProperties getInstance() {
        synchronized (lockObj) {
            if (activeConcreteInstance == null) {
                activeConcreteInstance = new DefaultConfigurationProperties();
            }
        }
        return activeConcreteInstance;
    }

    public String getPropertyValue(Property property) {
        loadIfPropertyValueNeeded(property);

        return GenericValidator.isBlankOrNull(propertiesValueMap.get(property)) ? null
                : propertiesValueMap.get(property).trim();
    }

    public String getPropertyValueUpperCase(Property property) {
        String value = getPropertyValue(property);
        return value == null ? null : value.toUpperCase();
    }

    public String getPropertyValueLowerCase(Property property) {
        String value = getPropertyValue(property);
        return value == null ? null : value.toLowerCase();
    }

    public static void forceReload() {
        activeConcreteInstance = null;
        SpringContext.getBean(ConfigurationListenerService.class).refreshConfigurations();
    }

    /*
     * Allowing for lazy loading.
     */
    protected abstract void loadIfPropertyValueNeeded(Property property);

    public boolean isPropertyValueEqual(Property property, String target) {

        if (target == null) {
            return getPropertyValue(property) == null;
        } else {
            return target.equals(getPropertyValue(property));
        }
    }

    public boolean isCaseInsensitivePropertyValueEqual(Property property, String target) {
        if (target == null) {
            return getPropertyValue(property) == null;
        } else {
            return target.toLowerCase().equals(getPropertyValueLowerCase(property));
        }
    }

    public void setPropertyValue(Property property, String value) {
        propertiesValueMap.put(property, value);
    }

    /**
     * For testing only to set a controllable singleton
     *
     * @param activeConcreteInstance
     */
    public static void setActiveConcreteInstance(ConfigurationProperties activeConcreteInstance) {
        ConfigurationProperties.activeConcreteInstance = activeConcreteInstance;
    }
}
