package org.openelisglobal.qaevent.daoimpl;

import java.util.List;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.dao.NceActionLogDAO;
import org.openelisglobal.qaevent.valueholder.NceActionLog;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class NceActionLogDAOImpl extends BaseDAOImpl<NceActionLog, String> implements NceActionLogDAO {

    public NceActionLogDAOImpl() {
        super(NceActionLog.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NceActionLog> getNceActionLogByNceId(String nceId) throws LIMSRuntimeException {
        return null;
    }
}
