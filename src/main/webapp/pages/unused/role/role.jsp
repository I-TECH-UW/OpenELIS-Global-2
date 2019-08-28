<%@ page language="java" contentType="text/html; charset=utf-8"
	import="org.openelisglobal.common.action.IActionConstants"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>

<script>

function validateForm(form) {
    return true;
}
</script>
<bean:define id="isParentRoleProperty" name='${form.formName}' property="isParentRole"  />
<table width="60%">
	<tr>
		<td class="label" width="30%">
			<spring:message code="role.name" />
			:
			<span class="requiredlabel">*</span>
		</td>
		<td width="70%">
			<form:input path="roleName" size="15"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<spring:message code="role.description" />
			:
		</td>
		<td>
			<form:input path="description" size="40" maxlength="40"/>
		</td>
	</tr>
	<tr>
		<td class="label" >
			<spring:message code="role.isGroupingRole"/>:
		</td>
		<td>

			<html:checkbox name='${form.formName}'
			               property="isParentRole"
			               value="isChecked" />
		</td>
	</tr>
	<tr>
		<td class="label" >
			<spring:message code="role.parent.role"/>:
		</td>
		<td>
			<html:select name="${form.formName}"
						 property="parentRole"
						 id="parentRoleID">
				<app:optionsCollection name="${form.formName}" property="parentRoles"   label="name" value="id" />
			</html:select>
	    </td>
	</tr>
	<tr>
		<td class="label" >
			<spring:message code="role.display.key"/>:
		</td>
		<td>
			<form:input path="displayKey" size="40" maxlength="60"/>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>

