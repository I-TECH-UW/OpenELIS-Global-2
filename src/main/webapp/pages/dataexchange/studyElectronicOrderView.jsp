<%@page import="org.openelisglobal.internationalization.MessageUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="select2/css/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="select2/js/select2.min.js"></script>
<script type="text/javascript" src="scripts/tableSort.js"></script>
<script type="text/javascript" src="scripts/utilities.js"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js?"></script>
<script type="text/javascript">

var colSort = [0,0,0,0,0,0,0,0,0,0,0,0];
var dirty = false;
var entering = false;

function makeDirty() {
    dirty = true;
    function formWarning() {
        return "<spring:message code="banner.menu.dataLossWarning"/>";
    }
    window.onbeforeunload = formWarning;
}

function searchByIdentifier() {
	const params = new URLSearchParams({
		searchType: "IDENTIFIER",
		searchValue: jQuery('#searchValue').val(),
		useAllInfo: jQuery('#allInfo1').is(':checked'),
		});
	
	window.location.href = "StudyElectronicOrders?" + params.toString();

}

function searchByFacility() {
	const params = new URLSearchParams({
		searchType: "FACILITY",
		organizationId: jQuery('#organizationId').val(),
		useAllInfo: jQuery('#allInfo2').is(':checked'),
		});
	
	window.location.href = "StudyElectronicOrders?" + params.toString();

}

function searchByDateAndStatus() {
	const params = new URLSearchParams({
		searchType: "DATE_STATUS",
		startDate: jQuery('#startDate').val(),
		endDate: jQuery('#endDate').val(),
		statusId: jQuery('#statusId').val(),
		useAllInfo: jQuery('#allInfo3').is(':checked'),
		});
	
	window.location.href = "StudyElectronicOrders?" + params.toString();

}

function sort(col) {
	if (entering) {
		alert('No sorting while data is being entered');
	} else {
		sortTableJquery('eOrderTable', col, ((colSort[col - 1]++) % 2) != 0);
	}
}


function enterOrder(index) {
	entering = true;
	var externalOrderId = jQuery('#externalOrderId_' + index).val();
	
	var tBody = document.getElementById('eOrderTableBody');
	var eOrderRow = document.getElementById('eOrderRow_' + index);
	var enterButton = document.getElementById('enterButton_' + index);
	var editButton = document.getElementById('editButton_' + index);
	var rejectButton = document.getElementById('rejectButton_' + index);
	enterButton.style.display='none';
	editButton.style.display='none';
	rejectButton.style.display='none';
	eOrderRow.style.background = '#FF6';
	sortedRowIndex = eOrderRow.rowIndex;
	var row = tBody.insertRow(sortedRowIndex - 1);
	row.id = 'filler-row_' + index;
	row.style.display = 'none';
	row = tBody.insertRow(sortedRowIndex - 1);
	row.id = 'enter-row_' + index;
	var cell = row.insertCell();
	cell = row.insertCell();
	cell.colSpan = '13';
	<c:set var="labPrefix">"<spring:message code='sample.entry.project.LVL'/>"</c:set>
	var inputLabelHTML = '<div class="blank"><spring:message code="quick.entry.accession.number"/>: &nbsp;<spring:message code="sample.entry.project.LVL"/></div>';
	var inputHTML='<input size="5" class="text" type="text" id="eOrderLabNumber_' + index + '" name="eOrderLabNumber_' + index + '"';
	inputHTML+= ' onchange="handleLabNoChange( this, <spring:escapeBody javaScriptEscape="true">${labPrefix}</spring:escapeBody>, false );makeDirty();"  maxlength="5"/>';
		
	var saveHTML = '&nbsp;<button type="button" onclick="saveEntry(\'' + index + '\')"><spring:message code='label.button.save'/></button>';
	var cancelHTML = '&nbsp;<button type="button" onclick="restoreRow(\'' + index + '\');"><spring:message code='label.button.cancel'/></button>';
	var checkboxHTML = '';
	var checkboxLabelHTML = '';
	
	cell.innerHTML += inputLabelHTML + inputHTML + saveHTML + cancelHTML + checkboxHTML + checkboxLabelHTML;
}


