<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="java.util.List,
			org.openelisglobal.common.action.IActionConstants,
			java.util.ArrayList,
			java.text.DecimalFormat,
			org.apache.commons.validator.GenericValidator,
			org.openelisglobal.inventory.form.InventoryKitItem,
			org.openelisglobal.test.beanItems.TestResultItem,
			org.openelisglobal.common.util.IdValuePair,
			org.openelisglobal.common.formfields.FormFields,
			org.openelisglobal.common.formfields.FormFields.Field,
			org.openelisglobal.sample.util.AccessionNumberUtil,
			org.openelisglobal.common.util.ConfigurationProperties,
			org.openelisglobal.common.util.ConfigurationProperties.Property,
			org.openelisglobal.internationalization.MessageUtil,
		    org.openelisglobal.common.util.Versioning,
		    org.openelisglobal.common.exception.LIMSInvalidConfigurationException,
		    org.openelisglobal.common.util.DateUtil,
		    org.owasp.encoder.Encode" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="tests" value="${form.testResult}"/>
<c:set var="testCount" value="${fn:length(tests)}" />
<c:set var="inventory" value="${form.inventoryItems}"/>
<c:set var="hivKits" value="${form.hivKits}"/>
<c:set var="syphilisKits" value="${form.syphilisKits}"/>

<c:set var="pagingSearch" value="${form.paging.searchTermToPage}"  />

<c:set var="type" value="${form.type}" />
<c:if test="${form.displayTestSections}">
	<c:set var="testSectionsByName" value="${form.testSectionsByName}" />
	<script type="text/javascript" >
		var testSectionNameIdHash = [];		
		<c:forEach items="${testSectionsByName}" var="testSection">
			testSectionNameIdHash["${testSection.id}"] = "${testSection.value}";
		</c:forEach>
	</script>
</c:if>
	
<%
	String searchTerm = request.getParameter("searchTerm");

	boolean useSTNumber = FormFields.getInstance().useField(Field.StNumber);
	boolean useNationalID = FormFields.getInstance().useField(Field.NationalID);
	boolean useSubjectNumber = FormFields.getInstance().useField(Field.SubjectNumber);
	boolean useTechnicianName =  ConfigurationProperties.getInstance().isPropertyValueEqual(Property.resultTechnicianName, "true");
	boolean useRejected =  ConfigurationProperties.getInstance().isPropertyValueEqual(Property.allowResultRejection, "true");

	boolean depersonalize = FormFields.getInstance().useField(Field.DepersonalizedResults);
	boolean ableToRefer = FormFields.getInstance().useField(Field.ResultsReferral);
	boolean compactHozSpace = FormFields.getInstance().useField(Field.ValueHozSpaceOnResults);
	boolean useInitialCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);
	boolean failedValidationMarks = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.failedValidationMarker, "true");
	boolean noteRequired =  ConfigurationProperties.getInstance().isPropertyValueEqual(Property.notesRequiredForModifyResults, "true");
	pageContext.setAttribute("noteRequired", noteRequired);
	boolean autofillTechBox = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.autoFillTechNameBox, "true");
%>

<link rel="stylesheet" type="text/css" href="css/bootstrap_simple.css?" />
<script type="text/javascript" src="scripts/utilities.js?" ></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?" ></script>
<script type="text/javascript" src="scripts/testResults.js?" ></script>
<script type="text/javascript" src="scripts/testReflex.js?" ></script>
<script type="text/javascript" src="scripts/overlibmws.js?"></script>
<script type="text/javascript" src="scripts/jquery.ui.js?"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?"></script>
<script type="text/javascript" src="scripts/OEPaging.js?"></script>
<script type="text/javascript" src="scripts/math-extend.js?" ></script>
<script type="text/javascript" src="scripts/multiselectUtils.js?" ></script>
<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?" />



<script type="text/javascript" >

<%if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.ALERT_FOR_INVALID_RESULTS, "true")){%>
       outOfValidRangeMsg = '<%= MessageUtil.getMessage("result.outOfValidRange.msg") %>';
<% }else{ %>
       outOfValidRangeMsg = null;
<% } %>

var compactHozSpace = '<%=compactHozSpace%>';
var dirty = false;

var pager = new OEPager('<c:out value="${form.formName}" />', '&type=' + encodeURIComponent('<spring:escapeBody javaScriptEscape="true">${type}</spring:escapeBody>'));
pager.setCurrentPageNumber('<spring:escapeBody javaScriptEscape="true">${form.paging.currentPage}</spring:escapeBody>');

var pageSearch; //assigned in post load function

var pagingSearch = {};
<c:forEach items="${pagingSearch}" var="paging">
pagingSearch['<spring:escapeBody javaScriptEscape="true">${paging.id}</spring:escapeBody>'] = '<spring:escapeBody javaScriptEscape="true">${paging.value}</spring:escapeBody>';
</c:forEach>

