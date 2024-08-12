/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.mozilla.org/MPL/
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
 * ANY KIND, either express or implied. See the License for the specific language governing rights
 * and limitations under the License.
 *
 * <p>The Original Code is OpenELIS code.
 *
 * <p>Copyright (C) The Minnesota Department of Health. All Rights Reserved.
 */
package org.openelisglobal.common.provider.selectdropdown;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajaxtags.servlets.BaseAjaxServlet;

public abstract class BaseSelectDropDownProvider {

    protected BaseAjaxServlet ajaxServlet = null;

    public abstract List processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public void setServlet(BaseAjaxServlet as) {
        this.ajaxServlet = as;
    }

    public BaseAjaxServlet getServlet() {
        return this.ajaxServlet;
    }
}
