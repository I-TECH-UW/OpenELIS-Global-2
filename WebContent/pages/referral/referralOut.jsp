<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.DateUtil,
                 us.mn.state.health.lims.common.util.StringUtil,
                 java.util.List,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.referral.action.beanitems.ReferralItem,
                 us.mn.state.health.lims.common.util.IdValuePair,
                 us.mn.state.health.lims.referral.action.beanitems.ReferredTest" %>


<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%!
    String basePath = "";
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>'/>

<script type="text/javascript" src="<%=basePath%>scripts/jquery.ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript"
        src="<%=basePath%>scripts/jquery.asmselect.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/testReflex.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript"
        src="<%=basePath%>scripts/multiselectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<link rel="stylesheet" type="text/css"
      href="<%=basePath%>css/jquery.asmselect.css?ver=<%= Versioning.getBuildNumber() %>"/>
<script type="text/javascript">

$jq(document).ready(function () {
    loadMultiSelects();
    $jq("select[multiple]:visible").asmSelect({
        removeLabel: "X"
        // , debugMode: true
    });

    $jq("select[multiple]").change(function (e, data) {
        handleMultiSelectChange(e, data);
    });

    $jq(".asmContainer").css("display", "inline-block");
});


function /*void*/ markModified(index) {
    $("modified_" + index).value = true;
    $("saveButtonId").disabled = missingRequiredValues();
    makeDirty();
}

