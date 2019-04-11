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

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ActionError extends org.apache.struts.action.ActionMessage {

	private String formField;

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 */
	/*
	 * **** WARNING **** Be warned that if you try to create an ActionError
	 * object by trying to instantiate this object by using the constructor in
	 * struts.action.ActionError(String key, Object value0), it will not work if
	 * value0 is a String (or not casted into an Object). This is because the
	 * following constructor will get found instead. You can either instantiate
	 * the parent, call the three parameter constructor passing null for
	 * formField.
	 */
	public ActionError(String key, String formField) {
		super(key);
		setFormField(formField);
	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 */
	public ActionError(String key) {
		super(key);

	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param value0
	 */
	public ActionError(String key, Object value0) {
		super(key, value0);

	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param value0
	 */
	public ActionError(String key, Object value0, String formField) {
		super(key, value0);
		setFormField(formField);
	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param value0
	 * @param value1
	 */
	public ActionError(String key, Object value0, Object value1,
			String formField) {
		super(key, value0, value1);
		setFormField(formField);
	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param value0
	 * @param value1
	 * @param value2
	 */
	public ActionError(String key, Object value0, Object value1, Object value2,
			String formField) {
		super(key, value0, value1, value2);
		setFormField(formField);
	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param value0
	 * @param value1
	 * @param value2
	 * @param value3
	 */
	public ActionError(String key, Object value0, Object value1, Object value2,
			Object value3, String formField) {
		super(key, value0, value1, value2, value3);
		setFormField(formField);
	}

	/**
	 * Constructor for ActionError.
	 * 
	 * @param key
	 * @param values
	 */
	public ActionError(String key, Object[] values, String formField) {
		super(key, values);
		setFormField(formField);
	}

	/**
	 * Returns the formField.
	 * 
	 * @return String
	 */
	public String getFormField() {
		return formField;
	}

	/**
	 * Sets the formField.
	 * 
	 * @param formField
	 *            The formField to set
	 */
	public void setFormField(String formField) {
		this.formField = formField;
	}

}
