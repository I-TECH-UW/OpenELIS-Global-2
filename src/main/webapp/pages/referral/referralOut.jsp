<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.openelisglobal.common.action.IActionConstants,
                 org.openelisglobal.common.util.DateUtil,
                 org.openelisglobal.internationalization.MessageUtil,
                 java.util.List,
                 org.openelisglobal.common.util.Versioning,
                 org.openelisglobal.referral.action.beanitems.ReferralItem,
                 org.openelisglobal.common.util.IdValuePair,
                 org.openelisglobal.referral.action.beanitems.ReferredTest" %>


<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="scripts/jquery.ui.js?"></script>
<script type="text/javascript"
        src="scripts/jquery.asmselect.js?"></script>
<script type="text/javascript" src="scripts/utilities.js?"></script>
<script type="text/javascript" src="scripts/testReflex.js?" ></script>
<script type="text/javascript"
        src="scripts/multiselectUtils.js?"></script>

<link rel="stylesheet" type="text/css"
      href="css/jquery.asmselect.css?"/>
<script type="text/javascript">

jQuery(document).ready(function () {
    loadMultiSelects();
    jQuery("select[multiple]:visible").asmSelect({
        removeLabel: "X"
        // , debugMode: true
    });

    jQuery("select[multiple]").change(function (e, data) {
        handleMultiSelectChange(e, data);
    });

    jQuery(".asmContainer").css("display", "inline-block");
    
    jQuery('.saveToggle').each(function() {
    	setReferralStatus(jQuery(this).val());
    	checkFinish(jQuery(this).val());
    });
});


function /*void*/ markModified(index) {
	checkFinish(index);
	jQuery("#modified_" + index).val('true');
	jQuery("#saveButtonId").prop('disabled', missingRequiredValues());
    makeDirty();
}

function missingRequiredValues() {
    var missing = false;

    jQuery(".requiredRow").each(function (index, element) {
        var children = jQuery(element).find(".required");
        if (!((children[1].value == 0 && children[0].value == 0) ||
                (children[1].value != 0 && children[0].value != 0))) {
            missing = true;
        }
    });

    return missing;

}

/*
 Checks to see if the test has dictionary results, multi select dictionary results or numeric results and then switch if needed
 */
function /*void*/ updateResultField(index) {
    //This function will now just enable/disable the existing results widgits
    if ($("testSelection_" + index).value == $("shadowReferredTest_" + index).value) {
        jQuery(".resultCell_" + index).show();
    } else {
        jQuery(".resultCell_" + index).hide();
    }
}

function insertNewTestRequest(button, index) {
    var cell, newRow;
    var addedRowCounter = $("addedRowCount_" + index);
    var parent = button.parentNode;
    var newRowMarker = index + "_" + addedRowCounter.value;
    addedRowCounter.value++;

    while (parent && parent.tagName != "TR") {
        parent = parent.parentNode;
    }

    additionalTests = parent.rowIndex - $("referralRow_" + index).rowIndex;

    newRow = $("mainTable").insertRow(parent.rowIndex);
    newRow.className = parent.className;
    newRow.id = "additionalRow_" + newRowMarker;

    cell = newRow.insertCell(0);
    cell.colSpan = 3;
    //used to create the xml wad
    cell.innerHTML = '<input type="hidden"  name="addedTest"  value="' + index + '" id="' + newRowMarker + '"  >';
    cell.innerHTML += '<input type="hidden"  value="N" id="referredType_' + newRowMarker + '"  >';

    newRow.insertCell(1).innerHTML = "<input type=\"button\"  name=\"remove\"  value=\"" + '<%= MessageUtil.getMessage("label.button.remove")%>' + "\" onclick=\"removeRow('" + newRowMarker + "');\" class=\"textButton\"  >";

    cell = newRow.appendChild($("testSelection_" + index).parentNode.cloneNode(true));
    var selectionNode = cell.getElementsByTagName("select")[0];
    selectionNode.id = "testSelection_" + newRowMarker;
    selectionNode.selectedIndex = 0;
    selectionNode.onchange = function () {
        markModified(index + "");
    }; //blank "" force to string

    newRow.insertCell(3);
    cell = newRow.insertCell(4);
    cell.colSpan = 3;
    cell.className = "leftVertical";

    $("saveButtonId").disabled = true;
}

