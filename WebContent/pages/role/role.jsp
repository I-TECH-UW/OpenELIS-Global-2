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
<bean:define id="isParentRoleProperty" name='<%=formName %>' property="isParentRole"  />
<table width="60%">
	<tr>
		<td class="label" width="30%">
			<bean:message key="role.name" />
			:
			<span class="requiredlabel">*</span>
		</td>
		<td width="70%">
			<html:text name="<%=formName%>" property="roleName" size="15"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			<bean:message key="role.description" />
			:
		</td>
		<td>
			<html:text name="<%=formName%>" property="description" size="40" maxlength="40"/>
		</td>
	</tr>
	<tr>
		<td class="label" >
			<bean:message key="role.isGroupingRole"/>:
		</td>
		<td>

			<html:checkbox name='<%=formName %>'
			               property="isParentRole"
			               value="isChecked" />
		</td>
	</tr>
	<tr>
		<td class="label" >
			<bean:message key="role.parent.role"/>:
		</td>
		<td>
			<html:select name="<%=formName%>"
						 property="parentRole"
						 styleId="parentRoleID">
				<app:optionsCollection name="<%=formName%>" property="parentRoles"   label="name" value="id" />
			</html:select>
	    </td>
	</tr>
	<tr>
		<td class="label" >
			<bean:message key="role.display.key"/>:
		</td>
		<td>
			<html:text name="<%=formName %>" property="displayKey" size="40" maxlength="60"/>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>

