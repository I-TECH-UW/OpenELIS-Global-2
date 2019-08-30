package org.openelisglobal.patientrelation.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.patientrelation.valueholder.PatientRelation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PatientRelationDAOImpl extends BaseDAOImpl<PatientRelation, String> implements PatientRelationDAO {
    PatientRelationDAOImpl() {
        super(PatientRelation.class);
    }
}