function /*void*/ removeRow(rowMarker) {
    var row = $("additionalRow_" + rowMarker);
    $("mainTable").deleteRow(row.rowIndex);
}

function /*void*/ handleCancelAction(button, index) {
    var cancelField = $("cancel_" + index);

    if (cancelField.value == "true") {
        cancelField.value = "false";
        button.value = "Cancel";
    } else {
        cancelField.value = "true";
        button.value = "Restore";
    }
}

function /*void*/ validateDateFormat(dateElement) {
    var valid = true;
    if (dateElement.value.length > 0) {
        var dateRegEx = new RegExp("\\d{2}/\\d{2}/\\d{4}");
        valid = dateRegEx.test(dateElement.value);
    }

    dateElement.style.borderColor = valid ? "" : "red";
}

function setXMLWads() {
    var i = 0;
    var XMLWadElements = $$(".XMLWad");
    var XMLWad;

    var addedTests = document.getElementsByName("addedTest");

    for (i; i < addedTests.length; i++) {
        addTestToXMLWad(addedTests[i]);
    }

    for (i = 0; i < XMLWadElements.length; i++) {
        if (XMLWadElements[i].value) {
            XMLWad = "<?xml version='1.0' encoding='utf-8'?><tests>";
            XMLWad += XMLWadElements[i].value;
            XMLWad += "</tests>";
            //alert(XMLWad);
            XMLWadElements[i].value = XMLWad;
        }
    }

}

function /*void*/ addTestToXMLWad(headElement) {
    var referralIndex = headElement.value;
    var marker = headElement.id;
    var dateElement = $("referredReportDate_" + marker);
    var numericElement = $("numericResult_" + marker);
    var dictionaryElement = $("dictionaryResult_" + marker);
    var multiSelectElement = $("resultMultiSelect_" + marker);

    var testSelection = $("testSelection_" + marker);
    var testId = testSelection.options[testSelection.selectedIndex].value;
    var reportDate = dateElement ? dateElement.value : "";
    var resultType = $("referredType_" + marker).value;

    var result = numericElement ? numericElement.value : "";
    if (resultType == "D") {
        result = dictionaryElement ? dictionaryElement.value : "";
    }
    if (resultType == "M") {
        result = multiSelectElement ? multiSelectElement.value : "";
    }

    var existingXML_Element = $("textXML_" + referralIndex);
    var existingXML = existingXML_Element.value;
    existingXML += '<test ';
    existingXML += 'testId=\"' + testId + '\" ';
    existingXML += 'resultType=\"' + resultType + '\" ';
    existingXML += 'result=\"' + result + '\" ';
    existingXML += 'report =\"' + reportDate + '\" ';
    existingXML += ' />';

    existingXML_Element.value = existingXML;
}

function /*void*/ savePage() {
    setXMLWads();

    window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
    var form = document.getElementById("mainForm");
    form.action = "ReferredOutTests.do";
    form.submit();
}
function  /*void*/ setMyCancelAction(form, action, validate, parameters) {
    //first turn off any further validation
    setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');
}

function setReferralStatus(index) {
	jQuery('#referralStatus_' + index).val(jQuery('#canceled_' + index).is(':checked') ? 'CANCELED' : jQuery('#finished_' + index).is(':checked') ? 'FINISHED' : 'SENT')
}

function checkFinish(index) {
	var hasNumericResult = jQuery("#numericResult_" + index).val() !== undefined && jQuery("#numericResult_" + index).val() !== "";
	var hasDictionaryResult = jQuery("#dictionaryResult_" + index).val() !== undefined && jQuery("#dictionaryResult_" + index).val() !== "0";
	var hasMultiSelectResult = jQuery("#resultMultiSelect_" + index).val() !== undefined && jQuery("#resultMultiSelect_" + index).val() !== "";
	var hasResult = hasNumericResult ||  hasDictionaryResult || hasMultiSelectResult;
	jQuery("#finished_" + index).prop('disabled', !hasResult);
	if (!hasResult && jQuery('#finished_' + index).is(':checked')) {
		jQuery("#finished_" + index).prop('checked', false);
	}
}


</script>

<c:if test="${not empty form.referralItems}">

<table width="100%" border="0" cellspacing="0" cellpadding="1" id="mainTable">
<tr>
    <th colspan="6" class="headerGroup"><spring:message code="referral.header.group.request"/></th>
    <th colspan="3" class="leftVertical headerGroup"><spring:message code="referral.header.group.results"/></th>
