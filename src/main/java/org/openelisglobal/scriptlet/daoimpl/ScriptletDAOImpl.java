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
package org.openelisglobal.scriptlet.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.scriptlet.dao.ScriptletDAO;
import org.openelisglobal.scriptlet.valueholder.Scriptlet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class ScriptletDAOImpl extends BaseDAOImpl<Scriptlet, String> implements ScriptletDAO {

    public ScriptletDAOImpl() {
        super(Scriptlet.class);
    }

//	@Override
//	public void deleteData(List scriptlets) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < scriptlets.size(); i++) {
//				Scriptlet data = (Scriptlet) scriptlets.get(i);
//
//				Scriptlet oldData = readScriptlet(data.getId());
//				Scriptlet newData = new Scriptlet();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SCRIPTLET";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < scriptlets.size(); i++) {
//				Scriptlet data = (Scriptlet) scriptlets.get(i);
//				// bugzilla 2206
//				data = readScriptlet(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Scriptlet scriptlet) throws LIMSRuntimeException {
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateScriptletExists(scriptlet)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(scriptlet);
//			scriptlet.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = scriptlet.getSysUserId();
//			String tableName = "SCRIPTLET";
//			auditDAO.saveNewHistory(scriptlet, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Scriptlet scriptlet) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateScriptletExists(scriptlet)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet updateData()", e);
//		}
//
//		Scriptlet oldData = readScriptlet(scriptlet.getId());
//		Scriptlet newData = scriptlet;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = scriptlet.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SCRIPTLET";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(scriptlet);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(scriptlet);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(scriptlet);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("ScriptletDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Scriptlet updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Scriptlet scriptlet) throws LIMSRuntimeException {
        try {
            Scriptlet sc = entityManager.unwrap(Session.class).get(Scriptlet.class, scriptlet.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sc != null) {
                PropertyUtils.copyProperties(scriptlet, sc);
            } else {
                scriptlet.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Scriptlet> getAllScriptlets() throws LIMSRuntimeException {
        List<Scriptlet> list;
        try {
            String sql = "from Scriptlet";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            // query.setMaxResults(10);
            // query.setFirstResult(3);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet getAllScriptlets()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Scriptlet> getPageOfScriptlets(int startingRecNo) throws LIMSRuntimeException {
        List<Scriptlet> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from Scriptlet s order by s.scriptletName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet getPageOfScriptlets()", e);
        }

        return list;
    }

    public Scriptlet readScriptlet(String idString) {
        Scriptlet scriptlet = null;
        try {
            scriptlet = entityManager.unwrap(Session.class).get(Scriptlet.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet readScriptlet()", e);
        }

        return scriptlet;
    }

    // this is for autocomplete
    @Override
    @Transactional(readOnly = true)
    public List<Scriptlet> getScriptlets(String filter) throws LIMSRuntimeException {
        List<Scriptlet> list;
        try {
            String sql = "from Scriptlet s where upper(s.scriptletName) like upper(:param) order by upper(s.scriptletName)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet getScriptlets(String filter)", e);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Scriptlet getScriptletByName(Scriptlet scriptlet) throws LIMSRuntimeException {
        try {
            String sql = "from Scriptlet s where s.scriptletName = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", scriptlet.getScriptletName());

            List<Scriptlet> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            Scriptlet s = null;
            if (list.size() > 0) {
                s = list.get(0);
            }

            return s;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Scriptlet getScriptletByName()", e);
        }
    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalScriptletCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicateScriptletExists(Scriptlet scriptlet) throws LIMSRuntimeException {
        try {

            List<Scriptlet> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from Scriptlet t where trim(lower(t.scriptletName)) = :param and t.id != :param2";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", scriptlet.getScriptletName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String scriptletId = "0";
            if (!StringUtil.isNullorNill(scriptlet.getId())) {
                scriptletId = scriptlet.getId();
            }
            query.setParameter("param2", scriptletId);

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
            throw new LIMSRuntimeException("Error in duplicateScriptletExists()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Scriptlet getScriptletById(String scriptletId) throws LIMSRuntimeException {
        try {
            Scriptlet scriptlet = entityManager.unwrap(Session.class).get(Scriptlet.class, scriptletId);
            // closeSession(); // CSL remove old
            return scriptlet;
        } catch (RuntimeException e) {
            handleException(e, "getScriptletById");
        }

        return null;
    }
}