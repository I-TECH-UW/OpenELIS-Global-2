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
package org.openelisglobal.analyte.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.analyte.dao.AnalyteDAO;
import org.openelisglobal.analyte.valueholder.Analyte;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class AnalyteDAOImpl extends BaseDAOImpl<Analyte, String> implements AnalyteDAO {

    public AnalyteDAOImpl() {
        super(Analyte.class);
    }

    @Override
    public void delete(Analyte analyte) {
        LogEvent.logInfo(this.getClass().getName(), "method unkown", "selete dao");
    }

//	@Override
//	public void deleteData(List analytes) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < analytes.size(); i++) {
//				Analyte data = (Analyte) analytes.get(i);
//
//				Analyte oldData = readAnalyte(data.getId());
//				Analyte newData = new Analyte();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "ANALYTE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < analytes.size(); i++) {
//				Analyte data = (Analyte) analytes.get(i);
//				Analyte cloneData = readAnalyte(data.getId());
//
//				cloneData.setIsActive(IActionConstants.NO);
//				entityManager.unwrap(Session.class).merge(cloneData);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(cloneData);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(cloneData);
//			}
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Analyte analyte) throws LIMSRuntimeException {
//
//		try {
//
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateAnalyteExists(analyte)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + analyte.getAnalyteName());
//			}
//			String id = (String) entityManager.unwrap(Session.class).save(analyte);
//			analyte.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = analyte.getSysUserId();
//			String tableName = "ANALYTE";
//			auditDAO.saveNewHistory(analyte, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public Analyte save(Analyte analyte) {
//		return analyte;
//	}

//	@Override
//	public void updateData(Analyte analyte) throws LIMSRuntimeException {
//		// bugzilla 1482 throw Exception if record already exists
//		try {
//			if (duplicateAnalyteExists(analyte)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + analyte.getAnalyteName());
//			}
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte updateData()", e);
//		}
//		Analyte oldData = readAnalyte(analyte.getId());
//		Analyte newData = analyte;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = analyte.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "ANALYTE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(analyte);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(analyte);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(analyte);
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte updateData()", e);
//		}
//	}

//	@Override
//	public void getData(Analyte analyte) throws LIMSRuntimeException {
//		try {
//			Analyte anal = entityManager.unwrap(Session.class).get(Analyte.class, analyte.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (anal != null) {
//				PropertyUtils.copyProperties(analyte, anal);
//			} else {
//				analyte.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte getData()", e);
//		}
//	}

//	@Override
//	public List getAllAnalytes() throws LIMSRuntimeException {
//		List list ;
//		try {
//			String sql = "from Analyte";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getAllAnalytes()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte getAllAnalytes()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfAnalytes(int startingRecNo) throws LIMSRuntimeException {
//		List list ;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			// bugzilla 1399
//			String sql = "from Analyte a order by a.analyteName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getPageOfAnalytes()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte getPageOfAnalytes()", e);
//		}
//
//		return list;
//	}

    // bugzilla 2370
//	@Override
//	public List getPagesOfSearchedAnalytes(int startingRecNo, String searchString) throws LIMSRuntimeException {
//		List list ;
//		String wildCard = "*";
//		String newSearchStr;
//		String sql;
//
//		try {
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//			int wCdPosition = searchString.indexOf(wildCard);
//
//			if (wCdPosition == -1) // no wild card looking for exact match
//			{
//				newSearchStr = searchString.toLowerCase().trim();
//				sql = "from Analyte a where trim(lower (a.analyteName)) = :param  order by a.analyteName ";
//			} else {
//				newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
//				sql = "from Analyte a where trim(lower (a.analyteName)) like :param  order by a.analyteName";
//			}
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", newSearchStr);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in AnalyteDAOImpl getPagesOfSearchedAnalytes()", e);
//		}
//
//		return list;
//	}
    // end bugzilla 2370

//	public Analyte readAnalyte(String idString) {
//		Analyte analyte = null;
//		try {
//			analyte = entityManager.unwrap(Session.class).get(Analyte.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "readAnalyte()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte readAnalyte()", e);
//		}
//
//		return analyte;
//	}

    // this is for autocomplete