</tr>
<tr>
    <th><spring:message code="referral.reason"/><span class="requiredlabel">*</span></th>
    <th><spring:message code="referral.referer"/></th>
    <th><spring:message code="referral.institute"/><span class="requiredlabel">*</span></th>
    <th><spring:message code="referral.sent.date"/><br/><%=DateUtil.getDateUserPrompt()%></th>
    <th><spring:message code="test.testName"/><span class="requiredlabel">*</span></th>
    <th width="5%"><spring:message code="label.button.cancel.referral"/></th>
    <th width="15%" class="leftVertical"><spring:message code="result.result"/></th>
    <th><spring:message code="referral.report.date"/><br/><%=DateUtil.getDateUserPrompt()%></th>
    <th width="5%"><spring:message code="referral.finish" text="Finish Referral"/></th>
</tr>

<c:forEach items="${form.referralItems}" var="referralItems" varStatus="iter">
<form:hidden id='textXML_${iter.index}' path="referralItems[${iter.index}].additionalTestsXMLWad" cssClass="XMLWad" />
<form:hidden id='referralResultId_${iter.index}' path="referralItems[${iter.index}].referralResultId" />
<form:hidden id='referralId_${iter.index}' path="referralItems[${iter.index}].referralId" indexed="true" />
<form:hidden id='referredType_${iter.index}' path="referralItems[${iter.index}].referredResultType" />
<form:hidden id='modified_${iter.index}' path="referralItems[${iter.index}].modified" />
<form:hidden id='causalResultId_${iter.index}' path="referralItems[${iter.index}].inLabResultId" />
<input type="hidden"
       value="${(referralItems.additionalTests == null) ? '0' : fn:length(referralItems.additionalTests)}"
       id='addedRowCount_${iter.index}'/>
<c:set var="rowColor" value="${(iter.index % 2 == 0) ? 'oddRow' :'evenRow'}"/>

<tr class='${rowColour}>Head' id='referralRow_${iter.index}'>
    <td colspan="2" class="HeadSeperator">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= MessageUtil.getContextualMessage( "resultsentry.accessionNumber" ) %>:
        <b><c:out value="${referralItems.accessionNumber}"/></b>
    </td>
    <td colspan="4" class="HeadSeperator">
        <spring:message code="referral.request.date"/>: <b><c:out value="${referralItems.referralDate}"/></b>
    </td>
    <td colspan='3' class="HeadSeperator leftVertical">
</tr>
<tr class='${rowColour}>Head' id='referralRow_${iter.index}'>
    <td colspan="2">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.sampleType"/>: <b><c:out value="${referralItems.sampleType}"/></b>
    </td>
    <td colspan="2">
        <spring:message code="test.testName"/>: <b><c:out value="${referralItems.referringTestName}"/></b>
    </td>
    <td colspan="2">
<%--         <spring:message code="result.original.result"/>: <b><c:out value="${referralItems.referralResults}"/></b> --%>
    </td>
    <td colspan="3" class="leftVertical">
        &nbsp;
    </td>
