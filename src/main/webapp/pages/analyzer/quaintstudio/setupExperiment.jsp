<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<style>

.well {
 	width: 5rem; 
 	height:5rem; 
	cursor: pointer;
	background-size:7rem 7rem;
	background-repeat: no-repeat;
	padding: 1rem;
}

.inactiveWell {
	background-image:url(./images/circle_transparent.png);
}

.activeWell {
	color: #336699;
	background-image: url(./images/circle_clicked.png)
}

.well-label {
	font-weight: bold
}

.well-value {
}
</style>

<script type="text/javascript" src="./scripts/ajaxCalls.js"></script>
<script>

var wellRows = 8;
var wellCols = 12;

function setWell(well) {
	jQuery(".well").removeClass("activeWell");
	jQuery(".well").addClass("inactiveWell");
	well.classList.add("activeWell");
	well.classList.add("filledWell");
	jQuery(".activeWell").removeClass("inactiveWell");
	if  (jQuery(".activeWell > .well-value-full").val()) {
		setValuesFromXML(new DOMParser().parseFromString(jQuery(".activeWell > .well-value-full").val(), 'application/xml'));
	}
	setTotalEmpty();
	jQuery("#selectPatientButtonID").prop("disabled", false);
}

function setTotalEmpty() {
	var total = wellCols * wellRows;
	var filled = jQuery(".filledWell").length;
	var empty = total - filled;
	jQuery(".emptyTotalNum").text(empty);
}

function handleSelectedPatientAlt(identifier, accessionNumber) {
	if (!identifier) {
		identifier = getCurActiveIdentifier();
	}
	if (!accessionNumber) {
	    if(jQuery("#searchCriteria").val() == 5){//lab number
	        accessionNumber = jQuery("#searchValue").val();
	    }
	}
    var patientID = patientSelectID ? patientSelectID : "";
    var searchUrl = '${form.formAction}'.sub('Form','') + "?accessionNumber=" + accessionNumber + "&patientID=" + patientID;
    if( !(typeof requestType === 'undefined') ){
    	searchUrl += "&type=" + requestType;
    }
    getSampleForLabOrderOrPatient(accessionNumber, patientID, selectedPatientSuccess, null, identifier);
}

function selectedPatientSuccess(xhr, identifier) {
	setValuesFromXML(xhr.responseXML, identifier);
}

function setValuesFromXML(xml, identifier) {
	if (xml.getElementsByTagName("formfield").item(0).firstChild.nodeValue === 'empty') {
		alert('could not be found');
	} else {
		var labNo = xml.getElementsByTagName("labNo").item(0).firstChild.nodeValue;
		setLabInfoForWell(labNo, xml, identifier);
	}
}


function setLabInfoForWell(labNo, xml, identifier) {
	jQuery("#well_" + identifier + " > .well-value").text(labNo);
	jQuery("input[name='wellValues[" + identifier + "]']").val(labNo);
	jQuery("#well_" + identifier + " > .well-value-full").val(new XMLSerializer().serializeToString(xml));
	jQuery("#summaryId").show();
	jQuery("#labNoSpan").text(labNo);
	if (labNo != "CPOS" && labNo != "CNEG") {
		jQuery("#receivedSpan").text(xml.getElementsByTagName("receivedDateForDisplay").item(0).firstChild.nodeValue);
	}
	
}

function generateTable() {
	var table = document.getElementById("experimentInputTable");
	generateColGroup(table);
	generateTableHeader(table, wellCols);
	generateTableBody(table, wellRows, wellCols);
}

function generateColGroup(table) {
	var colGroup = document.createElement('colgroup');
	var col = document.createElement('col')
	col.setAttribute("style", "color: #336699; background-color: #C8DADA; width: 1rem;")
	col.setAttribute('span', '1');
	colGroup.appendChild(col)
	table.appendChild(colGroup)
}

function generateTableHeader(table, numColumns) {
	var thead = table.createTHead();
	var row = thead.insertRow();
	row.setAttribute('style', 'text-align: center;')
	row.appendChild(createTH(""));
	for ( curCol = 0; curCol < numColumns; ++curCol ) {
		row.appendChild(createTH(curCol + 1));
	}
}

