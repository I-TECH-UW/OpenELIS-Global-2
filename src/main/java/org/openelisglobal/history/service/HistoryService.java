package org.openelisglobal.history.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.audittrail.valueholder.History;
import org.openelisglobal.common.exception.LIMSRuntimeException;

public interface HistoryService extends BaseObjectService<History, String> {

    public List getHistoryByRefIdAndRefTableId(String Id, String Table) throws LIMSRuntimeException;

    public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException;
}
