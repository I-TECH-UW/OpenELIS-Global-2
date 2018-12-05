<%@ page language="java" contentType="text/html; charset=utf-8"
		import="us.mn.state.health.lims.common.formfields.FormFields,
				us.mn.state.health.lims.common.formfields.FormFields.Field,
				us.mn.state.health.lims.common.action.IActionConstants,
	            us.mn.state.health.lims.common.util.SystemConfiguration,
	            us.mn.state.health.lims.common.util.ConfigurationProperties,
	            us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
	            us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
	            us.mn.state.health.lims.common.util.Versioning,
	            us.mn.state.health.lims.common.util.StringUtil,
	            us.mn.state.health.lims.sample.bean.SampleOrderItem,
	            us.mn.state.health.lims.sample.util.AccessionNumberUtil,
	            org.owasp.encoder.Encode,
	        	java.util.HashSet,
	        	us.mn.state.health.lims.login.dao.UserModuleDAO,
	        	us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl"%>


<%@ taglib uri="/tags/struts-bean"      prefix="bean" %>
<%@ taglib uri="/tags/struts-html"      prefix="html" %>
<%@ taglib uri="/tags/struts-logic"     prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"      prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<%!
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

<bean:define id="accessionFormat" value='<%=ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat)%>' />
<bean:define id="requestType" value='<%=(String)request.getSession().getAttribute("type")%>' />
<bean:define id="genericDomain" value='' />

<bean:define id="formName"      value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="idSeparator"   value='<%=SystemConfiguration.getInstance().getDefaultIdSeparator()%>' />
<bean:define id="accessionFormat" value='<%= ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat)%>' />
<bean:define id="genericDomain" value='' />
<bean:define id="sampleOrderItems" name='<%=formName%>' property='sampleOrderItems' type="SampleOrderItem" />
<bean:define id="entryDate" name="<%=formName%>" property="currentDate" />


<%!
    String basePath = "";
    boolean useSTNumber = true;
    boolean useMothersName = true;

    boolean useProviderInfo = false;
    boolean patientRequired = false;
    boolean trackPayment = false;
    boolean requesterLastNameRequired = false;
    boolean acceptExternalOrders = false;
    IAccessionNumberValidator accessionNumberValidator;

%>
<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
    useSTNumber =  FormFields.getInstance().useField(FormFields.Field.StNumber);
    useMothersName = FormFields.getInstance().useField(FormFields.Field.MothersName);
    useProviderInfo = FormFields.getInstance().useField(FormFields.Field.ProviderInfo);
    patientRequired = FormFields.getInstance().useField(FormFields.Field.PatientRequired);
    trackPayment = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.TRACK_PATIENT_PAYMENT, "true");
    accessionNumberValidator = AccessionNumberUtil.getAccessionNumberValidator();
    requesterLastNameRequired = FormFields.getInstance().useField(Field.SampleEntryRequesterLastNameRequired);
    acceptExternalOrders = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.ACCEPT_EXTERNAL_ORDERS, "true");
%>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/retroCIUtilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/entryByProjectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

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
	document.forms[0].studyForms.selectedIndex = i;
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
	var form = window.document.forms[0];
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

<script type="text/javascript" >

var useSTNumber = <%= useSTNumber %>;
var useMothersName = <%= useMothersName %>;
var requesterLastNameRequired = <%= requesterLastNameRequired %>;
var acceptExternalOrders = <%= acceptExternalOrders %>;
var dirty = false;
var invalidSampleElements = [];

function setSampleFieldValidity( valid, fieldName ){

    if( valid )
    {
        setSampleFieldValid(fieldName);
    }
    else
    {
        setSampleFieldInvalid(fieldName);
    }
}


function checkValidTime(time, blankAllowed)
{
    var lowRangeRegEx = new RegExp("^[0-1]{0,1}\\d:[0-5]\\d$");
    var highRangeRegEx = new RegExp("^2[0-3]:[0-5]\\d$");

    if (time.value.blank() && blankAllowed == true) {
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
        setSave();
        return;        
    }

    if( lowRangeRegEx.test(time.value) ||
        highRangeRegEx.test(time.value) )
    {
        if( time.value.length == 4 )
        {
            time.value = "0" + time.value;
        }
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
    }
    else
    {
        setFieldErrorDisplay(time);
        setSampleFieldInvalid(time.name);
    }

    setSave();
}

