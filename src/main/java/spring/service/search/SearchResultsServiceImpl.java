package spring.service.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.sample.dao.SearchResultsDAO;

@Service
public class SearchResultsServiceImpl implements SearchResultsService {

	@Autowired
	SearchResultsDAO searchResultsDAO;

	@Override
	public List<PatientSearchResults> getSearchResults(String lastName, String firstName, String STNumber,
			String subjectNumber, String nationalID, String externalID, String patientID, String guid) {
		return searchResultsDAO.getSearchResults(lastName, firstName, STNumber, subjectNumber, nationalID, externalID,
				patientID, guid);
	}

}
