package org.openelisglobal.common.provider.query;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.servlet.validation.AjaxServlet;

public abstract class BaseQueryProvider implements IActionConstants {

    protected AjaxServlet ajaxServlet = null;

    public abstract void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public void setServlet(AjaxServlet as) {
        this.ajaxServlet = as;
    }

    public AjaxServlet getServlet() {
        return this.ajaxServlet;
    }
}
