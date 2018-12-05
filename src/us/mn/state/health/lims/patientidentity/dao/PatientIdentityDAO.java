package us.mn.state.health.lims.patientidentity.dao;

import java.util.List;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;

public interface PatientIdentityDAO {

    public boolean insertData(PatientIdentity patientIdentity) throws LIMSRuntimeException;

    public void updateData(PatientIdentity patientIdentity) throws LIMSRuntimeException;

    public void delete(String patientIdentityId, String activeUserId) throws LIMSRuntimeException;

    public List<PatientIdentity> getPatientIdentitiesForPatient( String id )throws LIMSRuntimeException;

    public List<PatientIdentity> getPatientIdentitiesByValueAndType( String value, String identityType )throws LIMSRuntimeException;

	public PatientIdentity getPatitentIdentityForPatientAndType(String patientId, String identityTypeId) throws LIMSRuntimeException;
}
