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
package org.openelisglobal.panelitem.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.panelitem.dao.PanelItemDAO;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class PanelItemDAOImpl extends BaseDAOImpl<PanelItem, String> implements PanelItemDAO {

    public PanelItemDAOImpl() {
        super(PanelItem.class);
    }

//	@Override
//	public void deleteData(List panelItems) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < panelItems.size(); i++) {
//				PanelItem data = (PanelItem) panelItems.get(i);
//
//				PanelItem oldData = readPanelItem(data.getId());
//				PanelItem newData = new PanelItem();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PANEL_ITEM";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < panelItems.size(); i++) {
//				PanelItem data = (PanelItem) panelItems.get(i);
//				data = readPanelItem(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(PanelItem panelItem) throws LIMSRuntimeException {
//		try {
//			if (duplicatePanelItemExists(panelItem)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + panelItem.getPanelName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(panelItem);
//			panelItem.setId(id);
//
//			String sysUserId = panelItem.getSysUserId();
//			String tableName = "PANEL_ITEM";
//			auditDAO.saveNewHistory(panelItem, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(PanelItem panelItem) throws LIMSRuntimeException {
//		try {
//			if (duplicatePanelItemExists(panelItem)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + panelItem.getPanel().getPanelName());
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem updateData()", e);
//		}
//
//		PanelItem oldData = readPanelItem(panelItem.getId());
//		// add to audit trail
//		try {
//
//			String sysUserId = panelItem.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PANEL_ITEM";
//			auditDAO.saveHistory(panelItem, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(panelItem);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(panelItem);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(panelItem);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(PanelItem panelItem) throws LIMSRuntimeException {
        try {
            PanelItem data = entityManager.unwrap(Session.class).get(PanelItem.class, panelItem.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (data != null) {
                PropertyUtils.copyProperties(panelItem, data);
            } else {
                panelItem.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getAllPanelItems() throws LIMSRuntimeException {
        List<PanelItem> list;
        try {
            String sql = "from PanelItem P order by P.panel.id ";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem getAllPanelItems()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getPageOfPanelItems(int startingRecNo) throws LIMSRuntimeException {
        List<PanelItem> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from PanelItem p order by p.panel.panelName, p.testName";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem getPageOfPanelItems()", e);
        }

        return list;
    }

    public PanelItem readPanelItem(String idString) {
        PanelItem pi;
        try {
            pi = entityManager.unwrap(Session.class).get(PanelItem.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem readPanelItem()", e);
        }

        return pi;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getPanelItems(String filter) throws LIMSRuntimeException {
        List<PanelItem> list;
        try {
            String sql = "from PanelItem p where upper(p.methodName) like upper(:param) order by upper(p.methodName)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem getPanelItems(String filter)", e);
        }
        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getPanelItemsForPanel(String panelId) throws LIMSRuntimeException {
        List<PanelItem> list;
        try {
            String sql = "from PanelItem p where p.panel.id = :panelId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("panelId", Integer.parseInt(panelId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PanelItem getPanelItemsForPanel(String panelId)", e);
        }
        
        System.out.println(">>>:");
        for (PanelItem item: list) {
            System.out.println(">>>:" + item.getTest().getName());
        }

        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalPanelItemCount() throws LIMSRuntimeException {
        return getCount();
    }

//    @Override
//    public boolean duplicatePanelItemExists(PanelItem panelItem) throws LIMSRuntimeException {
//        try {
//            List<PanelItem> list;
//
//            // not case sensitive hemolysis and Hemolysis are considered
//            // duplicates
//            String sql = "from PanelItem t where trim(lower(t.panel.panelName)) = :panelName and trim(lower(t.testName)) = :testName and t.id != :panelItemId";
//            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//            query.setParameter("panelName", panelItem.getPanel().getPanelName().toLowerCase().trim());
//            query.setParameter("testName", panelItem.getTest().getTestName().toLowerCase().trim());
//
//            // initialize with 0 (for new records where no id has been generated
//            // yet
//            String panelItemId = "0";
//            if (!StringUtil.isNullorNill(panelItem.getId())) {
//                panelItemId = panelItem.getId();
//            }
//            query.setInteger("panelItemId", Integer.parseInt(panelItemId));
//
//            list = query.list();
//            // entityManager.unwrap(Session.class).flush(); // CSL remove old
//            // entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//            return !list.isEmpty();
//
//        } catch (RuntimeException e) {
//            LogEvent.logError(e.toString(), e);
//            throw new LIMSRuntimeException("Error in duplicatePanelItemExists()", e);
//        }
//    }

    @Override
    @Transactional(readOnly = true)
    public boolean getDuplicateSortOrderForPanel(PanelItem panelItem) throws LIMSRuntimeException {
        try {
            List<PanelItem> list;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from PanelItem t where trim(lower(t.panel.panelName)) = :param and t.sortOrder = :sortOrder and t.id != :panelItemId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setParameter("param", panelItem.getPanelName().toLowerCase().trim());
            query.setInteger("sortOrder", Integer.parseInt(panelItem.getSortOrder()));

            // initialize with 0 (for new records where no id has been generated
            // yet
            String panelItemId = "0";
            if (!StringUtil.isNullorNill(panelItem.getId())) {
                panelItemId = panelItem.getId();
            }

            query.setInteger("panelItemId", Integer.parseInt(panelItemId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return !list.isEmpty();

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getDuplicateSortOrderForPanel()", e);
        }
    }

//	@Override
//	public List getPanelItemByPanel(Panel panel, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
//		try {
//			String sql = "from PanelItem pi where pi.panel = :param";
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", panel);
//
//			List list = query.list();
//
//			if (onlyTestsFullySetup && list != null && list.size() > 0) {
//				Iterator panelItemIterator = list.iterator();
//				list ;
//				while (panelItemIterator.hasNext()) {
//					PanelItem panelItem = (PanelItem) panelItemIterator.next();
//					String testName = panelItem.getTestName();
//					Test test = testDAO.getTestByName(testName);
//					if (test != null && !StringUtil.isNullorNill(test.getId()) && testDAO.isTestFullySetup(test)) {
//						list.add(panelItem);
//					}
//
//				}
//			}
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return list;
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("PanelItemDAOImpl", "getPanelItemByPanel()", e.toString());
//			throw new LIMSRuntimeException("Error in Method getPanelItemByPanel(String filter)", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getPanelItemByTestId(String testId) throws LIMSRuntimeException {
        String sql = "From PanelItem pi where pi.test.id = :testId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("testId", Integer.parseInt(testId));
            List<PanelItem> panelItems = query.list();
            // closeSession(); // CSL remove old
            return panelItems;

        } catch (HibernateException e) {
            handleException(e, "getPanelItemByTestId");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanelItem> getPanelItemsForPanelAndItemList(String panelId, List<Integer> testList)
            throws LIMSRuntimeException {
        String sql = "From PanelItem pi where pi.panel.id = :panelId and pi.test.id in (:testList)";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("panelId", Integer.parseInt(panelId));
            query.setParameterList("testList", testList);
            List<PanelItem> items = query.list();
            // closeSession(); // CSL remove old
            return items;
        } catch (HibernateException e) {
            handleException(e, "getPanelItemsFromPanelAndItemList");
        }
        return null;
    }
}