package org.openelisglobal.sample.dao;

import java.util.List;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.query.PatientSearchResults;

public interface SearchResultsDAO {

  public List<PatientSearchResults> getSearchResults(
      String lastName,
      String firstName,
      String STNumber,
      String subjectNumber,
      String nationalID,
      String externalID,
      String patientID,
      String guid,
      String dateOfBirth,
      String gender)
      throws LIMSRuntimeException;

  List<PatientSearchResults> getSearchResultsByGUID(
      String lastName,
      String firstName,
      String STNumber,
      String subjectNumber,
      String nationalID,
      String externalID,
      String patientID,
      String guid,
      String dateOfBirth,
      String gender)
      throws LIMSRuntimeException;

  List<PatientSearchResults> getSearchResultsExact(
      String lastName,
      String firstName,
      String STNumber,
      String subjectNumber,
      String nationalID,
      String externalID,
      String patientID,
      String guid,
      String dateOfBirth,
      String gender)
      throws LIMSRuntimeException;
}
