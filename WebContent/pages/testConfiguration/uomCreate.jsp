<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.common.util.Versioning,
         		us.mn.state.health.lims.testconfiguration.action.TestSectionCreateAction" %>

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

<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>'/>
<bean:define id="testList" name='<%=formName%>' property="existingUomList" type="java.util.List"/>
<bean:define id="inactiveTestList" name='<%=formName%>' property="inactiveUomList" type="java.util.List"/>
<bean:define id="englishSectionNames" name='<%=formName%>' property="existingEnglishNames" type="String"/>
<bean:define id="frenchSectionNames" name='<%=formName%>' property="existingFrenchNames" type="String"/>


<%!
    int testCount = 0;
    int columnCount = 0;
    int columns = 4;
%>

<%
    columnCount = 0;
    testCount = 0;
%>
<form>
<script type="text/javascript">
    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    function makeDirty(){
        function formWarning(){
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }
        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }

    function confirmValues() {
        var hasError = false;
        $jq(".required").each(function () {
            var input = $jq(this);
            if (!input.val() || input.val().strip().length == 0) {
                input.addClass("error");
                hasError = true;
            }
        });

        if (hasError) {
            alert("<%=StringUtil.getMessageForKey("error.all.required")%>");
        } else {
            $jq(".required").each(function () {
                var element = $jq(this);
                element.prop("readonly", true);
                element.addClass("confirmation");
            });
            $jq(".requiredlabel").each(function () {
                $jq(this).hide();
            });
            $jq("#editButtons").hide();
            $jq("#confirmationButtons").show();
            $jq("#confirmationMessage").show();
            $jq("#action").text("<%=StringUtil.getMessageForKey("label.confirmation")%>");
        }
    }

    function rejectConfirmation() {
        $jq(".required").each(function () {
            var element = $jq(this);
            element.removeProp("readonly");
            element.removeClass("confirmation");
        });
        $jq(".requiredlabel").each(function () {
            $jq(this).show();
        });

        $jq("#editButtons").show();
        $jq("#confirmationButtons").hide();
        $jq("#confirmationMessage").hide();
        $jq("#action").text("<%=StringUtil.getMessageForKey("label.button.edit")%>");
    }

    function handleInput(element, locale) {
        var englishNames = "<%= englishSectionNames %>".toLowerCase();
        var frenchNames = "<%= frenchSectionNames %>".toLowerCase();
        var duplicate = false;
        if( locale == 'english'){
            duplicate = englishNames.indexOf( '<%=TestSectionCreateAction.NAME_SEPARATOR%>' + element.value.toLowerCase() + '<%=TestSectionCreateAction.NAME_SEPARATOR%>') != -1;
        }else{
            duplicate = frenchNames.indexOf( '<%=TestSectionCreateAction.NAME_SEPARATOR%>' + element.value.toLowerCase() + '<%=TestSectionCreateAction.NAME_SEPARATOR%>') != -1;
        }

        if(duplicate){
            $jq(element).addClass("error");
            alert("<bean:message key="configuration.uom.create.duplicate" />" );
        }else{
            $jq(element).removeClass("error");
        }

        makeDirty();
    }

    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = window.document.forms[0];
        form.action = "UomCreateUpdate.do";
        form.submit();
    }
</script>


    <input type="button" value="<%= StringUtil.getMessageForKey("banner.menu.administration") %>"
           onclick="submitAction('MasterListsPage.do');"
           class="textButton"/>&rarr;
    <input type="button" value="<%= StringUtil.getMessageForKey("configuration.test.management") %>"
           onclick="submitAction('TestManagementConfigMenu.do');"
           class="textButton"/>&rarr;
    <input type="button" value="<%= StringUtil.getMessageForKey("configuration.uom.manage") %>"
           onclick="submitAction('UomManagement.do');"
           class="textButton"/>&rarr;

<%=StringUtil.getMessageForKey( "configuration.uom.create" )%>
<br><br>

<div id="editDiv" >
    <h1 id="action"><bean:message key="label.button.edit"/></h1>
    <h2><bean:message key="configuration.uom.create"/> </h2>

    <table>
        <tr>
            <th colspan="2" style="text-align: center"><bean:message key="uom.new"/></th>
        </tr>
        <tr>
            <td style="text-align: center"><bean:message key="label.english"/></td>
            
        </tr>
        <tr>
            <td><span class="requiredlabel">*</span><html:text property="uomEnglishName" name="<%=formName%>" size="40"
                                                               styleClass="required"
                                                               onchange="handleInput(this, 'english');checkForDuplicates('english');"/>
            </td>
            <%-- 
            <td><span class="requiredlabel">*</span><html:text property="uomFrenchName" name="<%=formName%>" size="40"
                                                               styleClass="required" onchange="handleInput(this, 'french');"/>
            </td>
            --%>
        </tr> 
    </table>
    <div id="confirmationMessage" style="display:none">
        <h4><bean:message key="configuration.uom.confirmation.explain" /></h4>
    </div>
    <div style="text-align: center" id="editButtons">
        <input type="button" value="<%=StringUtil.getMessageForKey("label.button.next")%>"
               onclick="confirmValues();"/>
        <input type="button" value="<%=StringUtil.getMessageForKey("label.button.previous")%>"
               onclick="submitAction('UomManagement.do');"/>
    </div>
    <div style="text-align: center; display: none;" id="confirmationButtons">
        <input type="button" value="<%=StringUtil.getMessageForKey("label.button.accept")%>"
               onclick="savePage();"/>
        <input type="button" value="<%=StringUtil.getMessageForKey("label.button.reject")%>"
               onclick='rejectConfirmation();'/>
    </div>
</div>

<h3><bean:message key="uom.existing" /></h3>
<table width="80%">
    <% while(testCount < testList.size()){%>
    <tr>
        <td width='<%= 100/columns + "%"%>'><%= ((IdValuePair)testList.get(testCount)).getValue()%>
            <%
                testCount++;
                columnCount = 1;
            %></td>
        <% while(testCount < testList.size() && ( columnCount < columns )){%>
        <td width='<%= 100/columns + "%"%>'><%= ((IdValuePair)testList.get(testCount)).getValue()%>
            <%
                testCount++;
                columnCount++;
            %></td>
        <% } %>

    </tr>
    <% } %>
</table>
