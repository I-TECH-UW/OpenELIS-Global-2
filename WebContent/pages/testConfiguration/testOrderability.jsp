<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.common.util.Versioning" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
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

<script type="text/javascript" src="scripts/jquery-ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>


<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>'/>

<%!
    String basePath = "";
    int testCount = 0;
    int columnCount = 0;
    int columns = 4;
    int columnSize = (int) (100 / columns);
%>

<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    columnCount = 0;
    testCount = 0;
%>
<link rel="stylesheet" media="screen" type="text/css"
      href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%= Versioning.getBuildNumber() %>"/>

<script type="text/javascript">
    var backFunction = selectBack;

    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    $jq(document).ready( function() {
        configureForSelect();
    });

    function makeDirty() {
        function formWarning() {
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }

        window.onbeforeunload = formWarning;
    }

    function makeClean() {
        window.onbeforeunload = null;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }
    function checkedChanged(isActive, element) {
        var jqueriedElement = $jq(element);
        var activate, deactivate;

        if ((element.checked == isActive)) {
            jqueriedElement.parent().find("span").removeClass("altered");
            jqueriedElement.removeClass("activationChanged");
        } else {
            jqueriedElement.parent().find("span").addClass("altered");
            jqueriedElement.addClass("activationChanged");
        }

        deactivate = $jq(".active.activationChanged").length;
        activate = $jq(".inactive.activationChanged").length;

        $jq("#nextButtonSelect").attr('disabled', ( deactivate == 0 && activate == 0));

        if(  deactivate == 0 && activate == 0){
            makeClean();
        }else{
            makeDirty();
        }
    }


    function nextStepToConfirmation(){
        var deactivate = $jq(".active.activationChanged").length;
        var activate = $jq(".inactive.activationChanged").length;
        configureForConfirmation();
        confirmationStep(activate, deactivate);
        $jq("#activateSection input").prop("disabled", true);

        window.scrollTo(0,0);
    }

    function confirmationStep(activateNumber, deactivateNumber) {
        var jsonObj = {};

        jsonObj.activateTest = [];
        jsonObj.deactivateTest = [];

        if (activateNumber > 0) {
            listActivatedItems(jsonObj);
        }

        if (deactivateNumber > 0) {
            listDeactivatedItems(jsonObj);
        }

        $jq("#jsonChangeList").val(JSON.stringify(jsonObj));
    }

    function listActivatedItems( jsonObj){

        var activateTestList = $jq("#testActivateList");
        var jsonBlob, sampleType, activatedTests;

        $jq("#testActivate").show();
        $jq(".activeSampleType").each(function(){
            sampleType=$jq(this);
            activatedTests= sampleType.parent().find(".inactive.activationChanged");
            if(activatedTests.length > 0) {
                activateTestList.append("<br />");
                activateTestList.append(sampleType.text());
                activateTestList.append("<br />");
                activatedTests.each(function () {
                    jsonBlob = {};
                    jsonBlob.id = $jq(this).val();
                    jsonObj.activateTest[jsonObj.activateTest.length] = jsonBlob;
                    activateTestList.append("&nbsp;&nbsp;&nbsp;&nbsp;" + $jq(this).siblings("span").text());
                    activateTestList.append("<br />");

                });
            }
        });

    }

    function listDeactivatedItems(jsonObj) {
        var sampleType, deactivatedTests, jsonBlob;
        var deactivateTestList = $jq("#testDeactivateList");


        $jq("#testDeactivate").show();
        $jq(".activeSampleType").each(function () {
            sampleType = $jq(this);
            deactivatedTests = sampleType.parent().find(".active.activationChanged");

            if (deactivatedTests.length > 0) {
                deactivateTestList.append("<br />");
                deactivateTestList.append(sampleType.text());

                deactivateTestList.append("<br />");
                deactivatedTests.each(function () {
                    jsonBlob = {};
                    jsonBlob.id = $jq(this).val();
                    jsonObj.deactivateTest[jsonObj.deactivateTest.length] = jsonBlob;
                    deactivateTestList.append("&nbsp;&nbsp;&nbsp;&nbsp;" + $jq(this).siblings("span").text());
                    deactivateTestList.append("<br />");
                });
            }

        });
    }


    function navigateBack() {
        backFunction();
        window.scrollTo(0,0);
    }

    function selectBack(){
        submitAction('TestManagementConfigMenu.do');
    }

    function confirmBack(){
           configureForSelect();
    }
    function configureForSelect() {
        $jq("#step").text("<%=StringUtil.getMessageForKey("configuration.test.orderable")%>");
        $jq("#instructions").text("<bean:message key="instructions.test.order"/>");
        $jq("#activateSection input").prop("disabled", false);
        $jq(".selectHide").hide();
        $jq(".selectShow").show();
        $jq(".selectClear").empty();
        backFunction = selectBack;
    }


    function configureForConfirmation() {
        $jq("#step").text("<%=StringUtil.getMessageForKey("label.test.order.confirm")%>");
        $jq("#instructions").text("<%=StringUtil.getMessageForKey("instructions.test.activation.confirm")%>");
        $jq(".confirmHide").hide();
        $jq(".confirmShow").show();

        backFunction = confirmBack;
    }
    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.

        var form = window.document.forms[0];
        form.method = "POST";
        form.action = "TestOrderabilityUpdate.do";
        form.submit();
    }
