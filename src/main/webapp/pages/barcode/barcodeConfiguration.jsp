<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				 us.mn.state.health.lims.common.util.*" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%!
	String basePath = "";
%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
%>

	

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript">
var validator = new FieldValidator();
validator.setRequiredFields( new Array("heightOrderLabels", "widthOrderLabels", "heightSpecimenLabels", "widthSpecimenLabels", "numOrderLabels", "numSpecimenLabels") );

function savePage() {
	document.forms[0].action = "BarcodeConfigurationSave.do"
	document.forms[0].submit();
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

<tiles:insert attribute="numberBarCodes"/>
<tiles:insert attribute="elementsBarCodes"/>
<tiles:insert attribute="sizeBarCodes"/>