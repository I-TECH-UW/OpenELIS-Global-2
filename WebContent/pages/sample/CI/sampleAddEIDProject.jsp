<%@ page language="java" contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.common.util.SystemConfiguration,
				us.mn.state.health.lims.common.util.ConfigurationProperties,
				us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
	            us.mn.state.health.lims.common.util.Versioning,
		        java.util.HashSet,
		        org.owasp.encoder.Encode,
		        us.mn.state.health.lims.login.dao.UserModuleDAO,
		        us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl"%>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName"		value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="idSeparator"	value='<%=SystemConfiguration.getInstance().getDefaultIdSeparator()%>' />
<bean:define id="accessionFormat" value='<%=ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat)%>' />
<bean:define id="requestType" value='<%=(String)request.getSession().getAttribute("type")%>' />
<bean:define id="genericDomain" value='' />

<%!
	String basePath = "";
	UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
	HashSet accessMap = (HashSet)request.getSession().getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);
	boolean isAdmin = userModuleDAO.isUserAdmin(request);
	// no one should edit patient numbers at this time.  PAH 11/05/2010
	boolean canEditPatientSubjectNos =  isAdmin || accessMap.contains(IActionConstants.MODULE_ACCESS_PATIENT_SUBJECTNOS_EDIT);
	boolean canEditAccessionNo = isAdmin || accessMap.contains(IActionConstants.MODULE_ACCESS_SAMPLE_ACCESSIONNO_EDIT);
%>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/retroCIUtilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/entryByProjectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<script type="text/javascript" language="JavaScript1.2">
var dirty = false;
var type = '<%=Encode.forJavaScript(requestType)%>';
var requestType = '<%=Encode.forJavaScript(requestType)%>';
var pageType = "Sample";
birthDateUsageMessage = "<bean:message key='error.dob.complete.less.two.years'/>";
previousNotMatchedMessage = "<bean:message key='error.2ndEntry.previous.not.matched'/>";
noMatchFoundMessage = "<bean:message key='patient.message.patientNotFound'/>";
saveNotUnderInvestigationMessage = "<bean:message key='patient.project.conflicts.saveNotUnderInvestigation'/>";
testInvalid = "<bean:message key='error.2ndEntry.test.invalid'/>";
var canEditPatientSubjectNos = <%= canEditPatientSubjectNos %>;
var canEditAccessionNo = <%= canEditAccessionNo %>;