function setMyCancelAction(form, action, validate, parameters)
{
    //first turn off any further validation
    setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function showHideSection(button, targetId){
    if( button.value == "+" ){
        showSection(button, targetId);
    }else{
        hideSection(button, targetId);
    }
}

function showSection( button, targetId){
    $jq("#" + targetId ).show();
    button.value = "-";
}

function hideSection( button, targetId){
    $jq("#" + targetId ).hide();
    button.value = "+";
}

function /*bool*/ requiredSampleEntryFieldsValid(){

    if( acceptExternalOrders){ 
        if (missingRequiredValues())
            return false;
    }
        
    for( var i = 0; i < requiredFields.length; ++i ){
        if( $(requiredFields[i]).value.blank() ){
            //special casing
            if( requiredFields[i] == "requesterId" && 
               !( ($("requesterId").selectedIndex == 0)  &&  $("newRequesterName").value.blank())){
                continue;
            }
        return false;
        }
    }
    
    return sampleAddValid( true );
}

function /*bool*/ sampleEntryTopValid(){
    return invalidSampleElements.length == 0 && requiredSampleEntryFieldsValid() && $jq(".error").length == 0;
}

function /*void*/ loadSamples(){
    alert( "Implementation error:  loadSamples not found in addSample tile");
}

function show(id){
    document.getElementById(id).style.visibility="visible";
}

function hide(id){
    document.getElementById(id).style.visibility="hidden";
}


function capitalizeValue( text){
    $("requesterId").value = text.toUpperCase();
}

function checkOrderReferral( value ){

    getLabOrder(value, processLabOrderSuccess);
    showSection( $("orderSectionId"), 'orderDisplay');
}

function clearOrderData() {

    removeAllRows();
    clearTable(addTestTable);
    clearTable(addPanelTable);
    clearSearchResultTable();
    addPatient();
    clearPatientInfo();
    clearRequester();
    removeCrossPanelsTestsTable();

}


function clearRequester() {

    $("providerFirstNameID").value = '';
    $("providerLastNameID").value = '';
    $("labNo").value = '';
    $("receivedDateForDisplay").value = '<%=entryDate%>';
    $("receivedTime").value = '';
    $("referringPatientNumber").value = '';

}

function parseRequester(requester) {
    var firstName = requester.item(0).getElementsByTagName("firstName");
    var first = "";
    if (firstName.length > 0) {
            first = firstName[0].firstChild.nodeValue;
            $("providerFirstNameID").value = first;
    }
    var lastName = requester.item(0).getElementsByTagName("lastName");
    var last = "";
    if (lastName.length > 0) {
            last = lastName[0].firstChild.nodeValue;
            $("providerLastNameID").value = last;    
    }
    
    var phoneNum = requester.item(0).getElementsByTagName("providerWorkPhoneID");
    var phone = "";
    if (phoneNum.length > 0) {
        if (phoneNum[0].firstChild) {
            phone = phoneNum[0].firstChild.nodeValue;
            $("providerWorkPhoneID").value = phone;
        }
    }
    
    
    
}
function parseSampletypes(sampletypes, SampleTypes) {
        
        var index = 0;
        for( var i = 0; i < sampletypes.length; i++ ) {

            var sampleTypeName = sampletypes.item(i).getElementsByTagName("name")[0].firstChild.nodeValue;
            var sampleTypeId   = sampletypes.item(i).getElementsByTagName("id")[0].firstChild.nodeValue;
            var panels         = sampletypes.item(i).getElementsByTagName("panels")[0];
            var tests          = sampletypes.item(i).getElementsByTagName("tests")[0];
            var sampleTypeInList = getSampleTypeMapEntry(sampleTypeId);
            if (!sampleTypeInList) {
                index++;
                SampleTypes[index-1] = new SampleType(sampleTypeId, sampleTypeName);
                sampleTypeMap[sampleTypeId] = SampleTypes[index-1];
                SampleTypes[index-1].rowid = index;
                sampleTypeInList = SampleTypes[index-1];
                                
                //var addTable = $("samplesAddedTable");
                //var sampleDescription = sampleTypeName;
                //var sampleTypeValue = sampleTypeId;
                //var currentTime = getCurrentTime();
                
                //addTypeToTable(addTable, sampleDescription, sampleTypeValue, currentTime,  '<%=entryDate%>' );
            
            }
            var panelnodes = getNodeNamesByTagName(panels, "panel");
            var testnodes  = getNodeNamesByTagName(tests, "test");
            
            addPanelsToSampleType(sampleTypeInList, panelnodes);
            addTestsToSampleType(sampleTypeInList, testnodes);
           
        }

}

function addPanelsToSampleType(sampleType, panelNodes) {
    for (var i=0; i<panelNodes.length; i++) {
       sampleType.panels[sampleType.panels.length] = panelNodes[i];
    }
}

function addTestsToSampleType(sampleType, testNodes) {
    for (var i=0; i<testNodes.length; i++) {
       sampleType.tests[sampleType.tests.length] = new Test(testNodes[i].id, testNodes[i].name);
    }
}


function parseCrossPanels(crosspanels, crossSampleTypeMap, crossSampleTypeOrderMap) {
        for(i = 0; i < crosspanels.length; i++ ) {
            var crossPanelName = crosspanels.item(i).getElementsByTagName("name")[0].firstChild.nodeValue;
            var crossPanelId   = crosspanels.item(i).getElementsByTagName("id")[0].firstChild.nodeValue;
            var crossSampleTypes         = crosspanels.item(i).getElementsByTagName("crosssampletypes")[0];
            
            CrossPanels[i] = new CrossPanel(crossPanelId, crossPanelName);
            CrossPanels[i].sampleTypes = getNodeNamesByTagName(crossSampleTypes, "crosssampletype");
            CrossPanels[i].typeMap = new Array(CrossPanels[i].sampleTypes.length);
            
            for (j = 0; j < CrossPanels[i].sampleTypes.length; j = j + 1) {
                CrossPanels[i].typeMap[CrossPanels[i].sampleTypes[j].name] = "t";
                var sampleType = getCrossSampleTypeMapEntry(CrossPanels[i].sampleTypes[j].id);
                
                if (sampleType === undefined) {
                    crossSampleTypeMap[CrossPanels[i].sampleTypes[j].id] = CrossPanels[i].sampleTypes[j];
                    sampleTypeOrder = sampleTypeOrder + 1;
                    crossSampleTypeOrderMap[sampleTypeOrder] = CrossPanels[i].sampleTypes[j].id;
                }
            }
        }
} 

function parseCrossTests(crosstests, crossSampleTypeMap, crossSampleTypeOrderMap) {
    for (x = 0; x < crosstests.length; x = x + 1) {
        var crossTestName = crosstests.item(x).getElementsByTagName("name")[0].firstChild.nodeValue;
   //     var crossTestId   = crosstests.item(x).getElementsByTagName("id")[0].firstChild.nodeValue;
        var crossSampleTypes  = crosstests.item(x).getElementsByTagName("crosssampletypes")[0];
        
        CrossTests[x] = new CrossTest(crossTestName);
        CrossTests[x].sampleTypes = getNodeNamesByTagName(crossSampleTypes, "crosssampletype");
        CrossTests[x].typeMap = new Array(CrossTests[x].sampleTypes.length);    
        var sTypes = [];
        for (var y = 0; y < CrossTests[x].sampleTypes.length; y++) {
        
            //alert(crossTestName + " " + CrossTests[x].sampleTypes[y].id + " testid=" + CrossTests[x].sampleTypes[y].testId);
            sTypes[y] = CrossTests[x].sampleTypes[y];
            CrossTests[x].typeMap[CrossTests[x].sampleTypes[y].name] = "t";
            var sType = getCrossSampleTypeMapEntry(CrossTests[x].sampleTypes[y].id);
            
            if (sType === undefined) {
                crossSampleTypeMap[CrossTests[x].sampleTypes[y].id] = CrossTests[x].sampleTypes[y];
                sampleTypeOrder++;
                crossSampleTypeOrderMap[sampleTypeOrder] = CrossTests[x].sampleTypes[y].id;               
            }
        }
        crossTestSampleTypeTestIdMap[crossTestName] = sTypes;
    }

}




</script>


</div>
<script type="text/javascript" >

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

function  /*void*/ savePage()
{
    loadSamples(); //in addSample tile

  window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
    var form = window.document.forms[0];
    form.action = "SamplePatientEntrySave.do";
    form.submit();
}


function /*void*/ setSave()
{
    var validToSave =  patientFormValid() && sampleEntryTopValid();
    $("saveButtonId").disabled = !validToSave;
}

var patientRegistered = false;
var sampleRegistered = false;

/* is registered in patientManagement.jsp */
function /*void*/ registerPatientChangedForSampleEntry(){
    if( !patientRegistered ){
        addPatientInfoChangedListener( patientInfoChangedForSample );
        patientRegistered = true;
    }
}

/* is registered in sampleAdd.jsp */
function /*void*/ registerSampleChangedForSampleEntry(){
    if( !sampleRegistered ){
        addSampleChangedListener( makeDirty );
        sampleRegistered = true;
    }
}

registerPatientChangedForSampleEntry();
registerSampleChangedForSampleEntry();


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

<div id=sampleEntryPage >
<input type="button" name="showHide" value='<%= acceptExternalOrders ? "+" : "-" %>' onclick="showHideSection(this, 'orderDisplay');" id="orderSectionId">
<%= StringUtil.getContextualMessageForKey("sample.entry.order.label") %>
<span class="requiredlabel">*</span>

<tiles:insert attribute="sampleOrder" />

<hr style="width:100%;height:5px" />
<input type="button" name="showHide" value="+" onclick="showHideSection(this, 'samplesDisplay');" id="samplesSectionId">
<%= StringUtil.getContextualMessageForKey("sample.entry.sampleList.label") %>
<span class="requiredlabel">*</span>

<div id="samplesDisplay" class="colorFill">
    <tiles:insert attribute="addSample"/>
</div>

<script type="text/javascript" language="JavaScript1.2">

// On load using the built in feature of OpenElis pages onLoad
function pageOnLoad(){
	initializeStudySelection();
	studies.initializeProjectChecker();
	projectChecker == null || projectChecker.refresh();	
}
</script>