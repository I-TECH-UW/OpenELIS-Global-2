<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<script>
function checkFieldFloat(field) {
	if (isNaN(field.value)) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<bean:message key='siteInfo.size.nonnumber'/>");
	} else if (parseFloat(field.value) <= 0) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<bean:message key='siteInfo.size.invalidnumber'/>");
	} else {
		validator.setFieldValidity(true, field.id);
		selectFieldErrorDisplay(true, field);
	}
	if (validator.isAllValid()) {
		enableSave();
	} else {
		disableSave();
	}
}

</script>

<h2><bean:message key="siteInfo.section.size"/></h2>
<p><bean:message key="siteInfo.description.dimensions"/></p>
<table width="80%">
	<tr>
		<td><bean:message key="barcode.label.type.order"/>:</td>
		<td><bean:message key="barcode.label.type.specimen"/>:</td>
	</tr>
	<tr>
		<td>
			<bean:message key="siteInfo.size.height"/>:
			<html:text name="<%=formName%>" 
				property="heightOrderLabels"
				styleId="heightOrderLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<bean:message key="siteInfo.size.units"/>
		</td>
		<td>
			<bean:message key="siteInfo.size.height"/>:
			<html:text name="<%=formName%>" 
				property="heightSpecimenLabels"
				styleId="heightSpecimenLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<bean:message key="siteInfo.size.units"/>
		</td>
	</tr>
	<tr>
		<td>
			<bean:message key="siteInfo.size.width"/>:
			<html:text name="<%=formName%>" 
				property="widthOrderLabels"
				styleId="widthOrderLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<bean:message key="siteInfo.size.units"/>
		</td>
		<td>
			<bean:message key="siteInfo.size.width"/>:
			<html:text name="<%=formName%>" 
				property="widthSpecimenLabels"
				styleId="widthSpecimenLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<bean:message key="siteInfo.size.units"/>
		</td>
	</tr>
</table>