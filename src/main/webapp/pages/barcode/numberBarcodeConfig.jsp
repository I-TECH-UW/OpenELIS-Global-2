<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ page import="org.openelisglobal.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	

<script>
function checkFieldInt(field) {
	if (isNaN(field.value) || field.value.indexOf(".") > -1) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<spring:message code='siteInfo.number.nonnumber'/>");
	} else if (parseInt(field.value) <= 0) {
		validator.setFieldValidity(false, field.id);
		selectFieldErrorDisplay(false, field);
		alert("<spring:message code='siteInfo.number.invalidnumber'/>");
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

<h2><spring:message code="siteInfo.section.number"/></h2>
<p><spring:message code="siteInfo.description.max"/></p>
<table width="80%">
	<tr>
		<td>
			<spring:message code="barcode.label.type.order"/>
		</td>
		<td>
			<spring:message code="barcode.label.type.specimen"/>
		</td>
	</tr>
	<tr>
		<td>
			<form:input path="numOrderLabels" 
				id="numOrderLabels"
				onchange="checkFieldInt(this)"/>
		</td>
		<td>
			<form:input path="numSpecimenLabels"
				id="numSpecimenLabels"
				onchange="checkFieldInt(this)"/>
		</td>
	</tr>
</table>