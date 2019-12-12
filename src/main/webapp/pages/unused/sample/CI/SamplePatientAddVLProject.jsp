<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="org.openelisglobal.common.action.IActionConstants,
				org.openelisglobal.common.util.SystemConfiguration,
				org.openelisglobal.common.util.ConfigurationProperties,
				org.openelisglobal.common.util.ConfigurationProperties.Property,
		        org.openelisglobal.common.formfields.FormFields,
	            org.openelisglobal.common.util.Versioning,
                org.openelisglobal.login.valueholder.UserSessionData,
		        java.util.HashSet"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

		
<bean:define id="idSeparator"	value='<%=SystemConfiguration.getInstance().getDefaultIdSeparator()%>' />
<bean:define id="accessionFormat" value='<%=ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat)%>' />
<bean:define id="requestType" value='<%=(String)request.getSession().getAttribute("type")%>' />
<bean:define id="genericDomain" value='' />

<%-- sampleAddVLProject --%>
<%
	HashSet accessMap = (HashSet)request.getSession().getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);
	boolean isAdmin = ((UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA)).isAdmin();
	// no one should edit patient numbers at this time.  PAH 11/05/2010
	boolean canEditPatientSubjectNos =  isAdmin || accessMap.contains(IActionConstants.MODULE_ACCESS_PATIENT_SUBJECTNOS_EDIT);
	boolean canEditAccessionNo = isAdmin || accessMap.contains(IActionConstants.MODULE_ACCESS_SAMPLE_ACCESSIONNO_EDIT);
%>

<script type="text/javascript" src="scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="scripts/retroCIUtilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="scripts/entryByProjectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<script type="text/javascript">
var dirty = false;
var type = '<%=requestType%>';
var requestType = '<%=requestType%>';
var pageType = "Sample";
birthDateUsageMessage = "<spring:message code='error.dob.complete.less.two.years'/>";
previousNotMatchedMessage = "<spring:message code='error.2ndEntry.previous.not.matched'/>";
noMatchFoundMessage = "<spring:message code='patient.message.patientNotFound'/>";
saveNotUnderInvestigationMessage = "<spring:message code='patient.project.conflicts.saveNotUnderInvestigation'/>";
testInvalid = "<spring:message code='error.2ndEntry.test.invalid'/>";
var canEditPatientSubjectNos = <%= canEditPatientSubjectNos %>;
var canEditAccessionNo = <%= canEditAccessionNo %>;


function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');
}

function Studies() {
	this.validators = new Array();

	this.getValidator = function /*FieldValidator*/ (divId) {
		var validator = new FieldValidator();
	    validator.setRequiredFields( new Array("vl.labNo", "vl.receivedDateForDisplay", "vl.interviewDate", "subjectOrSiteSubject", "vl.centerCode", "vl.gender", "vl.dateOfBirth") );
		return validator;
	}

	this.getProjectChecker = function (divId) {
		return vl;
	}

	this.initializeProjectChecker = function () {
	}
}


studies = new Studies();
projectChecker = null;


/**
 * A list of answers that equate to yes in certain lists when comparing (cross check or 2nd entry for a match).
 */
yesesInDiseases = [
     <%= org.openelisglobal.dictionary.ObservationHistoryList.YES_NO.getList().get(0).getId() %>,
	 <%= org.openelisglobal.dictionary.ObservationHistoryList.YES_NO_UNKNOWN.getList().get(0).getId() %>
	 ];

