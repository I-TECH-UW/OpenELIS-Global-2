package spring.service.search;

import java.util.List;

import us.mn.state.health.lims.common.provider.query.PatientSearchResults;

public interface SearchResultsService {

	List<PatientSearchResults> getSearchResults(String lastName, String firstName, String STNumber,
			String subjectNumber, String nationalID, String externalID, String patientID, String guid);

}
