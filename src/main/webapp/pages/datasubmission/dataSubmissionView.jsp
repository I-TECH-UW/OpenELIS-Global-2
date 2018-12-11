<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
					us.mn.state.health.lims.datasubmission.valueholder.DataIndicator,
					us.mn.state.health.lims.common.util.DateUtil" %>

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
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>

 
<bean:define id="month" name="${form.formName}" property="month"/>
<bean:define id="year" name="${form.formName}" property="year" />


<script type="text/javascript">
function saveAndSubmit() {
	if (checkURL()) {
		if (confirmSentWarning()) {
			showsubmitting();
			var form = document.forms[0];
			form.action = "DataSubmissionSave.do?submit=true";
			form.submit();
		}
	} else {
		alert("<spring:message code="datasubmission.warning.missingurl" />");
	}
}

function saveAndExit() {
	var form = document.forms[0];
	form.action = "DataSubmissionSave.do";
	form.submit();
}

function dateChange() {
	var month = $jq("#month").val();
	var year = $jq("#year").val();
	window.location.replace("DataSubmission.do?month=" + month + "&year=" + year);
}

function editUrl() {
	$jq("#url").removeAttr("disabled");
}

function checkURL() {
	return $jq("#url").val() != "";
}

function showsubmitting() {
	if( typeof(showSuccessMessage) != 'undefined' ){
	   	showSuccessMessage( false );
	}
   $jq("#sending").show();
	window.location = '#sending';
}

function confirmSentWarning() {
	var sentIndicators = [];
	var message = "<spring:message code="datasubmission.warning.sent" arg0="<%=DateUtil.getMonthFromInt((int) month, false)%>" arg1="<%=Integer.toString((int) year)%>"/>";
	$jq("span.<%=DataIndicator.SENT%>").each(function() {
		sentIndicators.push(this.id);
	});
	$jq("span.<%=DataIndicator.RECEIVED%>").each(function() {
		sentIndicators.push(this.id);
	});
	for (var i = 0; i < sentIndicators.length; i++) {
		message += "\n\u2022" + sentIndicators[i];
	}
	if (sentIndicators.length == 0) {
		return true;
	}
	return confirm(message);
}
<%if( request.getAttribute(IActionConstants.FWD_SUCCESS) != null && ((Boolean)request.getAttribute(IActionConstants.FWD_SUCCESS)) == true ) { %>
	if( typeof(showSuccessMessage) != 'undefined' ){
	   	showSuccessMessage( true );
	}
<% } %>
</script>
<div id="sending" style="text-align:center;color:DarkOrange;width:100%;font-size:170%;display:none;">
				<spring:message code="datasubmission.warning.sending"/>
</div>
<spring:message code="datasubmission.label.url" />: 
<form:input path="dataSubUrl.value" id="url" disabled="true"></html:text>
<html:button property="" onclick="editUrl();"><spring:message code="datasubmission.button.edit" /></html:button>

<h3><spring:message code="datasubmission.siteid"/> - <c:out value="${form.siteId}"/></h3>

<spring:message code="datasubmission.description" />

<table style="width:100%;border-spacing:0 5px;" >
<tr>
	<td><spring:message code="datasubmission.label.month" /></td>
	<td>
		<html:select name="${form.formName}" property="month" id="month">
			<html:option value="1"><spring:message code="month.january.abbrev" /></html:option>
			<html:option value="2"><spring:message code="month.february.abbrev" /></html:option>
			<html:option value="3"><spring:message code="month.march.abbrev" /></html:option>
			<html:option value="4"><spring:message code="month.april.abbrev" /></html:option>
			<html:option value="5"><spring:message code="month.may.abbrev" /></html:option>
			<html:option value="6"><spring:message code="month.june.abbrev" /></html:option>
			<html:option value="7"><spring:message code="month.july.abbrev" /></html:option>
			<html:option value="8"><spring:message code="month.august.abbrev" /></html:option>
			<html:option value="9"><spring:message code="month.september.abbrev" /></html:option>
			<html:option value="10"><spring:message code="month.october.abbrev" /></html:option>
			<html:option value="11"><spring:message code="month.november.abbrev" /></html:option>
			<html:option value="12"><spring:message code="month.december.abbrev" /></html:option>
		</html:select>
	</td>
	<td><spring:message code="datasubmission.label.year" /></td>
	<td>
		<html:select name="${form.formName}" property="year" id="year">
			<html:option value="2017">2017</html:option>
			<html:option value="2018">2018</html:option>
			<html:option value="2019">2019</html:option>
			<html:option value="2020">2020</html:option>
		</html:select>
	</td>
	<td><html:button property="" value="Fetch Date" onclick="dateChange();"/></td>
