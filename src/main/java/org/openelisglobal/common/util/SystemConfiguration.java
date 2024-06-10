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
package org.openelisglobal.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.internationalization.MessageUtil;

/**
 * This class represents the configuration properties of the application
 *
 */

public class SystemConfiguration {

    private static final String propertyFile = "/SystemConfiguration.properties";

    private static class SingletonHelper {
        private static final SystemConfiguration INSTANCE = new SystemConfiguration();
    }

    private List<LocaleChangeListener> localChangeListeners = new ArrayList<>();

    private Properties properties = null;
    private Map<String, Locale> localePropertyToLocaleMap = new HashMap<>();

    private SystemConfiguration() {
        InputStream propertyStream = null;

        try {
            propertyStream = this.getClass().getResourceAsStream(propertyFile);

            // Now load a java.util.Properties object with the properties
            properties = new Properties();

            properties.load(propertyStream);

        } catch (IOException e) {
            // bugzilla 2154
            LogEvent.logError(e);
        } finally {
            if (null != propertyStream) {
                try {
                    propertyStream.close();
                } catch (IOException e) {
                    // bugzilla 2154
                    LogEvent.logError(e);
                }
            }

        }

    }

    public static SystemConfiguration getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void addLocalChangeListener(LocaleChangeListener listener) {
        localChangeListeners.add(listener);
    }

    public int getDefaultPageSize() {
        String pageSize = properties.getProperty("page.defaultPageSize");
        if (pageSize != null) {
            return Integer.parseInt(pageSize);
        }
        // bugzilla 1409
        return 20;
    }

    // bugzilla 1742
    public int getDefaultTreePageSize() {
        String pageSize = properties.getProperty("page.tree.defaultPageSize");
        if (pageSize != null) {
            return Integer.parseInt(pageSize);
        }
        return 10;
    }

    // bugzilla 1742
    public int getDefaultPaginatedNodeChildCount() {
        String count = properties.getProperty("page.tree.paginatednode.child.count");
        if (count != null) {
            return Integer.parseInt(count);
        }
        return 32;
    }

    public Locale getDefaultLocale() {
        return getLocaleByLocalString(
                ConfigurationProperties.getInstance().getPropertyValue(Property.DEFAULT_LANG_LOCALE));
    }

    public Locale getLocaleByLocalString(String localeString) {
        Locale locale = localePropertyToLocaleMap.get(localeString);

        if (locale == null) {
            if (localeString != null && localeString.length() == 5) {
                locale = new Locale(localeString.substring(0, 2), localeString.substring(3));
                localePropertyToLocaleMap.put(localeString, locale);
            }
        }

        return locale == null ? Locale.US : locale;
    }

    public void setDefaultLocale(String locale) {
        ConfigurationProperties.getInstance().setPropertyValue(Property.DEFAULT_LANG_LOCALE, locale);
        for (LocaleChangeListener listener : localChangeListeners) {
            listener.localeChanged(locale);
        }
    }

    public String getDefaultEncoding() {
        String encodingString = properties.getProperty("default.encoding");
        if (encodingString != null) {
            return encodingString;
        }
        return "Cp1252";
    }

    public String getDefaultApplicationName() {
        String applicationNameString = properties.getProperty("default.application.name");
        if (applicationNameString != null) {
            return applicationNameString;
        }
        // bugzilla 1995
        return "OpenELIS";
    }

    public String getDefaultIdSeparator() {
        String def = properties.getProperty("default.idSeparator");
        if (def != null) {
            return def;
        }
        return ";";
    }

    public String getDefaultTextSeparator() {
        String def = properties.getProperty("default.textSeparator");
        if (def != null) {
            return def;
        }
        return "|;|";
    }

    public String getDictionaryType() {
        String dictType = properties.getProperty("dictionaryType");
        if (dictType != null) {
            return dictType;
        }
        return "D";
    }

    public String getTiterType() {
        String titerType = properties.getProperty("titerType");
        if (titerType != null) {
            return titerType;
        }
        return "T";
    }

    public String getAnalyteTypeRequired() {
        String analyteTypeRequired = properties.getProperty("analyteTypeRequired");
        if (analyteTypeRequired != null) {
            return analyteTypeRequired;
        }
        return "R";
    }

