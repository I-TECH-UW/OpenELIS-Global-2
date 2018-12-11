<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.inventory.form.InventoryKitItem" %>


<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<div id="PatientPage" class="colorFill" style="display:inline" >
	<logic:present name="<%=formName%>" property="inventoryItems" >
	<table width="40%" >
	<tr >
		<th width="10%">
			<bean:message key="inventory.testKit.id"/>
		</th>
		<th width="25%">
			<bean:message key="inventory.testKit.name"/>		
		</th>
		<th width="15%">
			<bean:message key="inventory.testKit.receiveDate"/>		
		</th>
		<th width="15%">
			<bean:message key="inventory.testKit.expiration"/>		
		</th>
		<th width="10%">
			<bean:message key="inventory.testKit.lot"/>		
		</th>
		<th width="25%">
			<bean:message key="inventory.testKit.source"/>		
		</th>
	</tr>
	<logic:iterate id="inventoryItems"  name="<%=formName%>" property="inventoryItems" indexId="index" type="InventoryKitItem" >
		<logic:equal name="inventoryItems" property="isActive" value="true">
			<tr >
				<td >
					<bean:write name="inventoryItems" property="inventoryLocationId"/>
				</td>
				<td >
					<bean:write name="inventoryItems" property="kitName"/>
				</td>
				<td >
					<bean:write name="inventoryItems" property="receiveDate"/>
				</td>
				<td >
					<bean:write name="inventoryItems" property="expirationDate"/>
				</td>
				<td >
					<bean:write name="inventoryItems" property="lotNumber"/>
				</td>
				<td >
					<bean:write name="inventoryItems" property="source"/>
				</td>
			</tr>
		</logic:equal>
	</logic:iterate>
	</table>
</logic:present>
<logic:notPresent name="<%=formName%>" property="inventoryItems" >
	<bean:message key="inventory.testKit.none"/>
</logic:notPresent>	

	
</div>



