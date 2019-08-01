package org.openelisglobal.common.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.AbstractBindingResult;

@Component("defaultErrors")
@Scope("prototype")
public class BaseErrors extends AbstractBindingResult {

    public BaseErrors() {
        super("");
        // TODO Auto-generated constructor stub
    }

    boolean error = false;

    @Override
    protected Object getActualFieldValue(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

}
