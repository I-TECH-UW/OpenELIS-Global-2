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
package us.mn.state.health.lims.gender.daoimpl;

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
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.hibernate.HibernateUtil;


/**
 * @author diane benz
 */
public class GenderDAOImpl extends BaseDAOImpl implements GenderDAO {

	public void deleteData(List genders) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (Object gender : genders) {
				Gender data = (Gender) gender;

				Gender oldData = readGender(data.getId());
				Gender newData = new Gender();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "GENDER";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		}  catch (Exception e) {

			LogEvent.logError("GenderDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender AuditTrail deleteData()", e);
		}  
		
		try {
			for (Object gender : genders) {
				Gender data = (Gender) gender;
				data = readGender(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}						
		} catch (Exception e) {
			LogEvent.logError("GenderDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender deleteData()", e);
		}
	}

	public boolean insertData(Gender gender) throws LIMSRuntimeException {	
		
		try {
			// bugzilla 1482 throw Exception if record already exists
			if (duplicateGenderExists(gender)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ gender.getGenderType());
			}
			
			String id = (String)HibernateUtil.getSession().save(gender);
			gender.setId(id);
			
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = gender.getSysUserId();
			String tableName = "GENDER";
			auditDAO.saveNewHistory(gender,sysUserId,tableName);
			
			HibernateUtil.getSession().flush();	
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender insertData()", e);
		} 
		
		return true;
	}

	public void updateData(Gender gender) throws LIMSRuntimeException {
		try {
			if (duplicateGenderExists(gender)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ gender.getGenderType());
			}
		} catch (Exception e) {
			LogEvent.logError("GenderDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender updateData()",
					e);
		}

		Gender oldData = readGender(gender.getId());

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = gender.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "GENDER";
			auditDAO.saveHistory(gender,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender AuditTrail updateData()", e);
		}  
		
		try {
			HibernateUtil.getSession().merge(gender);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(gender);
			HibernateUtil.getSession().refresh(gender);
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender updateData()", e);
		} 
	}

	public void getData(Gender gender) throws LIMSRuntimeException {	
		try {					
			Gender gen = (Gender)HibernateUtil.getSession().get(Gender.class, gender.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (gen != null) {
			  PropertyUtils.copyProperties(gender, gen);
			} else {
				gender.setId(null);
			}
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Gender getData()", e);
		}
	}

	public List getAllGenders() throws LIMSRuntimeException {		
		List list;
		try {
			String sql = "from Gender";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch(Exception e) {
			
			LogEvent.logError("GenderDAOImpl","getAllGenders()",e.toString());
			throw new LIMSRuntimeException("Error in Gender getAllGenders()", e);
		} 
		
		return list;
	}

	public List getPageOfGenders(int startingRecNo) throws LIMSRuntimeException {		
		List list;
		try {			
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
			
			
			String sql = "from Gender g order by g.description, g.genderType";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 
			//query.setCacheMode(org.hibernate.CacheMode.REFRESH);
			
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","getPageOfGenders()",e.toString());
			throw new LIMSRuntimeException("Error in Gender getPageOfGenders()", e);
		} 
		
		return list;
	}

	public Gender readGender(String idString) {		
		Gender gender;
		try {			
			gender = (Gender)HibernateUtil.getSession().get(Gender.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","readGender()",e.toString());
			throw new LIMSRuntimeException("Error in Gender readGender(idString)", e);
		}			
		
		return gender;
	}
	
	public List getNextGenderRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Gender", Gender.class);
	}

    @Override
    public Gender getGenderByType( String type ) throws LIMSRuntimeException{
        String sql = "From Gender g where g.genderType = :type";
        try{
            Query query = HibernateUtil.getSession().createQuery( sql );
            query.setString( "type", type );
            Gender gender = (Gender)query.uniqueResult();
            closeSession();
            return gender;
        }catch( HibernateException e ){
            handleException( e, "getGenderByType" );
        }
        return null;
    }

    public List getPreviousGenderRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Gender", Gender.class);
	}
	
	public Integer getTotalGenderCount() throws LIMSRuntimeException {
		return getTotalCount("Gender", Gender.class);
	}
	
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId= Integer.valueOf( id );
		String tablePrefix = getTablePrefix(table);
		
		List list;

		try {
			String sql = "select g.id from Gender g order by g.description, g.genderType";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			int rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list(); 		
			
							
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;		
	}

	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		int currentId= Integer.valueOf( id );
		String tablePrefix = getTablePrefix(table);
		
		List list;

		try {	
			String sql = "select g.id from Gender g order by g.description desc, g.genderType desc";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			int rrn = list.indexOf(String.valueOf(currentId));
			
			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious")
			.setFirstResult(rrn + 1)
			.setMaxResults(2)
			.list(); 		
			
							
		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	// bugzilla 1482
	private boolean duplicateGenderExists(Gender gender) throws LIMSRuntimeException {
		try {

			List list;

			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from Gender t where trim(lower(t.genderType)) = :genderType and t.id != :genderId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("genderType", gender.getGenderType().toLowerCase().trim());
	
			// initialize with 0 (for new records where no id has been generated
			// yet
			String genderId = "0";
			if (!StringUtil.isNullorNill(gender.getId())) {
				genderId = gender.getId();
			}
			query.setInteger("genderId", Integer.parseInt(genderId));

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list.size() > 0;

		} catch (Exception e) {
			
			LogEvent.logError("GenderDAOImpl","duplicateGenderExists()",e.toString());
			throw new LIMSRuntimeException(
					"Error in duplicateGenderExists()", e);
		}
	}
}