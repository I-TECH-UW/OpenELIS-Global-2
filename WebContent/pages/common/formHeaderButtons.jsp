<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"
%>
<%@ page isELIgnored="false" %> 
			
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if(null != request.getAttribute(IActionConstants.FORM_NAME))
	{
%>
<h1>
	<c:choose>
		<c:when test="${!empty subtitle}">
			<c:out value="<%=request.getAttribute(IActionConstants.PAGE_SUBTITLE_KEY)%>" />
		</c:when>
		<c:otherwise>
			<% if ("0".equals(request.getParameter("ID"))) { %>
			  <spring:message code="default.add.title" />
			<% } else { %>
			  <spring:message code="default.edit.title" />
			<%}%>
		</c:otherwise>
	</c:choose>
</h1>
<%
	}
%>


