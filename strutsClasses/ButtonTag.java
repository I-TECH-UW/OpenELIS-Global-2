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
package us.mn.state.health.lims.taglib;

import javax.servlet.jsp.JspException;

import us.mn.state.health.lims.common.util.StringUtil;

/**
 * Renders an HTML BUTTON tag within the Struts framework.
 * 
 * @version $Rev: 54954 $ $Date: 2008/10/09 15:58:47 $
 */
public class ButtonTag extends org.apache.struts.taglib.html.ButtonTag {

	private String allowEdits = "true";

	/**
	 * Constructor for TextTag.
	 */
	public ButtonTag() {
		super();
	}

	/**
	 * Generate the required input tag.
	 * <p>
	 * Support for indexed property since Struts 1.1
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {

		if (isAllowEdits()) {
			setDisabled(false);
		} else {
			setDisabled(true);
		}

		return super.doStartTag();

	}

	/**
	 * Returns the allowEdits.
	 * 
	 * @return boolean
	 */
	public String getAllowEdits() {
		return allowEdits;
	}

	/**
	 * Returns the allowEdits.
	 * 
	 * @return boolean
	 */
	public boolean isAllowEdits() {
		if (StringUtil.isNullorNill(getAllowEdits())) {
			return true;
		}
		return getAllowEdits().equals("true");
	}

	public void setAllowEdits(boolean allowEdits) {
		this.allowEdits = (allowEdits ? "true" : "false");
	}

	/**
	 * Sets the allowEdits.
	 * 
	 * @param allowEdits
	 *            The allowEdits to set
	 */
	public void setAllowEdits(String allowEdits) {
		this.allowEdits = allowEdits;
	}


}
