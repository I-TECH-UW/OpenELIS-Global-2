package spring.mine.common.form;

import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMethod;

//a bean object to hold all objects to be passed between the server and the client and vice versa
public class BaseForm {

	private String formName;
	private String formAction;
	private RequestMethod formMethod = RequestMethod.POST;

	private String cancelAction = "Home.do";
	private boolean submitOnCancel = false;
	private RequestMethod cancelMethod = RequestMethod.POST;

	private List<ObjectError> errors;

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

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> list) {
		errors = list;
	}

}
