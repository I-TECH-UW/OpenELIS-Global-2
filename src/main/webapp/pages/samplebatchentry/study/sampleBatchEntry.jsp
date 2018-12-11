<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.formfields.FormFields,
                 us.mn.state.health.lims.common.formfields.FormFields.Field,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.StringUtil" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

      
<bean:parameter id="patientInfoCheck" name="patientInfoCheck" value="false" />
<bean:parameter id="facilityIDCheck" name="facilityIDCheck" value="false" />
<bean:parameter id="facilityID" name="facilityID" value="" />
<bean:parameter id="study" name="study" value="" />
<bean:parameter id="newRequesterName" name="sampleOrderItems.newRequesterName" value="" />

<%!
String path = "";
String basePath = "";
boolean restrictNewReferringSiteEntries = false;
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
restrictNewReferringSiteEntries = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.restrictFreeTextRefSiteEntry, "true");
%>

<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

<script type="text/javascript" src="scripts/utilities.js"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/retroCIUtilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/entryByProjectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript">
var inPrintState = true;	//is entryMethod in a print state 
var study = "<%= request.getParameter("study") %>";

function finish() {
	window.onbeforeunload = function(){};
    window.location = "SampleBatchEntrySetup.do";
}

//check fields, entryMethod printState, and then call patientManagement setSave
function setSave() {
	var saveAllowed = inPrintState;
	$jq("#saveButtonId").prop('disabled', !saveAllowed);
}

function siteListChanged(textValue) {
    var siteList = $("requesterId");

    //if the index is 0 it is a new entry, if it is not then the textValue may include the index value
    if (siteList.selectedIndex == 0 || siteList.options[siteList.selectedIndex].label != textValue) {
        $("newRequesterName").value = textValue;
    }
}

function syncCenterInfo(centerInfo) {
	if (centerInfo == document.getElementById("vl.centerName")) {
		syncLists(centerInfo, document.getElementById("vl.centerCode"));
	} else if (centerInfo == document.getElementById("vl.centerCode")) {
		syncLists(centerInfo, document.getElementById("vl.centerName"));
	} else if (centerInfo == document.getElementById("eid.centerName")) {
		syncLists(centerInfo, document.getElementById("eid.centerCode"));
	} else if (centerInfo == document.getElementById("eid.centerCode")) {
		syncLists(centerInfo, document.getElementById("eid.centerName"));
	}
}

$jq(document).ready(function() {
	setSave();
});

$jq(document).ready(function () {
    var dropdown = $jq("select#requesterId");
    autoCompleteWidth = dropdown.width() + 66 + 'px';
    <% if(restrictNewReferringSiteEntries) { %>
   			clearNonMatching = true;
    <% } else {%>
    		clearNonMatching = false;
    <% } %>
    capitialize = true;
    // Actually executes autocomplete
    dropdown.combobox();
    invalidLabID = '<spring:message code="error.site.invalid"/>'; // Alert if value is typed that's not on list. FIX - add bad message icon
    maxRepMsg = '<spring:message code="sample.entry.project.siteMaxMsg"/>';

    resultCallBack = function (textValue) {
        siteListChanged(textValue);
    	setSave();
    };
});
</script>
<div class="hidden-fields">
	<input id="lastPatientId" type="hidden">
	<form:hidden path="sampleOrderItems.newRequesterName" name='${form.formName}' id="newRequesterName"/>
	<form:hidden path="observations.projectFormName" name="${form.formName}" id="projectFormName"/>
	<form:hidden path="ProjectData.viralLoadTest" id="ProjectData.vlTest"/>
	<form:hidden path="ProjectData.edtaTubeTaken" id="ProjectData.edtaTube"/>
	<form:hidden path="ProjectData.dryTubeTaken" id="ProjectData.dryTube"/>
	<form:hidden path="ProjectData.dbsTaken" id="ProjectData.dbsTaken"/>
	<form:hidden path="ProjectData.dnaPCR" id="ProjectData.dnaPCR"/>
</div>
<table style="width:100%;">
	<tr>
	<td width="60%">
	<table style="width:100%;">
		<tr>
			<td>
				<h2><spring:message code="sample.batchentry.fields.specific"/></h2>
			</td>
		</tr>