</tr>
<tr>
	<td><b><spring:message code="datasubmission.checkbox.sendindicator"/></b></td>
	<td colspan="3"><b></b></td>
	<td><b></b></td>
</tr>
<logic:iterate property="indicators" name="${form.formName}" id="indicators" indexId="indicatorIndex">
	<bean:define name="indicators" property="typeOfIndicator.nameKey" id="nameKey" />
	<bean:define name="indicators" property="typeOfIndicator.descriptionKey" id="descriptionKey" />
<tr class="border_top">
	<td>
		<!--  checkbox-hidden combo trick to make struts send true when checked, false when unchecked as opposed to default checkbox (true when checked, false when unchecked)-->
		<html:checkbox name="${form.formName}" property='<%="indicators["+indicatorIndex+"].sendIndicator"%>' value="true"/>
		<form:hidden path='<%="indicators["+indicatorIndex+"].sendIndicator"%>' value="false" />
	</td>
	<td colspan="3">
		<span id="<spring:message code="<%=(String) nameKey%>" />" class="<bean:write name="indicators" property="status" />">
			<b><spring:message code="<%=(String) nameKey%>" /></b>
		</span>
	</td>
	<td >
		<b><spring:message code="<%=(String) nameKey%>" /></b>
	</td>
</tr>
	<logic:notEmpty name="indicators" property="dataValue"><logic:equal name="indicators" property="dataValue.visible" value="true">
<tr>
	<td></td>
	<td  colspan="3"><bean:write name="indicators" property="dataValue.value" /></td>
	<td >	<form:input path='<%="indicators["+indicatorIndex+"].dataValue.value"%>'/> </td>
</tr>
	</logic:equal></logic:notEmpty>
<tr>
	<td></td>
	<td  colspan="3">
		<spring:message code="<%=(String) descriptionKey%>" />
	</td>
</tr>
	<logic:iterate property="resources" name="indicators" id="resource" indexId="resourceIndex">
		<logic:notEmpty property="headerKey" name="resource">
			<bean:define name="resource" property="headerKey" id="headerKey" />
<tr>
	<td></td>
	<td  colspan="3"></td>
	<td >
		<spring:message code="<%=(String) headerKey%>" />
	</td>
</tr>
		</logic:notEmpty>
		<logic:iterate property="columnValues" name="resource" id="columnValue" indexId="columnIndex">
			<logic:equal name="columnValue" property="visible" value="true">
<tr>
	<td></td>
	<td  colspan="3">
		
	</td>
	<td >
		<form:input path='<%="indicators["+indicatorIndex+"].resources["+resourceIndex+"].value["+columnIndex+"]"%>' />
				<logic:notEmpty name="columnValue" property="displayKey">
					<bean:define name="columnValue" property="displayKey" id="displayKey" />
		<spring:message code="<%=(String)displayKey%>" />
				</logic:notEmpty>
	</td>
</tr>
			</logic:equal>
		</logic:iterate>
	</logic:iterate>
<tr class="spacerRow"><td>&nbsp;</td></tr>
</logic:iterate>
</table>

<html:button property="" onclick="saveAndSubmit();"><spring:message code="datasubmission.button.savesubmit" /> </html:button>
<html:button property="" onclick="saveAndExit();"><spring:message code="datasubmission.button.saveexit" /> </html:button>
