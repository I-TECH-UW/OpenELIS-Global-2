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
package us.mn.state.health.lims.dictionary.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSFrozenRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * @author diane benz
 */
public class DictionaryDAOImpl extends BaseDAOImpl implements DictionaryDAO {

    public void deleteData(List dictionarys) throws LIMSRuntimeException {
        // add to audit trail
        try {
            AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
            for (int i = 0; i < dictionarys.size(); i++) {
                Dictionary data = (Dictionary) dictionarys.get(i);

                Dictionary oldData = (Dictionary) readDictionary(data.getId());
                Dictionary newData = new Dictionary();

                String sysUserId = data.getSysUserId();
                String event = IActionConstants.AUDIT_TRAIL_DELETE;
                String tableName = "DICTIONARY";
                auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "deleteData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary AuditTrail deleteData()", e);
        }

        try {
            for (int i = 0; i < dictionarys.size(); i++) {
                Dictionary data = (Dictionary) dictionarys.get(i);
                Dictionary cloneData = (Dictionary) readDictionary(data.getId());

                // Make the change to the object.
                cloneData.setIsActive(IActionConstants.NO);
                HibernateUtil.getSession().merge(cloneData);
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().clear();
                HibernateUtil.getSession().evict(cloneData);
                HibernateUtil.getSession().refresh(cloneData);
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "deleteData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary deleteData()", e);
        }
    }

    public boolean insertData(Dictionary dictionary) throws LIMSRuntimeException {
        try {
            // bugzilla 1386 throw Exception if record already exists
            if (duplicateDictionaryExists(dictionary)) {
                throw new LIMSDuplicateRecordException("Duplicate record or abrevation exists for "
                                + dictionary.getDictEntry());
            }

            String id = (String) HibernateUtil.getSession().save(dictionary);
            dictionary.setId(id);

            // bugzilla 1824 inserts will be logged in history table
            AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
            String sysUserId = dictionary.getSysUserId();
            String tableName = "DICTIONARY";
            auditDAO.saveNewHistory(dictionary, sysUserId, tableName);

            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "insertData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary insertData()", e);
        }

