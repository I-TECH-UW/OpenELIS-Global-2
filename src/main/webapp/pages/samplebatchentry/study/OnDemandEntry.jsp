<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.StringUtil,
                 us.mn.state.health.lims.sample.bean.SampleOrderItem" %>
<%@ taglib uri="/tags/struts-bean"      prefix="bean" %>
<%@ taglib uri="/tags/struts-html"      prefix="html" %>
<%@ taglib uri="/tags/struts-logic"     prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"      prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>

<bean:define id="formName"      value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<script>
var lineSeparator = "";
var programCode = "<bean:write name="<%=formName%>" property="programCode"/>";

//Adds warning when leaving page
window.onbeforeunload = formWarning;
function formWarning(){ 
	//firefox overwrites any message that is put as a page closing message
	return "Are you sure you want to leave this page?";
}

//functions for generating and checking accession number
function getNextAccessionNumber() {
  generateNextProgramScanNumber(programCode, processStudyScanSuccess, defaultFailure);
}

//parses values once generateNextScanNumber returns from server
function processStudyScanSuccess(xhr) {
  var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
  var returnedData = formField.firstChild.nodeValue;
  var message = xhr.responseXML.getElementsByTagName("message").item(0);
  var success = message.firstChild.nodeValue == "valid";
  if (success) {
      $jq("#labNo").val(returnedData);
  } else {
      alert(returnedData);
      $jq("#labNo").val("");
  }
  selectFieldErrorDisplay(success, document.getElementById("labNo"));
  setValidIndicaterOnField(success, "labNo");
  $jq("#labNo").trigger('change');
}

//check that the accession number is available and well formed
function checkAccessionNumber(accessionNumber) {
    //check if empty
    if (!fieldIsEmptyById("labNo")) {
      	validateAccessionNumberOnServer(accessionNumber, "initialSample", false, processAccessionSuccessStudy);
    } else {
         selectFieldErrorDisplay(false, document.getElementById("labNo"));
    }
}

//called once accession number is validated on server
function processAccessionSuccessStudy(xhr) {
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var message = xhr.responseXML.getElementsByTagName("message").item(0);
    if (message.firstChild.nodeValue == "SAMPLE_NOT_FOUND") {
    	if (study == "viralLoad"){
    		postBatchSampleByProject('SampleEntryVLSave.do?type=initial', onPostBatchSampleSuccess, defaultFailure);
    	} else if (study == "EID"){
    		postBatchSampleByProject('SampleEntryEIDSave.do?type=initial', onPostBatchSampleSuccess, defaultFailure);
    	}
    } else {
        alert(message.firstChild.nodeValue);
    }
    var labElement = formField.firstChild.nodeValue;
    selectFieldErrorDisplay(success, document.getElementById(labElement));
}

//when a successful reponse is retured by the server this does NOT mean the sample was successfully entered
//	as the values are sent to the regular entry page and not a deicated server
function onPostBatchSampleSuccess(xhr) {
	validateAccessionNumberOnServer(document.getElementById('labNo'), "doubleSample", false, checkSampleEnteredSuccess);
}

//check if the sample was successfully entered into the system
function checkSampleEnteredSuccess(xhr) {
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var message = xhr.responseXML.getElementsByTagName("message").item(0);
    if (message.firstChild.nodeValue == "SAMPLE_FOUND") {
    	setPatientThenPrintLabel();
    } else {
        alert("<bean:message key='error.notentered' />");    	
    }
}

//used so new patient is created every time when no patient info is provided
function setPatientThenPrintLabel() {
	var splitName;
    var lastName = "";
    var firstName = "";
    var STNumber = "";
    var subjectNumber = "";
    var nationalID = "";
    var labNumber = "";
	labNumber = $jq("#labNo").val();
    $jq("#searchLabNumber").val(labNumber);
    patientSearch(lastName, firstName, STNumber, subjectNumber, nationalID, labNumber, "", false, processSearchSuccessPrint);
}

//set patient id in hidden field before making request to LabelMakerServlet, 
//so that barcode request contains all needed information
function processSearchSuccessPrint(xhr) {
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	if( message.firstChild.nodeValue == "valid" ) {
		var resultNode = formField.getElementsByTagName("result").item(0);
		var id = resultNode.getElementsByTagName("id").item(0);
		$jq("#lastPatientId").val(id.firstChild.nodeValue);
	}
	if (window.hasIdentifyingInfo) {
		//fill out patient management form if any "identifying info" was provided
		if (hasIdentifyingInfo() && !$jq("#patientPK_ID").val()) {
			processSearchSuccess(xhr);
			searchDone = true;
		} 
	}
	printLabel();
}

//generates the pdf for the label
function printLabel() {    
	inPrintState = false;
	setSave();
	$jq("#nextButtonId").prop('disabled', false);
	var labNo = $jq("#labNo").val();
	var patientId = $jq("#lastPatientId").val();
	$jq("#barcodeArea").show();
	//make request to LabelMakerServlet
	var src = "LabelMakerServlet?labNo=" + labNo + "&patientId=" + patientId + "&programCode=" + programCode;
	$jq("#ifbarcode").attr('src', src);
}

//next button is pressed
function nextLabel() {
	inPrintState = true;
	setSave();
	$jq("#nextButtonId").prop('disabled', true);
	moveAccessionToRecentArea();
	$jq("#labNo").val("");
}

//Add accession number to recent area
function moveAccessionToRecentArea() {
	var $recentTextArea = $jq("#recentSummary");
	if ($recentTextArea.val()) {
		lineSeparator = "\n";
	}
	var newRecent = $jq("#labNo").val() + lineSeparator + $recentTextArea.val();
	if ((newRecent.match(/\n/g)||[]).length >= 3) {
		newRecent = newRecent.slice(0,newRecent.lastIndexOf("\n"));
	}
	$recentTextArea.val(newRecent);
}
</script>

<h2><bean:message key="sample.batchentry.ondemand.header.print"/></h2>
<table style="width:100%;">
<tr>
	<td>
		<!-- gets next accession, and calls submit and print if success -->
		<html:button onclick="getNextAccessionNumber();"
			property="print"
			styleId="saveButtonId">
			<bean:message key="sample.batchentry.ondemand.saveprint" />
		</html:button>
		<!-- sets up for next label to be printed -->
		<html:button onclick="nextLabel();"
			property="next"
			styleId="nextButtonId"
			disabled="true">
			<bean:message key="sample.batchentry.ondemand.next" />
		</html:button>
	</td>
</tr>
<tr>
	<td> <br></td>
</tr>
<tr>
	<td>
		<bean:message key="sample.batchentry.ondemand.current" />:
	</td>
</tr>
<tr>
	<td>
		<app:text name='<%= formName%>' property="labNo"
           	onchange="checkAccessionNumber(this);"
            styleClass="text"
            styleId="labNo"
            readonly="true"/>
	</td>
</tr>
<tr>
	<td>
		<table>
			<tr>
				<td>
					<bean:message key="sample.batchentry.ondemand.previous" />:
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="recentSummary" 
						rows="5" 
						cols="50"
						readonly="readonly"></textarea>
				</td>
			</tr>
		</table>
		<div style="display:none;" id="barcodeArea">
			<h2><bean:message key="barcode.common.section.barcode.header"/></h2>
			<iframe  src="about:blank" id="ifbarcode" width="100%" height="300px"></iframe>
		</div>
	</td>
</tr>
</table>