function rejectOrder(index) {
	entering = true;
	var externalOrderId = jQuery('#externalOrderId_' + index).val();
	
	var tBody = document.getElementById('eOrderTableBody');
	var eOrderRow = document.getElementById('eOrderRow_' + index);
	var enterButton = document.getElementById('enterButton_' + index);
	var editButton = document.getElementById('editButton_' + index);
	var rejectButton = document.getElementById('rejectButton_' + index);
	enterButton.style.display='none';
	editButton.style.display='none';
	rejectButton.style.display='none';
	eOrderRow.style.background = '#FF6';
	sortedRowIndex = eOrderRow.rowIndex;
	var row = tBody.insertRow(sortedRowIndex - 1);
	row.id = 'filler-row_' + index;
	row.style.display = 'none';
	row = tBody.insertRow(sortedRowIndex - 1);
	row.id = 'enter-row_' + index;
	var cell = row.insertCell();
	cell = row.insertCell();
	cell.colSpan = '13';
	var inputHTMLtable = '<table id="qaEventsTable"><thead><tr><th style="width:10%"><spring:message code="label.refusal.reason" /><span class="requiredlabel">*</span></th>';
	inputHTMLtable+='<th style="width:13%"><spring:message code="label.biologist"/></th>';
	inputHTMLtable+='<th><spring:message code="nonconformity.note"/></th></tr></thead>';
	inputHTMLtable+='<tbody><tr><td> <form:select path="qaEventId" id="qaEventSelectBox"  class="eoder_select_qaEvent" htmlEscape="true"> <form:option value="">&nbsp;</form:option> ';
	inputHTMLtable+='<form:options items="${form.qaEvents}" itemLabel="value" itemValue="id" htmlEscape="true" /></form:select></td>';
	inputHTMLtable+= '<td> <form:input path="qaAuthorizer" id="qaAuthorizerInput" style="width: 99%" htmlEscape="true"/> </td>';
	inputHTMLtable+='<td> <form:input path="qaNote" id="qaNoteInput" style="width: 99%" htmlEscape="true"/> </td></tr>';
	inputHTMLtable+='</tbody></table>';

	var saveHTML = '&nbsp;<button type="button" onclick="saveEntry(\'' + index + '\')"><spring:message code='label.button.save'/></button>';
	var cancelHTML = '&nbsp;<button type="button" onclick="restoreRow(\'' + index + '\');"><spring:message code='label.button.cancel'/></button>';
	var checkboxHTML = '';
	var checkboxLabelHTML = '';
	
	cell.innerHTML += inputHTMLtable + saveHTML + cancelHTML + checkboxHTML + checkboxLabelHTML;
}

function restoreRow(index) {
	var table = document.getElementById('eOrderTable');
	var fillerRow = document.getElementById('filler-row_' + index);
	table.deleteRow(fillerRow.rowIndex);
	var enterRow = document.getElementById('enter-row_' + index);
	table.deleteRow(enterRow.rowIndex);
	var eOrderRow = document.getElementById('eOrderRow_' + index);
	eOrderRow.style.display = '';
	eOrderRow.style.background = '';
	var enterButton = document.getElementById('enterButton_' + index);
	var editButton = document.getElementById('editButton_' + index);
	var rejectButton = document.getElementById('rejectButton_' + index);
	enterButton.style.display='';
	editButton.style.display='';
	rejectButton.style.display='';
}

function saveEntry(index) {
	entering = false;
	
    var labNumber = jQuery("#eOrderLabNumber_" + index).val();
	var externalOrderId = jQuery('#externalOrderId_' + index).val();

	restoreRow(index);
	markRowOutOfSync(index);
	
	window.open('SampleEntryByProject?ID=' + externalOrderId + '&labNumber=' + labNumber + '&attemptAutoSave=true', "_blank");
}

function markRowOutOfSync(index) {
	jQuery('#enterButton_' + index).attr('disabled', 'disabled');
	jQuery('#editButton_' + index).attr('disabled', 'disabled');
	jQuery('#rejectButton_' + index).attr('disabled', 'disabled');
	jQuery('#eOrderRow_' + index).addClass('unsynced-resource');
}

function getNextAccessionNumber(index) {
    generateNextScanNumber(processScanSuccess, false, index);
}

function processScanSuccess(xhr, index) {
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var returnedData = formField.firstChild.nodeValue;

    var message = xhr.responseXML.getElementsByTagName("message").item(0);

    var success = message.firstChild.nodeValue == "valid";

    if (success) {
        jQuery("#eOrderLabNumber_" + index).val(returnedData);

    } else {
        alert("<spring:message code="error.accession.no.next"/>");
        $("labNo").value = "";
    }

}

function editOrder(index) {
	var externalOrderId = jQuery('#externalOrderId_' + index).val();
	location.href='SampleEntryByProject?type=Initial&ID=' + externalOrderId;
}

jQuery(document).ready( function() {
	jQuery('.eoder_select_site').select2({
	    placeholder: "<spring:message code='study.eorder.choose_site.label' text='Choisir un site' />"
	});
});
</script>
<hr>
<spring:message code='study.eorder.search.patient_facility.title' text='Rechercher par code Patient ou par Site de prise en charge' /><br/><br/>
<c:set var = "patientCodeLabel"> <spring:message code="report.patientCode" text="get all info" /> </c:set>
<form:hidden id="searchType" path="searchType" htmlEscape="true"/>
<form:input path="searchValue" id="searchValue" placeholder="${patientCodeLabel}" htmlEscape="true"/> 
<button type="button" onclick="searchByIdentifier()"><spring:message code="label.button.search" /></button>
<form:checkbox path="useAllInfo" id="allInfo1" value="true"/> <spring:message code="label.eorder.allinfo" text="get all info" />
<br/><br/>
<form:select path="organizationId"
			 id="organizationId"
			 class="eoder_select_site">
	<form:option value="">&nbsp;</form:option>
	<form:options items="${form.organizationList}" itemLabel="doubleName" itemValue="id" htmlEscape="true"/>
