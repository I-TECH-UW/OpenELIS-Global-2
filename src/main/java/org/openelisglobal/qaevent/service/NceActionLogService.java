package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.NceActionLog;

import java.util.List;

public interface NceActionLogService extends BaseObjectService<NceActionLog, String> {

    List getNceActionLogByNceId(String nceId) throws LIMSRuntimeException;
}
