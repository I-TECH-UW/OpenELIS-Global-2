package us.mn.state.health.lims.qaevent.worker;

import org.springframework.validation.Errors;

public interface INonConformityUpdateWorker {

	void setWebData(NonConformityUpdateData data);

	String update();

	Errors getErrors();

}
