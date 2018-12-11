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
package us.mn.state.health.lims.typeofsample.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * @author diane benz
 */
public class TypeOfSampleDAOImpl extends BaseDAOImpl implements TypeOfSampleDAO {

	private static Map<String, String> ID_NAME_MAP = null;

	@Override
	public void deleteData(List typeOfSamples) throws LIMSRuntimeException {
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < typeOfSamples.size(); i++) {
				TypeOfSample data = (TypeOfSample) typeOfSamples.get(i);

				TypeOfSample oldData = readTypeOfSample(data
						.getId());
				TypeOfSample newData = new TypeOfSample();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TYPE_OF_SAMPLE";
				auditDAO.saveHistory(newData, oldData, sysUserId, event,
						tableName);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < typeOfSamples.size(); i++) {
				TypeOfSample data = (TypeOfSample) typeOfSamples.get(i);
				//bugzilla 2206
				data = readTypeOfSample(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample deleteData()", e);
		}

		ID_NAME_MAP = null;
	}

	@Override
	public boolean insertData(TypeOfSample typeOfSample)
			throws LIMSRuntimeException {

		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateTypeOfSampleExists(typeOfSample)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ typeOfSample.getDescription());
			}

			String id = (String) HibernateUtil.getSession().save(typeOfSample);
			typeOfSample.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = typeOfSample.getSysUserId();
			String tableName = "TYPE_OF_SAMPLE";
			auditDAO.saveNewHistory(typeOfSample,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample insertData()", e);
		}

		ID_NAME_MAP = null;

		return true;
	}

	@Override
	public void updateData(TypeOfSample typeOfSample)
			throws LIMSRuntimeException {

		TypeOfSample oldData = readTypeOfSample(typeOfSample.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(typeOfSample, oldData, typeOfSample.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "TYPE_OF_SAMPLE");
		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(typeOfSample);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(typeOfSample);
			HibernateUtil.getSession().refresh(typeOfSample);
		} catch (Exception e) {

			LogEvent.logError("TypeOfSampleDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample updateData()", e);
		}

		ID_NAME_MAP = null;
	}

	@Override
	public void getData(TypeOfSample typeOfSample) throws LIMSRuntimeException {
		try {
			TypeOfSample tos = (TypeOfSample) HibernateUtil.getSession().get(
					TypeOfSample.class, typeOfSample.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tos != null) {
				PropertyUtils.copyProperties(typeOfSample, tos);
			} else {
				typeOfSample.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSample getData()", e);
		}
	}

	@Override
	public List getAllTypeOfSamples() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from TypeOfSample order by description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getAllTypeOfSamples()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample getAllTypeOfSamples()", e);
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TypeOfSample> getAllTypeOfSamplesSortOrdered() throws LIMSRuntimeException {
		List<TypeOfSample> list = new ArrayList<TypeOfSample>();
		try {
			String sql = "from TypeOfSample order by sort_order";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl","getAllTypeOfSamplesSortOrdered()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample getAllTypeOfSamplesSortOrdered()", e);
		}		
	
		return list;
	}

	@Override
	public List getPageOfTypeOfSamples(int startingRecNo)
			throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo
					+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			// bugzilla 1399
			String sql = "from TypeOfSample t order by t.domain, t.description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getPageOfTypeOfSamples()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample getPageOfTypeOfSamples()", e);
		}

		return list;
	}

	public TypeOfSample readTypeOfSample(String idString) {
		TypeOfSample tos = null;
		try {
			tos = (TypeOfSample) HibernateUtil.getSession().get(
					TypeOfSample.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","readTypeOfSample()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample readTypeOfSample()", e);
		}

		return tos;
	}

	// this is for autocomplete
	// bugzilla 1387 added domain parm
	@Override
	public List getTypes(String filter, String domain)
			throws LIMSRuntimeException {
		List list = new Vector();
		try {

			String sql = "";
			// bugzilla 1387 added domain parm
			if (!StringUtil.isNullorNill(domain)) {
				sql = "from TypeOfSample t where upper(t.description) like upper(:param) and t.domain = :param2 order by upper(t.description)";
			} else {
				sql = "from TypeOfSample t where upper(t.description) like upper(:param) order by upper(t.description)";

			}
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", filter + "%");
			// bugzilla 1387 added domain parm
			if (!StringUtil.isNullorNill(domain)) {
				query.setParameter("param2", domain);
			}

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getTypes()",e.toString());
			throw new LIMSRuntimeException(
					"Error in TypeOfSample getTypes(String filter)", e);
		}
		return list;

	}

	@Override
	public List getTypesForDomain(SampleDomain domain) throws LIMSRuntimeException {
		List list = new Vector();
		String key = getKeyForDomain(domain);

		try {

			String sql = "from TypeOfSample t where t.domain = :domainKey order by upper(t.description)";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

			query.setParameter("domainKey", key);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in TypeOfSample getTypes(String filter)", e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeOfSample> getTypesForDomainBySortOrder(SampleDomain domain) throws LIMSRuntimeException {
		List<TypeOfSample> list = null;
		String key = getKeyForDomain(domain);

		try {

			String sql = "from TypeOfSample t where t.domain = :domainKey order by t.sortOrder";

			Query query = HibernateUtil.getSession().createQuery(sql);

			query.setParameter("domainKey", key);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			handleException(e, "getTypesForDomainBySortOrder");
		}
		
		return list;
	
	}

    @Override
    public TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain( String localAbbrev, String domain ) throws LIMSRuntimeException{
        String sql = "From TypeOfSample tos where tos.localAbbreviation = :localAbbrev and tos.domain = :domain";
        try{
            Query query = HibernateUtil.getSession().createQuery( sql );
            query.setString( "localAbbrev", localAbbrev );
            query.setString( "domain", domain );
            TypeOfSample typeOfSample = (TypeOfSample)query.uniqueResult();
            closeSession();
            return typeOfSample;
        }catch( HibernateException he ){
            handleException( he, "getTypeOfSampeByLocalAbbreviationAndDomain" );
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

	@Override
	public List getNextTypeOfSampleRecord(String id)
			throws LIMSRuntimeException {

		return getNextRecord(id, "TypeOfSample", TypeOfSample.class);

	}

	@Override
	public List getPreviousTypeOfSampleRecord(String id)
			throws LIMSRuntimeException {

		return getPreviousRecord(id, "TypeOfSample", TypeOfSample.class);
	}

	// bugzilla 1411
	@Override
	public Integer getTotalTypeOfSampleCount() throws LIMSRuntimeException {
		return getTotalCount("TypeOfSample", TypeOfSample.class);
	}

	// bugzilla 1427
	@Override
	public List getNextRecord(String id, String table, Class clazz)
			throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select tos.id from TypeOfSample tos " +
					" order by tos.domain, tos.description";

  			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getNext").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for "
					+ table, e);
		}

		return list;
	}

	// bugzilla 1427
	@Override
	public List getPreviousRecord(String id, String table, Class clazz)
			throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		//bugzilla 1908
		int rrn = 0;
		try {
			//bugzilla 1908 cannot use named query for postgres because of oracle ROWNUM
			//instead get the list in this sortorder and determine the index of record with id = currentId
    		String sql = "select tos.id from TypeOfSample tos " +
					" order by tos.domain desc, tos.description desc";

  			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getPrevious").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for "
					+ table, e);
		}

		return list;
	}

	// bugzilla 1367 also handles NO domain (then all domains are retrieved)
	@Override
	public TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample tos,
			boolean ignoreCase) throws LIMSRuntimeException {
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
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);

			if (ignoreCase) {
				query.setParameter("param", tos.getDescription().toLowerCase()
						.trim());
			} else {
				query.setParameter("param", tos.getDescription().trim());
			}

			if (!StringUtil.isNullorNill(tos.getDomain())) {
				query.setParameter("param2", tos.getDomain());
			}

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			TypeOfSample typeOfSample = null;
			if (list.size() > 0)
				typeOfSample = (TypeOfSample) list.get(0);

			return typeOfSample;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfSampleDAOImpl","getTypeOfSampleByDescriptionAndDomain()",e.toString());
			throw new LIMSRuntimeException(
					"Error in Test getTypeOfSampleByDescriptionAndDomain()", e);
		}
	}

	// bugzilla 1482
	private boolean duplicateTypeOfSampleExists(TypeOfSample typeOfSample) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates

			// bugzilla 2432 add check for local abbreviation
			String sql = "from TypeOfSample t where (trim(lower(t.description)) = :description and trim(lower(t.domain)) = :domain and t.id != :id)" +
					" or (trim(lower(t.localAbbreviation)) = :abbrev and trim(lower(t.domain)) = :domain and t.id != :id)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("description", typeOfSample.getDescription().toLowerCase().trim());
			query.setParameter("domain", typeOfSample.getDomain().toLowerCase().trim());
			//bugzila 2432
			query.setParameter("abbrev", typeOfSample.getLocalAbbreviation().toLowerCase().trim());

			// initialize with 0 (for new records where no id has been generated
			// yet
			String typeOfSampleId = "0";
			if (!StringUtil.isNullorNill(typeOfSample.getId())) {
				typeOfSampleId = typeOfSample.getId();
			}
			query.setInteger("id", Integer.parseInt(typeOfSampleId));

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
			LogEvent.logError("TypeOfSampleDAOImpl","duplicateTypeOfSampleExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateTypeOfSampleExists()", e);
		}
	}

	@Override
	public String getNameForTypeOfSampleId(String id) {
		if( ID_NAME_MAP == null){
			List allTypes = getAllTypeOfSamples();

			if( allTypes != null){
				ID_NAME_MAP = new HashMap<String, String>();

				for( Object typeOfSample : allTypes){
					TypeOfSample sample = (TypeOfSample)typeOfSample;

					ID_NAME_MAP.put( sample.getId(), sample.getDescription());
				}
			}
		}

		return ID_NAME_MAP != null ? ID_NAME_MAP.get(id) : id;
	}

	@Override
	public TypeOfSample getTypeOfSampleById(String typeOfSampleId) throws LIMSRuntimeException {
		try {
			TypeOfSample tos = (TypeOfSample) HibernateUtil.getSession().get( TypeOfSample.class, typeOfSampleId);
			closeSession();
			return tos;
		} catch (Exception e) {
			handleException(e, "getTypeOfSampleById");
		}

		return null;
	}
	
	@Override
	public TypeOfSample getSampleTypeFromTest(Test test) {
		String sql = "select tos from TypeOfSample tos, Test test, TypeOfSampleTest tost "
				+ "where tost.testId = :id "
				+ "AND tos.id = tost.typeOfSampleId";
		try {
    		Query query = HibernateUtil.getSession().createQuery(sql);
    		query.setInteger("id", Integer.parseInt(test.getId()));
    		
    		TypeOfSample tos = (TypeOfSample) query.uniqueResult();
    		closeSession();
    		return tos;
		} catch (Exception e) {
			handleException(e, "getSampleTypeFromTest");
		}
		return null;
	}

}