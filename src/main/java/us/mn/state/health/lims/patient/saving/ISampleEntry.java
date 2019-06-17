package us.mn.state.health.lims.patient.saving;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface ISampleEntry extends IAccessioner {

	void setFieldsFromForm(BaseForm form)
			throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	void setSysUserId(String sysUserId);

	void setRequest(HttpServletRequest request);

	boolean canAccession();

}
