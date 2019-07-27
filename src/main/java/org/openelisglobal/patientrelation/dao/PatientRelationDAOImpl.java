package org.openelisglobal.patientrelation.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.patientrelation.valueholder.PatientRelation;

@Component
@Transactional 
public class PatientRelationDAOImpl extends BaseDAOImpl<PatientRelation, String> implements PatientRelationDAO {
  PatientRelationDAOImpl() {
    super(PatientRelation.class);
  }
}