function missingRequiredValues() {
    var missing = false;

    $jq(".requiredRow").each(function (index, element) {
        var children = $jq(element).find(".required");
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
        $jq(".resultCell_" + index).show();
    } else {
        $jq(".resultCell_" + index).hide();
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

    newRow.insertCell(1).innerHTML = "<input type=\"button\"  name=\"remove\"  value=\"" + '<%= StringUtil.getMessageForKey("label.button.remove")%>' + "\" onclick=\"removeRow('" + newRowMarker + "');\" class=\"textButton\"  >";

    cell = newRow.appendChild($("testSelection_" + index).parentNode.cloneNode(true));
    var selectionNode = cell.getElementsByTagName("select")[0];
    selectionNode.id = "testSelection_" + newRowMarker;
    selectionNode.selectedIndex = 0;
    selectionNode.onchange = function () {
        markModified(index + "");
    }; //blank "" force to string

    newRow.insertCell(3);
    cell = newRow.insertCell(4);
    cell.colSpan = 2;
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
    var form = window.document.forms[0];
    form.action = '<%=formName%>'.sub('Form', '') + "Update.do";
    form.submit();
}
function  /*void*/ setMyCancelAction(form, action, validate, parameters) {
    //first turn off any further validation
    setAction(window.document.forms[0], 'Cancel', 'no', '');
}
</script>

<logic:notEmpty name="<%=formName%>" property="referralItems">

<table width="100%" border="0" cellspacing="0" cellpadding="1" id="mainTable">
<tr>
    <th colspan="6" class="headerGroup"><bean:message key="referral.header.group.request"/></th>
    <th colspan="2" class="leftVertical headerGroup"><bean:message key="referral.header.group.results"/></th>
</tr>
<tr>
    <th><bean:message key="referral.reason"/><span class="requiredlabel">*</span></th>
    <th><bean:message key="referral.referer"/></th>
    <th><bean:message key="referral.institute"/><span class="requiredlabel">*</span></th>
    <th><bean:message key="referral.sent.date"/><br/><%=DateUtil.getDateUserPrompt()%></th>
    <th><bean:message key="test.testName"/><span class="requiredlabel">*</span></th>
    <th width="5%"><bean:message key="label.button.cancel.referral"/></th>
    <th width="15%" class="leftVertical"><bean:message key="result.result"/></th>
    <th><bean:message key="referral.report.date"/><br/><%=DateUtil.getDateUserPrompt()%></th>
</tr>

<logic:iterate id="referralItems" name="<%=formName%>" property="referralItems" indexId="index" type="ReferralItem">
<html:hidden styleId='<%= "textXML_" + index %>' name="referralItems" property="additionalTestsXMLWad" indexed="true"
             styleClass="XMLWad"/>
<html:hidden styleId='<%= "referralResultId_" + index %>' name="referralItems" property="referralResultId"
             indexed="true"/>
<html:hidden styleId='<%= "referralId_" + index %>' name="referralItems" property="referralId" indexed="true"/>
<html:hidden styleId='<%= "referredType_" + index %>' name="referralItems" property="referredResultType"
             indexed="true"/>
<html:hidden styleId='<%= "modified_" + index %>' name="referralItems" property="modified" indexed="true"/>
<html:hidden styleId='<%= "causalResultId_" + index %>' name="referralItems" property="inLabResultId" indexed="true"/>
<input type="hidden"
       value='<%= referralItems.getAdditionalTests() == null ? 0 : referralItems.getAdditionalTests().size()  %>'
       id='<%="addedRowCount_" + index%>'/>
<bean:define id="rowColor" value='<%=(index % 2 == 0) ? "oddRow" : "evenRow" %>'/>

<tr class='<%=rowColor%>Head' id='<%="referralRow_" + index%>'>
    <td colspan="2" class="HeadSeperator">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= StringUtil.getContextualMessageForKey( "resultsentry.accessionNumber" ) %>:
        <b><bean:write name="referralItems" property="accessionNumber"/></b>
    </td>
    <td colspan="4" class="HeadSeperator">
        <bean:message key="referral.request.date"/>: <b><bean:write name="referralItems" property="referralDate"/></b>
    </td>
    <td colspan='2' class="HeadSeperator leftVertical">
</tr>
<tr class='<%=rowColor%>Head' id='<%="referralRow_" + index%>'>
    <td colspan="2">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="label.sampleType"/>: <b><bean:write name="referralItems"
                                                                                                    property="sampleType"/></b>
    </td>
    <td colspan="2">
        <bean:message key="test.testName"/>: <b><bean:write name="referralItems" property="referringTestName"/></b>
    </td>
    <td colspan="2">
        <bean:message key="result.original.result"/>: <b><bean:write name="referralItems"
                                                                     property="referralResults"/></b>
    </td>
    <td colspan="2" class="leftVertical">
        &nbsp;
    </td>
</tr>
<tr class='<%=rowColor + " requiredRow"%>' id='<%="referralRow_" + index%>'>
    <td>
        <select name="<%="referralItems[" + index + "].referralReasonId"%>"
                id='<%="referralReasonId_" + index%>'
                onchange='<%="markModified(\"" + index + "\"); " %>'>
            <logic:iterate id="optionValue" name='<%=formName %>' property="referralReasons" type="IdValuePair">
                <option value='<%=optionValue.getId()%>'  <%
                    if( optionValue.getId().equals( referralItems.getReferralReasonId() ) )
                        out.print( "selected" );
                %>  >
                    <bean:write name="optionValue" property="value"/>
                </option>
            </logic:iterate>
        </select>
    </td>
    <td>
        <html:text name="referralItems"
                   property="referrer"
                   onchange='<%="markModified(\'" + index + "\'); " %>'
                   indexed="true"/>
    </td>
    <td>
        <select name='<%="referralItems[" + index + "].referredInstituteId"%>'
                class="required"
                onchange='<%="markModified(\"" + index + "\");"%>'>
            <logic:iterate id="optionValue" name='<%=formName %>' property="referralOrganizations" type="IdValuePair">
                <option value='<%=optionValue.getId()%>' <%
                    if( optionValue.getId().equals( referralItems.getReferredInstituteId() ) )
                        out.print( "selected" );
                %>   >
                    <bean:write name="optionValue" property="value"/>
                </option>
            </logic:iterate>
        </select>
    </td>
    <td>
        <html:text name='referralItems'
                   property="referredSendDate"
                   indexed="true"
                   size="8"
                   maxlength="10"
                   onchange='<%="markModified(\'" + index + "\');  validateDateFormat(this);"%>'
                   styleId='<%="sendDate_" + index %>'/>
    </td>
    <td>
        <html:hidden name="referralItems" property="referredTestId" indexed="false"
                     styleId='<%="shadowReferredTest_" + index %>'/>
        <select name='<%="referralItems[" + index + "].referredTestId"%>'
                onchange='<%="markModified(\"" + index + "\"); updateResultField(\"" + index + "\");"%>'
                id='<%="testSelection_" + index%>' class="required">
            <option value='0'></option>

            <logic:iterate id="optionValue" name='referralItems' property="testSelectionList" type="IdValuePair">
                <option value='<%=optionValue.getId()%>' <%
                    if( optionValue.getId().equals( referralItems.getReferredTestId() ) ) out.print( "selected" );%>   >
                    <bean:write name="optionValue" property="value"/>
                </option>
            </logic:iterate>
        </select>
    </td>
    <td>
        <html:checkbox name="referralItems" property="canceled"
                       onchange='<%="markModified(\'" + index + "\'); value=true" %>' indexed="true"/>
    </td>
    <td class="leftVertical"
        id='<%="resultCell_" + index %>'><% String referredResultType = referralItems.getReferredResultType(); %>
        <% if( referralItems.getReferredTestId() != null ){ %>
        <div class='<%="resultCell_" + index%>'>
            <% if( "N".equals( referredResultType ) || "A".equals( referredResultType ) || "R".equals( referredResultType ) ){ %>
            <input type="text"
                   class='<%="referralResult_" + index %>'
                   name='<%= "referralItems[" + index + "].referredResult" %>'
                   value='<%= referralItems.getReferredResult() %>'
                   onchange='<%= "markModified(\"" + index + "\");" %>'
                   id='<%= "numericResult_" + index %>'
                    />
            <% }else if( "D".equals( referredResultType ) ){ %>
            <select name='<%= "referralItems[" + index + "].referredDictionaryResult" %>'
                    class='<%="referralResult_" + index %>'
                    id='<%= "dictionaryResult_" + index %>'
                    onchange='<%="markModified(\"" + index + "\"); " %>'
                    >
                <option value="0"></option>
                <logic:notEmpty name="referralItems" property="dictionaryResults">
                    <logic:iterate id="optionValue" name="referralItems" property="dictionaryResults"
                                   type="IdValuePair">
                        <option value='<%=optionValue.getId()%>'  <%
                            if( optionValue.getId().equals( referralItems.getReferredDictionaryResult() ) )
                                out.print( "selected" );
                        %>  >
                            <bean:write name="optionValue" property="value"/>
                        </option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
            <% }else if( "M".equals( referredResultType ) ){ %>
            <select name='<%= "referralItems[" + index + "].referredDictionaryResult" %>'
                    id='<%= "MultiSelect_" + index %>'
                    class='<%="referralResult_" + index %>'
                    onchange='<%="markModified(\"" + index + "\"); " %>'
                    multiple="multiple"
                    title='<%= StringUtil.getMessageForKey("result.multiple_select")%>'
                    >
                <logic:notEmpty name="referralItems" property="dictionaryResults">
                    <logic:iterate id="optionValue" name="referralItems" property="dictionaryResults"
                                   type="IdValuePair">
                        <option value='<%=optionValue.getId()%>'
                                <%
                                    if( StringUtil.textInCommaSeperatedValues( optionValue.getId(), referralItems.getReferredMultiDictionaryResult() ) )
                                        out.print( "selected" );
                                %>
                                >
                            <bean:write name="optionValue" property="value"/>
                        </option>
                    </logic:iterate>
                </logic:notEmpty>
            </select>
            <html:hidden name="referralItems" property="multiSelectResultValues" indexed="true"
                         styleId='<%="multiresultId_" + index%>' styleClass="multiSelectValues"/>
            <html:hidden name="referralItems" indexed="true" property="referredMultiDictionaryResult"
                         styleId='<%= "resultMultiSelect_" + index %>'/>
            <% }else if( "C".equals( referredResultType ) ){ %>
            <div id='<%="cascadingMulti_" + index + "_0"%>'
                 class='<%="cascadingMulti_" + index + " referralResult_" + index %>'>
                <html:hidden name="referralItems" property="multiSelectResultValues" indexed="true"
                             styleId='<%="multiresultId_" + index%>' styleClass="multiSelectValues"/>
                <logic:present name="referralItems" property="dictionaryResults">
                    <input type="hidden" id='<%="divCount_" + index %>' value="0">
                    <select name="<%="testResult[" + index + "].multiSelectResultValues" %>"
                    id='<%="resultId_" + index + "_0"%>'
                    multiple="multiple"
                    title='<%= StringUtil.getMessageForKey( "result.multiple_select" )%>'
                    onchange='<%="markModified(\"" + index + "\"); " %>
                    >
                    <logic:iterate id="optionValue" name="referralItems" property="dictionaryResults"
                                   type="IdValuePair">
                        <option value='<%=optionValue.getId()%>' >
                        <bean:write name="optionValue" property="value"/>
                        </option>
                    </logic:iterate>
                    </select>
                    <input class='<%="addMultiSelect" + index%>' type="button" value="+"
                           onclick='<%="addNewMultiSelect(" + index + ", this);"%>'/>
                    <input class='<%="removeMultiSelect" + index%>' type="button" value="-"
                           onclick="removeMultiSelect('target');" style="visibility: hidden"/>
                    <input type="text"
                           name='<%="testResult[" + index + "].qualifiedResultValue" %>'
                           value='<%= "testResult.getQualifiedResultValue()" %>'
                           id='<%= "qualifiedDict_" + index %>'
                           style='<%= "display:" + ( false ? "inline" : "none") %>'
                           onchange='<%="markModified(\"" + index + "\");" %>'
                            />
                </logic:present>
            </div>
            <% } %>
        </div>
        <%} %>
    </td>
    <td>
        <div class='<%="resultCell_" + index%>'>
            <% if( referralItems.getReferredTestId() != null ){ %>
            <html:text name='referralItems'
                       property="referredReportDate"
                       styleClass='<%="referralResult_" + index %>'
                       indexed="true"
                       size="8"
                       maxlength="10"
                       onchange='<%="markModified(\'" + index + "\');  validateDateFormat(this);" %>'
                       styleId='<%="reportDate_" + index %>'/>
            <% } %>
        </div>
    </td>
</tr>
<logic:notEmpty name="referralItems" property="additionalTests">
    <logic:iterate id="additionalTests" name="referralItems" property="additionalTests" indexId="testIndex"
                   type="ReferredTest">
        <tr class='<%= rowColor %>'><% referredResultType = additionalTests.getReferredResultType(); %>
            <td colspan="4" style='text-align: right'>
                <input type="hidden"
                       name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].referralResultId" %>'
                       value='<%=additionalTests.getReferralResultId() %>'/>
                <input type="hidden"
                       id='<%="referredType_" + index + "_" + testIndex %>'
                       name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].referredResultType" %>'
                       value='<%=additionalTests.getReferredResultType() %>'/>
                <label for='<%="remove" + index + "_" + testIndex %>'><bean:message key="label.button.remove"/></label>
                <input type="checkbox"
                       name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].remove" %>'
                       id='<%="remove" + index + "_" + testIndex %>'
                       onchange='<%="markModified(\"" + index + "\");" %>'>
            </td>
            <td>
                <input type="hidden" value='<%= additionalTests.getReferredTestId() %>'
                       id='<%="shadowReferredTest_" + index + "_" + testIndex%>'/>
                <select name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].referredTestId" %>'
                        onchange='<%="markModified(\"" + index + "\"); updateResultField(\"" + index + "_" + testIndex + "\");" %>'
                        id='<%="testSelection_" + index + "_" + testIndex %>' class="required">
                    <option value='0'></option>
                    <logic:iterate id="optionValue" name='referralItems' property="testSelectionList"
                                   type="IdValuePair">
                        <option value='<%=optionValue.getId()%>' <%
                            if( optionValue.getId().equals( additionalTests.getReferredTestId() ) )
                                out.print( "selected" );
                        %>  >
                            <bean:write name="optionValue" property="value"/>
                        </option>
                    </logic:iterate>
                </select>
            </td>
            <td>&nbsp;</td>
            <td class="leftVertical">
                <div class='<%="resultCell_" + index + "_" + testIndex%>'>
                    <% if( "N".equals( referredResultType ) || "A".equals( referredResultType ) || "R".equals( referredResultType ) ){ %>
                    <input type="text"
                           name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].referredResult" %>'
                           value='<%= additionalTests.getReferredResult() %>'
                           onchange='<%="markModified(\"" + index + "\");" %>'
                           class='<%="referralResult_" + index + "_" + testIndex%>'
                           id='<%="numericResult_" + index + "_" + testIndex %>'
                            />
                    <% }else if( "D".equals( additionalTests.getReferredResultType() ) ){%>
                    <select name='<%= "referralItems[" + index + "].additionalTests[" + testIndex + "].referredDictionaryResult" %>'
                            id='<%= "dictionaryResult_" + index + "_" + testIndex  %>'
                            onchange='<%="markModified(" + index + ");" %>'
                            class='<%="referralResult_" + index + "_" + testIndex%>' >
                    <option value="0"></option>
                    <logic:notEmpty name="additionalTests" property="dictionaryResults">
                        <logic:iterate id="optionValue" name="additionalTests" property="dictionaryResults"
                                       type="IdValuePair">
                            <option value='<%=optionValue.getId()%>'  <%
                                if( optionValue.getId().equals( additionalTests.getReferredDictionaryResult() ) )
                                    out.print( "selected" );
                            %>  >
                                <bean:write name="optionValue" property="value"/>
                            </option>
                        </logic:iterate>
                    </logic:notEmpty>
                    </select>
                    <% }else if( "M".equals( referredResultType ) ){ %>
                    <input type="hidden"
                           name="<%="referralItems["+ index + "].additionalTests[" + testIndex + "].multiSelectResultValues"%>"
                           class="multiSelectValues"
                           id="<%="multiresultId_" + index + "-" + testIndex %>"
                           value=''<%=additionalTests.getMultiSelectResultValues()%>'>
                    <select name='<%= "referralItems[" + index + "].additionalTests[" + testIndex + "].referredDictionaryResult" %>'
                            id='<%= "MultiSelect_" + index + "_" + testIndex %>'
                            onchange='<%="markModified(\"" + index + "\"); " %>'
                            class='<%="referralResult_" + index + "_" + testIndex%>'
                            multiple="multiple"
                            title='<%= StringUtil.getMessageForKey("result.multiple_select")%>'
                            >
                        <logic:notEmpty name="additionalTests" property="dictionaryResults">
                            <logic:iterate id="optionValue" name="additionalTests" property="dictionaryResults"
                                           type="IdValuePair">
                                <option value='<%=optionValue.getId()%>'
                                        <%
                                            if( StringUtil.textInCommaSeperatedValues( optionValue.getId(), additionalTests.getReferredMultiDictionaryResult() ) )
                                                out.print( "selected" );
                                        %> >
                                    <bean:write name="optionValue" property="value"/>
                                </option>
                            </logic:iterate>
                        </logic:notEmpty>
                    </select>
                    <input type="hidden" style="display: none"
                           id='<%="resultMultiSelect_" + index + "_" + testIndex %>'
                           name='<%= "referralItems[" + index + "].additionalTests[" + testIndex + "].referredMultiDictionaryResult" %>'/>
                    <% }else if( "C".equals( referredResultType ) ){ %>
                    <div id='<%="cascadingMulti_" + index + "-" + testIndex + "_0" %>'
                         class='<%="cascadingMulti_" + index  + "-" + testIndex + " referralResult_" + index + "-" + testIndex %>'>
                        <input type="hidden"
                               name="<%="referralItems["+ index + "].additionalTests[" + testIndex + "].multiSelectResultValues"%>"
                               class="multiSelectValues"
                               id="<%="multiresultId_" + index + "-" + testIndex %>"
                               value='<%=additionalTests.getMultiSelectResultValues()%>'>
                        <input type="hidden" id='<%="divCount_" + index + "-" + testIndex %>' value="0">
                        <select name="<%="testResult[" + index + "].multiSelectResultValues" %>"
                                id='<%="resultId_" + index + "-" + testIndex + "_" + 0%>'
                                multiple="multiple"
                                title='<%= StringUtil.getMessageForKey("result.multiple_select")%>'
                                onchange='<%="markModified(\"" + index + "\"); " %>
                                        >
                                <logic:iterate id="optionValue" name="additionalTests" property="dictionaryResults"  type="IdValuePair" >
                                        <option value='<%=optionValue.getId()%>' >
                        <bean:write name="optionValue" property="value"/>
                        </option>
                        </logic:iterate>
                        </select>
                        <input class='<%="addMultiSelect" + index + "-" + testIndex%>' type="button" value="+"
                               onclick="<%="addNewMultiSelect('" + index + "-" + testIndex + "', this);"%>"/>
                        <input class='<%="removeMultiSelect" + index + "-" + testIndex%>' type="button" value="-"
                               onclick="removeMultiSelect('target');" style="visibility: hidden"/>

                        <input type="text"
                               name='<%="testResult[" + index + "].qualifiedResultValue" %>'
                               value='<%= "testResult.getQualifiedResultValue()" %>'
                               id='<%= "qualifiedDict_" + index %>'
                               style='<%= "display:" + ( false ? "inline" : "none") %>'
                               onchange='<%="markModified(\"" + index + "\");" %>'
                                />
                    </div>
                    <% } %>
                </div>
            </td>
            <td>
                <div class='<%="resultCell_" + index + "_" + testIndex%>'>
                    <input type="text"
                           class='<%="referralResult_" + index + "_" + testIndex%>'
                           name='<%="referralItems[" + index + "].additionalTests[" + testIndex + "].referredReportDate" %>'
                           value='<%= additionalTests.getReferredReportDate() %>'
                           onchange='<%="markModified(\"" + index + "\");  validateDateFormat(this);" %>'
                           size="8"
                           maxlength="10"/>
                </div>
            </td>
        </tr>
    </logic:iterate>
