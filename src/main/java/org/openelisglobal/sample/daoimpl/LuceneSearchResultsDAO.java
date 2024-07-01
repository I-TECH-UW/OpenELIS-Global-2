package org.openelisglobal.sample.daoimpl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.sample.dao.SearchResultsDAO;
import org.springframework.stereotype.Component;

@Component
public class LuceneSearchResultsDAO implements SearchResultsDAO {

    @Override
    @Transactional
    public List<PatientSearchResults> getSearchResults(String lastName, String firstName, String STNumber,
            String subjectNumber, String nationalID, String externalID, String patientID, String guid,
            String dateOfBirth, String gender) throws LIMSRuntimeException {

        List<PatientSearchResults> patientSearchResultsList = new ArrayList<>();
        return patientSearchResultsList;
    }

    @Override
    @Transactional
    public List<PatientSearchResults> getSearchResultsByGUID(String lastName, String firstName, String STNumber,
            String subjectNumber, String nationalID, String externalID, String patientID, String guid,
            String dateOfBirth, String gender) throws LIMSRuntimeException {

        List<PatientSearchResults> patientSearchResultsList = new ArrayList<>();
        return patientSearchResultsList;
    }

    @Override
    @Transactional
    public List<PatientSearchResults> getSearchResultsExact(String lastName, String firstName, String STNumber,
            String subjectNumber, String nationalID, String externalID, String patientID, String guid,
            String dateOfBirth, String gender) throws LIMSRuntimeException {

        List<PatientSearchResults> patientSearchResultsList = new ArrayList<>();
        return patientSearchResultsList;
    }

}