</script>

<form>
    <html:hidden name='<%=formName%>' property="jsonChangeList" styleId="jsonChangeList"/>
</form>
<br>
<input type="button" value="<%= StringUtil.getMessageForKey("banner.menu.administration") %>"
       onclick="submitAction('MasterListsPage.do');"
       class="textButton"/> &rarr;
<input type="button" value="<%= StringUtil.getMessageForKey("configuration.test.management") %>"
       onclick="submitAction('TestManagementConfigMenu.do');"
       class="textButton"/>&rarr;
<span id="testActivationSelectionButton" class="selectHide confirmShow" style="display:none" >
<input type="button" value="<%=StringUtil.getMessageForKey("configuration.test.orderable")%>"
       onclick="configureForSelect();"
       class="textButton"/>&rarr;
</span>


<span id="testActivationConfirmation" class="selectHide confirmShow" style="display:none" ><%=StringUtil.getMessageForKey("label.confirmation")%></span>
<span id="testActivationSelection" class="selectShow confirmHide"><bean:message key="configuration.test.orderable"/></span>
<br><br>

<h1 id="step"></h1><br/>

<div class="indent"><span id="instructions"></span></div>
<br>

<div id="testActivate" class="selectHide" >
    <h4><%=StringUtil.getMessageForKey("label.test.orderable")%></h4>

    <div id="testActivateList" class="selectClear"></div>
    <br>
</div>
<div id="testDeactivate" class="selectHide">
    <h4><%=StringUtil.getMessageForKey("label.test.unorderable")%></h4>

    <div id="testDeactivateList" class="selectClear"></div>
    <br>
</div>

<div class="selectHide confirmShow" style="margin-left:auto; margin-right:auto;width: 40%;">
    <input type="button"
           class="confirmShow "
           value="<%=StringUtil.getMessageForKey("label.button.accept")%>"
           onclick="savePage();"
           id="acceptButton"
           style="display: none"/>

    <input type="button" value="<%=StringUtil.getMessageForKey("label.button.back")%>" onclick="navigateBack()" />
</div>

<hr/>
<div id="activateSection" class="indent">
    <logic:iterate id="activeBean" name="<%=formName%>" property="orderableTestList">
        <div>
            <span class="activeSampleType"><bean:write name="activeBean" property="sampleType.value"/>
                <html:hidden name="activeBean" property="sampleType.id"/>
            </span>

            <div class="indent">
                <bean:define id="testList" name='activeBean' property="activeTests" type="java.util.List<IdValuePair>"/>
                <% testCount = 0;%>
                <table width="100%">
                    <% while (testCount < testList.size()) {%>
                    <tr>
                        <%
                            columnCount = 0;
                        %>
                        <% while (testCount < testList.size() && (columnCount < columns)) {%>
                        <td width='<%=columnSize + "%"%>'>
                            <input type="checkbox"
                                   class="active"
                                   value="<%=testList.get(testCount).getId()%>"
                                   onchange="checkedChanged(true,this)"
                                   checked>
                            <span><%= testList.get(testCount).getValue()%></span>
                            <%
                                testCount++;
                                columnCount++;
                            %></td>
                        <% } %>
                        <% while (columnCount < columns) {
                            columnCount++; %>
                        <td width='<%=columnSize + "%"%>'></td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>

                <bean:define id="testList" name='activeBean' property="inactiveTests"
                             type="java.util.List<IdValuePair>"/>
                <% testCount = 0; %>
                <table width="100%">
                    <% while (testCount < testList.size()) {%>
                    <tr>
                        <%
                            columnCount = 0;
                        %>
                        <% while (testCount < testList.size() && (columnCount < columns)) {%>
                        <td width='<%=columnSize + "%"%>'>
                            <input type="checkbox"
                                   class="inactive"
                                   value="<%=testList.get(testCount).getId()%>"
                                   onchange="checkedChanged(false,this)">
                            <span><%= testList.get(testCount).getValue()%></span>
                            <%
                                testCount++;
                                columnCount++;
                            %></td>
                        <% } %>
                        <% while (columnCount < columns) {
                            columnCount++; %>
                        <td width='<%=columnSize + "%"%>'></td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </logic:iterate>
</div>

<div class="selectShow confirmHide" style="margin-left:auto; margin-right:auto;width: 40%;">
    <input type="button"
           value="<%= StringUtil.getMessageForKey("label.button.next") %>"
           disabled="disabled"
           onclick="nextStepToConfirmation();"
           id="nextButtonSelect"/>

    <input type="button" value="<%=StringUtil.getMessageForKey("label.button.cancel")%>" onclick="navigateBack()" />


</div>