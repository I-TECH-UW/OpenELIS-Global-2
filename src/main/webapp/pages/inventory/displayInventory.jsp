<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.inventory.form.InventoryKitItem" %>


<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	

<div id="PatientPage" class="colorFill" style="display:inline" >
	<logic:present name="${form.formName}" property="inventoryItems" >
	<table width="40%" >
	<tr >
		<th width="10%">
			<spring:message code="inventory.testKit.id"/>
		</th>
		<th width="25%">
			<spring:message code="inventory.testKit.name"/>		
		</th>
		<th width="15%">
			<spring:message code="inventory.testKit.receiveDate"/>		
		</th>
		<th width="15%">
			<spring:message code="inventory.testKit.expiration"/>		
		</th>
		<th width="10%">
			<spring:message code="inventory.testKit.lot"/>		
		</th>
		<th width="25%">
			<spring:message code="inventory.testKit.source"/>		
		</th>
	</tr>
	<logic:iterate id="inventoryItems"  name="${form.formName}" property="inventoryItems" indexId="index" type="InventoryKitItem" >
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
<logic:notPresent name="${form.formName}" property="inventoryItems" >
	<spring:message code="inventory.testKit.none"/>
</logic:notPresent>	

	
</div>



