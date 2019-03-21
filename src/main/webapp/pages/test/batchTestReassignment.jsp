<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="java.util.List,
         		us.mn.state.health.lims.common.action.IActionConstants,
          		us.mn.state.health.lims.common.util.IdValuePair,
          		spring.mine.internationalization.MessageUtil,
         		us.mn.state.health.lims.common.util.Versioning,
         		us.mn.state.health.lims.test.action.BatchTestStatusChangeBean" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%--
  ~ The contents of this file are subject to the Mozilla Public License
  ~ Version 1.1 (the "License"); you may not use this file except in
  ~ compliance with the License. You may obtain a copy of the License at
  ~ http://www.mozilla.org/MPL/
  ~
  ~ Software distributed under the License is distributed on an "AS IS"
  ~ basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  ~ License for the specific language governing rights and limitations under
  ~ the License.
  ~
  ~ The Original Code is OpenELIS code.
  ~
  ~ Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
  --%>

<%!
    String basePath = "";
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/jquery.ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript"
        src="<%=basePath%>scripts/multiselectUtils.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?ver=<%= Versioning.getBuildNumber() %>" />

<script type="text/javascript">
    var currentSampleType = "";

    $jq(document).ready(function(){
        <%if( request.getAttribute(IActionConstants.FWD_SUCCESS) != null &&
        ((Boolean)request.getAttribute(IActionConstants.FWD_SUCCESS)) ) { %>
        if( typeof(showSuccessMessage) != 'undefined' ){
            showSuccessMessage( true );
        }
        <% } %>
    });

    function convertToJQueryMultiselect(){
        $jq("select[multiple]").asmSelect({
            removeLabel: "X"
        });

        $jq("select[multiple]").change(function (e, data) {
            handleMultiSelectChange(e, data);
        });

        $jq(".asmContainer").css("display", "inline-block");
    }

    function handleMultiSelectChange(e, data){
        enableNextStepButton();
    }

    function makeDirty() {
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

    function submitAction(target) {
        window.onbeforeunload = null;
        var form = document.getElementById("mainForm");
        form.action = target;
        form.submit();
    }


    function sampleTypeChanged(selectElement) {
        $jq("#verifySampleTypeSpan").text($jq(selectElement).find(":selected").text());
        currentSampleType = $jq(selectElement).find(":selected").text();
        if (selectElement.value != 0) {
            getAllTestsForSampleType(selectElement.value, testsForSampleTypeSuccess);
        }
    }

    function testsForSampleTypeSuccess(xhr) {
       //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response, i, option, currentSelection, replacementSelection, replacementSelectionSpan;


        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            currentSelection = $jq("#currentTestSelection");
            replacementSelectionSpan = $jq("#replacementTestSelectionSpan");
            currentSelection.empty();
            replacementSelectionSpan.empty();
            replacementSelection = $jq('<select>').attr('multiple', 'multiple').attr('title', 'multiple').attr('id', 'replacementTestSelection');
            replacementSelectionSpan.append(replacementSelection);
            option = $jq('<option/>').attr({"value" : 0});
            currentSelection.append(option);

            for( i = 0; i < response["tests"].length; i++){
                currentSelection.append(createOption(response["tests"][i]));
                if( response["tests"][i].isActive == 'Y'){
                    replacementSelection.append(createOption(response["tests"][i]));
                }
            }
        }

        if(!$jq('#includeInactive').is(":checked")){
            $jq(".inactiveTest").hide();
        }

        convertToJQueryMultiselect();

        $jq("#testSelection").show();
        $jq("#sampleSelectionDiv").hide();
        $jq("#nextStepButton").attr("disabled", "disabled");
        if($jq("#cancelOnly").is(":checked")){ $jq("#cancelOnly").click();}
    }

    function createOption(jsonTest){
        //alert(jsonTest.name);
        var  option = $jq('<option/>');
        option.attr({ 'value': jsonTest.id }).text(jsonTest.name);
        if( jsonTest.isActive == 'N'){
            option.addClass("inactiveTest");
        }
        return option;
    }

    function removeMultiSelect(){
        $jq('.asmListItemRemove').each(function(i) {
            $jq(this).click();
        });
    }
    function currentTestChanged(currentTestSelection){
        if( currentTestSelection.value == 0){
            $jq("#sampleSelectionDiv").hide();
            $jq("#nextStepButton").attr('disabled', 'disabled');
        }else{
            getPendingAnalysisForTest( currentTestSelection.value, analysisForTestSuccess);
        }
    }

    function analysisForTestSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response, i, checkbox, notStarted, replacementSelection, replacementSelectionSpan;

        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            addLabNoCoumns(response["notStarted"], $jq("#notStartedSamplesSpan"),$jq("#notStartedSamplesCheckBox"), true );
            addLabNoCoumns(response["technicianRejection"], $jq("#technicianRejectedSamplesSpan"), $jq("#technicianRejectedSamplesCheckBox"), true );
            addLabNoCoumns(response["biologistRejection"], $jq("#biologistRejectedSamplesSpan"), $jq("#biologistRejectedSamplesCheckBox"), true );
            addLabNoCoumns(response["notValidated"], $jq("#notValidatedSamplesSpan"),$jq("#notValidatedSamplesCheckBox"), false );
        }
        enableNextStepButton();
        $jq("#sampleSelectionDiv").show();
    }

    function addLabNoCoumns(jsonAnalysisGroup, column, allCheckbox, checked){
        column.empty();
        if(jsonAnalysisGroup.length == 0){
            allCheckbox.removeAttr('checked');
            allCheckbox.attr('disabled', 'disabled');
            return;
        }
        allCheckbox.removeAttr('disabled');

        if( checked){
            allCheckbox.attr('checked','checked');
        }else{
            allCheckbox.removeAttr('checked');
        }

        for( i = 0; i < jsonAnalysisGroup.length; i++){
            checkbox = $jq('<input/>').attr('type', 'checkbox').attr('value', jsonAnalysisGroup[i]["id"]).change(enableNextStepButton).addClass("labNoSelect");
            if( checked){
                checkbox.attr('checked','checked');
            }
            column.append(checkbox);
            column.append(jsonAnalysisGroup[i]["labNo"]);
            column.append('<br>')
        }
    }


    function showHideInactiveTest(inactiveTestCheckbox){
        if( inactiveTestCheckbox.checked){
            $jq(".inactiveTest").show();
        }else{
            $jq(".inactiveTest").hide();
            $jq("#currentTestSelection").val(0);
        }
    }

    function applyAll(checkAll, spanId){
        if(checkAll.checked){
            $jq("#" + spanId + " > :input").each(function(){
                $jq(this).attr('checked', 'checked');
            });
        }else{
            $jq("#" + spanId + " > :input").each(function(){
                $jq(this).removeAttr('checked');
            });
        }
        enableNextStepButton();
    }

    function handleCancelOnly(checkboxElement){
        if( checkboxElement.checked){
            alert("<spring:message code="warning.test.batch.reassignment.cancel" />");
            $jq("#replacementTestSelectionSpan").hide();
        }else{
            $jq("#replacementTestSelectionSpan").show();
        }

        enableNextStepButton();
    }

    function enableNextStepButton(){
        if( ($jq(".labNoSelect:checked").length > 0) && ($jq("#cancelOnly").is(":checked") || $jq('.asmListItemRemove').length > 0)){
            $jq("#nextStepButton").removeAttr("disabled");
        }else{
            $jq("#nextStepButton").attr("disabled", "disabled");
        }
    }


    function restoreSelect(){
        $jq("#selectDiv").show();
        $jq("#verifyDiv").hide();

    }
    function nextStep() {
        $jq("#selectDiv").hide();
        buildVerify();
        $jq("#verifyDiv").show();
    }

    function buildVerify(){
        var jsonWad = {};
        var canceledTest = $jq("#currentTestSelection :selected");
        var toTestStrings = "";
        var count = 0;

        $jq(".testReplaceFrom").text(canceledTest.text());
        jsonWad["current"] = canceledTest.text();
        jsonWad["sampleType"] = currentSampleType;

        if($jq('#cancelOnly:checked').length == 1){
            $jq("#verifyReplaceMessage").hide();
            $jq("#verifyCancelMessage").show();
        }else{
            jsonWad["replace"] = [];
            $jq("#replacementTestSelection option:selected").each(function(i) {
                toTestStrings += ", " + $jq(this).text();
                jsonWad["replace"][count++] = $jq(this).val();
            });
            $jq("#testReplaceTo").text(toTestStrings);
            $jq("#verifyCancelMessage").hide();
            $jq("#verifyReplaceMessage").show();
        }

        setUpSampleVerification( "notStartedSamplesSpan",
                "verifyChangeNotStartedSamplesSpan",
                "verifyNoChangeNotStartedSamplesSpan",
                jsonWad["changeNotStarted"] = [],
                jsonWad["noChangeNotStarted"] = [] );
        setUpSampleVerification( "technicianRejectedSamplesSpan",
                "verifyChangeTechnicianRejectedSamplesSpan",
                "verifyNoChangeTechnicianRejectedSamplesSpan",
                jsonWad["changeTechReject"] = [],
                jsonWad["noChangeTechReject"] = []);
        setUpSampleVerification( "biologistRejectedSamplesSpan",
                "verifyChangeBiologistRejectedSamplesSpan",
                "verifyNoChangeBiologistRejectedSamplesSpan",
                jsonWad["changeBioReject"] = [],
                jsonWad["noChangeBioReject"] = []);
        setUpSampleVerification( "notValidatedSamplesSpan",
                "verifyChangeNotValidatedSamplesSpan",
                "verifyNoChangeNotValidatedSamplesSpan",
                jsonWad["changeNotValidated"] = [],
                jsonWad["noChangeNotValidated"] = []);

        $jq("#jsonWad").val(JSON.stringify(jsonWad));
    }

    function setUpSampleVerification( live, change, noChange, jsonChange, jsonNoChange){
        var count = 0;
        var verify = $jq("#" + change);
        verify.empty();
        $jq("#" + live + " :checked").each(function(){
            verify.append(this.nextSibling.nodeValue);
            verify.append("<br>");
            jsonChange[count++] = this.value;
        });

        count = 0;
        verify = $jq("#" + noChange);
        verify.empty();
        $jq("#" + live + " > input:not(:checked)").each(function(){
            verify.append(this.nextSibling.nodeValue);
            verify.append("<br>");
            jsonNoChange[count++] = this.value;
        });

    }
