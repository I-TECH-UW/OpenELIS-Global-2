package org.openelisglobal.dataexchange.fhir.service;

import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;

public interface TaskPersister {

    void persist(MessagePatient patient, ElectronicOrder eOrder);

    String getServiceUserId();

    void cancelOrder(String referringOrderNumber);
}