function /*void*/ makeDirty(){
	dirty=true;
	if( typeof(showSuccessMessage) === 'function' ){
		showSuccessMessage(false); //refers to last save
	}
	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
    return "<spring:message code="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

/**
 * Set default lab tests for the particular study.
 */
function setDefaultTests() {
	
	var tests = new Array();
	tests = new Array ("vl.viralLoadTest", "vl.edtaTubeTaken");
	
	for( var i = 0; i < tests.length; i++ ){
		var testId = tests[i];
		$(testId).value = true;
		$(testId).checked = true;
	}
}

function initializeStudySelection() {
	selectStudy($('projectFormName').value);
}

function selectStudy( divId ) {
	var i = getSelectIndexFor("studyFormsId", divId);
	document.getElementById("mainForm").studyForms.selectedIndex = i;
	switchStudyForm( divId );
}

function switchStudyForm( divId ){
	//hideAllDivs();
	if (divId != "" && divId != "0") {
		$("projectFormName").value = divId;
		switch (divId) {
		case "VL_Id":
			break;
		default:
			//location.replace("SampleEntryByProject.do?type=initial");
			savePage__("SampleEntryByProject.do?type=" + type);
			return;
		}
		//toggleDisabledDiv(document.getElementById(divId), true);
		//document.getElementById("mainForm").project.value = divId;
		document.getElementById(divId).style.display = "block";
		fieldValidator = studies.getValidator(divId); // reset the page fieldValidator for all fields to use.
		projectChecker = studies.getProjectChecker(divId);
		projectChecker.setSubjectOrSiteSubjectEntered();				
		adjustFieldsForRequestType();
		setDefaultTests(divId);
		setSaveButton();
	}
}
function adjustFieldsForRequestType()  {
	switch (requestType) {
	case "initial":
		break;
	case "verify":
		break;
	}
}

function hideAllDivs(){
	toggleDisabledDiv(document.getElementById("InitialARV_Id"), false);
	toggleDisabledDiv(document.getElementById("FollowUpARV_Id"), false);
	toggleDisabledDiv(document.getElementById("RTN_Id"), false);
	toggleDisabledDiv(document.getElementById("EID_Id"), false);
	toggleDisabledDiv(document.getElementById("Indeterminate_Id"), false);
	toggleDisabledDiv(document.getElementById("Special_Request_Id"), false);
	toggleDisabledDiv(document.getElementById("VL_Id"), false);

	document.getElementById('InitialARV_Id').style.display = "none";
	document.getElementById('FollowUpARV_Id').style.display = "none";
	document.getElementById('RTN_Id').style.display = "none";
	document.getElementById('EID_Id').style.display = "none";
	document.getElementById('Indeterminate_Id').style.display = "none";
	document.getElementById('Special_Request_Id').style.display = "none";
	document.getElementById('VL_Id').style.display = "none";
}

function /*boolean*/ allSamplesHaveTests(){
	// based on studyType, check that at least one test is chosen
	// TODO PAHill this check is done on the server, but could be done here also.
}

function  /*void*/ savePage__(action) {
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = document.getElementById("mainForm");
	if (action == null) {
		action = "SampleEntryVLSave.do?type=" + type
	}
	form.action = action;
	form.submit();
}

function /*void*/ setSaveButton() {
	var validToSave = fieldValidator.isAllValid();
	$("saveButtonId").disabled = !validToSave;
}

function clearAllFormFields(formName) {
	var elements = document.forms[formName].elements;
	for (i=0; i< elements.length; i++) {
		clearFormElement(elements[i]);
	}
}

/**
 * This function is similar to utilities.js clearField( fieldId ), but covers other types of fields, is not clear that this is actually better.
 * @author PAHill
 */
function clearFormElement(field) {
    if (field == null) return;
	var type = field.type.toLowerCase();
	switch(type) {
	case "text":
	case "password":
	case "textarea":
	case "hidden":
		field.value = "";
		break;
	case "radio":
	case "checkbox":
		if (field.checked) {
			field.checked = false;
		}
		break;
	case "select-one":
	case "select-multi":
		field.selectedIndex = -1;
		break;
	default:
		break;
	}
}

function clearFormElements(fieldIds) {
	var fields = fieldIds.split(',');
	for (var i=0; i< fields.length; i++) {
		clearFormElement($(fields[i].trim()));
	}
} 
</script>

<form:hidden path="currentDate" id="currentDate"/>
<form:hidden path="domain" value="<%=genericDomain%>" id="domain"/>
<%--   html:hidden name="${form.formName}" property="project" id="project"/>  --%>
<form:hidden path="patientLastUpdated" id="patientLastUpdated" />
<form:hidden path="personLastUpdated" id="personLastUpdated"/>
<form:hidden path="patientProcessingStatus" id="processingStatus" value="add" />
<form:hidden path="patientPK" id="patientPK" />
<form:hidden path="samplePK" id="samplePK" />
<form:hidden path="observations.projectFormName" id="projectFormName"/>
<form:hidden path=""  id="subjectOrSiteSubject" value="" />

<b><spring:message code="sample.entry.project.form"/></b>
<select style="display:none;" name="studyForms" onchange="switchStudyForm(this.value);" id="studyFormsId">
	<option value="0" selected> </option>
	<option value="InitialARV_Id" ><spring:message code="sample.entry.project.initialARV.title"/></option>
	<option value="FollowUpARV_Id" ><spring:message code="sample.entry.project.followupARV.title"/></option>
	<option value="RTN_Id" ><spring:message code="sample.entry.project.RTN.title"/></option>
	<option value="EID_Id" ><spring:message code="sample.entry.project.EID.title"/></option>
	<option value="Indeterminate_Id" ><spring:message code="sample.entry.project.indeterminate.title"/></option>
	<option value="Special_Request_Id"><spring:message code="sample.entry.project.specialRequest.title"/></option>
	<option value="VL_Id" ><spring:message code="sample.entry.project.VL.title"/></option>
</select>
<br/>
<hr/>
<div id="studies">
<div id="VL_Id">
	<tiles:insert attribute="vlStudy"/>
<table width="100%">
	<tr>
		<td ></td>
		<td colspan="3" class="sectionTitle">
			<spring:message code="sample.entry.project.title.specimen" />
		</td>
	</tr>
	<tr>
		<td width="2%"></td>
		<td width="38%"><spring:message code="sample.entry.project.ARV.dryTubeTaken" /></td>
		<td width="60%">

			<html:checkbox name="${form.formName}" id="ARVDryTube"
				   property="ProjectData.dryTubeTaken"
				   id="vl.dryTubeTaken"
				   onchange="vl.checkSampleItem($('vl.dryTubeTaken'));"/>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><spring:message code="sample.entry.project.ARV.edtaTubeTaken" /></td>
		<td>
			<html:checkbox name="${form.formName}"
				   property="ProjectData.edtaTubeTaken"
				   id="vl.edtaTubeTaken"
				   onchange="vl.checkSampleItem($('vl.edtaTubeTaken'));" />
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="3" class="sectionTitle">
			<spring:message code="sample.entry.project.title.tests" />
		</td>
	</tr>
	<tr>
		<td></td>
		<td><spring:message code="sample.entry.project.ARV.viralLoadTest" /></td>
		<td>
			<html:checkbox name="${form.formName}"
				   property="ProjectData.viralLoadTest"
				   id="vl.viralLoadTest"
				   onchange="vl.checkSampleItem($('vl.edtaTubeTaken'), this);" />
		</td>
	</tr>
</table>	
</div>
</div>

<script type="text/javascript">

// On load using the built in feature of OpenElis pages onLoad
function pageOnLoad(){
	initializeStudySelection();
	studies.initializeProjectChecker();
	projectChecker == null || projectChecker.refresh();	
	switchStudyForm("VL_IdR");
}
</script>