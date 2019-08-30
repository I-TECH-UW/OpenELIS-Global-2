package org.openelisglobal.patient.saving;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.form.BaseForm;

public interface ISampleEntry extends IAccessioner {

    void setFieldsFromForm(BaseForm form)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    void setSysUserId(String sysUserId);

    void setRequest(HttpServletRequest request);

    boolean canAccession();

}