function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function Studies() {
	this.validators = new Array();

	this.getValidator = function /*FieldValidator*/ (divId) {
		var validator = new FieldValidator();
	    validator.setRequiredFields( new Array("eid.labNo", "eid.receivedDateForDisplay", "eid.interviewDate", "subjectOrSiteSubject", "eid.centerCode", "eid.gender", "eid.dateOfBirth") );
		return validator;
	}

	this.getProjectChecker = function (divId) {
		return eid;
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
     <%= us.mn.state.health.lims.dictionary.ObservationHistoryList.YES_NO.getList().get(0).getId() %>,
	 <%= us.mn.state.health.lims.dictionary.ObservationHistoryList.YES_NO_UNKNOWN.getList().get(0).getId() %>
	 ];

function /*void*/ makeDirty(){
	dirty=true;
	if( typeof(showSuccessMessage) != 'undefinded' ){
		showSuccessMessage(false); //refers to last save
	}
	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
    return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

/**
 * Set default lab tests for the particular study.
 */
function setDefaultTests( div ) {
	if ( requestType != 'initial' ) {
		return;
	}
	var tests = new Array();
	tests = new Array ("eid.dnaPCR", "eid.dbsTaken");
	
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
	document.forms[0].studyForms.selectedIndex = i;
	switchStudyForm( divId );
}

function switchStudyForm( divId ){
	//hideAllDivs();
	if (divId != "" && divId != "0") {
		$("projectFormName").value = divId;
		switch (divId) {
		case "EID_Id":
			break;
		default:
			//location.replace("SampleEntryByProject.do?type=initial");
			savePage__("SampleEntryByProject.do?type=" + type);
			return;
		}
		//toggleDisabledDiv(document.getElementById(divId), true);
		//document.forms[0].project.value = divId;
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
	toggleDisabledDiv(document.getElementById("VL_Id"), false);
	toggleDisabledDiv(document.getElementById("Indeterminate_Id"), false);
	toggleDisabledDiv(document.getElementById("Special_Request_Id"), false);

	document.getElementById('InitialARV_Id').style.display = "none";
	document.getElementById('FollowUpARV_Id').style.display = "none";
	document.getElementById('RTN_Id').style.display = "none";
	document.getElementById('EID_Id').style.display = "none";
	document.getElementById('VL_Id').style.display = "none";
	document.getElementById('Indeterminate_Id').style.display = "none";
	document.getElementById('Special_Request_Id').style.display = "none";
}

function /*boolean*/ allSamplesHaveTests(){
	// based on studyType, check that at least one test is chosen
	// TODO PAHill this check is done on the server, but could be done here also.
}

function  /*void*/ savePage__(action) {
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	if (action == null) {
		action = "SampleEntryEIDSave.do?type=" + type
	}
	form.action = action;
	form.submit();
}

function /*void*/ setSaveButton() {
	var validToSave = fieldValidator.isAllValid();
	$("saveButtonId").disabled = !validToSave;
}

</script>

<html:hidden name="<%=formName%>" property="currentDate" styleId="currentDate"/>
<html:hidden name="<%=formName%>" property="domain" value="<%=genericDomain%>" styleId="domain"/>
<!--   html:hidden name="<%=formName%>" property="project" styleId="project"/>  -->
<html:hidden name="<%=formName%>" property="patientLastUpdated" styleId="patientLastUpdated" />
<html:hidden name="<%=formName%>" property="personLastUpdated" styleId="personLastUpdated"/>
<html:hidden name="<%=formName%>" property="patientProcessingStatus" styleId="processingStatus" value="add" />
<html:hidden name="<%=formName%>" property="patientPK" styleId="patientPK" />
<html:hidden name="<%=formName%>" property="samplePK" styleId="samplePK" />
<html:hidden name="<%=formName%>" property="observations.projectFormName" styleId="projectFormName"/>
<html:hidden name="<%=formName%>" property=""  styleId="subjectOrSiteSubject" value="" />

<b><bean:message key="sample.entry.project.form"/></b>
<select name="studyForms" onchange="switchStudyForm(this.value);" id="studyFormsId">
	<option value="0" selected> </option>
	<option value="InitialARV_Id" ><bean:message key="sample.entry.project.initialARV.title"/></option>
	<option value="FollowUpARV_Id" ><bean:message key="sample.entry.project.followupARV.title"/></option>
	<option value="RTN_Id" ><bean:message key="sample.entry.project.RTN.title"/></option>
	<option value="EID_Id" ><bean:message key="sample.entry.project.EID.title"/></option>
	<option value="Indeterminate_Id" ><bean:message key="sample.entry.project.indeterminate.title"/></option>
	<option value="Special_Request_Id"><bean:message key="sample.entry.project.specialRequest.title"/></option>
    <option value="VL_Id" ><bean:message key="sample.entry.project.VL.title"/></option>	
</select>
<br/>
<hr/>
<div id="studies">
<div id="EID_Id" style="display:none;">
	<tiles:insert attribute="eidStudy"/>
<table width="100%">
	<tr>
		<td ></td>
		<td colspan="3" class="sectionTitle">
			<bean:message  key="sample.entry.project.title.specimen" />
		</td>
	</tr>
	<tr>
		<td width="2%"></td>
		<td width="38%"><bean:message key="sample.entry.project.ARV.dryTubeTaken" /></td>
		<td width="60%">

			<html:checkbox name="<%=formName%>"
				   property="ProjectData.dryTubeTaken"
				   styleId="eid.dryTubeTaken"
				   onchange="eid.checkSampleItem($('eid.dryTubeTaken'));"/>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><bean:message key="sample.entry.project.title.dryBloodSpot" /></td>
		<td>
			<html:checkbox name="<%=formName%>"
				   property="ProjectData.dbsTaken"
				   styleId="eid.dbsTaken"
				   onchange="eid.checkSampleItem($('eid.dbsTaken'));" />
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="3" class="sectionTitle">
			<bean:message  key="sample.entry.project.title.tests" />
		</td>
	</tr>
	<tr>
		<td></td>
		<td><bean:message key="sample.entry.project.dnaPCR" /></td>
		<td>
			<html:checkbox name="<%=formName%>"
				   property="ProjectData.dnaPCR"
				   styleId="eid.dnaPCR"
				   onchange="eid.checkSampleItem($('eid.dbsTaken'), $('eid.dnaPCR'));" />
		</td>
	</tr>
</table>	
</div>
</div>

<script type="text/javascript" language="JavaScript1.2">

// On load using the built in feature of OpenElis pages onLoad
function pageOnLoad(){
	initializeStudySelection();
	studies.initializeProjectChecker();
	projectChecker == null || projectChecker.refresh();	
}
</script>