</script>

<form:hidden path="jsonWad" id="jsonWad" />

<h3><spring:message code="label.selectSampleType"/></h3>

<div id="selectDiv" >
    <c:if test="${not empty form.statusChangedList}" >
    <hr>
    <spring:message code="label.test.batch.status.change.warning" /><br><br>
    <div style="overflow: hidden">
    <div style="float:left; width:15%;overflow: hidden;">
        <spring:message code="label.sampleType" />:&nbsp;<c:out value="${form.statusChangedSampleType}" /><br><br>
        <spring:message code="label.currentTest" />:&nbsp;<c:out value="${form.statusChangedCurrentTest}" /><br><br>
        <c:out value="${form.statusChangedNextTest}" /><br>
    </div>
    <div style="float:left;overflow: hidden;">
        <table>
            <tr>
            <th width="30%"><spring:message code="result.sample.id" /></th>
            <th width="30%"><spring:message code="report.from" /></th>
            <th width="30%"><spring:message code="report.to" /></th>
            </tr>
            <c:forEach items="${form.statusChangedList}" var="bean">
                <tr>
                    <td><c:out value="${bean.labNo}" /></td>
                    <td><c:set value="${bean.oldStatus}" /></td>
                    <td><c:set value="${bean.newStatus}" /></td>
                </tr>
            </c:forEach>
        </table><br>
    </div>
    </div>
    <br>
    <hr>
    </c:if>
    <spring:message code="label.sampleType" /><br>

    <select onchange="sampleTypeChanged(this); makeDirty();" >
    <option value="0"></option>
    <c:forEach items="${form.sampleList}" var="sampleType">
    <option value="${sampleType.id}">${sampleType.value}</option>
    </c:forEach>
    </select>

    <div id="testSelection" style="overflow: hidden; display:none" >
    <br>
    <div id="currentTest" style="float:left; margin:0; width:33%;">
    <spring:message code="label.currentTest" /><br><br>
    <input type="checkbox" id="includeInactive" checked="checked" onchange="showHideInactiveTest(this)"><spring:message code="label.includeInactiveTests" /><br><br>
        <select class="required" id="currentTestSelection" onchange="currentTestChanged(this)" >
        </select>
    </div>

        <div id="replacementTest" style="float:left; margin:0;width:66%;">
    <spring:message code="label.replaceWith" /><br><br>
    <input type="checkbox" id="cancelOnly" onchange="handleCancelOnly(this)" ><spring:message code="label.cancel.test.no.replace" /><br><br>
        <span id="replacementTestSelectionSpan"><select multiple="multiple" id="replacementTestSelection" title="multiple" ></select></span>
    </div>

    </div>
    <div id="sampleSelectionDiv" style="overflow: hidden; display:none" >
    <br>
        <spring:message code="label.checkedWillBeModified"/><br><br>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.analysisNotStarted"/><br><br>
            <input id="notStartedSamplesCheckBox" type="checkbox" checked="checked" onchange='applyAll(this, "notStartedSamplesSpan")'><spring:message code="label.button.checkAll" /><br><br>
            <span id="notStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByTechnician"/><br><br>
            <input type="checkbox" id="technicianRejectedSamplesCheckBox" checked="checked" onchange='applyAll(this, "technicianRejectedSamplesSpan")'><spring:message code="label.button.checkAll" /><br><br>
            <span id="technicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByBiologist" /><br><br>
            <input type="checkbox" id="biologistRejectedSamplesCheckBox" checked="checked" onchange='applyAll(this, "biologistRejectedSamplesSpan")'><spring:message code="label.button.checkAll" /><br><br>
            <span id="biologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.notValidated"/><br><br>
            <input type="checkbox"  id="notValidatedSamplesCheckBox" onchange='applyAll(this, "notValidatedSamplesSpan")'><spring:message code="label.button.checkAll" /><br><br>
            <span id="notValidatedSamplesSpan" ></span>
        </div>
    </div>
    <div align="center">
        <input type="button" id="nextStepButton" onclick="nextStep()" disabled="disabled" value='<%=MessageUtil.getMessage("label.button.process")%>'>
    &nbsp;<button onclick='submitAction("MasterListsPage.do")' ><spring:message code="label.button.cancel"/></button>
    </div>
