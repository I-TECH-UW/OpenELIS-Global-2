package org.openelisglobal.test.validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openelisglobal.common.JSONUtils;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.test.form.BatchTestReassignmentForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BatchTestReassignmentFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BatchTestReassignmentForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BatchTestReassignmentForm form = (BatchTestReassignmentForm) target;

        try {
            JSONObject batchTest = JSONUtils.getAsObject(form.getJsonWad());
            if (!JSONUtils.isEmpty(batchTest)) {

                // done by test name, not id
                // ValidationHelper.validateIdField(String.valueOf(batchTest.get("current")),
                // "JsonWad", "current", errors,
                // true);

                // done by sampleType name, not id
                // ValidationHelper.validateIdField(String.valueOf(batchTest.get("sampleType")),
                // "JsonWad", "sampleType", errors,
                // true);

                JSONArray replacements = JSONUtils.getAsArray(batchTest.get("replace"));
                for (int i = 0; i < replacements.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(replacements.get(i)), "JsonWad",
                            "replace[" + i + "]", errors, true);
                }

                JSONArray changesNotStarted = JSONUtils.getAsArray(batchTest.get("changeNotStarted"));
                for (int i = 0; i < changesNotStarted.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(changesNotStarted.get(i)), "JsonWad",
                            "changeNotStarted[" + i + "]", errors, true);
                }

                JSONArray noChangesNotStarted = JSONUtils.getAsArray(batchTest.get("noChangeNotStarted"));
                for (int i = 0; i < noChangesNotStarted.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(noChangesNotStarted.get(i)), "JsonWad",
                            "noChangeNotStarted[" + i + "]", errors, true);
                }

                JSONArray changesTechReject = JSONUtils.getAsArray(batchTest.get("changeTechReject"));
                for (int i = 0; i < changesTechReject.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(changesTechReject.get(i)), "JsonWad",
                            "changeTechReject[" + i + "]", errors, true);
                }

                JSONArray noChangesTechReject = JSONUtils.getAsArray(batchTest.get("noChangeTechReject"));
                for (int i = 0; i < noChangesTechReject.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(noChangesTechReject.get(i)), "JsonWad",
                            "noChangeTechReject[" + i + "]", errors, true);
                }

                JSONArray changesBioReject = JSONUtils.getAsArray(batchTest.get("changeBioReject"));
                for (int i = 0; i < changesBioReject.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(changesBioReject.get(i)), "JsonWad",
                            "changeBioReject[" + i + "]", errors, true);
                }

                JSONArray noChangesBioReject = JSONUtils.getAsArray(batchTest.get("noChangeBioReject"));
                for (int i = 0; i < noChangesBioReject.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(noChangesBioReject.get(i)), "JsonWad",
                            "noChangeBioReject[" + i + "]", errors, true);
                }

                JSONArray changesNotValidated = JSONUtils.getAsArray(batchTest.get("changeNotValidated"));
                for (int i = 0; i < changesNotValidated.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(changesNotValidated.get(i)), "JsonWad",
                            "changeNotValidated[" + i + "]", errors, true);
                }

                JSONArray noChangesNotValidated = JSONUtils.getAsArray(batchTest.get("noChangeNotValidated"));
                for (int i = 0; i < noChangesNotValidated.size(); ++i) {
                    ValidationHelper.validateIdField(String.valueOf(noChangesNotValidated.get(i)), "JsonWad",
                            "noChangeNotValidated[" + i + "]", errors, true);
                }
            }

        } catch (ParseException jsExcp) {
            errors.rejectValue("jsonWad", "error.field.json.format.invalid");
        }
    }

}
