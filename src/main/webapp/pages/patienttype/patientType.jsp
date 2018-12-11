<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<bean:define id="formName"
	value='<%=(String) request
									.getAttribute(IActionConstants.FORM_NAME)%>' />
<%!String allowEdits = "true";%>

<%
		if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request
		.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>
<script language="JavaScript1.2">
 
function validateForm(form) {
     return true;
}
</script>

<table>
	<tr>
		<td class="label">
			<bean:message key="patienttype.id" />:
		</td>
		<td>
		<app:text name="<%=formName%>" property="id" allowEdits="false" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<bean:message key="patienttype.type" />:<span class="requiredlabel">*</span>
		</td>
		<td>
			<html:text name="<%=formName%>" property="type" maxlength="1"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<bean:message key="patienttype.description" />:
		</td>
		<td>
			<html:text name="<%=formName%>" property="description" maxlength="50"/>
		</td>
	</tr>	
</table>