jQuery(document).ready( function() {
			var searchTerm = '<%=Encode.forJavaScript(searchTerm)%>';
            loadMultiSelects();
			jQuery("select[multiple]").asmSelect({
					removeLabel: "X"
				});

			jQuery("select[multiple]").change(function(e, data) {
				handleMultiSelectChange( e, data );
				});

			pageSearch = new OEPageSearch( document.getElementById("searchNotFound"), compactHozSpace == "true" ? "tr" : "td", pager );

			if( searchTerm != "null" ){
				 pageSearch.highlightSearch( searchTerm, false );
			}
			
            jQuery('#modal_ok').on('click',function(e){
                addReflexToTests( '<%= MessageUtil.getMessage("button.label.edit")%>' );
                e.preventDefault();
                jQuery('#reflexSelect').modal('hide');
			});

            loadPagedReflexSelections('<%= MessageUtil.getMessage("button.label.edit")%>');
            jQuery(".asmContainer").css("display","inline-block");
            disableRejectedResults();
            showCachedRejectionReasonRows();
            jQuery(".hasDefaultValue").change();
			});


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

function toggleKitDisplay( button ){
	if( button.value == "+" ){
		$(kitView).show();
		button.value = "-";
	}else{
		$(kitView).hide();
		button.value = "+";
	}
}


function markUpdated( index, userChoiceReflex, siblingReflexKey ){
	if( userChoiceReflex ){
		var siblingId = siblingReflexKey != 'null' ? $(siblingReflexKey).value : null;
		showUserReflexChoices( index, $("resultId_" + index).value, siblingId );
	}

	$("modified_" + index).value = "true";

	makeDirty();
	setSave();
}

function updateLogValue(element, index ){
	var logField = $("log_" + index );

	if( logField ){
		var logValue = Math.baseLog(element.value).toFixed(2);

		if( isNaN(logValue) ){
			logField.value = "--";
		}else{
			logField.value = logValue;
		}
	}
}

function setSave() {
	var valid = validateForm();
	if (valid) {
		jQuery("#saveButtonId").removeAttr("disabled");
	} else {
		jQuery("#saveButtonId").attr("disabled");
	}
}


function /*void*/ setRadioValue( index, value){
	$("results_" + index).value = value;
}

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');
}

function /*void*/ autofill( sourceElement ){
	var techBoxes = $$(".techName"),
	    boxCount = techBoxes.length,
	    value = sourceElement.value;
	    i = 0;
	
	for( ; i < boxCount; ++i){
		techBoxes[i].value = value;
	}	
}
function validateForm(){
	return true;
}

function handleReferralCheckChange(checkbox,  index ){
	var referralReason = $( "referralReasonId_" + index );
	referralReason.value = 0;
	referralReason.disabled = !checkbox.checked;
    $("shadowReferred_" + index).value = checkbox.checked;
}

function /*void*/ handleReferralReasonChange(select,  index ){
	if( select.value == 0 ){
		$( "referralId_" + index ).checked = false;
		select.disabled = true;
	}
}

//this overrides the form in additional_utilities.js
function  /*void*/ savePage()
{
	
	jQuery( "#saveButtonId" ).prop("disabled",true);
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = document.getElementById("mainForm");
	form.action = '<spring:escapeBody javaScriptEscape="true">${form.formName}</spring:escapeBody>'.sub('Form','') + ".do"  + '?type=' + encodeURIComponent('<spring:escapeBody javaScriptEscape="true">${type}</spring:escapeBody>');
	form.submit();
}

function updateReflexChild( group){

 	var reflexGroup = $$(".reflexGroup_" + group);
	var childReflex = $$(".childReflex_" + group);
 	var i, childId, rowId, resultIds = "", values="", requestString = "";
 	
 	if( childReflex ){
 		childId = childReflex[0].id.split("_")[1];
 		
		for( i = 0; i < reflexGroup.length; i++ ){
			if( childReflex[0] != reflexGroup[i]){
				rowId = reflexGroup[i].id.split("_")[1];
				resultIds += "," + $("hiddenResultId_" + rowId).value;
				values += "," + reflexGroup[i].value;
			}
		}
		
		requestString +=   "results=" +resultIds.slice(1) + "&values=" + values.slice(1) + "&childRow=" + childId;

		new Ajax.Request (
                      'ajaxQueryXML',  //url
                      {//options
                      method: 'get', //http method
                      parameters: 'provider=TestReflexCD4Provider&' + requestString,
                      indicator: 'throbbing',
      				requestHeaders : {
    					"X-CSRF-Token" : getCsrfToken()
    				},
                      onSuccess:  processTestReflexCD4Success,
                      onFailure:  processTestReflexCD4Failure
                           }
                          );
 	}

}

function processTestReflexCD4Failure(){
	alert("failed");
}

function processTestReflexCD4Success(parameters)
{
    var xhr = parameters.xhr;
	//alert( xhr.responseText );
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var childRow, value;


	if (message.firstChild.nodeValue == "valid"){
		childRow = formField.getElementsByTagName("childRow").item(0).childNodes[0].nodeValue;
		value = formField.getElementsByTagName("value").item(0).childNodes[0].nodeValue;
		
		if( value && value.length > 0){
			$("results_" + childRow).value = value;
		}
	}

}

function submitTestSectionSelect( element ) {
	window.location.href = "LogbookResults.do?testSectionId=" + element.value + "&type=" + encodeURIComponent(testSectionNameIdHash[element.value]) ;	
}

var showForceWarning = true;
function forceTechApproval(checkbox, index ){
	if( jQuery(checkbox).attr('checked')){
		if( showForceWarning){
			alert( `<%= MessageUtil.getContextualMessage("result.forceAccept.warning")%>` );
			showForceWarning = false;
		}
		showNote( index );
	}else{
		hideNote( index);
	}
}

