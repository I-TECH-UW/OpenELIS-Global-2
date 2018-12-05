<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants, 
			us.mn.state.health.lims.patienttype.valueholder.PatientType"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>

<bean:define id="formName"
	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />


<table width="100%" border=2">
	<tr>
		<th>
			<bean:message key="label.form.select"/>			
		</th>
		<th>
			<bean:message key="patienttype.type" />
		</th>
		<th>
			<bean:message key="patienttype.description" />
		</th>
	</tr>		
	<logic:iterate id="patientype" indexId="ctr" name="<%=formName%>" property="menuList"	type="PatientType">
		<bean:define id="patientypeID" name="patientype" property="id" />
		<tr>
			<td class="textcontent">
				<html:multibox name='<%=formName%>' property="selectedIDs">
					<bean:write name="patientypeID" />
				</html:multibox>
			</td>
			<td class="textcontent">
				<bean:write name="patientype" property="type" />
			</td>
			<td class="textcontent">
				<bean:write name="patientype" property="description" />
			</td>			
		</tr>
	</logic:iterate>
</table>
