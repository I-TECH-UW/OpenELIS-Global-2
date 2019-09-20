package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.NcEvent;

import java.util.List;

public interface NCEventService extends BaseObjectService<NcEvent, String> {

    List<NcEvent> findByNCENumberOrLabOrderId(String nceNumber, String labOrderId);
}
