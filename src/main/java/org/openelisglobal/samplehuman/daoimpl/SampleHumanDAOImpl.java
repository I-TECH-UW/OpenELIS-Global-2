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
package org.openelisglobal.samplehuman.daoimpl;

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
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.provider.valueholder.Provider;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.dao.SampleHumanDAO;
import org.openelisglobal.samplehuman.valueholder.SampleHuman;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * $Header$
 *
 * @author Hung Nguyen
 * @date created 08/04/2006
 * @version $Revision$
 */
@Component
@Transactional
public class SampleHumanDAOImpl extends BaseDAOImpl<SampleHuman, String> implements SampleHumanDAO {

    public SampleHumanDAOImpl() {
        super(SampleHuman.class);
    }

    // @Override
    // public void deleteData(List sampleHumans) throws LIMSRuntimeException {
    // // add to audit trail
    // try {
    //
    // for (int i = 0; i < sampleHumans.size(); i++) {
    // SampleHuman data = (SampleHuman) sampleHumans.get(i);
    //
    // SampleHuman oldData = readSampleHuman(data.getId());
    // SampleHuman newData = new SampleHuman();
    //
    // String sysUserId = data.getSysUserId();
    // String event = IActionConstants.AUDIT_TRAIL_DELETE;
    // String tableName = "SAMPLE_HUMAN";
    // auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
    // }
    // } catch (RuntimeException e) {
    // // bugzilla 2154
    // LogEvent.logError("SampleHumanDAOImpl", "AuditTrail deleteData()",
    // e.toString());
    // throw new LIMSRuntimeException("Error in SampleHuman AuditTrail
    // deleteData()", e);
    // }
    //
    // try {
    // for (int i = 0; i < sampleHumans.size(); i++) {
    // SampleHuman data = (SampleHuman) sampleHumans.get(i);
    // // bugzilla 2206
    // data = readSampleHuman(data.getId());
    // entityManager.unwrap(Session.class).delete(data);
    // // entityManager.unwrap(Session.class).flush(); // CSL remove old
    // // entityManager.unwrap(Session.class).clear(); // CSL remove old
    // }
    // } catch (RuntimeException e) {
    // // bugzilla 2154
    // LogEvent.logError("SampleHumanDAOImpl", "deleteData()", e.toString());
    // throw new LIMSRuntimeException("Error in SampleHuman deleteData()", e);
    // }
    // }
    //
    // @Override
    // public boolean insertData(SampleHuman sampleHuman) throws
    // LIMSRuntimeException {
    //
    // try {
    // String id = (String) entityManager.unwrap(Session.class).save(sampleHuman);
    // sampleHuman.setId(id);
    //
    // // bugzilla 1824 inserts will be logged in history table
    //
    // String sysUserId = sampleHuman.getSysUserId();
    // String tableName = "SAMPLE_HUMAN";
    // auditDAO.saveNewHistory(sampleHuman, sysUserId, tableName);
    //
    // // entityManager.unwrap(Session.class).flush(); // CSL remove old
    // // entityManager.unwrap(Session.class).clear(); // CSL remove old
    //
    // } catch (RuntimeException e) {
    // // bugzilla 2154
    // LogEvent.logError("SampleHumanDAOImpl", "insertData()", e.toString());
    // throw new LIMSRuntimeException("Error in SampleHuman insertData()", e);
    // }
    //
    // return true;
    // }

