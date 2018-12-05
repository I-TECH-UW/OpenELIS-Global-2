package us.mn.state.health.lims.result.action.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionErrors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.provider.validation.DateValidationProvider;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class ResultsValidation {

	private static final String SPECIAL_CASE = "XXXX";
	private boolean supportReferrals = FormFields.getInstance().useField( Field.ResultsReferral );
	private boolean useTechnicianName = ConfigurationProperties.getInstance().isPropertyValueEqual( Property.resultTechnicianName, "true");
	private boolean noteRequiredForChangedResults = false;
	private boolean useRejected = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.allowResultRejection, "true");
	
	private static ResultDAO resultDAO = new ResultDAOImpl();
	
	public  List<ActionError> validateItem(TestResultItem item ) {
		List<ActionError> errors = new ArrayList<ActionError>();

		validateTestDate(item, errors);

		if (!item.isRejected())
		validateResult(item, errors);

		if( noteRequiredForChangedResults && !item.isRejected()) {
			validateRequiredNote( item, errors);
		}
		
		if (supportReferrals) {
			validateReferral(item, errors);
		}
		if (useTechnicianName) {
			validateTesterSignature(item, errors);
		}
		if (useRejected) {
		    validateRejection(item, errors);
		}

		return errors;
	}
	

	public ActionErrors validateModifiedItems(List<TestResultItem> modifiedItems) {
		noteRequiredForChangedResults = "true".equals(ConfigurationProperties.getInstance().getPropertyValue(Property.notesRequiredForModifyResults));
		
		ActionErrors errors = new ActionErrors();

		for (TestResultItem item : modifiedItems) {

			List<ActionError> itemErrors = validateItem(item );

			if (itemErrors.size() > 0) {
				StringBuilder augmentedAccession = new StringBuilder(item.getSequenceAccessionNumber());
				augmentedAccession.append(" : ");
				augmentedAccession.append(item.getTestName());
				ActionError accessionError = new ActionError("errors.followingAccession", augmentedAccession);
				errors.add(ActionErrors.GLOBAL_MESSAGE, accessionError);

				for (ActionError error : itemErrors) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				}

			}
		}

		return errors;
	}

	
	private void validateTestDate(TestResultItem item, List<ActionError> errors) {

		DateValidationProvider dateValidator = new DateValidationProvider();
		Date date = dateValidator.getDate(item.getTestDate());

		if (date == null) {
			errors.add(new ActionError("errors.date", new StringBuilder(item.getTestDate())));
		} else if (!IActionConstants.VALID.equals(dateValidator.validateDate(date, DateValidationProvider.PAST))) {
			errors.add(new ActionError("error.date.inFuture"));
		}
	}
	
	private void validateResult(TestResultItem testResultItem, List<ActionError> errors) {
        String resultValue = testResultItem.getShadowResultValue();
	    if (GenericValidator.isBlankOrNull(resultValue) && testResultItem.isRejected())
	        return;

		if (!(ResultUtil.areNotes(testResultItem) || 
				(supportReferrals && ResultUtil.isReferred(testResultItem)) || 
				ResultUtil.areResults(testResultItem) || 
				ResultUtil.isForcedToAcceptance(testResultItem))) { 
			errors.add(new ActionError("errors.result.required"));
		}
		
		if (!GenericValidator.isBlankOrNull(resultValue) && "N".equals(testResultItem.getResultType())) {
			if( resultValue.equals(SPECIAL_CASE)){
				return;
			}
			try {
				Double.parseDouble(resultValue);
			} catch (NumberFormatException e) {
				errors.add(new ActionError("errors.number.format", new StringBuilder("Result")));
			}
		}
		
		if( testResultItem.isHasQualifiedResult() && GenericValidator.isBlankOrNull(testResultItem.getQualifiedResultValue())){
			errors.add(new ActionError("errors.missing.result.details", new StringBuilder("Result")));
		}
	}
	
	private void validateReferral(TestResultItem item, List<ActionError> errors) {
		if (item.isShadowReferredOut() && "0".equals(item.getReferralReasonId())) {
			errors.add(new ActionError("error.referral.noReason"));
		}
	}
	
	private void validateRequiredNote(TestResultItem item, List<ActionError> errors) {
		if( GenericValidator.isBlankOrNull(item.getNote())&&
			!GenericValidator.isBlankOrNull(item.getResultId())){

            if( resultHasChanged(item)){
                errors.add(new ActionError("error.requiredNote.missing"));
            }

		}
		
	}

    private boolean resultHasChanged( TestResultItem item ){

        if( TypeOfTestResultService.ResultType.isMultiSelectVariant( item.getResultType() )){
            List<Result> resultList = new AnalysisService( item.getAnalysisId() ).getResults();
            ArrayList<String> dictionaryIds = new ArrayList<String>( resultList.size() );
            for(Result result : resultList){
                dictionaryIds.add( result.getValue() );
            }
            if( !GenericValidator.isBlankOrNull( item.getMultiSelectResultValues() ) && !"{}".equals( item.getMultiSelectResultValues() ) ){
                JSONParser parser = new JSONParser();
                try{
                    int matchCount = 0;
                    JSONObject jsonResult = ( JSONObject ) parser.parse( item.getMultiSelectResultValues() );
                    for( Object key : jsonResult.keySet() ){
                        String[] ids = ( ( String ) jsonResult.get( key ) ).trim().split( "," );

                        for( String id : ids ){
                            if( dictionaryIds.contains( id )){
                                matchCount++;
                            }else{
                                return false;
                            }
                        }
                    }
                    return matchCount != dictionaryIds.size();
                }catch( ParseException e ){
                    e.printStackTrace();
                    return false;
                }
            }

        } else{
            Result dbResult = resultDAO.getResultById( item.getResultId() );
            return !item.getShadowResultValue().equals( dbResult.getValue() ) && !GenericValidator.isBlankOrNull( dbResult.getValue() );
        }

        return false;
    }

    private void validateTesterSignature(TestResultItem item, List<ActionError> errors) {
		// Conclusions may not need a signature. If the user changed the value
		// and then changed it back it will be
		// marked as dirty but the signature may still be blank.
		if ("0".equals(item.getResultId())) {
			return;
		}

		Result result = resultDAO.getResultById(item.getResultId());

		if (result != null && result.getAnalyte() != null && "Conclusion".equals(result.getAnalyte().getAnalyteName())) {
			if (result.getValue().equals(item.getShadowResultValue())) {
				return;
			}
		}

		if (GenericValidator.isBlankOrNull(item.getTechnician())) {
			errors.add(new ActionError("errors.signature.required"));
		}
	}

	private void validateRejection(TestResultItem item, List<ActionError> errors) {
        if (item.isRejected() && "0".equals(item.getRejectReasonId())) {
            errors.add(new ActionError("error.reject.noReason"));
        }
    }
}
