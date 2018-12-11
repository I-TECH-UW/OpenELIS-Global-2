<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<script>
function checkFieldInt(field) {
	if (isNaN(field.value) || field.value.indexOf(".") > -1) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<bean:message key='siteInfo.number.nonnumber'/>");
	} else if (parseInt(field.value) <= 0) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<bean:message key='siteInfo.number.invalidnumber'/>");
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

<h2><bean:message key="siteInfo.section.number"/></h2>
<p><bean:message key="siteInfo.description.max"/></p>
<table width="80%">
	<tr>
		<td>
			<bean:message key="barcode.label.type.order"/>
		</td>
		<td>
			<bean:message key="barcode.label.type.specimen"/>
		</td>
	</tr>
	<tr>
		<td>
			<html:text name="<%=formName%>" 
				property="numOrderLabels" 
				styleId="numOrderLabels"
				onchange="checkFieldInt(this)"></html:text>
		</td>
		<td>
			<html:text name="<%=formName%>" 
				property="numSpecimenLabels"
				styleId="numSpecimenLabels"
				onchange="checkFieldInt(this)"></html:text>
		</td>
	</tr>
</table>