<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.openelisglobal.common.action.IActionConstants,
                 org.openelisglobal.common.util.ConfigurationProperties,
                 org.openelisglobal.common.util.ConfigurationProperties.Property,
                 org.openelisglobal.common.util.Versioning" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<%
	boolean restrictNewReferringSiteEntries = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.restrictFreeTextRefSiteEntry, "true");
%>

<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?">
<link rel="stylesheet" href="css/customAutocomplete.css?">

<script type="text/javascript" src="scripts/utilities.js"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?"></script>
<script src="scripts/ui/jquery.ui.core.js?"></script>
<script src="scripts/ui/jquery.ui.widget.js?"></script>
<script src="scripts/ui/jquery.ui.button.js?"></script>
<script src="scripts/ui/jquery.ui.menu.js?"></script>
<script src="scripts/ui/jquery.ui.position.js?"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?"></script>
<script src="scripts/customAutocomplete.js?"></script>
<script type="text/javascript">
var inPrintState = true;	//is entryMethod in a print state 

function finish() {
    window.location = "SampleBatchEntrySetup.do";
}

//check fields, entryMethod printState, and then call patientManagement setSave
function setSave() {
	var saveAllowed = checkOptionalFields() && inPrintState;
	jQuery("#saveButtonId").prop('disabled', !saveAllowed);
}

//if optional fields are being used, check them for validity
function checkOptionalFields() {
	var fieldsValid = true;
	<c:if test="${patientInfoCheck}">
		if (!patientFormValid()) {
			return false;
		}
	</c:if>
	<c:if test="${facilityIDCheck}">
		if (!jQuery("#requesterId").val() && !jQuery("#newRequesterName").val()) {
			return false;
		}
	</c:if>
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
	labNumber = jQuery("#labNo").val();
  jQuery("#searchLabNumber").val(labNumber);
	if (window.hasIdentifyingInfo) {
		if (hasIdentifyingInfo() && !jQuery("#patientPK_ID").val()) {
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

jQuery(document).ready(function() {
	setSave();
});

jQuery(document).ready(function () {
    var dropdown = jQuery("select#requesterId");
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
	<c:if test="${form.facilityIDCheck}">
		<c:if test='${empty form.facilityID}'>
			<c:if test='${empty form.sampleOrderItems.newRequesterName}'>
			<tr>
				<td>
					<spring:message code="sample.batchentry.barcode.label.facilityid" />
					:  
					<c:if test="${not form.sampleOrderItems.readOnly}">
				        <form:select id="requesterId" path="sampleOrderItems.referringSiteId" onkeyup="capitalizeValue( this.value );" onchange="siteListChanged(this);setSave();">
				            <option value=""></option>
				            <form:options items="${form.sampleOrderItems.referringSiteList}" itemLabel="value" itemValue="id"/>
				        </form:select>
					</c:if>
					<c:if test="${form.sampleOrderItems.readOnly}">
				            <form:input path="form.sampleOrderItems.referringSiteName" cssStyle="width:300px" />
				    </c:if>
				</td>
			</tr>
			</c:if>
		</c:if>
	</c:if>
	<c:if test="${form.patientInfoCheck}">
		<tr>
			<td>
				<tiles:insertAttribute name="patientInfo" />
			</td>
		</tr>
	</c:if>
	<c:if test="${not form.patientInfoCheck}">
	<tr>
			<td>
				<form:hidden path="patientProperties.patientUpdateStatus" id="processingStatus" value="ADD" />
			</td>
	</tr>
	</c:if>
	<tr>
		<td>
			<tiles:insertAttribute name="entryMethod" />
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
			<form:input path="currentDate" readonly="true"/>
		</td>
		<td>
			<spring:message code="sample.batchentry.order.currenttime" />:
			<form:input path="currentTime" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<spring:message code="sample.batchentry.datereceived" />:
			<form:input path="sampleOrderItems.receivedDateForDisplay" readonly="true"/>
		</td>
		<td>
			<spring:message code="sample.batchentry.timereceived" />:
			<form:input path="sampleOrderItems.receivedTime" readonly="true"/>
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
			<form:hidden path="sampleXML"/>	
		</td>
	</tr>
	<tr>
		<c:if test='${not empty form.facilityID}'>
			<tr>
				<td>
					<spring:message code="sample.batchentry.barcode.label.facilityid" /> 
					: <%= request.getAttribute("facilityName") %>
					<form:hidden path="sampleOrderItems.referringSiteId" id="requesterId"/>
						
				</td>
			</tr>
		</c:if>
		<c:if test='${empty form.facilityID}'>
			<c:if test='${not empty form.sampleOrderItems.newRequesterName}'>
			<tr>
				<td>
					<spring:message code="sample.batchentry.barcode.label.facilityid" /> 
					: <%= request.getAttribute("facilityName") %>
					<form:hidden path="sampleOrderItems.referringSiteId" id="requesterId"/>
				</td>
			</tr>
			</c:if>
		</c:if>
	</tr>
</table>
</td>
</tr>
</table>
