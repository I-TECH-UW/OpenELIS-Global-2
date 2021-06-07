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
	var index = 0;
	jQuery('#mainTable .referralRow').addClass('deleteReferralRow');
	var samples = jQuery('#samplesAddedTable .sampleId');
	samples.each(function(index, value) {
		var sampleNum = jQuery(this).val();
		var testNames = document.getElementById("tests_" + sampleNum).value.split(",");
		var testIds = document.getElementById("testIds_" + sampleNum).value.split(",");
		for (var j = 0; j < testIds.length; ++j) {
			if (testIds[j] !== "") {
				createReferralOption(sampleNum, testIds[j], testIds[j], testNames[j], index++);
			}
		}
	});
	jQuery('.deleteReferralRow').remove();
}

function /*void*/ markModified(index) {
	checkFinish(index);
	jQuery("#modified_" + index).val('true');
	setSave();
    makeDirty();
}

function missingRequiredReferralValues() {
    var missing = false;

    jQuery(".referralRow").each(function (index, element) {
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

function createReferralOption(sampleNum, testNum, testId, testName, index) {
	var table, tableBody, row, cell1, cell2, cell3, cell4, cell5;
	var select, option;
	
	table = document.getElementById("mainTable");
	tableBody = table.getElementsByTagName('tbody')[0];
	
	if (jQuery('#sample_' + sampleNum + '_test_' + testNum).length) {
		document.getElementById('sample_' + sampleNum + '_test_' + testNum).className = "referralRow";
		return;
	}
	
	row = tableBody.insertRow(-1);
	row.setAttribute('id', 'sample_' + sampleNum + '_test_' + testNum);
	row.setAttribute('class', 'referralRow');
	cell1 = row.insertCell(-1);
	cell2 = row.insertCell(-1);
	cell3 = row.insertCell(-1);
	cell4 = row.insertCell(-1);
	cell5 = row.insertCell(-1);

	cell1.className = "center-text";
	cell2.className = "center-text";
	cell3.className = "center-text";
	cell4.className = "center-text";
	cell5.className = "center-text";
	
	var referralReasonSelect = document.createElement("select");
	referralReasonSelect.setAttribute('name','referralItems[' + index + '].referralReasonId');
	referralReasonSelect.setAttribute('id', 'referralReasonId_' + index);
	referralReasonSelect.setAttribute('onchange', 'markModified("' + index + '");');
	<c:forEach items="${form.referralReasons}" var="referralReason" varStatus="iter">
	option = document.createElement('option');
	option.value = '${referralReason.id}';
	option.innerHTML = '${referralReason.value}';
	referralReasonSelect.appendChild(option);
	</c:forEach>
	cell1.appendChild(referralReasonSelect);

	var referrerInput = document.createElement("input");
	referrerInput.setAttribute('type','text');
	referrerInput.setAttribute('name','referralItems[' + index + '].referrer');
	referrerInput.setAttribute('id', 'referrer_' + index);
	referrerInput.setAttribute('onchange', 'markModified("' + index + '");');
	referrerInput.setAttribute('value', '${userSessionData.elisUserName}');
	cell2.appendChild(referrerInput);
	
	var referralOrgSelect = document.createElement("select");
	referralOrgSelect.setAttribute('class','requiredReferral');
	referralOrgSelect.setAttribute('name','referralItems[' + index + '].referredInstituteId');
	referralOrgSelect.setAttribute('id', 'referredInstituteId_' + index);
	referralOrgSelect.setAttribute('onchange', 'markModified("' + index + '");');
    <c:if test="${form.referralOrganizations.size() != 1}" >
    option = document.createElement('option');
	option.value = '0';
	referralOrgSelect.appendChild(option);
    </c:if>
	<c:forEach items="${form.referralOrganizations}" var="referralOrganization" varStatus="iter">
	option = document.createElement('option');
	option.value = '${referralOrganization.id}';
	option.innerHTML = '${referralOrganization.value}';
	referralOrgSelect.appendChild(option);
	</c:forEach>
	cell3.appendChild(referralOrgSelect);

	var referrerInput = document.createElement("input");
	referrerInput.setAttribute('type','text');
	referrerInput.setAttribute('name','referralItems[' + index + '].referredSendDate');
	referrerInput.setAttribute('id', 'sendDate_' + index);
	referrerInput.setAttribute('size', '8');
	referrerInput.setAttribute('maxLength', '10');
	referrerInput.setAttribute('onchange', 'markModified("' + index + '");');
	cell4.appendChild(referrerInput);

	var shadowReferredTestInput = document.createElement("hidden");
// 	shadowReferredTestInput.setAttribute('type','hidden');
	shadowReferredTestInput.setAttribute('name','_referralItems[' + index + '].referredTestId');
	shadowReferredTestInput.setAttribute('id', 'shadowReferredTest_' + index);
	cell5.appendChild(shadowReferredTestInput);
	
	var referredTestSelect = document.createElement("select");
	referredTestSelect.setAttribute('class','requiredReferral');
	referredTestSelect.setAttribute('name','referralItems[' + index + '].referredTestId');
	referredTestSelect.setAttribute('id', 'testSelection_' + index);
	referredTestSelect.setAttribute('onchange', 'markModified("' + index + '");');
	option = document.createElement('option');
	option.value = 0;
	referredTestSelect.appendChild(option);
	option = document.createElement('option');
	option.value = testId;
	option.innerHTML = testName;
	referredTestSelect.appendChild(option);
	cell5.appendChild(referredTestSelect);
}

</script>

<c:if test="${not empty form.referralItems}">

<table width="75%" border="0" cellspacing="0" cellpadding="1" id="mainTable">
<thead>
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
</thead>
<tbody>
</tbody>
</table>
</c:if>
<c:if test="${empty form.referralItems}">
    <h2><spring:message code="referral.noReferralItems"/></h2>
</c:if>