    public String getAnalyteTypeNotRequired() {
        String analyteTypeNotRequired = properties.getProperty("analyteTypeNotRequired");
        if (analyteTypeNotRequired != null) {
            return analyteTypeNotRequired;
        }
        return "N";
    }

    public String getNumericType() {
        String numericType = properties.getProperty("numericType");
        if (numericType != null) {
            return numericType;
        }
        return "N";
    }
    // ==============================================================

    public String getQuickEntryDefaultReferredCultureFlag() {
        String def = properties.getProperty("quickEntry.default.sample.referredCultureFlag");
        if (def != null) {
            return def;
        }
        return "S";
    }

    public String getQuickEntryDefaultStickerReceivedFlag() {
        String def = properties.getProperty("quickEntry.default.sample.stickerReceivedFlag");
        if (def != null) {
            return def;
        }
        return "N";
    }

    public String getQuickEntryDefaultNextItemSequence() {
        String def = properties.getProperty("quickEntry.default.sample.nextItemSequence");
        if (def != null) {
            return def;
        }
        return "1";
    }

    public String getQuickEntryDefaultRevision() {
        String def = properties.getProperty("quickEntry.default.sample.revision");
        if (def != null) {
            return def;
        }
        return "0";
    }

    public String getQuickEntryDefaultCollectionTimeForDisplay() {
        String def = properties.getProperty("quickEntry.default.sample.collectionTimeForDisplay");
        if (def != null) {
            return def;
        }
        return "00:00";
    }
    // ==============================================================

    public String getHumanSampleOneDefaultReferredCultureFlag() {
        String def = properties.getProperty("humanSampleOne.default.sample.referredCultureFlag");
        if (def != null) {
            return def;
        }
        // bugzilla 1754 - blank is default
        return "";
    }

    public String getHumanSampleOneDefaultStickerReceivedFlag() {
        String def = properties.getProperty("humanSampleOne.default.sample.stickerReceivedFlag");
        if (def != null) {
            return def;
        }
        return "N";
    }

    public String getHumanSampleOneDefaultNextItemSequence() {
        String def = properties.getProperty("humanSampleOne.default.sample.nextItemSequence");
        if (def != null) {
            return def;
        }
        return "1";
    }

    public String getHumanSampleOneDefaultRevision() {
        String def = properties.getProperty("humanSampleOne.default.sample.revision");
        if (def != null) {
            return def;
        }
        return "0";
    }

    public String getHumanSampleOneDefaultCollectionTimeForDisplay() {
        String def = properties.getProperty("humanSampleOne.default.sample.collectionTimeForDisplay");
        if (def != null) {
            return def;
        }
        return "00:00";
    }

    public String getHumanSampleOneDefaultPatientGender() {
        String def = properties.getProperty("humanSampleOne.default.patient.gender");
        if (def != null) {
            return def;
        }
        return "";
    }

    // bugzilla 1387 rename this method so more generic
    public String getHumanDomain() {
        String def = properties.getProperty("domain.human");
        if (def != null) {
            return def;
        }
        return "H";
    }

    // bugzilla 1387 rename this method so more generic
    public String getAnimalDomain() {
        String def = properties.getProperty("domain.animal");
        if (def != null) {
            return def;
        }
        return "A";
    }

    // bugzilla 1348 - analysis status for verification
    public String getAnalysisVerifiedStatus() {
        String def = properties.getProperty("analysis.status.verified");
        if (def != null) {
            return def;
        }
        return "V";
    }

    // bugzilla 1348 - analysis status for verification
    public String getAnalysisReadyToVerifyStatus() {
        String def = properties.getProperty("analysis.status.readytoverify");
        if (def != null) {
            return def;
        }
        return "";
    }

    // bugzilla - reports id
    public String getOpenReportsReportId(String key) {
        String def = properties.getProperty(key);
        if (def != null) {
            return def;
        }
        return "";
    }

    // bugzilla - reports group
    public String getOpenReportsGroupId(String key) {
        String def = properties.getProperty(key);
        if (def != null) {
            return def;
        }
        return "";
    }

