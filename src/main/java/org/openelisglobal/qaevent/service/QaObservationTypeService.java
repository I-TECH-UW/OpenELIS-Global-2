package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.QaObservationType;

public interface QaObservationTypeService extends BaseObjectService<QaObservationType, String> {
    QaObservationType getQaObservationTypeByName(String typeName);
}
