/**
 * Project : LIS<br>
 * File name : PatientTypeDAOImpl.java<br>
 * Description : 
 * @author TienDH
 * @date Aug 20, 2007
 */
package us.mn.state.health.lims.patienttype.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;


public class PatientTypeDAOImpl extends BaseDAOImpl implements PatientTypeDAO {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(PatientTypeDAOImpl.class);

	public void deleteData(List patientTypes) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < patientTypes.size(); i++) {
				PatientType data = (PatientType)patientTypes.get(i);
			
				PatientType oldData = (PatientType)readPatientType(data.getId());
				PatientType newData = new PatientType();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PATIENT_TYPE";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PATIENT_TYPE AuditTrail deleteData()", e);
		}  
		
		try {					
			for (int i = 0; i < patientTypes.size(); i++) {
				PatientType data = (PatientType) patientTypes.get(i);				
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PatientType deleteData()", e);
		} 
	}

	public boolean insertData(PatientType patientType) throws LIMSRuntimeException {
		try {	
			
			if (duplicatePatientTypeExists(patientType)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ patientType.getDescription());
			}
			
			String id = (String)HibernateUtil.getSession().save(patientType);
			patientType.setId(id);
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientType.getSysUserId();
			String tableName = "PATIENT_TYPE";			
			auditDAO.saveNewHistory(patientType,sysUserId,tableName);			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();					
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in patientType insertData()", e);
		}
		
		return true;
	}

	public void updateData(PatientType patientTypes) throws LIMSRuntimeException {
		try {
			/*if (duplicatePatientTypeExists(patientTypes)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for "
								+ patientTypes.getDescription());
			}*/
		} catch (Exception e) {
			throw new LIMSRuntimeException("Error in patientType updateData()",
					e);
		}
		
		PatientType oldData = readPatientType(patientTypes.getId().toString());
		PatientType newData = patientTypes;

		//add to audit trail
		try {			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientTypes.getId().toString();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PATIENT_TYPE";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in patientType AuditTrail updateData()", e);
		}  
						
		try {			
			HibernateUtil.getSession().merge(patientTypes);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(patientTypes);
			HibernateUtil.getSession().refresh(patientTypes);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PatientType updateData()", e);
		}
	}

	public void getData(PatientType patientType) throws LIMSRuntimeException {		
		try {			
			PatientType cityvns = (PatientType)HibernateUtil.getSession().get(PatientType.class, patientType.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (cityvns != null) {
				PropertyUtils.copyProperties(patientType, cityvns);
			} else {
				patientType.setId(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PatientType getData()", e);
		} 
	}

	public List getAllPatientTypes() throws LIMSRuntimeException {		
		List list = new Vector();
		try {			
			String sql = "from PatientType p order by p.type";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			//query.setMaxResults(10);
			//query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in patientType getAllPatientTypes()", e);
		} 

		return list;
	}

	public List getPageOfPatientType(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {			
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from PatientType l order by l.type";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1); 					

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in getPageOfPatientType()",e);
		} 

		return list;
	}

	public PatientType readPatientType(String idString) {
		PatientType patientType = null;
		try {			
			patientType = (PatientType)HibernateUtil.getSession().get(PatientType.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PatientType readPatientType()", e);
		}			
		
		return patientType;
	}
	
	// this is for autocomplete
	public List getPatientTypes(String description) throws LIMSRuntimeException {		
		List list = new Vector(); 	
		try {			
			String sql = "from patientType l where upper(l.description) like upper(:param) order by upper(l.description)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", description+"%");		
		
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in patientType getPatientTypes(String filter)", e);
		}
		
		return list;
	}

	public List getNextPatientTypeRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "PatientType", PatientType.class);

	}

	public List getPreviousPatientTypeRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "PatientType", PatientType.class);
	}

	public PatientType getPatientTypeByName(PatientType patientType) throws LIMSRuntimeException {		
		try {			
			String sql = "from PatientType l where l.type = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", patientType.getType());
			
			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			PatientType patientTypes = null;
			if ( list.size() > 0 )
				patientTypes = (PatientType)list.get(0);
			
			return patientTypes;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in PatientType getPatientTypeByName()", e);
		}
	}
		
	public Integer getTotalPatientTypeCount() throws LIMSRuntimeException {
		return getTotalCount("PatientType", PatientType.class);
	}
	
	
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {	
				
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);
		List list = new Vector();
		int rrn = 0;
		try {
    		String sql = "select d.id from PatientType d" +
					" order by d.type";
 			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(
					tablePrefix + "getNext").setFirstResult(
					rrn + 1).setMaxResults(2).list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in getNextRecord() for "
					+ table, e);
		}
		return list;		
	}

	
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {		
		
		int currentId= (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);		
		List list = new Vector();
		int rrn = 0;
		try {
			String sql = "select g.id from PatientType g order by g.type desc";
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
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		} 

		return list;
	}
	
	//Check duplicate with fild Description .
	private boolean duplicatePatientTypeExists(PatientType patientType) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();
			String sql = "from PatientType t where trim(upper(t.description)) = :param1 or trim(upper(t.type)) = :param2";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);

			// initialize with 0 (for new records where no id has been generated
			// yet
			String descripts = "0";
			String type = "0";
			if (!StringUtil.isNullorNill(patientType.getDescription())) {
				descripts = patientType.getDescription();
				type = patientType.getType();
			}			
			query.setParameter("param1", descripts);
			query.setParameter("param2", type);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if (list.size() > 0) {
				return true;				
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException(
					"Error in duplicatePatientTypeExists()", e);
		}		
	}
}