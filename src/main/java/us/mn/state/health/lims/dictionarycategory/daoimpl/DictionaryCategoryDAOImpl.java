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
package us.mn.state.health.lims.dictionarycategory.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * @author diane benz
 * bugzilla 2061-2063
 */
public class DictionaryCategoryDAOImpl extends BaseDAOImpl implements DictionaryCategoryDAO {

	public void deleteData(List dictionaryCategorys) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < dictionaryCategorys.size(); i++) {
				DictionaryCategory data = (DictionaryCategory)dictionaryCategorys.get(i);

				DictionaryCategory oldData = (DictionaryCategory)readDictionaryCategory(data.getId());
				DictionaryCategory newData = new DictionaryCategory();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "DICTIONARY_CATEGORY";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < dictionaryCategorys.size(); i++) {
				DictionaryCategory data = (DictionaryCategory) dictionaryCategorys.get(i);
				//bugzilla 2206
				data = (DictionaryCategory)readDictionaryCategory(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory deleteData()", e);
		}
	}

	public boolean insertData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {
		try {

			// bugzilla 1482 throw Exception if record already exists
			if (duplicateDictionaryCategoryExists(dictionaryCategory)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ dictionaryCategory.getCategoryName());
			}

			String id = (String)HibernateUtil.getSession().save(dictionaryCategory);
			dictionaryCategory.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dictionaryCategory.getSysUserId();
			String tableName = "DICTIONARY_CATEGORY";
			auditDAO.saveNewHistory(dictionaryCategory,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory insertData()", e);
		}

		return true;
	}

	public void updateData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {

		// bugzilla 1482 throw Exception if record already exists
		try {
			if (duplicateDictionaryCategoryExists(dictionaryCategory)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ dictionaryCategory.getCategoryName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory updateData()",
					e);
		}

		DictionaryCategory oldData = (DictionaryCategory)readDictionaryCategory(dictionaryCategory.getId());
		DictionaryCategory newData = dictionaryCategory;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dictionaryCategory.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "DICTIONARY_CATEGORY";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(dictionaryCategory);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(dictionaryCategory);
			HibernateUtil.getSession().refresh(dictionaryCategory);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory updateData()", e);
		}
	}

	public void getData(DictionaryCategory dictionaryCategory) throws LIMSRuntimeException {
		try {
			DictionaryCategory cc = (DictionaryCategory)HibernateUtil.getSession().get(DictionaryCategory.class, dictionaryCategory.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (cc != null) {
			  PropertyUtils.copyProperties(dictionaryCategory, cc);
			} else {
				dictionaryCategory.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory getData()", e);
		}
	}

	public List getAllDictionaryCategorys() throws LIMSRuntimeException {
		List list = new Vector();

		try {
			String sql = "from DictionaryCategory";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","getAllDictionaryCategorys()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory getAllDictionaryCategorys()",e);
		}

		return list;
	}

	public List getPageOfDictionaryCategorys(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			int endingRecNo = startingRecNo
			+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			//bugzilla 1399
			String sql = "from DictionaryCategory cc order by cc.description, cc.categoryName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","getPageOfDictionaryCategorys()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory getPageOfDictionaryCategorys()",e);
		}

		return list;
	}

	public DictionaryCategory readDictionaryCategory(String idString) {
		DictionaryCategory dc = null;
		try {
			dc = (DictionaryCategory)HibernateUtil.getSession().get(DictionaryCategory.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","readDictionaryCategory()",e.toString());
			throw new LIMSRuntimeException("Error in DictionaryCategory readDictionaryCategory(idString)", e);
		}

		return dc;
	}

	public List getNextDictionaryCategoryRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "DictionaryCategory", DictionaryCategory.class);

	}

	public List getPreviousDictionaryCategoryRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "DictionaryCategory", DictionaryCategory.class);
	}

	//bugzilla 1411
	public Integer getTotalDictionaryCategoryCount() throws LIMSRuntimeException {
		return getTotalCount("DictionaryCategory", DictionaryCategory.class);
	}

//	bugzilla 1427
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
			String sql = "select dc.id from DictionaryCategory dc " +
					" order by dc.description, dc.categoryName";
				org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list();


		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}


	//bugzilla 1427
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
			String sql = "select dc.id from DictionaryCategory dc " +
					" order by dc.description desc, dc.categoryName desc";
				org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list();


		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}


	// bugzilla 1386
	private boolean duplicateDictionaryCategoryExists(DictionaryCategory dictionaryCategory)
			throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			//only one of each name, description, local abbrev can exist in entire table
			String sql = "from DictionaryCategory t where " +
					"((trim(lower(t.categoryName)) = :param and t.id != :param3) " +
					"or " +
					"(trim(lower(t.description)) = :param2 and t.id != :param3) " +
					"or " +
					"(trim(lower(t.localAbbreviation)) = :param4 and t.id != :param3)) ";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", dictionaryCategory.getCategoryName().toLowerCase()
					.trim());
			query.setParameter("param2", dictionaryCategory.getDescription().toLowerCase()
					.trim());
			query.setParameter("param4", dictionaryCategory.getLocalAbbreviation().toLowerCase()
					.trim());

			// initialize with 0 (for new records where no id has been generated
			// yet
			String dictId = "0";
			if (!StringUtil.isNullorNill(dictionaryCategory.getId())) {
				dictId = dictionaryCategory.getId();
			}
			query.setParameter("param3", dictId);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();


			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("DictionaryCategoryDAOImpl","duplicateDictionaryExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateDictionaryExists()", e);
		}
	}

	/**
	 * Read a list of DictionaryCategory which match those field in the entity which are filled in.
	 */
	@SuppressWarnings("unchecked")
	public List<DictionaryCategory> readByExample(DictionaryCategory entity) throws LIMSRuntimeException {
		List<DictionaryCategory> results;
		try {
			results = (List<DictionaryCategory>)HibernateUtil.getSession().createCriteria(DictionaryCategory.class).add(Example.create(entity)).list();
		} catch (Exception e) {
			LogEvent.logError("DictionaryCategoryDAOImpl","readByExample()",e.toString());
			throw new LIMSRuntimeException("Error in readByExample()", e);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public DictionaryCategory getDictionaryCategoryByName(String name) throws LIMSRuntimeException {

		String sql = "from DictionaryCategory dc where dc.categoryName = :name";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);

			List<DictionaryCategory> categoryList = query.list();
			closeSession();

			if( categoryList.size() > 0){
				return categoryList.get(0);
			}
		}catch(Exception e){
			handleException(e, "getDictonaryCategoryByName");
		}

		return null;
	}

}