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
package org.openelisglobal.statusofsample.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.statusofsample.dao.StatusOfSampleDAO;
import org.openelisglobal.statusofsample.valueholder.StatusOfSample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bill mcgough
 */
@Component
@Transactional
public class StatusOfSampleDAOImpl extends BaseDAOImpl<StatusOfSample, String> implements StatusOfSampleDAO {

    public StatusOfSampleDAOImpl() {
        super(StatusOfSample.class);
    }

    // bugzilla 1942
    @Override
    @Transactional(readOnly = true)
    public StatusOfSample getDataByStatusTypeAndStatusCode(StatusOfSample statusofsample) throws LIMSRuntimeException {

        try {
            // AIS - bugzilla 1546 - Used Upper
            String sql = "from StatusOfSample ss where UPPER(ss.statusType) = UPPER(:param) and ss.code = :param2";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", statusofsample.getStatusType());
            query.setParameter("param2", statusofsample.getCode());
            List<StatusOfSample> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            StatusOfSample statusOfSamp = null;

            if (list.size() > 0) {
                statusOfSamp = list.get(0);
            }

            return statusOfSamp;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in StatusOfSample getDataByStatusTypeAndStatusCode()", e);
        }
    }

    /**
     * insertData()
     *
     * @param statusOfSample
     * @return boolean
     * @throws LIMSRuntimeException
     */
//	@Override
//	public boolean insertData(StatusOfSample statusOfSample) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateStatusOfSampleExists(statusOfSample)) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("Duplicate record exists for Description: ");
//				sb.append(statusOfSample.getDescription());
//				sb.append(" Status Type: ");
//				sb.append(statusOfSample.getStatusType());
//				// bugzilla 2154
//				LogEvent.logError("StatusOfSample", "insertData()", sb.toString());
//				throw new LIMSDuplicateRecordException(sb.toString());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(statusOfSample);
//
//			statusOfSample.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = statusOfSample.getSysUserId();
//			String tableName = "STATUS_OF_SAMPLE";
//			auditDAO.saveNewHistory(statusOfSample, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("StatusOfSampleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in StatusOfSample insertData()", e);
//		}
//
//		return true;
//	}

    /**
     * udpateData()
     *
     * @param statusOfSample
     * @throws LIMSRuntimeException
     */
//	@Override
//	public void updateData(StatusOfSample statusOfSample) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateStatusOfSampleExists(statusOfSample)) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("Duplicate record exists for Description: ");
//				sb.append(statusOfSample.getDescription());
//				sb.append(" Status Type: ");
//				sb.append(statusOfSample.getStatusType());
//				// bugzilla 2154
//				LogEvent.logError("StatusOfSample", "updateData()", sb.toString());
//				throw new LIMSDuplicateRecordException(sb.toString());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("StatusOfSampleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in StatusOfSample updateData()", e);
//		}
//
//		StatusOfSample oldData = readStatusOfSample(statusOfSample.getId());
//		StatusOfSample newData = statusOfSample;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = statusOfSample.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "STATUS_OF_SAMPLE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("StatusOfSampleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in StatusOfSample AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(statusOfSample);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(statusOfSample);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(statusOfSample);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("StatusOfSampleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in StatusOfSample updateData()", e);
//		}
//	}

    /**
     * getData()
     *
     * @param statusOfSample
     * @throws LIMSRuntimeException
     */
    @Override
    @Transactional(readOnly = true)
    public void getData(StatusOfSample statusOfSample) throws LIMSRuntimeException {

        try {
            StatusOfSample sos = entityManager.unwrap(Session.class).get(StatusOfSample.class, statusOfSample.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sos != null) {
                PropertyUtils.copyProperties(statusOfSample, sos);
            } else {
                statusOfSample.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in StatusOfSample getData()", e);
        }
    }

    /**
     * getAllStatusOfSamples()
     *
     * @return List
     * @throws LIMSRuntimeException
     */
    @Override
    @Transactional(readOnly = true)
    public List<StatusOfSample> getAllStatusOfSamples() throws LIMSRuntimeException {

        List<StatusOfSample> list;
        try {
            String sql = "from StatusOfSample sos order by sos.statusOfSampleName ";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in StatusOfSample getAllStatusOfSamples()", e);
        }

        return list;

    }

    /**
     * getPageOfStatusOfSamples()
     *
     * @param startingRecNo
     * @return List
     * @throws LIMSRuntimeException
     */
    @Override
    @Transactional(readOnly = true)
    public List<StatusOfSample> getPageOfStatusOfSamples(int startingRecNo) throws LIMSRuntimeException {

        List<StatusOfSample> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from StatusOfSample s order by s.statusType, s.code";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in StatusOfSample getPageOfStatusOfSamples()", e);
        }

        return list;
    }

    /**
     * readStatusOfSample()
     *
     * @param idString
     * @return StatusOfSample
     */
    public StatusOfSample readStatusOfSample(String idString) {

        StatusOfSample sos = null;
        try {
            sos = entityManager.unwrap(Session.class).get(StatusOfSample.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in StatusOfSample readStatusOfSample()", e);
        }

        return sos;
    }

    // bugzilla 1761 removed getStatus() - no longer needed

    /**
     * getTotalStatusOfSampleCount()
     *
     * @return Integer - total count
     *
     */
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalStatusOfSampleCount() throws LIMSRuntimeException {
        return getCount();
    }

//	 bugzilla 1482
    /**
     * duplicateStatusOfSampleExists() - checks for duplicate description & status
     * type
     *
     * @param statusOfSample
     * @return boolean
     *
     */
    @Override
    public boolean duplicateStatusOfSampleExists(StatusOfSample statusOfSample) throws LIMSRuntimeException {
        try {

            List<StatusOfSample> list;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from StatusOfSample t where trim(lower(t.code)) = :param and trim(lower(t.statusType)) = :param2 and t.id != :param3";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", statusOfSample.getCode().toLowerCase().trim());
            query.setParameter("param2", statusOfSample.getStatusType().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String statusOfSampleId = "0";
            if (!StringUtil.isNullorNill(statusOfSample.getId())) {
                statusOfSampleId = statusOfSample.getId();
            }
            query.setParameter("param3", statusOfSampleId);

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
            throw new LIMSRuntimeException("Error in duplicateStatusOfSampleExists()", e);
        }
    }

}// end of class