    // bugzilla 1546
    public String getSampleStatusType() {
        String def = properties.getProperty("sample.status");
        if (def != null) {
            return def;
        }
        return "";
    }

    public String getAnalysisStatusType() {
        String def = properties.getProperty("analysis.status");
        if (def != null) {
            return def;
        }
        return "";
    }

    // bugzilla 2380
    public String getLabelPrinterName() {
        String printer = properties.getProperty("print.label.name");
        if (printer != null) {
            return printer;
        }
        return "";
    }

    public String getLabelNumberOfCopies(String key) {
        String numberOfCopies = properties.getProperty(key);
        if (numberOfCopies != null) {
            return numberOfCopies;
        }
        return "1";
    }

    // bugzilla 2374
    public String getMaxNumberOfLabels() {
        String maxNumberOfLabels = properties.getProperty("print.label.numeroflabels");
        if (maxNumberOfLabels != null) {
            return maxNumberOfLabels;
        }
        return "100";
    }

    public String getBarcodeHeight() {
        String height = properties.getProperty("print.label.barcode.height");
        if (height != null) {
            return height;
        }
        return "44";
    }

    public String getBarcodeWidth() {
        String width = properties.getProperty("print.label.barcode.width");
        if (width != null) {
            return width;
        }
        return "330";
    }

    public String getBarcodeResolution() {
        String res = properties.getProperty("print.label.barcode.resolution");
        if (res != null) {
            return res;
        }
        return "300";
    }

    // bugzilla 2592
    public String getDefaultSampleLabel(String accessionNumber) {
        String prependBarcode = properties.getProperty("print.label.sample.prepend.barcode");
        String prependHumanReadable = properties.getProperty("print.label.sample.prepend.humanreadable");
        String postpend = properties.getProperty("print.label.sample.postpend");
        if (prependBarcode != null && prependHumanReadable != null && postpend != null) {
            int sampleLabelLength = prependBarcode.length() + accessionNumber.length() + prependHumanReadable.length()
                    + accessionNumber.length() + postpend.length();
            StringBuffer sb = new StringBuffer(sampleLabelLength);
            sb.append(prependBarcode).append(accessionNumber).append(prependHumanReadable).append(accessionNumber)
                    .append(postpend);
            return sb.toString();
        }
        return "";
    }

    public String getAnalysisStatusAssigned() {
        String val = properties.getProperty("analysis.status.assigned");
        if (val != null) {
            return val;
        }

        return "1";
    }

    // bugzilla 2300
    public String getAnalysisStatusCanceled() {
        String val = properties.getProperty("analysis.status.canceled");
        if (val != null) {
            return val;
        }

        return "2";
    }

    public String getAnalysisStatusResultCompleted() {
        String val = properties.getProperty("analysis.status.result.completed");
        if (val != null) {
            return val;
        }

        return "3";
    }

    public String getAnalysisStatusReleased() {
        String val = properties.getProperty("analysis.status.released");
        if (val != null) {
            return val;
        }

        return "4";
    }

    public String getSampleStatusQuickEntryComplete() {
        String val = properties.getProperty("sample.status.quick.entry.complete");
        if (val != null) {
            return val;
        }

        return "1";
    }

    public String getSampleStatusEntry1Complete() {
        String val = properties.getProperty("sample.status.entry.1.complete");
        if (val != null) {
            return val;
        }

        return "2";
    }

    public String getSampleStatusEntry2Complete() {
        String val = properties.getProperty("sample.status.entry.2.complete");
        if (val != null) {
            return val;
        }

        return "3";
    }

    public String getSampleStatusReleased() {
        String val = properties.getProperty("sample.status.released");
        if (val != null) {
            return val;
        }

        return "7";
    }

    public String getSampleStatusLabelPrinted() {
        String val = properties.getProperty("sample.status.label.printed");
        if (val != null) {
            return val;
        }

        return "8";
    }

    public String getOpenReportsSwitchModulePath() {
        String res = properties.getProperty("openreports.switch.module.path");
        if (res != null) {
            return res;
        }
        return "../../openreports";
    }

