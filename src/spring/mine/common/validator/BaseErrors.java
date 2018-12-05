package spring.mine.common.validator;

import org.springframework.validation.AbstractBindingResult;

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
