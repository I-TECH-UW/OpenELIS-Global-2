<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.util.Versioning" %>
<%@ taglib uri="/tags/struts-bean"      prefix="bean" %>
<%@ taglib uri="/tags/struts-html"      prefix="html" %>
<%@ taglib uri="/tags/struts-logic"     prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" 		prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>

<bean:define id="formName"      value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:parameter id="patientInfoCheck" name="patientInfoCheck" value="false" />
<bean:parameter id="facilityIDCheck" name="facilityIDCheck" value="false" />
<bean:parameter id="facilityID" name="facilityID" value="" />
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
<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript">
var inPrintState = true;	//is entryMethod in a print state 

function finish() {
    window.location = "SampleBatchEntrySetup.do";
}

//check fields, entryMethod printState, and then call patientManagement setSave
function setSave() {
	var saveAllowed = checkOptionalFields() && inPrintState;
	$jq("#saveButtonId").prop('disabled', !saveAllowed);
}

//if optional fields are being used, check them for validity
function checkOptionalFields() {
	var fieldsValid = true;
	<logic:equal name="patientInfoCheck" value="true">
		if (!patientFormValid()) {
			return false;
		}
	</logic:equal>
	<logic:equal name="facilityIDCheck" value="true">
		if (!$jq("#requesterId").val() && !$jq("#newRequesterName").val()) {
			return false;
		}
	</logic:equal>
	return fieldsValid;
}

//sets patient PK when identifying information is provided for patientProperties
//fixes bug where person is added to database again and again
function setPatient() {
	var splitName;
  var lastName = "";
  var firstName = "";
  var STNumber = "";
  var subjectNumber = "";
  var nationalID = "";
  var labNumber = "";
	labNumber = $jq("#labNo").val();
  $jq("#searchLabNumber").val(labNumber);
	if (window.hasIdentifyingInfo) {
		if (hasIdentifyingInfo() && !$jq("#patientPK_ID").val()) {
		    patientSearch(lastName, firstName, STNumber, subjectNumber, nationalID, labNumber, "", false, processSearchSuccess);
		}
	}
}

function siteListChanged(textValue) {
    var siteList = $("requesterId");

    //if the index is 0 it is a new entry, if it is not then the textValue may include the index value
    if (siteList.selectedIndex == 0 || siteList.options[siteList.selectedIndex].label != textValue) {
        $("newRequesterName").value = textValue;
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
			<logic:equal name="newRequesterName" value="">
			<tr>
				<td>
					<bean:message key="sample.batchentry.barcode.label.facilityid" />
					:  
					<logic:equal value="false" name='<%=formName%>' property="sampleOrderItems.readOnly" >
				        <html:select styleId="requesterId"
				                     name="<%=formName%>"
				                     property="sampleOrderItems.referringSiteId"
				                     onkeyup="capitalizeValue( this.value );"
				                     onchange="siteListChanged(this);setSave();"
				                >
				            <option value=""></option>
				            <html:optionsCollection name="<%=formName%>" property="sampleOrderItems.referringSiteList" label="value"
				                                    value="id"/>
				        </html:select>
					</logic:equal>
				    <logic:equal value="true" name='<%=formName%>' property="sampleOrderItems.readOnly" >
				            <html:text property="sampleOrderItems.referringSiteName" name="<%=formName%>" style="width:300px" />
				    </logic:equal>
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
				property="sampleOrderItems.receivedDateForDisplay"
				readonly="true"></html:text>
		</td>
		<td>
			<bean:message key="sample.batchentry.timereceived" />:
			<html:text name='<%=formName %>'
				property="sampleOrderItems.receivedTime"
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
