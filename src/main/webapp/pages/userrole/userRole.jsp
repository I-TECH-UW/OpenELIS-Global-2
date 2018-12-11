<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>

<script language="JavaScript1.2">
function validateForm(form) {
    return true;
}
</script>

<table width="60%">
	<tr>
		<td class="label" width="10%">
			<bean:message key="role.name" />
			:
			<span class="requiredlabel">*</span>
		</td>
		<td width="40%">
			<html:select name="<%=formName%>" property="userNameId">
				<html:option value="0">&nbsp;</html:option>
				<html:optionsCollection name="<%=formName%>" property="users" label="loginName" value="id"/>	
			</html:select>

		</td>
		<td class="label" width="50%">
			<bean:message key="role.description" />
		</td>
	</tr>
	<logic:iterate  name="<%=formName%>" property="roles" id="role" >
	<tr>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>
		<html:multibox name="<%=formName %>" property="selectedRoles" >
			<bean:write name="role" property="id" />
		</html:multibox>
		<bean:write name="role" property="name" />
	</td>
	</tr>
	</logic:iterate>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>


