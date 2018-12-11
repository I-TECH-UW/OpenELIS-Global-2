<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				 us.mn.state.health.lims.common.util.*" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-tiles"		prefix="tiles" %>

<%!
	String basePath = "";
%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
%>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

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