/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.common.provider.validation;

import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.PATIENT_STATUS_FAIL;
import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.SAMPLE_FOUND;
import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.SAMPLE_STATUS_FAIL;

import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.services.StatusSet;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ProgramAccessionValidator implements IAccessionNumberValidator {

	private static final String INCREMENT_STARTING_VALUE  = "00001";
	private static final int UPPER_INC_RANGE = 99999;
	private static final int INCREMENT_START = 4;
	private static final int PROGRAM_START = 0;
	private static final int PROGRAM_END = 4;
	private static final int LENGTH = 9;
	private static final boolean NEED_PROGRAM_CODE = true;
	private static ProjectDAO projectDAO;
	

	public boolean needProgramCode() {
		return NEED_PROGRAM_CODE;
	}

	public String createFirstAccessionNumber(String programCode) {
		return programCode + INCREMENT_STARTING_VALUE;
	}

	public String incrementAccessionNumber(String currentHighAccessionNumber) {

		int increment = Integer.parseInt(currentHighAccessionNumber.substring(INCREMENT_START));
		String incrementAsString = INCREMENT_STARTING_VALUE;

		if( increment < UPPER_INC_RANGE){
			increment++;
			incrementAsString = String.format("%05d", increment);
		}else{
			throw new IllegalArgumentException("AccessionNumber has no next value");
		}

		StringBuilder builder = new StringBuilder( currentHighAccessionNumber.substring(PROGRAM_START, PROGRAM_END).toUpperCase());
		builder.append(incrementAsString);

		return builder.toString();
	}


	public ValidationResults validFormat(String accessionNumber, boolean checkDate) {
		// The rule is 4 digit program code and 4 incremented numbers
		if (accessionNumber.length() != LENGTH) {

			return ValidationResults.LENGTH_FAIL;
		}

		String programCode = accessionNumber.substring(PROGRAM_START, PROGRAM_END).toUpperCase();

		//check program code validity
		ProjectDAO projectDAO = getProjectDAO();
		List<Project> programCodes = projectDAO.getAllProjects();


		boolean found = false;
		for ( Project code: programCodes ){
			if ( programCode.equals(code.getProgramCode())){
				found = true;
				break;
			}
	    }

		if ( !found ) {
			return ValidationResults.PROGRAM_FAIL;
		}

		try {
			Integer.parseInt(accessionNumber.substring(INCREMENT_START));
		} catch (NumberFormatException e) {
			return ValidationResults.FORMAT_FAIL;
		}


		return ValidationResults.SUCCESS;
	}

	public String getInvalidMessage(ValidationResults results){

		switch(results){
			case LENGTH_FAIL: 	return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.length");
			case USED_FAIL:		return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.used");
			case PROGRAM_FAIL: 	return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.program");
			case FORMAT_FAIL:  	return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.format");
			case REQUIRED_FAIL:	return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.required");
            case PATIENT_STATUS_FAIL:   return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.patientRecordStatus");
            case SAMPLE_STATUS_FAIL:   return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.sampleRecordStatus");
			default: 			return StringUtil.getMessageForKey("sample.entry.invalid.accession.number");
		}
	}

    public String getInvalidFormatMessage( ValidationResults results ){
        return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.format");
    }
	public String getNextAvailableAccessionNumber(String prefix){
		String nextAccessionNumber = null;

		SampleDAO sampleDAO = new SampleDAOImpl();

		String curLargestAccessionNumber = sampleDAO.getLargestAccessionNumberWithPrefix(prefix);

		if( curLargestAccessionNumber == null){
			nextAccessionNumber = createFirstAccessionNumber(prefix);
		}else{
			nextAccessionNumber = incrementAccessionNumber(curLargestAccessionNumber);
		}

		return nextAccessionNumber;
	}

	public boolean accessionNumberIsUsed(String accessionNumber, String recordType) {
		boolean accessionNumberUsed = new SampleDAOImpl().getSampleByAccessionNumber(accessionNumber) != null;
		
		if( recordType == null){
			return accessionNumberUsed;
		}
		StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);
		String recordStatus = new String();
		boolean isSampleEntry = recordType.contains("Sample");
		boolean isPatientEntry = recordType.contains("Patient");
		boolean isInitial = recordType.contains("initial");
		boolean isDouble = recordType.contains("double");


		if (accessionNumberUsed) {

				// sample entry, get SampleRecordStatus
				if (isSampleEntry){
					recordStatus = statusSet.getSampleRecordStatus().toString();
				}

				// patient entry, get PatientRecordStatus
				else if (isPatientEntry) {
					recordStatus = statusSet.getPatientRecordStatus().toString();
				}

				// initial entry, the status must be NotRegistered
				String notRegistered = RecordStatus.NotRegistered.toString();
				String initialReg = RecordStatus.InitialRegistration.toString();
				if (isInitial){
					if(!notRegistered.equals(recordStatus) ){
						return true;
					}
				}

				// double entry, the status must be InitialRegistration
				else if (isDouble) {
					if ( !initialReg.equals(recordStatus) ) {
						return false;
					}
					else {
						return true;
					}

				}

		}

		return false;
	}

	public int getMaxAccessionLength() {
		return LENGTH;
	}

	/**
	 * There are many possible samples with various status, only some of which are valid during certain entry steps.
	 * This method provides validation results identifying whether a given sample is appropriate given all the information.
	 * @param accessionNumber  the number for the sample
	 * @param recordType initialPatient, initialSample, doublePatient (double entry for patient), doubleSample
	 * @param isRequired the step being done expects the sample to exist.  This is used generate appropriate results, either
	 * REQUIRED_FAIL vs SAMPLE_NOT_FOUND
	 * @param studyFormName - an additional
	 * @return
	 */
  public ValidationResults checkAccessionNumberValidity(String accessionNumber, String recordType,
          String isRequired, String studyFormName) {
    ValidationResults results = validFormat(accessionNumber, true);
    SampleDAO sampleDAO = new SampleDAOImpl();

    boolean accessionUsed = (sampleDAO.getSampleByAccessionNumber(accessionNumber) != null);
    if (results == ValidationResults.SUCCESS) {

      if (IActionConstants.TRUE.equals(isRequired) && !accessionUsed) {
        results = ValidationResults.REQUIRED_FAIL;
        return results;
      } else {
        if (recordType == null) {
          results = ValidationResults.USED_FAIL;
        }
        // record Type specified, so work out the detailed response to report
        if (accessionUsed) {
          if (recordType.contains("initial")) {
            if (recordType.contains("Patient")) {
              results = AccessionNumberUtil.isPatientStatusValid(accessionNumber,
                      RecordStatus.NotRegistered);
              if (results != PATIENT_STATUS_FAIL) {
                results = matchExistingStudyFormName(accessionNumber, studyFormName, false);
              }
            } else if (recordType.contains("Sample")) {
              results = AccessionNumberUtil.isSampleStatusValid(accessionNumber,
                      RecordStatus.NotRegistered);
              if (results != SAMPLE_STATUS_FAIL) {
                results = matchExistingStudyFormName(accessionNumber, studyFormName, false);
              }
            }
          } else if (recordType.contains("double")) {
            if (recordType.contains("Patient")) {
              results = AccessionNumberUtil.isPatientStatusValid(accessionNumber,
                      RecordStatus.InitialRegistration);
              if (results != PATIENT_STATUS_FAIL) {
                results = matchExistingStudyFormName(accessionNumber, studyFormName, true);
              }
            } else if (recordType.contains("Sample")) {
              results = AccessionNumberUtil.isSampleStatusValid(accessionNumber,
                      RecordStatus.InitialRegistration);
              if (results != SAMPLE_STATUS_FAIL) {
                results = matchExistingStudyFormName(accessionNumber, studyFormName, true);
              }
            }
          } else if (recordType.contains("orderModify")) {
            results = ValidationResults.USED_FAIL;
          }
        } else {
          if (recordType.contains("initial")) {
            results = ValidationResults.SAMPLE_NOT_FOUND;    // initial entry not used is good
          } else if (recordType.contains("double")) {
            results = ValidationResults.REQUIRED_FAIL;       // double entry not existing is a
                                                             // problem
          } else if (recordType.contains("orderModify")) {
            results = ValidationResults.SAMPLE_NOT_FOUND;    // modify order page
          }
        }
      }
    }
    return results;
  }

	/**
	 * Can the existing accession number be used in the given form?
	 * This method is useful when we have an existing accessionNumber and want to ask the question.
     * @param accessionNumber
	 * @param existingRequired true => it is required that there is an existing studyFormName?
     * @return
     */
    private static ValidationResults matchExistingStudyFormName(String accessionNumber, String studyFormName, boolean existingRequired) {
        if (GenericValidator.isBlankOrNull(studyFormName)) {
            return SAMPLE_FOUND;
        }
        String existingName = findStudyFormName(accessionNumber);
        if (existingName.equals(studyFormName) || (!existingRequired && GenericValidator.isBlankOrNull(existingName))) {
            return SAMPLE_FOUND;
        }
        return SAMPLE_STATUS_FAIL;  // the sample was entered on a different form!
    }

    public static String findStudyFormName(String accessionNumber) {
        ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();
        StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);
        Patient p = new Patient();
        p.setId(statusSet.getPatientId());
        Sample s = new Sample();
        s.setId(statusSet.getSampleId());
        List<ObservationHistory> all = ohDAO.getAll(p, s, ObservationHistoryTypeMap.getInstance().getIDForType("projectFormName"));
        String existingName = "";
        if (all.size() > 0) {
            existingName = all.get(0).getValue();
        }
        return existingName;
    }

	@Override
	public int getInvarientLength() {
		return PROGRAM_END;
	}

	@Override
	public int getChangeableLength() {
		return getMaxAccessionLength() - getInvarientLength();
	}

    @Override
    public String getPrefix(){
        return null; //no single prefix
    }

    private static ProjectDAO getProjectDAO() {
		if( projectDAO == null){
			projectDAO = new ProjectDAOImpl();
		}
		
		return projectDAO;
	}

	public static void setProjectDAO(ProjectDAO projectDAO) {
		ProgramAccessionValidator.projectDAO = projectDAO;
	}
}
