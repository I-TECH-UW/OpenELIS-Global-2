package org.openelisglobal.patienttype.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.patienttype.valueholder.BasePatientType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BasePatientTypeDAOImpl extends BaseDAOImpl<BasePatientType, String> implements BasePatientTypeDAO {
    BasePatientTypeDAOImpl() {
        super(BasePatientType.class);
    }
}
