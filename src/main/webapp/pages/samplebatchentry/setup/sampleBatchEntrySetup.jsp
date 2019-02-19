<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.StringUtil" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

      

<%!
	String path = "";
    String basePath = "";
    boolean acceptExternalOrders = false;

%>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
    acceptExternalOrders = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.ACCEPT_EXTERNAL_ORDERS, "true");
%>

<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/laborder.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<script type="text/javascript" >

var acceptExternalOrders = <%= acceptExternalOrders %>;
var dirty = false;
var invalidSampleElements = [];

 
function isFieldValid(fieldname) {
    return invalidSampleElements.indexOf(fieldname) == -1;
}

function setSampleFieldInvalid(field) {
    if( invalidSampleElements.indexOf(field) == -1 ) {
        invalidSampleElements.push(field);
    }
}

function setSampleFieldValid(field) {
    var removeIndex = invalidSampleElements.indexOf( field );
    if( removeIndex != -1 ) {
        invalidSampleElements.splice( removeIndex,1);
    }
}

function  /*void*/ processValidateEntryDateSuccess(xhr) {
    var message = xhr.responseXML.getElementsByTagName("message").item(0).firstChild.nodeValue;
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0).firstChild.nodeValue;
    var isValid = message == "<%=IActionConstants.VALID%>";
    //utilites.js
    selectFieldErrorDisplay( isValid, $(formField));
    setSampleFieldValidity( isValid, formField );
    checkValidSubPages();
    if( message == '<%=IActionConstants.INVALID_TO_LARGE%>' ) {
        alert( '<spring:message code="error.date.inFuture"/>' );
    } else if( message == '<%=IActionConstants.INVALID_TO_SMALL%>' ) {
        alert( '<spring:message code="error.date.inPast"/>' );
    }
}


function checkValidEntryDate(date, dateRange, blankAllowed) {   
    if((!date.value || date.value == "") && !blankAllowed) {
        checkValidSubPages();
        return;
    } else if ((!date.value || date.value == "") && blankAllowed) {
        setSampleFieldValid(date.id);
        setValidIndicaterOnField(true, date.id);
        return;
    }
    if( !dateRange || dateRange == ""){
        dateRange = 'past';
    }
    //ajax call from utilites.js
    isValidDate( date.value, processValidateEntryDateSuccess, date.id, dateRange );
}


function setSampleFieldValidity( valid, fieldName ){
    if( valid ) {
        setSampleFieldValid(fieldName);
    } else {
        setSampleFieldInvalid(fieldName);
    }
}

function setMyCancelAction(form, action, validate, parameters){
    //first turn off any further validation
    setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');
}

function /*void*/ loadSamples() {
    alert( "Implementation error:  loadSamples not found in addSample tile");
}

function /*void*/ makeDirty(){
    dirty=true;
    if( typeof(showSuccessMessage) != 'undefinded' ){
        showSuccessMessage(false); //refers to last save
    }
    // Adds warning when leaving page if content has been entered into makeDirty form fields
    function formWarning(){ 
    return "<spring:message code="banner.menu.dataLossWarning"/>";
    }
    window.onbeforeunload = formWarning;
}

function /*void*/ nextPage() {
    loadSamples(); //in addSample tile
    window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
    var form = document.getElementById("mainForm");
    //decide page to navigate to based on method of labels
    if (document.getElementById('study').value == "routine") {
		form.action = "SampleBatchEntry.do";
    } else {
    	form.action = "SampleBatchEntryByProject.do";
    }
    form.submit();
}

//check all subpages to see if 'next' should active
function checkValidSubPages() {
	var valid = configBarcodeValid() && sampleAddValid(true) && sampleOrderValid()
		&& invalidSampleElements.length == 0 && $jq(".error").length == 0;
	setNext(valid)
}

function /*void*/ setNext(valid) {
    $("nextButtonId").disabled = !valid;
}

</script>


<!-- Order -->    
<div id=orderEntryPage >
	<h2><%= StringUtil.getContextualMessageForKey("sample.entry.order.label") %></h2>	
	<tiles:insertAttribute name="sampleOrder"/>
</div>

<!-- Sample -->  
<div id=sampleEntryPage >
	<h2><%= StringUtil.getContextualMessageForKey("sample.entry.sampleList.label") %></h2>
	<tiles:insertAttribute name="addSample"/>
</div>

<!-- Barcode Configuration -->
<div id=configureBarcodePage >
	<h2><%= StringUtil.getContextualMessageForKey("sample.batchentry.configureBarcode") %></h2>
	<tiles:insertAttribute name="configureLabels" />
</div>