</tr>
<tr class='${rowColor} requiredRow' id='referralRow_${iter.index}'>
    <td style="text-align:center">
        <form:select path="referralItems[${iter.index}].referralReasonId"
                id='referralReasonId_${iter.index}'
                onchange='markModified("${iter.index}");'>
            <form:options items="${form.referralReasons}" itemValue="id" itemLabel="value"/>
        </form:select>
    </td>
    <td style="text-align:center">
        <form:input path="referralItems[${iter.index}].referrer"
                   onchange='markModified("${iter.index}");'/>
    </td>
    <td style="text-align:center">
        <form:select path='referralItems[${iter.index}].referredInstituteId'
                class="required"
                onchange='markModified("${iter.index}");'>
                <form:options items="${form.referralOrganizations}" itemValue="id" itemLabel="value"/>
        </form:select>
    </td>
    <td style="text-align:center">
        <form:input path="referralItems[${iter.index}].referredSendDate"
                   size="8"
                   maxlength="10"
                   onchange='markModified("${iter.index}"); validateDateFormat(this);'
                   id='sendDate_${iter.index}'/>
    </td>
    <td style="text-align:center">
        <input type="hidden" name="_referralItems[${iter.index}].referredTestId"
                     id='shadowReferredTest_${iter.index}'/>
        <form:select path='referralItems[${iter.index}].referredTestId'
                onchange='markModified("${iter.index}");updateResultField("${iter.index}");'
                id='testSelection_${iter.index}' 
                class="required">
            <option value='0'></option>
			<form:options items="${referralItems.testSelectionList}" itemValue="id" itemLabel="value"/>
        </form:select>
    </td>
    <td style="text-align:center">
        <input type="checkbox" id="canceled_${iter.index}" onchange='setReferralStatus("${iter.index}");markModified("${iter.index}");'/>
    </td>
    <td class="leftVertical" id='resultCell_${iter.index}' style="text-align:center">
    	<c:set var="referredResultType" value="${referralItems.referredResultType}"/>
        <c:set var="referredResultAvailable" value="${not empty referralItems.referredTestId}" />
        <c:if test="${referredResultAvailable}"> 
	        <div class='resultCell_${iter.index}'>
	        	<c:if test="${'N' == referredResultType || 'A' == referredResultType || 'R' == referredResultType}">
		            <form:input path="referralItems[${iter.index}].referredResult"
		                   class='referralResult_${iter.index}'
		                   onchange='markModified("${iter.index}");'
		                   id='numericResult_${iter.index}'
		                    />
		        </c:if> 
		        <c:if test="${'D' == referredResultType}" > 
	            	<form:select path='referralItems[${iter.index}].referredDictionaryResult'
	                    class='referralResult_${iter.index}'
	                    id='dictionaryResult_${iter.index}'
	                    onchange='markModified("${iter.index}");'
	                    >
	                	<option value="0"></option>
	                	<form:options items="${referralItems.dictionaryResults}" itemValue="id" itemLabel="value"/>
	           		</form:select>
	            </c:if>
	            <c:if test="${'M' == referredResultType}"> 
	            	<form:select path='referralItems[${iter.index}].referredDictionaryResult'
	                    id='MultiSelect_${iter.index}'
	                    class='referralResult_${iter.index}'
	                    onchange='markModified("${iter.index}");'
	                    multiple="multiple"
	                    >
	                   	<form:options items="${referralItems.dictionaryResults}" itemValue="id" itemLabel="value"/>
	            	</form:select>
	           		<form:hidden path="referralItems[${iter.index}].multiSelectResultValues"
	                         id='multiresultId_${iter.index}' cssClass="multiSelectValues"/>
	            	<form:hidden path="referralItems[${iter.index}].referredMultiDictionaryResult"
	                         id='resultMultiSelect_${iter.index}'/>
	            </c:if>
	            <c:if test="${'C' == referredResultType}"> 
	            	<div id='cascadingMulti_${iter.index}_0'
	                 	class='cascadingMulti_${iter.index} referralResult_${iter.index}'>
		                <form:hidden path="referralItems[${iter.index}].multiSelectResultValues"
		                             id='multiresultId_${iter.index}' cssClass="multiSelectValues"/>
		                <c:if test="${referralItems.dictionaryResults}">
		                    <input type="hidden" id='divCount_${iter.index}' value="0">
		                    <form:select path="testResult[${iter.index}].multiSelectResultValues"
		                     id='resultId_${iter.index}_0'
		                     multiple="multiple"
		                     onchange='markModified("${iter.index}");'
		                    >
		                    	<form:options items="${referralItems.dictionaryResults}" itemValue="id" itemLabel="value" />
		                    </form:select>
		                    <input class='addMultiSelect${iter.index}' type="button" value="+"
		                           onclick='addNewMultiSelect(${iter.index}, this);'/>
		                    <input class='removeMultiSelect${iter.index}' type="button" value="-"
		                           onclick="removeMultiSelect('target');" style="visibility: hidden"/>
		                    <form:input path='testResult[${iter.index}].qualifiedResultValue'
		                           id='qualifiedDict_${iter.index}'
		                           style='display:none'
		                           onchange='markModified("${iter.index}");'
		                            />
		                </c:if>
	           	 	</div>
	            </c:if>
	        </div>
        </c:if>
        
    </td>
    <td style="text-align:center">
        <div class='resultCell_${iter.index}'>
            <c:if test="${referralItems.referredTestId != null}">
	            <form:input path='referralItems[${iter.index}].referredReportDate'
	                       cssClass='referralResult_${iter.index}'
	                       size="8"
	                       maxlength="10"
	                       onchange='markModified("${iter.index}");  validateDateFormat(this);'
	                       id='reportDate_${iter.index}'/>
            </c:if>
        </div>
    </td>
    <td style="text-align:center">
        <div style="display: ${referredResultAvailable ? 'block' : 'none'}">
	            	<form:hidden path='referralItems[${iter.index}].referralStatus'
		                           id='referralStatus_${iter.index}'/>
	            	<input type="checkbox" class="saveToggle" id="finished_${iter.index}" onchange='setReferralStatus("${iter.index}");markModified("${iter.index}");' value="${iter.index}" />
        </div>
    </td>
