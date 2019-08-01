package org.openelisglobal.testconfiguration.validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.openelisglobal.testconfiguration.form.PanelOrderForm;
import org.openelisglobal.common.JSONUtils;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.common.log.LogEvent;

@Component
public class PanelOrderFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PanelOrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PanelOrderForm form = (PanelOrderForm) target;

        try {
            JSONObject changeList = JSONUtils.getAsObject(form.getJsonChangeList());

            JSONArray panels = JSONUtils.getAsArray(changeList.get("panels"));
            for (int i = 0; i < panels.size(); ++i) {
                JSONObject panel = JSONUtils.getAsObject(panels.get(i));

                ValidationHelper.validateIdField(String.valueOf(panel.get("id")), "JsonChangeList",
                        "panels[" + i + "] id", errors, true);

                ValidationHelper.validateFieldAndCharset(String.valueOf(panel.get("sortOrder")), "JsonChangeList",
                        "panels[" + i + "] sort order", errors, true, 3, "0-9");
            }
        } catch (ParseException | ClassCastException e) {
            LogEvent.logError("PanelOrderFormValidator", "validate()", e.toString());
            errors.rejectValue("jsonChangeList", "error.field.format.json", e.getMessage());
        }

    }

}
