<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
                 us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator" %>
                 
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

function saveLabel() {
	postBatchSample(onPostBatchSampleSuccess, defaultFailure);
}

function onPostBatchSampleSuccess() {
	setPatient();
	moveAccessionToRecentArea();
	$jq("#labNo").val("");
    $jq("#labNo").trigger('keyup');
	setSave();
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

//check if labNo is valid accession number when a character key is pressed and length is accession length
function checkAccessionNumber(accessionNumber, event) {
	var charPressed = (event.keyCode >=48 && event.keyCode <=57) || (event.keyCode >=65 && event.keyCode <=90) || (event.keyCode >=96 && event.keyCode <=105)
	if (accessionNumber.value.length >= accessionLength && charPressed) {
      	validateAccessionNumberOnServer(false, false, accessionNumber.id, accessionNumber.value, processAccessionSuccess, null);
	} else if (accessionNumber.value.length < accessionLength) {
		inPrintState = false;
		setSave();
	}
}

//check response from accession number validation
function processAccessionSuccess(xhr) {
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var message = xhr.responseXML.getElementsByTagName("message").item(0);
    if (message.firstChild.nodeValue == "valid") {
        inPrintState = true;
        setSave()
    } else {
        alert(message.firstChild.nodeValue);
    }
    var labElement = formField.firstChild.nodeValue;
    selectFieldErrorDisplay(success, document.getElementById(labElement));
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
		<app:text name='<%=formName%>' property="sampleOrderItems.labNo"
			maxlength='<%= Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>'
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