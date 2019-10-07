package org.openelisglobal.qaevent.daoimpl;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.qaevent.dao.NceCategoryDAO;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class NceCategoryDAOImpl extends BaseDAOImpl<NceCategory, String> implements NceCategoryDAO {

    public NceCategoryDAOImpl() {
        super(NceCategory.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllNceCategory() throws LIMSRuntimeException {
        List list;
        try {
            String sql = "from NceCategory nc order by nc.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (Exception e) {
            LogEvent.logError("NceCategoryDaoImpl", "getAllNceCategory()", e.toString());
            throw new LIMSRuntimeException("Error in NceCategory getAllNceCategory()", e);
        }
        return list;
    }
}
