package org.openelisglobal.observationhistorytype.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.observationhistorytype.dao.ObservationHistoryTypeDAO;
import org.openelisglobal.observationhistorytype.valueholder.ObservationHistoryType;

@Component
@Transactional
public class ObservationHistoryTypeDAOImpl extends BaseDAOImpl<ObservationHistoryType, String>
        implements ObservationHistoryTypeDAO {

    public ObservationHistoryTypeDAOImpl() {
        super(ObservationHistoryType.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public ObservationHistoryType getByName(String name) throws LIMSRuntimeException {
        List<ObservationHistoryType> historyTypeList;

        try {
            String sql = "from ObservationHistoryType oht where oht.typeName = :name";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", name);
            historyTypeList = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return historyTypeList.size() > 0 ? historyTypeList.get(0) : null;

        } catch (Exception e) {
            LogEvent.logError("ObservationHistoryTypeDAOImpl ", "getByName()", e.toString());
            throw new LIMSRuntimeException("Error in ObservationHistoryTypeDAOImpl  getByName()", e);
        }
    }

    /**
     * Read all entities from the database.
     */
    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<ObservationHistoryType> getAll() throws LIMSRuntimeException {
        List<ObservationHistoryType> entities;
        try {
            String sql = "from ObservationHistoryType";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            entities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }

        return entities;
    }

}