    public String getResultReferenceTableId() {
        String refId = properties.getProperty("result.reference.table.id");
        if (refId != null) {
            return refId;
        }
        return "21";
    }

    // bugzilla 2028
    public String getAnalysisQaEventActionReferenceTableId() {
        String refId = properties.getProperty("analysis.qaevent.action.reference.table.id");
        if (refId != null) {
            return refId;
        }
        return "21";
    }

    // bugzilla 2500
    public String getSampleQaEventActionReferenceTableId() {
        String refId = properties.getProperty("sample.qaevent.action.reference.table.id");
        if (refId != null) {
            return refId;
        }
        return "21";
    }

    public String getNoteTypeInternal() {
        String internalType = properties.getProperty("note.type.internal");
        if (internalType != null) {
            return internalType;
        }
        return "I";
    }

    public String getNoteTypeExternal() {
        String externalType = properties.getProperty("note.type.external");
        if (externalType != null) {
            return externalType;
        }
        return "I";
    }

    public String getDefaultTransportMethodForXMLTransmission() {
        String transportMethod = properties.getProperty("default.transport.method");
        if (transportMethod != null) {
            return transportMethod;
        }
        return "PHINMS_DEFINED_IN_SYS_CONFIG";
    }

    public String getDefaultProcessingIdForXMLTransmission() {
        String processingId = properties.getProperty("default.transport.processing.id");
        if (processingId != null) {
            return processingId;
        }
        return "T_DEFINED_IN_SYS_CONFIG";
    }

    public String getDefaultTransmissionTextSeparator() {
        String separator = properties.getProperty("default.transport.text.separator");
        if (separator != null) {
            return separator;
        }
        return "^_DEFINED_IN_SYS_CONFIG";
    }

    public String getDefaultTransmissionCodeSystemType() {
        String codeSystemType = properties.getProperty("default.transport.code.system.type");
        if (codeSystemType != null) {
            return codeSystemType;
        }
        return "L";
    }

    public String getMdhUhlIdForXMLTransmission() {
        String uhlId = properties.getProperty("mdh.uhl.id");
        if (uhlId != null) {
            return uhlId;
        }
        return "9999__DEFINED_IN_SYS_CONFIG";
    }

    public String getMdhUniversalIdForXMLTransmission() {
        String universalId = properties.getProperty("mdh.universal.id");
        if (universalId != null) {
            return universalId;
        }
        return "9999__DEFINED_IN_SYS_CONFIG";
    }

    public String getMdhUniversalIdTypeForXMLTransmission() {
        String universalIdType = properties.getProperty("mdh.universal.id.type");
        if (universalIdType != null) {
            return universalIdType;
        }
        return "9999__DEFINED_IN_SYS_CONFIG";
    }

    public String getMdhPhoneNumberForXMLTransmission() {
        String phoneNumber = properties.getProperty("mdh.work.phone");
        if (phoneNumber != null) {
            return phoneNumber;
        }
        return "800/999-9999_DEFINED_IN_SYS_CONFIG";
    }

    public String getMdhOrganizationIdForXMLTransmission() {
        String orgId = properties.getProperty("mdh.organization.record.id");
        if (orgId != null) {
            return orgId;
        }
        return "43";
    }

    // bugzilla 2393 INFLUENZA XML
    public String getInfluenzaDefaultProcessingIdForXMLTransmission() {
        String processingId = properties.getProperty("default.transport.processing.id.influenza");
        if (processingId != null) {
            return processingId;
        }
        return "T";
    }

    // bugzilla 2393
    public String getInfluenzaDefaultApplicationName() {
        String applicationNameString = properties.getProperty("default.application.name.influenza");
        if (applicationNameString != null) {
            return applicationNameString;
        }
        // bugzilla 1995
        return "MN OpenELIS Stage";
    }

    // 1742 openreports static ids (tests, projects etc.)
    public String getStaticIdByName(String name) {
        String testId = properties.getProperty(name);
        // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "SystemConfig
        // getting test by name " + name);
        if (testId != null) {
            return testId;
        }
        return "";
    }

    public String getDefaultDataSource() {
        String dsString = properties.getProperty("default.datasource");
        if (dsString != null) {
            return dsString;
        }
        return "LimsDS";
    }

