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

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.SelectTag;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class OptionsCollectionTag extends org.apache.struts.taglib.html.OptionsCollectionTag {

	private String filterProperty;

	private String filterValue;

	private String[] filterProperties = null;

	private String[] filterValues;

	private String matchProperty;

	private String matchValue;

	private String[] matchProperties = null;

	private String[] matchValues;

	private String allowEdits = "true";

	private String showDefault = "true";
	
	private String maxLength;

	/**
	 * Constructor for OptionsCollection.
	 */
	public OptionsCollectionTag() {
		super();
	}

	/**
	 * Process the start of this tag.
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		// Acquire the select tag we are associated with
		SelectTag selectTag = (SelectTag) pageContext
				.getAttribute(Constants.SELECT_KEY);

		if (selectTag == null) {
			JspException e = new JspException(messages
					.getMessage("optionsCollectionTag.select"));
			TagUtils.getInstance().saveException(pageContext, e);
			throw e;
		}

		// Acquire the collection containing our options
		Object collection = TagUtils.getInstance().lookup(pageContext, name,
				property, null);

		if (collection == null) {
			JspException e = new JspException(messages
					.getMessage("optionsCollectionTag.collection"));
			TagUtils.getInstance().saveException(pageContext, e);
			throw e;
		}

		// Acquire an iterator over the options collection
		Iterator iter = getIterator(collection);

		//bugzilla 2154
		LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Filter Properties = " + filterProperty);
		LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Filter Values = " + filterValue);

		StringBuffer sb = new StringBuffer();

		if (isAllowEdits() && isShowDefault()) {
			addOption(sb, "", "", false);
		}

		boolean first = true;

		// Render the options
		while (iter.hasNext()) {

			Object bean = iter.next();
			// Get the label for this option
			Object beanLabel = getProperty(bean, label);
			// Get the value for this option
			Object beanValue = getProperty(bean, value);

			String stringLabel = beanLabel.toString();
			String stringValue = beanValue.toString();

			boolean filterOkay = true;
			boolean matchOkay = true;
			Object beanFilter;
			// We only check the filter and match properties if the current
			// option is NOT a match.
			if (!selectTag.isMatched(stringValue)) {
				if (filterProperties != null) {
					for (int i = 0; i < filterProperties.length; ++i) {
						try {								
							//bugzilla 2154
		                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Checking filter: " + filterValues[i]);		
							beanFilter = PropertyUtils.getNestedProperty(bean,
									filterProperties[i]);
                            //bugzilla 2154
							LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Bean property: " + beanFilter.toString());											
						} catch (Exception e) {
    						//bugzilla 2154
			                LogEvent.logError("OptionsCollectionTag","doStartTag()",e.toString());
							throw new JspException(
									"Failed to retrieve property from bean.", e);
						}
						if (beanFilter != null
								&& beanFilter.toString()
										.equals(filterValues[i])) {
							//bugzilla 2154
		                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Filter failed for " + filterProperties[i] + ".");		
							// Filter failed, so break
							filterOkay = false;
							break;
						}
					}
				}

				if (matchProperties != null) {
					for (int i = 0; i < matchProperties.length; ++i) {
						try {
							//bugzilla 2154
		                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Checking filter: " + matchProperties[i]	+ "!=" + matchValues[i]);		
									
							beanFilter = PropertyUtils.getNestedProperty(bean,
									matchProperties[i]);
							//bugzilla 2154
		                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Bean property: " + matchProperties[i] + "=" + beanFilter.toString());		
						} catch (Exception e) {
    						//bugzilla 2154
			                LogEvent.logError("OptionsCollectionTag","doStartTag()",e.toString());
							throw new JspException(
									"Failed to retrieve property from bean.", e);
						}

						if (beanFilter != null
								&& !beanFilter.toString()
										.equals(matchValues[i])) {
							//bugzilla 2154
		                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","Match failed for " + matchProperties[i] + ".");										
							// Match failed, so break
							matchOkay = false;
							break;
						}
					}
				}
			}

			if (filterOkay && matchOkay) {
				//bugzilla 2154
                LogEvent.logDebug("OptionsCollectionTag","doStartTag()","In OptionsTag label = " + stringLabel + " value = " + stringValue);										

				if (isAllowEdits()) {
					addOption(sb, stringLabel, stringValue, selectTag
							.isMatched(stringValue));
				} else {
				    //bugzilla 2154
                    LogEvent.logDebug("OptionsCollectionTag","doStartTag()","In OptionsTag is matched = " + selectTag.isMatched(stringValue));										
					if (selectTag.isMatched(stringValue)) {
						if (!first) {
							stringLabel = ", " + stringLabel;
						}
						first = false;
						if (getStyleClass() != null) {
							stringLabel = "<span class=" + getStyleClass()
									+ ">" + stringLabel + "</span>";
						}
						sb.append(stringLabel);
						sb.append("\r\n<INPUT type=\"hidden\"  name=\""
								+ selectTag.getProperty() + "\"" + " value=\""
								+ stringValue + "\"" + ">");
					}
				}
			}
		}
	    //bugzilla 2154
        LogEvent.logDebug("OptionsCollectionTag","doStartTag()","In OptionsTag output = " + sb.toString());										

		// Render this element to our writer
		TagUtils.getInstance().write(pageContext, sb.toString());

		return SKIP_BODY;
	}

	protected Object getProperty(Object bean, String property)
			throws JspException {

		// Get the value for this option
		Object beanValue = "";
		try {
			beanValue = PropertyUtils.getProperty(bean, property);
			if (beanValue == null) {
				beanValue = "";
			}
		} catch (IllegalAccessException e) {
    		//bugzilla 2154
            LogEvent.logError("OptionsCollectionTag","getProperty()",e.toString());
			JspException jspe = new JspException(messages.getMessage(
					"getter.access", value, bean));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		} catch (InvocationTargetException e) {
    		//bugzilla 2154
            LogEvent.logError("OptionsCollectionTag","getProperty()",e.toString());
			Throwable t = e.getTargetException();
			JspException jspe = new JspException(messages.getMessage(
					"getter.result", value, t.toString()));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		} catch (NoSuchMethodException e) {
    		//bugzilla 2154
            LogEvent.logError("OptionsCollectionTag","getProperty()",e.toString());
			JspException jspe = new JspException(messages.getMessage(
					"getter.method", value, bean));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		}

		if (!StringUtil.isNullorNill(maxLength)) {
			try {
			   int max = Integer.parseInt(maxLength);
			   String beanVal = beanValue.toString();
			   if (beanVal.length() > max) {
			     beanValue = beanVal.substring(0, max);
			   }
			} catch (Exception e) {
    			//bugzilla 2154
                LogEvent.logError("OptionsCollectionTag","getProperty()",e.toString());
              // don't process maxLength if there is a problem
			}
		}
		return beanValue;
	}

	/**
	 * Returns the filterProperty.
	 * 
	 * @return String
	 */
	public String getFilterProperty() {
		return filterProperty;
	}

	/**
	 * Returns the filterValue.
	 * 
	 * @return String
	 */
	public String getFilterValue() {
		return filterValue;
	}

	/**
	 * Sets the filterProperty.
	 * 
	 * @param filterProperty
	 *            The filterProperty to set
	 */
	public void setFilterProperty(String filterProperty) {
		this.filterProperty = filterProperty;
		filterProperties = StringUtil.toArray(filterProperty);
	}

	/**
	 * Sets the filterValue.
	 * 
	 * @param filterValue
	 *            The filterValue to set
	 */
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
		filterValues = StringUtil.toArray(filterValue);
	}

	/**
	 * Returns the matchProperty.
	 * 
	 * @return String
	 */
	public String getMatchProperty() {
		return matchProperty;
	}

	/**
	 * Returns the matchValue.
	 * 
	 * @return String
	 */
	public String getMatchValue() {
		return matchValue;
	}

	/**
	 * Sets the matchProperty.
	 * 
	 * @param matchProperty
	 *            The matchProperty to set
	 */
	public void setMatchProperty(String matchProperty) {
		this.matchProperty = matchProperty;
		matchProperties = StringUtil.toArray(matchProperty);
	}

	/**
	 * Sets the matchValue.
	 * 
	 * @param matchValue
	 *            The matchValue to set
	 */
	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
		matchValues = StringUtil.toArray(matchValue);
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

	/**
	 * Sets the allowEdits.
	 * 
	 * @param allowEdits
	 *            The allowEdits to set
	 */
	public void setAllowEdits(String allowEdits) {
		this.allowEdits = allowEdits;
	}

	public void setAllowEdits(boolean allowEdits) {
		this.allowEdits = (allowEdits ? "true" : "false");
	}

	/**
	 * Returns the showDefault.
	 * 
	 * @return String
	 */
	public String getShowDefault() {
		return showDefault;
	}

	/**
	 * Returns the showDefault.
	 * 
	 * @return boolean
	 */
	public boolean isShowDefault() {
		if (StringUtil.isNullorNill(getShowDefault())) {
			return true;
		}
		return getShowDefault().equals("true");
	}

	/**
	 * Sets the showDefault.
	 * 
	 * @param showDefault
	 *            The allowEdits to set
	 */
	public void setShowDefault(boolean showDefault) {
		this.showDefault = (showDefault ? "true" : "false");
	}

	/**
	 * Sets the showDefault.
	 * 
	 * @param showDefault
	 *            The allowEdits to set
	 */
	public void setShowDefault(String showDefault) {
		this.showDefault = showDefault;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

}
