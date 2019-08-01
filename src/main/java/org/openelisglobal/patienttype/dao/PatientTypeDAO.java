/**
 * Project : LIS<br>
 * File name : PatientTypeDAO.java<br>
 * Description :
 * @author TienDH
 * @date Aug 20, 2007
 */
package org.openelisglobal.patienttype.dao;

import java.util.List;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.patienttype.valueholder.PatientType;

public interface PatientTypeDAO extends BaseDAO<PatientType, String> {

//	public boolean insertData(PatientType patientType) throws LIMSRuntimeException;

//	public void deleteData(List patientType) throws LIMSRuntimeException;

    public List getAllPatientTypes() throws LIMSRuntimeException;

    public List getPageOfPatientType(int startingRecNo) throws LIMSRuntimeException;

    public void getData(PatientType patientType) throws LIMSRuntimeException;

//	public void updateData(PatientType patientType) throws LIMSRuntimeException;

    public List getPatientTypes(String filter) throws LIMSRuntimeException;

    public List getNextPatientTypeRecord(String id) throws LIMSRuntimeException;

    public List getPreviousPatientTypeRecord(String id) throws LIMSRuntimeException;

    public PatientType getPatientTypeByName(PatientType patientType) throws LIMSRuntimeException;

    public Integer getTotalPatientTypeCount() throws LIMSRuntimeException;

    public boolean duplicatePatientTypeExists(PatientType patientType);
}
