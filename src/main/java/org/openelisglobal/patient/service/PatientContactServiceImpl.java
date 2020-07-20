package org.openelisglobal.patient.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.patient.dao.PatientContactDAO;
import org.openelisglobal.patient.valueholder.PatientContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientContactServiceImpl extends BaseObjectServiceImpl<PatientContact, String>
        implements PatientContactService {

    @Autowired
    private PatientContactDAO baseObjectDAO;

    public PatientContactServiceImpl() {
        super(PatientContact.class);
    }

    @Override
    protected PatientContactDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

}
