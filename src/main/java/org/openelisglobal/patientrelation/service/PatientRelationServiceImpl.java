package org.openelisglobal.patientrelation.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.patientrelation.dao.PatientRelationDAO;
import org.openelisglobal.patientrelation.valueholder.PatientRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientRelationServiceImpl extends BaseObjectServiceImpl<PatientRelation, String>
        implements PatientRelationService {
    @Autowired
    protected PatientRelationDAO baseObjectDAO;

    PatientRelationServiceImpl() {
        super(PatientRelation.class);
    }

    @Override
    protected PatientRelationDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
