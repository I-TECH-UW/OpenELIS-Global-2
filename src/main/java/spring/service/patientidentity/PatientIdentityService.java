package spring.service.patientidentity;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;

public interface PatientIdentityService extends BaseObjectService<PatientIdentity> {

	List<PatientIdentity> getPatientIdentitiesForPatient(String id);
}
