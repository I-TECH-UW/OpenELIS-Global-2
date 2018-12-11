package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;

public abstract class BaseQueryProvider implements IActionConstants {

	protected AjaxServlet ajaxServlet = null;

	public abstract void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public void setServlet(AjaxServlet as) {
		this.ajaxServlet = as;
	}

	public AjaxServlet getServlet() {
		return this.ajaxServlet;
	}

}