</div>
<div id="verifyDiv" style="display:none">
    <spring:message code="label.sampleType" />:&nbsp;<span id="verifySampleTypeSpan" ></span><br><br>
    <div id="verifyReplaceMessage" >
        <spring:message code="label.test.batch.replace.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<spring:message code="label.test.batch.replace.finish"/><span id="testReplaceTo" ></span>
    </div>
    <div id="verifyCancelMessage" >
        <spring:message code="label.test.batch.cancel.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<spring:message code="label.test.batch.cancel.finish"/>
    </div>

    <hr>
    <div id="verifyChangeDiv" style="overflow: hidden;" >
        <br>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.analysisNotStarted"/><br><br>
            <span id="verifyChangeNotStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByTechnician"/><br><br>
            <span id="verifyChangeTechnicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByBiologist" /><br><br>
            <span id="verifyChangeBiologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.notValidated"/><br><br>
            <span id="verifyChangeNotValidatedSamplesSpan" ></span>
        </div>
    </div>

    <hr>
    <spring:message code="label.test.batch.replace.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<spring:message code="label.test.batch.no.change.finish"/><span id="testReplaceTo" ></span><br><br>

    <div id="verifyNoChangeDiv" style="overflow: hidden;" >
        <br>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.analysisNotStarted"/><br><br>
            <span id="verifyNoChangeNotStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByTechnician"/><br><br>
            <span id="verifyNoChangeTechnicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.rejectedByBiologist" /><br><br>
            <span id="verifyNoChangeBiologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <spring:message code="label.notValidated"/><br><br>
            <span id="verifyNoChangeNotValidatedSamplesSpan" ></span>
        </div>
    </div>

    <div align="center">
        <button onclick='submitAction("BatchTestReassignment.do")' ><spring:message code="label.button.accept"/></button>&nbsp;
        <input type="button" onclick="restoreSelect()"  value='<%=MessageUtil.getMessage("label.button.reject")%>'>
    </div>
</div>

