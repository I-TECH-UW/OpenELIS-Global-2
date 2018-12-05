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
*/

package us.mn.state.health.lims.common.util.validator;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.Resources;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;

/**
 * <p>
 * This class contains the default validations that are used in the
 * validator-rules.xml file.
 * </p>
 * <p>
 * In general passing in a null or blank will return a null Object or a false
 * boolean. However, nulls and blanks do not result in an error being added to
 * the errors.
 * </p>
 * 
 * @since Struts 1.1
 */
public class FieldChecks extends org.apache.struts.validator.FieldChecks {

	/**
	 * Commons Logging instance.
	 */

	/**
	 * Checks if the field is a valid date. If the field has a datePattern
	 * variable, that will be used to format
	 * <code>java.text.SimpleDateFormat</code>. If the field has a
	 * datePatternStrict variable, that will be used to format
	 * <code>java.text.SimpleDateFormat</code> and the length will be checked
	 * so '2/12/1999' will not pass validation with the format 'MM/dd/yyyy'
	 * because the month isn't two digits. If no datePattern variable is
	 * specified, then the field gets the DateFormat.SHORT format for the
	 * locale. The setLenient method is set to <code>false</code> for all
	 * variations.
	 * 
	 * @param bean
	 *            The bean validation is being performed on.
	 * @param va
	 *            The <code>ValidatorAction</code> that is currently being
	 *            performed.
	 * @param field
	 *            The <code>Field</code> object associated with the current
	 *            field being validated.
	 * @param errors
	 *            The <code>ActionMessages</code> object to add errors to if
	 *            any validation errors occur.
	 * @param validator
	 *            The <code>Validator</code> instance, used to access other
	 *            field values.
	 * @param request
	 *            Current request object.
	 * @return true if valid, false otherwise.
	 */
	public static Object validateDate(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		Object result = null;
		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		String datePattern = field.getVarValue("datePattern");
		String datePatternStrict = field.getVarValue("datePatternStrict");
		Locale locale = RequestUtils.getUserLocale(request, null);

		// this is added to use message key
		String datePatternForResources = ResourceLocator.getInstance()
				.getMessageResources().getMessage(locale, datePattern);
		if (datePatternForResources != null) {
			datePattern = datePatternForResources;
		}

		datePatternForResources = ResourceLocator.getInstance()
				.getMessageResources().getMessage(locale, datePatternStrict);
		if (datePatternForResources != null) {
			datePatternStrict = datePatternForResources;
		}

		if (GenericValidator.isBlankOrNull(value)) {
			return Boolean.TRUE;
		}

		try {
			if (datePattern != null && datePattern.length() > 0) {
				result = GenericTypeValidator.formatDate(value, datePattern,
						false);
			} else if (datePatternStrict != null
					&& datePatternStrict.length() > 0) {
				result = GenericTypeValidator.formatDate(value,
						datePatternStrict, true);
			} else {
				result = GenericTypeValidator.formatDate(value, locale);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("FieldChecks","validateDate()",e.toString());
		}

		if (result == null) {
			errors.add(field.getKey(), Resources.getActionMessage(validator,
					request, va, field));
		}

		return result == null ? Boolean.FALSE : result;
	}
	
	/**
	 * Checks if the field is a valid time HH:MM. 
	 * @return true if valid, false otherwise.
	 */
	public static boolean validateTime(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		
	       String value = null;
	        if (isString(bean)) {
	            value = (String) bean;
	        } else {
	            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
	        }
	        
	        try {
	        String hours = value.substring(0, 2);
	        String minutes = value.substring(3);
	        
	        int hh = Integer.parseInt(hours);
	        int mm = Integer.parseInt(minutes);

	        if (!GenericValidator.isInRange(hh, 0, 23)) {
	            errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
	            return false;
	        } else if (!GenericValidator.isInRange(mm, 0, 59)){
	            errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
	            return false;
	        } else {
	            return true;
	        }
	        } catch (Exception ex) {
                //bugzilla 2154
				LogEvent.logError("FieldChecks","validateTime()",ex.toString());
	        	return false;
	        }
	}


}