function processDateCallbackEvaluation(xhr) {

    //alert(xhr.responseText);
    var message = xhr.responseXML.getElementsByTagName("message").item(0).firstChild.nodeValue;
    var formFieldId = xhr.responseXML.getElementsByTagName("formfield").item(0).firstChild.nodeValue;
    var givenDate = jQuery("#" + formFieldId).val();
    var isValid = message == "valid";

    if( !isValid ){
        if( message == 'invalid_value_to_large' ){
            alert( '<spring:message code="error.date.inFuture"/>' );
        }else if( message == 'invalid_value_to_small' ){
            alert( '<spring:message code="error.date.inPast"/>' );
        }else if( message == "invalid"){
            alert( givenDate + " <spring:message code="errors.date"/>");
        }
    }

    updateFieldValidity(isValid, formFieldId);
}

function updateShadowResult(source, index){
  jQuery("#shadowResult_" + index).val(source.value);
}

function setField(id, value) {
	jQuery("#" + id).val(value);
}

function altAccessionHighlightSearch(accessionNumber) {
	if (confirm('Searching for an individual Lab no will take you to a new page.\n\nUnsaved data on this page will be lost.\n\nWould you like to continue?')) {
		window.onbeforeunload = null;
		var params = new URLSearchParams("accessionNumber=" + accessionNumber);
		window.location = "AccessionResults.do?" + params.toString();
	}
}

</script>

<c:if test="${form.displayTestSections}">
<div id="searchDiv" class="colorFill"  >
<div id="PatientPage" class="colorFill" style="display:inline" >
<h2><spring:message code="sample.entry.search"/></h2>
	<table width="30%">
		<tr bgcolor="white">
			<td width="50%" align="right" >
				<%= MessageUtil.getMessage("workplan.unit.types") %>
			</td>
			<td>
			<form:select path="testSectionId" 
				 onchange="submitTestSectionSelect(this);" >
				<option value=""></option>
				<form:options items="${form.testSections}" itemLabel="value" itemValue="id" />
			</form:select>
	   		</td>
		</tr>
	</table>
	<br/>
	<h1>
		
	</h1>
</div>
</div>
</c:if>

<%-- Modal popup--%>
<div id="reflexSelect" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="headerLabel"></h3>
    </div>
    <div class="modal-body">
        <input type="hidden" id="testRow" />
        <input type="hidden" id="targetIds" />
        <input type="hidden" id="serverResponse" />
        <p ><input style='vertical-align:text-bottom' id='selectAll' type='checkbox' onchange='modalSelectAll(this);' >&nbsp;&nbsp;&nbsp;<b><spring:message code="label.button.checkAll"/></b></p><hr>
    </div>
    <div class="modal-footer">
        <button id="modal_ok" class="btn btn-primary" disabled="disabled">OK</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
    </div>
</div>

<%-- <c:if test="${not empty form.type}" > --%>
<%-- 	<form:hidden path="type" /> --%>
<%-- </c:if> --%>

<c:if test="${testCount != 0}">
<c:if test="${form.displayTestKit}">
	<hr style="width:100%" />
    <input type="button" onclick="toggleKitDisplay(this)" value="+">
	<spring:message code="inventory.testKits"/>
	<div id="kitView" style="display: none;" class="colorFill" >
		<tiles:insertAttribute name="testKitInfo" /> 
		<br/>
		<hr style="width:100%" />
	</div>
</c:if>

<c:if test="${form.singlePatient}">
<% if(!depersonalize){ %>        
<table style="width:100%" >
	<tr>
		
		<th style="width:20%">
			<spring:message code="person.lastName" />
		</th>
		<th style="width:20%">
			<spring:message code="person.firstName" />
		</th>
		<th style="width:10%">
			<spring:message code="patient.gender" />
		</th>
		<th style="width:15%">
			<spring:message code="patient.birthDate" />
		</th>
		<% if(useSTNumber){ %>
		<th style="width:15%">
			<spring:message code="patient.ST.number" />
		</th>
		<% } %>
		<% if(useNationalID){ %>
		<th style="width:20%">
			<%= MessageUtil.getContextualMessage("patient.NationalID") %>
		</th>
		<% } %>
		<% if(useSubjectNumber){ %>
		<th style="width:20%">
			<spring:message code="patient.subject.number" />
		</th>
		<% } %>
	</tr>
	<tr>
		<td style="text-align:center">
			<c:out value="${form.lastName}" />
		</td>
		<td style="text-align:center">
			<c:out value="${form.firstName}" />
		</td>
		<td style="text-align:center">
			<c:out value="${form.gender}" />
		</td>
		<td style="text-align:center">
			<c:out value="${form.dob}" />
		</td>
		<% if(useSTNumber){ %>
		<td style="text-align:center">
			<c:out value="${form.st}" />
		</td>
		<% } %>
		<% if(useNationalID){ %>
		<td style="text-align:center">
			<c:out value="${form.nationalId}" />
		</td>
		<% } %>
		<% if(useSubjectNumber){ %>
		<td style="text-align:center">
			<c:out value="${form.subjectNumber}" />
		</td>
		<% } %>
	</tr>
</table>
<% } %>
<br/>
</c:if>

