package org.openelisglobal.patient.saving;

import java.lang.reflect.InvocationTargetException;

import org.openelisglobal.common.exception.LIMSException;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.springframework.validation.Errors;

public interface IAccessioner {

    String save() throws IllegalAccessException, LIMSRuntimeException, LIMSInvalidConfigurationException,
            InvocationTargetException, NoSuchMethodException, LIMSException;

    Errors getMessages();

}
