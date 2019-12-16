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
package org.openelisglobal.typeofsample.daoimpl;

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
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.typeofsample.dao.TypeOfSampleDAO;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class TypeOfSampleDAOImpl extends BaseDAOImpl<TypeOfSample, String> implements TypeOfSampleDAO {

    public TypeOfSampleDAOImpl() {
        super(TypeOfSample.class);
    }

    private static Map<String, String> ID_NAME_MAP = null;

    @Override
    public void clearMap() {
        ID_NAME_MAP = null;
    }

//	@Override
//	public void deleteData(List typeOfSamples) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < typeOfSamples.size(); i++) {
//				TypeOfSample data = (TypeOfSample) typeOfSamples.get(i);
//
//				TypeOfSample oldData = readTypeOfSample(data.getId());
//				TypeOfSample newData = new TypeOfSample();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "TYPE_OF_SAMPLE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfSampleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSample AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < typeOfSamples.size(); i++) {
//				TypeOfSample data = (TypeOfSample) typeOfSamples.get(i);
//				// bugzilla 2206
//				data = readTypeOfSample(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfSampleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSample deleteData()", e);
//		}
//
//		clearMap();
//	}

//	@Override
//	public boolean insertData(TypeOfSample typeOfSample) throws LIMSRuntimeException {
//
//		try {
//			// bugzilla 1482 throw Exception if record already exists
//			if (duplicateTypeOfSampleExists(typeOfSample)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + typeOfSample.getDescription());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(typeOfSample);
//			typeOfSample.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = typeOfSample.getSysUserId();
//			String tableName = "TYPE_OF_SAMPLE";
//			auditDAO.saveNewHistory(typeOfSample, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("TypeOfSampleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSample insertData()", e);
//		}
//
//		clearMap();
//
//		return true;
//	}

//	@Override
//	public void updateData(TypeOfSample typeOfSample) throws LIMSRuntimeException {
//
//		TypeOfSample oldData = readTypeOfSample(typeOfSample.getId());
//
//		try {
//
//			auditDAO.saveHistory(typeOfSample, oldData, typeOfSample.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "TYPE_OF_SAMPLE");
//		} catch (RuntimeException e) {
//			LogEvent.logError("TypeOfSampleDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSample AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(typeOfSample);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(typeOfSample);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(typeOfSample);
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("TypeOfSampleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in TypeOfSample updateData()", e);
//		}
//
//		clearMap();
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(TypeOfSample typeOfSample) throws LIMSRuntimeException {
        try {
            TypeOfSample tos = entityManager.unwrap(Session.class).get(TypeOfSample.class, typeOfSample.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (tos != null) {
                PropertyUtils.copyProperties(typeOfSample, tos);
            } else {
                typeOfSample.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSample> getAllTypeOfSamples() throws LIMSRuntimeException {
        List<TypeOfSample> list;
        try {
            String sql = "from TypeOfSample order by description";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            // query.setMaxResults(10);
            // query.setFirstResult(3);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample getAllTypeOfSamples()", e);
        }
        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<TypeOfSample> getAllTypeOfSamplesSortOrdered() throws LIMSRuntimeException {
        List<TypeOfSample> list = new ArrayList<>();
        try {
            String sql = "from TypeOfSample order by sort_order";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample getAllTypeOfSamplesSortOrdered()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSample> getPageOfTypeOfSamples(int startingRecNo) throws LIMSRuntimeException {
        List<TypeOfSample> list;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            // bugzilla 1399
            String sql = "from TypeOfSample t order by t.domain, t.description";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample getPageOfTypeOfSamples()", e);
        }

        return list;
    }

    public TypeOfSample readTypeOfSample(String idString) {
        TypeOfSample tos = null;
        try {
            tos = entityManager.unwrap(Session.class).get(TypeOfSample.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample readTypeOfSample()", e);
        }

        return tos;
    }

    // this is for autocomplete
    // bugzilla 1387 added domain parm
    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSample> getTypes(String filter, String domain) throws LIMSRuntimeException {
        List<TypeOfSample> list;
        try {

            String sql = "";
            // bugzilla 1387 added domain parm
            if (!StringUtil.isNullorNill(domain)) {
                sql = "from TypeOfSample t where upper(t.description) like upper(:param) and t.domain = :param2 order by upper(t.description)";
            } else {
                sql = "from TypeOfSample t where upper(t.description) like upper(:param) order by upper(t.description)";

            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", filter + "%");
            // bugzilla 1387 added domain parm
            if (!StringUtil.isNullorNill(domain)) {
                query.setParameter("param2", domain);
            }

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in TypeOfSample getTypes(String filter)", e);
        }
        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSample> getTypesForDomain(SampleDomain domain) throws LIMSRuntimeException {
        List<TypeOfSample> list;
        String key = getKeyForDomain(domain);

        try {

            String sql = "from TypeOfSample t where t.domain = :domainKey order by upper(t.description)";

            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setParameter("domainKey", key);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in TypeOfSample getTypes(String filter)", e);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSample> getTypesForDomainBySortOrder(SampleDomain domain) throws LIMSRuntimeException {
        List<TypeOfSample> list = null;
        String key = getKeyForDomain(domain);

        try {

            String sql = "from TypeOfSample t where t.domain = :domainKey order by t.sortOrder";

            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setParameter("domainKey", key);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "getTypesForDomainBySortOrder");
        }

        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain(String localAbbrev, String domain)
            throws LIMSRuntimeException {
        String sql = "From TypeOfSample tos where tos.localAbbreviation = :localAbbrev and tos.domain = :domain";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("localAbbrev", localAbbrev);
            query.setString("domain", domain);
            TypeOfSample typeOfSample = (TypeOfSample) query.uniqueResult();
            // closeSession(); // CSL remove old
            return typeOfSample;
        } catch (HibernateException e) {
            handleException(e, "getTypeOfSampeByLocalAbbreviationAndDomain");
        }
        return null;
    }

    private String getKeyForDomain(SampleDomain domain) {
        String domainKey = "H";
        switch (domain) {
        case ANIMAL: {
            domainKey = "A";
            break;
        }
        case ENVIRONMENTAL: {
            domainKey = "E";
            break;
        }
        case HUMAN: {
            domainKey = "H";
            break;
        }
        default: {
            domainKey = "H";
        }

        }

        return domainKey;
    }

    // bugzilla 1411
    @Override
    @Transactional(readOnly = true)
    public Integer getTotalTypeOfSampleCount() throws LIMSRuntimeException {
        return getCount();
    }

    // bugzilla 1367 also handles NO domain (then all domains are retrieved)
    @Override
    @Transactional(readOnly = true)
    public TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample tos, boolean ignoreCase)
            throws LIMSRuntimeException {
        try {
            String sql = null;

            if (!StringUtil.isNullorNill(tos.getDomain())) {
                if (ignoreCase) {
                    sql = "from TypeOfSample tos where trim(lower(tos.description)) = :param and tos.domain = :param2";
                } else {
                    sql = "from TypeOfSample tos where trim(tos.description) = :param and tos.domain = :param2";
                }
            } else {
                if (ignoreCase) {
                    sql = "from TypeOfSample tos where trim(lower(tos.description)) = :param";
                } else {
                    sql = "from TypeOfSample tos where trim(tos.description) = :param";
                }
            }
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            if (ignoreCase) {
                query.setParameter("param", tos.getDescription().toLowerCase().trim());
            } else {
                query.setParameter("param", tos.getDescription().trim());
            }

            if (!StringUtil.isNullorNill(tos.getDomain())) {
                query.setParameter("param2", tos.getDomain());
            }

            List<TypeOfSample> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            TypeOfSample typeOfSample = null;
            if (list.size() > 0) {
                typeOfSample = list.get(0);
            }

            return typeOfSample;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Test getTypeOfSampleByDescriptionAndDomain()", e);
        }
    }

    // bugzilla 1482
    @Override
    public boolean duplicateTypeOfSampleExists(TypeOfSample typeOfSample) throws LIMSRuntimeException {
        try {

            List<TypeOfSample> list = new ArrayList();

            // not case sensitive hemolysis and Hemolysis are considered
            // duplicates

            // bugzilla 2432 add check for local abbreviation
            String sql = "from TypeOfSample t where (trim(lower(t.description)) = :description and trim(lower(t.domain)) = :domain and t.id != :id)"
                    + " or (trim(lower(t.localAbbreviation)) = :abbrev and trim(lower(t.domain)) = :domain and t.id != :id)";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("description", typeOfSample.getDescription().toLowerCase().trim());
            query.setParameter("domain", typeOfSample.getDomain().toLowerCase().trim());
            // bugzila 2432
            query.setParameter("abbrev", typeOfSample.getLocalAbbreviation().toLowerCase().trim());

            // initialize with 0 (for new records where no id has been generated
            // yet
            String typeOfSampleId = "0";
            if (!StringUtil.isNullorNill(typeOfSample.getId())) {
                typeOfSampleId = typeOfSample.getId();
            }
            query.setInteger("id", Integer.parseInt(typeOfSampleId));

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
            throw new LIMSRuntimeException("Error in duplicateTypeOfSampleExists()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String getNameForTypeOfSampleId(String id) {
        if (ID_NAME_MAP == null) {
            List<TypeOfSample> allTypes = getAllTypeOfSamples();

            if (allTypes != null) {
                ID_NAME_MAP = new HashMap<>();

                for (Object typeOfSample : allTypes) {
                    TypeOfSample sample = (TypeOfSample) typeOfSample;

                    ID_NAME_MAP.put(sample.getId(), sample.getDescription());
                }
            }
        }

        return ID_NAME_MAP != null ? ID_NAME_MAP.get(id) : id;
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfSample getTypeOfSampleById(String typeOfSampleId) throws LIMSRuntimeException {
        try {
            TypeOfSample tos = entityManager.unwrap(Session.class).get(TypeOfSample.class, typeOfSampleId);
            // closeSession(); // CSL remove old
            return tos;
        } catch (RuntimeException e) {
            handleException(e, "getTypeOfSampleById");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfSample getSampleTypeFromTest(Test test) {
        String sql = "select tos from TypeOfSample tos, Test test, TypeOfSampleTest tost " + "where tost.testId = :id "
                + "AND tos.id = tost.typeOfSampleId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("id", Integer.parseInt(test.getId()));

            TypeOfSample tos = (TypeOfSample) query.uniqueResult();
            // closeSession(); // CSL remove old
            return tos;
        } catch (RuntimeException e) {
            handleException(e, "getSampleTypeFromTest");
        }
        return null;
    }

}