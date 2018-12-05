<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
            us.mn.state.health.lims.common.util.Versioning,
            us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl,
            us.mn.state.health.lims.login.dao.UserModuleDAO,
	        java.util.HashSet,
	        org.owasp.encoder.Encode"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="requestType" value='<%=(String)request.getSession().getAttribute("type")%>' />

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

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>neon1/retroCIUtilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>neon/entryByProjectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" language="JavaScript1.2">

var dirty = false;
/* TODO PAHill the code in retroCIUtilities.js uses the var type, while this page uses requestType.  We should have one. */
var requestType = '<%=Encode.forJavaScript(requestType)%>';
var type = requestType;
var pageType = "Patient";
var formName = '<%=formName%>';
birthDateUsageMessage = "<bean:message key='error.dob.complete.less.two.years'/>";
previousNotMatchedMessage = "<bean:message key='error.2ndEntry.previous.not.matched'/>";
noMatchFoundMessage = "<bean:message key='patient.message.patientNotFound'/>";					
saveNotUnderInvestigationMessage = "<bean:message key='patient.project.conflicts.saveNotUnderInvestigation'/>";
var canEditPatientSubjectNos = <%= canEditPatientSubjectNos %>;
var canEditAccessionNo = <%= canEditAccessionNo %>;

function /*void*/ setSaveButton() {
	if (projectChecker != null ) {
		projectChecker.setSubjectOrSiteSubjectEntered();
	}
	var validToSave = fieldValidator.isAllValid() && requestType != "readonly";
	$("saveButtonId").disabled = !validToSave;
}

/**
 * General function for all AJAX search failures.
 * @param xhr the XML returned from the AJAX search
 */
function processSearchFailure(xhr)
{
	//alert( xhr.responseText );
	alert("<bean:message key="error.system"/>");
}

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	// patientLoader.compareDetails($("patientPK"));
	// actually needs to move to the 'third' AJAX thread after laoding for comparison of patient, sample and observation history fields.
	setAction(window.document.forms[0], 'Cancel', 'no', '');
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


<script type="text/javascript">
function Studies() {
	this.validators = new Array();
	this.studyNames = ["InitialARV_Id", "FollowUpARV_Id", "RTN_Id", "_Id", "RTN_Id"];

	this.validators["InitialARV_Id"] = new FieldValidator();
	this.validators["InitialARV_Id"].setRequiredFields( new Array("receivedDateForDisplay", "interviewDate", "centerCode", "subjectOrSiteSubject", "labNo", "gender", "dateOfBirth") );
	this.validators["FollowUpARV_Id"] = new FieldValidator();
	this.validators["FollowUpARV_Id"].setRequiredFields( new Array("subjectOrSiteSubject", "farv.receivedDateForDisplay", "farv.interviewDate", "farv.centerCode", "farv.labNo", "farv.gender", "farv.dateOfBirth") );

	this.validators["EID_Id"] = new FieldValidator();
	this.validators["EID_Id"].setRequiredFields( new Array("eid.receivedDateForDisplay", "eid.interviewDate", "eid.centerCode", "eid.centerName", "subjectOrSiteSubject", "eid.labNo", "eid.dateOfBirth", "eid.gender" ) );
	
	this.validators["VL_Id"] = new FieldValidator();
	this.validators["VL_Id"].setRequiredFields( new Array("vl.receivedDateForDisplay", "vl.interviewDate", "vl.centerCode", "vl.centerName", "subjectOrSiteSubject", "vl.labNo", "vl.dateOfBirth", "vl.gender" ) );
	
	this.validators["RTN_Id"] = new FieldValidator();
	this.validators["RTN_Id"].setRequiredFields( new Array("rtn.labNo", "rtn.receivedDateForDisplay", "rtn.interviewDate", "rtn.gender", "rtn.dateOfBirth", "rtn.nameOfDoctor", "rtn.service", "rtn.hospital") );

	this.getValidator = function /*FieldValidator*/ (divId) {
		return this.validators[divId];
	}

	this.projectChecker = new Array();

	/**
	 * checkers are all about the business logic of what fields have to match an existing record at what time
	 * while validators are enforcing required fields while using lists of field names, so don't have much specifics.
	 */
	this.initializeProjectChecker = function () {
		this.projectChecker["InitialARV_Id"] = iarv;
		this.projectChecker["FollowUpARV_Id"] = farv;
		this.projectChecker["EID_Id"] = eid;	
		this.projectChecker["VL_Id"] = vl;
		this.projectChecker["RTN_Id"] = rtn;
	}

	this.getProjectChecker = function (divId) {
		this.initializeProjectChecker(); // not clear why a navigating back to this page makes field checkers empty, so we'll always load.
		return this.projectChecker[divId];
	}
}

