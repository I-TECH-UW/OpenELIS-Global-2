package org.openelisglobal.patient.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.patient.dao.PatientContactDAO;
import org.openelisglobal.patient.valueholder.PatientContact;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PatientContactDAOImpl extends BaseDAOImpl<PatientContact, String> implements PatientContactDAO {

    public PatientContactDAOImpl() {
        super(PatientContact.class);
    }

    @Override
    public List<PatientContact> getForPatient(String patientId) {
        List<PatientContact> patients;

        try {
            String sql = "From PatientContact p where p.patientId = :patientId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("patientId", Integer.parseInt(patientId));
            patients = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException("Error in PatientContactDAOImpl getForPatient() ", e);
        }
        return patients;
    }

}
