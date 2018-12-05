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
package us.mn.state.health.lims.typeoftestresult.daoimpl;


import java.util.List;

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
import us.mn.state.health.lims.typeoftestresult.dao.TypeOfTestResultDAO;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

/**
 * @author diane benz
 */
public class TypeOfTestResultDAOImpl extends BaseDAOImpl implements TypeOfTestResultDAO {

	public void deleteData(List typeOfTestResults) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < typeOfTestResults.size(); i++) {
				TypeOfTestResult data = (TypeOfTestResult)typeOfTestResults.get(i);
			
				TypeOfTestResult oldData = readTypeOfTestResult(data.getId());
				TypeOfTestResult newData = new TypeOfTestResult();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "TYPE_OF_TEST_RESULT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			LogEvent.logError("TypeOfTestResultDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult AuditTrail deleteData()", e);
		}  
		
		try {		
			for (int i = 0; i < typeOfTestResults.size(); i++) {
				TypeOfTestResult data = (TypeOfTestResult) typeOfTestResults.get(i);

				data = readTypeOfTestResult(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();			
			}			
		} catch (Exception e) {
			LogEvent.logError("TypeOfTestResultDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult deleteData()", e);
		}
	}

	public boolean insertData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateTypeOfTestResultExists(typeOfTestResult)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ typeOfTestResult.getDescription());
			}
			
			String id = (String)HibernateUtil.getSession().save(typeOfTestResult);
			typeOfTestResult.setId(id);
			
			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = typeOfTestResult.getSysUserId();
			String tableName = "TYPE_OF_TEST_RESULT";
			auditDAO.saveNewHistory(typeOfTestResult,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();						
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult insertData()", e);
		}
		
		return true;
	}

	public void updateData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if record already exists
		try {
			if (duplicateTypeOfTestResultExists(typeOfTestResult)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ typeOfTestResult.getDescription());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult updateData()",
					e);
		}
		
		TypeOfTestResult oldData = readTypeOfTestResult(typeOfTestResult.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = typeOfTestResult.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "TYPE_OF_TEST_RESULT";
			auditDAO.saveHistory(typeOfTestResult,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("TypeOfTestResultDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult AuditTrail updateData()", e);
		}  
				
		try {
			HibernateUtil.getSession().merge(typeOfTestResult);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(typeOfTestResult);
			HibernateUtil.getSession().refresh(typeOfTestResult);			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult updateData()", e);
		}
	}

	public void getData(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
		try {
			TypeOfTestResult sc = (TypeOfTestResult)HibernateUtil.getSession().get(TypeOfTestResult.class, typeOfTestResult.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sc != null) {
			  PropertyUtils.copyProperties(typeOfTestResult, sc);
			} else {
				typeOfTestResult.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult getData()", e);
		}
	}

	public List getAllTypeOfTestResults() throws LIMSRuntimeException {
		List list;
		try {
			String sql = "from TypeOfTestResult";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);				
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","getAllTypeOfTestResults()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult getAllTypeOfTestResults()", e);
		}

		return list;
	}

	public List getPageOfTypeOfTestResults(int startingRecNo) throws LIMSRuntimeException {
		List list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from TypeOfTestResult t order by t.description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 
					
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","getPageOfTypeOfTestResults()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult getPageOfTypeOfTestResults()", e);
		}

		return list;
	}

	public TypeOfTestResult readTypeOfTestResult(String idString) {
		TypeOfTestResult data;
		try {
			data = (TypeOfTestResult)HibernateUtil.getSession().get(TypeOfTestResult.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","readTypeOfTestResult()",e.toString());
			throw new LIMSRuntimeException("Error in TypeOfTestResult readTypeOfTestResult()", e);
		}			
		
		return data;
	}

	public List getNextTypeOfTestResultRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TypeOfTestResult", TypeOfTestResult.class);

	}

	public List getPreviousTypeOfTestResultRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TypeOfTestResult", TypeOfTestResult.class);
	}

	//bugzilla 1411
	public Integer getTotalTypeOfTestResultCount() throws LIMSRuntimeException {
		return getTotalCount("TypeOfTestResult", TypeOfTestResult.class);
	}
	
	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
				
		List list;
		try {			
			String sql = "from "+table+" t where description >= "+ enquote(id) + " order by t.description";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();		
			
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}
		
		return list;		
	}

	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		
		List list;
		try {			
			String sql = "from "+table+" t order by t.description desc where description <= "+ enquote(id);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2); 	
			
			list = query.list();					
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	// bugzilla 1482
	private boolean duplicateTypeOfTestResultExists(TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
		try {

			List list;

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from TypeOfTestResult t where (trim(lower(t.description)) = :param and t.id != :param2) or (trim(lower(t.testResultType)) = :param3 and t.id != :param2)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", typeOfTestResult.getDescription().toLowerCase().trim());
			query.setParameter("param3", typeOfTestResult.getTestResultType().toLowerCase().trim());
	
			// initialize with 0 (for new records where no id has been generated
			// yet
			String typeOfTestResultId = "0";
			if (!StringUtil.isNullorNill(typeOfTestResult.getId())) {
				typeOfTestResultId = typeOfTestResult.getId();
			}
			query.setParameter("param2", typeOfTestResultId);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list.size() > 0;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("TypeOfTestResultDAOImpl","duplicateTypeOfTestResultExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateTypeOfTestResultExists()", e);
		}
	}
	
	//bugzilla 1866 to get HL7 value
	public TypeOfTestResult getTypeOfTestResultByType(
			TypeOfTestResult typeOfTestResult) throws LIMSRuntimeException {
		TypeOfTestResult totr = null;
		try {
			String sql = "from TypeOfTestResult totr where upper(totr.testResultType) = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", typeOfTestResult.getTestResultType().trim()
					.toUpperCase());
	
			List list = query.list();

			if (list != null && list.size() > 0)
				totr = (TypeOfTestResult) list.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("TypeOfTestResultDAOImpl","getTypeOfTestResultByType()",e.toString());
			throw new LIMSRuntimeException(	"Error in getTypeOfTestResultByType()", e);
		}

		return totr;
	}

    @Override
    public TypeOfTestResult getTypeOfTestResultByType( String type ) throws LIMSRuntimeException{
        String sql ="from TypeOfTestResult ttr where ttr.testResultType = :type";
        try{
            Query query = HibernateUtil.getSession().createQuery( sql );
            query.setString( "type", type );
            TypeOfTestResult typeOfTestResult = (TypeOfTestResult)query.uniqueResult();
            closeSession();
            return typeOfTestResult;
        }catch( HibernateException e ){
            handleException( e, "getTypeOfTestResultByType" );
        }

        return null;
    }
}