<div  style="width:100%" >
<c:if test="${not (form.paging.totalPages == 0)}">
	<form:hidden id="currentPageID" path="paging.currentPage"/>
	<c:set var="total" value="${form.paging.totalPages}"/>
	<c:set var="currentPage" value="${form.paging.currentPage}"/>
	<c:if test="${not empty analysisCount}">
		1 - ${pageSize} of ${analysisCount}
	</c:if>
	<c:if test="${empty analysisCount}">
		<button type="button" style="width:100px;" onclick="pager.pageBack();" <c:if test="${currentPage == 1}">disabled="disabled"</c:if>>
			<spring:message code="label.button.previous"/>
		</button>
		<button type="button" style="width:100px;" onclick="pager.pageFoward();" <c:if test="${currentPage == total}">disabled="disabled"</c:if>>
			<spring:message code="label.button.next"/>
		</button>
		&nbsp;
		<c:out value="${form.paging.currentPage}"/> <spring:message code="report.pageNumberOf" />
		<c:out value="${form.paging.totalPages}"/>
	</c:if>
	<div class='textcontent' style="float: right" >
	<span style="visibility: hidden" id="searchNotFound"><em><%= MessageUtil.getMessage("search.term.notFound") %></em></span>
	<%=MessageUtil.getContextualMessage("result.sample.id")%> : &nbsp;
	<input type="text"
	       id="labnoSearch"
	       placeholder='<spring:message code="sample.search.scanner.instructions"/>'
	       maxlength='<%= Integer.toString(AccessionNumberUtil.getMaxAccessionLength())%>' />
	<input type="button" onclick="pageSearch.doLabNoSearch(document.getElementById('labnoSearch'))" value='<%= MessageUtil.getMessage("label.button.search") %>'>
	</div>
</c:if>

<div style="float: right" >
<img src="./images/nonconforming.gif" /> = <spring:message code="result.nonconforming.item"/>&nbsp;&nbsp;&nbsp;&nbsp;
<% if(failedValidationMarks){ %> 
<img src="./images/validation-rejected.gif" /> = <spring:message code="result.validation.failed"/>&nbsp;&nbsp;&nbsp;&nbsp;
<% } %>
</div>

</div>

