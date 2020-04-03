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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.sampleitem.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.sampleitem.dao.SampleItemDAO;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.sourceofsample.valueholder.SourceOfSample;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class SampleItemDAOImpl extends BaseDAOImpl<SampleItem, String> implements SampleItemDAO {

    public SampleItemDAOImpl() {
        super(SampleItem.class);
    }

//	@Override
//	public void deleteData(List<SampleItem> sampleItems) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < sampleItems.size(); i++) {
//				SampleItem data = sampleItems.get(i);
//
//				SampleItem oldData = readSampleItem(data.getId());
//				SampleItem newData = new SampleItem();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SAMPLE_ITEM";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("SampleItemDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleItem AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < sampleItems.size(); i++) {
//				SampleItem data = sampleItems.get(i);
//
//				data = readSampleItem(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleItemDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleItem deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(SampleItem sampleItem) throws LIMSRuntimeException {
//		if (sampleItem == null) {
//			return false;
//		}
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(sampleItem);
//			sampleItem.setId(id);
//
//			String sysUserId = sampleItem.getSysUserId();
//			String tableName = "SAMPLE_ITEM";
//			auditDAO.saveNewHistory(sampleItem, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleItemDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleItem insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SampleItem sampleItem) throws LIMSRuntimeException {
//
//		SampleItem oldData = readSampleItem(sampleItem.getId());
//		SampleItem newData = sampleItem;
//
//		try {
//
//			String sysUserId = sampleItem.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SAMPLE_ITEM";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleItemDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleItem AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(sampleItem);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(sampleItem);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(sampleItem);
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleItemDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleItem updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(SampleItem sampleItem) throws LIMSRuntimeException {
        try {
            SampleItem sampleIt = entityManager.unwrap(Session.class).get(SampleItem.class, sampleItem.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sampleIt != null) {
                PropertyUtils.copyProperties(sampleItem, sampleIt);
            } else {
                sampleItem.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SampleItem getData(String sampleItemId) throws LIMSRuntimeException {
        try {
            SampleItem sampleItem = entityManager.unwrap(Session.class).get(SampleItem.class, sampleItemId);
            // closeSession(); // CSL remove old
            return sampleItem;
        } catch (RuntimeException e) {
            handleException(e, "getData");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<SampleItem> getAllSampleItems() throws LIMSRuntimeException {
        List<SampleItem> list;
        try {
            String sql = "from SampleItem";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getAllSampleItems()", e);
        }

        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<SampleItem> getPageOfSampleItems(int startingRecNo) throws LIMSRuntimeException {
        List<SampleItem> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from SampleItem s order by s.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {

            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getPageOfSampleItems()", e);
        }

        return list;
    }

    public SampleItem readSampleItem(String idString) {
        SampleItem samp = null;
        try {
            samp = entityManager.unwrap(Session.class).get(SampleItem.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {

            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem readSampleItem()", e);
        }

        return samp;

    }

    @Override
    @Transactional(readOnly = true)
    public void getDataBySample(SampleItem sampleItem) throws LIMSRuntimeException {
        // Use an expression to read in the Sample_Item by SAMP_ID
        try {
            String sql = "from SampleItem si where samp_id = :param";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setInteger("param", Integer.parseInt(sampleItem.getSample().getId()));

            List<SampleItem> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            SampleItem si = null;
            if (!list.isEmpty()) {
                si = list.get(0);

                TypeOfSample tos = null;
                if (si.getTypeOfSampleId() != null) {
                    tos = entityManager.unwrap(Session.class).get(TypeOfSample.class, si.getTypeOfSampleId());
                    // entityManager.unwrap(Session.class).flush(); // CSL remove old
                    // entityManager.unwrap(Session.class).clear(); // CSL remove old
                    si.setTypeOfSample(tos);
                }
                SourceOfSample sos = null;
                if (si.getSourceOfSampleId() != null) {
                    sos = entityManager.unwrap(Session.class).get(SourceOfSample.class, si.getSourceOfSampleId());
                    si.setSourceOfSample(sos);
                    // entityManager.unwrap(Session.class).flush(); // CSL remove old
                    // entityManager.unwrap(Session.class).clear(); // CSL remove old
                }
                PropertyUtils.copyProperties(sampleItem, si);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getDataBySample()", e);
        }

    }

    @Override

    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleId(String id) throws LIMSRuntimeException {

        try {
            String sql = "from SampleItem sampleItem where sampleItem.sample.id = :sampleId order by sampleItem.sortOrder";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(id));
            List<SampleItem> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list;

        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getSampleItemsBySampleId()", e);
        }

    }

    /**
     * @see org.openelisglobal.sampleitem.dao.SampleItemDAO#getSampleItemsBySampleIdAndType(java.lang.String,
     *      org.openelisglobal.typeofsample.valueholder.TypeOfSample)
     */
    @Override

    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleIdAndType(String sampleId, TypeOfSample typeOfSample) {
        try {
            String sql = "from SampleItem si where si.sample.id = :sampleId and si.typeOfSample.id = :typeOfSampleId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(sampleId));
            query.setInteger("typeOfSampleId", Integer.parseInt(typeOfSample.getId()));
            List<SampleItem> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list;

        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleItem getSampleItemsBySampleId()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> includedStatusList)
            throws LIMSRuntimeException {
        if (includedStatusList.isEmpty()) {
            return new ArrayList<>();
        }

        try {
            String sql = "from SampleItem sampleItem where sampleItem.sample.id = :sampleId and sampleItem.statusId in ( :statusIds ) order by sampleItem.sortOrder";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(id));
            query.setParameterList("statusIds", includedStatusList);

            List<SampleItem> list = query.list();

            return list;

        } catch (HibernateException e) {
            handleException(e, "getSampleItemsBySampleIdAndStatus");
        }

        return null;
    }

}