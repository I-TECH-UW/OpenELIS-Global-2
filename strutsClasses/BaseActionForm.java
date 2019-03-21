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
package us.mn.state.health.lims.common.action;

import org.apache.struts.validator.DynaValidatorForm;

public class BaseActionForm extends DynaValidatorForm {

	private static final long serialVersionUID = 1L;

//	private String currentDate = null;

	public BaseActionForm() {
		/*
		 * System.out.println("Here I am in BaseActionForm "); try {
		 * System.out.println("This is action form " + getDynaProperty("name")); }
		 * catch (NullPointerException npe){} try { System.out.println("This is
		 * action form " + getDynaProperty("description")); } catch
		 * (NullPointerException npe){}
		 */
		/*
		 * super(); String strLocale =
		 * SystemConfiguration.getInstance().getDefaultLocale() .toString();
		 * Locale locale = new Locale(strLocale); String pattern =
		 * ResourceLocator.getInstance().getMessageResources()
		 * .getMessage(locale, "date.format.formatKey");
		 * System.out.println("This is date pattern " + pattern);
		 * System.out.println("This is locale " + locale); SimpleDateFormat
		 * formatter = new SimpleDateFormat(pattern, locale); Date today = new
		 * Date(); String currentDate = formatter.format(today);
		 * System.out.println("currentDate " + currentDate);
		 * System.out.println("stuff " + this.getMap());
		 */
	}

	// overriding DynaActionForm method for debugging
	
	 /* public DynaProperty getDynaProperty(String name) { //
	  System.out.println("I am in getDynaProperty " + name); 
	  DynaProperty descriptor = getDynaClass().getDynaProperty(name); 
	  if (descriptor ==	  null) { 
		  throw new IllegalArgumentException("Invalid property name '" +	  name + "'"); } 
	  // System.out.println("Returning " + descriptor); //
	  System.out.println("This is value " + get(descriptor.toString())); 
	  return (descriptor); }*/
	 
	
	/*
	 * public ActionErrors validate(ActionMapping mapping, HttpServletRequest
	 * request) { ActionErrors errors = super.validate(mapping, request);
	 * System.out.println("Here I am"); if (errors == null) errors = new
	 * ActionErrors(); System.out.println("Here I am2"); if (errors.isEmpty()) {
	 * System.out.println("Here I am3"); if (true){ System.out.println("Here I
	 * am4"); errors.add(ActionErrors.GLOBAL_ERROR, new
	 * ActionError("errors.OptimisticLockException", "organizationName")); } }
	 * 
	 * if (errors.isEmpty()) return null; return errors; }
	 */
	
	/*
	 * public String getCurrentDate() { return currentDate; }
	 * 
	 * public void setCurrentDate(String currentDate) { this.currentDate =
	 * currentDate; }
	 */
}