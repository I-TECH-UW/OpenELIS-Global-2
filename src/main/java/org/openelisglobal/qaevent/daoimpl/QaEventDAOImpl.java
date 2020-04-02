/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package org.openelisglobal.qaevent.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.qaevent.dao.QaEventDAO;
import org.openelisglobal.qaevent.valueholder.QaEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class QaEventDAOImpl extends BaseDAOImpl<QaEvent, String> implements QaEventDAO {

    public QaEventDAOImpl() {
        super(QaEvent.class);
    }

//	@Override
//	public void deleteData(List qaEvents) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < qaEvents.size(); i++) {
//				QaEvent data = (QaEvent) qaEvents.get(i);
//
//				QaEvent oldData = readQaEvent(data.getId());
//				QaEvent newData = new QaEvent();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "QA_EVENT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < qaEvents.size(); i++) {
//				QaEvent data = (QaEvent) qaEvents.get(i);
//				QaEvent cloneData = readQaEvent(data.getId());
//
//				entityManager.unwrap(Session.class).merge(cloneData);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(cloneData);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(cloneData);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(QaEvent qaEvent) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateQaEventExists(qaEvent)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + qaEvent.getQaEventName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(qaEvent);
//			qaEvent.setId(id);
//
//			String sysUserId = qaEvent.getSysUserId();
//			String tableName = "QA_EVENT";
//			auditDAO.saveNewHistory(qaEvent, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(QaEvent qaEvent) throws LIMSRuntimeException {
//		try {
//			if (duplicateQaEventExists(qaEvent)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + qaEvent.getQaEventName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent updateData()", e);
//		}
//
//		QaEvent oldData = readQaEvent(qaEvent.getId());
//		QaEvent newData = qaEvent;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = qaEvent.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "QA_EVENT";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(qaEvent);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(qaEvent);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(qaEvent);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("QaEventDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaEvent updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(QaEvent qaEvent) throws LIMSRuntimeException {
        try {
            QaEvent qaEv = entityManager.unwrap(Session.class).get(QaEvent.class, qaEvent.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (qaEv != null) {
                PropertyUtils.copyProperties(qaEvent, qaEv);

            } else {
                qaEvent.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<QaEvent> getAllQaEvents() throws LIMSRuntimeException {
        List<QaEvent> list;
        try {
            String sql = "from QaEvent qe order by qe.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent getAllQaEvents()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QaEvent> getPageOfQaEvents(int startingRecNo) throws LIMSRuntimeException {
        List<QaEvent> list = new Vector<>();
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from QaEvent qe order by qe.qaEventName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent getPageOfQaEvents()", e);
        }

        return list;
    }

    public QaEvent readQaEvent(String idString) {
        QaEvent qaEvent = null;
        try {
            qaEvent = entityManager.unwrap(Session.class).get(QaEvent.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent readQaEvent()", e);
        }

        return qaEvent;
    }

    // this is for autocomplete
    @Override
    @Transactional(readOnly = true)
    public List<QaEvent> getQaEvents(String filter) throws LIMSRuntimeException {
        List<QaEvent> list = new Vector<>();
        try {
            String sql = "from QaEvent qe where upper(qe.qaEventName) like upper(:param) order by upper(qe.qaEventName)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent getQaEvents(String filter)", e);
        }
        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public QaEvent getQaEventByName(QaEvent qaEvent) throws LIMSRuntimeException {
        try {
            String sql = "from QaEvent qe where qe.qaEventName = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", qaEvent.getQaEventName());

            List<QaEvent> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            QaEvent qe = null;
            if (list.size() > 0) {
                qe = list.get(0);
            }

            return qe;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in QaEvent getQaEventByName()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalQaEventCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicateQaEventExists(QaEvent qaEvent) throws LIMSRuntimeException {
        try {

            List<QaEvent> list = new ArrayList<>();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from QaEvent t where "
                    + "((trim(lower(t.qaEventName)) = :param and trim(lower(t.type)) = :param3 and t.id != :param2) "
                    + "or "
                    + "(trim(lower(t.description)) = :param4 and trim(lower(t.type)) = :param3 and t.id != :param2)) ";

            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", qaEvent.getQaEventName().toLowerCase().trim());
            query.setParameter("param3", qaEvent.getType());
            query.setParameter("param4", qaEvent.getDescription().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated yet
            String qaEventId = "0";
            if (!StringUtil.isNullorNill(qaEvent.getId())) {
                qaEventId = qaEvent.getId();
            }
            query.setParameter("param2", qaEventId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateQaEventExists()", e);
        }
    }

}