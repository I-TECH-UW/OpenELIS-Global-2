package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Task;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.InterpreterResults;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.OrderType;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.test.valueholder.Test;

public interface TaskInterpreter {

    public List<String> getUnsupportedTests();

    public List<String> getUnsupportedPanels();

    List<InterpreterResults> interpret(Task incomingTask);

    String getReferringOrderNumber();

    String getMessage();

    MessagePatient getMessagePatient();

    public OrderType getOrderType();

    List<InterpreterResults> getResultStatus();

    Test getTest();

    
}
