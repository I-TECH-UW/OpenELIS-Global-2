<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.formfields.FormFields,
                 us.mn.state.health.lims.common.formfields.FormFields.Field,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.StringUtil" %>
<%@ taglib uri="/tags/struts-bean"      prefix="bean" %>
<%@ taglib uri="/tags/struts-html"      prefix="html" %>
<%@ taglib uri="/tags/struts-logic"     prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" 		prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>

<bean:define id="formName"      value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
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
    invalidLabID = '<bean:message key="error.site.invalid"/>'; // Alert if value is typed that's not on list. FIX - add bad message icon
    maxRepMsg = '<bean:message key="sample.entry.project.siteMaxMsg"/>';

    resultCallBack = function (textValue) {
        siteListChanged(textValue);
    	setSave();
    };
});
</script>
<div class="hidden-fields">
	<input id="lastPatientId" type="hidden">
	<html:hidden property="sampleOrderItems.newRequesterName" name='<%=formName%>' styleId="newRequesterName"/>
	<html:hidden property="observations.projectFormName" name="<%=formName%>" styleId="projectFormName"/>
	<html:hidden name='<%=formName%>' property="ProjectData.viralLoadTest" styleId="ProjectData.vlTest"/>
	<html:hidden name='<%=formName%>' property="ProjectData.edtaTubeTaken" styleId="ProjectData.edtaTube"/>
	<html:hidden name='<%=formName%>' property="ProjectData.dryTubeTaken" styleId="ProjectData.dryTube"/>
	<html:hidden name='<%=formName%>' property="ProjectData.dbsTaken" styleId="ProjectData.dbsTaken"/>
	<html:hidden name='<%=formName%>' property="ProjectData.dnaPCR" styleId="ProjectData.dnaPCR"/>
</div>
<table style="width:100%;">
	<tr>
	<td width="60%">
	<table style="width:100%;">
		<tr>
			<td>
				<h2><bean:message key="sample.batchentry.fields.specific"/></h2>
			</td>
		</tr>
<logic:equal name="facilityIDCheck" value="true">
	<logic:equal name="facilityID" value="">
		<logic:equal name="study" value="viralLoad">
		<tr>
			<td>
				<bean:message key="sample.entry.project.ARV.centerName" />
				:  
				<html:select name="<%=formName%>" 
				    property="ProjectData.ARVcenterName"
					styleId="vl.centerName" 
					onchange="syncCenterInfo(this)">
					<app:optionsCollection name="<%=formName%>"
						property="organizationTypeLists.ARV_ORGS_BY_NAME.list" 
						label="organizationName"
						value="id" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="patient.project.centerCode" />
				:
				<html:select name="<%=formName%>" 
					property="ProjectData.ARVcenterCode" 
					styleId="vl.centerCode"
					onchange="syncCenterInfo(this)">
				<app:optionsCollection name="<%=formName%>" 
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
	            <bean:message key="sample.entry.project.siteName"/>
	            :
	            <html:select name="<%=formName%>"
	                         property="ProjectData.EIDSiteName"
	                         styleId="eid.centerName"
							 onchange="syncCenterInfo(this)">
	                <app:optionsCollection name="<%=formName%>"
	                    property="organizationTypeLists.EID_ORGS_BY_NAME.list"
	                    label="organizationName"
	                    value="id" />
	            </html:select>
	        </td>
	    </tr>
	    <tr>
	        <td>
	        	<bean:message key="sample.entry.project.siteCode"/>
	        	:
	            <html:select name="<%=formName%>"  property="ProjectData.EIDsiteCode" styleClass="text"
	                    styleId="eid.centerCode"
						onchange="syncCenterInfo(this)">
	                <app:optionsCollection name="<%=formName%>" property="organizationTypeLists.EID_ORGS.list" label="doubleName" value="id" />
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
				<h2><bean:message key="sample.batchentry.fields.common"/></h2>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="sample.batchentry.order.currentdate" />: 
				<html:text name='<%=formName %>'
					property="currentDate"
					readonly="true"></html:text>
			</td>
			<td>
				<bean:message key="sample.batchentry.order.currenttime" />: 
				<html:text name='<%=formName %>'
					property="currentTime"
					readonly="true"></html:text>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="sample.batchentry.datereceived" />:
				<html:text name='<%=formName %>'
					property="receivedDateForDisplay"
					readonly="true"></html:text>
			</td>
			<td>
				<bean:message key="sample.batchentry.timereceived" />:
				<html:text name='<%=formName %>'
					property="receivedTimeForDisplay"
					readonly="true"></html:text>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table style="width:100%">
					<tr>
						<th><bean:message key="sample.entry.sample.type"/></th>
						<th><bean:message key="test.testName"/></th>
					</tr>
					<tr>
						<td><%= request.getAttribute("sampleType") %></td>
						<td><%= request.getAttribute("testNames") %></td>
					</tr>
				</table>
				<html:hidden name='<%=formName %>'
					property="sampleXML"/>	
			</td>
		</tr>
		<tr>
	<logic:notEqual name="facilityID" value="">
				<tr>
					<td>
						<bean:message key="sample.batchentry.barcode.label.facilityid" /> 
						: <%= request.getAttribute("facilityName") %>
						<html:hidden name="<%=formName %>"
							property="sampleOrderItems.referringSiteId"
							styleId="requesterId"/>
							
					</td>
				</tr>
	</logic:notEqual>
	<logic:equal name="facilityID" value="">
		<logic:notEqual name="newRequesterName" value="">
				<tr>
					<td>
						<bean:message key="sample.batchentry.barcode.label.facilityid" /> 
						: <%= request.getAttribute("facilityName") %>
						<html:hidden name="<%=formName %>"
							property="sampleOrderItems.referringSiteId"
							styleId="requesterId"/>
							
					</td>
				</tr>
		</logic:notEqual>
	</logic:equal>
		</tr>
	</table>
	</td>
	</tr>
</table>