</form:select>
<button type="button" onclick="searchByFacility()" class="button"><spring:message code="label.button.search" /></button>
<form:checkbox path="useAllInfo" id="allInfo2" value="true"/> <spring:message code="label.eorder.allinfo" text="get all info" />
<hr>
<spring:message code='study.eorder.search.date_range.title'/>

<br> 
<spring:message code='study.eorder.search.date.start'/>:
<form:input path="startDate" onkeyup="addDateSlashes(this,event); " htmlEscape="true"/> &nbsp;&nbsp;
<spring:message code='study.eorder.search.date.end'/>:
<form:input path="endDate"	onkeyup="addDateSlashes(this,event); " htmlEscape="true"/>
<spring:message code='eorder.status'/>
<form:select path="statusId">
<option value=""><spring:message code='study.eorder.all_status'/> </option>
<form:options items="${form.statusSelectionList}" itemLabel="value" itemValue="id" htmlEscape="true"/>
</form:select>
<button type="button" onclick="searchByDateAndStatus()"><spring:message code="label.button.search" /></button>
<form:checkbox path="useAllInfo" id="allInfo3r" value="true" /> <spring:message code="label.eorder.allinfo" text="get all info" />
<br>
<hr>

<c:if test="${empty form.eOrders && form.searchFinished}">
    <h2><spring:message code="referral.noreferralDisplayItem"/></h2>
</c:if>
<c:if test="${not empty form.eOrders}"> 
<spring:message code="eorder.search.result.title"/>
<br>
<table>
<tr class='unsynced-resource'>
<td>
<spring:message code="eorder.table.highlighted.label"/>
</td>
</tr>
</table>
<table id="eOrderTable"  class='alt-color-table' style="width:100%">
<thead>
<tr>
    <th class='split-content'>
    	<c:out value="No."/>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.request.id"/>
    	<span class="fa" onclick='sort(1)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.requester.facility"/>
    	<span class="fa" onclick='sort(2)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.patient.code"/>
    	<span class="fa" onclick='sort(3)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.patient.gender"/>
    	<span class="fa" onclick='sort(4)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.patient.birth_date"/>
    	<span class="fa" onclick='sort(5)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.request.date"/>
    	<span class="fa" onclick='sort(6)'><i class="fas fa-sort"></i></span>
    </th>
	<th class='split-content'>
    	<spring:message code="study.eorder.request.priority"/>
    	<span class="fa" onclick='sort(7)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.request.status"/>
    	<span class="fa" onclick='sort(8)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.request.test_name"/>
    	<span class="fa" onclick='sort(9)'><i class="fas fa-sort"></i></span>
    </th>
    <th class='split-content'>
    	<spring:message code="study.eorder.lab_number"/>
    	<span class="fa" onclick='sort(10)'><i class="fas fa-sort"></i></span>
    </th>
	<th style="background-color:white;" colspan="3">
		<spring:message code="study.eorder.action.title"/>
	</th>
</tr>
</thead>
<tbody id="eOrderTableBody">
	<c:forEach items="${form.eOrders}" var="eOrder" varStatus="iter">
		<c:set var="entered" value="${not empty eOrder.labNumber}"/>
		<tr id='eOrderRow_${iter.index}'> 
	    <td>
	       <c:out value="${iter.index + 1}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.externalOrderId}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.requestingFacility}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.subjectNumber}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.gender}"/>
	    </td>
	    <td class="dateCol">
	       <c:out value="${eOrder.birthDate}"/>
	    </td>
		<td class="dateCol">
	       <c:out value="${eOrder.requestDateDisplay}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.priority}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.status}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.testName}"/>
	    </td>
	    <td>
	       <c:out value="${eOrder.referringLabNumber}"/>
	    </td>
	    <td>
	    	<form:hidden id="externalOrderId_${iter.index}" path="eOrders[${iter.index}].externalOrderId" htmlEscape="true" />
	    	<button type="button" id="enterButton_${iter.index}" onclick="enterOrder('${iter.index}')" ${entered ? 'disabled="disabled"' : '' }>
	    	<spring:message code="study.eorder.action.enter"/>
	    	</button>
	  	</td>
    	<td>
		    <button type="button" id="editButton_${iter.index}" onclick="editOrder('${iter.index}')" ${entered ? 'disabled="disabled"' : '' }>
		    <spring:message code="study.eorder.action.edit"/>
		    </button>
	    </td>
	    <td>
		    <button type="button" id="rejectButton_${iter.index}" onclick="rejectOrder('${iter.index}')" ${entered ? 'disabled="disabled"' : '' }>
		    <spring:message code="study.eorder.action.reject"/>
		    </button>
	    </td>
	    <td style="background-color:white;">
			<c:if test="${not empty eOrder.warnings}">
				<span class="show-text-hover">
				<img src="./images/nonconforming.gif">
				<span class="hidden-text">
				<c:forEach items="${eOrder.warnings}" var="warning">
	       			<c:out value="${warning}"/><br>
				</c:forEach> 
				</span>
				</span>
			</c:if>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
</c:if>

