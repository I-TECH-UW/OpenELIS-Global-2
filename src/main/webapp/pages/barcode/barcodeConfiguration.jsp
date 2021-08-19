<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ page import="org.openelisglobal.common.action.IActionConstants,
				 org.openelisglobal.common.util.*, org.openelisglobal.internationalization.MessageUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript" src="scripts/utilities.js?" ></script>
<script type="text/javascript">
var validator = new FieldValidator();
validator.setRequiredFields( new Array("heightOrderLabels", "widthOrderLabels", "heightSpecimenLabels", "widthSpecimenLabels", "numMaxOrderLabels", "numMaxSpecimenLabels", "numDefaultOrderLabels", "numDefaultSpecimenLabels") );

function savePage() {
	document.getElementById("mainForm").action = "BarcodeConfiguration.do"
	document.getElementById("mainForm").submit();
}

function setMyCancelAction() {
	window.location.href = "MasterListsPage.do";
}

function disableSave() {
	document.getElementById("saveButtonId").disabled = true;
}

function enableSave() {
	document.getElementById("saveButtonId").disabled = false;
}
</script>

<tiles:insertAttribute name="numberBarCodes"/>
<tiles:insertAttribute name="elementsBarCodes"/>
<tiles:insertAttribute name="alternateAccession"/>
<tiles:insertAttribute name="sizeBarCodes"/>
