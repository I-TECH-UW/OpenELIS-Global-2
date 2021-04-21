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
package org.openelisglobal.patient.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.patient.dao.PatientDAO;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class PatientDAOImpl extends BaseDAOImpl<Patient, String> implements PatientDAO {

    public PatientDAOImpl() {
        super(Patient.class);
    }

//	@Override
//	public void deleteData(List patients) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < patients.size(); i++) {
//				Patient data = (Patient) patients.get(i);
//
//				Patient oldData = readPatient(data.getId());
//				Patient newData = new Patient();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PATIENT";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PatientDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Patient AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < patients.size(); i++) {
//				Patient data = (Patient) patients.get(i);
//				// bugzilla 2206
//				data = readPatient(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PatientDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Patient deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Patient patient) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(patient);
//			patient.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//
//			String sysUserId = patient.getSysUserId();
//			String tableName = "PATIENT";
//			auditDAO.saveNewHistory(patient, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PatientDAOImpl", "insertData()", e.toString());
//			LogEvent.logDebug(e);
//			throw new LIMSRuntimeException("Error in Patient insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(Patient patient) throws LIMSRuntimeException {
//
//		Patient oldData = readPatient(patient.getId());
//
//		try {
//
//			String sysUserId = patient.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PATIENT";
//			auditDAO.saveHistory(patient, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Patient AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(patient);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(patient);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(patient);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Patient updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public Patient getData(String patientId) throws LIMSRuntimeException {
        try {
            Patient pat = entityManager.unwrap(Session.class).get(Patient.class, patientId);
            // closeSession(); // CSL remove old
            if (pat != null) {
                updateDisplayValues(pat);
            }
            return pat;
        } catch (HibernateException e) {
            handleException(e, "getData(patientId)");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(Patient patient) throws LIMSRuntimeException {
        try {
            Patient pat = entityManager.unwrap(Session.class).get(Patient.class, patient.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (pat != null) {
                updateDisplayValues(pat);

                PropertyUtils.copyProperties(patient, pat);
            } else {
                patient.setId(null);
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Patient getData()", e);
        }
    }

    private void updateDisplayValues(Patient pat) {
        if (pat.getBirthDate() != null && pat.getBirthDateForDisplay() == null) {
            pat.setBirthDateForDisplay(DateUtil.convertTimestampToStringDate(pat.getBirthDate()));
        }
        if (pat.getBirthTime() != null) {
            pat.setBirthTimeForDisplay(DateUtil.convertSqlDateToStringDate(pat.getBirthTime()));
        }
        if (pat.getDeathDate() != null) {
            pat.setDeathDateForDisplay(DateUtil.convertSqlDateToStringDate(pat.getDeathDate()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> getAllPatients() throws LIMSRuntimeException {
        List<Patient> list;
        try {
            String sql = "from Patient";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Patient getAllPatients()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> getPageOfPatients(int startingRecNo) throws LIMSRuntimeException {
        List<Patient> patients;
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from Patient t order by t.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            patients = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Patient getPageOfPatients()", e);
        }

        return patients;
    }

    @Override
    public Patient readPatient(String idString) {
        Patient pat = null;
        try {
            pat = entityManager.unwrap(Session.class).get(Patient.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Patient readPatient()", e);
        }

        return pat;
    }

    @Override
    public boolean externalIDExists(String patientExternalID) {

        List<Patient> results;

        try {
            String sql = "From Patient where external_id = :patientID";

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("patientID", patientExternalID);

            results = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in Patient readPatient()", e);
        }

        return !results.isEmpty();
    }

    protected Patient getPatientByStringProperty(String propertyName, String propertyValue) {
        List<Patient> patients;

        try {
            String sql = "From Patient p where p." + propertyName + " = :" + propertyName;
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString(propertyName, propertyValue);
            patients = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in Patient getPatientByStringProperty(" + propertyName + "\", ) ", e);
        }
        return patients.isEmpty() ? null : patients.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientByNationalId(String nationalId) {
        return getPatientByStringProperty("nationalId", nationalId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> getPatientsByNationalId(String nationalId) throws LIMSRuntimeException {
        try {
            String sql = "From Patient p where p.nationalId = :nationalId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("nationalId", nationalId);
            List<Patient> patients = query.list();
            // closeSession(); // CSL remove old
            return patients;
        } catch (RuntimeException e) {
            handleException(e, "getPatientsByNationalId");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientByExternalId(String externalId) {
        return getPatientByStringProperty("externalId", externalId);
    }

    @Override

    @Transactional(readOnly = true)
    public Patient getPatientByPerson(Person person) throws LIMSRuntimeException {
        List<Patient> patients;

        try {
            String sql = "From Patient p where p.person.id = :personID";

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("personID", Integer.parseInt(person.getId()));

            patients = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in Patient getPatientByPerson()", e);
        }

        return patients.size() > 0 ? patients.get(0) : null;
    }

    @Override
    @Transactional
    public List<String> getPatientIdentityBySampleStatusIdAndProject(List<Integer> inclusiveStatusIdList,
            String project) throws LIMSRuntimeException {

        boolean useIdList = inclusiveStatusIdList != null && inclusiveStatusIdList.size() > 0;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select COALESCE(pat.national_id, pat.external_id) as subjectNo from clinlims.sample s ");
        sqlBuilder.append("join clinlims.sample_projects sp on sp.samp_id = s.id ");
        sqlBuilder.append("join clinlims.project p on sp.proj_id = p.id ");
        sqlBuilder.append("join clinlims.sample_human sh on sh.samp_id = s.id ");
        sqlBuilder.append("join clinlims.patient pat on pat.id = sh.patient_id  ");
        sqlBuilder.append("where p.name = :project ");
        if (useIdList) {
            sqlBuilder.append("and s.status_id in(:statusIdList) ");
        }

        try {
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sqlBuilder.toString());
            if (useIdList) {
                query.setParameterList("statusIdList", inclusiveStatusIdList);
            }
            query.setString("project", project);

            List<String> subjectList = query.list();
            // closeSession(); // CSL remove old

            return subjectList;
        } catch (RuntimeException e) {
            handleException(e, "getPatientIdentityBySampleStatusIdAndProject");
        }

        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientBySubjectNumber(String subjectNumber) {
        return getPatientByStringProperty("subjectNumber", subjectNumber);
    }

    @Override
    public List<Patient> getAllMissingFhirUuid() {
        String sql = "from Patient p where p.fhirUuid is NULL";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            List<Patient> patientList = query.list();

            return patientList;
        } catch (HibernateException e) {
            handleException(e, "getAllMissingFhirUuid");
        }
        return new ArrayList<>();
    }

}