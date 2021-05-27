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
    
//     jQuery('.saveToggle').each(function() {
//     	checkFinish(jQuery(this).val());
//     });
    
   jQuery("#addTestTable").on("click", referralTestSelected);
});

function referralTestSelected(e) {
	var testTable = $("addTestTable");
	var chosenTests = [];
	var chosenIds = [];
	var i, row,nameNode;
	var displayTests = "";
	var testIds = "";
	
	var inputs = testTable.getElementsByClassName("testCheckbox");

    jQuery("#testSelection_0").find('option').remove();
    jQuery("#testSelection_0").append(jQuery('<option/>', {
    	value: 0,
    	text: ""
    }));
	for( i = 0; i < inputs.length; i++ ){
		if( inputs[i].checked ){
            row = inputs[i].id.split("_")[1];
            nameNode = $("testName_" + row);
            chosenTest = nameNode.nextSibling.nodeValue;
            chosenId = nameNode.value;
            
            jQuery("#testSelection_0").append(jQuery('<option/>', {
            	value: chosenId,
            	text: chosenTest
            }));
            if ( jQuery("#testSelection_0").val() === "0") {
                jQuery("#testSelection_0").val(chosenId);
            }
		}
	}
}


function /*void*/ markModified(index) {
	checkFinish(index);
	jQuery("#modified_" + index).val('true');
	jQuery("#saveButtonId").prop('disabled', missingRequiredValues());
    makeDirty();
}

function missingRequiredReferralValues() {
    var missing = false;

    jQuery(".requiredRow").each(function (index, element) {
        var children = jQuery(element).find(".requiredReferral");
        if (!((children[1].value == 0 && children[0].value == 0) ||
                (children[1].value != 0 && children[0].value != 0))) {
            missing = true;
        }
    });

    return missing;

}

function /*void*/ validateDateFormat(dateElement) {
    var valid = true;
    if (dateElement.value.length > 0) {
        var dateRegEx = new RegExp("\\d{2}/\\d{2}/\\d{4}");
        valid = dateRegEx.test(dateElement.value);
    }

    dateElement.style.borderColor = valid ? "" : "red";
}

// function /*void*/ savePage() {
//     setXMLWads();

//     window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
//     var form = document.getElementById("mainForm");
//     form.action = "ReferredOutTests.do";
//     form.submit();
// }

// function  /*void*/ setMyCancelAction(form, action, validate, parameters) {
//     //first turn off any further validation
//     setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');
// }

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

<table width="75%" border="0" cellspacing="0" cellpadding="1" id="mainTable">
<tr>
    <th colspan="6" class="headerGroup"><spring:message code="referral.header.group.request"/></th>
</tr>
<tr>
    <th><spring:message code="referral.reason"/><span class="requiredlabel">*</span></th>
    <th><spring:message code="referral.referer"/></th>
    <th><spring:message code="referral.institute"/><span class="requiredlabel">*</span></th>
    <th><spring:message code="referral.sent.date"/><br/><%=DateUtil.getDateUserPrompt()%></th>
    <th><spring:message code="test.testName"/><span class="requiredlabel">*</span></th>
</tr>

<c:forEach items="${form.referralItems}" var="referralItems" varStatus="iter">
<form:hidden id='referralResultId_${iter.index}' path="referralItems[${iter.index}].referralResultId" />
<form:hidden id='referralId_${iter.index}' path="referralItems[${iter.index}].referralId" indexed="true" />
<form:hidden id='referredType_${iter.index}' path="referralItems[${iter.index}].referredResultType" />
<form:hidden id='modified_${iter.index}' path="referralItems[${iter.index}].modified" />
<form:hidden id='causalResultId_${iter.index}' path="referralItems[${iter.index}].inLabResultId" />
<c:set var="rowColor" value="${(iter.index % 2 == 0) ? 'oddRow' :'evenRow'}"/>

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
                class="requiredReferral"
                onchange='markModified("${iter.index}");'>
                <c:if test="${form.referralOrganizations.size() != 1}" >
            	<option value='0'></option>
                </c:if>
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
                onchange='markModified("${iter.index}");'
                id='testSelection_${iter.index}' 
                class="requiredReferral">
            <option value='0'></option>
			<form:options items="${referralItems.testSelectionList}" itemValue="id" itemLabel="value"/>
        </form:select>
    </td>
</tr>

</c:forEach>
</table>
</c:if>
<c:if test="${empty form.referralItems}">
    <h2><spring:message code="referral.noReferralItems"/></h2>
</c:if>

