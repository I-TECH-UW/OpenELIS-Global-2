package org.openelisglobal.datasubmission.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.datasubmission.dao.TypeOfDataIndicatorDAO;
import org.openelisglobal.datasubmission.valueholder.TypeOfDataIndicator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TypeOfDataIndicatorDAOImpl extends BaseDAOImpl<TypeOfDataIndicator, String>
        implements TypeOfDataIndicatorDAO {

    public TypeOfDataIndicatorDAOImpl() {
        super(TypeOfDataIndicator.class);
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException {
        try {
            TypeOfDataIndicator typeOfIndicatorClone = entityManager.unwrap(Session.class)
                    .get(TypeOfDataIndicator.class, typeOfIndicator.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (typeOfIndicatorClone != null) {
                PropertyUtils.copyProperties(typeOfIndicator, typeOfIndicatorClone);
            } else {
                typeOfIndicator.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfDataIndicator getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfDataIndicator> getAllTypeOfDataIndicator() throws LIMSRuntimeException {
        List<TypeOfDataIndicator> list;
        try {
            String sql = "from TypeOfDataIndicator";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfDataIndicator getAllTypeOfDataIndicator()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfDataIndicator getTypeOfDataIndicator(String id) throws LIMSRuntimeException {
        try {
            TypeOfDataIndicator dataValue = entityManager.unwrap(Session.class).get(TypeOfDataIndicator.class, id);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return dataValue;
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfDataIndicator getData()", e);
        }
    }

//	@Override
//	public boolean insertData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(typeOfIndicator);
//			typeOfIndicator.setId(id);
//
//			String sysUserId = typeOfIndicator.getSysUserId();
//			String tableName = "TYPE_OF_DATA_INDICATOR";
//			auditDAO.saveNewHistory(typeOfIndicator, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfDataIndicatorDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfDataIndicator insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException {
//
//		TypeOfDataIndicator oldData = getTypeOfDataIndicator(typeOfIndicator.getId());
//
//		try {
//
//			String sysUserId = typeOfIndicator.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "TYPE_OF_DATA_INDICATOR";
//			auditDAO.saveHistory(typeOfIndicator, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("DataValueDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfDataIndicator AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(typeOfIndicator);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(typeOfIndicator);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(typeOfIndicator);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfDataIndicatorDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataValue updateData()", e);
//		}
//	}

}