    // bugzilla 2028 get qaevent code for quickentry sample type NOT GIVEN
    public String getQaEventCodeForRequestNoSampleType() {
        String string = properties.getProperty("qaeventcode.request.sourcemissing");
        if (string != null) {
            return string;
        }
        return "RQNSO";
    }

    // bugzilla 2028 get qaevent code for quickentry missing collection date
    public String getQaEventCodeForRequestNoCollectionDate() {
        String string = properties.getProperty("qaeventcode.request.collectiondatemissing");
        if (string != null) {
            return string;
        }
        return "RQNCD";
    }

    // bugzilla 2028 get qaevent code for quickentry submitter unknown
    public String getQaEventCodeForRequestUnknownSubmitter() {
        String string = properties.getProperty("qaeventcode.request.submitterunknown");
        if (string != null) {
            return string;
        }
        return "RQNSNA";
    }

    // bugzilla 2028 get unknownSubmitterNumber
    // bugzilla 2589 unknown submitter number is null now
    public String getUnknownSubmitterNumberForQaEvent() {
        String string = properties.getProperty("unknown.submitter.number");
        if (string != null) {
            return string;
        }
        return "";
    }

    // bugzilla 2028 get qaevent action code for quickentry sample type NOT GIVEN
    public String getQaEventActionCodeForRequestNoSampleType() {
        String string = properties.getProperty("qaeventactioncode.request.sourcemissing");
        if (string != null) {
            return string;
        }
        return "RQSOC";
    }

    // bugzilla 2028 get qaevent action code for quickentry missing collection date
    public String getQaEventActionCodeForRequestNoCollectionDate() {
        String string = properties.getProperty("qaeventactioncode.request.collectiondatemissing");
        if (string != null) {
            return string;
        }
        return "CDC";
    }

    // bugzilla 2028 get qaevent action code for quickentry submitter unknown
    public String getQaEventActionCodeForRequestUnknownSubmitter() {
        String string = properties.getProperty("qaeventactioncode.request.submitterunknown");
        if (string != null) {
            return string;
        }
        return "SNAC";
    }

    // bugzilla 2063
    public String getQaEventDictionaryCategoryType() {
        String string = properties.getProperty("dictionary.category.qaevent.type");
        if (string != null) {
            return string;
        }
        // bugzilla 2221 - we are now defining only exceptions to the rule
        return "Q";
    }

    // bugzilla 2506
    public String getQaEventDictionaryCategoryCategory() {
        String string = properties.getProperty("dictionary.category.qaevent.category");
        if (string != null) {
            return string;
        }
        // bugzilla 2221 - we are now defining only exceptions to the rule
        return "QC";
    }

    public String getLoginUserChangePasswordAllowDay() {
        String string = properties.getProperty("login.user.change.allow.day");
        if (string != null) {
            return string;
        }
        return "3";
    }

    public String getLoginUserChangePasswordExpiredMonth() {
        String string = properties.getProperty("login.user.expired.month");
        if (string != null) {
            return string;
        }
        return "1";
    }

    // bugzilla 2286 password reminder days
    public String getLoginUserPasswordExpiredReminderDay() {
        String string = properties.getProperty("login.user.expired.reminder.day");
        if (string != null) {
            return string;
        }
        return "15";
    }

    // bugzilla 2286 account lock after 3 failed logins
    public String getLoginUserFailAttemptCount() {
        String string = properties.getProperty("login.fail.attempt.count");
        if (string != null) {
            return string;
        }
        return "3";
    }

    // bugzilla 2286 account unlock after 10 minutes
    public String getLoginUserAccountUnlockMinute() {
        String string = properties.getProperty("login.user.account.unlock.minute");
        if (string != null) {
            return string;
        }
        return "10";
    }

    // User Test Section
    public String getEnableUserTestSection() {
        String string = properties.getProperty("enable.user.test.section");
        if (string != null) {
            return string;
        }
        return "Y";
    }

    public String getAnalysisDefaultRevision() {
        String def = properties.getProperty("analysis.default.revision");
        if (def != null) {
            return def;
        }
        return "0";
    }