function generateTableBody(table, numRows, numColumns) {
	for (curRow = 0; curRow < numRows; ++curRow) {
		var row = table.insertRow();
		row.setAttribute('style', 'text-align: center;')
	    var cell = row.insertCell();
	    cell.appendChild(document.createTextNode(numToAlpha(curRow)));
		for (curCol = 0; curCol < numColumns; ++curCol) {
		    var cell = row.insertCell();
		    var identifier = numToAlpha(curRow) + (curCol + 1);
		    cell.setAttribute('id', 'well_' + identifier);
		    cell.setAttribute('identifier', identifier);
		    cell.setAttribute('onClick', 'setWell(this)');
		    cell.setAttribute('class', 'well inactiveWell');
		    cell.setAttribute('style', 'word-wrap:break-word');
		    var div = document.createElement('div');
		    div.setAttribute('class', 'well-label');
		    div.appendChild(document.createTextNode(identifier));
		    cell.appendChild(div);
		    div = document.createElement('div');
		    div.setAttribute('class', 'well-value');
		    cell.appendChild(div);
		    var sentInput = document.createElement('input');
		    sentInput.setAttribute('type', 'hidden');
		    sentInput.setAttribute('name', 'wellValues[' + identifier + ']');
		    sentInput.setAttribute('class', 'well-value-sent');
		    var mainForm = document.getElementById("mainForm");
		    mainForm.appendChild(sentInput);
		    var input = document.createElement('input');
		    input.setAttribute('class', 'well-value-full');
		    input.setAttribute('type', 'hidden');
		    cell.appendChild(input);
		}
	}
}

function createTH(data) {
	th = document.createElement("th");
	th.appendChild(document.createTextNode(data));
	return th;
}

function numToAlpha(num) {
	if (num < 0) {
		return -1;
	} 
	if (num > 25) {
		return numToAlpha(Math.floor(num/26 - 1)) + numToAlpha(num % 26);
	}
	return String.fromCharCode(num + 65);
}

function setupNewExperiment() {
	jQuery("#step1").hide();
	
	setDefaultFilename();
	jQuery("#step2").show();
}

function setDefaultFilename() {
	setFilename(getDateString(new Date()) + " Quantstudio 3 - 96 Wells")
}

function setFilename(filename) {
	jQuery("#filename").val( filename)
}

function getDateString(date) {
	var yyyy = date.getFullYear();
	var mm = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	var dd = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	var hh = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	var MM = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	
	return yyyy 
		+ "-" + mm
		+ "-"  + dd
		+ " " + hh
		+ ":" + MM;
}

function loadExperiment() {
	jQuery("#downloadSetup").attr('disabled',false);
	jQuery("#experimentId").val(jQuery("#previousRun").val());
	setDefaultFilename();
	getPreviousExperimentSetup(jQuery("#previousRun").val(), loadExperimentSuccess);
}

function loadExperimentSuccess(xhr) {
	jQuery("#step1").hide();
	jQuery("#step2").show();
	var responseText = xhr.responseText;
	var jsonResponse = JSON.parse(responseText);
	Object.keys(jsonResponse).forEach(function(key) {
		if (!jsonResponse[key]) {
			
		} else if (jsonResponse[key] != "CNEG" && jsonResponse[key] != "CPOS") {
			searchForLabNo(jsonResponse[key], key);
		} else if (jsonResponse[key] == "CNEG" ) {
			setNegative(key);
		} else if (jsonResponse[key] == "CPOS" ) {
			setPositive(key);
		}
	});
	
}

function searchForLabNo(labNumber, identifier) {
	patientSearch("", "", "", "", "", labNumber, "", "", "", false, searchByLabNoSuccess, null, [identifier, labNumber]);
}

function searchByLabNoSuccess(xhr, extraParams) {
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	handleSelectedPatientAlt(extraParams[0], extraParams[1]);
}

function saveExperiment() {
	setupExperimentFile(saveExperimentSuccess)
}

function saveExperimentSuccess(xhr) {
	var responseText = xhr.responseText;
	console.log("experiment id: " + responseText);
	jQuery("#experimentId").val(responseText);
	jQuery("#downloadSetup").attr('disabled',false);
}