</tr>

<c:forEach items="${referralItems.additionalTests}" var="additionalTests" varStatus="test_iter" >
        <tr class='${rowColor}'>
        	<c:set var="referredResultType" value="${additionalTests.referredResultType}"/>
            <td colspan="4" style='text-align: right'>
                <form:hidden path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referralResultId'/>
                <form:hidden path="referralItems[${iter.index}].additionalTests[${test_iter.index}].referredResultType" 
                	id='referredType_${iter.index}_${test_iter.index}' />
                <label for='remove${iter.index}_${test_iter.index}'><spring:message code="label.button.remove"/></label>
                <form:checkbox path='referralItems[${iter.index}].additionalTests[${test_iter.index}].remove'
                       id='remove${iter.index}_${test_iter.index}'
                       onchange='markModified("${iter.index}");'/>
            </td>
            <td>
                <input type="hidden" value='${additionalTests.referredTestId}'
                       id='shadowReferredTest_${iter.index}_${test_iter.index}'/>
                <form:select path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referredTestId'
                        onchange='markModified("${iter.index}"); updateResultField("${iter.index}_${test_iter.index}");'
                        id='testSelection_${iter.index}_${test_iter.index}' class="required">
                    <option value='0'></option>
                    <form:options items="${referralItems.testSelectionList}" itemValue="id" itemLabel="value"/>
                </form:select>
            </td>
            <td>&nbsp;</td>
            <td class="leftVertical">
                <div class='resultCell_${iter.index}_${test_iter.index}'>
                	<c:choose>
                    <c:when test="${'N' == referredResultType || 'A' == referredResultType || 'R' == referredResultType}">
                    <form:input path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referredResult'
                           onchange='markModified("${iter.index}");'
                           class='referralResult_${iter.index}_${test_iter.index}>'
                           id='numericResult_${iter.index}_${test_iter.index}'
                            />
                    </c:when><c:when test="${'D' == additionalTests.referredResultType}"> 
                    <form:select path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referredDictionaryResult'
                            id='dictionaryResult_${iter.index}_${test_iter.index}'
                            onchange='markModified(${iter.index});'
                            class='referralResult_${iter.index}_${test_iter.index}' >
                    	<option value="0"></option>
                    	<form:options items="${additionalTests.dictionaryResults}" itemValue="id" itemLabel="value"/>
                    </form:select>
                    </c:when><c:when test="${'M' == additionalTests.referredResultType}">
                    <form:hidden path="referralItems[${iter.index}].additionalTests[${test_iter.index}].multiSelectResultValues"
                           class="multiSelectValues"
                           id="multiresultId_${iter.index}-${test_iter.index}"
                           />
                    <form:select path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referredDictionaryResult'
                            id='MultiSelect_${iter.index}_${test_iter.index}'
                            onchange='markModified("${iter.index}");'
                            class='referralResult_${iter.index}_${test_iter.index}'
                            multiple="multiple"
                            >
                            	<form:options items="${additionalTests.dictionaryResults}" itemValue="id" itemLabel="value"/>
                    </form:select>
                    <form:hidden path="referralItems[${iter.index}].additionalTests[${test_iter.index}].referredMultiDictionaryResult"
                            id='resultMultiSelect_${iter.index}_${test_iter.index}'
               				/>
                    </c:when><c:when test="${'C'== referredResultType}">
                    <div id='cascadingMulti_${iter.index}-${test_iter.index}_0'
                         class='cascadingMulti_${iter.index}-${test_iter.index} referralResult_${iter.index}-${test_iter.index}'>
                        <form:hidden path="referralItems[${iter.index}].additionalTests[${test_iter.index}].multiSelectResultValues"
                               class="multiSelectValues"
                               id="multiresultId_${iter.index}-${test_iter.index}" />
                        <input type="hidden" id='"divCount_${iter.index}-${test_iter.index}' value="0">
                        <form:select path="testResult[${iter.index}].multiSelectResultValues"
                                id='resultId_${iter.index}-${test_iter.index}_0'
                                multiple="multiple"
                                onchange='markModified("${iter.index}");'
                                        >
                                <form:options items="${additionalTests.dictionaryResults}" itemValue="id" itemLabel="value" />
                        </form:select>
                        <input class='addMultiSelect${iter.index}-${test_iter.index}' type="button" value="+"
                               onclick="addNewMultiSelect('${iter.index}-${test_iter.index}', this);"/>
                        <input class='removeMultiSelect${iter.index}-${test_iter.index}' type="button" value="-"
                               onclick="removeMultiSelect('target');" style="visibility: hidden"/>

                        <form:input path='testResult[${iter.index}].qualifiedResultValue'
                               id='qualifiedDict_${iter.index}'
                               style='display:none'
                               onchange='markModified("${iter.index}");'
                                />
                    </div>
                    </c:when>
                    </c:choose>
                </div>
            </td>
            <td>
                <div class='resultCell_${iter.index}_${test_iter.index}'>
                    <form:input path='referralItems[${iter.index}].additionalTests[${test_iter.index}].referredReportDate'
                           class='referralResult_${iter.index}_${test_iter.index}'
                           onchange='markModified("${iter.index}");  validateDateFormat(this);'
                           size="8"
                           maxlength="10"/>
                </div>
            </td>
        </tr>
