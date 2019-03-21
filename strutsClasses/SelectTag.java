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
 * benzd1 bugzilla 1822 extend struts SelectTag to add allowEdits property
 * Custom tag that represents an HTML select element, associated with a
 * bean property specified by our attributes.  This tag must be nested
 * inside a form tag.
 *
 */
public class SelectTag extends org.apache.struts.taglib.html.SelectTag {


    // ----------------------------------------------------- Instance Variables


 
    /**
     * The value is used for security.
     */
	private String allowEdits = "true";


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of this select tag.
     * <p>
     * Support for indexed property since Struts 1.1
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

		TagUtils tagUtils = TagUtils.getInstance();
		if (isAllowEdits()) {
			return super.doStartTag();
		} else {
            setDisabled(true);
			return super.doStartTag();
		}
    }
    

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        allowEdits = null;

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
