<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	value='<%=(String) request
									.getAttribute(IActionConstants.FORM_NAME)%>' />
<%!String allowEdits = "true";%>

<%
		if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request
		.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>
<script>
 
function validateForm(form) {
     return true;
}
</script>

<table>
	<tr>
		<td class="label">
			<spring:message code="patienttype.id" />:
		</td>
		<td>
		<app:text name="${form.formName}" property="id" readonly="readonly" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<spring:message code="patienttype.type" />:<span class="requiredlabel">*</span>
		</td>
		<td>
			<form:input path="type" maxlength="1"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<spring:message code="patienttype.description" />:
		</td>
		<td>
			<form:input path="description" maxlength="50"/>
		</td>
	</tr>	
</table>