<Table style="width:100%" border="0" cellspacing="0" >
	<%-- header --%>
	<tr >
		<% if( !compactHozSpace ){ %>
		<th style="text-align: left">
			<%=MessageUtil.getContextualMessage("result.sample.id")%>
		</th>
		<c:if test="${not form.singlePatient}">
			<th style="text-align: left">
				<spring:message code="result.sample.patient.summary"/>
			</th>
		</c:if>
		<% } %>

		<th style="text-align: left">
			<spring:message code="result.test.date"/><br/>
			<%=DateUtil.getDateUserPrompt()%>
		</th>
		<c:if test="${form.displayTestMethod}">
			<th style="width: 72px; padding-right: 10px; text-align: center">
				<spring:message code="result.method.auto"/>
			</th>
		</c:if>
		<th style="text-align: left">
			<spring:message code="result.test"/>
		</th>
		<th style="width:16px">&nbsp;</th>
		<th style="width: 56px; padding-right: 10px; text-align: center"><%= MessageUtil.getContextualMessage("result.forceAccept.header") %></th>
		<th style="width:165px; text-align: left">
			<spring:message code="result.result"/>
		</th>
		<% if( ableToRefer ){ %>
		<th style="text-align: left">
			<spring:message code="referral.referandreason"/>
		</th>
		<% } %>
		<% if( useTechnicianName ){ %>
		<th style="text-align: left">
			<spring:message code="result.technician"/>
			<br/>
			<% if(autofillTechBox){ %>
			Autofill:<input type="text" size='10em' onchange="autofill( this )">
			<% } %>
		</th>
		<% }%>
        <% if( useRejected ){ %>
        <th style="text-align: center">
            <spring:message code="result.rejected"/>&nbsp;
        </th>
        <% }%>
		<th style="width:2%;text-align: left">
			<spring:message code="result.notes"/>
		</th>
	</tr>
	<%-- body --%>
	<c:forEach items="${form.testResult}" var="testResult" varStatus="iter">
	<form:hidden path="testResult[${iter.index}].accessionNumber"/>
	<c:if test="${testResult.isGroupSeparator}">
	<tr>
		<td colspan="10"><hr/></td>
	</tr>
	<tr>
		<th >
			<spring:message code="sample.receivedDate"/> <br/>
			<c:out value="${testResult.receivedDate}"/>
		</th>
		<th >
			<%=MessageUtil.getContextualMessage("resultsentry.accessionNumber")%><br/>
			<c:out value="${testResult.accessionNumber}"/>
		</th>
		<th colspan="8" ></th>
	</tr>
	</c:if>
	<c:if test="${not testResult.isGroupSeparator}">
		<c:set var="lowerBound" value="${testResult.lowerNormalRange}" />
		<c:set var="upperBound" value="${testResult.upperNormalRange}" />
		<c:set var="lowerAbnormalBound" value="${testResult.lowerAbnormalRange}" />
		<c:set var="upperAbnormalBound" value="${testResult.upperAbnormalRange}" />
        <c:set var="significantDigits" value="${testResult.significantDigits}" />
		<c:set var="accessionNumber" value="${testResult.accessionNumber}"/>
        <c:set var="rowEven" value="${testResult.sampleGroupingNumber %2 == 0}" />
	    <c:if test="${rowEven}"> <c:set var="rowColor" value='evenRow' /> </c:if>
	    <c:if test="${not rowEven}"> <c:set var="rowColor" value='oddRow' /> </c:if>

   <% if( compactHozSpace ){ %>
   <c:if test="${testResult.showSampleDetails}">
		<tr class='${rowColor}Head ${accessionNumber}' >
			<td colspan="10" class='InterstitialHead' >
			    <%=MessageUtil.getContextualMessage("result.sample.id")%> : &nbsp;
				<b><c:out value="${testResult.accessionNumber}"/> -
				<c:out value="${testResult.sequenceNumber}"/></b>
				<% if(useInitialCondition){ %>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="sample.entry.sample.condition" />:
					<b><c:out value="${testResult.initialSampleCondition}" /></b>
				<% } %>
				&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="sample.entry.sample.type"/>:
				<b><c:out value="${testResult.sampleType}" /></b>
		<c:if test="${not form.singlePatient}">
		    <% if( !depersonalize){ %>
				<c:if test="${testResult.showSampleDetails}">
					<br/>
					<spring:message code="result.sample.patient.summary"/> : &nbsp;
					<b><c:out value="${testResult.patientName}"/> &nbsp;
					<c:out value="${testResult.patientInfo}"/></b>
				</c:if>
			<% } %>	
		</c:if>
		</td>
		</tr>
	</c:if>
    <% } %>
	<tr class='${rowColor}'  id="row_${iter.index}">
			<form:hidden path="testResult[${iter.index}].isModified"  id="modified_${iter.index}" />
			<form:hidden path="testResult[${iter.index}].analysisId"  id="analysisId_${iter.index}" />
			<form:hidden path="testResult[${iter.index}].resultId"  id="hiddenResultId_${iter.index}"/>
			<form:hidden path="testResult[${iter.index}].testId"  id="testId_${iter.index}"/>
			<form:hidden path="testResult[${iter.index}].technicianSignatureId" indexed="true" />
			<form:hidden path="testResult[${iter.index}].testKitId" indexed="true" />
			<form:hidden path="testResult[${iter.index}].resultLimitId" indexed="true" />
			<form:hidden path="testResult[${iter.index}].resultType" id="resultType_${iter.index}" />
			<form:hidden path="testResult[${iter.index}].valid" id="valid_${iter.index}"/>
			<form:hidden path="testResult[${iter.index}].referralId" />
            <form:hidden path="testResult[${iter.index}].referralCanceled" />
            <form:hidden path="testResult[${iter.index}].considerRejectReason" id="considerRejectReason_${iter.index}" />
            <form:hidden path="testResult[${iter.index}].hasQualifiedResult" id="hasQualifiedResult_${iter.index}" />
            <form:hidden path="testResult[${iter.index}].shadowResultValue" id="shadowResult_${iter.index}" />
            <c:if test="${testResult.userChoiceReflex}">
                <form:hidden path="testResult[${iter.index}].reflexJSONResult"  id="reflexServerResultId_${iter.index}"  cssClass="reflexJSONResult"/>
            </c:if>
			<c:if test="${not empty testResult.thisReflexKey}">
					<input type="hidden" id='${testResult.thisReflexKey}' value='${iter.index}' />
			</c:if>
		 <% if( !compactHozSpace ){ %>
	     <td class='${accessionNumber}'>
			<c:if test="${testResult.showSampleDetails}">
				<c:out value="${testResult.accessionNumber}"/> -
				<c:out value="${testResult.sequenceNumber}"/>
			</c:if>
		</td>
		<c:if test="${not form.singlePatient}">
			<td >
				<c:if test="${testResult.showSampleDetails}">
					<c:out value="${testResult.patientName}"/><br/>
					<c:out value="${testResult.patientInfo}"/>
				</c:if>
			</td>
		</c:if>
		<% } %>
		<%-- date cell --%>
		<td class="ruled">
			<form:input path="testResult[${iter.index}].testDate"
                       size="10"
                       maxlength="10"
                       tabindex='-1'
                       onchange="markUpdated(${iter.index});checkValidDate(this, processDateCallbackEvaluation, 'past', false)"
                       onkeyup="addDateSlashes(this, event);"
                       id="testDate_${iter.index}"/>
		</td>
		<c:if test="${form.displayTestMethod}">
			<td class="ruled" style='text-align: center'>
				<form:checkbox path="testResult[${iter.index}].analysisMethod"
					 tabindex='-1'
					 value='on'
					 onchange='markUpdated(${iter.index});' />
			</td>
		</c:if>
		<%-- results --%>
		<c:if test="${testResult.resultDisplayType == 'HIV'}">
			<td style="vertical-align:top" class="ruled">
				<form:hidden path="testResult[${iter.index}].testMethod" />
				<c:out value="${testResult.testName}"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<spring:message code="inventory.testKit"/>
				<form:select path="testResult[${iter.index}].testKitInventoryId"
							 tabindex='-1'
							 onchange='markUpdated(iter.index);' >
				    <form:options items="${hivKits}"/>
					<c:if test="${testResult.testKitInactive}">
						<option value='${testResult.testKitInventoryId}' selected >${testResult.testKitInventoryId}</option>
					</c:if>
				</form:select>
			</td>
		</c:if>
		<c:if test="${testResult.resultDisplayType == 'SYPHILIS'}">
			<td style="vertical-align:middle; text-align: center" class="ruled">
				<form:hidden path="testResult[${iter.index}].testMethod"/>
				<c:out value="${testResult.testName}"/>
				<c:if test="${testResult.reflexStep > 0}">
				&nbsp;--&nbsp;
				<spring:message code="reflexTest.step" />&nbsp;<c:out value="${testResult.reflexStep}"/>
				</c:if>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<spring:message code="inventory.testKit"/>
				<form:select path="testResult[${iter.index}].testKitInventoryId"
							 tabindex='-1'
							 onchange='markUpdated(${iter.index});' >
				    <form:options items="${syphilisKits}" />
					<c:if test="${testResult.testKitInactive}">
						<option value='${testResult.testKitInventoryId}' selected >${testResult.testKitInventoryId}</option>
					</c:if>
				</form:select>
			</td>
		</c:if>
		<c:if test="${not (testResult.resultDisplayType == 'HIV') and not (testResult.resultDisplayType == 'SYPHILIS')}">
			<td style="vertical-align:middle" class="ruled">
                ${testResult.testName}
                <c:if test="${not (testResult.resultType == 'D')}">
				<c:if test="${not empty testResult.normalRange}">
					<br/><c:out value="${testResult.normalRange}"/>&nbsp;
					<c:out value="${testResult.unitsOfMeasure}"/>
				</c:if>
				</c:if>
			</td>
		</c:if>

		<td class="ruled" style='vertical-align: middle'>
		<% if( failedValidationMarks){ %>
		<c:if test="${testResult.failedValidation}">
			<img src="./images/validation-rejected.gif" />
		</c:if>
		<% } %>
		<c:if test="${testResult.nonconforming}">
			<img src="./images/nonconforming.gif" />
		</c:if>
		</td>
		<%-- force acceptance --%>
		<td class="ruled" style='text-align: center'>
			<form:checkbox path="testResult[${iter.index}].forceTechApproval"
							value="on"
							tabindex='-1'
							onchange='markUpdated(${iter.index}); forceTechApproval(this, ${iter.index});' 
							/>
		</td>
		<%-- result cell --%>
		<td id="cell_${iter.index}" class="ruled" >
			<c:if test="${testResult.resultType == 'N'}">
				<form:hidden path="testResult[${iter.index}].lowerNormalRange"/>
				<form:hidden path="testResult[${iter.index}].upperNormalRange"/>
			    <form:input path="testResult[${iter.index}].resultValue" 
			           size="6" 
			           id="results_${iter.index}"
			           style="background: ${testResult.valid ? testResult.normal ? '#ffffff' : '#ffffa0' : '#ffa0a0' }"
					   disabled='${testResult.readOnly}' 
					   onchange="validateResults( this, ${iter.index}, ${lowerBound}, ${upperBound}, ${lowerAbnormalBound}, ${upperAbnormalBound}, ${significantDigits}, 'XXXX' );
					   			 markUpdated(${iter.index});
					   			 ${(testResult.reflexGroup && not testResult.childReflex) ? 'updateReflexChild(' += testResult.reflexParentGroup += ');' : ''}
					   			 ${(noteRequired && not empty testResult.resultValue) ? 'showNote(' += iter.index += ');' : ''}
					   			 ${(testResult.displayResultAsLog) ? 'updateLogValue(this, ' += iter.index += ');' : ''}
					   			 updateShadowResult(this, ${iter.index});"
					   />
					   <form:hidden path="testResult[${iter.index}].significantDigits"/>
			</c:if><c:if test="${testResult.resultType == 'A'}">
				<form:input path="testResult[${iter.index}].resultValue"
						  size="20"
						  disabled='${testResult.readOnly}'
						  id="results_${iter.index}"
			              style="background: ${testResult.valid ? testResult.normal ? '#ffffff' : '#ffffa0' : '#ffa0a0' }"
						  onchange="markUpdated(${iter.index});
					   			    ${(testResult.displayResultAsLog) ? 'updateLogValue(this, ' += iter.index += ');' : ''}
					   				${(noteRequired && not empty testResult.resultValue) ? 'showNote(' += iter.index += ');' : ''}
					   			 	updateShadowResult(this, ${iter.index});"
						/>
			</c:if><c:if test="${testResult.resultType == 'R'}">
				<%-- text results --%>
				<form:textarea path="testResult[${iter.index}].resultValue"
						  rows="2"
						  disabled='${testResult.readOnly}'
						  id="results_${iter.index}"
			              style="background: ${testResult.valid ? testResult.normal ? '#ffffff' : '#ffffa0' : '#ffa0a0' }"
						  onkeyup="value = value.substr(0,200);
						           markUpdated(${iter.index});
					   			   ${(noteRequired && not (empty testResult.resultValue)) ? 'showNote(' += iter.index += ');' : ''}
					   			   updateShadowResult(this, ${iter.index}); "
						  />
			</c:if><c:if test="${testResult.resultType == 'D'}">
			<%-- dictionary results --%>
			<form:select path="testResult[${iter.index}].resultValue"
			        id="resultId_${iter.index}"
			        cssClass="${(empty testResult.resultValue) ? '' : 'hasDefaultValue' }"
			        onchange="markUpdated(${iter.index}, ${testResult.userChoiceReflex}, '${testResult.siblingReflexKey}');
					   		  ${(noteRequired && not (empty testResult.resultValue)) ? 'showNote(' += iter.index += ');' : ''}
					   		  ${(not (empty testResult.qualifiedDictionaryId)) ? 'showQuantity(this, ' += iter.index += ', ' += testResult.qualifiedDictionaryId += ', \\'D\\');' : ''}
					   		  updateShadowResult(this, ${iter.index}); "
			        disabled='${testResult.readOnly}'
			        >
					<option value="0"></option>
					<form:options items="${testResult.dictionaryResults}" itemValue="id" itemLabel="value"/>
			</form:select><br/>
			<form:input path='testResult[${iter.index}].qualifiedResultValue'
			           id="qualifiedDict_${iter.index}"
			           disabled='${testResult.readOnly}'
			           style="${(not testResult.hasQualifiedResult) ? 'display:none' : ''}"
					   onchange='markUpdated(${iter.index});'
					    />
			</c:if><c:if test="${testResult.resultType == 'M'}">
			<%-- multiple results --%>
			<form:select path="testResult[${iter.index}].multiSelectResultValues"
					id="resultId_${iter.index}_0"
                    multiple="multiple"
			        disabled='${testResult.readOnly}'
			        onchange="markUpdated(${iter.index});
			        		  ${(noteRequired && not (empty testResult.multiSelectResultValues) && fn:length(testResult.multiSelectResultValues) > 2) ? 'showNewNote(' += iter.index += ');' : ''}
			        		  ${(not (empty testResult.qualifiedDictionaryId)) ? 'showQuantity(this, ' += iter.index += ', ' += testResult.qualifiedDictionaryId += ', \\'M\\'); ' : ''}
			        ">
						<form:options items="${testResult.dictionaryResults}" itemValue="id" itemLabel="value"/>
				</form:select>
				<form:hidden path="testResult[${iter.index}].multiSelectResultValues" id="multiresultId_${iter.index}" cssClass="multiSelectValues"  />
                <form:input path='testResult[${iter.index}].qualifiedResultValue'
                   id="qualifiedDict_${iter.index}"
			       disabled='${testResult.readOnly}'
			       style="${(not testResult.hasQualifiedResult) ? 'display:none' : ''}"
                   onchange='markUpdated(${iter.index});'
                />
			</c:if><c:if test="${testResult.resultType == 'C'}">
                <%-- cascading multiple results --%>
                <div id="cascadingMulti_${iter.index}_0" class="cascadingMulti_${iter.index}" >
                <input type="hidden" id="divCount_${iter.index}" value="0" >
                <form:select path="testResult[${iter.index}].multiSelectResultValues"
                        id="resultId_${iter.index}_0"
                        cssClass="${testResult.userChoiceReflex}" 
                       multiple="multiple"
                       disabled='${testResult.readOnly}'
			           onchange="markUpdated(${iter.index});
			           			 ${(noteRequired && not (empty testResult.multiSelectResultValues) && fn:length(testResult.multiSelectResultValues) > 2) ? 'showNewNote(' += iter.index += '});' : ''}
			        		     ${(not (empty testResult.qualifiedDictionaryId)) ? 'showQuantity(this, ' += iter.index += ', ' += testResult.qualifiedDictionaryId += ', ' += '\\'M\\');' : '' }
			           " >
                       <form:options items="${testResult.dictionaryResults}" itemValue="id" itemLabel="value" />
                </form:select>
                <input class='addMultiSelect${iter.index}' type="button" value="+" 
                	onclick="addNewMultiSelect(${iter.index}, this);
                	 		 ${(noteRequired) ? 'showNewNote(' += iter.index += ');' : ''}
                	 		 "/>
                <input class='removeMultiSelect${iter.index}' type="button" value="-" 
                	onclick='removeMultiSelect("target");
                	 		 ${(noteRequired) ? 'showNewNote(' += iter.index += ');' : ''}
                			 ' 
                	style="display: none" />
                <form:hidden path="testResult[${iter.index}].multiSelectResultValues" id="multiresultId_${iter.index}" cssClass="multiSelectValues"  />
                <form:input path="testResult[${iter.index}].qualifiedResultValue"
                            id="qualifiedDict_${iter.index}"
			           		disabled='${testResult.readOnly}'
			       			style="${(not testResult.hasQualifiedResult) ? 'display:none' : ''}"
                       		onchange='markUpdated(${iter.index});'
                        />

                </div>
            </c:if>
            <c:if test="${testResult.displayResultAsLog}">
						<br/><input type='text'
								    id="log_${iter.index}"
									disabled='disabled'
									style="color:black"
									value="${testResult.resultValue}"
									size='6' /> log
					</c:if>
            <c:out value="${testResult.unitsOfMeasure}"/>
		</td>
		<% if( ableToRefer ){ %>
		<td style="white-space: nowrap" class="ruled">
            <form:hidden path="testResult[${iter.index}].shadowReferredOut" id="shadowReferred_${iter.index}" />
		<c:choose >
		<c:when test="${empty testResult.referralId || testResult.referralCanceled}">
			<form:checkbox path="testResult[${iter.index}].referredOut"
						   id="referralId_${iter.index}"
						   onchange='markUpdated(${iter.index}); handleReferralCheckChange( this, ${iter.index})'/>
	
		</c:when><c:otherwise>
			<form:checkbox path="testResult[${iter.index}].referredOut"
						   disabled="true" />
		</c:otherwise>
		</c:choose>
			<form:select path="testResult[${iter.index}].referralReasonId"
			        id="referralReasonId_${iter.index}"
					onchange='markUpdated(${iter.index}); handleReferralReasonChange( this, ${iter.index})'
					disabled="${(testResult.shadowReferredOut && (testResult.referralReasonId == '0')) ? 'false' : 'true'}"
			        >
			        <option value='0' >
					   <c:if test="${testResult.referralCanceled}">
					   		<spring:message code="referral.canceled" />
					   </c:if>
					</option>
			<form:options items="${form.referralReasons}" itemValue="id" itemLabel="value" />
			</form:select>
		</td>
		<% } %>
		<% if( useTechnicianName){ %>
		<td style="text-align: left" class="ruled">
			<form:input path="testResult[${iter.index}].technician"
					   id="technicianSig_${iter.index}"
					   disabled='${testResult.readOnly}'
					   style="margin: 1px"
					   size="10em"
                       maxlength="18"
					   onchange='markUpdated(${iter.index});'/>
		</td>
		<% } %>
		<% if( useRejected){ %> 
			<td class="ruled" style='text-align: center'>
			<form:hidden path="testResult[${iter.index}].shadowRejected" id="shadowRejected_${iter.index}" />
			
			<input type="hidden" id="isRejected_${iter.index}" value="${testResult.rejected}"/>
			<spring:message code="result.delete.confirm" var="deleteMsg"/>
	                <form:checkbox path="testResult[${iter.index}].rejected"
	                    id="rejected_${iter.index}" 
	                    tabindex='-1'
	                    onchange="markUpdated(${iter.index}); showHideRejectionReasons(${iter.index}, '${deleteMsg}' );" />
	   		</td>
		<% } %>
		<td style="text-align:left" class="ruled">
						 	<img src="./images/note-add.gif"
						 	     onclick='showHideNotes(${iter.index});'
						 	     id="showHideButton_${iter.index}"
						    />
            <input type="hidden" name="hideShowFlag" value="hidden" id="hideShow_${iter.index}" >
		</td>
	</tr>
	<tr id="rejectReasonRow_${iter.index}"
        class='${rowColor}'
        style="${(testResult.considerRejectReason != 'true') ? 'display: none;' : ''}"
		>
        <td colspan="4"></td>
        <td colspan="6" style="text-align:right" >
               <form:select path="testResult[${iter.index}].rejectReasonId"
                    id="rejectReasonId_${iter.index}"
                    disabled='${testResult.readOnly}'>
                    <form:options items="${form.rejectReasons}" itemValue="id" itemLabel="value"/>
            </form:select><br/>
       </td>
    </tr>   
	<c:if test="${not empty testResult.pastNotes}">
		<tr class='${rowColor}' >
			<td colspan="2" style="text-align:right;vertical-align:top"><spring:message code="label.prior.note" />: </td>
			<td colspan="8" style="text-align:left">
				${testResult.pastNotes}
			</td>
		</tr>
	</c:if>
	<tr id="noteRow_${iter.index}"
		class='${rowColor}'
		style="display: none;">
		<td colspan="4" style="vertical-align:top;text-align:right">
			<c:choose><c:when test="${noteRequired && not (empty testResult.multiSelectResultValues && empty testResult.resultValue)}">
							<spring:message code="note.required.result.change"/>					
					  </c:when>
					  <c:otherwise>
					  		<spring:message code="note.note"/>
					  </c:otherwise>
			</c:choose>
													:</td>
		<td colspan="6" style="text-align:left" >
			<form:textarea id="note_${iter.index}"
						   onchange='markUpdated(${iter.index});'
					   	   path="testResult[${iter.index}].note"
			           	   cols="100"
			           	   rows="3" />
		</td>
	</tr>
	</c:if>
	</c:forEach>
