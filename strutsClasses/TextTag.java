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

import org.apache.struts.taglib.TagUtils;

import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class TextTag extends org.apache.struts.taglib.html.TextTag {

	private String allowEdits = "true";

	/**
	 * Constructor for TextTag.
	 */
	public TextTag() {
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
		TagUtils tagUtils = TagUtils.getInstance();
		if (isAllowEdits()) {
			return super.doStartTag();
		} else {
			// Look up the requested property value
			Object value =
			// RequestUtils.lookup(pageContext, name, property, null);
			tagUtils.lookup(pageContext, name, property, null);

			if (value == null)
				return (SKIP_BODY); // Nothing to output
			String output = value.toString();
			if (getStyleClass() != null) {
				output = "<span class=" + getStyleClass() + ">" + output
						+ "</span>";
			}
			output = output + "\r\n" + "<INPUT type=\"hidden\"  name=\""
					+ getProperty() + "\"" + " value=\"" + value.toString()
					+ "\"" + ">";
			// ResponseUtils.write(pageContext, output);
			tagUtils.write(pageContext, output);
		}
		return (EVAL_BODY_BUFFERED);
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
