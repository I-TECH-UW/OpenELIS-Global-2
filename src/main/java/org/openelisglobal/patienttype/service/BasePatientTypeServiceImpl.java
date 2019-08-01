package org.openelisglobal.patienttype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.patienttype.dao.BasePatientTypeDAO;
import org.openelisglobal.patienttype.valueholder.BasePatientType;

@Service
public class BasePatientTypeServiceImpl extends BaseObjectServiceImpl<BasePatientType, String>
        implements BasePatientTypeService {
    @Autowired
    protected BasePatientTypeDAO baseObjectDAO;

    BasePatientTypeServiceImpl() {
        super(BasePatientType.class);
    }

    @Override
    protected BasePatientTypeDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
