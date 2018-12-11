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
package us.mn.state.health.lims.testanalyte.form;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.beanutils.ConversionException;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.log.LogEvent;

//We have some dynamically (through javascript) created collections that need to be initialized
public class TestAnalyteTestResultActionForm extends BaseActionForm {

	private Hashtable tableOfLists;// dynamic, nested and indexed property

	private ArrayList aList;

	public TestAnalyteTestResultActionForm() {
		tableOfLists = new Hashtable();
		tableOfLists.put("selectedAnalyteIds", new ArrayList());
		tableOfLists.put("selectedAnalyteNames", new ArrayList());
		tableOfLists.put("selectedAnalyteTypes", new ArrayList());
		//bugzilla 1870
		tableOfLists.put("selectedAnalyteIsReportables", new ArrayList());
		tableOfLists.put("selectedAnalyteResultGroups", new ArrayList());
		tableOfLists.put("selectedTestAnalyteIds", new ArrayList());
		tableOfLists.put("testAnalyteLastupdatedList", new ArrayList());


		tableOfLists.put("testResultValueList", new ArrayList());
		tableOfLists.put("testResultResultGroups", new ArrayList());
		tableOfLists.put("testResultResultGroupTypes", new ArrayList());
		tableOfLists.put("dictionaryEntryIdList", new ArrayList());
		tableOfLists.put("flagsList", new ArrayList());
		//bugzilla 1845 added testResult sortOrder
		tableOfLists.put("sortList", new ArrayList());
		tableOfLists.put("significantDigitsList", new ArrayList());
		tableOfLists.put("quantLimitList", new ArrayList());
		tableOfLists.put("testResultIdList", new ArrayList());
		tableOfLists.put("testResultLastupdatedList", new ArrayList());

	}

	private void increaseSize(List l, int index) {
		//System.out.println("I am in increaseSize(List l, int index)");
		while (l.size() < index + 1) {
			l.add("");
		}
	}

	public Object getAList(String name, int index) {
		//System.out.println("I am in getAList(String name, int index)");
		aList = (ArrayList) tableOfLists.get(name);
		while (index >= aList.size()) {
			aList.add("");
		}
		tableOfLists.put(name, aList);
		return (String) aList.get(index);
	}

	// Setter Method
	public void setAList(String name, int index, Object object) {
		//System.out
				//.println("I am in setAList(String name, int index, Object object) with "
						//+ name + " " + index + " " + object);
		aList = (ArrayList) tableOfLists.get(name);

		if (index < aList.size()) {
			aList.set(index, object);
		} else {
			increaseSize(aList, index);
			aList.set(index, object);
		}

		tableOfLists.put(name, aList);
		// System.out.println("setAList setting " + name + aList.size());
		set(name, aList);
	}

	/**
	 * <p>
	 * Return the value of an indexed property with the specified name.
	 * </p>
	 * 
	 * @param name
	 *            Name of the property whose value is to be retrieved
	 * @param index
	 *            Index of the value to be retrieved
	 * 
	 * @exception IllegalArgumentException
	 *                if there is no property of the specified name
	 * @exception IllegalArgumentException
	 *                if the specified property exists, but is not indexed
	 * @exception IndexOutOfBoundsException
	 *                if the specified index is outside the range of the
	 *                underlying property
	 * @exception NullPointerException
	 *                if no array or List has been initialized for this property
	 */
	public Object get(String name, int index) {
		//System.out.println("I am in get(String name, int index)");
		Object value = dynaValues.get(name);

		if (value == null) {
			throw new NullPointerException("No indexed value for '" + name
					+ "[" + index + "]'");
		} else if (value.getClass().isArray()) {
			return (Array.get(value, index));
		} else if (value instanceof List) {
			Object o = null;
			// here is override!!!!!
			o = getAList(name, index);
			return o;
		} else {
			throw new IllegalArgumentException("Non-indexed property for '"
					+ name + "[" + index + "]'");
		}

	}