</c:forEach>
<tr class='${rowColor}'>
    <td colspan="3"></td>
    <td colspan='1'>
    	<spring:message code="referral.addTest" var="addTest"/>
        <button type="button"
               class="textButton"
               onclick='insertNewTestRequest(this,${iter.index});'>${addTest}</button>
    </td>
    <td colspan='2' align="right" style="padding:5px">
        <img src="./images/note-add.gif"
             onclick='showHideNotes(${iter.index});'
             id='showHideButton_${iter.index}'
                />
        <input type="hidden" id='hideShow_${iter.index}' value="hidden"/>
    </td>
    <td colspan='3' class="leftVertical">&nbsp</td>
</tr>
<c:if test="${not empty referralItems.pastNotes}">
    <tr class='${rowColor}'>
        <td valign="top" align="right"><spring:message code="label.prior.note" />:</td>
        <td colspan="2" align="left">
<%--         	pastNotes are escaped in an html context when they are fetched by the server before --%>
<%--         	safe html tags are added in the controller, so this does not need to be escaped here as well --%>
            ${referralItems.pastNotes}
        </td>
        <td colspan="3" align="left" valign="top">
        	<span id='noteRow_${iter.index}' style="display: none;">
        	<spring:message code="note.note"/>:
        	<form:textarea path="referralItems[${iter.index}].note"
        			   id='note_${iter.index}'
                       onchange='markModified("${iter.index}");'
                       cols="80"
                       rows="3"/>
            </span>
        </td>
        <td colspan='3' class="leftVertical">
    </tr>
</c:if>
<c:if test="${empty referralItems.pastNotes}">
<tr id='noteRow_${iter.index}' 
	style="display: none;"
    class='${rowColor}'
    >
    <td valign="top" align="right">
        	<spring:message code="note.note"/>:
    </td>
    <td colspan="6" align="left">
    	<form:textarea path="referralItems[${iter.index}].note"
        			   id='note_${iter.index}'
                       onchange='markModified("${iter.index}");'
                       cols="80"
                       rows="3"/>
    </td>
</tr>
</c:if>
</c:forEach>
</table>
</c:if>
<c:if test="${empty form.referralItems}">
    <h2><spring:message code="referral.noReferralItems"/></h2>
</c:if>

<script type="text/javascript">
    var dirty = false;
    //all methods here either overwrite methods in tiles or all called after they are loaded

    function /*void*/ makeDirty() {
        dirty = true;
        if (typeof(showSuccessMessage) === 'function') {
            showSuccessMessage(false); //refers to last save
        }
        // Adds warning when leaving page if content has been entered into makeDirty form fields
        function formWarning() {
            return "<spring:message code="banner.menu.dataLossWarning"/>";
        }

        window.onbeforeunload = formWarning;
    }

</script>