</Table>
<c:if test="${not (form.paging.totalPages == 0)}">
	<c:if test="${not empty analysisCount}">
		1 - ${pageSize} of ${analysisCount}
	</c:if>
	<c:if test="${empty analysisCount}">
	<c:set var="total" value="${form.paging.totalPages}"/>
	<c:set var="currentPage" value="${form.paging.currentPage}"/>
	<button type="button" style="width:100px;" onclick="pager.pageBack();" <c:if test="${currentPage == 1}">disabled="disabled"</c:if>>
		<spring:message code="label.button.previous"/>
	</button>
	<button type="button" style="width:100px;" onclick="pager.pageFoward();" <c:if test="${currentPage == total}">disabled="disabled"</c:if>>
		<spring:message code="label.button.next"/>
	</button>
	&nbsp;
	<c:out value="${form.paging.currentPage}"/> of
	<c:out value="${form.paging.totalPages}"/>
	</c:if>
</c:if>

</c:if>


<c:if test="${form.displayTestSections}">
	<c:if test="${testCount == 0}">
		<c:if test="${not empty form.testSectionId}">
		<h2><%= MessageUtil.getContextualMessage("result.noTestsFound") %></h2>
		</c:if>
	</c:if>
</c:if>

<c:if test="${not form.displayTestSections}">
	<c:if test="${testCount == 0}">
	<h2><%= MessageUtil.getContextualMessage("result.noTestsFound") %></h2>
	</c:if>
</c:if>
