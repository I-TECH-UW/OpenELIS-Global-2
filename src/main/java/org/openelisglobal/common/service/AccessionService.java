package org.openelisglobal.common.service;

import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;
import org.openelisglobal.common.valueholder.AccessionNumberInfo.AccessionIdentity;

public interface AccessionService {

    long getNextNumberNoIncrement(String prefix, AccessionFormat accessionFormat);

    long getNextNumberIncrement(String prefix, AccessionFormat accessionFormat);

    long getNextNumberIncrement(AccessionIdentity accessionIdentity) throws LIMSInvalidConfigurationException;

    long getNextNumberNoIncrement(AccessionIdentity accessionIdentity) throws LIMSInvalidConfigurationException;

    void setCurVal(String prefix, AccessionFormat accessionFormat, long curVal);
}
