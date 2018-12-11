<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />


<h2><bean:message key="siteInfo.section.elements"/></h2>
<p><bean:message key="siteInfo.description.elements"/></p>
<table width="80%">
	<tr>
		<td><b><bean:message key="siteInfo.elements.mandatory"/></b></td>
	</tr>
	<tr>
		<td style="text-align:center"><bean:message key="barcode.label.type.order"/></td>
		<td style="text-align:center"><bean:message key="barcode.label.type.specimen"/></td>
	</tr>
	<tr>
		<td style="text-align:center">
		<table style="margin:auto;border-spacing:15px 2px;">
			<tr>
				<td><ul>
					<li><bean:message key="barcode.label.info.labnumber"/></li>
					<li><bean:message key="barcode.label.info.patientid"/></li>
					<li>Site ID</li>
				</ul></td>
				<td><ul>
					<li><bean:message key="barcode.label.info.patientname"/></li>
					<li><bean:message key="barcode.label.info.patientdobfull"/></li>
				</ul></td>
			</tr>
		</table>
		</td>
		<td style="text-align:center">
		<table style="margin:auto;border-spacing:15px 2px;">
			<tr>
				<td><ul>
					<li><bean:message key="barcode.label.info.labnumber"/></li>
					<li><bean:message key="barcode.label.info.patientid"/></li>
				</ul></td>
				<td><ul>
					<li><bean:message key="barcode.label.info.patientname"/></li>
					<li><bean:message key="barcode.label.info.patientdobfull"/></li>
				</ul></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td><b><bean:message key="siteInfo.elements.optional"/></b></td>
	</tr>
	<tr>
		<td style="text-align:center"><bean:message key="barcode.label.type.order"/></td>
		<td style="text-align:center"><bean:message key="barcode.label.type.specimen"/></td>
	</tr>
	<tr>
		<td style="text-align:center">
		<table style="margin:auto;border-spacing:15px 2px;">
			<tr>
				<td><bean:message key="barcode.label.info.none"/></td>
			</tr>
		</table>
		</td>
		<td style="text-align:center">
		<table style="margin:auto;border-spacing:15px 2px;">
			<tr>
				<td>
					<html:checkbox name="<%=formName%>" 
						property="collectionDateCheck"
						value="true"></html:checkbox>
					<bean:message key="barcode.label.info.collectiondatetime"/>
				</td>
			</tr>
			<tr>
				<td>
					<html:checkbox name="<%=formName%>" 
						property="testsCheck"
						value="true"></html:checkbox>
					<bean:message key="barcode.label.info.tests"/>
				</td>
			</tr>
			<tr>
				<td>
					<html:checkbox name="<%=formName%>" 
						property="patientSexCheck"
						value="true"></html:checkbox>
					<bean:message key="barcode.label.info.patientsexfull"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>