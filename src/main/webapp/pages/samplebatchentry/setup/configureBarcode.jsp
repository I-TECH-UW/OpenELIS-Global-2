<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!
boolean restrictNewReferringSiteEntries = false;
%>
<%
restrictNewReferringSiteEntries = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.restrictFreeTextRefSiteEntry, "true");
%>

<script type="text/javascript" >

function togglePatientInfo() {
	if (document.getElementsByName('patientInfoCheck')[0].disabled == false) {
		document.getElementsByName('patientInfoCheck')[0].disabled = true;
	} else {
		document.getElementsByName('patientInfoCheck')[0].disabled = false;
	}
}

function checkFacilityID() {
	document.getElementsByName('facilityIDCheck')[0].disabled = false;
}
function uncheckFacilityID() {
	document.getElementsByName('facilityIDCheck')[0].disabled = true;
}
function toggleFacilityID() {
	if (document.getElementsByName('facilityIDCheck')[0].disabled == false) {
		uncheckFacilityID();
	} else {
		checkFacilityID();
	}
}
function processFacilityIDChange() {
	if (document.getElementById('requesterId').value != '') {
		checkFacilityID();
		document.getElementById('psuedoFacilityID').checked = true;
		document.getElementById('psuedoFacilityID').disabled = true;
	} else if (document.getElementById('newRequesterName').value != '') {
		checkFacilityID();
		document.getElementById('psuedoFacilityID').checked = true;
		document.getElementById('psuedoFacilityID').disabled = true;
	} else {
		document.getElementById('psuedoFacilityID').disabled = false;
	}
}

//validation logic for this 'page'
function configBarcodeValid() {
	if (document.getElementsByName('method')[0].value == 'On Demand') {
		return true;
	} else if (document.getElementsByName('method')[0].value == 'Pre-Printed') {
		 return true;
	} else {
		return false;
	}
}

function siteListChanged(textValue) {
    var siteList = $("requesterId");

    //if the index is 0 it is a new entry, if it is not then the textValue may include the index value
    if (siteList.selectedIndex == 0 || siteList.options[siteList.selectedIndex].label != textValue) {
        $("newRequesterName").value = textValue;
    }
}

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
    	processFacilityIDChange();
       // setOrderModified();
        //setCorrectSave();
    };
});

</script>
<form:hidden path="sampleOrderItem.newRequesterName" id="newRequesterName"/>

Barcode Method : 
<form:select name="method" id="methodId" path="method">
	<form:option value="On Demand"><spring:message code="sample.batchentry.barcode.ondemand"/></form:option>
	<form:option value="Pre-Printed"><spring:message code="sample.batchentry.barcode.preprinted"/></form:option>
</form:select>

<table style="width:100%">
<tr>
<td>
<table>
	<tr>
 		<td>
			<spring:message code="sample.batchentry.barcode.label.options"/>
		</td>
		<td>
			<input type="checkbox"
			id="psuedoFacilityID"
			onchange="toggleFacilityID();">
			<form:hidden path="facilityIDCheck" name="facilityIDCheck" disabled="true" value="true"/>
			<spring:message code="sample.batchentry.barcode.label.facilityid"/>
		</td>
		<td>
			<spring:message code="sample.batchentry.barcode.label.facilityid"/>:
		</td>
		<td>
			<c:if test="${not form.sampleOrderItem.readOnly}">
		    	<form:select path="facilityID" id="requesterId" onchange="siteListChanged(this);processFacilityIDChange();" onkeyup="capitalizeValue( this.value );">
		    	
		            <option value=""></option>
		            <form:options items="${form.sampleOrderItem.referringSiteList}" itemValue="id" itemLabel="value"/>
		    	</form:select>
			</c:if>
		    <c:if test="${form.sampleOrderItem.readOnly}">
		    	<form:input path="facilityID" id="requesterId"/>
		    </c:if>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="checkbox"
			id="psuedoPatientInfo"
			onchange="togglePatientInfo()"
			/>
			<form:hidden path="patientInfoCheck" name="patientInfoCheck" disabled="true" value="true"/>
			<spring:message code="sample.batchentry.barcode.label.patientinfo"/>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
