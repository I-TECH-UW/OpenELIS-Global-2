package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;

import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.order.action.IOrderExistanceChecker;
import org.openelisglobal.dataexchange.order.action.IOrderExistanceChecker.CheckResult;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.InterpreterResults;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.OrderType;
import org.openelisglobal.dataexchange.order.action.IOrderPersister;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrderType;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;

import ca.uhn.fhir.context.FhirContext;

public class TaskWorker {

    @Autowired
    private FhirContext fhirContext;

    public enum TaskResult {
        OK, DUPLICATE_ORDER, NON_CANCELABLE_ORDER, MESSAGE_ERROR
    }

    private String message = "";
    private Task task = new Task();
    private ServiceRequest serviceRequest = null;
    private Patient patient = new Patient();
    private TaskInterpreter interpreter;
    private IOrderExistanceChecker existanceChecker;
    private IOrderPersister persister;
    private IStatusService statusService;
    private List<InterpreterResults> interpretResults;
    private CheckResult checkResult;

    public TaskWorker(Task incomingTask, String incomingMessage, ServiceRequest incomingServiceRequest, Patient incomingPatient) {
        task = incomingTask;
        message = incomingMessage;
        serviceRequest = incomingServiceRequest;
        patient = incomingPatient;
    }

    public void setInterpreter(TaskInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void setExistanceChecker(IOrderExistanceChecker orderExistanceChecker) {
        existanceChecker = orderExistanceChecker;
    }

    public void setPersister(IOrderPersister taskPersister) {
        persister = taskPersister;
    }

    private IStatusService getStatusService() {
        if (statusService == null) {
            statusService = SpringContext.getBean(IStatusService.class);
        }
        return statusService;
    }

    public void setStatusService(IStatusService statusService) {
        this.statusService = statusService;
    }

    public List<InterpreterResults> getMessageErrors() {
        return interpretResults;
    }

    public List<String> getUnsupportedTests() {
        return interpreter.getUnsupportedTests();
    }

    public List<String> getUnsupportedPanels() {
        return interpreter.getUnsupportedPanels();
    }

    public CheckResult getExistanceCheckResult() {
        return checkResult;
    }

    public TaskResult handleOrderRequest() throws IllegalStateException {
        if (interpreter == null || persister == null || existanceChecker == null) {
            throw new IllegalStateException("Interpreter, existanceChecker or persister have not been set");
        }

        interpretResults = interpreter.interpret(task, serviceRequest, patient);
        String referringOrderNumber = interpreter.getReferringOrderNumber();
        OrderType orderType = interpreter.getOrderType();
        MessagePatient patient = interpreter.getMessagePatient();
        checkResult = existanceChecker.check(referringOrderNumber);

        if (interpretResults.get(0) == InterpreterResults.OK) {

//            checkResult = existanceChecker.check(referringOrderNumber);
            switch (checkResult) {
            case ORDER_FOUND_QUEUED:
                if (orderType == OrderType.CANCEL) {
                    cancelOrder(referringOrderNumber);
                    return TaskResult.OK;
                } else {
                    LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:0");
                    return TaskResult.DUPLICATE_ORDER;
                }
            case ORDER_FOUND_INPROGRESS:
                LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:1");
                return orderType == OrderType.CANCEL ? TaskResult.NON_CANCELABLE_ORDER : TaskResult.DUPLICATE_ORDER;
            case NOT_FOUND:
                if (orderType == OrderType.CANCEL) {
                    LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:2");
                    return TaskResult.NON_CANCELABLE_ORDER;
                } else {
                    LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:3");
                    insertNewOrder(referringOrderNumber, message, patient, ExternalOrderStatus.Entered);
                }
                break;
            case ORDER_FOUND_CANCELED:
                if (orderType == OrderType.CANCEL) {
                    LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:4");
                    return TaskResult.NON_CANCELABLE_ORDER;
                } else {
                    LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:5");
                    insertNewOrder(referringOrderNumber, message, patient, ExternalOrderStatus.Entered);
                }
                break;
            }

        } else if (interpretResults.get(0) == InterpreterResults.UNSUPPORTED_TESTS && checkResult == CheckResult.ORDER_FOUND_QUEUED ) {
            if (orderType == OrderType.CANCEL) {
                LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:6");
                cancelOrder(referringOrderNumber);
                return TaskResult.OK;
            } else {
                LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:7");
                return TaskResult.DUPLICATE_ORDER;
            }
        } else if (interpretResults.get(0) == InterpreterResults.UNSUPPORTED_TESTS) {
            LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:8");
            LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest",
                    "TaskWorker:unsupported tests: " + referringOrderNumber + orderType);
            insertNewOrder(referringOrderNumber, message, patient, ExternalOrderStatus.NonConforming);
            return TaskResult.MESSAGE_ERROR;
        } else if (interpretResults.get(0) == InterpreterResults.MISSING_PATIENT_DOB) {
            LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest", "TaskWorker:9");
            LogEvent.logDebug(this.getClass().getName(), "handleOrderRequest",
                    "TaskWorker:missing patient dob: " + referringOrderNumber + orderType);
            insertNewOrder(referringOrderNumber, message, patient, ExternalOrderStatus.NonConforming);
            return TaskResult.MESSAGE_ERROR;
        }

        return TaskResult.OK;
    }

    private void cancelOrder(String referringOrderNumber) {
        LogEvent.logDebug(this.getClass().getName(), "cancelOrder", "cancelOrder: ");
        persister.cancelOrder(referringOrderNumber);
    }

    private void insertNewOrder(String referringOrderNumber, String message, MessagePatient patient, ExternalOrderStatus eoStatus) {
        LogEvent.logDebug(this.getClass().getName(), "insertNewOrder",
                "TaskWorker:insertNewOrder: " + referringOrderNumber);
        ElectronicOrder eOrder = new ElectronicOrder();
        eOrder.setExternalId(referringOrderNumber);
        eOrder.setData(message);
        eOrder.setStatusId(getStatusService().getStatusID(eoStatus));
        eOrder.setOrderTimestamp(DateUtil.getNowAsTimestamp());
        eOrder.setSysUserId(persister.getServiceUserId());
        eOrder.setType(ElectronicOrderType.FHIR);

        persister.persist(patient, eOrder);
    }

}
