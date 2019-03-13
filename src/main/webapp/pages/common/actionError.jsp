<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.apache.struts.taglib.TagUtils,
		org.apache.struts.action.*,
		org.apache.struts.Globals,
		java.util.Iterator,
		javax.servlet.jsp.JspException,
		us.mn.state.health.lims.common.action.IActionConstants,
		us.mn.state.health.lims.common.util.resources.ResourceLocator,
		us.mn.state.health.lims.common.util.validator.ActionError,
		us.mn.state.health.lims.common.util.SystemConfiguration,
		org.owasp.encoder.Encode"%>
<%@ page import="spring.mine.common.constants.Constants" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<!DOCTYPE html>

<%-- removed deprecated calls to methods in org.apache.struts.util.RequestUtils--%>
<%--html:errors/--%>
<%--html:messages/--%>


<%!
String path = "";
String basePath = "";
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<script>

function onLoad() {

  	// bugzilla 1397 If a page requires special functionality before load that isn't global, create a
	// preOnLoad method (if we need to run js before errors popup)
	if(window.prePageOnLoad)
	{  
		prePageOnLoad();
	}
       
  	// If a page requires special functionality on load that isn't global, create a
	// pageOnLoad method
	if(window.pageOnLoad)
	{  
		pageOnLoad();
	}

}

// The Struts action form object associated with this page. It is initialized in
// the onLoad() function below to ensure that it is available when defined.
var myActionForm;

// Initialize myActionForm variable after load.
myActionForm = document.forms["<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>"];



</script>
<c:if test="${not empty requestScope[Constants.REQUEST_ERRORS].allErrors}">
<center><h1>
<c:forEach items="${requestScope[Constants.REQUEST_ERRORS].allErrors}" var="error">
	<spring:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" /><br>
</c:forEach>
</h1></center>
</c:if>

<c:if test="${not empty requestScope[Constants.REQUEST_WARNINGS]}">
<center><h1>
<c:forEach items="${requestScope[Constants.REQUEST_WARNINGS]}" var="warningMsg">
	<c:out value="${warningMsg}"/><br>
</c:forEach>
</h1></center>
</c:if>

