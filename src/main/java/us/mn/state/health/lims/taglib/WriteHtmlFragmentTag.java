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

/**
 * @author Benzd1 
 * bugzilla 2569
 */
public class WriteHtmlFragmentTag extends WriteTag {


	// --------------------------------------------------------- Public Methods

	/**
	 * Process the start tag.
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		// Look up the requested bean (if necessary)
		if (ignore) {
			if (TagUtils.getInstance().lookup(pageContext, name, scope) == null) {
				return (SKIP_BODY); // Nothing to output
			}
		}

		// Look up the requested property value
		Object value = TagUtils.getInstance().lookup(pageContext, name,
				property, scope);

		if (value == null) {
			return (SKIP_BODY); // Nothing to output
		}

		// Convert value to the String with some formatting
		String output = formatValue(value);

		TagUtils.getInstance().write(pageContext, output);

		// Continue processing this page
		return (SKIP_BODY);

	}


}