studies = new Studies();
projectChecker = null;

// set the selected value of the study drop down and switch to display that study
function selectStudy( divId ) {
	var i = getSelectIndexFor("studyFormsID", divId);
	document.forms[0].studyForms.selectedIndex = i;
	switchStudyForm( divId );
}

var formFields = null;
// Switch the form value to a study and show the element by that name.
// the ID of the div with the right questions is the same as the projectFormName
function switchStudyForm( divId ) {
	hideAllDivs();
	if (divId != "" && divId != "0") {
		$("projectFormName").value = divId;
    	toggleDisabledDiv(document.getElementById(divId), true);
		document.getElementById(divId).style.display = "block";
		fieldValidator = studies.getValidator(divId); // reset the page var fieldValidator for all fields to use.
		projectChecker   = studies.getProjectChecker(divId);	// reset the page var projectChecker
		projectChecker.setSubjectOrSiteSubjectEntered();	
	    adjustFieldsForRequestType();
		setSaveButton();
	} else {
	    adjustFieldsForRequestType()
		setSaveButton();
	}
}

function adjustFieldsForRequestType() {
	// TODO PAHill -- maybe one guard check at the top for projectChecker?  No need to tweek fields if there are no fields to show right?
	if ( projectChecker != null ) {
		projectChecker.setSubjectOrSiteSubjectEntered();
		var nameField = $(projectChecker.idPre + "centerName");
		var codeField = $(projectChecker.idPre + "centerCode");
		if ( nameField && codeField ) {
			syncLists(codeField, nameField);		
		}	
		
	}
	
  	if (requestType == "readwrite" || requestType == "readonly") {
    	$("studyFormsID").style.display = "none";
    	if ( projectChecker != null ) {
			$(projectChecker.idPre + "patientRecordStatusRow").style.display = "table-row";
			$(projectChecker.idPre + "sampleRecordStatusRow").style.display = "table-row";
		}
		//$("iarv.underInvestigationCommentRow").style.display = "table-row";
    }

	var elements = document.forms[0].elements;
	
	if (projectChecker == null ) {
		return;
	}
	
	switch (requestType) {
	case "initial":
		break;
	case "verify":
		break;		
	case "readwrite":
		projectChecker.setFieldsForEdit( canEditPatientSubjectNos, canEditAccessionNo );    		
		setSaveButton();
		break;
	case "readonly":
		if (projectChecker != null ) {
	    	$("hivStatusRow").style.display = "table-row";
		}
		for (i=0; i< elements.length; i++) {
			setFieldReadOnly(elements[i], true);
		}
	//	setFieldReadOnly( document.forms[0].searchLastName, false);
	//	setFieldReadOnly( document.forms[0].searchFirstName, false);
	//	setFieldReadOnly( document.forms[0].searchNationalID, false);
		setFieldReadOnly( document.forms[0].searchButton, false);
		setFieldReadOnly( document.forms[0].cancelButtonId, false);
	//	setFieldReadOnly( document.forms[0].searchLabNumber, false);
        setFieldReadOnly( document.forms[0].saveButtonId, false );
        setFieldReadOnly( document.forms[0].searchValue, false );
        setFieldReadOnly( document.forms[0].searchCriteria, false );
		document.forms[0].saveButtonId.disabled = true;
		break;
	}
}

