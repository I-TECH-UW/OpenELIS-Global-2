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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.dictionarycategory.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.dictionarycategory.dao.DictionaryCategoryDAO;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz bugzilla 2061-2063
 */
@Component
@Transactional
public class DictionaryCategoryDAOImpl extends BaseDAOImpl<DictionaryCategory, String>
        implements DictionaryCategoryDAO {

    public DictionaryCategoryDAOImpl() {
        super(DictionaryCategory.class);
    }

//	@Override
//	public void deleteData(List dictionaryCategorys) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < dictionaryCategorys.size(); i++) {
//				DictionaryCategory data = (DictionaryCategory) dictionaryCategorys.get(i);
//
//				DictionaryCategory oldData = readDictionaryCategory(data.getId());
//				DictionaryCategory newData = new DictionaryCategory();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "DICTIONARY_CATEGORY";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < dictionaryCategorys.size(); i++) {
//				DictionaryCategory data = (DictionaryCategory) dictionaryCategorys.get(i);
//				// bugzilla 2206
//				data = readDictionaryCategory(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {
//		try {
//
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateDictionaryCategoryExists(dictionaryCategory)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + dictionaryCategory.getCategoryName());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(dictionaryCategory);
//			dictionaryCategory.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = dictionaryCategory.getSysUserId();
//			String tableName = "DICTIONARY_CATEGORY";
//			auditDAO.saveNewHistory(dictionaryCategory, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {
//
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateDictionaryCategoryExists(dictionaryCategory)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + dictionaryCategory.getCategoryName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory updateData()", e);
//		}
//
//		DictionaryCategory oldData = readDictionaryCategory(dictionaryCategory.getId());
//		DictionaryCategory newData = dictionaryCategory;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = dictionaryCategory.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "DICTIONARY_CATEGORY";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(dictionaryCategory);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove
//			// old(dictionaryCategory);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(dictionaryCategory);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory updateData()", e);
//		}
//	}

//	@Override
//	public void getData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {
//		try {
//			DictionaryCategory cc = entityManager.unwrap(Session.class).get(DictionaryCategory.class,
//					dictionaryCategory.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (cc != null) {
//				PropertyUtils.copyProperties(dictionaryCategory, cc);
//			} else {
//				dictionaryCategory.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory getData()", e);
//		}
//	}

//	@Override
//	public List getAllDictionaryCategorys() throws LIMSRuntimeException {
//		List list ;
//
//		try {
//			String sql = "from DictionaryCategory";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			// query.setMaxResults(10);
//			// query.setFirstResult(3);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "getAllDictionaryCategorys()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory getAllDictionaryCategorys()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfDictionaryCategorys(int startingRecNo) throws LIMSRuntimeException {
//		List list ;
//		try {
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			// bugzilla 1399
//			String sql = "from DictionaryCategory cc order by cc.description, cc.categoryName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "getPageOfDictionaryCategorys()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory getPageOfDictionaryCategorys()", e);
//		}
//
//		return list;
//	}

//	public DictionaryCategory readDictionaryCategory(String idString) {
//		DictionaryCategory dc = null;
//		try {
//			dc = entityManager.unwrap(Session.class).get(DictionaryCategory.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "readDictionaryCategory()", e.toString());
//			throw new LIMSRuntimeException("Error in DictionaryCategory readDictionaryCategory(idString)", e);
//		}
//
//		return dc;
//	}

//	@Override
//	public List getNextDictionaryCategoryRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "DictionaryCategory", DictionaryCategory.class);
//
//	}
//
//	@Override
//	public List getPreviousDictionaryCategoryRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "DictionaryCategory", DictionaryCategory.class);
//	}
//
//	// bugzilla 1411
//	@Override
//	public Integer getTotalDictionaryCategoryCount() throws LIMSRuntimeException {
//		return getCount();
//	}

//	bugzilla 1427
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = (Integer.valueOf(id)).intValue();
//		String tablePrefix = getTablePrefix(table);
//
//		List list ;
//		// bugzilla 1908
//		int rrn = 0;
//		try {
//			// bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
//			// instead get the list in this sortorder and determine the index of record with
//			// id = currentId
//			String sql = "select dc.id from DictionaryCategory dc " + " order by dc.description, dc.categoryName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1427
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = (Integer.valueOf(id)).intValue();
//		String tablePrefix = getTablePrefix(table);
//
//		List list ;
//		// bugzilla 1908
//		int rrn = 0;
//		try {
//			// bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
//			// instead get the list in this sortorder and determine the index of record with
//			// id = currentId
//			String sql = "select dc.id from DictionaryCategory dc "
//					+ " order by dc.description desc, dc.categoryName desc";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("DictionaryCategoryDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1386
    @Override
    public boolean duplicateDictionaryCategoryExists(DictionaryCategory dictionaryCategory)
            throws LIMSRuntimeException {
        try {

            List<DictionaryCategory> list = new ArrayList<>();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            // only one of each name, description, local abbrev can exist in entire table
            String sql = "from DictionaryCategory t where "
                    + "((trim(lower(t.categoryName)) = :param and t.id != :param3) " + "or "
                    + "(trim(lower(t.description)) = :param2 and t.id != :param3) " + "or "
                    + "(trim(lower(t.localAbbreviation)) = :param4 and t.id != :param3)) ";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", dictionaryCategory.getCategoryName().toLowerCase().trim());
            query.setParameter("param2", dictionaryCategory.getDescription().toLowerCase().trim());
            query.setParameter("param4", dictionaryCategory.getLocalAbbreviation().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String dictId = "0";
            if (!StringUtil.isNullorNill(dictionaryCategory.getId())) {
                dictId = dictionaryCategory.getId();
            }
            query.setParameter("param3", dictId);

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
            throw new LIMSRuntimeException("Error in duplicateDictionaryExists()", e);
        }
    }

    /**
     * Read a list of DictionaryCategory which match those field in the entity which
     * are filled in.
     */
//
//	public List<DictionaryCategory> readByExample(DictionaryCategory entity) throws LIMSRuntimeException {
//		List<DictionaryCategory> results;
//		try {
//			results = entityManager.unwrap(Session.class).createCriteria(DictionaryCategory.class)
//					.add(Example.create(entity)).list();
//		} catch (RuntimeException e) {
//			LogEvent.logError("DictionaryCategoryDAOImpl", "readByExample()", e.toString());
//			throw new LIMSRuntimeException("Error in readByExample()", e);
//		}
//		return results;
//	}

    @Override

    public DictionaryCategory getDictionaryCategoryByName(String name) throws LIMSRuntimeException {

        String sql = "from DictionaryCategory dc where dc.categoryName = :name";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", name);

            List<DictionaryCategory> categoryList = query.list();
            // closeSession(); // CSL remove old

            if (categoryList.size() > 0) {
                return categoryList.get(0);
            }
        } catch (RuntimeException e) {
            handleException(e, "getDictonaryCategoryByName");
        }

        return null;
    }

}