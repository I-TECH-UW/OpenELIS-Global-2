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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class LinkTag extends org.apache.struts.taglib.html.LinkTag {

	/**
	 * Constructor for LinkTag.
	 */
	public LinkTag() {
		super();
	}

	public int doStartTag() throws JspException {

		if ((null != getForward() || null != getHref() || null != getPage())
				&& null != getAction()) {
			setAction(null);
		}

		String contextPath = ((HttpServletRequest) pageContext.getRequest())
				.getContextPath();
		String url = null;
		try {
		 url = URLDecoder.decode(calculateURL(), SystemConfiguration.getInstance().getDefaultEncoding());
		} catch (UnsupportedEncodingException uee) {
    		//bugzilla 2154
			LogEvent.logError("LinkTag","doStartTag()",uee.toString());
			throw new JspException();
		}
		// Remove Session ID
		int sessionIndex = url.indexOf("/");
		if (sessionIndex >= 0) {
			int queryIndex = url.indexOf('?');
			int length = url.length();
			if (queryIndex > sessionIndex) {
				length = queryIndex;
			}
			String sessionid = url.substring(sessionIndex, length);
			url = StringUtil.replace(url, sessionid, "");
		}
		url = StringUtil.replace(url, contextPath + "/action/", "");
		setName(null);
		setProperty(null);
		setPage(null);
		setAnchor(null);
		setForward(null);
		setHref(null);
		setAction("Forward?fwd=" + url);
		setParamId(null);
		setParamName(null);
		setParamProperty(null);
		String formName = (String) pageContext.getRequest().getAttribute(
				"formName");
		String forwardURL = "Forward?fwd=" + url;
		forwardURL = ((HttpServletResponse) pageContext.getResponse())
				.encodeURL(forwardURL);
		setOnclick("return forwardAction(window.document.forms['" + formName
				+ "'], '" + forwardURL + "', '" + url + "');");
		return super.doStartTag();
	}

}
