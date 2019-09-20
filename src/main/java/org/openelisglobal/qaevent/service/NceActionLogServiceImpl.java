package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.qaevent.dao.NceActionLogDAO;
import org.openelisglobal.qaevent.valueholder.NceActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class NceActionLogServiceImpl extends BaseObjectServiceImpl<NceActionLog, String> implements NceActionLogService {

    @Autowired
    protected NceActionLogDAO baseObjectDAO;

    public NceActionLogServiceImpl() {
        super(NceActionLog.class);
    }

    @Override
    @Transactional
    public List getNceActionLogByNceId(String nceId) throws LIMSRuntimeException {
        return null;
    }

    @Override
    protected NceActionLogDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