    // @Override
    // public void updateData(SampleHuman sampleHuman) throws LIMSRuntimeException {
    //
    // SampleHuman oldData = readSampleHuman(sampleHuman.getId());
    // SampleHuman newData = sampleHuman;
    //
    // // add to audit trail
    // try {
    //
    // String sysUserId = sampleHuman.getSysUserId();
    // String event = IActionConstants.AUDIT_TRAIL_UPDATE;
    // String tableName = "SAMPLE_HUMAN";
    // auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
    // } catch (RuntimeException e) {
    // // bugzilla 2154
    // LogEvent.logError("SampleHumanDAOImpl", "updateData()", e.toString());
    // throw new LIMSRuntimeException("Error in SampleHuman AuditTrail
    // updateData()", e);
    // }
    //
    // try {
    // entityManager.unwrap(Session.class).merge(sampleHuman);
    // // entityManager.unwrap(Session.class).flush(); // CSL remove old
    // // entityManager.unwrap(Session.class).clear(); // CSL remove old
    // // entityManager.unwrap(Session.class).evict // CSL remove old(sampleHuman);
    // // entityManager.unwrap(Session.class).refresh // CSL remove
    // old(sampleHuman);
    // } catch (RuntimeException e) {
    // // bugzilla 2154
    // LogEvent.logError("SampleHumanDAOImpl", "updateData()", e.toString());
    // throw new LIMSRuntimeException("Error in SampleHuman updateData()", e);
    // }
    // }

    @Override
    @Transactional(readOnly = true)
    public void getData(SampleHuman sampleHuman) throws LIMSRuntimeException {
        try {
            SampleHuman sampHuman = entityManager.unwrap(Session.class).get(SampleHuman.class, sampleHuman.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (sampHuman != null) {
                PropertyUtils.copyProperties(sampleHuman, sampHuman);
            } else {
                sampleHuman.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman getData()", e);
        }
    }

    public SampleHuman readSampleHuman(String idString) {
        SampleHuman sh = null;
        try {
            sh = entityManager.unwrap(Session.class).get(SampleHuman.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman readSampleHuman()", e);
        }

        return sh;
    }

    @Override
    @Transactional(readOnly = true)
    public void getDataBySample(SampleHuman sampleHuman) throws LIMSRuntimeException {

        try {
            String sql = "from SampleHuman sh where samp_id = :param";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("param", Integer.parseInt(sampleHuman.getSampleId()));
            List<SampleHuman> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            SampleHuman sh = null;
            if (list.size() > 0) {
                sh = list.get(0);
                PropertyUtils.copyProperties(sampleHuman, sh);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman getDataBySample()", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientForSample(Sample sample) throws LIMSRuntimeException {
        Patient patient = null;
        try {
            String sql = "select patient from Patient as patient, SampleHuman as sampleHuman where sampleHuman.patientId = patient.id and sampleHuman.sampleId = :sId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sId", Integer.parseInt(sample.getId()));
            patient = (Patient) query.uniqueResult();
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman getPatientForSample()", e);
        }

        return patient;
    }

    @Override
    @Transactional(readOnly = true)
    public Provider getProviderForSample(Sample sample) throws LIMSRuntimeException {
        try {
            String sql = "select provider from Provider as provider, SampleHuman as sampleHuman where sampleHuman.providerId = provider.id and sampleHuman.sampleId = :sId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sId", Integer.parseInt(sample.getId()));
            Provider provider = (Provider) query.uniqueResult();
            // closeSession(); // CSL remove old

            return provider;
        } catch (HibernateException e) {
            handleException(e, "getProviderForSample");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesForPatient(String patientID) throws LIMSRuntimeException {

        List<Sample> samples;

        try {
            String sql = "select sample from Sample as sample, SampleHuman as sampleHuman where sampleHuman.sampleId = sample.id and sampleHuman.patientId = :patientId order by sample.id";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("patientId", Integer.parseInt(patientID));
            samples = query.list();
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException(e);
        }

        return samples;
    }

    @Override
    public List<Patient> getAllPatientsWithSampleEntered() {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "select distinct patient from Patient as patient, SampleHuman as sampleHuman where sampleHuman.patientId = patient.id";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            patients = query.getResultList();
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman getAllPatientsWithSampleEntered()", e);
        }

        return patients;
    }

    @Override
    public List<Patient> getAllPatientsWithSampleEnteredMissingFhirUuid() {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "select distinct patient from Patient as patient, SampleHuman as sampleHuman where sampleHuman.patientId = patient.id AND patient.fhirUuid is null";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            patients = query.getResultList();
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleHuman getAllPatientsWithSampleEntered()", e);
        }

        return patients;
    }
}