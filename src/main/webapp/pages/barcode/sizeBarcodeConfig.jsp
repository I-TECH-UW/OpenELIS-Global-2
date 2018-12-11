<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	

<script>
function checkFieldFloat(field) {
	if (isNaN(field.value)) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<spring:message code='siteInfo.size.nonnumber'/>");
	} else if (parseFloat(field.value) <= 0) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<spring:message code='siteInfo.size.invalidnumber'/>");
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

<h2><spring:message code="siteInfo.section.size"/></h2>
<p><spring:message code="siteInfo.description.dimensions"/></p>
<table width="80%">
	<tr>
		<td><spring:message code="barcode.label.type.order"/>:</td>
		<td><spring:message code="barcode.label.type.specimen"/>:</td>
	</tr>
	<tr>
		<td>
			<spring:message code="siteInfo.size.height"/>:
			<html:text name="${form.formName}" 
				property="heightOrderLabels"
				id="heightOrderLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<spring:message code="siteInfo.size.units"/>
		</td>
		<td>
			<spring:message code="siteInfo.size.height"/>:
			<html:text name="${form.formName}" 
				property="heightSpecimenLabels"
				id="heightSpecimenLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<spring:message code="siteInfo.size.units"/>
		</td>
	</tr>
	<tr>
		<td>
			<spring:message code="siteInfo.size.width"/>:
			<html:text name="${form.formName}" 
				property="widthOrderLabels"
				id="widthOrderLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<spring:message code="siteInfo.size.units"/>
		</td>
		<td>
			<spring:message code="siteInfo.size.width"/>:
			<html:text name="${form.formName}" 
				property="widthSpecimenLabels"
				id="widthSpecimenLabels"
				onchange="checkFieldFloat(this)"></html:text>
			<spring:message code="siteInfo.size.units"/>
		</td>
	</tr>
</table>