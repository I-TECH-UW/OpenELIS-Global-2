<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ page import="org.openelisglobal.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function useAltAccessionChange() {
	enableSave();
	jQuery("#prePrintAltAccessionPrefix").prop("disabled", !jQuery("#prePrintUseAltAccession").prop('checked'));
	altAccessionKeyUp();
}

function altAccessionOnChange() {
	if (jQuery("#prePrintAltAccessionPrefix").val().length < 4) {
		alert("<spring:message code="labno.alt.prefix.error.length"/>");
	} 
}

function altAccessionKeyUp() {
	if (altAccessionValid()) {
		enableSave();
	} else {
		disableSave();
	}
	
	if (jQuery("#prePrintAltAccessionPrefix").val().toLowerCase() === jQuery("#sitePrefix").val().toLowerCase()) {
	    alert("<spring:message code="labno.alt.prefix.error.unique"/>");
	} 
}

function altAccessionValid() {
	if (jQuery("#prePrintUseAltAccession").prop('checked')) {
		if (jQuery("#prePrintAltAccessionPrefix").val().toLowerCase() === jQuery("#sitePrefix").val().toLowerCase()) {
			return false;
		}
		if (jQuery("#prePrintAltAccessionPrefix").val().length < 4) {
			return false;
		}
	}
	return true;
}

jQuery(document).ready(function() {
	useAltAccessionChange();
}) 
</script>

<h2><spring:message code="siteInfo.section.altAccession"/></h2>
<%-- <p><spring:message code="siteInfo.description.altAccession"/></p> --%>
<table width="80%">
	<tr>
	<td>
		<spring:message code="labno.alt.prefix.use"/>: <form:checkbox id="prePrintUseAltAccession" path="prePrintUseAltAccession" onChange="useAltAccessionChange()"/>
	</td>
	</tr>
	<tr>
	<td>
		<form:hidden id="sitePrefix" path="sitePrefix" />
		<spring:message code="labno.alt.prefix.instruction"/>: <form:input id="prePrintAltAccessionPrefix" path="prePrintAltAccessionPrefix" maxlength="4" onChange="altAccessionOnChange()" onKeyUp="altAccessionKeyUp()"/>
		<br><spring:message code="labno.alt.prefix.note"/>
	</td>
	</tr>
<!-- 	<tr> -->
<%-- 		<td><b><spring:message code="siteInfo.elements.mandatory"/></b></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td style="text-align:center"><spring:message code="barcode.label.type.order"/></td> --%>
<%-- 		<td style="text-align:center"><spring:message code="barcode.label.type.specimen"/></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td style="text-align:center"> -->
<!-- 		<table style="margin:auto;border-spacing:15px 2px;"> -->
<!-- 			<tr> -->
<!-- 				<td><ul> -->
<%-- 					<li><spring:message code="barcode.label.info.labnumber"/></li> --%>
<%-- 					<li><spring:message code="barcode.label.info.patientid"/></li> --%>
<!-- 					<li>Site ID</li> -->
<!-- 				</ul></td> -->
<!-- 				<td><ul> -->
<%-- 					<li><spring:message code="barcode.label.info.patientname"/></li> --%>
<%-- 					<li><spring:message code="barcode.label.info.patientdobfull"/></li> --%>
<!-- 				</ul></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		</td> -->
<!-- 		<td style="text-align:center"> -->
<!-- 		<table style="margin:auto;border-spacing:15px 2px;"> -->
<!-- 			<tr> -->
<!-- 				<td><ul> -->
<%-- 					<li><spring:message code="barcode.label.info.labnumber"/></li> --%>
<%-- 					<li><spring:message code="barcode.label.info.patientid"/></li> --%>
<!-- 				</ul></td> -->
<!-- 				<td><ul> -->
<%-- 					<li><spring:message code="barcode.label.info.patientname"/></li> --%>
<%-- 					<li><spring:message code="barcode.label.info.patientdobfull"/></li> --%>
<!-- 				</ul></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><b><spring:message code="siteInfo.elements.optional"/></b></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td style="text-align:center"><spring:message code="barcode.label.type.order"/></td> --%>
<%-- 		<td style="text-align:center"><spring:message code="barcode.label.type.specimen"/></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td style="text-align:center"> -->
<!-- 		<table style="margin:auto;border-spacing:15px 2px;"> -->
<!-- 			<tr> -->
<%-- 				<td><spring:message code="barcode.label.info.none"/></td> --%>
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		</td> -->
<!-- 		<td style="text-align:center"> -->
<!-- 		<table style="margin:auto;border-spacing:15px 2px;"> -->
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<form:checkbox path="collectionDateCheck" --%>
<%-- 						value="true" --%>
<%-- 						onchange="enableSave();"/> --%>
<%-- 					<spring:message code="barcode.label.info.collectiondatetime"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<form:checkbox path="testsCheck" --%>
<%-- 						value="true" --%>
<%-- 						onchange="enableSave();"/> --%>
<%-- 					<spring:message code="barcode.label.info.tests"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<form:checkbox path="patientSexCheck" --%>
<%-- 						value="true" --%>
<%-- 						onchange="enableSave();"/> --%>
<%-- 					<spring:message code="barcode.label.info.patientsexfull"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		</td> -->
<!-- 	</tr> -->
</table>