package org.openelisglobal.qaevent.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.qaevent.dao.NceCategoryDAO;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class NceCategoryDAOImpl extends BaseDAOImpl<NceCategory, String> implements NceCategoryDAO {

    public NceCategoryDAOImpl() {
        super(NceCategory.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NceCategory> getAllNceCategory() throws LIMSRuntimeException {
        List<NceCategory> list;
        try {
            String sql = "from NceCategory nc order by nc.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in NceCategory getAllNceCategory()", e);
        }
        return list;
    }
}
