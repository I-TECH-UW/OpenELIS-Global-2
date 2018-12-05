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
import org.apache.struts.taglib.html.Constants;

/**
 * @author - Diane Benz 01/17/2008 - bugzilla 1844, 2293
 *
 *  
 * Tag for select options.  The body of this tag is presented to the user
 * in the option list, while the value attribute is the value returned to
 * the server if this option is selected.
 *
 * Extended struts OptionTag for purpose of adding more option information for use with assignable tests
 * (test management and quick entry test assignment and sorting)
 * added title (struts option tag does not have title parameter)
 * added type
 * added sortFieldA
 * added sortFieldB
 */
public class SortableOptionTag extends org.apache.struts.taglib.html.OptionTag {

    // ----------------------------------------------------- Instance Variables


    // ------------------------------------------------------------- Properties
    /**
     * The style associated with this tag.
     */
    private String style = null;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * The named style class associated with this tag.
     */
    private String styleClass = null;

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
    
    /**
     *  title 
     */
    protected String title = "";

    public String getTitle() {
        return (this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     *  type (in case of AssignableTests: panelType? testType)
     */
    protected String type = "";

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    /**
     * sortFieldA - this is value to be sorted by (in case of AssignableTests: name)
     */
    protected String sortFieldA = null;

    public String getSortFieldA() {
        return (this.sortFieldA);
    }

    public void setSortFieldA(String sortFieldA) {
        this.sortFieldA = sortFieldA;
    }
    
    /**
     * sortFieldB - this is value to be sorted by (in case of AssignableTests: description)
     */
    protected String sortFieldB = null;

    public String getSortFieldB() {
        return (this.sortFieldB);
    }

    public void setSortFieldB(String sortFieldB) {
        this.sortFieldB = sortFieldB;
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Generate an HTML %lt;option&gt; element.
     * @throws JspException
     * @since Struts 1.1
     */
    protected String renderOptionElement() throws JspException {
        StringBuffer results = new StringBuffer("<option value=\"");
        
        results.append(this.value);
        results.append("\"");
        if (disabled) {
            results.append(" disabled=\"disabled\"");
        }
        if (this.selectTag().isMatched(this.value)) {
            results.append(" selected=\"selected\"");
        }
        if (style != null) {
            results.append(" style=\"");
            results.append(style);
            results.append("\"");
        }
        if (styleId != null) {
            results.append(" id=\"");
            results.append(styleId);
            results.append("\"");
        }
        if (styleClass != null) {
            results.append(" class=\"");
            results.append(styleClass);
            results.append("\"");
        }
        if (title != null) {
            results.append(" title=\"");
            results.append(title);
            results.append("\"");
        }
        if (type != null) {
            results.append(" type=\"");
            results.append(type);
            results.append("\"");
        }
        if (sortFieldA != null) {
            results.append(" sortFieldA=\"");
            results.append(sortFieldA);
            results.append("\"");
        }
        if (sortFieldB != null) {
            results.append(" sortFieldB=\"");
            results.append(sortFieldB);
            results.append("\"");
        }
        results.append(">");

        results.append(text());
        
        results.append("</option>");
        return results.toString();
    }

    /**
     * Acquire the select tag we are associated with.
     * @throws JspException
     */
    private SelectTag selectTag() throws JspException {
        SelectTag selectTag =
            (SelectTag) pageContext.getAttribute(Constants.SELECT_KEY);
            
        if (selectTag == null) {
            JspException e =
                new JspException(messages.getMessage("optionTag.select"));
                
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }
        
        return selectTag;
    } 

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        type = null;
        sortFieldA = null;
        sortFieldB = null;
    }

}
