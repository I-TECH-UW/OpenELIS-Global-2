package org.openelisglobal.qaevent.dao;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.valueholder.NceActionLog;

import java.util.List;

public interface NceActionLogDAO extends BaseDAO<NceActionLog, String> {

    List getNceActionLogByNceId(String nceId) throws LIMSRuntimeException;
}
