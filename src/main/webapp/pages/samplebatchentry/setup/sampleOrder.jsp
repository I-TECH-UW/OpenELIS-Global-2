<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
          	     us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.util.StringUtil,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.DateUtil" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!
    String path = "";
    String basePath = "";
    boolean acceptExternalOrders = false;
%>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    acceptExternalOrders = ConfigurationProperties.getInstance().isPropertyValueEqual( Property.ACCEPT_EXTERNAL_ORDERS, "true" );
%>

<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?ver=<%= Versioning.getBuildNumber() %>"/>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.jsp"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/laborder.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript">

//validation logic for this 'page'
function /*bool*/ sampleOrderValid() {
	if( acceptExternalOrders){ 
    	if (missingRequiredValues())
    		return false;
    }
   	if( $("currentDate").value.blank() ){
		return false;
    } else if ($("receivedDateForDisplay").value.blank() ){
        return false;
    }
    return true;
}



function checkValidTime(time, blankAllowed)
{
    var lowRangeRegEx = new RegExp("^[0-1]{0,1}\\d:[0-5]\\d$");
    var highRangeRegEx = new RegExp("^2[0-3]:[0-5]\\d$");

    if (time.value.blank() && blankAllowed == true) {
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
        checkValidSubPages();
        return;        
    }

    if( lowRangeRegEx.test(time.value) ||
        highRangeRegEx.test(time.value) )
    {
        if( time.value.length == 4 )
        {
            time.value = "0" + time.value;
        }
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
    }
    else
    {
        setFieldErrorDisplay(time);
        setSampleFieldInvalid(time.name);
    }

    checkValidSubPages();
}

function studyChanged(studyElement) {
	var study = studyElement.value;
	if (study == "routine") {
		$jq("#psuedoPatientInfo").removeAttr("disabled");
		$jq("#facility-combobox").show();
		$jq("#routineSampleAdd").show();
		$jq("#viralLoadSampleAdd").hide();
		$jq("#EIDSampleAdd").hide();
	} else if (study == "viralLoad"){
		$jq("#psuedoPatientInfo").attr('checked', false);
		$jq("#psuedoPatientInfo").attr('disabled', 'disabled');
		document.getElementsByName('patientInfoCheck')[0].disabled = true;
		$jq("select#requesterId").prop("selectedIndex", 0);
		$jq("#facility-combobox").hide();
		$jq("#routineSampleAdd").hide();
		$jq("#viralLoadSampleAdd").show();
		$jq("#EIDSampleAdd").hide();
	} else if (study == "EID"){
		$jq("#psuedoPatientInfo").attr('checked', false);
		$jq("#psuedoPatientInfo").attr('disabled', 'disabled');
		document.getElementsByName('patientInfoCheck')[0].disabled = true;
		$jq("select#requesterId").prop("selectedIndex", 0);
		$jq("#facility-combobox").hide();
		$jq("#routineSampleAdd").hide();
		$jq("#viralLoadSampleAdd").hide();
		$jq("#EIDSampleAdd").show();
	}
}
</script>

<div id="orderDisplay">
<table style="width:100%">
<tr>
<td>
<table>
	<tr>
    	<td><%= StringUtil.getContextualMessageForKey("sample.batchentry.order.currentdate") %>
    		:
	        <span class="requiredlabel">*</span>
	        <span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
   	 	</td>
    	<td colspan="2">
	        <form:input path="currentDate" id="currentDate" cssClass="required" readonly="true" maxlength="10"/>
	     	<%= StringUtil.getContextualMessageForKey("sample.batchentry.order.currenttime") %>
	     	:
	        <span style="font-size: xx-small; "><%=DateUtil.getTimeUserPrompt()%></span> 
	        <form:input path="currentTime" onkeyup="filterTimeKeys(this, event);" id="currentTime" maxlength="5"/>
   		</td>
	</tr>
	<tr>
    	<td><%= StringUtil.getContextualMessageForKey( "quick.entry.received.date" ) %>
        	:
	        <span class="requiredlabel">*</span>
	        <span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
    	</td>
    	<td colspan="2">
    		<form:input path="sampleOrderItems.receivedDateForDisplay"
                  onchange="checkValidSubPages();checkValidEntryDate(this, 'past');"
                  onkeyup="addDateSlashes(this, event);"
                  maxlength="10"
                  cssClass="text required"
                  id="receivedDateForDisplay"/>
	        <spring:message code="sample.batchentry.order.receptiontime"/>
	        :
	        <span style="font-size: xx-small; "><%=DateUtil.getTimeUserPrompt()%></span>
	        <form:input path="sampleOrderItems.receivedTime" onkeyup="filterTimeKeys(this, event);" id="receivedTime" maxlength="5"  onblur="checkValidSubPages(); checkValidTime(this, true);"/>
    	</td>
	</tr>
	<tr <c:if test="${siteInfo == 'false'}">style='display:none'</c:if>>
    	<td>
    		<spring:message code="sample.entry.project.form"/>: 
    	</td>
    	<td>
    		<form:select path="study" id="study" onchange="studyChanged(this);checkValidSubPages();">
    			<option value="routine"><spring:message code="dictionary.program.routine" /></option>
    			<option value="viralLoad"><spring:message code="sample.entry.project.VL.simple.title" /></option>
    			<option value="EID"><spring:message code="sample.entry.project.EID.title" /></option>
    		</form:select>
    	</td>
	</tr>
	<tr class="spacerRow">
    	<td>&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>
</div>
