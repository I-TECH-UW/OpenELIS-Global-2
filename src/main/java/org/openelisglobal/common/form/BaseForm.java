package org.openelisglobal.common.form;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.log.LogEvent;
import org.springframework.web.bind.annotation.RequestMethod;

//a bean object to hold all objects to be passed between the server and the client and vice versa
public class BaseForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2614369858245937250L;

    @SafeHtml
    private String formName;
    @SafeHtml
    private String formAction;
    private RequestMethod formMethod = RequestMethod.POST;

    @SafeHtml
    private String cancelAction = "Home.do";
    private boolean submitOnCancel = false;
    private RequestMethod cancelMethod = RequestMethod.POST;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public RequestMethod getFormMethod() {
        return formMethod;
    }

    public void setFormMethod(RequestMethod formMethod) {
        this.formMethod = formMethod;
    }

    public void setCancelAction(String cancelAction) {
        this.cancelAction = cancelAction;
    }

    public String getCancelAction() {
        return cancelAction;
    }

    public boolean isSubmitOnCancel() {
        return submitOnCancel;
    }

    public void setSubmitOnCancel(boolean submitOnCancel) {
        this.submitOnCancel = submitOnCancel;
    }

    public RequestMethod getCancelMethod() {
        return cancelMethod;
    }

    public void setCancelMethod(RequestMethod cancelMethod) {
        this.cancelMethod = cancelMethod;
    }

    public Object get(String propertyName) {
        Object obj = null;
        try {
            obj = PropertyUtils.getProperty(this, propertyName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.getMessage(), e);
            LogEvent.logDebug(e);
        }
        return obj;
    }

    public String getString(String propertyName) {
        String obj = null;
        try {
            obj = (String) PropertyUtils.getProperty(this, propertyName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.getMessage(), e);
            LogEvent.logDebug(e);
        }
        return obj;
    }

    public String[] getStrings(String propertyName) {
        String[] obj = null;
        try {
            obj = (String[]) PropertyUtils.getProperty(this, propertyName);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.getMessage(), e);
            LogEvent.logDebug(e);
        }
        return obj;
    }

}