	/**
	 * <p>
	 * Set the value of an indexed property with the specified name.
	 * </p>
	 * 
	 * @param name
	 *            Name of the property whose value is to be set
	 * @param index
	 *            Index of the property to be set
	 * @param value
	 *            Value to which this property is to be set
	 * 
	 * @exception ConversionException
	 *                if the specified value cannot be converted to the type
	 *                required for this property
	 * @exception IllegalArgumentException
	 *                if there is no property of the specified name
	 * @exception IllegalArgumentException
	 *                if the specified property exists, but is not indexed
	 * @exception IndexOutOfBoundsException
	 *                if the specified index is outside the range of the
	 *                underlying property
	 */
	public void set(String name, int index, Object value) {
		//System.out
				//.println("I am in set(String name, int index, Object value) with "
						//+ name + " " + index + " " + value);
		Object prop = dynaValues.get(name);
		if (prop == null) {
			throw new NullPointerException("No indexed value for '" + name
					+ "[" + index + "]'");
		} else if (prop.getClass().isArray()) {
			Array.set(prop, index, value);
		} else if (prop instanceof List) {
			try {
				setAList(name, index, value);
			} catch (ClassCastException e) {
    			//bugzilla 2154
			    LogEvent.logError("TestAnalyteTestResultActionForm","set()",e.getMessage());
				throw new ConversionException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException("Non-indexed property for '"
					+ name + "[" + index + "]'");
		}

	}

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void resetLists() {
		//System.out.println("I am in resetLists()");
		tableOfLists = new Hashtable();
		tableOfLists.put("selectedAnalyteIds", new ArrayList());
		tableOfLists.put("selectedAnalyteNames", new ArrayList());
		tableOfLists.put("selectedAnalyteTypes", new ArrayList());
		//bugzilla 1870
		tableOfLists.put("selectedAnalyteIsReportables", new ArrayList());
		tableOfLists.put("selectedAnalyteResultGroups", new ArrayList());
		tableOfLists.put("selectedTestAnalyteIds", new ArrayList());
		tableOfLists.put("testAnalyteLastupdatedList", new ArrayList());

		tableOfLists.put("testResultValueList", new ArrayList());
		tableOfLists.put("testResultResultGroups", new ArrayList());
		tableOfLists.put("testResultResultGroupTypes", new ArrayList());
		tableOfLists.put("dictionaryEntryIdList", new ArrayList());
		tableOfLists.put("flagsList", new ArrayList());
    	//bugzilla 1845 added testResult sortOrder
		tableOfLists.put("sortList", new ArrayList());
		tableOfLists.put("significantDigitsList", new ArrayList());
		tableOfLists.put("quantLimitList", new ArrayList());
		tableOfLists.put("testResultIdList", new ArrayList());
		tableOfLists.put("testResultLastupdatedList", new ArrayList());

	}

	/**
	 * <p>
	 * Return the value of a simple property with the specified name.
	 * </p>
	 * 
	 * @param name
	 *            Name of the property whose value is to be retrieved
	 * 
	 * @exception IllegalArgumentException
	 *                if there is no property of the specified name
	 * @exception NullPointerException
	 *                if the type specified for the property is invalid
	 */
	public Object get(String name) {

		// Return any non-null value for the specified property
		Object value = dynaValues.get(name);

		//System.out.println("I am in get(String name) " + name + " " + value);
		if (value != null) {
			return (value);
		}

		// Return a null value for a non-primitive property
		Class type = getDynaProperty(name).getType();
		if (type == null) {
			throw new NullPointerException("The type for property " + name
					+ " is invalid");
		}
		if (!type.isPrimitive()) {
			return (value);
		}

		// Manufacture default values for primitive properties
		if (type == Boolean.TYPE) {
			return (Boolean.FALSE);
		} else if (type == Byte.TYPE) {
			return (new Byte((byte) 0));
		} else if (type == Character.TYPE) {
			return (new Character((char) 0));
		} else if (type == Double.TYPE) {
			return (new Double(0.0));
		} else if (type == Float.TYPE) {
			return (new Float((float) 0.0));
		} else if (type == Integer.TYPE) {
			return (new Integer(0));
		} else if (type == Long.TYPE) {
			return (new Long(0));
		} else if (type == Short.TYPE) {
			return (new Short((short) 0));
		} else {
			return (null);
		}

	}

}