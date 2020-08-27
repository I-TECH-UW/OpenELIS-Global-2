package org.openelisglobal.qaevent.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.qaevent.dao.NceSpecimenDAO;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class NceSpecimenDAOImpl extends BaseDAOImpl<NceSpecimen, String> implements NceSpecimenDAO {

    public NceSpecimenDAOImpl() {
        super(NceSpecimen.class);
    }

    @Override
    public List<NceSpecimen> getSpecimenByNceId(String nceId) throws LIMSRuntimeException {
        List<NceSpecimen> list;
        try {
            String sql = "from NceSpecimen ns where ns.nceId=:nceId ";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            ((Query) query).setParameter("nceId", nceId);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in NceCategory getAllNceCategory()", e);
        }
        return list;
    }

    @Override
    public List<NceSpecimen> getSpecimenBySampleId(String sampleId) {
        List<NceSpecimen> list;
        String sql = "from NceSpecimen ns where ns.sampleItemId=:sampleId ";
        org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
        ((Query) query).setParameter("sampleId", Integer.parseInt(sampleId));
        list = query.list();

        return list;
    }
}
