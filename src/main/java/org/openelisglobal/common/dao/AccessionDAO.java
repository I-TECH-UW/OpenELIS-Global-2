package org.openelisglobal.common.dao;

import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;

public interface AccessionDAO {

    long getNextNumberForAccessionFormat(AccessionFormat accessionFormat) throws LIMSInvalidConfigurationException;

    long getNextNumberForSiteYearFormatIncrement();

    long getNextNumberForAltYearFormatIncrement();

    long setNextValNumberForSiteYearFormat(long value);

    long setNextValNumberForAltYearFormat(long value);

    long getNextNumberForAltYearFormatNoIncrement();

    long getNextNumberForSiteYearFormatNoIncrement();

}
