package org.openelisglobal.patient.daoimpl;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
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


}
