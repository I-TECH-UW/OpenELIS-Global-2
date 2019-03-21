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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.SelectTag;

import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author benzd1
 * 
 * bugzilla 1844: extending OptionsCollectionTag to add toggling sort and label value
 * Test dropdown: display label as test.name dash test.description and allow toggling between
 * sorting by test.name and test.description. If sorting by test.description render labels
 * as alternateLabel (test.description dash test.name) property defined in 
 * us.mn.state.health.lims.test.valueholder.Test
 */
public class SortableOptionsCollectionTag extends OptionsCollectionTag {

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
	
	private String sortFieldA;
	
	private String sortFieldB;
	
	private String alternateLabel;
	
	private static Log log = LogFactory.getLog(SortableOptionsCollectionTag.class);

	/**
	 * Constructor for OptionsCollection.
	 */
	public SortableOptionsCollectionTag() {
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

		if (log.isDebugEnabled()) {
			log.debug("Filter Properties = " + filterProperty);
		}
		if (log.isDebugEnabled()) {
			log.debug("Filter Values = " + filterValue);
		}

		StringBuffer sb = new StringBuffer();

		if (isAllowEdits() && isShowDefault()) {
			addOption(sb, "", "", false, "", "", "");
		}

		boolean first = true;

		// Render the options
		while (iter.hasNext()) {

			Object bean = iter.next();
			// Get the label for this option
			Object beanLabel = getProperty(bean, label);
			// Get the value for this option
			Object beanValue = getProperty(bean, value);
			
			Object beanSortFieldA = getProperty(bean, sortFieldA);
			
			Object beanSortFieldB = getProperty(bean, sortFieldB);
			
			Object beanAlternateLabel = getProperty(bean, alternateLabel);

			String stringLabel = beanLabel.toString();
			String stringValue = beanValue.toString();
			String stringSortFieldA = beanSortFieldA.toString();
			String stringSortFieldB = beanSortFieldB.toString();
			String stringAlternateLabel = beanAlternateLabel.toString();

			boolean filterOkay = true;
			boolean matchOkay = true;
			Object beanFilter;
			// We only check the filter and match properties if the current
			// option is NOT a match.
			if (!selectTag.isMatched(stringValue)) {
				if (filterProperties != null) {
					for (int i = 0; i < filterProperties.length; ++i) {
						try {
							log.debug("Checking filter: " + filterProperties[i]
									+ "=" + filterValues[i]);
							beanFilter = PropertyUtils.getNestedProperty(bean,
									filterProperties[i]);
							log.debug("Bean property: " + filterProperties[i]
									+ "=" + beanFilter.toString());
						} catch (Exception e) {
							throw new JspException(
									"Failed to retrieve property from bean.", e);
						}
						if (beanFilter != null
								&& beanFilter.toString()
										.equals(filterValues[i])) {
							log.debug("Filter failed for "
									+ filterProperties[i] + ".");
							// Filter failed, so break
							filterOkay = false;
							break;
						}
					}
				}

				if (matchProperties != null) {
					for (int i = 0; i < matchProperties.length; ++i) {
						try {
							log.debug("Checking filter: " + matchProperties[i]
									+ "!=" + matchValues[i]);
							beanFilter = PropertyUtils.getNestedProperty(bean,
									matchProperties[i]);
							log.debug("Bean property: " + matchProperties[i]
									+ "=" + beanFilter.toString());
						} catch (Exception e) {
							throw new JspException(
									"Failed to retrieve property from bean.", e);
						}

						if (beanFilter != null
								&& !beanFilter.toString()
										.equals(matchValues[i])) {
							log.debug("Match failed for " + matchProperties[i]
									+ ".");
							// Match failed, so break
							matchOkay = false;
							break;
						}
					}
				}
			}

			if (filterOkay && matchOkay) {

				if (log.isDebugEnabled()) {
					log.debug("In OptionsTag label = " + stringLabel
							+ " value = " + stringValue);
				}

				if (isAllowEdits()) {
					addOption(sb, stringLabel, stringValue, selectTag
							.isMatched(stringValue), stringSortFieldA, stringSortFieldB, stringAlternateLabel);
				} else {
					if (log.isDebugEnabled()) {
						log.debug("In OptionsTag is matched = "
								+ selectTag.isMatched(stringValue));
					}
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
		if (log.isDebugEnabled()) {
			log.debug("In OptionsTag output = " + sb.toString());
		}

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
			JspException jspe = new JspException(messages.getMessage(
					"getter.access", value, bean));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		} catch (InvocationTargetException e) {
			Throwable t = e.getTargetException();
			JspException jspe = new JspException(messages.getMessage(
					"getter.result", value, t.toString()));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		} catch (NoSuchMethodException e) {
			JspException jspe = new JspException(messages.getMessage(
					"getter.method", value, bean));
			TagUtils.getInstance().saveException(pageContext, jspe);
			throw jspe;
		}

		//adjusting handling of maxLength to not truncate in the middle of a word
		if (!StringUtil.isNullorNill(maxLength)) {
			try {
			   int max = Integer.parseInt(maxLength);
			   String beanVal = beanValue.toString();
			   if (beanVal.length() > max) {
				 int indexOfNextSpaceForTruncation = beanVal.indexOf(" ", max);
				 if (indexOfNextSpaceForTruncation >= 0) {
					 beanValue = beanVal.substring(0, indexOfNextSpaceForTruncation);
				 } else {
					 //if we reach end of string before space we need to not cut off last word
			         beanValue = beanVal.substring(0, beanVal.length());
				 }
			   }
			} catch (Exception e) {
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

	public String getSortFieldA() {
		return sortFieldA;
	}

	public void setSortFieldA(String sortFieldA) {
		this.sortFieldA = sortFieldA;
	}

	public String getSortFieldB() {
		return sortFieldB;
	}

	public void setSortFieldB(String sortFieldB) {
		this.sortFieldB = sortFieldB;
	}

	
    // ------------------------------------------------------ Protected Methods

    /**
     * Add an option element to the specified StringBuffer 
     * benzd1 bugzilla 2293, 1844: overriding base addOption to include properties for sorting: type, sortFieldA, sortFieldB
     *
     * @param sb StringBuffer accumulating our results
     * @param value Value to be returned to the server for this option
     * @param label Value to be shown to the user for this option
     * @param matched Should this value be marked as selected?
     */
    protected void addOption(StringBuffer sb, String label, String value, boolean matched, String sortFieldA, String sortFieldB, String alternateLabel) {

        sb.append("<option value=\"");
        if (filter) {
            sb.append(TagUtils.getInstance().filter(value));
        } else {
            sb.append(value);
        }
        sb.append("\"");
        if (matched) {
            sb.append(" selected=\"selected\"");
        }
        if (getStyle() != null) {
            sb.append(" style=\"");
            sb.append(getStyle());
            sb.append("\"");
        }
        if (getStyleClass() != null) {
            sb.append(" class=\"");
            sb.append(getStyleClass());
            sb.append("\"");
        }
        
        if (sortFieldA != null) {
            sb.append(" sortFieldA=\"");
            sb.append(sortFieldA);
            sb.append("\"");
        }
        
        if (sortFieldB != null) {
            sb.append(" sortFieldB=\"");
            sb.append(sortFieldB);
            sb.append("\"");
        }
        
        if (alternateLabel != null) {
            sb.append(" alternateLabel=\"");
            sb.append(alternateLabel);
            sb.append("\"");
        }
        sb.append(">");
        
        if (filter) {
            sb.append(TagUtils.getInstance().filter(label));
        } else {
            sb.append(label);
        }
        
        sb.append("</option>\r\n");

    }

	public String getAlternateLabel() {
		return alternateLabel;
	}

	public void setAlternateLabel(String alternateLabel) {
		this.alternateLabel = alternateLabel;
	}
}