</logic:notEmpty>
<tr class='<%= rowColor %>'>
    <td colspan="3"></td>
    <td colspan='1'>
        <input type="button"
               name="addRequest"
               value="<%= StringUtil.getMessageForKey("referral.addTest")%>"
               class="textButton"
               onclick='<%="insertNewTestRequest(this," + index + ");"  %>'>
    </td>
    <td colspan='2' align="right" style="padding:5px">
        <img src="./images/note-add.gif"
             onclick='<%= "showHideNotes(" + index + ");" %>'
             id='<%="showHideButton_" + index %>'
                />
        <input type="hidden" id='<%="hideShow_" + index %>' value="hidden"/>
    </td>
    <td colspan='2' class="leftVertical">&nbsp</td>
</tr>
<logic:notEmpty name="referralItems" property="pastNotes">
    <tr class='<%= rowColor %>'>
        <td valign="top" align="right"><bean:message key="label.prior.note" />:</td>
        <td colspan="5" align="left">
            <%= referralItems.getPastNotes() %>
        </td>
        <td colspan='2' class="leftVertical">
    </tr>
</logic:notEmpty>
<tr id='<%="noteRow_" + index %>'
    class='<%= rowColor %>'
    style="display: none;">
    <td valign="top" align="right"><bean:message key="note.note"/>:</td>
    <td colspan="6" align="left">
        <html:textarea styleId='<%="note_" + index %>'
                       onchange='<%="markModified(" + index + ");"%>'
                       name="referralItems"
                       property="note"
                       indexed="true"
                       cols="80"
                       rows="3"/>
    </td>
</tr>
</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="<%=formName%>" property="referralItems">
    <h2><bean:message key="referral.noReferralItems"/></h2>
</logic:empty>

<script type="text/javascript" language="JavaScript1.2">
    var dirty = false;
    //all methods here either overwrite methods in tiles or all called after they are loaded

    function /*void*/ makeDirty() {
        dirty = true;
        if (typeof(showSuccessMessage) != 'undefinded') {
            showSuccessMessage(false); //refers to last save
        }
        // Adds warning when leaving page if content has been entered into makeDirty form fields
        function formWarning() {
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }

        window.onbeforeunload = formWarning;
    }

</script>