//	@Override
//	public List getAnalytes(String filter) throws LIMSRuntimeException {
//		List list ;
//		try {
//			String sql = "from Analyte a where upper(a.analyteName) like upper(:param) and a.isActive='Y' order by upper(a.analyteName)";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", filter + "%");
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getAnalytes()", e.toString());
//			throw new LIMSRuntimeException("Error in Analyte getAnalytes(String filter)", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getNextAnalyteRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "Analyte", Analyte.class);
//
//	}

//	@Override
//	public List getPreviousAnalyteRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "Analyte", Analyte.class);
//	}

    // bugzilla 1367 added ignoreCase
    @Override
    @Transactional(readOnly = true)
    public Analyte getAnalyteByName(Analyte analyte, boolean ignoreCase) throws LIMSRuntimeException {
        try {

            String sql = null;
            if (ignoreCase) {
                sql = "from Analyte a where trim(lower(a.analyteName)) = :param and a.isActive='Y'";

            } else {
                sql = "from Analyte a where a.analyteName = :param and a.isActive='Y'";
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            if (ignoreCase) {
                query.setString("param", analyte.getAnalyteName().trim().toLowerCase());
            } else {
                query.setString("param", analyte.getAnalyteName());
            }

            List<Analyte> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            Analyte ana = null;
            if (list.size() > 0) {
                ana = list.get(0);
            }

            return ana;

        } catch (RuntimeException e) {
            // buzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Analyte getAnalyteByName()", e);
        }
    }

    // bugzilla 1411
//	@Override
//	public Integer getTotalAnalyteCount() throws LIMSRuntimeException {
//		return getCount();
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list ;
//		try {
//			String sql = "from " + table + " t where name >= " + enquote(id) + " order by t.analyteName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // overriding BaseDAOImpl bugzilla 1427 pass in name not id
//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//
//		List list ;
//		try {
//			String sql = "from " + table + " t order by t.analyteName desc where name <= " + enquote(id);
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(1);
//			query.setMaxResults(2);
//
//			list = query.list();
//		} catch (RuntimeException e) {
//			// buzilla 2154
//			LogEvent.logError("AnalyteDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    // bugzilla 1482
    @Override
    public boolean duplicateAnalyteExists(Analyte analyte) {
        try {

            List<Analyte> list = new ArrayList<>();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates

            // bugzilla 2432 add check for local abbreviation
            String sql = "";
            if (analyte.getLocalAbbreviation() != null) {
                sql = "from Analyte a where (trim(lower(a.analyteName)) = :name and a.id != :id)"
                        + " or (trim(lower(a.localAbbreviation)) = :abbreviation and a.id != :id)";
            } else {
                sql = "from Analyte a where trim(lower(a.analyteName)) = :name and a.id != :id";
            }

            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("name", analyte.getAnalyteName().toLowerCase().trim());
            // bugzilla 2432
            if (analyte.getLocalAbbreviation() != null) {
                query.setString("abbreviation", analyte.getLocalAbbreviation().toLowerCase().trim());
            }

            String analyteId = !StringUtil.isNullorNill(analyte.getId()) ? analyte.getId() : "0";

            query.setInteger("id", Integer.parseInt(analyteId));

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;
        } catch (RuntimeException e) {
            // buzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateAnalyteExists()", e);
        }
    }

    // bugzilla 2370 get total searched results
//	@Override
//	public Integer getTotalSearchedAnalyteCount(String searchString) throws LIMSRuntimeException {
//
//		String wildCard = "*";
//		String newSearchStr;
//		String sql;
//		Integer count = null;
//
//		try {
//
//			int wCdPosition = searchString.indexOf(wildCard);
//
//			if (wCdPosition == -1) // no wild card looking for exact match
//			{
//				newSearchStr = searchString.toLowerCase().trim();
//				sql = "select count (*) from Analyte a where trim(lower (a.analyteName)) = :param ";
//			} else {
//				newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
//				sql = "select count (*) from Analyte a where trim(lower (a.analyteName)) like :param ";
//			}
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", newSearchStr);
//
//			List results = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			if (results != null && results.get(0) != null) {
//				if (results.get(0) != null) {
//					count = ((Long) results.get(0)).intValue();
//				}
//			}
//
//		} catch (RuntimeException e) {
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in AnalyteDAOImpl getTotalSearchedAnalyteCount()", e);
//		}
//
//		return count;
//
//	}
    // end bugzilla 2370

}