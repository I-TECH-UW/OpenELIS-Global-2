<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.common.util.Versioning,
         		java.util.List,
         		java.util.ArrayList,
         		spring.mine.testconfiguration.form.TestRenameEntryForm" %>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %> --%>
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

<c:set var="formName" value="${form.formName}" />
<c:set var="testList" value="${form.testList}" />

<%!
    int testCount = 0;
    int columnCount = 0;
    int columns = 3;
    List testList;
%>

<%
    columnCount = 0;
    testCount = 0;
    testList =  ((TestRenameEntryForm) request.getAttribute("form")).getTestList(); 
%>
<script type="text/javascript">
    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    function makeDirty(){
        function formWarning(){
            return "<spring:message code="banner.menu.dataLossWarning"/>";
        }
        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }

    function setForEditing(testId, name) {
        $jq("#editDiv").show();
        $jq("#testName").text(name);
        $jq(".error").each(function (index, value) {
            value.value = "";
            $jq(value).removeClass("error");
            $jq(value).removeClass("confirmation");
        });
        $jq("#testId").val(testId);
        $jq(".test").each(function () {
            var element = $jq(this);
            element.prop("disabled", "disabled");
            element.addClass("disabled-text-button");
        });
        getTestNames(testId, testNameSuccess);
    }

    function testNameSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;


        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            $jq("#nameEnglish").text(response["name"]["english"]);
            $jq("#nameFrench").text(response["name"]["french"]);
            $jq("#reportNameEnglish").text(response["reportingName"]["english"]);
            $jq("#reportNameFrench").text(response["reportingName"]["french"]);
            $jq(".required").each(function () {
                $jq(this).val("");
            });
        }

        window.onbeforeunload = null;
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
            alert('<%=StringUtil.getMessageForKey("error.all.required")%>');
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
            $jq("#action").text('<%=StringUtil.getMessageForKey("label.confirmation")%>');
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
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.button.edit")%>');
    }

    function cancel() {
        $jq("#editDiv").hide();
        $jq("#testId").val("");
        $jq(".test").each(function () {
            var element = $jq(this);
            element.removeProp("disabled");
            element.removeClass("disabled-text-button");
        });
        window.onbeforeunload = null;
    }

    function handleInput(element) {
        $jq(element).removeClass("error");
        makeDirty();
    }

    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = window.document.forms[0];
        form.action = "TestRenameUpdate.do";
        form.submit();
    }
</script>
<form:form modelAttribute="form" >
<form:hidden path="testId" />
<input type="button" value='<%= StringUtil.getMessageForKey("banner.menu.administration") %>'
       onclick="submitAction('MasterListsPage.do');"
       class="textButton"/> &rarr;
<input type="button" value='<%= StringUtil.getMessageForKey("configuration.test.management") %>'
       onclick="submitAction('TestManagementConfigMenu.do');"
       class="textButton"/>&rarr;
<%=StringUtil.getMessageForKey( "label.testName" )%>
<br><br>

<div id="editDiv" style="display: none">
    <h1 id="action"><spring:message code="label.button.edit"/></h1>

    <h2><%=StringUtil.getMessageForKey( "sample.entry.test" )%>:<span id="testName"></span></h2>
    <br>
    <table>
        <tr>
            <td></td>
            <th colspan="2" style="text-align: center"><spring:message code="test.testName"/></th>
            <th colspan="2" style="text-align: center"><spring:message code="test.testName.reporting"/></th>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: center"><spring:message code="label.english"/></td>
            <td style="text-align: center"><spring:message code="label.french"/></td>
            <td style="text-align: center"><spring:message code="label.english"/></td>
            <td style="text-align: center"><spring:message code="label.french"/></td>
        </tr>
        <tr>
            <td style="padding-right: 20px"><spring:message code="label.current"/>:</td>
            <td id="nameEnglish" style="padding-left: 10px"></td>
            <td id="nameFrench" style="padding-left: 10px"></td>
            <td id="reportNameEnglish" style="padding-left: 10px"></td>
            <td id="reportNameFrench" style="padding-left: 10px"></td>
        </tr>
        <tr>
            <td style="padding-right: 20px"><spring:message code="label.new"/>:</td>
            <td><span class="requiredlabel">*</span><form:input path="nameEnglish"  size="40"
                                                               styleClass="required"
                                                               onchange="handleInput(this);"/>
            </td>
            <td><span class="requiredlabel">*</span><form:input path="nameFrench"  size="40"
                                                               styleClass="required" onchange="handleInput(this);"/>
            </td>
            <td><span class="requiredlabel">*</span><form:input path="reportNameEnglish"
                                                               size="40" styleClass="required"
                                                               onchange="handleInput(this);"/>
            </td>
            <td><span class="requiredlabel">*</span><form:input path="reportNameFrench"
                                                               size="40" styleClass="required"
                                                               onchange="handleInput(this);"/>
            </td>
        </tr>
    </table>
    <div style="text-align: center" id="editButtons">
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.next")%>'
               onclick="confirmValues();"/>
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous")%>'
               onclick='cancel()'/>
    </div>
    <div style="text-align: center; display: none;" id="confirmationButtons">
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.accept")%>'
               onclick="savePage();"/>
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.reject")%>'
               onclick='rejectConfirmation();'/>
    </div>
    <br><br>
</div>

<table>
    <% while(testCount < testList.size()){%>
    <tr>
        <td><input type="button" value='<%= ((IdValuePair)testList.get(testCount)).getValue() %>'
                   onclick="setForEditing( '<%= ((IdValuePair)testList.get(testCount)).getId() + "', '" + ((IdValuePair)testList.get(testCount)).getValue() %>');"
                   class="textButton test"/>
            <%
                testCount++;
                columnCount = 1;
            %></td>
        <% while(testCount < testList.size() && ( columnCount < columns )){%>
        <td><input type="button" value='<%= ((IdValuePair)testList.get(testCount)).getValue() %>'
                   onclick="setForEditing( '<%= ((IdValuePair)testList.get(testCount)).getId() + "', '" + ((IdValuePair)testList.get(testCount)).getValue() %>' );"
                   class="textButton test"/>
            <%
                testCount++;
                columnCount++;
            %></td>
        <% } %>

    </tr>
    <% } %>
</table>

<br>
<input type="button" value='<%= StringUtil.getMessageForKey("label.button.finished") %>'
       onclick="submitAction('TestManagementConfigMenu.do');"/>
</form:form>