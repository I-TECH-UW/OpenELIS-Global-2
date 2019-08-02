package org.openelisglobal.patient.service;

import java.util.List;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.patienttype.valueholder.PatientType;

public interface PatientTypeService extends BaseObjectService<PatientType, String> {

    public List getAllPatientTypes() throws LIMSRuntimeException;

    public List getPageOfPatientType(int startingRecNo) throws LIMSRuntimeException;

    public void getData(PatientType patientType) throws LIMSRuntimeException;

    public List getPatientTypes(String filter) throws LIMSRuntimeException;

    public List getNextPatientTypeRecord(String id) throws LIMSRuntimeException;

    public List getPreviousPatientTypeRecord(String id) throws LIMSRuntimeException;

    public PatientType getPatientTypeByName(PatientType patientType) throws LIMSRuntimeException;

    public Integer getTotalPatientTypeCount() throws LIMSRuntimeException;

}