<logic:equal name="facilityIDCheck" value="true">
	<logic:equal name="facilityID" value="">
		<logic:equal name="study" value="viralLoad">
		<tr>
			<td>
				<spring:message code="sample.entry.project.ARV.centerName" />
				:  
				<html:select name="${form.formName}" 
				    property="ProjectData.ARVcenterName"
					id="vl.centerName" 
					onchange="syncCenterInfo(this)">
					<app:optionsCollection name="${form.formName}"
						property="organizationTypeLists.ARV_ORGS_BY_NAME.list" 
						label="organizationName"
						value="id" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="patient.project.centerCode" />
				:
				<html:select name="${form.formName}" 
					property="ProjectData.ARVcenterCode" 
					id="vl.centerCode"
					onchange="syncCenterInfo(this)">
				<app:optionsCollection name="${form.formName}" 
					property="organizationTypeLists.ARV_ORGS.list" 
					label="doubleName" 
					value="id" />
				</html:select>
			</td>
		</tr>
		</logic:equal>
		<logic:equal name="study" value="EID">
		<tr>
	        <td>
	            <spring:message code="sample.entry.project.siteName"/>
	            :
	            <html:select name="${form.formName}"
	                         property="ProjectData.EIDSiteName"
	                         id="eid.centerName"
							 onchange="syncCenterInfo(this)">
	                <app:optionsCollection name="${form.formName}"
	                    property="organizationTypeLists.EID_ORGS_BY_NAME.list"
	                    label="organizationName"
	                    value="id" />
	            </html:select>
	        </td>
	    </tr>
	    <tr>
	        <td>
	        	<spring:message code="sample.entry.project.siteCode"/>
	        	:
	            <html:select name="${form.formName}"  property="ProjectData.EIDsiteCode" styleClass="text"
	                    id="eid.centerCode"
						onchange="syncCenterInfo(this)">
	                <app:optionsCollection name="${form.formName}" property="organizationTypeLists.EID_ORGS.list" label="doubleName" value="id" />
	            </html:select>
	        </td>
	     </tr>
	     </logic:equal>
	</logic:equal>
</logic:equal>
<logic:equal name="patientInfoCheck" value="true">
		<tr>
			<td>
				<tiles:insert attribute="patientInfo" />
			</td>
		</tr>
</logic:equal>
		<tr>
			<td>
				<tiles:insert attribute="entryMethod" />
			</td>
		</tr>
	</table>
	</td>
	<td width="40%" style="vertical-align:top">
	<table style="width:100%;" id="summary">
		<tr>
			<td colspan="2">
				<h2><spring:message code="sample.batchentry.fields.common"/></h2>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="sample.batchentry.order.currentdate" />: 
				<html:text name='${form.formName}'
					property="currentDate"
					readonly="true"></html:text>
			</td>
			<td>
				<spring:message code="sample.batchentry.order.currenttime" />: 
				<html:text name='${form.formName}'
					property="currentTime"
					readonly="true"></html:text>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="sample.batchentry.datereceived" />:
				<html:text name='${form.formName}'
					property="receivedDateForDisplay"
					readonly="true"></html:text>
			</td>
			<td>
				<spring:message code="sample.batchentry.timereceived" />:
				<html:text name='${form.formName}'
					property="receivedTimeForDisplay"
					readonly="true"></html:text>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table style="width:100%">
					<tr>
						<th><spring:message code="sample.entry.sample.type"/></th>
						<th><spring:message code="test.testName"/></th>
					</tr>
					<tr>
						<td><%= request.getAttribute("sampleType") %></td>
						<td><%= request.getAttribute("testNames") %></td>
					</tr>
				</table>
				<html:hidden name='${form.formName}'
					property="sampleXML"/>	
			</td>
		</tr>
		<tr>
	<logic:notEqual name="facilityID" value="">
				<tr>
					<td>
						<spring:message code="sample.batchentry.barcode.label.facilityid" /> 
						: <%= request.getAttribute("facilityName") %>
						<html:hidden name="${form.formName}"
							property="sampleOrderItems.referringSiteId"
							id="requesterId"/>
							
					</td>
				</tr>
	</logic:notEqual>
	<logic:equal name="facilityID" value="">
		<logic:notEqual name="newRequesterName" value="">
				<tr>
					<td>
						<spring:message code="sample.batchentry.barcode.label.facilityid" /> 
						: <%= request.getAttribute("facilityName") %>
						<html:hidden name="${form.formName}"
							property="sampleOrderItems.referringSiteId"
							id="requesterId"/>
							
					</td>
				</tr>
		</logic:notEqual>
	</logic:equal>
		</tr>
	</table>
	</td>
	</tr>
</table>
