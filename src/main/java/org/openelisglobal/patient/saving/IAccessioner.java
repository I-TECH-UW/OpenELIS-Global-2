package org.openelisglobal.patient.saving;

import org.springframework.validation.Errors;

public interface IAccessioner {

	String save() throws Exception;

	Errors getMessages();

}
