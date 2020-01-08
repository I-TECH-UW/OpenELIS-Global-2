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
package org.openelisglobal.panel.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.panel.dao.PanelDAO;
import org.openelisglobal.panel.valueholder.Panel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class PanelDAOImpl extends BaseDAOImpl<Panel, String> implements PanelDAO {

    public PanelDAOImpl() {
        super(Panel.class);
    }

    private static Map<String, String> ID_NAME_MAP = null;
    private static Map<String, String> ID_DESCRIPTION_MAP = null;
    private static Map<String, String> NAME_ID_MAP = null;

//	@Override
//	public void deleteData(List panels) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < panels.size(); i++) {
//				Panel data = (Panel) panels.get(i);
//
//				Panel oldData = readPanel(data.getId());
//				Panel newData = new Panel();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PANEL";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < panels.size(); i++) {
//				Panel data = (Panel) panels.get(i);
//				// bugzilla 2206
//				data = readPanel(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel deleteData()", e);
//		}
//
//		clearIDMaps();
//	}

//	@Override
//	public String insert(Panel panel) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(panel);
//			panel.setId(id);
//
//			auditDAO.saveNewHistory(panel, panel.getSysUserId(), "PANEL");
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return id;
//		} catch (RuntimeException e) {
//			handleException(e, "insert");
//		}
//		return null;
//	}

//	@Override
//	public boolean insertData(Panel panel) throws LIMSRuntimeException {
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicatePanelExists(panel)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
//			}
//
//			// AIS - bugzilla 1563
//			if (duplicatePanelDescriptionExists(panel)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(panel);
//			panel.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = panel.getSysUserId();
//			String tableName = "PANEL";
//			auditDAO.saveNewHistory(panel, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel insertData()", e);
//		}
//
//		clearIDMaps();
//		return true;
//	}

//	@Override
//	public void updateData(Panel panel) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicatePanelExists(panel)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
//			}
//			// AIS - bugzilla 1563
//			if (duplicatePanelDescriptionExists(panel)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
//			}
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel updateData()", e);
//		}

//		Panel oldData = readPanel(panel.getId());
//		Panel newData = panel;
//
//		try {
//
//			String sysUserId = panel.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PANEL";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel AuditTrail updateData()", e);
//		}

//		try {
//			entityManager.unwrap(Session.class).merge(panel);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(panel);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(panel);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PanelDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Panel updateData()", e);
//		}
//
//		clearIDMaps();
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Panel panel) throws LIMSRuntimeException {
        try {
            Panel pan = entityManager.unwrap(Session.class).get(Panel.class, panel.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (pan != null) {
                PropertyUtils.copyProperties(panel, pan);
            } else {
                panel.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getData()", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Panel getPanelById(String panelId) throws LIMSRuntimeException {
        try {
            Panel panel = entityManager.unwrap(Session.class).get(Panel.class, panelId);
            // closeSession(); // CSL remove old
            return panel;
        } catch (HibernateException e) {
            handleException(e, "getDataById");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Panel> getAllActivePanels() throws LIMSRuntimeException {
        try {
            String sql = "from Panel p where p.isActive = 'Y' order by p.panelName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            List<Panel> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getAllActivePanels()", e);
        }

    }

    @Override

    @Transactional(readOnly = true)
    public List<Panel> getAllPanels() throws LIMSRuntimeException {
        try {
            String sql = "from Panel p order by p.sortOrderInt";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            List<Panel> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            return list;
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getAllPanels()", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Panel> getPageOfPanels(int startingRecNo) throws LIMSRuntimeException {
        List<Panel> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from Panel p order by p.panelName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getPageOfPanels()", e);
        }

        return list;
    }

    public Panel readPanel(String idString) {
        Panel panel = null;
        try {
            panel = entityManager.unwrap(Session.class).get(Panel.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel readPanel()", e);
        }

        return panel;
    }

    // this is for autocomplete
    @Override
    @Transactional(readOnly = true)
    public List<Panel> getActivePanels(String filter) throws LIMSRuntimeException {
        List<Panel> list = null;
        try {
            String sql = "from Panel p where isActive = 'Y' and upper(p.panelName) like upper(:param) order by upper(p.panelName)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getPanels()", e);
        }
        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public Panel getPanelByName(Panel panel) throws LIMSRuntimeException {
        return getPanelByName(panel.getPanelName());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalPanelCount() throws LIMSRuntimeException {
        return getCount();
    }

    @Override
    public boolean duplicatePanelExists(Panel panel) throws LIMSRuntimeException {
        try {

            List<Panel> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from Panel t where trim(lower(t.panelName)) = :param and t.id != :panelId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", panel.getPanelName().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String panelId = "0";
            if (!StringUtil.isNullorNill(panel.getId())) {
                panelId = panel.getId();
            }
            query.setInteger("panelId", Integer.parseInt(panelId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicatePanelExists()", e);
        }
    }

    @Override
    public boolean duplicatePanelDescriptionExists(Panel panel) throws LIMSRuntimeException {
        try {

            List<Panel> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from Panel t where trim(lower(t.description)) = :param and t.id != :panelId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", panel.getDescription().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String panelId = "0";
            if (!StringUtil.isNullorNill(panel.getId())) {
                panelId = panel.getId();
            }
            query.setInteger("panelId", Integer.parseInt(panelId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicatePanelDescriptionExists()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String getNameForPanelId(String id) {
        if (ID_NAME_MAP == null) {
            loadMaps();
        }

        return ID_NAME_MAP != null ? ID_NAME_MAP.get(id) : id;
    }

    @Override
    @Transactional(readOnly = true)
    public String getDescriptionForPanelId(String id) {
        if (ID_DESCRIPTION_MAP == null) {
            loadMaps();
        }

        return ID_DESCRIPTION_MAP != null ? ID_DESCRIPTION_MAP.get(id) : id;
    }

    @Override
    @Transactional(readOnly = true)
    public String getIdForPanelName(String name) {
        if (NAME_ID_MAP == null) {
            loadMaps();
        }

        return NAME_ID_MAP != null ? NAME_ID_MAP.get(name) : null;
    }

    private void loadMaps() {
        List<Panel> allPanels = getAllActivePanels();

        if (allPanels != null) {
            ID_NAME_MAP = new HashMap<>();
            ID_DESCRIPTION_MAP = new HashMap<>();
            NAME_ID_MAP = new HashMap<>();

            for (Object panelObj : allPanels) {
                Panel panel = (Panel) panelObj;
                ID_NAME_MAP.put(panel.getId(), panel.getPanelName());
                ID_DESCRIPTION_MAP.put(panel.getId(), panel.getDescription());
                NAME_ID_MAP.put(panel.getPanelName(), panel.getId());
            }
        }
    }

    @Override
    public void clearIDMaps() {
        ID_NAME_MAP = null;
        ID_DESCRIPTION_MAP = null;
    }

    @Override
    @Transactional(readOnly = true)
    public Panel getPanelByName(String panelName) {
        try {
            String sql = "from Panel p where p.panelName = :name";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", panelName);

            List<Panel> panelList = query.list();

            // closeSession(); // CSL remove old

            return panelList.isEmpty() ? null : panelList.get(0);

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Panel getPanelByName()", e);
        }
    }
}