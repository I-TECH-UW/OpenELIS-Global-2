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
package org.openelisglobal.common.provider.validation;

import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;

public class AccessionNumberValidatorFactory {

    public enum AccessionFormat {
        MAIN, GENERAL, SITE_YEAR, PROGRAM, YEAR_NUM_SIX, YEAR_NUM_DASH, YEAR_NUM_SEVEN, ALPHANUM_DASH, ALT_YEAR
    }

    private AccessionFormat mainAccessionFormat;
    private IAccessionNumberGenerator mainGenerator;

    private IAccessionNumberGenerator getConfiguredMainGenerator() throws LIMSInvalidConfigurationException {

        String accessionFormat = ConfigurationProperties.getInstance()
                .getPropertyValueUpperCase(Property.AccessionFormat);
        boolean mainGeneratorSet = mainGenerator != null;
        if (mainGeneratorSet) {
            return mainGenerator;
        }
        synchronized (this) {
            if (accessionFormat.equals("SITEYEARNUM")) {
                if (!mainGeneratorSet) {
                    mainGenerator = getSiteYearValidator();
                    mainAccessionFormat = AccessionFormat.SITE_YEAR;
                }
            } else if (accessionFormat.equals("PROGRAMNUM")) {
                if (!mainGeneratorSet) {
                    mainGenerator = getProgramValidator();
                    mainAccessionFormat = AccessionFormat.PROGRAM;
                }
            } else if (accessionFormat.equals("YEARNUM_SIX")) {
                if (!mainGeneratorSet) {
                    mainGenerator = getYearNumValidator(6, null);
                    mainAccessionFormat = AccessionFormat.YEAR_NUM_SIX;
                }
            } else if (accessionFormat.equals("YEARNUM_DASH_SEVEN")) {
                if (!mainGeneratorSet) {
                    mainGenerator = getYearNumValidator(7, '-');
                    mainAccessionFormat = AccessionFormat.YEAR_NUM_DASH;
                }
            } else if (accessionFormat.equals("YEARNUM_SEVEN")) {
                if (!mainGeneratorSet) {
                    mainGenerator = getYearNumValidator(7, null);
                    mainAccessionFormat = AccessionFormat.YEAR_NUM_SEVEN;
                }
            }

            if (mainGenerator == null) {
                throw new LIMSInvalidConfigurationException(
                        "AccessionNumberValidatorFactory: Unable to find generator/validator for " + accessionFormat);
            } else {
                return mainGenerator;
            }
        }
    }

    public IAccessionNumberValidator getValidator(AccessionFormat accessionFormat)
            throws LIMSInvalidConfigurationException {

        if (accessionFormat.equals(mainAccessionFormat)) {
            return mainGenerator;
        }
        switch (accessionFormat) {
        case MAIN:
            return getConfiguredMainGenerator();
        case GENERAL:
            return getAllActiveValidator();
        case SITE_YEAR:
            return getSiteYearValidator();
        case PROGRAM:
            return getProgramValidator();
        case YEAR_NUM_SIX:
            return getYearNumValidator(6, null);
        case YEAR_NUM_DASH:
            return getYearNumValidator(7, '-');
        case YEAR_NUM_SEVEN:
            return getYearNumValidator(7, null);
        case ALT_YEAR:
            return getAltYearValidator();
        default:
            throw new LIMSInvalidConfigurationException(
                    "AccessionNumberValidatorFactory: Unable to find validator for " + accessionFormat);
        }

    }

    public IAccessionNumberGenerator getGenerator(AccessionFormat accessionFormat)
            throws LIMSInvalidConfigurationException {

        if (accessionFormat.equals(mainAccessionFormat)) {
            return mainGenerator;
        }
        switch (accessionFormat) {
        case MAIN:
            return getConfiguredMainGenerator();
        case SITE_YEAR:
            return getSiteYearValidator();
        case PROGRAM:
            return getProgramValidator();
        case YEAR_NUM_SIX:
            return getYearNumValidator(6, null);
        case YEAR_NUM_DASH:
            return getYearNumValidator(7, '-');
        case YEAR_NUM_SEVEN:
            return getYearNumValidator(7, null);
        case ALT_YEAR:
            return getAltYearValidator();
        case GENERAL:
            throw new LIMSInvalidConfigurationException(
                    "AccessionNumberValidatorFactory: ALL_ACTIVE unable to be used as a generator ");
        default:
            throw new LIMSInvalidConfigurationException(
                    "AccessionNumberValidatorFactory: Unable to find Generator for " + accessionFormat);
        }

    }

    @SuppressWarnings("unused")
    private IAccessionNumberGenerator getDigitAccessionValidator(int length) {
        return new DigitAccessionValidator(length);
    }

    private IAccessionNumberGenerator getYearNumValidator(int length, Character separator) {
        return new YearNumAccessionValidator(length, separator);
    }

    private IAccessionNumberGenerator getSiteYearValidator() {
        return new SiteYearAccessionValidator();
    }

    private IAccessionNumberGenerator getAltYearValidator() {
        return new AltYearAccessionValidator();
    }

    private IAccessionNumberValidator getAllActiveValidator() throws LIMSInvalidConfigurationException {
        return new GeneralAccessionNumberValidator();
    }

    private IAccessionNumberGenerator getProgramValidator() {
        return new ProgramAccessionValidator();
    }

}