        return true;
    }

    // modified for bugzilla 2061-2063
    public void updateData(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired) throws LIMSRuntimeException {

        // bugzilla 1386 throw Exception if record already exists
        try {
            if (duplicateDictionaryExists(dictionary)) {
                throw new LIMSDuplicateRecordException("Duplicate record exists for " + dictionary.getDictEntry());
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "updateData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary updateData()", e);
        }

        // bugzilla 1482 throw Exception if dictionary is already referenced in
        // the system
        try {
            if (isDictionaryFrozenCheckRequired) {
                if (isDictionaryFrozen(dictionary)) {
                    throw new LIMSFrozenRecordException("Dictionary Entry is referenced " + dictionary.getDictEntry());
                }
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "updateData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary updateData()", e);
        }

        Dictionary oldData = (Dictionary) readDictionary(dictionary.getId());
        Dictionary newData = dictionary;

        // add to audit trail
        try {
            AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
            String sysUserId = dictionary.getSysUserId();
            String event = IActionConstants.AUDIT_TRAIL_UPDATE;
            String tableName = "DICTIONARY";
            auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "updateData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary AuditTrail updateData()", e);
        }

        try {
            HibernateUtil.getSession().merge(dictionary);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            HibernateUtil.getSession().evict(dictionary);
            HibernateUtil.getSession().refresh(dictionary);
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "updateData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary updateData()", e);
        }
    }

    public void getData(Dictionary dictionary) throws LIMSRuntimeException {
        try {
            Dictionary d = (Dictionary) HibernateUtil.getSession().get(Dictionary.class, dictionary.getId());
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            if (d != null) {
                PropertyUtils.copyProperties(dictionary, d);
            } else {
                dictionary.setId(null);
            }
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getData()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary getData()", e);
        }
    }

    public List getAllDictionarys() throws LIMSRuntimeException {
        List list = new Vector();
        try {
            String sql = "from Dictionary";
            Query query = HibernateUtil.getSession().createQuery(sql);
            // query.setMaxResults(10);
            // query.setFirstResult(3);

            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getAllDictionarys()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary getAllDictionarys()", e);
        }

        return list;
    }

    public List getPageOfDictionarys(int startingRecNo) throws LIMSRuntimeException {
        List list = new Vector();

        try {
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from Dictionary d order by d.dictionaryCategory.categoryName, d.dictEntry";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getPageOfDictionarys()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary getPageOfDictionarys()", e);
        }

        return list;
    }

    // bugzilla 1413
    public List getPagesOfSearchedDictionarys(int startingRecNo, String searchString) throws LIMSRuntimeException {
        List list = new Vector();
        String wildCard = "*";
        String newSearchStr;
        String sql;

        try {
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
            int wCdPosition = searchString.indexOf(wildCard);

            if (wCdPosition == -1) // no wild card looking for exact match
            {
                newSearchStr = searchString.toLowerCase().trim();
                sql = "from Dictionary d where trim(lower (d.dictEntry)) = :param  order by d.dictionaryCategory.categoryName, d.dictEntry";
            } else {
                newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
                sql = "from Dictionary d where trim(lower (d.dictEntry)) like :param  order by d.dictionaryCategory.categoryName, d.dictEntry";
            }
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param", newSearchStr);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LIMSRuntimeException("Error in Dictionary getPageOfSearchedDictionarys()", e);
        }

        return list;
    }

    // end bugzilla 1413

    public Dictionary readDictionary(String idString) {
        Dictionary dictionary = null;
        try {
            dictionary = (Dictionary) HibernateUtil.getSession().get(Dictionary.class, idString);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "readDictionary()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary readDictionary()", e);
        }

        return dictionary;
    }

    public List getNextDictionaryRecord(String id) throws LIMSRuntimeException {

        return getNextRecord(id, "Dictionary", Dictionary.class);

    }

    public List getPreviousDictionaryRecord(String id) throws LIMSRuntimeException {

        return getPreviousRecord(id, "Dictionary", Dictionary.class);
    }

    // this is for autocomplete
    // modified for 2062
    public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String filter, String categoryFilter)
                    throws LIMSRuntimeException {
        try {
            String sql = "";

            if (filter != null) {
                // bugzilla 1847: use dictEntryDisplayValue
                // bugzilla 2440 sql is incorrect - duplicate entries displaying
                // in dropdown
                if (!StringUtil.isNullorNill(categoryFilter)) {
                    sql = "from Dictionary d "
                                    + "where (d.localAbbreviation is not null and upper(d.localAbbreviation) || "
                                    + enquote(IActionConstants.LOCAL_CODE_DICT_ENTRY_SEPARATOR_STRING)
                                    + " || upper(d.dictEntry) like upper(:param1) and d.isActive= "
                                    + enquote(YES)
                                    + " and d.dictionaryCategory.categoryName = :param2)"
                                    + " OR (d.localAbbreviation is null and upper(d.dictEntry) like upper(:param1) and d.isActive= "
                                    + enquote(YES) + " and d.dictionaryCategory.categoryName = :param2)";
                } else {
                    sql = "from Dictionary d "
                                    + "where (d.localAbbreviation is not null and upper(d.localAbbreviation) || "
                                    + enquote(IActionConstants.LOCAL_CODE_DICT_ENTRY_SEPARATOR_STRING)
                                    + " || upper(d.dictEntry) like upper(:param1) and d.isActive= "
                                    + enquote(YES)
                                    + ")"
                                    + " OR (d.localAbbreviation is null and upper(d.dictEntry) like upper(:param1) and d.isActive= "
                                    + enquote(YES) + ")";
                }
            } else {
                if (!StringUtil.isNullorNill(categoryFilter)) {
                    sql = "from Dictionary d where d.dictEntry like :param1 and d.isActive= " + enquote(YES)
                                    + " and d.dictionaryCategory.categoryName = :param2";
                } else {
                    sql = "from Dictionary d where d.dictEntry like :param1 and d.isActive= " + enquote(YES);
                }
            }

            sql += " order by d.dictEntry asc";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param1", filter + "%");
            if (!StringUtil.isNullorNill(categoryFilter)) {
                query.setParameter("param2", categoryFilter);
            }

            @SuppressWarnings("unchecked")
            List<Dictionary> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            return list;

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getDictionaryEntrys()", e.toString());
            throw new LIMSRuntimeException(
                            "Error in Dictionary getDictionaryEntrys(String filter, String categoryFilter)", e);
        }
    }

    // bugzilla 2063 - this is when we know the category (local_abbrev) and we
    // need list of entries
    public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String categoryFilter) throws LIMSRuntimeException {
        return getDictionaryEntrysByCategoryAbbreviation("localAbbreviation", categoryFilter, true);
    }
    
    public List<Dictionary> getDictionaryEntrysByCategoryNameLocalizedSort(String categoryName) throws LIMSRuntimeException {
        List<Dictionary> entries = getDictionaryEntrysByCategoryAbbreviation("categoryName", categoryName, false);
        BaseObject.sortByLocalizedName(entries);
        return entries;
    }

    /**
     * @see DictionaryDAO#getDictionaryEntrysByCategoryAbbreviation(String, String, boolean)
     */
    public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String fieldName, String fieldValue, boolean orderByDictEntry)
                    throws LIMSRuntimeException {
        try {
            String sql = "from Dictionary d where d.isActive= " + enquote(YES);

            if (!StringUtil.isNullorNill(fieldValue)) {
                sql += " and d.dictionaryCategory." + fieldName + " = :param1";
            }

            if (orderByDictEntry) {
                sql += " order by d.dictEntry asc";
            } else {
                sql += " order by d.sortOrder asc";
            }
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param1", fieldValue);

            @SuppressWarnings("unchecked")
            List<Dictionary> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            return list;

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getDictionaryEntrysByCategoryAbbreviation()", e.toString());
            throw new LIMSRuntimeException("Error in Dictionary getDictionaryEntrysByCategoryAbbreviation(String categoryFilter)",
                            e);
        }
    }

    // bugzilla 1411
    public Integer getTotalDictionaryCount() throws LIMSRuntimeException {
        return getTotalCount("Dictionary", Dictionary.class);
    }

    // bugzilla 1427
    public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
        int currentId = (Integer.valueOf(id)).intValue();
        String tablePrefix = getTablePrefix(table);

        List list = new Vector();
        // bugzilla 1908
        int rrn = 0;
        try {
            // bugzilla 1908 cannot use named query for postgres because of
            // oracle ROWNUM
            // instead get the list in this sortorder and determine the index of
            // record with id = currentId
            String sql = "select d.id from Dictionary d" + " order by d.dictionaryCategory.categoryName, d.dictEntry";
            Query query = HibernateUtil.getSession().createQuery(sql);
            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            rrn = list.indexOf(String.valueOf(currentId));

            list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
                            .setMaxResults(2).list();

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getNextRecord()", e.toString());
            throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
        }

        return list;
    }

    // bugzilla 1427
    public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
        int currentId = (Integer.valueOf(id)).intValue();
        String tablePrefix = getTablePrefix(table);

        List list = new Vector();
        int rrn = 0;
        try {
            // bugzilla 1908 cannot use named query for postgres because of
            // oracle ROWNUM
            // instead get the list in this sortorder and determine the index of
            // record with id = currentId
            String sql = "select d.id from Dictionary d"
                            + " order by d.dictionaryCategory.categoryName desc, d.dictEntry desc";
            Query query = HibernateUtil.getSession().createQuery(sql);
            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            rrn = list.indexOf(String.valueOf(currentId));

            list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
                            .setMaxResults(2).list();

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getPreviousRecord()", e.toString());
            throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
        }

        return list;
    }

    /*
     * note 1: The case of no category will throw a conversion error for
     * postgres note 2: The error message claims that there is a duplicate
     * entry, it can also be a duplicate abbreviation in the same category
     */
    private boolean duplicateDictionaryExists(Dictionary dictionary) throws LIMSRuntimeException {
        try {

            List list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates
            // description within category is unique AND local abbreviation
            // within category is unique
            String sql = null;
            if (dictionary.getDictionaryCategory() != null) {
                sql = "from Dictionary t where "
                                + "((trim(lower(t.dictEntry)) = :param and trim(lower(t.dictionaryCategory.categoryName)) = :param2 and t.id != :dictId) "
                                + "or "
                                + "(trim(lower(t.localAbbreviation)) = :param4 and trim(lower(t.dictionaryCategory.categoryName)) = :param2 and t.id != :dictId)) ";

            } else {
                sql = "from Dictionary t where "
                                + "((trim(lower(t.dictEntry)) = :param and t.dictionaryCategory is null and t.id != :param3) "
                                + "or "
                                + "(trim(lower(t.localAbbreviation)) = :param4 and t.dictionaryCategory is null and t.id != :param3)) ";

            }
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param", dictionary.getDictEntry().toLowerCase().trim());
            query.setParameter("param4", dictionary.getLocalAbbreviation().toLowerCase().trim());
            if (dictionary.getDictionaryCategory() != null) {
                query.setParameter("param2", dictionary.getDictionaryCategory().getCategoryName().toLowerCase().trim());
            }

            // initialize with 0 (for new records where no id has been generated
            // yet
            String dictId = "0";
            if (!StringUtil.isNullorNill(dictionary.getId())) {
                dictId = dictionary.getId();
            }
            query.setInteger("dictId", Integer.parseInt(dictId));

            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "duplicateDictionaryExists()", e.toString());
            throw new LIMSRuntimeException("Error in duplicateDictionaryExists()", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Dictionary getDictionaryByLocalAbbrev(Dictionary dictionary) throws LIMSRuntimeException {
        try {
            String sql = null;
            Dictionary d = null;

            sql = "from Dictionary d where (d.localAbbreviation = :param) and d.isActive= " + enquote(YES);

            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param", dictionary.getLocalAbbreviation());

            List list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            if (list.size() > 0)
                d = (Dictionary) list.get(0);

            return d;

        } catch (Exception e) {
            // bugzilla 2154
            LogEvent.logError("DictionaryDAOImpl", "getDictionaryByDictEntry()", e.toString());
            throw new LIMSRuntimeException("Error in Test getDictionaryByDictEntry()", e);
        }
    }

    public Dictionary getDictionaryByDictEntry(Dictionary dictionary, boolean ignoreCase) throws LIMSRuntimeException {
        try {
            String sql = null;
            Dictionary d = null;

            if (ignoreCase) {
                sql = "from Dictionary d where "
                                + "((trim(lower(d.dictEntry)) = :param and d.localAbbreviation is null) OR (trim(lower(d.localAbbreviation)) || "
                                + enquote(IActionConstants.LOCAL_CODE_DICT_ENTRY_SEPARATOR_STRING)
                                + " || trim(lower(d.dictEntry)) = :param and d.localAbbreviation is not null))"
                                + " and d.isActive= " + enquote(YES);

            } else {
                sql = "from Dictionary d where "
                                + "((trim(d.dictEntry) = :param and d.localAbbreviation is null) OR (trim(d.localAbbreviation) = || "
                                + enquote(IActionConstants.LOCAL_CODE_DICT_ENTRY_SEPARATOR_STRING)
                                + " || trim(d.dictEntry) = :param and d.localAbbreviation is not null))"
                                + " and d.isActive= " + enquote(YES);
            }
            Query query = HibernateUtil.getSession().createQuery(sql);

            if (ignoreCase) {
                query.setParameter("param", dictionary.getDictEntry().toLowerCase().trim());
            } else {
                query.setParameter("param", dictionary.getDictEntry().trim());
            }

            List list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            if (list.size() > 0)
                d = (Dictionary) list.get(0);

            return d;

        } catch (Exception e) {
            LogEvent.logError("DictionaryDAOImpl", "getDictionaryByDictEntry()", e.toString());
            throw new LIMSRuntimeException("Error in DictionaryDAOImpl getDictionaryByDictEntry()", e);
        }
    }
    // bugzilla 2061-2063
    private boolean isDictionaryFrozen(Dictionary dictionary) throws LIMSRuntimeException {
        try {
            List list = new ArrayList();

            Dictionary oldData = (Dictionary) readDictionary(dictionary.getId());

            String sql = "";
            // TODO: when we add other tables that reference dictionary we need
            // to check those here
            // also
            // check references from other tables depending on dictionary
            // category local abbrev code
            if (dictionary.getDictionaryCategory().getLocalAbbreviation().equals(
                            SystemConfiguration.getInstance().getQaEventDictionaryCategoryType())) {
                sql = "from QaEvent q where q.type = :param";
                // bugzilla 2221: at this time there are only 2 categories as
                // far as this isFrozen() logic:
                // either referenced in QA_EVENT.TYPE OR in TEST_RESULT.VALUE
            } else {
                sql = "from TestResult tr where tr.value = :param and tr.test.isActive = " + enquote(YES);
            }

            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param", dictionary.getId());

            list = query.list();

            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            LogEvent.logError("DictionaryDAOImpl", "dictionaryIsInUse()", e.toString());
            throw new LIMSRuntimeException("Error in dictionaryIsInUse()", e);
        }
    }


    public Integer getTotalSearchedDictionaryCount(String searchString) throws LIMSRuntimeException {

        String wildCard = "*";
        String newSearchStr;
        String sql;
        Integer count = null;

        try {

            int wCdPosition = searchString.indexOf(wildCard);

            if (wCdPosition == -1) // no wild card looking for exact match
            {
                newSearchStr = searchString.toLowerCase().trim();
                sql = "select count (*) from Dictionary d where trim(lower (d.dictEntry)) = :param ";
            } else {
                newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
                sql = "select count (*) from Dictionary d where trim(lower (d.dictEntry)) like :param ";
            }
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setParameter("param", newSearchStr);

            List results = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            if (results != null && results.get(0) != null) {
                if (results.get(0) != null) {
                    count = (Integer) results.get(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new LIMSRuntimeException("Error in Dictionary getTotalSearchedDictionarys()", e);
        }

        return count;

    }


	@SuppressWarnings("unchecked")
	public List<Dictionary> getDictionaryEntriesByCategoryId(String categoryId) throws LIMSRuntimeException {
		String sql = "From Dictionary d where d.dictionaryCategory.id = :categoryId";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("categoryId", Integer.parseInt(categoryId));
			List<Dictionary>  queryResults = query.list();

			closeSession();

			return queryResults;
		}catch(Exception e){
			handleException(e, "getDictonaryEntriesByCategoryId");
		}

		return null;
	}

	public Dictionary getDictionaryById(String dictionaryId) throws LIMSRuntimeException {
		 try {
	            Dictionary dictionary = (Dictionary) HibernateUtil.getSession().get(Dictionary.class, dictionaryId);
	            closeSession();
	            return dictionary;
	        } catch (Exception e) {
	        	handleException(e, "getDictionaryById");
	        }

	        return null;
	}


	public Dictionary getDictionaryEntrysByNameAndCategoryDescription(String dictionaryName, String categoryDescription)
			throws LIMSRuntimeException {
		String sql = "From Dictionary d where d.dictEntry = :dictionaryEntry and d.dictionaryCategory.description = :description";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("dictionaryEntry", dictionaryName);
			query.setParameter("description", categoryDescription);

			Dictionary dictionary = (Dictionary)query.uniqueResult();

			closeSession();

			return dictionary;
		}catch(HibernateException e){
			handleException(e, "getDictionaryEntrysByNameAndCategoryDescription");
		}
		return null;
	}

	public Dictionary getDictionaryByDictEntry(String dictEntry) throws LIMSRuntimeException{
		String sql = "from Dictionary d where d.dictEntry = :dictionaryEntry";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("dictionaryEntry", dictEntry);
			Dictionary dictionary = (Dictionary)query.uniqueResult();
			closeSession();
			return dictionary;
		}catch(HibernateException e){
			handleException(e, "getDictioanryByDictEntry");
		}
		
		return null;
	}

	@Override
	public Dictionary getDataForId(String dictionaryId) throws LIMSRuntimeException {
		String sql = "from Dictionary d where d.id = :id";
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("id", Integer.parseInt(dictionaryId));
			Dictionary dictionary = (Dictionary)query.uniqueResult();
			closeSession();
			return dictionary;
			
		} catch (HibernateException e) {
			handleException(e, "getDataForId");
		}
		return null;
	}


}