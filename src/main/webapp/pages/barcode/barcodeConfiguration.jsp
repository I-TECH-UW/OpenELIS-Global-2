<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="org.openelisglobal.common.action.IActionConstants,
				 org.openelisglobal.common.util.*, org.openelisglobal.internationalization.MessageUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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
<tiles:insertAttribute name="sizeBarCodes"/>