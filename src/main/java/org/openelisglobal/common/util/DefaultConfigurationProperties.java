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
 * <p>Copyright (C) CIRG, University of Washington, Seattle WA. All Rights Reserved.
 */
package org.openelisglobal.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.externalconnections.service.BasicAuthenticationDataService;
import org.openelisglobal.externalconnections.service.ExternalConnectionService;
import org.openelisglobal.externalconnections.valueholder.BasicAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.ProgrammedConnection;
import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.openelisglobal.spring.util.SpringContext;

public class DefaultConfigurationProperties extends ConfigurationProperties {

  private static String propertyFile = "/SystemConfiguration.properties";
  private java.util.Properties properties = null;
  protected static Map<ConfigurationProperties.Property, KeyDefaultPair> propertiesFileMap;
  protected static Map<String, ConfigurationProperties.Property> dbNamePropertiesMap;

  private boolean databaseLoaded = false;

  {
    // config from SystemConfiguration.properties
    propertiesFileMap = new HashMap<>();
    propertiesFileMap.put(
        Property.AmbiguousDateValue, new KeyDefaultPair("date.ambiguous.date.value", "01"));
    propertiesFileMap.put(
        Property.AmbiguousDateHolder, new KeyDefaultPair("date.ambiguous.date.holder", "X"));
    propertiesFileMap.put(
        Property.ReferingLabParentOrg,
        new KeyDefaultPair("organization.reference.lab.parent", null));
    propertiesFileMap.put(
        Property.resultsResendTime, new KeyDefaultPair("results.send.retry.time", "30"));

    //		propertiesFileMap.put(Property. , new KeyDefaultPair() );

    // config from site_information table
    dbNamePropertiesMap = new HashMap<>();
    setDBPropertyMappingAndDefault(Property.SiteCode, Property.SiteCode.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.TrainingInstallation, Property.TrainingInstallation.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.PatientSearchURL, Property.PatientSearchURL.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.PatientSearchUserName, Property.PatientSearchUserName.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.PatientSearchPassword, Property.PatientSearchPassword.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.PatientSearchEnabled, Property.PatientSearchEnabled.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.UseExternalPatientInfo, Property.UseExternalPatientInfo.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.labDirectorName, Property.labDirectorName.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.languageSwitch, Property.languageSwitch.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.resultReportingURL, Property.resultReportingURL.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.reportResults, Property.reportResults.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.malariaSurveillanceReportURL, Property.malariaSurveillanceReportURL.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.malariaSurveillanceReport, Property.malariaSurveillanceReport.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.malariaCaseReport, Property.malariaCaseReport.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.malariaCaseReportURL, Property.malariaCaseReportURL.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.testUsageReportingURL, Property.testUsageReportingURL.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.testUsageReporting, Property.testUsageReporting.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.roleRequiredForModifyResults,
        Property.roleRequiredForModifyResults.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.notesRequiredForModifyResults,
        Property.notesRequiredForModifyResults.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.resultTechnicianName, Property.resultTechnicianName.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.customCriticalMessage, Property.customCriticalMessage.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.allowResultRejection, Property.allowResultRejection.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.restrictFreeTextRefSiteEntry,
        Property.restrictFreeTextRefSiteEntry.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.restrictFreeTextMethodEntry,
        Property.restrictFreeTextMethodEntry.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.restrictFreeTextProviderEntry,
        Property.restrictFreeTextProviderEntry.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.autoFillTechNameBox, Property.autoFillTechNameBox.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.autoFillTechNameUser, Property.autoFillTechNameUser.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.failedValidationMarker, Property.failedValidationMarker.getName(), "true");
    setDBPropertyMappingAndDefault(Property.SiteName, Property.SiteName.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.Addressline1label, Property.Addressline1label.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.Addressline2label, Property.Addressline2label.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.Addressline3label, Property.Addressline3label.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.GeographicUnit1Label, Property.GeographicUnit1Label.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.GeographicUnit2Label, Property.GeographicUnit2Label.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.PasswordRequirments, Property.PasswordRequirments.getName(), "MINN");
    setDBPropertyMappingAndDefault(
        Property.FormFieldSet,
        Property.FormFieldSet.getName(),
        IActionConstants.FORM_FIELD_SET_MAURITIUS);
    setDBPropertyMappingAndDefault(Property.StringContext, Property.StringContext.getName(), "");
    setDBPropertyMappingAndDefault(Property.StatusRules, Property.StatusRules.getName(), "CI");
    setDBPropertyMappingAndDefault(Property.ReflexAction, Property.ReflexAction.getName(), "Haiti");
    setDBPropertyMappingAndDefault(
        Property.AccessionFormat, Property.AccessionFormat.getName(), "SITEYEARNUM"); // spelled
    // wrong
    // in
    // DB
    setDBPropertyMappingAndDefault(
        Property.TRACK_PATIENT_PAYMENT, Property.TRACK_PATIENT_PAYMENT.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.ACCESSION_NUMBER_VALIDATE, Property.ACCESSION_NUMBER_VALIDATE.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.ALERT_FOR_INVALID_RESULTS, Property.ALERT_FOR_INVALID_RESULTS.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.DEFAULT_DATE_LOCALE, Property.DEFAULT_DATE_LOCALE.getName(), "fr-FR");
    setDBPropertyMappingAndDefault(
        Property.DEFAULT_LANG_LOCALE, Property.DEFAULT_LANG_LOCALE.getName(), "fr-FR");
    setDBPropertyMappingAndDefault(
        Property.configurationName, Property.configurationName.getName(), "not set");
    setDBPropertyMappingAndDefault(
        Property.CONDENSE_NFS_PANEL, Property.CONDENSE_NFS_PANEL.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.PATIENT_DATA_ON_RESULTS_BY_ROLE,
        Property.PATIENT_DATA_ON_RESULTS_BY_ROLE.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.USE_PAGE_NUMBERS_ON_REPORTS,
        Property.USE_PAGE_NUMBERS_ON_REPORTS.getName(),
        "true");
    setDBPropertyMappingAndDefault(
        Property.QA_SORT_EVENT_LIST, Property.QA_SORT_EVENT_LIST.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.ALWAYS_VALIDATE_RESULTS, Property.ALWAYS_VALIDATE_RESULTS.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.ADDITIONAL_SITE_INFO, Property.ADDITIONAL_SITE_INFO.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.SUBJECT_ON_WORKPLAN, Property.SUBJECT_ON_WORKPLAN.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.NEXT_VISIT_DATE_ON_WORKPLAN,
        Property.NEXT_VISIT_DATE_ON_WORKPLAN.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.ACCEPT_EXTERNAL_ORDERS, Property.ACCEPT_EXTERNAL_ORDERS.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.SIGNATURES_ON_NONCONFORMITY_REPORTS,
        Property.SIGNATURES_ON_NONCONFORMITY_REPORTS.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.AUTOFILL_COLLECTION_DATE, Property.AUTOFILL_COLLECTION_DATE.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.RESULTS_ON_WORKPLAN, Property.RESULTS_ON_WORKPLAN.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.NONCONFORMITY_RECEPTION_AS_UNIT,
        Property.NONCONFORMITY_RECEPTION_AS_UNIT.getName(),
        "true");
    setDBPropertyMappingAndDefault(
        Property.NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT,
        Property.NONCONFORMITY_SAMPLE_COLLECTION_AS_UNIT.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.ACCESSION_NUMBER_PREFIX, Property.ACCESSION_NUMBER_PREFIX.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.NOTE_EXTERNAL_ONLY_FOR_VALIDATION,
        Property.NOTE_EXTERNAL_ONLY_FOR_VALIDATION.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.PHONE_FORMAT, Property.PHONE_FORMAT.getName(), "(ddd) dddd-dddd");
    setDBPropertyMappingAndDefault(
        Property.VALIDATE_PHONE_FORMAT, Property.VALIDATE_PHONE_FORMAT.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS,
        Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS.getName(),
        "true");
    setDBPropertyMappingAndDefault(
        Property.ALLOW_DUPLICATE_NATIONAL_IDS,
        Property.ALLOW_DUPLICATE_NATIONAL_IDS.getName(),
        "false");

    setDBPropertyMappingAndDefault(
        Property.VALIDATE_REJECTED_TESTS, Property.VALIDATE_REJECTED_TESTS.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.TEST_NAME_AUGMENTED, Property.TEST_NAME_AUGMENTED.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.USE_BILLING_REFERENCE_NUMBER,
        Property.USE_BILLING_REFERENCE_NUMBER.getName(),
        "false");
    setDBPropertyMappingAndDefault(
        Property.BILLING_REFERENCE_NUMBER_LABEL,
        Property.BILLING_REFERENCE_NUMBER_LABEL.getName(),
        "-1");
    setDBPropertyMappingAndDefault(
        Property.ORDER_PROGRAM, Property.ORDER_PROGRAM.getName(), "true");
    setDBPropertyMappingAndDefault(Property.BANNER_TEXT, Property.BANNER_TEXT.getName(), "-1");
    setDBPropertyMappingAndDefault(Property.CLOCK_24, Property.CLOCK_24.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.PATIENT_NATIONALITY, Property.PATIENT_NATIONALITY.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.PATIENT_ID_REQUIRED, Property.PATIENT_ID_REQUIRED.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.PATIENT_SUBJECT_NUMBER_REQUIRED,
        Property.PATIENT_SUBJECT_NUMBER_REQUIRED.getName(),
        "true");
    setDBPropertyMappingAndDefault(
        Property.PATIENT_NATIONAL_ID_REQUIRED,
        Property.PATIENT_NATIONAL_ID_REQUIRED.getName(),
        "true");

    setDBPropertyMappingAndDefault(
        Property.QA_SAMPLE_ID_REQUIRED, Property.QA_SAMPLE_ID_REQUIRED.getName(), "false");
    setDBPropertyMappingAndDefault(
        Property.MAX_ORDER_PRINTED, Property.MAX_ORDER_PRINTED.getName(), "10");
    setDBPropertyMappingAndDefault(
        Property.MAX_SPECIMEN_PRINTED, Property.MAX_SPECIMEN_PRINTED.getName(), "1");
    setDBPropertyMappingAndDefault(
        Property.MAX_ALIQUOT_PRINTED, Property.MAX_ALIQUOT_PRINTED.getName(), "1");
    setDBPropertyMappingAndDefault(
        Property.DEFAULT_ORDER_PRINTED, Property.DEFAULT_ORDER_PRINTED.getName(), "2");
    setDBPropertyMappingAndDefault(
        Property.DEFAULT_SPECIMEN_PRINTED, Property.DEFAULT_SPECIMEN_PRINTED.getName(), "1");
    setDBPropertyMappingAndDefault(
        Property.DEFAULT_ALIQUOT_PRINTED, Property.DEFAULT_ALIQUOT_PRINTED.getName(), "1");
    setDBPropertyMappingAndDefault(
        Property.ORDER_BARCODE_HEIGHT, Property.ORDER_BARCODE_HEIGHT.getName(), "25.4");
    setDBPropertyMappingAndDefault(
        Property.ORDER_BARCODE_WIDTH, Property.ORDER_BARCODE_WIDTH.getName(), "76.2");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_BARCODE_HEIGHT, Property.SPECIMEN_BARCODE_HEIGHT.getName(), "25.4");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_BARCODE_WIDTH, Property.SPECIMEN_BARCODE_WIDTH.getName(), "76.2");
    setDBPropertyMappingAndDefault(
        Property.BLOCK_BARCODE_HEIGHT, Property.BLOCK_BARCODE_HEIGHT.getName(), "25.4");
    setDBPropertyMappingAndDefault(
        Property.BLOCK_BARCODE_WIDTH, Property.BLOCK_BARCODE_WIDTH.getName(), "76.2");
    setDBPropertyMappingAndDefault(
        Property.SLIDE_BARCODE_HEIGHT, Property.SLIDE_BARCODE_HEIGHT.getName(), "25.4");
    setDBPropertyMappingAndDefault(
        Property.SLIDE_BARCODE_WIDTH, Property.SLIDE_BARCODE_WIDTH.getName(), "76.2");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_FIELD_DATE, Property.SPECIMEN_FIELD_DATE.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_FIELD_COLLECTED_BY,
        Property.SPECIMEN_FIELD_COLLECTED_BY.getName(),
        "true");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_FIELD_SEX, Property.SPECIMEN_FIELD_SEX.getName(), "true");
    setDBPropertyMappingAndDefault(
        Property.SPECIMEN_FIELD_TESTS, Property.SPECIMEN_FIELD_TESTS.getName(), "true");

    setDBPropertyMappingAndDefault(
        Property.ALT_ACCESSION_PREFIX, Property.ALT_ACCESSION_PREFIX.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.USE_ALT_ACCESSION_PREFIX, Property.USE_ALT_ACCESSION_PREFIX.getName(), "false");

    setDBPropertyMappingAndDefault(
        Property.LAB_DIRECTOR_NAME, Property.LAB_DIRECTOR_NAME.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.LAB_DIRECTOR_TITLE, Property.LAB_DIRECTOR_TITLE.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.CONTACT_TRACING, Property.CONTACT_TRACING.getName(), "false");
    // these are set through external connection now
    //        setDBPropertyMappingAndDefault(Property.INFO_HIGHWAY_ADDRESS,
    // Property.INFO_HIGHWAY_ADDRESS.getName(), "");
    //        setDBPropertyMappingAndDefault(Property.INFO_HIGHWAY_USERNAME,
    // Property.INFO_HIGHWAY_USERNAME.getName(), "");
    //        setDBPropertyMappingAndDefault(Property.INFO_HIGHWAY_PASSWORD,
    // Property.INFO_HIGHWAY_PASSWORD.getName(), "");
    //        setDBPropertyMappingAndDefault(Property.INFO_HIGHWAY_ENABLED,
    // Property.INFO_HIGHWAY_ENABLED.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.ALPHANUM_ACCESSION_PREFIX, Property.ALPHANUM_ACCESSION_PREFIX.getName(), "");
    setDBPropertyMappingAndDefault(
        Property.USE_ALPHANUM_ACCESSION_PREFIX,
        Property.USE_ALPHANUM_ACCESSION_PREFIX.getName(),
        "false");
  }

  private void setDBPropertyMappingAndDefault(
      Property property, String dbName, String defaultValue) {
    dbNamePropertiesMap.put(dbName, property);
    propertiesValueMap.put(property, defaultValue);
  }

  protected DefaultConfigurationProperties() {
    loadFromPropertiesFile();
    loadSpecial();
  }

  @Override
  protected void loadIfPropertyValueNeeded(Property property) {
    if (!databaseLoaded && dbNamePropertiesMap.containsValue(property)) {
      loadFromDatabase();
    }
  }

  protected void loadExternalConnectionsFromDatabase() {
    ExternalConnectionService externalConnectionsService =
        SpringContext.getBean(ExternalConnectionService.class);
    BasicAuthenticationDataService basicAuthenticationDataService =
        SpringContext.getBean(BasicAuthenticationDataService.class);

    Optional<ExternalConnection> infoHighwayConnection =
        externalConnectionsService.getMatch(
            "programmedConnection", ProgrammedConnection.INFO_HIGHWAY.name());
    propertiesValueMap.put(Property.INFO_HIGHWAY_ENABLED, Boolean.FALSE.toString());
    if (infoHighwayConnection.isPresent()) {
      Optional<BasicAuthenticationData> basicAuthData =
          basicAuthenticationDataService.getByExternalConnection(
              infoHighwayConnection.get().getId());
      // basic auth is required for info highway
      if (basicAuthData.isPresent()) {
        propertiesValueMap.put(
            Property.INFO_HIGHWAY_ADDRESS, infoHighwayConnection.get().getUri().toString());
        propertiesValueMap.put(Property.INFO_HIGHWAY_USERNAME, basicAuthData.get().getUsername());
        propertiesValueMap.put(Property.INFO_HIGHWAY_PASSWORD, basicAuthData.get().getPassword());
        if (infoHighwayConnection.get().getActive() != null) {
          propertiesValueMap.put(
              Property.INFO_HIGHWAY_ENABLED, infoHighwayConnection.get().getActive().toString());
        }
      }
    }
    Optional<ExternalConnection> smtpConnection =
        externalConnectionsService.getMatch(
            "programmedConnection", ProgrammedConnection.SMTP_SERVER.name());
    propertiesValueMap.put(Property.PATIENT_RESULTS_SMTP_ENABLED, Boolean.FALSE.toString());
    if (smtpConnection.isPresent()) {
      Optional<BasicAuthenticationData> basicAuthData =
          basicAuthenticationDataService.getByExternalConnection(smtpConnection.get().getId());
      propertiesValueMap.put(
          Property.PATIENT_RESULTS_SMTP_ADDRESS, smtpConnection.get().getUri().toString());
      // basic auth only required if smtp server haas username password
      if (basicAuthData.isPresent()) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMTP_USERNAME, basicAuthData.get().getUsername());
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMTP_PASSWORD, basicAuthData.get().getPassword());
      }
      if (smtpConnection.get().getActive() != null) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMTP_ENABLED, smtpConnection.get().getActive().toString());
      }
    }

    Optional<ExternalConnection> bmpSmsConnection =
        externalConnectionsService.getMatch(
            "programmedConnection", ProgrammedConnection.BMP_SMS_SERVER.name());
    propertiesValueMap.put(Property.PATIENT_RESULTS_BMP_SMS_ENABLED, Boolean.FALSE.toString());
    if (bmpSmsConnection.isPresent()) {
      Optional<BasicAuthenticationData> basicAuthData =
          basicAuthenticationDataService.getByExternalConnection(bmpSmsConnection.get().getId());
      propertiesValueMap.put(
          Property.PATIENT_RESULTS_BMP_SMS_ADDRESS, bmpSmsConnection.get().getUri().toString());
      // basic auth only required if bmp sms server has username password
      if (basicAuthData.isPresent()) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_BMP_SMS_USERNAME, basicAuthData.get().getUsername());
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_BMP_SMS_PASSWORD, basicAuthData.get().getPassword());
      }
      if (bmpSmsConnection.get().getActive() != null) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_BMP_SMS_ENABLED,
            bmpSmsConnection.get().getActive().toString());
      }
    }

    Optional<ExternalConnection> smppSmsConnection =
        externalConnectionsService.getMatch(
            "programmedConnection", ProgrammedConnection.SMPP_SERVER.name());
    propertiesValueMap.put(Property.PATIENT_RESULTS_SMPP_SMS_ENABLED, Boolean.FALSE.toString());
    if (smppSmsConnection.isPresent()) {
      Optional<BasicAuthenticationData> basicAuthData =
          basicAuthenticationDataService.getByExternalConnection(smppSmsConnection.get().getId());
      propertiesValueMap.put(
          Property.PATIENT_RESULTS_SMPP_SMS_ADDRESS, smppSmsConnection.get().getUri().toString());
      // basic auth only required if smpp server has username password
      if (basicAuthData.isPresent()) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMPP_SMS_USERNAME, basicAuthData.get().getUsername());
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMPP_SMS_PASSWORD, basicAuthData.get().getPassword());
      }
      if (smppSmsConnection.get().getActive() != null) {
        propertiesValueMap.put(
            Property.PATIENT_RESULTS_SMPP_SMS_ENABLED,
            smppSmsConnection.get().getActive().toString());
      }
    }

    //        Optional<ExternalConnection> clinicConnection =
    // externalConnectionsService.getMatch("programmedConnection",
    //                ProgrammedConnection.CLINIC_SEARCH.name());
    //        if (clinicConnection.isPresent()) {
    //            Optional<BasicAuthenticationData> basicAuthData = basicAuthenticationDataService
    //                    .getByExternalConnection(clinicConnection.get().getId());
    //            if (basicAuthData.isPresent()) {
    //                propertiesValueMap.put(Property.PatientSearchURL,
    // clinicConnection.get().getUri().toString());
    //                propertiesValueMap.put(Property.PatientSearchUserName,
    // basicAuthData.get().getUsername());
    //                propertiesValueMap.put(Property.PatientSearchPassword,
    // basicAuthData.get().getPassword());
    //                if (clinicConnection.get().getActive() != null) {
    //                    propertiesValueMap.put(Property.PatientSearchEnabled,
    //                            clinicConnection.get().getActive().toString());
    //                } else {
    //                    propertiesValueMap.put(Property.PatientSearchEnabled,
    // Boolean.FALSE.toString());
    //                }
    //            }
    //        }

  }

  protected void loadFromDatabase() {
    SiteInformationService siteInformationService =
        SpringContext.getBean(SiteInformationService.class);
    List<SiteInformation> siteInformationList = siteInformationService.getAllSiteInformation();

    for (SiteInformation siteInformation : siteInformationList) {
      Property property = dbNamePropertiesMap.get(siteInformation.getName());
      if (property != null) {
        propertiesValueMap.put(property, siteInformation.getValue());
      }
    }

    loadExternalConnectionsFromDatabase();

    databaseLoaded = true;
  }

  private void loadFromPropertiesFile() {
    InputStream propertyStream = null;

    try {
      propertyStream = this.getClass().getResourceAsStream(propertyFile);

      // Now load a java.util.Properties object with the properties
      properties = new java.util.Properties();

      properties.load(propertyStream);

    } catch (IOException e) {
      LogEvent.logError(e);
    } finally {
      if (null != propertyStream) {
        try {
          propertyStream.close();
        } catch (IOException e) {
          LogEvent.logError(e);
        }
      }
    }

    for (Property property : propertiesFileMap.keySet()) {
      KeyDefaultPair pair = propertiesFileMap.get(property);
      String value = properties.getProperty(pair.key, pair.defaultValue);
      propertiesValueMap.put(property, value);
    }
  }

  private void loadSpecial() {
    propertiesValueMap.put(
        Property.releaseNumber, SpringContext.getBean(Versioning.class).getReleaseNumber());
  }

  protected class KeyDefaultPair {
    public final String key;
    public final String defaultValue;

    public KeyDefaultPair(String key, String defaultValue) {
      this.key = key;
      this.defaultValue = defaultValue;
    }
  }
}
