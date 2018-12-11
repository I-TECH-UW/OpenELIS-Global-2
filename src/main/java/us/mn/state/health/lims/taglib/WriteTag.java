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

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author Benzd1 bugzilla 1351
 * 
 */
public class WriteTag extends org.apache.struts.taglib.bean.WriteTag {

	protected String maxLength = null;

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

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

		// truncate to maxLength
		int truncateLength = 0;
		try {
			//bugzilla 2541 to eliminate NumberFormatException logging when not needed
			if (!StringUtil.isNullorNill(maxLength)) {
			  truncateLength = (new Integer(maxLength)).intValue();
			}
		} catch (NumberFormatException nfe) {
    		//bugzilla 2154
			LogEvent.logError("WriteTag","doStartTag()",nfe.toString());
		}
		if (truncateLength > 0) {
			if (output.length() > truncateLength) {
				// now find next space after truncateLength
				int indexOfNextSpaceForTruncation = output.indexOf(" ",
						truncateLength);
				//bugzilla 1399 fixed array index exception
				if (indexOfNextSpaceForTruncation >= 0) {
					output = output.substring(0, indexOfNextSpaceForTruncation);
				} else {
					output = output.substring(0, truncateLength);
				}
			}
		}

		// Print this property value to our output writer, suitably filtered
		if (filter) {
			TagUtils.getInstance().write(pageContext,
					TagUtils.getInstance().filter(output));
		} else {
			TagUtils.getInstance().write(pageContext, output);
		}

		// Continue processing this page
		return (SKIP_BODY);

	}

}
