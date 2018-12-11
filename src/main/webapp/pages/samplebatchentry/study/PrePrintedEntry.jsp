<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
                 us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
                 us.mn.state.health.lims.common.util.StringUtil" %>
<%@ taglib uri="/tags/struts-bean"      prefix="bean" %>
<%@ taglib uri="/tags/struts-html"      prefix="html" %>
<%@ taglib uri="/tags/struts-logic"     prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" 		prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>

<bean:define id="formName"      value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

<%!
String path = "";
String basePath = "";
IAccessionNumberValidator accessionNumberValidator;
%>

<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
%>

<script type="text/javascript">
var lineSeparator = "";
var accessionLength = <%= accessionNumberValidator.getMaxAccessionLength()%>;
inPrintState = false;

//Adds warning when leaving page
window.onbeforeunload = formWarning;
function formWarning(){ 
	//firefox overwrites any message that is put as a page closing message
	return "Are you sure you want to leave this page?";
}

//check if labNo is valid accession number when a character key is pressed and length is accession length
function checkAccessionNumber(accessionNumber, event) {
	var charPressed = (event.keyCode >=48 && event.keyCode <=57) || (event.keyCode >=65 && event.keyCode <=90) || (event.keyCode >=96 && event.keyCode <=105)
	if (accessionNumber.value.length >= 9 && charPressed) {
      	validateAccessionNumberOnServer(accessionNumber, "initialSample", false, processAccessionSuccessStudy);
	} else if (accessionNumber.value.length < 9) {
		inPrintState = false;
		setSave();
	}
}

//check response from accession number validation
function processAccessionSuccessStudy(xhr) {
  var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
  var message = xhr.responseXML.getElementsByTagName("message").item(0);
  if (message.firstChild.nodeValue == "valid" || message.firstChild.nodeValue == "SAMPLE_NOT_FOUND" ) {
      inPrintState = true;
      setSave()
  } else {
      alert(message.firstChild.nodeValue);
  }
  var labElement = formField.firstChild.nodeValue;
  selectFieldErrorDisplay(success, document.getElementById(labElement));
}

//what happens when save button is pressed
function saveLabel() {
	if (study == "viralLoad"){
		postBatchSampleByProject('SampleEntryVLSave.do?type=initial', onPostBatchSampleSuccess, defaultFailure);
	} else if (study == "EID"){
		postBatchSampleByProject('SampleEntryEIDSave.do?type=initial', onPostBatchSampleSuccess, defaultFailure);
	}
}

//when a successful reponse is retured by the server this does NOT mean the sample was successfully entered
//	as the values are sent to the regular entry page and not a deicated server
function onPostBatchSampleSuccess(xhr) {
	validateAccessionNumberOnServer(document.getElementById('labNo'), "doubleSample", false, checkSampleEnteredSuccess);
}

//check to see if the sample was successfully entered
function checkSampleEnteredSuccess(xhr) {
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var message = xhr.responseXML.getElementsByTagName("message").item(0);
    if (message.firstChild.nodeValue == "SAMPLE_FOUND") {
    	moveAccessionToRecentArea();
    	$jq("#labNo").val("");
        $jq("#labNo").trigger('keyup');
    	setSave();
    } else {
        alert("<bean:message key='error.notentered' />");    	
    }
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
<h2><bean:message key="sample.batchentry.preprinted.header.entry" /></h2>

<table>
<tr>
	<td>
		<bean:message key="sample.batchentry.preprinted.labno" />:
	</td>
</tr>
<tr>
	<td>		
		<app:text name='<%=formName%>' property="labNo"
			maxlength='9'
            onkeyup="checkAccessionNumber(this, event);"
            styleClass="text"
            styleId="labNo"/>
		<html:button onclick="saveLabel();"
			property="save"
			styleId="saveButtonId" 
			disabled="disabled">
		<bean:message key="sample.batchentry.preprinted.save" />
		</html:button>
	</td>
</tr>
<tr>
	<td> <br></td>
</tr>
<tr>
	<td>
		<table>
			<tr>
				<td>
					<bean:message key="sample.batchentry.preprinted.summary" />:
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
	</td>
</tr>
</table>