package us.mn.state.health.lims.patient.saving;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface IPatientEntry extends IAccessioner {

	void setRequest(HttpServletRequest request);

	void setFieldsFromForm(PatientEntryByProjectForm form)
			throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	void setSysUserId(String sysUserId);

	boolean canAccession();

}