    // Encrypted PDF Path
    public String getEncryptedPdfPath() {
        String string = properties.getProperty("encrypted.pdf.path");
        if (string != null) {
            return string;
        }
        return "/export/project/phl/scans";
    }

    // Allow to view the encrypted pdf
    public String getEnabledSamplePdf() {
        String string = properties.getProperty("enable.sample.pdf");
        if (string != null) {
            return string;
        }
        return "Y";
    }

    // bugzilla 2528
    public String getNewbornTestPanelName() {
        String string = properties.getProperty("newborn.testPanelName");
        if (string != null) {
            return string;
        }
        return "NBS-Panel";
    }

    public String getNewbornTypeOfSample() {
        String string = properties.getProperty("newborn.typeOfSample");
        if (string != null) {
            return string;
        }
        return "DRIED BLOOD SPOT CARD";
    }

    // bugzilla 2529, 2530
    public String getNewbornDomain() {
        String string = properties.getProperty("domain.newborn");
        if (string != null) {
            return string;
        }
        return "N";
    }

    // for testing only to inject values
    public void setSiteCode(String siteCode) {
        properties.setProperty("sitecode", siteCode);
    }

    public String getProgramCodes() {
        String codes = properties.getProperty("programcodes");

        return (codes == null ? "" : codes);
    }

    // for testing only to inject values
    public void setProgramCodes(String programCodes) {
        properties.setProperty("programcodes", programCodes);
    }

    public String getNewbornPatientRelation() {
        String string = properties.getProperty("newborn.patient.relation");
        if (string != null) {
            return string;
        }
        return "M";
    }

    // we are letting the date locale differ from the default locale. Not a good
    // thing
    public Locale getDateLocale() {
        String localeString = ConfigurationProperties.getInstance().getPropertyValue(Property.DEFAULT_DATE_LOCALE);
        Locale locale = Locale.US;

        if (!GenericValidator.isBlankOrNull(localeString)) {
            String[] splitLocale = localeString.split("-");

            if (splitLocale.length == 1) { // there is variation around separators
                splitLocale = localeString.split("_");
            }

            switch (splitLocale.length) {
            case 1: {
                locale = new Locale(splitLocale[0]);
                break;
            }
            case 2: {
                locale = new Locale(splitLocale[0], splitLocale[1]);
                break;
            }
            case 3: {
                locale = new Locale(splitLocale[0], splitLocale[1], splitLocale[2]);
                break;
            }
            default: // no-op
            }
        }
        return locale;
    }

    public String getPatternForDateLocale() {
        Locale locale = getLocaleByLocalString(
                ConfigurationProperties.getInstance().getPropertyValue(Property.DEFAULT_DATE_LOCALE));
        return MessageUtil.getMessage("date.format.formatKey", locale).toUpperCase();
        // DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
        // getDateLocale());
        // // yyyy/mm/dd
        // Date date = Date.valueOf("2000-01-02");
        // String pattern = dateFormat.format(date);
        // pattern = pattern.replaceFirst("01", "MM");
        // pattern = pattern.replaceFirst("02", "DD");
        // pattern = pattern.replaceFirst("00", "YYYY");
        // return pattern;
    }

    public boolean errorsToScreen() {
        String id = properties.getProperty("errors.to.screen");
        return id == null ? false : Boolean.valueOf(id.trim());
    }

    public String getPermissionAgent() {
        return properties.getProperty("permissions.agent", "USER").trim().toUpperCase();
    }

    public long getSearchTimeLimit() {
        long limit = 20000L;
        String timeLimit = properties.getProperty("patient.search.time.limit.ms");
        if (!GenericValidator.isBlankOrNull(timeLimit)) {
            try {
                limit = Long.parseLong(timeLimit);
            } catch (NumberFormatException e) {
                LogEvent.logError(
                        "Invalid SystemConfiguration format for 'patient.search.time.limit.ms'.  Default used", e);
            }
        }
        return limit;
    }

    public boolean useTestPatientGUID() {
        return "enable".equals(properties.getProperty("use.test.patient.guid", "disable"));
    }

    public void setProperty(String property, String value) {
        properties.setProperty(property, value);
    }
}
