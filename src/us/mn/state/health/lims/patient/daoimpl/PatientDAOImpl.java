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
package us.mn.state.health.lims.patient.daoimpl;

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
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;

/**
 * @author diane benz
 */
public class PatientDAOImpl extends BaseDAOImpl implements PatientDAO {

	public void deleteData(List patients) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < patients.size(); i++) {
				Patient data = (Patient)patients.get(i);

				Patient oldData = (Patient)readPatient(data.getId());
				Patient newData = new Patient();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PATIENT";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < patients.size(); i++) {
				Patient data = (Patient) patients.get(i);
			    //bugzilla 2206
				data = (Patient)readPatient(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient deleteData()", e);
		}
	}

	public boolean insertData(Patient patient) throws LIMSRuntimeException {

		try {
			String id = (String) HibernateUtil.getSession().save(patient);
			patient.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patient.getSysUserId();
			String tableName = "PATIENT";
			auditDAO.saveNewHistory(patient,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient insertData()", e);
		}

		return true;
	}

	public void updateData(Patient patient) throws LIMSRuntimeException {

		Patient oldData = readPatient(patient.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patient.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PATIENT";
			auditDAO.saveHistory(patient,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("PatientDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(patient);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(patient);
			HibernateUtil.getSession().refresh(patient);
		} catch (Exception e) {
			LogEvent.logError("PatientDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient updateData()", e);
		}
	}

	@Override
	public Patient getData(String patientId) throws LIMSRuntimeException {
		try{
		Patient pat = (Patient) HibernateUtil.getSession().get(	Patient.class, patientId );
		closeSession();
		if (pat != null) {
			updateDisplayValues(pat);
		}		
		return pat;
		}catch(HibernateException e){
			handleException(e, "getData(patientId)");
		}

		return null;
	}
	
	public void getData(Patient patient) throws LIMSRuntimeException {
		try {
			Patient pat = (Patient) HibernateUtil.getSession().get(	Patient.class, patient.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (pat != null) {
				updateDisplayValues(pat);

				PropertyUtils.copyProperties(patient, pat);
			} else {
				patient.setId(null);
			}

		} catch (Exception e) {
			LogEvent.logError("PatientDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Patient getData()", e);
		}
	}

	private void updateDisplayValues(Patient pat) {
		if (pat.getBirthDate() != null && pat.getBirthDateForDisplay() == null)
			pat.setBirthDateForDisplay(DateUtil.convertTimestampToStringDate(pat.getBirthDate()));
		if (pat.getBirthTime() != null)
			pat.setBirthTimeForDisplay(DateUtil.convertSqlDateToStringDate(pat.getBirthTime()));
		if (pat.getDeathDate() != null)
			pat.setDeathDateForDisplay(DateUtil.convertSqlDateToStringDate(pat.getDeathDate()));
	}

	public List getAllPatients() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Patient";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","getAllPatients()",e.toString());
			throw new LIMSRuntimeException("Error in Patient getAllPatients()",
					e);
		}

		return list;
	}

	public List getPageOfPatients(int startingRecNo)
			throws LIMSRuntimeException {
		List patients = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo
					+ (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Patient t order by t.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			patients = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","getPageOfPatients()",e.toString());
			throw new LIMSRuntimeException(
					"Error in Patient getPageOfPatients()", e);
		}

		return patients;
	}

	public Patient readPatient(String idString) {
		Patient pat = null;
		try {
			pat = (Patient) HibernateUtil.getSession().get(Patient.class,
					idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PatientDAOImpl","readPatient()",e.toString());
			throw new LIMSRuntimeException("Error in Patient readPatient()", e);
		}

		return pat;
	}

	public List getNextPatientRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Patient", Patient.class);

	}

	public List getPreviousPatientRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Patient", Patient.class);
	}

	public boolean externalIDExists(String patientExternalID) {

		List results;

		try {
			String sql = "From Patient where external_id = :patientID";

			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("patientID", patientExternalID);

			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in Patient readPatient()", e);
		}

		return !results.isEmpty();
	}

	@SuppressWarnings("unchecked")
    protected Patient getPatientByStringProperty(String propertyName, String propertyValue) {
        List<Patient> patients;

        try {
            String sql = "From Patient p where p." + propertyName + " = :" + propertyName;
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setString(propertyName, propertyValue);
            patients = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LIMSRuntimeException("Error in Patient getPatientByStringProperty(" + propertyName + "\", ) " , e);
        }
        return patients.isEmpty() ? null : patients.get(0);
	}

    public Patient getPatientByNationalId(String nationalId) {
        return getPatientByStringProperty("nationalId", nationalId);
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Patient> getPatientsByNationalId(String nationalId) throws LIMSRuntimeException{
        try {
            String sql = "From Patient p where p.nationalId = :nationalId";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setString("nationalId", nationalId);
            List<Patient> patients = query.list();
            closeSession();
            return patients;
        } catch (Exception e) {
        	handleException(e, "getPatientsByNationalId");
        }
        
        return null;
	}

    
    public Patient getPatientByExternalId(String externalId) {
        return getPatientByStringProperty("externalId", externalId);
    }

	@SuppressWarnings("unchecked")
	public Patient getPatientByPerson(Person person) throws LIMSRuntimeException {
		List<Patient> patients;

		try {
			String sql = "From Patient p where p.person.id = :personID";

			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("personID", Integer.parseInt(person.getId()));

			patients = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMSRuntimeException("Error in Patient getPatientByPerson()", e);
		}

		return patients.size() > 0 ? patients.get(0) : null;
	}

	public List<String> getPatientIdentityBySampleStatusIdAndProject(List<Integer> inclusiveStatusIdList, String project)
			throws LIMSRuntimeException {

		boolean useIdList = inclusiveStatusIdList != null && inclusiveStatusIdList.size() > 0;

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select COALESCE(pat.national_id, pat.external_id) as subjectNo from clinlims.sample s ");
		sqlBuilder.append("join clinlims.sample_projects sp on sp.samp_id = s.id ");
		sqlBuilder.append("join clinlims.project p on sp.proj_id = p.id ");
		sqlBuilder.append("join clinlims.sample_human sh on sh.samp_id = s.id ");
		sqlBuilder.append("join clinlims.patient pat on pat.id = sh.patient_id  ");
		sqlBuilder.append("where p.name = :project " );
		if( useIdList ){
			sqlBuilder.append("and s.status_id in(:statusIdList) ");
		}

		try{
			Query query = HibernateUtil.getSession().createSQLQuery(sqlBuilder.toString());
			if(useIdList){
				query.setParameterList("statusIdList", inclusiveStatusIdList);
			}
			query.setString("project", project);

			List<String> subjectList = query.list();
			closeSession();

			return subjectList;
		}catch (Exception e) {
			handleException(e, "getPatientIdentityBySampleStatusIdAndProject");
		}

		return new ArrayList<String>();
	}

	

}