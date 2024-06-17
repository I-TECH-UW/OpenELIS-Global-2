package org.openelisglobal.observationhistorytype.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.observationhistorytype.valueholder.ObservationHistoryType;

public interface ObservationHistoryTypeService
    extends BaseObjectService<ObservationHistoryType, String> {
  ObservationHistoryType getByName(String name);
}
