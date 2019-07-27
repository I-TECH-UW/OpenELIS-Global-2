package org.openelisglobal.patient.saving;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.patient.form.PatientEntryByProjectForm;
import org.openelisglobal.common.exception.LIMSRuntimeException;

public interface IPatientEntry extends IAccessioner {

	void setRequest(HttpServletRequest request);

	void setFieldsFromForm(PatientEntryByProjectForm form)
			throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	void setSysUserId(String sysUserId);

	boolean canAccession();

}
