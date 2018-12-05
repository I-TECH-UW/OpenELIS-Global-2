<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
					us.mn.state.health.lims.datasubmission.valueholder.DataIndicator,
					us.mn.state.health.lims.common.util.DateUtil" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/struts-nested"	prefix="nested" %>

<%!
    String basePath = "";
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="month" name="<%=formName%>" property="month"/>
<bean:define id="year" name="<%=formName%>" property="year" />


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
		alert("<bean:message key="datasubmission.warning.missingurl" />");
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
	var message = "<bean:message key="datasubmission.warning.sent" arg0="<%=DateUtil.getMonthFromInt((int) month, false)%>" arg1="<%=Integer.toString((int) year)%>"/>";
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
				<bean:message key="datasubmission.warning.sending"/>
</div>
<bean:message key="datasubmission.label.url" />: 
<html:text name="<%=formName%>" property="dataSubUrl.value" styleId="url" disabled="true"></html:text>
<html:button property="" onclick="editUrl();"><bean:message key="datasubmission.button.edit" /></html:button>

<h3><bean:message key="datasubmission.siteid"/> - <bean:write name="<%=formName%>" property="siteId"/></h3>

<bean:message key="datasubmission.description" />

<table style="width:100%;border-spacing:0 5px;" >
<tr>
	<td><bean:message key="datasubmission.label.month" /></td>
	<td>
		<html:select name="<%=formName%>" property="month" styleId="month">
			<html:option value="1"><bean:message key="month.january.abbrev" /></html:option>
			<html:option value="2"><bean:message key="month.february.abbrev" /></html:option>
			<html:option value="3"><bean:message key="month.march.abbrev" /></html:option>
			<html:option value="4"><bean:message key="month.april.abbrev" /></html:option>
			<html:option value="5"><bean:message key="month.may.abbrev" /></html:option>
			<html:option value="6"><bean:message key="month.june.abbrev" /></html:option>
			<html:option value="7"><bean:message key="month.july.abbrev" /></html:option>
			<html:option value="8"><bean:message key="month.august.abbrev" /></html:option>
			<html:option value="9"><bean:message key="month.september.abbrev" /></html:option>
			<html:option value="10"><bean:message key="month.october.abbrev" /></html:option>
			<html:option value="11"><bean:message key="month.november.abbrev" /></html:option>
			<html:option value="12"><bean:message key="month.december.abbrev" /></html:option>
		</html:select>
	</td>
	<td><bean:message key="datasubmission.label.year" /></td>
	<td>
		<html:select name="<%=formName%>" property="year" styleId="year">
			<html:option value="2017">2017</html:option>
			<html:option value="2018">2018</html:option>
			<html:option value="2019">2019</html:option>
			<html:option value="2020">2020</html:option>
		</html:select>
	</td>
	<td><html:button property="" value="Fetch Date" onclick="dateChange();"/></td>
</tr>
<tr>
	<td><b><bean:message key="datasubmission.checkbox.sendindicator"/></b></td>
	<td colspan="3"><b></b></td>
	<td><b></b></td>
</tr>
<logic:iterate property="indicators" name="<%=formName%>" id="indicators" indexId="indicatorIndex">
	<bean:define name="indicators" property="typeOfIndicator.nameKey" id="nameKey" />
	<bean:define name="indicators" property="typeOfIndicator.descriptionKey" id="descriptionKey" />
<tr class="border_top">
	<td>
		<!--  checkbox-hidden combo trick to make struts send true when checked, false when unchecked as opposed to default checkbox (true when checked, false when unchecked)-->
		<html:checkbox name="<%=formName%>" property='<%="indicators["+indicatorIndex+"].sendIndicator"%>' value="true"/>
		<html:hidden name="<%=formName%>" property='<%="indicators["+indicatorIndex+"].sendIndicator"%>' value="false" />
	</td>
	<td colspan="3">
		<span id="<bean:message key="<%=(String) nameKey%>" />" class="<bean:write name="indicators" property="status" />">
			<b><bean:message key="<%=(String) nameKey%>" /></b>
		</span>
	</td>
	<td >
		<b><bean:message key="<%=(String) nameKey%>" /></b>
	</td>
</tr>
	<logic:notEmpty name="indicators" property="dataValue"><logic:equal name="indicators" property="dataValue.visible" value="true">
<tr>
	<td></td>
	<td  colspan="3"><bean:write name="indicators" property="dataValue.value" /></td>
	<td >	<html:text name="<%=formName%>" property='<%="indicators["+indicatorIndex+"].dataValue.value"%>'/> </td>
</tr>
	</logic:equal></logic:notEmpty>
<tr>
	<td></td>
	<td  colspan="3">
		<bean:message key="<%=(String) descriptionKey%>" />
	</td>
</tr>
	<logic:iterate property="resources" name="indicators" id="resource" indexId="resourceIndex">
		<logic:notEmpty property="headerKey" name="resource">
			<bean:define name="resource" property="headerKey" id="headerKey" />
<tr>
	<td></td>
	<td  colspan="3"></td>
	<td >
		<bean:message key="<%=(String) headerKey%>" />
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
		<html:text name="<%=formName%>" property='<%="indicators["+indicatorIndex+"].resources["+resourceIndex+"].value["+columnIndex+"]"%>' />
				<logic:notEmpty name="columnValue" property="displayKey">
					<bean:define name="columnValue" property="displayKey" id="displayKey" />
		<bean:message key="<%=(String)displayKey%>" />
				</logic:notEmpty>
	</td>
</tr>
			</logic:equal>
		</logic:iterate>
	</logic:iterate>
<tr class="spacerRow"><td>&nbsp;</td></tr>
</logic:iterate>
</table>

<html:button property="" onclick="saveAndSubmit();"><bean:message key="datasubmission.button.savesubmit" /> </html:button>
<html:button property="" onclick="saveAndExit();"><bean:message key="datasubmission.button.saveexit" /> </html:button>
