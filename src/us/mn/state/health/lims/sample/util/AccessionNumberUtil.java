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
package us.mn.state.health.lims.sample.util;

import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.PATIENT_STATUS_FAIL;
import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.SAMPLE_FOUND;
import static us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults.SAMPLE_STATUS_FAIL;

import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.provider.validation.ProgramAccessionValidator;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.services.StatusSet;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class AccessionNumberUtil {

	private static IAccessionNumberValidator accessionNumberValidator;

	public static IAccessionNumberValidator getAccessionNumberValidator() {
		if( accessionNumberValidator == null){
			try {
				accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
			} catch (LIMSInvalidConfigurationException e) {
				LogEvent.logFatal("AccessionNumberUtil", "getAccessionNumberValidator", e.toString());
			}
		}

		return accessionNumberValidator;
	}

	public static IAccessionNumberValidator getAccessionNumberValidator(String prefix) {
		if( GenericValidator.isBlankOrNull(prefix)) {
			return getAccessionNumberValidator();
		}
		try {
			return new AccessionNumberValidatorFactory().getValidator("PROGRAMNUM");
		} catch (LIMSInvalidConfigurationException e) {
			LogEvent.logFatal("AccessionNumberUtil", "getAccessionNumberValidator", e.toString());
		}

		return accessionNumberValidator;
	}

	public static String getNextAccessionNumber(String optionalPrefix) throws IllegalStateException{
		return getAccessionNumberValidator(optionalPrefix).getNextAvailableAccessionNumber(optionalPrefix);
	}

	public static String getInvalidMessage(ValidationResults result){
		return getAccessionNumberValidator().getInvalidMessage(result);
	}

    public static String getInvalidFormatMessage(ValidationResults result){
        return getAccessionNumberValidator().getInvalidFormatMessage(result);
    }

	public static boolean needProgramCode(){
		return getAccessionNumberValidator().needProgramCode();
	}

	public static ValidationResults checkAccessionNumberValidity(String accessionNumber, String recordType, String isRequired, String projectFormName){

		try {
			return getAccessionNumberValidator().checkAccessionNumberValidity(accessionNumber, recordType, isRequired, projectFormName);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return ValidationResults.SAMPLE_NOT_FOUND;
	}

	public static ValidationResults isPatientStatusValid(String accessionNumber, RecordStatus validStatus) {
	    StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);
    	if ( statusSet.getPatientRecordStatus() == validStatus ) {
            return SAMPLE_FOUND;
        } else {
            return PATIENT_STATUS_FAIL;
        }
    }


    public static ValidationResults isSampleStatusValid(String accessionNumber, RecordStatus validStatus) {
        StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNumber);
        RecordStatus sampleRecordStatus = statusSet.getSampleRecordStatus();
        if ( sampleRecordStatus == validStatus ) {
            return SAMPLE_FOUND;
        } else {
            return SAMPLE_STATUS_FAIL;
        }
    }
    
    public static int getInvarientLength(){
    	return getAccessionNumberValidator().getInvarientLength();
    }
    
    public static int getChangeableLength(){
    	return getAccessionNumberValidator().getChangeableLength();
    }
    
    public static int getMaxLength(){
    	return getAccessionNumberValidator().getMaxAccessionLength();
    }
    
    public static ValidationResults correctFormat(String accessionNumber, boolean validateYear){
    	return getAccessionNumberValidator().validFormat(accessionNumber, validateYear);
    }
    
    public static boolean isUsed( String accessionNumber){
    	return getAccessionNumberValidator().accessionNumberIsUsed(accessionNumber, null);
    }
    
    public static String getAccessionNumberFromSampleItemAccessionNumber( String accessionNumber){
    	int lastDash = accessionNumber.lastIndexOf('-');
		return accessionNumber.substring(0, lastDash);
    }
    
    public static boolean isProjectAccessionNumber( String accessionNumber) {
      if (StringUtil.isNullorNill(ProgramAccessionValidator.findStudyFormName(accessionNumber))) {
        return false;
      }
      return true;
    }
}
