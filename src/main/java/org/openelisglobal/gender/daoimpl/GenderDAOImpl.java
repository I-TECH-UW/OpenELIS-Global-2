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
package org.openelisglobal.gender.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.gender.dao.GenderDAO;
import org.openelisglobal.gender.valueholder.Gender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class GenderDAOImpl extends BaseDAOImpl<Gender, Integer> implements GenderDAO {

    public GenderDAOImpl() {
        super(Gender.class);
    }

//	@Override
//	public void deleteData(List genders) throws LIMSRuntimeException {
//		try {
//
//			for (Object gender : genders) {
//				Gender data = (Gender) gender;
//
//				Gender oldData = readGender(data.getId());
//				Gender newData = new Gender();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "GENDER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (Object gender : genders) {
//				Gender data = (Gender) gender;
//				data = readGender(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("GenderDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Gender gender) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateGenderExists(gender)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + gender.getGenderType());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(gender);
//			gender.setId(id);
//
//			String sysUserId = gender.getSysUserId();
//			String tableName = "GENDER";
//			auditDAO.saveNewHistory(gender, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Gender gender) throws LIMSRuntimeException {
//		try {
//			if (duplicateGenderExists(gender)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + gender.getGenderType());
//			}
//		} catch (RuntimeException e) {
//			LogEvent.logError("GenderDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender updateData()", e);
//		}
//
//		Gender oldData = readGender(gender.getId());
//
//		// add to audit trail
//		try {
//
//			String sysUserId = gender.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "GENDER";
//			auditDAO.saveHistory(gender, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(gender);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(gender);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(gender);
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender updateData()", e);
//		}
//	}

//	@Override
//	public void getData(Gender gender) throws LIMSRuntimeException {
//		try {
//			Gender gen = entityManager.unwrap(Session.class).get(Gender.class, gender.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (gen != null) {
//				PropertyUtils.copyProperties(gender, gen);
//			} else {
//				gender.setId(null);
//			}
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender getData()", e);
//		}
//	}

//	@Override
//	public List getAllGenders() throws LIMSRuntimeException {
//		List list;
//		try {
//			String sql = "from Gender";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			// query.setMaxResults(10);
//			// query.setFirstResult(3);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "getAllGenders()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender getAllGenders()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfGenders(int startingRecNo) throws LIMSRuntimeException {
//		List list;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sql = "from Gender g order by g.description, g.genderType";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//			// query.setCacheMode(org.hibernate.CacheMode.REFRESH);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "getPageOfGenders()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender getPageOfGenders()", e);
//		}
//
//		return list;
//	}

//	public Gender readGender(String idString) {
//		Gender gender;
//		try {
//			gender = entityManager.unwrap(Session.class).get(Gender.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "readGender()", e.toString());
//			throw new LIMSRuntimeException("Error in Gender readGender(idString)", e);
//		}
//
//		return gender;
//	}

//	@Override
//	public List getNextGenderRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "Gender", Gender.class);
//	}

    @Override
    @Transactional(readOnly = true)
    public Gender getGenderByType(String type) throws LIMSRuntimeException {
        String sql = "From Gender g where g.genderType = :type";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("type", type);
            Gender gender = (Gender) query.uniqueResult();
            // closeSession(); // CSL remove old
            return gender;
        } catch (HibernateException e) {
            handleException(e, "getGenderByType");
        }
        return null;
    }

//	@Override
//	public List getPreviousGenderRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "Gender", Gender.class);
//	}

//	@Override
//	public Integer getTotalGenderCount() throws LIMSRuntimeException {
//		return getCount();
//	}

//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = Integer.valueOf(id);
//		String tablePrefix = getTablePrefix(table);
//
//		List list;
//
//		try {
//			String sql = "select g.id from Gender g order by g.description, g.genderType";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			int rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}
//
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = Integer.valueOf(id);
//		String tablePrefix = getTablePrefix(table);
//
//		List list;
//
//		try {
//			String sql = "select g.id from Gender g order by g.description desc, g.genderType desc";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			int rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("GenderDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1482
    @Override
    public boolean duplicateGenderExists(Gender gender) throws LIMSRuntimeException {
        try {

            List<Gender> list;

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            String sql = "from Gender t where trim(lower(t.genderType)) = :genderType and t.id != :genderId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("genderType", gender.getGenderType().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            Integer genderId = 0;
            if (gender.getId() != null) {
                genderId = gender.getId();
            }
            query.setInteger("genderId", genderId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;

        } catch (RuntimeException e) {

            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateGenderExists()", e);
        }
    }
}