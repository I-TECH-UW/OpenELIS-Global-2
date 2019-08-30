package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.QaObservation;

public interface QaObservationService extends BaseObjectService<QaObservation, String> {
    QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId);
}
