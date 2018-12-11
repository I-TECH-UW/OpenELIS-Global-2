<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="java.util.List,
         		us.mn.state.health.lims.common.action.IActionConstants,
          		us.mn.state.health.lims.common.util.IdValuePair,
          		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.common.util.Versioning,
         		us.mn.state.health.lims.test.action.BatchTestStatusChangeBean" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

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

<bean:define id='formName' value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>'/>
<bean:define id="sampleTypeList" name='<%=formName%>' property="sampleList" type="java.util.List"/>

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
        if (typeof(showSuccessMessage) != 'undefinded') {
            showSuccessMessage(false); //refers to last save
        }
        // Adds warning when leaving page if content has been entered into makeDirty form fields
        function formWarning() {
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }

        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        window.onbeforeunload = null;
        var form = window.document.forms[0];
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
            alert("<bean:message key="warning.test.batch.reassignment.cancel" />");
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

<html:hidden styleId="jsonWad" name='<%=formName%>' property="jsonWad" />

<h3><bean:message key="label.selectSampleType"/></h3>

<div id="selectDiv" >
    <logic:notEmpty name="<%=formName%>" property="statusChangedList" >
    <hr>
    <bean:message key="label.test.batch.status.change.warning" /><br><br>
    <div style="overflow: hidden">
    <div style="float:left; width:15%;overflow: hidden;">
        <bean:message key="label.sampleType" />:&nbsp;<bean:write name='<%=formName%>' property="statusChangedSampleType" /><br><br>
        <bean:message key="label.currentTest" />:&nbsp;<bean:write name='<%=formName%>' property="statusChangedCurrentTest" /><br><br>
        <bean:write name='<%=formName%>' property="statusChangedNextTest" /><br>
    </div>
    <div style="float:left;overflow: hidden;">
        <table>
            <tr>
            <th width="30%"><bean:message key="result.sample.id" /></th>
            <th width="30%"><bean:message key="report.from" /></th>
            <th width="30%"><bean:message key="report.to" /></th>
            </tr>
            <logic:iterate id="bean" name="<%=formName%>" property="statusChangedList" type="BatchTestStatusChangeBean" >
                <tr>
                    <td><bean:write name="bean" property="labNo" /></td>
                    <td><bean:write name="bean" property="oldStatus" /></td>
                    <td><bean:write name="bean" property="newStatus" /></td>
                </tr>
            </logic:iterate>
        </table><br>
    </div>
    </div>
    <br>
    <hr>
    </logic:notEmpty>
    <bean:message key="label.sampleType" /><br>

    <select onchange="sampleTypeChanged(this); makeDirty();" >
    <option value="0"></option>
    <% for (IdValuePair pair : (List<IdValuePair>) sampleTypeList) {%>
    <option value="<%=pair.getId()%>" ><%=pair.getValue()%></option>
    <% } %>
    </select>

    <div id="testSelection" style="overflow: hidden; display:none" >
    <br>
    <div id="currentTest" style="float:left; margin:0; width:33%;">
    <bean:message key="label.currentTest" /><br><br>
    <input type="checkbox" id="includeInactive" checked="checked" onchange="showHideInactiveTest(this)"><bean:message key="label.includeInactiveTests" /><br><br>
        <select class="required" id="currentTestSelection" onchange="currentTestChanged(this)" >
        </select>
    </div>

        <div id="replacementTest" style="float:left; margin:0;width:66%;">
    <bean:message key="label.replaceWith" /><br><br>
    <input type="checkbox" id="cancelOnly" onchange="handleCancelOnly(this)" ><bean:message key="label.cancel.test.no.replace" /><br><br>
        <span id="replacementTestSelectionSpan"><select multiple="multiple" id="replacementTestSelection" title="multiple" ></select></span>
    </div>

    </div>
    <div id="sampleSelectionDiv" style="overflow: hidden; display:none" >
    <br>
        <bean:message key="label.checkedWillBeModified"/><br><br>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.analysisNotStarted"/><br><br>
            <input id="notStartedSamplesCheckBox" type="checkbox" checked="checked" onchange='applyAll(this, "notStartedSamplesSpan")'><bean:message key="label.button.checkAll" /><br><br>
            <span id="notStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByTechnician"/><br><br>
            <input type="checkbox" id="technicianRejectedSamplesCheckBox" checked="checked" onchange='applyAll(this, "technicianRejectedSamplesSpan")'><bean:message key="label.button.checkAll" /><br><br>
            <span id="technicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByBiologist" /><br><br>
            <input type="checkbox" id="biologistRejectedSamplesCheckBox" checked="checked" onchange='applyAll(this, "biologistRejectedSamplesSpan")'><bean:message key="label.button.checkAll" /><br><br>
            <span id="biologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.notValidated"/><br><br>
            <input type="checkbox"  id="notValidatedSamplesCheckBox" onchange='applyAll(this, "notValidatedSamplesSpan")'><bean:message key="label.button.checkAll" /><br><br>
            <span id="notValidatedSamplesSpan" ></span>
        </div>
    </div>
    <div align="center">
        <input type="button" id="nextStepButton" onclick="nextStep()" disabled="disabled" value='<%=StringUtil.getMessageForKey("label.button.process")%>'>
    &nbsp;<button onclick='submitAction("MasterListsPage.do")' ><bean:message key="label.button.cancel"/></button>
    </div>
</div>
<div id="verifyDiv" style="display:none">
    <bean:message key="label.sampleType" />:&nbsp;<span id="verifySampleTypeSpan" ></span><br><br>
    <div id="verifyReplaceMessage" >
        <bean:message key="label.test.batch.replace.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<bean:message key="label.test.batch.replace.finish"/><span id="testReplaceTo" ></span>
    </div>
    <div id="verifyCancelMessage" >
        <bean:message key="label.test.batch.cancel.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<bean:message key="label.test.batch.cancel.finish"/>
    </div>

    <hr>
    <div id="verifyChangeDiv" style="overflow: hidden;" >
        <br>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.analysisNotStarted"/><br><br>
            <span id="verifyChangeNotStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByTechnician"/><br><br>
            <span id="verifyChangeTechnicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByBiologist" /><br><br>
            <span id="verifyChangeBiologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.notValidated"/><br><br>
            <span id="verifyChangeNotValidatedSamplesSpan" ></span>
        </div>
    </div>

    <hr>
    <bean:message key="label.test.batch.replace.start" />&nbsp;<span class="testReplaceFrom" ></span>&nbsp;<bean:message key="label.test.batch.no.change.finish"/><span id="testReplaceTo" ></span><br><br>

    <div id="verifyNoChangeDiv" style="overflow: hidden;" >
        <br>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.analysisNotStarted"/><br><br>
            <span id="verifyNoChangeNotStartedSamplesSpan" ></span>
        </div>
        <div  style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByTechnician"/><br><br>
            <span id="verifyNoChangeTechnicianRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.rejectedByBiologist" /><br><br>
            <span id="verifyNoChangeBiologistRejectedSamplesSpan" ></span>
        </div>
        <div style="float:left; margin:0;width:25%;">
            <bean:message key="label.notValidated"/><br><br>
            <span id="verifyNoChangeNotValidatedSamplesSpan" ></span>
        </div>
    </div>

    <div align="center">
        <button onclick='submitAction("BatchTestReassignmentUpdate.do")' ><bean:message key="label.button.accept"/></button>&nbsp;
        <input type="button" onclick="restoreSelect()"  value='<%=StringUtil.getMessageForKey("label.button.reject")%>'>
    </div>
</div>

