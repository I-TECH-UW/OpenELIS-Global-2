package spring.mine.common.form;

import java.util.List;

import org.springframework.validation.ObjectError;

//a bean object to hold all objects to be passed between the server and the client and vice versa
public class BaseForm {
	
	String formName;
	String formAction;
	List<ObjectError> errors;
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
	public List<ObjectError> getErrors() {
	    return errors;
	}
	public void setErrors(List<ObjectError> list) {
	    this.errors = list;
	}

}