function setProjectName(divId) {

}


/**
 * A list of answers that equate to yes in certain lists when comparing (cross check or 2nd entry for a match).
 */
yesesInDiseases = [
     <%= us.mn.state.health.lims.dictionary.ObservationHistoryList.YES_NO.getList().get(0).getId() %>,
	 <%= us.mn.state.health.lims.dictionary.ObservationHistoryList.YES_NO_UNKNOWN.getList().get(0).getId() %>
	 ];

function hideAllDivs(){
	toggleDisabledDiv(document.getElementById("InitialARV_Id"), false);
	toggleDisabledDiv(document.getElementById("FollowUpARV_Id"), false);
	toggleDisabledDiv(document.getElementById("RTN_Id"), false);
	toggleDisabledDiv(document.getElementById("EID_Id"), false);
	toggleDisabledDiv(document.getElementById("VL_Id"), false);

	document.getElementById('InitialARV_Id').style.display = "none";
	document.getElementById('FollowUpARV_Id').style.display = "none";
	document.getElementById('RTN_Id').style.display = "none";
	document.getElementById('EID_Id').style.display = "none";
	document.getElementById('VL_Id').style.display = "none";
}

</script>

<script type="text/javascript" language="JavaScript1.2">

//all methods here either overwrite methods in tiles or all called after they are loaded

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

function savePage__() {
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	if ( requestType == "readwrite" ) {
		form.action = "PatientEditByProjectSave.do?type=" + requestType;
	} else {
		form.action = "PatientEntryByProjectUpdate.do?type=" + requestType;
	}
	form.submit();
}

function initializeStudySelection() {
	selectStudy($('projectFormName').value);
}

</script>
<b><bean:message key="sample.entry.project.form" />
</b>
<tiles:insert attribute="patientSearch" ignore="true"/>
<br/>
<select name="studyForms" onchange="selectStudy(this.value);" id="studyFormsID">
	<option value="0" selected>
	</option>
	<option value="InitialARV_Id">
		<bean:message key="sample.entry.project.initialARV.title" />
	</option>
	<option value="FollowUpARV_Id">
		<bean:message key="sample.entry.project.followupARV.title" />
	</option>
	<option value="RTN_Id">
		<bean:message key="sample.entry.project.RTN.title" />
	</option>
</select>
<br />
<hr>

<html:hidden name="<%=formName%>" property="observations.projectFormName" styleId="projectFormName" />
<html:hidden name="<%=formName%>" property="patientLastUpdated" styleId="patientLastUpdated" />
<html:hidden name="<%=formName%>" property="personLastUpdated"  styleId="personLastUpdated"/>
<html:hidden name="<%=formName%>" property="patientProcessingStatus" styleId="processingStatus" value="add" />
<html:hidden name="<%=formName%>" property="patientPK" 			styleId="patientPK" />
<html:hidden name="<%=formName%>" property="samplePK" 			styleId="samplePK" />
<html:hidden name="<%=formName%>" property="" styleId="subjectOrSiteSubject" value="" />
<div id="studies">
	<div id="InitialARV_Id" style="display: none;">
		<tiles:insert attribute="arvInitialStudy"/>
	</div>
	
	<div id="FollowUpARV_Id" style="display: none;">
		<tiles:insert attribute="arvFollowupStudy"/>
	</div>
	<div id="EID_Id" style="display: none;">
		<tiles:insert attribute="ediStudy"/>
	</div>
	<div id="VL_Id" style="display: none;">
		<tiles:insert attribute="lvStudy"/>
	</div>
	<div id="RTN_Id" style="display: none;">
		<tiles:insert attribute="rtnStudy"/>
	</div>
</div>
<script type="text/javascript" language="JavaScript1.2">
// All openElis struts pages have a function to override to do some work onLoad
function onLoad() {
	// alert("load 1 " + $("projectFormName").value);
	initializeStudySelection();
	studies.initializeProjectChecker();
	registerPatientSearchChanged();
	projectChecker == null || projectChecker.refresh();
}
</script>