function downloadSetupFile() {
	window.open("AnalyzerSetupFile/" + jQuery("#experimentId").val() + "?filename=" + jQuery("#filename").val(), '_blank');
}

function setPositive(identifier) {
	var xml = new DOMParser().parseFromString("<fieldmessage><formfield>found</formfield><labNo>CPOS</labNo></fieldmessage>", 'application/xml');
	if (identifier) {
		setValuesFromXML(xml, identifier);
	} else {
		setValuesFromXML(xml, getCurActiveIdentifier());
	}
}

function setNegative(identifier) {
	var xml = new DOMParser().parseFromString("<fieldmessage><formfield>found</formfield><labNo>CNEG</labNo></fieldmessage>", 'application/xml');
	if (identifier) {
		setValuesFromXML(xml, identifier);
	} else {
		setValuesFromXML(xml, getCurActiveIdentifier());
	}
}

function getCurActiveIdentifier() {
	return jQuery(".activeWell").attr('identifier');
}

jQuery( document ).ready(function() {
	generateTable();
	jQuery("#downloadSetup").attr('disabled',true);
	jQuery("#selectPatientButtonID").prop("disabled", true);
	jQuery("#summaryId").hide();
	jQuery("#step2").hide();
	setTotalEmpty();
});

</script>
<input id="experimentId" type="hidden" name="experimentId"/>

<h2>
<spring:message code="" text="Analyzer Run Setup"/>
</h2>

<table id="step1" style="width: 80%;">
<tr>
	<td>
		<spring:message code="" text="Analyzer Template Name"/>
	</td>
	<td>
		<form:select path="analyzerId"  >
		<form:options items="${form.analyzers}" itemLabel="label" itenValue="value"/>
		</form:select>
	</td>
	<td>
	</td>
</tr>
<tr>
	<td style="padding-bottom:2em;"></td>
</tr>
<tr style="vertical-align: top;">
	<td>
	</td>
	<td>
		<button type="button" onClick="setupNewExperiment()" ><spring:message code="" text="Setup New Experiment"/></button>
	</td>
	<td>
		<form:select path="previousRun" id="previousRun">
			<form:options items="${form.previousRuns}" itemLabel="label" itemValue="value"/>
		</form:select>
		<br>
		<br>
		<button type="button" onClick="loadExperiment()"><spring:message code="" text="Load Experiment"/></button>
	</td>
</tr>
</table>

<table id="step2">
<tr style="vertical-align: top;">
	<td>
		<table>
		<tr>
			<td>
				<spring:message code="" text="Analyzer Run Label"/>
				<form:input id="filename" path="filename" />
			</td>
		</tr>
		<tr>
			<td>
<%-- 				<spring:message code="" text="Test name"/> --%>
<!-- 				<select > -->
<!-- 				</select> -->
			</td>
		</tr>
		<tr>
			<td>
				<button type="button" onClick="setPositive()"><spring:message code="" text="Positive Control Sample"/></button>
				<button type="button" onClick="setNegative()"><spring:message code="" text="Negative Control Sample"/></button>
			</td>
		</tr>
		</table>
		<tiles:insertAttribute name="patientEnhancedSearch" />
		<div id="summaryId">
			<spring:message code="" text="Lab No."/>:
			<span id="labNoSpan"></span>
			<spring:message code="" text="Received"/>:
			<span id="receivedSpan"></span>
		</div>
	</td>
	<td>
<!-- 		<div style="overflow:scroll; width: 70rem;"> -->
				<table id="experimentInputTable"  style="width: 85rem; text-align: center; table-layout: fixed;"></table>
				<div style="float:right;"><span class="emptyTotalNum"></span> <spring:message code="" text="Empty"/></div>
<!-- 		</div> -->
	</td>
</tr>
<tr>
	<td colspan="2">
		<button type="button" onClick="saveExperiment();"><spring:message code="" text="Save Experiment"/></button>
		<button type="button" id="downloadSetup" onClick="downloadSetupFile();" disabled="disabled"><spring:message code="" text="Download Experiment Setup File"/></button>
	</td>
</tr>
</table>

